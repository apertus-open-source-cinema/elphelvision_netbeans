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
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.windows.WindowsRuntimeUtil;

public class VLCPlayer {

    private EmbeddedMediaPlayer mediaPlayer;
    private MediaPlayerFactory mediaPlayerFactory;
    private ElphelVision Parent;
    private FullScreenStrategy fullScreenStrategy;
    private Canvas OverlayElement;

    VLCPlayer(ElphelVision parent) {
        this.Parent = parent;
        List<String> vlcArgs = new ArrayList<String>();

        vlcArgs.add("--no-video-title-show");
        vlcArgs.add("--rtsp-caching=40");
        vlcArgs.add("--clock-jitter=0");

        // This burns so many people on Windows that I decided to leave it in...
        if (RuntimeUtil.isWindows()) {
            vlcArgs.add("--plugin-path=" + WindowsRuntimeUtil.getVlcInstallDir() + "\\plugins");
        }

        //fullScreenStrategy = new DefaultFullScreenStrategy(Parent.GetMainframe());

        mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayer = mediaPlayerFactory.newMediaPlayer(fullScreenStrategy);
        //mediaPlayer = mediaPlayerFactory.newMediaPlayer(null);
    }

    public void close() {
        Parent.WriteLogtoConsole("Stopping VLC Player");
        mediaPlayer.stop();
    }

    public void SetCanvas(Canvas overlayelemt) {
        OverlayElement = overlayelemt;
        mediaPlayer.setVideoSurface(overlayelemt);

    }

    public void ToggleFullscreen() {
        //mediaPlayer.toggleFullScreen();
        //something is wrong here
        mediaPlayer.setFullScreen(true);
        fullScreenStrategy.enterFullScreenMode();

    }

    public void SetScale(float factor) {
        mediaPlayer.setScale(factor);
    }

    public void PlayVideoStream() {
        String mediaoptions = "effect-list=none";
        //String mediaoptions = "effect-list=scope";
        //magnify-gaussianblur-sigma=<float>
        //vlc rtsp://192.168.10.141:554 --video-filter magnify

        mediaPlayer.playMedia("rtsp://" + Parent.Camera.GetIP() + ":554", mediaoptions);
    }

    /*public void Overlay(Window overlay) {
    mediaPlayer.setOverlay(overlay);
    mediaPlayer.enableOverlay(true);
    }*/
    public void PlayVideoFile(String file) {
        mediaPlayer.playMedia("http://" + Parent.Camera.GetIP() + file);
    }

    public void PlayLocalVideoFile(String file) {
        mediaPlayer.playMedia(file);
    }

    public class DefaultFullScreenStrategy implements FullScreenStrategy {

        /**
         * The component that will be made full-screen.
         */
        private final Window window;

        /**
         * Create a new full-screen strategy.
         *
         * @param window component that will be made full-screen
         */
        public DefaultFullScreenStrategy(Window window) {
            //Logger.debug("DefaultFullScreenStrategy(window={})", window);

            if (window != null) {
                this.window = window;
            } else {
                throw new IllegalArgumentException("Window must not be null");
            }
        }

        @Override
        public void enterFullScreenMode() {
            //Logger.debug("enterFullScreenMode()");
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(window);
        }

        @Override
        public void exitFullScreenMode() {
            //Logger.debug("exitFullScreenMode()");
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
        }

        @Override
        public boolean isFullScreenMode() {
            //Logger.debug("isFullScreenMode()");

            return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getFullScreenWindow() != null;
        }
    }
}
