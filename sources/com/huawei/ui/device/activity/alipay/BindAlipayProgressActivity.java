package com.huawei.ui.device.activity.alipay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.cvx;
import defpackage.ixx;
import defpackage.mfm;
import defpackage.obc;
import defpackage.spn;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class BindAlipayProgressActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9058a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView f;
    private CustomTitleBar h;
    private Context i;
    private HealthButton j;
    private boolean e = false;
    private Handler g = new Handler() { // from class: com.huawei.ui.device.activity.alipay.BindAlipayProgressActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message != null) {
                switch (message.what) {
                    case 10:
                        BindAlipayProgressActivity.this.c(8196);
                        BindAlipayProgressActivity.this.d();
                        break;
                    case 11:
                        LogUtil.a("BindAlipayProgressActivity", "GET_BIND_CODE_START_FLAG, hasBound:", Boolean.valueOf(BindAlipayProgressActivity.this.e));
                        if (!BindAlipayProgressActivity.this.e) {
                            BindAlipayProgressActivity.this.c(8193);
                            break;
                        }
                        break;
                    case 12:
                        BindAlipayProgressActivity.this.finish();
                        break;
                    case 13:
                        BindAlipayProgressActivity.this.e = true;
                        BindAlipayProgressActivity.this.e();
                        break;
                    default:
                        LogUtil.h("BindAlipayProgressActivity", "mHandler default");
                        break;
                }
            }
            LogUtil.h("BindAlipayProgressActivity", "mHandler message is null");
        }
    };
    private PingCallback m = new PingCallback() { // from class: com.huawei.ui.device.activity.alipay.BindAlipayProgressActivity.1
        @Override // com.huawei.health.deviceconnect.callback.PingCallback
        public void onPingResult(int i) {
            LogUtil.a("BindAlipayProgressActivity", "pingDevice onPingResult:", Integer.valueOf(i));
            if (i == 202) {
                BindAlipayProgressActivity.this.g.sendEmptyMessageDelayed(11, 5000L);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_alipay_progress);
        this.i = this;
        a();
        obc.c().c(this);
        obc.c().e(this.m);
        this.g.sendEmptyMessageDelayed(10, 40000L);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.g.removeCallbacksAndMessages(null);
    }

    private void a() {
        this.h = (CustomTitleBar) mfm.cgL_(this, R.id.bind_alipay_title_bar);
        this.j = (HealthButton) mfm.cgL_(this, R.id.bind_alipay_cancel);
        this.f9058a = (ImageView) mfm.cgL_(this, R.id.bind_alipay_fail_image);
        this.b = (HealthTextView) mfm.cgL_(this, R.id.bind_alipay_title);
        this.f = (HealthTextView) mfm.cgL_(this, R.id.bind_alipay_try_tip);
        this.c = (HealthTextView) mfm.cgL_(this, R.id.bind_alipay_try_message);
        this.d = (HealthTextView) mfm.cgL_(this, R.id.bind_alipay_message);
        this.f9058a.setVisibility(8);
        this.f.setVisibility(8);
        this.c.setVisibility(8);
        this.j.setOnClickListener(this);
        this.h.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.alipay.BindAlipayProgressActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BindAlipayProgressActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("BindAlipayProgressActivity", "showBindFialView");
        this.j.setText(getResources().getString(R.string._2130841938_res_0x7f021152));
        this.b.setText(getResources().getString(R.string._2130844436_res_0x7f021b14));
        this.f9058a.setVisibility(0);
        this.f.setVisibility(0);
        this.c.setVisibility(0);
        this.d.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("BindAlipayProgressActivity", "showHasBindedView");
        this.j.setText(getResources().getString(R.string._2130841938_res_0x7f021152));
        this.b.setText(getResources().getString(R.string._2130841450_res_0x7f020f6a));
        this.d.setText(getResources().getString(R.string._2130844461_res_0x7f021b2d));
        this.f9058a.setImageResource(R.mipmap._2131821042_res_0x7f1101f2);
        this.f9058a.setVisibility(0);
        this.f.setVisibility(8);
        this.c.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        obc.c().c(i);
    }

    public void a(spn spnVar) {
        LogUtil.a("BindAlipayProgressActivity", "onReceiveDeviceCommand message");
        if (spnVar == null) {
            LogUtil.h("BindAlipayProgressActivity", "handleReceiveMessage message is null");
            return;
        }
        byte[] b = spnVar.b();
        if (b == null || b.length == 0) {
            LogUtil.h("BindAlipayProgressActivity", "handleReceiveMessage message is empty");
            return;
        }
        int c = c(b, 0);
        int c2 = c(b, 4);
        int c3 = c(b, 8);
        LogUtil.a("BindAlipayProgressActivity", "onReceiveMessage version = ", Integer.valueOf(c), " magic = ", Integer.valueOf(c2));
        if (c3 == 8194) {
            if (b.length > 16) {
                LogUtil.a("BindAlipayProgressActivity", "get bindcode success");
                d(cvx.e(cvx.d(Arrays.copyOfRange(b, 16, b.length))));
                this.g.sendEmptyMessageDelayed(12, 1000L);
                return;
            }
            return;
        }
        if (c3 != 8195) {
            if (c3 == 8197) {
                LogUtil.a("BindAlipayProgressActivity", "has already bind alipay");
                this.g.sendEmptyMessage(13);
                return;
            } else {
                LogUtil.h("BindAlipayProgressActivity", "get bindcode fail");
                return;
            }
        }
        LogUtil.a("BindAlipayProgressActivity", "get bindcode time out");
        HashMap hashMap = new HashMap(10);
        hashMap.put("errorCode", 8195);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.BIND_ALIPAY_TIME_OUT.value(), hashMap, 0);
        this.g.sendEmptyMessage(10);
    }

    private int c(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.bind_alipay_cancel) {
            c();
        } else {
            LogUtil.h("BindAlipayProgressActivity", "click nothing");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(String str) {
        LogUtil.a("BindAlipayProgressActivity", "startAlipay");
        try {
            this.i.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("alipays://platformapi/startapp?appId=2017092008831174&page=%2Fpages%2Fwatch%2Findex&query=" + URLEncoder.encode("code=" + URLEncoder.encode(str, "utf-8"), "utf-8"))));
        } catch (ActivityNotFoundException | UnsupportedEncodingException unused) {
            LogUtil.b("BindAlipayProgressActivity", "startAlipay UnsupportedEncodingException or ActivityNotFoundException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (TextUtils.equals(this.j.getText().toString(), getResources().getString(R.string._2130841939_res_0x7f021153))) {
            c(8196);
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
