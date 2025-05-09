package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.ui.adapter.AchieveLevelAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ixx;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mes;
import defpackage.mfm;
import defpackage.mjq;
import defpackage.mkh;
import defpackage.mki;
import defpackage.mkk;
import defpackage.mku;
import defpackage.mlc;
import defpackage.nqc;
import defpackage.nqf;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class AchieveLevelMainActivity extends BaseActivity {
    private HealthRecycleView b;
    private AchieveLevelAdapter e;
    private Context f;
    private meh g;
    private CustomTitleBar i;
    private nqc m;
    private HashMap<Integer, Integer> h = new HashMap<>(20);
    private HashMap<Integer, Integer> c = new HashMap<>(20);
    private HashMap<Integer, Integer> j = new HashMap<>(20);

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<mki> f8388a = new ArrayList<>(20);
    private Handler d = new Handler() { // from class: com.huawei.pluginachievement.ui.AchieveLevelMainActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1) {
                mkk mkkVar = (mkk) message.obj;
                mku d = mkkVar.d();
                AchieveLevelMainActivity.this.f8388a = mkkVar.e();
                LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "CurrentLevel = ", Integer.valueOf(d.a()));
                AchieveLevelMainActivity.this.e.c(d);
                AchieveLevelMainActivity.this.e.b(AchieveLevelMainActivity.this.f8388a, d.a());
                LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "CurrentForTest ----");
                AchieveLevelMainActivity.this.i.setTitleBarBackgroundColor(mlc.j(d.a()));
            } else if (message.what == 0) {
                double doubleValue = ((Double) message.obj).doubleValue();
                LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "days =", Double.valueOf(doubleValue));
                AchieveLevelMainActivity.this.c(doubleValue);
            } else if (message.what == 13) {
                AchieveLevelMainActivity.this.d((AchieveLevelMainActivity) message.obj);
            } else {
                LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "wrong type message");
                return;
            }
            super.handleMessage(message);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isLargerThanEmui910(CommonUtil.r()) || CommonUtil.az()) {
            setStatusBarColor();
        }
        setContentView(R.layout.achieve_level_new_layout);
        getWindow().getDecorView().setBackgroundResource(R.color._2131299296_res_0x7f090be0);
        LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "onCreate");
        this.f = this;
        mes.c(this).b();
        d();
        b();
        nqf.d(this.f);
    }

    private void d() {
        mlc.d(this.h);
        mlc.a(this.c);
        mlc.b(this.j);
        cancelAdaptRingRegion();
        this.b = (HealthRecycleView) mfm.cgL_(this, R.id.achieve_level_listview);
        this.b.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.b.setIsScroll(false);
        AchieveLevelAdapter achieveLevelAdapter = new AchieveLevelAdapter(this.f, this.f8388a, this.h, this.c, this.j);
        this.e = achieveLevelAdapter;
        this.b.setAdapter(achieveLevelAdapter);
        a();
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.level_titlebar);
        this.i = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        this.i.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131430195_res_0x7f0b0b33), nsf.h(R.string._2130850635_res_0x7f02334b));
        this.i.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveLevelMainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View inflate = View.inflate(AchieveLevelMainActivity.this.f, R.layout.common_pop_menu_single, null);
                AchieveLevelMainActivity.this.m = new nqc(AchieveLevelMainActivity.this.f, inflate);
                HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text);
                healthTextView.setText(R.string._2130840773_res_0x7f020cc5);
                healthTextView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveLevelMainActivity.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        AchieveLevelMainActivity.this.c();
                        Intent intent = new Intent();
                        if (LanguageUtil.m(AchieveLevelMainActivity.this.f)) {
                            intent.setClassName(AchieveLevelMainActivity.this.f, PersonalData.CLASS_NAME_PERSONAL_LEVEL_RULE_CN);
                        } else {
                            intent.setClassName(AchieveLevelMainActivity.this.f, PersonalData.CLASS_NAME_PERSONAL_LEVEL_RULE);
                        }
                        AchieveLevelMainActivity.this.f.startActivity(intent);
                        AchieveLevelMainActivity.this.m.b();
                        ViewClickInstrumentation.clickOnView(view2);
                    }
                });
                AchieveLevelMainActivity.this.m.cEh_(view, 17);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveLevelMainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveLevelMainActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i.setLeftButtonVisibility(0);
        if (LanguageUtil.bc(this.f)) {
            this.i.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
        } else {
            this.i.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
        }
        this.i.setTitleTextColor(getResources().getColor(R.color._2131297371_res_0x7f09045b));
        this.i.setTitleText(BaseApplication.getContext().getResources().getString(R.string._2130840689_res_0x7f020c71));
    }

    private void b() {
        LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "getData");
        this.g = meh.c(BaseApplication.getContext());
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveLevelMainActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (AchieveLevelMainActivity.this.g != null) {
                    AchieveLevelMainActivity achieveLevelMainActivity = AchieveLevelMainActivity.this;
                    achieveLevelMainActivity.a(achieveLevelMainActivity.g);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(meh mehVar) {
        HashMap hashMap = new HashMap(2);
        List<mcz> b = mehVar.b(15, hashMap);
        mcz d = mehVar.d(14, hashMap);
        mkh mkhVar = new mkh(b, d);
        if (b != null && d != null) {
            LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "Get user level is not null");
            d(13, mkhVar);
            return;
        }
        mcz d2 = mehVar.d(5, new HashMap(2));
        if (d2 instanceof AchieveInfo) {
            d(0, Double.valueOf(((AchieveInfo) d2).getUserReachStandardDays()));
        } else {
            d(0, Double.valueOf(0.0d));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(double d) {
        d(1, mjq.e(d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> void d(T t) {
        if (t instanceof mkh) {
            d(1, mjq.a((mkh) t));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("PLGACHIEVE_AchieveLevelMainActivity", "onDestroy");
    }

    private <T> void d(int i, T t) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        this.d.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", "1");
        ixx.d().d(this.f, AnalyticsValue.LEVEL_RULE_1100024.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
