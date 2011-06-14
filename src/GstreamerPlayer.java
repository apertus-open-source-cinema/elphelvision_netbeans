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

import java.awt.Color;
import java.awt.Dimension;
//import org.gstreamer.elements.PlayBin;
import java.util.List;
import org.gstreamer.Clock;
import org.gstreamer.swing.VideoComponent;
import org.gstreamer.Element;
import org.gstreamer.Gst;
import org.gstreamer.Pipeline;
import org.gstreamer.State;

public class GstreamerPlayer {

    private ElphelVision Parent;
    private String[] args;
    private VideoComponent videoComponent;
    private Pipeline pipe;
    //private VideoPlayer GSTPlayer;

    GstreamerPlayer(ElphelVision parent) {
        this.Parent = parent;

        args = new String[2];
        args[1] = "";
        args[0] = "";/*
        //args[0] = "rtsp://127.0.0.1:554";
        args[0] = "rtsp://192.168.10.141:554";
        //args[0] = "rtsp://" + Parent.Camera.GetIP() + ":554";
        args[1] = "";
         */
        args = Gst.init("Test Player", args);

        videoComponent = new VideoComponent();
        videoComponent.setPreferredSize(new Dimension(850, 480));

        //GSTPlayer = new VideoPlayer(args[0]);

        /*
        
        args = new String[2];
        args[0] = "";
        args[1] = "";
        
        args = Gst.init("GstreamerVideoPlayer", args);
         * 
         */
    }

    public VideoComponent GetGSTVideoComponent() {
        return videoComponent;
    }

    public void SetVideocomponent(VideoComponent newcomponent) {
        videoComponent = newcomponent;
    }

    public void ToggleFullscreen() {
    }

    public void SetScale(float factor) {
        //mediaPlayer.setScale(factor);
    }

    public void close() {
        Parent.WriteLogtoConsole("Stopping Gstreamer Video Player");
        //pipe.setState(State.PAUSED);
        pipe.pause();
        State test = pipe.getState();
        int i = 1;
        //Gst.deinit(); // more trouble
        //pipe = null; //do we really need this?
    }

    public void PlayVideoStream() {
        /*
        final List<URI> playList = new LinkedList<URI>();
        for (String arg : args) {
        playList.add(new File(arg).toURI());
        }
        
        GSTPlayer.setPreferredSize(new Dimension(640, 480));
        GSTPlayer.setControlsVisible(false);
        //player.setKeepAspect(true);
        GSTPlayer.getMediaPlayer().setPlaylist(playList);
        GSTPlayer.getMediaPlayer().play();
        
         */

        //GstreamerPlayer.SetVideocomponent(GstreamerVideoComponent);
        String rtspsource = "";
        if (Parent.Camera.GetColorMode() == ColorMode.RGB) {
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! decodebin ! ffmpegcolorspace name=elphelstream";
        } else if (Parent.Camera.GetColorMode() == ColorMode.JP46) {
            //            rtspsrc location=rtsp://" + Parent.Camera.GetIP() + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 ! ffmpegcolorspace ! videorate ! "video/x-raw-yuv, format=(fourcc)I420, width=(int)1920, height=(int)1088, framerate=(fraction)25/1" ! xvimagesink sync=false max-lateness=-1
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 ! ffmpegcolorspace  name=elphelstream";
        } else {
            //TODO in this mode we dont see anything from the non-jpeg compliant stream so the jp46 filter wont help, but what else should we show?
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 name=elphelstream";
        }

        //Notes

        //LUT
        //gst-launch rtspsrc location=rtsp://192.168.10.141:554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! ffmpegcolorspace ! videorate ! coloreffects preset=heat ! ffmpegcolorspace ! autovideosink -v

        //edge detection: 
        //gst-launch rtspsrc location=rtsp://192.168.10.141:554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! ffmpegcolorspace ! videorate ! edgetv ! ffmpegcolorspace ! autovideosink -v

        // kind of scopes
        //gst-launch rtspsrc location=rtsp://192.168.10.141:554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! ffmpegcolorspace ! videorate ! revtv ! ffmpegcolorspace ! autovideosink -v

        pipe = Pipeline.launch(rtspsource);

        videoComponent.setKeepAspect(true);
        Element videosink = videoComponent.getElement();
        pipe.add(videosink);
        pipe.getElementByName("elphelstream").link(videosink);


        pipe.setState(State.PLAYING);
        List<Element> sinks = pipe.getSinks();

        /*
        
        String rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP() + ":554 latency=30 ! rtpjpegdepay ! jpegdec name=elphelstream";
        pipe = Pipeline.launch(rtspsource);
        
        //pipe = new Pipeline("GstreamerViewer");
        
        //videosrc = ElementFactory.make("videotestsrc", "source");
        //videosrc = ElementFactory.make("rtspsource ", "source");
        ///videosrc.setCaps(Caps.fromString("location=rtsp://192.168.10.141:554 latency=30"));
        //Element rtpjpegdepay = ElementFactory.make("rtpjpegdepay", "rtpjpegdepay");
        //Element jpegdec = ElementFactory.make("jpegdec", "jpegdec");
        
        Element videosink = ElementFactory.make("xvimagesink", "sink");
        
        //Element videosink = videoComponent.getElement();
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
        
        
        //Element.linkMany(videosrc, videosink);*/
    }

    public void RePlayVideoStream() {
        Parent.WriteLogtoConsole("Trying to restart Gstreamer Video Player");

        /*videoComponent = null;
        videoComponent = new VideoComponent();
        videoComponent.setPreferredSize(new Dimension(850, 480));

        args = new String[2];
        args[1] = "";
        args[0] = "";/*
        //args[0] = "rtsp://127.0.0.1:554";
        args[0] = "rtsp://192.168.10.141:554";
        //args[0] = "rtsp://" + Parent.Camera.GetIP() + ":554";
        args[1] = "";
         */
        /*args = Gst.init("Test Player", args);

        String rtspsource = "";
        if (Parent.Camera.GetColorMode() == ColorMode.RGB) {
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec name=elphelstream";
        } else if (Parent.Camera.GetColorMode() == ColorMode.JP46) {
            //            rtspsrc location=rtsp://" + Parent.Camera.GetIP()    + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 ! ffmpegcolorspace ! videorate ! "video/x-raw-yuv, format=(fourcc)I420, width=(int)1920, height=(int)1088, framerate=(fraction)25/1" ! xvimagesink sync=false max-lateness=-1
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 ! ffmpegcolorspace  name=elphelstream";
        } else {
            //TODO in this mode we dont see anything from the non-jpeg compliant stream so the jp46 filter wont help, but what else should we show?
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 name=elphelstream";
        }

        pipe = Pipeline.launch(rtspsource);

        videoComponent.setKeepAspect(true);
        Element videosink = videoComponent.getElement();
        pipe.add(videosink);
        pipe.getElementByName("elphelstream").link(videosink);
*/
        List<Element> sinks = pipe.getSinks();
        pipe.setClock(null);
        pipe.play();
        
        //pipe.setState(State.PLAYING);
        //pipe.play();
    }

    /*public void Overlay(Window overlay) {
    mediaPlayer.setOverlay(overlay);
    mediaPlayer.enableOverlay(true);
    }*/
    public void PlayVideoFile(String file) {
        //mediaPlayer.playMedia("http://" + Parent.Camera.GetIP() + file);
    }
}
