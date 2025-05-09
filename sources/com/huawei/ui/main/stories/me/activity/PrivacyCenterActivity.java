package com.huawei.ui.main.stories.me.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.userprofile.DeleteOperationRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.HealthAccessTokenUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.myhuawei.MyHuaweiLogin;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import com.huawei.ui.main.stories.me.activity.PrivacyCenterActivity;
import com.huawei.ui.main.stories.me.views.privacy.AdRecommendActivity;
import com.huawei.ui.main.stories.me.views.privacy.ContentRecommendActivity;
import com.huawei.ui.main.stories.me.views.privacy.ServiceItemActivity;
import com.huawei.ui.main.stories.privacy.datasourcemanager.ManagerDataSourceActivity;
import defpackage.bzs;
import defpackage.csf;
import defpackage.gmz;
import defpackage.ixj;
import defpackage.ixx;
import defpackage.iyb;
import defpackage.jdx;
import defpackage.jsd;
import defpackage.knx;
import defpackage.koq;
import defpackage.mct;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.rvh;
import defpackage.rvn;
import defpackage.rvo;
import defpackage.rvr;
import defpackage.rwa;
import defpackage.sck;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class PrivacyCenterActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f10344a;
    private HealthDivider aa;
    private ImageView ab;
    private RelativeLayout ac;
    private HealthSwitchButton ad;
    private ImageView ae;
    private HealthDivider af;
    private rvo ag;
    private HealthSwitchButton ah;
    private RelativeLayout ai;
    private RelativeLayout ak;
    private RelativeLayout al;
    private HealthSubHeader am;
    private ImageView an;
    private ImageView ao;
    private CustomTextAlertDialog ap;
    private HealthSwitchButton aq;
    private HealthDivider ar;
    private RelativeLayout as;
    private RelativeLayout at;
    private rvr au;
    private HealthSwitchButton av;
    private HealthDivider aw;
    private RelativeLayout ay;
    private RelativeLayout az;
    private HealthDivider b;
    private RelativeLayout ba;
    private ImageView bb;
    private HealthSwitchButton bc;
    private ImageView bd;
    private HealthSwitchButton be;
    private HealthSwitchButton bf;
    private HealthSwitchButton bg;
    private RelativeLayout bh;
    private RelativeLayout bi;
    private ImageView bj;
    private RelativeLayout bk;
    private ImageView bl;
    private HealthDivider bm;
    private ImageView bn;
    private RelativeLayout bo;
    private gmz bq;
    private HealthSwitchButton br;
    private rvn bs;
    private String bt;
    private RelativeLayout bv;
    private RelativeLayout bw;
    private boolean d;
    private boolean e;
    private ImageView f;
    private HealthSwitchButton g;
    private RelativeLayout h;
    private RelativeLayout i;
    private ImageView j;
    private RelativeLayout k;
    private HealthDivider l;
    private RelativeLayout m;
    private HealthDivider n;
    private ImageView o;
    private ImageView p;
    private CustomTitleBar q;
    private RelativeLayout r;
    private Context s;
    private rvh t;
    private RelativeLayout u;
    private HealthSubHeader v;
    private ImageView w;
    private HealthSubHeader x;
    private ImageView y;
    private HealthTextView z;
    private int bp = -1;
    private boolean c = true;
    private boolean aj = false;
    private Handler ax = new e(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("PrivacyCenterActivity", "enter onCreate():");
        a(AnalyticsValue.HEALTH_MINE_SETTING_PRIVACY_2040037.value(), (String) null);
        this.s = this;
        this.bq = gmz.d();
        this.ag = rvo.e(getApplicationContext());
        this.d = Utils.o();
        this.e = Utils.i();
        this.t = new rvh(this.s, this, new c(this));
        HealthAccessTokenUtil.getAtInstance().registerReceiverToGetAt();
        s();
        l();
        sck.c(this.s);
        w();
    }

    private void s() {
        String str;
        LogUtil.a("PrivacyCenterActivity", "enter initView()");
        m();
        String a2 = this.ag.a(4);
        String a3 = this.ag.a(2);
        String a4 = this.ag.a(3);
        String a5 = this.ag.a(6);
        String a6 = this.ag.a(7);
        String b = SharedPreferenceManager.b(this.s, Integer.toString(10000), "health_product_recommend");
        e(this.ag, this.bq, this.d);
        String c2 = this.bq.c(401);
        LogUtil.a("PrivacyCenterActivity", "initView privacyBluetooth = ", a2, ",privacyBaseInfo = ", a3, ",privacySportData = ", a4, ",privacySleepData = ", a5, ",privacyHealthData = ", a6, ",privacyGoodsData = ", b, ",privacyVmallCloud = ", c2);
        if (TextUtils.isEmpty(a5)) {
            this.ag.e(6, "true".equals(a4));
        }
        if (TextUtils.isEmpty(a6)) {
            this.ag.e(7, "true".equals(a4));
            str = a4;
        } else {
            str = a6;
        }
        this.bg.setChecked("true".equals(a3));
        this.bf.setChecked("true".equals(a4));
        this.be.setChecked("true".equals(str));
        String c3 = this.bq.c(402);
        this.ad.setChecked("true".equals(c3));
        this.z.setTextColor(ContextCompat.getColor(this, "true".equals(c3) ? R.color._2131299236_res_0x7f090ba4 : R.color._2131299244_res_0x7f090bac));
        this.ac.setClickable("true".equals(c3));
        String a7 = this.ag.a(11);
        this.g.setChecked(TextUtils.isEmpty(a7) ? knx.e() : "true".equals(a7));
        this.ah.setChecked("1".equals(b));
        this.bc.setChecked("true".equals(c2));
        p();
        k();
        z();
        n();
    }

    private void p() {
        this.bg.setOnCheckedChangeListener(this);
        this.bf.setOnCheckedChangeListener(this);
        this.be.setOnCheckedChangeListener(this);
        this.ad.setOnCheckedChangeListener(this);
        this.h.setOnClickListener(this);
        this.br.setOnCheckedChangeListener(this);
        this.at.setOnClickListener(this);
        this.g.setOnCheckedChangeListener(this);
        this.ah.setOnCheckedChangeListener(this);
        this.aq.setOnCheckedChangeListener(this);
        this.bc.setOnCheckedChangeListener(this);
        this.bv.setOnClickListener(this);
        this.bo.setOnClickListener(this);
    }

    private void z() {
        if (this.ae != null) {
            final HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.healthService_title);
            final HealthTextView healthTextView2 = (HealthTextView) nsy.cMc_(this, R.id.personalized_title);
            this.ae.post(new Runnable() { // from class: rgo
                @Override // java.lang.Runnable
                public final void run() {
                    PrivacyCenterActivity.this.d(healthTextView, healthTextView2);
                }
            });
        }
    }

    public /* synthetic */ void d(HealthTextView healthTextView, HealthTextView healthTextView2) {
        int width = this.ae.getWidth() + BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
        if (LanguageUtil.bc(this.s)) {
            healthTextView.setPadding(width, 0, 0, 0);
            healthTextView2.setPadding(width, 0, 0, 0);
        } else {
            healthTextView.setPadding(0, 0, width, 0);
            healthTextView2.setPadding(0, 0, width, 0);
        }
    }

    private void e(rvo rvoVar, gmz gmzVar, boolean z) {
        String c2 = gmzVar.c(12);
        String b = SharedPreferenceManager.b(this.s, Integer.toString(10000), "personalized_recommend");
        if (TextUtils.isEmpty(b)) {
            this.aq.setChecked("true".equals(c2));
            if (z) {
                return;
            }
            rvoVar.e(12, true);
            return;
        }
        this.aq.setChecked("1".equals(b));
    }

    private void w() {
        int i = LoginInit.getInstance(BaseApplication.getContext()).isKidAccount() ? 8 : 0;
        this.az.setVisibility(i);
        findViewById(R.id.data_sources_manager_divider).setVisibility(i);
        if (LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount()) {
            this.bo.setVisibility(8);
            this.i.setVisibility(8);
            this.b.setVisibility(8);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        q();
        finish();
    }

    private void q() {
        String a2 = this.ag.a(7);
        Intent intent = new Intent();
        intent.putExtra("health_cloud", a2);
        setResult(0, intent);
    }

    private void m() {
        setContentView(R.layout.activity_privacy_center);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.personal_privacy_set_titlebar);
        this.q = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: rfv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNH_(view);
            }
        });
        ((HealthScrollView) nsy.cMc_(this, R.id.privacy_scrollview)).setScrollViewVerticalDirectionEvent(true);
        this.v = (HealthSubHeader) nsy.cMc_(this, R.id.data_sources_and_record_subheader);
        this.x = (HealthSubHeader) nsy.cMc_(this, R.id.data_syn_manager_subheader);
        this.am = (HealthSubHeader) nsy.cMc_(this, R.id.more_subheader);
        this.v.setSubHeaderBackgroundColor(0);
        this.x.setSubHeaderBackgroundColor(0);
        this.am.setSubHeaderBackgroundColor(0);
        f();
        this.bi.setOnClickListener(this);
        this.bk.setOnClickListener(this);
        this.al.setOnClickListener(this);
        this.bh.setOnClickListener(this);
        if (LoginInit.getInstance(this.s).isKidAccount()) {
            this.ba.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.bq.c(11, false, (String) null, (IBaseResponseCallback) null);
        }
        if (CommonUtil.bg()) {
            this.ad.setTrackDrawable(ContextCompat.getDrawable(this, R$drawable.hwswitch_selector_track_emui));
        }
        this.ab = (ImageView) nsy.cMc_(this, R.id.health_kit_data_show_arrow);
        this.o = (ImageView) nsy.cMc_(this, R.id.content_recommend_arrow);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.sport_health_data_share_or_authorize_layout);
        this.az = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.p = (ImageView) findViewById(R.id.img_data_share_authrize_red_point);
        h();
        this.ay = (RelativeLayout) nsy.cMc_(this, R.id.hw_share_huawei_account_with_vmall);
        boolean z = (Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) ? false : true;
        this.ay.setVisibility(z ? 0 : 8);
        this.bv.setVisibility(z ? 8 : 0);
        dNC_(this.ae);
        dNC_(this.ao);
    }

    public /* synthetic */ void dNH_(View view) {
        LogUtil.a("PrivacyCenterActivity", "initView out the page");
        q();
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        ((RelativeLayout) nsy.cMc_(this, R.id.hw_share_huawei_account_with_myhuawei)).setVisibility(MyHuaweiLogin.isSupportMyHuaweiLogin() ? 0 : 8);
        this.av = (HealthSwitchButton) nsy.cMc_(this, R.id.hw_share_huawei_account_with_myhuawei_switch);
        this.av.setChecked("true".equals(this.bq.c(405)));
        this.av.setOnCheckedChangeListener(this);
    }

    private void f() {
        this.bg = (HealthSwitchButton) nsy.cMc_(this, R.id.sync_profile_to_cloud_switch_button);
        this.bf = (HealthSwitchButton) nsy.cMc_(this, R.id.sync_fitness_data_to_cloud_switch_button);
        RelativeLayout relativeLayout = (RelativeLayout) nsy.cMc_(this, R.id.data_sources_manager);
        this.r = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) nsy.cMc_(this, R.id.health_kit_data_show_layout);
        this.ac = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        this.z = (HealthTextView) nsy.cMc_(this, R.id.health_kit_data_show_text);
        this.be = (HealthSwitchButton) nsy.cMc_(this, R.id.sync_health_data_to_cloud_switch_button);
        this.h = (RelativeLayout) nsy.cMc_(this, R.id.clear_cloud_fitness_data_linear_layout);
        this.br = (HealthSwitchButton) nsy.cMc_(this, R.id.hw_health_auto_update_weight_switchbutton);
        this.f = (ImageView) nsy.cMc_(this, R.id.cancel_auth_img);
        this.at = (RelativeLayout) nsy.cMc_(this, R.id.hw_health_privacy_notice);
        this.bv = (RelativeLayout) nsy.cMc_(this, R.id.hw_vmall_privacy_notice);
        this.bw = (RelativeLayout) nsy.cMc_(this, R.id.weight_layout);
        this.ba = (RelativeLayout) nsy.cMc_(this, R.id.share_data_layout);
        this.g = (HealthSwitchButton) nsy.cMc_(this, R.id.hw_health_analysis_and_improvement);
        this.ai = (RelativeLayout) nsy.cMc_(this, R.id.hw_show_setting_goods_main_layout);
        this.as = (RelativeLayout) nsy.cMc_(this, R.id.hw_show_setting_personalization_main_layout);
        RelativeLayout relativeLayout3 = (RelativeLayout) nsy.cMc_(this, R.id.content_recommend_layout);
        this.m = relativeLayout3;
        relativeLayout3.setOnClickListener(this);
        this.n = (HealthDivider) nsy.cMc_(this, R.id.divider_content_recommend);
        RelativeLayout relativeLayout4 = (RelativeLayout) nsy.cMc_(this, R.id.ad_recommend_layout);
        this.i = relativeLayout4;
        relativeLayout4.setOnClickListener(this);
        this.b = (HealthDivider) nsy.cMc_(this, R.id.divider_ad_recommend);
        this.j = (ImageView) nsy.cMc_(this, R.id.ad_recommend_arrow);
        this.ah = (HealthSwitchButton) nsy.cMc_(this, R.id.goods_switch);
        this.aq = (HealthSwitchButton) nsy.cMc_(this, R.id.personalization_switch);
        this.ar = (HealthDivider) nsy.cMc_(this, R.id.divider_personalization);
        this.aa = (HealthDivider) nsy.cMc_(this, R.id.divider_health);
        this.ae = (ImageView) nsy.cMc_(this, R.id.iv_healthServiceRecommendation);
        this.ao = (ImageView) nsy.cMc_(this, R.id.iv_personalizedRecommendation);
        this.bo = (RelativeLayout) nsy.cMc_(this, R.id.training_statistics_setting_main_layout);
        this.bn = (ImageView) nsy.cMc_(this, R.id.training_statistics_arrow);
        this.bc = (HealthSwitchButton) nsy.cMc_(this, R.id.hw_share_huawei_account_with_vmall_switch);
        this.ak = (RelativeLayout) nsy.cMc_(this, R.id.clear_cloud_fitness_data_linear_layout_1);
        this.u = (RelativeLayout) nsy.cMc_(this, R.id.clear_cloud_fitness_data_linear_layout);
        this.ak.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.w = (ImageView) nsy.cMc_(this, R.id.data_source_manager_arrow);
        this.y = (ImageView) nsy.cMc_(this, R.id.data_use_record_arrow);
        this.bb = (ImageView) nsy.cMc_(this, R.id.sport_health_data_share_or_authrize_arrow);
        this.bl = (ImageView) nsy.cMc_(this, R.id.third_party_sdk_arrow);
        this.ad = (HealthSwitchButton) nsy.cMc_(this, R.id.HUAWEI_health_kit_read_and_write_switch_button);
        this.aw = (HealthDivider) nsy.cMc_(this, R.id.health_divider_sdk);
        this.bi = (RelativeLayout) nsy.cMc_(this, R.id.third_party_sdk);
        this.bj = (ImageView) nsy.cMc_(this, R.id.third_shared_msg_arrow);
        this.bm = (HealthDivider) nsy.cMc_(this, R.id.health_divider_third_shared_msg);
        this.bk = (RelativeLayout) nsy.cMc_(this, R.id.third_shared_msg);
        this.an = (ImageView) nsy.cMc_(this, R.id.personal_info_arrow);
        this.bd = (ImageView) nsy.cMc_(this, R.id.statement_abstract_arrow);
        this.af = (HealthDivider) nsy.cMc_(this, R.id.health_divider_info);
        this.f10344a = (HealthDivider) nsy.cMc_(this, R.id.health_divider_abstract);
        this.al = (RelativeLayout) nsy.cMc_(this, R.id.personal_info);
        this.bh = (RelativeLayout) nsy.cMc_(this, R.id.statement_abstract);
        RelativeLayout relativeLayout5 = (RelativeLayout) nsy.cMc_(this, R.id.hw_health_complaint_report);
        this.k = relativeLayout5;
        relativeLayout5.setOnClickListener(this);
        this.l = (HealthDivider) nsy.cMc_(this, R.id.divider_complaint_report);
    }

    private void dNC_(ImageView imageView) {
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: rga
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PrivacyCenterActivity.this.dNG_(view);
                }
            });
        }
    }

    public /* synthetic */ void dNG_(View view) {
        if (view.getId() == R.id.iv_personalizedRecommendation) {
            a(this.s.getString(R$string.IDS_personalized_recommendation_exclamation_mark));
        } else if (view.getId() == R.id.iv_healthServiceRecommendation) {
            a(this.s.getString(R$string.IDS_healthService_recommendations_exclamation_mark));
        } else {
            LogUtil.h("PrivacyCenterActivity", "clickExclamationImage is other view");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(String str) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(str);
        builder.czz_(R$string.sug_dialog_yes, new View.OnClickListener() { // from class: rgp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCanceledOnTouchOutside(false);
        e2.show();
    }

    private void k() {
        if (this.d) {
            this.bo.setVisibility(8);
            this.aw.setVisibility(8);
            this.bi.setVisibility(8);
            this.bk.setVisibility(8);
            this.bm.setVisibility(8);
            this.af.setVisibility(8);
            this.f10344a.setVisibility(8);
            this.al.setVisibility(8);
            this.bh.setVisibility(8);
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            this.i.setVisibility(8);
            this.b.setVisibility(8);
            this.k.setVisibility(8);
            this.l.setVisibility(8);
            if (!this.e) {
                LinearLayout linearLayout = (LinearLayout) nsy.cMc_(this, R.id.user_profile_privacy_sync_layout);
                LinearLayout linearLayout2 = (LinearLayout) nsy.cMc_(this, R.id.user_profile_privacy_cloud_data_manager_layout);
                HealthCardView healthCardView = (HealthCardView) nsy.cMc_(this, R.id.user_profile_privacy_sync_layout_cardview);
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(8);
                healthCardView.setVisibility(8);
                this.x.setVisibility(8);
                this.bw.setVisibility(8);
                this.bv.setVisibility(8);
                this.ai.setVisibility(8);
                this.ay.setVisibility(8);
            }
        }
        if (LanguageUtil.bc(this.s)) {
            ((ImageView) nsy.cMc_(this, R.id.clear_cloud_profile_view1)).setBackgroundResource(R.mipmap._2131820813_res_0x7f11010d);
            ((ImageView) nsy.cMc_(this, R.id.clear_cloud_profile_view2)).setBackgroundResource(R.mipmap._2131820813_res_0x7f11010d);
        }
        x();
        this.as.setVisibility(8);
        this.ar.setVisibility(8);
        this.ai.setVisibility(8);
        this.aa.setVisibility(8);
    }

    private void x() {
        if (LanguageUtil.bc(this.s)) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable._2131427841_res_0x7f0b0201);
            this.f.setBackground(drawable);
            this.w.setBackground(drawable);
            this.y.setBackground(drawable);
            this.bb.setBackground(drawable);
            this.ab.setBackground(drawable);
            this.bl.setBackground(drawable);
            this.bj.setBackground(drawable);
            this.an.setBackground(drawable);
            this.bd.setBackground(drawable);
            this.o.setBackground(drawable);
            this.j.setBackground(drawable);
            this.bn.setBackground(drawable);
            return;
        }
        Drawable drawable2 = ContextCompat.getDrawable(this, R.drawable._2131427842_res_0x7f0b0202);
        this.f.setBackground(drawable2);
        this.w.setBackground(drawable2);
        this.y.setBackground(drawable2);
        this.bb.setBackground(drawable2);
        this.ab.setBackground(drawable2);
        this.bl.setBackground(drawable2);
        this.bj.setBackground(drawable2);
        this.an.setBackground(drawable2);
        this.bd.setBackground(drawable2);
        this.o.setBackground(drawable2);
        this.j.setBackground(drawable2);
        this.bn.setBackground(drawable2);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.a("PrivacyCenterActivity", "onResume enter");
        super.onResume();
        if (this.e) {
            g();
        }
        ac();
    }

    private void h() {
        String b = SharedPreferenceManager.b(this.s, Integer.toString(10000), "privacy_last_clear_cloud_data_time");
        if (TextUtils.isEmpty(b)) {
            this.ak.setVisibility(0);
            this.u.setVisibility(8);
            return;
        }
        try {
            e(Long.parseLong(b));
        } catch (NumberFormatException e2) {
            this.ak.setVisibility(0);
            this.u.setVisibility(8);
            LogUtil.h("PrivacyCenterActivity", "getLastClearCloudTimeFromLocal ", e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j) {
        this.ak.setVisibility(8);
        this.u.setVisibility(0);
        ((HealthTextView) nsy.cMc_(this, R.id.clear_cloud_profile_time)).setText(new SpannableString(this.s.getString(R$string.IDS_hw_privacy_last_clear_time, UnitUtil.a(new Date(j), 20), new SimpleDateFormat("HH:mm").format(new Date(j)))));
    }

    private void l() {
        String b = SharedPreferenceManager.b(this.s, Integer.toString(10000), "health_weight_auto_update");
        this.bt = b;
        this.br.setChecked("1".equals(b));
        jdx.b(new Runnable() { // from class: rgq
            @Override // java.lang.Runnable
            public final void run() {
                PrivacyCenterActivity.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_auto_update_status");
        if (userPreference != null) {
            this.ax.sendMessage(this.ax.obtainMessage(100, userPreference));
        }
    }

    private void a(final Boolean bool) {
        LogUtil.a("PrivacyCenterActivity", "save weight switch status ", bool);
        jdx.b(new Runnable() { // from class: rgi
            @Override // java.lang.Runnable
            public final void run() {
                PrivacyCenterActivity.d(bool);
            }
        });
    }

    public static /* synthetic */ void d(Boolean bool) {
        String str = bool.booleanValue() ? "1" : "0";
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("custom.weight_auto_update_status");
        hiUserPreference.setValue(str);
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(10026);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).synCloud(hiSyncOption, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HiUserPreference hiUserPreference) {
        if (hiUserPreference != null) {
            LogUtil.a("PrivacyCenterActivity", "weight switch status hiUserPreference.getValue ", hiUserPreference.getValue(), " mWeightAutoUpdate ", this.bt);
            if (hiUserPreference.getValue() == null || !hiUserPreference.getValue().equals(this.bt)) {
                this.br.setChecked("1".equals(hiUserPreference.getValue()));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    private void c(int i, int i2) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R$string.IDS_settings_restore_factory_settings_dialog_title).d(i).cyU_(i2, new View.OnClickListener() { // from class: rgb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNP_(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rfy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNQ_(view);
            }
        }).a();
        this.ap = a2;
        a2.setCancelable(false);
        if (this.ap.isShowing()) {
            return;
        }
        this.ap.show();
    }

    public /* synthetic */ void dNP_(View view) {
        i(false);
        e(false);
        c("2");
        g(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNQ_(View view) {
        this.c = false;
        i(true);
        g(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(String str) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(R$string.IDS_settings_restore_factory_settings_dialog_title).e(str).cyU_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: rgf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNR_(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rge
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNS_(view);
            }
        }).a();
        this.ap = a2;
        a2.setCancelable(false);
        if (this.ap.isShowing()) {
            return;
        }
        this.ap.show();
    }

    public /* synthetic */ void dNR_(View view) {
        i(true);
        e(true);
        c("1");
        g(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNS_(View view) {
        this.c = false;
        i(false);
        g(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(boolean z) {
        int i = this.bp;
        if (i == R.id.sync_profile_to_cloud_switch_button) {
            this.bg.setChecked(z);
        } else if (i == R.id.sync_fitness_data_to_cloud_switch_button) {
            this.bf.setChecked(z);
        } else if (i == R.id.sync_health_data_to_cloud_switch_button) {
            this.be.setChecked(z);
        } else if (i == R.id.hw_health_analysis_and_improvement) {
            this.g.setChecked(z);
        } else if (i == R.id.goods_switch) {
            this.ah.setChecked(z);
        } else if (i == R.id.personalization_switch) {
            this.aq.setChecked(z);
        } else if (i == R.id.HUAWEI_health_kit_read_and_write_switch_button) {
            this.z.setTextColor(ContextCompat.getColor(this, z ? R.color._2131299236_res_0x7f090ba4 : R.color._2131299244_res_0x7f090bac));
            this.ad.setChecked(z);
            this.ac.setClickable(z);
        } else if (i == R.id.hw_share_huawei_account_with_vmall_switch) {
            this.bc.setChecked(z);
        } else if (i == R.id.hw_share_huawei_account_with_myhuawei_switch) {
            this.av.setChecked(z);
        } else {
            LogUtil.h("PrivacyCenterActivity", "setSwitchButtonCheck else");
        }
        int i2 = this.bp;
        if (i2 == R.id.clear_cloud_fitness_data_linear_layout || i2 == R.id.clear_cloud_fitness_data_linear_layout_1) {
            this.c = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        int i;
        int i2 = this.bp;
        if (i2 == R.id.sync_profile_to_cloud_switch_button) {
            i = 2;
        } else if (i2 == R.id.sync_fitness_data_to_cloud_switch_button) {
            i = 3;
        } else if (i2 == R.id.sync_health_data_to_cloud_switch_button) {
            if (z) {
                HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(HiSyncOption.getDefaultHealthSyncOption(), null);
            }
            i = 7;
        } else {
            if (i2 == R.id.HUAWEI_health_kit_read_and_write_switch_button) {
                this.bq.c(402, z, String.valueOf(402), new IBaseResponseCallback() { // from class: rgm
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i3, Object obj) {
                        PrivacyCenterActivity.this.a(i3, obj);
                    }
                });
                i(z);
                this.ad.setEnabled(true);
                return;
            }
            if (i2 == R.id.clear_cloud_fitness_data_linear_layout || i2 == R.id.clear_cloud_fitness_data_linear_layout_1) {
                this.t.e();
                return;
            }
            if (i2 == R.id.goods_switch) {
                i = 10;
            } else if (i2 == R.id.personalization_switch) {
                i = 12;
            } else if (i2 == R.id.hw_share_huawei_account_with_vmall_switch) {
                i = 401;
            } else if (i2 == R.id.hw_share_huawei_account_with_myhuawei_switch) {
                i = 405;
            } else {
                LogUtil.h("PrivacyCenterActivity", "savePersonalPrivacySettings else");
                i = -1;
            }
        }
        LogUtil.a("PrivacyCenterActivity", "privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        this.ag.e(i, z);
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (i == 0) {
            LogUtil.a("PrivacyCenterActivity", "onResponse setUserPrivacy success");
        } else {
            nrh.b(this, R$string.IDS_connect_network);
            LogUtil.b("PrivacyCenterActivity", "onResponse setUserPrivacy failure");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("PrivacyCenterActivity", "onActivityResult requestCode = ", Integer.valueOf(i), ", resultCode = ", Integer.valueOf(i2));
        boolean z = i2 == -1;
        switch (i) {
            case a.C /* 210 */:
                if (z) {
                    b(true, AnalyticsValue.HEALTH_MINE_SETTINGS_RECOMMEND_AD_2040044.value(), "1", true);
                    break;
                } else {
                    i(false);
                    break;
                }
            case a.D /* 211 */:
                if (z) {
                    v();
                    break;
                } else {
                    i(false);
                    break;
                }
            case a.K /* 212 */:
                if (z) {
                    b(true, AnalyticsValue.HEALTH_PERSONALIZED_RECOMMENDATION_2041098.value(), "1", true);
                    break;
                } else {
                    this.c = false;
                    i(false);
                    break;
                }
            default:
                this.t.c(i, i2);
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.bg.setOnCheckedChangeListener(null);
        this.bf.setOnCheckedChangeListener(null);
        this.be.setOnCheckedChangeListener(null);
        this.g.setOnCheckedChangeListener(null);
        this.aq.setOnCheckedChangeListener(null);
        this.bg.setChecked(false);
        this.bf.setChecked(false);
        this.be.setChecked(false);
        boolean z = (Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) ? false : true;
        this.g.setChecked(z);
        this.ah.setChecked(false);
        this.aq.setChecked(z);
        this.bg.setOnCheckedChangeListener(this);
        this.bf.setOnCheckedChangeListener(this);
        this.be.setOnCheckedChangeListener(this);
        this.g.setOnCheckedChangeListener(this);
        this.aq.setOnCheckedChangeListener(this);
        this.t.d(this.ag, z);
    }

    /* loaded from: classes7.dex */
    static class c implements IBaseResponseCallback {
        private WeakReference<PrivacyCenterActivity> b;

        c(PrivacyCenterActivity privacyCenterActivity) {
            this.b = new WeakReference<>(privacyCenterActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("PrivacyCenterActivity", "ClearDataCallback onResponse errorCode:", Integer.valueOf(i));
            WeakReference<PrivacyCenterActivity> weakReference = this.b;
            if (weakReference == null) {
                LogUtil.h("PrivacyCenterActivity", "ClearDataCallback onResponse WeakReference is null");
                return;
            }
            PrivacyCenterActivity privacyCenterActivity = weakReference.get();
            if (privacyCenterActivity == null) {
                LogUtil.h("PrivacyCenterActivity", "ClearDataCallback onResponse activity is null");
                return;
            }
            if (i == 10 || i == 11) {
                privacyCenterActivity.c = false;
                privacyCenterActivity.i(true);
                return;
            }
            if (i == 12) {
                privacyCenterActivity.i(false);
                privacyCenterActivity.e(false);
            } else if (i == 13) {
                privacyCenterActivity.c = false;
                privacyCenterActivity.i(true);
            } else if (i == 14) {
                privacyCenterActivity.ax.sendMessage(privacyCenterActivity.ax.obtainMessage(101));
            } else {
                LogUtil.h("PrivacyCenterActivity", "ClearDataCallback onResponse to else branch");
            }
        }
    }

    private void g() {
        LogUtil.a("PrivacyCenterActivity", "getLastClearCloudDataTime enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: rfx
            @Override // java.lang.Runnable
            public final void run() {
                PrivacyCenterActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        this.ag.e(new d(this));
    }

    /* loaded from: classes7.dex */
    static class d implements IBaseResponseCallback {
        private WeakReference<PrivacyCenterActivity> b;

        d(PrivacyCenterActivity privacyCenterActivity) {
            this.b = new WeakReference<>(privacyCenterActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            DeleteOperationRecord deleteOperationRecord = obj instanceof DeleteOperationRecord ? (DeleteOperationRecord) obj : null;
            if (deleteOperationRecord == null) {
                LogUtil.h("PrivacyCenterActivity", "getLastClearCloudDataTime record == null");
                return;
            }
            long operateTime = deleteOperationRecord.getOperateTime();
            LogUtil.a("PrivacyCenterActivity", "getLastClearCloudDataTime time = ", Long.valueOf(operateTime));
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "privacy_last_clear_cloud_data_time", String.valueOf(operateTime), new StorageParams());
            PrivacyCenterActivity privacyCenterActivity = this.b.get();
            if (privacyCenterActivity == null) {
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 102;
            obtain.obj = Long.valueOf(operateTime);
            privacyCenterActivity.ax.sendMessage(obtain);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null || nsn.o()) {
            LogUtil.a("PrivacyCenterActivity", "onClick view is null or Fast Click");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        this.bp = id;
        if (id == R.id.clear_cloud_fitness_data_linear_layout || id == R.id.clear_cloud_fitness_data_linear_layout_1) {
            this.t.e(true);
            this.t.c();
        } else if (id == R.id.hw_health_privacy_notice) {
            if (this.d) {
                rvn b = new rvn.d(this.s, this).b();
                this.bs = b;
                b.show();
            } else {
                rvr rvrVar = new rvr(this);
                this.au = rvrVar;
                rvrVar.b(this.s);
            }
        } else if (id == R.id.hw_vmall_privacy_notice) {
            new rwa().e(this.s);
        } else if (id == R.id.sport_health_data_share_or_authorize_layout) {
            ad();
        } else if (id == R.id.health_kit_data_show_layout) {
            Intent intent = new Intent();
            intent.putExtra("jump_key", 2);
            intent.setClass(this.s, ManagerDataSourceActivity.class);
            this.s.startActivity(intent);
        } else if (id == R.id.content_recommend_layout) {
            Intent intent2 = new Intent();
            intent2.setClass(this.s, ContentRecommendActivity.class);
            this.s.startActivity(intent2);
        } else if (id == R.id.ad_recommend_layout) {
            Intent intent3 = new Intent();
            intent3.setClass(this.s, AdRecommendActivity.class);
            this.s.startActivity(intent3);
        } else if (id == R.id.hw_health_complaint_report) {
            aa();
        } else if (id == R.id.training_statistics_setting_main_layout) {
            Intent intent4 = new Intent();
            intent4.setClass(this.s, PrivacyTrainingStatisticsActivity.class);
            this.s.startActivity(intent4);
        } else {
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        int i = this.bp;
        if (i == R.id.data_sources_manager) {
            ReleaseLogUtil.e("R_PrivacyCenterActivity", "click data source manager");
            e();
            return;
        }
        if (i == R.id.third_party_sdk) {
            Intent intent = new Intent();
            intent.putExtra("Agreement_key", "3rdsdk");
            intent.putExtra("Is_show_cancel_key", false);
            intent.setClass(this.s, ServiceItemActivity.class);
            this.s.startActivity(intent);
            return;
        }
        if (i == R.id.third_shared_msg) {
            Intent intent2 = new Intent();
            intent2.putExtra("Agreement_key", "3rdshare");
            intent2.putExtra("Is_show_cancel_key", false);
            intent2.setClass(this.s, ServiceItemActivity.class);
            this.s.startActivity(intent2);
            return;
        }
        if (i != R.id.personal_info) {
            if (i == R.id.statement_abstract) {
                Intent intent3 = new Intent();
                intent3.putExtra("Agreement_key", "di");
                intent3.putExtra("Is_show_cancel_key", false);
                intent3.setClass(this.s, ServiceItemActivity.class);
                this.s.startActivity(intent3);
                return;
            }
            LogUtil.h("PrivacyCenterActivity", "handleOtherView onClick else");
            return;
        }
        if (!ixj.b().a() && !ixj.b().h()) {
            ixj.b().bCO_(false, null);
        }
        boolean z = ContextCompat.checkSelfPermission(this.s, "android.permission.READ_CALL_LOG") == 0;
        boolean z2 = ContextCompat.checkSelfPermission(this.s, "android.permission.READ_CONTACTS") == 0;
        long currentTimeMillis = System.currentTimeMillis();
        if (z) {
            SharedPreferenceManager.c("privacy_center", "communication_record", String.valueOf(currentTimeMillis));
            SharedPreferenceManager.c("privacy_center", "ori_communi_content", String.valueOf(currentTimeMillis));
        }
        if (z2) {
            SharedPreferenceManager.c("privacy_center", "addr_book", String.valueOf(currentTimeMillis));
        }
        o();
        af();
    }

    private void ac() {
        RelativeLayout relativeLayout;
        if (Utils.o() || (relativeLayout = this.az) == null || relativeLayout.getVisibility() != 0) {
            return;
        }
        String b = SharedPreferenceManager.b(this.s, Integer.toString(10000), "wechat_red_dot_show");
        LogUtil.a("PrivacyCenterActivity", " isShowWechatRedDot: ", b);
        this.p.setVisibility("true".equals(b) ? 8 : 0);
    }

    private void ad() {
        Intent launchIntentForPackage;
        PackageManager packageManager = getApplicationContext().getPackageManager();
        if (packageManager == null || (launchIntentForPackage = packageManager.getLaunchIntentForPackage(BaseApplication.getAppPackage())) == null) {
            return;
        }
        launchIntentForPackage.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.thirdpartservice.activity.ThirdPartServiceActivity"));
        this.s.startActivity(launchIntentForPackage);
    }

    private void aa() {
        LogUtil.a("PrivacyCenterActivity", "gotoComplaintReport");
        bzs.e().loadH5ProApp(this.s, "com.huawei.health.h5.complaint", new H5ProLaunchOption.Builder());
    }

    private void af() {
        LogUtil.a("PrivacyCenterActivity", "gotoPersonalInfo");
        bzs.e().loadH5ProApp(this.s, "com.huawei.health.h5.privacy-collection-display", new H5ProLaunchOption.Builder());
    }

    private void o() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.ui.main.stories.me.activity.PrivacyCenterActivity.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null || !koq.e(obj, HiUserInfo.class)) {
                    return;
                }
                List list = (List) obj;
                if (list.size() > 0) {
                    HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                    long currentTimeMillis = System.currentTimeMillis();
                    if (hiUserInfo.getHeight() > 0) {
                        SharedPreferenceManager.c("privacy_center", "user_height", String.valueOf(currentTimeMillis));
                    }
                    if (hiUserInfo.getWeight() > 0.0f) {
                        SharedPreferenceManager.c("privacy_center", "user_weight", String.valueOf(currentTimeMillis));
                    }
                    if (hiUserInfo.getGender() >= 0) {
                        SharedPreferenceManager.c("privacy_center", "sex", String.valueOf(currentTimeMillis));
                    }
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.b("PrivacyCenterActivity", "fetchUserData onFailure");
            }
        });
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == null) {
            LogUtil.a("PrivacyCenterActivity", "onCheckedChanged buttonView is null");
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        LogUtil.a("PrivacyCenterActivity", "onCheckedChanged isChecked() = ", Boolean.valueOf(z));
        int id = compoundButton.getId();
        this.bp = id;
        if (id == R.id.hw_health_analysis_and_improvement) {
            d(z);
        }
        if (!this.c) {
            this.c = true;
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        int i = this.bp;
        if (i == R.id.sync_profile_to_cloud_switch_button) {
            this.t.e(true);
            b(z);
        } else if (i == R.id.sync_fitness_data_to_cloud_switch_button) {
            if (!z) {
                c(R$string.IDS_hwh_sport_data_privacy_switch_close, R$string.IDS_settings_button_ok);
            } else {
                d(getString(R$string.IDS_hwh_sport_data_privacy_switch_open) + "");
            }
        } else if (i == R.id.sync_health_data_to_cloud_switch_button) {
            this.t.e(true);
            c(z);
        } else if (i == R.id.hw_health_auto_update_weight_switchbutton) {
            this.br.setChecked(z);
            String str = z ? "1" : "0";
            SharedPreferenceManager.e(this.s, Integer.toString(10000), "health_weight_auto_update", str, new StorageParams());
            if (compoundButton.isPressed()) {
                a(AnalyticsValue.HEALTH_MINE_SETTINGS_UPDATE_WEIGHT_2040045.value(), str);
                a(Boolean.valueOf(z));
            }
        } else {
            dND_(compoundButton, z);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void dND_(CompoundButton compoundButton, boolean z) {
        int i = this.bp;
        if (i == R.id.goods_switch) {
            if (z) {
                d(z, this.s.getString(R$string.IDS_hw_healthService_agreement), this.s.getString(R$string.IDS_hw_confirm_enable), AnalyticsValue.HEALTH_MINE_SETTINGS_RECOMMEND_AD_2040044.value(), 1);
                return;
            } else {
                b(z, AnalyticsValue.HEALTH_MINE_SETTINGS_RECOMMEND_AD_2040044.value(), "2", false);
                return;
            }
        }
        if (i == R.id.personalization_switch) {
            if (!z) {
                d(!z, this.s.getString(R$string.IDS_close_personalized_recommendation_tips), this.s.getString(R$string.IDS_beta_continue_use), AnalyticsValue.HEALTH_PERSONALIZED_RECOMMENDATION_2041098.value(), 2);
                return;
            } else {
                c(AnalyticsValue.HEALTH_PERSONALIZED_RECOMMENDATION_2041098.value(), "1", a.K);
                return;
            }
        }
        if (i == R.id.hw_share_huawei_account_with_vmall_switch || i == R.id.hw_share_huawei_account_with_myhuawei_switch) {
            if (z) {
                f(i == R.id.hw_share_huawei_account_with_vmall_switch);
                return;
            } else {
                i(false);
                e(false);
                return;
            }
        }
        if (i == R.id.HUAWEI_health_kit_read_and_write_switch_button) {
            if (!CommonUtil.aa(this.s.getApplicationContext())) {
                nrh.b(this, R$string.IDS_connect_network);
                compoundButton.setChecked(!z);
                return;
            } else {
                if (compoundButton.isEnabled()) {
                    compoundButton.setEnabled(false);
                    j(z);
                    return;
                }
                return;
            }
        }
        LogUtil.h("PrivacyCenterActivity", "handleOtherSwitch else");
    }

    private void c(String str, String str2, int i) {
        if (LoginInit.getInstance(this.s).isKidAccount() && (i != 212 || !t())) {
            a(i);
        } else {
            b(true, str, str2, true);
        }
    }

    private void a(int i) {
        Intent intent = new Intent();
        intent.putExtra("requestCode", SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON);
        intent.putExtra(CommonConstant.REALNAMERESULT.KEY_VERIFY_TYPE, "1");
        intent.setClassName(this.s, "com.huawei.health.HuaweiLoginActivity");
        startActivityForResult(intent, i);
    }

    private void a(boolean z) {
        this.aj = z;
    }

    private boolean t() {
        return this.aj;
    }

    private void b(boolean z, String str, String str2, boolean z2) {
        if (!t()) {
            LogUtil.a("PrivacyCenterActivity", "setSwitchStatusAndBiEvent");
            i(z);
            a(str, str2);
            e(z2);
        }
        a(false);
    }

    private void d(boolean z) {
        if (z) {
            e(this.s.getString(R$string.IDS_hwh_privacy_analysis_and_improvement_content));
            return;
        }
        h(false);
        this.bq.c(11, false, (String) null, (IBaseResponseCallback) null);
        csf.e(false);
    }

    private void b(boolean z) {
        if (!z) {
            if (this.t.e(this, R$string.IDS_hw_weight_wifi_userinfo_outh_dialog_msg)) {
                return;
            }
            c(R$string.IDS_hwh_personal_profile_info_privacy_switch_close, R$string.IDS_settings_button_ok);
            return;
        }
        d(getString(R$string.IDS_hwh_personal_profile_info_privacy_switch_open));
    }

    private void c(boolean z) {
        if (!z) {
            if (this.t.e(this, R$string.IDS_hw_weight_wifi_outh_dialog_msg)) {
                return;
            }
            c(j(), R$string.IDS_settings_button_ok);
            return;
        }
        d(i());
    }

    private int j() {
        if (String.valueOf(7).equals(LoginInit.getInstance(this).getAccountInfo(1009))) {
            return R$string.IDS_hwh_data_health_close_europe;
        }
        return R$string.IDS_hwh_data_health_privacy_switch_close_china;
    }

    private String i() {
        if (String.valueOf(7).equals(LoginInit.getInstance(this).getAccountInfo(1009))) {
            return getString(R$string.IDS_hwh_data_health_open_europe) + "";
        }
        String str = getString(R$string.IDS_hwh_data_health_privacy_switch_open_china) + "";
        if (!rvn.c()) {
            return str;
        }
        return str + System.lineSeparator() + getString(R$string.IDS_hwh_privacy_health_data_backup);
    }

    private void d(boolean z, String str, String str2, final String str3, final int i) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(str).czE_(str2.toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rgk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNK_(i, str3, view);
            }
        }).czA_(this.s.getString(R$string.IDS_hw_still_off).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rft
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNL_(i, str3, view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    public /* synthetic */ void dNK_(int i, String str, View view) {
        if (1 == i) {
            c(str, "1", a.C);
        } else {
            a(true);
            i(true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNL_(int i, String str, View view) {
        if (2 == i) {
            b(false, str, "2", false);
        } else {
            a(true);
            i(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f(boolean z) {
        int i;
        int i2 = z ? R$string.IDS_authorized_by_vmall : R$string.IDS_authorized_by_myhuawei;
        if (z) {
            i = R$string.IDS_authorized_by_vmall_dialog_content;
        } else {
            i = R$string.IDS_authorized_by_myhuawei_dialog_content;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(i2).e(BaseApplication.getContext().getResources().getString(i)).cyU_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: rgd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNI_(view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rgg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNJ_(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dNI_(View view) {
        i(true);
        e(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNJ_(View view) {
        i(false);
        e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(String str) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(str).czC_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: rgn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNM_(view);
            }
        }).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rfz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public /* synthetic */ void dNM_(View view) {
        mct.b(this.s, "data_pop_flag", "agree");
        ab();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j(final boolean z) {
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this).b(BaseApplication.getContext().getResources().getString(z ? R$string.IDS_health_kit_open_title : R$string.IDS_health_kit_close_title)).e(BaseApplication.getContext().getResources().getString(z ? R$string.IDS_health_kit_open_content : R$string.IDS_health_kit_close_content)).cyU_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: rgj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNN_(z, view);
            }
        }).cyR_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: rgl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNO_(z, view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    public /* synthetic */ void dNN_(boolean z, View view) {
        e(z);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNO_(boolean z, View view) {
        i(!z);
        this.ad.setEnabled(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("type", "0");
        hashMap.put("entrance", "e2");
        iyb iybVar = new iyb();
        iybVar.d(hashMap);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("pageId", "Privacy_0001");
        iybVar.e(hashMap2);
        ixx.d().a(this.s, AnalyticsValue.HEALTH_MINE_SETTINGS_IMPROVE_PLAN_2040021.value(), iybVar, 0);
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this).e(str).czE_(this.s.getString(R$string.IDS_settings_button_ok).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rgc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNT_(view);
            }
        }).czA_(this.s.getString(R$string.IDS_settings_button_cancal_ios_btn).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rgh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyCenterActivity.this.dNU_(view);
            }
        }).e();
        e2.setCancelable(false);
        e2.show();
    }

    public /* synthetic */ void dNT_(View view) {
        if (LoginInit.getInstance(this.s).isKidAccount()) {
            a(a.D);
        } else {
            v();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNU_(View view) {
        this.bq.c(11, false, (String) null, (IBaseResponseCallback) null);
        i(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void v() {
        this.bq.c(11, true, (String) null, (IBaseResponseCallback) null);
        h(true);
        i(true);
        csf.e(true);
    }

    private void g(boolean z) {
        if (this.d || LoginInit.getInstance(this.s).isKidAccount()) {
            LogUtil.a("PrivacyCenterActivity", "setTrainingStatistics invalid account");
            return;
        }
        int i = this.bp;
        if (i == R.id.sync_fitness_data_to_cloud_switch_button) {
            this.ag.e(202, z && !r());
        } else if (i == R.id.sync_health_data_to_cloud_switch_button) {
            this.ag.e(203, z && u());
        } else {
            LogUtil.h("PrivacyCenterActivity", "setTrainingStatistics else");
        }
    }

    private boolean r() {
        return "0".equals(SharedPreferenceManager.b(this.s, Integer.toString(10000), "key_user_close_training"));
    }

    private boolean u() {
        return "1".equals(SharedPreferenceManager.b(this.s, Integer.toString(10000), "key_user_close_health_training_statistics"));
    }

    private void c(String str) {
        int i = this.bp;
        if (i == R.id.sync_profile_to_cloud_switch_button) {
            a(AnalyticsValue.HEALTH_USER_PROFILE_TO_CLOUD_2040057.value(), str);
            return;
        }
        if (i == R.id.sync_fitness_data_to_cloud_switch_button) {
            a(AnalyticsValue.HEALTH_FITNESS_DATA_TO_CLOUD_2040058.value(), str);
        } else if (i == R.id.sync_health_data_to_cloud_switch_button) {
            a(AnalyticsValue.HEALTH_HEALTH_DATA_TO_CLOUD_2040059.value(), str);
        } else {
            LogUtil.h("PrivacyCenterActivity", "switchButtonBi else");
        }
    }

    private void a(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.s, str, hashMap, 0);
    }

    private void h(boolean z) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (z) {
            hashMap.put("type", "1");
            hashMap.put("service_improvement_status", "0");
        } else {
            hashMap.put("type", "2");
            hashMap.put("service_improvement_status", "1");
        }
        ixx.d().d(this.s, AnalyticsValue.HEALTH_MINE_SETTINGS_IMPROVE_PLAN_2040021.value(), hashMap, 0);
        ixx.d().c(this.s);
        KeyValDbManager.b(this.s).e("key_user_experience_plan_check_box", String.valueOf(z));
    }

    private void ab() {
        Intent intent = new Intent();
        intent.putExtra("jump_key", 1);
        intent.setClass(this.s, ManagerDataSourceActivity.class);
        ReleaseLogUtil.e("R_PrivacyCenterActivity", "start data Source activity");
        this.s.startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.ax.removeCallbacksAndMessages(null);
    }

    /* loaded from: classes7.dex */
    static class e extends BaseHandler<PrivacyCenterActivity> {
        e(PrivacyCenterActivity privacyCenterActivity) {
            super(privacyCenterActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dNV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PrivacyCenterActivity privacyCenterActivity, Message message) {
            switch (message.what) {
                case 100:
                    if (message.obj instanceof HiUserPreference) {
                        privacyCenterActivity.d((HiUserPreference) message.obj);
                        break;
                    } else {
                        LogUtil.a("PrivacyCenterActivity", "msg.obj is instanceof HiUserPreference");
                        break;
                    }
                case 101:
                    privacyCenterActivity.a();
                    break;
                case 102:
                    privacyCenterActivity.e(((Long) message.obj).longValue());
                    break;
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("PrivacyCenterActivity", "onConfigurationChanged");
        sck.b(this.s);
        y();
    }

    private void y() {
        rvr rvrVar;
        rvn rvnVar;
        if (this.d && (rvnVar = this.bs) != null && rvnVar.isShowing()) {
            this.bs.c(this);
        }
        if (this.d || (rvrVar = this.au) == null || !rvrVar.b()) {
            return;
        }
        this.au.b(this);
    }

    private void e() {
        if (!CommonUtil.aa(this)) {
            ab();
            return;
        }
        if (!TextUtils.isEmpty(mct.e(this.s, "data_pop_flag", ""))) {
            ab();
        } else if (jsd.c(BaseApplication.getContext())) {
            b(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_datasource_quantity_des));
        } else {
            b(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_network_quantity_des));
        }
    }
}
