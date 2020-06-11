package jp.jaxa.iss.kibo.rpc.defaultapk;

import android.graphics.Bitmap;
import android.util.Log;


import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import org.opencv.aruco.Aruco;
import org.opencv.aruco.Dictionary;
import org.opencv.core.Mat;

import java.util.ArrayList;
import java.util.List;



import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;

import static android.content.ContentValues.TAG;

public class YourService extends KiboRpcService {


    @Override
    protected void runPlan1() {

        int LOOP_COUNTER = 0;

        String pos_x = null;
        String pos_y = null;
        String pos_z = null;
        String qua_x = null;
        String qua_y = null;
        String qua_z = null;



        api.judgeSendStart();


//////////////////////////////////////////////////////////////////START P1-1////////////////////////////////////////////////////////


        while (LOOP_COUNTER == 0) {

            moveToWrapper(11, -5.5, 4.5, 0, 0.7071068, 0, 0.7071068);
            moveToWrapper(11, -5.5, 4.33, 0, 0.7071068, 0, 0.7071068);
            Bitmap QR2 = api.getBitmapNavCam();

            try {
                pos_z = DECODE_QR(QR2);
                Log.i(TAG, "FINISH DECODE PZ");
            } catch (Exception ignored) {

            }

            while (pos_z == null) {

                moveToWrapper(11, -5.5, 4.33, 0, 0.7071068, 0, 0.7071068);
                try {
                    pos_z = DECODE_QR(QR2);
                    Log.i(TAG, "FINISH DECODE PZ");
                } catch (Exception ignored) {
                }

            }


            api.judgeSendDiscoveredQR(2, pos_z);
            LOOP_COUNTER++;

        }


/////////////////////////////////////////////////////////////END P1-1///////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////START P1-2///////////////////////////////////////////////////////////////////////

        while (LOOP_COUNTER == 1) {


            moveToWrapper(11.323, -5.7, 4.52, 0, 0, 0, 1);
            moveToWrapper(11.5, -5.7, 4.5, 0, 0, 0, 1);
            Bitmap QR0 = api.getBitmapNavCam();

            try {
                pos_x = DECODE_QR(QR0);
                Log.i(TAG, "FINISH DECODE PX");
            } catch (Exception ignored) {

            }

            while (pos_x == null) {
                moveToWrapper(11.5, -5.7, 4.5, 0, 0, 0, 1);
                try {
                    pos_x = DECODE_QR(QR0);
                    Log.i(TAG, "FINISH DECODE PX");
                } catch (Exception ignored) {

                }

            }


            api.judgeSendDiscoveredQR(0, pos_x);
            LOOP_COUNTER++;


        }


/////////////////////////////////////////////////////////END P1-2/////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////START P1-3//////////////////////////////////////////////////////////////////////////


        while (LOOP_COUNTER == 2) {


            moveToWrapper(10.965, -6, 5.45, 0, -0.7071068, 0, 0.7071068);
            moveToWrapper(11, -6, 5.55, 0, -0.7071068, 0, 0.7071068);
            Bitmap QR1 = api.getBitmapNavCam();


            try {
                pos_y = DECODE_QR(QR1);
                Log.i(TAG, "FINISH DECODE PY");

            } catch (Exception ignored) {

            }

            while (pos_y == null) {
                moveToWrapper(11, -6, 5.55, 0, -0.7071068, 0, 0.7071068);
                try {
                    pos_y = DECODE_QR(QR1);
                    Log.i(TAG, "FINISH DECODE PY");
                } catch (Exception ignored) {

                }

            }


            api.judgeSendDiscoveredQR(1, pos_y);
            LOOP_COUNTER++;


        }


////////////////////////////////////////////////////////END P1-3/////////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        while (LOOP_COUNTER == 3) {
            moveToWrapper(10.60, -6.4, 5.55, 0, 0, 0.7071068, -0.7071068);
            moveToWrapper(11.325, -8, 5.02, 0, 0, 0, 1);
            LOOP_COUNTER++;
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        while (LOOP_COUNTER == 4) {

            moveToWrapper(11.5, -8, 5, 0, 0, 0, 1);
            Bitmap QR4 = api.getBitmapNavCam();


            try {
                qua_y = DECODE_QR(QR4);
                Log.i(TAG, "FINISH DECODE QY");
            } catch (Exception ignored) {

            }


            while (qua_y == null) {
                moveToWrapper(11.5, -8, 5, 0, 0, 0, 1);
                try {
                    qua_y = DECODE_QR(QR4);
                    Log.i(TAG, "FINISH DECODE QY");
                } catch (Exception ignored) {

                }
            }
            api.judgeSendDiscoveredQR(4, qua_y);
            LOOP_COUNTER++;
        }








        while (LOOP_COUNTER == 5) {


            moveToWrapper(10.40, -7.5, 4.72, 0, 0, 1, 0);
            moveToWrapper(10.30, -7.5, 4.7, 0, 0, 1, 0);
            Bitmap QR3 = api.getBitmapNavCam();


            try {
                qua_x = DECODE_QR(QR3);
                Log.i(TAG, "FINISH DECODE QX");
            } catch (Exception e) {
                e.printStackTrace();
            }


            while (qua_x == null) {
                moveToWrapper(10.30, -7.5, 4.7, 0, 0, 1, 0);
                try {
                    qua_x = DECODE_QR(QR3);
                    Log.i(TAG, "FINISH DECODE QX");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            api.judgeSendDiscoveredQR(3, qua_x);
            LOOP_COUNTER++;


        }








        while (LOOP_COUNTER == 6) {


            moveToWrapper(10.965, -7.7, 5.45, 0, -0.7071068, 0, 0.7071068);
            moveToWrapper(11, -7.7, 5.55, 0, -0.7071068, 0, 0.7071068);
            Bitmap QR5 = api.getBitmapNavCam();

            try {
                qua_z = DECODE_QR(QR5);
                Log.i(TAG, "FINISH DECODE QZ");
            } catch (Exception e) {
                e.printStackTrace();
            }


            while (qua_z == null) {
                moveToWrapper(11, -7.7, 5.55, 0, -0.7071068, 0, 0.7071068);
                try {
                    qua_z = DECODE_QR(QR5);
                    Log.i(TAG, "FINISH DECODE QZ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            api.judgeSendDiscoveredQR(5, qua_z);
            LOOP_COUNTER++;


        }







        while (LOOP_COUNTER == 7) {
            moveToWrapper(11.6, -9, 5.55, 0, 0, 0.7071068, -0.7071068);
            moveToWrapper(10.95,-9.7,5.4,0,0,0.7071068,-0.7071068);
            Mat mat = api.getMatNavCam();
            int value = (int)getIDs(mat);
            String ARID = String.valueOf(value);
            api.judgeSendDiscoveredAR(ARID);




            LOOP_COUNTER++;

        }

        api.judgeSendFinishSimulation();


    }


    private void moveToWrapper(double pos_x, double pos_y, double pos_z,
                               double qua_x, double qua_y, double qua_z,
                               double qua_w) {

        final int LOOP_MAX = 0;
        final Point point = new Point(pos_x, pos_y, pos_z);
        final Quaternion quaternion = new Quaternion((float) qua_x, (float) qua_y,
                (float) qua_z, (float) qua_w);

        Result result = api.moveTo(point, quaternion, true);

        int loopCounter = 0;
        while (!result.hasSucceeded() || loopCounter < LOOP_MAX) {
            result = api.moveTo(point, quaternion, true);

            ++loopCounter;


        }


    }






    protected String DECODE_QR(Bitmap barcodeBmp){

        int width = barcodeBmp.getWidth();
        int height = barcodeBmp.getHeight();
        int[] pixels = new int[width * height];
        barcodeBmp.getPixels(pixels, 0, width, 0, 0, width, height);
        Image barcode = new Image(width, height, "RGB4");
        barcode.setData(pixels);
        ImageScanner reader = new ImageScanner();
        reader.setConfig(Symbol.NONE, Config.ENABLE, 0);
        reader.setConfig(Symbol.QRCODE, Config.ENABLE, 1);
        int result = reader.scanImage(barcode.convert("Y800"));
        String value = null;


        if (result != 0) {

            SymbolSet syms = reader.getResults();
            for (Symbol sym : syms) {
                value = sym.getData();


            }


        }
        return value;


    }






    private double getIDs(Mat source)
    {
        Mat ids = new Mat();
        List<Mat> corners = new ArrayList<>();


        try{
            Dictionary dictionary = Aruco.getPredefinedDictionary(Aruco.DICT_5X5_250);
            Aruco.detectMarkers(source, dictionary, corners, ids);




        }catch (Exception e){

            try{
                Dictionary dictionary = Aruco.getPredefinedDictionary(Aruco.DICT_5X5_250);
                Aruco.detectMarkers(source, dictionary, corners, ids);



            }catch (Exception e1){

                try{
                    Dictionary dictionary = Aruco.getPredefinedDictionary(Aruco.DICT_5X5_250);
                    Aruco.detectMarkers(source, dictionary, corners, ids);





                }catch (Exception ignored){


                }


            }


        }






           return  ids.get(0,0)[0];

    }

}
