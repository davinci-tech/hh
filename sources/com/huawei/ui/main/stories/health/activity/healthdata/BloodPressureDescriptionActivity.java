package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureDescriptionActivity;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.dqn;
import defpackage.eeu;
import defpackage.koq;
import defpackage.qif;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class BloodPressureDescriptionActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10021a;
    private LinearLayout b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView n;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (LanguageUtil.m(this)) {
            setContentView(R.layout.activity_blood_pressure_description);
        } else {
            setContentView(R.layout.activity_blood_pressure_description_other);
        }
        c();
        d();
    }

    private void c() {
        this.g = (HealthTextView) findViewById(R.id.low_pressure_des_text);
        this.f = (HealthTextView) findViewById(R.id.high_pressure_des_text);
        this.n = (HealthTextView) findViewById(R.id.twenty_four_hour_des_text);
        this.e = (HealthTextView) findViewById(R.id.day_des_text);
        this.i = (HealthTextView) findViewById(R.id.night_des_text);
        this.d = (HealthTextView) findViewById(R.id.grading_standard_text);
        this.h = (HealthTextView) findViewById(R.id.hypotension_des_source_text);
        this.j = (HealthTextView) findViewById(R.id.hypopiesis_title_text);
        this.f10021a = (HealthTextView) findViewById(R.id.dynamic_blood_pressure_title_text);
        this.b = (LinearLayout) findViewById(R.id.dynamic_blood_layout);
        this.c = (HealthTextView) findViewById(R.id.blood_data_des);
    }

    private void d() {
        this.d.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_bp_grading_standard_value), UnitUtil.e(1.0d, 1, 0)));
        this.h.setText(BloodPressureUtil.c(1));
        this.j.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_hypopiesis_title_value), UnitUtil.e(2.0d, 1, 0)));
        this.f.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_sbp_high_pressure_value), UnitUtil.e(90.0d, 1, 0)));
        this.g.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_dbp_low_pressure_value), UnitUtil.e(60.0d, 1, 0), UnitUtil.e(140.0d, 1, 0)));
        if (Utils.a(BaseApplication.e())) {
            this.c.setText(getString(R$string.IDS_hw_dbp_low_bottom_japan_des));
        }
        b();
        if (koq.b(eeu.b())) {
            LogUtil.c("BloodPressureDescriptionActivity", "initData, BloodPressureList is null");
            return;
        }
        ArrayList<dqn> arrayList = new ArrayList(eeu.b());
        LogUtil.d("BloodPressureDescriptionActivity", "featureInfoList,", Integer.valueOf(arrayList.size()));
        Collections.sort(arrayList, new Comparator() { // from class: qbq
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return BloodPressureDescriptionActivity.d((dqn) obj, (dqn) obj2);
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (dqn dqnVar : arrayList) {
            List<dqn.b> a2 = dqnVar.a();
            if (dqnVar.c() != 0 && !koq.b(a2)) {
                arrayList2.addAll(a2);
            }
        }
        Collections.sort(arrayList2, new Comparator() { // from class: qbr
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return BloodPressureDescriptionActivity.c((dqn.b) obj, (dqn.b) obj2);
            }
        });
        a(arrayList2);
    }

    public static /* synthetic */ int d(dqn dqnVar, dqn dqnVar2) {
        return dqnVar.c() - dqnVar2.c();
    }

    public static /* synthetic */ int c(dqn.b bVar, dqn.b bVar2) {
        return bVar.d() - bVar2.d();
    }

    private void b() {
        this.f10021a.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_hypopiesis_title_dynamic_blood_pressure), UnitUtil.e(3.0d, 1, 0)));
        Pair<Integer, Integer> dDO_ = qif.dDO_("Twenty-four-hours");
        this.n.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_dbp_low_bottom_blood_measure_twenty_four), UnitUtil.e(24.0d, 1, 0), UnitUtil.e(((Integer) dDO_.first).intValue(), 1, 0), UnitUtil.e(((Integer) dDO_.second).intValue(), 1, 0)));
        Pair<Integer, Integer> dDO_2 = qif.dDO_("day");
        this.e.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_dbp_low_bottom_blood_measure_day), UnitUtil.e(((Integer) dDO_2.first).intValue(), 1, 0), UnitUtil.e(((Integer) dDO_2.second).intValue(), 1, 0)));
        Pair<Integer, Integer> dDO_3 = qif.dDO_("night");
        this.i.setText(String.format(Locale.ROOT, getString(R$string.IDS_hw_dbp_low_bottom_blood_measure_night), UnitUtil.e(((Integer) dDO_3.first).intValue(), 1, 0), UnitUtil.e(((Integer) dDO_3.second).intValue(), 1, 0)));
        if (!BloodPressureUtil.c()) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }

    private void a(List<dqn.b> list) {
        String[] e = eeu.e();
        int[] a2 = BloodPressureUtil.a(this);
        int i = 0;
        while (i < list.size()) {
            dqn.b bVar = list.get(i);
            List<Integer> a3 = bVar.a();
            int i2 = 1;
            String e2 = koq.d(a3, 0) ? UnitUtil.e(a3.get(0).intValue(), 1, 0) : "";
            String e3 = koq.d(a3, 1) ? UnitUtil.e(a3.get(1).intValue(), 1, 0) : "";
            List<Integer> b = bVar.b();
            String[] strArr = {e2, e3, koq.d(b, 0) ? UnitUtil.e(b.get(0).intValue(), 1, 0) : "", koq.d(b, 1) ? UnitUtil.e(b.get(1).intValue(), 1, 0) : ""};
            if (i == 0) {
                i2 = 0;
            } else if (i == list.size() - 1) {
                i2 = 2;
            }
            int i3 = i + 1;
            c(bVar, strArr, i2, e[i3], a2[i]);
            i = i3;
        }
    }

    private void c(dqn.b bVar, String[] strArr, int i, String str, int i2) {
        View inflate;
        if (LanguageUtil.m(this)) {
            inflate = LayoutInflater.from(this).inflate(R.layout.blood_pressure_description_item, (ViewGroup) null);
        } else {
            inflate = LayoutInflater.from(this).inflate(R.layout.blood_pressure_description_item_other, (ViewGroup) null);
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text_level);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.icon_level);
        healthTextView.setText(str);
        healthTextView2.getBackground().setTint(i2);
        ((HealthTextView) inflate.findViewById(R.id.text_and_or)).setText(bVar.c() == 0 ? getString(R$string.IDS_hw_and) : getString(R$string.IDS_hw_and_or));
        HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.text_systolic);
        HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.text_diastolic);
        if (i == 0) {
            healthTextView3.setText(String.format(Locale.ROOT, getString(R$string.IDS_bp_min_scope_value), strArr[1]));
            healthTextView4.setText(String.format(Locale.ROOT, getString(R$string.IDS_bp_min_scope_value), strArr[3]));
        } else if (i == 2) {
            healthTextView3.setText(String.format(Locale.ROOT, getString(R$string.IDS_bp_max_explain), strArr[0]));
            healthTextView4.setText(String.format(Locale.ROOT, getString(R$string.IDS_bp_max_explain), strArr[2]));
        } else {
            healthTextView3.setText(String.format(Locale.ROOT, getString(R$string.IDS_bp_range_value), strArr[0], strArr[1]));
            healthTextView4.setText(String.format(Locale.ROOT, getString(R$string.IDS_bp_range_value), strArr[2], strArr[3]));
        }
        ((ViewGroup) findViewById(R.id.list_item_container)).addView(inflate, 1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
