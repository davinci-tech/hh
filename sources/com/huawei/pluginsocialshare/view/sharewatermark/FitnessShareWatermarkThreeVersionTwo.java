package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fdv;
import defpackage.fed;
import defpackage.mwd;

/* loaded from: classes6.dex */
public class FitnessShareWatermarkThreeVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8536a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private int e;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView j;
    private View k;
    private HealthTextView l;
    private HealthTextView m;
    private String n;
    private HealthTextView o;
    private HealthTextView p;
    private int r;
    private HealthTextView t;
    private boolean i = true;
    private boolean f = false;

    public FitnessShareWatermarkThreeVersionTwo(Context context) {
        a(context);
        c();
    }

    private void a(Context context) {
        View inflate = View.inflate(context, R.layout.fitness_watermark_three_v2, null);
        this.k = inflate;
        this.m = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.o = (HealthTextView) this.k.findViewById(R.id.top_left_second_data);
        this.p = (HealthTextView) this.k.findViewById(R.id.top_left_third_data);
        this.b = (HealthTextView) this.k.findViewById(R.id.bottom_start_title);
        this.h = (HealthTextView) this.k.findViewById(R.id.bottom_start_value);
        this.j = (HealthTextView) this.k.findViewById(R.id.bottom_start_unit);
        this.f8536a = (HealthTextView) this.k.findViewById(R.id.bottom_end_title);
        this.c = (HealthTextView) this.k.findViewById(R.id.bottom_end_value);
        this.d = (HealthTextView) this.k.findViewById(R.id.bottom_end_unit);
        this.t = (HealthTextView) this.k.findViewById(R.id.edit_share_detail_title_username);
        this.g = (ImageView) this.k.findViewById(R.id.track_share_short_image);
        this.l = (HealthTextView) this.k.findViewById(R.id.edit_share_detail_title_device_name);
    }

    private void c() {
        this.h.setTypeface(fdv.awk_());
        this.c.setTypeface(fdv.awk_());
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.p.setTextColor(i);
        this.m.setTextColor(i);
        this.o.setTextColor(i);
        this.t.setTextColor(i);
        this.l.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.b.setTextColor(i);
        this.h.setTextColor(i);
        this.j.setTextColor(i);
        this.f8536a.setTextColor(i);
        this.c.setTextColor(i);
        this.d.setTextColor(i);
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
            if (mwd.b(a2) || mwd.b(i)) {
                this.f = true;
                return;
            }
            mwd.b(this.m, fedVar.r());
            mwd.b(this.o, fedVar.s());
            mwd.b(this.p, fedVar.v());
            mwd.b(this.b, fedVar.b());
            mwd.b(this.h, a2);
            mwd.b(this.j, fedVar.c());
            mwd.b(this.f8536a, fedVar.g());
            mwd.b(this.c, i);
            mwd.b(this.d, fedVar.f());
            mwd.b(this.l, fedVar.u());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.r = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.r;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.f;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.g.setVisibility(8);
            this.t.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.g.setImageBitmap(bitmap);
            this.g.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.t.setText(str);
            this.g.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.t);
        }
    }
}
