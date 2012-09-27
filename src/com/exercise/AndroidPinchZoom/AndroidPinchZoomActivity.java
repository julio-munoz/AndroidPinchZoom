package com.exercise.AndroidPinchZoom;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AndroidPinchZoomActivity extends Activity {
	
	TextView scaleGesture;
	ImageView myImageView;
	float curScale = 1F;
	Bitmap bitmap;
	int bmpWidth, bmpHeight;
	
	ScaleGestureDetector scaleGestureDetector;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        scaleGesture = (TextView)findViewById(R.id.ScaleGesture);
        myImageView = (ImageView)findViewById(R.id.imageview);
        
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        bmpWidth = bitmap.getWidth();
        bmpHeight = bitmap.getHeight();
        drawMatrix();
        
        scaleGestureDetector = new ScaleGestureDetector(this, new simpleOnScaleGestureListener());
    }
    
    private void drawMatrix(){
    	
    	curScale = ((curScale - 1) * 10) + 1;
    	if (curScale < 0.1){
    		curScale = 0.1f;
    	}
    	
    	Bitmap resizedBitmap;     
        int newHeight = (int) (bmpHeight * curScale);
        int newWidth = (int) (bmpWidth * curScale); 
        resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
        myImageView.setImageBitmap(resizedBitmap);
    }
    
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	scaleGestureDetector.onTouchEvent(event);
		return true;
	}

	public class simpleOnScaleGestureListener extends SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			curScale = detector.getScaleFactor();
			scaleGesture.setText(String.valueOf(curScale));
			drawMatrix();
			return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			super.onScaleEnd(detector);
		}

    }
}