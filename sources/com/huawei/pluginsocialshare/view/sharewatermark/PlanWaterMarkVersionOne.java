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
public class PlanWaterMarkVersionOne extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private int f8539a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView g;
    private ImageView h;
    private HealthTextView i;
    private HealthTextView k;
    private HealthTextView l;
    private String m;
    private HealthTextView n;
    private View o;
    private HealthTextView p;
    private int r;
    private HealthTextView s;
    private boolean j = true;
    private boolean f = false;

    public PlanWaterMarkVersionOne(Context context) {
        d(context);
        c();
    }

    private void c() {
        Typeface awk_ = fdv.awk_();
        this.p.setTypeface(awk_);
        this.i.setTypeface(awk_);
        this.e.setTypeface(awk_);
        this.l.setTypeface(awk_);
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.plan_watermark_v1, null);
        this.o = inflate;
        this.l = (HealthTextView) inflate.findViewById(R.id.left_top_first);
        this.n = (HealthTextView) this.o.findViewById(R.id.left_top_forth);
        this.d = (HealthTextView) this.o.findViewById(R.id.left_bottom_first);
        this.k = (HealthTextView) this.o.findViewById(R.id.right_top_first);
        this.p = (HealthTextView) this.o.findViewById(R.id.right_top_second);
        this.c = (HealthTextView) this.o.findViewById(R.id.right_center_first);
        this.i = (HealthTextView) this.o.findViewById(R.id.right_center_second);
        this.b = (HealthTextView) this.o.findViewById(R.id.right_bottom_first);
        this.e = (HealthTextView) this.o.findViewById(R.id.right_bottom_second);
        this.s = (HealthTextView) this.o.findViewById(R.id.edit_share_detail_title_username);
        this.h = (ImageView) this.o.findViewById(R.id.track_share_short_image);
        this.g = (HealthTextView) this.o.findViewById(R.id.edit_share_detail_title_device_name);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshUi(int i, int i2) {
        this.l.setTextColor(i);
        this.n.setTextColor(i);
        this.d.setTextColor(i);
        this.k.setTextColor(i);
        this.p.setTextColor(i);
        this.c.setTextColor(i);
        this.i.setTextColor(i);
        this.b.setTextColor(i);
        this.e.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshTopUi(int i) {
        this.s.setTextColor(i);
        this.g.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.f8539a = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.f8539a;
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
        this.j = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.j;
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
                this.f = true;
                return;
            }
            this.l.setText(mwd.cqH_(str, s, BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362718_res_0x7f0a039e)));
            mwd.b(this.n, fedVar.x());
            mwd.b(this.d, fedVar.w());
            mwd.b(this.k, fedVar.c());
            mwd.b(this.p, a2);
            mwd.b(this.c, fedVar.f());
            mwd.b(this.i, i);
            mwd.b(this.b, fedVar.l());
            mwd.b(this.e, m);
            mwd.b(this.g, "");
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
            this.h.setVisibility(8);
            this.s.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.h.setImageBitmap(bitmap);
            this.h.setVisibility(0);
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
