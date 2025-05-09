package com.huawei.phoneservice.feedback.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.util.ModuleConfigUtils;
import com.huawei.phoneservice.faq.base.util.SdkListener;
import com.huawei.phoneservice.faq.base.util.SdkListenerPoxy;
import com.huawei.phoneservice.faq.base.util.b;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.faq.base.util.j;
import com.huawei.phoneservice.faq.base.util.l;
import com.huawei.phoneservice.faq.base.util.s;
import com.huawei.phoneservice.feedback.utils.SdkFeedbackProblemManager;
import com.huawei.phoneservice.feedback.widget.FeedbackNoticeView;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemInfo;
import com.huawei.secure.android.common.intent.SafeBundle;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes5.dex */
public class FeedbackDisabledActivity extends FeedBaseActivity implements View.OnClickListener {
    private boolean b;
    private FeedbackNoticeView e;
    private int f;
    private SdkListenerPoxy h;
    private ProblemInfo i;
    private boolean l;
    private Bundle n;
    private boolean g = false;
    private boolean j = true;
    private boolean o = false;

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected int j() {
        return R.layout.feedback_sdk_activity_feedback_disabled;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.l) {
            i.e("FeedbackDisabledActivity", "ONKEYDOWN IS DEEPLINK RELEASE");
            j.c().release();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedBaseActivity, com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            j.c().setSdkListener(this.h.getSdkListener());
        }
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
        if (view.getId() == R.id.feedback_disabled_noticeView) {
            if (this.e.getFaqErrorCode() == FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR || this.e.getFaqErrorCode() == FaqConstants.FaqErrorCode.INTERNET_ERROR) {
                this.b = true;
            }
            l();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    public void onBackPressed(View view) {
        super.onBackPressed(view);
        if (this.l) {
            i.e("FeedbackDisabledActivity", "ONBACKPRESSED IS DEEPLINK RELEASE");
            j.c().release();
        }
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void n() {
        this.e = (FeedbackNoticeView) findViewById(R.id.feedback_disabled_noticeView);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void m() {
        this.e.setOnClickListener(this);
        this.h = new e(j.c().getSdkListener());
        j.c().setSdkListener(this.h);
    }

    @Override // com.huawei.phoneservice.feedback.ui.FeedbackAbstractBaseActivity
    protected void l() {
        setTitle(R.string._2130850845_res_0x7f02341d);
        SafeIntent safeIntent = new SafeIntent(getIntent());
        this.i = safeIntent.hasExtra("problem_info") ? (ProblemInfo) safeIntent.getParcelableExtra("problem_info") : null;
        this.f = safeIntent.getIntExtra("REQUEST_CODE", -1);
        this.n = safeIntent.getExtras();
        String string = new SafeBundle(this.n).getString("country");
        if (!l.e(string) && string != null) {
            this.o = !string.equals(j.c().getSdk("country"));
        }
        if (!b.b(this)) {
            this.e.c(FaqConstants.FaqErrorCode.INTERNET_ERROR);
            return;
        }
        this.l = "Y".equals(j.c().getSdk(FaqConstants.FAQ_IS_DEEPLICK));
        if (this.o) {
            a();
            return;
        }
        if (j.c().initIsDone()) {
            int sdkInitCode = j.e().getSdkInitCode();
            if (this.b) {
                if (sdkInitCode == 5) {
                    this.e.a(FeedbackNoticeView.c.PROGRESS);
                    j.e().getServiceUrl();
                } else if (sdkInitCode == 6) {
                    this.e.a(FeedbackNoticeView.c.PROGRESS);
                    j.e().getModuleList();
                }
                this.g = true;
            }
            if (this.g) {
                return;
            }
            e(sdkInitCode);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.j) {
            setResult(2);
        }
        super.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.e.c(FaqConstants.FaqErrorCode.DIFFERENT_SITE);
        this.e.setCallback(new d());
    }

    private void b() {
        if (!this.l) {
            SdkFeedbackProblemManager.getManager().gotoFeedback(this, this.i, this.f);
        } else if (this.o) {
            a();
        } else {
            SdkFeedbackProblemManager.getManager().gotoFeedbackByDeepLink(this, this.n);
        }
        this.j = false;
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        String string = getResources().getString(R.string._2130850890_res_0x7f02344a);
        if (i == -1) {
            this.e.a(FeedbackNoticeView.c.PROGRESS);
            return;
        }
        int i2 = R.drawable._2131430127_res_0x7f0b0aef;
        if (i != 0) {
            if (i != 2 && i != 3) {
                if (i == 4) {
                    string = getResources().getString(R.string._2130850885_res_0x7f023445);
                    i2 = R.drawable._2131430128_res_0x7f0b0af0;
                } else if (i == 5 || i == 6) {
                    this.e.c(FaqConstants.FaqErrorCode.CONNECT_SERVER_ERROR);
                }
                if (i != 5 || i == 6) {
                }
                this.e.c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR);
                this.e.e(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, i2);
                this.e.c(FaqConstants.FaqErrorCode.EMPTY_DATA_ERROR, getResources().getDimensionPixelOffset(R.dimen._2131362784_res_0x7f0a03e0));
                this.e.setShouldHideContactUsButton(true);
                this.e.getNoticeTextView().setText(string);
                return;
            }
        } else if (ModuleConfigUtils.newFeedbackEnabled()) {
            b();
            return;
        }
        string = getResources().getString(R.string._2130850890_res_0x7f02344a);
        if (i != 5) {
        }
    }

    class e extends SdkListenerPoxy {

        class d implements Runnable {

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f8285a;
            final /* synthetic */ String b;
            final /* synthetic */ int e;

            @Override // java.lang.Runnable
            public void run() {
                int i;
                i.e("FeedbackDisabledActivity", "result: " + this.f8285a + " code: " + this.e + " msg: " + this.b);
                if (this.f8285a != 0 || (i = this.e) != 0) {
                    if (FeedbackDisabledActivity.this.l && FeedbackDisabledActivity.this.o) {
                        FeedbackDisabledActivity.this.a();
                        return;
                    } else {
                        FeedbackDisabledActivity.this.e(this.e);
                        return;
                    }
                }
                FeedbackDisabledActivity.this.e(i);
            }

            d(int i, int i2, String str) {
                this.f8285a = i;
                this.e = i2;
                this.b = str;
            }
        }

        @Override // com.huawei.phoneservice.faq.base.util.SdkListenerPoxy
        public void onSdkInitImpl(int i, int i2, String str) {
            FeedbackDisabledActivity.this.runOnUiThread(new d(i, i2, str));
        }

        e(SdkListener sdkListener) {
            super(sdkListener);
        }
    }

    class d implements FeedbackNoticeView.b {
        @Override // com.huawei.phoneservice.feedback.widget.FeedbackNoticeView.b
        public void a() {
            if (FeedbackDisabledActivity.this.l) {
                i.e("FeedbackDisabledActivity", "ONBACKPRESSED IS DEEPLINK RELEASE");
                j.c().release();
            }
            FeedbackDisabledActivity.this.finish();
        }

        d() {
        }
    }
}
