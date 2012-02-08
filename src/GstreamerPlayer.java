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

//import java.awt.Color;
import java.awt.Dimension;
//import org.gstreamer.elements.PlayBin;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
//import org.gstreamer.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gstreamer.Bus;
import org.gstreamer.Message;
import org.gstreamer.swing.VideoComponent;
import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.Gst;
import org.gstreamer.GstException;
import org.gstreamer.GstObject;
import org.gstreamer.Pad;
import org.gstreamer.Pipeline;
import org.gstreamer.State;
import org.gstreamer.elements.PlayBin;
import org.gstreamer.elements.PlayBin2;
import org.gstreamer.elements.FileSrc;

public class GstreamerPlayer {

    private ElphelVision Parent;
    private String[] args;
    private VideoComponent videoComponent;
    private Pipeline pipe;
    //private VideoPlayer GSTPlayer;
    // private PlayBin2 Playbin;

    GstreamerPlayer(ElphelVision parent) {
        this.Parent = parent;

        args = new String[2];
        args[1] = "";
        args[0] = "";

        try {
            args = Gst.init("ElphelVision", args);
        } catch (GstException e) {
            Parent.WriteErrortoConsole("Gst.init error: " + e);
        }

//        Playbin = new PlayBin2("VideoPlayer");
//        rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 ! ffmpegcolorspace  name=elphelstream";
        //Playbin.setInputFile(new File("test.avi"));
        /*java.net.URI uri = null;
        try {
        uri = new URI("rtsp://" + Parent.Camera.GetIP()[0] + ":554");
        } catch (URISyntaxException ex) {
        Logger.getLogger(GstreamerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Playbin.setURI(uri);*/


        videoComponent = new VideoComponent();
        videoComponent.setPreferredSize(new Dimension(850, 480));
        //     Playbin.setVideoSink(videoComponent.getElement());


        /*
        Playbin.getBus().connect(new Bus.EOS() {
        
        public void endOfStream(GstObject source) {
        System.out.println("Finished playing file");
        Gst.quit();
        }
        });
        Playbin.getBus().connect(new Bus.ERROR() {
        
        public void errorMessage(GstObject source, int code, String message) {
        System.out.println("Error occurred: " + message);
        Gst.quit();
        }
        });
        Playbin.getBus().connect(new Bus.STATE_CHANGED() {
        
        public void stateChanged(GstObject source, State old, State current, State pending) {
        if (source == Playbin) {
        System.out.println("Pipeline state changed from " + old + " to " + current);
        }
        }
        });
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
        //todo
        //mediaPlayer.setScale(factor);
    }

    public void StopVideoStream() {
        Parent.WriteLogtoConsole("Stopping Gstreamer Video Player");

        pipe.setState(State.READY);

        //debugGST();

        Element videosink = videoComponent.getElement();
        videoComponent.repaint();
        pipe.getElementByName("elphelstream").unlink(videosink);
        pipe.remove(videosink);

        //debugGST();

        pipe.setState(State.NULL);

        //debugGST();

        List<Element> elements = pipe.getElementsRecursive();
        for (int i = 0; i < elements.size(); i++) {
            pipe.unlink(elements.get(i));
            pipe.remove(elements.get(i));
            elements.get(i).dispose();
        }

        List<Element> sources = pipe.getSources();
        for (int i = 0; i < sources.size(); i++) {
            pipe.unlink(sources.get(i));
            pipe.remove(sources.get(i));
            sources.get(i).dispose();
        }

        //debugGST();
        pipe = null;
        //debugGST();
    }

    public void debugGST() {

        List<Element> sinks = pipe.getSinks();
        List<Element> elements = pipe.getElementsRecursive();
        List<Element> sources = pipe.getSources();

        State state = pipe.getState();

        int a = 1;
    }

    public void PlayVideoStream() {
        //debugGST();

        String rtspsource = "";
        if (Parent.Camera.GetColorMode() == ColorMode.RGB) {
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=60 name=camerasource ! rtpjpegdepay ! jpegdec ! decodebin2 ! ffmpegcolorspace name=elphelstream";
        } else if (Parent.Camera.GetColorMode() == ColorMode.JP46) {
            //            rtspsrc location=rtsp://" + Parent.Camera.GetIP() + ":554 protocols=0x00000001 latency=50 ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 ! ffmpegcolorspace ! videorate ! "video/x-raw-yuv, format=(fourcc)I420, width=(int)1920, height=(int)1088, framerate=(fraction)25/1" ! xvimagesink sync=false max-lateness=-1
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=60 name=camerasource ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 ! ffmpegcolorspace  name=elphelstream";
        } else {
            //TODO in this mode we dont see anything from the non-jpeg compliant stream so the jp46 filter wont help, but what else should we show?
            rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP()[0] + ":554 protocols=0x00000001 latency=60 name=camerasource ! rtpjpegdepay ! jpegdec ! queue ! jp462bayer ! queue ! bayer2rgb2 method=0 name=elphelstream";
        }

        pipe = Pipeline.launch(rtspsource);

        videoComponent.setKeepAspect(true);
        Element videosink = videoComponent.getElement();
        pipe.add(videosink);

        pipe.getElementByName("elphelstream").link(videosink);

        pipe.getBus().connect(new Bus.EOS() {

            public void endOfStream(GstObject source) {
                System.out.println("Finished playing file");
                //Gst.quit();
            }
        });
        /*pipe.getBus().connect(new Bus.INFO() {
        
        public void infoMessage(GstObject source, int code, String message) {
        System.out.println("message: " + message);
        }
        });
        pipe.getBus().connect(new Bus.MESSAGE() {
        
        public void busMessage(Bus bus, Message message) {
        System.out.println("message: " + message);
        }
        });
        pipe.getBus().connect(new Bus.WARNING() {
        
        public void warningMessage(GstObject source, int code, String message) {
        System.out.println("warning: " + message);
        }
        });*/
        pipe.getBus().connect(new Bus.ERROR() {

            public void errorMessage(GstObject source, int code, String message) {
                System.out.println("Error occurred: " + message);
                Gst.quit();
            }
        });
        /*pipe.getBus().connect(new Bus.STATE_CHANGED() {
        
        public void stateChanged(GstObject source, State old, State current, State pending) {
        if (source == pipe) {
        System.out.println("Pipeline state changed from " + old + " to " + current);
        }
        }
        });*/

        pipe.setState(State.PLAYING);

        /*
        Playbin.add(videosink);
        //     Pipeline pipe = new Pipeline("SimplePipeline");
        /*
        Element src = ElementFactory.make("rtspsrc", "elphelsrc");
        src.set("location", "rtsp://" + Parent.Camera.GetIP()[0] + ":554");
        src.set("protocols", "00000001"); // "0x00000001" doesnt work
        src.set("latency", "50");
        Playbin.add(src);*/
        //Playbin.getElementByName("elphelsrc").link(Playbin.getSinks().get(0));
        //Element sink = ElementFactory.make("fakesink", "Destination");
        //Playbin.addMany(src, sink);
        //src.link(sink);*/
        // Playbin2
        /*
        try {
        Playbin.setURI(new URI("rtsp://" + Parent.Camera.GetIP()[0] + ":554"));
        } catch (URISyntaxException ex) {
        Logger.getLogger(GstreamerPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //.getElementByName("uri").set("protocols", "00000001");
        //Playbin.getElementByName("uri").set("latency", "50");
        
        //Playbin.setInputFile(new File("test.avi")); // works
        
        // Playbin.set("buffer-duration", 0); // NO Effect
        // Playbin.set("buffer-size", 0); // NO Effect
        
        Playbin.setState(State.PLAYING);
         */
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
        /*
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

    public void PlayVideoFile(String file) {
        //mediaPlayer.playMedia("http://" + Parent.Camera.GetIP() + file);
    }
}
