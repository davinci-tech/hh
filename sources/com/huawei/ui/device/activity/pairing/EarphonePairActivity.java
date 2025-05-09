package com.huawei.ui.device.activity.pairing;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.cuz;
import defpackage.jgs;
import defpackage.jpt;
import defpackage.nsy;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class EarphonePairActivity extends BaseActivity implements EarPhoneResponseCallback, View.OnClickListener {
    private HealthButton b;
    private AnimationDrawable c;
    private LinearLayout d;
    private DeviceInfo f;
    private ImageView g;
    private String h;
    private String i;
    private Handler j;
    private LinearLayout k;
    private LinearLayout l;
    private HealthTextView m;
    private HealthTextView n;
    private ImageView o;
    private RelativeLayout p;
    private HealthTextView q;
    private HealthButton r;
    private HealthButton s;
    private RelativeLayout t;
    private CustomTitleBar w;

    /* renamed from: a, reason: collision with root package name */
    private boolean f9194a = false;
    private boolean e = false;

    static class c extends Handler {
        WeakReference<EarphonePairActivity> d;

        c(EarphonePairActivity earphonePairActivity) {
            this.d = new WeakReference<>(earphonePairActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            EarphonePairActivity earphonePairActivity = this.d.get();
            if (earphonePairActivity == null) {
                LogUtil.h("EarphonePairActivity", "handleMessage, activity is null");
                return;
            }
            int i = message.what;
            if (i == -2) {
                LogUtil.h("EarphonePairActivity", "pair time out");
                jgs.c().a(earphonePairActivity.f);
                earphonePairActivity.f();
                return;
            }
            if (i == -1) {
                LogUtil.a("EarphonePairActivity", "handleMessage, pair fail");
                removeMessages(-2);
                earphonePairActivity.f();
                return;
            }
            if (i == 1) {
                LogUtil.a("EarphonePairActivity", "handleMessage, pair success");
                removeMessages(-2);
                earphonePairActivity.h();
                return;
            }
            if (i == 4) {
                LogUtil.a("EarphonePairActivity", "handleMessage, get info success");
                earphonePairActivity.cRJ_(message);
                return;
            }
            if (i == 5) {
                LogUtil.a("EarphonePairActivity", "handleMessage, device skip pair");
                removeMessages(-2);
                if (earphonePairActivity.f9194a) {
                    return;
                }
                earphonePairActivity.g();
                return;
            }
            if (i == 6) {
                LogUtil.a("EarphonePairActivity", "handleMessage, device retry pair");
                removeMessages(-2);
                if (earphonePairActivity.f9194a) {
                    return;
                }
                earphonePairActivity.a();
                return;
            }
            b(message.what, earphonePairActivity);
        }

        private void b(int i, EarphonePairActivity earphonePairActivity) {
            if (i == 0) {
                LogUtil.h("EarphonePairActivity", "earphone is ou side");
                removeMessages(-2);
                earphonePairActivity.c();
            } else if (i == 2) {
                LogUtil.h("EarphonePairActivity", "device ignore pair");
                earphonePairActivity.k();
            } else {
                LogUtil.h("EarphonePairActivity", "other status");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ear_phone_pair_layout);
        b();
        e();
        d();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.cancel_button) {
            jgs.c().a(this.f, this.h);
            c();
            ViewClickInstrumentation.clickOnView(view);
        } else if (view.getId() == R.id.pair_guide_again) {
            a();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.skip_pair_guide) {
                g();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        jgs.c().d(this.f);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        setResult(6);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        jgs.c().b(this.f);
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    private void b() {
        this.w = (CustomTitleBar) nsy.cMc_(this, R.id.pair_guide_custom_title);
        this.t = (RelativeLayout) nsy.cMc_(this, R.id.pair_result_progress_img);
        this.g = (ImageView) nsy.cMc_(this, R.id.pair_guide_progress_anim);
        this.o = (ImageView) nsy.cMc_(this, R.id.pair_result_show_img);
        this.p = (RelativeLayout) nsy.cMc_(this, R.id.pair_result_img_text);
        this.n = (HealthTextView) nsy.cMc_(this, R.id.pairing_progress_guide_single_note);
        this.q = (HealthTextView) nsy.cMc_(this, R.id.pair_result_show_txt);
        this.d = (LinearLayout) nsy.cMc_(this, R.id.cancel_button_layout);
        this.b = (HealthButton) nsy.cMc_(this, R.id.cancel_button);
        this.k = (LinearLayout) nsy.cMc_(this, R.id.ll_pairing_guide_choose);
        this.s = (HealthButton) nsy.cMc_(this, R.id.pair_guide_again);
        this.r = (HealthButton) nsy.cMc_(this, R.id.skip_pair_guide);
        this.l = (LinearLayout) nsy.cMc_(this, R.id.connecting_name_layout);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.connecting_name);
    }

    private void e() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("EarphonePairActivity", "initData intent is null");
            return;
        }
        String stringExtra = intent.getStringExtra("device_identify");
        this.i = stringExtra;
        this.f = jpt.e(stringExtra, "EarphonePairActivity");
        this.j = new c(this);
        i();
    }

    private void j() {
        this.f9194a = true;
        this.g.setVisibility(0);
        this.o.setVisibility(8);
        this.n.setVisibility(8);
        this.k.setVisibility(8);
        this.d.setVisibility(8);
        this.t.setVisibility(0);
        if (this.g.getDrawable() instanceof AnimationDrawable) {
            this.c = (AnimationDrawable) this.g.getDrawable();
        }
        this.c.start();
        this.p.setVisibility(0);
        this.q.setVisibility(0);
        this.q.setText(R.string._2130841387_res_0x7f020f2b);
        this.n.setVisibility(8);
        this.n.setText(R.string._2130845797_res_0x7f022065);
        this.d.setVisibility(0);
    }

    private void d() {
        this.b.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.w.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.EarphonePairActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                jgs.c().a(EarphonePairActivity.this.f, EarphonePairActivity.this.h);
                EarphonePairActivity.this.setResult(6);
                EarphonePairActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.j;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.j = null;
        }
        jgs.c().d();
    }

    private void i() {
        if (!this.j.hasMessages(-2)) {
            this.j.sendEmptyMessageDelayed(-2, 60000L);
        }
        j();
        if (this.f == null) {
            LogUtil.h("EarphonePairActivity", "mCurrentDeviceInfo is null");
        } else {
            jgs.c().e(this.f, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cRJ_(Message message) {
        if (message.obj instanceof cuz) {
            j();
            cuz cuzVar = (cuz) message.obj;
            String b = cuzVar.b();
            this.h = cuzVar.c();
            if (TextUtils.isEmpty(b)) {
                this.m.setText("");
            } else {
                String string = getResources().getString(R.string.IDS_blite_guide_paire_current_device, b);
                this.l.setVisibility(0);
                this.m.setText(string);
            }
            if (!b(cuzVar)) {
                c();
                return;
            }
            if (TextUtils.isEmpty(this.h) || !BluetoothAdapter.checkBluetoothAddress(this.h)) {
                f();
                jgs.c().a(this.f);
                this.j.removeMessages(-2);
            } else if (a(cuzVar) || this.e) {
                LogUtil.a("EarphonePairActivity", "start pair");
                jgs.c().d(this.f, this);
            } else {
                LogUtil.a("EarphonePairActivity", "waite device ignore");
                this.n.setVisibility(0);
                this.n.setText(R.string._2130845797_res_0x7f022065);
            }
        }
    }

    private boolean b(cuz cuzVar) {
        return (cuzVar.d() & 1) != 0;
    }

    private boolean a(cuz cuzVar) {
        int a2 = cuzVar.a();
        int e = cuzVar.e();
        int i = cuzVar.i();
        LogUtil.a("EarphonePairActivity", "earphone status is: ", Integer.valueOf(a2), " earCloseStatus: ", Integer.valueOf(e), " earphoneIgnore: ", Integer.valueOf(i));
        if (a2 == 3 && e == 1) {
            return true;
        }
        return i == 1 && e == 1 && a2 != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.n.setVisibility(8);
        this.e = true;
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.f9194a = false;
        this.c.stop();
        this.g.setVisibility(8);
        this.l.setVisibility(8);
        this.o.setVisibility(0);
        this.o.setImageResource(R.drawable._2131430228_res_0x7f0b0b54);
        this.q.setText(R.string._2130845793_res_0x7f022061);
        this.n.setVisibility(0);
        this.n.setText(R.string._2130845794_res_0x7f022062);
        this.k.setVisibility(0);
        this.d.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.f9194a = false;
        LogUtil.a("EarphonePairActivity", "updatePairSuccessView");
        setResult(5);
        finish();
    }

    @Override // com.huawei.hwdevice.mainprocess.mgr.hwearphonemgr.EarPhoneResponseCallback
    public void onResponse(int i, cuz cuzVar) {
        LogUtil.a("EarphonePairActivity", "executeResponse, errCode: ", Integer.valueOf(i));
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = cuzVar;
        this.j.sendMessage(obtain);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        LogUtil.a("EarphonePairActivity", "initViewTahiti()");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("EarphonePairActivity", "onBackPressed()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
