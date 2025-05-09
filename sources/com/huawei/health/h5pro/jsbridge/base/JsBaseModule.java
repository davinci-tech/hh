package com.huawei.health.h5pro.jsbridge.base;

import android.content.Context;
import android.content.Intent;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import java.util.Locale;

/* loaded from: classes.dex */
public class JsBaseModule extends JsModuleBase {
    public final String TAG = String.format(Locale.ENGLISH, "H5PRO_%s", getClass().getSimpleName());
    public Context mContext;
    public H5ProInstance mH5ProInstance;

    public void onCreate(H5ProBundle h5ProBundle) {
    }

    public final <T> void onSuccessCallback(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j, T t) {
        if (h5ProJsCbkInvoker == null) {
            LogUtil.w(this.TAG, "onSuccessCallback: Callback is null");
            return;
        }
        if (t == null) {
            t = (T) 0;
        }
        h5ProJsCbkInvoker.onSuccess(t, j);
    }

    public final <T> void onSuccessCallback(long j, T t) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "onSuccessCallback: H5ProInstance is null");
        } else {
            onSuccessCallback(h5ProInstance.getJsCbkInvoker(), j, t);
        }
    }

    public final void onSuccessCallback(long j) {
        onSuccessCallback(j, 0);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public final void onMount(Context context, H5ProInstance h5ProInstance, H5ProBundle h5ProBundle) {
        this.mContext = context;
        this.mH5ProInstance = h5ProInstance;
        onCreate(h5ProBundle);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public final void onMount(Context context, H5ProInstance h5ProInstance) {
        super.onMount(context, h5ProInstance);
        onMount(context, h5ProInstance, null);
    }

    public final void onFailureCallback(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j, String str, int i) {
        if (h5ProJsCbkInvoker == null) {
            LogUtil.w(this.TAG, "onFailureCallback: Callback is null");
            return;
        }
        if (str == null) {
            str = "";
        }
        h5ProJsCbkInvoker.onFailure(i, str, j);
    }

    public final void onFailureCallback(long j, String str, int i) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "onFailureCallback: H5ProInstance is null");
        } else {
            onFailureCallback(h5ProInstance.getJsCbkInvoker(), j, str, i);
        }
    }

    public final void onFailureCallback(long j, String str) {
        onFailureCallback(j, str, -1);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.mContext = null;
        this.mH5ProInstance = null;
    }

    public final void onCompleteCallback(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, long j, String str, int i) {
        if (h5ProJsCbkInvoker == null) {
            LogUtil.w(this.TAG, "onCompleteCallback: Callback is null");
            return;
        }
        if (str == null) {
            str = "";
        }
        h5ProJsCbkInvoker.onComplete(i, str, j);
    }

    public final void onCompleteCallback(long j, String str, int i) {
        H5ProInstance h5ProInstance = this.mH5ProInstance;
        if (h5ProInstance == null) {
            LogUtil.w(this.TAG, "onCompleteCallback: H5ProInstance is null");
        } else {
            onCompleteCallback(h5ProInstance.getJsCbkInvoker(), j, str, i);
        }
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
