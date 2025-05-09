package com.huawei.ui.main.stories.health.pressure.provider;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public abstract class BaseCallback<T> implements IBaseResponseCallback, CommonUiBaseResponse {
    private static final String TAG = "BaseCallback";
    private WeakReference<T> mWeakReference;

    abstract void onCall(T t, int i, Object obj);

    public BaseCallback(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    public void onResponse(int i, Object obj) {
        T t = this.mWeakReference.get();
        if (t == null) {
            LogUtil.h(TAG, "reference is null");
        } else {
            onCall(t, i, obj);
        }
    }
}
