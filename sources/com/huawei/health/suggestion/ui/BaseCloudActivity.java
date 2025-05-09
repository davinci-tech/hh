package com.huawei.health.suggestion.ui;

import android.os.Bundle;
import android.util.SparseArray;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.common.Constant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import defpackage.gcd;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes4.dex */
public abstract class BaseCloudActivity extends com.huawei.ui.commonui.base.BaseActivity {
    private static final String TAG = "Suggestion_BaseCloudActivity";
    private int mGetUiCallbackCount;
    private AtomicInteger mCloudTaskCount = new AtomicInteger();
    private SparseArray<UiCallback> mUiCallbacks = new SparseArray<>();

    protected abstract void bindView();

    protected abstract void initCloudData();

    protected abstract void initLayout();

    protected abstract void initLocalData();

    protected abstract void initViewController();

    protected void preInitCloudData() {
    }

    protected abstract void registerUiCallback();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        gcd.d(new WeakReference(this));
        setHealthTheme();
        bindView();
        preInitCloudData();
        registerUiCallback();
        initCloudData();
        addTaskCountAndCheckComplete(this.mGetUiCallbackCount);
    }

    protected void initCloudDataComplete() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.BaseCloudActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseCloudActivity.this.initLocalData();
                BaseCloudActivity.this.initLayout();
                BaseCloudActivity.this.initViewController();
            }
        });
    }

    protected void registerUiCallback(int i, UiCallback uiCallback) {
        if (uiCallback == null) {
            LogUtil.h(TAG, "uiCallback == null");
        } else {
            this.mUiCallbacks.put(i, new c(uiCallback));
        }
    }

    protected UiCallback getUiCallback(int i) {
        this.mGetUiCallbackCount++;
        return this.mUiCallbacks.get(i);
    }

    private void setHealthTheme() {
        int identifier = getResources().getIdentifier("HealthTheme", TemplateStyleRecord.STYLE, Constant.HEALTH_SERVICE_NAME);
        if (identifier == 0) {
            LogUtil.h(TAG, "onCreate if (themeId == 0)");
        } else {
            LogUtil.a(TAG, "onCreate if (themeId == 0) ELSE themeId=", Integer.valueOf(identifier));
            setTheme(identifier);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addTaskCountAndCheckComplete(int i) {
        if (this.mCloudTaskCount.addAndGet(i) == 0) {
            initCloudDataComplete();
        }
    }

    class c<T> extends UiCallback<T> {
        private AtomicBoolean c = new AtomicBoolean();
        private UiCallback<T> e;

        c(UiCallback<T> uiCallback) {
            this.e = uiCallback;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            this.e.onFailure(i, str);
            d();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onSuccess(T t) {
            this.e.onSuccess(t);
            d();
        }

        private void d() {
            LogUtil.c(BaseCloudActivity.TAG, "complete()");
            if (this.c.getAndSet(true)) {
                return;
            }
            LogUtil.c(BaseCloudActivity.TAG, "complete() !mIsComplete.getAndSet(true)");
            BaseCloudActivity.this.addTaskCountAndCheckComplete(-1);
        }
    }
}
