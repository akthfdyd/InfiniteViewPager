
package com.example.intiniteviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PagerActivity extends Activity {

    private int mStartNum = 4;
    private int mEndNum = 8;

    private LinearLayout mPlatinumMLayout;
    private PagerContainer mPlatinumMContainer;
    private ViewPager mPlatinumMViewPager;
    private int mPlatinumMViewPagerPosition;
    private PagerAdapter mPlatinumMViewPagerAdapter;
    private int mPlatinumMSize;

    private LinearLayout mSpecialMLayout;
    private ViewPager mSpecialMViewPager;
    private int mSpecialMViewPagerPosition;
    private PagerAdapter mSpecialMViewPagerAdapter;
    private int mSpecialMSize;
    private int mSpecialMListCount = 3;

    private LinearLayout mAlzzaMLayout;
    private ViewPager mAlzzaMViewPager;
    private int mAlzzaMViewPagerPosition;
    private PagerAdapter mAlzzaMViewPagerAdapter;
    private int mAlzzaMSize;
    private int mAlzzaMListCount = 2;

    private LinearLayout mNaraeMLayout;
    private PagerContainer mNaraeMContainer;
    private ViewPager mNaraeMViewPager;
    private int mNaraeMViewPagerPosition;
    private PagerAdapter mNaraeMViewPagerAdapter;
    private int mNaraeMSize;
    private int mNaraeMListCount = 2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPagerHeight();
        initLayout();
        initPlatinumMViewPager();
        initSpecialMViewPager();
        initAlzzaMViewPager();
        initNaraeMViewPager();
    }

    private void initPagerHeight() {
        mPlatinumMSize = (int) convertDpToPixel(120, this);
        mSpecialMSize = (int) convertDpToPixel(60, this);
        mAlzzaMSize = (int) convertDpToPixel(80, this);
        mNaraeMSize = (int) convertDpToPixel(80, this);
    }

    private void initLayout() {
        mPlatinumMLayout = findViewById(R.id.container_platinum);
        mSpecialMLayout = findViewById(R.id.container_special);
        mAlzzaMLayout = findViewById(R.id.container_alzza);
        mNaraeMLayout = findViewById(R.id.container_narae);
        mPlatinumMContainer = findViewById(R.id.pager_container_platinum);
        mSpecialMViewPager = findViewById(R.id.pager_container_special);
        mAlzzaMViewPager = findViewById(R.id.pager_container_alzza);
        mNaraeMContainer = findViewById(R.id.pager_container_narae);

        setLayoutParams(mPlatinumMLayout, ViewGroup.LayoutParams.MATCH_PARENT, mPlatinumMSize
                + getSampleTitleHeight());
        setLayoutParams(mSpecialMLayout, ViewGroup.LayoutParams.MATCH_PARENT, mSpecialMSize
                * mSpecialMListCount + getSampleTitleHeight());
        setLayoutParams(mAlzzaMLayout, ViewGroup.LayoutParams.MATCH_PARENT, mAlzzaMSize
                * mAlzzaMListCount + getSampleTitleHeight());
        setLayoutParams(mNaraeMLayout, ViewGroup.LayoutParams.MATCH_PARENT, mNaraeMSize
                * mNaraeMListCount + getSampleTitleHeight());
    }

    private void initPlatinumMViewPager() {
        mPlatinumMViewPagerAdapter = new PlatinumMPagerAdapter(this, mStartNum, mEndNum,
                mPlatinumMSize);
        mPlatinumMViewPager = mPlatinumMContainer.getViewPager();
        mPlatinumMViewPager.setAdapter(mPlatinumMViewPagerAdapter);
        mPlatinumMViewPager.setOffscreenPageLimit(mPlatinumMViewPagerAdapter.getCount());
        mPlatinumMViewPager.setPageMargin(15);
        mPlatinumMViewPager.setCurrentItem(mStartNum, false);
        mPlatinumMViewPager.setClipChildren(false);
        mPlatinumMViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.7f);
                page.setScaleY(normalizedposition / 2 + 0.7f);
            }
        });
        mPlatinumMViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mPlatinumMViewPagerPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    checkOverScroll(mPlatinumMViewPagerPosition, mStartNum, mEndNum,
                            mPlatinumMViewPager);
                }
            }
        });
    }

    private void initSpecialMViewPager() {
        mSpecialMViewPagerAdapter = new SpecialMPagerAdapter(this, mStartNum, mEndNum,
                mSpecialMSize, mSpecialMListCount);
        mSpecialMViewPager.setAdapter(mSpecialMViewPagerAdapter);
        mSpecialMViewPager.setOffscreenPageLimit(mSpecialMViewPagerAdapter.getCount());
        mSpecialMViewPager.setPageMargin(15);
        mSpecialMViewPager.setCurrentItem(mStartNum, false);
        mSpecialMViewPager.setClipChildren(false);
        mSpecialMViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mSpecialMViewPagerPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    checkOverScroll(mSpecialMViewPagerPosition, mStartNum, mEndNum,
                            mSpecialMViewPager);
                }
            }
        });
    }

    private void initAlzzaMViewPager() {
        mAlzzaMViewPagerAdapter = new AlzzaMPagerAdapter(this, mStartNum, mEndNum, mAlzzaMSize,
                mAlzzaMListCount);
        mAlzzaMViewPager.setAdapter(mAlzzaMViewPagerAdapter);
        mAlzzaMViewPager.setOffscreenPageLimit(mAlzzaMViewPagerAdapter.getCount());
        mAlzzaMViewPager.setPageMargin(15);
        mAlzzaMViewPager.setCurrentItem(mStartNum, false);
        mAlzzaMViewPager.setClipChildren(false);
        mAlzzaMViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mAlzzaMViewPagerPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    checkOverScroll(mAlzzaMViewPagerPosition, mStartNum, mEndNum, mAlzzaMViewPager);
                }
            }
        });
    }

    private void initNaraeMViewPager() {
        mNaraeMViewPagerAdapter = new NaraeMPagerAdapter(this, mStartNum, mEndNum, mNaraeMSize,
                mNaraeMListCount);
        mNaraeMViewPager = mNaraeMContainer.getViewPager();
        mNaraeMViewPager.setAdapter(mNaraeMViewPagerAdapter);
        mNaraeMViewPager.setOffscreenPageLimit(mNaraeMViewPagerAdapter.getCount());
        mNaraeMViewPager.setPageMargin(15);
        mNaraeMViewPager.setCurrentItem(mStartNum, false);
        mNaraeMViewPager.setClipChildren(false);
        mNaraeMViewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mNaraeMViewPagerPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    checkOverScroll(mNaraeMViewPagerPosition, mStartNum, mEndNum, mNaraeMViewPager);
                }
            }
        });
    }

    private void checkOverScroll(int position, int startNum, int endNum,
            ViewPager viewPager) {
        if (position > endNum) {
            viewPager.setCurrentItem(startNum + (position - endNum) - 1, false);
        }
        if (position < startNum) {
            viewPager.setCurrentItem(endNum - (startNum - position) + 1, false);
        }
    }

    private void setLayoutParams(View view, int width, int height) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                width, height, 0.0F);
        view.setLayoutParams(params);
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    private int getSampleTitleHeight() {
        return (int) convertDpToPixel(30, this);
    }
}
