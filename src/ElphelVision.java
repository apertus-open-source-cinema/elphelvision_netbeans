/*! Copyright (C) 2009 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *! Description: Main class of the Elphel Vision viewfinder software for Elphel cameras.
 *! Thanks to Adrian BER and his JJMplayer sources which helped greatly creating this.
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

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ElphelVision extends Panel implements ActionListener, Runnable {

    //private static final long serialVersionUID = 21L;
    Camera Camera; // class containing all camera specific information
    UserSettings Settings; // class containing user settings
    Mplayer Player; // Mplayer class dealing with video overlay
    //JPanel MainCard; // main card of the application
    JPanel CardManager;
    Thread ReadCameraDataAnimator;
    Thread InfoAreaAnimator;
    Thread HistogramAnimator;
    long winid = 0;
    int ReadCameradataFPS = 10;
    int InfoAreaFPS = 10;
    int HistogramFPS = 15;
    ConnectLayout ConnectCardLayout;
    MainLayout MaincardLayout;
    SettingsLayout Settings1CardLayout;
    Settings2Layout Settings2CardLayout;
    ResolutionSettings ResolutionSettingsCardLayout;
    FPSSettings FPSSettingsCardLayout;
    NumericalInputPanel NumberPanel;

    public static void main(String[] args) {
        Frame f = new Frame();
        f.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }

            ;
        });

        ElphelVision EV = new ElphelVision();
        EV.start();
        EV.setSize(1024, 600); // same size as defined in the HTML APPLET
        f.add(EV);
        f.pack();
        f.setSize(1024, 600 + 20); // add 20, seems enough for the Frame title,
        f.show();
    }

    public ElphelVision() {
        //super();
    }

    public void destroy() {
        Player.close();
    }

    public JPanel GetCardManager() {
        return this.CardManager;
    }

    public void StartMplayerVideoStream() {
        try {
            String mplayerOptions = null;
            if (Settings.GetOS() == OStype.Linux) {
                //mplayerOptions = " -slave -idle -lavdopts skipframe=nonref:skiploopfilter=all -benchmark -vo x11:ck-method=auto -colorkey 0x404040 -wid " + MaincardLayout.getWinID();
                mplayerOptions = " -slave -idle -lavdopts skipframe=nonref:skiploopfilter=all -benchmark -vo x11 -zoom -colorkey 0x404040";
                Settings.SetMplayerParameters(mplayerOptions);
            }
            if (Settings.GetOS() == OStype.Windows) {
                mplayerOptions = " -slave -idle -lavdopts skipframe=nonref:skiploopfilter=all -benchmark -vo directx -colorkey 0x404040";
                Settings.SetMplayerParameters(mplayerOptions);
                MaincardLayout.getWinID();
            }
            Player.open("rtsp://" + Camera.GetIP() + ":554", Settings.GetMplayerParameters() + " -wid " + MaincardLayout.getWinID(), Settings.GetMplayerPath());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        /*
        overlay.invalidate();


        Graphics graphics = overlay.getGraphics();

        // clear the area before drawing anything new on the canvas
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(0, 0, 800, 600);

        int x = 50;
        int y = 50;

        Font font = new Font("Arial", Font.PLAIN, 18);
        graphics.setFont(font);

        // draw border by drawing it 4 times with offset in each direction
        graphics.setColor(Color.black);
        graphics.drawString(testtext, x+1, y+1);
        graphics.drawString(testtext, x+1, y-1);
        graphics.drawString(testtext, x-1, y+1);
        graphics.drawString(testtext, x-1, y-1);

        // draw the filling
        graphics.setColor(Color.white);
        graphics.drawString(testtext, x, y);
         */
        //debugoutput.append(logger.);
    }

    public void start() {
        //super.start();

        ReadCameraDataAnimator = new Thread(this);
        HistogramAnimator = new Thread(this);
        InfoAreaAnimator = new Thread(this);

        //Init everything
        Camera = new Camera();
        Settings = new UserSettings();

        String osname = System.getProperty("os.name");
        if (osname.startsWith("Windows")) {
            Settings.SetOS(OStype.Windows);
        } else {
            Settings.SetOS(OStype.Linux);
        }

        if (!Settings.CheckMplayerInstallation()) {
            JOptionPane.showMessageDialog(this, "Mplayer was not detected!");
        }

        // global applet settings
        setSize(1024, 600);
        setBackground(Color.black);
        setLayout(new BorderLayout());

        //Create the panel that contains the "cards".
        CardManager = new JPanel(new CardLayout());

        ConnectCardLayout = new ConnectLayout(this);
        //ConnectCardLayout.init();
        MaincardLayout = new MainLayout(this);
        //MaincardLayout.init();
        Settings1CardLayout = new SettingsLayout(this);
        //Settings1CardLayout.init();
        Settings2CardLayout = new Settings2Layout(this);
        // Settings2CardLayout.init();
        ResolutionSettingsCardLayout = new ResolutionSettings(this);
        //ResolutionSettingsCardLayout.init();
        FPSSettingsCardLayout = new FPSSettings(this);
        //FPSSettingsCardLayout.init();
        NumberPanel = new NumericalInputPanel(this);
        //NumberPanel.init();

        CardManager.add(ConnectCardLayout, "ConnectCard");
        CardManager.add(MaincardLayout, "MainCard");
        CardManager.add(Settings1CardLayout, "Settings1Card");
        CardManager.add(Settings2CardLayout, "Settings2Card");
        CardManager.add(ResolutionSettingsCardLayout, "CustomResolutionCard");
        CardManager.add(FPSSettingsCardLayout, "CustomFPSCard");
        CardManager.add(NumberPanel, "Numberpanel");

        add(CardManager);

        ReadCameraData();
        if (!ReadCameraDataAnimator.isAlive()) {
            ReadCameraDataAnimator.start();
        }
        if (!HistogramAnimator.isAlive()) {
            HistogramAnimator.start();
        }

        InitInfoArea();
        if (!InfoAreaAnimator.isAlive()) {
            InfoAreaAnimator.start();
        }

        Player = new Mplayer();
    }

    public void PostConnect() {
        LoadConfigFile("autosave.cfg");
    }

    private void LoadConfigFile(String Filename) {
        File configfile = new File(Filename);
        if (configfile.exists()) {
            try {
                Camera.ReadConfigFile(Filename);
            } catch (FileNotFoundException ex) {
            }
        }
    }

    public void run() {
        while (Thread.currentThread() == ReadCameraDataAnimator) {
            ReadCameraData();
            try {
                Thread.sleep((int) (1.0f / ReadCameradataFPS * 1000.0f));
            } catch (InterruptedException e) {
                break;
            }
        }

        while (Thread.currentThread() == HistogramAnimator) {
            if (Camera != null) {
                Camera.ReadHistogram();
                if (MaincardLayout != null) {
                    MaincardLayout.RedrawHistogram();
                }
            }
            try {
                Thread.sleep((int) (1.0f / HistogramFPS * 1000.0f));
            } catch (InterruptedException e) {
                break;
            }
        }

        while (Thread.currentThread() == InfoAreaAnimator) {
            if (Camera != null) {
                UpdateInfoArea();
            }
            try {
                Thread.sleep((int) (1.0f / InfoAreaFPS * 1000.0f));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void ReadCameraData() {
        try {
            Camera.UpdateCameraData();
        } catch (Exception ex) {
        }
    }
    Style StyleRed = null;
    Style StyleNormal = null;

    public void InitInfoArea() {
        StyledDocument doc = (StyledDocument) MaincardLayout.GetInfoTextPane().getDocument();
        StyleRed = doc.addStyle("RedNotice", null);
        StyleConstants.setForeground(StyleRed, Color.red);
        StyleConstants.setBold(StyleRed, true);
        StyleNormal = doc.addStyle("NormalText", null);
        StyleConstants.setForeground(StyleNormal, Color.white);
        StyleConstants.setBold(StyleNormal, true);

        MutableAttributeSet standard = new SimpleAttributeSet();
        StyleConstants.setAlignment(standard, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, 0, standard, true);

    }

    public void UpdateInfoArea() {
        StyledDocument doc = (StyledDocument) MaincardLayout.GetInfoTextPane().getDocument();

        // Clear content
        MaincardLayout.GetInfoTextPane().setText("");

        String CameraInfo = "";
        if ((Camera.GetImageWidth() == 1920) && (Camera.GetImageHeight() == 1088)) {
            CameraInfo = "1080p ";
        }

        if ((Camera.GetImageWidth() == 1280) && (Camera.GetImageHeight() == 720)) {
            CameraInfo = "720p ";
        }

        CameraInfo += "(" + Camera.GetImageWidth() + "x" + Camera.GetImageHeight() + ")";
        CameraInfo += "    ";
        CameraInfo += Camera.GetFPS() + "fps";
        CameraInfo += "    ";
        CameraInfo += "JPEG: " + Camera.GetImageJPEGQuality() + "%";
        CameraInfo += "    ";

        CameraInfo += "WB: " + Camera.GetWhiteBalance().toString();
        CameraInfo += "    ";

        if (Camera.GetRecordFormat() == RecordFormat.MOV) {
            CameraInfo += "Quicktime";
        }

        if (Camera.GetRecordFormat() == RecordFormat.OGM) {
            CameraInfo += "OGM";
        }

        if (Camera.GetRecordFormat() == RecordFormat.JPEG) {
            CameraInfo += "JPEG Sequence";
        }
        try {
            doc.insertString(doc.getLength(), CameraInfo, StyleNormal);
        } catch (BadLocationException ex) {
            Logger.getLogger(ElphelVision.class.getName()).log(Level.SEVERE, null, ex);
        }
        CameraInfo = "";
        CameraInfo += "    ";
        if (Camera.GetFreeHDDSpace() == -1) {
            CameraInfo += "HDD: not found"; // No HDD attached/detected
            MaincardLayout.EnableRecord(false); // disable Rec Button
            try {
                doc.insertString(doc.getLength(), CameraInfo, StyleRed);
            } catch (BadLocationException ex) {
                Logger.getLogger(ElphelVision.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            CameraInfo += "HDD: " + Camera.GetFreeHDDSpace() + "% free";
            if (!MaincardLayout.GetRecordEnabled()) {
                MaincardLayout.EnableRecord(true);
            }
            try {
                doc.insertString(doc.getLength(), CameraInfo, StyleNormal);
            } catch (BadLocationException ex) {
                Logger.getLogger(ElphelVision.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        CameraInfo = "";
        CameraInfo += "    ";
        if (Camera.GetCamogmState() == CamogmState.RECORDING) {
            CameraInfo += "Recording (frame#): " + Camera.GetRecordedFramesCount();
            CameraInfo += "    ";
            CameraInfo += "Datarate: " + Camera.GetDatarate() + " MBit/s";
            /*
            Calendar now = Calendar.getInstance();
            double delta_t = now.getTimeInMillis() - Camera.GetRecordstartTime();
            int animateframes = (int) (delta_t / 1000 * Camera.GetFPS());
            CameraInfo += "Recording (frame#): " + animateframes;
             */

        } else {
            CameraInfo += "STOPPED";
        }

        try {
            doc.insertString(doc.getLength(), CameraInfo, StyleNormal);
        } catch (BadLocationException ex) {
            Logger.getLogger(ElphelVision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} 
