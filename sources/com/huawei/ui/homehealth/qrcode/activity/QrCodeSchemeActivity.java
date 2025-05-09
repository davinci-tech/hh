package com.huawei.ui.homehealth.qrcode.activity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.notification.SensitivePermissionStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import defpackage.jdx;
import defpackage.nrh;
import defpackage.obb;
import defpackage.onv;
import defpackage.ope;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes.dex */
public class QrCodeSchemeActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Bundle f9498a;
    private String b = null;
    private boolean c = false;
    public ActivityResultLauncher<IntentSenderRequest> e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ddw_(getIntent());
        d();
    }

    private void d() {
        this.e = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                QrCodeSchemeActivity.this.e((ActivityResult) obj);
            }
        });
    }

    /* synthetic */ void e(ActivityResult activityResult) {
        QrCodeBaseHandler a2 = ope.e().a();
        if (!(a2 instanceof onv)) {
            LogUtil.h("QrCodeSchemeActivity", "qrCoderHandler not DeviceQrCodeHandler");
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
        obb.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == -1) {
                    onvVar.ddL_(QrCodeSchemeActivity.this);
                } else {
                    onvVar.a();
                }
            }
        }, this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a("onNewIntent", new Object[0]);
        ddw_(intent);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102) {
            QrCodeBaseHandler a2 = ope.e().a();
            if (!(a2 instanceof onv)) {
                LogUtil.h("QrCodeSchemeActivity", "qrCoderHandler not DeviceQrCodeHandler");
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
                LogUtil.a("QrCodeSchemeActivity", "stopScanAndCloseTimer");
                if (isFinishing()) {
                    return;
                }
                finish();
                return;
            }
            onvVar.c();
        }
        if (i == 1) {
            if (i2 == 2 && intent != null) {
                if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
                    c();
                    return;
                } else {
                    d(intent.getStringExtra("device_id"));
                    return;
                }
            }
            finish();
        }
    }

    private void ddw_(Intent intent) {
        if (intent != null) {
            this.f9498a = intent.getExtras();
        }
        boolean z = (intent == null || TextUtils.isEmpty(intent.getStringExtra("schemeQrCode"))) ? false : true;
        boolean z2 = this.f9498a != null;
        if (z || z2) {
            b();
            this.b = z2 ? this.f9498a.getString("schemeQrCode") : intent.getStringExtra("schemeQrCode");
            this.c = true;
            LogUtil.a("QrCodeSchemeActivity", "KEY_NEED_SOURCE_INFO=", Boolean.valueOf(intent.getBooleanExtra("isAddSourceInfo", false)));
            if (z2 && !intent.getBooleanExtra("isAddSourceInfo", false)) {
                this.f9498a.putBoolean("sschemeQrCode", this.c);
            }
            b(this.b);
            return;
        }
        ReleaseLogUtil.d("R_QrCode_QrCodeSchemeActivity", "intent invalid");
        c();
    }

    private void c() {
        Intent intent = new Intent();
        intent.setClassName(this, "com.huawei.health.MainActivity");
        startActivity(intent);
        finish();
    }

    private void d(String str) {
        Intent intent = new Intent();
        intent.setClassName(this, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent.putExtra("device_id", str);
        startActivity(intent);
        finish();
    }

    protected void b() {
        LogUtil.a("QrCodeSchemeActivity", "initView");
        setContentView(R.layout.activity_qrcode_loading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2) {
        ope.e().deN_(str, str2, this.f9498a, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ope.e().deS_(this.f9498a, this);
    }

    public static void a() {
        nrh.b(BaseApplication.getContext(), R.string.IDS_device_wifi_my_qrcode_error_qrcode);
    }

    public void e() {
        b(this.b);
    }

    private void b(final String str) {
        LogUtil.a("QrCodeSchemeActivity", "startFromScheme");
        jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.qrcode.activity.QrCodeSchemeActivity.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    LogUtil.c("QrCodeSchemeActivity", "content:", str);
                    String str2 = str.split("\\&")[0];
                    String[] split = str2.split("\\=");
                    if (split != null && split.length >= 2) {
                        String str3 = split[1];
                        String replace = str.replace(str2, "");
                        LogUtil.c("QrCodeSchemeActivity", "action:", str3, "|qrResultAction:", str2, "|qrResult:", replace);
                        QrCodeSchemeActivity.this.e(str3, replace);
                        return;
                    }
                    QrCodeSchemeActivity.this.j();
                } catch (NumberFormatException e) {
                    e = e;
                    LogUtil.b("R_QrCode_QrCodeSchemeActivity", e.getClass().getSimpleName());
                    QrCodeSchemeActivity.this.j();
                } catch (PatternSyntaxException e2) {
                    e = e2;
                    LogUtil.b("R_QrCode_QrCodeSchemeActivity", e.getClass().getSimpleName());
                    QrCodeSchemeActivity.this.j();
                } catch (IllegalArgumentException unused) {
                    LogUtil.b("R_QrCode_QrCodeSchemeActivity", "IllegalArgumentException");
                    QrCodeSchemeActivity.this.j();
                }
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("R_QrCode_QrCodeSchemeActivity", "schemeQrCode is empty");
            return false;
        }
        try {
            String[] split = str.split("\\&")[0].split("\\=");
            if (split.length >= 2) {
                return split[1].equals("c");
            }
        } catch (PatternSyntaxException unused) {
            LogUtil.b("R_QrCode_QrCodeSchemeActivity", "PatternSyntaxException");
            a();
        } catch (IllegalArgumentException unused2) {
            LogUtil.b("R_QrCode_QrCodeSchemeActivity", "IllegalArgumentException");
            a();
        }
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
