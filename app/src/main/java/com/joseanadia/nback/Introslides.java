package com.joseanadia.nback;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Introslides extends AppCompatActivity {
    ViewPager slideViewPager;
    LinearLayout dotsLayout;

    SliderAdapter sliderAdapter;

    TextView[] dots;

    Button backButton;
    Button nextButton;

    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introslides);

        slideViewPager = findViewById(R.id.slidesVP);
        dotsLayout = findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(this);

        slideViewPager.setAdapter(sliderAdapter);

        addDots(0);

        slideViewPager.addOnPageChangeListener(viewListener);

        backButton = findViewById(R.id.btn_back);
        nextButton = findViewById(R.id.btn_next);
    }

    public void addDots(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for (int i=0; i<dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getColor(R.color.lightBlue));

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getColor(R.color.darkBlue));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPage = position;

            if (position==0) {
                nextButton.setEnabled(true);
                backButton.setEnabled(false);
                backButton.setVisibility(View.INVISIBLE);

                nextButton.setText("NEXT");
                backButton.setText(" ");

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currentPage+1);
                    }
                });

            } else if (position == dots.length-1) {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);

                nextButton.setText("PLAY");
                backButton.setText("BACK");

                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currentPage-1);
                    }
                });

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Introslides.this, Start.class));
                    }
                });
            } else {
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
                backButton.setVisibility(View.VISIBLE);

                nextButton.setText("NEXT");
                backButton.setText("BACK");

                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currentPage+1);
                    }
                });

                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        slideViewPager.setCurrentItem(currentPage-1);
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}