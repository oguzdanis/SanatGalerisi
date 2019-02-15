package com.example.sanatgalerisi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editText);
        Button saveButton = findViewById(R.id.button);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if(info.equals("yeni")){

            Bitmap ekle = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.ekle);
            imageView.setImageBitmap(ekle);
            saveButton.setVisibility(View.VISIBLE);
            editText.setText("");

        }else{
            saveButton.setVisibility(View.INVISIBLE);
        }
    }

    public void select(View view){
        //izin verilmediyse
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
        requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode ==2){
            if (grantResults.length >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==1 && resultCode==RESULT_OK && data!=null){
            Uri image = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public  void save(View view){

    }

}
