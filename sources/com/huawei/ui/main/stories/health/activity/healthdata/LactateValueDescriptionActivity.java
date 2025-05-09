package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.template.BaseActivity;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import health.compact.a.UnitUtil;

/* loaded from: classes9.dex */
public class LactateValueDescriptionActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HwTextView f10075a;
    private HwTextView b;
    private HwTextView c;
    private HwTextView d;
    private HwTextView e;
    private HwTextView f;
    private HwTextView g;
    private HwTextView h;
    private HwTextView i;
    private HwTextView j;
    private String k;
    private HwTextView l;
    private HwTextView m;
    private HwTextView n;
    private String o;
    private String p;
    private HwTextView q;
    private String r;
    private String s;
    private HwTextView t;
    private HwTextView v;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_lactate_description);
        cancelAdaptRingRegion();
        d();
        e();
        b();
    }

    public void d() {
        this.g = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top1_text);
        this.v = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top2);
        this.t = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top2_text);
        this.l = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top3);
        this.m = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top3_text);
        this.b = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top4);
        this.f10075a = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top4_text);
        this.h = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top5);
        this.j = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top5_text);
        this.e = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top6);
        this.d = (HwTextView) findViewById(R.id.IDS_lactate_subHeader_top6_text);
        this.q = (HwTextView) findViewById(R.id.IDS_lactate_one);
        this.n = (HwTextView) findViewById(R.id.IDS_lactate_two);
        this.f = (HwTextView) findViewById(R.id.IDS_lactate_three);
        this.i = (HwTextView) findViewById(R.id.IDS_lactate_four);
        this.c = (HwTextView) findViewById(R.id.IDS_lactate_five);
    }

    public void e() {
        this.g.setText(R$string.IDS_lactate_subHeader_top1_text);
        this.v.setText(R$string.IDS_lactate_subHeader_top2);
        this.t.setText(R$string.IDS_lactate_subHeader_top2_text);
        this.l.setText(R$string.IDS_lactate_subHeader_top3);
        this.m.setText(R$string.IDS_lactate_subHeader_top3_text);
        this.b.setText(R$string.IDS_lactate_subHeader_top4);
        this.f10075a.setText(R$string.IDS_lactate_subHeader_top4_text);
        this.h.setText(R$string.IDS_lactate_subHeader_top5);
        this.j.setText(R$string.IDS_lactate_subHeader_top5_text);
        this.e.setText(R$string.IDS_lactate_subHeader_top6);
        this.d.setText(R$string.IDS_lactate_subHeader_top6_text);
    }

    public void b() {
        String d = d(87.0d, 1, 0);
        String str = b(80.0d, 1, 0) + d;
        this.o = str;
        this.q.setText(str);
        String d2 = d(80.0d, 1, 0);
        String str2 = b(89.0d, 1, 0) + d2;
        this.p = str2;
        this.n.setText(str2);
        String d3 = d(89.0d, 1, 0);
        String str3 = b(97.0d, 1, 0) + d3;
        this.s = str3;
        this.f.setText(str3);
        String d4 = d(90.0d, 1, 0);
        String str4 = b(102.0d, 1, 0) + d4;
        this.r = str4;
        this.i.setText(str4);
        String b = b(102.0d, 1, 0);
        this.k = b;
        this.c.setText(b);
    }

    public String d(double d, int i, int i2) {
        return UnitUtil.e(d, i, i2) + " % ";
    }

    public String b(double d, int i, int i2) {
        return UnitUtil.e(d, i, i2) + " %~ ";
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
