package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewTreeObserver;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fed;
import defpackage.mwd;

/* loaded from: classes6.dex */
public class SportCommonWatermark extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private int f8544a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private boolean f;
    private HealthTextView g;
    private boolean h = false;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private View l;
    private HealthTextView m;
    private String n;
    private HealthTextView o;
    private HealthTextView r;
    private int t;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public SportCommonWatermark(Context context) {
        a(context);
        c(context);
        e();
    }

    private void e() {
        this.b.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.pluginsocialshare.view.sharewatermark.SportCommonWatermark.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int width = SportCommonWatermark.this.d.getWidth();
                float measureText = SportCommonWatermark.this.d.getPaint().measureText(SportCommonWatermark.this.d.getText().toString());
                if (SportCommonWatermark.this.b.getWidth() < SportCommonWatermark.this.b.getPaint().measureText(SportCommonWatermark.this.b.getText().toString()) || width < measureText) {
                    float textSize = SportCommonWatermark.this.d.getTextSize();
                    float textSize2 = SportCommonWatermark.this.b.getTextSize();
                    float f = textSize - 1.0f;
                    SportCommonWatermark.this.g.setTextSize(0, f);
                    SportCommonWatermark.this.c.setTextSize(0, f);
                    SportCommonWatermark.this.d.setTextSize(0, f);
                    float f2 = textSize2 - 1.0f;
                    SportCommonWatermark.this.i.setTextSize(0, f2);
                    SportCommonWatermark.this.b.setTextSize(0, f2);
                    SportCommonWatermark.this.e.setTextSize(0, f2);
                }
            }
        });
    }

    private void a(Context context) {
        View inflate = View.inflate(context, R.layout.sport_common_watermark_layout, null);
        this.l = inflate;
        this.k = (HealthTextView) inflate.findViewById(R.id.top_right_first_data);
        this.m = (HealthTextView) this.l.findViewById(R.id.top_right_second_data);
        this.r = (HealthTextView) this.l.findViewById(R.id.top_right_third_data);
        this.i = (HealthTextView) this.l.findViewById(R.id.bottom_start_value);
        this.g = (HealthTextView) this.l.findViewById(R.id.bottom_start_unit);
        this.b = (HealthTextView) this.l.findViewById(R.id.bottom_center_value);
        this.d = (HealthTextView) this.l.findViewById(R.id.bottom_center_unit);
        this.e = (HealthTextView) this.l.findViewById(R.id.bottom_end_value);
        this.c = (HealthTextView) this.l.findViewById(R.id.bottom_end_unit);
        this.o = (HealthTextView) this.l.findViewById(R.id.main_data_value);
        this.j = (HealthTextView) this.l.findViewById(R.id.main_data_unit);
    }

    private void c(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.i.setTypeface(createFromAsset);
        this.b.setTypeface(createFromAsset);
        this.e.setTypeface(createFromAsset);
        this.o.setTypeface(createFromAsset);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.i.setTextColor(i);
        this.g.setTextColor(i);
        this.b.setTextColor(i);
        this.d.setTextColor(i);
        this.e.setTextColor(i);
        this.c.setTextColor(i);
        this.o.setTextColor(i);
        this.j.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.k.setTextColor(i);
        this.m.setTextColor(i);
        this.r.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.l;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.f8544a;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.f8544a = i;
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
        this.f = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.f;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            String j = fedVar.j();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m) || mwd.b(j)) {
                this.h = true;
                return;
            }
            mwd.b(this.k, fedVar.x());
            mwd.b(this.m, fedVar.w());
            mwd.b(this.r, fedVar.u());
            mwd.b(this.o, a2);
            mwd.b(this.j, fedVar.c());
            mwd.b(this.g, fedVar.f());
            mwd.b(this.b, m);
            mwd.b(this.d, fedVar.l());
            mwd.b(this.e, j);
            mwd.b(this.c, fedVar.e());
            mwd.b(this.i, mwd.a(i));
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.t = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.t;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.h;
    }
}
