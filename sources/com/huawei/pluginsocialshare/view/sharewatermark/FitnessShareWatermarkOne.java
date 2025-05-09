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
public class FitnessShareWatermarkOne extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8533a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private int e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private View k;
    private String m;
    private HealthTextView o;
    private int q;
    private HealthTextView s;
    private HealthTextView t;
    private boolean l = true;
    private boolean n = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public FitnessShareWatermarkOne(Context context) {
        a(context);
        c(context);
    }

    private void a(Context context) {
        View inflate = View.inflate(context, R.layout.fitness_watermak_one, null);
        this.k = inflate;
        this.o = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.t = (HealthTextView) this.k.findViewById(R.id.top_left_second_data);
        this.s = (HealthTextView) this.k.findViewById(R.id.top_left_third_data);
        this.h = (HealthTextView) this.k.findViewById(R.id.bottom_start_title);
        this.f = (HealthTextView) this.k.findViewById(R.id.bottom_start_value);
        this.j = (HealthTextView) this.k.findViewById(R.id.bottom_start_unit);
        this.d = (HealthTextView) this.k.findViewById(R.id.bottom_center_title);
        this.c = (HealthTextView) this.k.findViewById(R.id.bottom_center_value);
        this.b = (HealthTextView) this.k.findViewById(R.id.bottom_center_unit);
        this.f8533a = (HealthTextView) this.k.findViewById(R.id.bottom_end_title);
        this.i = (HealthTextView) this.k.findViewById(R.id.bottom_end_value);
        this.g = (HealthTextView) this.k.findViewById(R.id.bottom_end_unit);
    }

    private void c(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.f.setTypeface(createFromAsset);
        this.c.setTypeface(createFromAsset);
        this.i.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.h.setTextColor(i);
        this.f.setTextColor(i);
        this.j.setTextColor(i);
        this.d.setTextColor(i);
        this.c.setTextColor(i);
        this.b.setTextColor(i);
        this.f8533a.setTextColor(i);
        this.i.setTextColor(i);
        this.g.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.s.setTextColor(i);
        this.o.setTextColor(i);
        this.t.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.k;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.e;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.e = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.m;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.m = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.l = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.l;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m)) {
                this.n = true;
                return;
            }
            mwd.b(this.o, fedVar.r());
            mwd.b(this.t, fedVar.s());
            mwd.b(this.s, fedVar.v());
            mwd.b(this.h, fedVar.b());
            mwd.b(this.f, a2);
            mwd.b(this.j, fedVar.c());
            mwd.b(this.d, fedVar.g());
            mwd.b(this.c, i);
            mwd.b(this.b, fedVar.f());
            mwd.b(this.f8533a, fedVar.h());
            mwd.b(this.i, m);
            mwd.b(this.g, fedVar.l());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.q = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.q;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.n;
    }
}
