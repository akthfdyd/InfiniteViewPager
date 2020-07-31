
package com.example.intiniteviewpager;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NaraeMPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int startNum;
    private int endNum;
    private int itemHeight;
    private int listCnt = 2;

    public NaraeMPagerAdapter(Context context, int startNum, int endNum, int itemHeight,
            int listCnt) {
        mContext = context;
        this.startNum = startNum;
        this.endNum = endNum;
        this.itemHeight = itemHeight;
        this.listCnt = listCnt;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout lContainer = new LinearLayout(mContext);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.0F);
        lContainer.setLayoutParams(lparams);
        lContainer.setOrientation(LinearLayout.VERTICAL);
        container.addView(lContainer);
        for (int itemCnt = 0; itemCnt < listCnt; itemCnt++) {
            appendHorizontalLayout(position, lContainer);
        }
        return lContainer;
    }

    private void appendHorizontalLayout(int position, LinearLayout lContainer) {
        LinearLayout hContainer = new LinearLayout(mContext);
        LinearLayout.LayoutParams hparams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                itemHeight,
                0.0F);
        hContainer.setLayoutParams(hparams);
        hContainer.setOrientation(LinearLayout.HORIZONTAL);
        lContainer.addView(hContainer);
        appendNumberView(position, hContainer);
        appendNumberView(position, hContainer);
    }

    private void appendNumberView(int position, LinearLayout container) {
        TextView numberView = new TextView(mContext);
        numberView.setWidth((int) convertDpToPixel(160, mContext));
        numberView.setHeight(itemHeight);
        showPosition(position, numberView);
        container.addView(numberView);
    }

    private void showPosition(int position, TextView numberView) {
        if (position > startNum) {
            numberView.setText("" + (position + 1 - startNum));
            initBackgroundColor(position, numberView);
        } else if (position < endNum) {
            numberView.setText("" + (position + 1 - startNum));
            initBackgroundColor(position, numberView);
        }
        int positionOverScrollLeft = position - startNum + endNum + 1;
        int positionOverScrollRight = position + startNum - endNum - 1;
        if (position < startNum) {
            numberView.setText("" + (positionOverScrollLeft + 1 - startNum));
            initBackgroundColor(positionOverScrollLeft, numberView);
        } else if (position > endNum) {
            numberView.setText("" + (positionOverScrollRight + 1 - startNum));
            initBackgroundColor(positionOverScrollRight, numberView);
        }
        numberView.setTextColor(Color.parseColor("#ffffff"));
        numberView.setGravity(Gravity.CENTER);
    }

    private void initBackgroundColor(int position, TextView view) {
        view.setBackgroundColor(Color
                .argb(255,
                        255 - (200 + position * (20 / endNum)),
                        200 - (position * (150 / endNum)),
                        255 - (200 + position * (20 / endNum))));
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return startNum + endNum + 1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    public void setListCount(int cnt) {
        listCnt = cnt;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
