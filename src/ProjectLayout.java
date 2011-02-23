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

public class ProjectLayout extends javax.swing.JPanel {

    ElphelVision Parent;

    public ProjectLayout(ElphelVision parent) {
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
        MovieMaxSplitSizePanel = new javax.swing.JPanel();
        MovieSplitSizeLabel = new javax.swing.JLabel();

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

        MovieMaxSplitSizePanel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout MovieMaxSplitSizePanelLayout = new javax.swing.GroupLayout(MovieMaxSplitSizePanel);
        MovieMaxSplitSizePanel.setLayout(MovieMaxSplitSizePanelLayout);
        MovieMaxSplitSizePanelLayout.setHorizontalGroup(
            MovieMaxSplitSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        MovieMaxSplitSizePanelLayout.setVerticalGroup(
            MovieMaxSplitSizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        MovieSplitSizeLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 14)); // NOI18N
        MovieSplitSizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        MovieSplitSizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MovieSplitSizeLabel.setText("Projects");
        MovieSplitSizeLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        MovieSplitSizeLabel.setAlignmentY(0.0F);
        MovieSplitSizeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        MovieSplitSizeLabel.setIconTextGap(0);
        MovieSplitSizeLabel.setInheritsPopupMenu(false);
        MovieSplitSizeLabel.setRequestFocusEnabled(false);
        MovieSplitSizeLabel.setVerifyInputWhenFocusTarget(false);
        MovieSplitSizeLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MovieSplitSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MovieMaxSplitSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(794, Short.MAX_VALUE)
                .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MovieMaxSplitSizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MovieSplitSizeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 501, Short.MAX_VALUE)
                .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        Parent.StopVideoPlayer();


        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent.LoadMainCard();     
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        Parent.StopVideoPlayer();
        Parent.LoadMainCard();  
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConfirmationPanel;
    private javax.swing.JPanel MovieMaxSplitSizePanel;
    private javax.swing.JLabel MovieSplitSizeLabel;
    private EButton SettingsCancelButton;
    private EButton SettingsOKButton;
    private javax.swing.JPanel bg;
    // End of variables declaration//GEN-END:variables
}
