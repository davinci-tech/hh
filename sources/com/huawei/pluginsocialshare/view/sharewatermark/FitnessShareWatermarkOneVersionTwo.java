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
public class FitnessShareWatermarkOneVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8534a;
    private int b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private ImageView f;
    private boolean g = true;
    private boolean h = false;
    private HealthTextView i;
    private HealthTextView j;
    private View k;
    private HealthTextView l;
    private String m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView s;
    private int t;

    public FitnessShareWatermarkOneVersionTwo(Context context) {
        b(context);
        d();
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.fitness_watermak_one_v2, null);
        this.k = inflate;
        this.n = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.o = (HealthTextView) this.k.findViewById(R.id.top_left_second_data);
        this.p = (HealthTextView) this.k.findViewById(R.id.top_left_third_data);
        this.i = (HealthTextView) this.k.findViewById(R.id.bottom_start_title);
        this.j = (HealthTextView) this.k.findViewById(R.id.bottom_start_value);
        this.c = (HealthTextView) this.k.findViewById(R.id.bottom_center_title);
        this.d = (HealthTextView) this.k.findViewById(R.id.bottom_center_value);
        this.f8534a = (HealthTextView) this.k.findViewById(R.id.bottom_end_title);
        this.e = (HealthTextView) this.k.findViewById(R.id.bottom_end_value);
        this.s = (HealthTextView) this.k.findViewById(R.id.edit_share_detail_title_username);
        this.f = (ImageView) this.k.findViewById(R.id.track_share_short_image);
        this.l = (HealthTextView) this.k.findViewById(R.id.edit_share_detail_title_device_name);
    }

    private void d() {
        this.j.setTypeface(fdv.awk_());
        this.d.setTypeface(fdv.awk_());
        this.e.setTypeface(fdv.awk_());
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.i.setTextColor(i);
        this.j.setTextColor(i);
        this.c.setTextColor(i);
        this.d.setTextColor(i);
        this.f8534a.setTextColor(i);
        this.e.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.p.setTextColor(i);
        this.n.setTextColor(i);
        this.o.setTextColor(i);
        this.s.setTextColor(i);
        this.l.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.k;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.b;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.b = i;
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
            String m = fedVar.m();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m)) {
                this.h = true;
                return;
            }
            mwd.b(this.n, fedVar.r());
            mwd.b(this.o, fedVar.s());
            mwd.b(this.p, fedVar.v());
            mwd.b(this.i, fedVar.b());
            mwd.b(this.j, a2);
            mwd.b(this.c, fedVar.g());
            mwd.b(this.d, i);
            mwd.b(this.f8534a, fedVar.h());
            mwd.b(this.e, m);
            mwd.b(this.l, fedVar.u());
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

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.f.setVisibility(8);
            this.s.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.f.setImageBitmap(bitmap);
            this.f.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.s.setText(str);
            this.s.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.s);
        }
    }
}
