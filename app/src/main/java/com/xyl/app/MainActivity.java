package com.xyl.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.xyl.app.image.ImageLoaderConfig;
import com.xyl.app.image.SimpleImageLoader;
import com.xyl.app.image.cache.DoubleCache;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);
        ImageLoaderConfig imageLoaderConfig = new ImageLoaderConfig.Builder()
                .setLoadingImage(R.mipmap.ic_launcher)
                .setFailedImage(R.mipmap.ic_launcher_round)
                .setCachePolicy(new DoubleCache())
                .build();
        SimpleImageLoader.getInstance(imageLoaderConfig);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyPermission();
            }
        });
    }

    private void applyPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);
            } else {
                loadImage();
            }
        } else {
            loadImage();
        }
    }

    void loadImage() {
        SimpleImageLoader.getInstance().displayImage(imageView, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1829653568,483602005&fm=27&gp=0.jpg");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadImage();
            } else {

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
