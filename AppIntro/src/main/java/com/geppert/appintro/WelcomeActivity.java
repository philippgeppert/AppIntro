/*
 * *
 *  * Created by Philipp Geppert on 12/27/20 11:42 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 12/27/20 11:42 AM
 *
 */

package com.geppert.appintro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;


public class WelcomeActivity extends AppCompatActivity {

    private static final String TAG = "WelcomeActivity";

    private ViewPager2 viewPager;
    private ViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Button btnSkip, btnNext;
    private PrefManager prefManager;

    public WelcomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnSkip = findViewById(R.id.btn_skip);
        btnNext = findViewById(R.id.btn_next);


        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();


        myViewPagerAdapter = new ViewPagerAdapter(this, IntroBuilder.DrawableList, IntroBuilder.HeaderList, IntroBuilder.DescriptionList);
        viewPager.setAdapter(myViewPagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                // changing the next button text 'NEXT' / 'GOT IT'
                if (position == IntroBuilder.HeaderList.size() - 1) {
                    // last page. make button text to GOT IT
                    btnNext.setText(getString(R.string.start));
                    btnSkip.setVisibility(View.GONE);
                } else {
                    // still pages are left
                    btnNext.setText(getString(R.string.next));
                    btnSkip.setVisibility(View.VISIBLE);
                }
                changeActiveDot(position);
            }
        });

        btnSkip.setOnClickListener(v -> launchHomeScreen());

        btnNext.setOnClickListener(v -> {
            int current = getItem(+1);
            if (current < IntroBuilder.HeaderList.size()) {
                // move to next screen
                viewPager.setCurrentItem(current);
                changeActiveDot(current);
                Log.e(TAG, "onCreate: " + current);
            } else {
                launchHomeScreen();
            }
        });
    }


    private void changeActiveDot(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.dot_active));
            } else {
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.dot_inactiv));
            }
        }
    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[IntroBuilder.HeaderList.size()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(this, R.color.dot_inactiv));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(ContextCompat.getColor(this, R.color.dot_active));
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, IntroBuilder.nextActivity));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }


    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * View pager adapter
     */
    public static class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

        private final List<Integer> Drawables;
        private final List<String> Titles;
        private final List<String> Descriptions;
        private final LayoutInflater mInflater;


        ViewPagerAdapter(Context context, List<Integer> drawables, List<String> titles, List<String> descriptions) {
            this.mInflater = LayoutInflater.from(context);
            this.Drawables = drawables;
            this.Titles = titles;
            this.Descriptions = descriptions;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.layout_slide, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position < Drawables.size()) {
                Integer drawable = Drawables.get(position);
                holder.ivIcon.setImageResource(drawable);
            }

            if (position < Titles.size()) {
                String title = Titles.get(position);
                holder.tvTitle.setText(title);
            }

            if (position < Descriptions.size()) {
                String description = Descriptions.get(position);
                holder.tvDescription.setText(description);

            }
        }

        @Override
        public int getItemCount() {
            return Titles.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivIcon;
            TextView tvTitle;
            TextView tvDescription;
            RelativeLayout relativeLayout;

            ViewHolder(View itemView) {
                super(itemView);
                ivIcon = itemView.findViewById(R.id.ivIcon);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvDescription = itemView.findViewById(R.id.tvDescription);
                relativeLayout = itemView.findViewById(R.id.fragment_layout);
            }
        }

    }
}
