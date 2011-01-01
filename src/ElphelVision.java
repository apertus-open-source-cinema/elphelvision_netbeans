/*! Copyright (C) 2009 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *! Description: Main class of the Elphel Vision viewfinder software for Elphel cameras.
 *! Thanks to Adrian BER and his JJMplayer sources which helped greatly creating this.
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
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
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
    VLCPlayer VLCPlayer = null; // VLC Video player class dealing with video streaming
    GstreamerPlayer GstreamerPlayer = null; // Gstreamer Video player class dealing with video streaming
    Thread ReadCameraDataAnimator;
    Thread InfoAreaAnimator;
    Thread HistogramAnimator;
    long winid = 0;
    int ReadCameradataFPS = 10;
    int InfoAreaFPS = 10;
    int HistogramFPS = 15;
    JPanel CardManager;
    ConnectLayout ConnectCardLayout;
    MainLayout MaincardLayout;
    Settings1Layout Settings1CardLayout;
    Settings2Layout Settings2CardLayout;
    Settings3Layout Settings3CardLayout;
    ResolutionSettings ResolutionSettingsCardLayout;
    FPSSettings FPSSettingsCardLayout;
    NumericalInputPanel NumberPanel;
    GuidesLayout GuidesPanel;
    PlaybackLayout PlaybackCardLayout;
    PhotoSettingsLayout PhotoSettingsCardLayout;
    String AppVersion = "0.4";
    static boolean WindowDecorations = false;
    static boolean NoCameraParameter = false;
    Utils Utils;
    static int Debuglevel;

    public static void main(String[] args) {
        ProcessArgs(args);

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
        EV.setSize(1024, 600);

        f.add(EV);
        if (!WindowDecorations) {
            f.setUndecorated(true);
        }
        f.pack();
        if (!WindowDecorations) {
            f.setSize(1024, 600);
        } else {
            f.setSize(1024, 600 + 20); // add 20, seems enough for the Frame title,
        }
        f.show();
    }

    public ElphelVision() {
        SetConsoleColor(Color.WHITE);
        System.out.println("=====================================================");
        SetConsoleColor(Color.CYAN);
        System.out.println(" ElphelVision - Apertus Viewfinder Software");
        System.out.println(" http://apertus.org");
        System.out.println(" Version: " + this.GetAppVersion());
        SetConsoleColor(Color.WHITE);
        System.out.println("=====================================================");
        System.out.println(" ");
        if (NoCameraParameter) {
            System.out.println("Starting without a connected camera...");
        }


        Utils = new Utils();
        TestGraphicCapability();
    }
    private boolean isShapingSupported;
    private boolean isOpacityControlSupported;
    private boolean isTranslucencySupported;
    private GraphicsConfiguration translucencyCapableGC;

    public GraphicsConfiguration GetTranslucencyCapableGC() {
        return translucencyCapableGC;
    }

    public void TestGraphicCapability() {
        isShapingSupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.PERPIXEL_TRANSPARENT);
        isOpacityControlSupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.TRANSLUCENT);
        isTranslucencySupported = AWTUtilitiesWrapper.isTranslucencySupported(AWTUtilitiesWrapper.PERPIXEL_TRANSLUCENT);

        translucencyCapableGC = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        if (!AWTUtilitiesWrapper.isTranslucencyCapable(translucencyCapableGC)) {
            translucencyCapableGC = null;

            GraphicsEnvironment env =
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = env.getScreenDevices();

            for (int i = 0; i < devices.length && translucencyCapableGC == null; i++) {
                GraphicsConfiguration[] configs = devices[i].getConfigurations();
                for (int j = 0; j < configs.length && translucencyCapableGC == null; j++) {
                    if (AWTUtilitiesWrapper.isTranslucencyCapable(configs[j])) {
                        translucencyCapableGC = configs[j];
                    }
                }
            }
            if (translucencyCapableGC == null) {
                isTranslucencySupported = false;
            }
        }

        if (!isTranslucencySupported) {
            WriteWarningtoConsole("Translucency is not supported by current Graphics Environment");
        } else {
            WriteLogtoConsole("Translucency is supported by current Graphics Environment");
        }
        if (!isOpacityControlSupported) {
            WriteWarningtoConsole("OpacityControl is not supported by current Graphics Environment");
        } else {
            WriteLogtoConsole("OpacityControl is supported by current Graphics Environment");
        }
        if (!isShapingSupported) {
            WriteWarningtoConsole("PERPIXEL_TRANSPARENT is not supported by current Graphics Environment");
        } else {
            WriteLogtoConsole("PERPIXEL_TRANSPARENT is supported by current Graphics Environment");
        }
    }

    public void destroy() {
        VLCPlayer.close();
    }

    public int GetDebuglevel() {
        return Debuglevel;
    }

    public void StartVideoPlayer() {
        if (NoCameraParameter) {
            VLCPlayer.PlayLocalVideoFile("test.avi");
        } else {
            if (Settings.GetVideoPlayer() == VideoPlayer.GSTREAMER) {
                //GstreamerPlayer.PlayVideoStream();
            }
            if (Settings.GetVideoPlayer() == VideoPlayer.VLC) {
                VLCPlayer.PlayVideoStream();
            }
        }
    }

    public void StopVideoPlayer() {
        if (Settings.GetVideoPlayer() == VideoPlayer.GSTREAMER) {
            //GstreamerPlayer.close();
        }
        if (Settings.GetVideoPlayer() == VideoPlayer.VLC) {
            VLCPlayer.close();
        }
    }

    static void ProcessArgs(String[] args) {
        Debuglevel = 2; // DEFAULT value

        for (int i = 0; i < args.length; i++) {
            WindowDecorations = false;
            if (args[i].equals("--help") || args[i].equals("-h")) {
                PrintHelp();
                System.exit(0);
            }
            if (args[i].equals("--no-camera")) {
                NoCameraParameter = true; // TODO: not fully implemented yet
            }
            // Default

            if (args[i].equals("--debuglevel")) {
                if (args[i + 1].equals("0")) {
                    Debuglevel = 0;
                }
                if (args[i + 1].equals("1")) {
                    Debuglevel = 1;
                }
                if (args[i + 1].equals("2")) {
                    Debuglevel = 2;
                }
                if (args[i + 1].equals("3")) {
                    Debuglevel = 3;
                }
            }
        }
    }

    public boolean GetNoCameraParameter() {
        return NoCameraParameter;
    }

    static void PrintHelp() {
        System.out.println("ElphelVision Help: ");
        System.out.println("Arguments: ");
        System.out.println("\t-h, --help\tshow this help message.");
        System.out.println("\t--no-camera\tstart without a connected camera for testing purpose");
        System.out.println("\t--debuglevel N\t0 - none, 1 - only errors, 2 - errors and warnings (default), 3 - everything");
    }

    public void SetConsoleColor(Color newcolor) {
        if (newcolor == Color.BLACK) {
            System.out.print("\033[30m");
        } else if (newcolor == Color.WHITE) {
            System.out.print("\033[39m");
        } else if (newcolor == Color.RED) {
            System.out.print("\033[31m");
        } else if (newcolor == Color.GREEN) {
            System.out.print("\033[32m");
        } else if (newcolor == Color.YELLOW) {
            System.out.print("\033[33m");
        } else if (newcolor == Color.BLUE) {
            System.out.print("\033[34m");
        } else if (newcolor == Color.MAGENTA) {
            System.out.print("\033[35m");
        } else if (newcolor == Color.CYAN) {
            System.out.print("\033[36m");
        }
        /*
        ANSI CODES:
        Black: \033[30m
        Red: \033[31m
        Green: \033[32m
        Yellow: \033[33m
        Blue: \033[34m
        Magenta: \033[35m
        Cyan: \033[36m
        White: \033[37m
         */
    }

    public void WriteLogtoConsole(String log) {
        if (Debuglevel > 2) {
            SetConsoleColor(Color.WHITE);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
            System.out.println("[" + sdf.format(cal.getTime()) + "] LOG:\033[1m " + log + "\033[22m\033[0m");
        }
    }

    public void WriteWarningtoConsole(String log) {
        if (Debuglevel > 1) {
            SetConsoleColor(Color.YELLOW);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
            System.out.println("[" + sdf.format(cal.getTime()) + "] WARNING: \033[1m" + log + "\033[22m\033[0m");
        }
    }

    public void WriteErrortoConsole(String log) {
        if (Debuglevel > 0) {
            SetConsoleColor(Color.RED);
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.S");
            System.out.println("[" + sdf.format(cal.getTime()) + "] ERROR: \033[1m" + log + "\033[22m\033[0m");
            SetConsoleColor(Color.WHITE);
        }
    }

    public JPanel GetCardManager() {
        return this.CardManager;
    }
    /*
    public void StartMplayerVideoStream() {
    try {
    String mplayerOptions = null;
    if (Settings.GetOS() == OStype.Linux) {
    //mplayerOptions = " -slave -idle -lavdopts skipframe=nonref:skiploopfilter=all -benchmark -vo x11:ck-method=auto -colorkey 0x404040 -wid " + MaincardLayout.getWinID();
    mplayerOptions = " -slave -idle -lavdopts skipframe=nonref:skiploopfilter=all -benchmark -vo xv -zoom -colorkey 0x404040";
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
     * */

    public String GetAppVersion() {
        return AppVersion;
    }

    public void start() {
        //super.start();

        if (!NoCameraParameter) {
            ReadCameraDataAnimator = new Thread(this);
            HistogramAnimator = new Thread(this);
            InfoAreaAnimator = new Thread(this);

        }
        //Init everything
        Camera = new Camera(this);
        Settings = new UserSettings();

        String osname = System.getProperty("os.name");
        if (osname.startsWith("Windows")) {
            Settings.SetOS(OStype.Windows);
        } else {
            Settings.SetOS(OStype.Linux);
        }

        /* // TODO adapt for other video players
        if (!Settings.CheckMplayerInstallation()) {
        JOptionPane.showMessageDialog(this, "Mplayer was not detected!");
        }
         */

        // global applet settings
        setSize(1024, 600);
        setBackground(Color.black);
        setLayout(new BorderLayout());

        //Create the panel that contains the "cards".
        CardManager = new JPanel(new CardLayout());

        ConnectCardLayout = new ConnectLayout(this);
        MaincardLayout = new MainLayout(this);
        Settings1CardLayout = new Settings1Layout(this);
        Settings2CardLayout = new Settings2Layout(this);
        Settings3CardLayout = new Settings3Layout(this);
        ResolutionSettingsCardLayout = new ResolutionSettings(this);
        FPSSettingsCardLayout = new FPSSettings(this);
        NumberPanel = new NumericalInputPanel(this);
        GuidesPanel = new GuidesLayout(this);
        PlaybackCardLayout = new PlaybackLayout(this);
        PhotoSettingsCardLayout = new PhotoSettingsLayout(this);

        CardManager.add(ConnectCardLayout, "ConnectCard");
        CardManager.add(MaincardLayout, "MainCard");
        CardManager.add(Settings1CardLayout, "Settings1Card");
        CardManager.add(Settings2CardLayout, "Settings2Card");
        CardManager.add(Settings3CardLayout, "Settings3Card");
        CardManager.add(ResolutionSettingsCardLayout, "CustomResolutionCard");
        CardManager.add(FPSSettingsCardLayout, "CustomFPSCard");
        CardManager.add(GuidesPanel, "GuidesCard");
        CardManager.add(NumberPanel, "Numberpanel");
        CardManager.add(PlaybackCardLayout, "PlaybackCard");
        CardManager.add(PhotoSettingsCardLayout, "PhotoSettings");

        add(CardManager);

        if (Settings.GetVideoPlayer() == VideoPlayer.GSTREAMER) {
            //GstreamerPlayer = new GstreamerPlayer(this);
        }
        if (Settings.GetVideoPlayer() == VideoPlayer.VLC) {
            VLCPlayer = new VLCPlayer(this);
        }
    }

    public void PostConnect() {
        this.WriteLogtoConsole("looking for autosave.config to read camera parameters");
        if (LoadConfigFile("autosave.config")) {
            this.WriteLogtoConsole("autosave.config loaded successfully");
        } else {
            this.WriteWarningtoConsole("autosave.config not found, falling back to default.config");
            if (LoadConfigFile("default.config")) {
                this.WriteLogtoConsole("default.config loaded successfully");
            } else {
                this.WriteErrortoConsole("default.config not found");
            }
        }

        // turn off autoexposure shutter by default
        // TODO: make chooseable
        Camera.SetAutoExposure(false);
        if (!NoCameraParameter) {
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
        }
        run();
    }

    private boolean LoadConfigFile(String Filename) {
        File configfile = new File(Filename);
        if (configfile.exists()) {
            try {
                Camera.ReadConfigFile(Filename);
                return true;
            } catch (FileNotFoundException ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    public void run() {
        if (Camera.GetCameraConnectionEstablished()) {
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

        if (Camera.GetFPSSkipFrames() != 0) {
            CameraInfo += Utils.Round(Camera.GetFPS() / (1.0f + Camera.GetFPSSkipFrames()), 3) + "fps (FS)";
        } else if (Camera.GetFPSSkipSeconds() != 0) {
            CameraInfo += Utils.Round((1.0f / Camera.GetFPSSkipSeconds()), 3) + "fps (SS)";
        } else {
            CameraInfo += Camera.GetFPS() + "fps";
        }

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
            CameraInfo += "HDD: " + Camera.GetFreeHDDRatio() + "% free";
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
