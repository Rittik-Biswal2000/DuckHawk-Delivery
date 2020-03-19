package com.duckhawk.duckhawk_delivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.util.MeasureUnit;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Signup extends AppCompatActivity {

    TextInputLayout email;
//    TextInputLayout pass;
    TextInputLayout name;
    private int PICk_IMAGE_AADHAR = 1;
    public Uri ImageUri;
    ImageView imageView;
    ImageView aadharpic,dlpic,bkp,pp;
    TextView aapicsug,dlpicsug,bkpsug,ppsug;
    private int PICk_IMAGE_DL = 2;
    private int PICk_IMAGE_BKP = 3;
    private int PICk_IMAGE_PP = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.email_input);
        name = findViewById(R.id.name_input);
        imageView = findViewById(R.id.image);
        aadharpic = findViewById(R.id.Adhaar);
        aapicsug = findViewById(R.id.aapicsug);
        dlpic = findViewById(R.id.dlpic);
        dlpicsug = findViewById(R.id.dlpicsug);
        bkp = findViewById(R.id.bikepic);
        bkpsug = findViewById(R.id.bikepicsug);
        pp = findViewById(R.id.PassPic);
        ppsug = findViewById(R.id.passpicsug);
    }

    private boolean validName(){
        String namein = name.getEditText().getText().toString().trim();
        if(namein.isEmpty()){
            name.setError("Name Field Can not be Empty");
            return false;
        }else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }

    }

   private boolean validEmail(){
        String emailin = email.getEditText().getText().toString().trim();
        if(emailin.isEmpty()){
            email.setError("Email Field Can not be Empty");
            return false;
        }else {
            if(emailin.contains("@") && emailin.contains(".")) {
                email.setError(null);
                email.setErrorEnabled(false);
                return true;
            }else {
                email.setError("Not a Valid Email");
                return false;
            }

        }

   }
//    private boolean validPassword(){
//        String passin = pass.getEditText().getText().toString().trim();
//        if(passin.isEmpty()){
//            pass.setError("Password Can not be Empty");
//            return false;
//        }else {
//            if(passin.length() >= 8 ){
//                pass.setError(null);
//                pass.setErrorEnabled(false);
//                return true;
//            }else {
//                pass.setError("Minimum 8 charcter long");
//                return false;
//            }
//
//        }
//
//    }


    public void conformInput(View v){
        if(!validName() || !validEmail()   ){
            return;
        }else{

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("Returned","True and Not image");

        //Aadhar Image upload
        if(requestCode == PICk_IMAGE_AADHAR){
            ImageUri = data.getData();
            Log.w("Returned","True");
            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(),ImageUri);
                aadharpic.setImageBitmap(img);
                aadharpic.setVisibility(View.VISIBLE);
                aapicsug.setText("Change the Photo");
                aapicsug.setTextSize(10);
                aapicsug.setCompoundDrawables(null,null,null,null);
                aapicsug.setTextColor(Color.rgb(255,0,0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == PICk_IMAGE_DL){
            ImageUri = data.getData();
            Log.w("Returned","True");
            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(),ImageUri);
                dlpic.setImageBitmap(img);
                dlpic.setVisibility(View.VISIBLE);
                dlpicsug.setText("Change the Photo");
                dlpicsug.setTextSize(10);
                dlpicsug.setCompoundDrawables(null,null,null,null);
                dlpicsug.setTextColor(Color.rgb(255,0,0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == PICk_IMAGE_BKP){
            ImageUri = data.getData();
            Log.w("Returned","True");
            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(),ImageUri);
                bkp.setImageBitmap(img);
                bkp.setVisibility(View.VISIBLE);
                bkpsug.setText("Change the Photo");
                bkpsug.setTextSize(10);
                bkpsug.setCompoundDrawables(null,null,null,null);
                bkpsug.setTextColor(Color.rgb(255,0,0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode == PICk_IMAGE_PP){
            ImageUri = data.getData();
            Log.w("Returned","True");
            try {
                Bitmap img = MediaStore.Images.Media.getBitmap(getContentResolver(),ImageUri);
                pp.setImageBitmap(img);
                pp.setVisibility(View.VISIBLE);
                ppsug.setText("Change the Photo");
                ppsug.setTextSize(10);
                ppsug.setCompoundDrawables(null,null,null,null);
                ppsug.setTextColor(Color.rgb(255,0,0));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void addAadhar(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"SelectPicture"),PICk_IMAGE_AADHAR);
    }

    public void addDlpic(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"SelectPicture"),PICk_IMAGE_DL);
    }
    public void addBikePic(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"SelectPicture"),PICk_IMAGE_BKP);
    }
    public void addProfilePic(View view){
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"SelectPicture"),PICk_IMAGE_PP);
    }

}
