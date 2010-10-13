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
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.windows.WindowsRuntimeUtil;

public class VLCPlayer {

    private EmbeddedMediaPlayer mediaPlayer;
    private MediaPlayerFactory mediaPlayerFactory;
    private ElphelVision Parent;

    VLCPlayer(ElphelVision parent) {
        this.Parent = parent;
        String vlcArgs = "--rtsp-caching=20 --no-video-title-show";

        // This burns so many people on Windows that I decided to leave it in...
        if (RuntimeUtil.isWindows()) {
            vlcArgs = "--plugin-path=" + WindowsRuntimeUtil.getVlcInstallDir() + "\\plugins";
        }

        mediaPlayerFactory = new MediaPlayerFactory(vlcArgs != null ? new String[]{vlcArgs} : new String[]{});
        mediaPlayer = mediaPlayerFactory.newMediaPlayer(null);
    }

    public void close() {
        mediaPlayer.stop();
    }

    public void SetCanvas(Canvas overlayelemt) {
        mediaPlayer.setVideoSurface(overlayelemt);
    }

    public void PlayVideoStream() {
        mediaPlayer.playMedia("rtsp://" + Parent.Camera.GetIP() + ":554");
    }

    public void PlayVideoFile(String file) {
        mediaPlayer.playMedia("http://" + Parent.Camera.GetIP() + file);
    }
}
