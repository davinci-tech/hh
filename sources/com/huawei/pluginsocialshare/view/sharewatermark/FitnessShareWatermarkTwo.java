package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fed;
import defpackage.mwd;

/* loaded from: classes6.dex */
public class FitnessShareWatermarkTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8537a;
    private HealthTextView b;
    private int c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private boolean l;
    private HealthTextView m;
    private boolean n = true;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private View r;
    private HealthTextView s;
    private String t;
    private int v;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public FitnessShareWatermarkTwo(Context context) {
        b(context);
        c(context);
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.fitness_watermask_two, null);
        this.r = inflate;
        this.q = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.p = (HealthTextView) this.r.findViewById(R.id.top_left_second_data);
        this.s = (HealthTextView) this.r.findViewById(R.id.top_left_third_data);
        this.e = (HealthTextView) this.r.findViewById(R.id.first_line_start_data_title);
        this.f = (HealthTextView) this.r.findViewById(R.id.first_line_start_data_value);
        this.g = (HealthTextView) this.r.findViewById(R.id.first_line_start_data_unit);
        this.b = (HealthTextView) this.r.findViewById(R.id.first_line_end_data_title);
        this.f8537a = (HealthTextView) this.r.findViewById(R.id.first_line_end_data_value);
        this.d = (HealthTextView) this.r.findViewById(R.id.first_line_end_data_unit);
        this.k = (HealthTextView) this.r.findViewById(R.id.second_line_start_data_title);
        this.o = (HealthTextView) this.r.findViewById(R.id.second_line_start_data_value);
        this.m = (HealthTextView) this.r.findViewById(R.id.second_line_start_data_unit);
        this.j = (HealthTextView) this.r.findViewById(R.id.second_line_end_data_title);
        this.i = (HealthTextView) this.r.findViewById(R.id.second_line_end_data_value);
        this.h = (HealthTextView) this.r.findViewById(R.id.second_line_end_data_unit);
    }

    private void c(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.f.setTypeface(createFromAsset);
        this.f8537a.setTypeface(createFromAsset);
        this.o.setTypeface(createFromAsset);
        this.i.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.e.setTextColor(i);
        this.f.setTextColor(i);
        this.g.setTextColor(i);
        this.b.setTextColor(i);
        this.f8537a.setTextColor(i);
        this.d.setTextColor(i);
        this.k.setTextColor(i);
        this.o.setTextColor(i);
        this.m.setTextColor(i);
        this.j.setTextColor(i);
        this.i.setTextColor(i);
        this.h.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.q.setTextColor(i);
        this.p.setTextColor(i);
        this.s.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.r;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.c;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.c = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.t;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.t = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.n = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.n;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            String j = fedVar.j();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m) || mwd.b(j)) {
                this.l = true;
                return;
            }
            mwd.b(this.q, fedVar.r());
            mwd.b(this.p, fedVar.s());
            mwd.b(this.s, fedVar.v());
            mwd.b(this.e, fedVar.b());
            mwd.b(this.f, a2);
            mwd.b(this.g, fedVar.c());
            mwd.b(this.b, fedVar.g());
            mwd.b(this.f8537a, i);
            mwd.b(this.d, fedVar.f());
            mwd.b(this.k, fedVar.h());
            mwd.b(this.o, m);
            mwd.b(this.m, fedVar.l());
            mwd.b(this.j, fedVar.d());
            mwd.b(this.i, j);
            mwd.b(this.h, fedVar.e());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.v = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.v;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.l;
    }
}
