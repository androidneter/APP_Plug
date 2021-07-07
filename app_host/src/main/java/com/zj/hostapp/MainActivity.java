package com.zj.hostapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.didi.virtualapk.PluginManager;
import com.zj.plusapp.R;
import java.io.File;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";
  boolean isSuccess = false;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED) {
    } else {
      ActivityCompat.requestPermissions(this, new String[] {
          Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
      }, 100);
    }

    //String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/app_plug-release.apk");
    File plugin = new File(this.getFilesDir(), "app_plug-release.apk");
    if (plugin.exists()) {
      Log.e(TAG, "'exists'");
      try {
        PluginManager.getInstance(this).loadPlugin(plugin);
        isSuccess = true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    Button viewById = findViewById(R.id.btn_skip);
    viewById.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (isSuccess) {
          if (PluginManager.getInstance(MainActivity.this).getLoadedPlugin("com.zj.plugapp")
              == null) {
            Toast.makeText(getApplicationContext(), "加载插件失败", Toast.LENGTH_SHORT).show();
            return;
          }
          // Given "com.didi.virtualapk.demo" is the package name of plugin APK,
          // and there is an activity called `MainActivity`.
          Intent intent = new Intent();
          intent.setClassName("com.zj.plugapp", "com.zj.plugapp.HomeActivity");
          startActivity(intent);
        } else {
          Toast.makeText(MainActivity.this, "初始化插件失败！", Toast.LENGTH_LONG).show();
        }
      }
    });
  }
}