package com.huawei.hms.iapfull;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

/* loaded from: classes4.dex */
public class n0 extends FragmentActivity {

    /* renamed from: a, reason: collision with root package name */
    private ProgressDialog f4729a;

    static void a(n0 n0Var, String str, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        ProgressDialog progressDialog;
        if (n0Var.isFinishing()) {
            return;
        }
        ProgressDialog progressDialog2 = n0Var.f4729a;
        if (progressDialog2 != null && progressDialog2.isShowing() && (progressDialog = n0Var.f4729a) != null) {
            progressDialog.setMessage(str);
            n0Var.f4729a.setCanceledOnTouchOutside(false);
            n0Var.f4729a.setCancelable(z);
        } else {
            ProgressDialog progressDialog3 = new ProgressDialog(n0Var);
            n0Var.f4729a = progressDialog3;
            progressDialog3.setMessage(str);
            n0Var.f4729a.setCanceledOnTouchOutside(false);
            n0Var.f4729a.setCancelable(z);
            n0Var.f4729a.show();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().setStatusBarColor(0);
        getWindow().getDecorView().setSystemUiVisibility(9216);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        if (a1.a(super.getIntent())) {
            finish();
        }
    }

    public void a(Fragment fragment, int i, boolean z) {
        if (isFinishing()) {
            Log.w("iap_full", "BaseActivity showFragment is finish");
            return;
        }
        try {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            y0.b("BaseActivity", "showFragment，isAdded： " + fragment.isAdded());
            if (fragment.isAdded()) {
                beginTransaction.show(fragment);
            } else {
                beginTransaction.add(i, fragment);
            }
            if (z) {
                beginTransaction.commitNowAllowingStateLoss();
            } else {
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (IllegalStateException unused) {
            Log.w("iap_full", "BaseActivity showFragment: IllegalStateException");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ProgressDialog progressDialog = this.f4729a;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.f4729a = null;
        }
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f4730a;

        @Override // java.lang.Runnable
        public void run() {
            n0.a(n0.this, this.f4730a, false, null);
        }

        a(String str) {
            this.f4730a = str;
        }
    }

    public void a(String str) {
        runOnUiThread(new a(str));
    }

    public void a() {
        ProgressDialog progressDialog;
        if (isFinishing() || (progressDialog = this.f4729a) == null) {
            return;
        }
        progressDialog.dismiss();
        this.f4729a = null;
    }
}
