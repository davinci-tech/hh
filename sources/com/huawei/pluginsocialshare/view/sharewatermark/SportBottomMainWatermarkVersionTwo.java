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
public class SportBottomMainWatermarkVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private int f8543a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private ImageView g;
    private HealthTextView i;
    private HealthTextView j;
    private String k;
    private HealthTextView l;
    private View m;
    private LinearLayout n;
    private int p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private boolean h = true;
    private boolean o = false;

    public SportBottomMainWatermarkVersionTwo(Context context) {
        c(context);
        b();
    }

    private void c(Context context) {
        View inflate = View.inflate(context, R.layout.sport_bottom_main_layout_v2, null);
        this.m = inflate;
        this.t = (HealthTextView) inflate.findViewById(R.id.top_right_first_data);
        this.n = (LinearLayout) this.m.findViewById(R.id.right_top_location_layout);
        this.l = (HealthTextView) this.m.findViewById(R.id.top_left_location_data);
        this.s = (HealthTextView) this.m.findViewById(R.id.top_right_second_data);
        this.q = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_device_name);
        this.b = (HealthTextView) this.m.findViewById(R.id.bottom_start_title);
        this.f = (HealthTextView) this.m.findViewById(R.id.bottom_start_value);
        this.i = (HealthTextView) this.m.findViewById(R.id.bottom_start_unit);
        this.c = (HealthTextView) this.m.findViewById(R.id.bottom_end_title);
        this.d = (HealthTextView) this.m.findViewById(R.id.bottom_end_value);
        this.e = (HealthTextView) this.m.findViewById(R.id.bottom_end_unit);
        this.r = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_username);
        this.g = (ImageView) this.m.findViewById(R.id.track_share_short_image);
        this.j = (HealthTextView) this.m.findViewById(R.id.detail_title_device_name);
    }

    private void b() {
        Typeface awk_ = fdv.awk_();
        this.f.setTypeface(awk_);
        this.d.setTypeface(awk_);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.b.setTextColor(i);
        this.f.setTextColor(i);
        this.i.setTextColor(i);
        this.c.setTextColor(i);
        this.d.setTextColor(i);
        this.e.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.t.setTextColor(i);
        this.s.setTextColor(i);
        this.r.setTextColor(i);
        this.q.setTextColor(i);
        this.j.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.m;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.f8543a;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.f8543a = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.k;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.k = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.h = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.h;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            if (mwd.b(a2) || mwd.b(i)) {
                this.o = true;
                return;
            }
            mwd.d(this.f, a2);
            mwd.b(this.t, fedVar.x());
            mwd.b(this.l, fedVar.t());
            mwd.b(this.s, fedVar.w());
            mwd.b(this.b, fedVar.b());
            mwd.b(this.i, fedVar.c());
            mwd.b(this.c, fedVar.g());
            mwd.b(this.d, i);
            mwd.b(this.e, fedVar.f());
            this.n.setVisibility(TextUtils.isEmpty(fedVar.t()) ? 8 : 0);
            String u = fedVar.u();
            if (TextUtils.isEmpty(u)) {
                this.q.setVisibility(8);
            } else if (Utils.l()) {
                this.j.setVisibility(0);
                mwd.b(this.j, u);
            } else {
                mwd.b(this.q, u);
            }
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
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.g.setVisibility(8);
            this.r.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.g.setImageBitmap(bitmap);
            this.g.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.r.setText(str);
            this.r.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.r);
        }
    }
}
