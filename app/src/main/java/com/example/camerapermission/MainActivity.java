package com.example.camerapermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.acl.Permission;

public class MainActivity extends AppCompatActivity {

    private Button mbtCameraPermission;
    public static final int  PER= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtCameraPermission=findViewById(R.id.cameraPermission);
        mbtCameraPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permission = {Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions(MainActivity.this,permission,PER);

            }
        });
    }
    int count=0;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            showToast("Camera Permission Granted");
        }else{
            if(count==1){
                showToast("Permission Denied");
            }else if(count<1){
                String[] permission = {Manifest.permission.CAMERA};
                ActivityCompat.requestPermissions(MainActivity.this, permission, PER);
                count++;
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
