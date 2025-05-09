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
public class FitnessShareWatermarkThree extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8535a;
    private HealthTextView b;
    private int c;
    private HealthTextView d;
    private HealthTextView e;
    private View f;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView k;
    private HealthTextView l;
    private String m;
    private HealthTextView n;
    private int o;
    private boolean g = true;
    private boolean j = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public FitnessShareWatermarkThree(Context context) {
        d(context);
        c(context);
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.fitness_watermark_three, null);
        this.f = inflate;
        this.n = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.l = (HealthTextView) this.f.findViewById(R.id.top_left_second_data);
        this.k = (HealthTextView) this.f.findViewById(R.id.top_left_third_data);
        this.d = (HealthTextView) this.f.findViewById(R.id.bottom_start_title);
        this.h = (HealthTextView) this.f.findViewById(R.id.bottom_start_value);
        this.i = (HealthTextView) this.f.findViewById(R.id.bottom_start_unit);
        this.b = (HealthTextView) this.f.findViewById(R.id.bottom_end_title);
        this.f8535a = (HealthTextView) this.f.findViewById(R.id.bottom_end_value);
        this.e = (HealthTextView) this.f.findViewById(R.id.bottom_end_unit);
    }

    private void c(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.h.setTypeface(createFromAsset);
        this.f8535a.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.k.setTextColor(i);
        this.n.setTextColor(i);
        this.l.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.d.setTextColor(i);
        this.h.setTextColor(i);
        this.i.setTextColor(i);
        this.b.setTextColor(i);
        this.f8535a.setTextColor(i);
        this.e.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.f;
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
        return this.m;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.m = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.g = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.g;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            if (mwd.b(a2) || mwd.b(i)) {
                this.j = true;
                return;
            }
            mwd.b(this.n, fedVar.r());
            mwd.b(this.l, fedVar.s());
            mwd.b(this.k, fedVar.v());
            mwd.b(this.d, fedVar.b());
            mwd.b(this.h, a2);
            mwd.b(this.i, fedVar.c());
            mwd.b(this.b, fedVar.g());
            mwd.b(this.f8535a, i);
            mwd.b(this.e, fedVar.f());
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
        return this.j;
    }
}
