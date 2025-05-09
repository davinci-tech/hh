package com.huawei.sim.esim.view;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.sim.esim.qrcode.QrCodeActivity;
import com.huawei.sim.multisim.MultiSimConfigActivity;
import com.huawei.sim.multisim.activity.EsimAccountManageActivity;
import com.huawei.sim.multisim.activity.EsimOverseaEsActivity;
import com.huawei.sim.multisim.activity.MultiSimAuthActivity;
import com.huawei.sim.multisim.activity.MultiSimInstallGuideActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.jdc;
import defpackage.jdf;
import defpackage.ktx;
import defpackage.nbt;
import defpackage.nca;
import defpackage.nce;
import defpackage.ncf;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class BaseOpenSimCardActivity extends BaseActivity {
    protected int c;
    protected int g;
    protected String i;
    private NoTitleCustomAlertDialog l;
    private Context m;
    private CustomTextAlertDialog n;
    protected CommonDialog21 f = null;
    protected String j = null;
    protected String h = "";
    public Handler e = new Handler();
    protected IBaseResponseCallback d = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("BaseOpenSimCardActivity", "mBaseResponseCallback the errCode:", Integer.valueOf(i));
        }
    };

    /* renamed from: a, reason: collision with root package name */
    protected IBaseResponseCallback f8668a = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            LogUtil.a("BaseOpenSimCardActivity", "mAuthResponseCallback the error:", Integer.valueOf(i));
            BaseOpenSimCardActivity.this.e.post(new Runnable() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.2.3
                @Override // java.lang.Runnable
                public void run() {
                    boolean z;
                    if (i == 0) {
                        String str = null;
                        try {
                        } catch (JsonIOException unused) {
                            LogUtil.b("BaseOpenSimCardActivity", "mAuthResponseCallback objData JsonIOException");
                        }
                        if (obj instanceof jdf) {
                            str = new Gson().toJson(((jdf) obj).d(), jdc.class);
                            if (((jdf) obj).e() != 1) {
                                z = true;
                                Intent intent = new Intent(BaseOpenSimCardActivity.this, (Class<?>) EsimProfileActivity.class);
                                intent.putExtra("eSim_profile", str);
                                LogUtil.a("BaseOpenSimCardActivity", "the data:", str);
                                intent.putExtra("confirm_status", !new nbt().b(BaseOpenSimCardActivity.this.j) || z);
                                intent.putExtra("esim_new_original_key", 1);
                                BaseOpenSimCardActivity.this.startActivity(intent);
                                BaseOpenSimCardActivity.this.finish();
                                return;
                            }
                        } else {
                            str = new Gson().toJson(obj, jdc.class);
                        }
                        z = false;
                        Intent intent2 = new Intent(BaseOpenSimCardActivity.this, (Class<?>) EsimProfileActivity.class);
                        intent2.putExtra("eSim_profile", str);
                        LogUtil.a("BaseOpenSimCardActivity", "the data:", str);
                        intent2.putExtra("confirm_status", !new nbt().b(BaseOpenSimCardActivity.this.j) || z);
                        intent2.putExtra("esim_new_original_key", 1);
                        BaseOpenSimCardActivity.this.startActivity(intent2);
                        BaseOpenSimCardActivity.this.finish();
                        return;
                    }
                    Object obj2 = obj;
                    BaseOpenSimCardActivity.this.e(i, obj2 instanceof Integer ? ((Integer) obj2).intValue() : -1);
                    sqo.o("mAuthResponseCallback failed errorCode: " + i);
                }
            });
        }
    };
    protected IBaseResponseCallback b = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("BaseOpenSimCardActivity", "errCode:", Integer.valueOf(i));
        }
    };
    private final BroadcastReceiver o = new BroadcastReceiver() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                LogUtil.h("BaseOpenSimCardActivity", "mNetBlueBroadcastReceiver intent is null or action is null");
                return;
            }
            LogUtil.a("BaseOpenSimCardActivity", "mNetBlueBroadcastReceiver action = ", intent.getAction());
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || "com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) && !ncf.csO_(intent)) {
                    LogUtil.h("BaseOpenSimCardActivity", "mNetBlueBroadcastReceiver is device not support current function");
                    sqo.o("mNetBlueBroadcastReceiver is device not support current function");
                } else {
                    Message obtainMessage = BaseOpenSimCardActivity.this.e.obtainMessage(10001);
                    obtainMessage.obj = intent.getAction();
                    BaseOpenSimCardActivity.this.e.sendMessage(obtainMessage);
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        LogUtil.a("BaseOpenSimCardActivity", "the errorCode:", Integer.valueOf(i));
        if (i2 == -2) {
            Intent intent = new Intent(this, (Class<?>) EsimProfileBtFailActivity.class);
            intent.putExtra("confirm_status", new nbt().b(this.j));
            intent.putExtra("esim_new_original_key", 1);
            startActivity(intent);
            finish();
            return;
        }
        d(i);
    }

    private void d(int i) {
        Intent intent = new Intent(this, (Class<?>) EsimProfileAuthenticationFail.class);
        intent.putExtra("mata_report", i);
        intent.putExtra("esim_new_original_key", 1);
        startActivity(intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.m = this;
        ktx.e().c(this.b);
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ktx.e().e(this.b);
        f();
        Handler handler = this.e;
        if (handler != null) {
            handler.removeMessages(10001);
        }
    }

    protected void a(int i) {
        LogUtil.a("BaseOpenSimCardActivity", "showLoadingDialog()");
        if (isFinishing()) {
            return;
        }
        CommonDialog21 commonDialog21 = this.f;
        if (commonDialog21 == null) {
            new CommonDialog21(this, R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.f = a2;
            a2.e(getResources().getString(i));
            this.f.setCancelable(false);
            this.f.a();
            LogUtil.a("BaseOpenSimCardActivity", "mLoadingUserInformationDialog.show()");
            return;
        }
        commonDialog21.e(getResources().getString(i));
        this.f.a();
        LogUtil.a("BaseOpenSimCardActivity", "mLoadingUserInformationDialog.show()");
    }

    protected void c() {
        ktx.e().c(this.b);
        a(R.string._2130848054_res_0x7f022936);
        ktx.e().a(this.j, this.d, this.f8668a);
    }

    protected void d() {
        PermissionUtil.b(this.m, PermissionUtil.PermissionType.CAMERA, new CustomPermissionAction(this.m) { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.5
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("BaseOpenSimCardActivity", "enterQrCodePage() onGranted");
                BaseOpenSimCardActivity.this.e();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("BaseOpenSimCardActivity", "enterQrCodePage() onDenied");
                BaseOpenSimCardActivity.this.e();
            }

            @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
                LogUtil.a("BaseOpenSimCardActivity", "enterQrCodePage() onForeverDenied");
                BaseOpenSimCardActivity.this.e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            Intent intent = new Intent(this.m, (Class<?>) QrCodeActivity.class);
            intent.putExtra("esim_new_original_key", 1);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "enterQrCodePage ActivityNotFoundException");
        }
    }

    protected void b(MultiSimDeviceInfo multiSimDeviceInfo) {
        try {
            Intent intent = new Intent(this.m, (Class<?>) EsimStandaloneNumGuideActivity.class);
            intent.putExtra("multi_sim_device_info", multiSimDeviceInfo);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "startInstallGuideActivity ActivityNotFoundException");
        }
    }

    protected void b(String str, int i) {
        if (i == 1 && ncf.b(str)) {
            c(str, i);
            return;
        }
        String e = ncf.e(str, i);
        boolean a2 = ncf.a(this.m, e);
        if (Utils.c(this.m, e) && a2) {
            e(str, i);
        } else {
            c(str, i);
        }
    }

    private void c(String str, int i) {
        try {
            Intent intent = new Intent(this.m, (Class<?>) MultiSimInstallGuideActivity.class);
            intent.putExtra("simImsi", str);
            intent.putExtra("cardType", i);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "startInstallGuideActivity ActivityNotFoundException");
        }
    }

    private void e(final String str, final int i) {
        if (this.l == null) {
            String format = String.format(Locale.ENGLISH, this.m.getString(R.string._2130847993_res_0x7f0228f9), ncf.c(this.m, str, i));
            String str2 = format + "\n\n" + this.m.getResources().getString(R.string._2130847999_res_0x7f0228ff);
            SpannableString spannableString = new SpannableString(str2);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.m.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan((int) (this.m.getResources().getDimension(R.dimen._2131364180_res_0x7f0a0954) / this.m.getResources().getDisplayMetrics().scaledDensity), true);
            spannableString.setSpan(foregroundColorSpan, 0, format.length(), 33);
            spannableString.setSpan(absoluteSizeSpan, 0, format.length(), 33);
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(this.m.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
            AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(14, true);
            spannableString.setSpan(foregroundColorSpan2, format.length() + 1, str2.length(), 33);
            spannableString.setSpan(absoluteSizeSpan2, format.length() + 1, str2.length(), 33);
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.m).czx_(spannableString).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseOpenSimCardActivity.this.g();
                    BaseOpenSimCardActivity.this.a(str, i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseOpenSimCardActivity.this.g();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            this.l = e;
            e.setCancelable(false);
            if (this.l.isShowing()) {
                return;
            }
            this.l.show();
            return;
        }
        LogUtil.h("BaseOpenSimCardActivity", "showJumpOperatorDialog is error");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i) {
        try {
            Intent intent = new Intent(this.m, (Class<?>) MultiSimAuthActivity.class);
            intent.putExtra("simImsi", str);
            intent.putExtra("cardType", i);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "startMultiSimAuthActivity ActivityNotFoundException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.l.dismiss();
        this.l = null;
    }

    protected void b(final String str, final String str2, final boolean z) {
        if (this.n == null) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.m).b(R.string._2130847965_res_0x7f0228dd).d(R.string._2130848000_res_0x7f022900).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseOpenSimCardActivity.this.b();
                    BaseOpenSimCardActivity.this.e(str, str2, z);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.sim.esim.view.BaseOpenSimCardActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseOpenSimCardActivity.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.n = a2;
            a2.setCancelable(false);
            if (this.n.isShowing()) {
                return;
            }
            this.n.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.n.dismiss();
        this.n = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, String str2, boolean z) {
        if (j()) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setComponent(new ComponentName("com.huawei.hwmultisim", "com.huawei.hwmultisim.views.IntroduceActivity"));
            startActivity(intent);
            return;
        }
        c(str, str2, z);
    }

    private boolean j() {
        try {
            this.m.getPackageManager().getPackageInfo("com.huawei.hwmultisim", 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("BaseOpenSimCardActivity", "no multisim apk");
            return false;
        }
    }

    private void c(String str, String str2, boolean z) {
        Intent intent;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.h("BaseOpenSimCardActivity", "imsi or openMethod is null");
            return;
        }
        try {
            if (ncf.h(str)) {
                intent = new Intent(this.m, (Class<?>) EsimOverseaEsActivity.class);
                intent.putExtra("esim_auth_method", this.c);
                intent.putExtra("esim_is_one_open_method", z);
            } else {
                intent = new Intent(this.m, (Class<?>) MultiSimConfigActivity.class);
            }
            intent.putExtra("simImsi", str2);
            intent.putExtra("MultiSimSlotId", this.g);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "startMultiSimMenu ActivityNotFoundException");
        }
    }

    private void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        BroadcastManagerUtil.bFC_(this, this.o, intentFilter, LocalBroadcast.c, null);
    }

    private void f() {
        try {
            LogUtil.a("BaseOpenSimCardActivity", "unregisterNetBlueBroadcast");
            unregisterReceiver(this.o);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "unregisterNetBlueBroadcast IllegalArgumentException");
        }
    }

    protected void a() {
        try {
            Intent intent = new Intent(this.m, (Class<?>) EsimAccountManageActivity.class);
            intent.putExtra("esim_auth_method", this.c);
            intent.putExtra("simImsi", this.h);
            intent.putExtra("MultiSimSlotId", this.g);
            this.m.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BaseOpenSimCardActivity", "startEsimAccountManageActivity ActivityNotFoundException");
        }
    }

    protected void b(Map<String, Object> map) {
        LogUtil.a("BaseOpenSimCardActivity", "setEsimAccountManageParam");
        if (map == null) {
            LogUtil.h("BaseOpenSimCardActivity", "setEsimAccountManageParam simCardInfo is null");
            return;
        }
        nca e = nce.e(map);
        if (e != null) {
            this.c = e.c();
            this.i = e.e();
        }
        if (map.containsKey("slotId")) {
            Object obj = map.get("slotId");
            if (obj instanceof Integer) {
                this.g = ((Integer) obj).intValue();
            }
        }
        if (map.containsKey("imsi")) {
            Object obj2 = map.get("imsi");
            if (obj2 instanceof String) {
                this.h = (String) obj2;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
