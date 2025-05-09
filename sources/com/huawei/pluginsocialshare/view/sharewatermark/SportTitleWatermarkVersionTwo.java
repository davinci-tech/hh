package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fdv;
import defpackage.fed;
import defpackage.mwd;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class SportTitleWatermarkVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8548a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private int e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView m;
    private ImageView n;
    private String p;
    private LinearLayout q;
    private HealthTextView r;
    private View s;
    private HealthTextView t;
    private int u;
    private HealthTextView v;
    private HealthTextView x;
    private HealthTextView y;
    private boolean o = true;
    private boolean l = false;

    public SportTitleWatermarkVersionTwo(Context context) {
        c(context);
        b();
    }

    private void c(Context context) {
        View inflate = View.inflate(context, R.layout.sport_title_watermark_layout_v2, null);
        this.s = inflate;
        this.t = (HealthTextView) inflate.findViewById(R.id.top_right_first_data);
        this.x = (HealthTextView) this.s.findViewById(R.id.top_right_second_data);
        this.y = (HealthTextView) this.s.findViewById(R.id.edit_share_detail_title_device_name);
        this.m = (HealthTextView) this.s.findViewById(R.id.main_data_value);
        this.k = (HealthTextView) this.s.findViewById(R.id.main_data_unit);
        this.i = (HealthTextView) this.s.findViewById(R.id.bottom_start_title);
        this.f = (HealthTextView) this.s.findViewById(R.id.bottom_start_value);
        this.j = (HealthTextView) this.s.findViewById(R.id.bottom_start_unit);
        this.d = (HealthTextView) this.s.findViewById(R.id.bottom_center_title);
        this.c = (HealthTextView) this.s.findViewById(R.id.bottom_center_value);
        this.f8548a = (HealthTextView) this.s.findViewById(R.id.bottom_center_unit);
        this.b = (HealthTextView) this.s.findViewById(R.id.bottom_end_title);
        this.g = (HealthTextView) this.s.findViewById(R.id.bottom_end_value);
        this.h = (HealthTextView) this.s.findViewById(R.id.bottom_end_unit);
        this.v = (HealthTextView) this.s.findViewById(R.id.edit_share_detail_title_username);
        this.n = (ImageView) this.s.findViewById(R.id.track_share_short_image);
        this.q = (LinearLayout) this.s.findViewById(R.id.right_top_location_layout);
        this.r = (HealthTextView) this.s.findViewById(R.id.top_left_location_data);
    }

    private void b() {
        Typeface awk_ = fdv.awk_();
        this.m.setTypeface(awk_);
        this.f.setTypeface(awk_);
        this.c.setTypeface(awk_);
        this.g.setTypeface(awk_);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.m.setTextColor(i);
        this.k.setTextColor(i);
        this.i.setTextColor(i);
        this.f.setTextColor(i);
        this.j.setTextColor(i);
        this.d.setTextColor(i);
        this.c.setTextColor(i);
        this.f8548a.setTextColor(i);
        this.b.setTextColor(i);
        this.g.setTextColor(i);
        this.h.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.x.setTextColor(i);
        this.t.setTextColor(i);
        this.v.setTextColor(i);
        this.y.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.s;
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
    public void setIsDefaultSource(boolean z) {
        this.o = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.p;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.p = str;
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
            mwd.d(this.f, i);
            mwd.b(this.t, fedVar.x());
            mwd.b(this.x, fedVar.w());
            mwd.b(this.y, fedVar.u());
            mwd.b(this.m, a2);
            mwd.b(this.k, fedVar.c());
            mwd.b(this.i, fedVar.g());
            mwd.b(this.j, fedVar.f());
            mwd.b(this.d, fedVar.h());
            mwd.b(this.c, m);
            mwd.b(this.f8548a, fedVar.l());
            mwd.b(this.b, fedVar.d());
            mwd.b(this.g, j);
            mwd.b(this.h, fedVar.e());
            mwd.b(this.r, fedVar.t());
            this.q.setVisibility(TextUtils.isEmpty(fedVar.t()) ? 8 : 0);
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.l;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.u = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.u;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.n.setVisibility(8);
            this.v.setVisibility(8);
            return;
        }
        this.n.setVisibility(Utils.l() ? 8 : 0);
        if (bitmap != null) {
            this.n.setImageBitmap(bitmap);
        }
        if (!TextUtils.isEmpty(str)) {
            this.v.setText(str);
            this.v.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.v);
        }
    }
}
