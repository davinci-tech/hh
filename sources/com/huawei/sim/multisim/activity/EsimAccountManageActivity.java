package com.huawei.sim.multisim.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
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
import com.huawei.up.utils.ErrorCode;
import defpackage.ktx;
import defpackage.lok;
import defpackage.nbx;
import defpackage.ncf;
import defpackage.nsn;
import defpackage.sqo;
import health.compact.a.Base64;
import health.compact.a.CommonUtil;
import java.nio.charset.StandardCharsets;

/* loaded from: classes6.dex */
public class EsimAccountManageActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f8710a;
    private HealthButton b;
    private ImageView d;
    private HealthTextView e;
    private RelativeLayout f;
    private HealthTextView g;
    private CustomTitleBar h;
    private Context i;
    private HealthProgressBar j;
    private lok k;
    private int l;
    private nbx o;
    private String p;
    private int q;
    private MultiSimDeviceInfo t;
    private b c = new b(this);
    private int n = 2;
    private String m = "";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("EsimAccountManageActivity", "onCreate");
        this.i = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.m = intent.getStringExtra("simImsi");
            this.l = intent.getIntExtra("esim_auth_method", 0);
            this.q = intent.getIntExtra("MultiSimSlotId", 0);
        }
        LogUtil.a("EsimAccountManageActivity", "onCreate mEsimAuthMethod = ", Integer.valueOf(this.l), ", mSimCardSlotId = ", Integer.valueOf(this.q));
        this.p = ncf.e(this.i);
        e();
        b();
        d();
        c();
    }

    private void d() {
        setContentView(R.layout.activity_esim_account_manage);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.account_manage_title_bar);
        this.h = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.f = (RelativeLayout) findViewById(R.id.account_manage_waiting_layout);
        this.g = (HealthTextView) findViewById(R.id.account_manage_waiting_text);
        this.j = (HealthProgressBar) findViewById(R.id.account_manage_waiting_image);
        this.f8710a = (RelativeLayout) findViewById(R.id.account_manage_fail_layout);
        this.e = (HealthTextView) findViewById(R.id.account_manage_fail_text);
        this.d = (ImageView) findViewById(R.id.account_manage_fail_image);
        HealthButton healthButton = (HealthButton) findViewById(R.id.account_manage_fail_btn);
        this.b = healthButton;
        healthButton.setOnClickListener(this);
    }

    public void b() {
        LogUtil.a("EsimAccountManageActivity", "initSdk");
        this.k = new lok();
        this.k.d(this.i, this.o, new String(Base64.e("MENBM0RBMzQ5QUExQ0I2OA=="), StandardCharsets.UTF_8), new String(Base64.e("MDZFMUJDMjgwRDFDQ0RCMTFCQjczNEZGREU3QUIyNkYwMzc0QkZFNTY2NUU2MkU4MjZDQzA1N0MwN0IyN0Q2OA=="), StandardCharsets.UTF_8));
    }

    private void c() {
        b(getString(R.string._2130847997_res_0x7f0228fd));
        ktx.e().b(new IBaseResponseCallback() { // from class: com.huawei.sim.multisim.activity.EsimAccountManageActivity.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("EsimAccountManageActivity", "getMultiSimDeviceInfo errorCode = ", Integer.valueOf(i));
                if (i != 0 || !(obj instanceof MultiSimDeviceInfo)) {
                    EsimAccountManageActivity.this.c.sendEmptyMessage(ErrorCode.HWID_IS_STOPED);
                    return;
                }
                EsimAccountManageActivity.this.t = (MultiSimDeviceInfo) obj;
                LogUtil.a("EsimAccountManageActivity", "getMultiSimDeviceInfo mMultiSimDeviceInfo = ", EsimAccountManageActivity.this.t);
                EsimAccountManageActivity esimAccountManageActivity = EsimAccountManageActivity.this;
                if (!esimAccountManageActivity.d(esimAccountManageActivity.t)) {
                    EsimAccountManageActivity.this.c.sendEmptyMessage(ErrorCode.HWID_IS_STOPED);
                    sqo.o("Eid not is Valid.");
                } else {
                    EsimAccountManageActivity.this.h();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(MultiSimDeviceInfo multiSimDeviceInfo) {
        return (multiSimDeviceInfo == null || TextUtils.isEmpty(multiSimDeviceInfo.getEID())) ? false : true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("EsimAccountManageActivity", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.account_manage_fail_btn) {
                finish();
            } else {
                LogUtil.h("EsimAccountManageActivity", "unknown view");
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    static class b extends BaseHandler<EsimAccountManageActivity> {
        b(EsimAccountManageActivity esimAccountManageActivity) {
            super(esimAccountManageActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: csx_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(EsimAccountManageActivity esimAccountManageActivity, Message message) {
            if (message == null) {
                LogUtil.h("EsimAccountManageActivity", "handleMessageWhenReferenceNotNull message is null");
            }
            LogUtil.a("EsimAccountManageActivity", "handleMessageWhenReferenceNotNull message = ", message);
            switch (message.what) {
                case 100001:
                    esimAccountManageActivity.d(message.arg1);
                    break;
                case ErrorCode.HWID_IS_STOPED /* 100002 */:
                    esimAccountManageActivity.c(ErrorCode.HWID_IS_STOPED);
                    esimAccountManageActivity.d(esimAccountManageActivity.getString(R.string.IDS_plugin_device_info_fail), R.drawable.esim_fail_device_img);
                    sqo.o("MSG_ACCOUNT_MANAGE_GET_DEVICEINFO_ERROR");
                    break;
                case 100003:
                    esimAccountManageActivity.csw_(message);
                    break;
                case 100004:
                    esimAccountManageActivity.c(100004);
                    esimAccountManageActivity.d(esimAccountManageActivity.getString(R.string._2130848010_res_0x7f02290a), R.drawable._2131428076_res_0x7f0b02ec);
                    sqo.o("MSG_ACCOUNT_MANAGE_OPERATION_TIMEOUT");
                    break;
                default:
                    LogUtil.h("EsimAccountManageActivity", "Unknown message");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("EsimAccountManageActivity", "handleAuthResult");
        if (i == 1000) {
            a();
            return;
        }
        if (i == 98) {
            d(getString(R.string._2130848010_res_0x7f02290a), R.drawable._2131428076_res_0x7f0b02ec);
            return;
        }
        if (i == -1005) {
            d(this.i.getString(R.string._2130848075_res_0x7f02294b), R.drawable._2131428075_res_0x7f0b02eb);
            return;
        }
        if (i == -1006) {
            d(this.i.getString(R.string._2130848072_res_0x7f022948), R.drawable._2131428075_res_0x7f0b02eb);
            return;
        }
        boolean z = CommonUtil.bh() || ncf.e();
        if (this.l == 5 && z && this.n == 2) {
            this.n = 4;
            h();
        } else {
            d(this.i.getString(R.string._2130848057_res_0x7f022939), R.drawable._2131428075_res_0x7f0b02eb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void csw_(Message message) {
        LogUtil.a("EsimAccountManageActivity", "handleRequestResult");
        c(100004);
        if (message.arg1 == 0) {
            finish();
            return;
        }
        d(this.i.getString(R.string._2130848057_res_0x7f022939), R.drawable._2131428075_res_0x7f0b02eb);
        sqo.o("handleRequestResult fail loading" + message.arg1);
    }

    private void a() {
        LogUtil.a("EsimAccountManageActivity", "changeMultiSimRequest");
        c(100004);
        this.c.sendEmptyMessageDelayed(100004, OpAnalyticsConstants.H5_LOADING_DELAY);
        this.k.bYj_(ncf.e(this.m, this.p), ncf.e(this.t), this.c.obtainMessage(100003));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        b bVar = this.c;
        if (bVar == null || !bVar.hasMessages(i)) {
            return;
        }
        this.c.removeMessages(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, int i) {
        this.f8710a.setVisibility(0);
        this.f.setVisibility(8);
        HealthTextView healthTextView = this.e;
        if (healthTextView != null) {
            healthTextView.setText(str);
            this.e.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.sim.multisim.activity.EsimAccountManageActivity.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    EsimAccountManageActivity.this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (EsimAccountManageActivity.this.e.getLineCount() >= 2) {
                        EsimAccountManageActivity.this.e.setGravity(GravityCompat.START);
                    }
                }
            });
        }
        ImageView imageView = this.d;
        if (imageView != null) {
            Context context = this.i;
            ncf.csP_(context, imageView, nsn.c(context, 120.0f));
            Drawable drawable = getResources().getDrawable(i);
            drawable.setAutoMirrored(true);
            this.d.setImageDrawable(drawable);
        }
    }

    private void b(String str) {
        this.f8710a.setVisibility(8);
        this.f.setVisibility(0);
        HealthTextView healthTextView = this.g;
        if (healthTextView != null) {
            healthTextView.setText(str);
        }
        HealthProgressBar healthProgressBar = this.j;
        if (healthProgressBar != null) {
            Context context = this.i;
            ncf.csP_(context, healthProgressBar, nsn.c(context, 66.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("EsimAccountManageActivity", "startAuth");
        e();
        this.k.bYm_(this.i, this.o, ncf.e(this.m, this.p), ncf.e(this.t), this.c.obtainMessage(100001));
    }

    private void e() {
        g();
        LogUtil.a("EsimAccountManageActivity", "setAuthParam mEsimTempAuthMethod = ", Integer.valueOf(this.n));
        nbx nbxVar = new nbx(this.n);
        this.o = nbxVar;
        nbxVar.setSlotId(this.q);
        this.o.setImsi(this.m);
    }

    private void g() {
        int i = this.l;
        if (i == 5) {
            if (CommonUtil.bh() || ncf.e()) {
                return;
            }
            this.n = 4;
            return;
        }
        this.n = i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("EsimAccountManageActivity", "onDestroy");
        lok lokVar = this.k;
        if (lokVar != null) {
            lokVar.a();
        }
        b bVar = this.c;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
