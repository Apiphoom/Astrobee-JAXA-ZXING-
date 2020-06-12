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

