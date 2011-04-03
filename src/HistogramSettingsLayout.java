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

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistogramSettingsLayout extends javax.swing.JPanel implements Runnable {

    ElphelVision Parent = null;
    Thread ValuesUpdater;
    int UpdaterFPS = 4;

    public HistogramSettingsLayout(ElphelVision parent) {
        Parent = parent;

        try {
            java.awt.EventQueue.invokeAndWait(new Runnable()                    {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        histogram.SetParent(Parent);
    }

    private void startAnimator() {
        ValuesUpdater = new Thread(this);
        ValuesUpdater.start();
    }

    private void StopAnimator() {
        ValuesUpdater.interrupt();
    }

    public void run() {
        while (Thread.currentThread() == ValuesUpdater) {

            UpdateValues();

            try {
                Thread.sleep(1 / UpdaterFPS * 1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void UpdateValues() {
        MinLabelRed.setText("Red Min.: " + (int) ((float) histogram.GetMin(Color.red) / 256.0f * 100.0f) + "%");
        MinLabelGreen.setText("Green Min: " + (int) ((float) histogram.GetMin(Color.green) / 256.0f * 100.0f) + "%");
        MinLabelBlue.setText("Blue Min: " + (int) ((float) histogram.GetMin(Color.blue) / 256.0f * 100.0f) + "%");
        MaxLabelRed.setText("Red Max.: " + (int) ((float) histogram.GetMax(Color.red) / 256.0f * 100.0f) + "%");
        MaxLabelGreen.setText("Green Max: " + (int) ((float) histogram.GetMax(Color.green) / 256.0f * 100.0f) + "%");
        MaxLabelBlue.setText("Blue Max: " + (int) ((float) histogram.GetMax(Color.blue) / 256.0f * 100.0f) + "%");
    }

    public void Load() {
        if (Parent.Camera.GetHistogramMode() == HistogramMode.LINEAR) {
            mode_linear.setChecked(true);
            mode_log.setChecked(false);
        } else if (Parent.Camera.GetHistogramMode() == HistogramMode.LOG) {
            mode_linear.setChecked(false);
            mode_log.setChecked(true);
        }
        histogram.startAnimator();

        startAnimator();
    }

    public void Unload() {
        histogram.StopAnimator();
        StopAnimator();
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
        SettingsCloseButton = new EButton();
        mode_linear = new EButton();
        mode_log = new EButton();
        jPanel1 = new javax.swing.JPanel();
        Image = new javax.swing.JLabel();
        histogram = new Histogram();
        jLabel1 = new javax.swing.JLabel();
        MinLabelGreen = new javax.swing.JLabel();
        MinLabelRed = new javax.swing.JLabel();
        MinLabelBlue = new javax.swing.JLabel();
        MaxLabelRed = new javax.swing.JLabel();
        MaxLabelGreen = new javax.swing.JLabel();
        MaxLabelBlue = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        SettingsCloseButton.setText("Close");
        SettingsCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCloseButtonActionPerformed(evt);
            }
        });

        mode_linear.setText("linear");
        mode_linear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode_linearActionPerformed(evt);
            }
        });

        mode_log.setText("log");
        mode_log.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mode_logActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Image.setBackground(new java.awt.Color(0, 0, 0));
        Image.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Image.setForeground(new java.awt.Color(255, 255, 255));
        Image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/media/top.png"))); // NOI18N
        jPanel1.add(Image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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
            .addGap(0, 768, Short.MAX_VALUE)
        );
        histogramLayout.setVerticalGroup(
            histogramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 256, Short.MAX_VALUE)
        );

        jPanel1.add(histogram, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 32, 768, 256));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RGB Min / Max Indicators");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, -1, -1));

        MinLabelGreen.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        MinLabelGreen.setForeground(new java.awt.Color(155, 255, 155));
        MinLabelGreen.setText("Green Min.: ");
        jPanel1.add(MinLabelGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 303, 100, -1));

        MinLabelRed.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        MinLabelRed.setForeground(new java.awt.Color(255, 155, 155));
        MinLabelRed.setText("Red Min.: ");
        jPanel1.add(MinLabelRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 290, 100, -1));

        MinLabelBlue.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        MinLabelBlue.setForeground(new java.awt.Color(155, 155, 255));
        MinLabelBlue.setText("Blue Min.: ");
        jPanel1.add(MinLabelBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 316, 100, -1));

        MaxLabelRed.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        MaxLabelRed.setForeground(new java.awt.Color(255, 155, 155));
        MaxLabelRed.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MaxLabelRed.setText("Red Min.: ");
        jPanel1.add(MaxLabelRed, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 290, 100, -1));

        MaxLabelGreen.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        MaxLabelGreen.setForeground(new java.awt.Color(155, 255, 155));
        MaxLabelGreen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MaxLabelGreen.setText("Green Min.: ");
        jPanel1.add(MaxLabelGreen, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 303, 100, -1));

        MaxLabelBlue.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        MaxLabelBlue.setForeground(new java.awt.Color(155, 155, 255));
        MaxLabelBlue.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        MaxLabelBlue.setText("Blue Min.: ");
        jPanel1.add(MaxLabelBlue, new org.netbeans.lib.awtextra.AbsoluteConstraints(675, 316, 100, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Scale");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jLabel2))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(mode_linear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mode_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SettingsCloseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mode_linear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mode_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(SettingsCloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SettingsCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCloseButtonActionPerformed
        histogram.StopAnimator();

        try { // Save to config file
            // TODO histogram settings in config file writing
            Parent.Camera.WriteConfigFile("autosave.config");
        } catch (IOException ex) {
            Logger.getLogger(Settings1Layout.class.getName()).log(Level.SEVERE, null, ex);
        }

        Parent.LoadMainCard();
    }//GEN-LAST:event_SettingsCloseButtonActionPerformed

    private void histogramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_histogramMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_histogramMouseClicked

    private void mode_logActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mode_logActionPerformed
        mode_linear.setChecked(false);
        mode_log.setChecked(true);
        Parent.Camera.SetHistogramMode(HistogramMode.LOG);
}//GEN-LAST:event_mode_logActionPerformed

    private void mode_linearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mode_linearActionPerformed
        mode_linear.setChecked(true);
        mode_log.setChecked(false);
        Parent.Camera.SetHistogramMode(HistogramMode.LINEAR);
}//GEN-LAST:event_mode_linearActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image;
    private javax.swing.JLabel MaxLabelBlue;
    private javax.swing.JLabel MaxLabelGreen;
    private javax.swing.JLabel MaxLabelRed;
    private javax.swing.JLabel MinLabelBlue;
    private javax.swing.JLabel MinLabelGreen;
    private javax.swing.JLabel MinLabelRed;
    private EButton SettingsCloseButton;
    private javax.swing.JPanel bg;
    private Histogram histogram;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private EButton mode_linear;
    private EButton mode_log;
    // End of variables declaration//GEN-END:variables
}
