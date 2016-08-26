package com.example.xh.glidedemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Context context;
    private String[] urls = {"http://img2.3lian.com/2014/f6/173/d/51.jpg", "http://img2.3lian.com/2014/f6/173/d/52.jpg",
            "http://img2.3lian.com/2014/f6/173/d/53.jpg", "http://img2.3lian.com/2014/f6/173/d/54.jpg",
            "http://img2.3lian.com/2014/f6/173/d/55.jpg", "http://img2.3lian.com/2014/f6/173/d/56.jpg",
            "http://img16.3lian.com/gif2016/q21/43/81.jpg", "http://img16.3lian.com/gif2016/q21/43/84.jpg",
            "http://img16.3lian.com/gif2016/q21/43/87.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        glideLoadImage();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void glideLoadImage() {
        int  resourceId=R.mipmap.image;
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/55.jpg").placeholder(R.mipmap.place).error(R.mipmap.error).into(imageView);

        //设置错误监听
         RequestListener<String,GlideDrawable> errorListener=new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                Log.e("onException",e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
                imageView.setImageResource(R.mipmap.ic_launcher);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.e("onResourceReady","isFromMemoryCache:"+isFromMemoryCache+"  model:"+model+" isFirstResource: "+isFirstResource);
                return false;
            }
        } ;
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").listener(errorListener).placeholder(R.mipmap.place).crossFade(3000).into(imageView);
        //Glide.with(context).load("http://116.255.134.172:9090/jqgj_server_client/mobilephotos/2016/8/11/18603718778_2016081110201470a7684a-0b4c-44db-8676-8bfa00359d19.jpg").dontAnimate().override(400,600).fitCenter().into(imageView);
        //Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").thumbnail(0.2f).centerCrop().animate(R.anim.anim).into(imageView);

        //java文件设置动画
        ViewPropertyAnimation.Animator animator=new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
              view.setAlpha(0f);
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat( view, "alpha", 0f, 1f );
                fadeAnim.setDuration( 2500 );
                fadeAnim.start();
            }
        };
        Glide.with(context).load("http://img2.3lian.com/2014/f6/173/d/51.jpg").thumbnail(0.2f).centerCrop().animate(animator).into(imageView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
