package com.huawei.pluginachievement.ui.level;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.mfm;
import defpackage.nrz;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class AchieveLevelRuleActivity extends BaseActivity implements View.OnClickListener {
    private ImageView c;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achieve_level_rule);
        b();
    }

    private void b() {
        ImageView imageView = (ImageView) mfm.cgL_(this, R.id.achieve_level_rule_navigation_left_button);
        this.c = imageView;
        imageView.setOnClickListener(this);
        if (LanguageUtil.bc(this)) {
            this.c.setImageDrawable(nrz.cKn_(this, R.mipmap._2131820919_res_0x7f110177));
        } else {
            this.c.setImageDrawable(getResources().getDrawable(R.mipmap._2131820919_res_0x7f110177));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.achieve_level_rule_navigation_left_button == view.getId()) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
