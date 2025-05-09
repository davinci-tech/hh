package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackLineChartActivityChartFrag;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.gww;
import health.compact.a.StorageParams;

/* loaded from: classes4.dex */
public class TrackLineChartActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private TrackLineChartActivityChartFrag f3671a = new TrackLineChartActivityChartFrag();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_track_fragment);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fragment_container, this.f3671a);
        beginTransaction.commit();
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        if (gwwVar.ab() || !b()) {
            return;
        }
        gwwVar.d(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        TrackLineChartActivityChartFrag trackLineChartActivityChartFrag = new TrackLineChartActivityChartFrag();
        this.f3671a = trackLineChartActivityChartFrag;
        beginTransaction.replace(R.id.fragment_container, trackLineChartActivityChartFrag);
        beginTransaction.commitAllowingStateLoss();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setRootViewBackgroundColor(View view) {
        super.setRootViewBackgroundColor(view);
        view.setBackgroundColor(ContextCompat.getColor(this, R.color._2131296690_res_0x7f0901b2));
    }

    private boolean b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Track_TrackLineChartActivity", "intent null,return");
            return true;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            LogUtil.h("Track_TrackLineChartActivity", "intent null,return");
            return true;
        }
        String str = (String) extras.getCharSequence("KEY_BASELINE");
        if (!TextUtils.isEmpty(str)) {
            return (str.equals("BASELINE_JUMP_TIME") || str.equals("BASELINE_JUMP_HEIGHT")) ? false : true;
        }
        LogUtil.h("Track_TrackLineChartActivity", "baseLine isEmpty,return");
        return true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("Track_TrackLineChartActivity", "onConfigurationChanged");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        TrackLineChartActivityChartFrag trackLineChartActivityChartFrag = this.f3671a;
        if (trackLineChartActivityChartFrag != null) {
            trackLineChartActivityChartFrag.a();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        finish();
    }
}
