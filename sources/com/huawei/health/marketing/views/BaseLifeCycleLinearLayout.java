package com.huawei.health.marketing.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.huawei.health.knit.data.IKnitLifeCycle;

/* loaded from: classes3.dex */
public class BaseLifeCycleLinearLayout extends LinearLayout implements IKnitLifeCycle {
    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onConfigurationChanged() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onCreate() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onMultiWindowModeChanged() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onPause() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onStop() {
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void setUserVisibleHint(boolean z) {
    }

    public BaseLifeCycleLinearLayout(Context context) {
        super(context);
    }

    public BaseLifeCycleLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseLifeCycleLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BaseLifeCycleLinearLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
