package com.huawei.operation.h5pro.jsmodules.audiocontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.openalliance.ad.constant.VastAttribute;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class AudioControlApi extends JsBaseModule {
    static final String BROADCAST_ACTION_UPDATE_H5_AUDIO_PAGE = "BROADCAST_ACTION_UPDATE_H5_AUDIO_PAGE";
    private static final String TAG = "H5PRO_AudioControlApi";
    private UpdateH5AudioPageReceiver mUpdateH5AudioPageReceiver;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        if (this.mUpdateH5AudioPageReceiver != null || this.mContext == null) {
            return;
        }
        this.mUpdateH5AudioPageReceiver = new UpdateH5AudioPageReceiver(this.mH5ProInstance.getWebView());
        BroadcastManagerUtil.bFA_(this.mContext, this.mUpdateH5AudioPageReceiver, new IntentFilter(BROADCAST_ACTION_UPDATE_H5_AUDIO_PAGE), LocalBroadcast.c, null);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        if (this.mUpdateH5AudioPageReceiver != null && this.mContext != null) {
            this.mContext.unregisterReceiver(this.mUpdateH5AudioPageReceiver);
            this.mUpdateH5AudioPageReceiver = null;
        }
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
        AudioControlOperate.INSTANCE.stopServiceSynchronized();
    }

    @JavascriptInterface
    public void closeControl() {
        LogUtil.i(TAG, "closeControl");
        AudioControlOperate.INSTANCE.stopServiceSynchronized();
    }

    @JavascriptInterface
    public void pause() {
        LogUtil.i(TAG, VastAttribute.PAUSE);
        AudioControlOperate.INSTANCE.pause();
    }

    @JavascriptInterface
    public void play(String str) {
        LogUtil.i(TAG, "play");
        if (TextUtils.isEmpty(str)) {
            LogUtil.w(TAG, "play: param is empty");
        } else {
            AudioControlOperate.INSTANCE.play(str);
        }
    }

    static class UpdateH5AudioPageReceiver extends BroadcastReceiver {
        private static final int SAFE_INTERVAL_UPDATE = 300;
        private long mUpdateTime = 0;
        private final WeakReference<WebView> mWrWebView;

        UpdateH5AudioPageReceiver(WebView webView) {
            this.mWrWebView = new WeakReference<>(webView);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            final String str;
            if (intent == null || !AudioControlApi.BROADCAST_ACTION_UPDATE_H5_AUDIO_PAGE.equals(intent.getAction())) {
                return;
            }
            if (System.currentTimeMillis() - this.mUpdateTime <= 300) {
                LogUtil.w(AudioControlApi.TAG, "onReceive: frequent updates are prohibited");
                return;
            }
            this.mUpdateTime = System.currentTimeMillis();
            int intExtra = intent.getIntExtra(AudioControlOperate.EVENT_KEY_AUDIO, 0);
            LogUtil.i(AudioControlApi.TAG, "onReceive: audioEvent -> " + intExtra);
            if (intExtra == 1) {
                str = "javascript:audioPlay()";
            } else {
                if (intExtra != 2) {
                    LogUtil.w(AudioControlApi.TAG, "onReceive: Invalid audioEvent -> " + intExtra);
                    return;
                }
                str = "javascript:audioPause()";
            }
            final WebView webView = (WebView) GeneralUtil.getReferent(this.mWrWebView);
            if (webView == null) {
                LogUtil.w(AudioControlApi.TAG, "onReceive: webView is null");
            } else {
                webView.post(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.audiocontrol.AudioControlApi$UpdateH5AudioPageReceiver$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        webView.evaluateJavascript(str, null);
                    }
                });
            }
        }
    }
}
