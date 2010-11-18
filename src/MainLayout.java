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
import javax.swing.JPanel;

public class MainLayout extends JPanel {

    ElphelVision Parent;
    CameraParameter EditingParameter = CameraParameter.EXPOSURE;
    //private GuidesOverlay_old Guidesoverlay = null;
    private GuidesOverlay Guidesoverlay = null;

    public MainLayout(ElphelVision parent) {
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
        histogram.SetParent(Parent);


    }

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
        Parent.Player.SetCanvas(vlcoverlay);

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

    /*
    @Override
    public void paint(Graphics g) {
    super.paint(g);

    String testtext = "test";
    VideoFrame.invalidate();
    Graphics graphics = VideoFrame.getGraphics();
    // clear the area before drawing anything new on the canvas
    graphics.setColor(Color.DARK_GRAY);
    graphics.fillRect(0, 0, 50, 100);

    int x = 50;
    int y = 50;

    Font font = new Font("Arial", Font.PLAIN, 18);
    graphics.setFont(font);

    // draw border by drawing it 4 times with offset in each direction
    graphics.setColor(Color.black);
    graphics.drawString(testtext, x + 1, y + 1);
    graphics.drawString(testtext, x + 1, y - 1);
    graphics.drawString(testtext, x - 1, y + 1);
    graphics.drawString(testtext, x - 1, y - 1);

    // draw the filling
    graphics.setColor(Color.white);
    graphics.drawString(testtext, x, y);
    }
     */
    /*public int getWinID() {
    int winid = 0;
    if (Parent.Settings.GetOS() == OStype.Windows) {
    try {
    final java.lang.Class<?> cl;
    cl = Class.forName("sun.awt.windows.WComponentPeer");
    java.lang.reflect.Field f = cl.getDeclaredField("hwnd");
    f.setAccessible(true);
    winid = (int) f.getLong(guides1.getPeer());
    //debugoutput.append("Video window ID: " + winid);
    } catch (ClassNotFoundException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
    } catch (IllegalArgumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (IllegalAccessException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (SecurityException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (NoSuchFieldException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }

    } else if (Parent.Settings.GetOS() == OStype.Linux) {
    try {
    final Class<?> cl = Class.forName("sun.awt.X11ComponentPeer");
    java.lang.reflect.Method m = cl.getMethod("getContentWindow", null);
    Object obj = m.invoke(vlcoverlay.getPeer());
    winid = (int) Long.parseLong(obj.toString());

    //debugoutput.append("Video window ID: " + winid);
    } catch (InvocationTargetException ex) {
    ex.printStackTrace();
    } catch (NoSuchMethodException ex) {
    ex.printStackTrace();
    } catch (ClassNotFoundException e1) {
    // TODO Auto-generated catch block
    e1.printStackTrace();
    } catch (IllegalArgumentException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (IllegalAccessException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    } catch (SecurityException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    }
    return winid;
    }*/
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
        vlcoverlay = new java.awt.Canvas();

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
        NoticeArea.setText("loading...");

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

        vlcoverlay.setBackground(new java.awt.Color(23, 23, 23));
        VideoFrame.add(vlcoverlay);

        bg.add(VideoFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 50, 760, 480));

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
                Parent.Camera.SetExposureIndex(Parent.Camera.GetExposureIndex() - 1);
                ExposureButton.setValue(Parent.Camera.GetExposure());
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
        Parent.Player.close();
        Guidesoverlay.SetVisibility(false);
        Parent.Settings1CardLayout.Load();
    }//GEN-LAST:event_SettingsButtonActionPerformed

    private void CaptureStillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaptureStillActionPerformed
        Parent.Player.close();
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
        Parent.Player.PlayVideoStream();

        Parent.Utils.PlayAudio("capturestill.wav");
    }//GEN-LAST:event_CaptureStillActionPerformed

    private void PlaybackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlaybackButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "PlaybackCard");
        Parent.Player.close();
        Guidesoverlay.SetVisibility(false);
        Parent.PlaybackCardLayout.Load();
    }//GEN-LAST:event_PlaybackButtonActionPerformed

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
    private EButton RecordButton;
    private EButton SettingsButton;
    private javax.swing.JPanel SliderPanel;
    private javax.swing.JPanel VideoFrame;
    private javax.swing.JPanel bg;
    private EButton decvalue;
    private Histogram histogram;
    private EButton incvalue;
    private java.awt.Canvas vlcoverlay;
    // End of variables declaration//GEN-END:variables
}
