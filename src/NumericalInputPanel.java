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
import javax.swing.JTextField;

public class NumericalInputPanel extends javax.swing.JPanel {

    JTextField FieldParent;
    String TargetCard;
    ElphelVision Parent;

    public NumericalInputPanel(ElphelVision parent) {
        this.Parent = parent;

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

    public void Load(String Varname, int value, JTextField parent, String targetCard) {
        this.FieldParent = parent;
        this.VarName.setText(Varname);
        this.TargetCard = targetCard;
        this.NumberField.setText(String.valueOf(value));
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
        Keypad = new javax.swing.JPanel();
        Number1 = new EButton();
        Number2 = new EButton();
        Number3 = new EButton();
        Number4 = new EButton();
        Number5 = new EButton();
        Number6 = new EButton();
        Number7 = new EButton();
        Number8 = new EButton();
        Number9 = new EButton();
        NumberClear = new EButton();
        Number0 = new EButton();
        Number12 = new EButton();
        Number13 = new EButton();
        NumberPlusOne = new EButton();
        Number15 = new EButton();
        Number16 = new EButton();
        Number17 = new EButton();
        NumberMinusOne = new EButton();
        Number19 = new EButton();
        ConfirmationPanel = new javax.swing.JPanel();
        SettingsCancelButton = new EButton();
        SettingsOKButton = new EButton();
        NumberPanel = new javax.swing.JPanel();
        VarName = new javax.swing.JLabel();
        NumberField = new javax.swing.JTextField();

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setPreferredSize(new java.awt.Dimension(1024, 600));

        Keypad.setBackground(new java.awt.Color(0, 0, 0));

        Number1.setClickFeedback(true);
        Number1.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number1.setPreferredSize(new java.awt.Dimension(100, 60));
        Number1.setText("1");
        Number1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number1ActionPerformed(evt);
            }
        });

        Number2.setClickFeedback(true);
        Number2.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number2.setPreferredSize(new java.awt.Dimension(100, 60));
        Number2.setText("2");
        Number2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number2ActionPerformed(evt);
            }
        });

        Number3.setClickFeedback(true);
        Number3.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number3.setPreferredSize(new java.awt.Dimension(100, 60));
        Number3.setText("3");
        Number3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number3ActionPerformed(evt);
            }
        });

        Number4.setClickFeedback(true);
        Number4.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number4.setPreferredSize(new java.awt.Dimension(100, 60));
        Number4.setText("4");
        Number4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number4ActionPerformed(evt);
            }
        });

        Number5.setClickFeedback(true);
        Number5.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number5.setPreferredSize(new java.awt.Dimension(100, 60));
        Number5.setText("5");
        Number5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number5ActionPerformed(evt);
            }
        });

        Number6.setClickFeedback(true);
        Number6.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number6.setPreferredSize(new java.awt.Dimension(100, 60));
        Number6.setText("6");
        Number6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number6ActionPerformed(evt);
            }
        });

        Number7.setClickFeedback(true);
        Number7.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number7.setPreferredSize(new java.awt.Dimension(100, 60));
        Number7.setText("7");
        Number7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number7ActionPerformed(evt);
            }
        });

        Number8.setClickFeedback(true);
        Number8.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number8.setPreferredSize(new java.awt.Dimension(100, 60));
        Number8.setText("8");
        Number8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number8ActionPerformed(evt);
            }
        });

        Number9.setClickFeedback(true);
        Number9.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number9.setPreferredSize(new java.awt.Dimension(100, 60));
        Number9.setText("9");
        Number9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number9ActionPerformed(evt);
            }
        });

        NumberClear.setClickFeedback(true);
        NumberClear.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        NumberClear.setPreferredSize(new java.awt.Dimension(100, 60));
        NumberClear.setText("Clear");
        NumberClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumberClearActionPerformed(evt);
            }
        });

        Number0.setClickFeedback(true);
        Number0.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number0.setPreferredSize(new java.awt.Dimension(100, 60));
        Number0.setText("0");
        Number0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number0ActionPerformed(evt);
            }
        });

        Number12.setClickFeedback(true);
        Number12.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number12.setPreferredSize(new java.awt.Dimension(100, 60));
        Number12.setText("DEL");
        Number12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number12ActionPerformed(evt);
            }
        });

        Number13.setClickFeedback(true);
        Number13.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number13.setPreferredSize(new java.awt.Dimension(100, 60));
        Number13.setText("<-");
        Number13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number13ActionPerformed(evt);
            }
        });

        NumberPlusOne.setClickFeedback(true);
        NumberPlusOne.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        NumberPlusOne.setPreferredSize(new java.awt.Dimension(100, 60));
        NumberPlusOne.setText("+1");
        NumberPlusOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumberPlusOneActionPerformed(evt);
            }
        });

        Number15.setClickFeedback(true);
        Number15.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number15.setPreferredSize(new java.awt.Dimension(100, 60));
        Number15.setText("Pos1");
        Number15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number15ActionPerformed(evt);
            }
        });

        Number16.setClickFeedback(true);
        Number16.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number16.setPreferredSize(new java.awt.Dimension(100, 60));
        Number16.setText("End");
        Number16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number16ActionPerformed(evt);
            }
        });

        Number17.setClickFeedback(true);
        Number17.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number17.setPreferredSize(new java.awt.Dimension(100, 60));
        Number17.setText("+10");
        Number17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number17ActionPerformed(evt);
            }
        });

        NumberMinusOne.setClickFeedback(true);
        NumberMinusOne.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        NumberMinusOne.setPreferredSize(new java.awt.Dimension(100, 60));
        NumberMinusOne.setText("-1");
        NumberMinusOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumberMinusOneActionPerformed(evt);
            }
        });

        Number19.setClickFeedback(true);
        Number19.setFont(new java.awt.Font("DejaVu Sans", 0, 18));
        Number19.setPreferredSize(new java.awt.Dimension(100, 60));
        Number19.setText("-10");
        Number19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Number19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout KeypadLayout = new javax.swing.GroupLayout(Keypad);
        Keypad.setLayout(KeypadLayout);
        KeypadLayout.setHorizontalGroup(
            KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeypadLayout.createSequentialGroup()
                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(KeypadLayout.createSequentialGroup()
                            .addComponent(Number1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Number2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Number3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(KeypadLayout.createSequentialGroup()
                            .addComponent(Number4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Number5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(Number6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(KeypadLayout.createSequentialGroup()
                        .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Number0, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(KeypadLayout.createSequentialGroup()
                                .addComponent(Number7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Number8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NumberClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Number9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KeypadLayout.createSequentialGroup()
                        .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NumberPlusOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Number17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NumberMinusOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(KeypadLayout.createSequentialGroup()
                                .addGap(313, 313, 313)
                                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Number16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Number15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Number13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(KeypadLayout.createSequentialGroup()
                        .addComponent(Number19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 331, Short.MAX_VALUE)
                        .addComponent(Number12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        KeypadLayout.setVerticalGroup(
            KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeypadLayout.createSequentialGroup()
                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Number2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Number5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumberPlusOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Number8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumberMinusOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(KeypadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Number0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumberClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Number12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        ConfirmationPanel.setBackground(java.awt.Color.black);

        SettingsCancelButton.setText("Cancel");
        SettingsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCancelButtonActionPerformed(evt);
            }
        });

        SettingsOKButton.setText("OK");
        SettingsOKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsOKButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ConfirmationPanelLayout = new javax.swing.GroupLayout(ConfirmationPanel);
        ConfirmationPanel.setLayout(ConfirmationPanelLayout);
        ConfirmationPanelLayout.setHorizontalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createSequentialGroup()
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        ConfirmationPanelLayout.setVerticalGroup(
            ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfirmationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(SettingsOKButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SettingsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        NumberPanel.setBackground(java.awt.Color.black);

        VarName.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        VarName.setForeground(new java.awt.Color(255, 255, 255));
        VarName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VarName.setText("Name");
        VarName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        VarName.setAlignmentY(0.0F);
        VarName.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        VarName.setIconTextGap(0);
        VarName.setInheritsPopupMenu(false);
        VarName.setRequestFocusEnabled(false);
        VarName.setVerifyInputWhenFocusTarget(false);
        VarName.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        NumberField.setBackground(new java.awt.Color(0, 0, 0));
        NumberField.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 14));
        NumberField.setForeground(new java.awt.Color(255, 255, 255));
        NumberField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NumberField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        NumberField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                NumberFieldCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout NumberPanelLayout = new javax.swing.GroupLayout(NumberPanel);
        NumberPanel.setLayout(NumberPanelLayout);
        NumberPanelLayout.setHorizontalGroup(
            NumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NumberPanelLayout.createSequentialGroup()
                .addComponent(VarName)
                .addGap(134, 134, 134)
                .addComponent(NumberField, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
        );
        NumberPanelLayout.setVerticalGroup(
            NumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NumberPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(NumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VarName)
                    .addComponent(NumberField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addContainerGap(800, Short.MAX_VALUE)
                .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Keypad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(411, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(Keypad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(ConfirmationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        this.FieldParent.setText(NumberField.getText());

        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), TargetCard);
    }//GEN-LAST:event_SettingsOKButtonActionPerformed

    private void SettingsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCancelButtonActionPerformed
        CardLayout cl = (CardLayout) (Parent.GetCardManager().getLayout());
        cl.show(Parent.GetCardManager(), TargetCard);
    }//GEN-LAST:event_SettingsCancelButtonActionPerformed

    private void NumberFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_NumberFieldCaretUpdate
    }//GEN-LAST:event_NumberFieldCaretUpdate

    private void Number1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number1ActionPerformed
        NumberField.setText(NumberField.getText() + "1");
    }//GEN-LAST:event_Number1ActionPerformed

    private void Number2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number2ActionPerformed
        NumberField.setText(NumberField.getText() + "2");        // TODO add your handling code here:
    }//GEN-LAST:event_Number2ActionPerformed

    private void Number3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number3ActionPerformed
        NumberField.setText(NumberField.getText() + "3");
    }//GEN-LAST:event_Number3ActionPerformed

    private void Number4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number4ActionPerformed
        NumberField.setText(NumberField.getText() + "4");
    }//GEN-LAST:event_Number4ActionPerformed

    private void Number5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number5ActionPerformed
        NumberField.setText(NumberField.getText() + "5");
    }//GEN-LAST:event_Number5ActionPerformed

    private void Number6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number6ActionPerformed
        NumberField.setText(NumberField.getText() + "6");
    }//GEN-LAST:event_Number6ActionPerformed

    private void Number7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number7ActionPerformed
        NumberField.setText(NumberField.getText() + "7");
    }//GEN-LAST:event_Number7ActionPerformed

    private void Number8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number8ActionPerformed
        NumberField.setText(NumberField.getText() + "8");
    }//GEN-LAST:event_Number8ActionPerformed

    private void Number9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number9ActionPerformed
        NumberField.setText(NumberField.getText() + "9");
    }//GEN-LAST:event_Number9ActionPerformed

    private void NumberClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumberClearActionPerformed
        NumberField.setText("");
    }//GEN-LAST:event_NumberClearActionPerformed

    private void Number0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number0ActionPerformed
        NumberField.getCaretPosition();
        NumberField.setText(NumberField.getText() + "0");
    }//GEN-LAST:event_Number0ActionPerformed

    private void Number12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Number12ActionPerformed

    private void Number13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Number13ActionPerformed

    private void NumberPlusOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumberPlusOneActionPerformed
        int temp = Integer.parseInt(NumberField.getText()) + 1;
        if (temp < 0) {
            temp = 0;
        }
        NumberField.setText(String.valueOf(temp));
    }//GEN-LAST:event_NumberPlusOneActionPerformed

    private void Number15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number15ActionPerformed
        NumberField.setCaretPosition(0);
    }//GEN-LAST:event_Number15ActionPerformed

    private void Number16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number16ActionPerformed
        NumberField.setCaretPosition(NumberField.getText().length());
    }//GEN-LAST:event_Number16ActionPerformed

    private void Number17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number17ActionPerformed
        int temp = Integer.parseInt(NumberField.getText()) + 10;
        if (temp < 0) {
            temp = 0;
        }
        NumberField.setText(String.valueOf(temp));        // TODO add your handling code here:
    }//GEN-LAST:event_Number17ActionPerformed

    private void NumberMinusOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumberMinusOneActionPerformed
        int temp = Integer.parseInt(NumberField.getText()) - 1;
        if (temp < 0) {
            temp = 0;
        }
        NumberField.setText(String.valueOf(temp));
    }//GEN-LAST:event_NumberMinusOneActionPerformed

    private void Number19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Number19ActionPerformed
        int temp = Integer.parseInt(NumberField.getText()) - 10;
        if (temp < 0) {
            temp = 0;
        }
        NumberField.setText(String.valueOf(temp));        // TODO add your handling code here:
    }//GEN-LAST:event_Number19ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ConfirmationPanel;
    private javax.swing.JPanel Keypad;
    private EButton Number0;
    private EButton Number1;
    private EButton Number12;
    private EButton Number13;
    private EButton Number15;
    private EButton Number16;
    private EButton Number17;
    private EButton Number19;
    private EButton Number2;
    private EButton Number3;
    private EButton Number4;
    private EButton Number5;
    private EButton Number6;
    private EButton Number7;
    private EButton Number8;
    private EButton Number9;
    private EButton NumberClear;
    private javax.swing.JTextField NumberField;
    private EButton NumberMinusOne;
    private javax.swing.JPanel NumberPanel;
    private EButton NumberPlusOne;
    private EButton SettingsCancelButton;
    private EButton SettingsOKButton;
    private javax.swing.JLabel VarName;
    private javax.swing.JPanel bg;
    // End of variables declaration//GEN-END:variables
}
