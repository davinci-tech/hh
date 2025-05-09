package com.huawei.ui.thirdpartservice.activity.googlefit;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.nrh;
import defpackage.shu;

/* loaded from: classes8.dex */
public class GoogleFitConnectActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f10542a;
    private GoogleApiClient d;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.c("GoogleFitConnectActivity", "onCreate");
        this.f10542a = this;
        e();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.c("GoogleFitConnectActivity", "onResume");
        GoogleApiClient googleApiClient = this.d;
        if (googleApiClient != null) {
            LogUtil.c("GoogleFitConnectActivity", "mGoogleApiClient.isConnected()", Boolean.valueOf(googleApiClient.isConnected()), " isConnecting()", Boolean.valueOf(this.d.isConnecting()));
            if (this.d.isConnecting() || this.d.isConnected()) {
                return;
            }
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        LogUtil.c("GoogleFitConnectActivity", "onStart");
        GoogleApiClient googleApiClient = this.d;
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtil.c("GoogleFitConnectActivity", "onStop");
        GoogleApiClient googleApiClient = this.d;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("GoogleFitConnectActivity", "nDestroy");
    }

    private void e() {
        LogUtil.c("GoogleFitConnectActivity", "connectGoogleFit");
        if (this.d == null) {
            LogUtil.c("GoogleFitConnectActivity", "mGoogleApiClient=null");
            GoogleApiClient build = new GoogleApiClient.Builder(this).addApi(Fitness.HISTORY_API).addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE)).addScope(new Scope(Scopes.FITNESS_BODY_READ_WRITE)).addScope(new Scope(Scopes.FITNESS_LOCATION_READ_WRITE)).addScope(new Scope(Scopes.CLOUD_SAVE)).addConnectionCallbacks(this).enableAutoManage(this, 0, this).build();
            this.d = build;
            build.connect();
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        LogUtil.c("GoogleFitConnectActivity", "onConnected");
        shu.b().c(true);
        nrh.b(this.f10542a, R.string._2130841442_res_0x7f020f62);
        finish();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public void onConnectionSuspended(int i) {
        LogUtil.c("GoogleFitConnectActivity", "onConnectionSuspended");
        shu.b().c(false);
        finish();
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        LogUtil.c("GoogleFitConnectActivity", "onConnectionFailed");
        shu.b().c(false);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
