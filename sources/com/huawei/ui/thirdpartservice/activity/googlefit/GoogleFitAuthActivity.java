package com.huawei.ui.thirdpartservice.activity.googlefit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrh;
import defpackage.shu;
import health.compact.a.CommonUtil;

/* loaded from: classes8.dex */
public class GoogleFitAuthActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10541a;
    private GoogleApiClient b;
    private Context c;
    private HealthButton d;
    private int e = 1;
    private Handler f = new Handler() { // from class: com.huawei.ui.thirdpartservice.activity.googlefit.GoogleFitAuthActivity.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 11) {
                return;
            }
            GoogleFitAuthActivity.this.e();
        }
    };
    private HealthTextView g;
    private boolean i;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.c("GoogleFitAuthActivity", "onCreate");
        setContentView(R.layout.activity_google_access);
        this.c = this;
        d();
    }

    private void d() {
        HealthButton healthButton = (HealthButton) findViewById(R.id.google_fit_open_button);
        this.d = healthButton;
        healthButton.setOnClickListener(this);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.google_fit_guide_describe);
        this.f10541a = healthTextView;
        healthTextView.setText(R.string._2130841933_res_0x7f02114d);
        this.g = (HealthTextView) findViewById(R.id.google_fit_guide_note);
        this.g.setText(String.format(getResources().getString(R.string._2130843008_res_0x7f021580), 1, 2));
        this.d.setText(R.string._2130843256_res_0x7f021678);
        this.d.setEnabled(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.c("GoogleFitAuthActivity", "onResume()");
        b();
        e();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        LogUtil.c("GoogleFitAuthActivity", "onClick");
        if (view.getId() == R.id.google_fit_open_button) {
            if (!CommonUtil.aa(this.c)) {
                nrh.b(this.c, R.string._2130841393_res_0x7f020f31);
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                if (!e(this.c)) {
                    LogUtil.c("GoogleFitAuthActivity", "no google service");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                c();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        boolean c = shu.b().c();
        this.i = c;
        LogUtil.c("GoogleFitAuthActivity", "initGoogleFitView() isConnect = ", Boolean.valueOf(c));
        if (this.i) {
            this.d.setText(R.string._2130841740_res_0x7f02108c);
            this.e = 2;
        } else {
            this.d.setText(R.string._2130843256_res_0x7f021678);
            this.e = 1;
        }
    }

    private void c() {
        int i = this.e;
        if (i == 1) {
            LogUtil.c("GoogleFitAuthActivity", "STATUS_NOT_CONNECTED");
            startActivity(new Intent(this.c, (Class<?>) GoogleFitConnectActivity.class));
        } else {
            if (i == 2) {
                shu.b().c(false);
                e();
                LogUtil.c("GoogleFitAuthActivity", "mGoogleApiClient.isConnected()", Boolean.valueOf(this.b.isConnected()));
                if (this.b.isConnected()) {
                    this.b.clearDefaultAccountAndReconnect();
                    return;
                }
                return;
            }
            LogUtil.c("GoogleFitAuthActivity", "pressClickButton mButtonStatus=", Integer.valueOf(i));
        }
    }

    private boolean e(Context context) {
        int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (GoogleApiAvailability.getInstance().isUserResolvableError(isGooglePlayServicesAvailable) && (context instanceof Activity)) {
            GoogleApiAvailability.getInstance().getErrorDialog((Activity) context, isGooglePlayServicesAvailable, 0).show();
        }
        LogUtil.a("GoogleFitAuthActivity", "error=", GoogleApiAvailability.getInstance().getErrorString(isGooglePlayServicesAvailable));
        return false;
    }

    private void b() {
        LogUtil.c("GoogleFitAuthActivity", "connectGoogleFit");
        GoogleApiClient build = new GoogleApiClient.Builder(this).addApi(Fitness.HISTORY_API).addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE)).addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE)).addScope(new Scope(Scopes.FITNESS_LOCATION_READ_WRITE)).addScope(new Scope(Scopes.CLOUD_SAVE)).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        this.b = build;
        build.connect();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LogUtil.c("GoogleFitAuthActivity", "onStart");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.c("GoogleFitAuthActivity", "onStop");
        this.b.disconnect();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("GoogleFitAuthActivity", "onDestroy");
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        LogUtil.a("GoogleFitAuthActivity", "Connected!!!");
        shu.b().c(true);
        this.e = 2;
        this.f.sendEmptyMessage(11);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        LogUtil.c("GoogleFitAuthActivity", "onConnectionSuspended");
        shu.b().c(false);
        this.e = 1;
        this.f.sendEmptyMessage(11);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        LogUtil.c("GoogleFitAuthActivity", "onConnectionFailed");
        shu.b().c(false);
        this.e = 1;
        this.f.sendEmptyMessage(11);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
