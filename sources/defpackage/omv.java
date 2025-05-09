package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.section.view.CustomH5ProWebview;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.homehealth.refreshcard.CardViewHolder;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes6.dex */
public final class omv extends CardViewHolder {
    private a c;
    private CustomH5ProWebview d;

    public omv(View view, Context context, boolean z) {
        super(view, context, z);
        this.d = (CustomH5ProWebview) view.findViewById(R.id.health_trend_web_view);
        BaseActivity.setViewSafeRegion(true, (LinearLayout) view.findViewById(R.id.health_trend));
        LogUtil.a("HealthTrendCardDataCardViewHolder", "begin to push health trend H5ProWebView");
        g();
        if (this.c == null) {
            this.c = new a(this, this.d);
        }
        HandlerExecutor.a(new Runnable() { // from class: oms
            @Override // java.lang.Runnable
            public final void run() {
                omv.this.a();
            }
        });
    }

    /* synthetic */ void a() {
        dcz_(this.d);
    }

    public void c() {
        nsy.cMf_(this.d, this.c);
        this.d.d();
    }

    public void e() {
        CustomH5ProWebview customH5ProWebview = this.d;
        if (customH5ProWebview != null) {
            customH5ProWebview.onResume();
        }
    }

    public void b() {
        if (this.d != null) {
            LogUtil.a("HealthTrendCardDataCardViewHolder", "h5 begin to refresh trend card");
            this.d.execJs("window.nativeEvent&&window.nativeEvent('onResume')", null);
        }
    }

    public void d() {
        if (this.d != null) {
            LogUtil.a("HealthTrendCardDataCardViewHolder", "loginChange h5 begin to refresh trend card");
            g();
        }
    }

    private void g() {
        HandlerExecutor.e(new Runnable() { // from class: omv.2
            @Override // java.lang.Runnable
            public void run() {
                bzs.e().loadEmbeddedH5(omv.this.d, "com.huawei.health.h5.health-trend", new H5ProLaunchOption.Builder().addPath("#/cardView?from=1"));
            }
        });
    }

    private void dcz_(View view) {
        if (view == null) {
            return;
        }
        nsy.cMa_(view, this.c);
    }

    static class a implements ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<View> b;
        private final WeakReference<omv> c;

        public a(omv omvVar, View view) {
            this.c = new WeakReference<>(omvVar);
            this.b = new WeakReference<>(view);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            omv omvVar = this.c.get();
            View view = this.b.get();
            if (omvVar == null || view == null || view.getVisibility() != 0) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            LogUtil.a("HealthTrendCardDataCardViewHolder", "uploadBiEvent health trend card visible, FUNCTION_CARD_2010229: ", hashMap.toString());
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_TREND_CARD_2010229.value(), hashMap, 0);
            nsy.cMf_(view, this);
        }
    }
}
