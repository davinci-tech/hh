package com.huawei.pluginachievement.ui.report;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.ui.adapter.AchieveAnnualAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bzs;
import defpackage.koq;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mgp;
import defpackage.mht;
import defpackage.mlg;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class ReportYearActivity extends BaseActivity {
    private static final int e = Color.rgb(97, 173, 181);

    /* renamed from: a, reason: collision with root package name */
    private ListView f8461a;
    private AchieveAnnualAdapter b;
    private Context c;
    private List<Integer> d;
    private RelativeLayout f;
    private Handler g = new e(this);
    private Runnable h = new Runnable() { // from class: com.huawei.pluginachievement.ui.report.ReportYearActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (ReportYearActivity.this.j == null) {
                return;
            }
            mcz d = ReportYearActivity.this.j.d(1, new HashMap(2));
            if (d instanceof TotalRecord) {
                ReportYearActivity.this.g.sendMessage(ReportYearActivity.this.g.obtainMessage(0, (TotalRecord) d));
            }
        }
    };
    private HealthTextView i;
    private meh j;

    static class e extends BaseHandler<ReportYearActivity> {
        e(ReportYearActivity reportYearActivity) {
            super(reportYearActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cjV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ReportYearActivity reportYearActivity, Message message) {
            if (message.what == 0 && (message.obj instanceof TotalRecord)) {
                long startDate = ((TotalRecord) message.obj).getStartDate();
                LogUtil.a("PLGACHIEVE_ReportYearActivity", "startDate ", Long.valueOf(startDate));
                reportYearActivity.d(startDate);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("PLGACHIEVE_ReportYearActivity", "onCreate");
        setContentView(R.layout.layout_report_main_year);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.c = this;
        d();
        c();
    }

    private void d() {
        ListView listView = (ListView) findViewById(R.id.annual_report_list);
        this.f8461a = listView;
        listView.setOverScrollMode(2);
        this.i = (HealthTextView) findViewById(R.id.report_empty_txt);
        this.f = (RelativeLayout) findViewById(R.id.reportListLayout);
        this.d = new ArrayList(10);
        AchieveAnnualAdapter achieveAnnualAdapter = new AchieveAnnualAdapter(this, this.d);
        this.b = achieveAnnualAdapter;
        this.f8461a.setAdapter((ListAdapter) achieveAnnualAdapter);
        this.f8461a.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.pluginachievement.ui.report.ReportYearActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!koq.b(ReportYearActivity.this.d, i)) {
                    int intValue = ((Integer) ReportYearActivity.this.d.get(i)).intValue();
                    LogUtil.a("PLGACHIEVE_ReportYearActivity", "mAnnualReportList year ", Integer.valueOf(intValue));
                    if (intValue <= 2020) {
                        ReportYearActivity.this.b(mgp.e(intValue), intValue);
                    } else {
                        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                        builder.addPath("#/?from=1");
                        bzs.e().loadH5ProApp(ReportYearActivity.this.c, "com.huawei.health.h5.annual-report-" + intValue, builder);
                    }
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                LogUtil.h("PLGACHIEVE_ReportYearActivity", "mAnnualReportList isOutOfBounds.");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
    }

    private void c() {
        this.j = meh.c(getApplicationContext());
        LogUtil.a("PLGACHIEVE_ReportYearActivity", "getData()");
        ThreadPoolManager.d().execute(this.h);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        if (koq.b(this.d)) {
            this.i.setVisibility(0);
            this.f.setVisibility(8);
        }
        this.b.d(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final int i) {
        GRSManager.a(this.c).e(i >= 2020 ? "domainContentcenterDbankcdnNew" : "achievementUrl", new GrsQueryCallback() { // from class: com.huawei.pluginachievement.ui.report.ReportYearActivity.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                LogUtil.c("PLGACHIEVE_ReportYearActivity", "annualUrl ", str2 + str);
                if (i == 2020) {
                    ReportYearActivity.this.c(str2 + str);
                    return;
                }
                ReportYearActivity.this.a(str2 + str);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.b("PLGACHIEVE_ReportYearActivity", "onCallBackFail errorCode = ", Integer.valueOf(i2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", str);
        intent.putExtra("EXTRA_BI_ID", "");
        intent.putExtra("EXTRA_BI_NAME", "");
        intent.putExtra("EXTRA_BI_SOURCE", "Annual");
        this.c.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(long j) {
        LogUtil.a("PLGACHIEVE_ReportYearActivity", "dealAnnualShow startDate ", Long.valueOf(j));
        if (j <= 0) {
            LogUtil.h("PLGACHIEVE_ReportYearActivity", "dealAnnualShow startDate is error!");
            this.i.setVisibility(0);
            this.f.setVisibility(8);
            return;
        }
        int b = mht.b(mht.d(j));
        List<Integer> c = mlg.c();
        LogUtil.a("PLGACHIEVE_ReportYearActivity", "dealAnnualShow configAnnualShowList ", c.toString());
        for (Integer num : c) {
            if (num.intValue() >= b) {
                this.d.add(num);
            }
        }
        LogUtil.a("PLGACHIEVE_ReportYearActivity", "dealAnnualShow mAnnualList ", this.d.toString());
        if (koq.b(this.d)) {
            this.i.setVisibility(0);
            this.f.setVisibility(8);
        }
        this.b.d(this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        bzs.e().initH5Pro();
        H5ProLaunchOption.Builder addCustomizeJsModule = new H5ProLaunchOption.Builder().enableSlowWholeDocumentDraw().addCustomizeJsModule("JsInteraction", bzs.e().getJsInteraction());
        if (e()) {
            addCustomizeJsModule.setBackgroundColor(e);
        }
        H5ProClient.startH5LightApp(this.c, str, addCustomizeJsModule.build());
    }

    private boolean e() {
        return "1".equals(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10006), this.j.t().getHuid() + "_annualAuth2020 "));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeMessages(0);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
