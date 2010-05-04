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

public class SettingsLayout extends javax.swing.JPanel {

    ElphelVision Parent;

    public SettingsLayout(ElphelVision parent) {
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
        if (Parent.Camera.GetPreset() == CameraPreset.AMAX) {
            SmallHD.setChecked(false);
            Amax.setChecked(true);
            Cimax.setChecked(false);
            FullHD.setChecked(false);
            color_rbg.setEnabled(false);
            Custom.setChecked(false);
        }
        if (Parent.Camera.GetPreset() == CameraPreset.CIMAX) {
            SmallHD.setChecked(false);
            Amax.setChecked(false);
            Cimax.setChecked(true);
            FullHD.setChecked(false);
            color_rbg.setEnabled(false);
            Custom.setChecked(false);
        }
        if (Parent.Camera.GetPreset() == CameraPreset.FULLHD) {
            SmallHD.setChecked(false);
            Amax.setChecked(false);
            Cimax.setChecked(false);
            FullHD.setChecked(true);
            color_rbg.setEnabled(true);
            Custom.setChecked(false);
        }
        if (Parent.Camera.GetPreset() == CameraPreset.SMALLHD) {
            SmallHD.setChecked(true);
            Amax.setChecked(false);
            Cimax.setChecked(false);
            FullHD.setChecked(false);
            color_rbg.setEnabled(true);
            Custom.setChecked(false);
        }
        if (Parent.Camera.GetPreset() == CameraPreset.CUSTOM) {
            SmallHD.setChecked(false);
            Amax.setChecked(false);
            Cimax.setChecked(false);
            FullHD.setChecked(false);
            color_rbg.setEnabled(true);
            Custom.setChecked(true);
        }
        if (Parent.Camera.GetColorMode() == ColorMode.JP46) {
            color_rbg.setChecked(false);
            color_jp4.setChecked(true);
        }
        if (Parent.Camera.GetColorMode() == ColorMode.RGB) {
            color_rbg.setChecked(true);
            color_jp4.setChecked(false);
        }

        if (Parent.Camera.GetRecordFormat() == RecordFormat.MOV) {
            FormatQuicktime.setChecked(true);
            FormatJPEGs.setChecked(false);
        }
        if (Parent.Camera.GetRecordFormat() == RecordFormat.JPEG) {
            FormatQuicktime.setChecked(false);
            FormatJPEGs.setChecked(true);
        }

        switch ((int) Parent.Camera.GetFPS()) {
            case 24:
                fps24.setChecked(true);
                fps25.setChecked(false);
                fps30.setChecked(false);
                fps50.setChecked(false);
                fps60.setChecked(false);
                fpscustom.setChecked(false);
                break;

            case 25:
                fps24.setChecked(false);
                fps25.setChecked(true);
                fps30.setChecked(false);
                fps50.setChecked(false);
                fps60.setChecked(false);
                fpscustom.setChecked(false);
                break;

            case 30:
                fps24.setChecked(false);
                fps25.setChecked(false);
                fps30.setChecked(true);
                fps50.setChecked(false);
                fps60.setChecked(false);
                fpscustom.setChecked(false);
                break;

            case 50:
                fps24.setChecked(false);
                fps25.setChecked(false);
                fps30.setChecked(false);
                fps50.setChecked(true);
                fps60.setChecked(false);
                fpscustom.setChecked(false);
                break;

            case 60:
                fps24.setChecked(false);
                fps25.setChecked(false);
                fps30.setChecked(false);
                fps50.setChecked(false);
                fps60.setChecked(true);
                fpscustom.setChecked(false);
                break;

            default:
                fps24.setChecked(false);
                fps25.setChecked(false);
                fps30.setChecked(false);
                fps50.setChecked(false);
                fps60.setChecked(false);
                fpscustom.setChecked(true);
                break;
        }


        JPEGQualityButton.setValue("" + Parent.Camera.GetJPEGQuality());
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
        ResolutionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Amax = new EButton();
        Cimax = new EButton();
        FullHD = new EButton();
        SmallHD = new EButton();
        Custom = new EButton();
        Full = new EButton();
        ColorModePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        color_rbg = new EButton();
        color_jp4 = new EButton();
        FPSPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fps24 = new EButton();
        fps25 = new EButton();
        fps30 = new EButton();
        fps50 = new EButton();
        fps60 = new EButton();
        fpscustom = new EButton();
        JPEGQualityPanel = new javax.swing.JPanel();
        JPEG_Plus = new EButton();
        JPEGQualityButton = new EButton();
        JPEG_Minus = new EButton();
        NavigationPanel = new javax.swing.JPanel();
        SettingsMenu1Button = new EButton();
        SettingsMenu2Button = new EButton();
        ConfirmationPanel = new javax.swing.JPanel();
        SettingsOKButton1 = new EButton();
        SettingsCancelButton = new EButton();
        WBPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        WBTungsten = new EButton();
        WBDaylight = new EButton();
        WBFlourescent = new EButton();
        WBAuto = new EButton();
        WBCloudy = new EButton();
        WBCustom = new EButton();
        RecordFormatPanel = new javax.swing.JPanel();
        FormatQuicktime = new EButton();
        jLabel5 = new javax.swing.JLabel();
        FormatJPEGs = new EButton();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        ResolutionPanel.setBackground(java.awt.Color.black);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Resolution");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Amax.setText("AMAX");
        Amax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AmaxActionPerformed(evt);
            }
        });

        Cimax.setText("CIMAX");
        Cimax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CimaxActionPerformed(evt);
            }
        });

        FullHD.setText("1080p");
        FullHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullHDActionPerformed(evt);
            }
        });

        SmallHD.setText("720p");
        SmallHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SmallHDActionPerformed(evt);
            }
        });

        Custom.setText("custom");
        Custom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomActionPerformed(evt);
            }
        });

        Full.setText("Full");
        Full.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FullActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ResolutionPanelLayout = new javax.swing.GroupLayout(ResolutionPanel);
        ResolutionPanel.setLayout(ResolutionPanelLayout);
        ResolutionPanelLayout.setHorizontalGroup(
            ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResolutionPanelLayout.createSequentialGroup()
                .addGroup(ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ResolutionPanelLayout.createSequentialGroup()
                        .addGroup(ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Custom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SmallHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FullHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Cimax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Amax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(ResolutionPanelLayout.createSequentialGroup()
                        .addGroup(ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Full, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap(1, Short.MAX_VALUE))
        );
        ResolutionPanelLayout.setVerticalGroup(
            ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ResolutionPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Full, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Amax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Cimax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FullHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SmallHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Custom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ColorModePanel.setBackground(java.awt.Color.black);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Color Mode");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        color_rbg.setText("RBG");
        color_rbg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                color_rbgActionPerformed(evt);
            }
        });

        color_jp4.setText("JP46 RAW");
        color_jp4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                color_jp4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ColorModePanelLayout = new javax.swing.GroupLayout(ColorModePanel);
        ColorModePanel.setLayout(ColorModePanelLayout);
        ColorModePanelLayout.setHorizontalGroup(
            ColorModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(color_jp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(ColorModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(color_rbg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ColorModePanelLayout.setVerticalGroup(
            ColorModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ColorModePanelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ColorModePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ColorModePanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(color_jp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(color_rbg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        FPSPanel.setBackground(java.awt.Color.black);

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("FPS");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        fps24.setText("24");
        fps24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fps24ActionPerformed(evt);
            }
        });

        fps25.setText("25");
        fps25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fps25ActionPerformed(evt);
            }
        });

        fps30.setText("30");
        fps30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fps30ActionPerformed(evt);
            }
        });

        fps50.setText("50");
        fps50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fps50ActionPerformed(evt);
            }
        });

        fps60.setText("60");
        fps60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fps60ActionPerformed(evt);
            }
        });

        fpscustom.setText("custom");
        fpscustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fpscustomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FPSPanelLayout = new javax.swing.GroupLayout(FPSPanel);
        FPSPanel.setLayout(FPSPanelLayout);
        FPSPanelLayout.setHorizontalGroup(
            FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(fpscustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fps60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fps24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fps25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fps30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(fps50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FPSPanelLayout.setVerticalGroup(
            FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FPSPanelLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FPSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FPSPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(fps25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fps24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(fps30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fps50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fps60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fpscustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JPEGQualityPanel.setBackground(new java.awt.Color(0, 0, 0));
        JPEGQualityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "JPEG Quality", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Bitstream Vera Sans", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        JPEG_Plus.setText("+");
        JPEG_Plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPEG_PlusActionPerformed(evt);
            }
        });

        JPEGQualityButton.setAlignmentY(0.0F);
        JPEGQualityButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        JPEGQualityButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        JPEGQualityButton.setIconTextGap(20);
        JPEGQualityButton.setMargin(new java.awt.Insets(0, 5, 0, 0));
        JPEGQualityButton.setText("JPEG %");
        JPEGQualityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPEGQualityButtonActionPerformed(evt);
            }
        });

        JPEG_Minus.setText("-");
        JPEG_Minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPEG_MinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPEGQualityPanelLayout = new javax.swing.GroupLayout(JPEGQualityPanel);
        JPEGQualityPanel.setLayout(JPEGQualityPanelLayout);
        JPEGQualityPanelLayout.setHorizontalGroup(
            JPEGQualityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPEGQualityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPEGQualityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPEGQualityButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPEG_Minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPEG_Plus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JPEGQualityPanelLayout.setVerticalGroup(
            JPEGQualityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPEGQualityPanelLayout.createSequentialGroup()
                .addComponent(JPEG_Plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(JPEGQualityButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPEG_Minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NavigationPanel.setBackground(java.awt.Color.black);

        SettingsMenu1Button.setChecked(true);
        SettingsMenu1Button.setText("Settings Tab 1");
        SettingsMenu1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu1ButtonActionPerformed(evt);
            }
        });

        SettingsMenu2Button.setText("Settings Tab 2");
        SettingsMenu2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu2ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationPanelLayout = new javax.swing.GroupLayout(NavigationPanel);
        NavigationPanel.setLayout(NavigationPanelLayout);
        NavigationPanelLayout.setHorizontalGroup(
            NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavigationPanelLayout.createSequentialGroup()
                .addComponent(SettingsMenu1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        NavigationPanelLayout.setVerticalGroup(
            NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsMenu1Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu2Button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ConfirmationPanel.setBackground(new java.awt.Color(0, 0, 0));

        SettingsOKButton1.setText("OK");
        SettingsOKButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsOKButton1ActionPerformed(evt);
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
                .addComponent(SettingsOKButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsOKButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        WBPanel.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("White Balance");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        WBTungsten.setAlignmentY(0.0F);
        WBTungsten.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WBTungsten.setIconTextGap(20);
        WBTungsten.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBTungsten.setText("Tungsten");
        WBTungsten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBTungstenActionPerformed(evt);
            }
        });

        WBDaylight.setAlignmentY(0.0F);
        WBDaylight.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WBDaylight.setIconTextGap(20);
        WBDaylight.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBDaylight.setText("Daylight");
        WBDaylight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBDaylightActionPerformed(evt);
            }
        });

        WBFlourescent.setAlignmentY(0.0F);
        WBFlourescent.setBorder(null);
        WBFlourescent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WBFlourescent.setIconTextGap(20);
        WBFlourescent.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBFlourescent.setText("Fluorescent");
        WBFlourescent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBFlourescentActionPerformed(evt);
            }
        });

        WBAuto.setAlignmentY(0.0F);
        WBAuto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WBAuto.setIconTextGap(20);
        WBAuto.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBAuto.setText("Auto");
        WBAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBAutoActionPerformed(evt);
            }
        });

        WBCloudy.setAlignmentY(0.0F);
        WBCloudy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WBCloudy.setIconTextGap(20);
        WBCloudy.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBCloudy.setText("Cloudy");
        WBCloudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBCloudyActionPerformed(evt);
            }
        });

        WBCustom.setAlignmentY(0.0F);
        WBCustom.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        WBCustom.setIconTextGap(20);
        WBCustom.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBCustom.setText("Custom");
        WBCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBCustomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WBPanelLayout = new javax.swing.GroupLayout(WBPanel);
        WBPanel.setLayout(WBPanelLayout);
        WBPanelLayout.setHorizontalGroup(
            WBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(WBCustom, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(WBCloudy, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(WBFlourescent, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(WBTungsten, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(WBDaylight, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
            .addComponent(WBAuto, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );
        WBPanelLayout.setVerticalGroup(
            WBPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WBPanelLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WBAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WBDaylight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WBTungsten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WBFlourescent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WBCloudy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WBCustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        RecordFormatPanel.setBackground(new java.awt.Color(0, 0, 0));

        FormatQuicktime.setText("Quicktime");
        FormatQuicktime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormatQuicktimeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Format");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        FormatJPEGs.setText("Image Seq.");
        FormatJPEGs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FormatJPEGsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RecordFormatPanelLayout = new javax.swing.GroupLayout(RecordFormatPanel);
        RecordFormatPanel.setLayout(RecordFormatPanelLayout);
        RecordFormatPanelLayout.setHorizontalGroup(
            RecordFormatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FormatJPEGs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(FormatQuicktime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RecordFormatPanelLayout.setVerticalGroup(
            RecordFormatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecordFormatPanelLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FormatQuicktime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FormatJPEGs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(ResolutionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(ColorModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(NavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(JPEGQualityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(WBPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RecordFormatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
                        .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPEGQualityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConfirmationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RecordFormatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WBPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ResolutionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ColorModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(NavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void SettingsMenu2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu2ButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings2Card");
        Parent.Settings2CardLayout.Load();
        Parent.Settings2CardLayout.StartMplayerVideoStream();
    }//GEN-LAST:event_SettingsMenu2ButtonActionPerformed

    private void FullHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullHDActionPerformed
        FullHD.setChecked(true);
        Cimax.setChecked(false);
        Amax.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        /*
        color_rbg.setEnabled(true);
         */
    }//GEN-LAST:event_FullHDActionPerformed

    private void color_jp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_jp4ActionPerformed
        color_jp4.setChecked(true);
        color_rbg.setChecked(false);
    }//GEN-LAST:event_color_jp4ActionPerformed

    private void CimaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CimaxActionPerformed
        Cimax.setChecked(true);
        Amax.setChecked(false);
        FullHD.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        /*
        color_rbg.setEnabled(false);
        color_rbg.setChecked(false);
        color_jp4.setChecked(true);
         */
    }//GEN-LAST:event_CimaxActionPerformed

    private void AmaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmaxActionPerformed
        Amax.setChecked(true);
        Cimax.setChecked(false);
        FullHD.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        /*
        color_rbg.setEnabled(false);
        color_rbg.setChecked(false);
        color_jp4.setChecked(true);
         */
    }//GEN-LAST:event_AmaxActionPerformed

    private void SmallHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmallHDActionPerformed
        SmallHD.setChecked(true);
        Amax.setChecked(false);
        Cimax.setChecked(false);
        FullHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        /*
        color_rbg.setEnabled(true);
         */
    }//GEN-LAST:event_SmallHDActionPerformed

    private void color_rbgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_rbgActionPerformed
        color_jp4.setChecked(false);
        color_rbg.setChecked(true);
    }//GEN-LAST:event_color_rbgActionPerformed

    private void fps30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps30ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(false);
        fps30.setChecked(true);
        fps50.setChecked(false);
        fps60.setChecked(false);
        fpscustom.setChecked(false);
    }//GEN-LAST:event_fps30ActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.StartMplayerVideoStream();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void CustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomActionPerformed
        Parent.ResolutionSettingsCardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "CustomResolutionCard");
    }//GEN-LAST:event_CustomActionPerformed

    private void fps25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps25ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(true);
        fps30.setChecked(false);
        fps50.setChecked(false);
        fps60.setChecked(false);
        fpscustom.setChecked(false);
    }//GEN-LAST:event_fps25ActionPerformed

    private void fps24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps24ActionPerformed
        fps24.setChecked(true);
        fps25.setChecked(false);
        fps30.setChecked(false);
        fps50.setChecked(false);
        fps60.setChecked(false);
        fpscustom.setChecked(false);
    }//GEN-LAST:event_fps24ActionPerformed

    private void fps50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps50ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(false);
        fps30.setChecked(false);
        fps50.setChecked(true);
        fps60.setChecked(false);
        fpscustom.setChecked(false);
    }//GEN-LAST:event_fps50ActionPerformed

    private void fps60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps60ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(false);
        fps30.setChecked(false);
        fps50.setChecked(false);
        fps60.setChecked(true);
        fpscustom.setChecked(false);
    }//GEN-LAST:event_fps60ActionPerformed

    private void fpscustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpscustomActionPerformed
        Parent.FPSSettingsCardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "CustomFPSCard");
    }//GEN-LAST:event_fpscustomActionPerformed

    private void JPEG_PlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPEG_PlusActionPerformed
        Parent.Camera.SetJPEGQuality(Parent.Camera.GetJPEGQuality() + 1);
        JPEGQualityButton.setValue("" + Parent.Camera.GetJPEGQuality());
    }//GEN-LAST:event_JPEG_PlusActionPerformed

    private void JPEGQualityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPEGQualityButtonActionPerformed
}//GEN-LAST:event_JPEGQualityButtonActionPerformed

    private void JPEG_MinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPEG_MinusActionPerformed
        Parent.Camera.SetJPEGQuality(Parent.Camera.GetJPEGQuality() - 1);
        JPEGQualityButton.setValue("" + Parent.Camera.GetJPEGQuality());
    }//GEN-LAST:event_JPEG_MinusActionPerformed

    private void SettingsOKButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsOKButton1ActionPerformed
        if (Full.getChecked()) {
            Parent.Camera.SetPreset(CameraPreset.FULL);
        }

        if (FullHD.getChecked()) {
            Parent.Camera.SetPreset(CameraPreset.FULLHD);
        }

        if (SmallHD.getChecked()) {
            Parent.Camera.SetPreset(CameraPreset.SMALLHD);
        }

        if (Amax.getChecked()) {
            Parent.Camera.SetPreset(CameraPreset.AMAX);
        }

        if (Cimax.getChecked()) {
            Parent.Camera.SetPreset(CameraPreset.CIMAX);
        }

        if (color_rbg.getChecked()) {
            Parent.Camera.SetColorMode(ColorMode.RGB);
        }

        if (color_jp4.getChecked()) {
            Parent.Camera.SetColorMode(ColorMode.JP46);
        }

        if (fps24.getChecked()) {
            Parent.Camera.SetFPS(24.0f);
        }

        if (fps25.getChecked()) {
            Parent.Camera.SetFPS(25.0f);
        }

        if (fps30.getChecked()) {
            Parent.Camera.SetFPS(30.0f);
        }

        if (fps50.getChecked()) {
            Parent.Camera.SetFPS(50.0f);
        }

        if (fps60.getChecked()) {
            Parent.Camera.SetFPS(60.0f);
        }

        if (WBAuto.getChecked()) {
            Parent.Camera.SetWhiteBalance(WhiteBalance.AUTO);
        }

        if (WBDaylight.getChecked()) {
            Parent.Camera.SetWhiteBalance(WhiteBalance.DAYLIGHT);
        }

        if (WBTungsten.getChecked()) {
            Parent.Camera.SetWhiteBalance(WhiteBalance.TUNGSTEN);
        }

        if (WBFlourescent.getChecked()) {
            Parent.Camera.SetWhiteBalance(WhiteBalance.FLOURESCENT);
        }

        if (WBCloudy.getChecked()) {
            Parent.Camera.SetWhiteBalance(WhiteBalance.CLOUDY);
        }

        if (FormatQuicktime.getChecked()) {
            Parent.Camera.SetRecordFormat(RecordFormat.MOV);
        }

        if (FormatJPEGs.getChecked()) {
            Parent.Camera.SetRecordFormat(RecordFormat.JPEG);
        }

        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.cfg");
        } catch (IOException ex) {
            Logger.getLogger(SettingsLayout.class.getName()).log(Level.SEVERE, null, ex);
        }

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.StartMplayerVideoStream();
    }//GEN-LAST:event_SettingsOKButton1ActionPerformed

    private void SettingsMenu1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu1ButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SettingsMenu1ButtonActionPerformed

    private void WBTungstenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBTungstenActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(true);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);
    }//GEN-LAST:event_WBTungstenActionPerformed

    private void WBDaylightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBDaylightActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(true);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);
    }//GEN-LAST:event_WBDaylightActionPerformed

    private void WBFlourescentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBFlourescentActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(true);
        WBCustom.setChecked(false);
    }//GEN-LAST:event_WBFlourescentActionPerformed

    private void WBAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBAutoActionPerformed
        WBAuto.setChecked(true);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);
    }//GEN-LAST:event_WBAutoActionPerformed

    private void WBCloudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBCloudyActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(true);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);
    }//GEN-LAST:event_WBCloudyActionPerformed

    private void WBCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WBCustomActionPerformed

    private void FormatJPEGsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormatJPEGsActionPerformed
        FormatQuicktime.setChecked(false);
        FormatJPEGs.setChecked(true);
    }//GEN-LAST:event_FormatJPEGsActionPerformed

    private void FormatQuicktimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormatQuicktimeActionPerformed
        FormatQuicktime.setChecked(true);
        FormatJPEGs.setChecked(false);
    }//GEN-LAST:event_FormatQuicktimeActionPerformed

    private void FullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullActionPerformed
        FullHD.setChecked(false);
        Cimax.setChecked(false);
        Amax.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(true);
    }//GEN-LAST:event_FullActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EButton Amax;
    private EButton Cimax;
    private javax.swing.JPanel ColorModePanel;
    private javax.swing.JPanel ConfirmationPanel;
    private EButton Custom;
    private javax.swing.JPanel FPSPanel;
    private EButton FormatJPEGs;
    private EButton FormatQuicktime;
    private EButton Full;
    private EButton FullHD;
    private EButton JPEGQualityButton;
    private javax.swing.JPanel JPEGQualityPanel;
    private EButton JPEG_Minus;
    private EButton JPEG_Plus;
    private javax.swing.JPanel NavigationPanel;
    private javax.swing.JPanel RecordFormatPanel;
    private javax.swing.JPanel ResolutionPanel;
    private EButton SettingsCancelButton;
    private EButton SettingsMenu1Button;
    private EButton SettingsMenu2Button;
    private EButton SettingsOKButton1;
    private EButton SmallHD;
    private EButton WBAuto;
    private EButton WBCloudy;
    private EButton WBCustom;
    private EButton WBDaylight;
    private EButton WBFlourescent;
    private javax.swing.JPanel WBPanel;
    private EButton WBTungsten;
    private javax.swing.JPanel bg;
    private EButton color_jp4;
    private EButton color_rbg;
    private EButton fps24;
    private EButton fps25;
    private EButton fps30;
    private EButton fps50;
    private EButton fps60;
    private EButton fpscustom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
