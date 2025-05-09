package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.huawei.agconnect.apms.Agent;
import com.huawei.haf.common.security.SecurityUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcrowdtestapi.HealthFeedbackParams;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.HelpCustomerOperate;
import com.huawei.operation.utils.OperationUtils;
import com.huawei.operation.utils.UriConstants;
import com.huawei.threecircle.ActiveTipStringUtils;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.exhibitioninfo.activity.ExhibitionInfoActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.HealthDatasActivity;
import com.huawei.ui.main.stories.ihealthlabs.IHealthLabsAcitivity;
import com.huawei.ui.main.stories.me.activity.AppSettingActivity;
import com.huawei.ui.main.stories.me.activity.HealthAboutActivity;
import com.huawei.ui.main.stories.me.activity.PrivacyCenterActivity;
import com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment;
import com.huawei.ui.main.stories.userprofile.activity.PersonalCenterRecyclerViewAdapter;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import com.huawei.up.utils.Constants;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.StorageResult;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.slf4j.Marker;

/* loaded from: classes7.dex */
public class sbd {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f16996a = {"com.tencent.qqpimsecure", "com.qihoo360.mobilesafe", "com.mpoyit.zawcgm", "com.zxly.assist", "com.anguanjia.safe", "com.fractalist.SystemOptimizer", "com.tencent.qlauncher.lite", "com.baoruan.launcher2", "com.hola.launcher", "com.dianxinos.dxhome", "com.nd.android.pandahome2", "com.mili.launcher", "com.tencent.launcher", "com.Dean.launcher", "com.gau.go.launcherex", "com.cleanmaster.mguard_cn", "com.isyezon.kbatterydoctor", "com.mdhlkj.batterysaver", "com.ijinshan.kbatterydoctor"};
    private rzs aa;
    private rzs ab;
    private rzs ac;
    private rzs ad;
    private rzs ae;
    private PersonalCenterRecyclerViewAdapter af;
    private rzs ag;
    private rzs ah;
    private PersonalCenterUiApi ai;
    private rzs ak;
    private rzs al;
    private String am;
    private rzs an;
    private rzs b;
    private Activity c;
    private rzs d;
    private rzs f;
    private rzs i;
    private Context k;
    private rzs l;
    private Handler m;
    private rzs n;
    private rzs o;
    private boolean p;
    private boolean q;
    private boolean r;
    private rzs s;
    private boolean t;
    private boolean u;
    private rzs v;
    private rzs w;
    private boolean x;
    private rzs y;
    private List<rzs> z;
    private List<rzs> g = new ArrayList(10);
    private String aj = "";
    private String e = "";
    private int j = 0;
    private BroadcastReceiver h = new BroadcastReceiver() { // from class: sbd.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                LogUtil.a("PersonalCenterListManager", "onReceive: action = ", action);
                if ("action_app_check_new_version_state".equals(action)) {
                    sbd.this.dUT_(intent);
                }
            }
        }
    };
    private VersionMgrApi aq = (VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class);

    public sbd(PersonalCenterFragment personalCenterFragment, Handler handler) {
        this.k = personalCenterFragment.getContext();
        this.c = personalCenterFragment.getActivity();
        this.ai = personalCenterFragment;
        this.m = handler;
    }

    public void e(PersonalCenterRecyclerViewAdapter personalCenterRecyclerViewAdapter) {
        this.af = personalCenterRecyclerViewAdapter;
    }

    public rzs h() {
        return this.ah;
    }

    public rzs c() {
        return this.i;
    }

    public rzs i() {
        return this.aa;
    }

    public rzs m() {
        return this.ak;
    }

    public rzs g() {
        return this.ag;
    }

    public rzs f() {
        return this.ae;
    }

    public rzs b() {
        return this.w;
    }

    public rzs e() {
        return this.v;
    }

    public rzs n() {
        return this.an;
    }

    public rzs d() {
        return this.s;
    }

    public rzs l() {
        return this.al;
    }

    public rzs a() {
        return this.o;
    }

    public rzs j() {
        return this.ac;
    }

    private void as() {
        List<rzs> list = this.z;
        if (list == null) {
            this.z = new ArrayList(16);
        } else {
            list.clear();
        }
        this.p = Utils.o();
        this.r = Utils.i();
        this.t = LoginInit.getInstance(this.k).isBrowseMode();
        this.q = LoginInit.getInstance(this.k).isKidAccount();
        this.x = CommonUtil.bu();
    }

    public List<rzs> o() {
        LogUtil.a("PersonalCenterListManager", "initOverseaRecyclerList");
        as();
        this.z.add(d(0));
        if (this.u && !LoginInit.getInstance(this.k).isBrowseMode()) {
            this.z.add(a(true));
        }
        this.z.add(i(true));
        this.z.add(g(true));
        this.z.add(h(true));
        this.z.add(ad());
        this.z.add(d(false));
        return this.z;
    }

    public List<rzs> k() {
        LogUtil.a("PersonalCenterListManager", "initRecyclerList");
        as();
        this.g.clear();
        if (this.r) {
            this.z.add(al());
            if (SportSupportUtil.j()) {
                this.z.add(an());
            }
        }
        if (!this.q && this.r) {
            this.z.add(aa());
            this.z.add(ai());
        }
        if (this.r) {
            this.z.add(d(R$string.IDS_user_divider_data));
            this.z.add(ac());
            this.z.add(ah());
            if (!this.q) {
                this.z.add(ae());
            }
            if (!this.x && !this.q && (!this.p || OperationUtils.isOperation(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010)))) {
                this.z.add(af());
            }
        }
        this.z.add(aj());
        if (CommonUtil.bu() && !EnvironmentInfo.k() && !this.t && !this.p && CommonUtil.bd() && LanguageUtil.m(BaseApplication.getContext())) {
            this.z.add(ag());
        }
        this.z.add(i(false));
        this.z.add(d(R$string.IDS_hw_messagecenter_other));
        ak();
        return this.z;
    }

    private void ak() {
        if (!this.q && !this.x && ((!Utils.l() || !CommonUtil.z(this.k)) && gpn.d())) {
            this.z.add(e(true));
        }
        if (ao()) {
            this.z.add(c(false));
        } else {
            List<rzs> list = this.z;
            rzs rzsVar = list.get(list.size() - 1);
            if (rzsVar.f() == 4) {
                rzsVar.a(false);
            }
        }
        if (this.u && !LoginInit.getInstance(this.k).isBrowseMode()) {
            this.z.add(d(0));
            this.z.add(a(false));
        }
        this.z.add(d(0));
        this.z.add(g(true));
        this.z.add(h(true));
        if (!this.x && this.r) {
            if (CommonUtil.m(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)) != 1 || LanguageUtil.j(this.k) || LanguageUtil.p(this.k)) {
                this.z.add(b(true));
            } else {
                this.z.add(am());
            }
        }
        if (CommonUtil.as() && (aq() || !sbc.a())) {
            LogUtil.a("PersonalCenterListManager", "initRecyclerData initBetaSuggestItem");
            this.z.add(z());
        }
        this.z.add(ad());
        this.z.add(d(false));
        this.z.add(d(0));
        this.z.add(d(0));
    }

    private boolean aq() {
        return CommonUtil.y(this.k) && !this.p && (LanguageUtil.m(this.k) || LanguageUtil.p(this.k));
    }

    private boolean ao() {
        if (this.q) {
            return false;
        }
        if (!this.p) {
            return true;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        if (this.r) {
            return OperationUtils.isOperation(accountInfo) || ActiveTipStringUtils.b();
        }
        return false;
    }

    private rzs al() {
        rzs rzsVar = this.ah;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ah = rzsVar2;
        rzsVar2.b(2);
        this.ah.d(R$string.IDS_user_profile_achieve_my_reward);
        this.ah.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    if (sbd.this.ah != null && sbd.this.ah.l()) {
                        sbd.this.ah.b(false);
                        sbd.this.ai.cancelBottomRedDotVisible(16);
                        mct.b(sbd.this.k, "my_medal_red_point", "no");
                    }
                    sas.a(sbd.this.k, 2);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PersonalCenterListManager", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_MINE_MY_MEDAL_2040012.value()));
        return this.ah;
    }

    private rzs d(int i) {
        rzs rzsVar = new rzs();
        rzsVar.b(1);
        rzsVar.d(i);
        return rzsVar;
    }

    private rzs aa() {
        rzs rzsVar = this.i;
        if (rzsVar == null) {
            rzs rzsVar2 = new rzs();
            this.i = rzsVar2;
            rzsVar2.b(6);
            this.i.d(6);
            this.i.c(false);
        } else if (this.q || !this.r) {
            rzsVar.c(false);
        }
        return this.i;
    }

    private rzs ai() {
        rzs rzsVar = this.n;
        if (rzsVar == null) {
            rzs rzsVar2 = new rzs();
            this.n = rzsVar2;
            rzsVar2.b(7);
            this.n.d(7);
            this.n.c(true);
        } else if (this.q || !this.r) {
            rzsVar.c(false);
        }
        return this.n;
    }

    private rzs ac() {
        rzs rzsVar = this.d;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.d = rzsVar2;
        rzsVar2.b(4);
        this.d.a(R.mipmap._2131820992_res_0x7f1101c0);
        this.d.d(R$string.IDS_hwh_me_achieve_report);
        this.d.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    sas.a(sbd.this.k, 4);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_MINE_MY_REPORT_2040010.value()));
        return this.d;
    }

    private rzs ah() {
        rzs rzsVar = this.ad;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ad = rzsVar2;
        rzsVar2.b(4);
        this.ad.a(R.mipmap._2131820980_res_0x7f1101b4);
        this.ad.d(R$string.IDS_user_profile_health_show_my_data);
        this.ad.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                sbd.this.d(AnalyticsValue.HEALTH_HEALTH_HEALTHSERVICE_HEALTHDATA_2030045.value());
                Intent intent = new Intent();
                intent.setClass(sbd.this.k, HealthDatasActivity.class);
                sbd.this.k.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_HEALTH_HEALTHSERVICE_HEALTHDATA_2030045.value()));
        return this.ad;
    }

    private rzs i(boolean z) {
        rzs rzsVar = this.ak;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ak = rzsVar2;
        rzsVar2.b(4);
        this.ak.a(R.mipmap._2131820991_res_0x7f1101bf);
        this.ak.a(z);
        this.ak.d(R$string.IDS_startup_set_user_info);
        this.ak.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                hashMap.put("from", 0);
                ixx.d().d(sbd.this.k, AnalyticsValue.REMIND_MY_USER_INFO_EVENT.value(), hashMap, 0);
                if (sbd.this.k != null) {
                    AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(sbd.this.k);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_MINE_MY_INFO_2040007.value()));
        return this.ak;
    }

    private rzs aj() {
        rzs rzsVar = this.aa;
        if (rzsVar != null) {
            rzsVar.c(true ^ this.t);
            return this.aa;
        }
        rzs rzsVar2 = new rzs();
        this.aa = rzsVar2;
        rzsVar2.b(4);
        this.aa.a(R.drawable._2131430152_res_0x7f0b0b08);
        this.aa.a(true);
        this.aa.d(R$string.IDS_hwh_my_route);
        this.aa.dUA_(new View.OnClickListener() { // from class: sbe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                sbd.this.dUU_(view);
            }
        });
        return this.aa;
    }

    /* synthetic */ void dUU_(View view) {
        if (nsn.cLk_(view)) {
            LogUtil.a("PersonalCenterListManager", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            d(AnalyticsValue.REMIND_MY_ROUTE_EVENT.value());
            if (this.k != null) {
                AppRouter.b("/HWUserProfileMgr/MyRouteActivity").c(this.k);
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private rzs ag() {
        rzs rzsVar = this.l;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.l = rzsVar2;
        rzsVar2.b(4);
        this.l.a(R.drawable._2131429948_res_0x7f0b0a3c);
        this.l.a(true);
        this.l.d(R$string.IDS_exhibition_info);
        this.l.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    if (sbd.this.k != null) {
                        PermissionUtil.b(sbd.this.k, PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES, new CustomPermissionAction(sbd.this.k) { // from class: sbd.19.3
                            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                            public void onGranted() {
                                Intent intent = new Intent();
                                intent.setClass(sbd.this.k, ExhibitionInfoActivity.class);
                                sbd.this.k.startActivity(intent);
                            }
                        });
                    }
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }, (BaseActivity) this.k, true, ""));
        return this.l;
    }

    private rzs h(boolean z) {
        rzs rzsVar = this.ag;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ag = rzsVar2;
        rzsVar2.b(4);
        this.ag.a(R.mipmap._2131820990_res_0x7f1101be);
        this.ag.a(z);
        this.ag.d(R$string.IDS_hwh_privacy_center);
        this.ag.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    if (sbd.this.k != null) {
                        Intent intent = new Intent();
                        intent.setClass(sbd.this.k, PrivacyCenterActivity.class);
                        sbd.this.k.startActivity(intent);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PersonalCenterListManager", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.k, true, ""));
        return this.ag;
    }

    private rzs af() {
        rzs rzsVar = this.y;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.y = rzsVar2;
        rzsVar2.b(4);
        this.y.a(R.mipmap._2131821007_res_0x7f1101cf);
        this.y.d(R$string.IDS_activity_social_my_activities);
        this.y.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    rzo.d(sbd.this.k);
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }, (BaseActivity) this.k, true, ""));
        return this.y;
    }

    private rzs an() {
        rzs rzsVar = this.ae;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ae = rzsVar2;
        rzsVar2.b(8);
        this.ae.a(R.mipmap._2131821257_res_0x7f1102c9);
        this.ae.d(R$string.sug_home_my_own_plans);
        this.ae.c(false);
        this.ae.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    sbd.this.x();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }, (BaseActivity) this.k, true, ""));
        return this.ae;
    }

    private rzs ae() {
        rzs rzsVar = this.ac;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ac = rzsVar2;
        rzsVar2.b(4);
        this.ac.a(R.drawable._2131430207_res_0x7f0b0b3f);
        this.ac.a(true);
        this.ac.b(false);
        if (this.p) {
            this.ac.d(R$string.sug_home_my_own_plans);
        } else {
            this.ac.d(R$string.IDS_title_course_plan);
        }
        this.ac.c(true);
        this.ac.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FitnessAdviceApi fitnessAdviceApi;
                if (!nsn.cLk_(view)) {
                    if (!sbd.this.p) {
                        sbd.this.x();
                    } else {
                        gge.e("1120020");
                        if (sbd.this.k != null && (fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class)) != null) {
                            fitnessAdviceApi.launchMyPlanH5();
                        }
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PersonalCenterListManager", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.k, true, ""));
        return this.ac;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        d(AnalyticsValue.HEALTH_MINE_TAB_MY_COURSE_1130052.value());
        if (this.c != null) {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.getContext(), "com.huawei.ui.main.stories.me.activity.MyCourseActivity");
            intent.putExtra("titleName", this.k.getResources().getString(R$string.IDS_FitnessAdvice_previous_course));
            intent.putExtra("courseCategoryKey", 4);
            intent.setFlags(268435456);
            this.c.startActivity(intent);
        }
    }

    private rzs e(boolean z) {
        rzs rzsVar = this.ab;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.ab = rzsVar2;
        rzsVar2.b(4);
        this.ab.a(R.mipmap._2131820989_res_0x7f1101bd);
        this.ab.a(z);
        this.ab.d(R$string.IDS_hwh_home_healthshop_featured_order_management);
        this.ab.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    if (sbd.this.p) {
                        LogUtil.a("PersonalCenterListManager", "start orderListActivity oversea.");
                        AppRouter.b("/TradeService/TradeOrderListActivity").c("PAGE_TYPE", 2).c(BaseApplication.getContext());
                    } else {
                        Intent intent = new Intent();
                        intent.setClass(sbd.this.k, WebViewActivity.class);
                        intent.putExtra("url", sbd.this.v());
                        intent.putExtra("EXTRA_BI_ID", "");
                        intent.putExtra("EXTRA_BI_NAME", "");
                        intent.putExtra("EXTRA_BI_SOURCE", "ShopManager");
                        intent.putExtra("EXTRA_BI_SHOWTIME", "SHOW_TIME_BI");
                        intent.putExtra("title", sbd.this.k.getString(R$string.IDS_hwh_home_healthshop_featured_order_management));
                        sbd.this.k.startActivity(intent);
                    }
                    sbd.this.d(AnalyticsValue.HEALTH_SHOP_ENTER_SHOP_MANAGER_2120009.value());
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PersonalCenterListManager", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_SHOP_ENTER_SHOP_MANAGER_2120009.value()));
        return this.ab;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String v() {
        if (!TextUtils.isEmpty(this.aj)) {
            return this.aj;
        }
        String str = "";
        try {
            str = OperationUtils.getVmallRules().getString("orderList");
            LogUtil.c("PersonalCenterListManager", "orderUrl: ", str);
        } catch (JSONException unused) {
            LogUtil.h("PersonalCenterListManager", "getShopCenterUrl JSONException.");
        }
        if (TextUtils.isEmpty(str)) {
            str = UriConstants.VMALL_URL_STRING;
        }
        String url = GRSManager.a(this.k).getUrl("healthRecommendUrl");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h("PersonalCenterListManager", "getShopCenterUrl recommendHost is empty");
            url = "https:/";
        }
        LogUtil.c("PersonalCenterListManager", "getShopCenterUrl recommendHost = ", url);
        String str2 = url + str;
        this.aj = str2;
        return str2;
    }

    private rzs c(boolean z) {
        int i;
        rzs rzsVar = new rzs();
        this.w = rzsVar;
        rzsVar.b(4);
        this.w.a(R.mipmap._2131820979_res_0x7f1101b3);
        this.w.a(z);
        if (CommonUtil.bu()) {
            i = R$string.IDS_my_award_my_award;
        } else {
            i = R$string.IDS_my_assets;
        }
        this.w.d(i);
        this.w.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    sbd.this.ai.openMyAssetPage();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PersonalCenterListManager", "click too fast", "in click my award item");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_MY_AWARD_ENTER_2190004.value()));
        return this.w;
    }

    private rzs a(boolean z) {
        rzs rzsVar = this.v;
        if (rzsVar != null) {
            rzsVar.c(true);
            return this.v;
        }
        rzs rzsVar2 = new rzs();
        this.v = rzsVar2;
        rzsVar2.b(4);
        this.v.a(R.drawable._2131430140_res_0x7f0b0afc);
        this.v.a(z);
        this.v.d(R$string.IDS_ihealth_labs);
        this.v.dUA_(new View.OnClickListener() { // from class: sbd.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                sbd.this.d(AnalyticsValue.HEALTH_MINE_LAB_2040072.value());
                Intent intent = new Intent();
                intent.setClass(sbd.this.k, IHealthLabsAcitivity.class);
                sbd.this.k.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this.v;
    }

    private rzs g(boolean z) {
        rzs rzsVar = this.an;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.an = rzsVar2;
        rzsVar2.b(4);
        this.an.a(R.mipmap._2131820993_res_0x7f1101c1);
        this.an.a(z);
        this.an.d(R$string.IDS_main_btn_state_settings);
        this.an.dUA_(new View.OnClickListener() { // from class: sbd.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                sbd.this.d(AnalyticsValue.HEALTH_MINE_SETTINGS_2040013.value());
                if (sbd.this.k != null) {
                    sbd.this.k.startActivity(new Intent(sbd.this.k, (Class<?>) AppSettingActivity.class));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this.an;
    }

    private rzs b(boolean z) {
        int i;
        rzs rzsVar = this.s;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.s = rzsVar2;
        rzsVar2.b(4);
        this.s.a(R.mipmap._2131820985_res_0x7f1101b9);
        this.s.a(z);
        if (njn.e(this.k) || com.huawei.operation.utils.Utils.isShowJapanCustomer(this.k)) {
            i = R$string.IDS_hw_personal_cetenr_help_customer_service;
        } else {
            i = R$string.IDS_hwh_health_vo2max_help;
        }
        this.s.d(i);
        this.s.dUA_(new View.OnClickListener() { // from class: sbd.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PersonalCenterListManager", "initHelpItem onClick help");
                sbd.this.d(AnalyticsValue.HEALTH_MINE_SETTINGS_HELP_2040049.value());
                if (!ixj.b().a() && !ixj.b().h()) {
                    ixj.b().bCO_(false, null);
                }
                if (sbd.this.k != null) {
                    sbd.this.ar();
                } else {
                    LogUtil.a("PersonalCenterListManager", "initHelpItem onClick context is null!");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this.s;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ar() {
        GRSManager.a(this.k).e("helpCustomerUrl", new GrsQueryCallback() { // from class: sbd.6
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                if (!TextUtils.isEmpty(str)) {
                    if (sbd.this.m != null) {
                        sbd.this.am = str;
                        sbd.this.m.post(new Runnable() { // from class: sbd.6.3
                            @Override // java.lang.Runnable
                            public void run() {
                                sbd.this.av();
                            }
                        });
                        return;
                    } else {
                        LogUtil.h("PersonalCenterListManager", "mHandler is null");
                        return;
                    }
                }
                LogUtil.h("PersonalCenterListManager", "obtainTipsUrlDomain urlDomain is empty");
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("PersonalCenterListManager", "obtainTipsUrlDomain onCallBackFail");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        String str;
        LogUtil.a("PersonalCenterListManager", "startWebViewActivity()");
        boolean z = njn.e(this.k) || com.huawei.operation.utils.Utils.isShowJapanCustomer(this.k);
        Intent intent = new Intent(this.k, (Class<?>) WebViewActivity.class);
        String helpCustomerUrl = HelpCustomerOperate.getHelpCustomerUrl(this.am, "#/help?cid=11069");
        if (LoginInit.getInstance(this.k).getIsLogined() && jgp.a(this.k).b()) {
            str = helpCustomerUrl + "&isDevice=3";
        } else {
            str = helpCustomerUrl + "&isDevice=2";
        }
        intent.putExtra("url", str);
        intent.putExtra("title", this.k.getString(z ? R$string.IDS_hw_personal_cetenr_help_customer_service : R$string.IDS_hwh_health_vo2max_help));
        this.k.startActivity(intent);
        a(this.s, false);
        this.ai.cancelBottomRedDotVisible(32);
        mct.b(this.k, "my_help_red_point", "no");
    }

    private rzs am() {
        rzs rzsVar = this.al;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.al = rzsVar2;
        rzsVar2.b(4);
        this.al.c(false);
        this.al.a(R.drawable._2131430183_res_0x7f0b0b27);
        this.al.d(R$string.IDS_user_profile_questions_suggestions);
        this.al.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.cLk_(view)) {
                    sbd.this.ax();
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_MINE_QA_2040026.value()));
        return this.al;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ax() {
        jcc.d().c();
        LogUtil.a("PersonalCenterListManager", "transfer enter");
        d(AnalyticsValue.HEALTH_MINE_QA_2040026.value());
        if (!Utils.h() || CommonUtil.bp()) {
            LogUtil.h("PersonalCenterListManager", "not support suggest");
            return;
        }
        d(this.k.getApplicationContext());
        int bCP_ = ixj.b().bCP_(this.c, true);
        if (bCP_ != 0) {
            LogUtil.a("PersonalCenterListManager", "Questions and Suggestions errorCode : ", Integer.valueOf(bCP_));
        } else {
            LogUtil.a("PersonalCenterListManager", "Questions and Suggestions enter successful");
        }
    }

    private rzs z() {
        rzs rzsVar = this.f;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.f = rzsVar2;
        rzsVar2.b(4);
        this.f.a(R.mipmap._2131821005_res_0x7f1101cd);
        this.f.d(R$string.IDS_user_profile_questions_suggestions_bata);
        if (this.k instanceof BaseActivity) {
            this.f.dUA_(nkx.cwZ_(new View.OnClickListener() { // from class: sbd.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (!nsn.cLk_(view)) {
                        sbd.this.u();
                        LogUtil.a("PersonalCenterListManager", "beta suggestions");
                        sbd.this.d(AnalyticsValue.HEALTH_MINE_QA_2040026.value());
                        sbd.this.p();
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }, (BaseActivity) this.k, true, AnalyticsValue.HEALTH_MINE_QA_2040026.value()));
        }
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: sbd.14
            @Override // java.lang.Runnable
            public void run() {
                ifp.c().c(sbd.this.k, -1, "");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        d(this.k.getApplicationContext());
        aw();
        y();
    }

    public void d(Context context) {
        LogUtil.a("PersonalCenterListManager", "getThreeAppInfos");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            LogUtil.h("PersonalCenterListManager", "PackageManager is null");
            return;
        }
        int i = 0;
        while (true) {
            String[] strArr = f16996a;
            if (i >= strArr.length) {
                return;
            }
            try {
                packageManager.getApplicationInfo(strArr[i], 0);
                jcc.d().b("third_app", strArr[i]);
                LogUtil.a("PersonalCenterListManager", "package : ", strArr[i]);
            } catch (PackageManager.NameNotFoundException e) {
                LogUtil.b("PersonalCenterListManager", "writeThirdAppInfos fail ", e.getMessage());
            }
            i++;
        }
    }

    private void aw() {
        jeq.e().setProductType(10);
    }

    private void y() {
        String str;
        LogUtil.a("PersonalCenterListManager", "enter goBetaFeedBack");
        HealthFeedbackParams healthFeedbackParams = new HealthFeedbackParams();
        d(healthFeedbackParams);
        healthFeedbackParams.setProductName(nsf.cKp_(this.k).getString(R$string.IDS_hw_app_name));
        LogUtil.a("PersonalCenterListManager", "goBetaFeedBack productName = ", healthFeedbackParams.getProductName());
        try {
            str = this.k.getPackageManager().getPackageInfo(this.k.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("PersonalCenterListManager", "goBetaFeedBack exception:", e.getMessage());
            str = "1.0.0";
        }
        healthFeedbackParams.setProductVersion(str);
        healthFeedbackParams.setProductType(10);
        b(this.k, healthFeedbackParams, new rzu(this.m));
        LogUtil.a("PersonalCenterListManager", "goBetaFeedBack ok.");
    }

    private void b(Context context, HealthFeedbackParams healthFeedbackParams, rzu rzuVar) {
        jep jepVar = new jep();
        jepVar.c(context.getPackageName());
        jepVar.e(String.valueOf(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)));
        jepVar.b(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008));
        jepVar.h("at");
        int i = 0;
        String a2 = CommonUtil.a(BaseApplication.getContext(), false);
        if (TextUtils.isEmpty(a2)) {
            a2 = "000000000000000";
        }
        jepVar.a(a2);
        try {
            String deviceType = SharedPreferenceUtil.getInstance(context).getDeviceType();
            if (deviceType != null) {
                i = Integer.parseInt(deviceType);
            }
        } catch (NumberFormatException e) {
            LogUtil.b("PersonalCenterListManager", "exception:", LogAnonymous.b((Throwable) e));
        }
        jepVar.a(Integer.valueOf(i));
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1000);
        if (TextUtils.isEmpty(accountInfo)) {
            accountInfo = b(context);
        }
        jepVar.g(accountInfo);
        jepVar.d(Agent.OS_NAME);
        jepVar.d(Integer.valueOf(Constants.HEALTH_APP_LOGIN_CHANNEL));
        jeq e2 = jeq.e();
        if (e2 != null) {
            e2.gotoFeedback(context, jepVar, healthFeedbackParams, rzuVar);
        }
    }

    private String b(Context context) {
        final ArrayList arrayList = new ArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        LoginInit.getInstance(context).getAccountInfo(1001, new StorageDataCallback() { // from class: sbd.11
            @Override // com.huawei.hwdataaccessmodel.utils.StorageDataCallback
            public void onProcessed(StorageResult storageResult) {
                if (storageResult.d() == 0) {
                    arrayList.add(String.valueOf(storageResult.e()));
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(1L, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PersonalCenterListManager", "getAccountName InterruptedException");
        }
        if (arrayList.isEmpty()) {
            LogUtil.h("PersonalCenterListManager", "getAccountName is null");
            return "";
        }
        return (String) arrayList.get(0);
    }

    private String b(String str, boolean z) {
        if (str == null) {
            return str;
        }
        if (z) {
            String b = SecurityUtils.b(str);
            return b.length() >= 24 ? b.replace(Marker.ANY_NON_NULL_MARKER, "A").replace("/", "A").replace("=", "A").substring(0, 24) : b;
        }
        if (str.contains(":") || str.length() <= 24) {
            return str.replace(":", "");
        }
        String b2 = SecurityUtils.b(str);
        return b2.length() >= 24 ? b2.replace(Marker.ANY_NON_NULL_MARKER, "A").replace("/", "A").replace("=", "A").substring(0, 24) : b2;
    }

    private void d(HealthFeedbackParams healthFeedbackParams) {
        if (cpl.c().j().size() <= 0 || !CommonUtil.ce()) {
            String a2 = CommonUtil.a(BaseApplication.getContext(), false);
            healthFeedbackParams.setDeviceId(TextUtils.isEmpty(a2) ? "000000000000000" : a2);
            healthFeedbackParams.setDeviceSn("NA");
            healthFeedbackParams.setDeviceModel("Wear");
            return;
        }
        LogUtil.c("PersonalCenterListManager", " has wear device");
        String a3 = cpl.c().j().get(0).a();
        DeviceInfo e = jpt.e(a3, "PersonalCenterListManager");
        if (e == null) {
            healthFeedbackParams.setDeviceId("000000000000000");
            healthFeedbackParams.setDeviceSn("NA");
            healthFeedbackParams.setDeviceModel("Wear");
            return;
        }
        String deviceModel = e.getDeviceModel();
        String udidFromDevice = e.getUdidFromDevice();
        String securityUuid = e.getSecurityUuid();
        LogUtil.c("PersonalCenterListManager", "identify: ", iyl.d().e(securityUuid));
        String replace = securityUuid != null ? securityUuid.replace(":", "") : "NA";
        if (TextUtils.isEmpty(udidFromDevice)) {
            if (e.getProductType() >= 34) {
                if (!a3.equals(replace)) {
                    udidFromDevice = knl.a(replace + a3);
                } else {
                    udidFromDevice = knl.a(replace);
                }
            } else {
                udidFromDevice = b(securityUuid, false);
            }
        }
        healthFeedbackParams.setDeviceId(TextUtils.isEmpty(udidFromDevice) ? "000000000000000" : udidFromDevice);
        healthFeedbackParams.setDeviceSn(replace);
        healthFeedbackParams.setDeviceModel(deviceModel);
    }

    private rzs ad() {
        rzs rzsVar = this.o;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.o = rzsVar2;
        rzsVar2.b(4);
        this.o.a(R.mipmap._2131821063_res_0x7f110207);
        this.o.d(R$string.IDS_hw_show_setting_detection_updates);
        if (this.x || !kyd.c()) {
            LogUtil.a("PersonalCenterListManager", "is GP or Store version or update switch is close");
            this.o.c(false);
        } else {
            this.o.c(true);
        }
        this.o.dUA_(new View.OnClickListener() { // from class: sbd.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                sbd.this.d(AnalyticsValue.HEALTH_MINE_SETTINGS_CHECK_UPDATE_2040020.value());
                LogUtil.c("PersonalCenterListManager", "onItemClick(): id = LIST_ITEM_APP_UPDATE");
                if (sbd.this.o != null) {
                    sbd.this.o.d(true);
                    sbd.this.af.c(sbd.this.o);
                }
                sbd.this.t();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this.o;
    }

    private rzs d(boolean z) {
        rzs rzsVar = this.b;
        if (rzsVar != null) {
            return rzsVar;
        }
        rzs rzsVar2 = new rzs();
        this.b = rzsVar2;
        rzsVar2.b(4);
        this.b.a(R.mipmap._2131820977_res_0x7f1101b1);
        this.b.a(z);
        this.b.d(R$string.IDS_settings_about);
        this.b.dUA_(new View.OnClickListener() { // from class: sbd.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.cLk_(view)) {
                    LogUtil.a("PersonalCenterListManager", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                sbd.this.d(AnalyticsValue.HEALTH_MINE_ABOUT_2040023.value());
                if (sbd.this.k != null) {
                    sbd.this.k.startActivity(new Intent(sbd.this.k, (Class<?>) HealthAboutActivity.class));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        q();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.h, intentFilter, LocalBroadcast.c, null);
        LogUtil.c("PersonalCenterListManager", " enterUpdateActivity():");
    }

    private void q() {
        LogUtil.a("PersonalCenterListManager", "doCheckAppNewVersion");
        if (!CommonUtil.as()) {
            this.aq.doManualCheckAppNewVersion();
            return;
        }
        if (LoginInit.getInstance(this.k).isBrowseMode()) {
            ab();
            LoginInit.getInstance(this.k).browsingToLogin(new IBaseResponseCallback() { // from class: sbd.18
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.h("PersonalCenterListManager", "LoginInit.browsingToLogin is logging");
                }
            }, "");
        } else {
            if (Utils.o()) {
                ab();
                Context context = this.k;
                nrh.d(context, context.getString(R$string.wallet_unenable_country));
                return;
            }
            this.aq.doManualCheckAppNewVersion();
        }
    }

    private void ab() {
        rzs rzsVar = this.o;
        if (rzsVar != null) {
            rzsVar.d(false);
            this.af.c(this.o);
        }
    }

    public void d(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(this.k, str, hashMap, 0);
    }

    private void at() {
        LogUtil.a("PersonalCenterListManager", "unregisterCheckNewAppBroadcast enter");
        try {
            if (this.h != null) {
                BaseApplication.getContext().unregisterReceiver(this.h);
            }
        } catch (IllegalArgumentException e) {
            LogUtil.b("PersonalCenterListManager", "unregister Exception ", LogAnonymous.b((Throwable) e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dUT_(Intent intent) {
        if (CommonUtil.as()) {
            dUS_(intent);
        }
        int intExtra = intent.getIntExtra("state", -1);
        int intExtra2 = intent.getIntExtra("result", -1);
        String dUR_ = dUR_(intent);
        boolean booleanExtra = intent.getBooleanExtra("isForced", false);
        LogUtil.a("PersonalCenterListManager", "updateAppState: state = ", Integer.valueOf(intExtra), ", result = ", Integer.valueOf(intExtra2));
        switch (intExtra) {
            case 10:
                LogUtil.a("PersonalCenterListManager", "STATE_CHECK_NEW_VERSION_START");
                break;
            case 11:
                c(intExtra2);
                break;
            case 12:
                if (this.k == null) {
                    LogUtil.a("PersonalCenterListManager", "mContext is null, return.");
                    break;
                } else {
                    b(intExtra2, dUR_);
                    break;
                }
            case 13:
                LogUtil.a("PersonalCenterListManager", "updateAppState,STATE_MANUAL_BEFORE_AUTO");
                rzs rzsVar = this.o;
                if (rzsVar != null && rzsVar.m()) {
                    this.o.d(false);
                    this.af.c(this.o);
                    break;
                }
                break;
            default:
                switch (intExtra) {
                    case 30:
                        LogUtil.a("PersonalCenterListManager", "STATE_FETCH_CHANGELOG_START");
                        break;
                    case 31:
                        w();
                        break;
                    case 32:
                        a(booleanExtra, dUR_);
                        break;
                }
        }
    }

    private void b(int i, String str) {
        this.e = str;
        this.j = i;
        LogUtil.a("PersonalCenterListManager", "mAppNewVersion is:" + this.e);
        LogUtil.a("PersonalCenterListManager", "appNewVersionNumSize is:", Integer.valueOf(this.j));
        LogUtil.a("PersonalCenterListManager", " is:" + CommonUtil.d(this.k));
    }

    private String dUR_(Intent intent) {
        String stringExtra = intent.getStringExtra("content");
        return stringExtra == null ? "" : stringExtra;
    }

    private void dUS_(Intent intent) {
        rzs rzsVar = this.o;
        if (rzsVar != null) {
            if (!rzsVar.m()) {
                return;
            }
            this.o.d(false);
            this.af.c(this.o);
        } else {
            LogUtil.h("PersonalCenterListManager", "updateAppState mCheckUpdateListBean == null");
        }
        if (!com.huawei.haf.application.BaseApplication.j()) {
            LogUtil.h("PersonalCenterListManager", "STATE_FETCH_CHANGELOG_SUCCESS: CommonUtil.isRunningForeground is false");
            return;
        }
        boolean booleanExtra = intent.getBooleanExtra("hasNewBetaVersion", false);
        int intExtra = intent.getIntExtra("responseCode", 0);
        if (booleanExtra) {
            Intent intent2 = new Intent();
            intent2.putExtra("hasNewBetaVersion", true);
            intent2.putExtra("isManual", intent.getBooleanExtra("isManual", false));
            this.aq.showNewVersionDialog(this.k, intent2);
            return;
        }
        if (intExtra == -5) {
            Context context = this.k;
            nrh.d(context, context.getString(R$string.IDS_member_center_not_exit));
            return;
        }
        if (intExtra == -4) {
            Context context2 = this.k;
            nrh.d(context2, context2.getString(R$string.IDS_member_center_version_incorrect));
            return;
        }
        if (intExtra == 200) {
            Context context3 = this.k;
            nrh.d(context3, context3.getString(R$string.IDS_hwh_me_settings_app_update));
            ap();
        } else if (intExtra == 300 || intExtra == 400) {
            Context context4 = this.k;
            nrh.d(context4, context4.getString(R$string.IDS_update_network_error));
        } else {
            Context context5 = this.k;
            nrh.d(context5, context5.getString(R$string.IDS_update_unknown_error));
        }
    }

    private void c(int i) {
        String string;
        LogUtil.a("PersonalCenterListManager", "STATE_CHECK_NEW_VERSION_FAILED");
        if (this.k == null) {
            LogUtil.b("PersonalCenterListManager", "mContext is null");
            return;
        }
        rzs rzsVar = this.o;
        if (rzsVar != null) {
            if (!rzsVar.m()) {
                return;
            }
            this.o.d(false);
            this.af.c(this.o);
        } else {
            LogUtil.a("PersonalCenterListManager", "updateAppState mCheckUpdateListBean==null");
        }
        at();
        if (i == 0) {
            LogUtil.a("PersonalCenterListManager", "No New Version");
            Context context = this.k;
            nrh.d(context, context.getString(R$string.IDS_hwh_me_settings_app_update));
            ap();
            return;
        }
        if (i == 1 || i == 3) {
            nrh.d(this.k, this.k.getResources().getString(R$string.IDS_update_network_error));
        } else {
            if (i == 2) {
                string = this.k.getResources().getString(R$string.IDS_update_server_error);
            } else {
                string = this.k.getResources().getString(R$string.IDS_update_unknown_error);
            }
            nrh.d(this.k, string);
        }
    }

    private void a(boolean z, String str) {
        LogUtil.a("PersonalCenterListManager", "STATE_FETCH_CHANGELOG_SUCCESS:");
        rzs rzsVar = this.o;
        if (rzsVar != null) {
            rzsVar.d(false);
            this.af.c(this.o);
        }
        if (!com.huawei.haf.application.BaseApplication.j()) {
            LogUtil.h("PersonalCenterListManager", "STATE_FETCH_CHANGELOG_SUCCESS: CommonUtil.isRunningForeground is false");
            return;
        }
        if (CommonUtil.ag(this.k) && this.e.contains("Beta")) {
            LogUtil.a("PersonalCenterListManager", "The current version is release and new version is beta");
            Context context = this.k;
            nrh.d(context, context.getString(R$string.IDS_hwh_me_settings_app_update));
            return;
        }
        if (CommonUtil.as() && !this.e.contains("Beta")) {
            LogUtil.a("PersonalCenterListManager", "The current version is beta and new version is release");
            Context context2 = this.k;
            nrh.d(context2, context2.getString(R$string.IDS_hwh_me_settings_app_update));
            ap();
            return;
        }
        if (!CommonUtil.bh()) {
            Intent intent = new Intent();
            intent.putExtra("UpdateMode", 2);
            intent.putExtra("appNewVersionNumSize", this.j);
            intent.putExtra("mAppNewVersion", this.e);
            intent.putExtra("isForced", z);
            intent.putExtra("mAppNewFeatureContent", str);
            this.aq.showNewVersionDialog(this.k, intent);
        }
        at();
    }

    private void w() {
        LogUtil.a("PersonalCenterListManager", "STATE_FETCH_CHANGELOG_FAILED");
        rzs rzsVar = this.o;
        if (rzsVar != null) {
            rzsVar.d(false);
            this.af.c(this.o);
        }
        Intent intent = new Intent();
        intent.putExtra("mChangeLog", ParamConstants.CallbackMethod.ON_FAIL);
        at();
        this.aq.showNewVersionDialog(this.k, intent);
    }

    private void ap() {
        kxz.d(String.valueOf(CommonUtil.d(this.k)), this.k);
        if (CommonUtil.as()) {
            kxz.f("", this.k);
        }
        au();
    }

    private void au() {
        boolean z;
        if (this.o != null) {
            boolean haveNewAppVersion = this.aq.haveNewAppVersion(this.k);
            LogUtil.a("PersonalCenterListManager", "showAppUpdateNew: haveNewAppVersion = ", Boolean.valueOf(haveNewAppVersion));
            if (haveNewAppVersion) {
                this.ai.setBottomRedDotVisibility(512);
                z = true;
            } else {
                this.ai.cancelBottomRedDotVisible(512);
                z = false;
            }
            a(this.o, z);
        }
    }

    public void a(rzs rzsVar, boolean z) {
        if (rzsVar == null || rzsVar.l() == z) {
            return;
        }
        rzsVar.b(z);
        this.af.c(rzsVar);
    }

    public void a(rzs rzsVar, Object obj) {
        if (rzsVar != null) {
            rzsVar.c(obj);
            this.af.c(rzsVar);
        }
    }

    public void s() {
        if (koq.b(this.z)) {
            LogUtil.h("PersonalCenterListManager", "setRecyclerViewItemBackground mPersonalCenterListBeans is empty");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (rzs rzsVar : this.z) {
            if (rzsVar.i()) {
                arrayList.add(rzsVar);
            }
        }
        int i = 0;
        while (i < arrayList.size()) {
            rzs rzsVar2 = (rzs) arrayList.get(i);
            rzs rzsVar3 = null;
            rzs rzsVar4 = i > 0 ? (rzs) arrayList.get(i - 1) : null;
            if (i < arrayList.size() - 1) {
                rzsVar3 = (rzs) arrayList.get(i + 1);
            }
            b(rzsVar2, rzsVar4, rzsVar3);
            i++;
        }
    }

    private void b(rzs rzsVar, rzs rzsVar2, rzs rzsVar3) {
        if ((rzsVar2 == null && rzsVar3 == null) || ((rzsVar2 == null && rzsVar3.f() == 1) || (rzsVar3 == null && rzsVar2.f() == 1))) {
            rzsVar.e(R.drawable.list_item_background_single_normal);
            return;
        }
        if (rzsVar2 == null) {
            rzsVar.e(R.drawable.list_item_background_top_normal);
            return;
        }
        if (rzsVar3 == null) {
            rzsVar.e(R.drawable.list_item_background_bottom_normal);
            return;
        }
        if (rzsVar2.f() == 1 && rzsVar3.f() == 1) {
            rzsVar.e(R.drawable.list_item_background_single_normal);
            return;
        }
        if (rzsVar2.f() == 1 && rzsVar3.f() != 1) {
            rzsVar.e(R.drawable.list_item_background_top_normal);
        } else if (rzsVar2.f() != 1 && rzsVar3.f() == 1) {
            rzsVar.e(R.drawable.list_item_background_bottom_normal);
        } else {
            rzsVar.e(R.color._2131297213_res_0x7f0903bd);
        }
    }

    public void r() {
        at();
    }
}
