package com.huawei.ui.main.stories.fitness.activity.bloodoxygen;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class BloodOxygenIntroducedActivity extends BaseActivity {
    private static int c = 2;
    private static int d = 3;
    private static int e = 1;

    /* renamed from: a, reason: collision with root package name */
    private Context f9742a;
    private List<HealthTextView> b = new ArrayList(4);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_oxygen_introduced_layout);
        this.f9742a = this;
        d();
    }

    private void d() {
        c();
        h();
        if (Utils.o()) {
            findViewById(R.id.high_land_blood_oxygen).setVisibility(8);
            return;
        }
        e();
        b();
        a();
    }

    private void c() {
        String e2 = UnitUtil.e(95.0d, 2, 0);
        String e3 = UnitUtil.e(100.0d, 2, 0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.blood_oxygen_introduced_description);
        healthTextView.setText(a(this.f9742a.getString(R$string.IDS_hw_health_blood_oxygen_introduction, c(e2), c(e3))));
        if (LanguageUtil.bl(this.f9742a)) {
            healthTextView.setBreakStrategy(2);
            healthTextView.setHyphenationFrequency(1);
        }
        ((HealthTextView) findViewById(R.id.blood_oxygen_measure_range_1)).setText(this.f9742a.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_greater_than, UnitUtil.e(90.0d, 2, 0)));
        String e4 = UnitUtil.e(70.0d, 2, 0);
        ((HealthTextView) findViewById(R.id.blood_oxygen_measure_range_2)).setText(String.format(this.f9742a.getString(R$string.IDS_press_auto_monitor_relax_range), e4, UnitUtil.e(89.0d, 2, 0)));
        ((HealthTextView) findViewById(R.id.blood_oxygen_measure_red)).setText(String.format(this.f9742a.getString(R$string.IDS_hw_health_blood_oxygen_measure_interval_lower_than), e4));
    }

    private void h() {
        ((HealthTextView) findViewById(R.id.considerations_first)).setText(a(getResources().getString(R$string.IDS_hw_health_blood_oxygen_measure_precautions_1, Integer.valueOf(e))));
        ((HealthTextView) findViewById(R.id.considerations_second)).setText(a(getResources().getString(R$string.IDS_hw_health_blood_oxygen_measure_precautions_2, Integer.valueOf(c))));
        ((HealthTextView) findViewById(R.id.considerations_third)).setText(a(getResources().getString(R$string.IDS_hw_health_blood_oxygen_measure_precautions_3, Integer.valueOf(d))));
    }

    private void e() {
        ((HealthTextView) findViewById(R.id.ams_title_desc)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_altitude_title_desc, 2500));
        ((HealthTextView) findViewById(R.id.ams_first_desc1)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_second_title_one_desc1, Integer.valueOf(e), 15));
        ((HealthTextView) findViewById(R.id.ams_first_desc2)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_second_title_one_desc2, Integer.valueOf(c)));
        ((HealthTextView) findViewById(R.id.ams_first_desc3)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_second_title_one_desc3, Integer.valueOf(d)));
        ((HealthTextView) findViewById(R.id.ams_first_range1)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_altitude_range_temp, 15, 40));
        ((HealthTextView) findViewById(R.id.ams_first_range2)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_altitude_range_alt, 4500));
    }

    private void b() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.ams_legend_desc1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.ams_legend_desc2);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.ams_legend_desc3);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.ams_legend_desc4);
        if (LanguageUtil.bp(this.f9742a)) {
            healthTextView.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_normal, 2, 0));
            healthTextView2.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_mild, 5, 3));
            healthTextView3.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_moderate, 9, 6));
            healthTextView4.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_severe, 12, 10));
        } else {
            healthTextView.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_normal, 0, 2));
            healthTextView2.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_mild, 3, 5));
            healthTextView3.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_moderate, 6, 9));
            healthTextView4.setText(this.f9742a.getString(R$string.IDS_blood_oxygen_legend_desc_severe, 10, 12));
        }
        ((HealthTextView) findViewById(R.id.ams_measure_note_tips1)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_note1, Integer.valueOf(e), 2));
        ((HealthTextView) findViewById(R.id.ams_measure_note_tips2)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_note2, Integer.valueOf(c)));
        ((HealthTextView) findViewById(R.id.ams_measure_note_tips3)).setText(this.f9742a.getString(R$string.IDS_blood_oxygen_note3, Integer.valueOf(d), 2018));
    }

    private void a() {
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.ams_legend1);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.ams_legend2);
        HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.ams_legend3);
        HealthTextView healthTextView4 = (HealthTextView) findViewById(R.id.ams_legend4);
        this.b.add(healthTextView);
        this.b.add(healthTextView2);
        this.b.add(healthTextView3);
        this.b.add(healthTextView4);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        int i = 0;
        for (HealthTextView healthTextView : this.b) {
            if (healthTextView.getMeasuredWidth() > i) {
                i = healthTextView.getMeasuredWidth();
            }
        }
        if (i != 0) {
            Iterator<HealthTextView> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().setWidth(i);
            }
        }
    }

    private String a(String str) {
        if (!LanguageUtil.h(this.f9742a)) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c2 = charArray[i];
            if (c2 == '(' || c2 == ')') {
                charArray[i] = (char) (c2 + 65248);
            }
        }
        return new String(charArray);
    }

    private String c(String str) {
        if (!LanguageUtil.bp(this.f9742a) && !LanguageUtil.y(this.f9742a)) {
            return str;
        }
        return "\u200f" + str;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
