package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity;
import defpackage.pqg;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes9.dex */
public class SleepInstructionPageActivity extends BaseCoreSleepActivity {
    private CharSequence p;
    private CharSequence[] q;
    private CharSequence[] r;
    private CharSequence s;
    private CharSequence t;
    private CharSequence u;
    private CharSequence v;
    private CharSequence[] x;
    private CharSequence y;

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_explain);
        super.onCreate(bundle);
        a();
        d();
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("SleepInstructionPageActivity", "getIntentData intent == null");
            return;
        }
        this.y = intent.getCharSequenceExtra("intent_sleep_title");
        this.p = intent.getCharSequenceExtra("intent_sleep_base_line");
        this.u = intent.getCharSequenceExtra("intent_sleep_item_value");
        this.v = intent.getCharSequenceExtra("intent_sleep_item_status");
        this.t = intent.getCharSequenceExtra("intent_sleep_item_instruction_title_one");
        this.r = intent.getCharSequenceArrayExtra("intent_sleep_item_instruction_content_one");
        this.s = intent.getCharSequenceExtra("intent_sleep_item_instruction_title_two");
        this.q = intent.getCharSequenceArrayExtra("intent_sleep_item_instruction_content_two");
        this.x = intent.getCharSequenceArrayExtra("intent_sleep_item_reference_content");
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        if (!TextUtils.isEmpty(this.y)) {
            customTitleBar.setTitleText((String) this.y);
        }
        b(this.h, this.p);
        b(this.l, this.u);
        b(this.j, this.v);
        b(this.e, this.t);
        d(this.b, this.r);
        b(this.f9836a, this.s);
        d(this.d, this.q);
        CharSequence[] charSequenceArr = this.x;
        if (charSequenceArr != null && charSequenceArr.length > 0) {
            b(this.c, pqg.e(this.x));
        } else {
            this.i.setVisibility(8);
            this.c.setVisibility(8);
        }
    }

    private void b(HealthTextView healthTextView, CharSequence charSequence) {
        if (healthTextView == null) {
            ReleaseLogUtil.e("R_Sleep_SleepInstructionPageActivity", "view is null");
        } else if (!TextUtils.isEmpty(charSequence)) {
            healthTextView.setText(charSequence);
        } else {
            healthTextView.setVisibility(8);
        }
    }

    private void d(HealthTextView healthTextView, CharSequence[] charSequenceArr) {
        if (healthTextView == null || charSequenceArr == null) {
            ReleaseLogUtil.e("R_Sleep_SleepInstructionPageActivity", "view is null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charSequenceArr.length; i++) {
            CharSequence charSequence = charSequenceArr[i];
            if (!TextUtils.isEmpty(charSequence)) {
                sb.append(charSequence);
                if (i != charSequenceArr.length - 1 && !"\n".equals(charSequence)) {
                    sb.append("\n");
                }
            }
        }
        b(healthTextView, sb);
    }

    public static void a(Context context, CharSequence[] charSequenceArr, CharSequence[] charSequenceArr2, CharSequence[][] charSequenceArr3) {
        if (context == null || charSequenceArr.length != 4 || charSequenceArr2.length != 2 || charSequenceArr3.length != 3) {
            ReleaseLogUtil.e("R_Sleep_SleepInstructionPageActivity", "context is ", context, "dataPart is ", charSequenceArr);
            return;
        }
        CharSequence charSequence = charSequenceArr2[0];
        CharSequence[] charSequenceArr4 = charSequenceArr3[0];
        CharSequence charSequence2 = charSequenceArr2[1];
        CharSequence[] charSequenceArr5 = charSequenceArr3[1];
        CharSequence[] charSequenceArr6 = charSequenceArr3[2];
        Intent intent = new Intent(context, (Class<?>) SleepInstructionPageActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_sleep_title", charSequenceArr[0]);
        intent.putExtra("intent_sleep_base_line", charSequenceArr[1]);
        intent.putExtra("intent_sleep_item_value", charSequenceArr[2]);
        intent.putExtra("intent_sleep_item_status", charSequenceArr[3]);
        intent.putExtra("intent_sleep_item_instruction_title_one", charSequence);
        intent.putExtra("intent_sleep_item_instruction_content_one", charSequenceArr4);
        intent.putExtra("intent_sleep_item_instruction_title_two", charSequence2);
        intent.putExtra("intent_sleep_item_instruction_content_two", charSequenceArr5);
        intent.putExtra("intent_sleep_item_reference_content", charSequenceArr6);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SleepInstructionPageActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
