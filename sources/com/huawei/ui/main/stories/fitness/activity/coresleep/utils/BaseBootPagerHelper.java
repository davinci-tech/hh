package com.huawei.ui.main.stories.fitness.activity.coresleep.utils;

import android.content.Context;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.section.listener.HealthPageResTrigger;
import com.huawei.ui.main.stories.fitness.views.coresleep.SleepBootMaskingLayerView;
import defpackage.jdx;
import defpackage.pqg;

/* loaded from: classes6.dex */
public abstract class BaseBootPagerHelper {
    protected SleepBootMaskingLayerView mBootPageView;
    protected LinearLayout mContainer;
    protected Context mContext;
    protected HealthPageResTrigger mHealthPageResTrigger;

    protected abstract void configBootPage(LinearLayout linearLayout);

    protected abstract String getSharePreferenceString();

    public BaseBootPagerHelper(Context context) {
        this.mContext = context;
    }

    public void showBootPage(final LinearLayout linearLayout) {
        if (linearLayout == null && this.mContainer == null) {
            return;
        }
        jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper.3
            @Override // java.lang.Runnable
            public void run() {
                String sharePreferenceString = BaseBootPagerHelper.this.getSharePreferenceString();
                String d = pqg.d(BaseBootPagerHelper.this.mContext, sharePreferenceString);
                if (!"default_value".equals(d) && !String.valueOf(true).equals(d)) {
                    if (BaseBootPagerHelper.this.mHealthPageResTrigger != null) {
                        BaseBootPagerHelper.this.mHealthPageResTrigger.setIsFirstEntryNoDataSleep(false);
                    }
                } else {
                    BaseBootPagerHelper.this.showBootPageInner(linearLayout);
                    pqg.d(BaseBootPagerHelper.this.mContext, sharePreferenceString, String.valueOf(false));
                }
            }
        });
    }

    public void showBootPageInner(final LinearLayout linearLayout) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.utils.BaseBootPagerHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    BaseBootPagerHelper.this.showBootPageInner(linearLayout);
                }
            });
            return;
        }
        if (this.mContainer == null) {
            configBootPage(linearLayout);
        }
        SleepBootMaskingLayerView sleepBootMaskingLayerView = this.mBootPageView;
        if (sleepBootMaskingLayerView != null) {
            sleepBootMaskingLayerView.setVisibility(0);
        }
    }

    public void setHealthPageResTrigger(HealthPageResTrigger healthPageResTrigger) {
        this.mHealthPageResTrigger = healthPageResTrigger;
    }
}
