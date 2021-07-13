package com.epnfis.gameapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Set MainActivity full-screen and no title bar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        //AlertDialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //load res->layout->select_plane.xml
        View planeSelectView=View.inflate(this, R.layout.select_plane, null);
        //get ImageView Object
        ImageView btnBluePlane=(ImageView)planeSelectView.findViewById(R.id.bluePlane);
        ImageView btnRedPlane=(ImageView)planeSelectView.findViewById(R.id.redPlane);

        //build a dialog
        dialog=builder.create();
//show dialog
        dialog.show();

        //replace dialog content view
        dialog.getWindow().setContentView(planeSelectView);

        //Set click command event
        btnBluePlane.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "BluePlane is selected",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnRedPlane.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this, "RedPlane is selected",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        /*Bitmap bluePlaneBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blue_plane);
                ImageCache.put("bluePlaneBitmap", bluePlaneBitmap);

        setContentView(new GameView(this));*/

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


    }
}