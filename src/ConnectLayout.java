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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectLayout extends javax.swing.JPanel {

    ElphelVision Parent;

    public ConnectLayout(ElphelVision parent) {
        Parent = parent;

        //Title.setText("Elphel Vision Alpha V" + Parent.GetAppVersion());

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
        ConnectButton = new EButton();
        CameraIP = new javax.swing.JTextField();
        InfoArea1 = new javax.swing.JLabel();
        VLCButton = new EButton();
        GstreamerButton = new EButton();
        jLabel1 = new javax.swing.JLabel();
        ExitBUtton = new EButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setForeground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        jPanel1.setBackground(new java.awt.Color(1, 1, 1));

        Title.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("Elphel Vision Alpha  V0.4");

        Image.setBackground(new java.awt.Color(0, 0, 0));
        Image.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Image.setForeground(new java.awt.Color(255, 255, 255));
        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/apertus.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Image)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ConnectPanel.setBackground(new java.awt.Color(0, 0, 0));

        ConnectButton.setClickFeedback(true);
        ConnectButton.setText("Connect");
        ConnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConnectButtonActionPerformed(evt);
            }
        });

        CameraIP.setText("192.168.0.9");

        InfoArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        InfoArea1.setForeground(new java.awt.Color(255, 255, 255));
        InfoArea1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        InfoArea1.setText("Camera IP: ");

        VLCButton.setChecked(true);
        VLCButton.setText("VLC");
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
        jLabel1.setText("<- dont use Gstreamer yet");

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
        );
        ConnectPanelLayout.setVerticalGroup(
            ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConnectPanelLayout.createSequentialGroup()
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InfoArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CameraIP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(ConnectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VLCButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GstreamerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)))
        );

        ExitBUtton.setForeground(new java.awt.Color(217, 2, 2));
        ExitBUtton.setText("Exit");
        ExitBUtton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBUttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap(337, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(345, 345, 345))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(ExitBUtton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addComponent(ConnectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(273, 273, 273))))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(ConnectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(ExitBUtton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void ConnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConnectButtonActionPerformed
        if (!Parent.GetNoCameraParameter()) {
            Parent.WriteLogtoConsole("Trying to connect to: " + CameraIP.getText());
        }

        if (!Parent.GetNoCameraParameter()) {
            try {
                Parent.Camera.SetIP(CameraIP.getText());
                Parent.Camera.InitCameraConnection();

                if (Parent.Camera.PingCamera()) {
                    Parent.WriteLogtoConsole("Connection to: " + CameraIP.getText() + " established");
                    while (!Parent.Camera.InitCameraServices()) {
                        Thread.sleep(400); // Evil I know
                    }
                    Parent.PostConnect();
                    Parent.WriteLogtoConsole("Checking Camera connected HDD");
                    if (Parent.Camera.CheckHDD()) {
                        Parent.WriteLogtoConsole("HDD detected");
                    } else {
                        Parent.WriteWarningtoConsole("HDD detection failed");
                    }
                    Parent.WriteLogtoConsole("Loading Main Window");
                    Parent.MaincardLayout.Load();
                    CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
                    cl.show(Parent.CardManager, "MainCard");
                } else {
                    Parent.WriteErrortoConsole("ConnectButtonActionPerformed() Connecting to: " + CameraIP.getText() + " failed");
                }
            } catch (Exception e) {
                Parent.WriteErrortoConsole("ConnectButtonActionPerformed() Connecting failed: " + e.getMessage());
            }
        } else {
            Parent.WriteLogtoConsole("Loading Main Window");
            Parent.MaincardLayout.Load();
            CardLayout cl = (CardLayout) (Parent.CardManager.getLayout());
            cl.show(Parent.CardManager, "MainCard");
        }


    }//GEN-LAST:event_ConnectButtonActionPerformed

    private void ExitBUttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBUttonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitBUttonActionPerformed

    private void GstreamerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GstreamerButtonActionPerformed
        VLCButton.setChecked(false);
        GstreamerButton.setChecked(true);
        Parent.Settings.SetVideoPlayer(VideoPlayer.GSTREAMER);
    }//GEN-LAST:event_GstreamerButtonActionPerformed

    private void VLCButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VLCButtonActionPerformed
        VLCButton.setChecked(true);
        GstreamerButton.setChecked(false);
        Parent.Settings.SetVideoPlayer(VideoPlayer.VLC);
    }//GEN-LAST:event_VLCButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CameraIP;
    private EButton ConnectButton;
    private javax.swing.JPanel ConnectPanel;
    private EButton ExitBUtton;
    private EButton GstreamerButton;
    private javax.swing.JLabel Image;
    private javax.swing.JLabel InfoArea1;
    private javax.swing.JLabel Title;
    private EButton VLCButton;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
