package com.huawei.health.knit.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ixx;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class BaseKnitDataProvider<T> implements KnitDataProvider<T> {
    public static final int INVALID_GROUP_ID = -1;
    private static final String TAG = "BaseKnitDataProvider";
    private boolean mIsActive = true;
    private int mGroupId = -1;
    private Bundle mExtra = new Bundle();

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public List<KnitDataProvider<T>> getDataProviderList() {
        return null;
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void loadDataLite(Context context) {
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, T t) {
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void updateGpsSignal(int i) {
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.mIsActive;
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void setIsActive(boolean z) {
        this.mIsActive = z;
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void setGroupId(int i) {
        this.mGroupId = i;
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public int getGroupId() {
        return this.mGroupId;
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public void setExtra(Bundle bundle) {
        if (this.mExtra == null || bundle == null || bundle.isEmpty()) {
            return;
        }
        this.mExtra.putAll(bundle);
    }

    @Override // com.huawei.health.knit.data.KnitDataProvider
    public Bundle getExtra() {
        return this.mExtra;
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onCreate() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onCreate");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onResume");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onPause() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onPause");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onStop() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onStop");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onDestroy");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onActivityResult");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void setUserVisibleHint(boolean z) {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "setUserVisibleHint");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onConfigurationChanged() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onConfigurationChanged");
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onMultiWindowModeChanged() {
        LogUtil.a("BaseKnitDataProvider_" + getLogTag(), "onMultiWindowModeChanged");
    }

    public static class d implements OnClickSectionListener {
        String d;

        public d() {
        }

        public d(String str) {
            this.d = str;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            c();
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
            c();
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
            c();
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
            c();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c();
            ViewClickInstrumentation.clickOnView(view);
        }

        private void c() {
            if (TextUtils.isEmpty(this.d)) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), this.d, hashMap, 0);
        }
    }

    protected String getLogTag() {
        return TAG;
    }
}
