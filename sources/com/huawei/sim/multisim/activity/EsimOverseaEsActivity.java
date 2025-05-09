package com.huawei.sim.multisim.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.me.adapter.AdapterWithBottomView;
import com.huawei.up.utils.ErrorCode;
import defpackage.ktx;
import defpackage.lok;
import defpackage.nbx;
import defpackage.ncf;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.Base64;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/* loaded from: classes6.dex */
public class EsimOverseaEsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private int f8712a;
    private HealthButton c;
    private Context e;
    private HealthTextView g;
    private lok h;
    private RelativeLayout i;
    private ImageView j;
    private nbx l;
    private boolean m;
    private int o;
    private String p;
    private HealthProgressBar q;
    private CustomTitleBar r;
    private RelativeLayout s;
    private HealthTextView t;
    private String n = "";
    private b f = new b(this);
    private MultiSimDeviceInfo d = new MultiSimDeviceInfo();
    private String b = "";
    private int k = 2;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("EsimOverseaEsActivity", "onCreate");
        this.e = this;
        this.p = ncf.e(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f8712a = intent.getIntExtra("esim_auth_method", 0);
            this.n = intent.getStringExtra("simImsi");
            this.o = intent.getIntExtra("MultiSimSlotId", 0);
            this.m = intent.getBooleanExtra("esim_is_one_open_method", false);
        }
        f();
        g();
        c();
        i();
    }

    public void c() {
        LogUtil.a("EsimOverseaEsActivity", "initSdk");
        this.h = new lok();
        this.h.d(this.e, this.l, new String(Base64.e("MENBM0RBMzQ5QUExQ0I2OA=="), StandardCharsets.UTF_8), new String(Base64.e("MDZFMUJDMjgwRDFDQ0RCMTFCQjczNEZGREU3QUIyNkYwMzc0QkZFNTY2NUU2MkU4MjZDQzA1N0MwN0IyN0Q2OA=="), StandardCharsets.UTF_8));
    }

    private void g() {
        setContentView(R.layout.activity_esim_oversea_es);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.esim_title_bar);
        this.r = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.s = (RelativeLayout) findViewById(R.id.esim_waiting_layout);
        this.q = (HealthProgressBar) findViewById(R.id.esim_waiting_image);
        this.t = (HealthTextView) findViewById(R.id.esim_waiting_text);
        this.i = (RelativeLayout) findViewById(R.id.esim_fail_layout);
        this.j = (ImageView) findViewById(R.id.esim_fail_image);
        this.g = (HealthTextView) findViewById(R.id.esim_fail_text);
        HealthButton healthButton = (HealthButton) findViewById(R.id.esim_fail_btn);
        this.c = healthButton;
        healthButton.setOnClickListener(this);
    }

    private void i() {
        d(getString(R.string._2130847997_res_0x7f0228fd));
        ktx.e().b(new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.activity.EsimOverseaEsActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimOverseaEsActivity", "getMultiSimDeviceInfo onResponse errorCode :", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof MultiSimDeviceInfo)) {
                    EsimOverseaEsActivity.this.f.sendEmptyMessage(100007);
                    return;
                }
                EsimOverseaEsActivity.this.d = (MultiSimDeviceInfo) obj;
                LogUtil.a("EsimOverseaEsActivity", "getMultiSimDeviceInfo mDeviceInfo :", EsimOverseaEsActivity.this.d);
                EsimOverseaEsActivity esimOverseaEsActivity = EsimOverseaEsActivity.this;
                if (!esimOverseaEsActivity.d(esimOverseaEsActivity.d)) {
                    EsimOverseaEsActivity.this.f.sendEmptyMessage(100007);
                } else {
                    EsimOverseaEsActivity.this.m();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(MultiSimDeviceInfo multiSimDeviceInfo) {
        return (multiSimDeviceInfo == null || multiSimDeviceInfo.getEID() == null || "".equals(multiSimDeviceInfo.getEID())) ? false : true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("EsimOverseaEsActivity", "view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.esim_fail_btn) {
                finish();
            } else {
                LogUtil.h("EsimOverseaEsActivity", "unknown view");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static class b extends BaseHandler<EsimOverseaEsActivity> {
        b(EsimOverseaEsActivity esimOverseaEsActivity) {
            super(esimOverseaEsActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csG_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimOverseaEsActivity esimOverseaEsActivity, Message message) {
            if (message == null) {
                LogUtil.h("EsimOverseaEsActivity", "handleMessageWhenReferenceNotNull message is null");
            }
            LogUtil.a("EsimOverseaEsActivity", "handleMessageWhenReferenceNotNull message:", message);
            switch (message.what) {
                case 100001:
                    esimOverseaEsActivity.c(message.arg1);
                    break;
                case ErrorCode.HWID_IS_STOPED /* 100002 */:
                    esimOverseaEsActivity.csF_(message);
                    break;
                case 100003:
                    esimOverseaEsActivity.csE_(message);
                    break;
                case 100004:
                    esimOverseaEsActivity.j();
                    esimOverseaEsActivity.a(esimOverseaEsActivity.getString(R.string._2130848010_res_0x7f02290a), R.drawable._2131428076_res_0x7f0b02ec);
                    sqo.o("MSG_ESIM_OPERATION_TIMEOUT");
                    break;
                case AdapterWithBottomView.TYPE_BOTTOM /* 100005 */:
                    esimOverseaEsActivity.e(AdapterWithBottomView.TYPE_BOTTOM);
                    esimOverseaEsActivity.a(esimOverseaEsActivity.getString(R.string._2130848010_res_0x7f02290a), R.drawable._2131428076_res_0x7f0b02ec);
                    break;
                case 100006:
                    esimOverseaEsActivity.csD_(esimOverseaEsActivity, message);
                    break;
                case 100007:
                    esimOverseaEsActivity.e(100007);
                    esimOverseaEsActivity.a(esimOverseaEsActivity.getString(R.string.IDS_plugin_device_info_fail), R.drawable.esim_fail_device_img);
                    sqo.o("MSG_ESIM_GET_DEVICEINFO_ERROR");
                    break;
                case 100008:
                    esimOverseaEsActivity.csF_(message);
                    break;
                default:
                    LogUtil.h("EsimOverseaEsActivity", "Unknown message");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("EsimOverseaEsActivity", "handleAuthResult");
        if (i == 1000) {
            h();
            return;
        }
        if (i == 98) {
            a(getString(R.string._2130848010_res_0x7f02290a), R.drawable._2131428076_res_0x7f0b02ec);
            return;
        }
        if (i == -1005) {
            if (this.m) {
                a(this.e.getString(R.string._2130848061_res_0x7f02293d), R.drawable._2131428075_res_0x7f0b02eb);
            } else {
                a(this.e.getString(R.string._2130848062_res_0x7f02293e), R.drawable._2131428075_res_0x7f0b02eb);
            }
            sqo.o("UNSUPPORTED_ANDROID_VERSION_EXCEPTION");
            return;
        }
        if (i == -1006) {
            if (this.m) {
                a(this.e.getString(R.string._2130848059_res_0x7f02293b), R.drawable._2131428075_res_0x7f0b02eb);
            } else {
                a(this.e.getString(R.string._2130848060_res_0x7f02293c), R.drawable._2131428075_res_0x7f0b02eb);
            }
            sqo.o("ONLY_PRIMARY_CARD_EXCEPTION");
            return;
        }
        boolean z = CommonUtil.bh() || ncf.e();
        if (this.f8712a == 5 && z && this.k == 2) {
            this.k = 4;
            m();
        } else {
            a(this.e.getString(R.string._2130848057_res_0x7f022939), R.drawable._2131428075_res_0x7f0b02eb);
            sqo.o("Esim fail loading");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void csF_(Message message) {
        LogUtil.a("EsimOverseaEsActivity", "handleQueryResult");
        if (message.arg1 == 1000) {
            b();
            return;
        }
        if (message.arg1 == 98) {
            a(getString(R.string._2130848010_res_0x7f02290a), R.drawable._2131428076_res_0x7f0b02ec);
            return;
        }
        if (message.arg1 == 1200) {
            Bundle data = message.getData();
            if (data != null) {
                String string = data.getString("ActivationCode");
                this.b = string;
                LogUtil.a("EsimOverseaEsActivity", "handleQueryResult mAcCode = ", string);
                d();
                return;
            }
            LogUtil.h("EsimOverseaEsActivity", "handleQueryResult bundle is null");
            a(this.e.getString(R.string._2130848057_res_0x7f022939), R.drawable._2131428075_res_0x7f0b02eb);
            return;
        }
        if (message.arg1 == -1011) {
            a(this.e.getString(R.string._2130848123_res_0x7f02297b), R.drawable._2131428077_res_0x7f0b02ed);
            sqo.o("ERROR_CODE_NO_ACTIVATED_CODE_EXCEPTION");
        } else {
            a(this.e.getString(R.string._2130848057_res_0x7f022939), R.drawable._2131428075_res_0x7f0b02eb);
            sqo.o("Esim fail loading.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void csE_(Message message) {
        LogUtil.a("EsimOverseaEsActivity", "handleOpenResult");
        j();
        if (message.arg1 == 0) {
            return;
        }
        a(this.e.getString(R.string._2130848057_res_0x7f022939), R.drawable._2131428075_res_0x7f0b02eb);
        sqo.o("Esim fail loading.." + message.arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void csD_(EsimOverseaEsActivity esimOverseaEsActivity, Message message) {
        esimOverseaEsActivity.e(AdapterWithBottomView.TYPE_BOTTOM);
        if (message.arg1 == 0) {
            ktx.e().a(true);
            finish();
            return;
        }
        ktx.e().a(false);
        a(this.e.getString(R.string._2130847883_res_0x7f02288b), R.drawable._2131428075_res_0x7f0b02eb);
        sqo.o("Esim fail loading :" + message.arg1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        b bVar = this.f;
        if (bVar == null || !bVar.hasMessages(i)) {
            return;
        }
        this.f.removeMessages(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        this.i.setVisibility(0);
        this.s.setVisibility(8);
        sqo.o("initFailView failReason :" + str);
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.setText(str);
            this.g.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.sim.multisim.activity.EsimOverseaEsActivity.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    EsimOverseaEsActivity.this.g.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (EsimOverseaEsActivity.this.g.getLineCount() >= 2) {
                        EsimOverseaEsActivity.this.g.setGravity(GravityCompat.START);
                    }
                }
            });
        }
        ImageView imageView = this.j;
        if (imageView != null) {
            Context context = this.e;
            ncf.csP_(context, imageView, nsn.c(context, 120.0f));
            Drawable drawable = getResources().getDrawable(i);
            drawable.setAutoMirrored(true);
            this.j.setImageDrawable(drawable);
        }
    }

    private void d(String str) {
        this.i.setVisibility(8);
        this.s.setVisibility(0);
        HealthTextView healthTextView = this.t;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        HealthProgressBar healthProgressBar = this.q;
        if (healthProgressBar != null) {
            Context context = this.e;
            ncf.csP_(context, healthProgressBar, nsn.c(context, 66.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("EsimOverseaEsActivity", "removeTimeOutMessage");
        b bVar = this.f;
        if (bVar == null || !bVar.hasMessages(100004)) {
            return;
        }
        this.f.removeMessages(100004);
    }

    private void b() {
        LogUtil.a("EsimOverseaEsActivity", "addMultiSimRequest");
        j();
        this.f.sendEmptyMessageDelayed(100004, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.h.bYi_(ncf.e(this.n, this.p), ncf.e(this.d), this.f.obtainMessage(100003));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        LogUtil.a("EsimOverseaEsActivity", "startAuth");
        f();
        this.h.bYm_(this.e, this.l, ncf.e(this.n, this.p), ncf.e(this.d), this.f.obtainMessage(100001));
    }

    private void f() {
        l();
        LogUtil.a("EsimOverseaEsActivity", "setAuthParam mTempAuthMethod = ", Integer.valueOf(this.k));
        nbx nbxVar = new nbx(this.k);
        this.l = nbxVar;
        nbxVar.setImsi(this.n);
        this.l.setSlotId(this.o);
    }

    private void l() {
        int i = this.f8712a;
        if (i == 5) {
            if (CommonUtil.bh() || ncf.e()) {
                return;
            }
            this.k = 4;
            return;
        }
        this.k = i;
    }

    private void h() {
        LogUtil.a("EsimOverseaEsActivity", "queryMultiSimServiceStatus");
        this.f.sendEmptyMessageDelayed(100004, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.h.bYk_(ncf.e(this.n, this.p), ncf.e(this.d), this.f.obtainMessage(ErrorCode.HWID_IS_STOPED));
    }

    private void e() {
        this.f.sendEmptyMessageDelayed(100004, 300000L);
        this.h.bYh_(ncf.e(this.n, this.p), ncf.e(this.d), this.f.obtainMessage(100008));
    }

    void d() {
        d(getString(R.string._2130848014_res_0x7f02290e));
        this.f.sendEmptyMessageDelayed(AdapterWithBottomView.TYPE_BOTTOM, 120000L);
        ktx.e().e(this.b, new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.activity.EsimOverseaEsActivity.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimOverseaEsActivity", "downLoadProfile first errorCode =", Integer.valueOf(i));
            }
        }, new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.activity.EsimOverseaEsActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimOverseaEsActivity", "downLoadProfile second errorCode =", Integer.valueOf(i));
                ncf.c(i, true);
                EsimOverseaEsActivity.this.e(AdapterWithBottomView.TYPE_BOTTOM);
                Message obtainMessage = EsimOverseaEsActivity.this.f.obtainMessage(100006);
                obtainMessage.arg1 = i;
                if (i == 0) {
                    EsimOverseaEsActivity.this.f.sendMessageDelayed(obtainMessage, 5000L);
                } else {
                    EsimOverseaEsActivity.this.f.sendMessage(obtainMessage);
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("EsimOverseaEsActivity", "Enter onActivityResult(): requestCode= ", Integer.valueOf(i), " resultCode=", Integer.valueOf(i2));
        if (i != 101) {
            LogUtil.h("EsimOverseaEsActivity", "onActivityResult requestCode is invalid");
            return;
        }
        if (intent == null) {
            LogUtil.h("EsimOverseaEsActivity", "data is null");
            sqo.o("data is null");
            a(this.e.getString(R.string._2130848058_res_0x7f02293a), R.drawable._2131428077_res_0x7f0b02ed);
            return;
        }
        if (i2 == 1) {
            String stringExtra = intent.getStringExtra("ActivationCode");
            this.b = stringExtra;
            LogUtil.a("EsimOverseaEsActivity", "onActivityResult mAcCode = ", stringExtra);
            d();
        } else if (i2 == 2) {
            d(String.format(Locale.ENGLISH, this.e.getString(R.string._2130848122_res_0x7f02297a), UnitUtil.e(1.0d, 1, 0), UnitUtil.e(5.0d, 1, 0)));
            e();
        } else {
            a(this.e.getString(R.string._2130848058_res_0x7f02293a), R.drawable._2131428077_res_0x7f0b02ed);
            sqo.o("query fail resultCode：" + i2);
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("EsimOverseaEsActivity", "onDestroy");
        a();
        j();
        b bVar = this.f;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    public void a() {
        LogUtil.a("EsimOverseaEsActivity", "finishSdk");
        lok lokVar = this.h;
        if (lokVar != null) {
            lokVar.a();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
