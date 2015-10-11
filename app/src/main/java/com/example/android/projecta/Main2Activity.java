package com.example.android.projecta;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main2Activity extends AppCompatActivity {

//Defined Variables
    public String insertTxtCopy;
    public EditText qrTextField;
    public ImageLoader imgLoader;
    public ImageView qrImg;
    public TextView qrTxt;
    public CharSequence clipTxt;

    String BASE_QR_URL = "http://chart.apis.google.com/chart?cht=qr&chs=400x400&chld=M&choe=UTF-8&chl=";
    String fullUrl = BASE_QR_URL;

//GENERATED CODE --------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(config);

        qrTextField = (EditText) findViewById(R.id.textQR);
        qrImg = (ImageView)findViewById(R.id.qrImg);
        qrTxt = (TextView)findViewById(R.id.textView);

    } //void onCreate

//-----------------------------------------------------------

//MY CODE STARTS FROM HERE-----------------------------------
//-----------------------------------------------------------


    public void buttonQR(View v){
        insertTxtCopy = qrTextField.getText().toString();
        clipTxt = insertTxtCopy;

        //If the clipboard has text, and it is more than 0 characters.
        if((null != clipTxt) && (clipTxt.length() > 0)){
            try {
                qrTxt.setText(clipTxt);
                String copiedStr = clipTxt.toString();
                fullUrl = BASE_QR_URL + URLEncoder.encode(copiedStr, "UTF-8");
                imgLoader.displayImage(fullUrl, qrImg);

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else{ //If no text display a dialog.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("QRMaker")
                    .setCancelable(true)
                    .setMessage("There was no data in the clipboard! Go copy something and come back")
                    .setNeutralButton("Okay", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();

                        }

                    });

            AlertDialog diag = builder.create();
            diag.show();
        }
    }

} //Public class Main2Activity
