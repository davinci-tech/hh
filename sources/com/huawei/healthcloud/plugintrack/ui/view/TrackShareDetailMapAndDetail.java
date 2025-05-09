package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.AverageHangTimeAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.EversionExcursionAngleAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundContactTimeAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundHangTimeRateAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundImpactAccelerationAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundImpactLoadRateAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundShockPeakAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundTouchdownBalanceAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundVerticalAmplitudeAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.GroundVerticalAmplitudeRatioAdvice;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.SwingAngleAdvice;
import com.huawei.healthcloud.plugintrack.ui.view.TrackShareDetailMapAndDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.view.TotalDataRectView;
import com.huawei.up.model.UserInfomation;
import defpackage.cvc;
import defpackage.ffs;
import defpackage.gpn;
import defpackage.gvv;
import defpackage.gwg;
import defpackage.gxp;
import defpackage.hjw;
import defpackage.hlp;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes4.dex */
public class TrackShareDetailMapAndDetail extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private BasketballScoreView f3808a;
    private HealthTextView aa;
    private HealthTextView ab;
    private HealthTextView ac;
    private ImageView ad;
    private LinearLayout ae;
    private HealthTextView af;
    private HealthTextView ag;
    private HealthTextView ah;
    private HealthTextView ai;
    private HealthTextView aj;
    private LinearLayout ak;
    private HealthTextView al;
    private ImageView am;
    private HealthTextView an;
    private HealthTextView ao;
    private HealthTextView ap;
    private ImageView aq;
    private LinearLayout ar;
    private HealthTextView as;
    private HealthTextView at;
    private LinearLayout au;
    private HealthTextView av;
    private ImageView aw;
    private HealthTextView ax;
    private HealthTextView ay;
    private ImageView az;
    private DetailItemContainer b;
    private HealthTextView ba;
    private HealthTextView bb;
    private LinearLayout bc;
    private HealthTextView bd;
    private LinearLayout be;
    private ImageView bf;
    private HealthTextView bg;
    private HealthTextView bh;
    private HealthTextView bi;
    private ImageView bj;
    private HealthTextView bk;
    private HealthTextView bl;
    private HealthTextView bm;
    private ImageView bn;
    private HealthTextView bo;
    private HealthTextView bp;
    private ImageView bq;
    private HealthTextView br;
    private int bs;
    private RelativeLayout bt;
    private ImageView bu;
    private TrackPaceColorGradientView bv;
    private ImageView bw;
    private LinearLayout bx;
    private LinearLayout by;
    private HealthTextView bz;
    private LinearLayout c;
    private LinearLayout ca;
    private RelativeLayout cb;
    private HealthTextView cc;
    private LinearLayout cd;
    private TotalDataRectView ce;
    private LinearLayout cf;
    private int cg;
    private ImageView ch;
    private HealthTextView ci;
    private LinearLayout cj;
    private LinearLayout ck;
    private HealthTextView cl;
    private LinearLayout cm;
    private LinearLayout cn;
    private HealthTextView co;
    private HealthTextView cp;
    private HealthTextView cq;
    private HealthTextView cr;
    private HealthTextView cs;
    private HealthTextView ct;
    private ImageView cu;
    private HealthTextView cv;
    private HealthTextView cw;
    private LinearLayout cx;
    private ImageView cy;
    private HealthTextView cz;
    private Context d;
    private HealthTextView da;
    private HealthTextView db;
    private RelativeLayout dc;
    private HealthTextView dd;
    private HealthTextView de;
    private HealthTextView df;
    private HealthTextView dg;
    private RelativeLayout di;
    private TrackShareDetailCustomTitleLayout e;
    private LinearLayout f;
    private HealthTextView g;
    private ImageView h;
    private ImageView i;
    private LinearLayout j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private HealthTextView w;
    private HealthTextView x;
    private ImageView y;
    private HealthTextView z;

    public TrackShareDetailMapAndDetail(Context context, int i) {
        super(context);
        this.d = null;
        this.bq = null;
        this.bn = null;
        this.bw = null;
        this.dd = null;
        this.dg = null;
        this.e = null;
        this.b = null;
        this.bu = null;
        this.bv = null;
        this.de = null;
        this.db = null;
        this.j = null;
        this.c = null;
        this.bt = null;
        this.cg = 100;
        c(context, i);
    }

    public TrackShareDetailMapAndDetail(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = null;
        this.bq = null;
        this.bn = null;
        this.bw = null;
        this.dd = null;
        this.dg = null;
        this.e = null;
        this.b = null;
        this.bu = null;
        this.bv = null;
        this.de = null;
        this.db = null;
        this.j = null;
        this.c = null;
        this.bt = null;
        this.cg = 100;
        c(context, 100);
    }

    private void c(Context context, int i) {
        this.d = context;
        this.cg = i;
        View.inflate(context, R.layout.track_share_map_and_detail_view_layout, this);
        this.j = (LinearLayout) findViewById(R.id.layout_title_and_detail);
        this.c = (LinearLayout) findViewById(R.id.layout_title_and_detail_child);
        this.bt = (RelativeLayout) findViewById(R.id.layout_top_map_device);
        this.bz = (HealthTextView) findViewById(R.id.map_type);
        this.cb = (RelativeLayout) findViewById(R.id.map_type_group);
        this.dc = (RelativeLayout) findViewById(R.id.track_title_above);
        this.di = (RelativeLayout) findViewById(R.id.track_title_below);
        this.bq = (ImageView) findViewById(R.id.track_share_map);
        this.bn = (ImageView) findViewById(R.id.img_track_device_name);
        this.dd = (HealthTextView) findViewById(R.id.txt_track_device_name);
        this.bu = (ImageView) findViewById(R.id.img_track_share_user_img);
        this.dg = (HealthTextView) findViewById(R.id.track_share_detail_title_usrname);
        this.bv = (TrackPaceColorGradientView) findViewById(R.id.img_track_share_pace_gradient);
        if (LanguageUtil.bc(this.d)) {
            this.bv.setRotationY(180.0f);
        }
        this.i = (ImageView) findViewById(R.id.divide_line);
        this.de = (HealthTextView) findViewById(R.id.txt_track_share_map_max_pace_value);
        this.db = (HealthTextView) findViewById(R.id.txt_track_share_map_min_pace_value);
        this.e = (TrackShareDetailCustomTitleLayout) findViewById(R.id.track_detail_title);
        this.b = (DetailItemContainer) findViewById(R.id.track_detail_container);
        this.bw = (ImageView) findViewById(R.id.img_track_share_map_gradient);
        r();
        this.cj = (LinearLayout) findViewById(R.id.track_detail_show_swim);
        this.ck = (LinearLayout) findViewById(R.id.track_detail_swim_avg_pace_layout);
        this.cm = (LinearLayout) findViewById(R.id.track_detail_swim_avg_stroke_rate_layout);
        this.cn = (LinearLayout) findViewById(R.id.track_detail_swim_avg_swolf_layout);
        this.cr = (HealthTextView) findViewById(R.id.track_detail_show_swim_title_name);
        this.cl = (HealthTextView) findViewById(R.id.track_detail_swim_avg_pace_name);
        this.co = (HealthTextView) findViewById(R.id.track_detail_swim_avg_pace);
        this.ci = (HealthTextView) findViewById(R.id.track_detail_swim_avg_pace_unit);
        this.ch = (ImageView) findViewById(R.id.track_detail_swim_avg_pace_line);
        this.ct = (HealthTextView) findViewById(R.id.track_detail_swim_avg_stroke_rate_name);
        this.cs = (HealthTextView) findViewById(R.id.track_detail_swim_avg_stroke_rate);
        this.cv = (HealthTextView) findViewById(R.id.track_detail_swim_avg_stroke_rate_unit);
        this.cu = (ImageView) findViewById(R.id.track_detail_swim_avg_stroke_rate_line);
        this.cp = (HealthTextView) findViewById(R.id.track_detail_swim_avg_swolf_name);
        this.cq = (HealthTextView) findViewById(R.id.track_detail_swim_avg_swolf);
        this.f3808a = (BasketballScoreView) findViewById(R.id.basketball_radar_view);
        q();
        x();
        v();
        s();
    }

    private void z() {
        LogUtil.a("TrackShareDetailMapAndDetail", "queryMemberInfo");
        UserMemberInfo a2 = gpn.a();
        if (a2 == null) {
            LogUtil.h("TrackShareDetailMapAndDetail", "userMemberInfo is null");
            return;
        }
        if (a2.getMemberFlag() != 1) {
            LogUtil.h("TrackShareDetailMapAndDetail", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
            return;
        }
        if (gpn.d(a2)) {
            LogUtil.a("TrackShareDetailMapAndDetail", "isVipExpired");
            return;
        }
        HealthImageView healthImageView = (HealthImageView) findViewById(R.id.member_card_vip_icon);
        if (healthImageView == null) {
            return;
        }
        healthImageView.setVisibility(0);
    }

    private void s() {
        int d = gwg.d(this.d);
        this.bs = d;
        if (d == 1) {
            this.bz.setVisibility(0);
            this.bz.setText(this.d.getResources().getString(R.string._2130837686_res_0x7f0200b6));
        } else if (d == 2) {
            this.bz.setVisibility(0);
            this.bz.setText(this.d.getResources().getString(R.string._2130837687_res_0x7f0200b7));
        } else if (d == 3) {
            this.bz.setVisibility(0);
            this.bz.setText(this.d.getResources().getString(R.string._2130845162_res_0x7f021dea));
        } else {
            this.bz.setVisibility(0);
        }
    }

    private void v() {
        if (gxp.a().e()) {
            return;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.j.getLayoutParams();
        layoutParams.setMargins(this.d.getResources().getDimensionPixelOffset(R.dimen._2131363711_res_0x7f0a077f), this.d.getResources().getDimensionPixelOffset(R.dimen._2131362363_res_0x7f0a023b), this.d.getResources().getDimensionPixelOffset(R.dimen._2131363711_res_0x7f0a077f), 0);
        this.j.setLayoutParams(layoutParams);
    }

    public void h() {
        if (gxp.a().e()) {
            return;
        }
        this.dc.setVisibility(8);
        this.di.setVisibility(8);
    }

    private void q() {
        SocialShareCenterApi socialShareCenterApi = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        if (!socialShareCenterApi.isShowUserInfo()) {
            l();
            return;
        }
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        String shareNickName = socialShareCenterApi.getShareNickName();
        this.dg.setText(shareNickName);
        d(userInfo, shareNickName);
        z();
    }

    private void d(UserInfomation userInfomation, String str) {
        if (Utils.o() && TextUtils.isEmpty(str)) {
            this.bu.setVisibility(8);
            return;
        }
        String picPath = userInfomation != null ? userInfomation.getPicPath() : null;
        if (!TextUtils.isEmpty(picPath)) {
            Bitmap cIe_ = nrf.cIe_(this.d, picPath);
            if (cIe_ != null) {
                setUserImg(cIe_);
                return;
            } else {
                LogUtil.h("TrackShareDetailMapAndDetail", "handleWhenGetUserInfoSuccess()bmp != null ");
                return;
            }
        }
        LogUtil.h("TrackShareDetailMapAndDetail", "handleWhenGetUserInfoSuccess()! headImgPath is null! ");
    }

    private void l() {
        this.dg.setVisibility(4);
        this.bu.setVisibility(8);
    }

    private void r() {
        this.cc = (HealthTextView) findViewById(R.id.track_detail_show_running_posture_title_name);
        m();
        o();
        k();
        t();
        u();
        ac();
        ad();
        y();
        p();
        n();
        w();
        aa();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_strike_pattern);
        this.cf = linearLayout;
        this.s = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.ce = (TotalDataRectView) this.cf.findViewById(R.id.proportion_bar);
        this.m = (HealthTextView) this.cf.findViewById(R.id.running_posture_avg_foot_strike_pattern_fore_name);
        this.n = (HealthTextView) this.cf.findViewById(R.id.running_posture_avg_foot_strike_pattern_fore_value);
        this.p = (HealthTextView) this.cf.findViewById(R.id.running_posture_avg_foot_strike_pattern_whole_name);
        this.t = (HealthTextView) this.cf.findViewById(R.id.running_posture_avg_foot_strike_pattern_whole_value);
        this.q = (HealthTextView) this.cf.findViewById(R.id.running_posture_avg_foot_strike_pattern_hind_name);
        this.r = (HealthTextView) this.cf.findViewById(R.id.running_posture_avg_foot_strike_pattern_hind_value);
        this.cf.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.track_show_running_posture);
        this.cd = linearLayout2;
        linearLayout2.setPadding(-16, nsn.c(this.d, 13.8f), -16, nsn.c(this.d, 16.0f));
    }

    private void w() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_swing_angle);
        this.cx = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.da = healthTextView;
        healthTextView.setText(R.string._2130842723_res_0x7f021463);
        this.df = (HealthTextView) this.cx.findViewById(R.id.track_detail_posture_item_value);
        this.cw = (HealthTextView) this.cx.findViewById(R.id.track_detail_posture_item_level);
        this.cz = (HealthTextView) this.cx.findViewById(R.id.track_detail_posture_item_unit);
        this.cx.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        this.cy = (ImageView) findViewById(R.id.track_detail_running_posture_swing_angle_line);
        e(this.df, this.da);
    }

    private void n() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_eversion_excursion);
        this.f = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.o = healthTextView;
        healthTextView.setText(R.string._2130842722_res_0x7f021462);
        this.k = (HealthTextView) this.f.findViewById(R.id.track_detail_posture_item_value);
        this.g = (HealthTextView) this.f.findViewById(R.id.track_detail_posture_item_level);
        this.l = (HealthTextView) this.f.findViewById(R.id.track_detail_posture_item_unit);
        this.f.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        this.h = (ImageView) findViewById(R.id.track_detail_running_posture_eversion_excursion_line);
        e(this.k, this.o);
    }

    private void t() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_impact_acceleration);
        this.ae = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.ag = healthTextView;
        healthTextView.setText(R.string._2130842717_res_0x7f02145d);
        this.ah = (HealthTextView) this.ae.findViewById(R.id.track_detail_posture_item_value);
        this.af = (HealthTextView) this.ae.findViewById(R.id.track_detail_posture_item_level);
        HealthTextView healthTextView2 = (HealthTextView) this.ae.findViewById(R.id.track_detail_posture_item_unit);
        this.ai = healthTextView2;
        healthTextView2.setText(R.string._2130842716_res_0x7f02145c);
        this.ae.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        e(this.ah, this.ag);
    }

    private void u() {
        this.au = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_touchdown_balance);
        this.aw = (ImageView) findViewById(R.id.track_detail_running_posture_ground_touchdown_balance_line);
        HealthTextView healthTextView = (HealthTextView) this.au.findViewById(R.id.track_detail_posture_item_name);
        this.ba = healthTextView;
        healthTextView.setText(R.string._2130845182_res_0x7f021dfe);
        this.ay = (HealthTextView) this.au.findViewById(R.id.track_detail_posture_item_value);
        this.av = (HealthTextView) this.au.findViewById(R.id.track_detail_posture_item_level);
        this.au.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        e(this.ay, this.ba);
    }

    private void ac() {
        this.bc = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude);
        this.az = (ImageView) findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude_line);
        HealthTextView healthTextView = (HealthTextView) this.bc.findViewById(R.id.track_detail_posture_item_name);
        this.bg = healthTextView;
        healthTextView.setText(R.string._2130845169_res_0x7f021df1);
        this.bk = (HealthTextView) this.bc.findViewById(R.id.track_detail_posture_item_value);
        this.bb = (HealthTextView) this.bc.findViewById(R.id.track_detail_posture_item_level);
        this.bm = (HealthTextView) this.bc.findViewById(R.id.track_detail_posture_item_unit);
        if (!UnitUtil.h()) {
            this.bm.setText(R.string._2130841416_res_0x7f020f48);
        } else {
            this.bm.setText(R.string._2130841897_res_0x7f021129);
        }
        this.bc.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        e(this.bk, this.bg);
    }

    private void ad() {
        this.be = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude_ratio);
        this.bf = (ImageView) findViewById(R.id.track_detail_running_posture_ground_vertical_amplitude_ratio_line);
        HealthTextView healthTextView = (HealthTextView) this.be.findViewById(R.id.track_detail_posture_item_name);
        this.bd = healthTextView;
        healthTextView.setText(R.string._2130845170_res_0x7f021df2);
        this.bl = (HealthTextView) this.be.findViewById(R.id.track_detail_posture_item_value);
        this.bh = (HealthTextView) this.be.findViewById(R.id.track_detail_posture_item_level);
        this.be.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        e(this.bl, this.bd);
    }

    private void y() {
        this.ar = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_shock_peak);
        this.aq = (ImageView) findViewById(R.id.track_detail_running_posture_ground_shock_peak_line);
        HealthTextView healthTextView = (HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_name);
        this.ao = healthTextView;
        healthTextView.setText(R.string._2130845177_res_0x7f021df9);
        this.at = (HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_value);
        this.as = (HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_level);
        HealthTextView healthTextView2 = (HealthTextView) this.ar.findViewById(R.id.track_detail_posture_item_unit);
        this.ax = healthTextView2;
        healthTextView2.setText(R.string._2130845180_res_0x7f021dfc);
        this.ar.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        e(this.at, this.ao);
    }

    private void p() {
        this.ak = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_impact_load_rate);
        this.am = (ImageView) findViewById(R.id.track_detail_running_posture_ground_impact_load_rate_line);
        HealthTextView healthTextView = (HealthTextView) this.ak.findViewById(R.id.track_detail_posture_item_name);
        this.al = healthTextView;
        healthTextView.setText(R.string._2130845178_res_0x7f021dfa);
        this.ap = (HealthTextView) this.ak.findViewById(R.id.track_detail_posture_item_value);
        this.aj = (HealthTextView) this.ak.findViewById(R.id.track_detail_posture_item_level);
        HealthTextView healthTextView2 = (HealthTextView) this.ak.findViewById(R.id.track_detail_posture_item_unit);
        this.an = healthTextView2;
        healthTextView2.setAutoTextInfo(9, 1, 2);
        this.an.setText(this.d.getResources().getQuantityString(R.plurals._2130903324_res_0x7f03011c, 1, ""));
        this.ak.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        e(this.ap, this.al);
    }

    private void m() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_contact_time);
        this.ca = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.x = healthTextView;
        healthTextView.setText(R.string._2130842711_res_0x7f021457);
        this.w = (HealthTextView) this.ca.findViewById(R.id.track_detail_posture_item_value);
        this.v = (HealthTextView) this.ca.findViewById(R.id.track_detail_posture_item_level);
        HealthTextView healthTextView2 = (HealthTextView) this.ca.findViewById(R.id.track_detail_posture_item_unit);
        this.u = healthTextView2;
        healthTextView2.setText(R.string._2130842713_res_0x7f021459);
        this.ca.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        this.y = (ImageView) findViewById(R.id.track_detail_running_posture_ground_contact_time_line);
        e(this.w, this.x);
    }

    private void o() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_hang_time);
        this.bx = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.br = healthTextView;
        healthTextView.setText(R.string._2130843163_res_0x7f02161b);
        this.bp = (HealthTextView) this.bx.findViewById(R.id.track_detail_posture_item_value);
        this.bi = (HealthTextView) this.bx.findViewById(R.id.track_detail_posture_item_level);
        HealthTextView healthTextView2 = (HealthTextView) this.bx.findViewById(R.id.track_detail_posture_item_unit);
        this.bo = healthTextView2;
        healthTextView2.setText(R.string._2130842713_res_0x7f021459);
        this.bx.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        this.bj = (ImageView) findViewById(R.id.track_detail_running_posture_hang_time_line);
        e(this.bp, this.br);
    }

    private void k() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.track_detail_running_posture_ground_hang_time_rate);
        this.by = linearLayout;
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_name);
        this.z = healthTextView;
        healthTextView.setText(R.string._2130843726_res_0x7f02184e);
        this.aa = (HealthTextView) this.by.findViewById(R.id.track_detail_posture_item_value);
        this.ac = (HealthTextView) this.by.findViewById(R.id.track_detail_posture_item_level);
        HealthTextView healthTextView2 = (HealthTextView) this.by.findViewById(R.id.track_detail_posture_item_unit);
        this.ab = healthTextView2;
        healthTextView2.setVisibility(8);
        this.by.findViewById(R.id.track_detail_posture_item_more_arrow).setVisibility(8);
        this.ad = (ImageView) findViewById(R.id.track_detail_running_posture_ground_hang_time_rate_line);
        e(this.aa, this.z);
    }

    private void e(final HealthTextView healthTextView, final HealthTextView healthTextView2) {
        if (nsn.l()) {
            if (healthTextView2 != null && healthTextView != null && (healthTextView.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
                healthTextView2.post(new Runnable() { // from class: hlw
                    @Override // java.lang.Runnable
                    public final void run() {
                        TrackShareDetailMapAndDetail.this.c(healthTextView2, healthTextView);
                    }
                });
            } else {
                LogUtil.b("TrackShareDetailMapAndDetail", "itemNameView or itemValueView is null, ", "or itemValueVeiw.LayoutParams isn't the instance of LinearLauout.LayoutParams");
            }
        }
    }

    public /* synthetic */ void c(HealthTextView healthTextView, HealthTextView healthTextView2) {
        int width = healthTextView.getWidth();
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.d, 0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthTextView2.getLayoutParams();
        if (nsn.ag(this.d)) {
            int d = (((int) healthColumnSystem.d(2)) + nrr.b(this.d)) - width;
            if (d < 0) {
                d = nsn.c(this.d, 12.0f);
            }
            layoutParams.setMarginStart(d);
        } else {
            layoutParams.setMarginStart(nsn.c(this.d, 12.0f));
        }
        healthTextView2.setLayoutParams(layoutParams);
    }

    private void bjJ_(boolean z, LinearLayout linearLayout) {
        if (linearLayout == null) {
            return;
        }
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.track_detail_posture_item_level);
        if (z) {
            healthTextView.setMaxWidth(nsn.c(this.d, 242.0f));
        } else {
            healthTextView.setMaxWidth(nsn.c(this.d, 86.0f));
        }
    }

    private void aa() {
        boolean ag = nsn.ag(getContext());
        bjJ_(ag, this.ca);
        bjJ_(ag, this.ae);
        bjJ_(ag, this.f);
        bjJ_(ag, this.cx);
        bjJ_(ag, this.bx);
        bjJ_(ag, this.by);
    }

    private void x() {
        if (this.d == null) {
            return;
        }
        if (this.cg == 101) {
            setTextColor(getResources().getColor(R.color._2131296871_res_0x7f090267));
            this.cf.setBackgroundColor(this.d.getResources().getColor(R.color._2131298120_res_0x7f090748));
            this.ce.setPaintBackgroundColor(false, this.d.getResources().getColor(R.color._2131296934_res_0x7f0902a6));
        } else {
            setTextColor(getResources().getColor(R.color._2131298052_res_0x7f090704));
            this.cf.setBackgroundColor(this.d.getResources().getColor(R.color._2131296475_res_0x7f0900db));
        }
    }

    public void setTextColor(int i) {
        this.e.setTextColor(i);
        this.b.setTextColor(i);
        setRunningPostureColor(i);
    }

    public void setImgMap(final Bitmap bitmap, final int i, final boolean z) {
        this.bq.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.view.TrackShareDetailMapAndDetail.5
            @Override // java.lang.Runnable
            public void run() {
                Matrix matrix = new Matrix();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                float width2 = TrackShareDetailMapAndDetail.this.bq.getWidth();
                float f = width;
                float f2 = width2 / f;
                float height2 = TrackShareDetailMapAndDetail.this.bq.getHeight();
                float f3 = height;
                float f4 = height2 / f3;
                if (f2 > f4) {
                    matrix.setScale(f2, f2);
                    if (!z) {
                        matrix.postTranslate(0.0f, -(((f3 * f2) - height2) / 4.0f));
                    }
                } else {
                    matrix.setScale(f4, f4);
                    if (!z) {
                        matrix.postTranslate(-(((f * f4) - width2) / 2.0f), 0.0f);
                    }
                }
                TrackShareDetailMapAndDetail.this.bq.setImageBitmap(bitmap);
                if (i == 271) {
                    TrackShareDetailMapAndDetail.this.bq.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    TrackShareDetailMapAndDetail trackShareDetailMapAndDetail = TrackShareDetailMapAndDetail.this;
                    trackShareDetailMapAndDetail.setMapLayoutMarginBottom(-nsn.c(trackShareDetailMapAndDetail.d, 100.0f));
                } else {
                    TrackShareDetailMapAndDetail.this.bq.setImageMatrix(matrix);
                }
                if (TrackShareDetailMapAndDetail.this.bw.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) TrackShareDetailMapAndDetail.this.bw.getLayoutParams();
                    layoutParams.height = TrackShareDetailMapAndDetail.this.bq.getHeight();
                    TrackShareDetailMapAndDetail.this.bw.setLayoutParams(layoutParams);
                }
            }
        });
    }

    public void j() {
        this.bq.setVisibility(8);
        this.bz.setVisibility(8);
        this.cb.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMapLayoutMarginBottom(int i) {
        if (this.bt.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bt.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, i);
            this.bt.setLayoutParams(layoutParams);
        }
    }

    public void setImgDevice(int i) {
        this.bn.setBackgroundResource(i);
    }

    public int a(int i) {
        cvc pluginInfoByHiType = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByHiType(i);
        if (pluginInfoByHiType == null || pluginInfoByHiType.f() == null || TextUtils.isEmpty(pluginInfoByHiType.f().bl())) {
            return 1;
        }
        this.bn.setBackground(new BitmapDrawable(((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImageName(pluginInfoByHiType, pluginInfoByHiType.f().bl())));
        return 0;
    }

    public void setTextDevice(String str) {
        this.dd.setText(str);
    }

    public void setTextDevice(int i, String str, int i2) {
        Context context = this.d;
        if (context == null) {
            return;
        }
        if (i != 0) {
            this.dd.setCompoundDrawablesRelativeWithIntrinsicBounds(context.getResources().getDrawable(i), (Drawable) null, (Drawable) null, (Drawable) null);
            this.dd.setCompoundDrawablePadding(this.d.getResources().getDimensionPixelSize(R.dimen._2131365113_res_0x7f0a0cf9));
        }
        this.dd.setText(str);
        if (i2 == 271) {
            if (!LanguageUtil.m(this.d) || Utils.o()) {
                this.dd.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
                this.dg.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            } else {
                this.dd.setTextColor(getResources().getColor(R.color._2131296871_res_0x7f090267));
                this.dg.setTextColor(getResources().getColor(R.color._2131296871_res_0x7f090267));
            }
        }
    }

    public void setImgDeviceVisibility(int i) {
        this.bn.setVisibility(i);
    }

    public void setTextDeviceVisibility(int i) {
        this.dd.setVisibility(i);
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        this.c.setBackgroundResource(i);
        this.cd.setBackgroundResource(i);
        this.cj.setBackgroundResource(i);
    }

    public DetailItemContainer d() {
        return this.b;
    }

    public TrackShareDetailCustomTitleLayout c() {
        return this.e;
    }

    public void setUserImg(Bitmap bitmap) {
        this.bu.setImageBitmap(bitmap);
    }

    public void setShowPaceVisibility(int i) {
        this.de.setVisibility(i);
        this.db.setVisibility(i);
        this.bv.setVisibility(i);
    }

    public void setMaxAndMinPaceVisibility(int i) {
        this.de.setVisibility(i);
        this.db.setVisibility(i);
    }

    public void setImgPaceGradientColors(List<Integer> list) {
        if (koq.b(list)) {
            return;
        }
        this.bv.setColors(list);
        this.de.setTextColor(list.get(list.size() - 1).intValue());
        this.db.setTextColor(list.get(0).intValue());
    }

    public void setMaxAndMinPace(String str, String str2) {
        this.de.setText(((Object) this.de.getText()) + " " + str2);
        this.db.setText(((Object) this.db.getText()) + " " + str);
    }

    public void setDivideLineVisibility(int i) {
        int i2 = this.cg;
        if (i2 == 101) {
            this.i.setBackgroundColor(getResources().getColor(R.color._2131296871_res_0x7f090267));
        } else if (i2 == 100) {
            this.i.setBackgroundColor(getResources().getColor(R.color._2131298052_res_0x7f090704));
        } else {
            LogUtil.a("TrackShareDetailMapAndDetail", "don't deal that style", Integer.valueOf(i2));
        }
        this.i.setVisibility(i);
    }

    public void setLayoutStyle(int i, boolean z, int i2) {
        Context context = this.d;
        if (context == null) {
            return;
        }
        Resources resources = context.getResources();
        if (this.bt.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.bt.getLayoutParams();
            if (i == 101) {
                bjK_(z, layoutParams);
            } else if (i == 102) {
                bjL_(z, resources, layoutParams);
            } else {
                bjM_(z, resources, layoutParams, i2);
            }
        }
    }

    private void bjM_(boolean z, Resources resources, RelativeLayout.LayoutParams layoutParams, int i) {
        this.bw.setVisibility(8);
        if (z) {
            ab();
            if (i == 271) {
                layoutParams.bottomMargin = -nsn.c(this.d, 105.0f);
            } else {
                layoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen._2131363621_res_0x7f0a0725);
            }
            this.bt.setLayoutParams(layoutParams);
            if (nrt.a(this.d)) {
                return;
            }
            this.bw.setVisibility(0);
            this.bw.setBackgroundResource(R.drawable._2131427594_res_0x7f0b010a);
            return;
        }
        ag();
        layoutParams.bottomMargin = 0;
        this.bt.setLayoutParams(layoutParams);
        this.bw.setVisibility(8);
    }

    private void bjL_(boolean z, Resources resources, RelativeLayout.LayoutParams layoutParams) {
        if (z) {
            ab();
            layoutParams.bottomMargin = resources.getDimensionPixelOffset(R.dimen._2131363621_res_0x7f0a0725);
            this.bt.setLayoutParams(layoutParams);
            this.bw.setVisibility(0);
            this.bw.setBackgroundResource(R.drawable._2131431922_res_0x7f0b11f2);
            return;
        }
        ag();
        layoutParams.bottomMargin = 0;
        this.bt.setLayoutParams(layoutParams);
        this.bw.setVisibility(8);
    }

    private void bjK_(boolean z, RelativeLayout.LayoutParams layoutParams) {
        ab();
        if (z) {
            this.bw.setVisibility(0);
            this.bw.setBackgroundResource(R.drawable._2131431922_res_0x7f0b11f2);
        } else {
            layoutParams.bottomMargin = nsn.c(this.d, -60.0f);
            this.bt.setLayoutParams(layoutParams);
            this.bw.setVisibility(8);
        }
    }

    private void ab() {
        Resources resources = this.d.getResources();
        if (this.dd.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.dd.getLayoutParams();
            layoutParams.removeRule(20);
            layoutParams.addRule(10);
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen._2131363664_res_0x7f0a0750);
            this.dd.setLayoutParams(layoutParams);
        }
    }

    private void ag() {
        Resources resources = this.d.getResources();
        if (this.dd.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.dd.getLayoutParams();
            layoutParams.removeRule(10);
            layoutParams.addRule(20);
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen._2131361970_res_0x7f0a00b2);
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen._2131361970_res_0x7f0a00b2);
            layoutParams.setMarginStart(resources.getDimensionPixelSize(R.dimen._2131363642_res_0x7f0a073a));
            this.dd.setLayoutParams(layoutParams);
        }
    }

    public void e(hlp hlpVar, List<PostureJudgeBean> list) {
        if (hlpVar == null || this.d == null) {
            LogUtil.h("TrackShareDetailMapAndDetail", "saveRunningPostureData invalid params.");
            return;
        }
        hjw c = gxp.a().c();
        if (c == null || c.e() == null) {
            LogUtil.h("TrackShareDetailMapAndDetail", "saveRunningPostureData invalid detail data manager");
            return;
        }
        if (ffs.a(c.e())) {
            this.cd.setVisibility(8);
            return;
        }
        this.cd.setVisibility(0);
        int round = Math.round(c.e().requestAvgPace());
        int requestAverageHangTime = c.e().requestAverageHangTime();
        float requestGroundHangTimeRate = c.e().requestGroundHangTimeRate();
        e(requestAverageHangTime, round, list);
        c(requestGroundHangTimeRate, round, list);
        c(hlpVar, round, list);
        setGcTimeBalancePosture(hlpVar);
        setVerticalOscillationPosture(hlpVar);
        setVerticalRatioPosture(hlpVar);
        setImpactPeakPosture(hlpVar);
        setAvergeVerticalImpactRatePosture(hlpVar);
        c(hlpVar);
        b(hlpVar);
        setPatternStepLayout(hlpVar);
        setAvgGroundImpactAcceleration(hlpVar);
    }

    private void c(hlp hlpVar, int i, List<PostureJudgeBean> list) {
        if (hlpVar.e() <= 0) {
            this.ca.setVisibility(8);
            this.y.setVisibility(8);
        } else {
            GroundContactTimeAdvice groundContactTimeAdvice = new GroundContactTimeAdvice(hlpVar.e(), i, list, SportDetailChartDataType.GROUND_CONTACT_TIME);
            this.w.setText(UnitUtil.e(hlpVar.e(), 1, 0));
            this.v.setText(groundContactTimeAdvice.acquireLevelShortTip());
            this.v.setTextColor(getResources().getColor(gvv.d(groundContactTimeAdvice.acquireLevel())));
        }
    }

    private void setPatternStepLayout(hlp hlpVar) {
        if (hlpVar.m() <= 0) {
            this.cf.setVisibility(8);
            return;
        }
        String e = UnitUtil.e(hlpVar.d(), 2, 0);
        String e2 = UnitUtil.e(hlpVar.j(), 2, 0);
        this.ce.setViewData(hlpVar.d(), hlpVar.j(), hlpVar.i());
        String e3 = UnitUtil.e(hlpVar.i(), 2, 0);
        this.n.setText(UnitUtil.bCR_(this.d, "\\d+.\\d+|\\d+", e, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        this.t.setText(UnitUtil.bCR_(this.d, "\\d+.\\d+|\\d+", e2, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
        this.r.setText(UnitUtil.bCR_(this.d, "\\d+.\\d+|\\d+", e3, R.style.strike_pattern_text_result_k, R.style.strike_pattern_text_result_n));
    }

    private void b(hlp hlpVar) {
        if (hlpVar.h() <= 0) {
            this.cx.setVisibility(8);
            this.cy.setVisibility(8);
        } else {
            this.df.setText(getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, hlpVar.h(), Integer.valueOf(hlpVar.h())));
            SwingAngleAdvice swingAngleAdvice = new SwingAngleAdvice(hlpVar.h(), hlpVar.c());
            this.cw.setText(swingAngleAdvice.acquireLevelShortTip());
            this.cw.setTextColor(getResources().getColor(gvv.d(swingAngleAdvice.acquireLevel())));
        }
    }

    private void c(hlp hlpVar) {
        if (hlpVar.a() <= -101) {
            this.f.setVisibility(8);
            this.h.setVisibility(8);
            LogUtil.h("TrackShareDetailMapAndDetail", "mEversionExcursionLayout is gone");
        } else {
            this.k.setText(getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, hlpVar.a(), Integer.valueOf(hlpVar.a())));
            EversionExcursionAngleAdvice eversionExcursionAngleAdvice = new EversionExcursionAngleAdvice(hlpVar.a());
            this.g.setText(eversionExcursionAngleAdvice.acquireLevelShortTip());
            this.g.setTextColor(getResources().getColor(gvv.d(eversionExcursionAngleAdvice.acquireLevel())));
        }
    }

    private void setAvgGroundImpactAcceleration(hlp hlpVar) {
        if (hlpVar.b() <= 0) {
            this.ae.setVisibility(8);
            return;
        }
        this.ae.setVisibility(0);
        this.ah.setText(UnitUtil.e(hlpVar.b(), 1, 0));
        GroundImpactAccelerationAdvice groundImpactAccelerationAdvice = new GroundImpactAccelerationAdvice(hlpVar.b());
        this.af.setText(groundImpactAccelerationAdvice.acquireLevelShortTip());
        this.af.setTextColor(getResources().getColor(gvv.d(groundImpactAccelerationAdvice.acquireLevel())));
    }

    private void setAvergeVerticalImpactRatePosture(hlp hlpVar) {
        if (Float.compare(hlpVar.g(), 0.0f) <= 0) {
            this.am.setVisibility(8);
            this.ak.setVisibility(8);
            return;
        }
        this.am.setVisibility(0);
        this.ak.setVisibility(0);
        GroundImpactLoadRateAdvice groundImpactLoadRateAdvice = new GroundImpactLoadRateAdvice(hlpVar.g());
        this.ap.setText(UnitUtil.e(groundImpactLoadRateAdvice.getValue(), 1, 1));
        this.aj.setText(groundImpactLoadRateAdvice.acquireLevelShortTip());
        this.aj.setTextColor(getResources().getColor(gvv.d(groundImpactLoadRateAdvice.acquireLevel())));
    }

    private void setImpactPeakPosture(hlp hlpVar) {
        if (Float.compare(hlpVar.n(), 0.0f) <= 0) {
            this.aq.setVisibility(8);
            this.ar.setVisibility(8);
            return;
        }
        this.aq.setVisibility(0);
        this.ar.setVisibility(0);
        GroundShockPeakAdvice groundShockPeakAdvice = new GroundShockPeakAdvice(hlpVar.n());
        this.at.setText(UnitUtil.e(groundShockPeakAdvice.getValue(), 1, 1));
        this.as.setText(groundShockPeakAdvice.acquireLevelShortTip());
        this.as.setTextColor(getResources().getColor(gvv.d(groundShockPeakAdvice.acquireLevel())));
    }

    private void setVerticalRatioPosture(hlp hlpVar) {
        if (Float.compare(hlpVar.l(), 0.0f) <= 0) {
            this.bf.setVisibility(8);
            this.be.setVisibility(8);
            return;
        }
        this.bf.setVisibility(0);
        this.be.setVisibility(0);
        GroundVerticalAmplitudeRatioAdvice groundVerticalAmplitudeRatioAdvice = new GroundVerticalAmplitudeRatioAdvice(hlpVar.l());
        this.bl.setText(UnitUtil.e(groundVerticalAmplitudeRatioAdvice.getValue(), 2, 1));
        this.bh.setText(groundVerticalAmplitudeRatioAdvice.acquireLevelShortTip());
        this.bh.setTextColor(getResources().getColor(gvv.d(groundVerticalAmplitudeRatioAdvice.acquireLevel())));
    }

    private void setVerticalOscillationPosture(hlp hlpVar) {
        if (Float.compare(hlpVar.k(), 0.0f) <= 0) {
            this.az.setVisibility(8);
            this.bc.setVisibility(8);
            return;
        }
        this.az.setVisibility(0);
        this.bc.setVisibility(0);
        GroundVerticalAmplitudeAdvice groundVerticalAmplitudeAdvice = new GroundVerticalAmplitudeAdvice(hlpVar.k());
        this.bk.setText(UnitUtil.e(d(groundVerticalAmplitudeAdvice.getValue()), 1, 1));
        this.bb.setText(groundVerticalAmplitudeAdvice.acquireLevelShortTip());
        this.bb.setTextColor(getResources().getColor(gvv.d(groundVerticalAmplitudeAdvice.acquireLevel())));
    }

    private float d(float f) {
        return !UnitUtil.h() ? f : (float) UnitUtil.e(f, 0);
    }

    private void setGcTimeBalancePosture(hlp hlpVar) {
        if (Float.compare(hlpVar.f(), 0.0f) <= 0) {
            this.aw.setVisibility(8);
            this.au.setVisibility(8);
            return;
        }
        this.aw.setVisibility(0);
        this.au.setVisibility(0);
        GroundTouchdownBalanceAdvice groundTouchdownBalanceAdvice = new GroundTouchdownBalanceAdvice(hlpVar.f());
        this.ay.setText(this.d.getResources().getString(LanguageUtil.h(this.d) ? R.string._2130849078_res_0x7f022d36 : R.string._2130845183_res_0x7f021dff, UnitUtil.e(groundTouchdownBalanceAdvice.getValue(), 2, 1), UnitUtil.e(100.0f - groundTouchdownBalanceAdvice.getValue(), 2, 1)));
        this.av.setText(groundTouchdownBalanceAdvice.acquireLevelShortTip());
        this.av.setTextColor(getResources().getColor(gvv.d(groundTouchdownBalanceAdvice.acquireLevel())));
    }

    private void e(int i, int i2, List<PostureJudgeBean> list) {
        if (i <= 0) {
            this.bx.setVisibility(8);
            this.bj.setVisibility(8);
        } else {
            AverageHangTimeAdvice averageHangTimeAdvice = new AverageHangTimeAdvice(i, i2, list, SportDetailChartDataType.HANG_TIME);
            this.bp.setText(UnitUtil.e(i, 1, 0));
            this.bi.setText(averageHangTimeAdvice.acquireLevelShortTip());
            this.bi.setTextColor(getResources().getColor(gvv.d(averageHangTimeAdvice.acquireLevel())));
        }
    }

    private void c(float f, int i, List<PostureJudgeBean> list) {
        if (f <= 0.0f) {
            this.by.setVisibility(8);
            this.ad.setVisibility(8);
        } else {
            GroundHangTimeRateAdvice groundHangTimeRateAdvice = new GroundHangTimeRateAdvice((int) (100.0f * f), i, list, SportDetailChartDataType.GROUND_HANG_TIME_RATE);
            this.aa.setText(UnitUtil.e(f, 1, 1));
            this.ac.setText(groundHangTimeRateAdvice.acquireLevelShortTip());
            this.ac.setTextColor(getResources().getColor(gvv.d(groundHangTimeRateAdvice.acquireLevel())));
        }
    }

    public void b() {
        this.cd.setVisibility(8);
    }

    public void c(int i) {
        this.y.setBackgroundColor(i);
        this.y.setAlpha(0.1f);
        this.bj.setBackgroundColor(i);
        this.bj.setAlpha(0.1f);
        this.ad.setBackgroundColor(i);
        this.ad.setAlpha(0.1f);
        this.h.setBackgroundColor(i);
        this.h.setAlpha(0.1f);
        this.cy.setBackgroundColor(i);
        this.cy.setAlpha(0.1f);
    }

    private void setRunningPostureColor(int i) {
        this.cc.setTextColor(i);
        this.ab.setTextColor(i);
        b(i, this.x, this.w, this.u);
        b(i, this.ao, this.at, this.ax);
        b(i, this.al, this.ap, this.an);
        b(i, this.o, this.k, this.l);
        b(i, this.ag, this.ah, this.ai);
        b(i, this.bg, this.bk, this.bm);
        b(i, this.da, this.df, this.cz);
        b(i, this.br, this.bp, this.bo);
        e(i, this.z, this.aa);
        e(i, this.ba, this.ay);
        e(i, this.bd, this.bl);
        this.ce.setColors(getResources().getColor(R.color._2131299277_res_0x7f090bcd), getResources().getColor(R.color._2131299281_res_0x7f090bd1), getResources().getColor(R.color._2131299280_res_0x7f090bd0));
        this.s.setTextColor(i);
        c(i, this.m, this.n);
        c(i, this.p, this.t);
        c(i, this.q, this.r);
        d(i);
        c(i);
    }

    private void c(int i, HealthTextView healthTextView, HealthTextView healthTextView2) {
        healthTextView.setTextColor(i);
        healthTextView.setAlpha(0.5f);
        healthTextView2.setAlpha(0.5f);
    }

    private void e(int i, HealthTextView healthTextView, HealthTextView healthTextView2) {
        healthTextView.setTextColor(i);
        healthTextView2.setTextColor(i);
        healthTextView2.setAlpha(0.5f);
    }

    private void b(int i, HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3) {
        healthTextView.setTextColor(i);
        healthTextView2.setTextColor(i);
        healthTextView3.setTextColor(i);
        healthTextView2.setAlpha(0.5f);
        healthTextView3.setAlpha(0.5f);
    }

    public void i() {
        this.cj.setVisibility(0);
    }

    public void a() {
        this.cj.setVisibility(8);
    }

    public void f() {
        this.ck.setVisibility(8);
    }

    public void e() {
        this.cm.setVisibility(8);
        this.ch.setVisibility(8);
    }

    public void g() {
        this.cn.setVisibility(8);
        this.cu.setVisibility(8);
    }

    public void d(String str, String str2) {
        this.co.setText(str);
        this.ci.setText(str2);
    }

    public void c(String str, String str2) {
        this.cs.setText(str);
        this.cv.setText(str2);
    }

    public void b(String str) {
        this.cq.setText(str);
    }

    public void d(int i) {
        this.cr.setTextColor(i);
        this.cl.setTextColor(i);
        this.co.setTextColor(i);
        this.co.setAlpha(0.5f);
        this.ci.setTextColor(i);
        this.ci.setAlpha(0.5f);
        this.ct.setTextColor(i);
        this.cs.setTextColor(i);
        this.cs.setAlpha(0.5f);
        this.cv.setTextColor(i);
        this.cv.setAlpha(0.5f);
        this.cp.setTextColor(i);
        this.cq.setTextColor(i);
        this.cq.setAlpha(0.5f);
        this.ch.setBackgroundColor(i);
        this.ch.setAlpha(0.1f);
        this.cu.setBackgroundColor(i);
        this.cu.setAlpha(0.1f);
    }

    public BasketballScoreView getBasketballRadarView() {
        return this.f3808a;
    }

    public void setRadarViewVisibility(int i) {
        this.f3808a.setVisibility(i);
    }
}
