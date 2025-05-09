package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity;
import defpackage.gnm;
import defpackage.nsf;
import defpackage.nsy;
import defpackage.pqg;
import defpackage.scn;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class SleepDurationActivity extends BaseCoreSleepActivity {
    private int p;
    private int q;
    private int r;
    private String s;
    private boolean t;

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_explain);
        super.onCreate(bundle);
        b();
        d();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("R_Sleep_TotalDataActivity", "intent is null");
            return;
        }
        this.p = intent.getIntExtra("intent_hour", 0);
        this.r = intent.getIntExtra("intent_minutes", 0);
        this.t = intent.getBooleanExtra("intent_is_phone", false);
        String stringExtra = intent.getStringExtra("intent_type");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.a("R_Sleep_TotalDataActivity", "getIntentData type ", stringExtra);
            this.s = "";
        } else {
            this.s = stringExtra;
        }
        this.q = intent.getIntExtra("intent_status", 0);
    }

    private void d() {
        k();
        c();
        g();
        m();
    }

    private void m() {
        this.j.setText(pqg.c(this.q, this.s));
        switch (this.q) {
            case 71:
                this.j.setTextColor(nsf.c(R.color._2131299381_res_0x7f090c35));
                break;
            case 72:
                this.j.setTextColor(nsf.c(R.color._2131299378_res_0x7f090c32));
                break;
            case 73:
                this.j.setTextColor(nsf.c(R.color._2131297032_res_0x7f090308));
                break;
            default:
                LogUtil.a("TotalDataActivity", "no status!");
                break;
        }
    }

    private void g() {
        SpannableString spannableString;
        int i = 1;
        if (this.p == 0) {
            Resources resources = getResources();
            int i2 = this.r;
            String quantityString = resources.getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i2, UnitUtil.e(i2, 1, 0));
            int length = String.valueOf(this.r).length();
            spannableString = new SpannableString(quantityString);
            boolean contains = quantityString.contains("\u200f");
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e));
            int i3 = length + (contains ? 1 : 0);
            spannableString.setSpan(absoluteSizeSpan, contains ? 1 : 0, i3, 33);
            spannableString.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131299241_res_0x7f090ba9)), i3, quantityString.length(), 33);
        } else {
            Resources resources2 = getResources();
            int i4 = this.p;
            String quantityString2 = resources2.getQuantityString(R.plurals._2130903199_res_0x7f03009f, i4, UnitUtil.e(i4, 1, 0));
            Resources resources3 = getResources();
            int i5 = this.r;
            String quantityString3 = resources3.getQuantityString(R.plurals._2130903200_res_0x7f0300a0, i5, UnitUtil.e(i5, 1, 0));
            String str = quantityString2 + " " + quantityString3;
            if (LanguageUtil.b(this)) {
                String string = getString(R$string.IDS_hw_about_service_and);
                i = 1 + string.length();
                str = quantityString2 + " " + string + quantityString3;
            }
            boolean contains2 = quantityString3.contains("\u200f");
            boolean contains3 = quantityString2.contains("\u200f");
            int length2 = String.valueOf(this.p).length();
            int length3 = String.valueOf(this.r).length();
            SpannableString spannableString2 = new SpannableString(str);
            AbsoluteSizeSpan absoluteSizeSpan2 = new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e));
            int i6 = length2 + (contains3 ? 1 : 0);
            spannableString2.setSpan(absoluteSizeSpan2, contains3 ? 1 : 0, i6, 33);
            spannableString2.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131299241_res_0x7f090ba9)), i6, quantityString2.length(), 33);
            spannableString2.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen._2131362974_res_0x7f0a049e)), quantityString2.length() + i + (contains2 ? 1 : 0), quantityString2.length() + length3 + i + (contains2 ? 1 : 0), 33);
            spannableString2.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131299241_res_0x7f090ba9)), quantityString2.length() + length3 + i + (contains2 ? 1 : 0), str.length(), 33);
            spannableString = spannableString2;
        }
        this.l.setVisibility(0);
        this.l.setText(spannableString);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void k() {
        char c;
        String string;
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar_panel);
        String str = this.s;
        str.hashCode();
        switch (str.hashCode()) {
            case -1195438574:
                if (str.equals("TYPE_SLEEP_BREATH_RATE")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1151624095:
                if (str.equals("TYPE_NIGHT_SLEEP_TIME")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -825327973:
                if (str.equals("TYPE_SLEEP_BLOOD_OX")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -426685062:
                if (str.equals("TYPE_SLEEP_TIME")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -385938629:
                if (str.equals("TYPE_SLEEP_LATENCY_TIME")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 213105222:
                if (str.equals("TYPE_SLEEP_HEART_RATE")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1252391960:
                if (str.equals("TYPE_SLEEP_BED_TIME")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                string = BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_breathrate);
                f();
                break;
            case 1:
                string = getString(R$string.IDS_fitness_core_sleep_night_sleep);
                a(true);
                break;
            case 2:
                string = BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_oxygen_saturation);
                j();
                break;
            case 3:
                string = getString(R$string.IDS_fitness_core_sleep_sleep_duration);
                a(false);
                break;
            case 4:
                string = BaseApplication.getContext().getString(R$string.IDS_sleep_latency_title);
                h();
                break;
            case 5:
                string = BaseApplication.getContext().getString(R$string.IDS_fitness_core_sleep_heartrate);
                i();
                break;
            case 6:
                string = getString(R$string.IDS_manual_sleep_bed_time);
                e();
                break;
            default:
                LogUtil.a("TotalDataActivity", "default title");
                string = "";
                break;
        }
        customTitleBar.setTitleText(string);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void c() {
        char c;
        String e;
        String str = this.s;
        str.hashCode();
        switch (str.hashCode()) {
            case -1151624095:
                if (str.equals("TYPE_NIGHT_SLEEP_TIME")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -426685062:
                if (str.equals("TYPE_SLEEP_TIME")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -385938629:
                if (str.equals("TYPE_SLEEP_LATENCY_TIME")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1252391960:
                if (str.equals("TYPE_SLEEP_BED_TIME")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            e = pqg.e(6, (int) 10.0f, 2);
        } else if (c == 2) {
            e = pqg.e(0, (int) 30.0f, 4);
        } else if (c != 3) {
            e = "";
        } else {
            e = pqg.b(UnitUtil.e(6, 1, 0), UnitUtil.e(10.5f, 1, 1)) + getString(R$string.IDS_messagecenter_time_hour_value);
        }
        this.h.setText(e);
    }

    private void a(boolean z) {
        int i;
        int i2;
        this.e.setText(String.format(getResources().getString(R$string.IDS_details_night_sleep_explain_1), 1));
        if (LanguageUtil.bp(BaseApplication.getContext())) {
            i2 = 6;
            i = 10;
        } else {
            i = 6;
            i2 = 10;
        }
        this.b.setText(getResources().getString(R$string.IDS_details_night_sleep_content_1, Integer.valueOf(i), Integer.valueOf(i2)) + "\n" + getResources().getString(R$string.IDS_details_night_sleep_content_2, getResources().getQuantityString(R.plurals._2130903220_res_0x7f0300b4, 10, 10), getResources().getQuantityString(R.plurals._2130903220_res_0x7f0300b4, 6, 6)));
        if (!this.t && z) {
            this.f9836a.setText(String.format(getResources().getString(R$string.IDS_details_night_sleep_explain_2), 2));
            this.d.setText(getResources().getString(R$string.IDS_details_night_sleep_content_3, 4, 6, 90, 100) + "\n" + getResources().getString(R$string.IDS_details_night_sleep_content_4));
        } else {
            nsy.cMA_(this.f9836a, 8);
            nsy.cMA_(this.d, 8);
        }
        this.c.setText(pqg.e(new String[]{getResources().getString(R$string.IDS_fitness_core_sleep_reference_1), getResources().getString(R$string.IDS_fitness_core_sleep_reference_2)}));
    }

    private void e() {
        this.e.setText(getResources().getString(R$string.IDS_length_of_stay_in_bed_title_new));
        this.b.setText(getResources().getString(R$string.IDS_length_of_stay_in_bed_content_new));
        nsy.cMA_(this.f9836a, 8);
        nsy.cMA_(this.d, 8);
        nsy.cMA_(this.i, 8);
        nsy.cMA_(this.c, 8);
    }

    private void h() {
        this.e.setText(getResources().getString(R$string.IDS_sleep_latency_explain, 1));
        this.b.setText(getResources().getString(R$string.IDS_sleep_latency_improvement_content_new, UnitUtil.e(30.0d, 1, 0), UnitUtil.e(30.0d, 1, 0)));
        this.f9836a.setText(getResources().getString(R$string.IDS_sleep_latency_improvement_explain, 2));
        this.d.setText(getResources().getString(R$string.IDS_sleep_latency_improvement_content_1_new, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(2.0d, 1, 0), UnitUtil.e(3.0d, 1, 0), UnitUtil.e(4.0d, 1, 0), UnitUtil.e(5.0d, 1, 0), UnitUtil.e(6.0d, 1, 0), UnitUtil.e(7.0d, 1, 0)));
        this.i.setVisibility(8);
        this.c.setVisibility(8);
    }

    private void i() {
        this.e.setText(getResources().getString(R$string.IDS_sleep_heart_rate_subtile));
        this.b.setText(getResources().getString(R$string.IDS_sleep_heart_rate_explain, UnitUtil.e(60.0d, 1, 0), UnitUtil.e(100.0d, 1, 0)));
        a();
    }

    private void a() {
        findViewById(R.id.scrollView).setVisibility(8);
        findViewById(R.id.divider1).setVisibility(8);
        this.f9836a.setVisibility(8);
        this.d.setVisibility(8);
        this.i.setVisibility(8);
        this.c.setVisibility(8);
    }

    private void j() {
        this.e.setText(getResources().getString(R$string.IDS_sleep_ox_subtile));
        this.b.setText(getResources().getString(R$string.IDS_sleep_ox_explain, pqg.e(95.0d, 0), pqg.e(100.0d, 0)));
        a();
    }

    private void f() {
        this.e.setText(getResources().getString(R$string.IDS_sleep_breath_rate_subtile));
        this.b.setText(getResources().getString(R$string.IDS_sleep_breate_rate_explain, UnitUtil.e(16.0d, 1, 0), UnitUtil.e(20.0d, 1, 0)));
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        scn.c(this.l);
        scn.c(this.b);
    }

    public static void b(Context context, String str, int i, int i2, int i3) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SleepDurationActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_hour", i);
        intent.putExtra("intent_minutes", i2);
        intent.putExtra("intent_status", i3);
        intent.putExtra("intent_type", str);
        gnm.aPB_(context, intent);
    }

    public static void c(Context context, String str, int i, int i2, int i3, boolean z) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) SleepDurationActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_hour", i);
        intent.putExtra("intent_minutes", i2);
        intent.putExtra("intent_status", i3);
        intent.putExtra("intent_is_phone", z);
        intent.putExtra("intent_type", str);
        try {
            gnm.aPB_(context, intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("TotalDataActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
