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
import defpackage.koq;
import defpackage.mwd;
import defpackage.nrf;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class TrackSimpleViewVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8552a;
    private ImageView b;
    private int c;
    private HealthTextView g;
    private View h;
    private TrackSimpleView i;
    private String j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private ImageView o;
    private int p;
    private int f = 3;
    private boolean e = true;
    private boolean d = false;

    public TrackSimpleViewVersionTwo(Context context) {
        b(context);
        a();
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.track_share_watermark_v2, null);
        this.h = inflate;
        this.m = (HealthTextView) inflate.findViewById(R.id.right_top_first_data);
        this.k = (HealthTextView) this.h.findViewById(R.id.right_top_second_data);
        this.l = (HealthTextView) this.h.findViewById(R.id.edit_share_detail_title_device_name);
        this.g = (HealthTextView) this.h.findViewById(R.id.track_share_watermark_main_data_value);
        this.f8552a = (HealthTextView) this.h.findViewById(R.id.track_share_watermark_main_data_unit);
        this.i = (TrackSimpleView) this.h.findViewById(R.id.track_share_simple_track);
        this.o = (ImageView) this.h.findViewById(R.id.track_share_bitmap_track);
        this.n = (HealthTextView) this.h.findViewById(R.id.edit_share_detail_title_username);
        this.b = (ImageView) this.h.findViewById(R.id.track_share_short_image);
        this.i.e(8.0f);
        if (LanguageUtil.b(context)) {
            this.f = 2;
        }
    }

    private void a() {
        this.g.setTypeface(fdv.awk_());
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.g.setTextColor(i);
        this.f8552a.setTextColor(i);
        this.i.a(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.h;
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
    public final void refreshTopUi(int i) {
        this.m.setTextColor(i);
        this.k.setTextColor(i);
        this.l.setTextColor(i);
        this.n.setTextColor(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public String getSourcePath() {
        return this.j;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.j = str;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setIsDefaultSource(boolean z) {
        this.e = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.e;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar == null) {
            LogUtil.e("Share_TrackSimpleViewVersionTwo", "saveData trackShareWaterMarkBean is null");
            return;
        }
        if (fedVar.k() == 1107) {
            e(fedVar);
        } else if (fedVar.k() == 1168) {
            d(fedVar);
        } else {
            LogUtil.e("Share_TrackSimpleViewVersionTwo", "saveData trackShareWaterMarkBean no branch");
        }
    }

    private void e(fed fedVar) {
        if (koq.b(fedVar.p())) {
            this.d = true;
            LogUtil.e("Share_TrackSimpleViewVersionTwo", "saveData points is empty");
            return;
        }
        String a2 = fedVar.a();
        if (mwd.b(a2)) {
            this.d = true;
            return;
        }
        this.o.setVisibility(8);
        this.i.setVisibility(0);
        mwd.b(this.m, fedVar.x());
        mwd.b(this.k, fedVar.w());
        mwd.b(this.l, fedVar.u());
        mwd.b(this.g, a2);
        mwd.b(this.f8552a, fedVar.c());
        this.i.b(fedVar.p(), this.f);
    }

    private void d(fed fedVar) {
        Bitmap cHB_ = nrf.cHB_(fedVar.q());
        if (cHB_ == null) {
            this.d = true;
            LogUtil.e("Share_TrackSimpleViewVersionTwo", "refreshBitmapData thumbnail is empty");
            return;
        }
        String a2 = fedVar.a();
        if (mwd.b(a2)) {
            this.d = true;
            return;
        }
        this.o.setVisibility(0);
        this.i.setVisibility(8);
        mwd.b(this.m, nsf.j(R.string._2130847254_res_0x7f022616));
        mwd.b(this.k, fedVar.w());
        mwd.b(this.l, fedVar.u());
        mwd.b(this.g, a2);
        mwd.b(this.f8552a, fedVar.c());
        this.o.setImageBitmap(cHB_);
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
        return this.d;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.b.setVisibility(8);
            this.n.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.b.setImageBitmap(bitmap);
            this.b.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.n.setText(str);
            this.n.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.n);
        }
    }
}
