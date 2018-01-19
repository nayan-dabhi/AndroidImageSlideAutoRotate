package com.nd.imageslideautorotate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nd.imageslideautorotate.Animation.AccordionTransformer;
import com.nd.imageslideautorotate.Animation.BackgroundToForegroundTransformer;
import com.nd.imageslideautorotate.Animation.CubeInTransformer;
import com.nd.imageslideautorotate.Animation.CubeOutTransformer;
import com.nd.imageslideautorotate.Animation.DepthPageTransformer;
import com.nd.imageslideautorotate.Animation.DrawFromBackTransformer;
import com.nd.imageslideautorotate.Animation.DrawerTransformer;
import com.nd.imageslideautorotate.Animation.FlipHorizontalTransformer;
import com.nd.imageslideautorotate.Animation.FlipVerticalTransformer;
import com.nd.imageslideautorotate.Animation.ForegroundToBackgroundTransformer;
import com.nd.imageslideautorotate.Animation.RotateDownTransformer;
import com.nd.imageslideautorotate.Animation.RotateUpTransformer;
import com.nd.imageslideautorotate.Animation.ScaleInOutTransformer;
import com.nd.imageslideautorotate.Animation.TabletTransformer;
import com.nd.imageslideautorotate.Animation.ZoomInTransformer;
import com.nd.imageslideautorotate.Animation.ZoomOutPageTransformer;
import com.nd.imageslideautorotate.Animation.ZoomOutSlideTransformer;
import com.nd.imageslideautorotate.Animation.ZoomOutTransformer;
import com.nd.imageslideautorotate.Utils.ViewPagerScroller;
import com.nd.imageslideautorotate.adapter.SliderAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Activity mActivity;
    ViewPager viewPager;
    SliderAdapter sliderAdapter;

    int images[] = {
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3
    };

    Timer timer;
    final long DELAY_MS = 10000;
    final long PERIOD_MS = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActivity = this;

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        sliderAdapter = new SliderAdapter(this, images);
        viewPager.setAdapter(sliderAdapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        ViewPagerScroller scroller = new ViewPagerScroller(viewPager.getContext());
        scroller.setViewPagerScrollSpeed(viewPager, 3000);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                viewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        int currentPage = (viewPager.getCurrentItem() + 1) % images.length;
                        viewPager.setCurrentItem(currentPage, true);
                    }
                });
            }
        };

        timer = new Timer();
        timer.schedule(timerTask, DELAY_MS, PERIOD_MS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        timer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.accordion) {
            viewPager.setPageTransformer(true, new AccordionTransformer());
            return true;
        } else if (id == R.id.background_to_foreground) {
            viewPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
            return true;
        } else if (id == R.id.cube_in) {
            viewPager.setPageTransformer(true, new CubeInTransformer());
            return true;
        } else if (id == R.id.cube_out) {
            viewPager.setPageTransformer(true, new CubeOutTransformer());
            return true;
        } else if (id == R.id.depth_page) {
            viewPager.setPageTransformer(true, new DepthPageTransformer());
            return true;
        } else if (id == R.id.drawer) {
            viewPager.setPageTransformer(true, new DrawerTransformer());
            return true;
        } else if (id == R.id.draw_from_back) {
            viewPager.setPageTransformer(true, new DrawFromBackTransformer());
            return true;
        } else if (id == R.id.flip_horizontal) {
            viewPager.setPageTransformer(true, new FlipHorizontalTransformer());
            return true;
        } else if (id == R.id.flip_vertical) {
            viewPager.setPageTransformer(true, new FlipVerticalTransformer());
            return true;
        } else if (id == R.id.foreground_to_backgroud) {
            viewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
            return true;
        } else if (id == R.id.rotate_down) {
            viewPager.setPageTransformer(true, new RotateDownTransformer());
            return true;
        } else if (id == R.id.rotate_up) {
            viewPager.setPageTransformer(true, new RotateUpTransformer());
            return true;
        } else if (id == R.id.scale_in_out) {
            viewPager.setPageTransformer(true, new ScaleInOutTransformer());
            return true;
        } else if (id == R.id.tablet) {
            viewPager.setPageTransformer(true, new TabletTransformer());
            return true;
        } else if (id == R.id.zoom_in) {
            viewPager.setPageTransformer(true, new ZoomInTransformer());
            return true;
        } else if (id == R.id.zoom_out) {
            viewPager.setPageTransformer(true, new ZoomOutTransformer());
            return true;
        } else if (id == R.id.zoom_out_page) {
            viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            return true;
        } else if (id == R.id.zoom_out_slide) {
            viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
