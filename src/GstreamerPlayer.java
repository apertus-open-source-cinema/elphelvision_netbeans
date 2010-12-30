/*! Copyright (C) 2010 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *! Description: Video Stream Player class based on VLC java bindings
-----------------------------------------------------------------------------**
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

import java.awt.Canvas;
import java.io.File;
import javax.swing.JFrame;
import org.gstreamer.Caps;
import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.Gst;
import org.gstreamer.Pipeline;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin;
import org.gstreamer.swing.VideoComponent;

public class GstreamerPlayer {

    private ElphelVision Parent;
    private PlayBin playbin;
    private Pipeline pipe;
    private Element videosrc;
    private Element videofilter;
    private Element videosink;
    private String[] args;
    private VideoComponent videoComponent;

    GstreamerPlayer(ElphelVision parent) {
        this.Parent = parent;

        args = new String[2];
        args[0] = "";
        args[1] = "";

        //args = Gst.init("GstreamerVideoPlayer", args);
    }

    public void close() {
        //mediaPlayer.stop();
    }

    public void SetVideocomponent(VideoComponent newcomponent) {
        videoComponent = newcomponent;
    }

    public void ToggleFullscreen() {
    }

    public void SetScale(float factor) {
        //mediaPlayer.setScale(factor);
    }

    public void PlayVideoStream() {

        String rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP() + ":554 latency=30 ! rtpjpegdepay ! jpegdec name=elphelstream";
        pipe = Pipeline.launch(rtspsource);

        //pipe = new Pipeline("GstreamerViewer");

        //videosrc = ElementFactory.make("videotestsrc", "source");
        //videosrc = ElementFactory.make("rtspsource ", "source");
        ///videosrc.setCaps(Caps.fromString("location=rtsp://192.168.10.141:554 latency=30"));
        //Element rtpjpegdepay = ElementFactory.make("rtpjpegdepay", "rtpjpegdepay");
        //Element jpegdec = ElementFactory.make("jpegdec", "jpegdec");

        //videosink = ElementFactory.make("xvimagesink", "sink");

        Element videosink = videoComponent.getElement();
        pipe.add(videosink);
        pipe.getElementByName("elphelstream").link(videosink);

        //playbin.setInputFile(new File(args[0]));
        //pipe.addMany(videosrc, rtpjpegdepay, jpegdec, videosink);
        //videosrc.link(videosink);
        //Element.linkMany(videosrc, rtpjpegdepay, jpegdec, videosink);
        pipe.setState(State.PLAYING);
        Gst.main();
        pipe.setState(State.NULL);


        //gst-launch rtspsrc location=rtsp://192.168.10.141:554 latency=30 ! rtpjpegdepay ! jpegdec ! xvimagesink

        //VideoComponent videoComponent = new VideoComponent();
        //videosink = videoComponent.getElement();
        //playbin.setVideoSink(videosink);


        //Element.linkMany(videosrc, videosink);

    }

    /*public void Overlay(Window overlay) {
    mediaPlayer.setOverlay(overlay);
    mediaPlayer.enableOverlay(true);
    }*/
    public void PlayVideoFile(String file) {
        //mediaPlayer.playMedia("http://" + Parent.Camera.GetIP() + file);
    }
}
