package com.huawei.ui.main.stories.settings.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.common.dfx.storage.StorageMonitor;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.SleepMonitorAlgorithmApi;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.h5pro.jsbridge.system.storage.StorageUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.gltexture.util.FileUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity;
import defpackage.bzi;
import defpackage.efb;
import defpackage.gzv;
import defpackage.hab;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.scl;
import defpackage.sdj;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class ClearDataCacheActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f10446a;
    private LinearLayout ad;
    private ImageView ah;
    private HealthTextView ai;
    private HealthCheckBox ak;
    private LinearLayout c;
    private CustomViewDialog e;
    private ImageView f;
    private NoTitleCustomAlertDialog g;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;
    private LinearLayout k;
    private ImageView l;
    private ImageView m;
    private LinearLayout n;
    private HealthTextView o;
    private ImageView p;
    private ImageView q;
    private HealthTextView r;
    private LinearLayout s;
    private HealthTextView t;
    private String v;
    private HealthTextView w;
    private LinearLayout y;
    private Context u = null;
    private String ae = null;
    private String x = null;
    private String aa = null;
    private String z = null;
    private String ab = null;
    private String af = null;
    private boolean b = false;
    private String d = null;
    private HashSet<String> am = new HashSet<>();
    private String ag = null;
    private boolean ac = true;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("ClearDataCacheActivity", "onCreate()");
        setContentView(R.layout.activity_clear_data_cache);
        this.u = this;
        this.v = nsn.b(this, 0L);
        j();
        l();
        StorageMonitor.d(false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        nsn.a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("ClearDataCacheActivity", "onDestroy()");
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.g;
        if (noTitleCustomAlertDialog == null || !noTitleCustomAlertDialog.isShowing()) {
            return;
        }
        this.g.dismiss();
    }

    private void l() {
        this.f10446a = (ImageView) findViewById(R.id.hw_clear_device_data_right_arrow);
        this.f = (ImageView) findViewById(R.id.hw_clear_fitness_data_right_arrow);
        this.m = (ImageView) findViewById(R.id.hw_clear_medal_data_right_arrow);
        this.l = (ImageView) findViewById(R.id.hw_clear_other_data_right_arrow);
        if (findViewById(R.id.hw_clear_daydream_snore_featured_data_right_arrow) instanceof ImageView) {
            this.q = (ImageView) findViewById(R.id.hw_clear_daydream_snore_featured_data_right_arrow);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.clear_device_data_cache_layout);
        this.c = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.clear_fitness_data_cache_layout);
        this.h = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.clear_medal_data_cache_layout);
        this.k = linearLayout3;
        linearLayout3.setOnClickListener(this);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.clear_other_data_cache_layout);
        this.n = linearLayout4;
        linearLayout4.setOnClickListener(this);
        if (findViewById(R.id.clear_daydream_snore_featured_data_cache_layout) instanceof LinearLayout) {
            LinearLayout linearLayout5 = (LinearLayout) findViewById(R.id.clear_daydream_snore_featured_data_cache_layout);
            this.s = linearLayout5;
            linearLayout5.setOnClickListener(this);
            if (getIntent().getBooleanExtra("showSleepAlertDialog", false)) {
                HandlerExecutor.a(new Runnable() { // from class: rvf
                    @Override // java.lang.Runnable
                    public final void run() {
                        ClearDataCacheActivity.this.c();
                    }
                });
            }
        }
        if (!efb.b(this.u)) {
            this.s.setVisibility(8);
            ((HealthDivider) findViewById(R.id.daydream_snore_cache_divider)).setVisibility(8);
        }
        this.i = (HealthTextView) findViewById(R.id.hw_show_clear_device_text);
        this.j = (HealthTextView) findViewById(R.id.hw_show_clear_fitness_text);
        this.o = (HealthTextView) findViewById(R.id.hw_show_clear_medal_text);
        this.t = (HealthTextView) findViewById(R.id.hw_show_clear_other_text);
        this.r = (HealthTextView) findViewById(R.id.hw_show_clear_daydream_snore_featured_text);
        this.y = (LinearLayout) findViewById(R.id.clear_smart_coach_data_cache_layout);
        this.w = (HealthTextView) findViewById(R.id.hw_show_clear_smart_coach_text);
        this.p = (ImageView) findViewById(R.id.hw_clear_smart_coach_data_right_arrow);
        this.y.setOnClickListener(this);
        this.ad = (LinearLayout) findViewById(R.id.sleep_music_cache_layout);
        this.ai = (HealthTextView) findViewById(R.id.sleep_music_cache_size_text);
        this.ah = (ImageView) findViewById(R.id.sleep_music_cache_icon);
        this.ad.setOnClickListener(this);
        i();
        this.ac = hab.j() || nsn.ae(this.u) || !LanguageUtil.m(this.u) || Utils.o();
        k();
        m();
    }

    public /* synthetic */ void c() {
        this.s.performClick();
    }

    private void k() {
        this.y.setVisibility(this.ac ? 8 : 0);
        ((HealthDivider) findViewById(R.id.smart_coach_data_cache_divider)).setVisibility(this.ac ? 8 : 0);
        if (Utils.l()) {
            this.h.setVisibility(8);
            ((HealthDivider) findViewById(R.id.fitness_cache_divider)).setVisibility(8);
            this.k.setVisibility(8);
            ((HealthDivider) findViewById(R.id.medal_cache_divider)).setVisibility(8);
            this.ad.setVisibility(8);
            ((HealthDivider) findViewById(R.id.sleep_music_cache_divider)).setVisibility(8);
        }
    }

    private void i() {
        String str = this.ae;
        if (str != null) {
            this.i.setText(str);
        } else {
            this.i.setText(nsn.b(this.u, 0L));
        }
        String str2 = this.x;
        if (str2 != null) {
            this.j.setText(str2);
        } else {
            this.j.setText(nsn.b(this.u, 0L));
        }
        String str3 = this.aa;
        if (str3 != null) {
            this.o.setText(str3);
        } else {
            this.o.setText(nsn.b(this.u, 0L));
        }
        String str4 = this.z;
        if (str4 != null) {
            this.t.setText(str4);
        } else {
            this.t.setText(nsn.b(this.u, 0L));
        }
        String str5 = this.ab;
        if (str5 != null) {
            this.r.setText(str5);
        } else {
            this.r.setText(nsn.b(this.u, 0L));
        }
        String str6 = this.ag;
        if (str6 != null) {
            this.w.setText(str6);
        } else {
            this.w.setText(nsn.b(this.u, 0L));
        }
        if (!TextUtils.isEmpty(this.af)) {
            this.ai.setText(this.af);
        } else {
            this.ai.setText(nsn.b(this.u, 0L));
        }
    }

    private void m() {
        if (LanguageUtil.bc(this.u)) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201);
            this.f10446a.setBackground(drawable);
            this.f.setBackground(drawable);
            this.m.setBackground(drawable);
            this.p.setBackground(drawable);
            this.l.setBackground(drawable);
            this.q.setBackground(drawable);
            this.ah.setBackground(drawable);
            return;
        }
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202);
        this.f10446a.setBackground(drawable2);
        this.f.setBackground(drawable2);
        this.m.setBackground(drawable2);
        this.p.setBackground(drawable2);
        this.l.setBackground(drawable2);
        this.q.setBackground(drawable2);
        this.ah.setBackground(drawable2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.h) {
            this.d = "fitness";
            String str = this.x;
            if (str != null && !str.equals(this.v)) {
                o();
            }
        } else if (view == this.k) {
            this.d = "medal";
            String str2 = this.aa;
            if (str2 != null && !str2.equals(this.v)) {
                o();
            }
        } else if (view == this.n) {
            this.d = "other";
            String str3 = this.z;
            if (str3 != null && !str3.equals(this.v)) {
                o();
            }
        } else if (view == this.c) {
            this.d = "device";
            String str4 = this.ae;
            if (str4 != null && !str4.equals(this.v)) {
                o();
            }
        } else {
            dRx_(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dRx_(View view) {
        if (view == this.y) {
            this.d = "smartCoach";
            String str = this.ag;
            if (str == null || str.equals(this.v)) {
                return;
            }
            o();
            return;
        }
        if (view == this.s) {
            this.d = "sleep";
            String str2 = this.ab;
            if (str2 == null || str2.equals(this.v)) {
                return;
            }
            n();
            return;
        }
        if (view == this.ad) {
            this.d = "music";
            if (TextUtils.isEmpty(this.af) || this.af.equals(this.v)) {
                return;
            }
            o();
            return;
        }
        LogUtil.h("ClearDataCacheActivity", "clickOtherCacheLayout else branch");
    }

    private void j() {
        HashSet<String> j = scl.j(this.u);
        this.am = j;
        this.ae = nsn.b(this.u, scl.b(j) + sdj.e().a());
        LogUtil.a("ClearDataCacheActivity", "getAllCacheSize mOtherCacheSizeUi is ", this.z);
        this.x = nsn.b(this.u, scl.e());
        Context context = this.u;
        this.aa = nsn.b(context, scl.e(context));
        Context context2 = this.u;
        this.ag = nsn.b(context2, scl.c(context2));
        Context context3 = this.u;
        this.z = nsn.b(context3, scl.b(context3));
        Context context4 = this.u;
        this.ab = nsn.b(context4, scl.d(context4, "sleepMonitor"));
        Context context5 = this.u;
        this.af = nsn.b(context5, scl.a(context5));
    }

    private void o() {
        if (nsn.a(500)) {
            LogUtil.h("ClearDataCacheActivity", "onClick too fast");
            return;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.u).e(this.u.getResources().getString(R$string.IDS_clear_cache_tips)).czB_(R$string.IDS_device_privacy_clear, R.color._2131296970_res_0x7f0902ca, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClearDataCacheActivity.this.g.dismiss();
                ClearDataCacheActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czy_(R$string.IDS_settings_button_cancal_ios_btn, R.color._2131296987_res_0x7f0902db, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClearDataCacheActivity.this.g.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.g = e;
        e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.g.show();
    }

    private void n() {
        if (nsn.a(500)) {
            LogUtil.h("ClearDataCacheActivity", "onClick too fast");
            return;
        }
        View inflate = View.inflate(this.u, R.layout.activity_sleep_radio_box, null);
        CustomViewDialog.Builder czc_ = new CustomViewDialog.Builder(this.u).czg_(inflate).a(this.u.getResources().getString(R$string.IDS_clear_dream_talk_cache_tips)).cze_(R$string.IDS_device_privacy_clear, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClearDataCacheActivity.this.e.dismiss();
                ClearDataCacheActivity.this.g();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czc_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClearDataCacheActivity.this.e.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.e = czc_.e();
        czc_.a(czc_.c(), R.color._2131297626_res_0x7f09055a);
        czc_.a(czc_.d(), 8);
        HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.radiobutton1);
        this.ak = healthCheckBox;
        healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ClearDataCacheActivity.this.b = z;
                LogUtil.a("ClearDataCacheActivity", " isChecked" + ClearDataCacheActivity.this.b + z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        this.e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        this.e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        LogUtil.c("ClearDataCacheActivity", "enter confirm clear cache");
        if (this.d.equals("fitness")) {
            a();
            return;
        }
        if (this.d.equals("medal")) {
            FileUtil.c(this.u).c();
            this.o.setText(this.v);
            this.aa = this.v;
            c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "1");
            nrh.d(BaseApplication.getContext(), this.u.getResources().getString(R$string.IDS_clear_cache_complish));
            return;
        }
        if (this.d.equals("other")) {
            h();
            this.t.setText(this.v);
            this.z = this.v;
            c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "2");
            nrh.d(BaseApplication.getContext(), this.u.getResources().getString(R$string.IDS_clear_cache_complish));
            return;
        }
        if (this.d.equals("device")) {
            f();
            sdj.e().d();
            this.i.setText(this.v);
            this.ae = this.v;
            c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "4");
            nrh.d(BaseApplication.getContext(), this.u.getResources().getString(R$string.IDS_clear_cache_complish));
            return;
        }
        if (this.d.equals("smartCoach")) {
            FileUtils.e(new File(gzv.e));
            this.w.setText(this.v);
            this.ag = this.v;
            c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "3");
            nrh.d(BaseApplication.getContext(), this.u.getResources().getString(R$string.IDS_clear_cache_complish));
            return;
        }
        if (this.d.equals("sleep")) {
            e();
            return;
        }
        if (this.d.equals("music")) {
            FileUtils.e(new File(StorageUtil.getNativeFilePath(this.u, "h5hosting-drcn.dbankcdn.cn/operationmusic")));
            FileUtils.e(new File(StorageUtil.getNativeFilePath(this.u, "operationmusic")));
            FileUtils.e(new File(StorageUtil.getNativeFilePath(this.u, "com.huawei.health.h5.sleeping-music/sleeping-music")));
            this.ai.setText(this.v);
            this.af = this.v;
            c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "6");
            nrh.d(BaseApplication.getContext(), this.u.getResources().getString(R$string.IDS_clear_cache_complish));
            return;
        }
        LogUtil.h("ClearDataCacheActivity", "confirm clear else branch");
    }

    private void e() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: rva
            @Override // java.lang.Runnable
            public final void run() {
                ClearDataCacheActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        if (this.b) {
            String str = this.u.getFilesDir() + File.separator + "sleepMonitor" + File.separator;
            String str2 = "SLEEP_MONITOR" + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            FileUtils.e(new File(str));
            SharedPreferenceManager.c(BaseApplication.getContext(), String.valueOf(10000), str2);
            this.ab = this.v;
        } else {
            SleepMonitorAlgorithmApi sleepMonitorAlgorithmApi = (SleepMonitorAlgorithmApi) Services.c("SleepMonitor", SleepMonitorAlgorithmApi.class);
            for (bzi bziVar : sleepMonitorAlgorithmApi.querySleepVoiceInfo(0L, System.currentTimeMillis())) {
                if (!bziVar.c()) {
                    sleepMonitorAlgorithmApi.forceDeleteAudioFile(bziVar.a().d());
                } else {
                    LogUtil.h("handleSleepCache no audio favorites", new Object[0]);
                }
            }
            Context context = this.u;
            this.ab = nsn.b(context, scl.d(context, "sleepMonitor"));
        }
        HandlerExecutor.a(new Runnable() { // from class: ruy
            @Override // java.lang.Runnable
            public final void run() {
                ClearDataCacheActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        this.r.setText(this.ab);
        c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "5");
        nrh.d(BaseApplication.getContext(), this.u.getResources().getString(R$string.IDS_clear_cache_complish));
    }

    private void a() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("ClearDataCacheActivity", "confirmClearCache courseApi == null");
        } else {
            courseApi.delCourseUseCache(new UiCallback<Boolean>() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("ClearDataCacheActivity", "confirmClearCache delUseCache onFailure errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    ClearDataCacheActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ClearDataCacheActivity.this.j.setText(ClearDataCacheActivity.this.v);
                            ClearDataCacheActivity.this.x = ClearDataCacheActivity.this.v;
                            ClearDataCacheActivity.this.c(AnalyticsValue.HEALTH_CLEAR_CACHE_DATA_SUCCEED_2040053.value(), "0");
                            nrh.d(BaseApplication.getContext(), ClearDataCacheActivity.this.u.getResources().getString(R$string.IDS_clear_cache_complish));
                        }
                    });
                }
            });
        }
    }

    private void f() {
        Iterator<String> it = this.am.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String[] split = next.split("/plugins/");
            if (split.length > 1) {
                ResourceManager.e().h(split[1]);
            }
            FileUtils.e(new File(next));
        }
    }

    private void h() {
        Iterator<File> it = scl.d(this.u).iterator();
        while (it.hasNext()) {
            FileUtils.e(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.u, str, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
