package edu.ucuccs.urdanetafoodmap;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;

public class SubmitPhoto extends Activity {

	ImageView imgview1;
	final String APP_ID = "eRyUdJZ7xKqk5lf4ecaUXtdOOXLGEIiXrnN1dpp0";
	final String CLIENT_KEY = "tKVaHCivOxHz93DZxaWwxMH9aJYo6Xx9sJEubeac";
	EditText txtTitle, txtDescription;
	Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submit_photo);
		txtTitle = (EditText) findViewById(R.id.txtTitle);
		txtDescription = (EditText) findViewById(R.id.txtDescription);

		Parse.initialize(this, APP_ID, CLIENT_KEY);

	}
	@Override
	public void onStart(){
		super.onStart();
		imgview1 = (ImageView) findViewById(R.id.imgView1);
		bitmap = (Bitmap) getIntent().getParcelableExtra("Image");
		imgview1.setImageBitmap(bitmap);
	}
	public void clickSubmit(View v){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
	    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
	    // get byte array here
	   byte[] bytearray= stream.toByteArray();
	   
	   ParseObject user = new ParseObject("Places");
	   user.put("title", txtTitle.getText().toString());
	   user.put("description", txtDescription.getText().toString());
	    if (bytearray != null){
	        ParseFile file = new ParseFile(txtTitle.getText().toString().toLowerCase()+".png",bytearray);
	        file.saveInBackground();
	        user.put("image",file);
	    }
	    user.saveInBackground();

	}
	public void clickCancel(View v){
		finish();
	}
	

}
