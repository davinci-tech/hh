package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class SuggestActivity extends BaseCoreSleepActivity {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_suggest);
        super.onCreate(bundle);
        String string = getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, 1);
        String string2 = getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, 2);
        if (LanguageUtil.q(this)) {
            this.h.setText(string + " ");
            this.l.setText(string2 + " ");
        } else {
            this.h.setText(string);
            this.l.setText(string2);
        }
        String format = String.format(getResources().getString(R$string.IDS_details_sleep_explain_1), 1);
        String format2 = String.format(getResources().getString(R$string.IDS_details_sleep_explain_2), 2);
        String format3 = String.format(getResources().getString(R$string.IDS_details_sleep_explain_3), 3);
        this.j.setText(format);
        this.e.setText(format2);
        this.b.setText(format3);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
