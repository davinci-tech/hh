package com.huawei.sim.esim.qrcode;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.view.EsimProfileActivity;
import com.huawei.sim.esim.view.EsimProfileAuthenticationFail;
import com.huawei.sim.esim.view.EsimProfileBtFailActivity;
import com.huawei.sim.esim.view.ScanFailActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import defpackage.ezd;
import defpackage.jdc;
import defpackage.jdf;
import defpackage.ktx;
import defpackage.nbt;
import defpackage.ncf;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sqo;
import java.util.Locale;

/* loaded from: classes6.dex */
public class QrCodeActivity extends BaseActivity {
    private Context d;
    private int i;
    private CommonDialog21 f = null;
    private String j = null;
    private IBaseResponseCallback b = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.qrcode.QrCodeActivity.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("QrCodeActivity", "mBaseResponseCallback the errCode:", Integer.valueOf(i));
        }
    };
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.qrcode.QrCodeActivity.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("QrCodeActivity", "errCode:", Integer.valueOf(i));
        }
    };
    private Handler c = new Handler();

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f8666a = new IBaseResponseCallback() { // from class: com.huawei.sim.esim.qrcode.QrCodeActivity.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(final int i, final Object obj) {
            LogUtil.c("QrCodeActivity", "mAuthResponseCallback the error:", Integer.valueOf(i));
            QrCodeActivity.this.c.post(new Runnable() { // from class: com.huawei.sim.esim.qrcode.QrCodeActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean z;
                    if (i == 0) {
                        String str = null;
                        try {
                        } catch (JsonIOException unused) {
                            LogUtil.b("QrCodeActivity", "mAuthResponseCallback objData JsonIOException");
                        }
                        if (obj instanceof jdf) {
                            str = new Gson().toJson(((jdf) obj).d(), jdc.class);
                            if (((jdf) obj).e() != 1) {
                                z = true;
                                Intent intent = new Intent(QrCodeActivity.this, (Class<?>) EsimProfileActivity.class);
                                intent.putExtra("eSim_profile", str);
                                LogUtil.c("QrCodeActivity", "the data:", str);
                                intent.putExtra("confirm_status", !new nbt().b(QrCodeActivity.this.j) || z);
                                intent.putExtra("esim_new_original_key", QrCodeActivity.this.i);
                                QrCodeActivity.this.startActivity(intent);
                                QrCodeActivity.this.finish();
                                QrCodeActivity.this.d();
                                return;
                            }
                        } else {
                            str = new Gson().toJson(obj, jdc.class);
                        }
                        z = false;
                        Intent intent2 = new Intent(QrCodeActivity.this, (Class<?>) EsimProfileActivity.class);
                        intent2.putExtra("eSim_profile", str);
                        LogUtil.c("QrCodeActivity", "the data:", str);
                        intent2.putExtra("confirm_status", !new nbt().b(QrCodeActivity.this.j) || z);
                        intent2.putExtra("esim_new_original_key", QrCodeActivity.this.i);
                        QrCodeActivity.this.startActivity(intent2);
                        QrCodeActivity.this.finish();
                        QrCodeActivity.this.d();
                        return;
                    }
                    Object obj2 = obj;
                    QrCodeActivity.this.d(i, obj2 instanceof Integer ? ((Integer) obj2).intValue() : -1);
                }
            });
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = this;
        ktx.e().c(this.e);
        Intent intent = getIntent();
        if (intent != null) {
            this.i = intent.getIntExtra("esim_new_original_key", 0);
        }
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        e();
    }

    private void e() {
        LogUtil.a("QrCodeActivity", "startScan");
        ezd.aue_(this, 10);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ktx.e().e(this.e);
        ktx.e().a(this.f8666a);
        d();
        Handler handler = this.c;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    public boolean b() {
        if (new nbt().e(this.j)) {
            LogUtil.b("QrCodeActivity", "qrCode is invalid");
            sqo.o("qrCode is invalid");
            Intent intent = new Intent(this, (Class<?>) ScanFailActivity.class);
            intent.putExtra("esim_new_original_key", this.i);
            startActivity(intent);
            finish();
            return false;
        }
        if (ktx.e().c() == 2) {
            LogUtil.a("QrCodeActivity", "bluetooth connected");
            ncf.b("", false);
            e(com.huawei.health.R.string._2130847904_res_0x7f0228a0);
            ktx.e().a(this.j, this.b, this.f8666a);
            return true;
        }
        Intent intent2 = new Intent(this, (Class<?>) EsimProfileBtFailActivity.class);
        intent2.putExtra("esim_new_original_key", this.i);
        startActivity(intent2);
        finish();
        return true;
    }

    private void e(int i) {
        LogUtil.a("QrCodeActivity", "showLoadingDialog()");
        if (isFinishing()) {
            return;
        }
        CommonDialog21 commonDialog21 = this.f;
        if (commonDialog21 == null) {
            new CommonDialog21(this, com.huawei.health.R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(this);
            this.f = a2;
            a2.e(getResources().getString(i));
            this.f.setCancelable(false);
            this.f.a();
            LogUtil.a("QrCodeActivity", "mLoadingUserInformationDialog.show()");
            return;
        }
        commonDialog21.e(getResources().getString(i));
        this.f.a();
        LogUtil.a("QrCodeActivity", "mLoadingUserInformationDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (isFinishing()) {
            return;
        }
        LogUtil.a("QrCodeActivity", "enter dismissLoadingDialog()");
        CommonDialog21 commonDialog21 = this.f;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("QrCodeActivity", "dismissLoadingDialog()");
        this.f.cancel();
        this.f = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        CommonDialog21 commonDialog21 = this.f;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        if (i2 == -2) {
            Intent intent = new Intent(this, (Class<?>) EsimProfileBtFailActivity.class);
            intent.putExtra("confirm_status", new nbt().b(this.j));
            intent.putExtra("esim_new_original_key", this.i);
            startActivity(intent);
            finish();
            d();
            return;
        }
        d(i);
    }

    private void d(int i) {
        Intent intent = new Intent(this, (Class<?>) EsimProfileAuthenticationFail.class);
        intent.putExtra("mata_report", i);
        intent.putExtra("esim_new_original_key", this.i);
        startActivity(intent);
        finish();
        d();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a("QrCodeActivity", "onActivityResult requestCode:", Integer.valueOf(i), ",resultCode:", Integer.valueOf(i2));
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            LogUtil.a("QrCodeActivity", "resultCode is zero, finish activity");
            finish();
            return;
        }
        if (i2 == -1 && i == 10) {
            int aub_ = ezd.aub_(intent);
            if (aub_ == 0) {
                crL_(intent);
            } else if (aub_ == 2) {
                nsn.cKS_(this, 10);
            } else {
                finish();
            }
        }
    }

    private void crL_(Intent intent) {
        String auc_ = ezd.auc_(intent);
        this.j = auc_;
        if (TextUtils.isEmpty(auc_)) {
            LogUtil.h("QrCodeActivity", "invalid image");
            sqo.o("invalid image");
            nrh.e(this.d, com.huawei.health.R.string._2130847975_res_0x7f0228e7);
            finish();
            return;
        }
        if (this.j.contains("1$esim.yhdzd.chinamobile.com") || this.j.contains("1$esim.yhdzd.chinamobile.com".toUpperCase(Locale.ENGLISH)) || this.j.contains("1$117.132.188.32")) {
            LogUtil.h("QrCodeActivity", "invalid download address.");
            sqo.o("invalid download address.");
            nrh.c(this.d, this.d.getResources().getString(com.huawei.health.R.string._2130841543_res_0x7f020fc7));
            finish();
            return;
        }
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
