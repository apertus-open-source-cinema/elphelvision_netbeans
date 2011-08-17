/*! Copyright (C) 2009 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *!
 *!  This program is free software: you can redistribute it and/or modify
 *!  it under the terms of the GNU General Public License as published by
 *!  the Free Software Foundation, either version 3 of the License, or
 *!  (at your option) any later version.
 *!
 *!  This program is distributed in the hope that it will be useful,
 *!  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *!  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *!  GNU General Public License for more details.
 *!
 *!  You should have received a copy of the GNU General Public License
 *!  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *!
-----------------------------------------------------------------------------**/

import java.io.IOException;
import java.io.File;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;

public class AudioRecorder extends Thread {

    private TargetDataLine Dataline;
    private AudioInputStream AudioInputStream;
    private AudioFileFormat.Type RecAudioFileFormat;
    private AudioFormat RecAudioFormat;
    private File AudioFile;
    ElphelVision Parent;

    public AudioRecorder(ElphelVision parent) {
        Parent = parent;
    }

    public void StartRecording() {
        Dataline.start();
        this.start();
        Parent.WriteLogtoConsole("Audio Recording started");
    }

    public void StopRecording() {
        Dataline.stop();
        Dataline.close();
        Parent.WriteLogtoConsole("Audio Recording stopped");
    }

    public void SetFilename(String filename) {
        AudioFile = new File(filename);
    }

    public String[] GetAvailableAudioMixers() {
        // Get all mixers from the system - USB audio device will have its own mixer
        Info[] mixerinfo = AudioSystem.getMixerInfo();
        String[] Return = new String[mixerinfo.length];
        for (int i = 0; i < mixerinfo.length; i++) {
            AudioFormat[] formats = GetMixerCapabilities(i);
            if (formats != null) {
                Return[i] = mixerinfo[i].getName() + " " + mixerinfo[i].getDescription();
            }
        }
        return Return;
    }

    public AudioFormat[] GetMixerCapabilities(int MixerIndex) {
        Info[] mixerinfo = AudioSystem.getMixerInfo();

        // select the Mixer to record from
        Mixer mixer = AudioSystem.getMixer(mixerinfo[MixerIndex]);
        Line.Info[] Infos = mixer.getTargetLineInfo();
        for (int i = 0; i < Infos.length; i++) {
            if (Infos[i] instanceof DataLine.Info) {
                DataLine.Info dataLineInfo = (DataLine.Info) Infos[i];
                // these are the available formats of the selected mixer
                AudioFormat[] supportedFormats = dataLineInfo.getFormats();
                return supportedFormats;
            }
        }
        return null;
    }

    public void SetAudioOptions(int MixerIndex, int FormatIndex) {

        Info[] mixerinfo = AudioSystem.getMixerInfo();

        // select the mixer to record from
        Mixer mixer = AudioSystem.getMixer(mixerinfo[MixerIndex]);

        // get all available audio formats on that device
        AudioFormat[] supportedFormats = GetMixerCapabilities(MixerIndex);
        
        // we use WAV by default
        RecAudioFileFormat = AudioFileFormat.Type.WAVE;
        
        // Create AudioFormat that the audio hardware supports
        // 48KHz is hardcoded for now until we create a custom field in the settings for it
        if (supportedFormats[FormatIndex].getSampleRate() == -1) {
            RecAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 48000.0F, supportedFormats[FormatIndex].getSampleSizeInBits(), supportedFormats[FormatIndex].getChannels(), supportedFormats[FormatIndex].getFrameSize(), 48000.0F, supportedFormats[FormatIndex].isBigEndian());
        } else {
            RecAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, supportedFormats[FormatIndex].getSampleRate(), supportedFormats[FormatIndex].getSampleSizeInBits(), supportedFormats[FormatIndex].getChannels(), supportedFormats[FormatIndex].getFrameSize(), supportedFormats[FormatIndex].getSampleRate(), supportedFormats[FormatIndex].isBigEndian());
        }

        TargetDataLine targetDataLine = null;
        try {
            targetDataLine = AudioSystem.getTargetDataLine(RecAudioFormat, mixerinfo[MixerIndex]);
            targetDataLine.open(RecAudioFormat);
        } catch (LineUnavailableException e) {
            Parent.WriteErrortoConsole("unable to get a recording line");
        }
        Dataline = targetDataLine;
        AudioInputStream = new AudioInputStream(Dataline);
    }

    public void run() {
        try {
            AudioSystem.write(AudioInputStream, RecAudioFileFormat, AudioFile);
        } catch (IOException e) {
        }
    }
}

/*
 * How can I detect a buffer underrun or overrun?
The following is working reliably at least with the "Direct Audio Device" mixers:
SourceDataLine: underrun if (line.available() == line.getBufferSize())
SourceDataLine.available(): how much data can be written to the buffer. If the whole buffer can be written to, there is no data in the buffer to be rendered.
TargetDataLine: overrun if (line.available() == line.getBufferSize())
TargetDataLine.available(): how much data can be read from the buffer. If the whole buffer can be read, there is no space in the buffer for new data captured from the line.
 */
