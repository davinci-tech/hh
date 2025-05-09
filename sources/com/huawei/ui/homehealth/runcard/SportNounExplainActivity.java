package com.huawei.ui.homehealth.runcard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.homehealth.runcard.trackfragments.SportNounExplainFragment;
import com.huawei.ui.homehealth.runcard.trackfragments.SportNounExplainItemFragment;

/* loaded from: classes9.dex */
public class SportNounExplainActivity extends BaseActivity implements View.OnClickListener {
    private SportNounExplainFragment b;
    private SportNounExplainItemFragment c;
    private int d;
    private CustomTitleBar i;
    private int h = 0;
    private boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private Handler f9525a = new Handler();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelMarginAdaptation();
        LogUtil.a("SportNounExplainActivity", "onCreate");
        setContentView(R.layout.activity_sport_noun_explain);
        d();
        c();
    }

    private void c() {
        clearBackgroundDrawable();
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.text_show_sport_type);
        this.i = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color._2131296690_res_0x7f0901b2));
        a(this.h);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (this.h == 0) {
            SportNounExplainFragment sportNounExplainFragment = new SportNounExplainFragment();
            this.b = sportNounExplainFragment;
            beginTransaction.add(R.id.frag_sport_noun_explain_list, sportNounExplainFragment);
        } else {
            SportNounExplainItemFragment sportNounExplainItemFragment = new SportNounExplainItemFragment(this.h, this.d);
            this.c = sportNounExplainItemFragment;
            beginTransaction.add(R.id.frag_sport_noun_explain_list, sportNounExplainItemFragment);
        }
        beginTransaction.commit();
    }

    private void a(int i) {
        switch (i) {
            case 0:
                this.i.setTitleText(getResources().getString(R.string._2130842767_res_0x7f02148f));
                break;
            case 1:
                this.i.setTitleText(getResources().getString(R.string._2130841430_res_0x7f020f56));
                break;
            case 2:
                this.i.setTitleText(getResources().getString(R.string._2130842718_res_0x7f02145e));
                break;
            case 3:
                this.i.setTitleText(getResources().getString(R.string._2130842721_res_0x7f021461));
                break;
            case 4:
                this.i.setTitleText(getResources().getString(R.string._2130842719_res_0x7f02145f));
                break;
            case 5:
                this.i.setTitleText(getResources().getString(R.string._2130842720_res_0x7f021460));
                break;
            case 6:
                this.i.setTitleText(getResources().getString(R.string._2130843171_res_0x7f021623));
                break;
            case 7:
                this.i.setTitleText(getResources().getString(R.string._2130837874_res_0x7f020172));
                break;
            case 8:
                this.i.setTitleText(getResources().getString(R.string._2130843675_res_0x7f02181b));
                break;
            case 9:
                this.i.setTitleText(getResources().getString(R.string._2130847655_res_0x7f0227a7));
                break;
        }
    }

    private void d() {
        Intent intent = getIntent();
        if (intent != null) {
            this.h = intent.getIntExtra(BleConstants.SPORT_TYPE, 0);
            this.d = intent.getIntExtra("eteAlgoKey", 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.e) {
            LogUtil.a("SportNounExplainActivity", "don't click to fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.f9525a.postDelayed(new Runnable() { // from class: com.huawei.ui.homehealth.runcard.SportNounExplainActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    SportNounExplainActivity.this.e = false;
                }
            }, 500L);
            this.e = true;
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
