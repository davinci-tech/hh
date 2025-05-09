package com.huawei.featureuserprofile.me;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.featureuserprofile.me.UserInfoActivity;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwnetworkmodel.NetworkStatusListener;
import com.huawei.hwsmartinteractmgr.util.SmartRulesApi;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.errortip.NetworkErrorTipBar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.callback.CommonCallback;
import com.huawei.up.model.UserInfomation;
import defpackage.bzw;
import defpackage.cei;
import defpackage.cpp;
import defpackage.glz;
import defpackage.gmc;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private String f1991a;
    private int aa;
    private RelativeLayout ab;
    private boolean ah;
    private boolean ai;
    private byte[] aj;
    private CommonDialog21 al;
    private boolean am;
    private NetworkStatusListener ao;
    private ImageView aq;
    private int ar;
    private ImageView at;
    private ImageView au;
    private ImageView av;
    private HealthTextView aw;
    private HealthTextView ax;
    private HealthTextView ay;
    private ImageView az;
    private Map<String, Integer> b;
    private ImageView bb;
    private HealthTextView bc;
    private RelativeLayout bd;
    private LinearLayout be;
    private HealthButton bf;
    private LinearLayout bg;
    private LinearLayout bh;
    private CustomTitleBar bi;
    private int bj;
    private HealthTextView bk;
    private HealthDivider bm;
    private String c;
    private Context d;
    private String f;
    private LinearLayout g;
    private String h;
    private String k;
    private int l;
    private ImageView m;
    private Context n;
    private float o;
    private int p;
    private LinearLayout q;
    private String r;
    private String s;
    private RelativeLayout t;
    private String u;
    private LinearLayout v;
    private HealthTextView w;
    private ImageView x;
    private String y;
    private HiUserInfo z;
    private final int e = 13;
    private boolean ad = false;
    private boolean j = false;
    private String bl = "";
    private String bo = "";
    private Handler ba = new d(this);
    private boolean ag = false;
    private boolean ae = false;
    private boolean af = false;
    private boolean ac = false;
    private boolean ak = false;
    private int i = 1;
    private AtomicBoolean an = new AtomicBoolean(false);
    private e as = new e(this);
    private a ap = new a(this);

    /* JADX INFO: Access modifiers changed from: private */
    public int e(int i) {
        if (i == 0) {
            return 1;
        }
        if (1 == i) {
            return 0;
        }
        return i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.n = this;
        this.d = getApplicationContext();
        this.z = new HiUserInfo();
        this.b = new HashMap();
        setContentView(R.layout.hw_show_userinfo_activity);
        getWindow().setBackgroundDrawable(null);
        this.f = SharedPreferenceManager.b(this.n, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN);
        this.ad = Utils.g();
        this.f1991a = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1004);
        Intent intent = getIntent();
        boolean z = false;
        if (intent != null) {
            tX_(intent);
            ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
            if (contentValues != null) {
                LogUtil.c("UIME_UserInfoActivity", "UserInfoActivity onCreate deviceInfo:", contentValues);
                this.bl = contentValues.getAsString("productId");
                this.bo = contentValues.getAsString("uniqueId");
            } else {
                LogUtil.h("UIME_UserInfoActivity", "has not pass device info ");
            }
            if (TextUtils.isEmpty(this.bo) || TextUtils.isEmpty(this.bl)) {
                LogUtil.h("UIME_UserInfoActivity", "device uniqueId or productId is null");
            }
            z = intent.getBooleanExtra("show_gender_window", false);
        }
        p();
        o();
        r();
        c();
        e(z);
    }

    private void tX_(Intent intent) {
        this.ag = intent.getBooleanExtra("hasManager", false);
        this.am = intent.getBooleanExtra("bundleKeyJumpUserInfoActivity", false);
        this.ae = intent.hasExtra("hasManager");
        this.i = intent.getIntExtra("config_mode", 5);
        this.k = intent.getStringExtra("deviceSsid");
        this.o = intent.getFloatExtra("deviceWeight", -1.0f);
        this.aj = intent.getByteArrayExtra("mainHuid");
        this.h = intent.getStringExtra("cloudDeviceId");
        boolean booleanExtra = intent.getBooleanExtra("isNfcConnect", false);
        this.ai = booleanExtra;
        LogUtil.a("UIME_UserInfoActivity", "onCreate getBoolean mIsNfcCommect = ", Boolean.valueOf(booleanExtra));
        if (intent.getBooleanExtra("isBleScale", false)) {
            this.j = true;
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            String stringExtra = intent.getStringExtra("device");
            if (stringExtra != null && stringExtra.equals("wifi")) {
                this.j = true;
            }
            if (intent.hasExtra("auth_device_id")) {
                String stringExtra2 = intent.getStringExtra("auth_device_id");
                if (!TextUtils.isEmpty(stringExtra2)) {
                    this.c = stringExtra2;
                }
            }
        }
        this.bl = intent.getStringExtra("productId");
    }

    private void e(boolean z) {
        LogUtil.a("UIME_UserInfoActivity", "isShowGenderWindow=", Boolean.valueOf(z));
        if (z) {
            this.ak = true;
            ae();
        }
    }

    private void c() {
        this.t.setVisibility(0);
    }

    private void p() {
        this.bi = (CustomTitleBar) findViewById(R.id.me_userInfo_titlebar);
        s();
        l();
        m();
        this.w = (HealthTextView) findViewById(R.id.user_info_fragment_set_gender);
        this.bm = (HealthDivider) findViewById(R.id.divide_line_before_interest);
        this.aq = (ImageView) findViewById(R.id.user_info_go_next_img);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_show_settings_info_layout);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, linearLayout);
        this.x.setOnClickListener(this);
        q();
        t();
        ad();
        this.r = getResources().getString(R.string._2130837640_res_0x7f020088);
        this.s = getResources().getString(R.string._2130837639_res_0x7f020087);
        this.u = getResources().getString(R.string._2130837710_res_0x7f0200ce);
        this.y = getResources().getString(R.string._2130837846_res_0x7f020156);
        String b2 = SharedPreferenceManager.b(this.n, Integer.toString(10000), "onboarding_skip_current_time");
        String b3 = SharedPreferenceManager.b(this.n, Integer.toString(10000), "onboarding_skip");
        if (!TextUtils.isEmpty(b2) && !"1".equals(b3)) {
            LogUtil.a("UIME_UserInfoActivity", "dot dismiss");
            SharedPreferenceManager.e(this.n, Integer.toString(10000), "rid_dot_dismiss", Integer.toString(1), new StorageParams());
        }
        this.bi.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserInfoActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void t() {
        if (this.j) {
            this.bk = (HealthTextView) findViewById(R.id.user_info_fragment_wifi_tip);
            this.bh = (LinearLayout) findViewById(R.id.user_info_complete_layout);
            HealthButton healthButton = (HealthButton) findViewById(R.id.user_info_complete_btn);
            this.bf = healthButton;
            healthButton.setOnClickListener(this);
            this.bk.setVisibility(0);
            this.bm.setVisibility(8);
            this.bi.setTitleText(getString(R.string.IDS_device_hygride_consum_improve_userinfo));
            this.bd = (RelativeLayout) findViewById(R.id.user_info_complete_guide_layout);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.user_info_go_next_layout);
            this.bg = linearLayout;
            linearLayout.setOnClickListener(this);
            aa();
        }
    }

    private void ad() {
        if (LanguageUtil.bc(this.n)) {
            this.at.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.bb.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.az.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.au.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.av.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.m.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.aq.setBackgroundResource(R.drawable._2131429720_res_0x7f0b0958);
        } else {
            this.at.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.bb.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.az.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.au.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.av.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.m.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.aq.setBackgroundResource(R.drawable._2131429722_res_0x7f0b095a);
        }
        if (LanguageUtil.b(this.n.getApplicationContext())) {
            this.w.setWidth(nsn.c(this.n.getApplicationContext(), 60.0f));
            this.w.setGravity(GravityCompat.START);
        }
    }

    private void m() {
        this.at = (ImageView) findViewById(R.id.user_info_fragment_set_gender_image);
        this.bb = (ImageView) findViewById(R.id.user_info_fragment_set_height_image);
        this.az = (ImageView) findViewById(R.id.user_info_fragment_set_weight_image);
        this.au = (ImageView) findViewById(R.id.user_info_fragment_set_birthday_image);
        this.av = (ImageView) findViewById(R.id.user_info_fragment_set_interest_image);
        this.m = (ImageView) findViewById(R.id.user_info_fragment_set_sos_image);
        this.x = (ImageView) findViewById(R.id.hw_health_gender_not_set_prompt);
    }

    private void l() {
        this.aw = (HealthTextView) findViewById(R.id.hw_show_userinfo_gender);
        this.bc = (HealthTextView) findViewById(R.id.hw_show_userinfo_height);
        this.ay = (HealthTextView) findViewById(R.id.hw_show_userinfo_weight);
        this.ax = (HealthTextView) findViewById(R.id.hw_show_userinfo_birthday);
    }

    private void s() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_show_userinfo_gender_layout);
        this.q = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.hw_show_userinfo_height_layout);
        this.v = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.hw_show_userinfo_weight_layout);
        this.be = linearLayout3;
        linearLayout3.setOnClickListener(this);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.hw_show_userinfo_birthday_layout);
        this.g = linearLayout4;
        linearLayout4.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.hw_show_userinfo_interest_layout);
        this.ab = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.hw_show_userinfo_sos_layout);
        this.t = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.an.compareAndSet(false, true)) {
            af();
            HiHealthManager.d(this.d).fetchUserData(new b(this));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.af) {
            if (!this.ak) {
                this.ac = true;
                this.af = false;
            }
            if (glz.e()) {
                ThirdPartyLoginManager.getInstance().queryUserInfo(new IBaseResponseCallback() { // from class: bsa
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        UserInfoActivity.this.b(i, obj);
                    }
                }, false);
                glz.b("UserInfoActivity");
            } else {
                ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).sync(new CommonCallback() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.2
                    @Override // com.huawei.up.callback.CommonCallback
                    public void onSuccess(Bundle bundle) {
                        LogUtil.a("UIME_UserInfoActivity", "hw sync userinfo success");
                        if (UserInfoActivity.this.ba != null) {
                            UserInfoActivity.this.ba.sendEmptyMessage(106);
                        }
                    }

                    @Override // com.huawei.up.callback.CommonCallback
                    public void onFail(int i) {
                        LogUtil.a("UIME_UserInfoActivity", "hw sync userinfo fail:", Integer.valueOf(i));
                    }
                });
            }
        }
    }

    public /* synthetic */ void b(int i, Object obj) {
        Handler handler = this.ba;
        if (handler != null) {
            if (i != 0) {
                handler.sendEmptyMessage(108);
            } else {
                handler.sendEmptyMessage(106);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.ad || this.j || !glz.a() || CommonUtil.bu()) {
            this.ab.setVisibility(8);
        } else {
            this.ab.setVisibility(0);
        }
    }

    private void r() {
        this.ao = new NetworkStatusListener(BaseApplication.getContext()) { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.4
            @Override // com.huawei.hwnetworkmodel.NetworkStatusListener
            public void onConnected() {
                LogUtil.a("UIME_UserInfoActivity", "network connected!");
                UserInfoActivity.this.o();
                UserInfoActivity.this.d(true);
                UserInfoActivity.this.q();
            }

            @Override // com.huawei.hwnetworkmodel.NetworkStatusListener
            public void onDisconnected() {
                LogUtil.h("R_PersonalInfo_UIME_UserInfoActivity", "network disconnected!");
                UserInfoActivity.this.d(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("UIME_UserInfoActivity", "update network status:", Boolean.valueOf(z));
        NetworkErrorTipBar networkErrorTipBar = (NetworkErrorTipBar) findViewById(R.id.network_error_bar);
        if (z) {
            networkErrorTipBar.setVisibility(8);
        } else if (glz.c()) {
            networkErrorTipBar.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj, boolean z) {
        if (!z) {
            Handler handler = this.ba;
            if (handler != null) {
                handler.sendEmptyMessage(102);
                return;
            } else {
                LogUtil.b("R_PersonalInfo_UIME_UserInfoActivity", "Handler is null");
                a();
                return;
            }
        }
        if (obj != null) {
            List list = (List) obj;
            if (list.size() > 0) {
                LogUtil.a("UIME_UserInfoActivity", "fetchUserData onSuccess");
                if (list.get(0) != null) {
                    int gender = this.z.getGender();
                    this.z = (HiUserInfo) list.get(0);
                    int gender2 = ((HiUserInfo) list.get(0)).getGender();
                    LogUtil.c("UIME_UserInfoActivity", "mGenderStart", Integer.valueOf(gender), "mGenderRefresh", Integer.valueOf(gender2));
                    if (gender != 2 && gender2 == 2) {
                        this.ba.sendEmptyMessage(107);
                    }
                }
                LogUtil.c("UIME_UserInfoActivity", "height==", Integer.valueOf(this.z.getHeight()), "weight==", Float.valueOf(this.z.getWeight()));
                LogUtil.c("UIME_UserInfoActivity", "birthday==", Integer.valueOf(this.z.getBirthday()), "gender==", Integer.valueOf(this.z.getGender()));
                this.b.put(CommonConstant.KEY_GENDER, Integer.valueOf(this.z.getGender()));
                this.b.put("birthday", Integer.valueOf(this.z.getBirthday()));
                this.b.put("height", Integer.valueOf(this.z.getHeight()));
                Handler handler2 = this.ba;
                if (handler2 != null) {
                    handler2.sendEmptyMessage(102);
                } else {
                    LogUtil.b("R_PersonalInfo_UIME_UserInfoActivity", "Handler is null");
                    a();
                }
            }
        }
    }

    private void an() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.7
            @Override // java.lang.Runnable
            public void run() {
                Intent intent = new Intent();
                if (!((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).hasEmergencyInfoProvider()) {
                    AppRouter.b("/HWUserProfileMgr/EditInfoActivity").c(UserInfoActivity.this.n);
                    return;
                }
                try {
                    intent.setClassName("com.android.emergency", "com.android.emergency.view.ViewInfoActivity");
                    UserInfoActivity.this.n.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("UIME_UserInfoActivity", "Emui110 activityNotFoundException SOS");
                }
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (glz.c()) {
            gmc.a(this.d);
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.cLk_(view)) {
            LogUtil.a("UIME_UserInfoActivity", "userinfo click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        tW_(view);
        if (this.j && (view == this.bf || view == this.bg)) {
            cei.b().setHeightIsNotSet(false);
            if (!e()) {
                Context context = this.n;
                nrh.d(context, context.getResources().getString(R.string._2130839538_res_0x7f0207f2));
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            cei.b().deliveringUserInformation(this.bl, this.bo, this.z);
            h();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void tW_(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (view == this.q) {
            ae();
        }
        if (view == this.v) {
            UserInfomation userInfomation = new UserInfomation(0);
            userInfomation.loadAccountExtData(this.z);
            gmc.aPb_(this, userInfomation, this.as);
        }
        if (view == this.be) {
            al();
        }
        if (view == this.g) {
            ab();
        }
        if (view == this.ab) {
            AppRouter.b("/HWUserProfileMgr/InterestAndConcernActivity").c(this.n);
        }
        if (view == this.t) {
            SharedPreferenceManager.c("privacy_center", "emergency", String.valueOf(currentTimeMillis));
            an();
        }
        if (view == this.x) {
            ai();
        }
    }

    private void al() {
        double weight = this.z.getWeight() != -100.0f ? this.z.getWeight() : 65.0d;
        LogUtil.a("UIME_UserInfoActivity", "lastWeight: ", Double.valueOf(weight));
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShowInput", true);
        bundle.putString("weight", String.valueOf(weight));
        bundle.putString("bodyFat", String.valueOf(20.0d));
        bundle.putString("from", "/HWUserProfileMgr/UserInfoActivity");
        AppRouter.b("/Main/InputWeightActivity").zF_(bundle).zD_(this, 1213);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("UIME_UserInfoActivity", "requestCode: ", Integer.valueOf(i), "resultCode: ", Integer.valueOf(i2));
        if (i == 1213 && i2 == 1214 && intent != null) {
            double doubleExtra = intent.getDoubleExtra("weight_data", 0.0d);
            LogUtil.a("UIME_UserInfoActivity", "onActivityResult weight data: ", Double.valueOf(doubleExtra));
            UserInfomation userInfomation = new UserInfomation(0);
            userInfomation.loadAccountExtData(this.z);
            userInfomation.setWeight((float) doubleExtra);
            this.as.a(1, this, userInfomation);
        }
    }

    private void ae() {
        if (!glz.b()) {
            if (glz.e()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo(this, null);
            } else {
                gmc.aPf_(this);
            }
            this.af = true;
            return;
        }
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.loadAccountData(this.z);
        gmc.aPa_(this, 20, userInfomation, this.as);
    }

    private void ab() {
        if (!glz.b()) {
            if (glz.e()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo(this, null);
            } else {
                gmc.aPf_(this);
            }
            this.af = true;
            return;
        }
        new UserInfomation(0).loadAccountExtData(this.z);
        gmc.aOZ_(this, this.bj, this.ar, this.l, this.as);
    }

    private void aa() {
        if (cei.b().isMiniScaleDevice(this.bl)) {
            a(true);
            return;
        }
        if (this.ae) {
            boolean resetWifi = cei.b().getResetWifi();
            LogUtil.a("UIME_UserInfoActivity", "showBottomButtonView: mIsHasManager = ", Boolean.valueOf(this.ag), "isResetWifi = ", Boolean.valueOf(resetWifi));
            if (this.ag && !resetWifi) {
                a(true);
                return;
            } else {
                a(false);
                return;
            }
        }
        a(true);
    }

    private void a(boolean z) {
        if (z) {
            this.bh.setVisibility(0);
            this.bd.setVisibility(8);
        } else {
            this.bh.setVisibility(8);
            this.bd.setVisibility(0);
        }
    }

    private void h() {
        if (cei.b().isMiniScaleDevice(this.bl)) {
            f();
        }
        if (TextUtils.isEmpty(this.c) && !"7a1063dd-0e0f-4a72-9939-461476ff0259".equals(this.bl)) {
            finish();
        } else if (this.ae) {
            k();
        } else {
            j();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Bundle bundle = new Bundle();
        bundle.putString("view", "deviceManage");
        bundle.putString("productId", this.bl);
        bundle.putString("deviceSsid", this.k);
        bundle.putBoolean("isNfcConnect", this.ai);
        bundle.putInt("config_mode", 5);
        LogUtil.a("UIME_UserInfoActivity", "gotoDeviceManagePage put isNfcConnect is ", Boolean.valueOf(this.ai));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.bo);
        contentValues.put("productId", this.bl);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        z();
        cei.b().uploadDeviceToCloud(this.bl, this.bo);
        AppRouter.b("/PluginDevice/DeviceMainActivity").zF_(bundle).c(this.n);
        finish();
    }

    private void n() {
        LogUtil.a("UIME_UserInfoActivity", "gotoHagridDeviceWlanUseGuideFragment");
        d("true");
        Bundle bundle = new Bundle();
        bundle.putString("view", "hagridDeviceWlanUseGuide");
        bundle.putString("productId", this.bl);
        bundle.putString("cloudDeviceId", this.h);
        bundle.putByteArray("mainHuid", this.aj);
        bundle.putBoolean("isNfcConnect", this.ai);
        LogUtil.a("UIME_UserInfoActivity", "gotoHagridDeviceWlanUseGuideFragment put isNfcConnect is", Boolean.valueOf(this.ai));
        bundle.putInt(ParamConstants.Param.VIEW_TYPE, 12);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.bo);
        contentValues.put("productId", this.bl);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        AppRouter.b("/PluginDevice/DeviceMainActivity").zF_(bundle).c(this.n);
        finish();
    }

    private void d(String str) {
        SharedPreferenceManager.e(cpp.a(), String.valueOf(10000), "request_sync_data_dialog", str, new StorageParams());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        c(this.ah);
        if (!TextUtils.isEmpty(this.c) || this.j || cei.b().isMiniScaleDevice(this.bl)) {
            ac();
        } else if (x() && u()) {
            ah();
        } else {
            finish();
        }
    }

    public void b() {
        v();
        LogUtil.a("UIME_UserInfoActivity", "getUserInfoError()");
        d(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        glz.b("UserInfoActivity");
        v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, int i2, int i3) {
        if (d(i, i2, i3)) {
            nrh.c(this.n.getApplicationContext(), this.n.getResources().getString(R.string._2130837709_res_0x7f0200cd));
            return false;
        }
        if (!CommonUtil.bu() && Utils.i() && !"1".equals(this.f)) {
            if (glz.c()) {
                gmc.a(this.n);
                return false;
            }
            if ("1".equals(this.f1991a)) {
                LogUtil.a("R_PersonalInfo_UIME_UserInfoActivity", "child account");
                if (!c(i + 13, i2, i3)) {
                    nrh.c(this.n.getApplicationContext(), this.n.getResources().getString(R.string._2130842945_res_0x7f021541, 13));
                    return false;
                }
            } else {
                LogUtil.a("R_PersonalInfo_UIME_UserInfoActivity", "not child account");
                if (c(i + 18, i2, i3 + 1)) {
                    nrh.c(this.n.getApplicationContext(), this.n.getResources().getString(R.string._2130842386_res_0x7f021312));
                    return false;
                }
            }
        } else if (c(i + 13, i2, i3)) {
            nrh.c(this.n.getApplicationContext(), this.n.getResources().getString(R.string._2130842386_res_0x7f021312));
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, int i3) {
        this.bj = i;
        this.ar = i2;
        this.l = i3;
        c(b(this.bj + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(this.ar + 1)) + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(this.l))), 3);
        g();
    }

    private boolean d(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        if (i > i4) {
            return true;
        }
        if (i != i4 || i2 <= i5) {
            return i == i4 && i2 == i5 && i3 > i6;
        }
        return true;
    }

    private boolean c(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        if (i < i4) {
            return false;
        }
        if (i != i4 || i2 >= i5) {
            return (i == i4 && i2 == i5 && i3 <= i6) ? false : true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final double d2, final int i) {
        gmn.c(i, this.n, new IBaseResponseCallback() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                UserInfoActivity.this.af();
                UserInfoActivity.this.ba.sendEmptyMessageDelayed(105, 5000L);
                int rint = (int) Math.rint(d2);
                UserInfomation userInfomation = new UserInfomation(0);
                int i3 = i;
                if (i3 == 1) {
                    userInfomation.setWeight((float) d2);
                    UserInfoActivity.this.z.setWeight((float) d2);
                    UserInfoActivity.this.d(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "2");
                } else if (i3 == 0) {
                    UserInfoActivity.this.ah = true;
                    userInfomation.setHeight(rint);
                    UserInfoActivity.this.z.setHeight(rint);
                    UserInfoActivity.this.d(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "1");
                    cei.b().setUserWeight(rint);
                } else if (i3 == 2) {
                    userInfomation.setGender(Integer.valueOf(UserInfoActivity.this.e(rint)));
                    UserInfoActivity.this.b(rint);
                } else if (i3 == 3) {
                    userInfomation.setBirthday(String.valueOf(rint));
                    UserInfoActivity.this.z.setBirthday(rint);
                    UserInfoActivity.this.z.setAge((HiDateUtil.c(System.currentTimeMillis()) / 10000) - (rint / 10000));
                    UserInfoActivity.this.d(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "4");
                } else {
                    LogUtil.a("UIME_UserInfoActivity", "saveValue");
                }
                UserInfoActivity.this.z.setCreateTime(System.currentTimeMillis());
                ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setUserInfo(userInfomation, UserInfoActivity.this.ap);
                ((SmartRulesApi) Services.a("HWSmartInteractMgr", SmartRulesApi.class)).deleteSmartMsgByType(60000);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        cei.b().setUserGender(i, this.z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (i == 0) {
            this.aw.setText(this.s);
            this.x.setVisibility(8);
        } else if (i == 1) {
            this.aw.setText(this.r);
            this.x.setVisibility(8);
        } else if (i == 2) {
            this.aw.setText(this.y);
            this.x.setVisibility(8);
        } else {
            this.aw.setText(this.u);
            this.x.setVisibility(0);
        }
        this.aw.setTextColor(this.n.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
    }

    private void c(int i) {
        if (i <= 0) {
            this.ax.setText(this.u);
            this.bj = 1992;
            this.ar = 0;
            this.l = 1;
            return;
        }
        this.bj = i / 10000;
        this.ar = ((i % 10000) / 100) - 1;
        this.l = i % 100;
        g();
    }

    private void g() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.bj, this.ar, this.l);
        this.ax.setText(UnitUtil.a(calendar.getTime(), 20));
    }

    private int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("R_PersonalInfo_UIME_UserInfoActivity", "NumberFormatException");
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        LogUtil.a("UIME_UserInfoActivity", "refreshView()");
        this.aw.setVisibility(0);
        this.bc.setVisibility(0);
        this.ay.setVisibility(0);
        this.ax.setVisibility(0);
        LogUtil.c("UIME_UserInfoActivity", "height=", Integer.valueOf(this.z.getHeight()), " weight=", Float.valueOf(this.z.getWeight()));
        LogUtil.c("UIME_UserInfoActivity", "unitFlag=", Integer.valueOf(this.z.getUnitType()));
        a(this.z.getGender());
        c(this.z.getBirthday());
        w();
        y();
        a();
    }

    private void w() {
        this.bc.setText(gmc.b(this.d, this.z.getHeight()));
    }

    private void y() {
        float weight = this.z.getWeight();
        float f = this.o;
        if (f > 0.0f) {
            c(f, 1);
            weight = f;
        }
        if (this.am) {
            String string = getResources().getString(R$string.IDS_hw_me_userinfo_not_set);
            if (weight > 0.0f) {
                int a2 = UnitUtil.a();
                if (a2 == 3) {
                    LogUtil.a("UIME_UserInfoActivity", "enter UNIT_IMPERIAL");
                    string = getResources().getString(R.string._2130837633_res_0x7f020081, UnitUtil.e(UnitUtil.h(weight), 1, 1));
                } else if (a2 == 1) {
                    string = getResources().getQuantityString(R.plurals._2130903105_res_0x7f030041, 1, UnitUtil.e(UnitUtil.b(weight), 1, 1));
                } else {
                    LogUtil.a("UIME_UserInfoActivity", "enter UNIT_CM_KG");
                    string = getResources().getString(R.string._2130837632_res_0x7f020080, UnitUtil.e(CommonUtil.a(String.valueOf(weight)), 1, 1));
                }
            }
            this.ay.setText(string);
            return;
        }
        this.ay.setText(gmc.d(this.d, weight));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.ba;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.ba = null;
        }
        NetworkStatusListener networkStatusListener = this.ao;
        if (networkStatusListener != null) {
            networkStatusListener.unregister(BaseApplication.getContext());
            this.ao = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.n, str, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void af() {
        CommonDialog21 commonDialog21;
        if (this.al == null) {
            LogUtil.a("UIME_UserInfoActivity", "is domestic app");
            new CommonDialog21(this.n, R.style.app_update_dialogActivity);
            this.al = CommonDialog21.a(this.n);
        }
        if (isFinishing() || (commonDialog21 = this.al) == null) {
            return;
        }
        commonDialog21.e(this.n.getString(R.string._2130841528_res_0x7f020fb8));
        this.al.setCancelable(true);
        this.al.show();
        LogUtil.a("UIME_UserInfoActivity", "showLoadingDialog...mLoadingDialog.show()");
    }

    public void a() {
        CommonDialog21 commonDialog21;
        if (isFinishing() || (commonDialog21 = this.al) == null) {
            return;
        }
        commonDialog21.cancel();
        this.al = null;
        LogUtil.a("UIME_UserInfoActivity", "destroy mLoadingDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ai() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.n);
        builder.b(this.n.getString(R.string._2130839506_res_0x7f0207d2)).e(this.n.getString(R.string._2130845097_res_0x7f021da9)).cyV_(this.n.getString(R.string._2130841794_res_0x7f0210c2), new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private void ah() {
        LogUtil.a("UIME_UserInfoActivity", "showModifyConfirmDialog() enter");
        View inflate = View.inflate(this, R.layout.dialog_confirm_user_info, null);
        ((HealthCheckBox) inflate.findViewById(R.id.confirm_userinfo_dialog_box)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.10
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a("UIME_UserInfoActivity", "modifyConfirmDialog onClick not remind");
                StorageParams storageParams = new StorageParams();
                if (z) {
                    SharedPreferenceManager.e(UserInfoActivity.this.n, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree", "false", storageParams);
                } else {
                    SharedPreferenceManager.e(UserInfoActivity.this.n, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree", "true", storageParams);
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.n);
        builder.czg_(inflate).czd_(this.n.getString(R.string._2130845098_res_0x7f021daa), new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserInfoActivity.this.c(((Integer) r0.b.get(CommonConstant.KEY_GENDER)).intValue(), 2);
                UserInfoActivity.this.c(((Integer) r0.b.get("birthday")).intValue(), 3);
                UserInfoActivity.this.c(((Integer) r0.b.get("height")).intValue(), 0);
                UserInfoActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czf_(this.n.getString(R.string._2130837648_res_0x7f020090), new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UserInfoActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        e2.setCanceledOnTouchOutside(false);
        e2.show();
    }

    private boolean x() {
        ArrayList<String> bondedWiFiDevices = cei.b().getBondedWiFiDevices();
        boolean z = (bondedWiFiDevices == null || bondedWiFiDevices.size() == 0) ? false : true;
        if (cei.b().isBondHuaweiBleScaleDevice()) {
            z = true;
        }
        return z && ("false".equals(SharedPreferenceManager.b(this.n, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree")) ^ true);
    }

    private boolean u() {
        Map<String, Integer> map = this.b;
        return (map == null || map.isEmpty() || (this.b.get(CommonConstant.KEY_GENDER).intValue() == this.z.getGender() && this.b.get("birthday").intValue() == this.z.getBirthday() && this.b.get("height").intValue() == this.z.getHeight())) ? false : true;
    }

    private void ac() {
        String string;
        if (cei.b().showConfirmUserInfo(this.bl, this.ae)) {
            return;
        }
        boolean resetWifi = cei.b().getResetWifi();
        if ((this.ag && !resetWifi) || cei.b().isMiniScaleDevice(this.bl)) {
            string = getString(R.string.IDS_device_exist_manager_wifi_confirm_info_and_display);
        } else {
            string = getString(R.string.IDS_device_not_manager_wifi_confirm_info_and_display);
        }
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.n);
        builder.b(this.n.getString(R.string._2130839506_res_0x7f0207d2));
        builder.e(string).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130841378_res_0x7f020f22, new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.UserInfoActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(UserInfoActivity.this.c)) {
                    cei.b().sendUserInfo(UserInfoActivity.this.c);
                } else {
                    LogUtil.a("UIME_UserInfoActivity", "showConfirmUserInfo() deviceid is null");
                }
                if (cei.b().isMiniScaleDevice(UserInfoActivity.this.bl)) {
                    UserInfoActivity.this.f();
                } else if (UserInfoActivity.this.ae) {
                    UserInfoActivity.this.k();
                } else if (!cei.b().isHonourWeightDevice(UserInfoActivity.this.bl)) {
                    UserInfoActivity.this.j();
                } else {
                    UserInfoActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private void z() {
        Bundle bundle = new Bundle();
        bundle.putString("huid", this.z.getHuid());
        bundle.putString("uid", null);
        bundle.putInt("height", this.z.getHeight());
        bundle.putInt(CommonConstant.KEY_GENDER, this.z.getGender());
        bundle.putInt("age", this.z.getAge());
        bundle.putFloat("weight", this.z.getWeight());
        bundle.putInt("birthday", this.z.getBirthday());
        cei.b().sendUserInfo(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        Bundle bundle = new Bundle();
        LogUtil.a("UIME_UserInfoActivity", "has manager: ", Boolean.valueOf(this.ag));
        boolean resetWifi = cei.b().getResetWifi();
        LogUtil.c("UIME_UserInfoActivity", "gotoNextPage isResetWifi = ", Boolean.valueOf(resetWifi));
        if (this.ag && !resetWifi) {
            if (cei.b().isHagridWiFiDevice(this.bl, this.bo)) {
                f();
                return;
            } else {
                n();
                return;
            }
        }
        cei.b().setBindStatus(8);
        bundle.putString("view", "bindResultConfirm");
        bundle.putBoolean("isBindSuccess", true);
        bundle.putString("productId", this.bl);
        bundle.putString("deviceSsid", this.k);
        bundle.putBoolean("isNfcConnect", this.ai);
        bundle.putInt("config_mode", 5);
        LogUtil.a("UIME_UserInfoActivity", "gotoNextPage put isNfcConnect is ", Boolean.valueOf(this.ai));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.bo);
        contentValues.put("productId", this.bl);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        LogUtil.c("UIME_UserInfoActivity", "goto HagridDeviceBindResultFragment bundle:", contentValues);
        z();
        AppRouter.b("/PluginDevice/DeviceMainActivity").zF_(bundle).c(this.n);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        nrh.b(this.n, R.string.IDS_device_wifi_my_qrcode_add_member_success);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.putExtra("arg1", "DeviceIntroduction");
        intent.putExtra("arg2", this.bl);
        intent.putExtra("arg3", this.bo);
        if (cei.b().isHonourScaleDevice(this.bl)) {
            LogUtil.a("UIME_UserInfoActivity", "gotoDeviceActivity isHonourScale bindSuccess");
            intent.putExtra("isBindSuccess", true);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.bl);
        contentValues.put("uniqueId", this.bo);
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            this.n.startActivity(intent);
        } catch (ActivityNotFoundException e2) {
            LogUtil.b("UIME_UserInfoActivity", "ActivityNotFoundException", e2.getMessage());
        }
        finish();
    }

    private boolean e() {
        boolean z;
        boolean z2 = false;
        if (this.z.isGenderValid()) {
            z = true;
        } else {
            this.aw.setTextColor(this.n.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
            z = false;
        }
        if (!this.z.isBirthdayValid()) {
            this.ax.setTextColor(this.n.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
            z = false;
        }
        if (!this.z.isHeightValid()) {
            this.bc.setTextColor(this.n.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
            z = false;
        }
        if (this.z.isWeightValid()) {
            z2 = z;
        } else {
            this.ay.setTextColor(this.n.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
        }
        LogUtil.a("R_PersonalInfo_UIME_UserInfoActivity", "checkUserDataDone  isCompleted = ", Boolean.valueOf(z2));
        return z2;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        c(this.ah);
        if (!TextUtils.isEmpty(this.c) || this.j || cei.b().isMiniScaleDevice(this.bl)) {
            ac();
        } else if (x() && u()) {
            ah();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        Context context = this.n;
        nrh.d(context, context.getResources().getString(R.string._2130839539_res_0x7f0207f3));
    }

    /* loaded from: classes7.dex */
    static class b implements HiCommonListener {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<UserInfoActivity> f1995a;

        b(UserInfoActivity userInfoActivity) {
            this.f1995a = new WeakReference<>(userInfoActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            LogUtil.c("UIME_UserInfoActivity", "fetchUserData onSuccess,data = ", obj);
            UserInfoActivity userInfoActivity = this.f1995a.get();
            if (userInfoActivity != null) {
                userInfoActivity.an.set(false);
                userInfoActivity.e(obj, true);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            ReleaseLogUtil.e("UIME_UserInfoActivity", "fetchUserData onFailure");
            UserInfoActivity userInfoActivity = this.f1995a.get();
            if (userInfoActivity != null) {
                userInfoActivity.an.set(false);
                userInfoActivity.e(obj, false);
            }
        }
    }

    /* loaded from: classes7.dex */
    static class e implements IBaseResponseCallback {
        WeakReference<UserInfoActivity> d;

        public e(UserInfoActivity userInfoActivity) {
            this.d = new WeakReference<>(userInfoActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            UserInfoActivity userInfoActivity = this.d.get();
            if (userInfoActivity == null) {
                LogUtil.h("UIME_UserInfoActivity", "ShowSetValueDialogListener activity null");
                return;
            }
            if (i == -1) {
                gmc.a(userInfoActivity);
                return;
            }
            if (!(obj instanceof UserInfomation)) {
                LogUtil.h("UIME_UserInfoActivity", "userinfomation");
                return;
            }
            UserInfomation userInfomation = (UserInfomation) obj;
            if (i == 1) {
                a(i, userInfoActivity, userInfomation);
                return;
            }
            if (i == 0) {
                b(i, userInfoActivity, userInfomation);
                return;
            }
            if (i == 3) {
                d(userInfoActivity, userInfomation);
            } else if (i == 20) {
                b(userInfoActivity, userInfomation);
            } else {
                LogUtil.h("UIME_UserInfoActivity", "errorCode not define");
            }
        }

        private void b(UserInfoActivity userInfoActivity, UserInfomation userInfomation) {
            userInfoActivity.c(userInfomation.getGender(), 2);
            userInfoActivity.a(userInfomation.getGender());
            if (userInfomation.getGender() == 2) {
                userInfoActivity.ai();
            }
        }

        private void d(UserInfoActivity userInfoActivity, UserInfomation userInfomation) {
            try {
                int parseInt = Integer.parseInt(userInfomation.getBirthday());
                userInfoActivity.bj = parseInt / 10000;
                userInfoActivity.ar = ((parseInt % 10000) / 100) - 1;
                userInfoActivity.l = parseInt % 100;
                if (userInfoActivity.a(userInfoActivity.bj, userInfoActivity.ar, userInfoActivity.l)) {
                    userInfoActivity.b(userInfoActivity.bj, userInfoActivity.ar, userInfoActivity.l);
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("UIME_UserInfoActivity", "handleBirthdayErrorCode: NumberFormatException");
            }
        }

        private void b(int i, UserInfoActivity userInfoActivity, UserInfomation userInfomation) {
            if (UnitUtil.h()) {
                int[] iArr = {5, 7};
                if (userInfomation.getHeight() > 30) {
                    int[] j = UnitUtil.j(new BigDecimal(userInfomation.getHeight()).divide(new BigDecimal(100), 2, 4).doubleValue());
                    if (j[0] > 0 && j[1] >= 0) {
                        iArr = j;
                    }
                }
                userInfoActivity.p = iArr[0];
                userInfoActivity.aa = iArr[1];
                userInfoActivity.bc.setText(UnitUtil.e(userInfoActivity.p, 1, 0) + " " + userInfoActivity.getResources().getString(R.string._2130841417_res_0x7f020f49) + " " + UnitUtil.e(userInfoActivity.aa, 1, 0) + " " + userInfoActivity.getResources().getString(R.string._2130841897_res_0x7f021129));
                userInfoActivity.c((UnitUtil.d((double) userInfoActivity.p, 1) * 100.0d) + UnitUtil.d((double) userInfoActivity.aa, 0), 0);
                return;
            }
            LogUtil.c("UIME_UserInfoActivity", "get hselect=", Float.valueOf(userInfomation.getWeight()));
            userInfoActivity.bc.setText(userInfoActivity.getResources().getString(R.string._2130837631_res_0x7f02007f, UnitUtil.e(userInfomation.getHeight() + 50, 1, 0)));
            userInfoActivity.c(userInfomation.getHeight() + 50, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i, UserInfoActivity userInfoActivity, UserInfomation userInfomation) {
            if (userInfoActivity.am) {
                int a2 = UnitUtil.a();
                if (a2 == 1) {
                    LogUtil.c("UIME_UserInfoActivity", "catty get wselect=", Float.valueOf(userInfomation.getWeight()));
                    userInfoActivity.ay.setText(userInfoActivity.getResources().getQuantityString(R.plurals._2130903105_res_0x7f030041, 1, UnitUtil.e(UnitUtil.b(userInfomation.getWeight()), 1, 1)));
                    userInfoActivity.c(userInfomation.getWeight(), i);
                    return;
                }
                b(i, userInfoActivity, userInfomation, a2 == 3);
                return;
            }
            b(i, userInfoActivity, userInfomation, UnitUtil.h());
        }

        private void b(int i, UserInfoActivity userInfoActivity, UserInfomation userInfomation, boolean z) {
            if (z) {
                LogUtil.c("UIME_UserInfoActivity", "get LB wselect=", Float.valueOf(userInfomation.getWeight()));
                userInfoActivity.ay.setText(userInfoActivity.getResources().getString(R.string._2130837633_res_0x7f020081, UnitUtil.e(UnitUtil.h(userInfomation.getWeight()), 1, 1)));
                userInfoActivity.c(userInfomation.getWeight(), i);
                return;
            }
            LogUtil.c("UIME_UserInfoActivity", "get wselect=", Float.valueOf(userInfomation.getWeight()));
            userInfoActivity.ay.setText(userInfoActivity.getResources().getString(R.string._2130837632_res_0x7f020080, UnitUtil.e(userInfomation.getWeight(), 1, 1)));
            userInfoActivity.c(userInfomation.getWeight(), i);
        }
    }

    /* loaded from: classes7.dex */
    static class d extends BaseHandler<UserInfoActivity> {
        public d(UserInfoActivity userInfoActivity) {
            super(userInfoActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: tY_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(UserInfoActivity userInfoActivity, Message message) {
            switch (message.what) {
                case 102:
                    userInfoActivity.i();
                    break;
                case 103:
                    userInfoActivity.v();
                    break;
                case 104:
                    userInfoActivity.b();
                    break;
                case 105:
                    userInfoActivity.a();
                    break;
                case 106:
                    userInfoActivity.o();
                    break;
                case 107:
                    if (userInfoActivity.ac) {
                        userInfoActivity.ai();
                        userInfoActivity.ac = false;
                        break;
                    }
                    break;
                case 108:
                    userInfoActivity.ag();
                    break;
            }
        }
    }

    /* loaded from: classes7.dex */
    static class a implements ICloudOperationResult<Boolean> {
        WeakReference<UserInfoActivity> d;

        public a(UserInfoActivity userInfoActivity) {
            this.d = new WeakReference<>(userInfoActivity);
        }

        @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void operationResult(Boolean bool, String str, boolean z) {
            UserInfoActivity userInfoActivity = this.d.get();
            if (userInfoActivity == null) {
                LogUtil.h("UIME_UserInfoActivity", "SetUserInfoListener activity null");
                return;
            }
            if (z) {
                bzw.e().setEvent(userInfoActivity.getApplicationContext(), String.valueOf(1100), new HashMap());
                CommonUtil.am(userInfoActivity.getApplicationContext());
            }
            userInfoActivity.a();
            if (userInfoActivity.ba != null) {
                userInfoActivity.ba.removeMessages(105);
            }
        }
    }

    private void c(boolean z) {
        LogUtil.a("UIME_UserInfoActivity", "setResultToH5 isChange ", Boolean.valueOf(z));
        Intent intent = new Intent();
        HashMap hashMap = new HashMap();
        hashMap.put("isChange", Boolean.valueOf(z));
        intent.putExtra("result", HiJsonUtil.e(hashMap));
        setResult(-1, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
