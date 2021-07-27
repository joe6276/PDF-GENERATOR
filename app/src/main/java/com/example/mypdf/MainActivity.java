package com.example.mypdf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button button;
Bitmap bmp,scaledBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= findViewById(R.id.generate);
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        scaledBitmap=Bitmap.createScaledBitmap(bmp,40,40,false);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
        PackageManager.PERMISSION_GRANTED);
        createPDF();
    }

    private void createPDF() {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {

                            PdfDocument pdfDocument=new PdfDocument();
                            Paint paint= new Paint();
                            PdfDocument.PageInfo mypageinfo1=new PdfDocument.PageInfo.Builder(250,400,1).create();
                            PdfDocument.Page mypage1= pdfDocument.startPage(mypageinfo1);
                            Canvas canvas=mypage1.getCanvas();

                            paint.setTextAlign(Paint.Align.CENTER);
                            paint.setTextSize(12.0f);
                            canvas.drawText("HAVAS NATIONAL HOSPITAL",mypageinfo1.getPageWidth()/2,30,paint);
                            canvas.drawBitmap(scaledBitmap,10,10,paint);

                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setTextSize(6.0f);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Customer Name",10,70,paint);

                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setTextSize(6.0f);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Customer Email",10,80,paint);

                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setTextSize(6.0f);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Customer Age",10,90,paint);

                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setTextSize(6.0f);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Customer Phone",10,100,paint);

                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setTextSize(6.0f);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Customer NHIF",10,110,paint);

                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setTextSize(6.0f);
                        paint.setColor(Color.BLACK);
                        canvas.drawText("Date ",10,120,paint);

                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(2);
                        paint.setTextSize(6.0f);
                        canvas.drawRect(100,70,mypageinfo1.getPageWidth()-10,120,paint);
                        paint.setStrokeWidth(0);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Referred By: ",110,80,paint);
                        canvas.drawText("Dr Joe ",110,100,paint);

                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(2);
                        paint.setTextSize(6.0f);
                        canvas.drawRect(10,150,mypageinfo1.getPageWidth()-10,200,paint);
                        paint.setStrokeWidth(0);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Reason for Referral ",30,160,paint);
                        canvas.drawText("The Reason for Referral is this",20,180,paint);

                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(2);
                        canvas.drawRect(10,220,mypageinfo1.getPageWidth()-10,300,paint);
                        canvas.drawLine(110, 220,110,300,paint);
                        paint.setStrokeWidth(0);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Restriction ",30,230,paint);
                        canvas.drawText("Restriction reason is this: ",20,250,paint);
                        canvas.drawText("Diagnosis ",140,230,paint);
                        canvas.drawText("The sickness is: ",130,250,paint);

                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(2);
                        canvas.drawRect(10,310,mypageinfo1.getPageWidth()-10,340,paint);
                        paint.setStrokeWidth(0);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Next of Kin: ",20,320,paint);
                        canvas.drawText("Jonathan",80,320,paint);

                        pdfDocument.finishPage(mypage1);


                            File file= new File(Environment.getExternalStorageDirectory(),"/first.pdf");
                            try {
                                pdfDocument.writeTo(new FileOutputStream(file));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            pdfDocument.close();
                        }


                }
        );
    }
}