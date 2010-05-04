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
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings2Layout extends javax.swing.JPanel {

    ElphelVision Parent;

    public Settings2Layout(ElphelVision parent) {
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

        //gammacurve.SetParent(Parent);
        black_level.setValue(String.valueOf(Parent.Camera.GetBlacklevel()));
        gamma.setValue(String.valueOf(Parent.Camera.GetGamma()));
        gammacurve.SetControlPoints(6 + Parent.Camera.GetBlacklevel(), 256, 150, 256, 150, 0, 6 + 256, 0);
    }

    public void Load() {
        Parent.Camera.ReadGammaCurve();
    }

    public void StartMplayerVideoStream() {
        try {
            String mplayerOptions = null;
            if (Parent.Settings.GetOS() == OStype.Linux) {
                mplayerOptions = " -slave -idle -vo xv -colorkey 0x404040 -wid " + getWinID();
            }
            if (Parent.Settings.GetOS() == OStype.Windows) {
                mplayerOptions = " -slave -idle -vo directx -colorkey 0x404040 -wid " + getWinID();
            }
            Parent.Player.open("rtsp://" + Parent.Camera.GetIP() + ":554", mplayerOptions, Parent.Settings.GetMplayerPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void RedrawGammacurve() {
        gammacurve.repaint();
    }

    public int getWinID() {
        int winid = 0;
        if (Parent.Settings.GetOS() == OStype.Windows) {
            try {
                final java.lang.Class<?> cl;
                cl = Class.forName("sun.awt.windows.WComponentPeer");
                java.lang.reflect.Field f = cl.getDeclaredField("hwnd");
                f.setAccessible(true);
                winid = (int) f.getLong(overlay.getPeer());
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
                Object obj = m.invoke(overlay.getPeer());
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
        NavigationPanel = new javax.swing.JPanel();
        SettingsMenu1Button = new elphelvision.EButton();
        SettingsMenu2Button = new elphelvision.EButton();
        ConfirmationPanel = new javax.swing.JPanel();
        SettingsOKButton = new elphelvision.EButton();
        SettingsCancelButton = new elphelvision.EButton();
        overlay = new java.awt.Canvas();
        Gamma = new javax.swing.JPanel();
        gamma_inc = new elphelvision.EButton();
        gamma = new elphelvision.EButton();
        gamma_dec = new elphelvision.EButton();
        GammaPresets = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        gammapreset_linear = new elphelvision.EButton();
        gammapreset_cine1 = new elphelvision.EButton();
        gammapreset_cine2 = new elphelvision.EButton();
        gammapreset_cines = new elphelvision.EButton();
        gammacurve = new elphelvision.GammaCurve();
        test = new elphelvision.EButton();
        Blacklevel = new javax.swing.JPanel();
        black_inc = new elphelvision.EButton();
        black_level = new elphelvision.EButton();
        black_dec = new elphelvision.EButton();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        NavigationPanel.setBackground(java.awt.Color.black);

        SettingsMenu1Button.setText("Settings Tab 1");
        SettingsMenu1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsMenu1ButtonActionPerformed(evt);
            }
        });

        SettingsMenu2Button.setText("Settings Tab 2");
        SettingsMenu2Button.setChecked(true);
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

        ConfirmationPanel.setBackground(java.awt.Color.black);

        SettingsOKButton.setText("OK");
        SettingsOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsOKButtonActionPerformed(evt);
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
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        overlay.setBackground(java.awt.Color.darkGray);

        Gamma.setBackground(java.awt.Color.black);

        gamma_inc.setText("+");
        gamma_inc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gamma_incActionPerformed(evt);
            }
        });

        gamma.setText("Gamma");
        gamma.setHorizontalAlignment(2);
        gamma.setHorizontalTextPosition(2);
        gamma.setIconTextGap(0);
        gamma.setMargin(new java.awt.Insets(0, 5, 0, 0));
        gamma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gammaActionPerformed(evt);
            }
        });

        gamma_dec.setText("-");
        gamma_dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gamma_decActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GammaLayout = new javax.swing.GroupLayout(Gamma);
        Gamma.setLayout(GammaLayout);
        GammaLayout.setHorizontalGroup(
            GammaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamma_inc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gamma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gamma_dec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GammaLayout.setVerticalGroup(
            GammaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GammaLayout.createSequentialGroup()
                .addComponent(gamma_inc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(gamma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gamma_dec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        GammaPresets.setBackground(java.awt.Color.black);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Presets");

        gammapreset_linear.setText("linear");
        gammapreset_linear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gammapreset_linearActionPerformed(evt);
            }
        });

        gammapreset_cine1.setText("cine 1");
        gammapreset_cine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gammapreset_cine1ActionPerformed(evt);
            }
        });

        gammapreset_cine2.setText("cine S");
        gammapreset_cine2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gammapreset_cine2ActionPerformed(evt);
            }
        });

        gammapreset_cines.setText("cine 2");
        gammapreset_cines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gammapreset_cinesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GammaPresetsLayout = new javax.swing.GroupLayout(GammaPresets);
        GammaPresets.setLayout(GammaPresetsLayout);
        GammaPresetsLayout.setHorizontalGroup(
            GammaPresetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
            .addComponent(gammapreset_cine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gammapreset_linear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gammapreset_cine2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gammapreset_cines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        GammaPresetsLayout.setVerticalGroup(
            GammaPresetsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GammaPresetsLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gammapreset_linear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gammapreset_cine1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gammapreset_cines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gammapreset_cine2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        gammacurve.setBackground(new java.awt.Color(0, 0, 0));
        gammacurve.setPreferredSize(new java.awt.Dimension(262, 262));

        javax.swing.GroupLayout gammacurveLayout = new javax.swing.GroupLayout(gammacurve);
        gammacurve.setLayout(gammacurveLayout);
        gammacurveLayout.setHorizontalGroup(
            gammacurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
        gammacurveLayout.setVerticalGroup(
            gammacurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        test.setText("test");
        test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testActionPerformed(evt);
            }
        });

        Blacklevel.setBackground(java.awt.Color.black);

        black_inc.setText("+");
        black_inc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                black_incActionPerformed(evt);
            }
        });

        black_level.setText("Black Level");
        black_level.setHorizontalAlignment(2);
        black_level.setHorizontalTextPosition(2);
        black_level.setIconTextGap(0);
        black_level.setMargin(new java.awt.Insets(0, 5, 0, 0));
        black_level.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                black_levelActionPerformed(evt);
            }
        });

        black_dec.setText("-");
        black_dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                black_decActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BlacklevelLayout = new javax.swing.GroupLayout(Blacklevel);
        Blacklevel.setLayout(BlacklevelLayout);
        BlacklevelLayout.setHorizontalGroup(
            BlacklevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(black_inc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(black_dec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(black_level, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BlacklevelLayout.setVerticalGroup(
            BlacklevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlacklevelLayout.createSequentialGroup()
                .addComponent(black_inc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(black_level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(black_dec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
                        .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(Gamma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(Blacklevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(gammacurve, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(GammaPresets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(overlay, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(overlay, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GammaPresets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addComponent(test, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                        .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Gamma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Blacklevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(gammacurve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
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

    private void SettingsOKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsOKButtonActionPerformed
        try { // Save to config file
            Parent.Camera.WriteConfigFile("autosave.cfg");
        } catch (IOException ex) {
            Logger.getLogger(SettingsLayout.class.getName()).log(Level.SEVERE, null, ex);
        }

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.Player.close();
        Parent.StartMplayerVideoStream();
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void gammaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gammaActionPerformed
    }//GEN-LAST:event_gammaActionPerformed

    private void gamma_incActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gamma_incActionPerformed
        Parent.Camera.SetGamma(Parent.Camera.GetGamma() + 0.1f);
        gamma.setValue(String.valueOf(Parent.Camera.GetGamma()));

        Parent.Camera.ReadGammaCurve();
        RedrawGammacurve();
    }//GEN-LAST:event_gamma_incActionPerformed

    private void black_decActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_black_decActionPerformed
        Parent.Camera.SetBlacklevel(Parent.Camera.GetBlacklevel() - 1);
        black_level.setValue(String.valueOf(Parent.Camera.GetBlacklevel()));

        Parent.Camera.ReadGammaCurve();
        RedrawGammacurve();
    }//GEN-LAST:event_black_decActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "MainCard");
        Parent.Player.close();
        Parent.StartMplayerVideoStream();
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void black_levelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_black_levelActionPerformed
        black_inc.setChecked(false);
        black_level.setChecked(true);
        black_dec.setChecked(false);
    }//GEN-LAST:event_black_levelActionPerformed

    private void black_incActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_black_incActionPerformed
        Parent.Camera.SetBlacklevel(Parent.Camera.GetBlacklevel() + 1);
        black_level.setValue(String.valueOf(Parent.Camera.GetBlacklevel()));

        gammacurve.SetControlPoints(6 + Parent.Camera.GetBlacklevel(), 256, 150, 256, 150, 0, 6 + 256, 0);

        Parent.Camera.ReadGammaCurve();
        RedrawGammacurve();
    }//GEN-LAST:event_black_incActionPerformed

    private void gamma_decActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gamma_decActionPerformed
        Parent.Camera.SetGamma(Parent.Camera.GetGamma() - 0.1f);
        gamma.setValue(String.valueOf(Parent.Camera.GetGamma()));

        Parent.Camera.ReadGammaCurve();
        RedrawGammacurve();
    }//GEN-LAST:event_gamma_decActionPerformed

    private void SettingsMenu1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu1ButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), "Settings1Card");
        Parent.Player.close();
}//GEN-LAST:event_SettingsMenu1ButtonActionPerformed

    private void SettingsMenu2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsMenu2ButtonActionPerformed
}//GEN-LAST:event_SettingsMenu2ButtonActionPerformed

    private void testActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testActionPerformed
        gammacurve.GetGammaTable();
    }//GEN-LAST:event_testActionPerformed

    private void gammapreset_cine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gammapreset_cine1ActionPerformed
        gammapreset_linear.setChecked(false);
        gammapreset_cine1.setChecked(true);
        gammapreset_cine2.setChecked(false);
        gammapreset_cines.setChecked(false);
    }//GEN-LAST:event_gammapreset_cine1ActionPerformed

    private void gammapreset_linearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gammapreset_linearActionPerformed
        gammapreset_linear.setChecked(true);
        gammapreset_cine1.setChecked(false);
        gammapreset_cine2.setChecked(false);
        gammapreset_cines.setChecked(false);
    }//GEN-LAST:event_gammapreset_linearActionPerformed

    private void gammapreset_cine2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gammapreset_cine2ActionPerformed
        gammapreset_linear.setChecked(false);
        gammapreset_cine1.setChecked(false);
        gammapreset_cine2.setChecked(true);
        gammapreset_cines.setChecked(false);
    }//GEN-LAST:event_gammapreset_cine2ActionPerformed

    private void gammapreset_cinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gammapreset_cinesActionPerformed
        gammapreset_linear.setChecked(false);
        gammapreset_cine1.setChecked(false);
        gammapreset_cine2.setChecked(false);
        gammapreset_cines.setChecked(true);
    }//GEN-LAST:event_gammapreset_cinesActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Blacklevel;
    private javax.swing.JPanel ConfirmationPanel;
    private javax.swing.JPanel Gamma;
    private javax.swing.JPanel GammaPresets;
    private javax.swing.JPanel NavigationPanel;
    private elphelvision.EButton SettingsCancelButton;
    private elphelvision.EButton SettingsMenu1Button;
    private elphelvision.EButton SettingsMenu2Button;
    private elphelvision.EButton SettingsOKButton;
    private javax.swing.JPanel bg;
    private elphelvision.EButton black_dec;
    private elphelvision.EButton black_inc;
    private elphelvision.EButton black_level;
    private elphelvision.EButton gamma;
    private elphelvision.EButton gamma_dec;
    private elphelvision.EButton gamma_inc;
    private elphelvision.GammaCurve gammacurve;
    private elphelvision.EButton gammapreset_cine1;
    private elphelvision.EButton gammapreset_cine2;
    private elphelvision.EButton gammapreset_cines;
    private elphelvision.EButton gammapreset_linear;
    private javax.swing.JLabel jLabel1;
    private java.awt.Canvas overlay;
    private elphelvision.EButton test;
    // End of variables declaration//GEN-END:variables
}
