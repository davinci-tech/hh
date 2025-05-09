package com.huawei.ui.homehealth.healthtrend;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.bzs;
import defpackage.nrr;
import defpackage.omv;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes6.dex */
public class HealthTrendCardData extends AbstractBaseCardData {
    private static XmlResourceParser d;

    /* renamed from: a, reason: collision with root package name */
    private omv f9465a;
    private Context b;
    private View e;

    public HealthTrendCardData(Context context) {
        this.b = context;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        long currentTimeMillis = System.currentTimeMillis();
        bzs.e().initH5Pro();
        XmlResourceParser xmlResourceParser = d;
        if (xmlResourceParser != null) {
            try {
                this.e = layoutInflater.inflate((XmlPullParser) xmlResourceParser, viewGroup, false);
                d.close();
                d = null;
                LogUtil.a("HealthTrendCardData", "pre parser time is : ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            } catch (Throwable th) {
                d.close();
                d = null;
                throw th;
            }
        } else {
            this.e = layoutInflater.inflate(R.layout.health_trend_card, viewGroup, false);
            LogUtil.a("HealthTrendCardData", "no pre parser time is : ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        }
        this.f9465a = new omv(this.e, this.b, false);
        this.e.setVisibility(8);
        if (!LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            LogUtil.a("HealthTrendCardData", "Logined health trend show H5 webview");
            e();
        }
        LogUtil.a("HealthTrendCardData", "getCardViewHolder total time is ：", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return this.f9465a;
    }

    public static void d() {
        d = BaseApplication.e().getResources().getLayout(R.layout.health_trend_card);
    }

    private void e() {
        if (this.e == null) {
            return;
        }
        LogUtil.a("HealthTrendCardData", "show H5 webview");
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.healthtrend.HealthTrendCardData.5
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) HealthTrendCardData.this.e.getLayoutParams();
                layoutParams.height = -2;
                layoutParams.bottomMargin = nrr.e(BaseApplication.e(), 12.0f);
                HealthTrendCardData.this.e.setLayoutParams(layoutParams);
                HealthTrendCardData.this.e.setVisibility(0);
            }
        });
    }

    private void a() {
        if (this.e == null) {
            return;
        }
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.healthtrend.HealthTrendCardData.1
            @Override // java.lang.Runnable
            public void run() {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) HealthTrendCardData.this.e.getLayoutParams();
                layoutParams.height = 0;
                layoutParams.bottomMargin = 0;
                HealthTrendCardData.this.e.setLayoutParams(layoutParams);
                HealthTrendCardData.this.e.setVisibility(8);
            }
        });
    }

    private boolean b() {
        if (this.f9465a == null || this.e == null) {
            LogUtil.h("HealthTrendCardData", "ViewHolder is null");
            return false;
        }
        if (LoginInit.getInstance(BaseApplication.e()).isBrowseMode()) {
            if (this.e.getVisibility() == 0) {
                LogUtil.a("HealthTrendCardData", "loginout hide itemview");
                a();
            }
            return false;
        }
        LogUtil.a("HealthTrendCardData", "begin to refresh CardData");
        if (this.e.getVisibility() != 8) {
            return true;
        }
        LogUtil.a("HealthTrendCardData", "H5 Card is hide, need to show Card");
        e();
        return true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        LogUtil.a("HealthTrendCardData", "login change H5 need to refresh Card");
        if (b()) {
            this.f9465a.d();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        if (b()) {
            this.f9465a.e();
            this.f9465a.b();
        }
        ReleaseLogUtil.e("HealthTrendCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        omv omvVar = this.f9465a;
        if (omvVar == null) {
            LogUtil.h("HealthTrendCardData", "ViewHolder is null");
        } else {
            omvVar.c();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "HealthTrendCardData";
    }
}
