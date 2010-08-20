/*! Copyright (C) 2009 Apertus, All Rights Reserved
 *! Author : Apertus Team
 *! Description: The Camera class holds everything relevant to communicating
 *! with the camera retrieving and setting parameters, etc.
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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.regex.Pattern;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

enum CamogmState {

    NOTRUNNING, STOPPED, RECORDING
}

enum HDDState {

    UNMOUNTED, MOUNTED, FULL
}

enum ImageOrientation {

    LANDSCAPE, PORTRAIT
}

enum RecordFormat {

    MOV, OGM, JPEG
}

enum CameraParameter {

    EXPOSURE, GAIN, GAMMA, PRESET, AUTOEXP, JPEGQUAL, COLORMODE, FPS, SENSORWIDTH, SENSORHEIGHT, WHITEBALANCE, RECORDFORMAT
}

enum CameraPreset {

    FULL, AMAX, CIMAX, FULLHD, SMALLHD, CUSTOM
}

enum ColorMode {

    RGB, JP4
}

enum WhiteBalance {

    AUTO, DAYLIGHT, TUNGSTEN, CLOUDY, CUSTOM, FLOURESCENT
}

enum MirrorImage {

    NONE, VERTICAL, HORIZONTAL, VERTICALHORIZONTAL
}

enum GammaPreset {

    LINEAR, CINE1, CINE2, CINES
}

/**
 * The camera class holds all settings/parameters/states/commands of the Elphel Camera.
 */
public class Camera {

    private String IP = null;
    private URL CameraUrl = null;
    private URL CameraPingUrl = null;
    private float FPS;
    private RecordFormat RecordFormat = null;
    private float HDDSpaceFree;
    private HDDState HDDState;
    private int RecordedFrames = 0;
    private float Datarate = 0;
    private CamogmState CAMOGMState = CamogmState.NOTRUNNING;
    private int JPEGQuality;
    private int ImageWidth;
    private int ImageHeight;
    private ImageOrientation ImageOrientation;
    private CameraPreset Preset = CameraPreset.FULLHD;
    private int[][] Histogram;// = {{0, 1, 2, 3}, {1, 10, 100, 255}};
    private float Gamma;
    private int[] GammaCurve;
    private int Blacklevel;
    private boolean AutoExposure = false;
    private boolean GuideDrawCenterX = false;
    private boolean GuideDrawOuterX = false;
    private boolean GuideDrawThirds = false;
    private boolean GuideDrawSafeArea = false;
    private GammaPreset GammaPreset;
    private float ExposureTimeEV[] = {
        4,
        3.2f,
        2.5f,
        2,
        1.667f,
        1.333f,
        1,
        0.8f,
        0.6f,
        0.5f,
        0.4f,
        0.3f,
        0.25f,
        0.1667f,
        0.125f,
        0.1f,
        0.0769f,
        0.0667f,
        0.05f,
        0.04f,
        0.03333f,
        0.03f,
        0.02f,
        0.016667f,
        0.0125f,
        0.01f,
        0.008f,
        0.00625f,
        0.005f,
        0.004f,
        0.003125f,
        0.0025f,
        0.002f,
        0.0015625f,
        0.00125f,
        0.001f,
        0.0008f,
        0.000625f,
        0.0005f,
        0.0004f,
        0.0003125f,
        0.00025f,
        0.0002f,
        0.00015625f,
        0.000125f,
        0.0001f,
        0.00008f,
        0.0000625f,
        0.00005f,
        0.00004f,
        0.00003125f,
        0.000025f,
        0.00002f,
        0.000015625f,
        0.0000125f,
        0.00001f};
    private String GainNames[] = {
        "+12dB",
        "+9dB",
        "+6dB",
        "+3dB",
        "0dB"};
    private int GainIndex;
    private float Gain[] = {
        16,
        8,
        4,
        2,
        1};
    private float Gain_R = 1;
    private float Gain_G = 1;
    private float Gain_B = 1;
    private float Gain_GB = 1;
    private float WB_Factor_R = 1;
    private float WB_Factor_G = 1;
    private float WB_Factor_B = 1;
    private float WB_Factor_GB = 1;
    private String ExposureTimeEVNames[] = {
        "4",
        "3.2",
        "2.5",
        "2",
        "1.6",
        "1.3",
        "1",
        "0.8",
        "0.6",
        "0.5",
        "0.4",
        "0.3",
        "0.25",
        "1/6",
        "1/8",
        "1/10",
        "1/13",
        "1/15",
        "1/20",
        "1/25",
        "1/30",
        "1/40",
        "1/50",
        "1/60",
        "1/80",
        "1/100",
        "1/125",
        "1/160",
        "1/200",
        "1/250",
        "1/320",
        "1/400",
        "1/500",
        "1/640",
        "1/800",
        "1/1000",
        "1/1250",
        "1/1600",
        "1/2000",
        "1/2500",
        "1/3200",
        "1/4000",
        "1/5000",
        "1/6400",
        "1/8000",
        "1/10000",
        "1/12500",
        "1/16000",
        "1/20000",
        "1/25000",
        "1/32000",
        "1/40000",
        "1/50000",
        "1/64000",
        "1/80000",
        "1/100000"};
    private int ExposureIndex;
    private int JPEGQual;
    private long RecordstartTime = 0;
    private ColorMode Colormode = ColorMode.RGB;
    private WhiteBalance ImageWhiteBalance = WhiteBalance.AUTO;
    private MirrorImage ImageFlip = MirrorImage.NONE;
    private int MovieClipMaxChunkSize; // in Megabytes // Default 4 GB = 4 x 1024 x 1024 x 1024 bytes

    Camera() {
        this.ImageHeight = 0;
        this.ImageWidth = 0;
        this.FPS = 0;
        this.JPEGQuality = 0;
        this.IP = "192.168.0.9";
        this.ExposureIndex = 20;
        this.GainIndex = 4;
        this.JPEGQual = 80;
        this.Histogram = new int[3][256];
        this.GammaCurve = new int[256];
        this.ImageOrientation = ImageOrientation.LANDSCAPE;
        this.Blacklevel = 10;
        this.Gamma = 0.5f;
        this.GammaPreset = GammaPreset.LINEAR;
        this.GuideDrawCenterX = false;
        this.GuideDrawOuterX = false;
        this.GuideDrawThirds = false;
        this.GuideDrawSafeArea = false;
        this.MovieClipMaxChunkSize = 2048; // Megabytes
    }

    public void SetIP(String IP) {
        this.IP = IP;
        this.InitCameraConnection();
    }

    public String GetIP() {
        return this.IP;
    }

    public void SetMovieClipMaxChunkSize(int newchunksize) {
        long newsize = (long)newchunksize * 1024 * 1024;  // Megabytes
        this.ExecuteCommand("set_size&size=" + newsize);
    }

    public int GetMovieClipMaxChunkSize() {
        return this.MovieClipMaxChunkSize;
    }

    public void SetImageOrientation(ImageOrientation orientation) {
        this.ImageOrientation = orientation;
    }

    public ImageOrientation GetImageOrientation() {
        return this.ImageOrientation;
    }

    public MirrorImage GetImageFlipMode() {
        return this.ImageFlip;
    }

