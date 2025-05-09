package com.huawei.watchface.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.skinner.base.SkinBaseFragmentActivity;
import com.huawei.uikit.phone.hwtextview.widget.HwTextView;
import com.huawei.watchface.R$drawable;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.R$string;
import com.huawei.watchface.cm;
import com.huawei.watchface.dp;
import com.huawei.watchface.dw;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.ui.widget.HwToolbar;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import defpackage.mcf;

/* loaded from: classes9.dex */
public class PrivacyStatementActivity extends SkinBaseFragmentActivity {
    public static final String ACTION_WATCHFACE_SERVICE_DISABLE = "com.huawei.bone.action.WATCHFACE_SERVICE_DISABLE";
    private static final String AGREEMENT_KEY = "Agreement_key";
    private static final String FROM_WHERE = "from";
    private static final String PRIVACY_URL = "privacyurl";
    private static final String TAG = "PrivacyStatementActivity";
    private static final String TERMS_URL = "termsurl";
    private ImageView mCancleServiceImg;
    private RelativeLayout mCancleServiceLayout;
    private Context mContext;
    private ImageView mPrivacyStatementImg;
    private RelativeLayout mPrivacyStatementLayout;
    private ImageView mUserAgreementImg;
    private RelativeLayout mUserAgreementLayout;
    private String mWhereFrom = "";

    @Override // com.huawei.skinner.base.SkinBaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.watchface_activity_privacy_statement);
        this.mContext = this;
        Environment.setApplication(getApplication());
        if (getIntent() != null) {
            try {
                this.mWhereFrom = getIntent().getStringExtra("from");
            } catch (Exception unused) {
                HwLog.e(TAG, "getStringExtra FROM_WHERE Exception");
            }
            HwLog.i(TAG, "mWhereFrom = " + this.mWhereFrom);
        }
        initView();
    }

    private void initView() {
        initToolbar();
        this.mCancleServiceLayout = (RelativeLayout) dw.a(this, R$id.rl_cancel_service);
        this.mCancleServiceImg = (ImageView) dw.a(this, R$id.im_cancel_service_right);
        this.mUserAgreementLayout = (RelativeLayout) dw.a(this, R$id.rl_user_agreement);
        this.mUserAgreementImg = (ImageView) dw.a(this, R$id.im_user_agreement_right);
        this.mPrivacyStatementLayout = (RelativeLayout) dw.a(this, R$id.rl_privacy_statement);
        this.mPrivacyStatementImg = (ImageView) dw.a(this, R$id.im_privacy_statement_right);
        if (LanguageUtils.a(this.mContext)) {
            this.mCancleServiceImg.setImageResource(R$drawable.watchface_common_ui_arrow_left);
            this.mUserAgreementImg.setImageResource(R$drawable.watchface_common_ui_arrow_left);
            this.mPrivacyStatementImg.setImageResource(R$drawable.watchface_common_ui_arrow_left);
        } else {
            this.mCancleServiceImg.setImageResource(R$drawable.watchface_common_ui_arrow_right);
            this.mUserAgreementImg.setImageResource(R$drawable.watchface_common_ui_arrow_right);
            this.mPrivacyStatementImg.setImageResource(R$drawable.watchface_common_ui_arrow_right);
        }
        this.mCancleServiceLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PrivacyStatementActivity.this.showCancelDialog();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mUserAgreementLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(PrivacyStatementActivity.this.mContext, (Class<?>) WatchfaceUrlActivity.class);
                intent.putExtra(PrivacyStatementActivity.AGREEMENT_KEY, PrivacyStatementActivity.TERMS_URL);
                intent.addFlags(268435456);
                mcf.cfJ_(PrivacyStatementActivity.this.mContext, intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mPrivacyStatementLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyStatementActivity.this.m921xa763c96(view);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-huawei-watchface-mvp-ui-activity-PrivacyStatementActivity, reason: not valid java name */
    /* synthetic */ void m921xa763c96(View view) {
        Intent intent = new Intent(this.mContext, (Class<?>) WatchfaceUrlActivity.class);
        intent.putExtra(AGREEMENT_KEY, PRIVACY_URL);
        intent.addFlags(268435456);
        mcf.cfJ_(this.mContext, intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initToolbar() {
        HwLog.i(TAG, "initToolbar() enter.");
        HwTextView hwTextView = (HwTextView) ((HwToolbar) LayoutInflater.from(this).inflate(R$layout.watchface_activity_common_toolbar, (ViewGroup) null).findViewById(R$id.tl_common_toolbar)).findViewById(R$id.tv_common_toolbar_title);
        if (this.mContext != null) {
            hwTextView.setText(DensityUtil.getStringById(R$string.IDS_hw_watchface_secret_privacy_statement));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCancelDialog() {
        cm.a aVar = new cm.a(this.mContext);
        aVar.a(DensityUtil.getStringById(R$string.IDS_hw_watchface_secret_cancle_service)).b(DensityUtil.getStringById(R$string.IDS_hw_watchface_secret_cancle_service_dialog_content)).a(R$string.IDS_hw_watchface_secret_revoke, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyStatementActivity.this.m922x744b4801(view);
            }
        }).b(R$string.cancel, new View.OnClickListener() { // from class: com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        aVar.a().show();
    }

    /* renamed from: lambda$showCancelDialog$1$com-huawei-watchface-mvp-ui-activity-PrivacyStatementActivity, reason: not valid java name */
    /* synthetic */ void m922x744b4801(View view) {
        dp.a("watch_face_privacy_is_agree", false, "goal_steps_perference");
        Intent intent = new Intent();
        intent.setAction(ACTION_WATCHFACE_SERVICE_DISABLE);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
        if (localBroadcastManager != null) {
            localBroadcastManager.sendBroadcast(intent);
        }
        setResult(-1);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
