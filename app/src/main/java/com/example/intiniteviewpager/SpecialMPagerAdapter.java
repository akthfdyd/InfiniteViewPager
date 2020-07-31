
package com.example.intiniteviewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SpecialMPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int startNum;
    private int endNum;
    private int itemHeight;
    private int listCnt = 4;

    public SpecialMPagerAdapter(Context context, int startNum, int endNum, int itemHeight,
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.0F);
        lContainer.setLayoutParams(params);
        lContainer.setOrientation(LinearLayout.VERTICAL);
        container.addView(lContainer);
        for (int itemCnt = 0; itemCnt < listCnt; itemCnt++) {
            appendNumberView(position, lContainer);
        }
        return lContainer;
    }

    private void appendNumberView(int position, LinearLayout lContainer) {
        TextView numberView = new TextView(mContext);
        numberView.setHeight(itemHeight);
        showPosition(position, numberView);
        lContainer.addView(numberView);
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
                        255 - (position * (150 / endNum)),
                        255 - (200 + position * (20 / endNum)),
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
}
