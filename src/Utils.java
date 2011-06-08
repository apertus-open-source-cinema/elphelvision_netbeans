
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*! Copyright (C) 20010 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *! Description: Class to store helper functions and handy utilities.
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
public class Utils {

    // round a float value to a certain number of decimals
    public static float Round(float Rval, int decimals) {
        float p = (float) Math.pow(10, decimals);
        Rval = Rval * p;
        float tmp = Math.round(Rval);
        return (float) tmp / p;
    }

    // makes sure the input is within the min/max range
    public static int MinMaxRange(int input, int min, int max) {
        if (input > max) {
            input = max;
        }
        if (input < min) {
            input = min;

        }
        return input;
    }
    //private Color ButtonBorderColor = new Color(70, 70, 70);
    private Color ButtonBorderColor = new Color(240, 10, 10);
    private Color ButtonBorderColorChecked = new Color(255, 255, 255);
    private Color ButtonTextColor = new Color(255, 255, 255);
    private Color ButtonTextColorChecked = new Color(0, 0, 0);
    private Color ButtonBackgroundColorGradientStart = new Color(40, 40, 40);
    private Color ButtonBackgroundColorGradientEnd = new Color(0, 0, 0);
    //public static final Color ButtonBackgroundColorCheckedGradientStart = new Color(0, 155, 255);
    //public static final Color ButtonBackgroundColorCheckedGradientEnd = new Color(105, 175, 220);
    private Color ButtonBackgroundColorCheckedGradientStart = new Color(200, 200, 200);
    private Color ButtonBackgroundColorCheckedGradientEnd = new Color(255, 255, 255);
    private int ButtonCornerRadius = 12;

    public boolean LoadStyles(String FileName) throws FileNotFoundException {

        File ConfigFile = new File(FileName);

        if (!ConfigFile.exists()) {
            return false;
        }

        Scanner scanner1 = new Scanner(ConfigFile);
        try {
            //first use a Scanner to get each line
            while (scanner1.hasNextLine()) {
                //use a second Scanner to parse the content of each line
                Scanner scanner2 = new Scanner(scanner1.nextLine());
                scanner2.useDelimiter("=");
                if (scanner2.hasNext()) {
                    String name = scanner2.next();
                    if (!scanner2.hasNext()) {
                        break;
                    }
                    String value = scanner2.next();
                    if (name.trim().equals("ButtonBorderColor")) {
                        int R = Integer.parseInt(value.trim().split(",")[0]);
                        int G = Integer.parseInt(value.trim().split(",")[1]);
                        int B = Integer.parseInt(value.trim().split(",")[2]);
                        ButtonBorderColor = new Color(R, G, B);
                    } else {
                        //Empty or invalid line. Unable to process
                    }
                    scanner2.close();
                }
            }
        } finally {
            //ensure the underlying stream is always closed
            scanner1.close();
        }
        return true;
    }

    public Color GetButtonBorderColor() {
        return ButtonBorderColor;
    }

    public Color GetButtonBorderColorChecked() {
        return ButtonBorderColorChecked;
    }

    public Color GetButtonTextColor() {
        return ButtonTextColor;
    }

    public Color GetButtonTextColorChecked() {
        return ButtonTextColorChecked;
    }

    public Color GetButtonBackgroundColorGradientStart() {
        return ButtonBackgroundColorGradientStart;
    }

    public Color GetButtonBackgroundColorGradientEnd() {
        return ButtonBackgroundColorGradientEnd;
    }

    public Color GetButtonBackgroundColorCheckedGradientStart() {
        return ButtonBackgroundColorCheckedGradientStart;
    }

    public Color GetButtonBackgroundColorCheckedGradientEnd() {
        return ButtonBackgroundColorCheckedGradientEnd;
    }

    public int GetButtonCornerRadius() {
        return ButtonCornerRadius;
    }
    // Experimental = not yet working way to play audio files within ElphelVision
    private Audio AudioEngine;

    public void Utils() {
        AudioEngine = new Audio();
    }

    public void PlayAudio(String filename) {
        AudioEngine.PlaySoundFile(filename);
    }
}
