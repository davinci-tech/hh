package com.huawei.pluginsocialshare.view.sharewatermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.view.threeCircle.ThreeCircleView;
import defpackage.fed;
import defpackage.mwd;
import defpackage.nrn;
import defpackage.nsn;
import defpackage.ntp;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.LinkedHashMap;

/* loaded from: classes6.dex */
public class ThreeCircleShareWatermark extends EditShareCommonView {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8549a;
    private HealthTextView aa;
    private int b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private ImageView g;
    private HealthTextView h;
    private HealthTextView i;
    private RelativeLayout j;
    private LinearLayout k;
    private View m;
    private HealthTextView n;
    private String o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private RelativeLayout x;
    private ThreeCircleView y;
    private int z;
    private boolean f = true;
    private boolean l = false;

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public final void refreshUi(int i, int i2) {
    }

    public ThreeCircleShareWatermark(Context context) {
        c(context);
        b(context);
    }

    private void c(Context context) {
        View inflate = View.inflate(context, R.layout.three_circle_watermark_layout, null);
        this.m = inflate;
        this.k = (LinearLayout) inflate.findViewById(R.id.ll_perfect_circle_data);
        this.n = (HealthTextView) this.m.findViewById(R.id.tv_perfect_circle_data);
        this.s = (HealthTextView) this.m.findViewById(R.id.tv_date);
        this.aa = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_username);
        this.g = (ImageView) this.m.findViewById(R.id.track_share_short_image);
        this.u = (HealthTextView) this.m.findViewById(R.id.edit_share_detail_title_device_name);
        this.y = (ThreeCircleView) this.m.findViewById(R.id.three_circle_layout_container);
        this.g.setVisibility(Utils.l() ? 8 : 0);
        this.x = (RelativeLayout) this.m.findViewById(R.id.step_three_circle_date_layout);
        this.p = (HealthTextView) this.m.findViewById(R.id.tv_sport_intensity_time);
        this.r = (HealthTextView) this.m.findViewById(R.id.tv_sport_intensity_time_unit);
        this.v = (HealthTextView) this.m.findViewById(R.id.tv_steps);
        this.w = (HealthTextView) this.m.findViewById(R.id.tv_steps_unit);
        this.t = (HealthTextView) this.m.findViewById(R.id.tv_sport_hours);
        this.q = (HealthTextView) this.m.findViewById(R.id.tv_sport_hours_unit);
        this.j = (RelativeLayout) this.m.findViewById(R.id.calorie_three_circle_date_layout);
        this.d = (HealthTextView) this.m.findViewById(R.id.tv_active_caloric);
        this.c = (HealthTextView) this.m.findViewById(R.id.tv_active_caloric_unit);
        if (LanguageUtil.g(context) || LanguageUtil.f(context)) {
            this.c.setAutoTextInfo(10, 1, 1);
            this.c.setMaxWidth(context.getResources().getDimensionPixelSize(R.dimen._2131363026_res_0x7f0a04d2));
        }
        this.e = (HealthTextView) this.m.findViewById(R.id.tv_active_workout);
        this.f8549a = (HealthTextView) this.m.findViewById(R.id.tv_active_workout_unit);
        this.h = (HealthTextView) this.m.findViewById(R.id.tv_hours);
        this.i = (HealthTextView) this.m.findViewById(R.id.tv_hours_unit);
    }

    private void a() {
        ntp.b bVar = new ntp.b();
        bVar.b(new int[]{nrn.d(R.color._2131296933_res_0x7f0902a5), nrn.d(R.color._2131296933_res_0x7f0902a5)}).c(new int[]{nrn.d(R.color._2131299154_res_0x7f090b52), nrn.d(R.color._2131299153_res_0x7f090b51)}).e();
        ntp ntpVar = new ntp(bVar);
        ntp.b bVar2 = new ntp.b();
        bVar2.b(new int[]{nrn.d(R.color._2131296933_res_0x7f0902a5), nrn.d(R.color._2131296933_res_0x7f0902a5)}).c(new int[]{nrn.d(R.color._2131299158_res_0x7f090b56), nrn.d(R.color._2131299157_res_0x7f090b55)}).e();
        ntp ntpVar2 = new ntp(bVar2);
        ntp.b bVar3 = new ntp.b();
        bVar3.b(new int[]{nrn.d(R.color._2131296933_res_0x7f0902a5), nrn.d(R.color._2131296933_res_0x7f0902a5)}).c(new int[]{nrn.d(R.color._2131296457_res_0x7f0900c9), nrn.d(R.color._2131296456_res_0x7f0900c8)}).e();
        ntp ntpVar3 = new ntp(bVar3);
        LinkedHashMap<String, ntp> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("firstCircle", ntpVar);
        linkedHashMap.put("secondCircle", ntpVar2);
        linkedHashMap.put("thirdCircle", ntpVar3);
        this.y.a(linkedHashMap);
    }

    private void b(Context context) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fonts/hw-italic.ttf");
        this.p.setTypeface(createFromAsset);
        this.v.setTypeface(createFromAsset);
        this.d.setTypeface(createFromAsset);
        this.e.setTypeface(createFromAsset);
        this.h.setTypeface(createFromAsset);
        this.t.setTypeface(createFromAsset);
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
        this.aa.setTextColor(i);
        this.u.setTextColor(i);
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
        this.f = z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsDefaultSource() {
        return this.f;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void refreshData(fed fedVar) {
        if (fedVar == null) {
            ReleaseLogUtil.c("Share_ThreeCircleShareWatermark", "saveData sportShareWatermarkBean is null");
            return;
        }
        if ("0".equals(fedVar.o())) {
            this.x.setVisibility(0);
            a();
            d(fedVar);
        } else {
            this.j.setVisibility(0);
            a(fedVar);
        }
        mwd.b(this.s, fedVar.w());
        mwd.b(this.u, fedVar.u());
        c(fedVar.r());
    }

    private void a(fed fedVar) {
        String str = "0";
        try {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            mwd.b(this.d, d(a2));
            mwd.b(this.c, fedVar.c());
            mwd.b(this.e, d(i));
            mwd.b(this.f8549a, fedVar.f());
            mwd.b(this.h, d(m));
            mwd.b(this.i, fedVar.l());
            if (a2.length() > 3 || nsn.a(2.0f)) {
                b(12.0f);
            }
            String b = "0".equals(a2) ? "0" : fedVar.b();
            String g = "0".equals(i) ? "0" : fedVar.g();
            if (!"0".equals(m)) {
                str = fedVar.h();
            }
            ReleaseLogUtil.e("Share_ThreeCircleShareWatermark", "refreshCalorieCircleData caloric: ", a2, "/", b, ", activeWorkout: ", i, "/", g, ", hours: ", m, "/", str);
            this.y.c("firstCircle", Integer.parseInt(a2), Integer.parseInt(b));
            this.y.c("secondCircle", Integer.parseInt(i), Integer.parseInt(g));
            this.y.c("thirdCircle", Integer.parseInt(m), Integer.parseInt(str));
        } catch (NumberFormatException e) {
            LogUtil.e("Share_ThreeCircleShareWatermark", "refreshCalorieCircleData exception: ", e.getMessage());
        }
    }

    private void d(fed fedVar) {
        String str = "0";
        try {
            String a2 = fedVar.a();
            String i = fedVar.i();
            String m = fedVar.m();
            mwd.b(this.p, d(i));
            mwd.b(this.r, fedVar.f());
            mwd.b(this.v, d(a2));
            mwd.b(this.w, fedVar.c());
            mwd.b(this.t, d(m));
            mwd.b(this.q, fedVar.l());
            if (a2.length() > 5) {
                b(11.0f);
            } else if (a2.length() > 4 || nsn.a(2.0f)) {
                b(12.0f);
            }
            String b = "0".equals(a2) ? "0" : fedVar.b();
            String g = "0".equals(i) ? "0" : fedVar.g();
            if (!"0".equals(m)) {
                str = fedVar.h();
            }
            ReleaseLogUtil.e("Share_ThreeCircleShareWatermark", "refreshStepCircleData step: ", a2, "/", b, ", strength: ", i, "/", g, ", hours: ", m, "/", str);
            this.y.c("firstCircle", Integer.parseInt(a2), Integer.parseInt(b));
            this.y.c("secondCircle", Integer.parseInt(i), Integer.parseInt(g));
            this.y.c("thirdCircle", Integer.parseInt(m), Integer.parseInt(str));
        } catch (NumberFormatException e) {
            LogUtil.e("Share_ThreeCircleShareWatermark", "refreshStepCircleData exception: ", e.getMessage());
        }
    }

    private void b(float f) {
        this.p.setTextSize(1, f);
        this.v.setTextSize(1, f);
        this.h.setTextSize(1, f);
        this.d.setTextSize(1, f);
        this.e.setTextSize(1, f);
        this.t.setTextSize(1, f);
    }

    private void c(String str) {
        try {
            if (TextUtils.isEmpty(str) || Integer.parseInt(str) <= 0) {
                return;
            }
            ReleaseLogUtil.e("Share_ThreeCircleShareWatermark", "perfectDay: ", str);
            mwd.b(this.n, crq_(R.plurals._2130903513_res_0x7f0301d9, str));
            this.k.setVisibility(0);
        } catch (NumberFormatException e) {
            LogUtil.e("Share_ThreeCircleShareWatermark", "setPerfectDays exception: ", e.getMessage());
        }
    }

    private SpannableString crq_(int i, String str) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("Share_ThreeCircleShareWatermark", "getSpannableString value is null");
            return new SpannableString("");
        }
        try {
            i2 = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.e("Share_ThreeCircleShareWatermark", "getSpannableString exception: ", e.getMessage());
            i2 = 0;
        }
        return crr_(str, BaseApplication.getContext().getResources().getQuantityString(i, i2, Integer.valueOf(i2)));
    }

    private SpannableString crr_(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("Share_ThreeCircleShareWatermark", "getSpannableString content or part is null");
            return new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(str2);
        int indexOf = str2.indexOf(str);
        if (indexOf == -1) {
            LogUtil.c("Share_ThreeCircleShareWatermark", "getSpannableString content not contains part");
            return spannableString;
        }
        spannableString.setSpan(new AbsoluteSizeSpan(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d)), indexOf, str.length() + indexOf, 34);
        return spannableString;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setWatermarkId(int i) {
        this.z = i;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public int getWatermarkId() {
        return this.z;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public boolean getIsNeedHide() {
        return this.l;
    }

    @Override // com.huawei.health.socialshare.model.EditShareCommonView
    public void setUserInfo(Bitmap bitmap, String str) {
        if (bitmap == null && TextUtils.isEmpty(str)) {
            this.g.setVisibility(8);
            this.aa.setVisibility(8);
            return;
        }
        if (bitmap != null) {
            this.g.setImageBitmap(bitmap);
            this.g.setVisibility(Utils.l() ? 8 : 0);
        }
        if (!TextUtils.isEmpty(str)) {
            this.aa.setText(str);
            this.aa.setVisibility(0);
        }
        if (mwd.a()) {
            mwd.a(this.aa);
        }
    }

    private CharSequence d(String str) {
        try {
            return UnitUtil.e(Integer.parseInt(str), 1, 0);
        } catch (NumberFormatException e) {
            LogUtil.e("Share_ThreeCircleShareWatermark", "getNumberFormat exception: ", e.getMessage());
            return "";
        }
    }
}
