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
import org.videolan.jvlc.JVLC;
import org.videolan.jvlc.MediaPlayer;

public class VLCPlayer {

    private MediaPlayer mediaPlayer;
    private JVLC jvlc;
    private ElphelVision Parent;

    VLCPlayer(ElphelVision parent) {
        this.Parent = parent;
        String args = "--rtsp-caching=20 --no-video-title-show";
        jvlc = new JVLC(args);
    }

    public void close() {
        mediaPlayer.stop();
    }

    public void SetCanvas(Canvas overlayelemt) {
        jvlc.setVideoOutput(overlayelemt);
    }

    public void PlayVideoStream() {
        mediaPlayer = jvlc.play("rtsp://192.168.10.141:554"); // TODO
    }
}
