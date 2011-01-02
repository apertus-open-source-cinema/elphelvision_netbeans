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

public class ResolutionSettings extends javax.swing.JPanel {

    ElphelVision Parent;

    public ResolutionSettings(ElphelVision parent) {
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
       
        WidthField.setText(Integer.toString(Parent.Camera.GetImageWidth()));
        HeightField.setText(Integer.toString(Parent.Camera.GetImageHeight()));

        if (Parent.Camera.GetImageOrientation() == ImageOrientation.LANDSCAPE) {
            OrientationPortrait.setChecked(false);
            OrientationLandscape.setChecked(true);
        }
        if (Parent.Camera.GetImageOrientation() == ImageOrientation.PORTRAIT) {
            OrientationPortrait.setChecked(true);
            OrientationLandscape.setChecked(false);
        }
        UpdateWidthLabel();
        UpdateHeightLabel();

        if (Parent.Camera.GetImageFlipMode() == MirrorImage.NONE) {
            FlipHorizontal.setChecked(false);
            FlipVertical.setChecked(false);
        }
        if (Parent.Camera.GetImageFlipMode() == MirrorImage.HORIZONTAL) {
            FlipHorizontal.setChecked(true);
            FlipVertical.setChecked(false);
        }
        if (Parent.Camera.GetImageFlipMode() == MirrorImage.VERTICAL) {
            FlipHorizontal.setChecked(false);
            FlipVertical.setChecked(true);
        }
        if (Parent.Camera.GetImageFlipMode() == MirrorImage.VERTICALHORIZONTAL) {
            FlipHorizontal.setChecked(true);
            FlipVertical.setChecked(true);
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
        MirrorPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        FlipHorizontal = new EButton();
        FlipVertical = new EButton();
        RotationPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        OrientationLandscape = new EButton();
        OrientationPortrait = new EButton();
        ConfirmationPanel = new javax.swing.JPanel();
        SettingsCancelButton = new EButton();
        SettingsOKButton = new EButton();
        DimensionsPanel = new javax.swing.JPanel();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        WidthField = new javax.swing.JTextField();
        CalculatedWidth = new javax.swing.JLabel();
        CalculatedHeight = new javax.swing.JLabel();
        HeightField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        HeightType = new EButton();
        WidthType = new EButton();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        MirrorPanel.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Mirror Image");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setMaximumSize(new java.awt.Dimension(84, 14));
        jLabel10.setMinimumSize(new java.awt.Dimension(84, 14));
        jLabel10.setPreferredSize(new java.awt.Dimension(84, 14));

        FlipHorizontal.setText("horizontal");
        FlipHorizontal.setAlignmentY(0.0F);
        FlipHorizontal.setHorizontalTextPosition(2);
        FlipHorizontal.setIconTextGap(20);
        FlipHorizontal.setMargin(new java.awt.Insets(0, 5, 0, 0));
        FlipHorizontal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlipHorizontalActionPerformed(evt);
            }
        });

        FlipVertical.setText("vertical");
        FlipVertical.setAlignmentY(0.0F);
        FlipVertical.setHorizontalTextPosition(2);
        FlipVertical.setIconTextGap(20);
        FlipVertical.setMargin(new java.awt.Insets(0, 5, 0, 0));
        FlipVertical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlipVerticalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MirrorPanelLayout = new javax.swing.GroupLayout(MirrorPanel);
        MirrorPanel.setLayout(MirrorPanelLayout);
        MirrorPanelLayout.setHorizontalGroup(
            MirrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FlipVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(MirrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FlipHorizontal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MirrorPanelLayout.setVerticalGroup(
            MirrorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MirrorPanelLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FlipHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FlipVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        RotationPanel.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Orientation");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        OrientationLandscape.setText("Landscape");
        OrientationLandscape.setAlignmentY(0.0F);
        OrientationLandscape.setHorizontalTextPosition(2);
        OrientationLandscape.setIconTextGap(20);
        OrientationLandscape.setMargin(new java.awt.Insets(0, 5, 0, 0));
        OrientationLandscape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrientationLandscapeActionPerformed(evt);
            }
        });

        OrientationPortrait.setText("Portrait");
        OrientationPortrait.setAlignmentY(0.0F);
        OrientationPortrait.setHorizontalTextPosition(2);
        OrientationPortrait.setIconTextGap(20);
        OrientationPortrait.setMargin(new java.awt.Insets(0, 5, 0, 0));
        OrientationPortrait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrientationPortraitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RotationPanelLayout = new javax.swing.GroupLayout(RotationPanel);
        RotationPanel.setLayout(RotationPanelLayout);
        RotationPanelLayout.setHorizontalGroup(
            RotationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(OrientationLandscape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(OrientationPortrait, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        RotationPanelLayout.setVerticalGroup(
            RotationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RotationPanelLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OrientationLandscape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OrientationPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ConfirmationPanel.setBackground(java.awt.Color.black);

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

        javax.swing.GroupLayout ConfirmationPanelLayout = new javax.swing.GroupLayout(ConfirmationPanel);
        ConfirmationPanel.setLayout(ConfirmationPanelLayout);
        ConfirmationPanelLayout.setHorizontalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        DimensionsPanel.setBackground(java.awt.Color.black);

        jTextArea1.setBackground(java.awt.Color.black);
        jTextArea1.setColumns(5);
        jTextArea1.setEditable(false);
        jTextArea1.setForeground(new java.awt.Color(254, 254, 254));
        jTextArea1.setRows(3);
        jTextArea1.setText("Width and Height must be a multiple  of 16.");
        jTextArea1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextArea1.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Width");
        jLabel9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel9.setAlignmentY(0.0F);
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel9.setIconTextGap(0);
        jLabel9.setInheritsPopupMenu(false);
        jLabel9.setRequestFocusEnabled(false);
        jLabel9.setVerifyInputWhenFocusTarget(false);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        WidthField.setBackground(new java.awt.Color(0, 0, 0));
        WidthField.setForeground(new java.awt.Color(255, 255, 255));
        WidthField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        WidthField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        WidthField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                WidthFieldCaretUpdate(evt);
            }
        });

        CalculatedWidth.setBackground(new java.awt.Color(0, 0, 0));
        CalculatedWidth.setForeground(new java.awt.Color(255, 255, 255));
        CalculatedWidth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CalculatedWidth.setText("jLabel1");

        CalculatedHeight.setBackground(new java.awt.Color(0, 0, 0));
        CalculatedHeight.setForeground(new java.awt.Color(255, 255, 255));
        CalculatedHeight.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CalculatedHeight.setText("jLabel1");

        HeightField.setBackground(new java.awt.Color(0, 0, 0));
        HeightField.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 14));
        HeightField.setForeground(new java.awt.Color(255, 255, 255));
        HeightField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        HeightField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        HeightField.setCaretColor(new java.awt.Color(254, 254, 254));
        HeightField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                HeightFieldCaretUpdate(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Height");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel7.setAlignmentY(0.0F);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel7.setIconTextGap(0);
        jLabel7.setInheritsPopupMenu(false);
        jLabel7.setRequestFocusEnabled(false);
        jLabel7.setVerifyInputWhenFocusTarget(false);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        HeightType.setText("type");
        HeightType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        HeightType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HeightTypeActionPerformed(evt);
            }
        });

        WidthType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        WidthType.setText("type");
        WidthType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WidthTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DimensionsPanelLayout = new javax.swing.GroupLayout(DimensionsPanel);
        DimensionsPanel.setLayout(DimensionsPanelLayout);
        DimensionsPanelLayout.setHorizontalGroup(
            DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DimensionsPanelLayout.createSequentialGroup()
                .addGroup(DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(WidthField)
                    .addComponent(HeightField, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(WidthType, 0, 60, Short.MAX_VALUE)
                    .addComponent(HeightType, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CalculatedHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalculatedWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
            .addGroup(DimensionsPanelLayout.createSequentialGroup()
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DimensionsPanelLayout.setVerticalGroup(
            DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DimensionsPanelLayout.createSequentialGroup()
                .addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WidthField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(CalculatedWidth)
                    .addComponent(WidthType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(DimensionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(HeightField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CalculatedHeight)
                    .addComponent(HeightType, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DimensionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RotationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MirrorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(383, 383, 383))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(800, Short.MAX_VALUE)
                .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MirrorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RotationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(DimensionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 395, Short.MAX_VALUE)
                        .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        Parent.Camera.SetParameter(CameraParameter.SENSORWIDTH, Integer.parseInt(CalculatedWidth.getText()));
        Parent.Camera.SetParameter(CameraParameter.SENSORHEIGHT, Integer.parseInt(CalculatedHeight.getText()));

        Parent.Camera.SetPreset(CameraPreset.CUSTOM);

        if (OrientationPortrait.getChecked()) {
            Parent.Camera.SetImageOrientation(ImageOrientation.PORTRAIT);
        }
        if (OrientationLandscape.getChecked()) {
            Parent.Camera.SetImageOrientation(ImageOrientation.LANDSCAPE);
        }

        if (!FlipHorizontal.getChecked() && !FlipVertical.getChecked()) {
            Parent.Camera.SetImageFlipMode(MirrorImage.NONE);
        }
        if (FlipHorizontal.getChecked() && !FlipVertical.getChecked()) {
            Parent.Camera.SetImageFlipMode(MirrorImage.HORIZONTAL);
        }
        if (!FlipHorizontal.getChecked() && FlipVertical.getChecked()) {
            Parent.Camera.SetImageFlipMode(MirrorImage.VERTICAL);
        }
        if (FlipHorizontal.getChecked() && FlipVertical.getChecked()) {
            Parent.Camera.SetImageFlipMode(MirrorImage.VERTICALHORIZONTAL);
        }

        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(ResolutionSettings.class.getName()).log(Level.SEVERE, null, ex);
        }

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.StartVideoPlayer();
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.StartVideoPlayer();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void UpdateWidthLabel() {
        if (!WidthField.getText().isEmpty()) {
            int WidthValue = 0;
            if (OrientationPortrait.getChecked()) {
                WidthValue = Integer.parseInt(HeightField.getText());
            }
            if (OrientationLandscape.getChecked()) {
                WidthValue = Integer.parseInt(WidthField.getText());
            }
            if (WidthValue % 16 != 0) {
                WidthValue = Math.round(WidthValue / 16) * 16;
            }
            CalculatedWidth.setText(Integer.toString(WidthValue));
        }
    }

    private void UpdateHeightLabel() {
        if (!HeightField.getText().isEmpty()) {
            int HeightValue = 0;
            if (OrientationPortrait.getChecked()) {
                HeightValue = Integer.parseInt(WidthField.getText());
            }
            if (OrientationLandscape.getChecked()) {
                HeightValue = Integer.parseInt(HeightField.getText());
            }
            if (HeightValue % 16 != 0) {
                HeightValue = Math.round(HeightValue / 16) * 16;
            }
            CalculatedHeight.setText(Integer.toString(HeightValue));
        }
    }
    private void WidthFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_WidthFieldCaretUpdate
        UpdateWidthLabel();
    }//GEN-LAST:event_WidthFieldCaretUpdate

    private void HeightFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_HeightFieldCaretUpdate
        UpdateHeightLabel();
    }//GEN-LAST:event_HeightFieldCaretUpdate

    private void OrientationLandscapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrientationLandscapeActionPerformed
        OrientationLandscape.setChecked(true);
        OrientationPortrait.setChecked(false);

        UpdateWidthLabel();
        UpdateHeightLabel();
    }//GEN-LAST:event_OrientationLandscapeActionPerformed

    private void OrientationPortraitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrientationPortraitActionPerformed
        OrientationLandscape.setChecked(false);
        OrientationPortrait.setChecked(true);

        UpdateWidthLabel();
        UpdateHeightLabel();
    }//GEN-LAST:event_OrientationPortraitActionPerformed

    private void FlipHorizontalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlipHorizontalActionPerformed
        if (FlipHorizontal.getChecked()) {
            FlipHorizontal.setChecked(false);
        } else {
            FlipHorizontal.setChecked(true);
        }
    }//GEN-LAST:event_FlipHorizontalActionPerformed

    private void FlipVerticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlipVerticalActionPerformed
        if (FlipVertical.getChecked()) {
            FlipVertical.setChecked(false);
        } else {
            FlipVertical.setChecked(true);
        }
    }//GEN-LAST:event_FlipVerticalActionPerformed

    private void HeightTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HeightTypeActionPerformed
        Parent.NumberPanelInteger.Load("Height", Integer.parseInt(HeightField.getText()), HeightField, "CustomResolutionCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelInteger");
    }//GEN-LAST:event_HeightTypeActionPerformed

    private void WidthTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WidthTypeActionPerformed
        Parent.NumberPanelInteger.Load("Width", Integer.parseInt(WidthField.getText()), WidthField, "CustomResolutionCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelInteger");
    }//GEN-LAST:event_WidthTypeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CalculatedHeight;
    private javax.swing.JLabel CalculatedWidth;
    private javax.swing.JPanel ConfirmationPanel;
    private javax.swing.JPanel DimensionsPanel;
    private EButton FlipHorizontal;
    private EButton FlipVertical;
    private javax.swing.JTextField HeightField;
    private EButton HeightType;
    private javax.swing.JPanel MirrorPanel;
    private EButton OrientationLandscape;
    private EButton OrientationPortrait;
    private javax.swing.JPanel RotationPanel;
    private EButton SettingsCancelButton;
    private EButton SettingsOKButton;
    private javax.swing.JTextField WidthField;
    private EButton WidthType;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
