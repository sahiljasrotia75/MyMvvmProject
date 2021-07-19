package com.app.niluscoach.startWorkOut;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.app.niluscoach.R;

//public class Accelerometer extends Activity implements SensorEventListener {
//    private SensorManager mSensorManager;
//    private Sensor mAccelerometer;
//
//    private AnimatedView mAnimatedView = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//
//        mAnimatedView = new AnimatedView(this);
//        //Set our content to a view, not like the traditional setting to a layout
//        setContentView(mAnimatedView);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mSensorManager.unregisterListener(this);
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor arg0, int arg1) {
//
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            mAnimatedView.onSensorEvent(event);
//        }
//    }
//
//    public class AnimatedView extends View {
//
//        private static final int CIRCLE_RADIUS = 25; //pixels
//
//        private Paint mPaint;
//        private int x;
//        private int y;
//        private int viewWidth;
//        private int viewHeight;
//
//        public AnimatedView(Context context) {
//            super(context);
//            mPaint = new Paint();
//            mPaint.setColor(Color.MAGENTA);
//        }
//
//        @Override
//        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//            super.onSizeChanged(w, h, oldw, oldh);
//            viewWidth = w;
//            viewHeight = h;
//        }
//
//        public void onSensorEvent (SensorEvent event) {
//            x = x - (int) event.values[0];
//            y = y + (int) event.values[1];
//            //Make sure we do not draw outside the bounds of the view.
//            //So the max values we can draw to are the bounds + the size of the circle
//            if (x <= 0 + CIRCLE_RADIUS) {
//                x = 0 + CIRCLE_RADIUS;
//            }
//            if (x >= viewWidth - CIRCLE_RADIUS) {
//                x = viewWidth - CIRCLE_RADIUS;
//            }
//            if (y <= 0 + CIRCLE_RADIUS) {
//                y = 0 + CIRCLE_RADIUS;
//            }
//            if (y >= viewHeight - CIRCLE_RADIUS) {
//                y = viewHeight - CIRCLE_RADIUS;
//            }
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            canvas.drawCircle(x, y, CIRCLE_RADIUS, mPaint);
//            //We need to call invalidate each time, so that the view continuously draws
//            invalidate();
//        }
//    }
//}





public class Accelerometer extends Activity implements SensorEventListener
{
    /** Called when the activity is first created. */
    CustomDrawableView mCustomDrawableView = null;
    ShapeDrawable mDrawable = new ShapeDrawable();
    public static int x;
    public static int y;

    private SensorManager sensorManager = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        // Get a reference to a SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mCustomDrawableView = new CustomDrawableView(this);
        setContentView(mCustomDrawableView);
        // setContentView(R.layout.main);

    }

    // This method will update the UI on new sensor events
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                // the values you were calculating originally here were over 10000!
//                x = (int) Math.pow(sensorEvent.values[1], 2);
//                y = (int) Math.pow(sensorEvent.values[1], 2);
                //x = (int) sensorEvent.values[0];
                y = (int) Math.pow(sensorEvent.values[2], 2);

            }

            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {

            }
        }
    }

    // I've chosen to not implement this method
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
        // ...and the orientation sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onStop()
    {
        // Unregister the listener
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    public class CustomDrawableView extends View
    {
        static final int width = 50;
        static final int height = 50;

        public CustomDrawableView(Context context)
        {
            super(context);

            mDrawable = new ShapeDrawable(new OvalShape());
            mDrawable.getPaint().setColor(0xff74AC23);
            mDrawable.setBounds(x, y, x + width, y + height);
        }

        protected void onDraw(Canvas canvas)
        {
            RectF oval = new RectF(Accelerometer.x, Accelerometer.y, Accelerometer.x + width, Accelerometer.y
                    + height); // set bounds of rectangle
            Paint p = new Paint(); // set some paint options
            p.setColor(Color.BLUE);
            canvas.drawOval(oval, p);
            invalidate();
        }
    }
}
//
//public class Accelerometer extends Activity implements SensorEventListener {
//    private SensorManager sensorManager;
//    private Sensor accelerometer;
//    private long lastUpdate;
//
//    AnimatedView animatedView = null;
//    ShapeDrawable mDrawable = new ShapeDrawable();
//    public static int x;
//    public static int y;
//   private float[] mAccelerometerData = new float[3];
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // setContentView(R.layout.activity_main);
//
//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        accelerometer = sensorManager
//                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        lastUpdate = System.currentTimeMillis();
//
//        animatedView = new AnimatedView(this);
//        setContentView(animatedView);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        sensorManager.registerListener(this, accelerometer,
//                SensorManager.SENSOR_DELAY_FASTEST);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this);
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.activity_main, menu);
////        return true;
////    }
//
//    @Override
//    public void onAccuracyChanged(Sensor arg0, int arg1) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        // TODO Auto-generated method stub
//        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            mAccelerometerData = event.values.clone();
//            //x -= (int) event.values[0];
//      y += (int) event.values[1];
//
//
//        }
//    }
//
//
//
//    @SuppressLint("AppCompatCustomView")
//    public class AnimatedView extends ImageView {
//
//        static final int width = 50;
//        static final int height = 50;
//
//        public AnimatedView(Context context) {
//            super(context);
//            // TODO Auto-generated constructor stub
//
//            mDrawable = new ShapeDrawable(new OvalShape());
//            mDrawable.getPaint().setColor(0xffffAC23);
//            mDrawable.setBounds(x, y, x + width, y + height);
//
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//
//            mDrawable.setBounds(x, y, x + width, y + height);
//            mDrawable.draw(canvas);
//            invalidate();
//        }
//    }
//
//}



















