package edu.ucuccs.urdanetafoodmap;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder holdMe;
	private Camera camera1;

	public ShowCamera(Context context, Camera camera) {
		super(context);
		camera1 = camera;
		holdMe = getHolder();
		holdMe.addCallback(this);

	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		try{
			camera1.setPreviewDisplay(holdMe);
			camera1.startPreview();
		}catch (IOException e){
			
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

}
