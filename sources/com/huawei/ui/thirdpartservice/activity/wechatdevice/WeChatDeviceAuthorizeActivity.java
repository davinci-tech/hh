package com.huawei.ui.thirdpartservice.activity.wechatdevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnlayout.HealthColumnLinearLayout;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceAuthorizeActivity;
import com.huawei.ui.thirdpartservice.syncdata.wechatdevice.SdkManager;
import defpackage.bzs;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.shl;
import defpackage.sid;
import defpackage.sie;
import defpackage.sii;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class WeChatDeviceAuthorizeActivity extends BaseActivity {
    private boolean e;
    private DeviceInfo f;
    private ImageView g;
    private String i;
    private String j;
    private CustomTitleBar l;
    private String m;
    private final Handler n = new a();
    private CommonDialog21 o = null;
    private CustomViewDialog h = null;
    private WebView k = null;

    /* renamed from: a, reason: collision with root package name */
    private final int[] f10558a = {R.string.IDS_wx_device_auth_privilege_1, R.string.IDS_wx_device_auth_privilege_2, R.string.IDS_wx_device_auth_privilege_3, R.string.IDS_wx_device_auth_privilege_4};
    private final String b = "file:///android_asset/silent_login/entry/index.html";
    private final String d = "silentLogin";
    private final BroadcastReceiver c = new BroadcastReceiver() { // from class: com.huawei.ui.thirdpartservice.activity.wechatdevice.WeChatDeviceAuthorizeActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.a("WeChatDeviceAuthorizeActivity", "Receive broadcast action = ", action);
            if (action.equals("com.huawei.ui.thirdpartservice.SILENT_LOGIN_JS_RESPONSE")) {
                WeChatDeviceAuthorizeActivity.this.e(intent.getStringExtra("serverAuthCode"));
            }
            if (action.equals("com.huawei.ui.thirdpartservice.WX_MINIPTOGRAM_LAUNCH")) {
                ReleaseLogUtil.e("R_WeChatDeviceAuthorizeActivity", "mBroadcastReceiver launch wechat app");
                WeChatDeviceAuthorizeActivity.this.n.removeMessages(-1);
                if (!CommonUtil.x(WeChatDeviceAuthorizeActivity.this)) {
                    WeChatDeviceAuthorizeActivity.this.e = true;
                } else {
                    WeChatDeviceAuthorizeActivity.this.finish();
                }
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wechat_device_authorize);
        dXD_(getIntent());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        e();
        if (this.e) {
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WebView webView = this.k;
        if (webView != null) {
            webView.removeAllViews();
            this.k.destroy();
            this.k = null;
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.c);
    }

    private void dXD_(Intent intent) {
        if (intent == null) {
            return;
        }
        o();
        String stringExtra = intent.getStringExtra("name");
        this.m = intent.getStringExtra("wxLinkSdkId");
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.device_title_bar);
        this.l = customTitleBar;
        customTitleBar.setTitleText(stringExtra);
        this.g = (ImageView) findViewById(R.id.device_img);
        this.j = intent.getStringExtra("identify");
        e(this.j, intent.getStringExtra("deviceIconUrl"));
        boolean booleanExtra = intent.getBooleanExtra("isLocalBound", true);
        this.f = booleanExtra ? sii.a(this.j) : null;
        final boolean booleanExtra2 = intent.getBooleanExtra("isAuthorized", false);
        this.i = booleanExtra2 ? intent.getStringExtra("deviceCode") : null;
        ((HealthTextView) findViewById(R.id.device_authorized_tips)).setText(booleanExtra2 ? R.string.IDS_wx_device_cancel_auth_tips : R.string.IDS_wx_device_auth_tips);
        i();
        e(!booleanExtra);
        HealthButton healthButton = (HealthButton) findViewById(R.id.btn_authorize);
        healthButton.setBackgroundResource(booleanExtra2 ? R.drawable._2131428850_res_0x7f0b05f2 : R.drawable.button_background_emphasize);
        healthButton.setTextColor(getColor(booleanExtra2 ? R.color._2131296927_res_0x7f09029f : R.color._2131296937_res_0x7f0902a9));
        healthButton.setText(booleanExtra2 ? R.string._2130848927_res_0x7f022c9f : R.string._2130842480_res_0x7f021370);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sgn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeChatDeviceAuthorizeActivity.this.dXG_(booleanExtra2, view);
            }
        });
    }

    public /* synthetic */ void dXG_(boolean z, View view) {
        if (z) {
            d();
        } else {
            f();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void o() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.ui.thirdpartservice.SILENT_LOGIN_JS_RESPONSE");
        intentFilter.addAction("com.huawei.ui.thirdpartservice.WX_MINIPTOGRAM_LAUNCH");
        LocalBroadcastManager.getInstance(this).registerReceiver(this.c, intentFilter);
    }

    private void e(String str, String str2) {
        LogUtil.a("WeChatDeviceAuthorizeActivity", "loadDeviceImg Identify = ", CommonUtil.l(str), " , Url = ", str2);
        Bitmap dXU_ = !TextUtils.isEmpty(str) ? sid.dXU_(str) : null;
        if (dXU_ != null) {
            this.g.setImageBitmap(dXU_);
            LogUtil.c("WeChatDeviceAuthorizeActivity", "load device image from local , Identify = ", CommonUtil.l(str));
        } else {
            LogUtil.c("WeChatDeviceAuthorizeActivity", "load device image from network , Url = ", str2);
            nrf.cIv_(str2, RequestOptions.bitmapTransform(new RoundedCorners((int) getResources().getDimension(R.dimen._2131362360_res_0x7f0a0238))), this.g);
        }
    }

    private void i() {
        HealthColumnLinearLayout healthColumnLinearLayout = (HealthColumnLinearLayout) findViewById(R.id.device_authorized_privilege_layout);
        for (int i : this.f10558a) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_wechat_device_auth_dot, (ViewGroup) null, false);
            ((HealthTextView) inflate.findViewById(R.id.tv_third_party_auth_content)).setText(i);
            healthColumnLinearLayout.addView(inflate);
        }
    }

    private void e(boolean z) {
        ((HealthTextView) findViewById(R.id.device_unbound_title)).setVisibility(z ? 0 : 4);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.device_unbound_tip_layout);
        HealthTextView healthTextView = (HealthTextView) relativeLayout.findViewById(R.id.tv_third_party_auth_content);
        relativeLayout.setVisibility(z ? 0 : 8);
        healthTextView.setVisibility(z ? 0 : 8);
        healthTextView.setText(R.string.IDS_wx_device_unbound_tip);
    }

    private void f() {
        if (c() && b()) {
            g();
            sid.b(this.f, new ICloudOperationResult() { // from class: sgr
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    WeChatDeviceAuthorizeActivity.this.b((String) obj, str, z);
                }
            });
        }
    }

    public /* synthetic */ void b(String str, String str2, boolean z) {
        ReleaseLogUtil.e("R_WeChatDeviceAuthorizeActivity", "getDeviceCodeAsync deviceCode = ", str);
        Message obtainMessage = this.n.obtainMessage();
        obtainMessage.what = z ? 1 : -1;
        obtainMessage.obj = str;
        this.n.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dXC_(Message message) {
        int i = message.what;
        if (i == -1) {
            e();
            nrh.b(this, message.obj instanceof Integer ? ((Integer) message.obj).intValue() : R.string._2130842411_res_0x7f02132b);
        } else if (i == 1) {
            if (message.obj instanceof String) {
                this.i = (String) message.obj;
            }
            h();
        } else if (i == 2 && (message.obj instanceof String)) {
            e((String) message.obj);
        }
    }

    private void d() {
        if (c() && b()) {
            j();
        }
    }

    private void h() {
        H5ProWebView h5ProWebView = (H5ProWebView) findViewById(R.id.h5_webview);
        boolean z = CommonUtil.z(this);
        LogUtil.a("WeChatDeviceAuthorizeActivity", "performSilentLogin isHmsLiteEnable = ", Boolean.valueOf(z));
        if (!z) {
            bzs.e().initH5Pro();
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addCustomizeJsModule("silentLogin", sie.class);
            H5ProClient.startH5LightApp("file:///android_asset/silent_login/entry/index.html", h5ProWebView, builder.build());
            return;
        }
        LogUtil.a("WeChatDeviceAuthorizeActivity", "performSilentLogin with HMS Lite");
        if (this.k == null) {
            this.k = h5ProWebView.h.getWebView();
        }
        runOnUiThread(new shl(this.k, new ICloudOperationResult() { // from class: sgp
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z2) {
                WeChatDeviceAuthorizeActivity.this.e((String) obj, str, z2);
            }
        }));
    }

    public /* synthetic */ void e(String str, String str2, boolean z) {
        LogUtil.a("WeChatDeviceAuthorizeActivity", "HMS Lite auth isSuccess = ", Boolean.valueOf(z), " , response = ", str);
        boolean z2 = z && !TextUtils.isEmpty(str2);
        Message obtainMessage = this.n.obtainMessage();
        obtainMessage.what = z2 ? 2 : -1;
        if (!z2) {
            str2 = null;
        }
        obtainMessage.obj = str2;
        this.n.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        LogUtil.a("WeChatDeviceAuthorizeActivity", "handleAuthorize serverAuthCode = ", str);
        if (TextUtils.isEmpty(this.i) || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.c("R_WeChatDeviceAuthorizeActivity", "handleAuthorize deviceCode or serverAuthCode is empty");
            e();
            nrh.b(this, R.string._2130842411_res_0x7f02132b);
            return;
        }
        sid.a(a(str), new ICloudOperationResult() { // from class: sgu
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                WeChatDeviceAuthorizeActivity.this.d((String) obj, str2, z);
            }
        });
    }

    public /* synthetic */ void d(String str, final String str2, boolean z) {
        char c;
        LogUtil.a("WeChatDeviceAuthorizeActivity", "handleAuthorize bindDeviceSync isSuccess = ", Boolean.valueOf(z), ", ticket = ", str2);
        if (z && !TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.e("R_WeChatDeviceAuthorizeActivity", "handleAuthorize bindDeviceSync success");
            this.n.post(new Runnable() { // from class: sgw
                @Override // java.lang.Runnable
                public final void run() {
                    WeChatDeviceAuthorizeActivity.this.b(str2);
                }
            });
            return;
        }
        ReleaseLogUtil.c("R_WeChatDeviceAuthorizeActivity", "handleAuthorize bindDeviceSync error :", str2);
        e();
        str2.hashCode();
        int hashCode = str2.hashCode();
        if (hashCode == -1761149810) {
            if (str2.equals("wechat error")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 436492011) {
            if (hashCode == 2113577665 && str2.equals("bind device error")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str2.equals("server error")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            nrh.b(this, R.string.IDS_wx_device_platform_error_tips);
            return;
        }
        if (c == 1) {
            nrh.b(this, R.string._2130842544_res_0x7f0213b0);
        } else if (c == 2) {
            nrh.b(this, R.string.IDS_wx_bind_device_error_tips);
        } else {
            nrh.b(this, R.string._2130840733_res_0x7f020c9d);
        }
    }

    public /* synthetic */ void b(String str) {
        new SdkManager().e(this, str, new SdkManager.launchWechatCallBack() { // from class: sgq
            @Override // com.huawei.ui.thirdpartservice.syncdata.wechatdevice.SdkManager.launchWechatCallBack
            public final void onLaunchFailed() {
                WeChatDeviceAuthorizeActivity.this.e();
            }
        });
        Message obtain = Message.obtain();
        obtain.obj = Integer.valueOf(R.string.IDS_wx_device_platform_error_tips);
        obtain.what = -1;
        this.n.sendMessageDelayed(obtain, PreConnectManager.CONNECT_INTERNAL);
    }

    private Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("deviceCode", this.i);
        hashMap.put("authorizationCode", str);
        hashMap.put("certModel", this.f.getCertModel());
        hashMap.put("deviceName", this.f.getDeviceName());
        hashMap.put("deviceModelName", this.f.getDeviceModel());
        hashMap.put("deviceUdid", this.f.getDeviceUdid());
        return hashMap;
    }

    private boolean b() {
        if (CommonUtil.aa(this)) {
            return true;
        }
        nrh.b(this, R.string._2130841393_res_0x7f020f31);
        return false;
    }

    private boolean c() {
        if (SdkManager.e()) {
            return true;
        }
        nrh.b(this, R.string._2130839536_res_0x7f0207f0);
        return false;
    }

    private void j() {
        if (this.h != null) {
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_oauth_revoke, (ViewGroup) null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.oauth_health_dialog_text);
        if (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView.getLayoutParams();
            layoutParams.topMargin = (int) TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics());
            healthTextView.setLayoutParams(layoutParams);
        }
        healthTextView.setText(R.string.IDS_wx_device_cancel_auth_dlg);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.c(false);
        builder.czg_(inflate);
        builder.czf_(getString(R.string._2130837648_res_0x7f020090).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sgt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeChatDeviceAuthorizeActivity.this.dXE_(view);
            }
        });
        builder.czd_(getString(R.string._2130845098_res_0x7f021daa).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sgs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeChatDeviceAuthorizeActivity.this.dXF_(view);
            }
        });
        CustomViewDialog e = builder.e();
        this.h = e;
        e.setCanceledOnTouchOutside(false);
        this.h.show();
    }

    public /* synthetic */ void dXE_(View view) {
        sid.a(this, this.m, new SdkManager.launchWechatCallBack() { // from class: sgz
            @Override // com.huawei.ui.thirdpartservice.syncdata.wechatdevice.SdkManager.launchWechatCallBack
            public final void onLaunchFailed() {
                WeChatDeviceAuthorizeActivity.this.a();
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dXF_(View view) {
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CustomViewDialog customViewDialog = this.h;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
            this.h = null;
        }
    }

    private void g() {
        if (this.o == null && !isFinishing()) {
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.o = a2;
            a2.e(getString(R.string._2130841528_res_0x7f020fb8));
            this.o.setCancelable(false);
            this.o.a();
            this.o.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CommonDialog21 commonDialog21;
        if (isFinishing() || (commonDialog21 = this.o) == null) {
            return;
        }
        commonDialog21.dismiss();
        this.o = null;
    }

    /* loaded from: classes8.dex */
    static class a extends BaseHandler<WeChatDeviceAuthorizeActivity> {
        private a(WeChatDeviceAuthorizeActivity weChatDeviceAuthorizeActivity) {
            super(weChatDeviceAuthorizeActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dXH_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeChatDeviceAuthorizeActivity weChatDeviceAuthorizeActivity, Message message) {
            weChatDeviceAuthorizeActivity.dXC_(message);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
