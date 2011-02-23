
/*! Copyright (C) 2009 Apertus, All Rights Reserved
 *! Author : Apertus Team
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
enum OStype {

    Windows, Linux
}

enum streamVideoPlayer {

    VLC, GSTREAMER
}

/**
 *
 * Holds client-side user settings
 */
public class UserSettings {

    private OStype OS = null;
    private streamVideoPlayer StreamVideoPlayer = streamVideoPlayer.VLC;

    UserSettings() {
    }

    public void SetVideoPlayer(streamVideoPlayer input) {
        this.StreamVideoPlayer = input;
    }

    public streamVideoPlayer GetVideoPlayer() {
        return this.StreamVideoPlayer;
    }

    public void SetOS(OStype input) {
        this.OS = input;
    }

    public OStype GetOS() {
        return this.OS;
    }
}
