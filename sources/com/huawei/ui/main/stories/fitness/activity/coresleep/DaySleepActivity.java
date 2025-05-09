package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity;
import defpackage.nsn;
import defpackage.scn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class DaySleepActivity extends BaseCoreSleepActivity {
    private int p;
    private String q;
    private String r;
    private LinearLayout s;
    private ArrayList<String> t;

    @Override // com.huawei.ui.main.stories.fitness.activity.coresleep.base.BaseCoreSleepActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.activity_daysleep);
        super.onCreate(bundle);
        c();
        a();
        b();
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("DaySleepActivity", "intent is null.");
            return;
        }
        this.q = intent.getStringExtra("intent_hour");
        this.r = intent.getStringExtra("intent_minutes");
        this.p = intent.getIntExtra("intent_status", 0);
        try {
            this.t = intent.getStringArrayListExtra("intent_list");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.h("DaySleepActivity", "getIntentData error: ArrayIndexOutOfBoundsException");
        }
    }

    private void a() {
        this.s = (LinearLayout) findViewById(R.id.noon_sleep_ll);
    }

    private void b() {
        String a2 = scn.a(this);
        this.h.setText(String.format(getResources().getString(R$string.IDS_sleep_referece_title_string), UnitUtil.e(0.0d, 1, 0) + a2 + UnitUtil.e(40.0d, 1, 0) + " "));
        if (CommonUtil.m(this, this.q) == 0) {
            this.l.setVisibility(8);
            this.j.setVisibility(8);
            this.b.setText(R$string.IDS_messagecenter_time_minute_value);
        }
        this.l.setText(this.q + " ");
        this.e.setText(this.r + " ");
        if (CommonUtil.m(this, this.q) == 0 && CommonUtil.m(this, this.r) == 0) {
            this.f9836a.setVisibility(8);
        }
        switch (this.p) {
            case 71:
                this.f9836a.setText(R$string.IDS_hwh_runningstyle_longer);
                this.f9836a.setTextColor(Color.parseColor("#FFFF3C3C"));
                break;
            case 72:
                this.f9836a.setText(R$string.IDS_hwh_runningstyle_shorter);
                this.f9836a.setTextColor(Color.parseColor("#FFF79A3C"));
                break;
            case 73:
                this.f9836a.setText(R$string.IDS_details_sleep_grade_normal);
                this.f9836a.setTextColor(Color.parseColor("#FF4CC51F"));
                break;
            default:
                LogUtil.a("DaySleepActivity", "no status!");
                break;
        }
        e();
    }

    private void e() {
        View inflate;
        ArrayList<String> arrayList = this.t;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (nsn.t()) {
                    inflate = LayoutInflater.from(this).inflate(R.layout.layout_noon_sleep_item_largre_mode, (ViewGroup) null);
                } else {
                    inflate = LayoutInflater.from(this).inflate(R.layout.layout_noon_sleep_item, (ViewGroup) null);
                }
                HealthDivider healthDivider = (HealthDivider) inflate.findViewById(R.id.noon_sleep_divide);
                inflate.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                ((HealthTextView) inflate.findViewById(R.id.time_tv)).setText(this.t.get(i).toString());
                if (this.t.size() - 1 >= 0 && i == this.t.size() - 1) {
                    healthDivider.setVisibility(8);
                } else {
                    healthDivider.setVisibility(0);
                }
                this.s.addView(inflate);
            }
        } else {
            LogUtil.h("DaySleepActivity", "noonSleepList is null!");
        }
        this.d.setText(getResources().getString(R$string.IDS_day_sleep_explain_1, 1));
        this.f.setText(getResources().getString(R$string.IDS_day_sleep_explain_2, 2));
        this.n.setText(getResources().getString(R$string.IDS_day_sleep_content_1, 15, 30));
        this.k.setText(getResources().getString(R$string.IDS_day_sleep_content_2, getResources().getString(R$string.IDS_hw_show_set_target_sport_time_unit, UnitUtil.e(40.0d, 1, 0))));
        this.g.setText(getResources().getString(R$string.IDS_day_sleep_content_4, 1));
        this.o.setText(getResources().getString(R$string.IDS_day_sleep_content_3, getResources().getQuantityString(R.plurals._2130903223_res_0x7f0300b7, 3, UnitUtil.e(3.0d, 1, 0))));
        if (!LanguageUtil.q(this)) {
            this.c.setText(getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, 1));
            this.m.setText(getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, 2));
            return;
        }
        this.c.setText(getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, 1) + " ");
        this.m.setText(getResources().getString(R$string.IDS_fitness_core_sleep_reference_14, 2) + " ");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        scn.c(this.j);
        scn.c(this.b);
    }

    public static void a(Context context, String str, String str2, int i, ArrayList<String> arrayList) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) DaySleepActivity.class);
        intent.setFlags(536870912);
        intent.putExtra("intent_hour", str);
        intent.putExtra("intent_minutes", str2);
        intent.putExtra("intent_status", i);
        intent.putExtra("intent_list", arrayList);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("DaySleepActivity", "ActivityNotFoundException", e.getMessage());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
