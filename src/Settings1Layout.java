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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Settings1Layout extends javax.swing.JPanel implements Runnable {

    ElphelVision Parent;
    Thread SettingsOVerviewUpdater;
    int UpdaterFPS = 4;

    public Settings1Layout(ElphelVision parent) {
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

        datarateMonitor1.SetParent(Parent);
    }

    private void startUpdater() {
        SettingsOVerviewUpdater = new Thread(this);
        SettingsOVerviewUpdater.start();
    }

    public void run() {
        if (!Parent.GetNoCameraParameter()) {
            while (Thread.currentThread() == SettingsOVerviewUpdater) {

                Overview_Resolution.setText(Parent.Camera.GetImageWidth() + " x " + Parent.Camera.GetImageHeight());

                if (Parent.Camera.GetFPSSkipFrames() != 0) {
                    Overview_FPS.setText(Utils.Round(Parent.Camera.GetFPS() / (1.0f + Parent.Camera.GetFPSSkipFrames()), 3) + " (FS)");
                } else if (Parent.Camera.GetFPSSkipSeconds() != 0) {
                    Overview_FPS.setText(Utils.Round((1.0f / Parent.Camera.GetFPSSkipSeconds()), 3) + " (SS)");
                } else {
                    Overview_FPS.setText(Parent.Camera.GetFPS() + "");
                }

                Overview_JPEGQ.setText(Parent.Camera.GetJPEGQuality() + " %");
                if (Parent.Camera.GetColorMode() == ColorMode.JP4) {
                    Overview_ColorMode.setText("JP4 RAW");
                } else {
                    Overview_ColorMode.setText("RGB");
                }
                if (Parent.Camera.GetRecordFormat() == RecordFormat.MOV) {
                    Overview_Format.setText("Quicktime MOV");
                } else {
                    Overview_Format.setText("Image Sequence");
                }
                Overview_FreeSpace.setText(Utils.Round(Parent.Camera.GetFreeHDDSpace() / 1024.0f, 2) + " GB");
                float capacity = Parent.Camera.GetFreeHDDSpace() / ((float) (Parent.Camera.GetFrameSizeBytes()) / 1024.0f / 1024.0f * (float) (Parent.Camera.GetFPS()) * 3600.0f);
                Overview_RecCapacity.setText(Utils.Round(capacity, 2) + " h");
                Overview_CoringIndex.setText(Parent.Camera.GetCoringIndex() + "");

                repaint();

                try {
                    Thread.sleep(1 / UpdaterFPS * 1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public void Load() {
        startUpdater();

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
        if (Parent.Camera.GetColorMode() == ColorMode.JP4) {
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

        switch ((int) Utils.Round(Parent.Camera.GetFPS(), 1)) {
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
        // override if Frameskip or Secondsskip is set
        if ((Parent.Camera.GetFPSSkipFrames() != 0) || (Parent.Camera.GetFPSSkipSeconds() != 0)) {
            fps24.setChecked(false);
            fps25.setChecked(false);
            fps30.setChecked(false);
            fps50.setChecked(false);
            fps60.setChecked(false);
            fpscustom.setChecked(true);
        }

        JPEGQualityButton.setValue("" + Parent.Camera.GetJPEGQuality());
        CoringValueTextField.setText(String.valueOf(Parent.Camera.GetCoringIndex()));

        datarateMonitor1.startAnimator();
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
        SettingsOverview = new javax.swing.JPanel();
        datarateMonitor1 = new DatarateMonitor();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Overview_Resolution = new javax.swing.JLabel();
        Overview_FPS = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Overview_JPEGQ = new javax.swing.JLabel();
        Overview_ColorMode = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Overview_Format = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Overview_FreeSpace = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Overview_RecCapacity = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Overview_CoringIndex = new javax.swing.JLabel();
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
        jLabel6 = new javax.swing.JLabel();
        JPEG_Plus = new EButton();
        JPEGQualityButton = new EButton();
        JPEG_Minus = new EButton();
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
        TerminateButton = new EButton();
        NavigationPanel = new javax.swing.JPanel();
        SettingsMenu1Button10 = new EButton();
        SettingsMenu2Button10 = new EButton();
        GuidesMenuButton10 = new EButton();
        SettingsMenu3Button10 = new EButton();
        PhotoSettingsMenu = new EButton();
        SettingsCancelButton = new EButton();
        Experimental = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        CoringValueTextField = new javax.swing.JTextField();
        CoringSet = new EButton();
        CoringType = new EButton();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        SettingsOverview.setBackground(new java.awt.Color(0, 0, 0));
        SettingsOverview.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(44, 44, 44), 1, true));
        SettingsOverview.setForeground(new java.awt.Color(255, 255, 255));

        datarateMonitor1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout datarateMonitor1Layout = new javax.swing.GroupLayout(datarateMonitor1);
        datarateMonitor1.setLayout(datarateMonitor1Layout);
        datarateMonitor1Layout.setHorizontalGroup(
            datarateMonitor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
        );
        datarateMonitor1Layout.setVerticalGroup(
            datarateMonitor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Settings Overview");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Resolution:");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("FPS:");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_Resolution.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_Resolution.setForeground(new java.awt.Color(255, 255, 255));
        Overview_Resolution.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_Resolution.setText("loading");
        Overview_Resolution.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_FPS.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_FPS.setForeground(new java.awt.Color(255, 255, 255));
        Overview_FPS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_FPS.setText("loading");
        Overview_FPS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("JPEG Quality:");
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Color Mode:");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Format:");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_JPEGQ.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_JPEGQ.setForeground(new java.awt.Color(255, 255, 255));
        Overview_JPEGQ.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_JPEGQ.setText("loading");
        Overview_JPEGQ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_ColorMode.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_ColorMode.setForeground(new java.awt.Color(255, 255, 255));
        Overview_ColorMode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_ColorMode.setText("loading");
        Overview_ColorMode.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Datarate:");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_Format.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_Format.setForeground(new java.awt.Color(255, 255, 255));
        Overview_Format.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_Format.setText("loading");
        Overview_Format.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Free Space:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_FreeSpace.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_FreeSpace.setForeground(new java.awt.Color(255, 255, 255));
        Overview_FreeSpace.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_FreeSpace.setText("loading");
        Overview_FreeSpace.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel15.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Record Capacity:");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_RecCapacity.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_RecCapacity.setForeground(new java.awt.Color(255, 255, 255));
        Overview_RecCapacity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_RecCapacity.setText("loading");
        Overview_RecCapacity.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Coring Index");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Overview_CoringIndex.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        Overview_CoringIndex.setForeground(new java.awt.Color(255, 255, 255));
        Overview_CoringIndex.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Overview_CoringIndex.setText("loading");
        Overview_CoringIndex.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout SettingsOverviewLayout = new javax.swing.GroupLayout(SettingsOverview);
        SettingsOverview.setLayout(SettingsOverviewLayout);
        SettingsOverviewLayout.setHorizontalGroup(
            SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SettingsOverviewLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addGap(164, 164, 164))
                    .addGroup(SettingsOverviewLayout.createSequentialGroup()
                        .addComponent(datarateMonitor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(139, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SettingsOverviewLayout.createSequentialGroup()
                        .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Overview_Resolution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Overview_FPS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Overview_JPEGQ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Overview_ColorMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Overview_Format, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Overview_FreeSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Overview_RecCapacity, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                        .addContainerGap(25, Short.MAX_VALUE))))
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
            .addGroup(SettingsOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(159, Short.MAX_VALUE))
            .addGroup(SettingsOverviewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SettingsOverviewLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(Overview_CoringIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(SettingsOverviewLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        SettingsOverviewLayout.setVerticalGroup(
            SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsOverviewLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_Resolution, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_FPS, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_JPEGQ, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_ColorMode, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_Format, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_FreeSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_RecCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SettingsOverviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Overview_CoringIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datarateMonitor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        ResolutionPanel.setBackground(java.awt.Color.black);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
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
            .addGroup(ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(Custom, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SmallHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FullHD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Cimax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Amax, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ResolutionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Full, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        color_jp4.setText("JP4 RAW");
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
        JPEGQualityPanel.setBorder(null);

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("JPEG Quality");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        JPEG_Plus.setText("+");
        JPEG_Plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JPEG_PlusActionPerformed(evt);
            }
        });

        JPEGQualityButton.setAlignmentY(0.0F);
        JPEGQualityButton.setHorizontalAlignment(2);
        JPEGQualityButton.setHorizontalTextPosition(2);
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
            .addGroup(JPEGQualityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(JPEG_Plus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JPEGQualityButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(JPEG_Minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        JPEGQualityPanelLayout.setVerticalGroup(
            JPEGQualityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPEGQualityPanelLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPEG_Plus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JPEGQualityButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(JPEG_Minus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        WBPanel.setBackground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("White Balance");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        WBTungsten.setAlignmentY(0.0F);
        WBTungsten.setHorizontalTextPosition(0);
        WBTungsten.setIconTextGap(20);
        WBTungsten.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBTungsten.setText("Tungsten");
        WBTungsten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBTungstenActionPerformed(evt);
            }
        });

        WBDaylight.setAlignmentY(0.0F);
        WBDaylight.setHorizontalTextPosition(0);
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
        WBFlourescent.setHorizontalTextPosition(0);
        WBFlourescent.setIconTextGap(20);
        WBFlourescent.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBFlourescent.setText("Fluorescent");
        WBFlourescent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBFlourescentActionPerformed(evt);
            }
        });

        WBAuto.setAlignmentY(0.0F);
        WBAuto.setHorizontalTextPosition(0);
        WBAuto.setIconTextGap(20);
        WBAuto.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBAuto.setText("Auto");
        WBAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBAutoActionPerformed(evt);
            }
        });

        WBCloudy.setAlignmentY(0.0F);
        WBCloudy.setHorizontalTextPosition(0);
        WBCloudy.setIconTextGap(20);
        WBCloudy.setMargin(new java.awt.Insets(0, 5, 0, 0));
        WBCloudy.setText("Cloudy");
        WBCloudy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WBCloudyActionPerformed(evt);
            }
        });

        WBCustom.setAlignmentY(0.0F);
        WBCustom.setHorizontalTextPosition(0);
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

        TerminateButton.setText("Quit ElphelVision");
        TerminateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TerminateButtonActionPerformed(evt);
            }
        });

        NavigationPanel.setBackground(java.awt.Color.black);

        SettingsMenu1Button10.setChecked(true);
        SettingsMenu1Button10.setText("Settings Tab 1");
        SettingsMenu1Button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu1Button10ActionPerformed(evt);
            }
        });

        SettingsMenu2Button10.setText("Settings Tab 2");
        SettingsMenu2Button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu2Button10ActionPerformed(evt);
            }
        });

        GuidesMenuButton10.setText("Guides");
        GuidesMenuButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuidesMenuButton10ActionPerformed(evt);
            }
        });

        SettingsMenu3Button10.setText("Settings Tab 3");
        SettingsMenu3Button10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu3Button10ActionPerformed(evt);
            }
        });

        PhotoSettingsMenu.setText("Photo Settings");
        PhotoSettingsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhotoSettingsMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationPanelLayout = new javax.swing.GroupLayout(NavigationPanel);
        NavigationPanel.setLayout(NavigationPanelLayout);
        NavigationPanelLayout.setHorizontalGroup(
            NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanelLayout.createSequentialGroup()
                .addComponent(SettingsMenu1Button10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu2Button10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsMenu3Button10, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GuidesMenuButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PhotoSettingsMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        NavigationPanelLayout.setVerticalGroup(
            NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsMenu1Button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu2Button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsMenu3Button10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(GuidesMenuButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(PhotoSettingsMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        SettingsCancelButton.setText("Close");
        SettingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCancelButtonActionPerformed(evt);
            }
        });

        Experimental.setBackground(new java.awt.Color(0, 0, 0));
        Experimental.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(44, 44, 44), 1, true));
        Experimental.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("DejaVu Sans", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Experimental");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel17.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Coring");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        CoringSet.setText("Set");
        CoringSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoringSetActionPerformed(evt);
            }
        });

        CoringType.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        CoringType.setText("type");
        CoringType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CoringTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ExperimentalLayout = new javax.swing.GroupLayout(Experimental);
        Experimental.setLayout(ExperimentalLayout);
        ExperimentalLayout.setHorizontalGroup(
            ExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExperimentalLayout.createSequentialGroup()
                .addGroup(ExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(ExperimentalLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CoringValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CoringType, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CoringSet, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        ExperimentalLayout.setVerticalGroup(
            ExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExperimentalLayout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ExperimentalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CoringValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CoringType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CoringSet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(NavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(ResolutionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(JPEGQualityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ColorModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(RecordFormatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(WBPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Experimental, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TerminateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SettingsOverview, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RecordFormatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createSequentialGroup()
                            .addComponent(TerminateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(SettingsOverview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(JPEGQualityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ColorModePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createSequentialGroup()
                            .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(FPSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ResolutionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                            .addComponent(Experimental, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(WBPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void FullHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullHDActionPerformed
        FullHD.setChecked(true);

        Cimax.setChecked(false);
        Amax.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        Parent.Camera.SetPreset(CameraPreset.FULLHD);
    }//GEN-LAST:event_FullHDActionPerformed

    private void color_jp4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_jp4ActionPerformed
        color_jp4.setChecked(true);
        color_rbg.setChecked(false);

        Parent.Camera.SetColorMode(ColorMode.JP4);
    }//GEN-LAST:event_color_jp4ActionPerformed

    private void CimaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CimaxActionPerformed
        Cimax.setChecked(true);

        Amax.setChecked(false);
        FullHD.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        Parent.Camera.SetPreset(CameraPreset.CIMAX);
    }//GEN-LAST:event_CimaxActionPerformed

    private void AmaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AmaxActionPerformed
        Amax.setChecked(true);

        Cimax.setChecked(false);
        FullHD.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        Parent.Camera.SetPreset(CameraPreset.AMAX);
    }//GEN-LAST:event_AmaxActionPerformed

    private void SmallHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SmallHDActionPerformed
        SmallHD.setChecked(true);
        Amax.setChecked(false);
        Cimax.setChecked(false);
        FullHD.setChecked(false);
        Custom.setChecked(false);
        Full.setChecked(false);

        Parent.Camera.SetPreset(CameraPreset.SMALLHD);
    }//GEN-LAST:event_SmallHDActionPerformed

    private void color_rbgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_rbgActionPerformed
        color_jp4.setChecked(false);
        color_rbg.setChecked(true);

        Parent.Camera.SetColorMode(ColorMode.RGB);
    }//GEN-LAST:event_color_rbgActionPerformed

    private void fps30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps30ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(false);
        fps30.setChecked(true);
        fps50.setChecked(false);
        fps60.setChecked(false);
        fpscustom.setChecked(false);

        Parent.Camera.SetFPS(30.0f);
    }//GEN-LAST:event_fps30ActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent.MaincardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.StartVideoPlayer();
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

        Parent.Camera.SetFPS(25.0f);
    }//GEN-LAST:event_fps25ActionPerformed

    private void fps24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps24ActionPerformed
        fps24.setChecked(true);

        fps25.setChecked(false);
        fps30.setChecked(false);
        fps50.setChecked(false);
        fps60.setChecked(false);
        fpscustom.setChecked(false);

        Parent.Camera.SetFPS(24.0f);
    }//GEN-LAST:event_fps24ActionPerformed

    private void fps50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps50ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(false);
        fps30.setChecked(false);
        fps50.setChecked(true);
        fps60.setChecked(false);
        fpscustom.setChecked(false);

        Parent.Camera.SetFPS(50.0f);
    }//GEN-LAST:event_fps50ActionPerformed

    private void fps60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fps60ActionPerformed
        fps24.setChecked(false);
        fps25.setChecked(false);
        fps30.setChecked(false);
        fps50.setChecked(false);
        fps60.setChecked(true);
        fpscustom.setChecked(false);

        Parent.Camera.SetFPS(60.0f);
    }//GEN-LAST:event_fps60ActionPerformed

    private void fpscustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpscustomActionPerformed
        Parent.FPSSettingsCardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "CustomFPSCard");
    }//GEN-LAST:event_fpscustomActionPerformed

    private void WBTungstenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBTungstenActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(true);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);

        Parent.Camera.SetWhiteBalance(WhiteBalance.TUNGSTEN);
    }//GEN-LAST:event_WBTungstenActionPerformed

    private void WBDaylightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBDaylightActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(true);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);

        Parent.Camera.SetWhiteBalance(WhiteBalance.DAYLIGHT);
    }//GEN-LAST:event_WBDaylightActionPerformed

    private void WBFlourescentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBFlourescentActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(true);
        WBCustom.setChecked(false);

        Parent.Camera.SetWhiteBalance(WhiteBalance.FLOURESCENT);
    }//GEN-LAST:event_WBFlourescentActionPerformed

    private void WBAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBAutoActionPerformed
        WBAuto.setChecked(true);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(false);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);

        Parent.Camera.SetWhiteBalance(WhiteBalance.AUTO);
    }//GEN-LAST:event_WBAutoActionPerformed

    private void WBCloudyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBCloudyActionPerformed
        WBAuto.setChecked(false);
        WBDaylight.setChecked(false);
        WBTungsten.setChecked(false);
        WBCloudy.setChecked(true);
        WBFlourescent.setChecked(false);
        WBCustom.setChecked(false);

        Parent.Camera.SetWhiteBalance(WhiteBalance.CLOUDY);
    }//GEN-LAST:event_WBCloudyActionPerformed

    private void WBCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBCustomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WBCustomActionPerformed

    private void FormatJPEGsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormatJPEGsActionPerformed
        FormatQuicktime.setChecked(false);
        FormatJPEGs.setChecked(true);

        Parent.Camera.SetRecordFormat(RecordFormat.JPEG);
    }//GEN-LAST:event_FormatJPEGsActionPerformed

    private void FormatQuicktimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FormatQuicktimeActionPerformed
        FormatQuicktime.setChecked(true);
        FormatJPEGs.setChecked(false);

        Parent.Camera.SetRecordFormat(RecordFormat.MOV);
    }//GEN-LAST:event_FormatQuicktimeActionPerformed

    private void FullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FullActionPerformed
        Full.setChecked(true);

        FullHD.setChecked(false);
        Cimax.setChecked(false);
        Amax.setChecked(false);
        SmallHD.setChecked(false);
        Custom.setChecked(false);

        Parent.Camera.SetPreset(CameraPreset.FULL);
    }//GEN-LAST:event_FullActionPerformed

    private void TerminateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TerminateButtonActionPerformed
        if (JOptionPane.showConfirmDialog(new JFrame(), "Do you want to quit ElphelVision?", "Quit ElphelVision?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_TerminateButtonActionPerformed

    private void SettingsMenu1Button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu1Button10ActionPerformed
}//GEN-LAST:event_SettingsMenu1Button10ActionPerformed

    private void SettingsMenu2Button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu2Button10ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings2Card");
        Parent.Settings2CardLayout.Load();
}//GEN-LAST:event_SettingsMenu2Button10ActionPerformed

    private void GuidesMenuButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuidesMenuButton10ActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "GuidesCard");
        Parent.GuidesPanel.Load();
}//GEN-LAST:event_GuidesMenuButton10ActionPerformed

    private void SettingsMenu3Button10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu3Button10ActionPerformed
        Parent.Settings3CardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings3Card");
        Parent.StopVideoPlayer();
}//GEN-LAST:event_SettingsMenu3Button10ActionPerformed

    private void JPEG_MinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPEG_MinusActionPerformed
        Parent.Camera.SetJPEGQuality(Parent.Camera.GetJPEGQuality() - 1);
        JPEGQualityButton.setValue("" + Parent.Camera.GetJPEGQuality());
}//GEN-LAST:event_JPEG_MinusActionPerformed

    private void JPEGQualityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPEGQualityButtonActionPerformed
}//GEN-LAST:event_JPEGQualityButtonActionPerformed

    private void JPEG_PlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JPEG_PlusActionPerformed
        Parent.Camera.SetJPEGQuality(Parent.Camera.GetJPEGQuality() + 1);
        JPEGQualityButton.setValue("" + Parent.Camera.GetJPEGQuality());
}//GEN-LAST:event_JPEG_PlusActionPerformed

    private void PhotoSettingsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhotoSettingsMenuActionPerformed
        Parent.PhotoSettingsCardLayout.Load();
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "PhotoSettings");
    }//GEN-LAST:event_PhotoSettingsMenuActionPerformed

    private void CoringSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoringSetActionPerformed
        Parent.Camera.SetCoringIndex(Integer.parseInt(CoringValueTextField.getText()));
    }//GEN-LAST:event_CoringSetActionPerformed

    private void CoringTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CoringTypeActionPerformed
        Parent.NumberPanelInteger.Load("Coring", Integer.parseInt(CoringValueTextField.getText()), CoringValueTextField, "Settings1Card");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelInteger");
}//GEN-LAST:event_CoringTypeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EButton Amax;
    private EButton Cimax;
    private javax.swing.JPanel ColorModePanel;
    private EButton CoringSet;
    private EButton CoringType;
    private javax.swing.JTextField CoringValueTextField;
    private EButton Custom;
    private javax.swing.JPanel Experimental;
    private javax.swing.JPanel FPSPanel;
    private EButton FormatJPEGs;
    private EButton FormatQuicktime;
    private EButton Full;
    private EButton FullHD;
    private EButton GuidesMenuButton10;
    private EButton JPEGQualityButton;
    private javax.swing.JPanel JPEGQualityPanel;
    private EButton JPEG_Minus;
    private EButton JPEG_Plus;
    private javax.swing.JPanel NavigationPanel;
    private javax.swing.JLabel Overview_ColorMode;
    private javax.swing.JLabel Overview_CoringIndex;
    private javax.swing.JLabel Overview_FPS;
    private javax.swing.JLabel Overview_Format;
    private javax.swing.JLabel Overview_FreeSpace;
    private javax.swing.JLabel Overview_JPEGQ;
    private javax.swing.JLabel Overview_RecCapacity;
    private javax.swing.JLabel Overview_Resolution;
    private EButton PhotoSettingsMenu;
    private javax.swing.JPanel RecordFormatPanel;
    private javax.swing.JPanel ResolutionPanel;
    private EButton SettingsCancelButton;
    private EButton SettingsMenu1Button10;
    private EButton SettingsMenu2Button10;
    private EButton SettingsMenu3Button10;
    private javax.swing.JPanel SettingsOverview;
    private EButton SmallHD;
    private EButton TerminateButton;
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
    private DatarateMonitor datarateMonitor1;
    private EButton fps24;
    private EButton fps25;
    private EButton fps30;
    private EButton fps50;
    private EButton fps60;
    private EButton fpscustom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
