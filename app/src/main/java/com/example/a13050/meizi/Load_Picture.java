package com.example.a13050.meizi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 13050 on 2018/9/19.
 */

public class Load_Picture {
    private ImageView loadImg;
    private String imgUrl;
    private byte[] picByte;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123) {
                if (picByte != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(picByte, 0, picByte.length);
                    loadImg.setImageBitmap(bitmap);
                }
            }
        }
    };




    public void Load(ImageView loadImg,String imgUrl)
    {
        this.loadImg=loadImg;
        this.imgUrl=imgUrl;
        Log.d("Net","making");
        Drawable drawable=loadImg.getDrawable();
        if (drawable ==null)
        {   Log.d("Net","making154315");
            new Thread(runnable_L).start();
        }
        if(drawable != null && drawable instanceof BitmapDrawable)
        { Log.d("Net","make_Picture");
            Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
            if(bitmap !=null && !bitmap.isRecycled())
            {
                bitmap.recycle();
            }
            new Thread(runnable_L).start();
        }
        Log.d("Net","making123");
    }

    Runnable runnable_L=new Runnable() {
        @Override
        public void run() {
            try {

                URL url=new URL(imgUrl);
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(10000);
                if(conn.getResponseCode()==200)
                {

                    InputStream in=conn.getInputStream();
                    ByteArrayOutputStream out=new ByteArrayOutputStream();
                    byte[] bytes=new byte[1024];
                    int length=-1;
                    while ((length=in.read(bytes))!=-1)
                    {
                        out.write(bytes,0,length);
                    }

                    picByte=out.toByteArray();
                    in.close();
                    out.close();

                  handler.sendEmptyMessage(0x123);


                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    };


}

