package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.activity.HistoricalRoutesActivity;
import com.huawei.healthcloud.plugintrack.ui.adapter.HistoricalRoutesGroupAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.emc;
import defpackage.emx;
import defpackage.enb;
import defpackage.enf;
import defpackage.enj;
import defpackage.gxg;
import defpackage.ixx;
import defpackage.jec;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.util.LogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public class HistoricalRoutesActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f3647a;
    private CustomTitleBar c;
    private HistoricalRoutesGroupAdapter d;
    private int e;
    private RelativeLayout f;
    private View h;
    private boolean j;
    private HealthRecycleView k;
    private RelativeLayout n;
    private List<enj> i = new ArrayList();
    private boolean g = false;
    private Handler b = new Handler(Looper.getMainLooper()) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.HistoricalRoutesActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 220) {
                if (HistoricalRoutesActivity.this.i.isEmpty()) {
                    HistoricalRoutesActivity.this.c();
                } else {
                    HistoricalRoutesActivity historicalRoutesActivity = HistoricalRoutesActivity.this;
                    historicalRoutesActivity.d(historicalRoutesActivity.i);
                }
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.historical_routes_page);
        a();
        h();
        i();
        f();
    }

    private void i() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.c("HistoricalRoutesActivity", "intent == null");
            return;
        }
        this.e = intent.getIntExtra("from", 0);
        Uri zs_ = AppRouterUtils.zs_(intent);
        if (zs_ == null || zs_.isOpaque()) {
            LogUtil.c("HistoricalRoutesActivity", "uri is invalid");
            return;
        }
        String queryParameter = zs_.getQueryParameter(WebViewHelp.BI_KEY_PULL_FROM);
        this.j = zs_.getBooleanQueryParameter("isBackList", false);
        LogUtil.d("HistoricalRoutesActivity", "from : ", queryParameter);
        if ("push".equals(queryParameter)) {
            this.e = 2;
        }
        if (this.j) {
            this.c.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: heo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HistoricalRoutesActivity.this.bcd_(view);
                }
            });
        }
    }

    public /* synthetic */ void bcd_(View view) {
        g();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void g() {
        startActivity(new Intent(this, (Class<?>) RunningRouteListActivity.class));
        finish();
    }

    private void f() {
        int b2;
        j();
        int b3 = b();
        int b4 = b(e());
        LogUtil.d("HistoricalRoutesActivity", "goLiveFormatDay: ", Integer.valueOf(b4));
        Date c = c(e());
        Date time = Calendar.getInstance().getTime();
        if (c.after(time)) {
            b2 = b(time);
        } else {
            b2 = b(c);
        }
        LogUtil.d("HistoricalRoutesActivity", "curFormatDay: ", Integer.valueOf(b2));
        if (b4 == 0 || b2 == 0) {
            return;
        }
        d(b3, b4, b2);
    }

    private void d(int i, int i2, int i3) {
        CountDownLatch countDownLatch = new CountDownLatch(i);
        while (true) {
            int i4 = i3;
            int i5 = i2;
            i2 = i4;
            if (i <= 0) {
                return;
            }
            emc.d().getHotPathParticipateInfos(new emx.b().d(i5).c(i2).b(), new b(this, countDownLatch));
            Date c = c(String.valueOf(i2));
            i3 = a(c) == 0 ? i2 : a(c);
            LogUtil.d("HistoricalRoutesActivity", "reqStartDay: ", Integer.valueOf(i2), " reqEndDay: ", Integer.valueOf(i3));
            i--;
        }
    }

    private int b(Date date) {
        String c = jec.c(date, "yyyyMMdd");
        if (TextUtils.isEmpty(c)) {
            return 0;
        }
        try {
            return Integer.parseInt(c);
        } catch (NumberFormatException unused) {
            LogUtil.d("HistoricalRoutesActivity", "parse fail");
            return 0;
        }
    }

    private int b() {
        return (int) Math.ceil(((Calendar.getInstance().getTime().getTime() - e().getTime()) * 1.0d) / 8.5536E9d);
    }

    private int a(Date date) {
        Date time = Calendar.getInstance().getTime();
        if (date == null) {
            return 0;
        }
        Date c = c(date);
        if (c.after(time)) {
            return b(time);
        }
        return b(c);
    }

    private void a() {
        this.n = (RelativeLayout) findViewById(R.id.historical_routes_recycler_layout);
        this.f3647a = (RelativeLayout) findViewById(R.id.no_data_layout);
        this.h = findViewById(R.id.loading_layout);
        this.f = (RelativeLayout) findViewById(R.id.no_network_layout);
        this.c = (CustomTitleBar) findViewById(R.id.title_bar);
        if (CommonUtil.aa(this)) {
            return;
        }
        this.n.setVisibility(8);
        this.f.setVisibility(0);
    }

    private Date c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyyMMdd").parse(str);
        } catch (ParseException e2) {
            LogUtil.e("TimeDateFormatUtil", "ParseException = ", e2.getMessage());
            return null;
        }
    }

    private Date e() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2022);
        calendar.set(2, 3);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", 3);
        hashMap.put("from", Integer.valueOf(this.e));
        hashMap.put("pageStatus", z ? "true" : "false");
        ixx.d().d(BaseApplication.e(), "1040087", hashMap, 0);
    }

    private void h() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.historical_routes_recycler_view);
        this.k = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        HistoricalRoutesGroupAdapter historicalRoutesGroupAdapter = new HistoricalRoutesGroupAdapter(this, new ArrayList(), R.layout.historical_routes_group_item);
        this.d = historicalRoutesGroupAdapter;
        this.k.setAdapter(historicalRoutesGroupAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        HandlerExecutor.a(new e(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final List<enj> list) {
        LogUtil.d("HistoricalRoutesActivity", "getDataSuccess enter");
        LogUtil.d("HistoricalRoutesActivity", "getDataSuccess data.size=", Integer.valueOf(list.size()));
        runOnUiThread(new Runnable() { // from class: hep
            @Override // java.lang.Runnable
            public final void run() {
                HistoricalRoutesActivity.this.a(list);
            }
        });
    }

    public /* synthetic */ void a(List list) {
        this.f3647a.setVisibility(8);
        this.n.setVisibility(0);
        this.d.refreshDataChange(b((List<enj>) list));
        d();
    }

    private void d() {
        if (this.h.getVisibility() == 0) {
            this.h.setVisibility(8);
        }
    }

    private void j() {
        this.h.setVisibility(0);
    }

    private Date c(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, 99);
        return calendar.getTime();
    }

    private void e(enf enfVar) {
        if (enfVar != null) {
            enfVar.b(RunningRouteUtils.a(RunningRouteUtils.a(), RunningRouteUtils.b(enfVar.g())));
        }
    }

    private List<gxg> b(List<enj> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        HashMap hashMap = new HashMap();
        for (enj enjVar : list) {
            if (enjVar != null) {
                long longValue = enjVar.d().longValue();
                e(enjVar.a());
                long time = jec.w(new Date(longValue)).getTime();
                if (!hashMap.containsKey(Long.valueOf(time))) {
                    hashMap.put(Long.valueOf(time), new ArrayList());
                }
                ((List) hashMap.get(Long.valueOf(time))).add(enjVar);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry != null) {
                gxg gxgVar = new gxg();
                gxgVar.a(((Long) entry.getKey()).longValue());
                List<enj> list2 = (List) entry.getValue();
                if (koq.b(list2)) {
                    LogUtil.e("HistoricalRoutesActivity", "sortList is empty");
                } else {
                    Collections.sort(list2, new Comparator<enj>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.HistoricalRoutesActivity.4
                        @Override // java.util.Comparator
                        /* renamed from: b, reason: merged with bridge method [inline-methods] */
                        public int compare(enj enjVar2, enj enjVar3) {
                            return Long.compare(enjVar3.d().longValue(), enjVar2.d().longValue());
                        }
                    });
                    gxgVar.a(e(list2));
                    arrayList.add(gxgVar);
                }
            }
        }
        Collections.sort(arrayList, new Comparator() { // from class: hem
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((gxg) obj2).d(), ((gxg) obj).d());
                return compare;
            }
        });
        LogUtil.d("HistoricalRoutesActivity", "convertGroup beanList=", arrayList);
        return arrayList;
    }

    private List<enj> e(List<enj> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (enj enjVar : list) {
            if (enjVar == null) {
                LogUtil.e("HistoricalRoutesActivity", "hotPathParticipateHistory is null");
            } else {
                enf a2 = enjVar.a();
                if (a2 == null) {
                    LogUtil.e("HistoricalRoutesActivity", "hotPathOperationInfo is null");
                } else {
                    String i = a2.i();
                    if (!arrayList2.contains(i)) {
                        arrayList2.add(i);
                        arrayList.add(enjVar);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        HistoricalRoutesGroupAdapter historicalRoutesGroupAdapter = this.d;
        if (historicalRoutesGroupAdapter != null) {
            historicalRoutesGroupAdapter.a();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.b.removeMessages(HeartRateThresholdConfig.HEART_RATE_LIMIT);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.j) {
            g();
        } else {
            super.onBackPressed();
        }
    }

    /* loaded from: classes4.dex */
    static class b extends UiCallback<enb> {
        private CountDownLatch b;
        private WeakReference<HistoricalRoutesActivity> e;

        public b(HistoricalRoutesActivity historicalRoutesActivity, CountDownLatch countDownLatch) {
            this.e = new WeakReference<>(historicalRoutesActivity);
            this.b = countDownLatch;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeakReference<HistoricalRoutesActivity> weakReference = this.e;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            LogUtil.e("HistoricalRoutesActivity", "loadData onFailure errorCode=", Integer.valueOf(i));
            this.e.get().c();
            this.b.countDown();
            if (this.b.getCount() == 0) {
                this.e.get().b.sendEmptyMessageDelayed(HeartRateThresholdConfig.HEART_RATE_LIMIT, 500L);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(enb enbVar) {
            WeakReference<HistoricalRoutesActivity> weakReference = this.e;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            LogUtil.d("HistoricalRoutesActivity", "loadData onSuccess");
            List<enj> e = enbVar.e();
            if (!koq.b(e)) {
                LogUtil.d("HistoricalRoutesActivity", "list size: ", Integer.valueOf(e.size()));
                this.e.get().i.addAll(e);
                this.e.get().g = true;
            }
            this.b.countDown();
            if (this.b.getCount() == 0) {
                this.e.get().b.sendEmptyMessageDelayed(HeartRateThresholdConfig.HEART_RATE_LIMIT, 500L);
                this.e.get().c(this.e.get().g);
            }
        }
    }

    /* loaded from: classes4.dex */
    static class e implements Runnable {
        private WeakReference<HistoricalRoutesActivity> d;

        public e(HistoricalRoutesActivity historicalRoutesActivity) {
            this.d = new WeakReference<>(historicalRoutesActivity);
        }

        @Override // java.lang.Runnable
        public void run() {
            WeakReference<HistoricalRoutesActivity> weakReference = this.d;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            HistoricalRoutesActivity historicalRoutesActivity = this.d.get();
            if (historicalRoutesActivity.h.getVisibility() == 0) {
                historicalRoutesActivity.h.setVisibility(8);
            }
            historicalRoutesActivity.n.setVisibility(8);
            historicalRoutesActivity.f3647a.setVisibility(0);
        }
    }
}
