package com.huawei.health.knit.section.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import androidx.core.util.Consumer;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.health.utils.vippage.ViewTreeVisibilityListener;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import defpackage.nhj;
import defpackage.nru;
import defpackage.nsf;
import defpackage.nsy;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class SectionSummary extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2743a;
    private boolean b;
    private volatile boolean c;
    private String d;
    private LinearLayout e;
    private d f;
    private H5ProWebView h;
    private int i;
    private View j;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isSupportShare() {
        return false;
    }

    public SectionSummary(Context context) {
        super(context);
        this.c = false;
        this.b = true;
    }

    private void a(Context context) {
        a();
        if (this.j == null) {
            View cKs_ = nsf.cKs_(context, R.layout.section_summary, this, false);
            this.j = cKs_;
            if (cKs_ == null) {
                ReleaseLogUtil.a("R_SectionSummary", "initView mView is null context ", context);
                return;
            }
            this.e = (LinearLayout) cKs_.findViewById(R.id.section_summary_layout);
        }
        if (this.f == null) {
            this.f = new d(this);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        a(context);
        return this.j;
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.c("R_SectionSummary", "bindParamsToView currentParams ", hashMap);
        this.i = nru.d((Map) hashMap, "PAGE_TYPE", 0);
        this.d = nru.b(hashMap, "PAGE_ID", "");
        this.f2743a = ((Boolean) nru.c(hashMap, "is_scroll_to_summary", Boolean.class, false)).booleanValue();
        Consumer consumer = (Consumer) nru.c(hashMap, "CALL_BACK", Consumer.class, null);
        if (consumer == null) {
            ReleaseLogUtil.a("R_SectionSummary", "bindParamsToView consumer is null");
            return;
        }
        Context context = getContext();
        if (this.e == null) {
            ReleaseLogUtil.a("R_SectionSummary", "bindParamsToView mLayout is null context ", context);
            a(context);
        }
        LinearLayout linearLayout = this.e;
        if (linearLayout == null) {
            ReleaseLogUtil.a("R_SectionSummary", "bindParamsToView mLayout is null");
            return;
        }
        if (this.h != null) {
            ReleaseLogUtil.b("R_SectionSummary", "webView is not null");
            return;
        }
        linearLayout.removeAllViews();
        H5ProWebView h5ProWebView = new H5ProWebView(context);
        this.h = h5ProWebView;
        this.e.addView(h5ProWebView, new ViewGroup.LayoutParams(-1, -2));
        consumer.accept(this.h);
        d();
    }

    private void d() {
        if (!this.f2743a || !this.b) {
            ReleaseLogUtil.b("R_SectionSummary", "mIsCardShowSummary is false");
            return;
        }
        if (this.e == null || getKnitFragment() == null || getKnitFragment().getHealthScrollView() == null) {
            ReleaseLogUtil.b("R_SectionSummary", "view is null");
        } else {
            final HealthScrollView healthScrollView = getKnitFragment().getHealthScrollView();
            healthScrollView.postDelayed(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionSummary.3
                @Override // java.lang.Runnable
                public void run() {
                    SectionSummary.this.b = false;
                    int[] iArr = new int[2];
                    healthScrollView.getLocationInWindow(iArr);
                    int[] iArr2 = new int[2];
                    SectionSummary.this.e.getLocationOnScreen(iArr2);
                    healthScrollView.scrollTo(0, iArr2[1] - iArr[1]);
                }
            }, 1000L);
        }
    }

    static class d implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
        private final WeakReference<SectionSummary> b;

        d(SectionSummary sectionSummary) {
            this.b = new WeakReference<>(sectionSummary);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            SectionSummary sectionSummary = this.b.get();
            if (sectionSummary != null && ViewTreeVisibilityListener.Zx_(sectionSummary)) {
                sectionSummary.c();
                nsy.cMf_(sectionSummary, this);
            }
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            SectionSummary sectionSummary = this.b.get();
            if (sectionSummary != null && ViewTreeVisibilityListener.Zx_(sectionSummary)) {
                sectionSummary.c();
                nsy.cMg_(sectionSummary, this);
            }
        }
    }

    private void a() {
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.huawei.health.knit.section.view.SectionSummary.2
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                SectionSummary.this.c = true;
                LogUtil.c("R_SectionSummary", "onViewAttachedToWindow, addOnScrollChangedListener");
                if (SectionSummary.this.f == null) {
                    SectionSummary sectionSummary = SectionSummary.this;
                    sectionSummary.f = new d(sectionSummary);
                }
                SectionSummary sectionSummary2 = SectionSummary.this;
                nsy.cMb_(sectionSummary2, sectionSummary2.f);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                if (SectionSummary.this.c) {
                    LogUtil.c("R_SectionSummary", "onViewDetachedFromWindow, removeOnAttachStateChangeListene");
                    SectionSummary.this.removeOnAttachStateChangeListener(this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (!TextUtils.isEmpty(this.d)) {
            nhj.c(this.d, this.f2743a);
            return;
        }
        Boolean a2 = nhj.a();
        LogUtil.c("R_SectionSummary", "onVisible isActiveSleepManagement ", a2);
        if (Boolean.TRUE.equals(a2)) {
            return;
        }
        if (1073 == this.i) {
            nhj.p();
        }
        if (1077 == this.i) {
            nhj.b(nhj.b(), System.currentTimeMillis());
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "R_SectionSummary";
    }
}
