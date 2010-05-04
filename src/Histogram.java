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

import java.awt.*;
import java.awt.image.ColorModel;
import java.net.*;
import java.io.*;
import javax.swing.JPanel;

public class Histogram extends JPanel implements Runnable, java.io.Serializable {

    int width, height;
    int i = 0;
    Thread animator;
    int fps = 25;
    URLConnection conn = null;
    BufferedReader data = null;
    String line;
    String result;
    StringBuffer buf = new StringBuffer();
    URL theURL = null;
    ElphelVision Parent = null;

    public Histogram() {
        width = 256 + 2;
        height = 50;
    }

    public Histogram(ElphelVision parent) {
        this.Parent = parent;
        width = 256 + 2;
        height = 50;
    }

    public void SetParent(ElphelVision parent) {
        this.Parent = parent;
    }

    public void startAnimator() {
        animator = new Thread(this);
        animator.start();
        //setSize(width, height);
    }

    public void run() {

        while (Thread.currentThread() == animator) {

            repaint();

            try {
                Thread.sleep(1 / fps * 1000);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (this.Parent != null) {

            int[][] hist_value = Parent.Camera.GetHistogram(0);

            Graphics2D g2 = (Graphics2D) g;
            g.setPaintMode();

            //range background
            //10%
            g.setColor(new Color(5, 5, 5));
            g.fillRect(0, 0, 256 / 10, height - 6);
            // 30%
            g.setColor(new Color(15, 15, 15));
            g.fillRect(256 / 10, 0, 3 * 256 / 10, height - 6);
            // 50%
            g.setColor(new Color(20, 20, 20));
            g.fillRect(3 * 256 / 10, 0, 5 * 256 / 10, height - 6);
            // 70%
            g.setColor(new Color(25, 25, 25));
            g.fillRect(5 * 256 / 10, 0, 7 * 256 / 10, height - 6);
            // 90%
            g.setColor(new Color(30, 30, 30));
            g.fillRect(7 * 256 / 10, 0, 9 * 256 / 10, height - 6);
            // 100%
            g.setColor(new Color(50, 50, 50));
            g.fillRect(9 * 256 / 10, 0, 10 * 256 / 10, height - 6);

            // range indicators
            g.setColor(new Color(35, 35, 35));
            g2.drawLine(256 / 10, 0, 256 / 10, height - 6);
            g.setColor(new Color(40, 40, 40));
            g2.drawLine(3 * 256 / 10, 0, 3 * 256 / 10, height - 6);
            g.setColor(new Color(45, 45, 45));
            g2.drawLine(5 * 256 / 10, 0, 5 * 256 / 10, height - 6);
            g.setColor(new Color(50, 50, 50));
            g2.drawLine(7 * 256 / 10, 0, 7 * 256 / 10, height - 6);
            g.setColor(new Color(70, 70, 70));
            g2.drawLine(9 * 256 / 10, 0, 9 * 256 / 10, height - 6);

            // gradient
            Color c1 = Color.black;
            Color c2 = Color.white;
            GradientPaint gradient = new GradientPaint(0, 0, c1, 256, 0, c2, false);
            g2.setPaint(gradient);
            g2.fillRect(0, height - 4, 256, 5);

            // coordinate system
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.darkGray);
            g2.drawLine(0, height - 6, 255, height - 6);
            g2.drawLine(255, height - 6, 255, 0);
            g2.drawLine(255, 0, 0, 0);
            g2.drawLine(0, 0, 0, height - 6);

            // Why does this not work in a single loop? weird behaviour
            g2.setStroke(new BasicStroke(1));
            for (int i = 0; i < hist_value[2].length / 2; i++) {
                int helper = 127 + i / 2;
                // RED
                Color RedGradient = new Color(helper, 0, 0);
                g2.setColor(RedGradient);
                g2.drawLine(i, height - 6, i, height - 6 - (hist_value[0][i] * (height - 6) / 256));

                // GREEN
                Color GreenGradient = new Color(0, helper, 0);
                g2.setColor(GreenGradient);
                g2.drawLine(i, height - 6, i, height - 6 - (hist_value[1][i] * (height - 6) / 256));

                // BLUE
                Color BlueGradient = new Color(0, 0, helper);
                g2.setColor(BlueGradient);
                g2.drawLine(i, height - 6, i, height - 6 - (hist_value[2][i] * (height - 6) / 256));

                //YELLOW
                if ((hist_value[1][i] > hist_value[2][i]) && (hist_value[0][i] > hist_value[2][i])) {
                    Color YellowGradient = new Color(helper, helper, 0);
                    g2.setColor(YellowGradient);
                    if (hist_value[1][i] > hist_value[0][i]) {
                        g2.drawLine(i, height - 6, i, height - 6 - (hist_value[0][i] * (height - 6) / 256));
                    } else {
                        g2.drawLine(i, height - 6, i, height - 6 - (hist_value[1][i] * (height - 6) / 256));
                    }
                }

                //PINK
                if ((hist_value[2][i] > hist_value[1][i]) && (hist_value[0][i] > hist_value[1][i])) {
                    Color PinkGradient = new Color(helper, 0, helper);
                    g2.setColor(PinkGradient);
                    if (hist_value[2][i] > hist_value[0][i]) {
                        g2.drawLine(i, height - 6, i, height - 6 - (hist_value[0][i] * (height - 6) / 256));
                    } else {
                        g2.drawLine(i, height - 6, i, height - 6 - (hist_value[2][i] * (height - 6) / 256));
                    }
                }

                //CYAN
                if ((hist_value[2][i] > hist_value[0][i]) && (hist_value[1][i] > hist_value[0][i])) {
                    Color CyanGradient = new Color(0, helper, helper);
                    g2.setColor(CyanGradient);
                    if (hist_value[2][i] > hist_value[1][i]) {
                        g2.drawLine(i, height - 6, i, height - 6 - (hist_value[1][i] * (height - 6) / 256));
                    } else {
                        g2.drawLine(i, height - 6, i, height - 6 - (hist_value[2][i] * (height - 6) / 256));
                    }
                }

                // WHITE
                int lowest = 999999999;
                if ((hist_value[0][i] * (height - 6) / 256) < lowest) {
                    lowest = hist_value[0][i] * (height - 6) / 256;
                }
                if ((hist_value[1][i] * (height - 6) / 256) < lowest) {
                    lowest = hist_value[1][i] * (height - 6) / 256;
                }
                if ((hist_value[2][i] * (height - 6) / 256) < lowest) {
                    lowest = hist_value[2][i] * (height - 6) / 256;
                }
                Color WhiteGradient = new Color(helper, helper, helper);
                g2.setColor(WhiteGradient);
                g2.drawLine(i, height - 6, i, height - 6 - lowest);
            }
            for (int j = 128; j < hist_value[2].length; j++) {
                int helper = 127 + j / 2;
                // RED
                Color RedGradient = new Color(helper, 0, 0);
                g2.setColor(RedGradient);
                g2.drawLine(j, height - 6, j, height - 6 - (hist_value[0][j] * (height - 6) / 256));

                // GREEN
                Color GreenGradient = new Color(0, helper, 0);
                g2.setColor(GreenGradient);
                g2.drawLine(j, height - 6, j, height - 6 - (hist_value[1][j] * (height - 6) / 256));

                // BLUE
                Color BlueGradient = new Color(0, 0, helper);
                g2.setColor(BlueGradient);
                g2.drawLine(j, height - 6, j, height - 6 - (hist_value[2][j] * (height - 6) / 256));

                //YELLOW
                if ((hist_value[1][j] > hist_value[2][j]) && (hist_value[0][j] > hist_value[2][j])) {
                    Color YellowGradient = new Color(helper, helper, 0);
                    g2.setColor(YellowGradient);
                    if (hist_value[1][j] > hist_value[0][j]) {
                        g2.drawLine(j, height - 6, j, height - 6 - (hist_value[0][j] * (height - 6) / 256));
                    } else {
                        g2.drawLine(j, height - 6, j, height - 6 - (hist_value[1][j] * (height - 6) / 256));
                    }
                }


                //PINK
                if ((hist_value[2][j] > hist_value[1][j]) && (hist_value[0][j] > hist_value[1][j])) {
                    Color PinkGradient = new Color(helper, 0, helper);
                    g2.setColor(PinkGradient);
                    if (hist_value[2][j] > hist_value[0][j]) {
                        g2.drawLine(j, height - 6, j, height - 6 - (hist_value[0][j] * (height - 6) / 256));
                    } else {
                        g2.drawLine(j, height - 6, j, height - 6 - (hist_value[2][j] * (height - 6) / 256));
                    }
                }

                //CYAN
                if ((hist_value[2][j] > hist_value[0][j]) && (hist_value[1][j] > hist_value[0][j])) {
                    Color CyanGradient = new Color(0, helper, helper);
                    g2.setColor(CyanGradient);
                    if (hist_value[2][j] > hist_value[1][j]) {
                        g2.drawLine(j, height - 6, j, height - 6 - (hist_value[1][j] * (height - 6) / 256));
                    } else {
                        g2.drawLine(j, height - 6, j, height - 6 - (hist_value[2][j] * (height - 6) / 256));
                    }
                }

                // WHITE
                int lowest = 999999999;
                if ((hist_value[0][j] * (height - 6) / 256) < lowest) {
                    lowest = hist_value[0][j] * (height - 6) / 256;
                }
                if ((hist_value[1][j] * (height - 6) / 256) < lowest) {
                    lowest = hist_value[1][j] * (height - 6) / 256;
                }
                if ((hist_value[2][j] * (height - 6) / 256) < lowest) {
                    lowest = hist_value[2][j] * (height - 6) / 256;
                }
                Color WhiteGradient = new Color(helper, helper, helper);
                g2.setColor(WhiteGradient);
                g2.drawLine(j, height - 6, j, height - 6 - lowest);

            }
        }
    }

    public void stop() {
        animator = null;
    }
}
