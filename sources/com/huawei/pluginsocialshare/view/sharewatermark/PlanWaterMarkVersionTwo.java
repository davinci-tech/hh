package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fdv;
import defpackage.fed;
import defpackage.mwd;

/* loaded from: classes6.dex */
public class PlanWaterMarkVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8541a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private int e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private ImageView j;
    private HealthTextView k;
    private View m;
    private String n;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private int u;
    private boolean o = true;
    private boolean l = false;

    public PlanWaterMarkVersionTwo(Context context) {
        c(context);
        a();
    }

    private void a() {
        Typeface awk_ = fdv.awk_();
        this.p.setTypeface(awk_);
        this.h.setTypeface(awk_);
        this.f8541a.setTypeface(awk_);
        this.k.setTypeface(awk_);
    }

    private void c(Context context) {
        View inflate = View.inflate(context, R.layout.plan_watermark_v2, null);
        this.m = inflate;
        this.k = (HealthTextView) inflate.findViewById(R.id.left_top_first);
        this.r = (HealthTextView) this.m.findViewById(R.id.left_top_forth);
        this.d = (HealthTextView) this.m.findViewById(R.id.left_bottom_first);
        this.q = (HealthTextView) this.m.findViewById(R.id.right_top_first);
        this.p = (HealthTextView) this.m.findViewById(R.id.right_top_second);
        this.s = (HealthTextView) this.m.findViewById(R.id.right_top_third);
        this.g = (HealthTextView) this.m.findViewById(R.id.right_center_first);
        this.h = (HealthTextView) this.m.findViewById(R.id.right_center_second);
        this.i = (HealthTextView) this.m.findViewById(R.id.right_center_third);
        this.b = (HealthTextView) this.m.findViewById(R.id.right_bottom_first);
        this.f8541a = (HealthTextView) this.m.findViewById(R.id.right_bottom_second);
        this.c = (HealthTextView) this.m.findViewById(R.id.right_bottom_third);
        this.t = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_username);
        this.j = (ImageView) this.m.findViewById(R.id.track_share_short_image);
        this.f = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_device_name);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshUi(int i, int i2) {
        this.k.setTextColor(i);
        this.r.setTextColor(i);
        this.d.setTextColor(i);
        this.q.setTextColor(i);
        this.p.setTextColor(i);
        this.s.setTextColor(i);
        this.g.setTextColor(i);
        this.h.setTextColor(i);
        this.i.setTextColor(i);
        this.b.setTextColor(i);
        this.f8541a.setTextColor(i);
        this.c.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshTopUi(int i) {
        this.f.setTextColor(i);
        this.t.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.m;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.e = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.e;
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
        this.o = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar != null) {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            String str = fedVar.r() + " ";
            String s = fedVar.s();
            if (mwd.b(a2) || mwd.b(i) || mwd.b(m)) {
                this.l = true;
                return;
            }
            this.k.setText(mwd.cqH_(str, s, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362718_res_0x7f0a039e)));
            mwd.b(this.r, fedVar.x());
            mwd.b(this.d, fedVar.w());
            mwd.b(this.q, fedVar.b());
            mwd.b(this.p, a2);
            mwd.b(this.s, fedVar.c());
            mwd.b(this.g, fedVar.g());
            mwd.b(this.h, i);
            mwd.b(this.i, fedVar.f());
            mwd.b(this.b, fedVar.h());
            mwd.b(this.f8541a, m);
            mwd.b(this.c, fedVar.l());
            mwd.b(this.f, "");
        }
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
    public boolean getIsNeedHide() {
        return this.l;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.j.setVisibility(8);
            this.t.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.j.setImageBitmap(bitmap);
            this.j.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.t.setText(str);
            this.t.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.t);
        }
    }
}
