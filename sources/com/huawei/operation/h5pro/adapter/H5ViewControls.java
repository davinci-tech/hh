package com.huawei.operation.h5pro.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.h5pro.vengine.H5ProNativeView;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.hwlogsmodel.LogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class H5ViewControls implements H5ProViewControls {
    private static final String TAG = "H5PRO_H5ViewControls";
    private int mNativeViewMaxId = 0;
    private Map<String, H5ProNativeView> mNativeViews;
    private WeakReference<RelativeLayout> mRootViewRef;

    public H5ViewControls(RelativeLayout relativeLayout) {
        LogUtil.a(TAG, "H5ViewControls start.");
        this.mRootViewRef = new WeakReference<>(relativeLayout);
        this.mNativeViews = new HashMap();
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public String showNativeView(final View view) {
        this.mNativeViewMaxId++;
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.adapter.H5ViewControls.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(H5ViewControls.TAG, "showNativeView");
                View view2 = view;
                if (view2 != null && view2 != null) {
                    if (H5ViewControls.this.mRootViewRef != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) H5ViewControls.this.mRootViewRef.get();
                        if (relativeLayout == null) {
                            LogUtil.h(H5ViewControls.TAG, "rootView is null");
                            return;
                        }
                        H5ViewControls.this.mNativeViews.put(String.valueOf(H5ViewControls.this.mNativeViewMaxId), new H5ProNativeView(view));
                        relativeLayout.addView(view);
                        return;
                    }
                    LogUtil.h(H5ViewControls.TAG, "mRootViewRef is null");
                    return;
                }
                LogUtil.h(H5ViewControls.TAG, "nativeView is null");
            }
        });
        return String.valueOf(this.mNativeViewMaxId);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public String showNativeView(final H5ProNativeView h5ProNativeView) {
        this.mNativeViewMaxId++;
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.adapter.H5ViewControls.2
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(H5ViewControls.TAG, "showNativeView");
                H5ProNativeView h5ProNativeView2 = h5ProNativeView;
                if (h5ProNativeView2 != null && h5ProNativeView2.getView() != null) {
                    if (H5ViewControls.this.mRootViewRef != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) H5ViewControls.this.mRootViewRef.get();
                        if (relativeLayout != null) {
                            H5ViewControls.this.mNativeViews.put(String.valueOf(H5ViewControls.this.mNativeViewMaxId), h5ProNativeView);
                            relativeLayout.addView(h5ProNativeView.getView());
                            return;
                        } else {
                            LogUtil.h(H5ViewControls.TAG, "rootView is null");
                            return;
                        }
                    }
                    LogUtil.h(H5ViewControls.TAG, "mRootViewRef is null");
                    return;
                }
                LogUtil.h(H5ViewControls.TAG, "nativeView is null");
            }
        });
        return String.valueOf(this.mNativeViewMaxId);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public void removeNativeView(final String str) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.adapter.H5ViewControls.3
            @Override // java.lang.Runnable
            public void run() {
                H5ProNativeView h5ProNativeView;
                RelativeLayout relativeLayout;
                LogUtil.a(H5ViewControls.TAG, "removeNativeView");
                if (H5ViewControls.this.mNativeViews == null || !H5ViewControls.this.mNativeViews.containsKey(str)) {
                    h5ProNativeView = null;
                } else {
                    h5ProNativeView = (H5ProNativeView) H5ViewControls.this.mNativeViews.get(str);
                    H5ViewControls.this.mNativeViews.remove(str);
                }
                if (H5ViewControls.this.mRootViewRef == null || h5ProNativeView == null || (relativeLayout = (RelativeLayout) H5ViewControls.this.mRootViewRef.get()) == null) {
                    return;
                }
                relativeLayout.removeView(h5ProNativeView.getView());
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public void removeAllNativeView() {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.adapter.H5ViewControls.4
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(H5ViewControls.TAG, "removeAllNativeView");
                if (H5ViewControls.this.mNativeViews != null && H5ViewControls.this.mNativeViews.size() != 0) {
                    for (H5ProNativeView h5ProNativeView : H5ViewControls.this.mNativeViews.values()) {
                        if (H5ViewControls.this.mRootViewRef != null && H5ViewControls.this.mRootViewRef.get() != null && h5ProNativeView != null) {
                            ((RelativeLayout) H5ViewControls.this.mRootViewRef.get()).removeView(h5ProNativeView.getView());
                        }
                    }
                    H5ViewControls.this.mNativeViews.clear();
                    return;
                }
                LogUtil.c(H5ViewControls.TAG, "no custom native view");
            }
        });
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public H5ProNativeView getNativeView(String str) {
        Map<String, H5ProNativeView> map = this.mNativeViews;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.mNativeViews.get(str);
    }

    @Override // com.huawei.health.h5pro.vengine.H5ProViewControls
    public void updateNativeView(final String str, final H5ProNativeView h5ProNativeView) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.adapter.H5ViewControls.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a(H5ViewControls.TAG, "updateNativeView");
                if (TextUtils.isEmpty(str)) {
                    LogUtil.h(H5ViewControls.TAG, "updateNativeView -> viewId is null");
                    return;
                }
                H5ProNativeView h5ProNativeView2 = h5ProNativeView;
                if (h5ProNativeView2 != null && h5ProNativeView2.getView() != null) {
                    if (H5ViewControls.this.mRootViewRef != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) H5ViewControls.this.mRootViewRef.get();
                        if (relativeLayout != null) {
                            if (H5ViewControls.this.mNativeViews == null) {
                                H5ViewControls.this.mNativeViews = new HashMap();
                            }
                            H5ProNativeView h5ProNativeView3 = (H5ProNativeView) H5ViewControls.this.mNativeViews.get(str);
                            if (h5ProNativeView3 != null && h5ProNativeView3.getView() != null) {
                                relativeLayout.removeView(h5ProNativeView3.getView());
                            }
                            H5ViewControls.this.mNativeViews.put(String.valueOf(H5ViewControls.this.mNativeViewMaxId), h5ProNativeView);
                            relativeLayout.addView(h5ProNativeView.getView());
                            return;
                        }
                        LogUtil.a(H5ViewControls.TAG, "updateNativeView -> rootView is null");
                        return;
                    }
                    LogUtil.h(H5ViewControls.TAG, "updateNativeView -> mRootViewRef is null");
                    return;
                }
                LogUtil.h(H5ViewControls.TAG, "updateNativeView -> nativeView is null");
            }
        });
    }
}