    public void SetAutoExposure(boolean OnOff) {
        this.AutoExposure = OnOff;
        if (this.AutoExposure) {
            SetParameter(CameraParameter.AUTOEXP, 1);
        } else {
            SetParameter(CameraParameter.AUTOEXP, 0);
        }
    }

    public boolean GetAutoExposure() {
        return this.AutoExposure;
    }

    public void SetGuides(boolean drawCenterX, boolean drawOuterX, boolean drawThirds, boolean drawSafeArea) {
        this.GuideDrawCenterX = drawCenterX;
        this.GuideDrawOuterX = drawOuterX;
        this.GuideDrawThirds = drawThirds;
        this.GuideDrawSafeArea = drawSafeArea;
    }

    public boolean[] GetGuides() {
        boolean[] returnval;
        returnval = new boolean[4];
        returnval[0] = this.GuideDrawCenterX;
        returnval[1] = this.GuideDrawOuterX;
        returnval[2] = this.GuideDrawThirds;
        returnval[3] = this.GuideDrawSafeArea;
        return returnval;
    }

    public void SetImageFlipMode(MirrorImage newmode) {
        this.ImageFlip = newmode;

        if (newmode == MirrorImage.NONE) {
            SendParametertoCamera("FLIPH=0&FLIPV=0");
        }
        if (newmode == MirrorImage.HORIZONTAL) {
            SendParametertoCamera("FLIPH=1&FLIPV=0");
        }
        if (newmode == MirrorImage.VERTICAL) {
            SendParametertoCamera("FLIPH=0&FLIPV=1");
        }
        if (newmode == MirrorImage.VERTICALHORIZONTAL) {
            SendParametertoCamera("FLIPH=1&FLIPV=1");
        }
    }

    public int GetImageWidth() {
        return this.ImageWidth;
    }

    public int GetImageHeight() {
        return this.ImageHeight;
    }

    public float GetGamma() {
        return this.Gamma;
    }

    public GammaPreset GetGammaPreset() {
        return this.GammaPreset;
    }

    public void SetGammaPreset(GammaPreset newpreset) {
        this.GammaPreset = newpreset;
    }

    public int GetBlacklevel() {
        return this.Blacklevel;
    }

    public void SetBlacklevel(int newblacklevel) {
        if (newblacklevel > 255) {
            newblacklevel = 255;
        }
        if (newblacklevel < 0) {
            newblacklevel = 0;
        }
        this.Blacklevel = newblacklevel;
        this.SendCamVCParameters("set=0/gam:" + this.Gamma + "/pxl:" + this.Blacklevel + "/");
    }

    public double GetRecordstartTime() {
        return this.RecordstartTime;
    }

    public int GetImageJPEGQuality() {
        return this.JPEGQuality;
    }

    public CamogmState GetCamogmState() {
        return this.CAMOGMState;
    }

    public RecordFormat GetRecordFormat() {
        return this.RecordFormat;
    }

    public float GetFreeHDDSpace() {
        return this.HDDSpaceFree;
    }

    public int GetRecordedFramesCount() {
        return this.RecordedFrames;
    }

    public float GetDatarate() {
        return this.Datarate;
    }

    public String GetExposure() {
        return this.ExposureTimeEVNames[ExposureIndex];
    }

    public int GetExposureIndex() {
        return this.ExposureIndex;
    }

    public String GetGain() {
        return this.GainNames[GainIndex];
    }

    public int GetGainIndex() {
        return this.GainIndex;
    }

    public void SetGain(float newgain) {
        float GainR, GainB, GainG, GainGB;
        GainR = newgain * 65536 * WB_Factor_R;
        GainG = newgain * 65536 * WB_Factor_G;
        GainB = newgain * 65536 * WB_Factor_B;
        GainGB = newgain * 65536 * WB_Factor_GB;
        SendParametertoCamera("framedelay=1&GAINR=" + (int) GainR + "&GAING=" + (int) GainG + "&GAINB=" + (int) GainB + "&GAINGB=" + (int) GainGB);
    }

    public void SetGainIndex(int newindex) {
        if (newindex > Gain.length - 1) {
            newindex = Gain.length - 1;
        }
        if (newindex < 0) {
            newindex = 0;
        }
        this.GainIndex = newindex;

        float GainR, GainB, GainG, GainGB;
        GainR = this.Gain[GainIndex] * 65536 * WB_Factor_R;
        GainG = this.Gain[GainIndex] * 65536 * WB_Factor_G;
        GainB = this.Gain[GainIndex] * 65536 * WB_Factor_B;
        GainGB = this.Gain[GainIndex] * 65536 * WB_Factor_GB;
        SendParametertoCamera("framedelay=3&GAINR=" + (int) GainR + "&GAING=" + (int) GainG + "&GAINB=" + (int) GainB + "&GAINGB=" + (int) GainGB);
    }

    public void SetExposure(float newexposure) {
        this.SetParameter(CameraParameter.EXPOSURE, newexposure);
    }

    public void SetExposureIndex(int newindex) {
        if (newindex > ExposureTimeEV.length - 1) {
            newindex = ExposureTimeEV.length - 1;
        }
        if (newindex < 0) {
            newindex = 0;
        }
        this.ExposureIndex = newindex;

        this.SetParameter(CameraParameter.EXPOSURE, ExposureTimeEV[ExposureIndex]);
    }

    public void SetJPEGQuality(int newquality) {
        if (newquality > 99) {
            newquality = 99;
        }
        if (newquality < 0) {
            newquality = 0;
        }

        this.SetParameter(CameraParameter.JPEGQUAL, newquality);
        this.JPEGQual = newquality;
    }

    public void SetColorMode(ColorMode Mode) {
        this.SetParameter(CameraParameter.COLORMODE, ColorModeTranslate(Mode));
        this.Colormode = Mode;
    }

    public ColorMode GetColorMode() {
        return this.Colormode;
    }

