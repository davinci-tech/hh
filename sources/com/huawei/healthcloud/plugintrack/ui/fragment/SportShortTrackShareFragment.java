package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.healthcloud.plugintrack.ui.view.TrackPaceColorGradientView;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.up.model.UserInfomation;
import defpackage.cwa;
import defpackage.gpn;
import defpackage.gvv;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.gxp;
import defpackage.hji;
import defpackage.hjw;
import defpackage.hpx;
import defpackage.koq;
import defpackage.kxb;
import defpackage.nrf;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class SportShortTrackShareFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3734a;
    private hjw aa = null;
    private HealthTextView ab;
    private HealthTextView ac;
    private TrackPaceColorGradientView ad;
    private HealthTextView ae;
    private HealthTextView ah;
    private LinearLayout ai;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private Context e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private ImageView k;
    private ImageView l;
    private HealthTextView m;
    private int n;
    private Bitmap o;
    private Matrix p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private LinearLayout u;
    private HealthTextView v;
    private View w;
    private LinearLayout x;
    private HealthTextView y;
    private HealthTextView z;

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.o = gxp.a().aVH_();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.w = layoutInflater.inflate(R.layout.track_fragment_sport_share_short_track, viewGroup, false);
        this.e = getActivity();
        hjw c = gxp.a().c();
        this.aa = c;
        if (c == null) {
            return new View(this.e);
        }
        gwg.b(this.e);
        this.n = gwg.d(this.e);
        f();
        e();
        i();
        b();
        return this.w;
    }

    private void f() {
        if (this.o != null) {
            this.l = (ImageView) this.w.findViewById(R.id.track_share_short_map);
            this.k = (ImageView) this.w.findViewById(R.id.sport_track_map_mask);
            this.p = new Matrix();
            this.l.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SportShortTrackShareFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    int width = SportShortTrackShareFragment.this.o.getWidth();
                    int height = SportShortTrackShareFragment.this.o.getHeight();
                    float width2 = SportShortTrackShareFragment.this.l.getWidth();
                    float f = width;
                    float f2 = width2 / f;
                    float height2 = SportShortTrackShareFragment.this.l.getHeight();
                    float f3 = height;
                    float f4 = height2 / f3;
                    if (f2 > f4) {
                        SportShortTrackShareFragment.this.p.setScale(f2, f2);
                        if (!SportShortTrackShareFragment.this.aa.ax()) {
                            SportShortTrackShareFragment.this.p.postTranslate(0.0f, -(((f3 * f2) - height2) / 2.0f));
                        }
                    } else {
                        SportShortTrackShareFragment.this.p.setScale(f4, f4);
                        if (!SportShortTrackShareFragment.this.aa.ax()) {
                            SportShortTrackShareFragment.this.p.postTranslate(-(((f * f4) - width2) / 2.0f), 0.0f);
                        }
                    }
                    SportShortTrackShareFragment.this.l.setImageMatrix(SportShortTrackShareFragment.this.p);
                    SportShortTrackShareFragment.this.l.setImageBitmap(SportShortTrackShareFragment.this.o);
                    SportShortTrackShareFragment.this.g();
                }
            });
        } else {
            LogUtil.h("Track_ShortTrackShareFragment", "map is null!");
        }
        d();
        this.ad = (TrackPaceColorGradientView) this.w.findViewById(R.id.img_track_share_pace_gradient);
        if (LanguageUtil.bc(this.e)) {
            this.ad.setRotationY(180.0f);
        }
        if (LanguageUtil.b(this.e)) {
            ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
            if (!(layoutParams instanceof LinearLayout.LayoutParams)) {
                LogUtil.b("Track_ShortTrackShareFragment", "object is not instanceof LayoutParams");
                return;
            }
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            layoutParams2.gravity = 17;
            this.i.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ImageView imageView = this.k;
        if (imageView == null) {
            LogUtil.h("Track_ShortTrackShareFragment", "map mask is null");
            return;
        }
        int i = this.n;
        if (i == 2 || i == 3) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
    }

    private void d() {
        this.h = (HealthTextView) this.w.findViewById(R.id.track_detail_map_sport_distance_value);
        this.i = (HealthTextView) this.w.findViewById(R.id.text_targetUnit);
        this.y = (HealthTextView) this.w.findViewById(R.id.track_detail_map_sport_type);
        this.c = (HealthTextView) this.w.findViewById(R.id.track_detail_map_sport_type_dayime);
        this.g = (HealthTextView) this.w.findViewById(R.id.track_main_page_left_datatype);
        this.f = (HealthTextView) this.w.findViewById(R.id.track_detail_map_sport_during_time);
        this.j = (HealthTextView) this.w.findViewById(R.id.track_main_page_left_unit);
        this.b = (HealthTextView) this.w.findViewById(R.id.track_detail_map_calorie_value);
        this.v = (HealthTextView) this.w.findViewById(R.id.track_main_page_right_unit);
        this.q = (HealthTextView) this.w.findViewById(R.id.track_main_page_right_datatype);
        this.t = (HealthTextView) this.w.findViewById(R.id.track_detail_map_speed_value);
        this.x = (LinearLayout) this.w.findViewById(R.id.track_detail_linear_layout);
        this.u = (LinearLayout) this.w.findViewById(R.id.layout_otherValues);
        this.s = (HealthTextView) this.w.findViewById(R.id.txt_track_share_map_max_pace_value);
        this.r = (HealthTextView) this.w.findViewById(R.id.txt_track_share_map_min_pace_value);
        this.ae = (HealthTextView) this.w.findViewById(R.id.track_share_detail_title_username);
        this.d = (HealthTextView) this.w.findViewById(R.id.track_detail_map_speed_value_unit);
        this.f3734a = (HealthTextView) this.w.findViewById(R.id.track_main_page_mid_datatype);
        this.ac = (HealthTextView) this.w.findViewById(R.id.triathlon_map_sport_distance_value);
        this.ab = (HealthTextView) this.w.findViewById(R.id.triathlon_map_sport_during_time);
        this.z = (HealthTextView) this.w.findViewById(R.id.triathlon_map_sport_calorie_value);
        this.ai = (LinearLayout) this.w.findViewById(R.id.triathlon_detail_layout);
        this.ah = (HealthTextView) this.w.findViewById(R.id.triathlon_text_targetUnit);
        this.m = (HealthTextView) this.w.findViewById(R.id.map_type);
        j();
    }

    private void j() {
        int i = this.n;
        if (i == 1) {
            this.m.setVisibility(0);
            this.m.setText(this.e.getResources().getString(R.string._2130837686_res_0x7f0200b6));
        } else if (i == 2) {
            this.m.setVisibility(0);
            this.m.setText(this.e.getResources().getString(R.string._2130837687_res_0x7f0200b7));
        } else if (i == 3) {
            this.m.setVisibility(0);
            this.m.setText(this.e.getResources().getString(R.string._2130845162_res_0x7f021dea));
        } else {
            this.m.setVisibility(0);
        }
    }

    private void b() {
        String a2;
        String e;
        MotionPathSimplify e2 = this.aa.e();
        if (e2 == null) {
            LogUtil.h("Track_ShortTrackShareFragment", "simplify is null");
            this.x.setVisibility(8);
            return;
        }
        double requestTotalDistance = e2.requestTotalDistance() / 1000.0d;
        float requestAvgPace = e2.requestAvgPace();
        if (e2.requestSportType() == 222) {
            requestAvgPace = e2.requestTotalTime() / e2.getExtendDataFloat("wayPointDistance");
        }
        if (hji.j(e2.requestSportType())) {
            this.i.setText(R.string._2130841568_res_0x7f020fe0);
        } else if (UnitUtil.h()) {
            requestTotalDistance = UnitUtil.e(requestTotalDistance, 3);
            this.i.setText(R.string._2130841383_res_0x7f020f27);
            this.ah.setText(R.string._2130841383_res_0x7f020f27);
            requestAvgPace = (float) UnitUtil.d(requestAvgPace, 3);
        }
        if (requestAvgPace <= 0.0f) {
            a2 = getString(R.string._2130850262_res_0x7f0231d6);
        } else if (hji.j(e2.requestSportType())) {
            a2 = gvv.a(requestAvgPace / 2.0f);
        } else {
            a2 = gvv.a(requestAvgPace);
        }
        if (requestTotalDistance <= 0.0d) {
            e = getString(R.string._2130850262_res_0x7f0231d6);
        } else if (hji.j(e2.requestSportType())) {
            e = UnitUtil.a(e2.requestTotalDistance(), 1, 0, false);
        } else {
            e = UnitUtil.e(requestTotalDistance, 1, 2);
        }
        this.t.setText(a2);
        this.c.setText(UnitUtil.a("yyyy/M/d H:mm", e2.requestStartTime()));
        k();
        this.b.setText(hji.c(e2.requestTotalCalories()));
        this.h.setText(e);
        String d = UnitUtil.d(((int) e2.requestTotalTime()) / 1000);
        this.f.setText(d);
        a(e2);
        if (e2.requestSportType() == 260) {
            c(e2, requestAvgPace);
            return;
        }
        if (e2.requestSportType() == 259 || hji.h(e2.requestSportType())) {
            a(requestAvgPace);
            return;
        }
        if (e2.requestSportType() == 262 || e2.requestSportType() == 266) {
            b(e2);
            return;
        }
        if (kxb.a(e2.requestSportType())) {
            d(e2, requestAvgPace);
            return;
        }
        if (e2.requestSportType() == 512) {
            c(e2, e, d);
        } else if (e2.requestSportType() == 222) {
            b(e2, requestAvgPace);
        } else {
            LogUtil.b("Track_ShortTrackShareFragment", "not request sport type");
        }
    }

    private void b(MotionPathSimplify motionPathSimplify, float f) {
        this.h.setText(gvv.c(motionPathSimplify.requestTotalTime(), R.style.sport_day_hour_min_num_38dp, R.style.sport_day_hour_min_unit_14dp));
        this.i.setVisibility(8);
        this.g.setText(R.string._2130846373_res_0x7f0222a5);
        int extendDataInt = motionPathSimplify.getExtendDataInt("wayPointDistance");
        this.f.setText(extendDataInt == 0 ? this.e.getString(R.string._2130850262_res_0x7f0231d6) : hji.e(extendDataInt));
        this.j.setVisibility(0);
        this.w.findViewById(R.id.layout_middata).setVisibility(8);
        this.q.setText(this.e.getString(R.string._2130846653_res_0x7f0223bd));
        this.b.setText(hji.o(f));
        if (UnitUtil.h()) {
            this.j.setText(R.string._2130844081_res_0x7f0219b1);
            this.v.setText(this.e.getResources().getString(R.string._2130844079_res_0x7f0219af));
        } else {
            this.j.setText(R.string._2130844082_res_0x7f0219b2);
            this.v.setText(this.e.getResources().getString(R.string._2130844078_res_0x7f0219ae));
        }
    }

    private void k() {
        MotionPathSimplify e = this.aa.e();
        if (e != null) {
            if (e.requestRunCourseId() != null) {
                String d = gwg.d(this.e, this.aa.e().requestRunCourseId(), this.aa.e().getExtendDataString("courseName", ""));
                if (TextUtils.isEmpty(d)) {
                    this.y.setText(gwg.e(this.e, e.requestSportType()));
                    return;
                } else {
                    this.y.setText(d);
                    return;
                }
            }
            if (gwg.a(e)) {
                this.y.setText(getString(R.string._2130845268_res_0x7f021e54));
            } else if (this.aa.ax()) {
                this.y.setText(getString(R.string._2130847254_res_0x7f022616));
            } else {
                this.y.setText(gwg.e(this.e, e.requestSportType()));
            }
        }
    }

    private void c(MotionPathSimplify motionPathSimplify, String str, String str2) {
        this.x.setVisibility(0);
        this.u.setVisibility(8);
        this.ai.setVisibility(0);
        this.ac.setText(str);
        this.ab.setText(str2);
        this.z.setText(hji.c(motionPathSimplify.requestTotalCalories()));
    }

    private void b(MotionPathSimplify motionPathSimplify) {
        this.d.setVisibility(0);
        this.t.setText(a(motionPathSimplify.requestAvgPace()));
        this.d.setText(hji.d(this.e));
        if (UnitUtil.h()) {
            this.i.setText(this.e.getResources().getQuantityString(R.plurals._2130903227_res_0x7f0300bb, (int) Math.round(UnitUtil.e(this.aa.e().requestTotalDistance(), 2))));
        } else {
            this.i.setText(this.e.getResources().getString(R.string._2130841568_res_0x7f020fe0));
        }
        int requestTotalDistance = this.aa.e().requestTotalDistance();
        if (requestTotalDistance > 0) {
            this.h.setText(hji.i(requestTotalDistance));
        } else {
            this.h.setText(this.e.getResources().getString(R.string._2130850262_res_0x7f0231d6));
        }
    }

    private void a(float f) {
        this.f3734a.setText(this.e.getString(R.string._2130839763_res_0x7f0208d3));
        this.d.setVisibility(0);
        this.t.setText(hji.o(f));
        if (UnitUtil.h()) {
            this.d.setText(this.e.getResources().getString(R.string._2130844079_res_0x7f0219af));
        } else {
            this.d.setText(this.e.getResources().getString(R.string._2130844078_res_0x7f0219ae));
        }
    }

    private void d(MotionPathSimplify motionPathSimplify, float f) {
        String o;
        int requestSportType = motionPathSimplify.requestSportType();
        this.d.setVisibility(0);
        if (kxb.c(requestSportType)) {
            o = hji.b(motionPathSimplify.requestTotalDistance(), TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime()));
            this.f3734a.setText(this.e.getResources().getString(R.string._2130839893_res_0x7f020955));
            this.b.setText(String.valueOf(motionPathSimplify.getExtendDataInt("skiTripTimes", 0)));
            this.v.setVisibility(8);
            this.q.setText(R.string._2130839892_res_0x7f020954);
        } else {
            this.f3734a.setText(this.e.getResources().getString(R.string._2130839763_res_0x7f0208d3));
            o = hji.o(f);
        }
        this.t.setText(o);
        if (UnitUtil.h()) {
            this.d.setText(this.e.getResources().getString(R.string._2130844079_res_0x7f0219af));
        } else {
            this.d.setText(this.e.getResources().getString(R.string._2130844078_res_0x7f0219ae));
        }
    }

    private void c(MotionPathSimplify motionPathSimplify, float f) {
        String string;
        String e;
        double requestCreepingWave = motionPathSimplify.requestCreepingWave() / 10.0f;
        if (requestCreepingWave <= 1.0E-6d) {
            a(f);
            return;
        }
        if (UnitUtil.h()) {
            double e2 = UnitUtil.e(requestCreepingWave, 1);
            string = this.e.getResources().getQuantityString(R.plurals._2130903238_res_0x7f0300c6, (int) Math.round(e2));
            e = UnitUtil.e(e2, 1, 2);
        } else {
            string = this.e.getResources().getString(R.string._2130841568_res_0x7f020fe0);
            e = UnitUtil.e(requestCreepingWave, 1, 1);
        }
        this.t.setText(e);
        HealthTextView healthTextView = (HealthTextView) this.w.findViewById(R.id.track_detail_map_speed_value_unit);
        healthTextView.setText(string);
        healthTextView.setVisibility(0);
        ((HealthTextView) this.w.findViewById(R.id.track_main_page_mid_datatype)).setText(this.e.getResources().getString(R.string._2130842325_res_0x7f0212d5));
    }

    private void a(MotionPathSimplify motionPathSimplify) {
        Map<Integer, Float> bb = this.aa.bb();
        if (bb != null && bb.size() >= 2 && this.aa.a(this.e)) {
            Float[] e = this.aa.e(bb);
            if (e != null && e.length >= 2) {
                a(motionPathSimplify, e);
            } else {
                a();
            }
        } else {
            a();
        }
        if (hji.j(motionPathSimplify.requestSportType())) {
            this.d.setVisibility(0);
            this.d.setText(nsf.a(R.plurals._2130903225_res_0x7f0300b9, 500, 500));
        }
    }

    private void a() {
        this.s.setVisibility(8);
        this.r.setVisibility(8);
        this.ad.setVisibility(8);
    }

    private void a(MotionPathSimplify motionPathSimplify, Float[] fArr) {
        if (hji.g(motionPathSimplify.requestSportType())) {
            String c = c(fArr[0]);
            String c2 = c(fArr[1]);
            if (hji.c(this.aa) && hji.d(this.aa)) {
                c2 = hji.c(this.aa, true);
                c = hji.a(this.aa, true);
            } else if (hji.c(this.aa) && !hji.d(this.aa)) {
                this.s.setVisibility(4);
                this.r.setVisibility(4);
            } else {
                LogUtil.c("Track_ShortTrackShareFragment", "no valid show speed chart");
            }
            this.s.setText(((Object) this.s.getText()) + " " + c);
            this.r.setText(((Object) this.r.getText()) + " " + c2);
        } else if (motionPathSimplify.requestSportType() == 262 || motionPathSimplify.requestSportType() == 266) {
            this.s.setText(a(fArr[0].floatValue()) + " " + hji.d(this.e));
            this.s.setText(a((double) fArr[1].floatValue()) + " " + hji.d(this.e));
        } else {
            this.s.setText(((Object) this.s.getText()) + " " + gvv.a(fArr[0].floatValue()));
            this.r.setText(((Object) this.r.getText()) + " " + gvv.a(fArr[1].floatValue()));
        }
        MotionPath d = this.aa.d();
        if (d != null) {
            Float[] e = gvv.e(d.requestPaceMap());
            List<Integer> b = gwe.b(e[1].floatValue(), e[0].floatValue(), motionPathSimplify.requestSportType());
            if (koq.b(b)) {
                return;
            }
            this.ad.setColors(b);
            this.s.setTextColor(b.get(b.size() - 1).intValue());
            this.r.setTextColor(b.get(0).intValue());
        }
    }

    private String a(double d) {
        float f = ((float) d) / 10.0f;
        double d2 = f;
        if (d2 > 360000.0d || d2 <= 3.6d) {
            return getString(R.string._2130850262_res_0x7f0231d6);
        }
        if (UnitUtil.h()) {
            f = (float) UnitUtil.d(d2, 2);
        }
        return gvv.a(f);
    }

    private String c(Float f) {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(hji.o(f.floatValue()));
        stringBuffer.append(" ");
        if (UnitUtil.h()) {
            stringBuffer.append(this.e.getResources().getString(R.string._2130844079_res_0x7f0219af));
        } else {
            stringBuffer.append(this.e.getResources().getString(R.string._2130844078_res_0x7f0219ae));
        }
        return stringBuffer.toString();
    }

    private void e() {
        int requestDeviceType = this.aa.e().requestDeviceType();
        int e = gvv.e(this.aa.e().requestTrackType(), requestDeviceType);
        HealthTextView healthTextView = (HealthTextView) this.w.findViewById(R.id.track_detail_map_sport_device_name);
        if (e == 0) {
            String b = hpx.b(this.aa.e());
            if (TextUtils.isEmpty(b)) {
                healthTextView.setVisibility(8);
                return;
            } else {
                healthTextView.setVisibility(0);
                healthTextView.setText(b);
                return;
            }
        }
        Context context = this.e;
        healthTextView.setText(cwa.d(requestDeviceType, context, context.getPackageName(), this.aa.e().requestProductId()));
    }

    private void i() {
        SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        if (!socialShareCenterApi.isShowUserInfo()) {
            c();
            return;
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        String shareNickName = socialShareCenterApi.getShareNickName();
        this.ae.setText(shareNickName);
        ImageView imageView = (ImageView) this.w.findViewById(R.id.track_share_short_image);
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        String picPath = userInfo != null ? userInfo.getPicPath() : null;
        if (!TextUtils.isEmpty(picPath)) {
            Bitmap cIe_ = nrf.cIe_(this.e, picPath);
            if (cIe_ != null) {
                imageView.setImageBitmap(cIe_);
            } else {
                LogUtil.h("Track_ShortTrackShareFragment", "handleWhenGetUserInfoSuccess()bmp != null ");
            }
        } else {
            LogUtil.h("Track_ShortTrackShareFragment", "handleWhenGetUserInfoSuccess()! headImgPath is null! ");
        }
        if (Utils.o() && TextUtils.isEmpty(shareNickName)) {
            imageView.setVisibility(8);
        }
        h();
    }

    private void c() {
        this.ae.setVisibility(4);
        this.w.findViewById(R.id.track_share_short_image).setVisibility(8);
    }

    private void h() {
        LogUtil.a("Track_ShortTrackShareFragment", "queryMemberInfo");
        UserMemberInfo a2 = gpn.a();
        if (a2 == null) {
            LogUtil.h("Track_ShortTrackShareFragment", "userMemberInfo is null");
            return;
        }
        if (a2.getMemberFlag() != 1) {
            LogUtil.h("Track_ShortTrackShareFragment", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
            return;
        }
        if (gpn.d(a2)) {
            LogUtil.a("Track_ShortTrackShareFragment", "isVipExpired");
            return;
        }
        HealthImageView healthImageView = (HealthImageView) this.w.findViewById(R.id.member_card_vip_icon);
        if (healthImageView == null) {
            return;
        }
        healthImageView.setVisibility(0);
    }
}
