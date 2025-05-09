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
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity;
import defpackage.pqg;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes9.dex */
public class SleepTimeActivity extends BaseCoreSleepActivity {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_explain);
        super.onCreate(bundle);
        b();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("R_Sleep_SleepTimeActivity", "intent is null!");
            return;
        }
        int intExtra = intent.getIntExtra("intent_sleep_type", -1);
        String stringExtra = intent.getStringExtra("sleepTime");
        a(intExtra);
        drC_(intent, intExtra, stringExtra);
        scn.e(this.j, intent.getIntExtra("intent_status", 0), true);
    }

    private void a(int i) {
        String string;
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        if (i == 1) {
            string = getResources().getString(R$string.IDS_get_in_bed_time_label);
            a();
        } else if (i == 2) {
            string = getResources().getString(R$string.IDS_fitness_core_sleep_go_to_sleep_time);
            d();
        } else if (i == 3) {
            string = getResources().getString(R$string.IDS_manual_sleep_waking_time);
            c();
        } else if (i == 4) {
            string = getResources().getString(R$string.IDS_fitness_core_sleep_wake_up_time);
            e();
        } else {
            LogUtil.b("R_Sleep_SleepTimeActivity", "type error!");
            string = "";
        }
        customTitleBar.setTitleText(string);
    }

    private void a() {
        this.e.setText(getResources().getString(R$string.IDS_go_bed_time_explain_1, UnitUtil.e(1.0d, 1, 0)));
        a(getResources().getString(R$string.IDS_go_bed_time_explain_2));
    }

    private void a(String str) {
        this.b.setText(str);
        this.f9836a.setText(getResources().getString(R$string.IDS_go_bed_time_explain_3, UnitUtil.e(2.0d, 1, 0)));
        this.d.setText(getResources().getString(R$string.IDS_go_bed_time_explain_4, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0)));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_2), getResources().getString(R$string.IDS_fitness_core_sleep_reference_5)}));
    }

    private void d() {
        this.e.setText(getResources().getString(R$string.IDS_fall_asleep_time_explain_1, UnitUtil.e(1.0d, 1, 0)));
        a(getResources().getString(R$string.IDS_fall_asleep_time_explain_2));
    }

    private void c() {
        this.e.setText(getResources().getString(R$string.IDS_wake_up_explain_1, UnitUtil.e(1.0d, 1, 0)));
        a(getResources().getString(R$string.IDS_wake_up_explain_2));
    }

    private void e() {
        this.e.setText(getResources().getString(R$string.IDS_off_bed_time_explain_1, UnitUtil.e(1.0d, 1, 0)));
        a(getResources().getString(R$string.IDS_off_bed_time_explain_2));
    }

    private void drC_(Intent intent, int i, String str) {
        String str2;
        String drB_;
        Locale locale = Locale.getDefault();
        if (i == 1 || i == 2) {
            if (LanguageUtil.b(this)) {
                str2 = String.format(locale, "%1$s", HiDataFilter.DataFilterExpression.LESS_THAN) + UnitUtil.e(0);
            } else {
                str2 = "< 00:00";
            }
            drB_ = drB_(intent);
        } else if (i == 3 || i == 4) {
            drB_ = drB_(intent);
            str2 = String.format(locale, "%1$s", HiDataFilter.DataFilterExpression.BIGGER_THAN) + " " + UnitUtil.e(d("6:0"));
        } else {
            LogUtil.b("R_Sleep_SleepTimeActivity", "type error!");
            drB_ = "";
            str2 = drB_;
        }
        this.j.setText(drB_);
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e)), 0, str.length(), 33);
        this.l.setText(spannableString);
        e(str2);
    }

    private String drB_(Intent intent) {
        String stringExtra = intent.getStringExtra("sleepTimeScore");
        return stringExtra != null ? UnitUtil.e(d(stringExtra)) : "--";
    }

    private void e(String str) {
        this.h.setText(String.format(getResources().getString(R$string.IDS_sleep_referece_title_string), str));
    }

    private int d(String str) {
        String[] split = str.split(":");
        try {
            return (Integer.parseInt(split[0]) * 3600) + (Integer.parseInt(split[1]) * 60);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            LogUtil.b("R_Sleep_SleepTimeActivity", "getSeconds exception = ", e.getMessage());
            return 0;
        }
    }

    public static void d(Context context, int i, String str, int i2) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SleepTimeActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_sleep_type", i);
        intent.putExtra("sleepTime", str);
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
