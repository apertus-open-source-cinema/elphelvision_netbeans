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

public class FPSSettings extends javax.swing.JPanel {

    ElphelVision Parent;

    public FPSSettings(ElphelVision parent) {
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
        jPanel1 = new javax.swing.JPanel();
        SettingsCancelButton = new EButton();
        SettingsOKButton = new EButton();
        FPSPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        CalculatedFPS = new javax.swing.JLabel();
        FPSType = new EButton();
        FPSField = new javax.swing.JTextField();
        TimelapsePanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        SkipFramesField = new javax.swing.JTextField();
        SkipSecondsField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        SkipFrameType = new EButton();
        SkipSecondsType = new EButton();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        jPanel1.setBackground(java.awt.Color.black);

        SettingsCancelButton.setText("Cancel");
        SettingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCancelButtonActionPerformed(evt);
            }
        });

        SettingsOKButton.setText("OK");
        SettingsOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsOKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        FPSPanel.setBackground(java.awt.Color.black);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("FPS");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel9.setAlignmentY(0.0F);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel9.setIconTextGap(0);
        jLabel9.setInheritsPopupMenu(false);
        jLabel9.setRequestFocusEnabled(false);
        jLabel9.setVerifyInputWhenFocusTarget(false);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        CalculatedFPS.setBackground(new java.awt.Color(0, 0, 0));
        CalculatedFPS.setForeground(new java.awt.Color(255, 255, 255));
        CalculatedFPS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CalculatedFPS.setText("jLabel1");

        FPSType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        FPSType.setText("type");
        FPSType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FPSTypeActionPerformed(evt);
            }
        });

        FPSField.setBackground(new java.awt.Color(0, 0, 0));
        FPSField.setForeground(new java.awt.Color(255, 255, 255));
        FPSField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FPSField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        FPSField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                FPSFieldCaretUpdate(evt);
            }
        });

        TimelapsePanel.setBackground(new java.awt.Color(0, 0, 0));
        TimelapsePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Timelapse", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("DejaVu Sans", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Skip Frames");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel11.setAlignmentY(0.0F);
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel11.setIconTextGap(0);
        jLabel11.setInheritsPopupMenu(false);
        jLabel11.setRequestFocusEnabled(false);
        jLabel11.setVerifyInputWhenFocusTarget(false);
        jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        SkipFramesField.setBackground(new java.awt.Color(0, 0, 0));
        SkipFramesField.setForeground(new java.awt.Color(255, 255, 255));
        SkipFramesField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkipFramesField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        SkipFramesField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SkipFramesFieldCaretUpdate(evt);
            }
        });

        SkipSecondsField.setBackground(new java.awt.Color(0, 0, 0));
        SkipSecondsField.setForeground(new java.awt.Color(255, 255, 255));
        SkipSecondsField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        SkipSecondsField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        SkipSecondsField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SkipSecondsFieldCaretUpdate(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Skip Seconds");
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel12.setAlignmentY(0.0F);
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel12.setIconTextGap(0);
        jLabel12.setInheritsPopupMenu(false);
        jLabel12.setRequestFocusEnabled(false);
        jLabel12.setVerifyInputWhenFocusTarget(false);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        SkipFrameType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        SkipFrameType.setText("type");
        SkipFrameType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipFrameTypeActionPerformed(evt);
            }
        });

        SkipSecondsType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        SkipSecondsType.setText("type");
        SkipSecondsType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipSecondsTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TimelapsePanelLayout = new javax.swing.GroupLayout(TimelapsePanel);
        TimelapsePanel.setLayout(TimelapsePanelLayout);
        TimelapsePanelLayout.setHorizontalGroup(
            TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimelapsePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimelapsePanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SkipSecondsField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SkipSecondsType, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TimelapsePanelLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(SkipFramesField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SkipFrameType, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12))
        );
        TimelapsePanelLayout.setVerticalGroup(
            TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimelapsePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(SkipFramesField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipFrameType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(SkipSecondsField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipSecondsType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FPSPanelLayout = new javax.swing.GroupLayout(FPSPanel);
        FPSPanel.setLayout(FPSPanelLayout);
        FPSPanelLayout.setHorizontalGroup(
            FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FPSPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TimelapsePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(FPSPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(FPSField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(FPSType, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(CalculatedFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        FPSPanelLayout.setVerticalGroup(
            FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FPSPanelLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CalculatedFPS)
                    .addComponent(jLabel9)
                    .addComponent(FPSType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FPSField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TimelapsePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        TimelapsePanel.getAccessibleContext().setAccessibleName("a");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(800, 800, 800)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
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

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.Player.PlayVideoStream();
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.Player.PlayVideoStream();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void UpdateWidthLabel() {
        
    }

    private void UpdateHeightLabel() {
    }
    private void SkipFramesFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SkipFramesFieldCaretUpdate
        UpdateWidthLabel();
    }//GEN-LAST:event_SkipFramesFieldCaretUpdate

    private void FPSTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FPSTypeActionPerformed
        Parent.NumberPanel.Load("FPS", Integer.parseInt(FPSField.getText()), FPSField, "FPSSettings");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Numberpanel");
}//GEN-LAST:event_FPSTypeActionPerformed

    private void FPSFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FPSFieldCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_FPSFieldCaretUpdate

    private void SkipSecondsFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SkipSecondsFieldCaretUpdate
        CalculatedFPS.setText("actual FPS: " + (Float.parseFloat(FPSField.getText()) / Integer.parseInt(SkipFramesField.getText())));
    }//GEN-LAST:event_SkipSecondsFieldCaretUpdate

    private void SkipSecondsTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipSecondsTypeActionPerformed
        Parent.NumberPanel.Load("Skip Seconds", Integer.parseInt(SkipSecondsField.getText()), SkipSecondsField, "FPSSettings");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Numberpanel");
    }//GEN-LAST:event_SkipSecondsTypeActionPerformed

    private void SkipFrameTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipFrameTypeActionPerformed
        Parent.NumberPanel.Load("Skip Frames", Integer.parseInt(SkipFramesField.getText()), SkipFramesField, "FPSSettings");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Numberpanel");
    }//GEN-LAST:event_SkipFrameTypeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CalculatedFPS;
    private javax.swing.JTextField FPSField;
    private javax.swing.JPanel FPSPanel;
    private EButton FPSType;
    private EButton SettingsCancelButton;
    private EButton SettingsOKButton;
    private EButton SkipFrameType;
    private javax.swing.JTextField SkipFramesField;
    private javax.swing.JTextField SkipSecondsField;
    private EButton SkipSecondsType;
    private javax.swing.JPanel TimelapsePanel;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
