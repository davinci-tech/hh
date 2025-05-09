package com.huawei.health.device.ui.measure.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.activity.WifiDeviceAddUserActivity;
import com.huawei.health.device.ui.qrcode.activity.MyQrCodeActivity;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetVerifyCodeForMainUserRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.cpa;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.fdu;
import defpackage.jbs;
import defpackage.jdm;
import defpackage.jdx;
import defpackage.mxv;
import defpackage.nrh;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class WifiDeviceAddUserActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f2238a;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private CustomTitleBar g;
    private ImageView h;
    private ImageView i;
    private String j;
    private ImageView l;
    private String b = "";
    private String f = "";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_adduser);
        this.f2238a = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getStringExtra("deviceId");
            this.f = intent.getStringExtra("productId");
        }
        f();
        d();
    }

    private boolean d() {
        Context context = this.f2238a;
        if (context != null && CommonUtil.aa(context)) {
            LogUtil.a("WifiDeviceAddUserActivity", "netWork is ok");
            return true;
        }
        nrh.b(this.f2238a, R.string._2130841884_res_0x7f02111c);
        LogUtil.h("WifiDeviceAddUserActivity", "netWork is error");
        return false;
    }

    private void f() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.my_qrcode_title_layout);
        this.g = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: clw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDeviceAddUserActivity.this.Ic_(view);
            }
        });
        this.c = (LinearLayout) findViewById(R.id.hw_device_share_qr);
        this.e = (LinearLayout) findViewById(R.id.hw_device_share_account);
        this.d = (LinearLayout) findViewById(R.id.hw_device_share_wechat);
        this.i = (ImageView) findViewById(R.id.hw_device_adduser_qrtint);
        this.h = (ImageView) findViewById(R.id.hw_device_adduser_hwtint);
        this.l = (ImageView) findViewById(R.id.hw_device_adduser_wechattint);
        this.c.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.d.setOnClickListener(this);
        if (!Utils.o() && (cpa.w(this.f) || cpa.v(this.f))) {
            this.e.setVisibility(0);
        }
        Ib_(this.i);
        Ib_(this.h);
        Ib_(this.l);
        c();
    }

    public /* synthetic */ void Ic_(View view) {
        setResult(10);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!CommonUtil.aa(this.f2238a)) {
            nrh.b(this.f2238a, R.string._2130841884_res_0x7f02111c);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.c) {
            i();
        } else if (view == this.e) {
            boolean a2 = a();
            LogUtil.a("WifiDeviceAddUserActivity", "click mAddUserAccount and check isInstallHMSCore is ", Boolean.valueOf(a2));
            if (!CommonUtil.ao() && !a2) {
                mxv.d(this.f2238a);
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            j();
        } else if (view == this.d) {
            b();
        } else {
            LogUtil.h("WifiDeviceAddUserActivity", "click error");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void Ib_(ImageView imageView) {
        if (LanguageUtil.bc(this)) {
            imageView.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            imageView.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
    }

    private void b() {
        Bitmap decodeResource = BitmapFactory.decodeResource(this.f2238a.getResources(), R.drawable._2131429779_res_0x7f0b0993);
        Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color._2131298109_res_0x7f09073d));
        canvas.drawRect(0.0f, 0.0f, createBitmap.getWidth(), createBitmap.getHeight(), paint);
        canvas.drawBitmap(decodeResource, 0.0f, 0.0f, paint);
        LogUtil.c("WifiDeviceAddUserActivity", "shareMyQrCode shareUrl is::", this.j);
        if (TextUtils.isEmpty(this.j)) {
            LogUtil.h("WifiDeviceAddUserActivity", "shareMyQrCode shareUrl is empty.");
        } else {
            Ia_(createBitmap, this.j, false);
        }
    }

    private void Ia_(Bitmap bitmap, String str, boolean z) {
        fdu fduVar = new fdu(2);
        fduVar.c(this.f2238a.getResources().getString(R.string.IDS_device_wifi_share_bodyfat_scale));
        fduVar.a("");
        fduVar.awp_(bitmap);
        fduVar.f(str);
        fduVar.c(z);
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(fduVar, this.f2238a);
    }

    private void i() {
        Intent intent = new Intent(this.f2238a, (Class<?>) MyQrCodeActivity.class);
        intent.putExtra("deviceId", this.b);
        intent.putExtra("productId", this.f);
        startActivityForResult(intent, 100);
    }

    private void j() {
        Intent intent = new Intent(this.f2238a, (Class<?>) WifiDeviceAccountAddUserActivity.class);
        intent.putExtra("deviceId", this.b);
        intent.putExtra("productId", this.f);
        startActivity(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("WifiDeviceAddUserActivity", "onBackPressed");
        setResult(10);
        super.onBackPressed();
    }

    private void c() {
        jdx.b(new Runnable() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceAddUserActivity.1
            @Override // java.lang.Runnable
            public void run() {
                String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
                if (accountInfo == null) {
                    LogUtil.h("WifiDeviceAddUserActivity", "get userid or accountName is null");
                    return;
                }
                UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
                if (userInfo == null) {
                    LogUtil.h("WifiDeviceAddUserActivity", "get userInfo is null");
                    return;
                }
                String name = userInfo.getName();
                if (TextUtils.isEmpty(name)) {
                    LogUtil.h("WifiDeviceAddUserActivity", "get userInfo.getName() is null");
                    name = new UpApi(WifiDeviceAddUserActivity.this.f2238a).getLegalAccountName();
                }
                if (TextUtils.isEmpty(name)) {
                    LogUtil.h("WifiDeviceAddUserActivity", "get UpApi.getLegalAccountName is null");
                    name = LoginInit.getInstance(WifiDeviceAddUserActivity.this.f2238a).getAccountInfo(1002);
                }
                if (!TextUtils.isEmpty(name)) {
                    if (TextUtils.isEmpty(WifiDeviceAddUserActivity.this.b)) {
                        LogUtil.h("WifiDeviceAddUserActivity", "get mDeviceId is null");
                        return;
                    } else {
                        WifiDeviceAddUserActivity wifiDeviceAddUserActivity = WifiDeviceAddUserActivity.this;
                        wifiDeviceAddUserActivity.e(accountInfo, name, wifiDeviceAddUserActivity.b);
                        return;
                    }
                }
                LogUtil.h("WifiDeviceAddUserActivity", "get LoginInit.getUserName is null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final String str, final String str2, final String str3) {
        jbs.a(this.f2238a).c(new ICloudOperationResult<WifiDeviceGetVerifyCodeForMainUserRsp>() { // from class: com.huawei.health.device.ui.measure.activity.WifiDeviceAddUserActivity.3
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(WifiDeviceGetVerifyCodeForMainUserRsp wifiDeviceGetVerifyCodeForMainUserRsp, String str4, boolean z) {
                int i;
                String str5;
                if (z) {
                    LogUtil.a("WifiDeviceAddUserActivity", "getVerifyCode success.");
                    if (wifiDeviceGetVerifyCodeForMainUserRsp == null) {
                        LogUtil.a("WifiDeviceAddUserActivity", "verifyCodeForMainUserRsp is null.");
                        return;
                    }
                    String verifyCode = wifiDeviceGetVerifyCodeForMainUserRsp.getVerifyCode();
                    if (!TextUtils.isEmpty(verifyCode)) {
                        WifiDeviceAddUserActivity.this.b(str, str2, str3, verifyCode);
                        return;
                    } else {
                        LogUtil.h("WifiDeviceAddUserActivity", "verifyCode is null.");
                        return;
                    }
                }
                if (wifiDeviceGetVerifyCodeForMainUserRsp != null) {
                    i = wifiDeviceGetVerifyCodeForMainUserRsp.getResultCode().intValue();
                    str5 = wifiDeviceGetVerifyCodeForMainUserRsp.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str5 = "unknown error";
                }
                LogUtil.a("WifiDeviceAddUserActivity", "getVerifyCode error: ", Integer.valueOf(i), ", resultDesc: ", str5);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str, String str2, String str3, String str4) {
        String str5;
        HashMap hashMap = new HashMap(16);
        hashMap.put("mainHuid", str);
        hashMap.put("nickName", str2);
        hashMap.put("devId", str3);
        hashMap.put("verifyCode", str4);
        ctk c = ctq.c(str3);
        if (c != null) {
            hashMap.put("proId", c.b().f());
        }
        try {
            String jSONObject = new JSONObject(hashMap).toString();
            LogUtil.c("WifiDeviceAddUserActivity", "content:", jSONObject);
            String replaceAll = Base64.encodeToString(jSONObject.getBytes("utf-8"), 0).replaceAll("\r|\n", "");
            if (CompileParameterUtil.a("SUPPORT_WIFI_QR_URL_TEST", false)) {
                str5 = CompileParameterUtil.c("WIFI_QRCODE_URL_PRE_TEST", "");
            } else {
                str5 = GRSManager.a(BaseApplication.getContext()).getUrl("domainUrlCloudHuawei") + "/1Lfn1eswP6?a=";
            }
            String str6 = str5 + "s&c=" + replaceAll + "&name=" + str2;
            this.j = str6;
            LogUtil.c("WifiDeviceAddUserActivity", "qrcodeUrl:", str6);
            return this.j;
        } catch (UnsupportedEncodingException e) {
            LogUtil.h("WifiDeviceAddUserActivity", "encode QrCode UnsupportedEncodingException:", e.getMessage());
            return "";
        }
    }

    private boolean a() {
        LogUtil.a("WifiDeviceAddUserActivity", "Enter checkIsInstallHuaweiAccount");
        if (CommonUtil.bu()) {
            LogUtil.a("WifiDeviceAddUserActivity", "StoreDemo no check hms");
            return true;
        }
        PackageManager packageManager = this.f2238a.getPackageManager();
        try {
            String hMSPackageName = HMSPackageManager.getInstance(this.f2238a).getHMSPackageName();
            LogUtil.a("WifiDeviceAddUserActivity", "checkIsInstallHuaweiAccount packageName: ", hMSPackageName);
            boolean z = packageManager.getApplicationInfo(hMSPackageName, 128) != null;
            LogUtil.a("WifiDeviceAddUserActivity", "checkIsInstallHuaweiAccount: ", Boolean.valueOf(z));
            return z;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("WifiDeviceAddUserActivity", "checkIsInstallHuaweiAccount NameNotFoundException false");
            return e();
        }
    }

    private boolean e() {
        LogUtil.a("WifiDeviceAddUserActivity", "Enter checkIsInstallAGLiteApp");
        if (CommonUtil.bu()) {
            LogUtil.a("WifiDeviceAddUserActivity", "checkIsInstallAGLiteApp no check hms");
            return true;
        }
        String hMSPackageNameForMultiService = HMSPackageManager.getInstance(this.f2238a).getHMSPackageNameForMultiService();
        LogUtil.a("WifiDeviceAddUserActivity", "checkIsInstallAGLiteApp packageName: ", hMSPackageNameForMultiService);
        boolean b = jdm.b(this.f2238a, hMSPackageNameForMultiService);
        LogUtil.a("WifiDeviceAddUserActivity", "checkIsInstallAGLiteApp result: ", Boolean.valueOf(b));
        return b;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
