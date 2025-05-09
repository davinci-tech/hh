package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.ruf;

/* loaded from: classes6.dex */
public class AthleticAbilityEmptyActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthSubHeader f10018a;
    private HealthTextView b;
    private HealthTextView c;
    private CustomTitleBar e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_athletic_ability_empty);
        d();
        c();
        a();
    }

    private void d() {
        getWindow().setBackgroundDrawable(null);
        this.e = (CustomTitleBar) nsy.cMc_(this, R.id.empty_title_bar);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.tv_no_record_content);
        this.e.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.b = (HealthTextView) nsy.cMc_(this, R.id.text1);
        HealthSubHeader healthSubHeader = (HealthSubHeader) nsy.cMc_(this, R.id.sub_header);
        this.f10018a = healthSubHeader;
        healthSubHeader.setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        this.f10018a.setHeadTitleMaxLine(3);
    }

    private void c() {
        b();
    }

    private void a() {
        this.e.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.AthleticAbilityEmptyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.a(500)) {
                    LogUtil.h("AthleticAbilityEmptyActivity", "click finish AthleticAbilityEmptyActivity is too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    AthleticAbilityEmptyActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("AthleticAbilityEmptyActivity", "setSubHeaderAndProduce intent is null");
            return;
        }
        String stringExtra = intent.getStringExtra("athletic_ability_empty_flag");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.h("AthleticAbilityEmptyActivity", "setSubHeaderAndProduce emptyFlag is null");
        } else {
            d(stringExtra);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str) {
        char c;
        String string;
        String string2;
        String string3;
        String string4;
        str.hashCode();
        switch (str.hashCode()) {
            case -2013544945:
                if (str.equals("running_no_record")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -302061104:
                if (str.equals("running_lactic_no_record")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 145468790:
                if (str.equals("vo_to_max_no_record")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 561024609:
                if (str.equals("state_no_record")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            string = getString(R$string.IDS_data_running_index_title_outside);
            ruf.d(this, this.e, "RUNNING_STATE_INDEX");
            string2 = getString(R$string.IDS_rq_no_record_guide_run);
            string3 = getString(R$string.IDS_data_what_is_running_index);
            string4 = getString(R$string.IDS_data_running_index_explain);
        } else if (c == 1) {
            string = getString(R$string.IDS_running_lactate_threshold);
            ruf.d(this, this.e, "LACTATE_THRESHOLD");
            string2 = getString(R$string.IDS_rq_no_record_guide_lactic);
            string3 = getString(R$string.IDS_running_lactate_threshold_q);
            string4 = getString(R$string.IDS_running_lactate_threshold_a);
        } else if (c == 2) {
            string = getString(R$string.IDS_hwh_health_vo2max);
            ruf.d(this, this.e, "VO2_MAX");
            string2 = getString(R$string.IDS_rq_no_record_guide);
            string3 = getString(R$string.IDS_hw_motiontrack_vo2max_explain);
            string4 = getString(R$string.IDS_hwh_health_vo2max_explain);
        } else if (c == 3) {
            string = getString(R$string.IDS_data_state_index_title);
            ruf.d(this, this.e, "TRAIN_CONDITION");
            string2 = getString(R$string.IDS_rq_no_record_guide_training);
            string3 = getString(R$string.IDS_data_what_is_train_condition);
            string4 = getString(R$string.IDS_data_train_condition_explain);
        } else {
            LogUtil.h("AthleticAbilityEmptyActivity", "setSubHeaderAndProduce default");
            string = "";
            string2 = "";
            string3 = string2;
            string4 = string3;
        }
        this.e.setTitleText(string);
        this.c.setText(string2);
        e(string3, string4);
    }

    private void e(String str, String str2) {
        this.f10018a.setHeadTitleText(str);
        this.b.setText(str2);
    }
}