    public void SetWhiteBalance(WhiteBalance newbalance) {
        this.ImageWhiteBalance = newbalance;
        float GainR, GainB, GainG, GainGB;
        String Parameters = "";
        switch (newbalance) {
            case AUTO:
                Parameters = "WB_EN=1&framedelay=3";
                break;
            case DAYLIGHT:
                WB_Factor_R = 1.5f; // TODO just estimated badly
                WB_Factor_G = 1.0f;
                WB_Factor_B = 1.6f; // TODO just estimated badly
                WB_Factor_GB = 1.0f;
                GainR = this.Gain[GainIndex] * 65536 * WB_Factor_R;
                GainG = this.Gain[GainIndex] * 65536 * WB_Factor_G;
                GainB = this.Gain[GainIndex] * 65536 * WB_Factor_B;
                GainGB = this.Gain[GainIndex] * 65536 * WB_Factor_GB;
                Parameters = "WB_EN=0&framedelay=3&GAINR=" + (int) GainR + "&GAING=" + (int) GainG + "&GAINB=" + (int) GainB + "&GAINGB=" + (int) GainGB;
                break;
            case CLOUDY:
                WB_Factor_R = 1.4f; // TODO just estimated badly
                WB_Factor_G = 1.0f;
                WB_Factor_B = 1.4f; // TODO just estimated badly
                WB_Factor_GB = 1.0f;
                GainR = this.Gain[GainIndex] * 65536 * WB_Factor_R;
                GainG = this.Gain[GainIndex] * 65536 * WB_Factor_G;
                GainB = this.Gain[GainIndex] * 65536 * WB_Factor_B;
                GainGB = this.Gain[GainIndex] * 65536 * WB_Factor_GB;
                Parameters = "WB_EN=0&framedelay=3&GAINR=" + (int) GainR + "&GAING=" + (int) GainG + "&GAINB=" + (int) GainB + "&GAINGB=" + (int) GainGB;
                break;
            case TUNGSTEN:
                WB_Factor_R = 1.1286f; // TODO just estimated badly
                WB_Factor_G = 1.0f;
                WB_Factor_B = 2.3317f; // TODO just estimated badly
                WB_Factor_GB = 1.0f;
                GainR = this.Gain[GainIndex] * 65536 * WB_Factor_R;
                GainG = this.Gain[GainIndex] * 65536 * WB_Factor_G;
                GainB = this.Gain[GainIndex] * 65536 * WB_Factor_B;
                GainGB = this.Gain[GainIndex] * 65536 * WB_Factor_GB;
                Parameters = "WB_EN=0&framedelay=3&GAINR=" + (int) GainR + "&GAING=" + (int) GainG + "&GAINB=" + (int) GainB + "&GAINGB=" + (int) GainGB;
                break;
            case FLOURESCENT:
                WB_Factor_R = 1.2f; // TODO just estimated badly
                WB_Factor_G = 1.0f;
                WB_Factor_B = 1.5f; // TODO just estimated badly
                WB_Factor_GB = 1.0f;
                GainR = this.Gain[GainIndex] * 65536 * WB_Factor_R;
                GainG = this.Gain[GainIndex] * 65536 * WB_Factor_G;
                GainB = this.Gain[GainIndex] * 65536 * WB_Factor_B;
                GainGB = this.Gain[GainIndex] * 65536 * WB_Factor_GB;
                Parameters = "WB_EN=0&framedelay=3&GAINR=" + (int) GainR + "&GAING=" + (int) GainG + "&GAINB=" + (int) GainB + "&GAINGB=" + (int) GainGB;
                break;
            case CUSTOM:
                //TODO
                //SendParametertoCamera("WB_EN=0");
                break;
        }
        SendParametertoCamera(Parameters);
    }

    public WhiteBalance GetWhiteBalance() {
        return this.ImageWhiteBalance;
    }

    public void SetFPS(float fps) {
        this.SetParameter(CameraParameter.FPS, fps);
        this.FPS = fps;
    }

    public void SetGamma(float newgamma) {
        if (newgamma < 0) {
            newgamma = 0;
        }
        if (newgamma > 1) {
            newgamma = 1;
        }
        this.Gamma = newgamma;
        this.SendCamVCParameters("set=0/gam:" + this.Gamma + "/pxl:" + this.Blacklevel + "/");
    }

    public void SetRecordFormat(RecordFormat newformat) {
        if (newformat == RecordFormat.JPEG) {
            this.ExecuteCommand("SETCONTAINERFORMATJPEG");
        }
        if (newformat == RecordFormat.MOV) {
            this.ExecuteCommand("SETCONTAINERFORMATQUICKTIME");
        }

        this.RecordFormat = newformat;
    }

    public float GetFPS() {
        return this.FPS;
    }

    public int GetJPEGQuality() {
        return this.JPEGQual;
    }

    public int[][] GetHistogram(int color) {
        return Histogram;
    }

    public int[] GetGammaCurve() {
        return GammaCurve;
    }

    public void ReadHistogram() {

        URLConnection conn = null;
        BufferedReader data = null;
        String line;
        String result = null;
        StringBuffer buf = new StringBuffer();
        URL HistURL = null;
        int parameter = 0;

        String camera = "http://" + this.IP + "/ElphelVision/histogram.php?" + (int) (Math.random() * 32000);
        try {
            HistURL = new URL(camera);
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + HistURL);
        }

        try {
            conn = HistURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }

        try {
            String[] x = Pattern.compile(";").split(result);
            int a = 0;

            // RED
            for (int j = 0; j < 256; j++) {
                Histogram[0][j] = (int) (Integer.parseInt(x[a++]));
                //System.out.println(j + " \"" + x[j] + "\"");
                }
            // GREEN
            for (int j = 0; j < 256; j++) {
                Histogram[1][j] = (int) (Integer.parseInt(x[a++]));
                //System.out.println(j + " \"" + x[j] + "\"");
                }
            // BLUE
            for (int j = 0; j < 256; j++) {
                Histogram[2][j] = (int) (Integer.parseInt(x[a++]));
                //System.out.println(j + " \"" + x[j] + "\"");
                }
            int b = 1;
        } catch (Exception e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    public void ReadGammaCurve() {

        URLConnection conn = null;
        BufferedReader data = null;
        String line;
        String result = null;
        StringBuffer buf = new StringBuffer();
        URL GammaURL = null;
        int parameter = 0;

        String camera = "http://" + this.IP + "/ElphelVision/gamma.php?" + (int) (Math.random() * 32000);
        try {
            GammaURL = new URL(camera);
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + GammaURL);
        }

        try {
            conn = GammaURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
        String[] x = Pattern.compile(";").split(result);
        int a = 0;

        // TODO GAMMA CURVE FOR EVERY CHANNEL
        for (int j = 0; j < 256; j++) {
            GammaCurve[j] = (int) (Integer.parseInt(x[j]));
        }
    }

    public boolean InitCameraConnection() {
        boolean error = false;

        String camera_url = "http://" + this.IP + "/ElphelVision/elphelvision_interface.php";
        try {
            this.CameraUrl = new URL(camera_url);
            error = true;
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + this.CameraUrl);
            error = false;
        }

        String Camera_Ping_Url = "http://" + this.IP + "/ElphelVision/ping.php";
        try {
            this.CameraPingUrl = new URL(Camera_Ping_Url);
            error = true;
        } catch (MalformedURLException e) {
            System.out.println("Bad URL: " + this.CameraPingUrl);
            error = false;
        }
        return error;
    }

