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

        Bitmap bluePlaneBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.blue_plane);
        ImageCache.put("bluePlaneBitmap", bluePlaneBitmap);
        Bitmap redPlaneBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.red_plane);
        ImageCache.put("redPlaneBitmap", redPlaneBitmap);

        Bitmap redBulletBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.red_bullet);
        ImageCache.put("redBulletBitmap", redBulletBitmap);
        Bitmap blueBulletBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.blue_bullet);
        ImageCache.put("blueBulletBitmap", blueBulletBitmap);
        Bitmap missileBulletBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.missile_bullet);
        ImageCache.put("missileBulletBitmap", missileBulletBitmap);

        Bitmap enemyPlane1Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.enemy_plane);
        ImageCache.put("enemyPlane1Bitmap", enemyPlane1Bitmap);
        Bitmap enemyPlane2Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.enemy_plane2);
        ImageCache.put("enemyPlane2Bitmap", enemyPlane2Bitmap);
        Bitmap enemyBossBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.boss);
        ImageCache.put("enemyBossBitmap", enemyBossBitmap);

        Bitmap bluePlaneLifeBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.blue_plane_small);
        ImageCache.put("bluePlaneLifeBitmap", bluePlaneLifeBitmap);

        Bitmap bomBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bom);
        ImageCache.put("bomBitmap", bomBitmap);

        Bitmap map1Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.map);
        map1Bitmap=ImageUtils.scaleBitmap(map1Bitmap, 1080, 216);
        ImageCache.put("map1Bitmap", map1Bitmap);
        Bitmap map2Bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.map2);
        map2Bitmap=ImageUtils.scaleBitmap(map2Bitmap, 1080, 216);
        ImageCache.put("map2Bitmap", map2Bitmap);

        //this.setContentView(new GameView(MainActivity.this, "1"));

        //AlertDialog
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //load res->layout->select_plane.xml
        View planeSelectView=View.inflate(this, R.layout.select_plane, null);
        //get ImageView Object
        ImageView btnBluePlane=(ImageView)planeSelectView.findViewById(R.id.bluePlane);
        ImageView btnRedPlane=(ImageView)planeSelectView.findViewById(R.id.redPlane);

        //Set click command event
        btnBluePlane.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                MainActivity.this.setContentView(new GameView(MainActivity.this,"1"));
                //Toast.makeText(MainActivity.this, "BluePlane is selected",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnRedPlane.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                MainActivity.this.setContentView(new GameView(MainActivity.this,"2"));
                //Toast.makeText(MainActivity.this, "RedPlane is selected",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog=builder.create();//build a dialog
        dialog.show();//show dialog

        //replace dialog content view
        dialog.getWindow().setContentView(planeSelectView);

        //setContentView(new GameView(this));
        //setContentView(R.layout.activity_main);


    }
}