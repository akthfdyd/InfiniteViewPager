
package com.example.intiniteviewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlatinumMPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int startNum;
    private int endNum;
    private int itemHeight;

    public PlatinumMPagerAdapter(Context context, int startNum, int endNum, int itemHeight) {
        mContext = context;
        this.startNum = startNum;
        this.endNum = endNum;
        this.itemHeight = itemHeight;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView view = new TextView(mContext);
        view.setHeight(itemHeight);
        if (position > startNum) {
            view.setText("" + (position + 1 - startNum));
            initBackgroundColor(position, view);
        } else if (position < endNum) {
            view.setText("" + (position + 1 - startNum));
            initBackgroundColor(position, view);
        }
        int positionOverScrollLeft = position - startNum + endNum + 1;
        int positionOverScrollRight = position + startNum - endNum - 1;
        if (position < startNum) {
            view.setText("" + (positionOverScrollLeft + 1 - startNum));
            initBackgroundColor(positionOverScrollLeft, view);
        } else if (position > endNum) {
            view.setText("" + (positionOverScrollRight + 1 - startNum));
            initBackgroundColor(positionOverScrollRight, view);
        }
        view.setTextColor(Color.parseColor("#ffffff"));
        view.setGravity(Gravity.CENTER);
        container.addView(view);
        return view;
    }

    private void initBackgroundColor(int position, TextView view) {
        view.setBackgroundColor(Color
                .argb(255, position * 25, position * 5, position * 25));
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
}
