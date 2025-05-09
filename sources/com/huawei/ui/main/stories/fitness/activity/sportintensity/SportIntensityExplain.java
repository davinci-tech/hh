package com.huawei.ui.main.stories.fitness.activity.sportintensity;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.mxf;
import defpackage.nsy;
import health.compact.a.SportIntensityUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class SportIntensityExplain extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private mxf f9886a = new mxf();
    private HealthTextView b;
    private CustomTitleBar c;
    private HealthTextView e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sport_intensity_explain);
        c();
        a();
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.fitness_detail_titlebar);
        this.c = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_hw_pressure_explain));
    }

    private void a() {
        this.f9886a = SportIntensityUtil.b();
        this.e = (HealthTextView) nsy.cMc_(this, R.id.activity_textview);
        this.b = (HealthTextView) nsy.cMc_(this, R.id.rules_textview);
        String quantityString = getResources().getQuantityString(R.plurals._2130903333_res_0x7f030125, this.f9886a.b(), Integer.valueOf(this.f9886a.b()));
        try {
            this.e.setText(String.format(getResources().getString(R$string.IDS_hw_sport_intensity_new_suggest), Integer.valueOf(UnitUtil.e(150.0d, 1, 0)), Integer.valueOf(UnitUtil.e(300.0d, 1, 0)), Integer.valueOf(UnitUtil.e(75.0d, 1, 0))));
            this.b.setText(String.format(getResources().getString(R$string.IDS_hw_sport_intensity_new_detail), quantityString));
        } catch (NumberFormatException e) {
            LogUtil.b("Step_SportIntensityExplain", "NumberFormatException", e.getMessage());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
