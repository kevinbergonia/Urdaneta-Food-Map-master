package edu.ucuccs.urdanetafoodmap;

import java.io.File;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;

public class Third extends Activity {
	File generalFile, combined;
	FrameLayout frame1;
	Camera cam1;
	int REQUEST_CODE_CAM = 1;

	private ShowCamera mPreview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		cam1 = getInstanceCamera();
		mPreview = new ShowCamera(this, cam1);
		frame1 = (FrameLayout) findViewById(R.id.frame1);
		frame1.addView(mPreview);

	}

	private final ShutterCallback shutterCallback = new ShutterCallback() {

		@Override
		public void onShutter() {
			generalFile = Environment.getExternalStorageDirectory();
			combined = new File(generalFile + "/piktyur");
			combined.mkdirs();

		}

	};
	public void ClickCapture (View v){
		cam1.takePicture(shutterCallback, null, null);
	
		
	}

	private static Camera getInstanceCamera() {
		Camera c = null;
		try {
			c = Camera.open();

		} catch (Exception e) {

		}
		return c;

	}

}
