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
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings3Layout extends javax.swing.JPanel {

    ElphelVision Parent;

    public Settings3Layout(ElphelVision parent) {
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
        MovieSplitSizeField.setText(String.valueOf(Parent.Camera.GetMovieClipMaxChunkSize()));
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
        ConfirmationPanel = new javax.swing.JPanel();
        SettingsOKButton = new EButton();
        SettingsCancelButton = new EButton();
        gammacurve = new GammaCurve();
        MovieMaxSplitSizePanel = new javax.swing.JPanel();
        MovieSplitSizeField = new javax.swing.JTextField();
        MovieSplitSizeLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        MovieSplitSizeType = new EButton();
        NavigationPanel1 = new javax.swing.JPanel();
        SettingsMenu1Button1 = new EButton();
        SettingsMenu2Button1 = new EButton();
        GuidesMenuButton1 = new EButton();
        SettingsMenu3Button = new EButton();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        ConfirmationPanel.setBackground(java.awt.Color.black);

        SettingsOKButton.setText("OK");
        SettingsOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsOKButtonActionPerformed(evt);
            }
        });

        SettingsCancelButton.setText("Cancel");
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
                .addGap(6, 6, 6)
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gammacurve.setBackground(new java.awt.Color(0, 0, 0));
        gammacurve.setPreferredSize(new java.awt.Dimension(262, 262));

        javax.swing.GroupLayout gammacurveLayout = new javax.swing.GroupLayout(gammacurve);
        gammacurve.setLayout(gammacurveLayout);
        gammacurveLayout.setHorizontalGroup(
            gammacurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        gammacurveLayout.setVerticalGroup(
            gammacurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        MovieMaxSplitSizePanel.setBackground(new java.awt.Color(0, 0, 0));

        MovieSplitSizeField.setBackground(new java.awt.Color(0, 0, 0));
        MovieSplitSizeField.setForeground(new java.awt.Color(255, 255, 255));
        MovieSplitSizeField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        MovieSplitSizeField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        MovieSplitSizeField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                MovieSplitSizeFieldCaretUpdate(evt);
            }
        });

        MovieSplitSizeLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        MovieSplitSizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        MovieSplitSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MovieSplitSizeLabel.setText("Movie Split Size");
        MovieSplitSizeLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        MovieSplitSizeLabel.setAlignmentY(0.0F);
        MovieSplitSizeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        MovieSplitSizeLabel.setIconTextGap(0);
        MovieSplitSizeLabel.setInheritsPopupMenu(false);
        MovieSplitSizeLabel.setRequestFocusEnabled(false);
        MovieSplitSizeLabel.setVerifyInputWhenFocusTarget(false);
        MovieSplitSizeLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MB");

        MovieSplitSizeType.setText("type");
        MovieSplitSizeType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        MovieSplitSizeType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MovieSplitSizeTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MovieMaxSplitSizePanelLayout = new javax.swing.GroupLayout(MovieMaxSplitSizePanel);
        MovieMaxSplitSizePanel.setLayout(MovieMaxSplitSizePanelLayout);
        MovieMaxSplitSizePanelLayout.setHorizontalGroup(
            MovieMaxSplitSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MovieMaxSplitSizePanelLayout.createSequentialGroup()
                .addComponent(MovieSplitSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MovieSplitSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MovieSplitSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        MovieMaxSplitSizePanelLayout.setVerticalGroup(
            MovieMaxSplitSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MovieMaxSplitSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                .addComponent(MovieSplitSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3)
                .addComponent(MovieSplitSizeType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(MovieSplitSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

        GuidesMenuButton1.setText("Guides");
        GuidesMenuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuidesMenuButton1ActionPerformed(evt);
            }
        });

        SettingsMenu3Button.setText("Settings Tab 3");
        SettingsMenu3Button.setChecked(true);
        SettingsMenu3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu3ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationPanel1Layout = new javax.swing.GroupLayout(NavigationPanel1);
        NavigationPanel1.setLayout(NavigationPanel1Layout);
        NavigationPanel1Layout.setHorizontalGroup(
            NavigationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanel1Layout.createSequentialGroup()
                .addComponent(SettingsMenu1Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu2Button1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu3Button, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GuidesMenuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        NavigationPanel1Layout.setVerticalGroup(
            NavigationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsMenu1Button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu2Button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu3Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(GuidesMenuButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(584, 584, 584)
                        .addComponent(gammacurve, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(MovieMaxSplitSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(NavigationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
                        .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MovieMaxSplitSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164)
                .addComponent(gammacurve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NavigationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void SettingsOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsOKButtonActionPerformed
        Parent.Camera.SetMovieClipMaxChunkSize(Integer.parseInt(MovieSplitSizeField.getText()));

        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.Player.close();
        Parent.Player.PlayVideoStream();
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.Player.close();
        Parent.Player.PlayVideoStream();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void MovieSplitSizeFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_MovieSplitSizeFieldCaretUpdate
}//GEN-LAST:event_MovieSplitSizeFieldCaretUpdate

    private void MovieSplitSizeTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MovieSplitSizeTypeActionPerformed
        Parent.NumberPanel.Load("Movie Split Size", Integer.parseInt(MovieSplitSizeField.getText()), MovieSplitSizeField, "Settings3Card");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Numberpanel");
}//GEN-LAST:event_MovieSplitSizeTypeActionPerformed

    private void SettingsMenu1Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu1Button1ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.Player.close();
}//GEN-LAST:event_SettingsMenu1Button1ActionPerformed

    private void SettingsMenu2Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu2Button1ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings2Card");
        Parent.Settings2CardLayout.Load();
        Parent.Player.PlayVideoStream();
        //Parent.Settings2CardLayout.StartMplayerVideoStream();
}//GEN-LAST:event_SettingsMenu2Button1ActionPerformed

    private void GuidesMenuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuidesMenuButton1ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "GuidesCard");
        Parent.GuidesPanel.Load();
}//GEN-LAST:event_GuidesMenuButton1ActionPerformed

    private void SettingsMenu3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu3ButtonActionPerformed
}//GEN-LAST:event_SettingsMenu3ButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConfirmationPanel;
    private EButton GuidesMenuButton1;
    private javax.swing.JPanel MovieMaxSplitSizePanel;
    private javax.swing.JTextField MovieSplitSizeField;
    private javax.swing.JLabel MovieSplitSizeLabel;
    private EButton MovieSplitSizeType;
    private javax.swing.JPanel NavigationPanel1;
    private EButton SettingsCancelButton;
    private EButton SettingsMenu1Button1;
    private EButton SettingsMenu2Button1;
    private EButton SettingsMenu3Button;
    private EButton SettingsOKButton;
    private javax.swing.JPanel bg;
    private GammaCurve gammacurve;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
