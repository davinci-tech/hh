package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fed;
import defpackage.mwd;
import defpackage.nrf;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class EmotionalHealthWatermark extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8532a;
    private int b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private LinearLayout g;
    private HealthTextView h;
    private RelativeLayout i;
    private HealthTextView j;
    private View m;
    private ImageView n;
    private HealthTextView o;
    private String p;
    private ImageView q;
    private HealthTextView r;
    private HealthTextView s;
    private ImageView t;
    private int v;
    private boolean l = true;
    private boolean k = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
    }

    public EmotionalHealthWatermark(Context context) {
        e(context);
        d(context);
    }

    private void e(Context context) {
        View inflate = View.inflate(context, R.layout.emotional_health_share_watermark, null);
        this.m = inflate;
        this.i = (RelativeLayout) inflate.findViewById(R.id.emotion_real);
        this.g = (LinearLayout) this.m.findViewById(R.id.emotion_pressure);
        this.c = (HealthTextView) this.m.findViewById(R.id.data_title);
        this.e = (HealthTextView) this.m.findViewById(R.id.data_value);
        this.f8532a = (HealthTextView) this.m.findViewById(R.id.data_date);
        this.o = (HealthTextView) this.m.findViewById(R.id.real_date);
        this.d = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_device_name);
        this.r = (HealthTextView) this.m.findViewById(R.id.right_top_first_data_real);
        this.t = (ImageView) this.m.findViewById(R.id.share_real_bitmap);
        this.q = (ImageView) this.m.findViewById(R.id.track_share_bitmap_track);
        this.s = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_username);
        this.n = (ImageView) this.m.findViewById(R.id.track_share_short_image);
        this.h = (HealthTextView) this.m.findViewById(R.id.emotion_high_type);
        this.f = (HealthTextView) this.m.findViewById(R.id.emotion_calm_type);
        this.j = (HealthTextView) this.m.findViewById(R.id.emotion_pressure_type);
    }

    private void d(Context context) {
        this.e.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf"));
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public View getView() {
        return this.m;
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
    public final void refreshTopUi(int i) {
        this.d.setTextColor(i);
        this.s.setTextColor(i);
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
    public void setIsDefaultSource(boolean z) {
        this.l = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.l;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar == null) {
            LogUtil.e("Share_EmotionalHealthWatermark", "saveData shareWaterMarkBean is null");
            return;
        }
        if (fedVar.k() == 1169) {
            a(fedVar);
        } else if (fedVar.k() == 1170) {
            c(fedVar);
        } else if (fedVar.k() == 1171) {
            e(fedVar);
        } else if (fedVar.k() == 1172) {
            d(fedVar);
        } else {
            LogUtil.e("Share_EmotionalHealthWatermark", "refreshData shareWaterMarkBean no branch");
        }
        e(fedVar.w(), fedVar.u());
    }

    private void a(fed fedVar) {
        Bitmap cHB_ = nrf.cHB_(fedVar.q());
        if (cHB_ == null) {
            this.k = true;
            LogUtil.e("Share_EmotionalHealthWatermark", "refreshRealData thumbnail is empty");
            return;
        }
        this.i.setVisibility(0);
        this.g.setVisibility(8);
        mwd.b(this.r, fedVar.b());
        mwd.b(this.d, fedVar.r());
        this.t.setBackground(nrf.cHq_(cHB_));
        String a2 = fedVar.a();
        if (TextUtils.isEmpty(a2) || !a2.contains(",")) {
            return;
        }
        String[] split = a2.split(",");
        if (split.length >= 3) {
            this.h.setText(split[0]);
            this.f.setText(split[1]);
            this.j.setText(split[2]);
        }
    }

    private void c(fed fedVar) {
        if (TextUtils.isEmpty(fedVar.a())) {
            this.k = true;
            return;
        }
        this.i.setVisibility(8);
        this.g.setVisibility(0);
        mwd.b(this.c, fedVar.b());
        mwd.b(this.e, fedVar.a());
        mwd.b(this.d, fedVar.r());
        this.q.setImageResource(R.drawable._2131427924_res_0x7f0b0254);
    }

    private void e(fed fedVar) {
        if (TextUtils.isEmpty(fedVar.a())) {
            this.k = true;
            return;
        }
        this.i.setVisibility(8);
        this.g.setVisibility(0);
        mwd.b(this.c, fedVar.b());
        mwd.b(this.e, fedVar.a());
        mwd.b(this.d, fedVar.r());
        this.q.setImageResource(R.drawable._2131427923_res_0x7f0b0253);
    }

    private void d(fed fedVar) {
        if (TextUtils.isEmpty(fedVar.a())) {
            this.k = true;
            return;
        }
        this.i.setVisibility(8);
        this.g.setVisibility(0);
        mwd.b(this.c, fedVar.b());
        mwd.b(this.e, fedVar.a());
        mwd.b(this.d, fedVar.r());
        this.q.setImageResource(R.drawable._2131427922_res_0x7f0b0252);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.v = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.v;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.k;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.n.setVisibility(8);
            this.s.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.n.setImageBitmap(bitmap);
            this.n.setVisibility(0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.s.setText(str);
            this.s.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.s);
        }
    }

    private void e(String str, String str2) {
        LogUtil.d("Share_EmotionalHealthWatermark", "setDateInfo dateStr:", str, " weekNum:", str2);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f8532a.setText(str);
        this.o.setText(str);
    }
}
