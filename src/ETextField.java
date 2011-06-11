
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
public class ETextField extends JTextField implements java.io.Serializable {

    private ElphelVision Parent;
    private Color DefaultBorderColor = new Color(255, 255, 255);
    private Color DefaultBorderColorActive = new Color(255, 255, 255);
    private Color DefaultTextColor = new Color(255, 255, 255);
    private Color DefaultTextColorActive = new Color(60, 0, 0);
    private Color DefaultBackgroundColorGradientStart = new Color(200, 200, 200);
    private Color DefaultBackgroundColorGradientEnd = new Color(255, 255, 255);
    private Color DefaultBackgroundColorActiveGradientStart = new Color(200, 200, 200);
    private Color DefaultBackgroundColorActiveGradientEnd = new Color(255, 255, 255);
    private int BorderWidth = 2;
    private int CornerRadius = 12;
    private int FontSize = 11;
    private int FontWeight = Font.PLAIN;
    private boolean Active = false;

    public ETextField() {
        this.setBackground(Color.BLACK);
        this.setForeground(DefaultTextColor);
        this.setMargin(new Insets(0, 6, 0, 0));
    }

    public ETextField(ElphelVision parent) {
        Parent = parent;
        this.setBackground(Color.BLACK);
        this.setForeground(DefaultTextColor);
        this.setMargin(new Insets(0, 6, 0, 0));
    }

    @Override
    public void paintBorder(Graphics g) {
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
        // Button Border
        g2.setPaint(DefaultBorderColor);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(BorderWidth - 1, BorderWidth - 1, x - 2 * (BorderWidth - 1), y - 2 * (BorderWidth - 1), this.CornerRadius, this.CornerRadius);
        //super.paintBorder(g);
    }
    /*@Override
    public void paint(Graphics g) {
    
    setBackground(Parent.Utils.GetPanelBackgroundColor());
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
    GradientPaint CheckedGradient = new GradientPaint(0, 0, DefaultBackgroundColorActiveGradientStart, 0, 25, DefaultBackgroundColorActiveGradientEnd, true);
    
    if (this.Active) {
    // Button Fill
    g2.setPaint(CheckedGradient);
    g2.fillRoundRect(BorderWidth * 2, BorderWidth * 2, x - BorderWidth * 4, y - BorderWidth * 4, this.CornerRadius - BorderWidth * 2, this.CornerRadius - BorderWidth * 2);
    // Button Border
    } else {
    // Button Fill
    g2.setPaint(DarkGradient);
    g2.fillRoundRect(1, 1, x - 2, y - 2, this.CornerRadius - 2, this.CornerRadius - 2);
    // Button Border
    }
    /*
    // Button Text
    if (Active) {
    g2.setPaint(DefaultTextColorActive);
    } else {
    g2.setPaint(DefaultTextColor);
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
     */
    //super.paint(g);
    //super.paintComponent(g);
    //}*/
}
