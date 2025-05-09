package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity;
import defpackage.pqg;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class SleepScoreTimesActivity extends BaseCoreSleepActivity {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_explain);
        super.onCreate(bundle);
        e();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void e() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("R_Sleep_SleepTimeActivity", "intent is null!");
            return;
        }
        char c = 65535;
        int intExtra = intent.getIntExtra("intent_score", -1);
        int intExtra2 = intent.getIntExtra("intent_status", -1);
        String stringExtra = intent.getStringExtra("intent_sleep_type");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.a("R_Sleep_SleepTimeActivity", "setData type ", stringExtra);
            return;
        }
        e(stringExtra);
        stringExtra.hashCode();
        switch (stringExtra.hashCode()) {
            case -1546853722:
                if (stringExtra.equals("TYPE_DEEP_SLEEP_CONTINUITY")) {
                    c = 0;
                    break;
                }
                break;
            case -490874802:
                if (stringExtra.equals("TYPE_WAKE_UP_REGULAR")) {
                    c = 1;
                    break;
                }
                break;
            case 841426407:
                if (stringExtra.equals("TYPE_FALL_SLEEPING_REGULAR")) {
                    c = 2;
                    break;
                }
                break;
            case 1029392504:
                if (stringExtra.equals("TYPE_WAKE_UP_TIMES")) {
                    c = 3;
                    break;
                }
                break;
            case 1745853349:
                if (stringExtra.equals("TYPE_BREATH_QUALITY")) {
                    c = 4;
                    break;
                }
                break;
            case 1912088818:
                if (stringExtra.equals("TYPE_FALL_GO_TO_BED_REGULAR")) {
                    c = 5;
                    break;
                }
                break;
        }
        if (c == 0) {
            d(70, 100, intExtra, intExtra2);
            c();
            return;
        }
        if (c != 1) {
            if (c != 2) {
                if (c == 3) {
                    a(0, 1, intExtra, intExtra2);
                    g();
                    return;
                } else if (c == 4) {
                    d(70, 100, intExtra, intExtra2);
                    d();
                    return;
                } else if (c != 5) {
                    LogUtil.a("SleepTimeActivity", "default type");
                    return;
                }
            }
            d(70, 100, intExtra, intExtra2);
            a();
            return;
        }
        d(70, 100, intExtra, intExtra2);
        b();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void e(String str) {
        char c;
        String string;
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        str.hashCode();
        switch (str.hashCode()) {
            case -1546853722:
                if (str.equals("TYPE_DEEP_SLEEP_CONTINUITY")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -490874802:
                if (str.equals("TYPE_WAKE_UP_REGULAR")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 841426407:
                if (str.equals("TYPE_FALL_SLEEPING_REGULAR")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1029392504:
                if (str.equals("TYPE_WAKE_UP_TIMES")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1745853349:
                if (str.equals("TYPE_BREATH_QUALITY")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1912088818:
                if (str.equals("TYPE_FALL_GO_TO_BED_REGULAR")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            string = getResources().getString(R$string.IDS_fitness_core_sleep_deep_sleep_continuity);
        } else if (c == 1) {
            string = getResources().getString(R$string.IDS_fitness_core_sleep_end_sleep_regularity);
        } else if (c == 2) {
            string = getResources().getString(R$string.IDS_fitness_core_sleep_start_sleep_regularity);
        } else {
            if (c == 3) {
                string = getResources().getString(R$string.IDS_details_sleep_sleep_awake_time);
            } else if (c == 4) {
                string = getResources().getString(R$string.IDS_fitness_core_sleep_rdi_score);
            } else if (c != 5) {
                string = "";
            } else {
                string = getResources().getString(R$string.IDS_manual_sleep_bedtime_regularity);
            }
            LogUtil.b("R_Sleep_SleepTimeActivity", "type error!");
        }
        customTitleBar.setTitleText(string);
    }

    private void d(int i, int i2, int i3, int i4) {
        this.h.setText(pqg.e(i, i2, 0));
        String e = UnitUtil.e(i3, 1, 0);
        SpannableString spannableString = new SpannableString(getResources().getQuantityString(R.plurals._2130903221_res_0x7f0300b5, i3, e));
        spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e)), 0, e.length(), 33);
        this.l.setVisibility(0);
        this.l.setText(spannableString);
        if (i3 == 0) {
            scn.d(this.j, 72);
        }
        scn.d(this.j, i4);
    }

    private void a(int i, int i2, int i3, int i4) {
        this.h.setText(pqg.e(i, i2, 1));
        String e = UnitUtil.e(i3, 1, 0);
        SpannableString spannableString = new SpannableString(getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, i3, e));
        spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e)), 0, e.length(), 33);
        this.l.setVisibility(0);
        this.l.setText(spannableString);
        if (i3 == 0) {
            scn.d(this.j, 72);
        }
        scn.d(this.j, i4);
    }

    private void c() {
        this.e.setText(getResources().getString(R$string.IDS_deep_sleep_continuity_explain_1, 1));
        this.b.setText(getResources().getString(R$string.IDS_deep_sleep_continuity_content_1));
        this.f9836a.setText(getResources().getString(R$string.IDS_deep_sleep_continuity_explain_2, 2));
        this.d.setText(getResources().getString(R$string.IDS_deep_sleep_continuity_content_2_no_harvard) + "\n" + getResources().getString(R$string.IDS_deep_sleep_continuity_content_3, 1) + "\n" + getResources().getString(R$string.IDS_deep_sleep_continuity_content_4, 2) + "\n" + getResources().getString(R$string.IDS_deep_sleep_continuity_content_5, 3) + "\n" + getResources().getString(R$string.IDS_deep_sleep_continuity_content_6, 4) + "\n" + getResources().getString(R$string.IDS_deep_sleep_continuity_content_7, 5) + "\n" + getResources().getString(R$string.IDS_deep_sleep_continuity_content_8, 6));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_5), getResources().getString(R$string.IDS_fitness_core_sleep_reference_6), getResources().getString(R$string.IDS_fitness_core_sleep_reference_4)}));
    }

    private void d() {
        this.e.setText(getResources().getString(R$string.IDS_breath_quality_explain_1, 1));
        this.b.setText(getResources().getString(R$string.IDS_breath_quality_content_1_new, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(10.0d, 1, 0), UnitUtil.e(80.0d, 1, 0), UnitUtil.e(30.0d, 1, 0), UnitUtil.e(60.0d, 1, 0)));
        this.f9836a.setText(getResources().getString(R$string.IDS_breath_quality_explain_2, 2));
        this.d.setText(getResources().getString(R$string.IDS_breath_quality_content_2_new, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(3.0d, 1, 0), UnitUtil.e(4.0d, 1, 0), UnitUtil.e(5.0d, 1, 0)));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_11), getResources().getString(R$string.IDS_fitness_core_sleep_reference_12)}));
    }

    private void b() {
        this.e.setText(getResources().getString(R$string.IDS_fitness_core_sleep_regular_explain_1_new, UnitUtil.e(1.0d, 1, 0)));
        this.b.setText(getResources().getString(R$string.IDS_fitness_core_sleep_regular_explain_2_new));
        h();
    }

    private void h() {
        this.f9836a.setText(getResources().getString(R$string.IDS_fitness_core_sleep_regular_schedule_explain_3_new, UnitUtil.e(2.0d, 1, 0)));
        this.d.setText(getResources().getString(R$string.IDS_fitness_core_sleep_regular_schedule_explain_4_new));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_2), getResources().getString(R$string.IDS_fitness_core_sleep_reference_5)}));
    }

    private void a() {
        this.e.setText(getResources().getString(R$string.IDS_fitness_core_sleep_regular_schedule_explain_1_new, UnitUtil.e(1.0d, 1, 0)));
        this.b.setText(getResources().getString(R$string.IDS_fitness_core_sleep_regular_schedule_explain_2_new));
        h();
    }

    private void g() {
        this.e.setText(getResources().getString(R$string.IDS_wake_times_explain_1, 1));
        String quantityString = getResources().getQuantityString(R.plurals._2130903078_res_0x7f030026, 2, 2);
        String e = UnitUtil.e(10.0d, 2, 0);
        if (LanguageUtil.bp(BaseApplication.getContext()) || LanguageUtil.y(BaseApplication.getContext())) {
            StringBuilder sb = new StringBuilder(UnitUtil.e(10.0d, 1, 0));
            sb.insert(0, "%");
            e = sb.toString();
        }
        this.b.setText(getResources().getString(R$string.IDS_wake_times_content_1_new, quantityString, e));
        this.f9836a.setText(getResources().getString(R$string.IDS_wake_times_explain_2, 2));
        this.d.setText(getResources().getString(R$string.IDS_wake_times_content_2_no_harvard) + "\n" + getResources().getString(R$string.IDS_wake_times_content_3, 1) + "\n" + getResources().getString(R$string.IDS_wake_times_content_4, 2) + "\n" + getResources().getString(R$string.IDS_wake_times_content_5, 3) + "\n" + getResources().getString(R$string.IDS_wake_times_content_6, 4) + "\n" + getResources().getString(R$string.IDS_wake_times_content_7, 5));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_9), getResources().getString(R$string.IDS_fitness_core_sleep_reference_2)}));
    }

    public static void b(Context context, String str, int i, int i2) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SleepScoreTimesActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_sleep_type", str);
        intent.putExtra("intent_score", i);
        intent.putExtra("intent_status", i2);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SleepTimeActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
