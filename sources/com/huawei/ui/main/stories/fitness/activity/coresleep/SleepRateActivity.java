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
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes9.dex */
public class SleepRateActivity extends BaseCoreSleepActivity {
    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_explain);
        super.onCreate(bundle);
        d();
    }

    private void d() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("SleepRateActivity", "intent is null");
            return;
        }
        int intExtra = intent.getIntExtra("sleep_rate", 0);
        int intExtra2 = intent.getIntExtra("intent_status", 0);
        String stringExtra = intent.getStringExtra("intent_sleep_type");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.a("SleepRateActivity", "setIntentData type ", stringExtra);
        } else {
            d(stringExtra, intExtra, intExtra2);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str, int i, int i2) {
        char c;
        d(str);
        str.hashCode();
        switch (str.hashCode()) {
            case -785678506:
                if (str.equals("TYPE_SLEEP_EFFICIENCY")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -415951927:
                if (str.equals("TYPE_DEEP_SLEEP")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 808690857:
                if (str.equals("TYPE_LIGHT_SLEEP")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1550128305:
                if (str.equals("TYPE_SLUM_SLEEP_RATE")) {
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
            b(85, 100, i, i2, str);
            c();
            return;
        }
        if (c == 1) {
            b(20, 60, i, i2, str);
            b();
        } else if (c == 2) {
            b(i, i2, str);
            e();
        } else if (c == 3) {
            b(10, 30, i, i2, str);
            a();
        } else {
            LogUtil.b("SleepRateActivity", "current type is: ", str, ", no type match");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str) {
        char c;
        String string;
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        str.hashCode();
        switch (str.hashCode()) {
            case -785678506:
                if (str.equals("TYPE_SLEEP_EFFICIENCY")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -415951927:
                if (str.equals("TYPE_DEEP_SLEEP")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 808690857:
                if (str.equals("TYPE_LIGHT_SLEEP")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1550128305:
                if (str.equals("TYPE_SLUM_SLEEP_RATE")) {
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
            string = BaseApplication.getContext().getString(R$string.IDS_manual_sleep_sleep_efficiency);
        } else if (c == 1) {
            string = BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_deep_sleep_percent);
        } else if (c == 2) {
            string = BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_light_sleep_percent);
        } else if (c == 3) {
            string = BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_rem_sleep_percent);
        } else {
            LogUtil.a("SleepRateActivity", "default title");
            string = "";
        }
        customTitleBar.setTitleText(string);
    }

    private void b(int i, int i2, String str) {
        String format;
        String string = getResources().getString(R$string.IDS_sleep_referece_title_string);
        if (LanguageUtil.bp(BaseApplication.getContext()) || LanguageUtil.y(BaseApplication.getContext())) {
            StringBuffer stringBuffer = new StringBuffer(UnitUtil.e(55.0d, 1, 0));
            stringBuffer.insert(0, "%");
            format = String.format(Locale.ROOT, string, "< " + ((Object) stringBuffer));
        } else {
            format = String.format(Locale.ROOT, string, "< " + UnitUtil.e(55.0d, 2, 0));
        }
        this.h.setText(format);
        a(i, i2, str);
    }

    private void b(int i, int i2, int i3, int i4, String str) {
        this.h.setText(pqg.e(i, i2, 3));
        a(i3, i4, str);
    }

    private void a(int i, int i2, String str) {
        SpannableString spannableString = new SpannableString(UnitUtil.e(i, 2, 0));
        spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e)), 0, spannableString.length(), 33);
        this.l.setText(spannableString);
        this.j.setText(pqg.c(i2, str));
        switch (i2) {
            case 71:
                this.j.setTextColor(getResources().getColor(R.color._2131299381_res_0x7f090c35));
                break;
            case 72:
                this.j.setTextColor(getResources().getColor(R.color._2131299378_res_0x7f090c32));
                break;
            case 73:
                this.j.setTextColor(getResources().getColor(R.color._2131297032_res_0x7f090308));
                break;
            default:
                LogUtil.a("SleepRateActivity", "no status!");
                break;
        }
        if (i == 0) {
            this.j.setVisibility(8);
        }
    }

    private void b() {
        this.e.setText(getResources().getString(R$string.IDS_deep_sleep_rate_explain_1, 1));
        this.b.setText(getResources().getString(R$string.IDS_deep_sleep_rate_content_1));
        this.f9836a.setText(getResources().getString(R$string.IDS_deep_sleep_rate_explain_2, 2));
        this.d.setText(getResources().getString(R$string.IDS_light_sleep_rate_content_2_no_harvard) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_3, 1) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_4, 2) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_5, 3) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_6, 4) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_7, 5) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_8, 6));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_5), getResources().getString(R$string.IDS_fitness_core_sleep_reference_6), getResources().getString(R$string.IDS_fitness_core_sleep_reference_4)}));
    }

    private void e() {
        this.e.setText(getResources().getString(R$string.IDS_light_sleep_rate_explain_1, 1));
        this.b.setText(getResources().getString(R$string.IDS_light_sleep_rate_content_1));
        this.f9836a.setText(getResources().getString(R$string.IDS_light_sleep_rate_explain_2, 2));
        this.d.setText(getResources().getString(R$string.IDS_light_sleep_rate_content_2_no_harvard) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_3, 1) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_4, 2) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_5, 3) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_6, 4) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_7, 5) + "\n" + getResources().getString(R$string.IDS_light_sleep_rate_content_8, 6));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_5), getResources().getString(R$string.IDS_fitness_core_sleep_reference_6), getResources().getString(R$string.IDS_fitness_core_sleep_reference_4)}));
    }

    private void c() {
        this.e.setText(getResources().getString(R$string.IDS_sleep_efficiency_content_2, 1));
        this.b.setText(getResources().getString(R$string.IDS_sleep_efficiency_content_3_new, pqg.e(100.0d, 0), pqg.e(85.0d, 0), pqg.e(85.0d, 0)));
        this.f9836a.setText(getResources().getString(R$string.IDS_sleep_efficiency_content_4, 2));
        this.d.setText(getResources().getString(R$string.IDS_sleep_efficiency_content_5_new, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(3.0d, 1, 0), UnitUtil.e(4.0d, 1, 0), UnitUtil.e(5.0d, 1, 0), UnitUtil.e(6.0d, 1, 0), UnitUtil.e(7.0d, 1, 0)));
        this.i.setVisibility(8);
        this.c.setVisibility(8);
    }

    private void a() {
        this.e.setText(getResources().getString(R$string.IDS_slum_sleep_rate_explain_1, 1));
        this.b.setText(getResources().getString(R$string.IDS_slum_sleep_rate_content_1));
        this.f9836a.setText(getResources().getString(R$string.IDS_slum_sleep_rate_explain_2, 2));
        this.d.setText(getResources().getString(R$string.IDS_slum_sleep_rate_content_2) + "\n" + getResources().getString(R$string.IDS_slum_sleep_rate_content_3) + "\n" + getResources().getString(R$string.IDS_slum_sleep_rate_content_4_no_harvard) + "\n" + getResources().getString(R$string.IDS_slum_sleep_rate_content_5, 1) + "\n" + getResources().getString(R$string.IDS_slum_sleep_rate_content_6, 2) + "\n" + getResources().getString(R$string.IDS_slum_sleep_rate_content_7, 3));
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_7), getResources().getString(R$string.IDS_fitness_core_sleep_reference_8)}));
    }

    public static void d(Context context, String str, int i, int i2) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SleepRateActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_sleep_type", str);
        intent.putExtra("sleep_rate", i);
        intent.putExtra("intent_status", i2);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("SleepRateActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
