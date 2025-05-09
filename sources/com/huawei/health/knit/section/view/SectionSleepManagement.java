package com.huawei.health.knit.section.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.knit.section.listener.IWebViewRefreshListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixx;
import defpackage.nhj;
import defpackage.nru;
import defpackage.nsy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionSleepManagement extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private H5ProWebView f2738a;
    private LinearLayout b;
    private volatile boolean c;
    private View d;
    private c e;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    static class c implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
        private final WeakReference<SectionSleepManagement> d;

        public c(SectionSleepManagement sectionSleepManagement) {
            this.d = new WeakReference<>(sectionSleepManagement);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SectionSleepManagement sectionSleepManagement = this.d.get();
            if (sectionSleepManagement == null) {
                return;
            }
            if (sectionSleepManagement.hasSetBiEvent()) {
                LogUtil.a("SectionSleepManagement", "enter onGlobalLayout");
                sectionSleepManagement.b();
                nsy.cMf_(sectionSleepManagement, this);
                return;
            }
            sectionSleepManagement.checkVisibilityAndSetBiEvent();
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            SectionSleepManagement sectionSleepManagement = this.d.get();
            if (sectionSleepManagement == null) {
                return;
            }
            if (sectionSleepManagement.hasSetBiEvent()) {
                LogUtil.a("SectionSleepManagement", "enter onScrollChanged");
                sectionSleepManagement.b();
                nsy.cMg_(sectionSleepManagement, this);
                return;
            }
            sectionSleepManagement.checkVisibilityAndSetBiEvent();
        }
    }

    public SectionSleepManagement(Context context) {
        this(context, null);
    }

    public SectionSleepManagement(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionSleepManagement(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        e();
        return this.d;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        nsy.cMg_(this, this.e);
    }

    private void e() {
        d();
        if (this.d == null) {
            LogUtil.h("SectionSleepManagement", "initView mainView is null, start to inflate");
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.section_sleep_managerment, (ViewGroup) this, false);
            this.d = inflate;
            if (inflate == null) {
                LogUtil.a("SectionSleepManagement", "mainView is null");
                return;
            }
            this.b = (LinearLayout) inflate.findViewById(R.id.layout_webview);
        }
        if (this.e == null) {
            this.e = new c(this);
        }
    }

    private void d() {
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.health.knit.section.view.SectionSleepManagement.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                SectionSleepManagement.this.c = true;
                LogUtil.a("SectionSleepManagement", "onViewAttachedToWindow, addOnScrollChangedListener");
                if (SectionSleepManagement.this.e == null) {
                    SectionSleepManagement sectionSleepManagement = SectionSleepManagement.this;
                    sectionSleepManagement.e = new c(sectionSleepManagement);
                }
                SectionSleepManagement sectionSleepManagement2 = SectionSleepManagement.this;
                nsy.cMb_(sectionSleepManagement2, sectionSleepManagement2.e);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                if (SectionSleepManagement.this.c) {
                    LogUtil.a("SectionSleepManagement", "onViewDetachedFromWindow, removeOnAttachStateChangeListene ");
                    SectionSleepManagement.this.removeOnAttachStateChangeListener(this);
                }
            }
        });
    }

    @Override // com.huawei.health.knit.section.view.BaseSection, com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener.ViewTreeListenee
    public void biEvent() {
        HashMap hashMap = new HashMap();
        hashMap.put("client", "app");
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.e(), AnalyticsValue.SLEEP_MANAGEMENT_2030128_EVENT_VALUE.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("SectionSleepManagement", "onVisible");
        if (!nhj.a().booleanValue()) {
            LogUtil.a("SectionSleepManagement", "SleepManagement is close");
        } else {
            nhj.p();
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionSleepManagement", "start to bindViewParams, ", this);
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionSleepManagement", "no need to bind");
            return;
        }
        if (this.d == null) {
            e();
        }
        IWebViewRefreshListener iWebViewRefreshListener = (IWebViewRefreshListener) nru.c(hashMap, "LAYOUT_VIEW", IWebViewRefreshListener.class, null);
        if (iWebViewRefreshListener == null) {
            LogUtil.h("SectionSleepManagement", "listener is null");
            return;
        }
        if (this.f2738a == null) {
            this.b.removeAllViews();
            H5ProWebView h5ProWebView = new H5ProWebView(getContext());
            this.f2738a = h5ProWebView;
            this.b.addView(h5ProWebView, new LinearLayout.LayoutParams(-1, -2));
            iWebViewRefreshListener.notifyWebViewLoad(this.f2738a);
            return;
        }
        iWebViewRefreshListener.notifyDataChanged(nru.d((Map) hashMap, "BAR_CHART_TIME_INT", 0L));
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionSleepManagement";
    }
}
