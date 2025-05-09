package com.huawei.operation.h5pro.jsmodules.trade;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.core.WebViewConfigChangeListener;
import com.huawei.health.h5pro.dfx.bi.Analyzer;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProEventInterceptor;
import com.huawei.health.h5pro.vengine.H5ProNativeView;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.health.h5pro.vengine.H5ResultCallback;
import com.huawei.health.vip.api.VipApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.operation.h5pro.jsmodules.trade.TradePayViewParamObj;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.TradeViewInfo;
import defpackage.gla;
import defpackage.jdx;
import defpackage.njn;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class JsTradeApi extends JsBaseModule implements WebViewConfigChangeListener {
    private static final long CALLBACK_NO = -1;
    private static final int COUNT_DOWN_NUM = 1;
    private H5ProViewControls mH5ProViewControls;
    private String mTradeViewId;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        if (this.mH5ProInstance != null) {
            this.mH5ProViewControls = this.mH5ProInstance.getViewControls();
        }
    }

    @JavascriptInterface
    public void showTradeView(long j, String str) {
        LogUtil.i(this.TAG, "showTradeView");
        showNativeTradeView(j, str, false);
    }

    private void showNativeTradeView(final long j, String str, boolean z) {
        final View tradePayViewOfMainThread;
        if (TextUtils.isEmpty(str)) {
            if (j >= 0) {
                onFailureCallback(j, "param is empty");
                return;
            }
            return;
        }
        TradePayViewParamObj tradePayViewParam = getTradePayViewParam(j, str);
        if (tradePayViewParam == null) {
            return;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            tradePayViewOfMainThread = getTradePayView(tradePayViewParam);
        } else {
            tradePayViewOfMainThread = getTradePayViewOfMainThread(tradePayViewParam);
        }
        if (tradePayViewOfMainThread == null) {
            if (j >= 0) {
                onFailureCallback(j, "tradePayView is null");
                return;
            }
            return;
        }
        tradePayViewOfMainThread.setLayoutParams(getTradeViewLayoutParams(tradePayViewOfMainThread));
        if (this.mH5ProViewControls == null) {
            if (j >= 0) {
                onFailureCallback(j, "H5ProViewControls is null");
                return;
            }
            return;
        }
        H5ProNativeView h5ProNativeView = new H5ProNativeView(tradePayViewOfMainThread, str);
        h5ProNativeView.setEventInterceptor(new H5ProEventInterceptor() { // from class: com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi$$ExternalSyntheticLambda4
            @Override // com.huawei.health.h5pro.vengine.H5ProEventInterceptor
            public final void onEvent(int i, H5ResultCallback h5ResultCallback) {
                JsTradeApi.this.m754x8abf1444(tradePayViewOfMainThread, i, h5ResultCallback);
            }
        });
        if (z) {
            this.mH5ProViewControls.updateNativeView(this.mTradeViewId, h5ProNativeView);
        } else {
            this.mTradeViewId = this.mH5ProViewControls.showNativeView(h5ProNativeView);
        }
        if (j >= 0) {
            if (!TradePayViewParamObj.ViewType.TRADE.viewType.equalsIgnoreCase(tradePayViewParam.getViewType())) {
                onSuccessCallback(j);
            } else if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                onSuccessCallback(j, getTradePayViewAttributeValue(tradePayViewOfMainThread));
            } else {
                LogUtil.i(this.TAG, "showNativeTradeView: main thread");
                jdx.b(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        JsTradeApi.this.m755xb4136985(j, tradePayViewOfMainThread);
                    }
                });
            }
        }
    }

    /* renamed from: lambda$showNativeTradeView$1$com-huawei-operation-h5pro-jsmodules-trade-JsTradeApi, reason: not valid java name */
    /* synthetic */ void m755xb4136985(long j, View view) {
        onSuccessCallback(j, getTradePayViewAttributeValue(view));
    }

    private HashMap<String, Object> getTradePayViewAttributeValue(final View view) {
        final HashMap<String, Object> hashMap = new HashMap<>();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ViewTreeObserver.OnDrawListener onDrawListener = new ViewTreeObserver.OnDrawListener() { // from class: com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnDrawListener
            public final void onDraw() {
                JsTradeApi.this.m752xcf3778e5(view, countDownLatch, hashMap);
            }
        };
        view.getViewTreeObserver().addOnDrawListener(onDrawListener);
        try {
            boolean await = countDownLatch.await(3L, TimeUnit.SECONDS);
            LogUtil.i(this.TAG, "getTradePayViewAttributeValue: await -> " + await);
            view.getViewTreeObserver().removeOnDrawListener(onDrawListener);
        } catch (IllegalStateException | InterruptedException e) {
            LogUtil.w(this.TAG, "getTradePayViewAttributeValue: exception -> " + e.getMessage());
        }
        LogUtil.i(this.TAG, "getTradePayViewAttributeValue: return");
        return hashMap;
    }

    /* renamed from: lambda$getTradePayViewAttributeValue$2$com-huawei-operation-h5pro-jsmodules-trade-JsTradeApi, reason: not valid java name */
    /* synthetic */ void m752xcf3778e5(View view, CountDownLatch countDownLatch, HashMap hashMap) {
        if (view.getVisibility() == 8) {
            countDownLatch.countDown();
            return;
        }
        int height = view.getHeight();
        if (height <= 0) {
            return;
        }
        LogUtil.i(this.TAG, "getTradePayViewAttributeValue: viewHeight -> " + height);
        hashMap.put("height", Integer.valueOf(height));
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: processBackEvent, reason: merged with bridge method [inline-methods] */
    public void m754x8abf1444(View view, int i, final H5ResultCallback h5ResultCallback) {
        if (i == 2) {
            njn.cvi_(view, new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi$$ExternalSyntheticLambda3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    JsTradeApi.lambda$processBackEvent$4(H5ResultCallback.this, i2, obj);
                }
            });
        } else {
            h5ResultCallback.onResult(true);
        }
    }

    static /* synthetic */ void lambda$processBackEvent$4(H5ResultCallback h5ResultCallback, int i, Object obj) {
        if (i == 0) {
            h5ResultCallback.onResult(true);
        } else {
            h5ResultCallback.onResult(false);
        }
    }

    private RelativeLayout.LayoutParams getTradeViewLayoutParams(View view) {
        RelativeLayout.LayoutParams layoutParams;
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(layoutParams2);
        }
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        return layoutParams;
    }

    private TradePayViewParamObj getTradePayViewParam(long j, String str) {
        TradePayViewParamObj tradePayViewParamObj;
        try {
            tradePayViewParamObj = (TradePayViewParamObj) new Gson().fromJson(str, TradePayViewParamObj.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.e(this.TAG, "JsonSyntaxException");
            tradePayViewParamObj = null;
        }
        if ((tradePayViewParamObj == null || TextUtils.isEmpty(tradePayViewParamObj.getViewType())) && j >= 0) {
            onFailureCallback(j, "Invalid parameter");
        }
        return tradePayViewParamObj;
    }

    private View getTradePayViewOfMainThread(final TradePayViewParamObj tradePayViewParamObj) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final View[] viewArr = {null};
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.trade.JsTradeApi$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                JsTradeApi.this.m753xfa3ae84d(viewArr, tradePayViewParamObj, countDownLatch);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            LogUtil.e(this.TAG, "getTradePayViewOfMainThread: InterruptedException");
            viewArr[0] = null;
        }
        return viewArr[0];
    }

    /* renamed from: lambda$getTradePayViewOfMainThread$5$com-huawei-operation-h5pro-jsmodules-trade-JsTradeApi, reason: not valid java name */
    /* synthetic */ void m753xfa3ae84d(View[] viewArr, TradePayViewParamObj tradePayViewParamObj, CountDownLatch countDownLatch) {
        viewArr[0] = getTradePayView(tradePayViewParamObj);
        countDownLatch.countDown();
    }

    private View getTradePayView(TradePayViewParamObj tradePayViewParamObj) {
        if (TradePayViewParamObj.ViewType.VIP.viewType.equalsIgnoreCase(tradePayViewParamObj.getViewType())) {
            return getVipView(tradePayViewParamObj.getVipInfo());
        }
        if (TradePayViewParamObj.ViewType.TRADE.viewType.equalsIgnoreCase(tradePayViewParamObj.getViewType())) {
            return getTradeView(tradePayViewParamObj.getTradeInfo());
        }
        return null;
    }

    private View getVipView(TradePayViewParamObj.VipViewInfo vipViewInfo) {
        if (vipViewInfo != null) {
            VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
            if (vipApi != null && (this.mContext instanceof Activity)) {
                return vipApi.getVipPayView((Activity) this.mContext, vipViewInfo.getPackageName(), vipViewInfo.getPath(), getVipMap(Analyzer.getGlobalParams()));
            }
            LogUtil.w(this.TAG, "getVipView: Invalid parameter 'vipApi' or 'mContext'");
        } else {
            LogUtil.w(this.TAG, "getVipView: vipInfo is null");
        }
        return null;
    }

    private View getTradeView(TradeViewInfo tradeViewInfo) {
        if (tradeViewInfo != null) {
            TradeViewApi tradeViewApi = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
            if (tradeViewApi != null && this.mContext != null) {
                tradeViewInfo.setBiParams(Analyzer.getGlobalParams());
                return tradeViewApi.getTradeView(this.mContext, tradeViewInfo);
            }
            LogUtil.w(this.TAG, "getTradeView: tradeViewApi or mContext is null");
        } else {
            LogUtil.w(this.TAG, "getTradeView: tradeInfo is null");
        }
        return null;
    }

    private Map<String, Object> getVipMap(Map<String, String> map) {
        HashMap hashMap = new HashMap(16);
        if (map == null) {
            return hashMap;
        }
        hashMap.putAll(map);
        return hashMap;
    }

    @JavascriptInterface
    public void removeTradeView(long j) {
        LogUtil.i(this.TAG, "removeTradeView");
        if (this.mH5ProViewControls == null) {
            onFailureCallback(j, "removeTradeView: mH5ProViewControls is null");
        } else {
            if (TextUtils.isEmpty(this.mTradeViewId)) {
                onFailureCallback(j, "removeTradeView: tradeViewId is null");
                return;
            }
            this.mH5ProViewControls.removeNativeView(this.mTradeViewId);
            this.mTradeViewId = "";
            onSuccessCallback(j);
        }
    }

    @JavascriptInterface
    public void removeAllTradeView(long j) {
        LogUtil.i(this.TAG, "removeAllTradeView");
        H5ProViewControls h5ProViewControls = this.mH5ProViewControls;
        if (h5ProViewControls == null) {
            onFailureCallback(j, "removeAllTradeView: mH5ProViewControls is null");
            return;
        }
        h5ProViewControls.removeAllNativeView();
        this.mTradeViewId = "";
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void setDiscountPeriod(String str) {
        gla.c(str);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.mH5ProViewControls = null;
    }

    @Override // com.huawei.health.h5pro.core.WebViewConfigChangeListener
    public void onConfigurationChanged() {
        LogUtil.i(this.TAG, "onConfigurationChanged -> start.");
        if (this.mH5ProViewControls == null) {
            LogUtil.w(this.TAG, "onConfigurationChanged -> mH5ProViewControls is null");
            return;
        }
        if (TextUtils.isEmpty(this.mTradeViewId)) {
            LogUtil.w(this.TAG, "onConfigurationChanged -> mTradeViewId is null");
            return;
        }
        H5ProNativeView nativeView = this.mH5ProViewControls.getNativeView(this.mTradeViewId);
        if (nativeView != null) {
            LogUtil.i(this.TAG, "onConfigurationChanged -> reload tradeView.");
            showNativeTradeView(-1L, nativeView.getData(), true);
        }
    }
}
