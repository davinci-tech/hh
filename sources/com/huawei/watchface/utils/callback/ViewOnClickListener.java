package com.huawei.watchface.utils.callback;

import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;

/* loaded from: classes9.dex */
public abstract class ViewOnClickListener implements View.OnClickListener {
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static final String TAG = "ViewOnClickListener";
    private static long lastClickTime;

    public abstract void onMultiClick(View view);

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (isSeriesClick()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            onMultiClick(view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static boolean isSeriesClick() {
        return isSeriesClick(500);
    }

    public static boolean isSeriesClick(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - lastClickTime) < i) {
            return true;
        }
        lastClickTime = currentTimeMillis;
        return false;
    }
}
