package edu.ucuccs.urdanetafoodmap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.parse.ParseUser;

public class Second extends Activity {

	private static final int PICK_IMAGE = 1;
	private static final int TAKE_PICTURE = 2;
	private ImageButton imgBrowse, imgTake, imgMap, imgfav;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		ParseUser currentUser = ParseUser.getCurrentUser();
		String struser = currentUser.getUsername().toString();
		TextView txtuser = (TextView) findViewById(R.id.txtuser);
		txtuser.setText(" Welcome " + struser);
		imgBrowse = (ImageButton) findViewById(R.id.imgBrowse);
		imgMap = (ImageButton) findViewById(R.id.imgMap);
		imgTake = (ImageButton) findViewById(R.id.imgTake);
		imgfav = (ImageButton) findViewById(R.id.imgstar);

		imgBrowse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/*
				 * Intent intent = new Intent(); intent.setType("image/*");
				 * intent.setAction(Intent.ACTION_GET_CONTENT);
				 * startActivityForResult(Intent.createChooser(intent,
				 * "Select Picture"), PICK_IMAGE);
				 */
				startActivityForResult(
						new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
						PICK_IMAGE);

			}
		});

		imgTake.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivityForResult(new Intent(
						MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
			}

		});

		imgMap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), Map.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", 2);
				i.putExtras(bundle);
				startActivity(i);
			}

		});

		imgfav.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}

		});

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == TAKE_PICTURE) {
				Bitmap photo = (Bitmap) data.getExtras().get("data");
				Intent i = new Intent(getApplicationContext(),
						SubmitPhoto.class);
				i.putExtra("Image", photo);
				startActivity(i);
			} else if (requestCode == PICK_IMAGE) {
				Uri selectedImage = data.getData();
				String[] filePath = { MediaStore.Images.Media.DATA };
				Cursor c = getApplicationContext().getContentResolver().query(
						selectedImage, filePath, null, null, null);
				c.moveToFirst();
				int columnIndex = c.getColumnIndex(filePath[0]);
				String picturePath = c.getString(columnIndex);
				Bitmap photo2 = (BitmapFactory.decodeFile(picturePath));
				Intent v = new Intent(getApplicationContext(),
						SubmitPhoto.class);
				v.putExtra("Image", photo2);
				startActivity(v);
				// c.close();
			}

		}
		super.onActivityResult(requestCode, resultCode, data);

	}
}
