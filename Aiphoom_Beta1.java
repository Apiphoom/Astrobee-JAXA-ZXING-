
//@copyRight Apiphoom chuecnhompoo 2020
//@copyRight API Development 2020

package jp.jaxa.iss.kibo.rpc.sampleapk;


//////////////////////import JAXA section //////////////////////////////////
import gov.nasa.arc.astrobee.android.gs.MessageType;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import android.graphics.Bitmap;

///////////////////////////////////////////////////////////////////////////////

//////////////////////import QR code section//////////////////////////////////
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.lang.Object;
import java.lang.Throwable;
import java.lang.Exception;
import java.lang.ReflectiveOperationException;

////////////////////////////////////////////////////////////////////////////////


import com.google.zxing.Reader;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;



//////////////////////////////////////////////////////////////////////////////




/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1() {
       //Qua_x 0.7071068 Astrobee spin right
       //QUa_x -0.7071068 Astrobee spin left
       //Qua_y  1    Astrobee look up
       //Qua_y -1    Astrobee look down
       //Qua_w  1    Astrobee turn right
       //Qua_W -1    Astrobee turn left

///////////////////////////////////////////////////////start P1-1/////////////////////////////////////////////////////////
        api.judgeSendStart();
        
        moveToWrapper(11.5, -5.7, 4.5, 0, 0, 0, 1);// P.1-1
        moveToWrapper(11.562, -5.642, 4.588, 0, 0, 0, 1);// P.1-1

        Bitmap bMap = api.getBitmapNavCam();
         int[] intArray = new int[bMap.getWidth()*bMap.getHeight()];
         bMap.getPixels(intArray, 0, bMap.getWidth(), 0, 0, bMap.getWidth(), bMap.getHeight());
         LuminanceSource source = new RGBLuminanceSource(bMap.getWidth(), bMap.getHeight(),intArray);
         BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
         String pos_x;
         com.google.zxing.Result qrcode = null;
         try {
             qrcode = new MultiFormatReader().decode(bitmap);
         }
         catch (Exception e) {
         }

        pos_x = qrcode.getText();
        api.judgeSendDiscoveredQR(0,pos_x);

/////////////////////////////////////////////////////////end P1-1///////////////////////////////////////////////////////




//////////////////////////////////////////////////////start P1-2//////////////////////////////////////////////////////



        moveToWrapper(11, -6, 5.55, 0, -0.7071068, 0, 0.7071068);// P. 1-2
        moveToWrapper(11, -6, 5.576, 0, -0.7071068, 0, 0.7071068);// P. 1-2

        Bitmap bMap1 = api.getBitmapNavCam();
        int[] intArray1 = new int[bMap1.getWidth()*bMap1.getHeight()];
        bMap1.getPixels(intArray1, 0, bMap1.getWidth(), 0, 0, bMap1.getWidth(), bMap1.getHeight());
        LuminanceSource source1 = new RGBLuminanceSource(bMap1.getWidth(), bMap1.getHeight(),intArray1);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source1));
        String pos_y;
        com.google.zxing.Result qrcode1 = null;
        try {
            qrcode1 = new MultiFormatReader().decode(bitmap1);
        }
        catch (Exception e) {
        }

        pos_y = qrcode1.getText();
        api.judgeSendDiscoveredQR(1,pos_y);


/////////////////////////////////////////////////////end P1-2///////////////////////////////////////////////////////////q

////////////////////////////////////////////////////start P1-3///////////////////////////////////////////////////////////


        moveToWrapper(11, -5.5, 4.33, 0, 0.7071068, 0, 0.7071068);// P. 1-3h

        Bitmap bMap2 = api.getBitmapNavCam();
        int[] intArray2 = new int[bMap2.getWidth()*bMap2.getHeight()];
        bMap2.getPixels(intArray2, 0, bMap2.getWidth(), 0, 0, bMap2.getWidth(), bMap2.getHeight());
        LuminanceSource source2 = new RGBLuminanceSource(bMap2.getWidth(), bMap2.getHeight(),intArray2);
        BinaryBitmap bitmap2 = new BinaryBitmap(new HybridBinarizer(source2));
        String pos_z;
        com.google.zxing.Result qrcode2 = null;
        try {
            qrcode2 = new MultiFormatReader().decode(bitmap2);
        }
        catch (Exception e) {
        }

        pos_z = qrcode2.getText();
        api.judgeSendDiscoveredQR(2,pos_z);

 /////////////////////////////////////////////////end P1-3///////////////////////////////////////////////////////////////////

 /////////////////////////////////////////////////Avoid obsticle/////////////////////////////////////////////////////////////





        api.laserControl(true);


        api.judgeSendFinishSimulation();





    }

    @Override
    protected void runPlan2() {

    }

    @Override
    protected void runPlan3() {

    }


    // You can add your method
    private void moveToWrapper(double pos_x, double pos_y, double pos_z,
                               double qua_x, double qua_y, double qua_z,
                               double qua_w) {

        final int LOOP_MAX = 3;
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


           //int[] intArray = new int[snapshot1.getWidth()*snapshot1.getHeight()];
           //LuminanceSource source = new RGBLuminanceSource(snapshot1.getWidth(), snapshot1.getHeight(),intArray);
          // BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(snapshot1));
}
