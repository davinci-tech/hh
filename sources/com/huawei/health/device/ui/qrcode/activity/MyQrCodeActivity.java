package com.huawei.health.device.ui.qrcode.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.ui.qrcode.activity.MyQrCodeActivity;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetVerifyCodeForMainUserRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.cpa;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.ezd;
import defpackage.ezl;
import defpackage.fdu;
import defpackage.ixx;
import defpackage.jah;
import defpackage.jbs;
import defpackage.jdx;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class MyQrCodeActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f2263a;
    private Context c;
    private HealthTextView d;
    private d e;
    private HealthTextView f;
    private HealthTextView g;
    private LinearLayout h;
    private LinearLayout j;
    private Bitmap k;
    private HealthProgressBar l;
    private HealthTextView m;
    private View n;
    private ImageView o;
    private View p;
    private HealthButton q;
    private LinearLayout r;
    private String s;
    private ImageView t;
    private HealthTextView u;
    private LinearLayout w;
    private CustomTitleBar x;
    private String b = "";
    private String i = "";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_qrcode);
        this.c = this;
        this.e = new d(this);
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getStringExtra("deviceId");
            this.i = intent.getStringExtra("productId");
        }
        a();
        e();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("MyQrCodeActivity", "MyQrCodeActivity on resume");
        try {
            super.onResume();
        } catch (Exception unused) {
            LogUtil.b("MyQrCodeActivity", "MyQrCodeActivity exception");
        }
    }

    static class d extends StaticHandler<MyQrCodeActivity> {
        d(MyQrCodeActivity myQrCodeActivity) {
            super(myQrCodeActivity);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        /* renamed from: JP_, reason: merged with bridge method [inline-methods] */
        public void handleMessage(MyQrCodeActivity myQrCodeActivity, Message message) {
            if (myQrCodeActivity == null || myQrCodeActivity.isFinishing() || myQrCodeActivity.isDestroyed()) {
                LogUtil.h("MyQrCodeActivity", "MyQrCodeActivity is Destroyed.");
                return;
            }
            LogUtil.a("MyQrCodeActivity", "MyHandler what:", Integer.valueOf(message.what));
            switch (message.what) {
                case 2000001:
                    if (myQrCodeActivity.e.hasMessages(3000004)) {
                        myQrCodeActivity.e.removeMessages(3000004);
                    }
                    myQrCodeActivity.e.sendEmptyMessageDelayed(3000004, 1800000L);
                    myQrCodeActivity.b();
                    break;
                case 2000002:
                    if (myQrCodeActivity.e.hasMessages(3000004)) {
                        myQrCodeActivity.e.removeMessages(3000004);
                    }
                    myQrCodeActivity.c();
                    break;
                case 2000003:
                    nrh.b(myQrCodeActivity.c, R.string._2130843056_res_0x7f0215b0);
                    myQrCodeActivity.a(false);
                    myQrCodeActivity.d(false);
                    break;
                case 3000004:
                    if (myQrCodeActivity.e.hasMessages(3000004)) {
                        myQrCodeActivity.e.removeMessages(3000004);
                    }
                    myQrCodeActivity.d();
                    myQrCodeActivity.e.sendEmptyMessageDelayed(3000004, 1800000L);
                    break;
                case 3000005:
                    nrh.d(myQrCodeActivity.c, String.format(Locale.ENGLISH, myQrCodeActivity.c.getString(R.string.IDS_device_wifi_qrcode_refresh_too_much), 30));
                    myQrCodeActivity.a(false);
                    myQrCodeActivity.d(false);
                    break;
                default:
                    LogUtil.h("MyQrCodeActivity", "MyHandler what is other");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        if (CommonUtil.aa(this.c.getApplicationContext())) {
            LogUtil.a("MyQrCodeActivity", "netWork is ok");
            this.e.sendEmptyMessage(2000001);
            return true;
        }
        LogUtil.h("MyQrCodeActivity", "netWork is False");
        this.e.sendEmptyMessage(2000002);
        return false;
    }

    private void e() {
        d();
        this.e.sendEmptyMessageDelayed(3000004, 1800000L);
        this.x.setRightButtonOnClickListener(new View.OnClickListener() { // from class: cok
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyQrCodeActivity.this.JM_(view);
            }
        });
        String i = ctq.i(this.b);
        if (TextUtils.isEmpty(i)) {
            return;
        }
        if (cpa.ae(i)) {
            this.h.setVisibility(8);
            this.g.setVisibility(8);
        }
        if ("e835d102-af95-48a6-ae13-2983bc06f5c0".equals(i)) {
            this.h.setVisibility(0);
            this.g.setVisibility(0);
        }
    }

    public /* synthetic */ void JM_(View view) {
        if (this.p.getVisibility() == 0 && this.l.getVisibility() != 0) {
            i();
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", "1");
            ixx.d().d(this.c, AnalyticsValue.HEALTH_PLUGIN_DEVICE_WIFI_DEVICE_MULTIACCOUNT_SHARE_2060032.value(), hashMap, 0);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        this.p = findViewById(R.id.show_my_qr);
        this.f2263a = (HealthTextView) findViewById(R.id.textView_default);
        this.l = (HealthProgressBar) findViewById(R.id.show_my_qr_progressbar);
        this.n = findViewById(R.id.show_my_qr_iv_layout);
        this.o = (ImageView) findViewById(R.id.show_my_qr_iv);
        this.u = (HealthTextView) findViewById(R.id.show_my_qr_how_to_scan_text);
        this.h = (LinearLayout) findViewById(R.id.qrcode_scanning_find_qrcode_ios_phone_layout);
        this.x = (CustomTitleBar) findViewById(R.id.my_qrcode_title_layout);
        this.w = (LinearLayout) findViewById(R.id.device_share_qr_preview);
        this.t = (ImageView) findViewById(R.id.device_share_qr_img);
        this.m = (HealthTextView) findViewById(R.id.device_share_qr_tips);
        this.x.setRightButtonVisibility(0);
        this.x.setRightButtonDrawable(this.c.getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        this.x.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: coj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyQrCodeActivity.this.JN_(view);
            }
        });
        this.r = (LinearLayout) findViewById(R.id.qrcode_scanning_qrcode_tips_layout);
        this.o.setOnClickListener(this);
        HealthButton healthButton = (HealthButton) findViewById(R.id.hw_refresh_qr_code_btn);
        this.q = healthButton;
        healthButton.setOnClickListener(this);
        this.d = (HealthTextView) findViewById(R.id.qrcode_scanning_find_qrcode_android_tips);
        this.g = (HealthTextView) findViewById(R.id.qrcode_scanning_find_qrcode_ios_tips);
        this.f = (HealthTextView) findViewById(R.id.qrcode_scanning_find_qrcode_harmony_tips);
        this.j = (LinearLayout) findViewById(R.id.qrcode_scanning_find_qrcode_harmony_phone_layout);
        String string = this.c.getString(R.string.IDS_device_qrcode_show_tips);
        this.d.setText(string);
        this.g.setText(string);
        a(string);
    }

    public /* synthetic */ void JN_(View view) {
        LogUtil.a("MyQrCodeActivity", "mTitleBar leftButton onClick");
        setResult(10);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(String str) {
        String e = jah.c().e("scale_share_harmony_tips");
        LogUtil.c("MyQrCodeActivity", "setHarmonyTips switchHarmony = ", e);
        if ("on".equalsIgnoreCase(e) && !CommonUtil.bf()) {
            this.j.setVisibility(0);
            this.f.setVisibility(0);
            this.f.setText(str);
        } else {
            this.j.setVisibility(8);
            this.f.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.q) {
            d();
        } else if (view == this.o) {
            d();
        } else {
            LogUtil.a("MyQrCodeActivity", "View is others");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(final boolean z) {
        if (this.o == null) {
            LogUtil.h("MyQrCodeActivity", "mQrCodeImage is null");
            return;
        }
        if (this.f2263a == null) {
            LogUtil.h("MyQrCodeActivity", "mDefaultText is null");
        } else if (this.x == null) {
            LogUtil.h("MyQrCodeActivity", "mTitleBar is null");
        } else {
            runOnUiThread(new Runnable() { // from class: cor
                @Override // java.lang.Runnable
                public final void run() {
                    MyQrCodeActivity.this.c(z);
                }
            });
        }
    }

    public /* synthetic */ void c(boolean z) {
        if (z) {
            this.u.setVisibility(0);
            this.f2263a.setVisibility(8);
            this.o.setPadding(0, 0, 0, 0);
            this.x.setRightButtonVisibility(0);
            this.r.setVisibility(0);
            return;
        }
        this.u.setVisibility(4);
        this.r.setVisibility(4);
        this.o.setImageResource(R.drawable._2131430675_res_0x7f0b0d13);
        this.o.setPadding(nsn.c(this.c, 16.0f), nsn.c(this.c, 16.0f), nsn.c(this.c, 16.0f), nsn.c(this.c, 16.0f));
        this.f2263a.setVisibility(0);
        this.x.setRightButtonVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final boolean z) {
        runOnUiThread(new Runnable() { // from class: coi
            @Override // java.lang.Runnable
            public final void run() {
                MyQrCodeActivity.this.e(z);
            }
        });
    }

    public /* synthetic */ void e(boolean z) {
        if (z) {
            this.o.setVisibility(8);
            this.n.setBackground(null);
            this.l.setVisibility(0);
        } else {
            this.o.setVisibility(0);
            this.n.setBackgroundResource(R.color._2131298109_res_0x7f09073d);
            this.l.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        JH_(this.o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        nrh.b(this.c, R.string._2130843057_res_0x7f0215b1);
        a(false);
    }

    private void JH_(final ImageView imageView) {
        a(true);
        d(true);
        jdx.b(new Runnable() { // from class: col
            @Override // java.lang.Runnable
            public final void run() {
                MyQrCodeActivity.this.JO_(imageView);
            }
        });
    }

    public /* synthetic */ void JO_(ImageView imageView) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        LogUtil.c("MyQrCodeActivity", "userId:", accountInfo);
        if (accountInfo == null) {
            LogUtil.h("MyQrCodeActivity", "get userid or accountName is null");
            this.e.sendEmptyMessage(2000003);
            return;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            LogUtil.h("MyQrCodeActivity", "get userInfo is null");
            this.e.sendEmptyMessage(2000003);
            return;
        }
        String name = userInfo.getName();
        if (TextUtils.isEmpty(name)) {
            LogUtil.h("MyQrCodeActivity", "get userInfo.getName() is null");
            name = new UpApi(this.c).getLegalAccountName();
        }
        if (TextUtils.isEmpty(name)) {
            LogUtil.h("MyQrCodeActivity", "get getLegalAccountName is null");
            name = LoginInit.getInstance(this.c).getAccountInfo(1002);
        }
        if (TextUtils.isEmpty(name)) {
            LogUtil.h("MyQrCodeActivity", "get LoginInit.getUserName is null");
            return;
        }
        String str = this.b;
        if (str == null) {
            LogUtil.h("MyQrCodeActivity", "get mDeviceId is null");
            this.e.sendEmptyMessage(2000003);
        } else {
            JG_(accountInfo, name, str, imageView);
        }
    }

    private void JG_(final String str, final String str2, final String str3, final ImageView imageView) {
        jbs.a(this.c).c(new ICloudOperationResult() { // from class: cot
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str4, boolean z) {
                MyQrCodeActivity.this.JL_(str, str2, str3, imageView, (WifiDeviceGetVerifyCodeForMainUserRsp) obj, str4, z);
            }
        });
    }

    public /* synthetic */ void JL_(String str, String str2, String str3, final ImageView imageView, WifiDeviceGetVerifyCodeForMainUserRsp wifiDeviceGetVerifyCodeForMainUserRsp, String str4, boolean z) {
        int i;
        String str5;
        if (!z) {
            if (wifiDeviceGetVerifyCodeForMainUserRsp != null) {
                i = wifiDeviceGetVerifyCodeForMainUserRsp.getResultCode().intValue();
                str5 = wifiDeviceGetVerifyCodeForMainUserRsp.getResultDesc();
            } else {
                i = Constants.CODE_UNKNOWN_ERROR;
                str5 = "unknown error";
            }
            if (i == 112000080) {
                LogUtil.h("MyQrCodeActivity", "refresh too much error.");
                this.e.sendEmptyMessage(3000005);
            } else {
                this.e.sendEmptyMessage(2000003);
            }
            LogUtil.a("MyQrCodeActivity", "getVerifyCode error: ", Integer.valueOf(i), ", resultDesc: ", str5);
            return;
        }
        if (wifiDeviceGetVerifyCodeForMainUserRsp == null) {
            LogUtil.h("MyQrCodeActivity", "rsp is null.");
            this.e.sendEmptyMessage(2000003);
            return;
        }
        String verifyCode = wifiDeviceGetVerifyCodeForMainUserRsp.getVerifyCode();
        if (TextUtils.isEmpty(verifyCode)) {
            this.e.sendEmptyMessage(2000003);
            return;
        }
        final Bitmap JE_ = JE_(str, str2, str3, verifyCode);
        if (JE_ == null) {
            LogUtil.h("MyQrCodeActivity", "create QRcode error");
            this.e.sendEmptyMessage(2000003);
        } else {
            this.k = JE_;
            runOnUiThread(new Runnable() { // from class: coo
                @Override // java.lang.Runnable
                public final void run() {
                    MyQrCodeActivity.this.JK_(imageView, JE_);
                }
            });
        }
    }

    public /* synthetic */ void JK_(ImageView imageView, Bitmap bitmap) {
        LogUtil.a("MyQrCodeActivity", "setImageBitmap qrCodeBmp");
        d(false);
        imageView.setImageBitmap(bitmap);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        Bitmap bitmap = this.k;
        if (bitmap != null) {
            bitmap.recycle();
        }
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("MyQrCodeActivity", "onBackPressed");
        setResult(10);
        super.onBackPressed();
    }

    private Bitmap JE_(String str, String str2, String str3, String str4) {
        String str5;
        HashMap hashMap = new HashMap(16);
        hashMap.put("mainHuid", str);
        hashMap.put("nickName", str2);
        hashMap.put("devId", str3);
        hashMap.put("verifyCode", str4);
        ctk c = ctq.c(str3);
        if (c != null) {
            hashMap.put("proId", c.b().f());
            if (cpa.w(this.i)) {
                hashMap.put("snNumber", c.b().m());
                hashMap.put("UUID", c.getProductId());
                hashMap.put("name", c.getDeviceName());
                hashMap.put("identify", c.b().j());
            }
        }
        try {
            String jSONObject = new JSONObject(hashMap).toString();
            LogUtil.c("MyQrCodeActivity", "content:", jSONObject);
            String encodeToString = Base64.encodeToString(jSONObject.getBytes("utf-8"), 0);
            if (CompileParameterUtil.a("SUPPORT_WIFI_QR_URL_TEST", false)) {
                str5 = CompileParameterUtil.c("WIFI_QRCODE_URL_PRE_TEST", "");
            } else {
                str5 = GRSManager.a(BaseApplication.getContext()).getUrl("domainUrlCloudHuawei") + "/1Lfn1eswP6?a=";
            }
            String str6 = str5 + "s&c=" + encodeToString + "&name=" + str2;
            this.s = str6;
            LogUtil.c("MyQrCodeActivity", "qrcodeUrl:", str6);
            ezd.e.d dVar = new ezd.e.d();
            dVar.a(nsn.c(this, 220.0f), nsn.c(this, 220.0f));
            try {
                return ezd.aua_(this.s, dVar.a());
            } catch (ezl e) {
                LogUtil.b("MyQrCodeActivity", "setGroupQrCode() generateMap meet exception ", e.getMessage());
                return null;
            }
        } catch (UnsupportedEncodingException e2) {
            LogUtil.b("MyQrCodeActivity", "encode QrCode UnsupportedEncodingException:", e2.getMessage());
            return null;
        }
    }

    private void i() {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.c.getResources(), R.drawable._2131429779_res_0x7f0b0993);
        Canvas canvas = new Canvas(Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), Bitmap.Config.ARGB_8888));
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffffff"));
        canvas.drawRect(0.0f, 0.0f, r1.getWidth(), r1.getHeight(), paint);
        canvas.drawBitmap(decodeResource, 0.0f, 0.0f, paint);
        LogUtil.c("MyQrCodeActivity", "shareMyQrCode shareUrl is::", this.s);
        if (TextUtils.isEmpty(this.s)) {
            LogUtil.h("MyQrCodeActivity", "shareMyQrCode shareUrl is empty.");
        } else {
            JI_(this.k, false);
        }
    }

    private void JI_(Bitmap bitmap, final boolean z) {
        this.m.setText(String.format(Locale.ENGLISH, getString(R.string.IDS_device_share_qr_content), 1, 2, 3));
        if (LanguageUtil.i(this.c) || LanguageUtil.ar(this.c) || LanguageUtil.bj(this.c)) {
            this.m.setAutoTextSize(14.0f);
            this.m.setLineSpacing(0.0f, 1.0f);
        }
        this.w.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.huawei.health.device.ui.qrcode.activity.MyQrCodeActivity.5
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                MyQrCodeActivity.this.w.removeOnLayoutChangeListener(this);
                MyQrCodeActivity myQrCodeActivity = MyQrCodeActivity.this;
                myQrCodeActivity.JJ_(myQrCodeActivity.JF_(myQrCodeActivity.w), z);
            }
        });
        this.t.setImageBitmap(bitmap);
        this.w.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void JJ_(Bitmap bitmap, boolean z) {
        fdu fduVar = new fdu(1);
        fduVar.c(this.c.getResources().getString(R.string.IDS_device_wifi_share_bodyfat_scale));
        fduVar.awp_(bitmap);
        fduVar.c(z);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, this.c);
        this.w.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap JF_(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
