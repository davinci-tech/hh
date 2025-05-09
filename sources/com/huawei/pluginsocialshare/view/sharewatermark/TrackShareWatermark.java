package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fed;
import defpackage.koq;
import defpackage.mwd;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class TrackShareWatermark extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private int f8550a;
    private HealthTextView b;
    private HealthTextView d;
    private String g;
    private HealthTextView h;
    private TrackSimpleView i;
    private View j;
    private HealthTextView k;
    private HealthTextView n;
    private int o;
    private int f = 2;
    private boolean e = true;
    private boolean c = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
    }

    public TrackShareWatermark(Context context) {
        d(context);
        a(context);
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.track_share_watermark, null);
        this.j = inflate;
        this.h = (HealthTextView) inflate.findViewById(R.id.right_top_first_data);
        this.n = (HealthTextView) this.j.findViewById(R.id.right_top_second_data);
        this.k = (HealthTextView) this.j.findViewById(R.id.right_top_third_data);
        this.d = (HealthTextView) this.j.findViewById(R.id.track_share_watermark_main_data_value);
        this.b = (HealthTextView) this.j.findViewById(R.id.track_share_watermark_main_data_unit);
        TrackSimpleView trackSimpleView = (TrackSimpleView) this.j.findViewById(R.id.track_share_simple_track);
        this.i = trackSimpleView;
        trackSimpleView.e(8.0f);
        if (LanguageUtil.b(context)) {
            this.f = 3;
        }
    }

    private void a(Context context) {
        this.d.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf"));
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.d.setTextColor(i);
        this.b.setTextColor(i);
        this.i.a(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.j;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getBitmap() {
        return this.f8550a;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setBitmap(int i) {
        this.f8550a = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.h.setTextColor(i);
        this.n.setTextColor(i);
        this.k.setTextColor(i);
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
        this.e = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.e;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar == null) {
            LogUtil.e("Share_TrackShareWatermark", "saveData trackShareWaterMarkBean is null");
            return;
        }
        if (koq.b(fedVar.p())) {
            this.c = true;
            LogUtil.e("Share_TrackShareWatermark", "saveData points is empty");
            return;
        }
        String a2 = fedVar.a();
        if (mwd.b(a2)) {
            this.c = true;
            return;
        }
        mwd.b(this.h, fedVar.x());
        mwd.b(this.n, fedVar.w());
        mwd.b(this.k, fedVar.u());
        mwd.b(this.d, a2);
        mwd.b(this.b, fedVar.c());
        this.i.b(fedVar.p(), this.f);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.o = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.o;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.c;
    }
}
