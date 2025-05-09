package com.huawei.updatesdk.service.otaupdate;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hms.update.UpdateConstants;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.updatesdk.b.i.a;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.c;
import java.io.Serializable;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class AppUpdateActivity extends Activity implements com.huawei.updatesdk.service.otaupdate.b, com.huawei.updatesdk.b.f.b {

    /* renamed from: a, reason: collision with root package name */
    private String f10856a;
    private AlertDialog b;
    private com.huawei.updatesdk.b.i.a c;
    private com.huawei.updatesdk.b.i.a d;
    private ProgressBar e;
    private TextView f;
    private com.huawei.updatesdk.b.d.c l;
    private q p;
    private boolean g = false;
    private boolean h = false;
    private ApkUpgradeInfo i = null;
    private boolean j = false;
    private boolean k = false;
    private int m = -99;
    private int n = -99;
    private int o = -99;
    private boolean q = false;

    @Override // android.app.Activity
    protected void onDestroy() {
        com.huawei.updatesdk.b.i.a aVar = this.c;
        if (aVar != null) {
            aVar.a();
            this.c = null;
        }
        com.huawei.updatesdk.b.i.a aVar2 = this.d;
        if (aVar2 != null) {
            aVar2.a();
            this.d = null;
        }
        a();
        f();
        super.onDestroy();
        finishActivity(1002);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Serializable serializable;
        requestWindowFeature(1);
        com.huawei.updatesdk.a.a.d.i.c.f().a(getWindow());
        super.onCreate(bundle);
        Bundle b2 = com.huawei.updatesdk.a.b.b.b.a(getIntent()).b();
        com.huawei.updatesdk.a.b.a.a.a(this);
        if (b2 != null) {
            try {
                serializable = b2.getSerializable("app_update_parm");
                this.q = b2.getBoolean("is_apptouch", false);
                this.k = b2.getBoolean("app_must_btn", false);
            } catch (Throwable th) {
                com.huawei.updatesdk.a.a.a.a("AppUpdateActivity", "Type Conversion Error: " + th.getMessage());
                super.finish();
                return;
            }
        } else {
            serializable = null;
        }
        String b3 = com.huawei.updatesdk.b.e.e.a(this.q).b();
        this.f10856a = b3;
        if (TextUtils.isEmpty(b3)) {
            com.huawei.updatesdk.a.a.a.a("AppUpdateActivity", "finish activity and appStorePkgName is: " + this.f10856a);
            super.finish();
            return;
        }
        if (!(serializable instanceof ApkUpgradeInfo)) {
            this.m = 3;
            finish();
            return;
        }
        ApkUpgradeInfo apkUpgradeInfo = (ApkUpgradeInfo) serializable;
        this.i = apkUpgradeInfo;
        if (apkUpgradeInfo.getIsCompulsoryUpdate_() == 1) {
            this.j = true;
        }
        if (1 == this.i.getDevType_() && com.huawei.updatesdk.b.h.b.i(this, this.f10856a)) {
            b(this.i);
        } else if (2 == this.i.getDevType_() && com.huawei.updatesdk.b.h.b.h(this, this.f10856a)) {
            com.huawei.updatesdk.b.g.c.b.execute(new e());
        } else {
            c(this.i);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        com.huawei.updatesdk.a.a.a.b("AppUpdateActivity", "get from market requestCode: " + i2 + ", resultCode: " + i3);
        if (i2 == 1002) {
            if (intent != null) {
                this.m = i3;
                a(intent);
            }
            this.o = i3 == 4 ? 100 : 101;
            if (this.h) {
                return;
            }
            finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.g) {
            overridePendingTransition(0, 0);
        }
        Intent intent = new Intent();
        intent.putExtra("status", this.m);
        intent.putExtra(UpdateKey.FAIL_CODE, this.n);
        intent.putExtra(UpdateKey.MUST_UPDATE, this.g);
        intent.putExtra(UpdateKey.BUTTON_STATUS, this.o);
        com.huawei.updatesdk.service.otaupdate.d.a().b(intent);
        super.finish();
    }

    @Override // com.huawei.updatesdk.service.otaupdate.b
    public void b(int i2) {
        Toast.makeText(this, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_getting_message_fail_prompt_toast")), 0).show();
        com.huawei.updatesdk.service.otaupdate.d.a().a(i2);
        finish();
    }

    @Override // com.huawei.updatesdk.service.otaupdate.b
    public void a(com.huawei.updatesdk.service.appmgr.bean.c cVar) {
        if (cVar == null || !cVar.f()) {
            Toast.makeText(this, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_getting_message_fail_prompt_toast")), 0).show();
            finish();
        } else {
            com.huawei.updatesdk.b.f.c.a().a(this);
            this.f10856a = cVar.d();
            b(cVar);
        }
    }

    @Override // com.huawei.updatesdk.b.f.b
    public void a(int i2, com.huawei.updatesdk.a.b.b.b bVar) {
        if (bVar == null) {
            return;
        }
        if (i2 == 0) {
            a(bVar);
        } else if (1 == i2) {
            b(bVar);
        } else {
            c(bVar);
        }
    }

    @Override // com.huawei.updatesdk.service.otaupdate.b
    public void a(int i2) {
        Toast.makeText(this, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_getting_message_fail_prompt_toast")), 0).show();
        com.huawei.updatesdk.service.otaupdate.d.a().a(i2);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (com.huawei.updatesdk.b.h.b.g(this, this.f10856a)) {
            a(this.i.getPackage_(), this.i.getDetailId_());
            return;
        }
        com.huawei.updatesdk.b.d.f.a(this);
        com.huawei.updatesdk.b.d.f.a(this.q);
        this.d.a();
    }

    private void f() {
        com.huawei.updatesdk.service.otaupdate.a.a(this, this.p);
        com.huawei.updatesdk.b.f.c.a().b(this);
        com.huawei.updatesdk.b.d.c cVar = this.l;
        if (cVar != null) {
            cVar.b();
        }
        com.huawei.updatesdk.b.d.f.a((com.huawei.updatesdk.service.otaupdate.b) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        com.huawei.updatesdk.b.i.a a2 = com.huawei.updatesdk.b.i.a.a(this, null, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_third_app_dl_cancel_download_prompt_ex")));
        this.c = a2;
        a2.a(new h());
        String string = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_third_app_dl_sure_cancel_download"));
        this.c.a(new i());
        this.c.a(-1, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.huawei.updatesdk.service.appmgr.bean.c cVar) {
        c();
        b(cVar.a());
        com.huawei.updatesdk.b.d.b bVar = new com.huawei.updatesdk.b.d.b(cVar.b(), cVar.c(), cVar.e());
        bVar.a(cVar.d());
        com.huawei.updatesdk.b.d.c cVar2 = new com.huawei.updatesdk.b.d.c(bVar);
        this.l = cVar2;
        cVar2.executeOnExecutor(com.huawei.updatesdk.b.g.c.f10843a, new Void[0]);
    }

    private void d() {
        com.huawei.updatesdk.b.i.a aVar = this.d;
        if (aVar != null) {
            e eVar = null;
            aVar.a(new o(eVar));
            this.d.a(new p(eVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.huawei.updatesdk.service.appmgr.bean.c cVar) {
        com.huawei.updatesdk.b.i.a a2 = com.huawei.updatesdk.b.i.a.a(this, null, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_mobile_dld_warn"), new Object[]{com.huawei.updatesdk.b.h.d.a(this, cVar.c())}));
        a2.a(new c(cVar, a2));
        String string = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_app_download_info_new"));
        a2.a(new d());
        a2.a(-1, string);
        a2.a(new r(this, a2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ApkUpgradeInfo apkUpgradeInfo) {
        com.huawei.updatesdk.a.a.a.b("AppUpdateActivity", "showOtaDialog");
        if (apkUpgradeInfo == null) {
            finish();
            return;
        }
        String string = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_ota_title"));
        String string2 = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_ota_notify_updatebtn"));
        String string3 = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_ota_cancel"));
        if (!a(string)) {
            com.huawei.updatesdk.a.a.a.a("AppUpdateActivity", "UpdateSDK show update dialog error and resource is proguard, please add ignore proguard rules!");
            finish();
            return;
        }
        if (1 == apkUpgradeInfo.getIsCompulsoryUpdate_()) {
            string3 = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_ota_force_cancel_new"));
            if (this.k) {
                this.d.c();
            }
            this.g = true;
        }
        this.d.a(new m());
        d();
        this.d.a(new a());
        if (this.g) {
            this.d.a(false);
        } else {
            this.d.a(new b());
        }
        this.d.a(-1, string2);
        this.d.a(-2, string3);
    }

    private void c(com.huawei.updatesdk.a.b.b.b bVar) {
        new Handler(Looper.getMainLooper()).post(new g(bVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        if (i2 == 5 || i2 == 4) {
            Toast.makeText(this, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_third_app_dl_install_failed")), 0).show();
            com.huawei.updatesdk.b.h.b.a();
            finish();
        }
        if (i2 == 7) {
            com.huawei.updatesdk.b.h.b.a();
            if (this.g) {
                c(this.i);
            } else {
                finish();
            }
        }
    }

    private void c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        q qVar = new q(this, null);
        this.p = qVar;
        com.huawei.updatesdk.service.otaupdate.a.a(this, intentFilter, qVar);
    }

    private void b(String str) {
        AlertDialog alertDialog = this.b;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.b = (com.huawei.updatesdk.a.a.d.i.c.l() ? new AlertDialog.Builder(this) : ((getResources().getConfiguration().uiMode & 48) != 32 || Build.VERSION.SDK_INT < 29) ? new AlertDialog.Builder(this, R.style.Theme.DeviceDefault.Light.Dialog.Alert) : new AlertDialog.Builder(this, R.style.Theme.DeviceDefault.Dialog.Alert)).create();
            com.huawei.updatesdk.a.a.d.i.c.f().a(this.b.getWindow());
            View inflate = LayoutInflater.from(this).inflate(com.huawei.updatesdk.b.h.c.b(this, "upsdk_app_dl_progress_dialog"), (ViewGroup) null);
            ((TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "third_app_warn_text"))).setText(getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_app_download_installing"), new Object[]{str}));
            ProgressBar progressBar = (ProgressBar) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "third_app_dl_progressbar"));
            this.e = progressBar;
            progressBar.setMax(100);
            this.f = (TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "third_app_dl_progress_text"));
            inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "cancel_bg")).setOnClickListener(new j());
            this.b.setView(inflate);
            this.b.setCancelable(false);
            this.b.setCanceledOnTouchOutside(false);
            if (!com.huawei.updatesdk.service.otaupdate.a.a(this)) {
                this.b.show();
            }
            this.f.setText(com.huawei.updatesdk.b.h.d.a(0));
        }
    }

    private void b(com.huawei.updatesdk.service.appmgr.bean.c cVar) {
        com.huawei.updatesdk.b.i.a a2 = com.huawei.updatesdk.b.i.a.a(this, null, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_appstore_install"), new Object[]{cVar.a()}));
        a2.a(new k(cVar, a2));
        String string = getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_app_download_info_new"));
        a2.a(new l());
        a2.a(-1, string);
        a2.a(new r(this, a2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ApkUpgradeInfo apkUpgradeInfo) {
        Intent intent = new Intent(UpdateConstants.ACTION_NAME_HIAPP_SILENT_DOWNLOAD);
        intent.setPackage(this.f10856a);
        intent.putExtra("APP_PACKAGENAME", apkUpgradeInfo.getPackage_());
        intent.putExtra("devType", apkUpgradeInfo.getDevType_());
        intent.putExtra("version", apkUpgradeInfo.getVersion_());
        intent.putExtra("longSize", apkUpgradeInfo.getLongSize_());
        intent.putExtra("newFeature", apkUpgradeInfo.getNewFeatures_());
        intent.putExtra("IsCompulsoryUpdate", apkUpgradeInfo.getIsCompulsoryUpdate_());
        intent.putExtra("APP_MUST_UPDATE_BTN", this.k);
        intent.putExtra("VersionCode", apkUpgradeInfo.getVersionCode_());
        intent.putExtra("detailId", apkUpgradeInfo.getDetailId_());
        intent.putExtra("name", apkUpgradeInfo.getName_());
        try {
            this.h = false;
            startActivityForResult(intent, 1002);
        } catch (ActivityNotFoundException unused) {
            com.huawei.updatesdk.a.a.a.a("AppUpdateActivity", "goHiappUpgrade error: ActivityNotFoundException");
            this.h = true;
            Intent intent2 = new Intent();
            intent2.putExtra("status", 8);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent2);
            c(apkUpgradeInfo);
        }
    }

    private void b(com.huawei.updatesdk.a.b.b.b bVar) {
        if (this.e == null) {
            return;
        }
        this.e.setProgress(com.huawei.updatesdk.b.h.d.a(bVar.b("download_apk_already", 0), bVar.b("download_apk_size", 0)));
        this.f.setText(com.huawei.updatesdk.b.h.d.a((int) ((this.e.getProgress() / this.e.getMax()) * 100.0f)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        runOnUiThread(new f(com.huawei.updatesdk.b.h.b.a(this.f10856a)));
    }

    private boolean a(String str) {
        TextView textView;
        View inflate = LayoutInflater.from(this).inflate(com.huawei.updatesdk.b.h.c.b(this, "upsdk_ota_update_view"), (ViewGroup) null);
        String string = TextUtils.isEmpty(this.i.getNewFeatures_()) ? getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_choice_update")) : this.i.getNewFeatures_();
        if (inflate == null || (textView = (TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "content_textview"))) == null) {
            return false;
        }
        textView.setText(string);
        String version_ = this.i.getVersion_();
        TextView textView2 = (TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "version_textview"));
        if (textView2 == null) {
            return false;
        }
        textView2.setText(version_);
        com.huawei.updatesdk.b.h.d.a(textView2);
        String a2 = com.huawei.updatesdk.b.h.d.a(this, a(this.i));
        TextView textView3 = (TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "appsize_textview"));
        textView3.setText(a2);
        com.huawei.updatesdk.b.h.d.a(textView3);
        TextView textView4 = (TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "name_textview"));
        textView4.setText(this.i.getName_());
        com.huawei.updatesdk.b.h.d.a(textView4);
        TextView textView5 = (TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "allsize_textview"));
        a(this.i, textView5);
        com.huawei.updatesdk.b.h.d.a(textView5);
        ((TextView) inflate.findViewById(com.huawei.updatesdk.b.h.c.a(this, "download_install_textview"))).setVisibility(TextUtils.equals("CN", com.huawei.updatesdk.service.otaupdate.f.e().a()) ? 0 : 8);
        a(inflate);
        com.huawei.updatesdk.b.i.a a3 = com.huawei.updatesdk.b.i.a.a(this, str, null);
        this.d = a3;
        a3.a(inflate);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.m = 1;
            finish();
            return;
        }
        Intent intent = new Intent("com.huawei.appmarket.appmarket.intent.action.AppDetail.withdetailId");
        intent.setPackage(this.f10856a);
        intent.putExtra("appDetailId", str2);
        intent.putExtra("thirdId", str);
        intent.addFlags(268468224);
        try {
            startActivity(intent);
            if (this.j) {
                return;
            }
            finish();
        } catch (Exception e2) {
            com.huawei.updatesdk.a.a.a.a("AppUpdateActivity", "openMarketUpdateDetail error: " + e2.getMessage());
            com.huawei.updatesdk.b.d.f.a(this);
            com.huawei.updatesdk.b.d.f.a(this.q);
            Intent intent2 = new Intent();
            intent2.putExtra("status", 8);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent2);
            com.huawei.updatesdk.b.i.a aVar = this.d;
            if (aVar != null) {
                aVar.a();
            }
        }
    }

    private void a(ApkUpgradeInfo apkUpgradeInfo, TextView textView) {
        if (textView == null) {
            return;
        }
        if (apkUpgradeInfo.getDiffSize_() <= 0) {
            textView.setVisibility(8);
            return;
        }
        String a2 = com.huawei.updatesdk.b.h.d.a(this, (apkUpgradeInfo.getPackingType_() != 3 || apkUpgradeInfo.getObbSize_() <= 0) ? apkUpgradeInfo.getLongSize_() : apkUpgradeInfo.getLongSize_() + apkUpgradeInfo.getObbSize_());
        SpannableString spannableString = new SpannableString(a2);
        spannableString.setSpan(new StrikethroughSpan(), 0, a2.length(), 33);
        spannableString.setSpan(new TextAppearanceSpan(com.huawei.updatesdk.b.h.a.f().b() > 0 ? "HnChinese-medium" : Constants.FONT, 0, (int) textView.getTextSize(), null, null), 0, spannableString.length(), 33);
        textView.setText(spannableString);
    }

    private void a(com.huawei.updatesdk.a.b.b.b bVar) {
        Bundle b2 = bVar.b();
        if (b2 != null) {
            int i2 = b2.getInt("download_status_param", -1);
            com.huawei.updatesdk.service.otaupdate.d.a().a(a(-1, -1, i2));
            if (com.huawei.updatesdk.b.d.a.a(i2)) {
                return;
            }
            a();
            if (com.huawei.updatesdk.b.d.a.b(i2)) {
                Toast.makeText(this, getString(com.huawei.updatesdk.b.h.c.c(this, "upsdk_third_app_dl_install_failed")), 0).show();
                finish();
            }
        }
    }

    private void a(View view) {
        if (com.huawei.updatesdk.a.a.d.i.c.l()) {
            try {
                ScrollView scrollView = (ScrollView) view.findViewById(com.huawei.updatesdk.b.h.c.a(this, "scroll_layout"));
                TypedValue typedValue = new TypedValue();
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getTheme().resolveAttribute(R.attr.dialogPreferredPadding, typedValue, true);
                ((WindowManager) getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getMetrics(displayMetrics);
                int complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue.data, displayMetrics);
                scrollView.setPadding(complexToDimensionPixelSize, 0, complexToDimensionPixelSize, 0);
            } catch (Exception e2) {
                com.huawei.updatesdk.a.a.c.a.a.a.b("AppUpdateActivity", e2.toString());
            }
        }
    }

    private void a(Intent intent) {
        try {
            com.huawei.updatesdk.a.b.b.b a2 = com.huawei.updatesdk.a.b.b.b.a(intent);
            this.n = a2.a("installResultCode", -99);
            this.g = a2.a(UpdateKey.MUST_UPDATE, false);
        } catch (Throwable th) {
            this.n = -99;
            this.g = false;
            com.huawei.updatesdk.a.a.a.a("AppUpdateActivity", "get result error, e: " + th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            AlertDialog alertDialog = this.b;
            if (alertDialog == null || !alertDialog.isShowing()) {
                return;
            }
            this.b.dismiss();
            this.b = null;
        } catch (IllegalArgumentException unused) {
            com.huawei.updatesdk.a.a.c.a.a.a.a("AppUpdateActivity", "progressDialog dismiss IllegalArgumentException");
        }
    }

    class c implements com.huawei.updatesdk.b.i.b {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.updatesdk.service.appmgr.bean.c f10859a;
        final /* synthetic */ com.huawei.updatesdk.b.i.a b;

        @Override // com.huawei.updatesdk.b.i.b
        public void b() {
            this.b.a();
            if (AppUpdateActivity.this.j) {
                AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
                appUpdateActivity.c(appUpdateActivity.i);
            } else {
                AppUpdateActivity.this.m = 4;
                AppUpdateActivity.this.finish();
            }
        }

        @Override // com.huawei.updatesdk.b.i.b
        public void a() {
            AppUpdateActivity.this.d(this.f10859a);
            this.b.a();
        }

        c(com.huawei.updatesdk.service.appmgr.bean.c cVar, com.huawei.updatesdk.b.i.a aVar) {
            this.f10859a = cVar;
            this.b = aVar;
        }
    }

    class h implements com.huawei.updatesdk.b.i.b {
        @Override // com.huawei.updatesdk.b.i.b
        public void b() {
            AppUpdateActivity.this.c.a();
        }

        @Override // com.huawei.updatesdk.b.i.b
        public void a() {
            AppUpdateActivity.this.e.setProgress(0);
            AppUpdateActivity.this.e.setMax(0);
            AppUpdateActivity.this.f.setText("");
            AppUpdateActivity.this.a();
            if (AppUpdateActivity.this.l != null) {
                AppUpdateActivity.this.l.a();
            }
            AppUpdateActivity.this.c.a();
            if (AppUpdateActivity.this.j) {
                AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
                appUpdateActivity.c(appUpdateActivity.i);
            } else {
                AppUpdateActivity.this.m = 4;
                AppUpdateActivity.this.finish();
            }
        }

        h() {
        }
    }

    class k implements com.huawei.updatesdk.b.i.b {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.updatesdk.service.appmgr.bean.c f10867a;
        final /* synthetic */ com.huawei.updatesdk.b.i.a b;

        @Override // com.huawei.updatesdk.b.i.b
        public void b() {
            this.b.a();
            if (AppUpdateActivity.this.j) {
                AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
                appUpdateActivity.c(appUpdateActivity.i);
            } else {
                AppUpdateActivity.this.m = 4;
                AppUpdateActivity.this.finish();
            }
        }

        @Override // com.huawei.updatesdk.b.i.b
        public void a() {
            if (!com.huawei.updatesdk.a.a.d.j.a.d(AppUpdateActivity.this)) {
                AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
                Toast.makeText(appUpdateActivity, com.huawei.updatesdk.b.h.c.c(appUpdateActivity, "upsdk_no_available_network_prompt_toast"), 0).show();
                AppUpdateActivity.this.finish();
            } else if (com.huawei.updatesdk.a.a.d.j.a.c(AppUpdateActivity.this) == 1) {
                AppUpdateActivity.this.d(this.f10867a);
                this.b.a();
            } else {
                AppUpdateActivity.this.c(this.f10867a);
            }
        }

        k(com.huawei.updatesdk.service.appmgr.bean.c cVar, com.huawei.updatesdk.b.i.a aVar) {
            this.f10867a = cVar;
            this.b = aVar;
        }
    }

    class m implements com.huawei.updatesdk.b.i.b {
        @Override // com.huawei.updatesdk.b.i.b
        public void b() {
            com.huawei.updatesdk.a.a.a.b("AppUpdateActivity", "performCancel");
            AppUpdateActivity.this.d.a();
            AppUpdateActivity.this.m = 4;
            AppUpdateActivity.this.o = 100;
            if (AppUpdateActivity.this.j) {
                AppUpdateActivity.this.finish();
                return;
            }
            com.huawei.updatesdk.service.otaupdate.c cVar = new com.huawei.updatesdk.service.otaupdate.c();
            AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
            cVar.a(appUpdateActivity, appUpdateActivity.f10856a, AppUpdateActivity.this.new n(), AppUpdateActivity.this.q);
        }

        @Override // com.huawei.updatesdk.b.i.b
        public void a() {
            com.huawei.updatesdk.a.a.a.b("AppUpdateActivity", "performConfirm");
            AppUpdateActivity.this.o = 101;
            if (com.huawei.updatesdk.a.a.d.j.a.d(AppUpdateActivity.this)) {
                AppUpdateActivity.this.g();
                return;
            }
            AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
            Toast.makeText(appUpdateActivity, com.huawei.updatesdk.b.h.c.c(appUpdateActivity, "upsdk_no_available_network_prompt_toast"), 0).show();
            AppUpdateActivity.this.m = 2;
            AppUpdateActivity.this.finish();
        }

        m() {
        }
    }

    static class o implements DialogInterface.OnDismissListener {
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            Intent intent = new Intent();
            intent.putExtra(UpdateKey.DIALOG_STATUS, 10001);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent);
        }

        /* synthetic */ o(e eVar) {
            this();
        }

        private o() {
        }
    }

    static class p implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public void onShow(DialogInterface dialogInterface) {
            Intent intent = new Intent();
            intent.putExtra(UpdateKey.DIALOG_STATUS, 10002);
            com.huawei.updatesdk.service.otaupdate.d.a().b(intent);
        }

        /* synthetic */ p(e eVar) {
            this();
        }

        private p() {
        }
    }

    class q extends com.huawei.updatesdk.a.b.b.a {
        @Override // com.huawei.updatesdk.a.b.b.a
        public void a(Context context, com.huawei.updatesdk.a.b.b.b bVar) {
            if (bVar.d()) {
                if (AppUpdateActivity.this.c != null) {
                    AppUpdateActivity.this.c.a();
                }
                AppUpdateActivity.this.a();
                String a2 = bVar.a();
                String dataString = bVar.c().getDataString();
                if (dataString == null || dataString.length() < 9) {
                    return;
                }
                String substring = dataString.substring(8);
                if ("android.intent.action.PACKAGE_ADDED".equals(a2) && TextUtils.equals(AppUpdateActivity.this.f10856a, substring)) {
                    com.huawei.updatesdk.service.otaupdate.d.a().a(AppUpdateActivity.this.a(6, 0, -1));
                    com.huawei.updatesdk.b.h.b.a();
                    AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
                    appUpdateActivity.a(appUpdateActivity.i.getPackage_(), AppUpdateActivity.this.i.getDetailId_());
                    if (AppUpdateActivity.this.j) {
                        AppUpdateActivity appUpdateActivity2 = AppUpdateActivity.this;
                        appUpdateActivity2.c(appUpdateActivity2.i);
                    }
                }
            }
        }

        /* synthetic */ q(AppUpdateActivity appUpdateActivity, e eVar) {
            this();
        }

        private q() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Intent a(int i2, int i3, int i4) {
        Intent intent = new Intent();
        intent.putExtra(UpdateKey.MARKET_DLD_STATUS, i4);
        intent.putExtra(UpdateKey.MARKET_INSTALL_STATE, i2);
        intent.putExtra(UpdateKey.MARKET_INSTALL_TYPE, i3);
        return intent;
    }

    class a implements a.e {
        @Override // com.huawei.updatesdk.b.i.a.e
        public void a() {
            AppUpdateActivity.this.finish();
        }

        a() {
        }
    }

    class b implements DialogInterface.OnKeyListener {
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            com.huawei.updatesdk.a.a.a.b("AppUpdateActivity", "onKeyBack");
            if (i != 4 || keyEvent.getAction() != 0) {
                return false;
            }
            AppUpdateActivity.this.m = 4;
            AppUpdateActivity.this.finish();
            return true;
        }

        b() {
        }
    }

    class d implements a.e {
        @Override // com.huawei.updatesdk.b.i.a.e
        public void a() {
            AppUpdateActivity.this.finish();
        }

        d() {
        }
    }

    class e implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            AppUpdateActivity.this.b();
        }

        e() {
        }
    }

    class f implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f10862a;

        @Override // java.lang.Runnable
        public void run() {
            if (!this.f10862a) {
                AppUpdateActivity appUpdateActivity = AppUpdateActivity.this;
                appUpdateActivity.c(appUpdateActivity.i);
            } else {
                AppUpdateActivity appUpdateActivity2 = AppUpdateActivity.this;
                appUpdateActivity2.b(appUpdateActivity2.i);
            }
        }

        f(boolean z) {
            this.f10862a = z;
        }
    }

    class g implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.huawei.updatesdk.a.b.b.b f10863a;

        @Override // java.lang.Runnable
        public void run() {
            Bundle b = this.f10863a.b();
            if (b == null) {
                return;
            }
            int i = b.getInt("INSTALL_STATE");
            com.huawei.updatesdk.service.otaupdate.d.a().a(AppUpdateActivity.this.a(i, b.getInt("INSTALL_TYPE"), -1));
            AppUpdateActivity.this.c(i);
        }

        g(com.huawei.updatesdk.a.b.b.b bVar) {
            this.f10863a = bVar;
        }
    }

    class i implements a.e {
        @Override // com.huawei.updatesdk.b.i.a.e
        public void a() {
            AppUpdateActivity.this.finish();
        }

        i() {
        }
    }

    class j implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppUpdateActivity.this.e();
        }

        j() {
        }
    }

    class l implements a.e {
        @Override // com.huawei.updatesdk.b.i.a.e
        public void a() {
            AppUpdateActivity.this.finish();
        }

        l() {
        }
    }

    public class n implements c.a {
        @Override // com.huawei.updatesdk.service.otaupdate.c.a
        public void a(Boolean bool) {
            if (bool.booleanValue()) {
                try {
                    Intent intent = new Intent("com.huawei.appmarket.intent.action.ThirdUpdateRemindAction");
                    intent.setPackage(AppUpdateActivity.this.f10856a);
                    AppUpdateActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    com.huawei.updatesdk.a.a.c.a.a.a.b("AppUpdateActivity", "goHiappUpgrade error: " + e.getMessage());
                }
            }
            AppUpdateActivity.this.finish();
        }

        public n() {
        }
    }

    static class r implements DialogInterface.OnKeyListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<AppUpdateActivity> f10872a;
        private final com.huawei.updatesdk.b.i.a b;

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            AppUpdateActivity appUpdateActivity = this.f10872a.get();
            if (appUpdateActivity == null || i != 4 || keyEvent.getAction() != 0) {
                return false;
            }
            com.huawei.updatesdk.a.a.a.b("AppUpdateActivity", "press back");
            if (appUpdateActivity.j) {
                this.b.a();
                appUpdateActivity.c(appUpdateActivity.i);
                return true;
            }
            appUpdateActivity.m = 4;
            appUpdateActivity.finish();
            return true;
        }

        public r(AppUpdateActivity appUpdateActivity, com.huawei.updatesdk.b.i.a aVar) {
            this.f10872a = new WeakReference<>(appUpdateActivity);
            this.b = aVar;
        }
    }

    private long a(ApkUpgradeInfo apkUpgradeInfo) {
        long longSize_ = apkUpgradeInfo.getLongSize_();
        if (apkUpgradeInfo.getPackingType_() == 1 && apkUpgradeInfo.getBundleSize_() > 0) {
            return apkUpgradeInfo.getBundleSize_();
        }
        if (apkUpgradeInfo.getPackingType_() == 3 && apkUpgradeInfo.getObbSize_() > 0) {
            longSize_ += apkUpgradeInfo.getObbSize_();
        }
        if (apkUpgradeInfo.getDiffSize_() <= 0) {
            return longSize_;
        }
        long diffSize_ = apkUpgradeInfo.getDiffSize_();
        return (apkUpgradeInfo.getPackingType_() != 3 || apkUpgradeInfo.getObbSize_() <= 0) ? diffSize_ : diffSize_ + apkUpgradeInfo.getObbSize_();
    }
}
