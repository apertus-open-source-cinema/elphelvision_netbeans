/*! Copyright (C) 2009 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *! Description: Main GUI layout class of the Elphel Vision viewfinder software for Elphel cameras.
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
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MainLayoutVLC extends JPanel {

    ElphelVision Parent;
    CameraParameter EditingParameter = CameraParameter.EXPOSURE;
    private GuidesOverlay Guidesoverlay = null;

    public MainLayoutVLC(ElphelVision parent) {
        Parent = parent;

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                    bg.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    ShutterPanel.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    InfoPanel.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    ParameterPanel.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    QuickPanel.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    GainPanel.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    ScaleLabel.setForeground(Parent.Settings.GetTextColor());
                    ColorModeLabel.setForeground(Parent.Settings.GetTextColor());
                    NoticeArea.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    InfoTextPane.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    InfoTextPane.setForeground(Parent.Settings.GetTextColor());
                    DatarateMonitor.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    DatarateMonitor.setForeground(Parent.Settings.GetTextColor());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        histogram.SetParent(Parent);
        DatarateMonitor.SetParent(Parent);

        if (Parent.NoCameraParameter) {

            StyledDocument doc = (StyledDocument) GetInfoTextPane().getDocument();
            javax.swing.text.Style StyleNormal = doc.addStyle("NormalText", null);
            StyleConstants.setForeground(StyleNormal, Color.white);
            StyleConstants.setBold(StyleNormal, true);

            MutableAttributeSet standard = new SimpleAttributeSet();
            StyleConstants.setAlignment(standard, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, 0, standard, true);

            String CameraInfo = "Running with --no-camera Parameter - Most live content is now in Demo mode.";
            try {
                doc.insertString(doc.getLength(), CameraInfo, StyleNormal);
            } catch (BadLocationException ex) {
                Logger.getLogger(MainLayoutGST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Load() {

        //AWTUtilitiesWrapper.setWindowOpaque(guides, false);
        //guides.repaint();
        synchronized (this) {
            if (Guidesoverlay == null) {
                Guidesoverlay = new GuidesOverlay(Parent.GetTranslucencyCapableGC(), Parent);
                AWTUtilitiesWrapper.setWindowOpaque(Guidesoverlay, false);
            }
            Guidesoverlay.setBounds(vlcoverlay.getBounds());
            Guidesoverlay.setVisible(true);
            Guidesoverlay.SetOptions(Parent.Camera.GetGuides());
        }
        Guidesoverlay.SetVisibility(true); // DEBUG

        Parent.VLCPlayer.SetCanvas(vlcoverlay);
        if (Parent.Settings.isVideoStreamEnabled()) {
            Parent.StartVideoPlayer();
        }

        ExposureButton.setChecked(true);
        ExposureButton.setValue(Parent.Camera.GetExposure());

        UpdateGainButtons(Parent.Camera.GetGainIndex());

        if (Parent.Camera.GetAllowSlowShutter()) {
            slowshutter.setChecked(true);
        } else {
            slowshutter.setChecked(false);
        }

        DatarateMonitor.startAnimator();
        audioMonitor1.startAnimator();

        if (Parent.Camera.GetIP().length == 1) {
            LiveVideoButton.setVisible(false);
        }
    }

    public void UpdateOverlayPosition() {
        if (Parent.Settings.GetVideoPlayer() == streamVideoPlayer.VLC) {
            Guidesoverlay.setLocation(vlcoverlay.getLocationOnScreen());
        }
    }

    public javax.swing.JTextPane GetInfoTextPane() {
        return this.InfoTextPane;
    }

    public javax.swing.JTextPane GetNoticeTextPane() {
        return this.NoticeArea;
    }

    public void RedrawHistogram() {
        histogram.repaint();
    }

    public void AddNoticeMessage(String Message) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");

        StyledDocument doc = null;
        doc = (StyledDocument) NoticeArea.getDocument();
        String text = sdf.format(cal.getTime()) + " : " + Message;
        NoticeArea.setText("");
        try {
            doc.insertString(doc.getLength(), text, doc.getStyle("RedNotice"));
        } catch (BadLocationException ex) {
            Logger.getLogger(ElphelVision.class.getName()).log(Level.SEVERE, null, ex);
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
        SliderPanel = new javax.swing.JPanel();
        ShutterPanel = new javax.swing.JPanel();
        incvalue2 = new EButton(Parent);
        slowshutter = new EButton(Parent);
        decvalue3 = new EButton(Parent);
        GainPanel = new javax.swing.JPanel();
        twelvedb = new EButton(Parent);
        incvalue1 = new EButton(Parent);
        decvalue1 = new EButton(Parent);
        zerodb = new EButton(Parent);
        threedb = new EButton(Parent);
        sixdb = new EButton(Parent);
        ninedb = new EButton(Parent);
        ParameterPanel = new javax.swing.JPanel();
        ExposureButton = new EButton(Parent);
        GainButton = new EButton(Parent);
        SettingsButton = new EButton(Parent);
        histogram = new Histogram();
        CaptureStill = new EButton(Parent);
        RecordButton = new EButton(Parent);
        PlaybackButton = new EButton(Parent);
        AudioRec = new EButton(Parent);
        audioMonitor1 = new AudioMonitor(Parent);
        eButton7 = new EButton(Parent);
        InfoPanel = new javax.swing.JPanel();
        InfoTextPane = new javax.swing.JTextPane();
        NoticeArea = new javax.swing.JTextPane();
        InfoArea_Record = new javax.swing.JTextPane();
        InfoArea_Resolution = new javax.swing.JTextPane();
        Image = new javax.swing.JLabel();
        Image1 = new javax.swing.JLabel();
        InfoArea_FPS = new javax.swing.JTextPane();
        Image2 = new javax.swing.JLabel();
        InfoArea_WB = new javax.swing.JTextPane();
        InfoArea_Quality = new javax.swing.JTextPane();
        Image3 = new javax.swing.JLabel();
        Image4 = new javax.swing.JLabel();
        InfoArea_HDD = new javax.swing.JTextPane();
        VideoFrame = new javax.swing.JPanel();
        vlcoverlay = new java.awt.Canvas();
        QuickPanel = new javax.swing.JPanel();
        eButton1 = new EButton(Parent);
        eButton3 = new EButton(Parent);
        eButton4 = new EButton(Parent);
        ScaleLabel = new javax.swing.JLabel();
        ColorModeLabel = new javax.swing.JLabel();
        eButton6 = new EButton(Parent);
        eButton8 = new EButton(Parent);
        eButton9 = new EButton(Parent);
        DatarateMonitor = new DatarateMonitor();
        LiveVideoButton = new EButton(Parent);

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(5, 5, 5));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SliderPanel.setBackground(new java.awt.Color(0, 0, 0));
        SliderPanel.setLayout(new java.awt.CardLayout());

        ShutterPanel.setBackground(new java.awt.Color(0, 0, 0));
        ShutterPanel.setPreferredSize(new java.awt.Dimension(50, 480));
        ShutterPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        incvalue2.setText("+");
        incvalue2.setAlignmentY(0.0F);
        incvalue2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incvalue2ActionPerformed(evt);
            }
        });
        ShutterPanel.add(incvalue2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        slowshutter.setText("slow\\nshutter");
        slowshutter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slowshutterActionPerformed(evt);
            }
        });
        ShutterPanel.add(slowshutter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 50, 50));

        decvalue3.setText("‒");
        decvalue3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decvalue3ActionPerformed(evt);
            }
        });
        ShutterPanel.add(decvalue3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 50, 50));

        SliderPanel.add(ShutterPanel, "ShutterPanel");

        GainPanel.setBackground(new java.awt.Color(0, 0, 0));
        GainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        twelvedb.setText("+12dB");
        twelvedb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                twelvedbActionPerformed(evt);
            }
        });
        GainPanel.add(twelvedb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 50, 47));

        incvalue1.setText("+");
        incvalue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incvalue1ActionPerformed(evt);
            }
        });
        GainPanel.add(incvalue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        decvalue1.setText("‒");
        decvalue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decvalue1ActionPerformed(evt);
            }
        });
        GainPanel.add(decvalue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 50, 50));

        zerodb.setText("0dB");
        zerodb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zerodbActionPerformed(evt);
            }
        });
        GainPanel.add(zerodb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 50, 47));

        threedb.setText("+3dB");
        threedb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                threedbActionPerformed(evt);
            }
        });
        GainPanel.add(threedb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 50, 47));

        sixdb.setText("+6dB");
        sixdb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sixdbActionPerformed(evt);
            }
        });
        GainPanel.add(sixdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 50, 47));

        ninedb.setText("+9dB");
        ninedb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ninedbActionPerformed(evt);
            }
        });
        GainPanel.add(ninedb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 50, 47));

        SliderPanel.add(GainPanel, "GainPanel");

        bg.add(SliderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 480));

        ParameterPanel.setBackground(new java.awt.Color(0, 0, 0));
        ParameterPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ExposureButton.setText("Shutter");
        ExposureButton.setAlignmentY(0.0F);
        ExposureButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ExposureButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ExposureButton.setIconTextGap(0);
        ExposureButton.setMargin(new java.awt.Insets(0, 5, 0, 0));
        ExposureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExposureButtonActionPerformed(evt);
            }
        });
        ParameterPanel.add(ExposureButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 0, -1, -1));

        GainButton.setText("Gain");
        GainButton.setAlignmentY(0.0F);
        GainButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GainButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        GainButton.setIconTextGap(20);
        GainButton.setMargin(new java.awt.Insets(0, 5, 0, 0));
        GainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GainButtonActionPerformed(evt);
            }
        });
        ParameterPanel.add(GainButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 0, -1, -1));

        SettingsButton.setBackground(new java.awt.Color(255, 255, 255));
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/settings.png"))); // NOI18N
        SettingsButton.setToolTipText("Settings");
        SettingsButton.setAlignmentY(0.0F);
        SettingsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SettingsButton.setIconTextGap(0);
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonActionPerformed(evt);
            }
        });
        ParameterPanel.add(SettingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 52));

        histogram.setBackground(new java.awt.Color(0, 0, 0));
        histogram.setPreferredSize(new java.awt.Dimension(256, 50));
        histogram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                histogramMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout histogramLayout = new javax.swing.GroupLayout(histogram);
        histogram.setLayout(histogramLayout);
        histogramLayout.setHorizontalGroup(
            histogramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );
        histogramLayout.setVerticalGroup(
            histogramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        ParameterPanel.add(histogram, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 0, -1, 52));

        CaptureStill.setForeground(new java.awt.Color(255, 0, 0));
        CaptureStill.setText("Still");
        CaptureStill.setAlignmentY(0.0F);
        CaptureStill.setDoubleBuffered(true);
        CaptureStill.setFont(CaptureStill.getFont());
        CaptureStill.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        CaptureStill.setIconTextGap(0);
        CaptureStill.setPreferredSize(new java.awt.Dimension(10, 50));
        CaptureStill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaptureStillActionPerformed(evt);
            }
        });
        ParameterPanel.add(CaptureStill, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 1, 52, -1));

        RecordButton.setForeground(new java.awt.Color(255, 0, 0));
        RecordButton.setText("Record");
        RecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecordButtonActionPerformed(evt);
            }
        });
        ParameterPanel.add(RecordButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 0, -1, 52));

        PlaybackButton.setText("Playback");
        PlaybackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaybackButtonActionPerformed(evt);
            }
        });
        ParameterPanel.add(PlaybackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 0, -1, -1));

        AudioRec.setText("Audio Rec");
        AudioRec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AudioRecActionPerformed(evt);
            }
        });
        ParameterPanel.add(AudioRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 0, 70, -1));

        audioMonitor1.setBackground(new java.awt.Color(0, 0, 0));
        audioMonitor1.setForeground(new java.awt.Color(166, 166, 166));
        audioMonitor1.setPreferredSize(new java.awt.Dimension(27, 60));

        javax.swing.GroupLayout audioMonitor1Layout = new javax.swing.GroupLayout(audioMonitor1);
        audioMonitor1.setLayout(audioMonitor1Layout);
        audioMonitor1Layout.setHorizontalGroup(
            audioMonitor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        audioMonitor1Layout.setVerticalGroup(
            audioMonitor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        ParameterPanel.add(audioMonitor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 0, 18, 50));

        eButton7.setText("test");
        eButton7.setToolTipText("RGB 24bit color mode");
        eButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton7ActionPerformed(evt);
            }
        });
        ParameterPanel.add(eButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, 66, -1));

        bg.add(ParameterPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 535, 1010, 60));

        InfoPanel.setBackground(new java.awt.Color(0, 0, 0));
        InfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        InfoTextPane.setBackground(new java.awt.Color(0, 0, 0));
        InfoTextPane.setForeground(new java.awt.Color(255, 255, 255));
        InfoTextPane.setDoubleBuffered(true);
        InfoTextPane.setFocusable(false);
        InfoPanel.add(InfoTextPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 200, 19));

        NoticeArea.setBackground(new java.awt.Color(0, 0, 0));
        NoticeArea.setForeground(new java.awt.Color(254, 54, 54));
        NoticeArea.setText("loading...");
        NoticeArea.setDoubleBuffered(true);
        NoticeArea.setFocusable(false);
        InfoPanel.add(NoticeArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 170, 19));

        InfoArea_Record.setBackground(new java.awt.Color(0, 0, 0));
        InfoArea_Record.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea_Record.setText("Record");
        InfoArea_Record.setDoubleBuffered(true);
        InfoArea_Record.setFocusable(false);
        InfoPanel.add(InfoArea_Record, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 130, 50));

        InfoArea_Resolution.setBackground(new java.awt.Color(0, 0, 0));
        InfoArea_Resolution.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea_Resolution.setText("Resolution");
        InfoArea_Resolution.setDoubleBuffered(true);
        InfoArea_Resolution.setFocusable(false);
        InfoPanel.add(InfoArea_Resolution, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 50));

        Image.setBackground(new java.awt.Color(0, 0, 0));
        Image.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image.setForeground(new java.awt.Color(255, 255, 255));
        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/divider01.png"))); // NOI18N
        InfoPanel.add(Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 0, -1, -1));

        Image1.setBackground(new java.awt.Color(0, 0, 0));
        Image1.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image1.setForeground(new java.awt.Color(255, 255, 255));
        Image1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/divider01.png"))); // NOI18N
        InfoPanel.add(Image1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, -1));

        InfoArea_FPS.setBackground(new java.awt.Color(0, 0, 0));
        InfoArea_FPS.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea_FPS.setText("FPS");
        InfoArea_FPS.setDoubleBuffered(true);
        InfoArea_FPS.setFocusable(false);
        InfoPanel.add(InfoArea_FPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 110, 50));

        Image2.setBackground(new java.awt.Color(0, 0, 0));
        Image2.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image2.setForeground(new java.awt.Color(255, 255, 255));
        Image2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/divider01.png"))); // NOI18N
        InfoPanel.add(Image2, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 0, -1, -1));

        InfoArea_WB.setBackground(new java.awt.Color(0, 0, 0));
        InfoArea_WB.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea_WB.setText("WB");
        InfoArea_WB.setDoubleBuffered(true);
        InfoArea_WB.setFocusable(false);
        InfoPanel.add(InfoArea_WB, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 120, 50));

        InfoArea_Quality.setBackground(new java.awt.Color(0, 0, 0));
        InfoArea_Quality.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea_Quality.setText("Quality");
        InfoArea_Quality.setDoubleBuffered(true);
        InfoArea_Quality.setFocusable(false);
        InfoPanel.add(InfoArea_Quality, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 0, 60, 50));

        Image3.setBackground(new java.awt.Color(0, 0, 0));
        Image3.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image3.setForeground(new java.awt.Color(255, 255, 255));
        Image3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/divider01.png"))); // NOI18N
        InfoPanel.add(Image3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, -1, -1));

        Image4.setBackground(new java.awt.Color(0, 0, 0));
        Image4.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image4.setForeground(new java.awt.Color(255, 255, 255));
        Image4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/divider01.png"))); // NOI18N
        InfoPanel.add(Image4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, -1));

        InfoArea_HDD.setBackground(new java.awt.Color(0, 0, 0));
        InfoArea_HDD.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea_HDD.setText("HDD");
        InfoArea_HDD.setDoubleBuffered(true);
        InfoArea_HDD.setFocusable(false);
        InfoPanel.add(InfoArea_HDD, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 90, 50));

        bg.add(InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 0, 850, -1));

        VideoFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)));
        VideoFrame.setDoubleBuffered(false);
        VideoFrame.setOpaque(false);
        VideoFrame.setLayout(new javax.swing.BoxLayout(VideoFrame, javax.swing.BoxLayout.LINE_AXIS));

        vlcoverlay.setBackground(new java.awt.Color(0, 0, 0));
        vlcoverlay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        VideoFrame.add(vlcoverlay);

        bg.add(VideoFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 50, 850, 480));

        QuickPanel.setBackground(new java.awt.Color(0, 0, 0));

        eButton1.setText("2:1");
        eButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton1ActionPerformed(evt);
            }
        });

        eButton3.setText("fit");
        eButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton3ActionPerformed(evt);
            }
        });

        eButton4.setText("1:1");
        eButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton4ActionPerformed(evt);
            }
        });

        ScaleLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        ScaleLabel.setForeground(new java.awt.Color(255, 255, 255));
        ScaleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ScaleLabel.setText("Scaling");
        ScaleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        ColorModeLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        ColorModeLabel.setForeground(new java.awt.Color(255, 255, 255));
        ColorModeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ColorModeLabel.setText("Color-Mode");
        ColorModeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        eButton6.setText("RGB");
        eButton6.setToolTipText("RGB 24bit color mode");
        eButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton6ActionPerformed(evt);
            }
        });

        eButton8.setText("JP46 RAW");
        eButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton8ActionPerformed(evt);
            }
        });

        eButton9.setText("JP4 RAW");
        eButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton9ActionPerformed(evt);
            }
        });

        DatarateMonitor.setBackground(new java.awt.Color(0, 0, 0));
        DatarateMonitor.setPreferredSize(new java.awt.Dimension(90, 40));

        javax.swing.GroupLayout DatarateMonitorLayout = new javax.swing.GroupLayout(DatarateMonitor);
        DatarateMonitor.setLayout(DatarateMonitorLayout);
        DatarateMonitorLayout.setHorizontalGroup(
            DatarateMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        DatarateMonitorLayout.setVerticalGroup(
            DatarateMonitorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        LiveVideoButton.setText("Camera 1");
        LiveVideoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LiveVideoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QuickPanelLayout = new javax.swing.GroupLayout(QuickPanel);
        QuickPanel.setLayout(QuickPanelLayout);
        QuickPanelLayout.setHorizontalGroup(
            QuickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eButton4, 0, 0, Short.MAX_VALUE)
            .addComponent(eButton3, 0, 0, Short.MAX_VALUE)
            .addComponent(ScaleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
            .addComponent(ColorModeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
            .addComponent(eButton6, 0, 0, Short.MAX_VALUE)
            .addComponent(eButton1, 0, 0, Short.MAX_VALUE)
            .addComponent(eButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
            .addComponent(eButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
            .addComponent(DatarateMonitor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(LiveVideoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );
        QuickPanelLayout.setVerticalGroup(
            QuickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuickPanelLayout.createSequentialGroup()
                .addComponent(ScaleLabel)
                .addGap(5, 5, 5)
                .addComponent(eButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DatarateMonitor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(ColorModeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(LiveVideoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bg.add(QuickPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void RecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordButtonActionPerformed
        if (Parent.Camera.GetIP().length > 1) {
            // Multiple Cameras
            CamogmState check = Parent.Camera.GetCamogmState();
            if (check == CamogmState.STOPPED) {
                float RecordDelay = Parent.Camera.GetMultiCameraRecordingStartDelay();
                double CurrentTime = Parent.Camera.GetCameraTime(0);
                String CurTime = String.format("%3f", (CurrentTime / 10000));
                final String StartTime = String.format("%3f", (CurrentTime + (RecordDelay * 10000)) / 10000);
                //Parent.WriteLogtoConsole("Current time = " + CurTime);

                Parent.Camera.StartRecording(StartTime);
                Parent.Camera.ArmRecording();

                RecordButton.setText("Stop");
                RecordButton.setChecked(true);
                if (Parent.Camera.GetAllowCaptureStillWhileRecording()) {
                    CaptureStill.setEnabled(true);
                } else {
                    CaptureStill.setEnabled(false);
                }
            } else if (check == CamogmState.RECORDING) {
                Parent.Camera.StopRecording();

                RecordButton.setText("Record");
                RecordButton.setChecked(false);

                CaptureStill.setEnabled(true);
            }
        } else {
            // Single Camera
            CamogmState check = Parent.Camera.GetCamogmState();
            if (check == CamogmState.STOPPED) {
                Parent.Camera.StartRecording();

                RecordButton.setText("Stop");
                RecordButton.setChecked(true);

                if (Parent.Camera.GetAllowCaptureStillWhileRecording()) {
                    CaptureStill.setEnabled(true);
                } else {
                    CaptureStill.setEnabled(false);
                }
            } else if (check == CamogmState.RECORDING) {
                Parent.Camera.StopRecording();

                RecordButton.setText("Record");
                RecordButton.setChecked(false);

                CaptureStill.setEnabled(true);
            }
        }
    }//GEN-LAST:event_RecordButtonActionPerformed

    private void ExposureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExposureButtonActionPerformed
        //ParameterName.setText("EV");
        //EditingParameter = CameraParameter.EXPOSURE;
        CardLayout cl = (CardLayout) (SliderPanel.getLayout());
        cl.show(SliderPanel, "ShutterPanel");
        ExposureButton.setChecked(true);
        GainButton.setChecked(false);
    }//GEN-LAST:event_ExposureButtonActionPerformed

    private void GainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GainButtonActionPerformed
        //ParameterName.setText("Gain");
        //EditingParameter = CameraParameter.GAIN;
        CardLayout cl = (CardLayout) (SliderPanel.getLayout());
        cl.show(SliderPanel, "GainPanel");
        ExposureButton.setChecked(false);
        GainButton.setChecked(true);
    }//GEN-LAST:event_GainButtonActionPerformed

    private void SettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.StopVideoPlayer();
        Guidesoverlay.SetVisibility(false);
        Parent.Settings1CardLayout.Load();
    }//GEN-LAST:event_SettingsButtonActionPerformed

    private void CaptureStillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaptureStillActionPerformed
        Parent.StopVideoPlayer();
        String Command = "";
        String Message = "";
        if (Parent.Camera.GetPhotoColorMode() == ColorMode.JP4) {
            Command += "COLOR=5&EXTENSION=.jp4";
            Message += "JP4 RAW ";
        }
        if (Parent.Camera.GetPhotoColorMode() == ColorMode.RGB) {
            Command += "COLOR=1&EXTENSION=.jpg";
            Message += "RGB ";
        }
        if (Parent.Camera.GetPhotoresolution() == PhotoResolution.FULL) {
            Command += "&WOI_LEFT=0&WOI_TOP=0&WOI_WIDTH=9999&WOI_HEIGHT=9999";
            Message += "Full Resolution Image ";
        }
        if (Parent.Camera.GetPhotoresolution() == PhotoResolution.ASVIDEO) {
            Command += "";
            Message += "Video Resolution Image ";
        }
        Command += "&QUALITY=" + Parent.Camera.GetPhotoQuality();
        Message += "(Quality=" + Parent.Camera.GetPhotoQuality() + ") ";

        String ReturnMessage = Parent.Camera.CaptureStillImage(Command);
        Parent.WriteLogtoConsole(Message + ReturnMessage);
        Parent.StartVideoPlayer();

        // Parent.Utils.PlayAudio("capturestill.wav"); //TODO not working yet
    }//GEN-LAST:event_CaptureStillActionPerformed

    private void PlaybackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaybackButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "PlaybackCard");
        Parent.StopVideoPlayer();
        Guidesoverlay.SetVisibility(false);
        Parent.PlaybackCardLayout.Load();
    }//GEN-LAST:event_PlaybackButtonActionPerformed
    private void eButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton1ActionPerformed
        Parent.VLCPlayer.SetScale(2);
    }//GEN-LAST:event_eButton1ActionPerformed
    private void eButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton3ActionPerformed
        Parent.VLCPlayer.SetScale(0);
    }//GEN-LAST:event_eButton3ActionPerformed
    private void eButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton4ActionPerformed
        Parent.VLCPlayer.SetScale(1);
    }//GEN-LAST:event_eButton4ActionPerformed
    private void eButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton6ActionPerformed
        Parent.Camera.SetColorMode(ColorMode.RGB);
    }//GEN-LAST:event_eButton6ActionPerformed
    private void eButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton8ActionPerformed
        Parent.Camera.SetColorMode(ColorMode.JP46);
    }//GEN-LAST:event_eButton8ActionPerformed

    private void eButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton9ActionPerformed
        Parent.Camera.SetColorMode(ColorMode.JP4);
    }//GEN-LAST:event_eButton9ActionPerformed

    private void histogramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_histogramMouseClicked
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "HistogramSettings");
        Parent.StopVideoPlayer();
        Guidesoverlay.SetVisibility(false);
        Parent.HistogramSettingsCardLayout.Load();
    }//GEN-LAST:event_histogramMouseClicked

    private void twelvedbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_twelvedbActionPerformed
        Parent.Camera.SetGainIndex(0);
        UpdateGainButtons(0);
}//GEN-LAST:event_twelvedbActionPerformed

    private void UpdateGainButtons(int newgainindex) {
        switch (newgainindex) {
            case 4:
                zerodb.setChecked(true);
                threedb.setChecked(false);
                sixdb.setChecked(false);
                ninedb.setChecked(false);
                twelvedb.setChecked(false);
                break;
            case 3:
                zerodb.setChecked(false);
                threedb.setChecked(true);
                sixdb.setChecked(false);
                ninedb.setChecked(false);
                twelvedb.setChecked(false);
                break;
            case 2:
                zerodb.setChecked(false);
                threedb.setChecked(false);
                sixdb.setChecked(true);
                ninedb.setChecked(false);
                twelvedb.setChecked(false);
                break;
            case 1:
                zerodb.setChecked(false);
                threedb.setChecked(false);
                sixdb.setChecked(false);
                ninedb.setChecked(true);
                twelvedb.setChecked(false);
                break;
            case 0:
                zerodb.setChecked(false);
                threedb.setChecked(false);
                sixdb.setChecked(false);
                ninedb.setChecked(false);
                twelvedb.setChecked(true);
                break;
        }
        GainButton.setValue(Parent.Camera.GetGain(newgainindex));

    }
    private void incvalue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incvalue1ActionPerformed
        int oldgainindex = Parent.Camera.GetGainIndex();
        int newgainindex = oldgainindex - 1;
        newgainindex = Utils.MinMaxRange(newgainindex, 0, 4);
        Parent.WriteLogtoConsole("oldgainindex = " + oldgainindex);
        Parent.WriteLogtoConsole("newgainindex = " + newgainindex);
        Parent.Camera.SetGainIndex(newgainindex);
        UpdateGainButtons(newgainindex);
    }//GEN-LAST:event_incvalue1ActionPerformed

    private void decvalue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decvalue1ActionPerformed
        int oldgainindex = Parent.Camera.GetGainIndex();
        int newgainindex = oldgainindex + 1;
        newgainindex = Utils.MinMaxRange(newgainindex, 0, 4);
        Parent.WriteLogtoConsole("oldgainindex = " + oldgainindex);
        Parent.WriteLogtoConsole("newgainindex = " + newgainindex);
        Parent.Camera.SetGainIndex(newgainindex);
        UpdateGainButtons(newgainindex);
    }//GEN-LAST:event_decvalue1ActionPerformed

    private void zerodbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zerodbActionPerformed
        Parent.Camera.SetGainIndex(4);
        UpdateGainButtons(4);
    }//GEN-LAST:event_zerodbActionPerformed

    private void threedbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_threedbActionPerformed
        Parent.Camera.SetGainIndex(3);
        UpdateGainButtons(3);
    }//GEN-LAST:event_threedbActionPerformed

    private void sixdbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sixdbActionPerformed
        Parent.Camera.SetGainIndex(2);
        UpdateGainButtons(2);
    }//GEN-LAST:event_sixdbActionPerformed

    private void ninedbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ninedbActionPerformed
        Parent.Camera.SetGainIndex(1);
        UpdateGainButtons(1);
    }//GEN-LAST:event_ninedbActionPerformed

    private void incvalue2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incvalue2ActionPerformed
        float max_shutter = 1 / Parent.Camera.GetFPS();
        if ((max_shutter > (Parent.Camera.GetExposurefromIndex(Parent.Camera.GetExposureIndex() - 1)) || Parent.Camera.GetAllowSlowShutter())) {
            Parent.Camera.SetExposureIndex(Parent.Camera.GetExposureIndex() - 1);
            ExposureButton.setValue(Parent.Camera.GetExposure());
        }
    }//GEN-LAST:event_incvalue2ActionPerformed

    private void slowshutterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slowshutterActionPerformed
        // Toggle
        if (Parent.Camera.GetAllowSlowShutter()) {
            slowshutter.setChecked(false);
            Parent.Camera.SetAllowSlowShutter(false);
        } else {
            slowshutter.setChecked(true);
            Parent.Camera.SetAllowSlowShutter(true);
        }
    }//GEN-LAST:event_slowshutterActionPerformed

    private void decvalue3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decvalue3ActionPerformed
        float max_shutter = 1 / Parent.Camera.GetFPS();
        if ((max_shutter > (Parent.Camera.GetExposurefromIndex(Parent.Camera.GetExposureIndex() + 1)) || Parent.Camera.GetAllowSlowShutter())) {
            Parent.Camera.SetExposureIndex(Parent.Camera.GetExposureIndex() + 1);
            ExposureButton.setValue(Parent.Camera.GetExposure());
        }
    }//GEN-LAST:event_decvalue3ActionPerformed
    int VideoStreamloop = 1;
    private void LiveVideoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LiveVideoButtonActionPerformed
        // Loop through Video sources 
        // 1 - video from source 1 (default)
        // 2 - video from source 2 (stereo 3d mode)
        if (Parent.Camera.GetIP().length > 1) { // stereo 3d mode
            if (VideoStreamloop == 1) {
                Parent.StopVideoPlayer();
                LiveVideoButton.setText("Camera 2");
                VideoStreamloop = 2;
                Parent.VLCPlayer.PlayVideoStream(1);
            } else if (VideoStreamloop == 2) {
                Parent.StopVideoPlayer();
                LiveVideoButton.setText("Camera 1");
                VideoStreamloop = 1;
                Parent.VLCPlayer.PlayVideoStream(0);
            }
        }
    }//GEN-LAST:event_LiveVideoButtonActionPerformed

private void AudioRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AudioRecActionPerformed
    if (AudioRec.isChecked()) {
        new Thread() {

            @Override
            public void run() {
                Parent.Utils.SoundRecorder.StopRecording();
            }
        }.start();
    } else {
        int AudioFileIndex = 0;
        String AudioFilename = "Audio" + String.format("%07d", AudioFileIndex) + ".wav";
        File AudioFile = new File(AudioFilename);
        while (AudioFile.exists()) {
            AudioFileIndex++;
            AudioFilename = "Audio" + String.format("%07d", AudioFileIndex) + ".wav";
            AudioFile = new File(AudioFilename);
        }
        Parent.Utils.SoundRecorder.SetFilename(AudioFilename);
        Parent.WriteLogtoConsole("Setting Audio Recording Filename: " + AudioFilename);
        Parent.Utils.SoundRecorder.StartRecording();
    }
    AudioRec.ToggleChecked();
}//GEN-LAST:event_AudioRecActionPerformed

private void eButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButton7ActionPerformed
    //String result = Parent.Camera.SetRecordDirectory(0, "test");
    File f1 = new File("no-video.jpg");
    Parent.VLCPlayer.PlayStillImage(f1.getAbsolutePath());
}//GEN-LAST:event_eButton7ActionPerformed

    public void EnableRecord(boolean val) {
        this.RecordButton.setEnabled(val);
    }

    public boolean GetRecordEnabled() {
        return this.RecordButton.isEnabled();
    }

    public void SetInfoAreaResolution(String newtext) {
        InfoArea_Resolution.setText(newtext);
    }

    public javax.swing.JTextPane GetInfoAreaResolution() {
        return this.InfoArea_Resolution;
    }

    public void SetInfoAreaFPS(String newtext) {
        InfoArea_Record.setText(newtext);
    }

    public javax.swing.JTextPane GetInfoAreaFPS() {
        return this.InfoArea_FPS;
    }

    public void SetInfoAreaWB(String newtext) {
        InfoArea_Record.setText(newtext);
    }

    public javax.swing.JTextPane GetInfoAreaWB() {
        return this.InfoArea_WB;
    }

    public void SetInfoAreaQuality(String newtext) {
        InfoArea_Quality.setText(newtext);
    }

    public javax.swing.JTextPane GetInfoAreaQuality() {
        return this.InfoArea_Quality;
    }

    public void SetInfoAreaHDD(String newtext) {
        InfoArea_HDD.setText(newtext);
    }

    public javax.swing.JTextPane GetInfoAreaHDD() {
        return this.InfoArea_HDD;
    }

    public void SetInfoAreaRecord(String newtext) {
        InfoArea_Record.setText(newtext);
    }

    public javax.swing.JTextPane GetInfoAreaRecord() {
        return this.InfoArea_Record;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EButton AudioRec;
    private EButton CaptureStill;
    private javax.swing.JLabel ColorModeLabel;
    private DatarateMonitor DatarateMonitor;
    private EButton ExposureButton;
    private EButton GainButton;
    private javax.swing.JPanel GainPanel;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel Image1;
    private javax.swing.JLabel Image2;
    private javax.swing.JLabel Image3;
    private javax.swing.JLabel Image4;
    private javax.swing.JTextPane InfoArea_FPS;
    private javax.swing.JTextPane InfoArea_HDD;
    private javax.swing.JTextPane InfoArea_Quality;
    private javax.swing.JTextPane InfoArea_Record;
    private javax.swing.JTextPane InfoArea_Resolution;
    private javax.swing.JTextPane InfoArea_WB;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JTextPane InfoTextPane;
    private EButton LiveVideoButton;
    private javax.swing.JTextPane NoticeArea;
    private javax.swing.JPanel ParameterPanel;
    private EButton PlaybackButton;
    private javax.swing.JPanel QuickPanel;
    private EButton RecordButton;
    private javax.swing.JLabel ScaleLabel;
    private EButton SettingsButton;
    private javax.swing.JPanel ShutterPanel;
    private javax.swing.JPanel SliderPanel;
    private javax.swing.JPanel VideoFrame;
    private org.gstreamer.swing.VideoComponent GstreamerVideoComponent;
    private AudioMonitor audioMonitor1;
    private javax.swing.JPanel bg;
    private EButton decvalue1;
    private EButton decvalue3;
    private EButton eButton1;
    private EButton eButton3;
    private EButton eButton4;
    private EButton eButton6;
    private EButton eButton7;
    private EButton eButton8;
    private EButton eButton9;
    private Histogram histogram;
    private EButton incvalue1;
    private EButton incvalue2;
    private EButton ninedb;
    private EButton sixdb;
    private EButton slowshutter;
    private EButton threedb;
    private EButton twelvedb;
    private java.awt.Canvas vlcoverlay;
    private EButton zerodb;
    // End of variables declaration//GEN-END:variables
}
