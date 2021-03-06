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
        FPSField.setText(Parent.Camera.GetFPS() + "");
        SkipSecondsField.setText(Parent.Camera.GetFPSSkipSeconds() + "");
        SkipFramesField.setText(Parent.Camera.GetFPSSkipFrames() + "");
        if (Parent.Camera.GetFPSSkipSeconds() != 0) {
            SkipSecondsButton.setChecked(true);
            SkipFramesButton.setChecked(false);

            SkipSecondsField.setEnabled(true);
            SkipFramesField.setEnabled(false);

            SkipSecondsType.setEnabled(true);
            SkipFramesType.setEnabled(false);
        }
        if (Parent.Camera.GetFPSSkipFrames() != 0) {
            SkipSecondsButton.setChecked(false);
            SkipFramesButton.setChecked(true);

            SkipSecondsField.setEnabled(false);
            SkipFramesField.setEnabled(true);

            SkipSecondsType.setEnabled(false);
            SkipFramesType.setEnabled(true);
        }
        if (Parent.Camera.getFrameTrigger() == Trigger.FREERUNNING) {
            TriggerFreeRunning.setChecked(true);
            TriggerTriggered.setChecked(false);
        } else {
            TriggerFreeRunning.setChecked(false);
            TriggerTriggered.setChecked(true);
        }
    }

    private void UpdateResultingFPS() {
        float FPS = 0;
        if (FPSField.getText().isEmpty()) {
            FPS = Parent.Camera.GetFPS();
        } else {
            FPS = Float.parseFloat(FPSField.getText());
        }

        if (SkipSecondsButton.isChecked()) {
            if (SkipSecondsField.getText().isEmpty() || SkipSecondsField.getText().equals("0")) {
                if (!FPSField.getText().isEmpty()) {
                    CalculatedFPS.setText("resulting FPS: " + FPS);
                }
            } else {
                CalculatedFPS.setText("resulting FPS: " + Utils.Round(1.0f / (Float.parseFloat(SkipSecondsField.getText())), 2));
            }
        }
        if (SkipFramesButton.isChecked()) {
            if (SkipFramesField.getText().isEmpty() || SkipFramesField.getText().equals("0")) {
                if (!FPSField.getText().isEmpty()) {
                    CalculatedFPS.setText("resulting FPS: " + FPS);
                }
            } else {
                CalculatedFPS.setText("resulting FPS: " + Utils.Round(FPS / (1 + Integer.parseInt(SkipFramesField.getText())), 2));
            }
        }
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
        SettingsCancelButton = new EButton(Parent);
        SettingsOKButton = new EButton(Parent);
        FPSPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        FPSType = new EButton(Parent);
        FPSField = new javax.swing.JTextField();
        TimelapsePanel = new javax.swing.JPanel();
        SkipFramesField = new javax.swing.JTextField();
        SkipSecondsField = new javax.swing.JTextField();
        SkipFramesType = new EButton(Parent);
        SkipSecondsType = new EButton(Parent);
        SkipFramesButton = new EButton(Parent);
        SkipSecondsButton = new EButton(Parent);
        CalculatedFPS = new javax.swing.JLabel();
        TriggerFreeRunning = new EButton(Parent);
        CalculatedFPS1 = new javax.swing.JLabel();
        TriggerTriggered = new EButton(Parent);

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

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
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

        FPSType.setText("type");
        FPSType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
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
        FPSField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FPSFieldActionPerformed(evt);
            }
        });

        TimelapsePanel.setBackground(new java.awt.Color(0, 0, 0));
        TimelapsePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Timelapse", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

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
        SkipSecondsField.setEnabled(false);
        SkipSecondsField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                SkipSecondsFieldCaretUpdate(evt);
            }
        });

        SkipFramesType.setText("type");
        SkipFramesType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        SkipFramesType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipFramesTypeActionPerformed(evt);
            }
        });

        SkipSecondsType.setText("type");
        SkipSecondsType.setEnabled(false);
        SkipSecondsType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        SkipSecondsType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipSecondsTypeActionPerformed(evt);
            }
        });

        SkipFramesButton.setText("Skip Frames");
        SkipFramesButton.setChecked(true);
        SkipFramesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipFramesButtonActionPerformed(evt);
            }
        });

        SkipSecondsButton.setText("Skip Seconds");
        SkipSecondsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipSecondsButtonActionPerformed(evt);
            }
        });

        CalculatedFPS.setBackground(new java.awt.Color(0, 0, 0));
        CalculatedFPS.setForeground(new java.awt.Color(255, 255, 255));
        CalculatedFPS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CalculatedFPS.setText("resulting FPS:");

        javax.swing.GroupLayout TimelapsePanelLayout = new javax.swing.GroupLayout(TimelapsePanel);
        TimelapsePanel.setLayout(TimelapsePanelLayout);
        TimelapsePanelLayout.setHorizontalGroup(
            TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimelapsePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TimelapsePanelLayout.createSequentialGroup()
                        .addComponent(SkipSecondsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SkipSecondsField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SkipSecondsType, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TimelapsePanelLayout.createSequentialGroup()
                        .addComponent(SkipFramesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SkipFramesField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SkipFramesType, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CalculatedFPS, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TimelapsePanelLayout.setVerticalGroup(
            TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimelapsePanelLayout.createSequentialGroup()
                .addGroup(TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkipFramesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipFramesField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipFramesType, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TimelapsePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SkipSecondsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipSecondsField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SkipSecondsType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CalculatedFPS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TriggerFreeRunning.setText("continous\\nfree-running");
        TriggerFreeRunning.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        TriggerFreeRunning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TriggerFreeRunningActionPerformed(evt);
            }
        });

        CalculatedFPS1.setBackground(new java.awt.Color(0, 0, 0));
        CalculatedFPS1.setForeground(new java.awt.Color(255, 255, 255));
        CalculatedFPS1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CalculatedFPS1.setText("Frame Read-Out");

        TriggerTriggered.setText("triggered");
        TriggerTriggered.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        TriggerTriggered.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TriggerTriggeredActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FPSPanelLayout = new javax.swing.GroupLayout(FPSPanel);
        FPSPanel.setLayout(FPSPanelLayout);
        FPSPanelLayout.setHorizontalGroup(
            FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FPSPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FPSPanelLayout.createSequentialGroup()
                        .addComponent(TimelapsePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CalculatedFPS1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TriggerTriggered, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TriggerFreeRunning, javax.swing.GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)))
                    .addGroup(FPSPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(FPSField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(FPSType, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        FPSPanelLayout.setVerticalGroup(
            FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FPSPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(FPSType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FPSField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(TimelapsePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FPSPanelLayout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(CalculatedFPS1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TriggerFreeRunning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TriggerTriggered, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        TimelapsePanel.getAccessibleContext().setAccessibleName("a");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGap(800, 800, 800)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 319, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        Parent.Camera.SetFPS(Float.parseFloat(FPSField.getText()));

        if (SkipSecondsButton.isChecked()) {
            Parent.Camera.SetFPSSkipSeconds(Integer.parseInt(SkipSecondsField.getText()));
            //Parent.Camera.SetFPSSkipFrames(0);
        }
        if (SkipFramesButton.isChecked()) {
            //Parent.Camera.SetFPSSkipSeconds(0);
            Parent.Camera.SetFPSSkipFrames(Integer.parseInt(SkipFramesField.getText()));
        }

        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.Settings1CardLayout.Load();
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.Settings1CardLayout.Load();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void SkipFramesFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SkipFramesFieldCaretUpdate
        UpdateResultingFPS();
    }//GEN-LAST:event_SkipFramesFieldCaretUpdate

    private void FPSTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FPSTypeActionPerformed
        Parent.NumberPanelFloat.Load("FPS", Float.parseFloat(FPSField.getText()), FPSField, "CustomFPSCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelFloat");
}//GEN-LAST:event_FPSTypeActionPerformed

    private void FPSFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_FPSFieldCaretUpdate
        UpdateResultingFPS();
    }//GEN-LAST:event_FPSFieldCaretUpdate

    private void SkipSecondsFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_SkipSecondsFieldCaretUpdate
        UpdateResultingFPS();
    }//GEN-LAST:event_SkipSecondsFieldCaretUpdate

    private void SkipSecondsTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipSecondsTypeActionPerformed
        Parent.NumberPanelInteger.Load("Skip Seconds", Integer.parseInt(SkipSecondsField.getText()), SkipSecondsField, "CustomFPSCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelInteger");
    }//GEN-LAST:event_SkipSecondsTypeActionPerformed

    private void SkipFramesTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipFramesTypeActionPerformed
        Parent.NumberPanelInteger.Load("Skip Frames", Integer.parseInt(SkipFramesField.getText()), SkipFramesField, "CustomFPSCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelInteger");
    }//GEN-LAST:event_SkipFramesTypeActionPerformed

    private void SkipFramesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipFramesButtonActionPerformed
        SkipSecondsButton.setChecked(false);
        SkipFramesButton.setChecked(true);

        SkipSecondsField.setEnabled(false);
        SkipFramesField.setEnabled(true);

        SkipSecondsType.setEnabled(false);
        SkipFramesType.setEnabled(true);

        UpdateResultingFPS();
    }//GEN-LAST:event_SkipFramesButtonActionPerformed

    private void SkipSecondsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipSecondsButtonActionPerformed
        SkipSecondsButton.setChecked(true);
        SkipFramesButton.setChecked(false);

        SkipSecondsField.setEnabled(true);
        SkipFramesField.setEnabled(false);

        SkipSecondsType.setEnabled(true);
        SkipFramesType.setEnabled(false);

        UpdateResultingFPS();
    }//GEN-LAST:event_SkipSecondsButtonActionPerformed

    private void FPSFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FPSFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FPSFieldActionPerformed