    public boolean InitCameraServices() {
        URLConnection conn = null;
        BufferedReader data = null;
        String line;
        String result;
        StringBuffer buf = new StringBuffer();

        // try to connect
        try {
            conn = this.CameraUrl.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();

            // try to extract data from XML structure
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(new ByteArrayInputStream(result.getBytes()));
                doc.getDocumentElement().normalize();
                NodeList nodeLst = doc.getElementsByTagName("elphel_vision_data");
                for (int s = 0; s < nodeLst.getLength(); s++) {
                    Node fstNode = nodeLst.item(s);
                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element fstElmnt = (Element) fstNode;
                        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("camogm");

                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        if ((((Node) fstNm.item(0)).getNodeValue().compareTo("not running")) == 0) {
                            this.CAMOGMState = CamogmState.NOTRUNNING;
                        } else {
                            NodeList NmElmntLst5 = fstElmnt.getElementsByTagName("camogm_state");
                            Element NmElmnt5 = (Element) NmElmntLst5.item(0);
                            NodeList Elmnt5 = NmElmnt5.getChildNodes();
                            if (((Node) Elmnt5.item(0)) != null) {
                                if (((Node) Elmnt5.item(0)).getNodeValue().compareTo("stopped") != 0) {
                                    this.CAMOGMState = CamogmState.STOPPED;
                                }
                                if (((Node) Elmnt5.item(0)).getNodeValue().compareTo("running") != 0) {
                                    this.CAMOGMState = CamogmState.RECORDING;
                                }
                                NodeList NmElmntLst6 = fstElmnt.getElementsByTagName("hdd_freespaceratio");
                                Element NmElmnt6 = (Element) NmElmntLst6.item(0);
                                NodeList Elmnt6 = NmElmnt6.getChildNodes();
                                if (((Node) Elmnt6.item(0)).getNodeValue().compareTo("unmounted") == 0) {
                                    this.HDDState = HDDState.UNMOUNTED;
                                } else {
                                    this.HDDState = HDDState.MOUNTED;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }

        if (this.CAMOGMState == CamogmState.NOTRUNNING) {
            this.ExecuteCommand("CAMOGMSTART");
            return false;
        }
        if (this.HDDState == HDDState.UNMOUNTED) {
            this.ExecuteCommand("MOUNTHDD");
            this.ExecuteCommand("SETRECDIR");
            this.ExecuteCommand("SETCONTAINERFORMATQUICKTIME");
            return true; // ElphelVision should also work with cameras without HDD
        } else {
            return true;
        }
    }

    public void SetParameter(CameraParameter par, float value) {
        switch (par) {
            case EXPOSURE:
                this.SendParameter(CameraParameter.EXPOSURE, value);
                break;
            case GAIN:
                this.SendParameter(CameraParameter.GAIN, value);
                break;
            case AUTOEXP:
                this.SendParameter(CameraParameter.AUTOEXP, value);
                break;
            case JPEGQUAL:
                this.SendParameter(CameraParameter.JPEGQUAL, value);
                break;
            case COLORMODE:
                this.SendParameter(CameraParameter.COLORMODE, value);
                break;
            case FPS:
                this.SendParameter(CameraParameter.FPS, value);
                break;
            case SENSORWIDTH:
                this.SendParameter(CameraParameter.SENSORWIDTH, value);
                break;
            case SENSORHEIGHT:
                this.SendParameter(CameraParameter.SENSORHEIGHT, value);
                break;
            case RECORDFORMAT:
                this.SendParameter(CameraParameter.RECORDFORMAT, value);
                break;
        }
    }

    public CameraPreset GetPreset() {
        return this.Preset;
    }

    public void SetPreset(CameraPreset preset) {
        this.Preset = preset;

        // Dont apply any settings if its a custom preset
        if (preset == CameraPreset.CUSTOM) {
            return;
        }

        try {
            String param_url = "";
            URLConnection conn = null;
            BufferedReader data = null;
            String line;

            String result;

            StringBuffer buf = new StringBuffer();
            URL ParamURL = null;
            int parameter = 0;
            int woi_left = 0;
            int woi_top = 0;
            int width = 0;
            int height = 0;
            int fps = 0;
            int binning = 0;

            switch (preset) {
                case FULL:
                    // 2592x1936
                    woi_left = 0;
                    woi_top = 0;
                    width = 2592;
                    height = 1936;
                    binning = 1;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_LEFT=" + woi_left + "&WOI_TOP=" + woi_top + "&WOI_WIDTH=" + width;
                    param_url += "&WOI_HEIGHT=" + height + "&DCM_HOR=" + binning + "&DCM_VERT=" + binning + "&BIN_HOR=" + binning + "&BIN_VERT=" + binning;
                    param_url += "&framedelay=3";
                    break;
                case AMAX:
                    // 2224x1251
                    woi_left = 184;
                    woi_top = 340;
                    width = 2224;
                    height = 1264;
                    binning = 1;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_LEFT=" + woi_left + "&WOI_TOP=" + woi_top + "&WOI_WIDTH=" + width;
                    param_url += "&WOI_HEIGHT=" + height + "&DCM_HOR=" + binning + "&DCM_VERT=" + binning + "&BIN_HOR=" + binning + "&BIN_VERT=" + binning;
                    param_url += "&framedelay=3";
                    break;
                case CIMAX:
                    // 2592x1120
                    woi_left = 0;
                    woi_top = 416;
                    width = 2592;
                    height = 1120;
                    binning = 1;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_LEFT=" + woi_left + "&WOI_TOP=" + woi_top + "&WOI_WIDTH=" + width;
                    param_url += "&WOI_HEIGHT=" + height + "&DCM_HOR=" + binning + "&DCM_VERT=" + binning + "&BIN_HOR=" + binning + "&BIN_VERT=" + binning;
                    param_url += "&framedelay=3";
                    break;
                case FULLHD:
                    woi_left = 336;
                    woi_top = 442;
                    width = 1920;
                    height = 1088;
                    binning = 1;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_LEFT=" + woi_left + "&WOI_TOP=" + woi_top + "&WOI_WIDTH=" + width;
                    param_url += "&WOI_HEIGHT=" + height + "&DCM_HOR=" + binning + "&DCM_VERT=" + binning + "&BIN_HOR=" + binning + "&BIN_VERT=" + binning;
                    param_url += "&framedelay=3";
                    break;
                case SMALLHD:
                    woi_left = 656;
                    woi_top = 612;
                    width = 1280;
                    height = 720;
                    binning = 1;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_LEFT=" + woi_left + "&WOI_TOP=" + woi_top + "&WOI_WIDTH=" + width;
                    param_url += "&WOI_HEIGHT=" + height + "&DCM_HOR=" + binning + "&DCM_VERT=" + binning + "&BIN_HOR=" + binning + "&BIN_VERT=" + binning;
                    param_url += "&framedelay=3";
                    break;
                case CUSTOM:
                    woi_left = 16;
                    woi_top = 252;
                    width = 2560;
                    height = 1440;
                    binning = 1;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_LEFT=" + woi_left + "&WOI_TOP=" + woi_top + "&WOI_WIDTH=" + width;
                    param_url += "&WOI_HEIGHT=" + height + "&DCM_HOR=" + binning + "&DCM_VERT=" + binning + "&BIN_HOR=" + binning + "&BIN_VERT=" + binning;
                    param_url += "&framedelay=3";
                    break;
            }

            try {
                ParamURL = new URL(param_url);
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + param_url);
            }

            conn = ParamURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    public void CheckHDD() {
        if (this.HDDSpaceFree == -1) {
            String message = "No HDD detected, video recording disabled";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void WriteConfigFile(String FileName) throws IOException {
        File ConfigFile = new File(FileName);

        if (FileName == null) {
            throw new IllegalArgumentException("File should not be null.");
        }

        //use buffering
        Writer output = new BufferedWriter(new FileWriter(ConfigFile));
        try {
            //FileWriter always assumes default encoding is OK!
            String line = "";
            line += "IP=" + this.GetIP() + "\n";
            line += "ImageWidth=" + Integer.toString(this.GetImageWidth()) + "\n";
            line += "ImageHeight=" + Integer.toString(this.GetImageHeight()) + "\n";
            line += "Preset=";
            if (this.GetPreset() == Preset.FULL) {
                line += "FULL";
            }
            if (this.GetPreset() == Preset.AMAX) {
                line += "AMAX";
            }
            if (this.GetPreset() == Preset.CIMAX) {
                line += "CIMAX";
            }
            if (this.GetPreset() == Preset.FULLHD) {
                line += "FULLHD";
            }
            if (this.GetPreset() == Preset.SMALLHD) {
                line += "SMALLHD";
            }
            line += "\n";
            line += "FPS=" + Float.toString(this.GetFPS()) + "\n";
            line += "JPEGQuality=" + Integer.toString(this.GetJPEGQuality()) + "\n";
            line += "ColorMode=";
            if (this.GetColorMode() == ColorMode.RGB) {
                line += "RGB";
            } else if (this.GetColorMode() == ColorMode.JP4) {
                line += "JP4";
            }
            line += "\n";
            line += "Exposure=" + ExposureTimeEV[this.GetExposureIndex()] + "\n";
            line += "AutoExposure=" + Boolean.toString(this.GetAutoExposure()) + "\n";
            line += "RecordFormat=" + this.GetRecordFormat() + "\n";
            line += "Gain=" + Float.toString(Gain[this.GetGainIndex()]) + "\n";
            line += "WB=";
            if (this.GetWhiteBalance() == WhiteBalance.AUTO) {
                line += "AUTO";
            } else if (this.GetWhiteBalance() == WhiteBalance.CLOUDY) {
                line += "CLOUDY";
            } else if (this.GetWhiteBalance() == WhiteBalance.CUSTOM) {
                line += "CUSTOM";
            } else if (this.GetWhiteBalance() == WhiteBalance.DAYLIGHT) {
                line += "DAYLIGHT";
            } else if (this.GetWhiteBalance() == WhiteBalance.FLOURESCENT) {
                line += "FLOURESCENT";
            } else if (this.GetWhiteBalance() == WhiteBalance.TUNGSTEN) {
                line += "TUNGSTEN";
            }
            line += "\n";
            line += "WB_Factor_R=" + Float.toString(this.WB_Factor_R) + "\n";
            line += "WB_Factor_G=" + Float.toString(this.WB_Factor_G) + "\n";
            line += "WB_Factor_B=" + Float.toString(this.WB_Factor_B) + "\n";
            line += "WB_Factor_GB=" + Float.toString(this.WB_Factor_GB) + "\n";
            line += "MovieMaxChunkSize=" + Integer.toString(this.GetMovieClipMaxChunkSize()) + "\n";
            line += "ImageOrientation=";
            if (this.GetImageOrientation() == ImageOrientation.LANDSCAPE) {
                line += "LANDSCAPE";
            } else if (this.GetImageOrientation() == ImageOrientation.PORTRAIT) {
                line += "PORTRAIT";
            }
            line += "\n";
            line += "ImageFlip=";
            if (this.GetImageFlipMode() == MirrorImage.NONE) {
                line += "NONE";
            } else if (this.GetImageFlipMode() == MirrorImage.HORIZONTAL) {
                line += "HORIZONTAL";
            } else if (this.GetImageFlipMode() == MirrorImage.VERTICAL) {
                line += "VERTICAL";
            } else if (this.GetImageFlipMode() == MirrorImage.VERTICALHORIZONTAL) {
                line += "VERTICALHORIZONTAL";
            }
            line += "\n";
            line += "RecordFormat=";
            if (this.GetRecordFormat() == RecordFormat.JPEG) {
                line += "JPEG";
            } else if (this.GetRecordFormat() == RecordFormat.MOV) {
                line += "MOV";
            } else if (this.GetRecordFormat() == RecordFormat.OGM) {
                line += "OGM";
            }
            line += "\n";
            line += "Gamma=" + Float.toString(this.GetGamma()) + "\n";
            line += "Gammapreset=";
            if (this.GetGammaPreset() == GammaPreset.LINEAR) {
                line += "LINEAR";
            } else if (this.GetGammaPreset() == GammaPreset.CINE1) {
                line += "CINE1";
            } else if (this.GetGammaPreset() == GammaPreset.CINE2) {
                line += "CINE2";
            } else if (this.GetGammaPreset() == GammaPreset.CINES) {
                line += "CINES";
            }
            line += "\n";
            line += "Blacklevel=" + Integer.toString(this.GetBlacklevel()) + "\n";
            output.write(line);
        } finally {
            output.close();
        }
    }

    public String ReadConfigFileIP(String FileName) throws FileNotFoundException {
        File ConfigFile = new File(FileName);
        String RetValue = null;

        Scanner scanner1 = new Scanner(ConfigFile);
        try {
            //first use a Scanner to get each line
            while (scanner1.hasNextLine()) {
                //use a second Scanner to parse the content of each line
                Scanner scanner2 = new Scanner(scanner1.nextLine());
                scanner2.useDelimiter("=");
                if (scanner2.hasNext()) {
                    String name = scanner2.next();
                    if (!scanner2.hasNext()) {
                        break;
                    }
                    String value = scanner2.next();
                    if (name.trim().equals("IP")) {
                        RetValue = value.trim();
                    } else {
                        //Empty or invalid line. Unable to process
                    }
                    scanner2.close();
                }
            }
        } finally {
            //ensure the underlying stream is always closed
            scanner1.close();
        }
        return RetValue;
    }

    public void ReadConfigFile(String FileName) throws FileNotFoundException {
        File ConfigFile = new File(FileName);

        Scanner scanner1 = new Scanner(ConfigFile);
        try {
            //first use a Scanner to get each line
            while (scanner1.hasNextLine()) {
                //use a second Scanner to parse the content of each line
                Scanner scanner2 = new Scanner(scanner1.nextLine());
                scanner2.useDelimiter("=");
                if (scanner2.hasNext()) {
                    String name = scanner2.next();
                    if (!scanner2.hasNext()) {
                        break;
                    }
                    String value = scanner2.next();
                    if (name.trim().equals("IP")) {
                        this.SetIP(value.trim()); // TODO: autoconnect
                    }
                    if (name.trim().equals("ImageWidth")) {
                        this.ImageWidth = Integer.parseInt(value.trim());
                    }
                    if (name.trim().equals("ImageHeight")) {
                        this.ImageHeight = Integer.parseInt(value.trim());
                    }
                    if (name.trim().equals("JPEGQuality")) {
                        this.SetJPEGQuality(Integer.parseInt(value.trim()));
                    }
                    if (name.trim().equals("FPS")) {
                        this.SetFPS(Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("Preset")) {
                        if (value.trim().contentEquals("AMAX")) {
                            this.SetPreset(Preset.AMAX);
                        }
                        if (value.trim().contentEquals("CIMAX")) {
                            this.SetPreset(Preset.CIMAX);
                        }
                        if (value.trim().contentEquals("FULLHD")) {
                            this.SetPreset(Preset.FULLHD);
                        }
                        if (value.trim().contentEquals("SMALLHD")) {
                            this.SetPreset(Preset.SMALLHD);
                        }
                        if (value.trim().contentEquals("CUSTOM")) {
                            this.SetPreset(Preset.CUSTOM);
                        }
                    }
                    if (name.trim().equals("ColorMode")) {
                        if (value.trim().contentEquals("RGB")) {
                            this.SetColorMode(ColorMode.RGB);
                        }
                        if ((value.trim()).contentEquals("JP4")) {
                            this.SetColorMode(ColorMode.JP4);
                        }
                    }
                    if (name.trim().equals("Exposure")) {
                        this.SetExposure(Float.parseFloat(value.trim()));
                    }

                    if ((name.trim()).equals("RecordFormat")) {
                        if (value.trim().contentEquals("MOV")) {
                            this.SetRecordFormat(RecordFormat.MOV);
                        }
                        if (value.trim().contentEquals("OGM")) {
                            this.SetRecordFormat(RecordFormat.OGM);
                        }
                        if (value.trim().contentEquals("JPEG")) {
                            this.SetRecordFormat(RecordFormat.JPEG);
                        }
                    }
                    if ((name.trim()).equals("WB")) {
                        if (value.trim().contentEquals("AUTO")) {
                            this.SetWhiteBalance(WhiteBalance.AUTO);
                        }
                        if (value.trim().contentEquals("CLOUDY")) {
                            this.SetWhiteBalance(WhiteBalance.CLOUDY);
                        }
                        if (value.trim().contentEquals("CUSTOM")) {
                            this.SetWhiteBalance(WhiteBalance.CUSTOM);
                        }
                        if (value.trim().contentEquals("DAYLIGHT")) {
                            this.SetWhiteBalance(WhiteBalance.DAYLIGHT);
                        }
                        if (value.trim().contentEquals("FLOURESCENT")) {
                            this.SetWhiteBalance(WhiteBalance.FLOURESCENT);
                        }
                        if (value.trim().contentEquals("TUNGSTEN")) {
                            this.SetWhiteBalance(WhiteBalance.TUNGSTEN);
                        }
                    }
                    if ((name.trim()).equals("ImageOrientation")) {
                        if (value.trim().contentEquals("LANDSCAPE")) {
                            this.SetImageOrientation(ImageOrientation.LANDSCAPE);
                        }
                        if (value.trim().contentEquals("PORTRAIT")) {
                            this.SetImageOrientation(ImageOrientation.PORTRAIT);
                        }
                    }
                    if ((name.trim()).equals("ImageFlip")) {
                        if (value.trim().contentEquals("NONE")) {
                            this.SetImageFlipMode(MirrorImage.NONE);
                        }
                        if (value.trim().contentEquals("HORIZONTAL")) {
                            this.SetImageFlipMode(MirrorImage.HORIZONTAL);
                        }
                        if (value.trim().contentEquals("VERTICAL")) {
                            this.SetImageFlipMode(MirrorImage.VERTICAL);
                        }
                        if (value.trim().contentEquals("VERTICALHORIZONTAL")) {
                            this.SetImageFlipMode(MirrorImage.VERTICALHORIZONTAL);
                        }
                    }
                    if (name.trim().equals("Gain")) {
                        this.SetGain(Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("WB_Factor_R")) {
                        this.WB_Factor_R = (Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("WB_Factor_G")) {
                        this.WB_Factor_G = (Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("WB_Factor_B")) {
                        this.WB_Factor_B = (Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("WB_Factor_GB")) {
                        this.WB_Factor_GB = (Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("Gamma")) {
                        this.Gamma = (Float.parseFloat(value.trim()));
                    }
                    if (name.trim().equals("Blacklevel")) {
                        this.Blacklevel = (Integer.parseInt(value.trim()));
                    }
                    if (name.trim().equals("MovieMaxChunkSize")) {
                        this.MovieClipMaxChunkSize = (Integer.parseInt(value.trim()));
                    }

                } else {
                    //Empty or invalid line. Unable to process
                }
                scanner2.close();
            }
        } finally {
            //ensure the underlying stream is always closed
            scanner1.close();
        }
    }

    private void SendParameter(CameraParameter par, float value) {
        try {
            String param_url = "";
            URLConnection conn = null;
            BufferedReader data = null;
            String line;

            String result;

            StringBuffer buf = new StringBuffer();
            URL ParamURL = null;
            int parameter = 0;
            int margin = 0;

            switch (par) {
                case EXPOSURE:
                    parameter = (int) (value * 1000000);
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?framedelay=1&EXPOS=" + parameter;
                    break;

                case AUTOEXP:
                    parameter = (int) value;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?framedelay=1&AUTOEXP_ON=" + parameter;
                    break;

                case JPEGQUAL:
                    parameter = (int) value;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?framedelay=3&QUALITY=" + parameter;
                    break;

                case COLORMODE:
                    parameter = (int) value;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?framedelay=3&COLOR=" + parameter;
                    break;

                case FPS:
                    float fps_parameter = value * 1000;
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?framedelay=3&FPSFLAGS=1&FP1000SLIM=" + fps_parameter;
                    break;

                case SENSORHEIGHT:
                    margin = Math.round(1936 / 2 - value / 2);
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_HEIGHT=" + value + "WOI_TOP=" + margin;
                    break;

                case SENSORWIDTH:
                    margin = Math.round(2592 / 2 - value / 2);
                    param_url = "http://" + this.IP + "/ElphelVision/setparam.php?WOI_WIDTH=" + value + "WOI_LEFT=" + margin;
                    break;
            }
            try {
                ParamURL = new URL(param_url);
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + param_url);
            }

            conn = ParamURL.openConnection();
            conn.connect();

            data =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    private void SendCamVCParameters(String UrlParameter) {
        try {
            String param_url = "";
            URLConnection conn = null;
            BufferedReader data = null;
            String line;

            String result;

            StringBuffer buf = new StringBuffer();
            URL ParamURL = null;
            int parameter = 0;
            int margin = 0;

            param_url = "http://" + this.IP + "/camvc.php?" + UrlParameter;

            try {
                ParamURL = new URL(param_url);
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + param_url);
            }

            conn = ParamURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    private void SendParametertoCamera(String UrlParameter) {
        try {
            String param_url = "";
            URLConnection conn = null;
            BufferedReader data = null;
            String line;

            String result;

            StringBuffer buf = new StringBuffer();
            URL ParamURL = null;
            int parameter = 0;
            int margin = 0;

            param_url = "http://" + this.IP + "/ElphelVision/setparam.php?" + UrlParameter;

            try {
                ParamURL = new URL(param_url);
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + param_url);
            }

            conn = ParamURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    public String CaptureStillImage(String UrlParameter) {
        String ReturnValue = "";

        try {
            String param_url = "";
            URLConnection conn = null;
            BufferedReader data = null;
            String line;
            String result;

            StringBuffer buf = new StringBuffer();
            URL ParamURL = null;

            param_url = "http://" + this.IP + "/ElphelVision/savestill.php?" + UrlParameter;

            try {
                ParamURL = new URL(param_url);
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + param_url);
            }

            conn = ParamURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();

            // try to extract data from XML structure
            /*
            <SaveStill>
            <path>/var/hdd/stills/still_00034.jpg</path>
            <size>50390</size>
            <save_duration>0.343< </save_duration>
            </SaveStill>
             * */
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(new ByteArrayInputStream(result.getBytes()));
                doc.getDocumentElement().normalize();
                NodeList nodeLst = doc.getElementsByTagName("SaveStill");
                for (int s = 0; s < nodeLst.getLength(); s++) {
                    Node fstNode = nodeLst.item(s);
                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element fstElmnt = (Element) fstNode;
                        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("path");

                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        String response = ((Node) fstNm.item(0)).getNodeValue();

                        ReturnValue = "saved " + response;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
        return ReturnValue;
    }

    private int ColorModeTranslate(ColorMode mode) {
        int colormode = 1;
        switch (mode) {
            case RGB:
                colormode = 1;
                break;

            case JP4:
                colormode = 3;
                break;

        }
        return colormode;
    }

    public void ExecuteCommand(String Command) {
        URLConnection conn = null;
        BufferedReader data = null;
        String line;

        String result;

        StringBuffer buf = new StringBuffer();
        URL CommandURL = null;
        String command_name = "";

        if (Command.equals("CAMOGMSTART")) {
            command_name = "run_camogm";
        } else if (Command.equals("RECORDSTART")) {
            command_name = "start";
            Calendar now = Calendar.getInstance();
            RecordstartTime = now.getTimeInMillis();
        } else if (Command.equals("RECORDSTOP")) {
            command_name = "stop";
        } else if (Command.equals("MOUNTHDD")) {
            command_name = "mount";
        } else if (Command.equals("SETRECDIR")) {
            command_name = "set_prefix&prefix=/var/hdd/";
        } else if (Command.equals("SETCONTAINERFORMATQUICKTIME")) {
            command_name = "setmov";
        } else if (Command.equals("SETCONTAINERFORMATJPEG")) {
            command_name = "setjpeg";
        } else {
            command_name = Command;
        }

        // try to connect
        try {
            String command_url = "http://" + this.IP + "/camogmgui/camogm_interface.php?cmd=" + command_name;
            try {
                CommandURL = new URL(command_url);
            } catch (MalformedURLException e) {
                System.out.println("Bad URL: " + command_url);
            }

            conn = CommandURL.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();
        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }

    public boolean PingCamera() {
        URLConnection conn = null;
        BufferedReader data = null;
        String line;

        String result;

        StringBuffer buf = new StringBuffer();

        // try to connect
        try {
            conn = this.CameraPingUrl.openConnection();
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            conn.connect();

            data =
                    new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();

            // try to extract data from XML structure
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(new ByteArrayInputStream(result.getBytes()));
                doc.getDocumentElement().normalize();
                NodeList nodeLst = doc.getElementsByTagName("response");
                for (int s = 0; s <
                        nodeLst.getLength(); s++) {
                    Node fstNode = nodeLst.item(s);
                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element fstElmnt = (Element) fstNode;
                        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("ping");

                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        String response = ((Node) fstNm.item(0)).getNodeValue();
                        if (response.compareTo("\"pong\"") != 0) {
                            return true;
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
            return false;
        }

        return false;
    }

    public void UpdateCameraData() throws Exception {
        URLConnection conn = null;
        BufferedReader data = null;
        String line;

        String result;

        StringBuffer buf = new StringBuffer();

        // try to connect
        try {
            conn = CameraUrl.openConnection();
            conn.connect();

            data = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            buf.delete(0, buf.length());
            while ((line = data.readLine()) != null) {
                buf.append(line + "\n");
            }

            result = buf.toString();
            data.close();

            // try to extract data from XML structure
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(new ByteArrayInputStream(result.getBytes()));
                doc.getDocumentElement().normalize();
                NodeList nodeLst = doc.getElementsByTagName("elphel_vision_data");
                for (int s = 0; s <
                        nodeLst.getLength(); s++) {
                    Node fstNode = nodeLst.item(s);
                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element fstElmnt = (Element) fstNode;
                        NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("image_width");

                        Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                        NodeList fstNm = fstNmElmnt.getChildNodes();
                        this.ImageWidth = Integer.parseInt(((Node) fstNm.item(0)).getNodeValue());

                        NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("image_height");
                        Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                        NodeList lstNm = lstNmElmnt.getChildNodes();
                        this.ImageHeight = Integer.parseInt(((Node) lstNm.item(0)).getNodeValue());

                        NodeList nxtNmElmntLst = fstElmnt.getElementsByTagName("fps");
                        Element nxtNmElmnt = (Element) nxtNmElmntLst.item(0);
                        NodeList nxtNm = nxtNmElmnt.getChildNodes();
                        this.FPS = Float.parseFloat(((Node) nxtNm.item(0)).getNodeValue()) / 1000.0f;

                        NodeList NmElmntLst4 = fstElmnt.getElementsByTagName("jpeg_quality");
                        Element NmElmnt4 = (Element) NmElmntLst4.item(0);
                        NodeList Elmnt4 = NmElmnt4.getChildNodes();
                        this.JPEGQuality = Integer.parseInt(((Node) Elmnt4.item(0)).getNodeValue());

                        NodeList fstNmElmntLst1 = fstElmnt.getElementsByTagName("camogm");
                        Element fstNmElmnt1 = (Element) fstNmElmntLst1.item(0);
                        NodeList fstNm1 = fstNmElmnt1.getChildNodes();
                        if ((((Node) fstNm.item(0)).getNodeValue().compareTo("not running")) == 0) {
                            this.CAMOGMState = CamogmState.NOTRUNNING;
                        } else {
                            NodeList NmElmntLst11 = fstElmnt.getElementsByTagName("camogm_state");
                            Element NmElmnt11 = (Element) NmElmntLst11.item(0);
                            NodeList Elmnt11 = NmElmnt11.getChildNodes();
                            if (((Node) Elmnt11.item(0)) != null) {
                                if (((Node) Elmnt11.item(0)).getNodeValue().startsWith("stopped")) {
                                    this.CAMOGMState = CamogmState.STOPPED;
                                }

                                if (((Node) Elmnt11.item(0)).getNodeValue().startsWith("running")) {
                                    this.CAMOGMState = CamogmState.RECORDING;
                                }

                            }
                        }

                        NodeList NmElmntLst5 = fstElmnt.getElementsByTagName("camogm_format");
                        Element NmElmnt5 = (Element) NmElmntLst5.item(0);
                        NodeList Elmnt5 = NmElmnt5.getChildNodes();
                        if (((Node) Elmnt5.item(0)) != null) {
                            String formatname = ((Node) Elmnt5.item(0)).getNodeValue();
                            if (formatname.startsWith("mov")) {
                                this.RecordFormat = RecordFormat.MOV;
                            } else if (formatname.startsWith("ogm")) {
                                this.RecordFormat = RecordFormat.OGM;
                            } else if (formatname.startsWith("jpeg")) {
                                this.RecordFormat = RecordFormat.JPEG;
                            }

                        }

                        NodeList NmElmntLst6 = fstElmnt.getElementsByTagName("hdd_freespaceratio");
                        Element NmElmnt6 = (Element) NmElmntLst6.item(0);
                        NodeList Elmnt6 = NmElmnt6.getChildNodes();
                        if (((Node) Elmnt6.item(0)).getNodeValue().startsWith("unmounted")) {
                            this.HDDSpaceFree = -1;
                        } else {
                            try {
                                this.HDDSpaceFree = Float.parseFloat(((Node) Elmnt6.item(0)).getNodeValue());
                            } catch (Exception e) {
                                this.HDDSpaceFree = -1;
                            }

                        }

                        NodeList NmElmntLst7 = fstElmnt.getElementsByTagName("camogm_fileframeduration");
                        Element NmElmnt7 = (Element) NmElmntLst7.item(0);
                        NodeList Elmnt7 = NmElmnt7.getChildNodes();
                        if (((Node) Elmnt7.item(0)) != null) {
                            this.RecordedFrames = Integer.parseInt(((Node) Elmnt7.item(0)).getNodeValue());
                        }

                        NodeList NmElmntLstDatarate = fstElmnt.getElementsByTagName("camogm_datarate");
                        Element NmElmntDatarate = (Element) NmElmntLstDatarate.item(0);
                        NodeList ElmntDatarate = NmElmntDatarate.getChildNodes();
                        if (((Node) ElmntDatarate.item(0)) != null) {
                            this.Datarate = Float.parseFloat(((Node) ElmntDatarate.item(0)).getNodeValue());
                        }

                        NodeList NmElmntLstGainR = fstElmnt.getElementsByTagName("gain_r");
                        Element NmElmntGainR = (Element) NmElmntLstGainR.item(0);
                        NodeList ElmntGainR = NmElmntGainR.getChildNodes();
                        if (((Node) ElmntGainR.item(0)) != null) {
                            this.Gain_R = Integer.parseInt(((Node) ElmntGainR.item(0)).getNodeValue()) / 65536.0f;
                        }

                        NodeList NmElmntLstGainB = fstElmnt.getElementsByTagName("gain_b");
                        Element NmElmntGainB = (Element) NmElmntLstGainB.item(0);
                        NodeList ElmntGainB = NmElmntGainB.getChildNodes();
                        if (((Node) ElmntGainB.item(0)) != null) {
                            this.Gain_B = Integer.parseInt(((Node) ElmntGainB.item(0)).getNodeValue()) / 65536.0f;
                        }

                        NodeList NmElmntLstGainG = fstElmnt.getElementsByTagName("gain_g");
                        Element NmElmntGainG = (Element) NmElmntLstGainG.item(0);
                        NodeList ElmntGainG = NmElmntGainG.getChildNodes();
                        if (((Node) ElmntGainG.item(0)) != null) {
                            this.Gain_G = Integer.parseInt(((Node) ElmntGainG.item(0)).getNodeValue()) / 65536.0f;
                            if (this.Gain_G == 1.0f) {
                                this.GainIndex = 4;
                            }

                            if (this.Gain_G == 2.0f) {
                                this.GainIndex = 3;
                            }

                            if (this.Gain_G == 4.0f) {
                                this.GainIndex = 2;
                            }

                            if (this.Gain_G == 8.0f) {
                                this.GainIndex = 1;
                            }

                            if (this.Gain_G == 16.0f) {
                                this.GainIndex = 0;
                            }

                        }

                        NodeList NmElmntLstGainGB = fstElmnt.getElementsByTagName("gain_gb");
                        Element NmElmntGainGB = (Element) NmElmntLstGainGB.item(0);
                        NodeList ElmntGainGB = NmElmntGainGB.getChildNodes();
                        if (((Node) ElmntGainGB.item(0)) != null) {
                            this.Gain_GB = Integer.parseInt(((Node) ElmntGainGB.item(0)).getNodeValue()) / 65536.0f;
                        }

                        NodeList NmElmntLstCamOGMMaxDuration = fstElmnt.getElementsByTagName("camogm_max_duration"); // seconds
                        Element NmElmntCamOGMMaxDuration = (Element) NmElmntLstCamOGMMaxDuration.item(0);
                        NodeList ElmntCamOGMMaxDuration = NmElmntCamOGMMaxDuration.getChildNodes();
                        if (((Node) ElmntCamOGMMaxDuration.item(0)) != null) {
                            // TODO
                        }

                        NodeList NmElmntLstCamOGMMaxLength = fstElmnt.getElementsByTagName("camogm_max_length"); // bytes
                        Element NmElmntCamOGMMaxLength = (Element) NmElmntLstCamOGMMaxLength.item(0);
                        NodeList ElmntCamOGMMaxLength = NmElmntCamOGMMaxLength.getChildNodes();
                        if (((Node) ElmntCamOGMMaxLength.item(0)) != null) {
                            this.MovieClipMaxChunkSize = Integer.parseInt(((Node) ElmntCamOGMMaxLength.item(0)).getNodeValue()) / 1024 / 1024;
                        }

                        NodeList NmElmntLstCamOGMMaxFrames = fstElmnt.getElementsByTagName("camogm_max_frames"); // frames
                        Element NmElmntCamOGMMaxFrames = (Element) NmElmntLstCamOGMMaxFrames.item(0);
                        NodeList ElmntCamOGMMaxFrames = NmElmntCamOGMMaxFrames.getChildNodes();
                        if (((Node) ElmntCamOGMMaxFrames.item(0)) != null) {
                            // TODO
                        }

                        NodeList NmElmntLst9 = fstElmnt.getElementsByTagName("exposure");
                        Element NmElmnt9 = (Element) NmElmntLst9.item(0);
                        NodeList Elmnt9 = NmElmnt9.getChildNodes();
                        if (((Node) Elmnt9.item(0)) != null) {
                            // Todo
                            //Integer.parseInt(((Node) Elmnt9.item(0)).getNodeValue());
                        }

                        NodeList NmElmntLstFlipH = fstElmnt.getElementsByTagName("fliph");
                        Element NmElmntFlipH = (Element) NmElmntLstFlipH.item(0);
                        NodeList ElmntFlipH = NmElmntFlipH.getChildNodes();
                        NodeList NmElmntLstFlipV = fstElmnt.getElementsByTagName("flipv");
                        Element NmElmntFlipV = (Element) NmElmntLstFlipV.item(0);
                        NodeList ElmntFlipV = NmElmntFlipV.getChildNodes();
                        if (((Node) ElmntFlipV.item(0)) != null) {
                            int flipv = Integer.parseInt(((Node) ElmntFlipV.item(0)).getNodeValue());
                            int fliph = Integer.parseInt(((Node) ElmntFlipH.item(0)).getNodeValue());
                            if ((flipv == 1) && (fliph == 1)) {
                                this.ImageFlip = MirrorImage.VERTICALHORIZONTAL;
                            }

                            if ((flipv == 1) && (fliph == 0)) {
                                this.ImageFlip = MirrorImage.VERTICAL;
                            }

                            if ((flipv == 0) && (fliph == 1)) {
                                this.ImageFlip = MirrorImage.HORIZONTAL;
                            }

                            if ((flipv == 0) && (fliph == 0)) {
                                this.ImageFlip = MirrorImage.NONE;
                            }

                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("IO Error:" + e.getMessage());
        }
    }
}
