package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mct;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mer;
import defpackage.mex;
import defpackage.mfm;
import defpackage.mht;
import defpackage.mkx;
import defpackage.mlg;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class WeekAndMonthActivity extends BaseActivity implements View.OnClickListener {
    private Context b;
    private LinearLayout c;
    private CustomTitleBar e;
    private HealthTextView f;
    private LinearLayout g;
    private RelativeLayout h;
    private HealthCardView j;
    private HealthTextView l;
    private RelativeLayout m;
    private RelativeLayout n;
    private HealthTextView o;
    private boolean d = true;
    private boolean i = false;

    /* renamed from: a, reason: collision with root package name */
    private Handler f8462a = new a(this);

    static class a extends BaseHandler<WeekAndMonthActivity> {
        a(WeekAndMonthActivity weekAndMonthActivity) {
            super(weekAndMonthActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cjX_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeekAndMonthActivity weekAndMonthActivity, Message message) {
            if (message.what == 1) {
                weekAndMonthActivity.l.setText(weekAndMonthActivity.getString(R.string._2130842615_res_0x7f0213f7));
                weekAndMonthActivity.f.setText(weekAndMonthActivity.getString(R.string._2130842616_res_0x7f0213f8));
                weekAndMonthActivity.d = false;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_health_week_and_month_layout);
        clearBackgroundDrawable();
        cancelAdaptRingRegion();
        this.b = this;
        if (Utils.o()) {
            this.i = true;
        } else {
            this.i = false;
        }
        i();
        b();
        mex.b(this.b).b(mkx.d(-4, System.currentTimeMillis(), 1), System.currentTimeMillis(), 11, null);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        if (nsn.ag(this.b)) {
            return;
        }
        LogUtil.b("WeekAndMonthActivity", "onConfigurationChanged adaptToGeneralPhone!");
        a();
    }

    private void e() {
        if (nsn.ag(this.b)) {
            d();
        } else {
            a();
        }
    }

    private void d() {
        this.g.setOrientation(0);
        this.c.setOrientation(0);
        this.j.setVisibility(4);
    }

    private void a() {
        this.g.setOrientation(1);
        this.c.setOrientation(1);
        this.j.setVisibility(8);
    }

    private void i() {
        this.n = (RelativeLayout) findViewById(R.id.hw_health_weekly_report_layout);
        this.h = (RelativeLayout) findViewById(R.id.hw_health_monthly_layout);
        this.m = (RelativeLayout) findViewById(R.id.year_report_layout);
        this.c = (LinearLayout) findViewById(R.id.year_layout);
        this.l = (HealthTextView) findViewById(R.id.hw_health_weekly_date_text);
        this.f = (HealthTextView) findViewById(R.id.hw_health_monthly_date_text);
        this.e = (CustomTitleBar) mfm.cgL_(this, R.id.title_layout);
        this.g = (LinearLayout) findViewById(R.id.week_and_month_layout);
        this.j = (HealthCardView) findViewById(R.id.report_placeholder_layout_card);
        this.o = (HealthTextView) findViewById(R.id.hw_health_year_date_text);
        setViewSafeRegion(false, this.c, this.g);
        e();
        h();
        if (Utils.o() || CommonUtil.bu()) {
            this.m.setVisibility(8);
            this.e.setTitleText(getResources().getString(R.string._2130842614_res_0x7f0213f6));
        } else {
            this.m.setVisibility(0);
            this.e.setTitleText(getResources().getString(R.string._2130840816_res_0x7f020cf0));
        }
        this.n.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.m.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.report.WeekAndMonthActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!mer.e(500)) {
                    WeekAndMonthActivity.this.a("2");
                    Intent intent = new Intent();
                    intent.setClassName(WeekAndMonthActivity.this.b, PersonalData.CLASS_NAME_PERSONAL_YEAR_REPORT);
                    intent.putExtra("report_stype", WeekAndMonthActivity.this.o.getText().toString());
                    intent.setFlags(276824064);
                    WeekAndMonthActivity.this.b.startActivity(intent);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("WeekAndMonthActivity", "YearLayout enter isFastClick!");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void h() {
        ImageView imageView = (ImageView) mfm.cgL_(this, R.id.week_icon);
        ImageView imageView2 = (ImageView) mfm.cgL_(this, R.id.month_icon);
        ImageView imageView3 = (ImageView) mfm.cgL_(this, R.id.year_icon);
        mkx.ckf_(imageView, "/sandbox/cch5/health/health-resource/achieve/report/img_me_weekly_entrance.png", "/sandbox/cch5/health/health-resource/achieve/report/img_me_weekly_entrance.png", getResources().getDrawable(R.drawable._2131430666_res_0x7f0b0d0a));
        mkx.ckf_(imageView2, "/sandbox/cch5/health/health-resource/achieve/report/im_me_monthly_entrance.png", "/sandbox/cch5/health/health-resource/achieve/report/im_me_monthly_entrance.png", getResources().getDrawable(R.drawable._2131430666_res_0x7f0b0d0a));
        mkx.ckf_(imageView3, "/sandbox/cch5/health/health-resource/achieve/report/img_me_year_entrance.png", "/sandbox/cch5/health/health-resource/achieve/report/img_me_year_entrance.png", getResources().getDrawable(R.drawable._2131430666_res_0x7f0b0d0a));
    }

    private void b() {
        this.l.setText(mlg.a(mkx.b(1, 1, this.i), 1) + " - " + mlg.a(mkx.b(2, 1, this.i), 1));
        this.f.setText(mlg.a(mkx.d(-1, System.currentTimeMillis(), 1), 0));
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.report.WeekAndMonthActivity.3
            @Override // java.lang.Runnable
            public void run() {
                mcz d = meh.c(WeekAndMonthActivity.this.b.getApplicationContext()).d(1, new HashMap(4));
                if (d instanceof TotalRecord) {
                    TotalRecord totalRecord = (TotalRecord) d;
                    if (totalRecord.getDays() >= 1) {
                        String valueOf = String.valueOf(totalRecord.getStartDate());
                        LogUtil.a("WeekAndMonthActivity", "initData startTime=", valueOf);
                        mct.b(WeekAndMonthActivity.this.b, BaseHistoricalReportActivity.USER_START_EXERCISE_TIMESTAMP, valueOf);
                        return;
                    }
                }
                LogUtil.a("WeekAndMonthActivity", "Report have no data");
                if (WeekAndMonthActivity.this.f8462a != null) {
                    WeekAndMonthActivity.this.f8462a.sendEmptyMessage(1);
                }
                if (Utils.o()) {
                    LogUtil.h("WeekAndMonthActivity", "Report totalRecord refresh.");
                    mct.b(WeekAndMonthActivity.this.b, "generateAchieveTime", "");
                }
            }
        });
        int c = c();
        LogUtil.a("WeekAndMonthActivity", "getConfigLatestYear year ", Integer.valueOf(c));
        if (c != 0) {
            this.o.setText(b(c));
        }
    }

    private String b(int i) {
        return LanguageUtil.bc(BaseApplication.getContext()) ? UnitUtil.e(i, 1, 0) : String.valueOf(i);
    }

    private int c() {
        int b = mht.b(mht.d(System.currentTimeMillis()));
        List<Integer> c = mlg.c();
        if (koq.b(c)) {
            return 0;
        }
        int intValue = c.get(0).intValue();
        LogUtil.a("WeekAndMonthActivity", "getConfigLatestYear currentYear ", Integer.valueOf(b), " configLatestYear ", Integer.valueOf(intValue));
        return intValue;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("report", str);
        hashMap.put("from", "1");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_REPORT_1100009.value(), hashMap, 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.setFlags(276824064);
        if (view == this.n) {
            intent.setClassName(this.b, PersonalData.CLASS_NAME_ACHIEVE_WEEK_REPORT_ACTIVITY);
            intent.putExtra("report_stype", String.valueOf(1));
            a("0");
        }
        if (view == this.h) {
            intent.setClassName(this.b, PersonalData.CLASS_NAME_ACHIEVE_MONTH_REPORT_ACTIVITY);
            intent.putExtra("report_stype", String.valueOf(0));
            a("1");
        }
        if (this.d) {
            this.b.startActivity(intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
