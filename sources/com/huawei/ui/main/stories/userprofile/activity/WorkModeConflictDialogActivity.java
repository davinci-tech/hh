package com.huawei.ui.main.stories.userprofile.activity;

import android.content.Context;
import android.content.Intent;
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
import com.huawei.ui.main.R$string;

/* loaded from: classes7.dex */
public class WorkModeConflictDialogActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10530a;
    private String b;
    private HealthButton c;
    private HealthTextView d;
    private HealthTextView e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f10530a = this;
        LogUtil.a("WorkModeConflictDialogActivity", "onCreate()");
        setContentView(R.layout.activity_personal_center_dialog);
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        b();
        c();
    }

    private void c() {
        LogUtil.a("WorkModeConflictDialogActivity", "initUpdateMode()");
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("content");
            this.b = stringExtra;
            this.e.setText(stringExtra);
        }
    }

    private void b() {
        LogUtil.a("WorkModeConflictDialogActivity", "initView()");
        this.d = (HealthTextView) findViewById(R.id.dialog_title_activity);
        this.e = (HealthTextView) findViewById(R.id.dialog_content_activity);
        this.c = (HealthButton) findViewById(R.id.dialog_show_true_but);
        this.d.setText(this.f10530a.getResources().getString(R$string.IDS_hw_health_show_common_dialog_title));
        this.c.setText(this.f10530a.getResources().getString(R$string.IDS_user_permission_know));
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.userprofile.activity.WorkModeConflictDialogActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("WorkModeConflictDialogActivity", "onClick");
                WorkModeConflictDialogActivity.this.finish();
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
