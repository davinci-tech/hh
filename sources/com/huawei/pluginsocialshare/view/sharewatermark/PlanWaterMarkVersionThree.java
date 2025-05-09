package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
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
public class PlanWaterMarkVersionThree extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8540a;
    private HealthTextView b;
    private HealthTextView c;
    private int d;
    private HealthTextView e;
    private HealthTextView f;
    private ImageView g;
    private View j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private String o;
    private int p;
    private HealthTextView q;
    private boolean i = true;
    private boolean h = false;

    public PlanWaterMarkVersionThree(Context context) {
        e(context);
        a();
    }

    private void a() {
        Typeface awk_ = fdv.awk_();
        this.f8540a.setTypeface(awk_);
        this.c.setTypeface(awk_);
    }

    private void e(Context context) {
        View inflate = View.inflate(context, R.layout.plan_watermark_v3, null);
        this.j = inflate;
        this.l = (HealthTextView) inflate.findViewById(R.id.top_left_first_data);
        this.n = (HealthTextView) this.j.findViewById(R.id.top_left_second_data);
        this.c = (HealthTextView) this.j.findViewById(R.id.left_buttom_first);
        this.b = (HealthTextView) this.j.findViewById(R.id.left_buttom_second);
        this.m = (HealthTextView) this.j.findViewById(R.id.left_top_first);
        this.k = (HealthTextView) this.j.findViewById(R.id.right_top_first);
        this.f8540a = (HealthTextView) this.j.findViewById(R.id.right_bottom_first);
        this.e = (HealthTextView) this.j.findViewById(R.id.right_bottom_second);
        this.q = (HealthTextView) this.j.findViewById(R.id.edit_share_detail_title_username);
        this.g = (ImageView) this.j.findViewById(R.id.track_share_short_image);
        this.f = (HealthTextView) this.j.findViewById(R.id.edit_share_detail_title_device_name);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshUi(int i, int i2) {
        this.l.setTextColor(i);
        this.n.setTextColor(i);
        this.c.setTextColor(i);
        this.b.setTextColor(i);
        this.m.setTextColor(i);
        this.k.setTextColor(i);
        this.f8540a.setTextColor(i);
        this.e.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshTopUi(int i) {
        this.f.setTextColor(i);
        this.q.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.j;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.d = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.d;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.o = str;
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
                this.h = true;
                return;
            }
            mwd.b(this.l, fedVar.x());
            mwd.b(this.n, fedVar.w());
            mwd.b(this.c, a2);
            mwd.b(this.b, fedVar.c());
            mwd.b(this.m, fedVar.b());
            mwd.b(this.k, fedVar.g());
            mwd.b(this.f8540a, i);
            mwd.b(this.e, fedVar.f());
            mwd.b(this.f, fedVar.u());
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
        return this.h;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.g.setVisibility(8);
            this.q.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.g.setImageBitmap(bitmap);
            this.g.setVisibility(0);
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
