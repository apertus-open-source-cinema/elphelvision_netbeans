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

public class GuidesLayout extends javax.swing.JPanel {

    ElphelVision Parent;

    public GuidesLayout(ElphelVision parent) {
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

        guidespreview1.SetParent(parent);
    }

    public void Load() {
        CenterXButton.setChecked(Parent.Camera.GetGuides()[0]);
        OuterXButton.setChecked(Parent.Camera.GetGuides()[1]);
        ThirdsButton.setChecked(Parent.Camera.GetGuides()[2]);
        SafeAreaButton.setChecked(Parent.Camera.GetGuides()[3]);

        guidespreview1.SetOptions(CenterXButton.getChecked(), OuterXButton.getChecked(), ThirdsButton.getChecked(), SafeAreaButton.getChecked());
        RedrawPreviewArea();
    }

    private void RedrawPreviewArea() {
        guidespreview1.repaint();
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PhotoSettingsMenu = new EButton();
        bg = new javax.swing.JPanel();
        ConfirmationPanel = new javax.swing.JPanel();
        SettingsCancelButton = new EButton();
        jPanel1 = new javax.swing.JPanel();
        CenterXButton = new EButton();
        ThirdsButton = new EButton();
        OuterXButton = new EButton();
        SafeAreaButton = new EButton();
        guidespreview1 = new Guides();
        NavigationPanel1 = new javax.swing.JPanel();
        SettingsMenu1Button1 = new EButton();
        SettingsMenu2Button1 = new EButton();
        SettingsMenu3Button = new EButton();
        PhotoSettingsMenu1 = new EButton();
        GuidesMenuButton1 = new EButton();

        PhotoSettingsMenu.setText("Photo Settings");
        PhotoSettingsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhotoSettingsMenuActionPerformed(evt);
            }
        });

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        ConfirmationPanel.setBackground(java.awt.Color.black);

        SettingsCancelButton.setText("Close");
        SettingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConfirmationPanelLayout = new javax.swing.GroupLayout(ConfirmationPanel);
        ConfirmationPanel.setLayout(ConfirmationPanelLayout);
        ConfirmationPanelLayout.setHorizontalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        CenterXButton.setText("Center X");
        CenterXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CenterXButtonActionPerformed(evt);
            }
        });

        ThirdsButton.setText("Thirds");
        ThirdsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThirdsButtonActionPerformed(evt);
            }
        });

        OuterXButton.setText("Outer X");
        OuterXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OuterXButtonActionPerformed(evt);
            }
        });

        SafeAreaButton.setText("Safe Area");
        SafeAreaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SafeAreaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CenterXButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ThirdsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(OuterXButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SafeAreaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CenterXButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThirdsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OuterXButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SafeAreaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        guidespreview1.setBackground(new java.awt.Color(76, 76, 76));

        NavigationPanel1.setBackground(java.awt.Color.black);

        SettingsMenu1Button1.setText("Settings Tab 1");
        SettingsMenu1Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu1Button1ActionPerformed(evt);
            }
        });

        SettingsMenu2Button1.setText("Settings Tab 2");
        SettingsMenu2Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu2Button1ActionPerformed(evt);
            }
        });

        SettingsMenu3Button.setText("Settings Tab 3");
        SettingsMenu3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu3ButtonActionPerformed(evt);
            }
        });

        PhotoSettingsMenu1.setText("Photo Settings");
        PhotoSettingsMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhotoSettingsMenu1ActionPerformed(evt);
            }
        });

        GuidesMenuButton1.setText("Guides");
        GuidesMenuButton1.setChecked(true);
        GuidesMenuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuidesMenuButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationPanel1Layout = new javax.swing.GroupLayout(NavigationPanel1);
        NavigationPanel1.setLayout(NavigationPanel1Layout);
        NavigationPanel1Layout.setHorizontalGroup(
            NavigationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanel1Layout.createSequentialGroup()
                .addComponent(SettingsMenu1Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu2Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(GuidesMenuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PhotoSettingsMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        NavigationPanel1Layout.setVerticalGroup(
            NavigationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsMenu1Button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu2Button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu3Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PhotoSettingsMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(GuidesMenuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(NavigationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(guidespreview1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guidespreview1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(NavigationPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ConfirmationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        Parent.Camera.SetGuides(CenterXButton.getChecked(), OuterXButton.getChecked(), ThirdsButton.getChecked(), SafeAreaButton.getChecked());

        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent.LoadMainCard();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void CenterXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CenterXButtonActionPerformed
        CenterXButton.setChecked(!CenterXButton.getChecked());
        guidespreview1.SetOptions(CenterXButton.getChecked(), OuterXButton.getChecked(), ThirdsButton.getChecked(), SafeAreaButton.getChecked());
        RedrawPreviewArea();
    }//GEN-LAST:event_CenterXButtonActionPerformed

    private void ThirdsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThirdsButtonActionPerformed
        ThirdsButton.setChecked(!ThirdsButton.getChecked());
        guidespreview1.SetOptions(CenterXButton.getChecked(), OuterXButton.getChecked(), ThirdsButton.getChecked(), SafeAreaButton.getChecked());
        RedrawPreviewArea();
    }//GEN-LAST:event_ThirdsButtonActionPerformed

    private void SafeAreaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SafeAreaButtonActionPerformed
        SafeAreaButton.setChecked(!SafeAreaButton.getChecked());
        guidespreview1.SetOptions(CenterXButton.getChecked(), OuterXButton.getChecked(), ThirdsButton.getChecked(), SafeAreaButton.getChecked());
        RedrawPreviewArea();
    }//GEN-LAST:event_SafeAreaButtonActionPerformed

    private void OuterXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OuterXButtonActionPerformed
        OuterXButton.setChecked(!OuterXButton.getChecked());
        guidespreview1.SetOptions(CenterXButton.getChecked(), OuterXButton.getChecked(), ThirdsButton.getChecked(), SafeAreaButton.getChecked());
        RedrawPreviewArea();
    }//GEN-LAST:event_OuterXButtonActionPerformed

    private void SettingsMenu1Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu1Button1ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.StopVideoPlayer();
}//GEN-LAST:event_SettingsMenu1Button1ActionPerformed

    private void SettingsMenu2Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu2Button1ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings2Card");
        Parent.Settings2CardLayout.Load();
}//GEN-LAST:event_SettingsMenu2Button1ActionPerformed

    private void GuidesMenuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuidesMenuButton1ActionPerformed
}//GEN-LAST:event_GuidesMenuButton1ActionPerformed

    private void SettingsMenu3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu3ButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings3Card");
        Parent.Settings3CardLayout.Load();
}//GEN-LAST:event_SettingsMenu3ButtonActionPerformed

    private void PhotoSettingsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhotoSettingsMenuActionPerformed
        Parent.PhotoSettingsCardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "PhotoSettings");
}//GEN-LAST:event_PhotoSettingsMenuActionPerformed

    private void PhotoSettingsMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhotoSettingsMenu1ActionPerformed
        Parent.PhotoSettingsCardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "PhotoSettings");
}//GEN-LAST:event_PhotoSettingsMenu1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EButton CenterXButton;
    private javax.swing.JPanel ConfirmationPanel;
    private EButton GuidesMenuButton1;
    private javax.swing.JPanel NavigationPanel1;
    private EButton OuterXButton;
    private EButton PhotoSettingsMenu;
    private EButton PhotoSettingsMenu1;
    private EButton SafeAreaButton;
    private EButton SettingsCancelButton;
    private EButton SettingsMenu1Button1;
    private EButton SettingsMenu2Button1;
    private EButton SettingsMenu3Button;
    private EButton ThirdsButton;
    private javax.swing.JPanel bg;
    private Guides guidespreview1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
