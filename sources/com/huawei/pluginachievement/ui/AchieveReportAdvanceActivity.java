package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.github.mikephil.charting.data.Entry;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity;
import com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportLineChart;
import com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportMarkView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.gvv;
import defpackage.ixx;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mka;
import defpackage.mkc;
import defpackage.mkx;
import defpackage.mld;
import defpackage.mlf;
import defpackage.mlg;
import defpackage.nrz;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class AchieveReportAdvanceActivity extends BaseActivity {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8410a;
    private PluginAchieveAdapter aa;
    private HealthTextView ab;
    private HealthTextView ac;
    private CustomTitleBar ad;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthTextView ai;
    private HealthTextView aj;
    private String al;
    private HealthTextView am;
    private HealthTextView b;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView i;
    private HealthTextView j;
    private LinearLayout k;
    private Context l;
    private int p;
    private HwHealthAchieveReportLineChart q;
    private int r;
    private String s;
    private ArrayList t;
    private int u;
    private ImageView w;
    private ImageView y;
    private HealthTextView z;
    private Map<Long, MotionPathSimplify> v = new HashMap(0);
    private ArrayList<Long> x = new ArrayList<>(0);
    private long h = 0;
    private boolean g = false;
    private int o = 0;
    private long n = 0;
    private Handler m = new Handler() { // from class: com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                AchieveReportAdvanceActivity.this.i();
                return;
            }
            if (i == 2) {
                mlg.e(AchieveReportAdvanceActivity.this.l);
            } else {
                if (i != 3) {
                    return;
                }
                LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "mMotionCount is ", Integer.valueOf(AchieveReportAdvanceActivity.this.r));
                AchieveReportAdvanceActivity achieveReportAdvanceActivity = AchieveReportAdvanceActivity.this;
                achieveReportAdvanceActivity.a(achieveReportAdvanceActivity.r);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achievement_record_history_page);
        this.l = this;
        this.aa = mcv.d(this).getAdapter();
        a();
        g();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "intent is null");
            finish();
            return;
        }
        this.u = intent.getIntExtra("dialogType", 0);
        String stringExtra = intent.getStringExtra("value");
        this.s = stringExtra;
        if (this.u == 0 || TextUtils.isEmpty(stringExtra)) {
            LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "mType or mJson is null");
            finish();
        } else {
            LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "mType=", Integer.valueOf(this.u));
        }
    }

    private void g() {
        if (UnitUtil.h()) {
            this.g = true;
            this.al = mlg.a(this.u, this.l);
        } else {
            this.al = mlg.c(this.u, this.l);
        }
        b();
        this.q = (HwHealthAchieveReportLineChart) mfm.cgL_(this, R.id.hw_health_report_linechart);
        this.ag = (HealthTextView) mfm.cgL_(this, R.id.hw_health_total_pace);
        this.i = (HealthTextView) mfm.cgL_(this, R.id.hw_health_date);
        this.am = (HealthTextView) mfm.cgL_(this, R.id.hw_health_total_sport_time);
        this.b = (HealthTextView) mfm.cgL_(this, R.id.hw_health_average_speed);
        this.j = (HealthTextView) mfm.cgL_(this, R.id.hw_health_average_speed_unit);
        this.f = (HealthTextView) mfm.cgL_(this, R.id.hw_health_value_desc);
        this.ae = (HealthTextView) mfm.cgL_(this, R.id.hw_health_total_heat);
        this.aj = (HealthTextView) mfm.cgL_(this, R.id.hw_health_total_pace_unit);
        this.y = (ImageView) mfm.cgL_(this, R.id.achieve_report_middle);
        this.w = (ImageView) mfm.cgL_(this, R.id.achieve_report_share_middle);
        this.ab = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_value_desc);
        this.ac = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_pace_unit);
        this.af = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_total_pace);
        this.ai = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_total_sport_time);
        this.f8410a = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_average_speed);
        this.e = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_speed_value_desc);
        this.d = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_average_speed_unit);
        this.ah = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_total_heat);
        this.z = (HealthTextView) mfm.cgL_(this, R.id.hw_health_share_date);
        RelativeLayout relativeLayout = (RelativeLayout) mfm.cgL_(this, R.id.code_information);
        if (Utils.o()) {
            relativeLayout.setVisibility(8);
        }
        if (TextUtils.isEmpty(this.al)) {
            this.aj.setVisibility(8);
            this.ac.setVisibility(8);
        } else {
            this.aj.setText(this.al);
            this.ac.setText(this.al);
        }
        if (mlg.e(this.u) || this.u == 10) {
            this.f.setText(this.l.getString(R.string._2130839763_res_0x7f0208d3));
            this.e.setText(this.l.getString(R.string._2130839763_res_0x7f0208d3));
            this.y.setImageResource(R.mipmap._2131821123_res_0x7f110243);
            this.w.setImageResource(R.mipmap._2131821123_res_0x7f110243);
        }
        e();
        h();
        f();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.title_layout);
        this.ad = customTitleBar;
        customTitleBar.setTitleText(mlg.d(this.u, this.l));
        this.ab.setText(mlg.d(this.u, this.l));
        this.ad.setRightButtonVisibility(0);
        if (LanguageUtil.bc(this.l)) {
            this.ad.setRightButtonDrawable(nrz.cKn_(this.l, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.ad.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.ad.setRightButtonOnClickListener(new View.OnClickListener() { // from class: miy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveReportAdvanceActivity.this.cib_(view);
            }
        });
    }

    public /* synthetic */ void cib_(View view) {
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "onClick share information");
        if (PermissionUtil.c()) {
            j();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.l, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.l) { // from class: com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity.1
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    AchieveReportAdvanceActivity.this.j();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void b() {
        LinearLayout linearLayout = (LinearLayout) mfm.cgL_(this, R.id.hw_health_report_history_layout);
        this.k = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                long d2;
                long e;
                long j;
                if (SystemClock.elapsedRealtime() - AchieveReportAdvanceActivity.this.n < 2000) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                AchieveReportAdvanceActivity.this.n = SystemClock.elapsedRealtime();
                if (mlg.g(AchieveReportAdvanceActivity.this.u)) {
                    if (AchieveReportAdvanceActivity.this.o < AchieveReportAdvanceActivity.this.t.size()) {
                        mka mkaVar = (mka) AchieveReportAdvanceActivity.this.t.get(AchieveReportAdvanceActivity.this.o);
                        d2 = mkaVar.a();
                        e = mkaVar.e();
                        j = e;
                    }
                    d2 = 0;
                    j = 0;
                } else {
                    if (mlg.f(AchieveReportAdvanceActivity.this.u)) {
                        if (AchieveReportAdvanceActivity.this.o < AchieveReportAdvanceActivity.this.t.size()) {
                            mkc mkcVar = (mkc) AchieveReportAdvanceActivity.this.t.get(AchieveReportAdvanceActivity.this.o);
                            d2 = mkcVar.d();
                            e = mkcVar.e();
                            j = e;
                        }
                    } else {
                        LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "initLayout mType is not matching");
                    }
                    d2 = 0;
                    j = 0;
                }
                if (d2 == 0) {
                    d2 = j + 1;
                }
                long j2 = d2;
                PluginAchieveAdapter adapter = mcv.d(AchieveReportAdvanceActivity.this.l).getAdapter();
                if (adapter != null) {
                    adapter.gotoTrackDetailData(AchieveReportAdvanceActivity.this.l, j, j2);
                } else {
                    LogUtil.b("PLGACHIEVE_AchieveReportAdvanceActivity", "PluginAchieve.getInstance(mContext).getAdapter() is null");
                }
                AchieveReportAdvanceActivity achieveReportAdvanceActivity = AchieveReportAdvanceActivity.this;
                achieveReportAdvanceActivity.b(achieveReportAdvanceActivity.u);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        HashMap hashMap = new HashMap(1);
        String value = AnalyticsValue.ACHIEVE_REPORT_1100030.value();
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
    }

    private void h() {
        if (mlg.g(this.u)) {
            this.t = mlg.d(this.s, 5);
        } else if (mlg.f(this.u)) {
            this.t = mlg.e(this.s, 5);
        } else {
            LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "initTrackDetailData mType is not matching");
        }
        ArrayList arrayList = this.t;
        if (arrayList == null || arrayList.isEmpty()) {
            finish();
            return;
        }
        l();
        this.o = this.t.size() - 1;
        c();
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "mCurrentPosition=", Integer.valueOf(this.o));
        d();
    }

    private void l() {
        if (mlg.g(this.u)) {
            Collections.sort(this.t, new Comparator<mka>() { // from class: com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity.3
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(mka mkaVar, mka mkaVar2) {
                    if (LanguageUtil.bc(AchieveReportAdvanceActivity.this.l)) {
                        if (mkaVar2.b() - mkaVar.b() < 0) {
                            return -1;
                        }
                        return mkaVar2.b() - mkaVar.b() == 0 ? 0 : 1;
                    }
                    if (mkaVar.b() - mkaVar2.b() < 0) {
                        return -1;
                    }
                    return mkaVar.b() - mkaVar2.b() == 0 ? 0 : 1;
                }
            });
        } else if (mlg.f(this.u)) {
            Collections.sort(this.t, new Comparator<mkc>() { // from class: com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity.4
                @Override // java.util.Comparator
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public int compare(mkc mkcVar, mkc mkcVar2) {
                    if (LanguageUtil.bc(AchieveReportAdvanceActivity.this.l)) {
                        return mlg.e(mkcVar.b(), mkcVar2.b());
                    }
                    return mlg.e(mkcVar2.b(), mkcVar.b());
                }
            });
        } else {
            LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "sortTrackDetailData mType is not matching");
        }
    }

    private void c() {
        int size = this.t.size();
        if (mlg.g(this.u)) {
            e(size);
        } else if (mlg.f(this.u)) {
            h(size);
        } else {
            LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "initMotionPathMap mType is not matching");
        }
    }

    private void e(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.t.get(i2);
            if (obj instanceof mka) {
                long e = ((mka) obj).e();
                this.v.put(Long.valueOf(e), null);
                this.x.add(i2, Long.valueOf(e));
                this.h = e;
            }
        }
    }

    private void h(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.t.get(i2);
            if (obj instanceof mkc) {
                long e = ((mkc) obj).e();
                this.v.put(Long.valueOf(e), null);
                this.x.add(i2, Long.valueOf(e));
                this.h = e;
            }
        }
    }

    private void d() {
        if (this.aa == null) {
            LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "pluginAchieveAdapter is null");
            return;
        }
        ArrayList arrayList = this.t;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        int size = this.t.size();
        this.p = size;
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "size is", Integer.valueOf(size));
        if (this.p > 0) {
            this.r = 0;
            this.m.sendEmptyMessage(3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i >= this.t.size()) {
            return;
        }
        if (mlg.g(this.u)) {
            c(i);
        } else if (mlg.f(this.u)) {
            d(i);
        } else {
            LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "getMotionPathData mType is not matching");
        }
    }

    private void c(int i) {
        Object obj = this.t.get(i);
        if (obj instanceof mka) {
            mka mkaVar = (mka) obj;
            long a2 = mkaVar.a();
            if (a2 == 0) {
                a2 = mkaVar.e() + 1;
            }
            e(mkaVar.e(), a2);
        }
    }

    private void d(int i) {
        Object obj = this.t.get(i);
        if (obj instanceof mkc) {
            mkc mkcVar = (mkc) obj;
            long d2 = mkcVar.d();
            if (d2 == 0) {
                d2 = mkcVar.e() + 1;
            }
            e(mkcVar.e(), d2);
        }
    }

    private void e(long j, long j2) {
        this.aa.getTrackDetailDataByTimestamp(this.l.getApplicationContext(), j, j2, new d(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void e(T t) {
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "called by getTrackDetailDataByTimestamp ");
        int i = this.r;
        if (i < this.p) {
            this.r = i + 1;
            this.m.sendEmptyMessage(3);
        }
        if (t instanceof MotionPathSimplify) {
            MotionPathSimplify motionPathSimplify = (MotionPathSimplify) t;
            this.v.put(Long.valueOf(motionPathSimplify.requestStartTime()), motionPathSimplify);
            if (motionPathSimplify.requestStartTime() == this.h) {
                LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "search completed");
                this.m.sendEmptyMessage(0);
                return;
            }
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "obj is not instanceof MotionPathSimplify or is null");
    }

    static class d implements AchieveCallback {
        WeakReference<AchieveReportAdvanceActivity> d;

        d(AchieveReportAdvanceActivity achieveReportAdvanceActivity) {
            this.d = new WeakReference<>(achieveReportAdvanceActivity);
        }

        @Override // com.huawei.pluginachievement.impl.AchieveCallback
        public void onResponse(int i, Object obj) {
            AchieveReportAdvanceActivity achieveReportAdvanceActivity = this.d.get();
            if (achieveReportAdvanceActivity != null) {
                achieveReportAdvanceActivity.e((AchieveReportAdvanceActivity) obj);
            }
        }
    }

    private void f() {
        ArrayList arrayList = this.t;
        int size = arrayList == null ? 0 : arrayList.size();
        ArrayList<String> arrayList2 = new ArrayList<>(size);
        ArrayList<Entry> arrayList3 = new ArrayList<>(size);
        ArrayList arrayList4 = this.t;
        if (arrayList4 != null && !arrayList4.isEmpty()) {
            int size2 = this.t.size();
            for (int i = 0; i < size2; i++) {
                if (mlg.g(this.u)) {
                    b(i, arrayList2, arrayList3);
                } else if (mlg.f(this.u)) {
                    c(i, arrayList2, arrayList3);
                } else {
                    LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "refreshLineChartData mType is not matching");
                }
            }
        }
        c(arrayList2, arrayList3);
    }

    private void b(int i, ArrayList<String> arrayList, ArrayList<Entry> arrayList2) {
        Object obj = this.t.get(i);
        if (obj instanceof mka) {
            arrayList.add(mlg.a(((mka) obj).e(), 2));
            double b = r0.b() / 1000.0d;
            if (this.g) {
                arrayList2.add(cia_(i, (float) UnitUtil.e(b, 3), this.l.getResources().getDrawable(R.drawable._2131431182_res_0x7f0b0f0e), "LINE_PATH"));
            } else {
                arrayList2.add(cia_(i, (float) mlg.c(b), this.l.getResources().getDrawable(R.drawable._2131431182_res_0x7f0b0f0e), "LINE_PATH"));
            }
        }
    }

    private void c(int i, ArrayList<String> arrayList, ArrayList<Entry> arrayList2) {
        Object obj = this.t.get(i);
        if (obj instanceof mkc) {
            mkc mkcVar = (mkc) obj;
            arrayList.add(mlg.a(mkcVar.e(), 2));
            if (mlg.e(this.u)) {
                arrayList2.add(cia_(i, mlg.e(mkcVar.b()), this.l.getResources().getDrawable(R.drawable._2131431182_res_0x7f0b0f0e), "LINE_PATH"));
            } else {
                arrayList2.add(cia_(i, (float) mkcVar.b(), this.l.getResources().getDrawable(R.drawable._2131431182_res_0x7f0b0f0e), "LINE_PATH"));
            }
        }
    }

    private void c(ArrayList<String> arrayList, ArrayList<Entry> arrayList2) {
        this.q.setExtraOffsets(mld.d(this.l, 10.0f), 0.0f, mld.d(this.l, 11.0f), mld.d(this.l, 2.0f));
        this.q.setMyMarkView(new HwHealthAchieveReportMarkView(this.l, R.layout.achieve_report_markview, this.q));
        this.q.c(mkx.c, mkx.e, mkx.e);
        if (mlg.e(this.u)) {
            this.q.setChartData(this.l, arrayList, arrayList2, mlg.f(this.u), true);
        } else {
            this.q.setChartData(this.l, arrayList, arrayList2, mlg.f(this.u), false);
        }
        this.q.setOnSingleTapListener(new HwHealthAchieveReportLineChart.OnSingleTapListener() { // from class: com.huawei.pluginachievement.ui.AchieveReportAdvanceActivity.8
            @Override // com.huawei.pluginachievement.ui.linechart.HwHealthAchieveReportLineChart.OnSingleTapListener
            public void onSingleTap(int i) {
                if (AchieveReportAdvanceActivity.this.o != i) {
                    LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "position=", Integer.valueOf(i));
                    AchieveReportAdvanceActivity.this.o = i;
                    AchieveReportAdvanceActivity.this.i();
                }
            }
        });
        this.q.invalidate();
    }

    private <T> Entry cia_(int i, float f, Drawable drawable, T t) {
        Entry entry = new Entry(i, f);
        entry.setIcon(drawable);
        entry.setData(t);
        return entry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        int i;
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "enter refreshView");
        synchronized (c) {
            ArrayList<Long> arrayList = this.x;
            if (arrayList != null) {
                LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "mStartTimeList=", Integer.valueOf(arrayList.size()));
                if (this.o < this.x.size() && (i = this.o) >= 0) {
                    MotionPathSimplify motionPathSimplify = this.v.get(Long.valueOf(this.x.get(i).longValue()));
                    if (motionPathSimplify == null) {
                        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "motionPathSimplify is null");
                        m();
                        return;
                    }
                    this.i.setText(mkx.f(motionPathSimplify.requestStartTime()) + "");
                    this.am.setText(mlg.d((int) (motionPathSimplify.requestTotalTime() / 1000)));
                    c(gvv.a(motionPathSimplify.requestAvgPace()));
                    this.ae.setText(UnitUtil.e((motionPathSimplify.requestTotalCalories() * 1.0d) / 1000.0d, 1, 0) + "");
                    this.z.setText(mlg.b(String.valueOf(motionPathSimplify.requestStartTime())));
                    this.ai.setText(mlg.d((int) (motionPathSimplify.requestTotalTime() / 1000)));
                    this.ah.setText(UnitUtil.e((motionPathSimplify.requestTotalCalories() * 1.0d) / 1000.0d, 1, 0) + "");
                    if (mlg.g(this.u)) {
                        d(motionPathSimplify);
                    } else if (mlg.f(this.u)) {
                        a(motionPathSimplify);
                    } else {
                        LogUtil.c("PLGACHIEVE_AchieveReportAdvanceActivity", "refreshView mType is not matching");
                    }
                }
            } else {
                m();
            }
        }
    }

    private void d(MotionPathSimplify motionPathSimplify) {
        String d2 = mlf.d(motionPathSimplify.requestTotalDistance());
        this.ag.setText(d2);
        this.af.setText(d2);
        if (this.u == 10) {
            String string = this.l.getString(R.string._2130844078_res_0x7f0219ae);
            if (this.g) {
                string = this.l.getString(R.string._2130844079_res_0x7f0219af);
            }
            this.j.setText(string);
            this.d.setText(string);
            this.j.setVisibility(0);
            this.d.setVisibility(0);
            c(mlg.a(motionPathSimplify.requestAvgPace() * 1.0d));
        }
    }

    private void a(MotionPathSimplify motionPathSimplify) {
        if (this.o < this.t.size()) {
            Object obj = this.t.get(this.o);
            if (obj instanceof mkc) {
                mkc mkcVar = (mkc) obj;
                if (mlg.e(this.u)) {
                    this.ag.setText(mlg.a(mkcVar.b()));
                    this.af.setText(mlg.a(mkcVar.b()));
                    this.j.setText(this.al);
                    this.d.setText(this.al);
                    this.j.setVisibility(0);
                    this.d.setVisibility(0);
                    c(mlg.a(motionPathSimplify.requestAvgPace() * 1.0d));
                    return;
                }
                if (this.u == 4) {
                    this.ag.setText(gvv.a((float) mkcVar.b()));
                    this.af.setText(gvv.a((float) mkcVar.b()));
                } else {
                    String valueOf = String.valueOf(mlg.d((int) (mkcVar.b() + 0.5d)));
                    this.ag.setText(valueOf);
                    this.af.setText(valueOf);
                }
                c(gvv.a(motionPathSimplify.requestAvgPace()));
            }
        }
    }

    private void m() {
        this.ag.setText("--");
        this.i.setText("--");
        this.am.setText("--");
        c("--");
        this.ae.setText("--");
        this.j.setVisibility(8);
        this.d.setVisibility(8);
        this.af.setText("--");
        this.ai.setText("--");
        this.ah.setText("--");
    }

    private void c(String str) {
        this.b.setText(str);
        this.f8410a.setText(str);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "enter onDestroy");
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "share enter");
        if (!mcx.d(this.l)) {
            LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "isNetworkAvailable error");
            this.m.sendEmptyMessage(2);
            return;
        }
        Bitmap cgJ_ = mfp.cgJ_((LinearLayout) mfm.cgL_(this, R.id.hw_health_share_layout));
        if (cgJ_ == null) {
            LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "share bmpShare == null ");
        } else {
            mcx.cfN_(this.l, cgJ_, AnalyticsValue.SUCCESSES_SHARE_1100014.value(), null);
        }
        LogUtil.a("PLGACHIEVE_AchieveReportAdvanceActivity", "share end");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
