package com.huawei.phoneservice.feedback.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes5.dex */
public class FeedbackSubmitSuccessActivity extends FeedBaseActivity implements View.OnClickListener {
    private Button b;
    private TextView e;
    private String f;
    private boolean i = false;

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_submit_success;
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (s.cdx_(view)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.btn_submit == view.getId()) {
            Intent intent = new Intent(this, (Class<?>) FeedDetailsActivity.class);
            intent.putExtra("questionId", this.f);
            intent.putExtra("COME_FROM", this.i);
            startActivity(intent);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        this.b = (Button) findViewById(R.id.btn_submit);
        this.e = (TextView) findViewById(R.id.tv_sr_number);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.b.setOnClickListener(this);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        SafeIntent safeIntent = new SafeIntent(getIntent());
        boolean booleanExtra = safeIntent.getBooleanExtra("COME_FROM", false);
        this.i = booleanExtra;
        setTitle(booleanExtra ? R.string._2130850907_res_0x7f02345b : R.string._2130850845_res_0x7f02341d);
        String stringExtra = safeIntent.getStringExtra("problemId");
        this.f = stringExtra;
        this.e.setText(stringExtra);
        this.b.setVisibility(ModuleConfigUtils.feedbackHistoryEnabled() ? 0 : 8);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int[] k() {
        return new int[]{R.id.rl_submit};
    }
}
