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

//import com.sun.opengl.util.Animator;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
//import javax.swing.SwingUtilities;
//import javax.media.opengl.GLCapabilities;
//import javax.media.opengl.GLJPanel;

public class ConnectLayout extends javax.swing.JPanel {

    private ElphelVision Parent;
    // private Animator IntroAnimator;

    public ConnectLayout(ElphelVision parent) {

        Parent = parent;

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                    bg.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    TitleBackground.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    ConnectPanel.setBackground(Parent.Settings.GetPanelBackgroundColor());
                    Title.setForeground(Parent.Settings.GetTextColor());
                    Camera1IPLabel.setForeground(Parent.Settings.GetTextColor());

                    Title.setText("Elphel Vision Alpha " + Parent.GetAppVersion());
                    if (Parent.Settings.GetVideoPlayer() == streamVideoPlayer.VLC) {
                        VLCButton.setChecked(true);
                        GstreamerButton.setChecked(false);
                    }
                    if (Parent.Settings.GetVideoPlayer() == streamVideoPlayer.GSTREAMER) {
                        VLCButton.setChecked(false);
                        GstreamerButton.setChecked(true);
                    }
                    
                    //WBDaylight.setIconImage(getClass().getClassLoader().getResource("/media/wb_daylight.png"));

                    //Title.setText("Elphel Vision Alpha V" + Parent.GetAppVersion());
//                    AnimationPanel.addGLEventListener(new JoglIntroAnimation());
                    //                  IntroAnimator = new Animator(AnimationPanel);
                    //                IntroAnimator.start(); // JOGL is still troublesome so disabled for now

                    //BalloonTip test = new BalloonTip(jButton1, "test");
                }
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new Thread() {

            public void run() {
                Parent.WriteLogtoConsole("Looking for autosave.config to read IP");
                File AutoSaveFile = new File("autosave.config");

                if (AutoSaveFile.exists()) {
                    try {
                        ArrayList IPs = Parent.Camera.ReadConfigFileIP("autosave.config");
                        CameraIP.setText((String) IPs.get(0));
                        Parent.WriteLogtoConsole("autosave.config found - IP loaded");
                        if (IPs.size() > 1) {
                            Stereo3DButton.setChecked(true);
                            CameraIP2.setEnabled(true);
                            CameraIP2.setText((String) IPs.get(1));
                            Parent.WriteLogtoConsole("second IP found in autosave.config - enabling Stereo3D");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ConnectLayout.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Parent.WriteWarningtoConsole("autosave.config not found: falling back to default.config");
                    try {
                        String IP = (String) Parent.Camera.ReadConfigFileIP("default.config").get(0);
                        if (IP != null) {
                            CameraIP.setText(IP);
                            Parent.WriteLogtoConsole("default.config found: read IP: " + IP);
                        } else {
                            Parent.WriteWarningtoConsole("default.config not found: using 192.168.0.9 as default IP");
                        }
                    } catch (FileNotFoundException ex) {
                        Parent.WriteWarningtoConsole("default.config not found: using 192.168.0.9 as default IP");
                        Logger.getLogger(ConnectLayout.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }.start();

        // Tests to deal with keyboard shortcuts
        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                Parent.WriteLogtoConsole("keypressed: " + actionEvent.paramString());
            }
        };
        KeyStroke up = KeyStroke.getKeyStroke('c');
        this.registerKeyboardAction(actionListener, "c childfocus", up, JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        this.registerKeyboardAction(actionListener, "c focus", up, JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.requestFocus();
    }

    /*    @Override
    public void setVisible(boolean show) {
    if (!show) {
    IntroAnimator.stop();
    }
    super.setVisible(show);
    if (show) {
    IntroAnimator.start();
    }
    }
    
    private GLCapabilities createGLCapabilites() {
    
    GLCapabilities capabilities = new GLCapabilities();
    capabilities.setHardwareAccelerated(true);
    
    // try to enable 2x anti aliasing - should be supported on most hardware
    capabilities.setNumSamples(2);
    capabilities.setSampleBuffers(true);
    
    return capabilities;
    }
     */
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        TitleBackground = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        Image = new javax.swing.JLabel();
        ConnectPanel = new javax.swing.JPanel();
        CameraIP = new javax.swing.JTextField();
        Camera1IPLabel = new javax.swing.JLabel();
        VLCButton = new EButton(Parent);
        GstreamerButton = new EButton(Parent);
        jLabel1 = new javax.swing.JLabel();
        Stereo3DButton = new EButton(Parent);
        CameraIP2 = new javax.swing.JTextField();
        IP_type = new EButton(Parent);
        IP2_type = new EButton(Parent);
        ConnectButton = new EButton(Parent);
        ExitButton = new EButton(Parent);

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1024, 600));

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setForeground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitleBackground.setBackground(new java.awt.Color(1, 1, 1));

        Title.setFont(new java.awt.Font("Tahoma", 0, 14));
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Elphel Vision Alpha  V");

        Image.setBackground(new java.awt.Color(0, 0, 0));
        Image.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image.setForeground(new java.awt.Color(255, 255, 255));
        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/apertus.png"))); // NOI18N

        javax.swing.GroupLayout TitleBackgroundLayout = new javax.swing.GroupLayout(TitleBackground);
        TitleBackground.setLayout(TitleBackgroundLayout);
        TitleBackgroundLayout.setHorizontalGroup(
            TitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleBackgroundLayout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addComponent(Image)
                .addContainerGap(353, Short.MAX_VALUE))
            .addComponent(Title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
        );
        TitleBackgroundLayout.setVerticalGroup(
            TitleBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitleBackgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Image)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.add(TitleBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, 260));

        ConnectPanel.setBackground(new java.awt.Color(0, 0, 0));

        CameraIP.setText("192.168.0.9");
        CameraIP.setMargin(new java.awt.Insets(0, 4, 0, 0));

        Camera1IPLabel.setFont(new java.awt.Font("Tahoma", 0, 14));
        Camera1IPLabel.setForeground(new java.awt.Color(255, 255, 255));
        Camera1IPLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Camera1IPLabel.setText("Camera IP: ");

        VLCButton.setChecked(true);
        VLCButton.setText("VLC");
        VLCButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VLCButtonMouseClicked(evt);
            }
        });

        GstreamerButton.setText("Gstreamer");
        GstreamerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GstreamerButtonMouseClicked(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Gstreamer is experimental");

        Stereo3DButton.setBackground(new java.awt.Color(254, 254, 254));
        Stereo3DButton.setText("Stereo 3D");
        Stereo3DButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Stereo3DButtonMouseClicked(evt);
            }
        });

        CameraIP2.setText("192.168.0.9");
        CameraIP2.setEnabled(false);
        CameraIP2.setMargin(new java.awt.Insets(0, 4, 0, 0));

        IP_type.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        IP_type.setText("type");
        IP_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IP_typeMouseClicked(evt);
            }
        });

        IP2_type.setEnabled(false);
        IP2_type.setFont(new java.awt.Font("DejaVu Sans", 0, 10));
        IP2_type.setText("type");
        IP2_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IP2_typeMouseClicked(evt);
            }
        });

        ConnectButton.setText("Connect");
        ConnectButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ConnectButtonMouseClicked(evt);
            }
        });
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConnectPanelLayout = new javax.swing.GroupLayout(ConnectPanel);
        ConnectPanel.setLayout(ConnectPanelLayout);
        ConnectPanelLayout.setHorizontalGroup(
            ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConnectPanelLayout.createSequentialGroup()
                        .addComponent(VLCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GstreamerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(ConnectPanelLayout.createSequentialGroup()
                        .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Stereo3DButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Camera1IPLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ConnectPanelLayout.createSequentialGroup()
                                .addComponent(CameraIP, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IP_type, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ConnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ConnectPanelLayout.createSequentialGroup()
                                .addComponent(CameraIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(IP2_type, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        ConnectPanelLayout.setVerticalGroup(
            ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Camera1IPLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CameraIP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IP_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Stereo3DButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CameraIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IP2_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VLCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GstreamerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        bg.add(ConnectPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        ExitButton.setForeground(new java.awt.Color(217, 2, 2));
        ExitButton.setText("Exit");
        ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtonMouseClicked(evt);
            }
        });
        bg.add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 540, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Stereo3DButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Stereo3DButtonMouseClicked
        Stereo3DButton.ToggleChecked();

        if (Stereo3DButton.isChecked()) {
            CameraIP2.setEnabled(true);
            IP2_type.setEnabled(true);
        } else {
            CameraIP2.setEnabled(false);
            IP2_type.setEnabled(false);
        }
    }//GEN-LAST:event_Stereo3DButtonMouseClicked

    private void IP_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IP_typeMouseClicked
        Parent.NumberPanelIP.Load("Camera IP", CameraIP.getText(), CameraIP, "ConnectCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelIP");
    }//GEN-LAST:event_IP_typeMouseClicked
    boolean Fake3D = false; // for debugging 3D settings with a single camera
    private void ConnectButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ConnectButtonMouseClicked
        ConnectButton.setText("Connecting");
        new Thread() {

            public void run() {
                if (!Parent.GetNoCameraParameter()) {
                    if (!Fake3D) {
                        if (CameraIP.getText().equals(CameraIP2.getText())) {
                            Parent.WriteWarningtoConsole("Trying to connect to Dual Camera Stereo 3D setup with single IP, assuming single camera setup: " + CameraIP.getText());
                            Stereo3DButton.setChecked(false);
                        }
                    }

                    if (Stereo3DButton.isChecked()) {
                        Parent.WriteLogtoConsole("Trying to connect to Dual Camera Stereo 3D setup: " + CameraIP.getText() + " and " + CameraIP2.getText());
                    } else {
                        Parent.WriteLogtoConsole("Trying to connect to: " + CameraIP.getText());
                    }
                }

                if (!Parent.GetNoCameraParameter()) {
                    try {
                        if (Stereo3DButton.isChecked()) {
                            Parent.Camera.SetIP(new String[]{CameraIP.getText(), CameraIP2.getText()});
                        } else {
                            Parent.Camera.SetIP(new String[]{CameraIP.getText()});
                        }
                        Parent.Camera.InitCameraConnection();
                        for (int i = 0; i < Parent.Camera.GetIP().length; i++) {
                            if (Parent.Camera.PingCamera(i)) {
                                Parent.WriteLogtoConsole("Connection to: " + Parent.Camera.GetIP()[i] + " established");
                                while (!Parent.Camera.InitCameraServices(i)) {
                                    Thread.sleep(500); // since we are in our own thread its safe to do this
                                }

                                Parent.WriteLogtoConsole("Checking Camera(" + Parent.Camera.GetIP()[i] + ") connected HDD");
                                if (Parent.Camera.CheckHDD()) {
                                    Parent.WriteLogtoConsole("HDD detected");
                                } else {
                                    Parent.WriteWarningtoConsole("HDD detection failed");
                                }
                            } else {
                                Parent.WriteErrortoConsole("ConnectButtonActionPerformed() Connecting to: " + CameraIP.getText() + " failed");
                            }
                        }
                        Parent.PostConnect();
                        if (Stereo3DButton.isChecked() && !Fake3D) {
                            Parent.Camera.InitStereo3DSettings();
                        }
                        if (VLCButton.isChecked()) {
                            Parent.WriteLogtoConsole("Loading Main Window with VLC Player");
                            Parent.MaincardLayoutVLC.Load();
                            CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
                            cl.show(Parent.CardManager, "MainCardVLC");
                        }
                        if (GstreamerButton.isChecked()) {
                            Parent.WriteLogtoConsole("Loading Main Window with Gstreamer");
                            Parent.MaincardLayoutGST.Load();
                            CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
                            cl.show(Parent.CardManager, "MainCardGST");
                        }
                    } catch (Exception e) {
                        Parent.WriteErrortoConsole("ConnectButtonActionPerformed() Connecting failed: " + e.getMessage());
                    }
                } else {
                    // no camera connected
                    Parent.WriteLogtoConsole("Loading Main Window without connected camera");
                    Parent.MaincardLayoutVLC.Load();
                    CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
                    cl.show(Parent.CardManager, "MainCardVLC");
                }
            }
        }.start();
    }//GEN-LAST:event_ConnectButtonMouseClicked

    private void GstreamerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GstreamerButtonMouseClicked
        VLCButton.setChecked(false);
        GstreamerButton.setChecked(true);
        Parent.Settings.SetVideoPlayer(streamVideoPlayer.GSTREAMER);
    }//GEN-LAST:event_GstreamerButtonMouseClicked

    private void VLCButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VLCButtonMouseClicked
        VLCButton.setChecked(true);
        GstreamerButton.setChecked(false);
        Parent.Settings.SetVideoPlayer(streamVideoPlayer.VLC);
    }//GEN-LAST:event_VLCButtonMouseClicked

    private void IP2_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IP2_typeMouseClicked
        Parent.NumberPanelIP.Load("Camera 2 IP", CameraIP2.getText(), CameraIP2, "ConnectCard");
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "NumberpanelIP");
    }//GEN-LAST:event_IP2_typeMouseClicked

    private void ExitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitButtonMouseClicked

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConnectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Camera1IPLabel;
    private javax.swing.JTextField CameraIP;
    private javax.swing.JTextField CameraIP2;
    private EButton ConnectButton;
    private javax.swing.JPanel ConnectPanel;
    private EButton ExitButton;
    private EButton GstreamerButton;
    private EButton IP2_type;
    private EButton IP_type;
    private javax.swing.JLabel Image;
    private EButton Stereo3DButton;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel TitleBackground;
    private EButton VLCButton;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
