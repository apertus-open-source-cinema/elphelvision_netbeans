

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

    private ElphelVision Parent;
    private Color DefaultBorderColor = new Color(70, 70, 70);
    private Color DefaultBorderColorChecked = new Color(255, 255, 255);
    private Color DefaultTextColor = new Color(255, 255, 255);
    private Color DefaultTextColorChecked = new Color(0, 0, 0);
    private Color DefaultBackgroundColorGradientStart = new Color(40, 40, 40);
    private Color DefaultBackgroundColorGradientEnd = new Color(0, 0, 0);
    private Color DefaultBackgroundColorCheckedGradientStart = new Color(200, 200, 200);
    private Color DefaultBackgroundColorCheckedGradientEnd = new Color(255, 255, 255);
    private int BorderWidth = 2;
    private int CornerRadius = 12;
    private int FontSize = 11;
    private int FontWeight = Font.PLAIN;
    private boolean Checked = false;
    private String ParameterName = "";
    private String AdditionalValue;
    private boolean ClickFeedback = false;
    private Timer ClickFeedbacktimer;
    private static final int BLINKING_RATE = 100; // in ms

    public EButton() {
        this.setBorderPainted(false);
        this.setFont(new Font("DejaVu Sans", Font.PLAIN, FontSize));
        this.setRolloverEnabled(false);
        this.setPreferredSize(new Dimension(80, 35));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setMargin(new Insets(0, 0, 0, 0));
    }

    public EButton(ElphelVision parent) {
        Parent = parent;
        this.setBorderPainted(false);
        this.setRolloverEnabled(false);
        this.setPreferredSize(new Dimension(80, 35));
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.DefaultBorderColor = Parent.Utils.GetButtonBorderColor();
        this.DefaultBorderColorChecked = Parent.Utils.GetButtonBorderColorChecked();
        this.DefaultTextColor = Parent.Utils.GetButtonTextColor();
        this.DefaultTextColorChecked = Parent.Utils.GetButtonTextColorChecked();
        this.DefaultBackgroundColorGradientStart = Parent.Utils.GetButtonBackgroundColorGradientStart();
        this.DefaultBackgroundColorGradientEnd = Parent.Utils.GetButtonBackgroundColorGradientEnd();
        this.DefaultBackgroundColorCheckedGradientStart = Parent.Utils.GetButtonBackgroundColorCheckedGradientStart();
        this.DefaultBackgroundColorCheckedGradientEnd = Parent.Utils.GetButtonBackgroundColorCheckedGradientEnd();
        this.CornerRadius = Parent.Utils.GetButtonCornerRadius();
        this.BorderWidth = Parent.Utils.GetButtonBorderWidth();
        this.FontSize = Parent.Utils.GetButtonFontSize();
        this.FontWeight = Parent.Utils.GetButtonFontWeight();
        this.setFont(new Font(Parent.Utils.GetButtonFontName(), this.FontWeight, this.FontSize));
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
    /*
    public void setBorderColor(Color newcolor) {
    this.BorderColor = newcolor;
    }
    
    public Color getBorderColor() {
    return this.BorderColor;
    }
     */

    private void ClickactionPerformed(ActionEvent e) {
        if (ClickFeedback) {
            ClickFeedbacktimer = new Timer(BLINKING_RATE, new TimerListener(this));
            ClickFeedbacktimer.setInitialDelay(100);

            setChecked(true);
            this.repaint();
            ClickFeedbacktimer.start();
        }
    }

    public void ToggleChecked() {
        if (this.Checked) {
            setChecked(false);
        } else {
            setChecked(true);
        }
        this.repaint();


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

    public void setCornerRadius(int newradius) {
        this.CornerRadius = newradius;
        this.repaint();
    }

    public void setChecked(boolean checked) {
        this.Checked = checked;
        this.repaint();
    }

    public boolean getChecked() {
        return this.Checked;
    }

    @Override
    public void setForeground(java.awt.Color newcolor) {
        if (newcolor != null) { // Evil hack to override default colors but maintain custom color settings
            if ((newcolor.getRed() == 51) && (newcolor.getBlue() == 51) && (newcolor.getGreen() == 51)) {
                newcolor = DefaultTextColor;
            }
        }
        super.setForeground(newcolor);
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
        GradientPaint CheckedGradient = new GradientPaint(0, 0, DefaultBackgroundColorCheckedGradientStart, 0, 25, DefaultBackgroundColorCheckedGradientEnd, true);

        if (this.Checked) {
            // Button Fill
            g2.setPaint(CheckedGradient);
            g2.fillRoundRect(BorderWidth * 2, BorderWidth * 2, x - BorderWidth * 4, y - BorderWidth * 4, this.CornerRadius - BorderWidth * 2, this.CornerRadius - BorderWidth * 2);
            // Button Border
            g2.setPaint(DefaultBorderColorChecked);
            g2.setStroke(new BasicStroke(BorderWidth));
            g2.drawRoundRect(BorderWidth, BorderWidth, x - 2 * BorderWidth, y - 2 * BorderWidth, this.CornerRadius, this.CornerRadius);
        } else {
            // Button Fill
            g2.setPaint(DarkGradient);
            g2.fillRoundRect(1, 1, x - 2, y - 2, this.CornerRadius - 2, this.CornerRadius - 2);
            // Button Border
            g2.setPaint(DefaultBorderColor);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(BorderWidth - 1, BorderWidth - 1, x - 2 * (BorderWidth - 1), y - 2 * (BorderWidth - 1), this.CornerRadius, this.CornerRadius);
        }

        // Button Text
        if (Checked) {
            g2.setPaint(DefaultTextColorChecked);
        } else {
            g2.setPaint(this.getForeground());
        }

        //Draw Text
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D area = fm.getStringBounds(this.getText(), g2);
        int textx = 0, texty = 0;
        if (this.getHorizontalAlignment() == 0) { // center
            textx = (int) (getWidth() / 2 - area.getWidth() / 2);
            texty = (int) (getHeight() / 2 + area.getHeight() / 2 - 2);
        } else if (this.getHorizontalAlignment() == 2) { // left
            textx = this.getMargin().left;
            texty = (int) (getHeight() / 2 + area.getHeight() / 2 - 2);
        }
        g2.drawString(this.getText(), textx, texty);

        // Draw the Icon Image
        if (this.getIcon() != null) {
            this.getIcon().paintIcon(this, g2, (int) (getWidth() / 2 - this.getIcon().getIconWidth() / 2), (int) (getHeight() / 2 - this.getIcon().getIconHeight() / 2));
        }
        //Draw Additional Value
        if (this.AdditionalValue != null) {
            Rectangle2D area2 = fm.getStringBounds(this.AdditionalValue, g2);
            g2.drawString(this.AdditionalValue, (int) (getWidth() - area2.getWidth() - 6), (int) (getHeight() / 2 + area2.getHeight() / 2 - 2));
        }

        // We do this all ourselves now so no need to call:
        //super.paint(g);
    }
}
