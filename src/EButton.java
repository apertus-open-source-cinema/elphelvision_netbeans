

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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;
import javax.swing.JButton;

public class EButton extends JButton implements java.io.Serializable {
    //private static final long serialVersionUID = 21L;

    public static final Color DefaultBorderColor = new Color(70, 70, 70);
    public static final Color DefaultBorderColorChecked = new Color(255, 255, 255);
    public static final Color DefaultTextColor = new Color(255, 255, 255);
    public static final Color DefaultTextColorChecked = new Color(0, 0, 0);
    public static final Color DefaultBackgroundColorGradientStart = new Color(40, 40, 40);
    public static final Color DefaultBackgroundColorGradientEnd = new Color(0, 0, 0);
    public static final Color DefaultBackgroundColorCheckedGradientStart = new Color(200, 200, 200);
    public static final Color DefaultBackgroundColorCheckedGradientEnd = new Color(255, 255, 255);
    private Color TextColor;
    private Color BorderColor;
    private int CornerRadius = 12;
    private boolean Checked = false;
    private String ParameterName = "";
    private String AdditionalValue;
    private boolean ClickFeedback = false;
    private Timer ClickFeedbacktimer;
    private static final int BLINKING_RATE = 1000; // in ms

    public EButton() {
        this.setBorderPainted(false);
        this.setFont(new Font("DejaVu Sans", Font.PLAIN, 13));
        this.setRolloverEnabled(false);
        this.setPreferredSize(new Dimension(100, 50));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    public void setClickFeedback(boolean setting) {
        this.ClickFeedback = setting;
        if (setting) {
            addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ClickactionPerformed(evt);
                }
            });
        }
    }

    public boolean getClickFeedback() {
        return this.ClickFeedback;
    }

    public void setBorderColor(Color newcolor) {
        this.BorderColor = newcolor;
    }

    public Color getBorderColor() {
        return this.BorderColor;
    }

    public void setTextColor(Color newcolor) {
        this.TextColor = newcolor;
    }

    public Color getTextColor() {
        return this.TextColor;
    }

    private void ClickactionPerformed(ActionEvent e) {
        if (ClickFeedback) {
            ClickFeedbacktimer = new Timer(BLINKING_RATE, new TimerListener(this));
            ClickFeedbacktimer.setInitialDelay(200);

            setChecked(true);
            this.repaint();
            ClickFeedbacktimer.start();
        }
    }

    private class TimerListener implements ActionListener {

        private EButton targetbutton;

        public TimerListener(EButton button) {
            targetbutton = button;
        }

        public void actionPerformed(ActionEvent e) {
            if (targetbutton.getClickFeedback()) {
                targetbutton.setChecked(false);
                ClickFeedbacktimer.stop();
            }
        }
    }

    public void setParameterName(String name) {
        this.ParameterName = name;
    }

    public String getParameterName() {
        return this.ParameterName;
    }

    public void setValue(String val) {
        this.AdditionalValue = val;
        this.repaint();
    }

    public String getValue() {
        return this.AdditionalValue;
    }

    public void setColor(Color newcolor) {
        //this.ButtonColor = newcolor;
        this.repaint();
    }

    public void setCornerRadius(int newradius) {
        this.CornerRadius = newradius;
        this.repaint();
    }
    Color SavedButtonColor = Color.WHITE;

    public void setChecked(boolean checked) {
        this.Checked = checked;

        this.repaint();
    }

    public boolean getChecked() {
        return this.Checked;
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void paint(Graphics g) {
        setBackground(Color.BLACK);

        Graphics2D g2 = (Graphics2D) g;

        Paint oldPaint = g2.getPaint();
        g2.setPaint(oldPaint);

        g2.setStroke(new BasicStroke(2));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

        int x = this.getWidth();
        int y = this.getHeight();

        // Gradients
        GradientPaint DarkGradient = new GradientPaint(0, 0, DefaultBackgroundColorGradientStart, 0, 25, DefaultBackgroundColorGradientEnd, false);
        GradientPaint BrightGradient = new GradientPaint(0, 0, DefaultBackgroundColorCheckedGradientStart, 0, 25, DefaultBackgroundColorCheckedGradientEnd, true);

        if (this.Checked) {
            // Button Fill
            g2.setPaint(BrightGradient);
            g2.fillRoundRect(4, 4, x - 8, y - 8, this.CornerRadius - 4, this.CornerRadius - 4);
            // Button Border
            g2.setPaint(DefaultBorderColorChecked);
            g2.drawRoundRect(1, 1, x - 2, y - 2, this.CornerRadius, this.CornerRadius);
        } else {
            // Button Fill
            g2.setPaint(DarkGradient);
            g2.fillRoundRect(0, 0, x, y, this.CornerRadius, this.CornerRadius);
            // Button Border
            g2.setPaint(DefaultBorderColor);
            g2.drawRoundRect(1, 1, x - 2, y - 2, this.CornerRadius, this.CornerRadius);
        }

        // Button Text
        if (Checked) {
            this.setForeground(DefaultTextColorChecked);
        } else {
            this.setForeground(TextColor);
            this.setForeground(Color.WHITE);
        }
        //java.awt.Dimension rect = this.getSize();

        if (this.AdditionalValue != null) {
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D area = fm.getStringBounds(this.AdditionalValue, g2);
            //g.drawString(str, (int)(getWidth() - area.getWidth())/2,(int)(getHeight() + area.getHeight())/2);
            //g2.drawString(this.AdditionalValue,  (int)(getWidth() - area.getWidth()), (int)(getHeight() + area.getHeight()));
            g2.drawString(this.AdditionalValue, (int) (getWidth() - area.getWidth() - 6), 31);
        }

        super.paint(g);
    }
}
