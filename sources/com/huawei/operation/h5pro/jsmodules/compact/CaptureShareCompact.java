package com.huawei.operation.h5pro.jsmodules.compact;

import android.content.Context;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.operation.adapter.OnCaptureCallback;
import com.huawei.operation.adapter.OnCaptureExtCallback;
import com.huawei.operation.share.CaptureUtils;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class CaptureShareCompact implements OnCaptureCallback, OnCaptureExtCallback {
    private Context mContext;
    private H5ProInstance mInstance;

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onCustomUserPrefSet(String str, String str2, String str3) {
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onParseFunction(String str) {
    }

    public CaptureShareCompact(Context context, H5ProInstance h5ProInstance) {
        this.mInstance = h5ProInstance;
        this.mContext = context;
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onCapture(String str) {
        new CaptureUtils(this.mContext).capture(this.mInstance.getWebView(), str);
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onNoGranted(String str) {
        new CaptureUtils(this.mContext).captureNoPermission(this.mInstance.getWebView(), str);
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onShare(String str) {
        new CaptureUtils(this.mContext).share(str);
    }

    @Override // com.huawei.operation.adapter.OnCaptureExtCallback
    public void onShare(String str, String str2, HashMap<String, Object> hashMap) {
        new CaptureUtils(this.mContext).share(str, str2, hashMap);
    }

    @Override // com.huawei.operation.adapter.OnCaptureCallback
    public void onShareMultiple(String str, int i) {
        new CaptureUtils(this.mContext).share(str, i);
    }
}