private void TriggerFreeRunningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TriggerFreeRunningActionPerformed
    Parent.Camera.setFrameTrigger(Trigger.FREERUNNING);
    TriggerFreeRunning.setChecked(true);
    TriggerTriggered.setChecked(false);
}//GEN-LAST:event_TriggerFreeRunningActionPerformed

private void TriggerTriggeredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TriggerTriggeredActionPerformed
    Parent.Camera.setFrameTrigger(Trigger.TRIGGERED);
    TriggerFreeRunning.setChecked(false);
    TriggerTriggered.setChecked(true);
}//GEN-LAST:event_TriggerTriggeredActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CalculatedFPS;
    private javax.swing.JLabel CalculatedFPS1;
    private javax.swing.JTextField FPSField;
    private javax.swing.JPanel FPSPanel;
    private EButton FPSType;
    private EButton SettingsCancelButton;
    private EButton SettingsOKButton;
    private EButton SkipFramesButton;
    private javax.swing.JTextField SkipFramesField;
    private EButton SkipFramesType;
    private EButton SkipSecondsButton;
    private javax.swing.JTextField SkipSecondsField;
    private EButton SkipSecondsType;
    private javax.swing.JPanel TimelapsePanel;
    private EButton TriggerFreeRunning;
    private EButton TriggerTriggered;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
