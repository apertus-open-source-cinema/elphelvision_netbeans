
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

/**
 *
 * Holds client sided uses settings
 */
public class UserSettings {

    private String mplayerPath = null;     // The path to the MPlayer executable
    private OStype OS = null;      // Detected Operating System
    private String mplayerParameters = null;     // The path to the MPlayer executable

    UserSettings() {
        mplayerPath = "mplayer";
        mplayerParameters = "-slave- idle";
    }

    public void SetMplayerPath(String path) {
        this.mplayerPath = path;
    }

    public void SetMplayerParameters(String parm) {
        this.mplayerParameters = parm;
    }

    public String GetMplayerParameters() {
        return this.mplayerParameters;
    }

    public boolean CheckMplayerInstallation() {
        // TODO!!!
        return true;
    }

    public String GetMplayerPath() {
        return this.mplayerPath;
    }

    public void SetOS(OStype input) {
        this.OS = input;
        if (this.OS == OStype.Linux) {
            mplayerPath = "mplayer"; // mplayerPath = "/usr/bin/mplayer";
        } else {
            mplayerPath = "C:\\mplayer\\mplayer.exe";
        }
    }

    public OStype GetOS() {
        return this.OS;
    }
}
