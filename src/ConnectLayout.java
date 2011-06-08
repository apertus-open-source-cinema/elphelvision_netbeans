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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
//import javax.media.opengl.GLCapabilities;
//import javax.media.opengl.GLJPanel;

public class ConnectLayout extends javax.swing.JPanel {

    private ElphelVision Parent;
    // private Animator IntroAnimator;

    public ConnectLayout(ElphelVision parent) {

        Parent = parent;

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable()                                              {

                public void run() {
                    initComponents();

                    Title.setText("Elphel Vision Alpha");
                    if (Parent.Settings.GetVideoPlayer() == streamVideoPlayer.VLC) {
                        VLCButton.setChecked(true);
                        GstreamerButton.setChecked(false);
                    }
                    if (Parent.Settings.GetVideoPlayer() == streamVideoPlayer.GSTREAMER) {
                        VLCButton.setChecked(false);
                        GstreamerButton.setChecked(true);
                    }

                    //Title.setText("Elphel Vision Alpha V" + Parent.GetAppVersion());
//                    AnimationPanel.addGLEventListener(new JoglIntroAnimation());
                    //                  IntroAnimator = new Animator(AnimationPanel);
                    //                IntroAnimator.start(); // JOGL is still troublesome so disabled for now
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        new Thread()                                 {

            public void run() {
                Parent.WriteLogtoConsole("Looking for autosave.config to read IP");
                File AutoSaveFile = new File("autosave.config");


                if (AutoSaveFile.exists()) {
                    try {
                        CameraIP.setText(Parent.Camera.ReadConfigFileIP("autosave.config"));
                        Parent.WriteLogtoConsole("autosave.config found - IP loaded");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ConnectLayout.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Parent.WriteWarningtoConsole("autosave.config not found: falling back to default.config");
                    try {
                        String IP = Parent.Camera.ReadConfigFileIP("default.config");
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
        jPanel1 = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        Image = new javax.swing.JLabel();
        ConnectPanel = new javax.swing.JPanel();
        ConnectButton = new EButton(Parent);
        CameraIP = new javax.swing.JTextField();
        InfoArea1 = new javax.swing.JLabel();
        VLCButton = new EButton(Parent);
        GstreamerButton = new EButton(Parent);
        jLabel1 = new javax.swing.JLabel();
        Stereo3DButton = new EButton(Parent);
        CameraIP2 = new javax.swing.JTextField();
        ExitButton = new EButton(Parent);

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setForeground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(1, 1, 1));

        Title.setFont(new java.awt.Font("Tahoma", 0, 14));
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Elphel Vision Alpha  V0.4");

        Image.setBackground(new java.awt.Color(0, 0, 0));
        Image.setFont(new java.awt.Font("Tahoma", 0, 14));
        Image.setForeground(new java.awt.Color(255, 255, 255));
        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/apertus.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 998, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addComponent(Image)
                .addContainerGap(353, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Image)
                .addGap(14, 14, 14)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, -1, 260));

        ConnectPanel.setBackground(new java.awt.Color(0, 0, 0));

        ConnectButton.setText("Connect");
        ConnectButton.setClickFeedback(true);
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        CameraIP.setText("192.168.0.9");

        InfoArea1.setFont(new java.awt.Font("Tahoma", 0, 14));
        InfoArea1.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InfoArea1.setText("Camera IP: ");

        VLCButton.setText("VLC");
        VLCButton.setChecked(true);
        VLCButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VLCButtonActionPerformed(evt);
            }
        });

        GstreamerButton.setText("Gstreamer");
        GstreamerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GstreamerButtonActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Gstreamer is experimental");

        Stereo3DButton.setBackground(new java.awt.Color(254, 254, 254));
        Stereo3DButton.setText("Stereo 3D");
        Stereo3DButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Stereo3DButtonActionPerformed(evt);
            }
        });

        CameraIP2.setText("192.168.0.9");
        CameraIP2.setEnabled(false);

        javax.swing.GroupLayout ConnectPanelLayout = new javax.swing.GroupLayout(ConnectPanel);
        ConnectPanel.setLayout(ConnectPanelLayout);
        ConnectPanelLayout.setHorizontalGroup(
            ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addComponent(InfoArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CameraIP, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ConnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addComponent(VLCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GstreamerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addComponent(Stereo3DButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CameraIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConnectPanelLayout.setVerticalGroup(
            ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InfoArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CameraIP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Stereo3DButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CameraIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VLCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GstreamerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        bg.add(ConnectPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));

        ExitButton.setForeground(new java.awt.Color(217, 2, 2));
        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        bg.add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 540, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        ConnectButton.setText("Connecting");
        new Thread()                                 {

            public void run() {
                if (!Parent.GetNoCameraParameter()) {
                    /*if (CameraIP.getText().equals(CameraIP2.getText())) {
                        Parent.WriteWarningtoConsole("Trying to connect to Dual Camera Stereo 3D setup with single IP, assuming single camera setup: " + CameraIP.getText());
                        Stereo3DButton.setChecked(false);
                    }*/

                    if (Stereo3DButton.getChecked()) {
                        Parent.WriteLogtoConsole("Trying to connect to Dual Camera Stereo 3D setup: " + CameraIP.getText() + " and " + CameraIP2.getText());
                    } else {
                        Parent.WriteLogtoConsole("Trying to connect to: " + CameraIP.getText());
                    }
                }

                if (!Parent.GetNoCameraParameter()) {
                    try {
                        if (Stereo3DButton.getChecked()) {
                            Parent.Camera.SetIP(new String[]{CameraIP.getText(), CameraIP2.getText()});
                        } else {
                            Parent.Camera.SetIP(new String[]{CameraIP.getText()});
                        }
                        Parent.Camera.InitCameraConnection();
                        for (int i = 0; i < Parent.Camera.GetIP().length; i++) {
                            if (Parent.Camera.PingCamera(i)) {
                                Parent.WriteLogtoConsole("Connection to: " + Parent.Camera.GetIP()[i] + " established");
                                while (!Parent.Camera.InitCameraServices()) {
                                    Thread.sleep(50); // since we are in our own thread its safe to do this
                                }
                                Parent.PostConnect();
                                Parent.WriteLogtoConsole("Checking Camera("+Parent.Camera.GetIP()[i]+") connected HDD");
                                if (Parent.Camera.CheckHDD()) {
                                    Parent.WriteLogtoConsole("HDD detected");
                                } else {
                                    Parent.WriteWarningtoConsole("HDD detection failed");
                                }
                                if (VLCButton.getChecked()) {
                                    Parent.WriteLogtoConsole("Loading Main Window with VLC Player");
                                    Parent.MaincardLayoutVLC.Load();
                                    CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
                                    cl.show(Parent.CardManager, "MainCardVLC");
                                }
                                if (GstreamerButton.getChecked()) {
                                    Parent.WriteLogtoConsole("Loading Main Window with Gstreamer");
                                    Parent.MaincardLayoutGST.Load();
                                    CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
                                    cl.show(Parent.CardManager, "MainCardGST");
                                }
                            } else {
                                Parent.WriteErrortoConsole("ConnectButtonActionPerformed() Connecting to: " + CameraIP.getText() + " failed");
                            }
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
    }//GEN-LAST:event_ConnectButtonActionPerformed
    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void GstreamerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GstreamerButtonActionPerformed
        VLCButton.setChecked(false);
        GstreamerButton.setChecked(true);
        Parent.Settings.SetVideoPlayer(streamVideoPlayer.GSTREAMER);
    }//GEN-LAST:event_GstreamerButtonActionPerformed
    private void VLCButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VLCButtonActionPerformed
        VLCButton.setChecked(true);
        GstreamerButton.setChecked(false);
        Parent.Settings.SetVideoPlayer(streamVideoPlayer.VLC);
    }//GEN-LAST:event_VLCButtonActionPerformed

    private void Stereo3DButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Stereo3DButtonActionPerformed
        Stereo3DButton.ToggleChecked();

        if (Stereo3DButton.getChecked()) {
            CameraIP2.setEnabled(true);
        } else {
            CameraIP2.setEnabled(false);
        }
    }//GEN-LAST:event_Stereo3DButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CameraIP;
    private javax.swing.JTextField CameraIP2;
    private EButton ConnectButton;
    private javax.swing.JPanel ConnectPanel;
    private EButton ExitButton;
    private EButton GstreamerButton;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel InfoArea1;
    private EButton Stereo3DButton;
    private javax.swing.JLabel Title;
    private EButton VLCButton;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
