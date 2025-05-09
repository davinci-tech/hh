package com.huawei.ui.main.stories.me.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.health.knit.ui.KnitFragment;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.healthcloud.plugintrack.offlinemap.ui.activity.OfflineMapTabActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hihealth.motion.IExecuteResult;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.downloadwidget.HealthDownLoadWidget;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.guide.interactors.GuideInteractors;
import com.huawei.ui.main.stories.me.activity.AppSettingActivity;
import com.huawei.ui.main.stories.me.util.AppSettingUtil;
import com.huawei.ui.main.stories.settings.activity.ClearDataCacheActivity;
import defpackage.dlf;
import defpackage.dsl;
import defpackage.dss;
import defpackage.gso;
import defpackage.guz;
import defpackage.gva;
import defpackage.gwe;
import defpackage.gwg;
import defpackage.ivv;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.jdi;
import defpackage.koq;
import defpackage.kor;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.pqm;
import defpackage.pxp;
import defpackage.rud;
import defpackage.rvh;
import defpackage.sbk;
import defpackage.scl;
import defpackage.sdj;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes7.dex */
public class AppSettingActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final Object d = new Object();
    private static String e;
    private RelativeLayout aa;
    private ImageView ab;
    private HealthDivider ac;
    private rvh ad;
    private ImageView ae;
    private RelativeLayout af;
    private RelativeLayout ag;
    private ImageView ah;
    private HealthDivider ai;
    private RelativeLayout aj;
    private RelativeLayout ak;
    private HealthRadioButton al;
    private ImageView am;
    private RelativeLayout an;
    private HealthRadioButton ao;
    private RelativeLayout ap;
    private HealthSwitchButton aq;
    private RelativeLayout ar;
    private HealthDivider as;
    private GuideInteractors au;
    private RelativeLayout aw;
    private RelativeLayout ax;
    private HealthRadioButton ay;
    private RelativeLayout az;
    private AppSettingUtil b;
    private boolean ba;
    private boolean bb;
    private ImageView bc;
    private boolean bf;
    private HealthTextView bl;
    private RelativeLayout bn;
    private LinearLayout bo;
    private HealthButton bp;
    private HealthDivider br;
    private ImageView bs;
    private RelativeLayout bt;
    private ImageView bu;
    private ImageView bv;
    private HealthTextView bw;
    private RelativeLayout bx;
    private ImageView bz;
    private ImageView c;
    private HealthDivider ca;
    private RelativeLayout cb;
    private RelativeLayout cc;
    private ImageView cd;
    private RelativeLayout ce;
    private HealthDownLoadWidget cf;
    private ImageView cg;
    private LinearLayout ch;
    private d ci;
    private RelativeLayout cj;
    private HealthTextView ck;
    private ImageView cl;
    private RelativeLayout cm;
    private ImageView co;
    private ImageView cp;
    private RelativeLayout cq;
    private RelativeLayout cr;
    private SocialShareCenterApi cs;
    private ImageView ct;
    private HealthSwitchButton cu;
    private ImageView cv;
    private ImageView cw;
    private ImageView cx;
    private RelativeLayout cy;
    private HealthDivider cz;
    private RelativeLayout da;
    private sbk db;
    private HealthSwitchButton dc;
    private RelativeLayout dd;
    private ImageView de;
    private RelativeLayout df;
    private RelativeLayout dg;
    private LinearLayout f;
    private HealthImageView h;
    private HealthImageView i;
    private HealthTextView j;
    private HealthDivider k;
    private RelativeLayout l;
    private HealthSwitchButton m;
    private RelativeLayout n;
    private HealthRadioButton o;
    private e p;
    private e q;
    private guz r;
    private int s;
    private HealthTextView t;
    private RelativeLayout u;
    private ImageView v;
    private HealthDivider w;
    private RelativeLayout x;
    private e y;
    private Context z;
    private boolean bg = false;
    private HealthOpenSDK av = null;
    private a at = new a(this);
    private boolean bk = false;
    private boolean bi = false;
    private boolean bm = false;
    private boolean bd = false;
    private boolean bh = false;
    private boolean be = false;
    private boolean by = false;
    private int cn = 0;
    private long bq = 0;
    private boolean bj = false;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<HiAppInfo> f10322a = new ArrayList<>(10);
    private Handler g = new b(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_show_settings_app);
        this.ci = new d();
        this.z = this;
        this.db = sbk.a(this);
        this.b = new AppSettingUtil(this.z);
        this.db.dUY_(this.g);
        this.au = new GuideInteractors(this.z);
        this.bj = Utils.o();
        this.ba = LoginInit.getInstance(this.z).isBrowseMode();
        this.ad = new rvh(this.z, this, null);
        this.cs = (SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class);
        q();
        p();
    }

    private void q() {
        this.av = dss.c(this).d();
        this.y = new e("getNotificationSupport");
        this.p = new e("getGoalNotifiState");
        this.q = new e("getStepsNotifiState");
    }

    private void p() {
        LogUtil.a("UIME_AppSettingActivity", "initView to enter");
        l();
        f();
        ag();
        aq();
        af();
        r();
        m();
        ac();
        s();
        ah();
        t();
        ae();
        an();
        if (!"1".equals(SharedPreferenceManager.b(this.z, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            this.bp.setVisibility(8);
        }
        String b2 = SharedPreferenceManager.b(this.z, Integer.toString(10000), "key_settings_cache_size");
        this.ck.setText(b2);
        this.t.setText(b2);
        this.dc.setChecked(c());
        d();
        if (CommonUtil.bb()) {
            return;
        }
        ((HealthTextView) findViewById(R.id.hw_stop_health_text)).setText(R$string.IDS_hw_stop_health2);
    }

    private void f() {
        ((HealthScrollView) findViewById(R.id.app_setting_activity_main_layout)).setScrollViewVerticalDirectionEvent(true);
        this.am = (ImageView) findViewById(R.id.goal_image_view);
        this.de = (ImageView) findViewById(R.id.about_wechat_rank_account);
        this.cx = (ImageView) findViewById(R.id.about_change_other_account);
        this.bv = (ImageView) findViewById(R.id.about_offline_map);
        this.bu = (ImageView) findViewById(R.id.hw_map_type_right_arrow);
        this.cd = (ImageView) findViewById(R.id.plugin_management_arrow);
        this.cg = (ImageView) findViewById(R.id.plugin_setting_arrow);
        this.bc = (ImageView) findViewById(R.id.health_todo_right_arrow);
        this.bs = (ImageView) findViewById(R.id.notification_message_right_arrow);
        this.ct = (ImageView) findViewById(R.id.sport_setting_item_right_arrow);
        this.cv = (ImageView) findViewById(R.id.hw_setting_unit_right_arrow);
        this.co = (ImageView) findViewById(R.id.hw_setting_service_model_right_arrow);
        this.cl = (ImageView) findViewById(R.id.service_express_card_plugin_setting_arrow);
        this.bz = (ImageView) findViewById(R.id.user_profile_opensource_right_arrow);
        this.ab = (ImageView) findViewById(R.id.clear_cache_right_arrow);
        this.v = (ImageView) findViewById(R.id.clear_cache_arrow);
        this.cw = (ImageView) findViewById(R.id.stop_health_arrow);
        this.ck = (HealthTextView) findViewById(R.id.app_setting_cache_size);
        this.t = (HealthTextView) findViewById(R.id.app_cache_size);
        this.ah = (ImageView) findViewById(R.id.device_share_img);
        this.cp = (ImageView) findViewById(R.id.setting_select_img);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.user_profile_health_equipment_capacity_opening_layout);
        this.ag = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.ae = (ImageView) findViewById(R.id.equipment_capacity_opening_img);
        HealthButton healthButton = (HealthButton) findViewById(R.id.hw_show_settings_login);
        this.bp = healthButton;
        healthButton.setOnClickListener(this);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.show_voice_switch);
        this.dc = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(this);
        HealthSwitchButton healthSwitchButton2 = (HealthSwitchButton) findViewById(R.id.app_setting_scan_automatic);
        this.m = healthSwitchButton2;
        healthSwitchButton2.setChecked(true ^ this.au.b());
        this.m.setOnCheckedChangeListener(this);
        HealthSwitchButton healthSwitchButton3 = (HealthSwitchButton) findViewById(R.id.share_userinfo_switch);
        this.cu = healthSwitchButton3;
        healthSwitchButton3.setChecked(this.cs.isShowUserInfo());
        this.cu.setOnCheckedChangeListener(this);
        v();
    }

    private void v() {
        if (LanguageUtil.bc(this.z)) {
            Drawable drawable = getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201);
            this.am.setBackground(drawable);
            this.cx.setBackground(drawable);
            this.bv.setBackground(drawable);
            this.bu.setBackground(drawable);
            this.cd.setBackground(drawable);
            this.cg.setBackground(drawable);
            this.bc.setBackground(drawable);
            this.bs.setBackground(drawable);
            this.cl.setBackground(drawable);
            this.ct.setBackground(drawable);
            this.cv.setBackground(drawable);
            this.co.setBackground(drawable);
            this.de.setBackground(drawable);
            this.bz.setBackground(drawable);
            this.ab.setBackground(drawable);
            this.v.setBackground(drawable);
            this.cw.setBackground(drawable);
            this.ah.setBackground(drawable);
            this.cp.setBackground(drawable);
            this.ae.setBackground(drawable);
            this.i.setBackground(drawable);
            return;
        }
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202);
        this.am.setBackground(drawable2);
        this.cx.setBackground(drawable2);
        this.bv.setBackground(drawable2);
        this.bu.setBackground(drawable2);
        this.cd.setBackground(drawable2);
        this.cg.setBackground(drawable2);
        this.bc.setBackground(drawable2);
        this.bs.setBackground(drawable2);
        this.cl.setBackground(drawable2);
        this.ct.setBackground(drawable2);
        this.cv.setBackground(drawable2);
        this.co.setBackground(drawable2);
        this.de.setBackground(drawable2);
        this.bz.setBackground(drawable2);
        this.ab.setBackground(drawable2);
        this.v.setBackground(drawable2);
        this.cw.setBackground(drawable2);
        this.ah.setBackground(drawable2);
        this.cp.setBackground(drawable2);
        this.ae.setBackground(drawable2);
        this.i.setBackground(drawable2);
    }

    private void ag() {
        this.ar = (RelativeLayout) findViewById(R.id.hw_show_setting_gps_low_consumption_layout);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.show_gps_low_consumption_switch);
        this.aq = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(this);
        String b2 = SharedPreferenceManager.b(this, "flp_track_switch", "flp_track_flag");
        if (this.b.a(b2)) {
            this.aq.setChecked(false);
            this.bg = true;
        } else if ("1".equals(b2)) {
            this.aq.setChecked(true);
            this.bg = true;
        } else {
            LogUtil.h("UIME_AppSettingActivity", "setGpsLowPower this status is not exist");
        }
        this.bf = this.aq.isChecked();
        if (gva.a() && gwg.f()) {
            this.ar.setVisibility(0);
            LogUtil.a("UIME_AppSettingActivity", "setGpsLowPower support low power");
        } else {
            this.ar.setVisibility(8);
            LogUtil.a("UIME_AppSettingActivity", "setGpsLowPower don't support low power");
        }
        this.ar.setVisibility(8);
        this.cb = (RelativeLayout) findViewById(R.id.hw_offline_map_layout);
        this.df = (RelativeLayout) findViewById(R.id.hw_show_setting_voice_layout);
        this.cy = (RelativeLayout) findViewById(R.id.hw_stop_health_layout);
        this.bo = (LinearLayout) findViewById(R.id.hw_manual_synchronous_layout);
        this.l = (RelativeLayout) findViewById(R.id.hw_show_auto_scan_layout);
        this.br = (HealthDivider) findViewById(R.id.manual_sync_divider);
        this.bn = (RelativeLayout) findViewById(R.id.hw_manual_synchronous_rl_layout);
        LogUtil.a("UIME_AppSettingActivity", "setGpsLowPower(), CommonUtil.isSupportWearProduct() = ", Boolean.valueOf(CommonUtil.ce()));
    }

    private void aq() {
        if (CommonUtil.ce() && !EnvironmentInfo.k()) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
        this.cf = (HealthDownLoadWidget) findViewById(R.id.syn_circle_progress);
        String upperCase = nsf.h(R$string.IDS_device_wear_home_confirm_sync_account).toUpperCase();
        this.cf.setIdleText(upperCase);
        this.cf.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.syn_circle_progress_layout);
        this.aw = relativeLayout;
        jcf.bEA_(relativeLayout, upperCase, Button.class);
        this.cb.setOnClickListener(this);
        this.cy.setOnClickListener(this);
        this.af = (RelativeLayout) findViewById(R.id.hw_show_developer_options_layout);
        this.w = (HealthDivider) findViewById(R.id.setting_clear_cache_image);
        this.ch = (LinearLayout) findViewById(R.id.app_setting_activity_layout_top);
        this.af.setOnClickListener(this);
        if (a()) {
            this.af.setVisibility(0);
            this.w.setVisibility(0);
        } else {
            this.af.setVisibility(8);
            this.w.setVisibility(8);
        }
        this.df.setVisibility(8);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.user_profile_opensource_layout);
        this.dd = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
    }

    private void af() {
        this.cc = (RelativeLayout) findViewById(R.id.plugin_management_layout);
        this.ce = (RelativeLayout) findViewById(R.id.plugin_setting_layout);
        if (AppBundle.c().getAllBundleModules(this, true).isEmpty()) {
            this.cc.setVisibility(8);
            this.ce.setVisibility(8);
        } else {
            this.cc.setOnClickListener(this);
            this.ce.setOnClickListener(this);
        }
    }

    private void r() {
        this.aa = (RelativeLayout) findViewById(R.id.app_setting_clear_cache);
        this.u = (RelativeLayout) findViewById(R.id.app_clear_cache);
        this.dg = (RelativeLayout) findViewById(R.id.hw_wechat_rank_layout);
        this.ak = (RelativeLayout) findViewById(R.id.hw_goal_layout);
        this.as = (HealthDivider) findViewById(R.id.hw_goal_line);
        this.aj = (RelativeLayout) findViewById(R.id.user_profile_device_share_layout);
        this.ai = (HealthDivider) findViewById(R.id.device_capacity_opening_divider_line);
        this.aj.setOnClickListener(this);
        this.ac = (HealthDivider) findViewById(R.id.user_profile_health_data_share_line);
        this.dg.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        dMO_(this.aa);
        dMO_(this.u);
        this.x = (RelativeLayout) findViewById(R.id.hw_change_other_account_layout);
        if (CommonUtil.bu()) {
            this.cf.setClickable(false);
        } else {
            this.cf.setClickable(true);
        }
        if (nsn.s()) {
            this.aa.setVisibility(8);
            this.u.setVisibility(0);
        } else {
            this.aa.setVisibility(0);
            this.u.setVisibility(8);
        }
    }

    private void m() {
        this.ax = (RelativeLayout) findViewById(R.id.health_todo_layout);
        TodoDataApi todoDataApi = (TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class);
        todoDataApi.isShowTodo();
        if (todoDataApi.isShowTodo()) {
            this.ax.setVisibility(0);
            this.ax.setOnClickListener(this);
            ao();
            return;
        }
        this.ax.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ao() {
        ((HealthTextView) findViewById(R.id.health_todo_type_text)).setText(((TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class)).isMainSwitchChecked() ? R$string.IDS_status_enabled : R$string.IDS_status_disabled);
    }

    private void ac() {
        this.bx = (RelativeLayout) findViewById(R.id.notification_message_layout);
        if (!"com.huawei.health".equals(BaseApplication.getAppPackage())) {
            this.bx.setVisibility(8);
        }
        this.bx.setOnClickListener(this);
        this.cq = (RelativeLayout) findViewById(R.id.setting_select_zip_file);
        if (CommonUtil.bo()) {
            this.cq.setVisibility(0);
            SharedPreferences.Editor edit = this.z.getSharedPreferences("devicedownloadtime", 0).edit();
            edit.putBoolean("isdemo", true);
            edit.commit();
        } else {
            this.cq.setVisibility(8);
        }
        this.cq.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.sport_setting_item_layout);
        this.da = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.cz = (HealthDivider) findViewById(R.id.sport_setting_line);
        this.bl = (HealthTextView) findViewById(R.id.last_sync_time_tv);
        ai();
    }

    private void s() {
        this.bt = (RelativeLayout) findViewById(R.id.hw_show_map_type_layout);
        this.bw = (HealthTextView) findViewById(R.id.hw_show_map_type_text);
        this.ca = (HealthDivider) findViewById(R.id.hw_offline_map_divider);
        this.bt.setOnClickListener(this);
    }

    private void ah() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hw_show_setting_unit_layout);
        this.cr = relativeLayout;
        relativeLayout.setOnClickListener(this);
        if (!Utils.o()) {
            u();
            return;
        }
        if (Utils.i()) {
            this.bo.setVisibility(0);
            this.br.setVisibility(0);
            n();
        } else {
            this.as.setVisibility(8);
            this.ac.setVisibility(8);
            this.ch.setVisibility(8);
            n();
        }
    }

    private void t() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.service_express_card_setting_layout);
        this.cj = relativeLayout;
        relativeLayout.setOnClickListener(this);
        this.c = (ImageView) findViewById(R.id.service_express_card_red_point);
        if (pqm.c()) {
            this.cj.setVisibility(0);
        } else {
            this.cj.setVisibility(8);
        }
    }

    private void ae() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hw_show_setting_service_model_layout);
        this.cm = relativeLayout;
        relativeLayout.setOnClickListener(this);
        if (CommonUtil.h(LoginInit.getInstance(this.z).getAccountInfo(1009)) == 1 || SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false)) {
            this.cm.setVisibility(0);
        }
    }

    private void l() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.cl_auto_identify_record);
        this.f = linearLayout;
        linearLayout.setOnClickListener(this);
        this.h = (HealthImageView) findViewById(R.id.hiv_red_point);
        this.j = (HealthTextView) findViewById(R.id.tv_auto_identify_record_right_text);
        this.i = (HealthImageView) findViewById(R.id.iv_auto_identify_record_arrow);
        this.k = (HealthDivider) findViewById(R.id.auto_identify_record_divider);
    }

    private void an() {
        if (this.ba) {
            this.ak.setVisibility(8);
            this.as.setVisibility(8);
            this.bo.setVisibility(8);
            this.br.setVisibility(8);
            this.ag.setVisibility(8);
            this.da.setVisibility(8);
            this.bx.setVisibility(8);
            return;
        }
        this.ak.setVisibility(0);
        this.as.setVisibility(0);
        this.bo.setVisibility(0);
        this.br.setVisibility(0);
        if (EnvironmentInfo.k()) {
            this.ag.setVisibility(8);
        } else {
            this.ag.setVisibility(0);
        }
        this.da.setVisibility(0);
        this.bx.setVisibility(0);
    }

    private void u() {
        if (this.ba) {
            this.cb.setVisibility(8);
        } else {
            this.cb.setVisibility(0);
        }
        this.ch.setVisibility(0);
        if (!CommonUtil.bx() && !gwe.d(this.z)) {
            this.bt.setVisibility(8);
            this.ca.setVisibility(8);
        } else {
            this.bt.setVisibility(0);
            this.ca.setVisibility(0);
        }
        this.g.sendEmptyMessage(104);
        this.x.setVisibility(8);
    }

    private void n() {
        this.dg.setVisibility(8);
        this.x.setVisibility(8);
        this.cb.setVisibility(8);
        if (CommonUtil.bh() && CommonUtil.bx() && gwe.d(this.z)) {
            this.bt.setVisibility(0);
            this.ca.setVisibility(0);
            this.g.sendEmptyMessage(104);
        } else {
            this.bt.setVisibility(8);
            this.ca.setVisibility(8);
        }
    }

    private void ai() {
        String b2 = SharedPreferenceManager.b(this.z, Integer.toString(10000), "last_sync_time");
        if (b2 == null || "".equals(b2) || "0".equals(b2)) {
            return;
        }
        LogUtil.a("UIME_AppSettingActivity", "setLastSyncTime lastSyncTime = ", b2);
        Date date = null;
        long j = 0;
        if (b2.contains(":")) {
            try {
                date = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(b2);
                if (date != null) {
                    j = date.getTime();
                }
            } catch (ParseException e2) {
                LogUtil.b("UIME_AppSettingActivity", "setLastSyncTime Exception: ", e2.getMessage());
            }
        } else {
            try {
                j = Long.parseLong(b2);
                date = new Date(j);
            } catch (NumberFormatException e3) {
                LogUtil.b("UIME_AppSettingActivity", "setLastSyncTime Exception: ", e3.getMessage());
            }
        }
        if (date != null) {
            LogUtil.a("UIME_AppSettingActivity", "setLastSyncTime date = ", date);
            StringBuilder b3 = b(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMdd")).format(date), nsj.c(this.z, j, 1));
            this.bl.setVisibility(0);
            this.bl.setText(nsf.h(R$string.IDS_hw_last_sync_time) + ((Object) b3));
            jcf.bEz_(this.bn, nsf.b(R$string.accessibility_setting_manual_sync_time, b3));
        }
    }

    private StringBuilder b(String str, String str2) {
        if (str.isEmpty() || str2.isEmpty()) {
            LogUtil.h("UIME_AppSettingActivity", "setLastTime formatDate is empty or formatTime is empty");
        }
        StringBuilder sb = new StringBuilder();
        if (LanguageUtil.p(this.z) || LanguageUtil.be(this.z) || LanguageUtil.bg(this.z) || LanguageUtil.bb(this.z) || LanguageUtil.r(this.z)) {
            sb.append(" ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
        } else {
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
        }
        return sb;
    }

    private void k() {
        this.r = gso.e().d(this.z);
        if (y()) {
            this.bb = true;
            this.f.setVisibility(0);
            this.k.setVisibility(0);
            this.j.setText(this.r.e() ? R$string.IDS_status_enabled : R$string.IDS_status_disabled);
            this.h.setVisibility(this.r.g() ? 0 : 8);
            return;
        }
        this.bb = false;
        this.f.setVisibility(8);
        this.k.setVisibility(8);
    }

    private boolean y() {
        guz guzVar = this.r;
        if (guzVar == null) {
            LogUtil.h("UIME_AppSettingActivity", "isShowAutoTrackEntrance mAutoTrackConfig is null");
            return false;
        }
        if (!guzVar.b()) {
            LogUtil.h("UIME_AppSettingActivity", "isShowAutoTrackEntrance is not show AutoTrackBtn");
            return false;
        }
        if (!"1".equals(KeyValDbManager.b(this.z).e("SUPPORT_AR_ABILITY"))) {
            LogUtil.h("UIME_AppSettingActivity", "isShowAutoTrackEntrance AR capability is not supported");
            return false;
        }
        if (nsn.ae(this.z)) {
            LogUtil.h("UIME_AppSettingActivity", "isShowAutoTrackEntrance the current device is pad");
            return false;
        }
        if (Utils.o()) {
            LogUtil.h("UIME_AppSettingActivity", "isShowAutoTrackEntrance the version is oversea");
            return false;
        }
        if (!rud.e()) {
            return true;
        }
        LogUtil.h("UIME_AppSettingActivity", "isShowAutoTrackEntrance is base service model");
        return false;
    }

    private boolean c() {
        String b2 = SharedPreferenceManager.b(this.z, Integer.toString(20002), "voice_enable_type");
        return b2 == null || !"0".equals(b2);
    }

    private void d() {
        HiHealthNativeApi.a(BaseApplication.getContext()).queryWearKitAppInfo(new g(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("UIME_AppSettingActivity", "checkApkExist NameNotFoundException");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Context context, HiAppInfo hiAppInfo) {
        if (hiAppInfo == null) {
            LogUtil.h("UIME_AppSettingActivity", "checkApkValid() hiAppInfo is null");
            return false;
        }
        String packageName = hiAppInfo.getPackageName();
        String signature = hiAppInfo.getSignature();
        if (TextUtils.isEmpty(packageName) || TextUtils.isEmpty(signature)) {
            LogUtil.h("UIME_AppSettingActivity", "checkApkValid() packageName or signature is null");
            return false;
        }
        String d2 = ivv.d(signature);
        if (d2 == null) {
            LogUtil.h("UIME_AppSettingActivity", "checkApkValid fingerprint is null");
            return false;
        }
        int b2 = ivv.b(signature);
        String e2 = HsfSignValidator.e(context, packageName);
        try {
            int i = context.getPackageManager().getApplicationInfo(packageName, 0).uid;
            if (d2.equals(e2) && b2 == i) {
                return true;
            }
            LogUtil.h("UIME_AppSettingActivity", "checkApkValid() return false");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("UIME_AppSettingActivity", "checkApkValid() NameNotFoundException");
            return false;
        }
    }

    private void i() {
        e eVar;
        HealthOpenSDK healthOpenSDK = this.av;
        if (healthOpenSDK == null || (eVar = this.y) == null) {
            return;
        }
        healthOpenSDK.a(eVar);
    }

    private void o() {
        HealthOpenSDK healthOpenSDK = this.av;
        if (healthOpenSDK == null) {
            return;
        }
        e eVar = this.q;
        if (eVar != null) {
            healthOpenSDK.d((IExecuteResult) eVar);
        }
        e eVar2 = this.p;
        if (eVar2 != null) {
            this.av.c(eVar2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void at() {
        int h = h();
        LogUtil.a("UIME_AppSettingActivity", "updateSettingMapType map type : ", Integer.valueOf(h));
        if (h == 1) {
            if (LanguageUtil.m(this.z) || LanguageUtil.p(this.z)) {
                this.bw.setText(R$string.IDS_hwh_motiontrack_map_type_setting_gaode);
                return;
            } else {
                this.bw.setText(R$string.IDS_hw_show_data_tarck_map_type_setting_gaode);
                return;
            }
        }
        if (h != 2) {
            if (h == 3) {
                this.bw.setText(R$string.IDS_hw_petal_maps);
                return;
            } else {
                this.bw.setText(R$string.IDS_aw_auto_mode);
                return;
            }
        }
        if (LanguageUtil.m(this.z) || LanguageUtil.p(this.z)) {
            this.bw.setText(R$string.IDS_hwh_motiontrack_map_type_setting_google);
        } else {
            this.bw.setText(R$string.IDS_hw_show_data_tarck_map_type_setting_google);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.a("UIME_AppSettingActivity", "Enter onDestroy()");
        if (this.ci != null) {
            LogUtil.a("UIME_AppSettingActivity", "unregisterReceiver mReceiver");
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.ci);
            this.bk = false;
        }
        super.onDestroy();
        if (this.y != null) {
            this.y = null;
        }
        if (this.p != null) {
            this.p = null;
        }
        if (this.q != null) {
            this.q = null;
        }
        a aVar = this.at;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
            this.at = null;
        }
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.g = null;
        }
        ThreadPoolManager.d().e("getAllCacheSize", null);
        FaCardAgdsApi c2 = dlf.c();
        if (c2 != null) {
            c2.close();
        }
    }

    public void dMS_(Intent intent) {
        synchronized (d) {
            int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
            LogUtil.a("UIME_AppSettingActivity", "updateSyncView status = ", Integer.valueOf(intExtra));
            if (intExtra == 2) {
                j();
            } else if (intExtra == 3) {
                ar();
            } else if (intExtra == 1) {
                double doubleExtra = intent.getDoubleExtra("com.huawei.hihealth.action_sync_process", 0.0d);
                LogUtil.a("UIME_AppSettingActivity", "updateSyncView process = ", Double.valueOf(doubleExtra));
                this.cf.setVisibility(0);
                this.cf.c(((int) doubleExtra) - this.cf.getProgress());
                this.cf.setClickable(false);
            } else if (intExtra == 0) {
                LogUtil.c("UIME_AppSettingActivity", "updateSyncView Sync BEGIN");
                this.cf.setProgress(0);
            } else {
                LogUtil.h("UIME_AppSettingActivity", "updateSyncView is other status");
            }
        }
    }

    private void ab() {
        this.cf.setClickable(true);
        this.cf.b();
        this.cf.d();
        this.cf.setProgressButtonBackgroundDrawable(getDrawable(R.drawable._2131428862_res_0x7f0b05fe));
        this.cf.setIdleText(getResources().getString(R$string.IDS_device_wear_home_confirm_sync_account).toUpperCase());
    }

    private void j() {
        LogUtil.c("UIME_AppSettingActivity", "finishDataSync sync done");
        if (this.ci != null) {
            LogUtil.c("UIME_AppSettingActivity", "finishDataSync unregisterReceiver mReceiver");
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.ci);
            this.bk = false;
        }
        ab();
        nrh.d(this.z, getResources().getString(R$string.IDS_hw_show_synchronous_success));
        Date date = new Date();
        long currentTimeMillis = System.currentTimeMillis();
        String format = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMdd")).format(date);
        String c2 = nsj.c(this.z, currentTimeMillis, 1);
        LogUtil.a("UIME_AppSettingActivity", "finishDataSync formatDate:", format, " formatTime:", c2);
        StringBuilder b2 = b(format, c2);
        SharedPreferenceManager.e(this.z, Integer.toString(10000), "last_sync_time", String.valueOf(currentTimeMillis), new StorageParams());
        this.bl.setVisibility(0);
        this.bl.setText(nsf.h(R$string.IDS_hw_last_sync_time) + ((Object) b2));
        jcf.bEz_(this.bn, nsf.b(R$string.accessibility_setting_manual_sync_time, b2));
    }

    private void ar() {
        LogUtil.c("UIME_AppSettingActivity", "syncDataFail to enter");
        if (this.ci != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.ci);
            this.bk = false;
        }
        ab();
        nrh.b(this.z, R$string.IDS_hw_show_me_sync_fail);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.z);
        builder.b(getResources().getString(R$string.IDS_hw_health_show_common_dialog_title)).e(getResources().getString(R$string.IDS_hw_sync_fail_try)).cyU_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_AppSettingActivity", "syncDataFail onClick click to positive view");
                AppSettingActivity.this.ad();
                AppSettingActivity.this.z();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_hw_common_ui_dialog_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_AppSettingActivity", "syncDataFail onClick click to negative view");
                AppSettingActivity.this.z();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final CustomTextAlertDialog a2 = builder.a();
        a2.show();
        a2.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.13
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                LogUtil.a("UIME_AppSettingActivity", "syncDataFail onCancel click dialog");
                AppSettingActivity.this.z();
                a2.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        Intent intent = new Intent();
        intent.setAction("sync_cloud_data_action");
        intent.putExtra("sync_cloud_data_status", "sync_cloud_data_setting_flag");
        BroadcastManagerUtil.bFI_(this.z, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        LogUtil.a("UIME_AppSettingActivity", "processManualSync() to enter");
        if (!this.bk) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.hihealth.action_sync");
            LocalBroadcastManager.getInstance(this).registerReceiver(this.ci, intentFilter);
            this.bk = true;
        }
        if (NetworkUtil.i()) {
            if (NetworkUtil.f()) {
                am();
                return;
            }
            this.cf.setClickable(false);
            this.b.c(this.av, this.cf, getResources().getString(R$string.IDS_hw_show_me_sync_begin));
            ap();
            return;
        }
        nrh.e(this.z, R$string.IDS_hw_show_set_about_privacy_connectting_error);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ap() {
        if (this.bj) {
            LocalBroadcastManager.getInstance(this.z).sendBroadcast(new Intent("com.huawei.sync_activity_to_third_platform"));
        }
    }

    private void am() {
        LogUtil.a("UIME_AppSettingActivity", "showManualSyncDialog() to enter");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.z);
        builder.b(getString(R$string.IDS_hw_health_show_common_dialog_title)).e(getString(R$string.IDS_hw_show_setting_synchronous_prompt)).cyU_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_AppSettingActivity", "showManualSyncDialog onClick positive");
                AppSettingActivity.this.cf.setClickable(false);
                AppSettingActivity.this.b.c(AppSettingActivity.this.av, AppSettingActivity.this.cf, AppSettingActivity.this.getResources().getString(R$string.IDS_hw_show_me_sync_begin));
                AppSettingActivity.this.ap();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_AppSettingActivity", "showManualSyncDialog onClick negative");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private void x() {
        dsl.q();
        LogUtil.a("UIME_AppSettingActivity", "loginOut enter");
        SharedPreferenceManager.e(this.z, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN, "0", new StorageParams(0));
        dss.a(false);
        LoginInit.getInstance(this.z).logout();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (isDestroyed() || isFinishing() || this.z == null) {
            LogUtil.h("UIME_AppSettingActivity", "onClick activity isDestroyed or isFinishing or mContext is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (nsn.a(750)) {
            LogUtil.a("UIME_AppSettingActivity", "onClick too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.bp) {
            x();
        } else if (view.getId() == R.id.hw_offline_map_layout) {
            d(hashMap);
        } else if (view == this.cf) {
            z();
            ixx.d().d(this.z, AnalyticsValue.HEALTH_MINE_SETTINGS_SYNC_2040014.value(), hashMap, 0);
            ad();
        } else if (view == this.cy) {
            this.ad.b(true);
            this.ad.d();
        } else if (view == this.af) {
            Intent intent = new Intent();
            intent.setClassName(getPackageName(), "com.huawei.health.developer.DeveloperActivity");
            startActivity(intent);
        } else if (view == this.bx) {
            String value = AnalyticsValue.HEALTH_MINE_SETTINGS_OPEN_NOTIFICATION_2040015.value();
            HashMap hashMap2 = new HashMap();
            hashMap2.put("click", "1");
            ixx.d().d(this.z, value, hashMap2, 0);
            this.be = false;
            this.bh = false;
            o();
        } else if (view == this.ax) {
            AppRouter.b("/todoTask/TodoSettingActivity").c(this);
            HashMap hashMap3 = new HashMap(3);
            hashMap3.put("from", 2);
            hashMap3.put("click", 1);
            ixx.d().d(this.z, AnalyticsValue.HEALTH_LIFE_TODO_SETTINGS_ENTER_2040174.value(), hashMap3, 0);
        } else {
            dMM_(view, hashMap);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void dMM_(View view, Map<String, Object> map) {
        if (view == this.cr) {
            startActivity(new Intent(this, (Class<?>) UnitSettingActivity.class));
            return;
        }
        if (view == this.cm) {
            startActivity(new Intent(this, (Class<?>) ServiceModelSettingActivity.class));
            return;
        }
        if (view == this.bt) {
            this.b.c(AnalyticsValue.HEALTH_MINE_SETTINGS_MAP_ENGINE_2040029.value(), null);
            aj();
            return;
        }
        if (view == this.da) {
            Intent intent = new Intent();
            intent.setClassName(this.z, "com.huawei.ui.homehealth.runcard.SportAssistSettingsActivity");
            intent.putExtra("currentFrag", 0);
            intent.putExtra("currentSportType", 0);
            intent.putExtra("enterSportSetting", true);
            startActivity(intent);
            return;
        }
        if (view == this.ak) {
            if (this.z != null) {
                AppRouter.b("/HWUserProfileMgr/MyTargetActivity").c("from", 6).c(this.z);
                pxp.d(6, 1);
            }
            ixx.d().d(this.z, AnalyticsValue.HEALTH_MINE_TARGET_2040004.value(), map, 0);
            return;
        }
        if (view == this.dg) {
            a(map);
            return;
        }
        if (view == this.cq) {
            Intent intent2 = new Intent();
            intent2.setClassName(getPackageName(), "com.huawei.health.developer.DeviceTestActivity");
            startActivity(intent2);
        } else {
            if (view == this.cj) {
                SharedPreferenceManager.e(Integer.toString(10000), "service_express_card_red_point", false);
                FaCardAgdsApi c2 = dlf.c();
                if (c2 == null || this.by) {
                    return;
                }
                c2.faQuickServicesBiEvent(0, 1);
                this.by = true;
                c2.open(100001, "default", new c(this));
                return;
            }
            dMP_(view);
        }
    }

    static class c implements FaCardAgdsApi.OpenAgdsResultCallback {
        private final WeakReference<AppSettingActivity> e;

        c(AppSettingActivity appSettingActivity) {
            this.e = new WeakReference<>(appSettingActivity);
        }

        @Override // com.huawei.health.facardagds.FaCardAgdsApi.OpenAgdsResultCallback
        public void onResponse(int i) {
            ReleaseLogUtil.b("UIME_AppSettingActivity", "openFaCardAgds errorCode:", Integer.valueOf(i));
            AppSettingActivity appSettingActivity = this.e.get();
            if (appSettingActivity == null || appSettingActivity.isFinishing()) {
                LogUtil.h("UIME_AppSettingActivity", "OpenAgdsResultCallback onResponse activity is finish");
            } else if (i != 0) {
                appSettingActivity.by = false;
            }
        }
    }

    private void a(Map<String, Object> map) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.bq > 1000) {
            ixx.d().d(this.z, AnalyticsValue.HEALTH_MINE_SETTINGS_WECHAT_LEADERBOARD_2040038.value(), map, 0);
            this.db.e(this.z);
            this.bq = currentTimeMillis;
        }
    }

    private void d(Map<String, Object> map) {
        if (h() == 2) {
            e(String.format(getResources().getString(com.huawei.ui.commonui.R$string.IDS_hw_offline_map_content), getResources().getString(com.huawei.ui.commonui.R$string.IDS_hw_show_data_tarck_map_type_setting_google)));
        } else if (h() == 3) {
            e(getResources().getString(com.huawei.ui.commonui.R$string.IDS_hw_offline_map_content, getResources().getString(com.huawei.ui.commonui.R$string.IDS_hw_petal_maps)));
        } else {
            b(map);
        }
    }

    private void b(Map<String, Object> map) {
        ixx.d().d(this.z, AnalyticsValue.HEALTH_MINE_SETTINGS_OFFINE_MAP_2040016.value(), map, 0);
        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        if (PermissionUtil.c() || jdi.c(this.z, strArr) || !w()) {
            startActivity(new Intent(this, (Class<?>) OfflineMapTabActivity.class));
        }
    }

    private void e(String str) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.z);
        builder.b(getResources().getString(R$string.IDS_hw_offline_map)).e(str).cyU_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_AppSettingActivity", "chooseMapDialog is positive view");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private boolean w() {
        boolean z = CommonUtil.a(this.z, "android.permission.WRITE_EXTERNAL_STORAGE") || shouldShowRequestPermissionRationale("android.permission.WRITE_EXTERNAL_STORAGE");
        boolean z2 = shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION") || CommonUtil.a(this.z, "android.permission.ACCESS_COARSE_LOCATION");
        if (z || z2) {
            return false;
        }
        nsn.a((Context) this, false);
        return true;
    }

    private void g() {
        ThreadPoolManager.d().d("getAllCacheSize", new Runnable() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.19
            @Override // java.lang.Runnable
            public void run() {
                long b2 = scl.b(scl.j(AppSettingActivity.this.z));
                long a2 = sdj.e().a();
                long e2 = scl.e();
                long b3 = scl.b(AppSettingActivity.this.z);
                long e3 = scl.e(AppSettingActivity.this.z);
                long c2 = scl.c(AppSettingActivity.this.z);
                long d2 = scl.d(AppSettingActivity.this.z, "sleepMonitor");
                String b4 = nsn.b(AppSettingActivity.this.z, b2 + a2 + e2 + b3 + e3 + c2 + d2 + scl.a(AppSettingActivity.this.z));
                SharedPreferenceManager.e(AppSettingActivity.this.z, Integer.toString(10000), "key_settings_cache_size", b4, (StorageParams) null);
                Message obtain = Message.obtain();
                obtain.what = 106;
                obtain.obj = b4;
                if (AppSettingActivity.this.g != null) {
                    AppSettingActivity.this.g.sendMessage(obtain);
                }
            }
        });
    }

    private void dMP_(View view) {
        if (view == this.aj) {
            ak();
            return;
        }
        if (view == this.dd) {
            this.b.c(AnalyticsValue.HEALTH_MINE_SETTINGS_OPEN_SOURCE_SOFTWARE_NOTICE_2040068.value(), null);
            startActivity(new Intent(this, (Class<?>) OpensourceNoticeActivity.class));
            return;
        }
        if (view == this.cc) {
            startActivity(new Intent(this, (Class<?>) PluginManagerActivity.class));
            return;
        }
        if (view == this.ce) {
            startActivity(new Intent(this, (Class<?>) PluginSettingActivity.class));
            return;
        }
        if (view == this.ag) {
            al();
        } else {
            if (view == this.f) {
                Intent intent = new Intent(this, (Class<?>) AutoIdentifyRecordActivity.class);
                intent.putExtra("auto_track_launch_source", 1);
                startActivity(intent);
                return;
            }
            LogUtil.h("UIME_AppSettingActivity", "startOtherView to other view");
        }
    }

    private void ak() {
        if (this.z == null) {
            LogUtil.h("UIME_AppSettingActivity", "startDeviceShare mContext is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), "com.huawei.ui.thirdpartservice.activity.DeviceShareActivity");
        intent.putParcelableArrayListExtra(KnitFragment.KEY_EXTRA, this.f10322a);
        this.z.startActivity(intent);
    }

    private void al() {
        if (this.z == null) {
            LogUtil.h("UIME_AppSettingActivity", "startDeviceCapacityOpen mContext is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), "com.huawei.ui.main.stories.devicecapacity.DeviceCapacityThirdPartyListActivity");
        intent.setPackage(getPackageName());
        this.z.startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 10) {
            if (iArr == null || iArr.length == 0) {
                LogUtil.h("UIME_AppSettingActivity", "onRequestPermissionsResult grantResults illegal");
                return;
            }
            LogUtil.a("UIME_AppSettingActivity", "onRequestPermissionsResult back");
            if (iArr[0] == 0) {
                LogUtil.c("UIME_AppSettingActivity", "onRequestPermissionsResult get permission succeed");
                startActivity(new Intent(this, (Class<?>) ClearDataCacheActivity.class));
            } else {
                LogUtil.a("UIME_AppSettingActivity", "onRequestPermissionsResult get permission rejected");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOpenRealTimeStep", this.bm);
        bundle.putBoolean("isOpenCompleteGoal", this.bd);
        bundle.putBoolean("isOpenNotification", this.bi);
        Intent intent = new Intent(this, (Class<?>) NotificationMessageSettingActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.show_voice_switch) {
            String num = Integer.toString(20002);
            StorageParams storageParams = new StorageParams(0);
            if (z) {
                SharedPreferenceManager.e(this, num, "voice_enable_type", "1", storageParams);
            } else {
                SharedPreferenceManager.e(this, num, "voice_enable_type", "0", storageParams);
            }
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        if (compoundButton == this.aq) {
            LogUtil.c("UIME_AppSettingActivity", "mGPSLowConsumptionSwitchStatus = ", Boolean.valueOf(this.bf));
            StorageParams storageParams2 = new StorageParams();
            if (this.bg) {
                if (z && !this.bf) {
                    LogUtil.a("UIME_AppSettingActivity", "mGPSLowConsumptionSwitch isChecked = ", Boolean.valueOf(z));
                    e(storageParams2);
                } else if (!z && this.bf) {
                    a(storageParams2);
                    LogUtil.c("UIME_AppSettingActivity", "mGPSLowConsumptionSwitch isChecked = ", Boolean.valueOf(z));
                }
            }
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        if (compoundButton == this.m) {
            this.au.c(!z);
            a(z);
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        if (compoundButton == this.cu) {
            if (z) {
                this.cs.setIsShowUserInfo(true);
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            this.cs.showUserinfoDisableDialog(this.z, R$string.IDS_share_info_dialog_title, R$string.IDS_share_close_info_tips, new View.OnClickListener() { // from class: rel
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AppSettingActivity.this.dMQ_(view);
                }
            }, new View.OnClickListener() { // from class: rek
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AppSettingActivity.this.dMR_(view);
                }
            });
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ void dMQ_(View view) {
        this.cs.setIsShowUserInfo(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dMR_(View view) {
        this.cu.setChecked(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(final StorageParams storageParams) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.z).e(R$string.IDS_hw_show_setting_is_allow_gps_low_consumption).czz_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppSettingActivity.this.aq.setChecked(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.2
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                AppSettingActivity appSettingActivity = AppSettingActivity.this;
                appSettingActivity.bf = appSettingActivity.aq.isChecked();
                if (AppSettingActivity.this.bf) {
                    SharedPreferenceManager.e(AppSettingActivity.this.z, "flp_track_switch", "flp_track_flag", "1", storageParams);
                    LogUtil.a("UIME_AppSettingActivity", "set flp value = 1");
                } else {
                    SharedPreferenceManager.e(AppSettingActivity.this.z, "flp_track_switch", "flp_track_flag", "0", storageParams);
                    LogUtil.a("UIME_AppSettingActivity", "set flp value = 0");
                }
            }
        });
        e2.setCancelable(false);
        e2.show();
    }

    private void a(final StorageParams storageParams) {
        NoTitleCustomAlertDialog e2 = new NoTitleCustomAlertDialog.Builder(this.z).e(R$string.IDS_hw_show_setting_is_disagree_gps_low_consumption).czz_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppSettingActivity.this.aq.setChecked(true);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.5
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                AppSettingActivity appSettingActivity = AppSettingActivity.this;
                appSettingActivity.bf = appSettingActivity.aq.isChecked();
                if (AppSettingActivity.this.bf) {
                    LogUtil.a("UIME_AppSettingActivity", "mGPSLowConsumptionSwitchStatus if");
                    SharedPreferenceManager.e(AppSettingActivity.this.z, "flp_track_switch", "flp_track_flag", "1", storageParams);
                    LogUtil.a("UIME_AppSettingActivity", "set flp value = 1");
                } else {
                    LogUtil.a("UIME_AppSettingActivity", "mGPSLowConsumptionSwitchStatus else");
                    SharedPreferenceManager.e(AppSettingActivity.this.z, "flp_track_switch", "flp_track_flag", "0", storageParams);
                    LogUtil.a("UIME_AppSettingActivity", "set flp value = 0");
                }
            }
        });
        e2.setCancelable(false);
        e2.show();
    }

    private void a(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (z) {
            hashMap.put("status", "1");
        } else {
            hashMap.put("status", "0");
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_MINE_AUTO_SCAN_DEVICE_2040069.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        k();
        if (!this.bk && Utils.i()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.hihealth.action_sync");
            LocalBroadcastManager.getInstance(this).registerReceiver(this.ci, intentFilter);
            this.bk = true;
        }
        this.by = false;
        this.bq = 0L;
        if (a()) {
            this.af.setVisibility(0);
            this.w.setVisibility(0);
        } else {
            this.af.setVisibility(8);
            this.w.setVisibility(8);
        }
        if (as() && pqm.c()) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        i();
        g();
        this.at.sendEmptyMessageDelayed(206, 250L);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        LogUtil.a("UIME_AppSettingActivity", "onRestart");
        this.db.dUY_(this.g);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.ad.c(i, i2);
        ReleaseLogUtil.b("UIME_AppSettingActivity", "onActivityResult requestCode: ", Integer.valueOf(i), ", resultCode: ", Integer.valueOf(i2));
        FaCardAgdsApi c2 = dlf.c();
        if (c2 == null) {
            ReleaseLogUtil.b("UIME_AppSettingActivity", "onActivityResult openFaCardAgds faCardAgdsApi null:");
        }
        if (c2 != null && i == 100001) {
            int loadResultCode = c2.getLoadResultCode(intent);
            ReleaseLogUtil.b("UIME_AppSettingActivity", "onActivityResult openFaCardAgds loadResultcode:", Integer.valueOf(loadResultCode));
            c2.addToDeskTopBi(intent, 1);
            if (loadResultCode == 3) {
                nrh.b(BaseApplication.getContext(), R$string.wallet_unenable_country);
            }
            c2.close();
        }
        if (c2 == null || i != 100) {
            return;
        }
        if (i2 == c2.getResultcodeAgreeProtocol()) {
            c2.open(100001, "default", new FaCardAgdsApi.OpenAgdsResultCallback() { // from class: rem
                @Override // com.huawei.health.facardagds.FaCardAgdsApi.OpenAgdsResultCallback
                public final void onResponse(int i3) {
                    ReleaseLogUtil.b("UIME_AppSettingActivity", "onActivityResult openFaCardAgds errorCode:", Integer.valueOf(i3));
                }
            });
        } else if (i2 == c2.getResultcodeNotAgreeProtocol()) {
            ReleaseLogUtil.b("UIME_AppSettingActivity", "onActivityResult openFaCardAgds NotAgreeProtocol");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    private boolean a() {
        if (LogUtil.c()) {
            return false;
        }
        String b2 = SharedPreferenceManager.b(this.z, "developeroptions", "developerswitch");
        LogUtil.a("UIME_AppSettingActivity", "enableShowDevelopOption developerKey:", b2);
        return b2 != null && b2.equals("1");
    }

    private boolean as() {
        return SharedPreferenceManager.a(Integer.toString(10000), "service_express_card_red_point", true);
    }

    private void aj() {
        LogUtil.a("UIME_AppSettingActivity", "showSettingMapTypeDialog()");
        Context context = this.z;
        if (context != null) {
            View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.hw_show_map_type_view, (ViewGroup) null);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.z);
            if (inflate == null) {
                LogUtil.h("UIME_AppSettingActivity", "showSettingMapTypeDialog() dialog layout fail");
                return;
            }
            builder.a(getString(R$string.IDS_hw_show_map_type_title)).czh_(inflate, 0, 0).cze_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIME_AppSettingActivity", "positive settingMapType : ", Integer.valueOf(AppSettingActivity.this.cn));
                    AppSettingActivity appSettingActivity = AppSettingActivity.this;
                    appSettingActivity.d(appSettingActivity.cn);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            CustomViewDialog e2 = builder.e();
            NoTitleCustomAlertDialog.Builder builder2 = new NoTitleCustomAlertDialog.Builder(this.z);
            builder2.e(getString(R$string.IDS_hwh_motiontrack_show_map_type_no_gms)).czC_(R$string.IDS_common_notification_know_tips, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e3 = builder2.e();
            NoTitleCustomAlertDialog.Builder builder3 = new NoTitleCustomAlertDialog.Builder(this.z);
            builder3.e(R.string._2130841236_res_0x7f020e94).czC_(R.string._2130841237_res_0x7f020e95, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            dMN_(inflate, e2, e3, builder3.e());
            e2.show();
            return;
        }
        LogUtil.c("UIME_AppSettingActivity", "showSettingMapTypeDialog() dialog layout fail");
    }

    private void dMN_(View view, CustomViewDialog customViewDialog, NoTitleCustomAlertDialog noTitleCustomAlertDialog, NoTitleCustomAlertDialog noTitleCustomAlertDialog2) {
        this.an = (RelativeLayout) view.findViewById(R.id.settings_gaode_map_layout);
        this.ap = (RelativeLayout) view.findViewById(R.id.settings_google_map_layout);
        this.az = (RelativeLayout) view.findViewById(R.id.settings_hms_map_layout);
        this.n = (RelativeLayout) view.findViewById(R.id.settings_auto_map_layout);
        this.al = (HealthRadioButton) view.findViewById(R.id.settings_gaode_map_radioButton);
        this.ao = (HealthRadioButton) view.findViewById(R.id.settings_google_map_radioButton);
        this.ay = (HealthRadioButton) view.findViewById(R.id.settings_hms_map_radioButton);
        this.o = (HealthRadioButton) view.findViewById(R.id.settings_auto_map_radioButton);
        HealthDivider healthDivider = (HealthDivider) view.findViewById(R.id.user_info_fragment_image1);
        HealthDivider healthDivider2 = (HealthDivider) view.findViewById(R.id.user_info_fragment_image2);
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.settings_gaode_map_text);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.settings_google_map_text);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.settings_huawei_map_text);
        healthTextView.setText(R$string.IDS_hwh_motiontrack_map_type_setting_gaode);
        healthTextView2.setText(R$string.IDS_hwh_motiontrack_map_type_setting_google);
        healthTextView3.setText(R$string.IDS_hw_petal_maps);
        if (!gwe.d(this.z)) {
            healthDivider2.setVisibility(8);
            this.ay.setVisibility(8);
            this.az.setVisibility(8);
        }
        if (Utils.o()) {
            this.al.setVisibility(8);
            this.an.setVisibility(8);
            healthDivider.setVisibility(8);
        }
        if (!CommonUtil.bh()) {
            healthDivider2.setVisibility(8);
            this.ay.setVisibility(8);
            view.findViewById(R.id.settings_hms_map_layout).setVisibility(8);
        } else if (!gwg.h()) {
            healthDivider.setVisibility(8);
            this.ao.setVisibility(8);
            this.ap.setVisibility(8);
        }
        d(customViewDialog, noTitleCustomAlertDialog, noTitleCustomAlertDialog2);
        this.s = this.cn;
    }

    private void d(final CustomViewDialog customViewDialog, final NoTitleCustomAlertDialog noTitleCustomAlertDialog, final NoTitleCustomAlertDialog noTitleCustomAlertDialog2) {
        this.al.setClickable(false);
        this.ao.setClickable(false);
        this.ay.setClickable(false);
        this.o.setClickable(false);
        this.an.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppSettingActivity.this.e(1);
                customViewDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ap.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (gwg.c(AppSettingActivity.this.z)) {
                    AppSettingActivity.this.e(2);
                    customViewDialog.dismiss();
                } else {
                    noTitleCustomAlertDialog.show();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.az.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!gwe.b(AppSettingActivity.this.z) && !CommonUtil.b(AppSettingActivity.this.z)) {
                    noTitleCustomAlertDialog2.show();
                }
                AppSettingActivity.this.e(3);
                customViewDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppSettingActivity.this.e(0);
                customViewDialog.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        aa();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        this.al.setClickable(false);
        this.ao.setClickable(false);
        this.ay.setClickable(false);
        this.o.setClickable(false);
        this.cn = i;
        d(i);
        if (i == 0) {
            this.al.setClickable(false);
            this.ao.setClickable(true);
            this.ay.setClickable(false);
            this.o.setClickable(true);
            return;
        }
        if (i == 1) {
            this.al.setClickable(true);
            this.ao.setClickable(false);
            this.ay.setClickable(false);
            this.o.setClickable(false);
            return;
        }
        if (i == 2) {
            this.al.setClickable(false);
            this.ao.setClickable(true);
            this.ay.setClickable(false);
            this.o.setClickable(false);
            return;
        }
        if (i == 3) {
            this.al.setClickable(false);
            this.ao.setClickable(true);
            this.ay.setClickable(true);
            this.o.setClickable(false);
            return;
        }
        LogUtil.h("UIME_AppSettingActivity", "settingMapType is not support Type ");
    }

    private void aa() {
        if (h() == 2) {
            this.cn = 2;
            this.al.setChecked(false);
            this.ao.setChecked(true);
            this.ay.setChecked(false);
            this.o.setChecked(false);
            return;
        }
        if (h() == 1) {
            this.cn = 1;
            this.al.setChecked(true);
            this.ao.setChecked(false);
            this.ay.setChecked(false);
            this.o.setChecked(false);
            return;
        }
        if (h() == 3) {
            this.cn = 3;
            this.al.setChecked(false);
            this.ao.setChecked(false);
            this.ay.setChecked(true);
            this.o.setChecked(false);
            return;
        }
        this.cn = 0;
        this.al.setChecked(false);
        this.ao.setChecked(false);
        this.ay.setChecked(false);
        this.o.setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        StorageParams storageParams = new StorageParams();
        LogUtil.a("UIME_AppSettingActivity", "saveMapType type = ", Integer.valueOf(i));
        SharedPreferenceManager.e(this.z, Integer.toString(20002), "map_type_setting_key", Integer.toString(i), storageParams);
        int i2 = this.cn;
        if (i2 == 1) {
            this.b.c(AnalyticsValue.HEALTH_MINE_SETTINGS_SELECT_MAP_2040030.value(), "1");
        } else if (i2 == 2) {
            this.b.c(AnalyticsValue.HEALTH_MINE_SETTINGS_SELECT_MAP_2040030.value(), "2");
        } else if (i2 == 3) {
            this.b.c(AnalyticsValue.HEALTH_MINE_SETTINGS_SELECT_MAP_2040030.value(), "3");
        } else {
            this.b.c(AnalyticsValue.HEALTH_MINE_SETTINGS_SELECT_MAP_2040030.value(), "0");
        }
        this.g.sendEmptyMessage(104);
        if (this.s != this.cn) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("result", Integer.valueOf(this.cn));
            ixx.d().d(this.z, AnalyticsValue.MOTION_TRACK_1040024.value(), hashMap, 0);
        }
    }

    private int h() {
        String b2 = SharedPreferenceManager.b(this.z, Integer.toString(20002), "map_type_setting_key");
        if (!TextUtils.isEmpty(b2)) {
            try {
                return Integer.parseInt(b2);
            } catch (NumberFormatException e2) {
                LogUtil.c("UIME_AppSettingActivity", "getMapType NumberFormatException ", LogAnonymous.b((Throwable) e2));
                return 0;
            }
        }
        d(0);
        return 0;
    }

    static class g implements HiDataOperateListener {
        private WeakReference<AppSettingActivity> c;

        g(AppSettingActivity appSettingActivity) {
            this.c = new WeakReference<>(appSettingActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
        public void onResult(int i, Object obj) {
            LogUtil.a("UIME_AppSettingActivity", "onResult invoke");
            AppSettingActivity appSettingActivity = this.c.get();
            if (appSettingActivity == null) {
                LogUtil.h("UIME_AppSettingActivity", "onResult appSettingActivity is null");
                return;
            }
            if (!(obj instanceof List)) {
                LogUtil.a("UIME_AppSettingActivity", "checkWearKit onResult data not list");
                return;
            }
            List list = (List) obj;
            if (koq.b(list)) {
                LogUtil.h("UIME_AppSettingActivity", "checkWearKit onResult tempAppInfoList empty");
                return;
            }
            for (Object obj2 : list) {
                if (obj2 instanceof HiAppInfo) {
                    HiAppInfo hiAppInfo = (HiAppInfo) obj2;
                    if (appSettingActivity.e(BaseApplication.getContext(), hiAppInfo.getPackageName()) && appSettingActivity.e(BaseApplication.getContext(), hiAppInfo)) {
                        appSettingActivity.f10322a.add(hiAppInfo);
                    }
                }
            }
            if (koq.b(appSettingActivity.f10322a)) {
                return;
            }
            LogUtil.a("UIME_AppSettingActivity", "checkWearKit onResult mAppInfoList size = ", Integer.valueOf(appSettingActivity.f10322a.size()));
            Message obtainMessage = appSettingActivity.at.obtainMessage();
            obtainMessage.what = 105;
            appSettingActivity.at.sendMessage(obtainMessage);
        }
    }

    static class a extends BaseHandler<AppSettingActivity> {
        a(AppSettingActivity appSettingActivity) {
            super(appSettingActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dMV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AppSettingActivity appSettingActivity, Message message) {
            int i = message.what;
            if (i == 101) {
                appSettingActivity.dMS_((Intent) message.obj);
                return;
            }
            if (i != 105) {
                if (i != 206) {
                    return;
                }
                appSettingActivity.ao();
            } else {
                if (EnvironmentInfo.k()) {
                    return;
                }
                appSettingActivity.aj.setVisibility(0);
                appSettingActivity.ai.setVisibility(0);
            }
        }
    }

    static class b extends BaseHandler<AppSettingActivity> {
        b(AppSettingActivity appSettingActivity) {
            super(appSettingActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dMU_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AppSettingActivity appSettingActivity, Message message) {
            int i = message.what;
            if (i == 103) {
                if (!Utils.g() || appSettingActivity.bi || appSettingActivity.bb) {
                    appSettingActivity.bx.setVisibility(0);
                    return;
                } else {
                    appSettingActivity.bx.setVisibility(8);
                    return;
                }
            }
            if (i == 104) {
                appSettingActivity.at();
                return;
            }
            if (i == 106) {
                if (message.obj instanceof String) {
                    appSettingActivity.ck.setText((String) message.obj);
                    appSettingActivity.t.setText((String) message.obj);
                    return;
                }
                return;
            }
            if (i != 205) {
                switch (i) {
                    case 201:
                        dMT_(appSettingActivity, message);
                        break;
                    case 202:
                        appSettingActivity.db.c();
                        Toast.makeText(appSettingActivity.z, appSettingActivity.z.getResources().getText(R$string.IDS_confirm_network_whether_connected), 0).show();
                        break;
                    case 203:
                        appSettingActivity.db.c();
                        Toast.makeText(appSettingActivity.z, appSettingActivity.z.getResources().getText(R$string.IDS_update_server_bussy), 0).show();
                        break;
                }
                return;
            }
            appSettingActivity.db.b(appSettingActivity.z, message.obj.toString());
        }

        private void dMT_(final AppSettingActivity appSettingActivity, Message message) {
            String unused = AppSettingActivity.e = LoginInit.getInstance(appSettingActivity.z).getAccountInfo(1011);
            if (AppSettingActivity.e == null || "".equals(AppSettingActivity.e)) {
                LogUtil.h("UIME_AppSettingActivity", "getQrCodeTicketId get unvalid userId");
                appSettingActivity.db.c();
            } else {
                final String obj = message.obj.toString();
                LogUtil.c("UIME_AppSettingActivity", "getQrCodeTicketId userId is ", AppSettingActivity.e);
                kor.a().c(new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.b.4
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj2) {
                        int i2 = 0;
                        if (i == 0 && (obj2 instanceof List)) {
                            List list = (List) obj2;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= list.size()) {
                                    break;
                                }
                                FitnessTotalData fitnessTotalData = (FitnessTotalData) list.get(i3);
                                if (fitnessTotalData.getSportType() == 221) {
                                    i2 = fitnessTotalData.getSteps();
                                    break;
                                }
                                i3++;
                            }
                        }
                        LogUtil.c("UIME_AppSettingActivity", "getQrCodeTicketId onResponse totalSteps = ", Integer.valueOf(i2));
                        String str = obj + "#" + AppSettingActivity.e + "#" + i2;
                        LogUtil.c("UIME_AppSettingActivity", "getQrCodeTicketId onResponse codeTicket = ", str);
                        appSettingActivity.db.a(str);
                        appSettingActivity.db.c();
                    }
                });
            }
        }
    }

    class e implements IExecuteResult {
        String c;

        e(String str) {
            this.c = str;
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onSuccess(Object obj) {
            LogUtil.a("UIME_AppSettingActivity", "onSuccess initSDK ", this.c);
            if (obj instanceof Bundle) {
                Bundle bundle = (Bundle) obj;
                String str = this.c;
                if (str != null && str.equals("getNotificationSupport") && !AppSettingActivity.this.ba) {
                    AppSettingActivity.this.bi = bundle.getBoolean("notificationSupport");
                    LogUtil.a("UIME_AppSettingActivity", "onSuccess isSupportNotification :", Boolean.valueOf(AppSettingActivity.this.bi));
                    AppSettingActivity.this.g.sendEmptyMessage(103);
                }
                String str2 = this.c;
                if (str2 != null && str2.equals("getStepsNotifiState")) {
                    AppSettingActivity.this.bm = bundle.getBoolean("stepsNotifiState");
                    LogUtil.a("UIME_AppSettingActivity", "onSuccess isOpenRealTimeStep :", Boolean.valueOf(AppSettingActivity.this.bm));
                    AppSettingActivity.this.bh = true;
                }
                String str3 = this.c;
                if (str3 != null && str3.equals("getGoalNotifiState")) {
                    AppSettingActivity.this.bd = bundle.getBoolean("goalNotifiState");
                    LogUtil.a("UIME_AppSettingActivity", "onSuccess isOpenCompleteGoad :", Boolean.valueOf(AppSettingActivity.this.bd));
                    AppSettingActivity.this.be = true;
                }
                if (AppSettingActivity.this.bh && AppSettingActivity.this.be) {
                    LogUtil.a("UIME_AppSettingActivity", "onSuccess enterSetNotification");
                    AppSettingActivity.this.e();
                    AppSettingActivity.this.be = false;
                    AppSettingActivity.this.bh = false;
                }
            }
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onFailed(Object obj) {
            LogUtil.a("UIME_AppSettingActivity", "OpenSdkCommonCallback onFailed ", this.c);
        }

        @Override // com.huawei.hihealth.motion.IExecuteResult
        public void onServiceException(Object obj) {
            LogUtil.c("UIME_AppSettingActivity", "OpenSdkCommonCallback onServiceException ", this.c);
        }
    }

    class d extends BroadcastReceiver {
        d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.c("UIME_AppSettingActivity", "onReceive() to enter");
            if (intent == null) {
                LogUtil.h("UIME_AppSettingActivity", "onReceive intent is null");
            } else if ("com.huawei.hihealth.action_sync".equals(intent.getAction())) {
                AppSettingActivity.this.at.sendMessage(AppSettingActivity.this.at.obtainMessage(101, 0, 0, intent));
            }
        }
    }

    private void dMO_(View view) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.me.activity.AppSettingActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (nsn.cLk_(view2)) {
                    LogUtil.a("UIME_AppSettingActivity", "onClick too fast");
                    ViewClickInstrumentation.clickOnView(view2);
                } else {
                    AppSettingActivity.this.startActivity(new Intent(AppSettingActivity.this, (Class<?>) ClearDataCacheActivity.class));
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
