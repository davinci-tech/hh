package com.huawei.ui.main.stories.nps.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;

/* loaded from: classes7.dex */
public class NpsDialogActivity extends BaseActivity {
    private static final String TAG = "NpsDialogActivity";
    private DialogActivityUtils mDialogActivityUtils;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_nps_dialog);
        this.mDialogActivityUtils = DialogActivityUtils.getInstance();
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        window.setWindowAnimations(R.style.track_dialog_anim);
        initView();
    }

    private void initView() {
        ((HealthTextView) nsy.cMc_(this, R.id.nps_tv_dialog_title)).setText(this.mDialogActivityUtils.getTitle());
        LogUtil.c(TAG, "content = ", this.mDialogActivityUtils.getMessage());
        ((HealthTextView) nsy.cMc_(this, R.id.nps_tv_dialog_message)).setText(this.mDialogActivityUtils.getMessage());
        HealthButton healthButton = (HealthButton) nsy.cMc_(this, R.id.nps_btn_dialog_positive);
        healthButton.setText(this.mDialogActivityUtils.getPositionText());
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.NpsDialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NpsDialogActivity.this.finish();
                if (NpsDialogActivity.this.mDialogActivityUtils.getPositionListener() != null) {
                    NpsDialogActivity.this.mDialogActivityUtils.getPositionListener().onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HealthButton healthButton2 = (HealthButton) nsy.cMc_(this, R.id.nps_btn_dialog_negative);
        healthButton2.setText(this.mDialogActivityUtils.getNegativeText());
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.activity.NpsDialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NpsDialogActivity.this.finish();
                if (NpsDialogActivity.this.mDialogActivityUtils.getNegativeListener() != null) {
                    NpsDialogActivity.this.mDialogActivityUtils.getNegativeListener().onClick(view);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
