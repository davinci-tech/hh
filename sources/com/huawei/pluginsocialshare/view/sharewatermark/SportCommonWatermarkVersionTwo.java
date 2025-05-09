package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.fdv;
import defpackage.fed;
import defpackage.koq;
import defpackage.mwd;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class SportCommonWatermarkVersionTwo extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8545a;
    private HealthTextView aa;
    private String ab;
    private TrackSimpleView ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private int af;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthTextView ai;
    private HealthTextView ak;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private int e;
    private ImageView f;
    private HealthTextView g;
    private boolean h;
    private HealthTextView i;
    private HealthTextView j;
    private boolean k = false;
    private LinearLayout l;
    private HealthTextView m;
    private LinearLayout n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private LinearLayout r;
    private View s;
    private HealthTextView t;
    private HealthTextView u;
    private LinearLayout v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private HealthTextView z;

    public SportCommonWatermarkVersionTwo(Context context) {
        a(context);
        c();
    }

    private void a(Context context) {
        View inflate = View.inflate(context, R.layout.sport_common_watermark_layout_v2, null);
        this.s = inflate;
        this.ah = (HealthTextView) inflate.findViewById(R.id.first_line_first_data);
        this.ai = (HealthTextView) this.s.findViewById(R.id.first_line_second_data);
        this.l = (LinearLayout) this.s.findViewById(R.id.first_line_data_layout);
        this.v = (LinearLayout) this.s.findViewById(R.id.second_line_start_data_lin);
        this.r = (LinearLayout) this.s.findViewById(R.id.outdoor_adventures_title_lin);
        this.p = (HealthTextView) this.s.findViewById(R.id.outdoor_adventures_title);
        this.o = (HealthTextView) this.s.findViewById(R.id.outdoor_adventures_time);
        this.m = (HealthTextView) this.s.findViewById(R.id.outdoor_adventures_short_language);
        this.ak = (HealthTextView) this.s.findViewById(R.id.third_line_end_data_unit);
        this.g = (HealthTextView) this.s.findViewById(R.id.duration_unit_day);
        this.i = (HealthTextView) this.s.findViewById(R.id.duration_unit_hours);
        this.j = (HealthTextView) this.s.findViewById(R.id.duration_unit_minute);
        this.d = (HealthTextView) this.s.findViewById(R.id.duration_day_hour);
        this.c = (HealthTextView) this.s.findViewById(R.id.duration_minute);
        this.b = (HealthTextView) this.s.findViewById(R.id.duration_day);
        this.n = (LinearLayout) this.s.findViewById(R.id.outdoor_adventures_time_lin);
        this.y = (HealthTextView) this.s.findViewById(R.id.second_line_start_data_title);
        this.u = (HealthTextView) this.s.findViewById(R.id.second_line_start_data_value);
        this.x = (HealthTextView) this.s.findViewById(R.id.second_line_start_data_unit);
        this.t = (HealthTextView) this.s.findViewById(R.id.second_line_end_data_value);
        this.q = (HealthTextView) this.s.findViewById(R.id.second_line_end_data_title);
        this.w = (HealthTextView) this.s.findViewById(R.id.second_line_end_data_unit);
        this.ag = (HealthTextView) this.s.findViewById(R.id.third_line_start_data_title);
        this.z = (HealthTextView) this.s.findViewById(R.id.third_line_start_data_value);
        this.aa = (HealthTextView) this.s.findViewById(R.id.third_line_end_data_title);
        this.ad = (HealthTextView) this.s.findViewById(R.id.third_line_end_data_value);
        this.ae = (HealthTextView) this.s.findViewById(R.id.edit_share_detail_title_username);
        this.f = (ImageView) this.s.findViewById(R.id.track_share_short_image);
        this.f8545a = (HealthTextView) this.s.findViewById(R.id.edit_share_detail_title_device_name);
        this.ac = (TrackSimpleView) this.s.findViewById(R.id.adventures_share_simple_track);
    }

    private void c() {
        Typeface awk_ = fdv.awk_();
        this.u.setTypeface(awk_);
        this.t.setTypeface(awk_);
        this.z.setTypeface(awk_);
        this.d.setTypeface(awk_);
        this.c.setTypeface(awk_);
        this.b.setTypeface(awk_);
        this.ad.setTypeface(awk_);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
        this.p.setTextColor(i);
        this.o.setTextColor(i);
        this.m.setTextColor(i);
        this.ah.setTextColor(i);
        this.ai.setTextColor(i);
        this.u.setTextColor(i);
        this.y.setTextColor(i);
        this.x.setTextColor(i);
        this.t.setTextColor(i);
        this.q.setTextColor(i);
        this.w.setTextColor(i);
        this.z.setTextColor(i);
        this.ag.setTextColor(i);
        this.ad.setTextColor(i);
        this.aa.setTextColor(i);
        this.ak.setTextColor(i);
        this.d.setTextColor(i);
        this.c.setTextColor(i);
        this.b.setTextColor(i);
        this.g.setTextColor(i);
        this.i.setTextColor(i);
        this.j.setTextColor(i);
        this.ac.a(i);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshTopUi(int i) {
        this.ae.setTextColor(i);
        this.f8545a.setTextColor(i);
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
    public String getSourcePath() {
        return this.ab;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setSourcePath(String str) {
        this.ab = str;
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
            String m = fedVar.m();
            String j = fedVar.j();
            int k = fedVar.k();
            boolean z = k == 1166 || k == 1167;
            if ((mwd.b(a2) || mwd.b(i) || mwd.b(m) || mwd.b(j)) && !z) {
                this.k = true;
                return;
            }
            if (koq.b(fedVar.p()) && k == 1167) {
                LogUtil.h("Share_SportCommonWatermarkVersionTwo", "refreshData points is empty");
                this.k = true;
                return;
            }
            this.q.setVisibility(mwd.b(i) ? 8 : 0);
            this.t.setVisibility(mwd.b(i) ? 8 : 0);
            mwd.b(this.q, fedVar.g());
            mwd.c(this.t, i);
            mwd.b(this.w, fedVar.f());
            mwd.b(this.aa, fedVar.d());
            mwd.b(this.ag, fedVar.h());
            mwd.b(this.ad, j);
            mwd.b(this.f8545a, fedVar.u());
            if (z) {
                a(fedVar);
            } else {
                e(fedVar, a2, m);
            }
        }
    }

    private void a(fed fedVar) {
        this.l.setVisibility(8);
        this.z.setVisibility(8);
        this.v.setVisibility(8);
        this.r.setVisibility(0);
        this.n.setVisibility(0);
        this.ak.setVisibility(0);
        mwd.b(this.ak, fedVar.e());
        mwd.b(this.p, fedVar.b());
        mwd.b(this.o, fedVar.a());
        String t = fedVar.t();
        if (LanguageUtil.h(BaseApplication.e())) {
            LogUtil.a("Share_SportCommonWatermarkVersionTwo", "outdoorAdventuresWatermark isChinese");
            mwd.b(this.m, t);
        }
        this.ag.setVisibility(TextUtils.isEmpty(t) && fedVar.k() == 1167 ? 8 : 0);
        e(fedVar);
        b(fedVar);
    }

    private void e(fed fedVar) {
        try {
            long parseLong = Long.parseLong(fedVar.m());
            int i = (int) (parseLong / 86400000);
            int i2 = (int) ((parseLong - (86400000 * i)) / 3600000);
            int ceil = (int) Math.ceil((r0 - (3600000 * i2)) / 60000.0d);
            if (i > 0) {
                mwd.b(this.b, UnitUtil.e(i, 1, 0));
                mwd.b(this.g, fedVar.r());
            }
            if (i2 > 0) {
                mwd.b(this.d, UnitUtil.e(i2, 1, 0));
                mwd.b(this.i, fedVar.s());
            }
            if (ceil > 0) {
                mwd.b(this.c, UnitUtil.e(ceil, 1, 0));
                mwd.b(this.j, fedVar.v());
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("Share_SportCommonWatermarkVersionTwo", "setAdventuresDuration time is illegal");
            mwd.b(this.b, fedVar.m());
        }
    }

    private void b(fed fedVar) {
        this.ac.setVisibility(fedVar.y() ? 0 : 8);
        this.ac.e(8.0f);
        this.ac.b(fedVar.p(), 3);
    }

    private void e(fed fedVar, String str, String str2) {
        this.l.setVisibility(0);
        this.z.setVisibility(0);
        this.v.setVisibility(0);
        this.r.setVisibility(8);
        this.n.setVisibility(8);
        this.ak.setVisibility(8);
        mwd.b(this.ah, fedVar.x());
        mwd.b(this.ai, fedVar.w());
        mwd.b(this.y, fedVar.b());
        mwd.d(this.u, str);
        mwd.b(this.x, fedVar.c());
        mwd.b(this.z, str2);
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.af = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.af;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.k;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.f.setVisibility(8);
            this.ae.setVisibility(8);
            return;
        }
        this.f.setVisibility(Utils.l() ? 8 : 0);
        if (bitmap != null) {
            this.f.setImageBitmap(bitmap);
        }
        if (!TextUtils.isEmpty(str)) {
            this.ae.setText(str);
            this.ae.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.ae);
        }
    }
}
