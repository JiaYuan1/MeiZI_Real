package com.example.a13050.meizi;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button showBtn;
    private ImageView showImg;
    private ArrayList<String> urls;
    private Load_Picture loader;
    private int curPos=0;
    private byte[] picByte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loader=new Load_Picture();
        initData();
        initUI();

    }

    private void initData()
    {
        urls=new ArrayList<>();
        urls.add("http://ww4.sinaimg.cn/large/610dc034jw1f6ipaai7wgj20dw0kugp4.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f6gcxc1t7vj20hs0hsgo1.jpg");
        urls.add("http://ww4.sinaimg.cn/large/610dc034jw1f6f5ktcyk0j20u011hacg.jpg");
        urls.add("http://ww1.sinaimg.cn/large/610dc034jw1f6e1f1qmg3j20u00u0djp.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f6aipo68yvj20qo0qoaee.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f69c9e22xjj20u011hjuu.jpg");
        urls.add("http://ww3.sinaimg.cn/large/610dc034jw1f689lmaf7qj20u00u00v7.jpg");
        urls.add("http://ww3.sinaimg.cn/large/c85e4a5cjw1f671i8gt1rj20vy0vydsz.jpg");
        urls.add("http://ww2.sinaimg.cn/large/610dc034jw1f65f0oqodoj20qo0hntc9.jpg");
        urls.add("http://ww2.sinaimg.cn/large/c85e4a5cgw1f62hzfvzwwj20hs0qogpo.jpg");
    }

    private void initUI()
    {
        showBtn=(Button)findViewById(R.id.btn_show);
        showImg=(ImageView)findViewById(R.id.img_show);
        showBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_show:
                if(curPos >9)
                {
                    curPos=0;
                    break;
                }
               loader.Load(showImg,urls.get(curPos));
                curPos++;
                break;
        }
    }


/*

    Handler handler=new Handler();

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(picByte!=null)
            {
                Bitmap bitmap= BitmapFactory.decodeByteArray(picByte,0,picByte.length);
                showImg.setImageBitmap(bitmap);
            }
        }
    };




    public void Load()
    {

        Log.d("Net","making");
        Drawable drawable=showImg.getDrawable();
        if(drawable !=null && drawable instanceof BitmapDrawable)
        {
            Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
            if(bitmap !=null && !bitmap.isRecycled())
            {
                bitmap.recycle();
            }
            new Thread(runnable_L).start();;
        }
    }

    Runnable runnable_L=new Runnable() {
        @Override
        public void run() {
            try {

                URL url=new URL(urls.get(curPos));
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

                    handler.post(runnable);


                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    };




*/



}













