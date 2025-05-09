package com.huawei.ui.main.stories.history;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hms.kit.awareness.barrier.internal.d.c;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;

/* loaded from: classes.dex */
public class GradeEvaluationDescriptionActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_grade_evaluation_description);
        ((HealthTextView) findViewById(R.id.text_good)).setText(getString(R$string.IDS_grade_evaluation_score, new Object[]{25, 89}));
        ((HealthTextView) findViewById(R.id.text_great)).setText(getString(R$string.IDS_grade_evaluation_score, new Object[]{90, Integer.valueOf(a.A)}));
        ((HealthTextView) findViewById(R.id.text_perfect)).setText(getString(R$string.IDS_grade_evaluation_score, new Object[]{Integer.valueOf(a.C), Integer.valueOf(c.b)}));
        ((HealthTextView) findViewById(R.id.text_crazy)).setText(getString(R$string.IDS_grade_evaluation_score, new Object[]{400, 679}));
        ((HealthTextView) findViewById(R.id.text_amazing)).setText(getString(R$string.IDS_bp_max_scope, new Object[]{680}));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
