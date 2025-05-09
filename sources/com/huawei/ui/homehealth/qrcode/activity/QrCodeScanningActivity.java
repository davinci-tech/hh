package com.huawei.ui.homehealth.qrcode.activity;

import android.R;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.BuildConfig;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.qrcode.CodeValueUtil;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.embedded.u3;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.Product;
import com.huawei.trade.datatype.ProductInfo;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.thirdpartservice.activity.healthkit.HealthKitActivity;
import defpackage.bzs;
import defpackage.ceo;
import defpackage.cld;
import defpackage.dhy;
import defpackage.dks;
import defpackage.eaa;
import defpackage.ezd;
import defpackage.jdx;
import defpackage.nrh;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.nue;
import defpackage.obb;
import defpackage.onv;
import defpackage.ooj;
import defpackage.opa;
import defpackage.ope;
import defpackage.opf;
import defpackage.shx;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.Sha256;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes6.dex */
public class QrCodeScanningActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Bundle f9493a;
    private CountDownLatch b;
    private Context c;
    public ActivityResultLauncher<IntentSenderRequest> d;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            finish();
            return;
        }
        this.c = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.f9493a = intent.getExtras();
        }
        e();
        Window window = getWindow();
        window.setFormat(-2);
        window.setBackgroundDrawableResource(R.color.transparent);
        c();
        j();
    }

    private void e() {
        this.d = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                QrCodeScanningActivity.this.e((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void e(ActivityResult activityResult) {
        QrCodeBaseHandler a2 = ope.e().a();
        if (!(a2 instanceof onv)) {
            LogUtil.h("QrCodeScanningActivity", "qrCoderHandler not DeviceQrCodeHandler");
            if (isFinishing()) {
                return;
            }
            finish();
            return;
        }
        onv onvVar = (onv) a2;
        onvVar.a(String.valueOf(activityResult.getResultCode()));
        if (activityResult.getResultCode() == -1) {
            SharedPreferenceManager.c("SENSITIVE_PERMISSION_STATUS", "SENSITIVE_PERMISSION_STATUS", SensitivePermissionStatus.RESTART.getValue());
            onvVar.a();
        } else {
            b(onvVar);
        }
    }

    private void b(final onv onvVar) {
        obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == -1) {
                    onvVar.ddL_(QrCodeScanningActivity.this);
                } else {
                    onvVar.a();
                }
            }
        }, this.c);
    }

    private void c() {
        if (!b()) {
            LogUtil.a("QrCodeScanningActivity", "not support initCodeValuePlatform");
            return;
        }
        CodeValueUtil.atY_(getApplication(), String.valueOf(BuildConfig.HMS_APPLICATION_ID));
        if (!CommonUtil.bv()) {
            CodeValueUtil.c();
        }
        this.b = new CountDownLatch(1);
        GRSManager.a(this).e("domainQrcodeMatchRules", new GrsQueryCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                CodeValueUtil.d(str, new CodeValueUtil.RuleResultCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.2.3
                    @Override // com.huawei.health.qrcode.CodeValueUtil.RuleResultCallback
                    public void onResponse(String str2, CodeValueUtil.a aVar) {
                        if (aVar != null && aVar.c() > 0) {
                            LogUtil.a("QrCodeScanningActivity", "initCodeValuePlatform, onUpdate rules size: ", Integer.valueOf(aVar.c()), " with cachePath:", str2);
                        } else {
                            ReleaseLogUtil.d("R_QrCode_QrCodeScanningActivity", "initCodeValuePlatform rules update failed");
                        }
                        QrCodeScanningActivity.this.b.countDown();
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("QrCodeScanningActivity", "initCodeValuePlatform, get grs is fail");
                QrCodeScanningActivity.this.b.countDown();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("R_QrCode_QrCodeScanningActivity", "onActivityResult requestCode: ", Integer.valueOf(i), " resultCode: ", Integer.valueOf(i2));
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            QrCodeBaseHandler a2 = ope.e().a();
            if (!(a2 instanceof onv)) {
                LogUtil.h("QrCodeScanningActivity", "qrCoderHandler not DeviceQrCodeHandler");
                if (isFinishing()) {
                    return;
                }
                finish();
                return;
            }
            onv onvVar = (onv) a2;
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (i2 == 0 || defaultAdapter == null || !defaultAdapter.isEnabled()) {
                onvVar.b();
                LogUtil.a("QrCodeScanningActivity", "stopScanAndCloseTimer");
                if (isFinishing()) {
                    return;
                }
                finish();
                return;
            }
            onvVar.c();
        }
        if (i != 3 && i2 == 0) {
            LogUtil.a("R_QrCode_QrCodeScanningActivity", "requestCode is not REQUEST_GPS_LOCATION, resultCode is zero, finish activity");
            finish();
            return;
        }
        if (i == 3) {
            j();
            return;
        }
        if (i == 1) {
            if (i2 == 2) {
                nue.cNU_(intent, this, nue.e(i2, true, intent.getIntExtra("product_type", -1), true));
                return;
            } else {
                finish();
                return;
            }
        }
        if (i2 == -1 && i == 4) {
            int aub_ = ezd.aub_(intent);
            if (aub_ == 0) {
                x(ezd.auc_(intent).trim());
                return;
            } else if (aub_ == 2) {
                nsn.cKS_(this, 4);
                return;
            } else {
                finish();
                return;
            }
        }
        ddt_(i, i2, intent);
    }

    private void ddt_(int i, int i2, Intent intent) {
        int aub_ = ezd.aub_(intent);
        if (i == 5) {
            if (i2 == -1 && aub_ == 0) {
                ope.deJ_(this.f9493a, CommonConstant.AckQrLoginVerifyValue.VERIFY_RESULT_OK, "");
            }
            finish();
        }
    }

    private void x(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("R_QrCode_QrCodeScanningActivity", "parseQrCodeResult content is null");
            d();
            return;
        }
        LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult content: ", str);
        if (j(str)) {
            LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult SnCode is match");
            o(str);
            return;
        }
        if (i(str)) {
            LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult NFCCode is match");
            u(str);
            return;
        }
        if (a(str)) {
            if (!Utils.o()) {
                LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult LargeScreenQrCode is match");
                ope.deJ_(this.f9493a, "login", "");
                a(this.c, str);
                return;
            } else {
                nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(com.huawei.health.R.string._2130844514_res_0x7f021b62));
                finish();
                return;
            }
        }
        if (c(str)) {
            s(str);
            return;
        }
        if (f(str)) {
            LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult ThirdDeviceCode is match");
            d(str);
        } else if (!CommonUtil.cc() ? !(!str.contains("sns.hicloud") || !str.contains("/g/")) : !(!str.contains("lfsnstest01.hwcloudtest") || !str.contains("/g/"))) {
            LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult go to group");
            q(str);
        } else {
            m(str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void l(String str) {
        char c;
        String str2;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("QrCodeScanningActivity", "jumpThirdH5Page content is null");
            return;
        }
        if (opa.e(str)) {
            finish();
            return;
        }
        LogUtil.a("QrCodeScanningActivity", "jumpThirdH5Page: not common h5pro app url, continue.");
        String[] split = str.split(str.contains("cloudtest") ? "#//" : Constants.H5PRO_PAGE_PREFIX);
        int length = split.length;
        if (length > 1) {
            String str3 = split[length - 1];
            str3.hashCode();
            switch (str3.hashCode()) {
                case -1757536492:
                    if (str3.equals("vipPrivilege")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1571036505:
                    if (str3.equals("exclusiveGuard")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1421187384:
                    if (str3.equals("exchangeVouchers")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1386255408:
                    if (str3.equals("vipActivate")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                str2 = "PrivilegeRedeem";
            } else if (c == 1) {
                str2 = "ExclusiveGuard";
            } else if (c == 2) {
                str2 = "MembersExchange";
            } else if (c != 3) {
                LogUtil.h("QrCodeScanningActivity", "jumpVipH5Page suffix is not exist");
                str2 = "";
            } else {
                str2 = "VipActivate";
            }
            r(str2);
            return;
        }
        t(str);
    }

    private void r(String str) {
        if (Utils.g()) {
            nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(com.huawei.health.R.string._2130844514_res_0x7f021b62));
            finish();
            return;
        }
        if (TextUtils.isEmpty(str)) {
            nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(com.huawei.health.R.string._2130840249_res_0x7f020ab9));
            finish();
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath(Constants.H5PRO_PAGE_PREFIX + str + "?from=0");
        bzs.e().loadH5ProApp(this.c, "com.huawei.health.h5.vip", builder);
        finish();
    }

    private void t(String str) {
        if (Utils.o()) {
            nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(com.huawei.health.R.string._2130838362_res_0x7f02035a));
            finish();
            return;
        }
        H5proUtil.initH5pro();
        H5ProClient.startH5LightApp(this.c, str + "?jumpFrom=1", (H5ProLaunchOption) null);
        finish();
    }

    private void q(String str) {
        if (Utils.o()) {
            if (!CommonUtil.aa(BaseApplication.getContext())) {
                nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_network_connect_error));
            } else {
                nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R$string.IDS_device_wifi_my_qrcode_error_qrcode));
            }
            finish();
            return;
        }
        ope.deJ_(this.f9493a, MessageConstant.GROUP_MEDAL_TYPE, "");
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/?joinGroupQr=" + str);
        bzs.e().loadH5ProApp(this.c, "com.huawei.health.h5.groups", builder);
        finish();
    }

    private void j() {
        LogUtil.a("R_QrCode_QrCodeScanningActivity", "startQrCode");
        ezd.aue_(this, 4);
    }

    private void o(String str) {
        ope.e().deP_(this, str, this.f9493a);
    }

    private void m(final String str) {
        if (str == null) {
            return;
        }
        jdx.b(new Runnable() { // from class: onm
            @Override // java.lang.Runnable
            public final void run() {
                QrCodeScanningActivity.this.e(str);
            }
        });
    }

    public /* synthetic */ void e(String str) {
        try {
            if (h(str)) {
                Uri parse = Uri.parse(str);
                if (parse.isOpaque()) {
                    LogUtil.h("QrCodeScanningActivity", "This isn't a hierarchical URI.");
                    d();
                    return;
                }
                String query = parse.getQuery();
                if (TextUtils.isEmpty(query)) {
                    LogUtil.h("R_QrCode_QrCodeScanningActivity", "handleDecode null query");
                    d();
                    return;
                }
                String queryParameter = parse.getQueryParameter(VideoPlayFlag.PLAY_IN_ALL);
                if (!"deeplink".equals(queryParameter) && !"dl".equals(queryParameter)) {
                    String substring = query.substring(query.indexOf(queryParameter) + queryParameter.length());
                    LogUtil.a("QrCodeScanningActivity", "action:", queryParameter, ", qrResult:", substring);
                    e(queryParameter, substring);
                    return;
                }
                LogUtil.a("R_QrCode_QrCodeScanningActivity", "handleDecode appRouter");
                opf.deT_(this.c, parse, query);
            }
        } catch (NumberFormatException e) {
            e = e;
            LogUtil.b("R_QrCode_QrCodeScanningActivity", e.getClass().getSimpleName());
            d();
        } catch (PatternSyntaxException e2) {
            e = e2;
            LogUtil.b("R_QrCode_QrCodeScanningActivity", e.getClass().getSimpleName());
            d();
        } catch (IllegalArgumentException unused) {
            LogUtil.b("R_QrCode_QrCodeScanningActivity", "IllegalArgumentException");
            d();
        }
    }

    private boolean h(String str) {
        if (!str.toUpperCase(Locale.ENGLISH).startsWith("http".toUpperCase(Locale.ENGLISH))) {
            LogUtil.b("QrCodeScanningActivity", "url not start with http");
            if (!n(str)) {
                d();
            }
            return false;
        }
        if (!Utils.o() && ooj.c(str)) {
            LogUtil.a("QrCodeScanningActivity", "old device qr.");
            ooj.a(this);
            return false;
        }
        if (opf.b(str)) {
            return true;
        }
        if (opf.d(str)) {
            l(str);
            return false;
        }
        if (opf.e(str)) {
            return true;
        }
        LogUtil.a("R_QrCode_QrCodeScanningActivity", "other url qrcode");
        k(str);
        return false;
    }

    private boolean j(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("QrCodeScanningActivity", "content is empty");
            return false;
        }
        if (str.length() == 11) {
            return true;
        }
        LogUtil.h("QrCodeScanningActivity", "sn code does not match the length");
        return false;
    }

    private boolean f(String str) {
        String[] split = str.split("_");
        if (str.startsWith(u3.m) && split.length == 3 && split[1].length() == 4) {
            return true;
        }
        if (str.startsWith("RG01")) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String sb2 = sb.reverse().toString();
            if (sb2.length() >= 10) {
                return "5B60".equals(sb2.substring(10, 14));
            }
            return false;
        }
        if (str.startsWith("VivaChek-VGM60-") || str.startsWith("VivaChek-VGM6801-")) {
            return true;
        }
        LogUtil.a("QrCodeScanningActivity", "is other third device");
        return false;
    }

    private void d(final String str) {
        if (new shx().b(402)) {
            g(str);
            return;
        }
        LogUtil.a("QrCodeScanningActivity", "showHealthKit enter");
        ActivityResultLauncher registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.1
            @Override // androidx.activity.result.ActivityResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onActivityResult(ActivityResult activityResult) {
                LogUtil.a("QrCodeScanningActivity", "result:", Integer.valueOf(activityResult.getResultCode()));
                QrCodeScanningActivity.this.g(str);
            }
        });
        Intent intent = new Intent(this, (Class<?>) HealthKitActivity.class);
        intent.putExtra("key_health_kit_authorization_to_thread_device", true);
        registerForActivityResult.launch(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(String str) {
        String str2;
        String str3;
        LogUtil.a("QrCodeScanningActivity", "downloadDeviceResource");
        if (str.startsWith("RG01")) {
            str2 = "O0DH";
            str3 = str;
        } else if (str.startsWith("VivaChek-VGM60-")) {
            String[] split = str.split(com.huawei.openalliance.ad.constant.Constants.LINK);
            str3 = split[split.length - 1];
            str2 = "O0DE";
        } else if (str.startsWith("VivaChek-VGM6801-")) {
            String[] split2 = str.split(com.huawei.openalliance.ad.constant.Constants.LINK);
            str3 = split2[split2.length - 1];
            str2 = "O0DG";
        } else {
            String[] split3 = str.split("_");
            str2 = split3[1];
            str3 = split3[split3.length - 1];
        }
        LogUtil.a("QrCodeScanningActivity", "downloadDeviceResource : serialNumber is ", CommonUtil.l(str3));
        new dhy(this, str, str3, str2).d("HDK_BLOOD_PRESSURE");
    }

    private boolean i(String str) {
        LogUtil.a("QrCodeScanningActivity", "checkMACCode starting and content = ", CommonUtil.l(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("QrCodeScanningActivity", "checkMACCode content is empty and return false");
            return false;
        }
        String a2 = dks.a("ble", str);
        return !TextUtils.isEmpty(a2) && a2.matches("^([A-Fa-f0-9]{2}[-,:]){5}[A-Fa-f0-9]{2}$") && "54".equals(dks.a(FitRunPlayAudio.PLAY_TYPE_T, str));
    }

    private void u(String str) {
        LogUtil.h("QrCodeScanningActivity", "startBindWaitingFragmentByNfc");
        String a2 = dks.a("ble", str);
        LogUtil.a("QrCodeScanningActivity", "startBindGuideFragmentByNfc getParameter: mac is ", CommonUtil.l(a2));
        boolean h = ceo.d().h(a2);
        boolean b = cld.HJ_(this, "M00F", a2).b();
        LogUtil.a("QrCodeScanningActivity", "startDeviceMainActByNfc hagrid scale isBondedDevice,", Boolean.valueOf(h), ",isDeviceConnect,", Boolean.valueOf(b));
        if (h && b) {
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("BLE_FROM_QRCODE", dks.a("ble", str));
        intent.putExtra("DEVICE_TYPE_INDEX", dks.a(FitRunPlayAudio.PLAY_TYPE_T, str));
        intent.putExtra("PID_FROM_QRCODE", dks.a(u3.m, str));
        intent.putExtra("isBondedDevice", h);
        intent.putExtra("KEY_TO_GET_START_FROM_QRCODE", true);
        intent.putExtra("isNfcConnect", true);
        LogUtil.a("QrCodeScanningActivity", "startBindWaitingFragmentByNfc put isNfcConnect is true");
        intent.setClass(this, DeviceMainActivity.class);
        this.c.startActivity(intent);
        finish();
    }

    private void k(String str) {
        if (opf.c(str)) {
            LogUtil.a("R_QrCode_QrCodeScanningActivity", "jumpToBrowser：start webview");
            ope.deJ_(this.f9493a, "guide", "");
            y(str);
            ((QrCodeScanningActivity) this.c).finish();
            return;
        }
        if (n(str)) {
            return;
        }
        b(str);
    }

    private boolean n(String str) {
        if (!b()) {
            LogUtil.a("QrCodeScanningActivity", "isJumpIntentFromPlatform, not support codevalueplatform");
            return false;
        }
        CountDownLatch countDownLatch = this.b;
        if (countDownLatch != null) {
            try {
                if (!countDownLatch.await(3000L, TimeUnit.MILLISECONDS)) {
                    LogUtil.h("QrCodeScanningActivity", "isJumpIntentFromPlatform codeValueRules update timeout");
                }
            } catch (InterruptedException unused) {
                LogUtil.b("QrCodeScanningActivity", "isJumpIntentFromPlatform InterruptedException");
            }
        }
        CodeValueUtil.a d = CodeValueUtil.d();
        if (d != null && d.c() > 0) {
            LogUtil.a("QrCodeScanningActivity", "isJumpIntentFromPlatform codeValueRules size is ", Integer.valueOf(d.c()));
        } else {
            ReleaseLogUtil.e("R_QrCode_QrCodeScanningActivity", "isJumpIntentFromPlatform get rules failed");
        }
        Intent atZ_ = CodeValueUtil.atZ_(str, d);
        if (atZ_ != null) {
            try {
                startActivityForResult(atZ_, 0);
                ReleaseLogUtil.e("R_QrCode_QrCodeScanningActivity", "isJumpIntentFromPlatform jump intent success");
                return true;
            } catch (ActivityNotFoundException unused2) {
                LogUtil.b("QrCodeScanningActivity", "isJumpIntentFromPlatform activity not found");
                return false;
            }
        }
        LogUtil.h("QrCodeScanningActivity", "isJumpIntentFromPlatform,jumpIntent is null");
        return false;
    }

    private boolean b() {
        if (!Utils.o() && CommonUtil.bh()) {
            return true;
        }
        LogUtil.a("QrCodeScanningActivity", "isOversea:", Boolean.valueOf(Utils.o()), " isHuaweiSystem:", Boolean.valueOf(CommonUtil.bh()), " lower than Android7:", false);
        return false;
    }

    private void b(final String str) {
        LogUtil.a("R_QrCode_QrCodeScanningActivity", "checkBrows");
        if (opf.a(str)) {
            ope.deJ_(this.f9493a, "downloadApp", "");
        }
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.4
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(str).normalizeScheme());
                if (intent.resolveActivity(QrCodeScanningActivity.this.getPackageManager()) != null) {
                    QrCodeScanningActivity.this.ddu_(intent);
                } else {
                    QrCodeScanningActivity.this.d();
                }
            }
        });
    }

    private boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("QrCodeScanningActivity", "Content is empty");
            return false;
        }
        if (str.split("\\?")[0].matches("^https://id[0-9]*\\.cloud\\.huawei\\.com/qrcode/login\\.jsp$")) {
            return true;
        }
        LogUtil.h("QrCodeScanningActivity", "Large Screen QrCode is not matching");
        return false;
    }

    private void a(Context context, String str) {
        boolean z = !CommonUtil.z(context);
        int hwidVersionCode = com.huawei.common.util.Utils.getHwidVersionCode(context);
        LogUtil.a("QrCodeScanningActivity", "Current HMS Core Version = ", Integer.valueOf(hwidVersionCode));
        if (z && hwidVersionCode < 60300300) {
            LogUtil.a("QrCodeScanningActivity", "HMS Core Version is lower than 6.3.0.300");
            f();
            return;
        }
        if (z) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("hwid://com.huawei.hwid/QrCodeProcess"));
            intent.setPackage(HMSPackageManager.getInstance(context).getHMSPackageName());
            intent.putExtra("clientID", String.valueOf(BuildConfig.HMS_APPLICATION_ID));
            intent.putExtra("QR_CODE", str);
            startActivityForResult(intent, 5);
        } else {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("qRCode");
            int h = CommonUtils.h(parse.getQueryParameter("qrSiteID"));
            LogUtil.a("QrCodeScanningActivity", "qrCode = ", queryParameter, " qrSiteID = ", Integer.valueOf(h));
            c(queryParameter, h);
        }
        LogUtil.a("QrCodeScanningActivity", "Login in Large Screen finished");
    }

    private void c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("QrCodeScanningActivity", "The qrCode is empty.");
        } else {
            ThirdPartyLoginManager.getInstance().qrCodeAuthLogin(BaseApplication.getActivity(), str, i, "0", new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 == 0) {
                        LogUtil.a("QrCodeScanningActivity", "QrAuthLoginResult isSuccess()");
                        ope.deJ_(QrCodeScanningActivity.this.f9493a, CommonConstant.AckQrLoginVerifyValue.VERIFY_RESULT_OK, "");
                    } else {
                        LogUtil.h("QrCodeScanningActivity", "QrAuthLoginResult fail:", Integer.valueOf(i2));
                    }
                    QrCodeScanningActivity.this.finish();
                }
            });
        }
    }

    private void f() {
        LogUtil.a("QrCodeScanningActivity", "showHmsUpdateDialog dialog in main thread");
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.c).b(R$string.IDS_hw_wechat_rank_show_common_title).d(R$string.IDS_hwh_home_health_login_update_hwid).cyU_(com.huawei.health.R.string._2130837648_res_0x7f020090, new View.OnClickListener() { // from class: onr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QrCodeScanningActivity.this.ddv_(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void ddv_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("QrCodeScanningActivity", "Large Screen Vip QrCode is empty");
            return false;
        }
        String str2 = str.split("\\?")[0];
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("QrCodeScanningActivity", "Large Screen Vip QrCode Prefix is empty");
            return false;
        }
        Uri parse = Uri.parse(str);
        if (parse.isOpaque()) {
            LogUtil.h("QrCodeScanningActivity", "This isn't a hierarchical URI.");
            return false;
        }
        String queryParameter = parse.getQueryParameter(VideoPlayFlag.PLAY_IN_ALL);
        String queryParameter2 = parse.getQueryParameter(BleConstants.KEY_PATH);
        LogUtil.a("QrCodeScanningActivity", parse.getQueryParameterNames(), " paraOfAction = ", queryParameter, " paraOfPath = ", queryParameter2);
        return str2.matches("^https://url\\.cloud\\.huawei\\.com/1Lfn1eswP6$") && "dl".equals(queryParameter) && "abc/xyz".equals(queryParameter2);
    }

    private void s(String str) {
        LogUtil.a("QrCodeScanningActivity", "parseQrCodeResult LargeScreenVipQrCode is match");
        if (Utils.o()) {
            LogUtil.a("QrCodeScanningActivity", "account is oversea, not support");
            nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(com.huawei.health.R.string._2130844514_res_0x7f021b62));
            finish();
            return;
        }
        ope.deJ_(this.f9493a, "vip", "");
        Uri parse = Uri.parse(str);
        if (!dds_(parse)) {
            LogUtil.h("QrCodeScanningActivity", "large screen qrCode is inValid: ", str);
            nrh.b(BaseApplication.getContext(), com.huawei.health.R.string.IDS_device_wifi_my_qrcode_error_qrcode);
            finish();
        } else {
            String queryParameter = parse.getQueryParameter(JsbMapKeyNames.H5_USER_ID);
            String queryParameter2 = parse.getQueryParameter(HwPayConstant.KEY_USER_NAME);
            String queryParameter3 = parse.getQueryParameter("productId");
            if (c(queryParameter, queryParameter2)) {
                p(queryParameter3);
            }
            finish();
        }
    }

    private boolean dds_(Uri uri) {
        return (TextUtils.isEmpty(uri.getQueryParameter(JsbMapKeyNames.H5_USER_ID)) || TextUtils.isEmpty(uri.getQueryParameter(HwPayConstant.KEY_USER_NAME)) || TextUtils.isEmpty(uri.getQueryParameter("productId"))) ? false : true;
    }

    private boolean c(String str, String str2) {
        String accountInfo = LoginInit.getInstance(com.huawei.haf.application.BaseApplication.e()).getAccountInfo(1011);
        if (!Sha256.e(accountInfo, "SHA-256").equalsIgnoreCase(str)) {
            LogUtil.h("QrCodeScanningActivity", "app huid is not match with large screen");
            nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(com.huawei.health.R.string._2130850519_res_0x7f0232d7, str2));
            ope.deJ_(this.f9493a, "vip_failed", "");
            return false;
        }
        LogUtil.a("QrCodeScanningActivity", "app huid is matching with large screen: ", accountInfo);
        return true;
    }

    private void p(final String str) {
        final PayApi payApi = (PayApi) Services.c("TradeService", PayApi.class);
        payApi.getProductDetails(str, new IBaseResponseCallback() { // from class: ont
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                QrCodeScanningActivity.this.a(str, payApi, i, obj);
            }
        });
    }

    public /* synthetic */ void a(String str, PayApi payApi, int i, Object obj) {
        if (i != 0) {
            LogUtil.b("QrCodeScanningActivity", "getProductDetails failed, cause errorCode is: ", Integer.valueOf(i), ", productId = ", str);
            nrh.b(BaseApplication.getContext(), R$string.IDS_system_error);
        } else if (!(obj instanceof Product)) {
            LogUtil.b("QrCodeScanningActivity", "getProductDetails failed, cause objData is: ", obj, ", productId = ", str);
            nrh.b(BaseApplication.getContext(), R$string.IDS_system_error);
        } else {
            Product product = (Product) obj;
            LogUtil.a("QrCodeScanningActivity", "getProductSummaryInfo success: ", product.toString());
            d(str, payApi, product);
        }
    }

    private void d(String str, final PayApi payApi, Product product) {
        final ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(str);
        productInfo.setPurchasePolicy(product.getPurchasePolicy());
        productInfo.setProductName(product.getProductName());
        productInfo.setClientVersion(eaa.c(this.c));
        productInfo.setTrigResType("1000");
        productInfo.setTrigResName("会员开通");
        productInfo.setTrigResMemberPrice("0");
        if (product.getSubscriptionPromotion() != null && product.getSubscriptionPromotion().getPromotionId() != null) {
            final String promotionId = product.getSubscriptionPromotion().getPromotionId();
            payApi.promotionInfoQuery(str, promotionId, product.getSubscriptionPromotion().getPromotionPolicyId(), new IBaseResponseCallback() { // from class: onn
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    QrCodeScanningActivity.this.e(productInfo, promotionId, payApi, i, obj);
                }
            });
        } else {
            payApi.buyByProductId(this, 10000, nrv.a(productInfo));
        }
    }

    public /* synthetic */ void e(ProductInfo productInfo, String str, PayApi payApi, int i, Object obj) {
        if ((obj instanceof Integer) && ((Integer) obj).intValue() == 0) {
            productInfo.setPromotionId(str);
        }
        payApi.buyByProductId(this, 10000, nrv.a(productInfo));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ddu_(final Intent intent) {
        String string = this.c.getResources().getString(com.huawei.health.R.string._2130843455_res_0x7f02173f);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.c);
        builder.e(this.c.getString(com.huawei.health.R.string._2130843527_res_0x7f021787, string)).czE_(this.c.getString(com.huawei.health.R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("QrCodeScanningActivity", "start browser");
                try {
                    QrCodeScanningActivity.this.startActivity(intent);
                    if (QrCodeScanningActivity.this.c instanceof QrCodeScanningActivity) {
                        ((QrCodeScanningActivity) QrCodeScanningActivity.this.c).finish();
                    }
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("QrCodeScanningActivity", "ActivityNotFoundException");
                    QrCodeScanningActivity.this.d();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(this.c.getString(com.huawei.health.R.string._2130839505_res_0x7f0207d1), new View.OnClickListener() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (QrCodeScanningActivity.this.c instanceof QrCodeScanningActivity) {
                    ((QrCodeScanningActivity) QrCodeScanningActivity.this.c).finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private void y(String str) {
        Intent intent = new Intent(this.c, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        this.c.startActivity(intent);
    }

    private void e(String str, String str2) {
        ope.e().deN_(str, str2, this.f9493a, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        ope.e().deS_(this.f9493a, this);
    }

    public void a() {
        LogUtil.a("QrCodeScanningActivity", "into restartScan.");
        j();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("QrCodeScanningActivity", "onBackPressed");
        super.onBackPressed();
        health.compact.a.util.LogUtil.d("QrCodeScanningActivity", "cancel to select device");
        ObserverManagerUtil.c("DEVICE_ASSOCIATION", 152);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
