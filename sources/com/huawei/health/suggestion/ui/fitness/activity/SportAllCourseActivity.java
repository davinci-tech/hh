package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.ui.fitness.activity.SportAllCourseActivity;
import com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericCustomer;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.fsn;
import defpackage.fta;
import defpackage.gge;
import defpackage.ggr;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;

/* loaded from: classes.dex */
public class SportAllCourseActivity extends PolymericActivity {
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getIntent() != null && getIntent().getBooleanExtra("moveTaskToBack", false)) {
            moveTaskToBack(true);
        }
        super.onBackPressed();
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity
    public PolymericCustomer createPolymericCustomer(Intent intent) {
        this.mPageType = "";
        if (intent != null) {
            this.mPageType = intent.getStringExtra("SKIP_ALL_COURSE_KEY");
        }
        if ("HEALTH_COURSE".equals(this.mPageType)) {
            return new fta();
        }
        return new fsn();
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity
    public void initNormalViewController() {
        super.initNormalViewController();
        if (azK_() == null) {
            LogUtil.a("Suggestion_AllRecommendActivity", "initNormalViewController, rightSoftDrawable is null.");
            this.mTitleBar.setRightSoftkeyVisibility(8);
        } else {
            this.mTitleBar.setRightSoftkeyVisibility(0);
            this.mTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
            this.mTitleBar.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: flq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SportAllCourseActivity.this.azM_(view);
                }
            });
        }
    }

    public /* synthetic */ void azM_(View view) {
        if (nsn.o()) {
            LogUtil.h("Suggestion_AllRecommendActivity", "isFastClick");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            gge.e("1130034");
            e(TextUtils.equals(this.mPageType, "FITNESS_COURSE") ? "RUNNING_COURSE" : "FITNESS_COURSE");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private Drawable azK_() {
        Drawable drawable = null;
        if ("HEALTH_COURSE".equals(this.mPageType)) {
            return null;
        }
        if (getIntent() != null && getIntent().getBooleanExtra("KEY_SUG_COURSE_ADD_STATUS", false)) {
            return null;
        }
        if (TextUtils.equals(this.mPageType, "FITNESS_COURSE") && !nsn.ae(this.mContext) && !Utils.o()) {
            Drawable azJ_ = azJ_(R.drawable._2131431650_res_0x7f0b10e2);
            azL_(azJ_, R.string._2130848518_res_0x7f022b06);
            drawable = azJ_;
        }
        if (!TextUtils.equals(this.mPageType, "RUNNING_COURSE") || !SportSupportUtil.a() || Utils.o()) {
            return drawable;
        }
        Drawable azJ_2 = azJ_(R.drawable._2131431661_res_0x7f0b10ed);
        azL_(azJ_2, R.string._2130848519_res_0x7f022b07);
        return azJ_2;
    }

    private void azL_(Drawable drawable, int i) {
        if (this.mTitleBar == null) {
            ReleaseLogUtil.a("Suggestion_AllRecommendActivity", "setRightSoftkeyBackground mTitleBar is null");
        } else {
            this.mTitleBar.setRightSoftkeyBackground(drawable, nsf.h(i));
        }
    }

    private Drawable azJ_(int i) {
        if (LanguageUtil.bc(this.mContext)) {
            return nrz.cKn_(this.mContext, i);
        }
        return ContextCompat.getDrawable(this.mContext, i);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity
    public void initTitleBarSearchController() {
        if (this.mTitleBar == null) {
            LogUtil.h("Suggestion_AllRecommendActivity", "initTitleBarSearchController, failed with null mTitleBar.");
            return;
        }
        this.mTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: flr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportAllCourseActivity.this.azN_(view);
            }
        });
        Drawable rightButtonDrawable = getRightButtonDrawable();
        if (rightButtonDrawable == null) {
            LogUtil.a("Suggestion_AllRecommendActivity", "initTitleBarSearchController, rightButtonDrawable is null.");
            this.mTitleBar.setRightButtonVisibility(8);
        } else {
            this.mTitleBar.setRightButtonDrawable(rightButtonDrawable, nsf.h(R.string._2130847322_res_0x7f02265a));
            this.mTitleBar.setRightButtonVisibility(0);
            this.mTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: flt
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SportAllCourseActivity.this.azO_(view);
                }
            });
        }
    }

    public /* synthetic */ void azN_(View view) {
        if (getIntent() != null && getIntent().getBooleanExtra("moveTaskToBack", false)) {
            moveTaskToBack(true);
        }
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void azO_(View view) {
        rightButtonClick();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity
    public Drawable getRightButtonDrawable() {
        if ("HEALTH_COURSE".equals(this.mPageType)) {
            return null;
        }
        boolean o = Utils.o();
        if (o && TextUtils.equals(this.mPageType, "")) {
            return null;
        }
        if (this.mPolymericCustomer != null && this.mPolymericCustomer.acquireFragmentParam() != null && (this.mPolymericCustomer.acquireFragmentParam().get("KEY_SUG_COURSE_ADD_STATUS") instanceof Boolean) && ((Boolean) this.mPolymericCustomer.acquireFragmentParam().get("KEY_SUG_COURSE_ADD_STATUS")).booleanValue()) {
            return null;
        }
        if (o) {
            return azI_();
        }
        return azJ_(R.drawable._2131431370_res_0x7f0b0fca);
    }

    private Drawable azI_() {
        if (nsn.ae(this.mContext)) {
            return null;
        }
        if (getIntent() != null && getIntent().getBooleanExtra("KEY_SUG_COURSE_ADD_STATUS", false)) {
            return null;
        }
        if (TextUtils.equals(this.mPageType, "FITNESS_COURSE")) {
            return azJ_(R.drawable._2131431650_res_0x7f0b10e2);
        }
        if (SportSupportUtil.a()) {
            return azJ_(R.drawable._2131431661_res_0x7f0b10ed);
        }
        return null;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.activity.PolymericActivity
    public void rightButtonClick() {
        LogUtil.a("Suggestion_AllRecommendActivity", "rightButtonClick.");
        if (Utils.o() && TextUtils.equals(this.mPageType, "FITNESS_COURSE")) {
            gge.e("1130034");
            e("RUNNING_COURSE");
        } else if (Utils.o() && TextUtils.equals(this.mPageType, "RUNNING_COURSE") && SportSupportUtil.a()) {
            gge.e("1130035");
            e("FITNESS_COURSE");
        } else {
            super.rightButtonClick();
        }
    }

    private void e(String str) {
        int i;
        if ("RUNNING_COURSE".equals(str)) {
            gge.e("1130034");
            i = 6;
        } else {
            gge.e("1130035");
            i = 2;
        }
        int c = gge.c(str);
        Intent intent = new Intent(this, (Class<?>) SportAllCourseActivity.class);
        intent.putExtra("SKIP_ALL_COURSE_KEY", str);
        startActivity(intent);
        ggr.c(c, i);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
