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
public class SportBottomMainWatermark extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8542a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private int e;
    private View g;
    private HealthTextView h;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private String n;
    private int o;
    private boolean i = true;
    private boolean f = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public SportBottomMainWatermark(Context context) {
        e(context);
        c(context);
    }

    private void e(Context context) {
        View inflate = View.inflate(context, R.layout.sport_bottom_main_layout, null);
        this.g = inflate;
        this.m = (HealthTextView) inflate.findViewById(R.id.top_right_first_data);
        this.l = (HealthTextView) this.g.findViewById(R.id.top_right_second_data);
        this.k = (HealthTextView) this.g.findViewById(R.id.top_right_third_data);
        this.d = (HealthTextView) this.g.findViewById(R.id.bottom_start_title);
        this.j = (HealthTextView) this.g.findViewById(R.id.bottom_start_value);
        this.h = (HealthTextView) this.g.findViewById(R.id.bottom_start_unit);
        this.c = (HealthTextView) this.g.findViewById(R.id.bottom_end_title);
        this.b = (HealthTextView) this.g.findViewById(R.id.bottom_end_value);
        this.f8542a = (HealthTextView) this.g.findViewById(R.id.bottom_end_unit);
    }

    private void c(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.j.setTypeface(createFromAsset);
        this.b.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.d.setTextColor(i);
        this.j.setTextColor(i);
        this.h.setTextColor(i);
        this.c.setTextColor(i);
        this.b.setTextColor(i);
        this.h.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.m.setTextColor(i);
        this.k.setTextColor(i);
        this.l.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.g;
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
        return this.n;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.n = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.i = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            boolean z = fedVar.k() == 1005;
            if ((mwd.b(a2) && !z) || mwd.b(i)) {
                this.f = true;
                return;
            }
            mwd.b(this.m, fedVar.x());
            mwd.b(this.l, fedVar.w());
            mwd.b(this.k, fedVar.u());
            mwd.b(this.d, fedVar.b());
            mwd.b(this.j, a2);
            mwd.b(this.h, fedVar.c());
            mwd.b(this.c, fedVar.g());
            mwd.b(this.b, i);
            mwd.b(this.f8542a, fedVar.f());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.o = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.f;
    }
}
