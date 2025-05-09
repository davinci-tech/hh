package com.huawei.ui.main.stories.me.activity.thirdparty;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.RopeCloudAuthManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.hihealth.HuaweiHiHealth;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAccountAuthAdapter;
import com.huawei.up.utils.Utils;
import defpackage.ehp;
import defpackage.nrh;
import defpackage.nsj;
import defpackage.nsy;
import defpackage.rhb;
import defpackage.rua;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class HealthKitThirdPartyAccountAuthActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f10353a;
    private String b;
    private rua c;
    private String d;
    private LinearLayout e;
    private CustomTitleBar g;
    private HealthButton h;
    private RelativeLayout k;
    private Intent l;
    private String m;
    private HealthRecycleView n;
    private ThirdPartAccountAuthAdapter o;
    private String s;
    private List<rhb> f = new ArrayList(10);
    private List<Boolean> i = new ArrayList(10);
    private HashMap<String, String> p = new HashMap<>();
    private Handler j = new Handler() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAccountAuthActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                return;
            }
            if (message.what == 0) {
                HealthKitThirdPartyAccountAuthActivity.this.a();
                return;
            }
            if (message.what == 1) {
                HealthKitThirdPartyAccountAuthActivity.this.n();
                HealthKitThirdPartyAccountAuthActivity.this.finish();
            } else if (message.what == 2) {
                HealthKitThirdPartyAccountAuthActivity.this.e();
            } else {
                LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "handle message.what");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_third_party_account_auth);
        if (getIntent() == null) {
            finish();
            return;
        }
        this.l = getIntent();
        d();
        j();
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAccountAuthActivity.5
            @Override // java.lang.Runnable
            public void run() {
                HealthKitThirdPartyAccountAuthActivity.this.s = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1015);
                HealthKitThirdPartyAccountAuthActivity.this.c = new rua(BaseApplication.getContext(), HealthKitThirdPartyAccountAuthActivity.this.s);
                HealthKitThirdPartyAccountAuthActivity.this.i();
                LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "get userAT: ", HealthKitThirdPartyAccountAuthActivity.this.s);
            }
        });
        h();
        b();
    }

    private void b() {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAccountAuthActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ArrayList arrayList = new ArrayList();
                StringBuilder sb = new StringBuilder();
                for (rhb rhbVar : HealthKitThirdPartyAccountAuthActivity.this.f) {
                    if (!rhbVar.e()) {
                        String c = rhbVar.c();
                        HealthKitThirdPartyAccountAuthActivity healthKitThirdPartyAccountAuthActivity = HealthKitThirdPartyAccountAuthActivity.this;
                        String d = healthKitThirdPartyAccountAuthActivity.d(healthKitThirdPartyAccountAuthActivity.p, c);
                        if (!Utils.isEmpty(d)) {
                            arrayList.add(d);
                            sb.append(d);
                            sb.append(" ");
                            LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "Click confirm button at ", d, " ", c);
                        }
                    }
                }
                if (Utils.isEmpty(HealthKitThirdPartyAccountAuthActivity.this.s)) {
                    LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "accessToken is null.", HealthKitThirdPartyAccountAuthActivity.this.b);
                    HealthKitThirdPartyAccountAuthActivity.this.finish();
                }
                if (HealthKitThirdPartyAccountAuthActivity.this.c == null) {
                    LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "cloudAuthUtil is null.", HealthKitThirdPartyAccountAuthActivity.this.b);
                    HealthKitThirdPartyAccountAuthActivity.this.finish();
                }
                if (arrayList.size() == HealthKitThirdPartyAccountAuthActivity.this.p.size()) {
                    HealthKitThirdPartyAccountAuthActivity.this.c.a(HealthKitThirdPartyAccountAuthActivity.this.s, ProfileRequestConstants.DELETE_TYPE, HealthKitThirdPartyAccountAuthActivity.this.c.a(HealthKitThirdPartyAccountAuthActivity.this.b));
                    LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "Cancle all authorization.", HealthKitThirdPartyAccountAuthActivity.this.b);
                } else {
                    HealthKitThirdPartyAccountAuthActivity.this.c.b(HealthKitThirdPartyAccountAuthActivity.this.s, ProfileRequestConstants.DELETE_TYPE, HealthKitThirdPartyAccountAuthActivity.this.c.e(HealthKitThirdPartyAccountAuthActivity.this.b, sb.toString().trim()));
                    LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "Cancle partial authorization.", sb.toString().trim());
                }
                HuaweiHiHealth.clearCache();
                Intent intent = new Intent();
                intent.putExtra("activity", "auth");
                HealthKitThirdPartyAccountAuthActivity.this.setResult(-1, intent);
                if (arrayList.size() > 0) {
                    RopeCloudAuthManager.c(HealthKitThirdPartyAccountAuthActivity.this.b);
                }
                HealthKitThirdPartyAccountAuthActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d() {
        this.e = (LinearLayout) nsy.cMc_(this, R.id.auth_layout);
        this.k = (RelativeLayout) nsy.cMc_(this, R.id.list_load_progress);
        this.g = (CustomTitleBar) nsy.cMc_(this, R.id.tb_third_party_account_auth);
        this.n = (HealthRecycleView) nsy.cMc_(this, R.id.rv_third_part_account_auth);
        this.h = (HealthButton) nsy.cMc_(this, R.id.hw_show_confirm);
    }

    private void h() {
        this.n.setLayoutManager(new LinearLayoutManager(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            if (this.l.hasExtra("app_id")) {
                this.b = this.l.getStringExtra("app_id");
            }
            Uri zs_ = AppRouterUtils.zs_(this.l);
            if (zs_ != null) {
                this.b = zs_.getQueryParameter("app_id");
            }
            String str = this.b;
            if (str != null) {
                LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "initData(): mAppId = ", str);
                ehp c = c(this.b);
                if (c == null) {
                    this.j.sendEmptyMessage(1);
                    LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "scopeLangItem is null.");
                } else {
                    this.d = c.c();
                    this.f10353a = nsj.c(Long.parseLong(c.b()) * 1000);
                    this.m = c.d();
                    g();
                }
                this.j.sendEmptyMessage(2);
            }
        } catch (NumberFormatException unused) {
            LogUtil.c("HealthKitThirdPartyAccountAuthActivity", "Exception in initData");
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        nrh.b(getBaseContext(), R$string.IDS_hwh_ali_sport_net_error);
    }

    private ehp c(String str) {
        String b = this.c.b(String.valueOf(str), this.c.a());
        LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "Get scopeLangItemUrl in ThirdPartServiceActivity: ", b);
        ehp e = this.c.e(this.s, "GET", b);
        if (e != null) {
            LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "scopeLangItem get successfully.");
            this.p.putAll(e.a());
        }
        return e;
    }

    private void f() {
        Iterator<rhb> it = this.f.iterator();
        while (it.hasNext()) {
            this.i.add(Boolean.valueOf(it.next().h()));
        }
    }

    private void g() {
        c();
        this.j.sendEmptyMessage(0);
    }

    private void c() {
        this.f.add(new rhb(0, this.f10353a, this.m, true, 2));
        this.f.add(new rhb(3, getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select), true, false, 2));
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        for (Map.Entry<String, String> entry : this.p.entrySet()) {
            if (d(entry.getKey()) == 0) {
                arrayList.add(entry);
            } else if (d(entry.getKey()) == 1) {
                arrayList2.add(entry);
            } else {
                LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "Invaild permission.");
            }
        }
        b(arrayList, 0);
        b(arrayList2, 1);
    }

    public int d(String str) {
        if (str == null || str.length() < 8) {
            return 2;
        }
        return (str.substring(str.length() + (-4)).equals("read") || str.substring(str.length() - 8).equals("readonly")) ? 0 : 1;
    }

    private void b(List<Map.Entry<String, String>> list, int i) {
        int i2 = 0;
        while (i2 < list.size()) {
            Map.Entry<String, String> entry = list.get(i2);
            boolean z = true;
            this.f.add(new rhb(1, entry.getValue(), true, i2 == 0, i));
            Object[] objArr = new Object[8];
            objArr[0] = "Constants: ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = " isFirst: ";
            if (i2 != 0) {
                z = false;
            }
            objArr[3] = Boolean.valueOf(z);
            objArr[4] = " ScopeUrl: ";
            objArr[5] = entry.getKey();
            objArr[6] = " desc: ";
            objArr[7] = entry.getValue();
            LogUtil.a("HealthKitThirdPartyAccountAuthActivity", objArr);
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        f();
        this.e.setVisibility(0);
        this.g.setTitleText(this.d);
        ThirdPartAccountAuthAdapter thirdPartAccountAuthAdapter = new ThirdPartAccountAuthAdapter(this.f, this.d, this.i);
        this.o = thirdPartAccountAuthAdapter;
        thirdPartAccountAuthAdapter.e(new ThirdPartAccountAuthAdapter.CheckButtonClickListener() { // from class: com.huawei.ui.main.stories.me.activity.thirdparty.HealthKitThirdPartyAccountAuthActivity.4
            @Override // com.huawei.ui.main.stories.me.activity.thirdparty.adapter.ThirdPartAccountAuthAdapter.CheckButtonClickListener
            public void onCheckButtonClick(int i, boolean z, List<rhb> list) {
                HealthKitThirdPartyAccountAuthActivity.this.f = list;
                if (z) {
                    HealthKitThirdPartyAccountAuthActivity.this.h.setEnabled(true);
                } else {
                    HealthKitThirdPartyAccountAuthActivity.this.h.setEnabled(false);
                }
            }
        });
        this.n.setAdapter(this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d(HashMap<String, String> hashMap, String str) {
        String str2 = null;
        if (hashMap == null) {
            return null;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue().equals(str)) {
                str2 = key;
            }
        }
        return str2;
    }

    private void j() {
        RelativeLayout relativeLayout;
        if (isFinishing() || (relativeLayout = this.k) == null) {
            return;
        }
        relativeLayout.setVisibility(0);
        LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "mLoadingDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        RelativeLayout relativeLayout = this.k;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
            LogUtil.a("HealthKitThirdPartyAccountAuthActivity", "destroy mLoadingDialog");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
