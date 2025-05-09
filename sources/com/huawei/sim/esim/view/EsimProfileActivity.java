package com.huawei.sim.esim.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.sim.esim.view.adapter.EsimProfileAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.cvx;
import defpackage.jdc;
import defpackage.ktx;
import defpackage.nbp;
import defpackage.ncf;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class EsimProfileActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private EsimProfileAdapter f8691a;
    private int ad;
    private View c;
    private View d;
    private ImageView e;
    private ImageView f;
    private View h;
    private View i;
    private Context l;
    private CustomTextAlertDialog m;
    private CustomTitleBar o;
    private boolean r;
    private ListView s;
    private View u;
    private ImageView v;
    private HealthTextView w;
    private jdc y;
    private View z;
    private ArrayList<nbp> p = new ArrayList<>(16);
    private CommonDialog21 t = null;
    private int j = 3;
    private boolean n = true;
    private byte[] x = null;
    private boolean q = false;
    private IBaseResponseCallback b = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("EsimProfileActivity", "errorCode:", Integer.valueOf(i));
            Message obtain = Message.obtain();
            obtain.what = 5;
            obtain.arg1 = i;
            if (i == 0 && EsimProfileActivity.this.q) {
                EsimProfileActivity.this.k.sendMessageDelayed(obtain, 5000L);
                return;
            }
            sqo.o("mBaseResponseCallback not is SUCCESS: " + i);
            EsimProfileActivity.this.k.sendMessage(obtain);
        }
    };
    private Handler k = new Handler() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("EsimProfileActivity", "message is null");
                return;
            }
            super.handleMessage(message);
            int i = message.what;
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        EsimProfileActivity.this.c();
                        return;
                    } else if (i == 5) {
                        EsimProfileActivity.this.c(message.arg1);
                        return;
                    } else if (i != 6) {
                        LogUtil.h("EsimProfileActivity", "message:", Integer.valueOf(message.what));
                        return;
                    }
                }
                EsimProfileActivity.this.e();
                return;
            }
            EsimProfileActivity.this.d();
        }
    };
    private IBaseResponseCallback g = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("EsimProfileActivity", "errorCode:", Integer.valueOf(i));
            EsimProfileActivity.this.k.removeMessages(6);
            if (i == 1) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                EsimProfileActivity.this.k.sendMessage(obtain);
            } else if (i == 2) {
                Message obtain2 = Message.obtain();
                obtain2.what = 2;
                EsimProfileActivity.this.k.sendMessage(obtain2);
            } else {
                Message obtain3 = Message.obtain();
                obtain3.what = 3;
                EsimProfileActivity.this.k.sendMessage(obtain3);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_esim_profile);
        this.l = this;
        this.q = ncf.b();
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("eSim_profile");
            if (stringExtra != null) {
                this.y = (jdc) c(stringExtra, jdc.class);
            }
            this.r = intent.getBooleanExtra("confirm_status", false);
            this.ad = intent.getIntExtra("esim_new_original_key", 0);
        }
        f();
        j();
        ktx.e().c(this.g);
    }

    private static <T> T c(String str, Class<T> cls) {
        try {
            if (TextUtils.isEmpty(str)) {
                str = "{}";
            }
            return (T) new Gson().fromJson(CommonUtil.z(str), (Class) cls);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("EsimProfileActivity", "getModelFromJson JSONException");
            return null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        int c = ktx.e().c();
        this.j = c;
        if (c == 2) {
            LogUtil.a("EsimProfileActivity", "mBtStatus is DEVICE_CONNECTED");
            d();
        } else if (c == 1) {
            c();
        } else {
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        a();
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.c.setVisibility(8);
        this.u.setEnabled(false);
        this.w.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
        if (LanguageUtil.bc(this)) {
            this.v.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
        } else {
            this.v.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.h.setVisibility(8);
        this.i.setVisibility(8);
        if (!this.r && !this.n) {
            this.c.setVisibility(0);
            this.w.setText(R.string._2130847924_res_0x7f0228b4);
        } else {
            this.c.setVisibility(8);
            this.w.setText(R.string._2130847937_res_0x7f0228c1);
        }
        this.u.setEnabled(true);
        this.w.setTextColor(getResources().getColor(R.color._2131296318_res_0x7f09003e));
        if (LanguageUtil.bc(this)) {
            this.v.setImageResource(R.drawable._2131431476_res_0x7f0b1034);
        } else {
            this.v.setImageResource(R.drawable._2131431488_res_0x7f0b1040);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        this.c.setVisibility(8);
        this.f.startAnimation(AnimationUtils.loadAnimation(this, R.anim._2130771992_res_0x7f010018));
        this.u.setEnabled(false);
        this.w.setTextColor(getResources().getColor(R.color._2131296314_res_0x7f09003a));
        if (LanguageUtil.bc(this)) {
            this.v.setImageResource(R.drawable._2131431477_res_0x7f0b1035);
        } else {
            this.v.setImageResource(R.drawable._2131431489_res_0x7f0b1041);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        a();
        super.onDestroy();
        ktx.e().e(this.g);
    }

    private void j() {
        byte b;
        this.s = (ListView) findViewById(R.id.esim_profile);
        EsimProfileAdapter esimProfileAdapter = new EsimProfileAdapter(this.p, this);
        this.f8691a = esimProfileAdapter;
        this.s.setAdapter((ListAdapter) esimProfileAdapter);
        this.o = (CustomTitleBar) findViewById(R.id.esim_profile_title_bar);
        g();
        this.i = findViewById(R.id.bt_disconnect);
        View findViewById = findViewById(R.id.set_bt_reconnect);
        this.z = findViewById;
        if (this.q) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            this.z.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    new Handler().post(new Runnable() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            ncf.i(EsimProfileActivity.this);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        this.h = findViewById(R.id.bt_connecting);
        this.f = (ImageView) findViewById(R.id.bt_connecting_imgage);
        LogUtil.a("EsimProfileActivity", "mProfilePolicyRules:", cvx.d(this.x));
        byte[] bArr = this.x;
        if (bArr != null && bArr.length > 0 && ((b = bArr[0]) == Byte.MIN_VALUE || b == -64 || b == -32 || b == -96)) {
            LogUtil.a("EsimProfileActivity", "have PPR1 count");
            o();
        }
        this.d = findViewById(R.id.back_button_layout);
        this.e = (ImageView) findViewById(R.id.back_button);
        this.u = findViewById(R.id.next_button_layout);
        h();
    }

    private void g() {
        this.o.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!EsimProfileActivity.this.q) {
                    ktx.e().e(null, 1, EsimProfileActivity.this.b, null);
                } else {
                    ktx.e().e(null, 1, null, null);
                }
                EsimProfileActivity.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void h() {
        b();
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("EsimProfileActivity", "mNextButton setOnClickListener");
                if (EsimProfileActivity.this.x == null || (EsimProfileActivity.this.x.length > 0 && (EsimProfileActivity.this.x[0] == 0 || EsimProfileActivity.this.x[0] == Byte.MIN_VALUE))) {
                    LogUtil.a("EsimProfileActivity", "mProfilePolicyRules is null");
                    if (!EsimProfileActivity.this.r) {
                        if (EsimProfileActivity.this.q) {
                            ktx.e().e(null, 0, null, EsimProfileActivity.this.b);
                        } else {
                            ktx.e().e(null, 0, EsimProfileActivity.this.b, null);
                        }
                        EsimProfileActivity.this.a(R.string._2130847931_res_0x7f0228bb);
                    } else {
                        EsimProfileActivity.this.startActivity(new Intent(EsimProfileActivity.this, (Class<?>) ConfirmActivity.class));
                        EsimProfileActivity.this.finish();
                    }
                } else {
                    LogUtil.a("EsimProfileActivity", "PPR1&PPR2");
                    Intent intent = new Intent(EsimProfileActivity.this, (Class<?>) EsimPprActivity.class);
                    intent.putExtra("profile_policy_rules", EsimProfileActivity.this.x);
                    intent.putExtra("confirm_status", EsimProfileActivity.this.r);
                    EsimProfileActivity.this.startActivity(intent);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.w = (HealthTextView) findViewById(R.id.next_button_text);
        this.v = (ImageView) findViewById(R.id.next_button);
        View findViewById = findViewById(R.id.bt_comunictaion_fail);
        this.c = findViewById;
        findViewById.setVisibility(8);
        if (LanguageUtil.bc(this)) {
            this.v.setImageResource(R.drawable._2131431476_res_0x7f0b1034);
            this.e.setImageResource(R.drawable._2131431488_res_0x7f0b1040);
        }
    }

    private void b() {
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("EsimProfileActivity", "mBackButton setOnClickListener");
                if (!EsimProfileActivity.this.q) {
                    ktx.e().e(null, 1, EsimProfileActivity.this.b, null);
                } else {
                    ktx.e().e(null, 1, null, null);
                }
                EsimProfileActivity.this.i();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void o() {
        LogUtil.a("EsimProfileActivity", "enter showHavePpr1CountDialog");
        String string = this.l.getResources().getString(R.string._2130847903_res_0x7f02289f);
        if (this.m == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.l).b(R.string.IDS_service_area_notice_title).e(string).cyU_(R.string._2130841379_res_0x7f020f23, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("EsimProfileActivity", "showUnbindDialog ok click");
                    EsimProfileActivity.this.m.dismiss();
                    EsimProfileActivity.this.m = null;
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.c("EsimProfileActivity", "showLoginFail cancel click");
                    EsimProfileActivity.this.m.dismiss();
                    EsimProfileActivity.this.m = null;
                    if (EsimProfileActivity.this.q) {
                        ktx.e().e(null, 1, null, null);
                    } else {
                        ktx.e().e(null, 1, EsimProfileActivity.this.b, null);
                    }
                    EsimProfileActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.m = a2;
            a2.setCancelable(false);
            this.m.show();
        }
    }

    private void f() {
        jdc jdcVar = this.y;
        if (jdcVar != null) {
            if (!TextUtils.isEmpty(jdcVar.b()) && !Constants.NULL.equalsIgnoreCase(this.y.b())) {
                nbp nbpVar = new nbp();
                nbpVar.a(getString(R.string._2130847906_res_0x7f0228a2));
                nbpVar.c(this.y.b());
                nbpVar.a(this.y.e());
                nbpVar.e(1);
                this.p.add(nbpVar);
            } else {
                LogUtil.h("EsimProfileActivity", "initData() SPN is null or empty");
            }
            if (!TextUtils.isEmpty(this.y.d()) && !Constants.NULL.equalsIgnoreCase(this.y.d())) {
                nbp nbpVar2 = new nbp();
                nbpVar2.a(getString(R.string._2130847944_res_0x7f0228c8));
                nbpVar2.c(this.y.d());
                nbpVar2.e(0);
                this.p.add(nbpVar2);
            } else {
                LogUtil.h("EsimProfileActivity", "initData() profile name is null or empty");
            }
            if (!TextUtils.isEmpty(this.y.a()) && !Constants.NULL.equalsIgnoreCase(this.y.a())) {
                nbp nbpVar3 = new nbp();
                nbpVar3.a(getString(R.string._2130847905_res_0x7f0228a1));
                nbpVar3.c(this.y.a());
                nbpVar3.e(0);
                this.p.add(nbpVar3);
            } else {
                LogUtil.h("EsimProfileActivity", "initData() ICCID is null or empty");
            }
            this.x = this.y.c();
            return;
        }
        LogUtil.h("EsimProfileActivity", "initData() mProfile is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.r) {
            LogUtil.c("EsimProfileActivity", "commandResult errorCode:", Integer.valueOf(i));
            a();
            return;
        }
        ncf.c(i, true);
        if (i == 0) {
            this.n = true;
            Intent intent = new Intent(this, (Class<?>) EsimProfileSuccessActivity.class);
            intent.putExtra("confirm_status", false);
            startActivity(intent);
        } else {
            this.n = false;
            if (ktx.e().c() == 2) {
                d();
            }
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        LogUtil.a("EsimProfileActivity", "showLoadingDialog");
        CommonDialog21 commonDialog21 = this.t;
        if (commonDialog21 == null) {
            new CommonDialog21(this, R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.t = a2;
            a2.e(getResources().getString(i));
            this.t.setCancelable(false);
        } else {
            commonDialog21.e(getResources().getString(i));
        }
        this.t.a();
    }

    private void a() {
        LogUtil.a("EsimProfileActivity", "enter dismissLoadingDialog");
        if (isFinishing()) {
            LogUtil.h("EsimProfileActivity", "dismissLoadingDialog isFinishing");
            return;
        }
        CommonDialog21 commonDialog21 = this.t;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("EsimProfileActivity", "dismissLoadingDialog");
        this.t.cancel();
        this.t = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        LogUtil.a("EsimProfileActivity", "onKeyDown");
        if (i == 4) {
            if (ktx.e() != null) {
                if (this.q) {
                    ktx.e().e(null, 1, null, null);
                } else {
                    ktx.e().e(null, 1, this.b, null);
                }
            }
            i();
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        Intent intent = new Intent();
        if (this.ad == 1) {
            intent.setClass(this, EsimManagerActivity.class);
        } else {
            intent.setClass(this, EsimActivationActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
