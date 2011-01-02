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
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.soap.SOAPBinding.Style;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import org.gstreamer.Element;
import org.gstreamer.ElementFactory;
import org.gstreamer.Gst;
import org.gstreamer.Pipeline;
import org.gstreamer.State;
import org.gstreamer.swing.VideoComponent;

public class MainLayout extends JPanel {

    ElphelVision Parent;
    CameraParameter EditingParameter = CameraParameter.EXPOSURE;
    //private GuidesOverlay_old Guidesoverlay = null;
    private GuidesOverlay Guidesoverlay = null;
    private String[] args;

    public MainLayout(ElphelVision parent) {
        Parent = parent;

        if (Parent.Settings.GetVideoPlayer() == VideoPlayer.GSTREAMER) {
            args = new String[2];
            args[0] = "";
            args[1] = "";
            args = Gst.init("Swing Player", args);
        }

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        histogram.SetParent(Parent);

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
                Logger.getLogger(MainLayout.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private Pipeline pipe;

    public void Load() {

        //AWTUtilitiesWrapper.setWindowOpaque(guides, false);
        //guides.repaint();
        synchronized (this) {
            if (Guidesoverlay == null) {
                Guidesoverlay = new GuidesOverlay(Parent.GetTranslucencyCapableGC(), Parent);
                AWTUtilitiesWrapper.setWindowOpaque(Guidesoverlay, false);
            }
            //Guidesoverlay.setSize(vlcoverlay.getWidth(), vlcoverlay.getHeight());
            Guidesoverlay.setBounds(vlcoverlay.getBounds());
            Guidesoverlay.setVisible(true);
            Guidesoverlay.SetOptions(Parent.Camera.GetGuides());
        }
        Guidesoverlay.SetVisibility(true);

        if (Parent.Settings.GetVideoPlayer() == VideoPlayer.GSTREAMER) {

            Parent.GstreamerPlayer.SetVideocomponent(GstreamerVideoComponent);
            String rtspsource = "rtspsrc location=rtsp://" + Parent.Camera.GetIP() + ":554 latency=30 ! rtpjpegdepay ! jpegdec name=elphelstream";
            pipe = Pipeline.launch(rtspsource);

            GstreamerVideoComponent.setKeepAspect(true);
            Element videosink = GstreamerVideoComponent.getElement();
            //Element videosink = ElementFactory.make("xvimagesink", "sink");
            pipe.add(videosink);
            pipe.getElementByName("elphelstream").link(videosink);

            pipe.setState(State.PLAYING);
            Gst.main();
            pipe.setState(State.NULL);
        }
        if (Parent.Settings.GetVideoPlayer() == VideoPlayer.VLC) {
            Parent.VLCPlayer.SetCanvas(vlcoverlay);
            Parent.WriteLogtoConsole("Starting VLC Video Stream");
            Parent.StartVideoPlayer();
        }

        ExposureButton.setChecked(true);
        ParameterName.setText("EV");
        ExposureButton.setValue(Parent.Camera.GetExposure());
        GainButton.setValue(Parent.Camera.GetGain());


    }

    public void UpdateOverlayPosition() {
        Guidesoverlay.setLocation(vlcoverlay.getLocationOnScreen());
    }

    public javax.swing.JTextPane GetInfoTextPane() {
        return this.InfoTextPane;
    }

    public void setNoticeArea(String input) {
        this.NoticeArea.setText(input);
    }

    public void RedrawHistogram() {
        histogram.repaint();
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
        incvalue = new EButton();
        ParameterName = new javax.swing.JLabel();
        decvalue = new EButton();
        ParameterPanel = new javax.swing.JPanel();
        ExposureButton = new EButton();
        GainButton = new EButton();
        SettingsButton = new EButton();
        histogram = new Histogram();
        CaptureStill = new EButton();
        RecordButton = new EButton();
        PlaybackButton = new EButton();
        InfoPanel = new javax.swing.JPanel();
        InfoTextPane = new javax.swing.JTextPane();
        NoticeArea = new javax.swing.JLabel();
        VideoFrame = new javax.swing.JPanel();
        //GstreamerVideoComponent = new VideoComponent();
        vlcoverlay = new java.awt.Canvas();
        QuickPanel = new javax.swing.JPanel();
        eButton1 = new EButton();
        eButton3 = new EButton();
        eButton4 = new EButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        eButton6 = new EButton();
        eButton8 = new EButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(5, 5, 5));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SliderPanel.setBackground(new java.awt.Color(0, 0, 0));

        incvalue.setText("+");
        incvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incvalueActionPerformed(evt);
            }
        });

        ParameterName.setForeground(new java.awt.Color(254, 254, 254));
        ParameterName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParameterName.setText("...");

        decvalue.setMaximumSize(new java.awt.Dimension(23, 27));
        decvalue.setMinimumSize(new java.awt.Dimension(23, 27));
        decvalue.setText("-");
        decvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decvalueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SliderPanelLayout = new javax.swing.GroupLayout(SliderPanel);
        SliderPanel.setLayout(SliderPanelLayout);
        SliderPanelLayout.setHorizontalGroup(
            SliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(incvalue, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(decvalue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(ParameterName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );
        SliderPanelLayout.setVerticalGroup(
            SliderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SliderPanelLayout.createSequentialGroup()
                .addComponent(incvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(ParameterName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                .addComponent(decvalue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(SliderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        ParameterPanel.setBackground(new java.awt.Color(0, 0, 0));

        ExposureButton.setAlignmentY(0.0F);
        ExposureButton.setHorizontalAlignment(2);
        ExposureButton.setHorizontalTextPosition(2);
        ExposureButton.setIconTextGap(0);
        ExposureButton.setMargin(new java.awt.Insets(0, 5, 0, 0));
        ExposureButton.setText("Shutter");
        ExposureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExposureButtonActionPerformed(evt);
            }
        });

        GainButton.setAlignmentY(0.0F);
        GainButton.setHorizontalAlignment(2);
        GainButton.setHorizontalTextPosition(2);
        GainButton.setIconTextGap(20);
        GainButton.setMargin(new java.awt.Insets(0, 5, 0, 0));
        GainButton.setText("Gain");
        GainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GainButtonActionPerformed(evt);
            }
        });

        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/settings.png"))); // NOI18N
        SettingsButton.setAlignmentY(0.0F);
        SettingsButton.setBackground(new java.awt.Color(255, 255, 255));
        SettingsButton.setHorizontalTextPosition(0);
        SettingsButton.setIconTextGap(0);
        SettingsButton.setToolTipText("Settings");
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonActionPerformed(evt);
            }
        });

        histogram.setBackground(new java.awt.Color(0, 0, 0));
        histogram.setPreferredSize(new java.awt.Dimension(256, 50));

        javax.swing.GroupLayout histogramLayout = new javax.swing.GroupLayout(histogram);
        histogram.setLayout(histogramLayout);
        histogramLayout.setHorizontalGroup(
            histogramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );
        histogramLayout.setVerticalGroup(
            histogramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        CaptureStill.setAlignmentY(0.0F);
        CaptureStill.setClickFeedback(true);
        CaptureStill.setDoubleBuffered(true);
        CaptureStill.setFont(CaptureStill.getFont());
        CaptureStill.setForeground(new java.awt.Color(255, 0, 0));
        CaptureStill.setHorizontalTextPosition(0);
        CaptureStill.setIconTextGap(0);
        CaptureStill.setPreferredSize(new java.awt.Dimension(10, 50));
        CaptureStill.setText("Still");
        CaptureStill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaptureStillActionPerformed(evt);
            }
        });

        RecordButton.setForeground(new java.awt.Color(255, 0, 0));
        RecordButton.setText("Record");
        RecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RecordButtonActionPerformed(evt);
            }
        });

        PlaybackButton.setText("Playback");
        PlaybackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlaybackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ParameterPanelLayout = new javax.swing.GroupLayout(ParameterPanel);
        ParameterPanel.setLayout(ParameterPanelLayout);
        ParameterPanelLayout.setHorizontalGroup(
            ParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParameterPanelLayout.createSequentialGroup()
                .addComponent(SettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ExposureButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GainButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(PlaybackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(histogram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CaptureStill, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        ParameterPanelLayout.setVerticalGroup(
            ParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ParameterPanelLayout.createSequentialGroup()
                .addGroup(ParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ExposureButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(GainButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PlaybackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SettingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(histogram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CaptureStill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.add(ParameterPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 980, -1));

        InfoPanel.setBackground(new java.awt.Color(0, 0, 0));

        InfoTextPane.setBackground(new java.awt.Color(0, 0, 0));
        InfoTextPane.setForeground(new java.awt.Color(255, 255, 255));
        InfoTextPane.setDoubleBuffered(true);
        InfoTextPane.setFocusable(false);

        NoticeArea.setForeground(new java.awt.Color(255, 255, 255));
        NoticeArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(NoticeArea, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addComponent(InfoTextPane, javax.swing.GroupLayout.DEFAULT_SIZE, 927, Short.MAX_VALUE)
                .addContainerGap())
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addComponent(InfoTextPane, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NoticeArea, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        VideoFrame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(100, 100, 100)));
        VideoFrame.setDoubleBuffered(false);
        VideoFrame.setOpaque(false);
        VideoFrame.setLayout(new javax.swing.BoxLayout(VideoFrame, javax.swing.BoxLayout.LINE_AXIS));

        vlcoverlay.setBackground(new java.awt.Color(0, 0, 0));
        vlcoverlay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        VideoFrame.add(vlcoverlay);

        bg.add(VideoFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 50, 850, 480));
        //VideoFrame.add(GstreamerVideoComponent);
        //GstreamerVideoComponent.setPreferredSize(new Dimension(320, 240));

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

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Scaling");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Color-Mode");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        eButton6.setText("RGB");
        eButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton6ActionPerformed(evt);
            }
        });

        eButton8.setText("JP4 RAW");
        eButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout QuickPanelLayout = new javax.swing.GroupLayout(QuickPanel);
        QuickPanel.setLayout(QuickPanelLayout);
        QuickPanelLayout.setHorizontalGroup(
            QuickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eButton4, 0, 0, Short.MAX_VALUE)
            .addComponent(eButton3, 0, 0, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(eButton6, 0, 0, Short.MAX_VALUE)
            .addComponent(eButton1, 0, 0, Short.MAX_VALUE)
            .addComponent(eButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        QuickPanelLayout.setVerticalGroup(
            QuickPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuickPanelLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(eButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        bg.add(QuickPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 70, -1));

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

    private void decvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decvalueActionPerformed
        // decreaseing the value actually means lower shutter = higher exposure time
        switch (EditingParameter) {
            case EXPOSURE:
                Parent.Camera.SetExposureIndex(Parent.Camera.GetExposureIndex() + 1);
                ExposureButton.setValue(Parent.Camera.GetExposure());
                break;

            case GAIN:
                Parent.Camera.SetGainIndex(Parent.Camera.GetGainIndex() + 1);
                GainButton.setValue(Parent.Camera.GetGain());
                break;
        }
    }//GEN-LAST:event_decvalueActionPerformed

    private void RecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RecordButtonActionPerformed
        CamogmState check = Parent.Camera.GetCamogmState();
        if (check == CamogmState.STOPPED) {
            Parent.Camera.ExecuteCommand("RECORDSTART");
            RecordButton.setText("Stop");
            RecordButton.setChecked(true);

            if (Parent.Camera.GetAllowCaptureStillWhileRecording()) {
                CaptureStill.setEnabled(true);
            } else {
                CaptureStill.setEnabled(false);
            }
        } else if (check == CamogmState.RECORDING) {
            Parent.Camera.ExecuteCommand("RECORDSTOP");
            RecordButton.setText("Record");
            RecordButton.setChecked(false);

            CaptureStill.setEnabled(true);
        }
    }//GEN-LAST:event_RecordButtonActionPerformed

    private void incvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incvalueActionPerformed
        switch (EditingParameter) {
            case EXPOSURE:
                float max_shutter = 1 / Parent.Camera.GetFPS();
                if (max_shutter > (Parent.Camera.GetExposurefromIndex(Parent.Camera.GetExposureIndex() - 1))) {
                    Parent.Camera.SetExposureIndex(Parent.Camera.GetExposureIndex() - 1);
                    ExposureButton.setValue(Parent.Camera.GetExposure());
                }
                break;

            case GAIN:
                Parent.Camera.SetGainIndex(Parent.Camera.GetGainIndex() - 1);
                GainButton.setValue(Parent.Camera.GetGain());
                break;
        }
    }//GEN-LAST:event_incvalueActionPerformed

    private void ExposureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExposureButtonActionPerformed
        ParameterName.setText("EV");
        EditingParameter = CameraParameter.EXPOSURE;
        ExposureButton.setChecked(true);
        GainButton.setChecked(false);
    }//GEN-LAST:event_ExposureButtonActionPerformed

    private void GainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GainButtonActionPerformed
        ParameterName.setText("Gain");
        EditingParameter = CameraParameter.GAIN;
        ExposureButton.setChecked(false);
        GainButton.setChecked(true);
    }//GEN-LAST:event_GainButtonActionPerformed

    private void SettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.VLCPlayer.close();
        Guidesoverlay.SetVisibility(false);
        Parent.Settings1CardLayout.Load();
    }//GEN-LAST:event_SettingsButtonActionPerformed

    private void CaptureStillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaptureStillActionPerformed
        Parent.VLCPlayer.close();
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
        NoticeArea.setText(Message + ReturnMessage);
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
        Parent.Camera.SetColorMode(ColorMode.JP4);
    }//GEN-LAST:event_eButton8ActionPerformed

    public void EnableRecord(boolean val) {
        this.RecordButton.setEnabled(val);
    }

    public boolean GetRecordEnabled() {
        return this.RecordButton.isEnabled();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private EButton CaptureStill;
    private EButton ExposureButton;
    private EButton GainButton;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JTextPane InfoTextPane;
    private javax.swing.JLabel NoticeArea;
    private javax.swing.JLabel ParameterName;
    private javax.swing.JPanel ParameterPanel;
    private EButton PlaybackButton;
    private javax.swing.JPanel QuickPanel;
    private EButton RecordButton;
    private EButton SettingsButton;
    private javax.swing.JPanel SliderPanel;
    private javax.swing.JPanel VideoFrame;
    private org.gstreamer.swing.VideoComponent GstreamerVideoComponent;
    private javax.swing.JPanel bg;
    private EButton decvalue;
    private EButton eButton1;
    private EButton eButton3;
    private EButton eButton4;
    private EButton eButton6;
    private EButton eButton8;
    private Histogram histogram;
    private EButton incvalue;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private java.awt.Canvas vlcoverlay;
    // End of variables declaration//GEN-END:variables
}
