package com.huawei.ui.main.stories.fitness.views.bloodsugar.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface;
import defpackage.pjp;

/* loaded from: classes6.dex */
public abstract class CommonBaseMvpChartView<P extends pjp<LineChartViewInterface>> extends CommonBaseChartView implements LineChartViewInterface {
    private static final String TAG = "CommonBaseMvpChartView";
    public P mPresenter;

    protected abstract P createPresenter();

    public CommonBaseMvpChartView(Context context) {
        super(context);
        init();
    }

    public CommonBaseMvpChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CommonBaseMvpChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        Log.d(TAG, "constructor");
        P createPresenter = createPresenter();
        this.mPresenter = createPresenter;
        if (createPresenter != null) {
            createPresenter.b(this);
        }
    }

    public void onDestroy() {
        this.mPresenter.b();
    }
}
