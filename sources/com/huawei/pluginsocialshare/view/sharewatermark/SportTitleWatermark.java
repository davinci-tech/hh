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
public class SportTitleWatermark extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private int f8547a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private View k;
    private HealthTextView l;
    private HealthTextView o;
    private int p;
    private HealthTextView q;
    private String r;
    private HealthTextView s;
    private HealthTextView t;
    private boolean n = true;
    private boolean m = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public SportTitleWatermark(Context context) {
        b(context);
        d(context);
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.sport_title_watermark_layout, null);
        this.k = inflate;
        this.s = (HealthTextView) inflate.findViewById(R.id.top_right_first_data);
        this.q = (HealthTextView) this.k.findViewById(R.id.top_right_second_data);
        this.t = (HealthTextView) this.k.findViewById(R.id.top_right_third_data);
        this.l = (HealthTextView) this.k.findViewById(R.id.main_data_value);
        this.o = (HealthTextView) this.k.findViewById(R.id.main_data_unit);
        this.g = (HealthTextView) this.k.findViewById(R.id.bottom_start_title);
        this.f = (HealthTextView) this.k.findViewById(R.id.bottom_start_value);
        this.j = (HealthTextView) this.k.findViewById(R.id.bottom_start_unit);
        this.b = (HealthTextView) this.k.findViewById(R.id.bottom_center_title);
        this.d = (HealthTextView) this.k.findViewById(R.id.bottom_center_value);
        this.c = (HealthTextView) this.k.findViewById(R.id.bottom_center_unit);
        this.e = (HealthTextView) this.k.findViewById(R.id.bottom_end_title);
        this.i = (HealthTextView) this.k.findViewById(R.id.bottom_end_value);
        this.h = (HealthTextView) this.k.findViewById(R.id.bottom_end_unit);
    }

    private void d(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.l.setTypeface(createFromAsset);
        this.f.setTypeface(createFromAsset);
        this.d.setTypeface(createFromAsset);
        this.i.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.l.setTextColor(i);
        this.o.setTextColor(i);
        this.g.setTextColor(i);
        this.f.setTextColor(i);
        this.j.setTextColor(i);
        this.c.setTextColor(i);
        this.d.setTextColor(i);
        this.b.setTextColor(i);
        this.e.setTextColor(i);
        this.i.setTextColor(i);
        this.h.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.q.setTextColor(i);
        this.t.setTextColor(i);
        this.s.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.k;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.f8547a;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.f8547a = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.r;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.r = str;
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
                this.m = true;
                return;
            }
            mwd.b(this.s, fedVar.x());
            mwd.b(this.q, fedVar.w());
            mwd.b(this.t, fedVar.u());
            mwd.b(this.l, a2);
            mwd.b(this.o, fedVar.c());
            mwd.b(this.g, fedVar.g());
            mwd.b(this.f, i);
            mwd.b(this.j, fedVar.f());
            mwd.b(this.b, fedVar.h());
            mwd.b(this.d, m);
            mwd.b(this.c, fedVar.l());
            mwd.b(this.e, fedVar.d());
            mwd.b(this.i, j);
            mwd.b(this.h, fedVar.e());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.p = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.p;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.m;
    }
}
