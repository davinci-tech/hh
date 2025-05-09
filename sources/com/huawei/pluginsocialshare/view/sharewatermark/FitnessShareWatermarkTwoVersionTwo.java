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
public class FitnessShareWatermarkTwoVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private int f8538a;
    private ImageView b;
    private View c;
    private boolean d = true;
    private boolean e;
    private HealthTextView f;
    private String g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private int s;
    private HealthTextView t;

    public FitnessShareWatermarkTwoVersionTwo(Context context) {
        e(context);
        a();
    }

    private void e(Context context) {
        View inflate = View.inflate(context, R.layout.fitness_watermask_two_v2, null);
        this.c = inflate;
        this.p = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.r = (HealthTextView) this.c.findViewById(R.id.top_left_second_data);
        this.t = (HealthTextView) this.c.findViewById(R.id.top_left_third_data);
        this.i = (HealthTextView) this.c.findViewById(R.id.second_line_start_data_title);
        this.h = (HealthTextView) this.c.findViewById(R.id.second_line_start_data_value);
        this.j = (HealthTextView) this.c.findViewById(R.id.second_line_end_data_value);
        this.f = (HealthTextView) this.c.findViewById(R.id.second_line_end_data_title);
        this.l = (HealthTextView) this.c.findViewById(R.id.third_line_start_data_title);
        this.o = (HealthTextView) this.c.findViewById(R.id.third_line_start_data_value);
        this.m = (HealthTextView) this.c.findViewById(R.id.third_line_end_data_title);
        this.k = (HealthTextView) this.c.findViewById(R.id.third_line_end_data_value);
        this.q = (HealthTextView) this.c.findViewById(R.id.edit_share_detail_title_username);
        this.b = (ImageView) this.c.findViewById(R.id.track_share_short_image);
        this.n = (HealthTextView) this.c.findViewById(R.id.edit_share_detail_title_device_name);
    }

    private void a() {
        this.h.setTypeface(fdv.awk_());
        this.j.setTypeface(fdv.awk_());
        this.o.setTypeface(fdv.awk_());
        this.k.setTypeface(fdv.awk_());
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.h.setTextColor(i);
        this.i.setTextColor(i);
        this.j.setTextColor(i);
        this.f.setTextColor(i);
        this.o.setTextColor(i);
        this.l.setTextColor(i);
        this.k.setTextColor(i);
        this.m.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.p.setTextColor(i);
        this.r.setTextColor(i);
        this.t.setTextColor(i);
        this.q.setTextColor(i);
        this.n.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.c;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.f8538a;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.f8538a = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.g;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.g = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.d = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.d;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            String j = fedVar.j();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m) || mwd.b(j)) {
                this.e = true;
                return;
            }
            mwd.b(this.p, fedVar.r());
            mwd.b(this.r, fedVar.s());
            mwd.b(this.t, fedVar.v());
            mwd.b(this.i, fedVar.b());
            mwd.b(this.h, a2);
            mwd.b(this.f, fedVar.g());
            mwd.b(this.j, i);
            mwd.b(this.l, fedVar.h());
            mwd.b(this.o, m);
            mwd.b(this.m, fedVar.d());
            mwd.b(this.k, j);
            mwd.b(this.n, fedVar.u());
        }
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.s = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.s;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.e;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.b.setVisibility(8);
            this.q.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.b.setImageBitmap(bitmap);
            this.b.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.q.setText(str);
            this.q.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.q);
        }
    }
}
