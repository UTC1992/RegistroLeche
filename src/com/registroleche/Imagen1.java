package com.registroleche;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ZoomControls;

public class Imagen1 extends ActionBarActivity {
	ImageView imageView;
    ZoomControls zoomControls;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imagen1);
		 imageView=(ImageView)findViewById(R.id.imageView);
	
		 zoomControls=(ZoomControls)findViewById(R.id.zoomControls);
	        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
	            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
				@Override            public void onClick(View v) {

	                float x = imageView.getScaleX();
	                float y = imageView.getScaleY();

	                imageView.setScaleX((float) (x + 0.20));
	                imageView.setScaleY((float) (y + 0.20));
	            }
	        });
	        
	        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
	            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
				@Override            public void onClick(View v) {

	                float x = imageView.getScaleX();
	                float y = imageView.getScaleY();

	                imageView.setScaleX((float) (x - 0.20));
	                imageView.setScaleY((float) (y - 0.20));
	            }
	        });
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.imagen1, menu);
		return true;
	}
	public void Salir(View view)
	{
		Intent salir = new Intent(this, MenuImagen.class);
		startActivity(salir);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
