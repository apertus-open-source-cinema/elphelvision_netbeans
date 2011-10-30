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

import java.awt.CardLayout;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HITSettings extends javax.swing.JPanel {

    ElphelVision Parent;

    public HITSettings(ElphelVision parent) {
        Parent = parent;

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void Load() {
        Parent.VLCPlayer.SetCanvas(vlcoverlay);
        if (Parent.Settings.isVideoStreamEnabled()) {
            Parent.StartVideoPlayer();
        }
        this.UpdateLabel();
    }

    private void UpdateLabel() {
        int WOILeft = Parent.Camera.getImageWOILeft(0);
        int HITOffset = ((WOILeft + Parent.Camera.GetImageWidth() / 2) - 1296) * 2;
        float HITOffsetPercent = (float) HITOffset / (float) Parent.Camera.GetImageWidth() * 100.0f;
        HITTopLabel.setText("Horizontal Shift: " + Utils.Round(HITOffsetPercent, 2) + " % | " + HITOffset + " Pixels");
        HITValueTextField.setText(HITOffset + "");

        // HIT as % offset relates to the resolution of the image itself
        // 10% total HIT means both cameras move 5% "towards" each other; 5% of their current image width (at FullHD: 96 Pixels)
        // Negative values mean the images are shifted "away" from each other
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        VideoFrame = new javax.swing.JPanel();
        vlcoverlay = new java.awt.Canvas();
        SettingsCancelButton = new EButton(Parent);
        HITShiftPanel = new javax.swing.JPanel();
        HITMinusOneButton = new EButton(Parent);
        HITMinusTenButton = new EButton(Parent);
        HITPlusTenButton = new EButton(Parent);
        HITPlusOneButton1 = new EButton(Parent);
        HITTypeButton = new EButton(Parent);
        HITValueTextField = new javax.swing.JTextField();
        HITSet = new EButton(Parent);
        jPanel2 = new javax.swing.JPanel();
        HITTopLabel = new javax.swing.JLabel();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        VideoFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)));
        VideoFrame.setLayout(new javax.swing.BoxLayout(VideoFrame, javax.swing.BoxLayout.LINE_AXIS));

        vlcoverlay.setBackground(java.awt.Color.darkGray);
        vlcoverlay.setForeground(new java.awt.Color(254, 254, 254));
        VideoFrame.add(vlcoverlay);

        SettingsCancelButton.setText("Close");
        SettingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCancelButtonActionPerformed(evt);
            }
        });

        HITShiftPanel.setBackground(java.awt.Color.black);
        HITShiftPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HITMinusOneButton.setText("-4px");
        HITMinusOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITMinusOneButtonActionPerformed(evt);
            }
        });
        HITShiftPanel.add(HITMinusOneButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 50, -1));

        HITMinusTenButton.setText("-50px");
        HITMinusTenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITMinusTenButtonActionPerformed(evt);
            }
        });
        HITShiftPanel.add(HITMinusTenButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 50, -1));

        HITPlusTenButton.setText("+50px");
        HITPlusTenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITPlusTenButtonActionPerformed(evt);
            }
        });
        HITShiftPanel.add(HITPlusTenButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 50, -1));

        HITPlusOneButton1.setText("+4px");
        HITPlusOneButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITPlusOneButton1ActionPerformed(evt);
            }
        });
        HITShiftPanel.add(HITPlusOneButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 50, -1));

        HITTypeButton.setText("type");
        HITTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITTypeButtonActionPerformed(evt);
            }
        });
        HITShiftPanel.add(HITTypeButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 50, -1));
        HITShiftPanel.add(HITValueTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 50, -1));

        HITSet.setText("Set");
        HITSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HITSetActionPerformed(evt);
            }
        });
        HITShiftPanel.add(HITSet, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 50, -1));

        jPanel2.setBackground(java.awt.Color.black);

        HITTopLabel.setForeground(new java.awt.Color(254, 254, 254));
        HITTopLabel.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(HITTopLabel)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HITTopLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HITShiftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 270, Short.MAX_VALUE)
                        .addComponent(VideoFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SettingsCancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VideoFrame, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(HITShiftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (Parent.Settings.isVideoStreamEnabled()) {
            Parent.StopVideoPlayer();
        }
        Parent.LoadMainCard();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void HITMinusOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITMinusOneButtonActionPerformed
        Parent.Camera.SetStereo3DHIT(Parent.Camera.GetStereo3DHIT() - 4);
        this.UpdateLabel();
    }//GEN-LAST:event_HITMinusOneButtonActionPerformed

    private void HITMinusTenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITMinusTenButtonActionPerformed
        Parent.Camera.SetStereo3DHIT(Parent.Camera.GetStereo3DHIT() - 50);
        this.UpdateLabel();
    }//GEN-LAST:event_HITMinusTenButtonActionPerformed

    private void HITPlusTenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITPlusTenButtonActionPerformed
        Parent.Camera.SetStereo3DHIT(Parent.Camera.GetStereo3DHIT() + 50);
        this.UpdateLabel();
    }//GEN-LAST:event_HITPlusTenButtonActionPerformed

    private void HITTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITTypeButtonActionPerformed
        Parent.NumberPanelInteger.Load("HIT Offset", Integer.parseInt(HITValueTextField.getText()), HITValueTextField, "HITSettingsCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelInteger");
    }//GEN-LAST:event_HITTypeButtonActionPerformed

    private void HITPlusOneButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITPlusOneButton1ActionPerformed
        Parent.Camera.SetStereo3DHIT(Parent.Camera.GetStereo3DHIT() + 4);
        this.UpdateLabel();
    }//GEN-LAST:event_HITPlusOneButton1ActionPerformed

    private void HITSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HITSetActionPerformed
        Parent.Camera.SetStereo3DHIT(Integer.parseInt(HITValueTextField.getText()));
        this.UpdateLabel();
    }//GEN-LAST:event_HITSetActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EButton HITMinusOneButton;
    private EButton HITMinusTenButton;
    private EButton HITPlusOneButton1;
    private EButton HITPlusTenButton;
    private EButton HITSet;
    private javax.swing.JPanel HITShiftPanel;
    private javax.swing.JLabel HITTopLabel;
    private EButton HITTypeButton;
    private javax.swing.JTextField HITValueTextField;
    private EButton SettingsCancelButton;
    private javax.swing.JPanel VideoFrame;
    private javax.swing.JPanel bg;
    private javax.swing.JPanel jPanel2;
    private java.awt.Canvas vlcoverlay;
    // End of variables declaration//GEN-END:variables
}
