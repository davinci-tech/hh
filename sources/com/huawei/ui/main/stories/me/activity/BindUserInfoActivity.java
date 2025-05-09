package com.huawei.ui.main.stories.me.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceBindResultFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceExitAuthorizeSubUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceUnbindReq;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwnetworkmodel.NetworkStatusListener;
import com.huawei.login.huaweilogin.ThirdPartyLoginManager;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomButtonMenuDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.errortip.NetworkErrorTipBar;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.BindUserInfoActivity;
import com.huawei.up.model.UserInfomation;
import defpackage.ceo;
import defpackage.cff;
import defpackage.cfi;
import defpackage.cgs;
import defpackage.cgt;
import defpackage.cjx;
import defpackage.ckm;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cps;
import defpackage.cpw;
import defpackage.csb;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.cts;
import defpackage.ctv;
import defpackage.cxh;
import defpackage.dcz;
import defpackage.dks;
import defpackage.glz;
import defpackage.gmc;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.jbs;
import defpackage.jed;
import defpackage.kvt;
import defpackage.kxp;
import defpackage.mcv;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.qsj;
import defpackage.rgv;
import defpackage.sdk;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes9.dex */
public class BindUserInfoActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10332a;
    private HealthTextView aa;
    private String ab;
    private LinearLayout ac;
    private HealthRadioButton ad;
    private int af;
    private Map<String, Integer> ag;
    private HiUserInfo ah;
    private boolean ak;
    private String al;
    private int ao;
    private NetworkStatusListener ap;
    private byte[] aq;
    private CommonDialog21 ar;
    private HealthTextView as;
    private RelativeLayout at;
    private dcz aw;
    private ImageView ax;
    private RelativeLayout ay;
    private CustomTitleBar az;
    private String b;
    private HealthTextView ba;
    private RelativeLayout bc;
    private ImageView bd;
    private HealthTextView be;
    private HealthTextView bf;
    private ImageView bg;
    private ImageView bh;
    private HealthTextView bi;
    private ImageView bj;
    private ckm bk;
    private LinearLayout bm;
    private LinearLayout bn;
    private LinearLayout bo;
    private ctk bp;
    private LinearLayout bq;
    private HealthButton br;
    private int bs;
    private LinearLayout c;
    private String d;
    private HealthTextView e;
    private ContentValues f;
    private String g;
    private LinearLayout h;
    private HealthTextView i;
    private HealthTextView j;
    private int k;
    private Context l;
    private String m;
    private float n;
    private int p;
    private HealthRadioButton q;
    private HealthTextView r;
    private String s;
    private LinearLayout u;
    private HealthRadioButton v;
    private String w;
    private String x;
    private ImageView y;
    private HealthTextView z;
    private final WifiDeviceControlDataModelReq bw = new WifiDeviceControlDataModelReq();
    private final List<DeviceServiceInfo> bv = new ArrayList(16);
    private Dialog o = null;
    private boolean am = false;
    private String av = "";
    private String bb = "";
    private Handler bl = new b(this);
    private boolean ae = false;
    private boolean ai = false;
    private boolean aj = false;
    private AtomicBoolean an = new AtomicBoolean(false);
    private coy t = new coy();
    private String au = "";
    private WifiDeviceExitAuthorizeSubUserReq bu = new WifiDeviceExitAuthorizeSubUserReq();

    private int e(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        return i;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.l = this;
        this.ah = new HiUserInfo();
        this.ag = new HashMap(16);
        setContentView(R.layout.hw_show_bind_userinfo_activity);
        this.al = SharedPreferenceManager.b(this.l, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN);
        this.d = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1004);
        boolean t = t();
        w();
        q();
        u();
        c(t);
    }

    private boolean t() {
        Intent intent = getIntent();
        if (intent == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra("sn");
        this.au = stringExtra;
        LogUtil.a("BindUserInfoActivity", "initIntentView mSerialNumber:", cpw.d(stringExtra));
        this.ae = intent.getBooleanExtra("hasManager", false);
        this.ai = intent.hasExtra("hasManager");
        this.m = intent.getStringExtra("deviceSsid");
        this.n = intent.getFloatExtra("deviceWeight", -1.0f);
        if (intent.getSerializableExtra("deviceResis") instanceof ckm) {
            this.bk = (ckm) intent.getSerializableExtra("deviceResis");
        }
        try {
            this.aq = intent.getByteArrayExtra("mainHuid");
        } catch (ArrayIndexOutOfBoundsException unused) {
            LogUtil.b("BindUserInfoActivity", "onCreate: Get main huid failed.");
        }
        this.g = intent.getStringExtra("cloudDeviceId");
        if (intent.getBooleanExtra("isBleScale", false)) {
            this.am = true;
        }
        if (CompileParameterUtil.a("SUPPORT_WIFI_WEIGHT_DEVICE", false)) {
            String stringExtra2 = intent.getStringExtra("device");
            if (stringExtra2 != null && stringExtra2.equals("wifi")) {
                this.am = true;
            }
            if (intent.hasExtra("auth_device_id") && !TextUtils.isEmpty(intent.getStringExtra("auth_device_id"))) {
                this.b = intent.getStringExtra("auth_device_id");
            }
        }
        this.av = intent.getStringExtra("productId");
        boolean booleanExtra = intent.getBooleanExtra("isNfcConnect", false);
        this.ak = booleanExtra;
        LogUtil.a("BindUserInfoActivity", "onCreate getBoolean mIsNfcCommect = ", Boolean.valueOf(booleanExtra));
        ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
        if (contentValues != null) {
            this.av = contentValues.getAsString("productId");
            this.bb = contentValues.getAsString("uniqueId");
        } else {
            LogUtil.h("BindUserInfoActivity", "has not pass device info ");
        }
        if (TextUtils.isEmpty(this.bb) || TextUtils.isEmpty(this.av)) {
            LogUtil.h("BindUserInfoActivity", "device uniqueId or productId is null");
        }
        return intent.getBooleanExtra("show_gender_window", false);
    }

    private void c(boolean z) {
        LogUtil.a("BindUserInfoActivity", "isShowGenderWindow=", Boolean.valueOf(z));
        if (z) {
            aq();
        }
    }

    private void w() {
        this.az = (CustomTitleBar) findViewById(R.id.me_userInfo_titlebar);
        y();
        r();
        s();
        this.aa = (HealthTextView) findViewById(R.id.user_info_fragment_set_gender);
        this.ax = (ImageView) findViewById(R.id.user_info_fragment_wifi_device_complete_guide_go_next_img);
        this.bo = (LinearLayout) findViewById(R.id.user_info_fragment_wifi_device_complete_btn_layout);
        this.br = (HealthButton) findViewById(R.id.user_info_fragment_wifi_device_complete_btn);
        this.bn = (LinearLayout) findViewById(R.id.user_info_fragment_wifi_device_complete_guide_bottom_layout);
        this.bq = (LinearLayout) findViewById(R.id.user_info_fragment_wifi_device_complete_guide_go_next_layout);
        this.f10332a = (LinearLayout) findViewById(R.id.body_data_visit_layout);
        this.e = (HealthTextView) findViewById(R.id.bmi_value);
        this.h = (LinearLayout) findViewById(R.id.body_fat_rate_layout);
        this.j = (HealthTextView) findViewById(R.id.body_fat_rate_value);
        this.i = (HealthTextView) findViewById(R.id.body_fat_rate_value_unit);
        this.z = (HealthTextView) findViewById(R.id.hw_show_userinfo_gender_tip);
        this.as = (HealthTextView) findViewById(R.id.user_info_fragment_wifi_device_complete_guide_go_next_text);
        if (cpa.w(this.av) || cpa.r(this.av) || "b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(this.av)) {
            this.f10332a.setVisibility(0);
        }
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_show_settings_info_layout);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, linearLayout);
        this.y.setOnClickListener(this);
        v();
        al();
        this.x = getResources().getString(R$string.IDS_hw_show_set_default_gender_male);
        this.s = getResources().getString(R$string.IDS_hw_show_set_default_gender_female);
        this.w = getResources().getString(R$string.IDS_hw_me_userinfo_not_set);
        this.ab = getResources().getString(R$string.IDS_hw_me_userinfo_secret);
        String b2 = SharedPreferenceManager.b(this.l, Integer.toString(10000), "onboarding_skip_current_time");
        String b3 = SharedPreferenceManager.b(this.l, Integer.toString(10000), "onboarding_skip");
        if (!TextUtils.isEmpty(b2) && !"1".equals(b3)) {
            LogUtil.a("BindUserInfoActivity", "dot dismiss");
            SharedPreferenceManager.e(this.l, Integer.toString(10000), "rid_dot_dismiss", Integer.toString(1), new StorageParams());
        }
        this.az.setLeftButtonVisibility(8);
    }

    private void v() {
        if (this.am) {
            this.bq.setOnClickListener(this);
            this.br.setOnClickListener(this);
            am();
        }
    }

    private void al() {
        if (LanguageUtil.bc(this.l)) {
            this.bg.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.bh.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.bj.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.bd.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.ax.setBackgroundResource(R.drawable._2131429720_res_0x7f0b0958);
        } else {
            this.bg.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.bh.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.bj.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.bd.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.ax.setBackgroundResource(R.drawable._2131429722_res_0x7f0b095a);
        }
        if (LanguageUtil.b(this.l.getApplicationContext())) {
            this.aa.setWidth(nsn.c(this.l.getApplicationContext(), 60.0f));
            this.aa.setGravity(GravityCompat.START);
        }
    }

    private void s() {
        this.bg = (ImageView) findViewById(R.id.user_info_fragment_set_gender_image);
        this.bh = (ImageView) findViewById(R.id.user_info_fragment_set_height_image);
        this.bj = (ImageView) findViewById(R.id.user_info_fragment_set_weight_image);
        this.bd = (ImageView) findViewById(R.id.user_info_fragment_set_birthday_image);
        this.y = (ImageView) findViewById(R.id.hw_health_gender_not_set_prompt);
    }

    private void r() {
        this.be = (HealthTextView) findViewById(R.id.hw_show_userinfo_gender);
        this.bf = (HealthTextView) findViewById(R.id.hw_show_userinfo_height);
        this.bi = (HealthTextView) findViewById(R.id.hw_show_userinfo_weight);
        this.ba = (HealthTextView) findViewById(R.id.hw_show_userinfo_birthday);
    }

    private void y() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_show_userinfo_gender_layout);
        this.u = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.hw_show_userinfo_height_layout);
        this.ac = linearLayout2;
        linearLayout2.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.hw_show_userinfo_weight_layout);
        this.bm = linearLayout3;
        linearLayout3.setOnClickListener(this);
        LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.hw_show_userinfo_birthday_layout);
        this.c = linearLayout4;
        linearLayout4.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (this.an.compareAndSet(false, true)) {
            ao();
            HiHealthManager.d(this.l).fetchUserData(new c(this));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.aj) {
            LogUtil.a("BindUserInfoActivity", "the data of lite version from personal center");
            this.aj = false;
            ThirdPartyLoginManager.getInstance().queryUserInfo(new IBaseResponseCallback() { // from class: rey
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    BindUserInfoActivity.this.a(i, obj);
                }
            }, false);
        }
    }

    public /* synthetic */ void a(int i, Object obj) {
        Handler handler = this.bl;
        if (handler != null) {
            handler.sendEmptyMessage(106);
        }
    }

    private void u() {
        this.ap = new NetworkStatusListener(BaseApplication.getContext()) { // from class: com.huawei.ui.main.stories.me.activity.BindUserInfoActivity.4
            @Override // com.huawei.hwnetworkmodel.NetworkStatusListener
            public void onConnected() {
                LogUtil.a("BindUserInfoActivity", "network connected!");
                BindUserInfoActivity.this.q();
                BindUserInfoActivity.this.d(true);
            }

            @Override // com.huawei.hwnetworkmodel.NetworkStatusListener
            public void onDisconnected() {
                LogUtil.h("BindUserInfoActivity", "network disconnected!");
                BindUserInfoActivity.this.d(false);
            }
        };
    }

    private boolean ac() {
        if (CommonUtil.bu() || !Utils.i()) {
            return false;
        }
        return !CommonUtil.aa(this.l.getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final boolean z) {
        final NetworkErrorTipBar networkErrorTipBar = (NetworkErrorTipBar) findViewById(R.id.network_error_bar);
        if (networkErrorTipBar == null) {
            LogUtil.h("BindUserInfoActivity", "NetworkErrorTipBar is null");
        } else {
            runOnUiThread(new Runnable() { // from class: rfg
                @Override // java.lang.Runnable
                public final void run() {
                    BindUserInfoActivity.this.d(z, networkErrorTipBar);
                }
            });
        }
    }

    public /* synthetic */ void d(boolean z, NetworkErrorTipBar networkErrorTipBar) {
        if (z) {
            networkErrorTipBar.setVisibility(8);
        } else if (ac()) {
            networkErrorTipBar.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Object obj, boolean z) {
        if (z && (obj instanceof List)) {
            List list = (List) obj;
            if (list.size() > 0) {
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                this.ah = hiUserInfo;
                this.ag.put(CommonConstant.KEY_GENDER, Integer.valueOf(hiUserInfo.getGender()));
                this.ag.put("birthday", Integer.valueOf(this.ah.getBirthday()));
                this.ag.put("height", Integer.valueOf(this.ah.getHeight()));
                Handler handler = this.bl;
                if (handler != null) {
                    handler.sendEmptyMessage(102);
                    return;
                } else {
                    LogUtil.b("BindUserInfoActivity", "Handler is null");
                    b();
                    return;
                }
            }
            return;
        }
        Handler handler2 = this.bl;
        if (handler2 != null) {
            handler2.sendEmptyMessage(102);
        } else {
            LogUtil.b("BindUserInfoActivity", "Handler is null");
            b();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String d;
        if (ac()) {
            ap();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        dNa_(view);
        if (!this.am) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.br || view == this.bq) {
            sdk.c().a(false);
            if (!g()) {
                Context context = this.l;
                nrh.d(context, context.getResources().getString(R$string.IDS_hw_show_complete_privacy_wifi_tip));
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (cpa.ae(this.av) || cpa.au(this.av)) {
                ctk e2 = ctq.e(this.av);
                MeasurableDevice d2 = ceo.d().d(this.bb, false);
                if (e2 == null && d2 == null) {
                    at();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else if (e2 != null) {
                    d = e2.d();
                    c(d);
                    o();
                }
            }
            d = "";
            c(d);
            o();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void at() {
        if (!TextUtils.isEmpty(this.b)) {
            i(R$string.IDS_device_wifi_release_auth_msg);
        } else {
            i(R$string.IDS_device_wifi_device_does_not_exist);
        }
    }

    private void c(String str) {
        HashMap hashMap = new HashMap(16);
        d(str, hashMap);
        csf.a(hashMap, str, cts.b, this.l);
        if (!Utils.l()) {
            List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
            for (int i = 1; i < allUser.size(); i++) {
                HashMap hashMap2 = new HashMap(16);
                cfi cfiVar = allUser.get(i);
                hashMap2.put("id", LoginInit.getInstance(this.l).getAccountInfo(1011));
                hashMap2.put("uid", cfiVar.i());
                hashMap2.put(CommonConstant.KEY_GENDER, String.valueOf((int) (cfiVar.c() == 2 ? (byte) 1 : cfiVar.c())));
                hashMap2.put("age", String.valueOf(cgs.e(cfiVar.g(), cfiVar.a())));
                hashMap2.put("height", String.valueOf(cfiVar.d()));
                hashMap2.put("isDelete", String.valueOf(0));
                String e2 = csf.e(str, cfiVar.i());
                if (e2 != null && !TextUtils.isEmpty(e2)) {
                    hashMap2.put("bodyRes", csf.d(e2));
                }
                if (cfiVar.l() != 0.0f && !Float.isNaN(cfiVar.l())) {
                    hashMap2.put("currentWeight", String.valueOf(cfiVar.l()));
                }
                if (cgs.d(this.bb)) {
                    hashMap2.put("month", String.valueOf(cgs.a(cfiVar.g())));
                }
                csf.a(hashMap2, str, cfiVar.j(), this.l);
            }
            return;
        }
        LogUtil.a("BindUserInfoActivity", "Oversea is no child user");
    }

    private void d(String str, Map<String, String> map) {
        map.put("id", LoginInit.getInstance(this.l).getAccountInfo(1011));
        map.put("uid", String.valueOf(0));
        map.put(CommonConstant.KEY_GENDER, String.valueOf(this.ah.getGender() == 2 ? 1 : this.ah.getGender()));
        map.put("age", String.valueOf(cgs.e(this.ah.getBirthday(), this.ah.getAge())));
        map.put("height", String.valueOf(this.ah.getHeight()));
        map.put("isDelete", String.valueOf(0));
        String e2 = csf.e(str, String.valueOf(0));
        if (e2 != null && !TextUtils.isEmpty(e2)) {
            map.put("bodyRes", csf.d(e2));
        }
        if (this.ah.getWeight() != 0.0f && !Float.isNaN(this.ah.getWeight())) {
            map.put("currentWeight", String.valueOf(this.ah.getWeight()));
        }
        if (cgs.d(this.bb)) {
            map.put("month", String.valueOf(cgs.a(this.ah.getBirthday())));
        }
    }

    private void dNa_(View view) {
        if (view == this.u) {
            aq();
        }
        if (view == this.ac) {
            if (UnitUtil.h()) {
                ar();
            } else {
                l(0);
            }
        }
        if (view == this.bm) {
            l(1);
        }
        if (view == this.c) {
            an();
        }
        if (view == this.y) {
            ak();
        }
    }

    private void am() {
        if (cpa.ah(this.av) || cpa.r(this.av)) {
            e(true);
            return;
        }
        if (this.ai) {
            boolean m = cgt.e().m();
            if (this.ae && !m) {
                e(true);
                return;
            } else {
                e(false);
                return;
            }
        }
        e(true);
    }

    private void e(boolean z) {
        if (z) {
            this.bo.setVisibility(0);
            this.bn.setVisibility(8);
        } else {
            this.bo.setVisibility(8);
            this.bn.setVisibility(0);
        }
    }

    private void o() {
        if (cpa.ah(this.av)) {
            m();
            return;
        }
        if (this.ai) {
            p();
            return;
        }
        if (cpa.r(this.av)) {
            ae();
            l();
        } else if (TextUtils.isEmpty(this.b) && !"7a1063dd-0e0f-4a72-9939-461476ff0259".equals(this.av)) {
            finish();
            ai();
        } else {
            n();
        }
    }

    private void ai() {
        if (LanguageUtil.bc(this.l)) {
            overridePendingTransition(R.anim._2130772077_res_0x7f01006d, R.anim._2130772079_res_0x7f01006f);
        } else {
            overridePendingTransition(R.anim._2130772057_res_0x7f010059, R.anim._2130772061_res_0x7f01005d);
        }
    }

    private void m() {
        Bundle bundle = new Bundle();
        bundle.putString("view", "deviceManage");
        bundle.putString("productId", this.av);
        bundle.putString("deviceSsid", this.m);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.bb);
        contentValues.put("productId", this.av);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        bundle.putBoolean("isNfcConnect", this.ak);
        LogUtil.a("BindUserInfoActivity", "gotoDeviceManagePage put isNfcConnect is", Boolean.valueOf(this.ak));
        Intent intent = new Intent(this.l, (Class<?>) DeviceMainActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("config_mode", 5);
        ae();
        coy.b(this.av, this.bb);
        try {
            this.l.startActivity(intent);
            ai();
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BindUserInfoActivity", "gotoDeviceManagePage startActivity ActivityNotFoundException.");
        }
        finish();
    }

    private void l() {
        e("true");
        Bundle bundle = new Bundle();
        bundle.putString("view", "hagridDeviceWlanUseGuide");
        bundle.putString("productId", this.av);
        bundle.putString("cloudDeviceId", this.g);
        bundle.putByteArray("mainHuid", this.aq);
        bundle.putBoolean("isNfcConnect", this.ak);
        LogUtil.a("BindUserInfoActivity", "gotoHagridDeviceWlanUseGuideFragment put isNfcConnect is", Boolean.valueOf(this.ak));
        if (this.ai) {
            bundle.putInt(ParamConstants.Param.VIEW_TYPE, 12);
        } else {
            bundle.putInt(ParamConstants.Param.VIEW_TYPE, 14);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.bb);
        contentValues.put("productId", this.av);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        if (getIntent() != null) {
            bundle.putString("accountInfo", getIntent().getStringExtra("accountInfo"));
        }
        Intent intent = new Intent(this.l, (Class<?>) DeviceMainActivity.class);
        intent.putExtras(bundle);
        try {
            this.l.startActivity(intent);
            ai();
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BindUserInfoActivity", "gotoHagridDeviceWlanUseGuideFragment startActivity ActivityNotFoundException.");
        }
        finish();
    }

    private void e(String str) {
        SharedPreferenceManager.e(cpp.a(), String.valueOf(10000), "request_sync_data_dialog", str, new StorageParams());
    }

    public void c() {
        b();
        d(false);
    }

    private void ap() {
        nrh.b(this.l, R$string.IDS_network_connect_error);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if ("1".equals(this.al)) {
            this.bl.sendEmptyMessage(103);
        } else {
            LoginInit.getInstance(BaseApplication.getContext()).getUserInfo(new e(this));
        }
    }

    static class e implements IBaseResponseCallback {
        private WeakReference<BindUserInfoActivity> e;

        e(BindUserInfoActivity bindUserInfoActivity) {
            this.e = new WeakReference<>(bindUserInfoActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BindUserInfoActivity bindUserInfoActivity = this.e.get();
            if (bindUserInfoActivity == null) {
                LogUtil.h("BindUserInfoActivity", "mWeakActivity is null");
                return;
            }
            if (i == 0 && (obj instanceof HiUserInfo)) {
                bindUserInfoActivity.bb();
                bindUserInfoActivity.d(true);
            }
            if (bindUserInfoActivity.bl != null) {
                bindUserInfoActivity.bl.sendEmptyMessage(103);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bb() {
        int b2;
        int e2;
        if (this.ah == null) {
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1005);
        boolean z = false;
        UserInfomation userInfomation = new UserInfomation(0);
        if (!TextUtils.isEmpty(accountInfo) && TextUtils.isDigitsOnly(accountInfo)) {
            int b3 = b(accountInfo);
            if ((b3 == 0 || b3 == 1 || b3 == 2) && (e2 = e(b3)) != this.ah.getGender()) {
                this.ah.setGender(e2);
                userInfomation.setGender(Integer.valueOf(b3));
                z = true;
            }
        } else {
            LogUtil.a("BindUserInfoActivity", "updateAccountData gender is invalidate");
        }
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1006);
        if (!TextUtils.isEmpty(accountInfo2) && TextUtils.isDigitsOnly(accountInfo2) && (b2 = b(accountInfo2)) > 0 && b2 != this.ah.getBirthday()) {
            this.ah.setBirthday(b2);
            userInfomation.setBirthday(String.valueOf(b2));
        } else if (!z) {
            return;
        }
        b(userInfomation);
    }

    private void aq() {
        LogUtil.a("BindUserInfoActivity", "showGenderPickerDialog()");
        if (!glz.b()) {
            if (glz.e()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo(this, null);
            } else {
                gmc.aPf_(this);
            }
            this.aj = true;
            return;
        }
        View inflate = ((LayoutInflater) this.l.getSystemService("layout_inflater")).inflate(R.layout.hw_show_guest_settings_gender_view, (ViewGroup) null);
        ((RelativeLayout) inflate.findViewById(R.id.settings_gender_view_layout_secrect)).setVisibility(8);
        ((HealthTextView) inflate.findViewById(R.id.settings_gender_secrect_tip)).setVisibility(0);
        if (!dNb_(inflate)) {
            LogUtil.h("BindUserInfoActivity", "showGenderPickerDialog() dialog layout fail");
            this.o = null;
            return;
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.l);
        builder.a(getString(R$string.IDS_hw_show_set_gender)).czg_(inflate).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: ret
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        this.o = e2;
        e2.show();
    }

    private void an() {
        if (!glz.b()) {
            if (glz.e()) {
                ThirdPartyLoginManager.getInstance().openPersonalInfo(this, null);
            } else {
                gmc.aPf_(this);
            }
            this.aj = true;
            return;
        }
        if (this.bs <= 0) {
            this.bs = 1992;
            this.ao = 0;
            this.k = 1;
        }
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: rev
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public final void onDateSelected(int i, int i2, int i3) {
                BindUserInfoActivity.this.a(i, i2, i3);
            }
        }, new GregorianCalendar(this.bs, this.ao, this.k));
        int i = Calendar.getInstance().get(1);
        healthDatePickerDialog.e(i - 150, i);
        healthDatePickerDialog.show();
    }

    public /* synthetic */ void a(int i, int i2, int i3) {
        if (c(i, i2, i3)) {
            j(i, i2, i3);
        }
    }

    private boolean c(int i, int i2, int i3) {
        if (d(i, i2, i3)) {
            nrh.c(this.l.getApplicationContext(), this.l.getResources().getString(R$string.IDS_hw_show_no_choose_birthday_after_now));
            return false;
        }
        if (!CommonUtil.bu() && Utils.i() && !"1".equals(this.al)) {
            if (ac()) {
                ap();
                return false;
            }
            if ("1".equals(this.d)) {
                if (!b(i + 13, i2, i3)) {
                    nrh.c(this.l.getApplicationContext(), getString(R$string.IDS_hw_show_chosen_birthday_exceeds_kid_range, new Object[]{13}));
                    return false;
                }
            } else if (b(i + 18, i2, i3 + 1)) {
                nrh.c(this.l.getApplicationContext(), getString(R$string.IDS_hw_show_no_choose_birthday_after_eighteen));
                return false;
            }
        } else if (b(i + 13, i2, i3)) {
            nrh.c(this.l.getApplicationContext(), getString(R$string.IDS_hw_show_no_choose_birthday_after_eighteen));
            return false;
        }
        return true;
    }

    private void j(int i, int i2, int i3) {
        this.bs = i;
        this.ao = i2;
        this.k = i3;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.bs);
        stringBuffer.append(String.format(Locale.ENGLISH, "%02d", Integer.valueOf(this.ao + 1)));
        stringBuffer.append(String.format(Locale.ENGLISH, "%02d", Integer.valueOf(this.k)));
        d(b(stringBuffer.toString()), 3);
        i();
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

    private boolean b(int i, int i2, int i3) {
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

    private ArrayList<String> h() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (UnitUtil.h()) {
            int h = (int) UnitUtil.h(250.0d);
            for (int h2 = (int) UnitUtil.h(10.0d); h2 <= h + 1; h2++) {
                arrayList.add(getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, new Object[]{UnitUtil.e(h2, 1, 0)}));
            }
        } else {
            for (int i = 10; i < 251; i++) {
                arrayList.add(getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, new Object[]{UnitUtil.e(i, 1, 0)}));
            }
        }
        return arrayList;
    }

    private ArrayList<String> j() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 50; i < 251; i++) {
            arrayList.add(getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, new Object[]{UnitUtil.e(i, 1, 0)}));
        }
        return arrayList;
    }

    private void ar() {
        View inflate = getLayoutInflater().inflate(R.layout.health_healthdata_userinfo_dialog_inch_height_v9, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.height_ft_number_picker_v9);
        String[] strArr = new String[8];
        int i = 0;
        while (i < 8) {
            StringBuffer stringBuffer = new StringBuffer();
            int i2 = i + 1;
            stringBuffer.append(jed.b(i2, 1, 0));
            stringBuffer.append(" ");
            stringBuffer.append(getString(R$string.IDS_ft));
            strArr[i] = stringBuffer.toString();
            i = i2;
        }
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(7);
        HealthNumberPicker healthNumberPicker2 = (HealthNumberPicker) inflate.findViewById(R.id.height_inch_number_picker_v9);
        String[] strArr2 = new String[12];
        for (int i3 = 0; i3 < 12; i3++) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(jed.b(i3, 1, 0));
            stringBuffer2.append(" ");
            stringBuffer2.append(getString(R$string.IDS_ins));
            strArr2[i3] = stringBuffer2.toString();
        }
        healthNumberPicker2.setDisplayedValues(strArr2);
        healthNumberPicker2.setMinValue(0);
        healthNumberPicker2.setMaxValue(11);
        int[] iArr = {5, 7};
        HiUserInfo hiUserInfo = this.ah;
        if (hiUserInfo != null && hiUserInfo.getHeight() > 30) {
            int[] j = UnitUtil.j(this.ah.getHeight() / 100.0d);
            if (j[0] > 0 && j[1] >= 0) {
                iArr = j;
            }
        }
        healthNumberPicker.setValue(iArr[0] - 1);
        healthNumberPicker2.setValue(iArr[1]);
        dMY_(inflate, healthNumberPicker, healthNumberPicker2);
    }

    private void dMY_(View view, final HealthNumberPicker healthNumberPicker, final HealthNumberPicker healthNumberPicker2) {
        String string = getString(R$string.IDS_hw_show_set_height);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.l);
        builder.a(string).czg_(view).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: rfa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BindUserInfoActivity.this.dNj_(healthNumberPicker, healthNumberPicker2, view2);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: rff
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void dNj_(HealthNumberPicker healthNumberPicker, HealthNumberPicker healthNumberPicker2, View view) {
        if (ac()) {
            ap();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        this.p = healthNumberPicker.getValue() + 1;
        this.af = healthNumberPicker2.getValue();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getString(R$string.IDS_ft_string, new Object[]{jed.b(this.p, 1, 0)}));
        stringBuffer.append(" ");
        stringBuffer.append(getString(R$string.IDS_ins_string, new Object[]{UnitUtil.e(this.af, 1, 0)}));
        this.bf.setText(stringBuffer.toString());
        a((int) Math.rint((UnitUtil.d(this.p, 1) * 100.0d) + UnitUtil.d(this.af, 0)), 0);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void l(int i) {
        String string;
        if (i == 1) {
            string = getString(R$string.IDS_hw_health_show_healthdata_weight);
        } else {
            string = getString(R$string.IDS_hw_show_set_height);
        }
        View inflate = getLayoutInflater().inflate(R.layout.health_healthdata_userinfo_dialog_set, (ViewGroup) null);
        this.r = (HealthTextView) inflate.findViewById(R.id.hw_health_dialog_tips_userinfo);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.userinfo_number_picker_v9);
        if (i == 1) {
            this.r.setText(getString(R$string.IDS_hwh_user_data_weight_tips));
            ArrayList<String> h = h();
            healthNumberPicker.setDisplayedValues((String[]) h.toArray(new String[h.size()]));
            healthNumberPicker.setMinValue(0);
            healthNumberPicker.setMaxValue(h.size() - 1);
            if (UnitUtil.h()) {
                if (this.ah != null && ((int) Math.round(UnitUtil.h(r0.getWeight()))) >= ((int) UnitUtil.h(10.0d))) {
                    healthNumberPicker.setValue(((int) Math.round(UnitUtil.h(this.ah.getWeight()))) - ((int) UnitUtil.h(10.0d)));
                } else {
                    healthNumberPicker.setValue((int) UnitUtil.h(70.0d));
                }
            } else {
                HiUserInfo hiUserInfo = this.ah;
                if (hiUserInfo != null && Math.round(hiUserInfo.getWeight()) >= 10) {
                    healthNumberPicker.setValue(Math.round(this.ah.getWeight()) - 10);
                } else {
                    healthNumberPicker.setValue(70);
                }
            }
        } else {
            this.r.setText(getString(R$string.IDS_hwh_user_data_height_tips));
            ArrayList<String> j = j();
            healthNumberPicker.setDisplayedValues((String[]) j.toArray(new String[j.size()]));
            healthNumberPicker.setMinValue(0);
            healthNumberPicker.setMaxValue(j.size() - 1);
            HiUserInfo hiUserInfo2 = this.ah;
            if (hiUserInfo2 != null && hiUserInfo2.getHeight() >= 50) {
                healthNumberPicker.setValue(this.ah.getHeight() - 50);
            } else {
                healthNumberPicker.setValue(110);
            }
        }
        dMZ_(inflate, i, string, healthNumberPicker);
    }

    private void dMZ_(View view, final int i, String str, final HealthNumberPicker healthNumberPicker) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.l);
        builder.a(str).czg_(view).cze_(R$string.IDS_hw_common_ui_dialog_confirm, new View.OnClickListener() { // from class: rew
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BindUserInfoActivity.this.dNk_(i, healthNumberPicker, view2);
            }
        }).czc_(R$string.IDS_hw_show_cancel, new View.OnClickListener() { // from class: reu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BindUserInfoActivity.dNd_(view2);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void dNk_(int i, HealthNumberPicker healthNumberPicker, View view) {
        if (ac()) {
            ap();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (i == 1) {
            if (UnitUtil.h()) {
                this.bi.setText(getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, new Object[]{jed.b(healthNumberPicker.getValue() + ((int) UnitUtil.h(10.0d)), 1, 1)}));
                c((float) UnitUtil.i(healthNumberPicker.getValue() + ((int) UnitUtil.h(10.0d))));
            } else {
                this.bi.setText(getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, new Object[]{jed.b(healthNumberPicker.getValue() + 10.0d, 1, 1)}));
                d(healthNumberPicker.getValue() + 10, i);
            }
        } else {
            this.bf.setText(getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, new Object[]{jed.b(healthNumberPicker.getValue() + 50, 1, 0)}));
            d(healthNumberPicker.getValue() + 50, i);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dNd_(View view) {
        LogUtil.a("BindUserInfoActivity", "buildShowValueDialog click cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(final int i, final int i2) {
        gmn.c(i2, this.l, new IBaseResponseCallback() { // from class: rfb
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                BindUserInfoActivity.this.a(i2, i, i3, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, int i2, int i3, Object obj) {
        ao();
        this.bl.sendEmptyMessageDelayed(105, 5000L);
        UserInfomation userInfomation = new UserInfomation(0);
        if (i == 1) {
            float f = i2;
            userInfomation.setWeight(f);
            this.ah.setWeight(f);
            c(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "2");
        } else if (i == 0) {
            userInfomation.setHeight(i2);
            this.ah.setHeight(i2);
            c(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "1");
            qsj.d(i2);
        } else if (i == 2) {
            userInfomation.setGender(Integer.valueOf(e(i2)));
            j(i2);
        } else if (i == 3) {
            userInfomation.setBirthday(String.valueOf(i2));
            this.ah.setBirthday(i2);
            this.ah.setAge((HiDateUtil.c(System.currentTimeMillis()) / 10000) - (i2 / 10000));
            c(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "4");
        } else {
            LogUtil.a("BindUserInfoActivity", "saveValue type is invalidate");
        }
        this.ah.setCreateTime(System.currentTimeMillis());
        b(userInfomation);
        kvt.e(this.l).b(60000);
        e(this.bk);
    }

    private void j(int i) {
        HiUserInfo hiUserInfo = this.ah;
        if (hiUserInfo != null) {
            hiUserInfo.setGender(i);
        }
        SharedPreferenceManager.e(this.l, Integer.toString(10000), "hw_health_gender_value", Integer.toString(e(i)), new StorageParams(1));
        c(AnalyticsValue.HEALTH_MINE_PERSONAL_INFOR_2040006.value(), "3");
        String i2 = MultiUsersManager.INSTANCE.getMainUser().i();
        if (TextUtils.isEmpty(i2) || !i2.equals(MultiUsersManager.INSTANCE.getCurrentUser().i())) {
            return;
        }
        MultiUsersManager.INSTANCE.getMainUser().a((byte) (i == 0 ? 0 : 1));
    }

    private void b(UserInfomation userInfomation) {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).setUserInfo(userInfomation, new ICloudOperationResult() { // from class: res
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                BindUserInfoActivity.this.d((Boolean) obj, str, z);
            }
        });
    }

    public /* synthetic */ void d(Boolean bool, String str, boolean z) {
        if (z) {
            mcv.d(this.l).c(this.l, String.valueOf(1100), new HashMap(16));
            CommonUtil.am(this.l);
        }
        b();
        Handler handler = this.bl;
        if (handler != null) {
            handler.removeMessages(105);
        }
    }

    private void a(final int i, final int i2) {
        gmn.c(0, this.l, new IBaseResponseCallback() { // from class: rfo
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i3, Object obj) {
                BindUserInfoActivity.this.c(i, i2, i3, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, int i2, int i3, Object obj) {
        this.ah.setHeight(i);
        this.ah.setUnitType(i2);
        qsj.d(i);
        UserInfomation userInfomation = new UserInfomation(i2);
        userInfomation.setHeight(i);
        b(userInfomation);
    }

    private void c(final float f) {
        gmn.c(1, this.l, new IBaseResponseCallback() { // from class: req
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BindUserInfoActivity.this.c(f, i, obj);
            }
        });
    }

    public /* synthetic */ void c(float f, int i, Object obj) {
        this.ah.setWeight(f);
        UserInfomation userInfomation = new UserInfomation(0);
        userInfomation.setWeight(f);
        b(userInfomation);
    }

    private boolean dNb_(View view) {
        if (view == null) {
            return false;
        }
        this.v = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview1);
        this.q = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview2);
        this.ad = (HealthRadioButton) view.findViewById(R.id.settings_gender_secrect);
        this.ay = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout1);
        this.at = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout2);
        this.bc = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout_secrect);
        this.ay.setOnClickListener(new rgv(this, 1));
        this.at.setOnClickListener(new rgv(this, 0));
        this.bc.setOnClickListener(new rgv(this, 2));
        c(1);
        HiUserInfo hiUserInfo = this.ah;
        if (hiUserInfo != null) {
            c(hiUserInfo.getGender());
        }
        return true;
    }

    private void c(int i) {
        if (i == 0) {
            this.v.setChecked(false);
            this.q.setChecked(true);
            this.ad.setChecked(false);
        } else if (i == 1) {
            this.v.setChecked(true);
            this.q.setChecked(false);
            this.ad.setChecked(false);
        } else if (i == 2) {
            this.v.setChecked(false);
            this.q.setChecked(false);
            this.ad.setChecked(true);
        } else {
            this.v.setChecked(true);
            this.q.setChecked(false);
            this.ad.setChecked(false);
        }
    }

    private void a(int i) {
        if (i == -1) {
            this.be.setText(this.w);
            this.y.setVisibility(0);
            this.z.setVisibility(0);
            this.as.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
            this.bq.setClickable(false);
            return;
        }
        if (i == 0) {
            this.be.setText(this.s);
            this.y.setVisibility(8);
            this.z.setVisibility(8);
            this.as.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.bq.setClickable(true);
            return;
        }
        if (i == 1) {
            this.be.setText(this.x);
            this.y.setVisibility(8);
            this.z.setVisibility(8);
            this.as.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
            this.bq.setClickable(true);
            return;
        }
        if (i == 2) {
            this.be.setText(this.ab);
            this.y.setVisibility(8);
            this.z.setVisibility(0);
            this.as.setTextColor(getResources().getColor(R.color._2131299244_res_0x7f090bac));
            this.bq.setClickable(false);
            return;
        }
        this.be.setText(this.w);
        this.y.setVisibility(0);
    }

    public void b(int i) {
        c(i);
        if (ac()) {
            ap();
            return;
        }
        Dialog dialog = this.o;
        if (dialog != null) {
            dialog.dismiss();
            this.o = null;
        }
        d(i, 2);
        a(i);
        if (i == 2) {
            ak();
        }
    }

    private void f(int i) {
        if (i <= 0) {
            this.ba.setText(this.w);
            this.bs = 1992;
            this.ao = 0;
            this.k = 1;
            return;
        }
        this.bs = i / 10000;
        this.ao = ((i % 10000) / 100) - 1;
        this.k = i % 100;
        i();
    }

    private void i() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.bs, this.ao, this.k);
        this.ba.setText(UnitUtil.a(calendar.getTime(), 20));
    }

    private int b(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("BindUserInfoActivity", "NumberFormatException");
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ah() {
        this.be.setVisibility(0);
        this.bf.setVisibility(0);
        this.bi.setVisibility(0);
        this.ba.setVisibility(0);
        a(this.ah.getGender());
        f(this.ah.getBirthday());
        ag();
        af();
        e(this.bk);
        b();
    }

    private void ag() {
        int height = this.ah.getHeight();
        if (height <= 0) {
            e();
            return;
        }
        if (UnitUtil.h()) {
            LogUtil.a("BindUserInfoActivity", "enter UNIT_IMPERIAL");
            int[] iArr = {5, 7};
            if (height > 30) {
                iArr = UnitUtil.j(height / 100.0d);
            }
            this.bf.setText(UnitUtil.e(iArr[0], 1, 0) + " " + this.l.getResources().getString(R$string.IDS_ft) + " " + UnitUtil.e(iArr[1], 1, 0) + " " + this.l.getResources().getString(R$string.IDS_ins));
            return;
        }
        LogUtil.a("BindUserInfoActivity", "enter UNIT_CM_KG");
        this.bf.setText(getResources().getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(height, 1, 0)));
    }

    private void e() {
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: rfm
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                BindUserInfoActivity.this.b(i, (cfi) obj);
            }
        });
    }

    public /* synthetic */ void b(int i, cfi cfiVar) {
        int i2;
        if (cfiVar != null && i == 0) {
            cfi singleUserById = MultiUsersManager.INSTANCE.getSingleUserById(cfiVar.i());
            if (singleUserById != null) {
                i2 = singleUserById.d();
                h(i2);
            }
        } else {
            LogUtil.h("BindUserInfoActivity", "checkHeight : currentUser is null");
        }
        i2 = 0;
        h(i2);
    }

    private void h(final int i) {
        runOnUiThread(new Runnable() { // from class: rfr
            @Override // java.lang.Runnable
            public final void run() {
                BindUserInfoActivity.this.d(i);
            }
        });
    }

    public /* synthetic */ void d(int i) {
        HealthTextView healthTextView;
        if (i <= 0 && (healthTextView = this.bf) != null) {
            healthTextView.setText(this.w);
            return;
        }
        HiUserInfo hiUserInfo = this.ah;
        if (hiUserInfo != null) {
            hiUserInfo.setHeight(i);
        }
        ag();
    }

    private void af() {
        double d;
        float weight = this.ah.getWeight();
        float f = this.n;
        if (f > 0.0f) {
            c(f);
            weight = f;
        }
        if (weight <= 0.0f) {
            this.bi.setText(this.w);
            return;
        }
        if (UnitUtil.h()) {
            LogUtil.a("BindUserInfoActivity", "enter UNIT_IMPERIAL");
            this.bi.setText(getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_lb, UnitUtil.e(UnitUtil.h(weight), 1, 1)));
            return;
        }
        LogUtil.a("BindUserInfoActivity", "enter UNIT_CM_KG");
        try {
            d = Double.valueOf("" + weight).doubleValue();
        } catch (NumberFormatException unused) {
            LogUtil.b("BindUserInfoActivity", "refreshWeightText NumberFormatException");
            d = 0.0d;
        }
        this.bi.setText(getResources().getString(R$string.IDS_hw_show_set_weightvalue_with_unit_kg, UnitUtil.e(d, 1, 1)));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Dialog dialog = this.o;
        if (dialog != null) {
            dialog.dismiss();
            this.o = null;
        }
        if (this.bl != null) {
            this.bl = null;
        }
        NetworkStatusListener networkStatusListener = this.ap;
        if (networkStatusListener != null) {
            networkStatusListener.unregister(BaseApplication.getContext());
            this.ap = null;
        }
    }

    private void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.l, str, hashMap, 0);
    }

    private void ao() {
        CommonDialog21 commonDialog21;
        if (this.ar == null) {
            new CommonDialog21(this.l, R.style.app_update_dialogActivity);
            this.ar = CommonDialog21.a(this.l);
        }
        if (isFinishing() || (commonDialog21 = this.ar) == null) {
            return;
        }
        commonDialog21.e(getString(R$string.IDS_sns_waiting));
        this.ar.setCancelable(false);
        this.ar.a();
        this.ar.show();
        LogUtil.a("BindUserInfoActivity", "showLoadingDialog...mLoadingDialog.show()");
    }

    public void b() {
        CommonDialog21 commonDialog21;
        if (isFinishing() || (commonDialog21 = this.ar) == null) {
            return;
        }
        commonDialog21.cancel();
        this.ar = null;
        LogUtil.a("BindUserInfoActivity", "destroy mLoadingDialog");
    }

    private void ak() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.l);
        builder.b(getString(R$string.IDS_hw_health_show_common_dialog_title)).e(getString(R$string.IDS_hwh_home_gender_change_remind_content)).cyV_(getString(R$string.IDS_common_notification_know_tips), new View.OnClickListener() { // from class: rer
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    private void as() {
        LogUtil.a("BindUserInfoActivity", "showModifyConfirmDialog() enter");
        View inflate = View.inflate(this, R.layout.dialog_confirm_user_info, null);
        ((HealthCheckBox) inflate.findViewById(R.id.confirm_userinfo_dialog_box)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.me.activity.BindUserInfoActivity$$ExternalSyntheticLambda26
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BindUserInfoActivity.this.dNn_(compoundButton, z);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.l);
        builder.czg_(inflate).czd_(getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: rfe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.this.dNo_(view);
            }
        }).czf_(getString(R$string.IDS_hw_common_ui_dialog_confirm), new View.OnClickListener() { // from class: rfk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.this.dNp_(view);
            }
        });
        CustomViewDialog e2 = builder.e();
        e2.setCanceledOnTouchOutside(false);
        e2.show();
    }

    /* synthetic */ void dNn_(CompoundButton compoundButton, boolean z) {
        LogUtil.a("BindUserInfoActivity", "modifyConfirmDialog onClick not remind");
        StorageParams storageParams = new StorageParams();
        if (z) {
            SharedPreferenceManager.e(this.l, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree", "false", storageParams);
        } else {
            SharedPreferenceManager.e(this.l, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree", "true", storageParams);
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ void dNo_(View view) {
        d(this.ag.get(CommonConstant.KEY_GENDER).intValue(), 2);
        d(this.ag.get("birthday").intValue(), 3);
        d(this.ag.get("height").intValue(), 0);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNp_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean aa() {
        ArrayList<String> c2 = cjx.e().c();
        boolean z = (c2 == null || c2.size() == 0) ? false : true;
        if (cpa.h()) {
            z = true;
        }
        return z && ("false".equals(SharedPreferenceManager.b(this.l, Integer.toString(PrebakedEffectId.RT_HEARTBEAT), "health_userinfo_modify_agree")) ^ true);
    }

    private boolean ad() {
        Map<String, Integer> map = this.ag;
        return (map == null || map.isEmpty() || (this.ag.get(CommonConstant.KEY_GENDER).intValue() == this.ah.getGender() && this.ag.get("birthday").intValue() == this.ah.getBirthday() && this.ag.get("height").intValue() == this.ah.getHeight())) ? false : true;
    }

    private void aj() {
        LogUtil.a("BindUserInfoActivity", "showConfirmUserInfo() enter");
        if (ctq.e(this.av) == null && !this.ai && !cpa.ah(this.av) && !cpa.ab(this.av) && !"7a1063dd-0e0f-4a72-9939-461476ff0259".equals(this.av)) {
            i(R$string.IDS_device_wifi_release_auth_msg);
            LogUtil.b("BindUserInfoActivity", "showConfirmUserInfo wifi device is null");
            return;
        }
        String string = getString(R$string.IDS_device_exiting_binding);
        String string2 = getString(R$string.IDS_device_continue_to_improve);
        if (string.length() > 12 || string2.length() > 12) {
            g(R$string.IDS_device_confirm_user_info_back_prompt);
        } else {
            d(getString(R$string.IDS_device_confirm_user_info_back_prompt));
        }
    }

    private void g(int i) {
        CustomButtonMenuDialog b2 = new CustomButtonMenuDialog.Builder(this.l).c(R$string.IDS_hw_health_show_common_dialog_title).a(i).cyw_(R$string.IDS_device_exiting_binding, new View.OnClickListener() { // from class: rfh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.this.dNl_(view);
            }
        }).cyw_(R$string.IDS_device_continue_to_improve, new View.OnClickListener() { // from class: rfd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.dNe_(view);
            }
        }).b();
        if (b2.isShowing()) {
            return;
        }
        b2.show();
    }

    public /* synthetic */ void dNl_(View view) {
        f();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dNe_(View view) {
        LogUtil.a("BindUserInfoActivity", "showCustomButtonMenuDialog continue improve");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(String str) {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.l);
        builder.b(getString(R$string.IDS_hw_health_show_common_dialog_title));
        builder.e(str).cyR_(R$string.IDS_device_exiting_binding, new View.OnClickListener() { // from class: rfq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.this.dNm_(view);
            }
        }).cyU_(R$string.IDS_device_continue_to_improve, new View.OnClickListener() { // from class: rfp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.dNf_(view);
            }
        });
        builder.a().show();
    }

    public /* synthetic */ void dNm_(View view) {
        f();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void dNf_(View view) {
        LogUtil.a("BindUserInfoActivity", "showCustomTextAlertDialog continue improve");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        if (cpa.ae(this.av)) {
            LogUtil.a("BindUserInfoActivity", "exitingBindingDevice onClick hagrid or herm");
            a(ceo.d().d(this.bb, false));
        } else if (cpa.e(this.av, this.bb)) {
            LogUtil.a("BindUserInfoActivity", "exitingBindingDevice onClick wifi");
            ab();
        } else {
            LogUtil.a("BindUserInfoActivity", "exitingBindingDevice onClick ble");
            aw();
        }
    }

    private void ae() {
        Bundle bundle = new Bundle();
        bundle.putString("huid", this.ah.getHuid());
        bundle.putString("uid", null);
        bundle.putInt("height", this.ah.getHeight());
        bundle.putInt(CommonConstant.KEY_GENDER, this.ah.getGender());
        bundle.putInt("age", this.ah.getAge());
        bundle.putFloat("weight", this.ah.getWeight());
        bundle.putInt("birthday", this.ah.getBirthday());
        EventBus.d(new EventBus.b("weight_measure_set_user", bundle));
    }

    private void p() {
        LogUtil.a("BindUserInfoActivity", "has manager: ", Boolean.valueOf(this.ae));
        boolean m = cgt.e().m();
        LogUtil.c("BindUserInfoActivity", "gotoNextPage isResetWifi = ", Boolean.valueOf(m));
        if (this.ae && !m) {
            if (cpa.b(this.av, this.bb)) {
                m();
                return;
            } else {
                l();
                return;
            }
        }
        HagridDeviceBindResultFragment.setBindStatus(8);
        Bundle bundle = new Bundle();
        bundle.putString("view", "bindResultConfirm");
        bundle.putBoolean("isBindSuccess", true);
        bundle.putString("productId", this.av);
        bundle.putString("deviceSsid", this.m);
        bundle.putBoolean("isNfcConnect", this.ak);
        LogUtil.a("BindUserInfoActivity", "gotoNextPage HagridDeviceBindResultFragment put isNfcConnect is", Boolean.valueOf(this.ak));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.bb);
        contentValues.put("productId", this.av);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        Intent intent = new Intent(this.l, (Class<?>) DeviceMainActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("config_mode", 5);
        ae();
        try {
            this.l.startActivity(intent);
            ai();
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BindUserInfoActivity", "gotoNextPage startActivity ActivityNotFoundException.");
        }
        finish();
    }

    private void n() {
        if (ceo.d().d(this.bb, false) instanceof cxh) {
            nrh.b(this.l, R$string.IDS_device_wifi_my_qrcode_add_member_success);
        }
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.putExtra("arg1", "DeviceIntroduction");
        intent.putExtra("arg2", this.av);
        intent.putExtra("arg3", this.bb);
        intent.putExtra("sn", this.au);
        LogUtil.a("BindUserInfoActivity", "gotoDeviceActivity mSerialNumber:", cpw.d(this.au));
        if (cpa.aa(this.av)) {
            LogUtil.a("BindUserInfoActivity", "gotoDeviceActivity isHonourScale bindSuccess");
            intent.putExtra("isBindSuccess", true);
        }
        intent.putExtra("FROM_BIND_USER_INFO", true);
        ContentValues contentValues = new ContentValues();
        contentValues.put("productId", this.av);
        contentValues.put("uniqueId", this.bb);
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            this.l.startActivity(intent);
            ai();
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("BindUserInfoActivity", "gotoDeviceActivity startActivity ActivityNotFoundException.");
        }
        finish();
    }

    private boolean g() {
        boolean z;
        boolean z2 = false;
        if (this.ah.isGenderValid()) {
            z = true;
        } else {
            this.be.setTextColor(this.l.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
            z = false;
        }
        if (!this.ah.isBirthdayValid()) {
            this.ba.setTextColor(this.l.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
            z = false;
        }
        if (!this.ah.isHeightValid()) {
            this.bf.setTextColor(this.l.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
            z = false;
        }
        if (this.ah.isWeightValid()) {
            z2 = z;
        } else {
            this.bi.setTextColor(this.l.getResources().getColor(R.color._2131298790_res_0x7f0909e6));
        }
        LogUtil.a("BindUserInfoActivity", "checkUserDataDone  isCompleted = ", Boolean.valueOf(z2));
        return z2;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!TextUtils.isEmpty(this.b) || this.am || cpa.ah(this.av)) {
            aj();
        } else if (aa() && ad()) {
            as();
        } else {
            super.onBackPressed();
        }
    }

    private void i(int i) {
        LogUtil.a("BindUserInfoActivity", "showUnBindDeviceDialog in");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(i);
        builder.czC_(R$string.IDS_device_measureactivity_result_confirm, new View.OnClickListener() { // from class: rfn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BindUserInfoActivity.this.dNq_(view);
            }
        });
        NoTitleCustomAlertDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    public /* synthetic */ void dNq_(View view) {
        LogUtil.a("BindUserInfoActivity", "showUnBindDeviceDialog click positive button");
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(ckm ckmVar) {
        this.e.setText("--");
        this.j.setText("--");
        this.i.setVisibility(8);
        HiUserInfo hiUserInfo = this.ah;
        if (hiUserInfo == null || !hiUserInfo.isWeightValid() || !this.ah.isHeightValid()) {
            LogUtil.h("BindUserInfoActivity", "convertResisData mHiUserInfo is null");
            return;
        }
        if (ckmVar == null) {
            LogUtil.a("BindUserInfoActivity", "convertResisData bodyDatas is null");
            e(this.ah.getWeight(), this.ah.getHeight());
            return;
        }
        if (!x()) {
            LogUtil.h("BindUserInfoActivity", "convertResisData age is invalid");
            return;
        }
        double bodyFatRat = ckmVar.getBodyFatRat();
        double d = 0.0d;
        if (bodyFatRat > 0.0d) {
            LogUtil.a("BindUserInfoActivity", "the bodyFatRat is real");
            d = bodyFatRat / 100.0d;
        }
        double[] dArr = new double[ckmVar.o().length];
        System.arraycopy(ckmVar.o(), 0, dArr, 0, ckmVar.o().length);
        double[] d2 = cps.d(dArr);
        cps c2 = c(cpa.c(ckmVar), d2, ckmVar, d);
        b(c2.c());
        if (cps.b(d2)) {
            LogUtil.a("BindUserInfoActivity", "four electrode constructor invoke");
            e(c2.n());
        } else {
            LogUtil.a("BindUserInfoActivity", "eight electrode constructor invoke");
            e(c2.h());
        }
    }

    private cps c(boolean z, double[] dArr, ckm ckmVar, double d) {
        if (z) {
            double[] dArr2 = new double[ckmVar.i().length];
            System.arraycopy(ckmVar.i(), 0, dArr2, 0, ckmVar.i().length);
            double[] d2 = cps.d(dArr2);
            if (cps.b(dArr)) {
                return new cps(this.ah.getHeight(), ckmVar.getWeight(), cff.c((byte) this.ah.getGender()), this.ah.getAge(), this.ah.getBirthday(), 4, dArr, d, 1, d2);
            }
            return new cps(this.ah.getHeight(), ckmVar.getWeight(), cff.c((byte) this.ah.getGender()), this.ah.getAge(), this.ah.getBirthday(), 8, dArr, d, 2, d2);
        }
        if (cps.b(dArr)) {
            return new cps(this.ah.getHeight(), ckmVar.getWeight(), cff.c((byte) this.ah.getGender()), this.ah.getAge(), this.ah.getBirthday(), dArr[0], d);
        }
        return new cps(this.ah.getHeight(), ckmVar.getWeight(), cff.c((byte) this.ah.getGender()), this.ah.getAge(), this.ah.getBirthday(), dArr, d);
    }

    private void e(float f, int i) {
        double a2 = dks.a(f, i);
        if (a2 > 0.0d) {
            this.e.setText(jed.b(a2, 1, 1));
        } else {
            this.e.setText("--");
        }
    }

    private void e(double d) {
        if (d <= 3.0d) {
            this.j.setText("--");
            this.i.setVisibility(8);
            return;
        }
        if (Double.compare(d, 0.0d) > 0.0d) {
            if (LanguageUtil.ai(this)) {
                this.h.setLayoutDirection(1);
            }
            if (LanguageUtil.j(this) || LanguageUtil.p(this)) {
                this.j.setText(jed.b(d, 1, 1));
                this.i.setVisibility(0);
                return;
            } else {
                this.j.setText(jed.b(d, 2, 1));
                this.i.setVisibility(8);
                return;
            }
        }
        this.j.setText("--");
        this.i.setVisibility(8);
    }

    private void b(double d) {
        if (Double.compare(d, 0.0d) > 0.0d) {
            this.e.setText(jed.b(d, 1, 1));
        } else {
            e(this.ah.getWeight(), this.ah.getHeight());
        }
    }

    private boolean x() {
        int i = Utils.o() ? 65 : 80;
        int e2 = e(this.bs, this.ao, this.k);
        return e2 >= 18 && e2 <= i;
    }

    private int e(int i, int i2, int i3) {
        if (d(i, i2, i3)) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1) - i;
        int i5 = calendar.get(2) - i2;
        return (i5 < 0 || (i5 == 0 && calendar.get(5) - i3 < 0)) ? i4 - 1 : i4;
    }

    static class c implements HiCommonListener {
        WeakReference<BindUserInfoActivity> e;

        c(BindUserInfoActivity bindUserInfoActivity) {
            this.e = new WeakReference<>(bindUserInfoActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            LogUtil.a("BindUserInfoActivity", "FetchUserDataListener onSuccess");
            BindUserInfoActivity bindUserInfoActivity = this.e.get();
            if (bindUserInfoActivity != null) {
                bindUserInfoActivity.an.set(false);
                bindUserInfoActivity.d(i, obj, true);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("BindUserInfoActivity", "FetchUserDataListener onFailure errCode,", Integer.valueOf(i));
            BindUserInfoActivity bindUserInfoActivity = this.e.get();
            if (bindUserInfoActivity != null) {
                bindUserInfoActivity.an.set(false);
                bindUserInfoActivity.d(i, obj, false);
            }
        }
    }

    static class b extends BaseHandler<BindUserInfoActivity> {
        b(BindUserInfoActivity bindUserInfoActivity) {
            super(bindUserInfoActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dNr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BindUserInfoActivity bindUserInfoActivity, Message message) {
            switch (message.what) {
                case 102:
                    bindUserInfoActivity.k();
                    break;
                case 103:
                    bindUserInfoActivity.ah();
                    break;
                case 104:
                    bindUserInfoActivity.c();
                    break;
                case 105:
                    bindUserInfoActivity.b();
                    break;
                case 106:
                    bindUserInfoActivity.q();
                    break;
            }
        }
    }

    private void a(HealthDevice healthDevice) {
        this.aw = ResourceManager.e().d(this.av);
        ContentValues contentValues = new ContentValues();
        this.f = contentValues;
        contentValues.put("uniqueId", this.bb);
        this.f.put("productId", this.av);
        if (healthDevice != null) {
            if (healthDevice instanceof ctk) {
                ctk ctkVar = (ctk) healthDevice;
                if (!TextUtils.isEmpty(ctkVar.d())) {
                    LogUtil.a("BindUserInfoActivity", "getDeviceId :", ctkVar.d());
                    this.bp = ctkVar;
                    c(ctkVar);
                    return;
                }
            }
            if (healthDevice instanceof cxh) {
                LogUtil.a("BindUserInfoActivity", "device instanceof BleDevice");
                au();
                return;
            } else {
                au();
                return;
            }
        }
        LogUtil.h("BindUserInfoActivity", "device has unbind");
        au();
    }

    private void c(ctk ctkVar) {
        if (!ctv.d(BaseApplication.getContext())) {
            nrh.c(this, R.string.IDS_device_wifi_not_network);
            return;
        }
        if (ctkVar != null) {
            LogUtil.a("BindUserInfoActivity", "onClickUnBind");
            if (ctkVar.b().k() == 2) {
                LogUtil.a("BindUserInfoActivity", "onClickUnBind==01");
                a(this, ctkVar.d());
                return;
            } else {
                LogUtil.a("BindUserInfoActivity", "onClickUnBind==02");
                b(ctkVar.d(), false);
                return;
            }
        }
        LogUtil.h("BindUserInfoActivity", " onClickUnBind mWFDevice is null");
    }

    private void b(String str, final boolean z) {
        this.t.d(str, new ICloudOperationResult() { // from class: rep
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z2) {
                BindUserInfoActivity.this.a(z, (CloudCommonReponse) obj, str2, z2);
            }
        });
    }

    public /* synthetic */ void a(boolean z, CloudCommonReponse cloudCommonReponse, String str, boolean z2) {
        int i;
        String str2;
        LogUtil.a("BindUserInfoActivity", "onClickUnBind :", Boolean.valueOf(z2));
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = -1;
            str2 = "unknown error";
        }
        if (z2 || i == 112000000) {
            if (z) {
                LogUtil.a("BindUserInfoActivity", "unBindCloudDevice=operationResult=02");
                dNi_("send_reset_cmd", new Bundle());
            } else {
                LogUtil.a("BindUserInfoActivity", "unBindCloudDevice=operationResult=02");
                au();
            }
        }
        LogUtil.h("BindUserInfoActivity", " unBindCloudDevice error:", Integer.valueOf(i), ",resultDesc:", str2);
    }

    private void a(Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BindUserInfoActivity", "sendExitAuthorization deviceid is null");
            return;
        }
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("dltId", LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        this.bv.add(deviceServiceInfo);
        this.bw.setDeviceServiceInfo(this.bv);
        this.bw.setDevId(str);
        jbs.a(context).d(this.bw, new ICloudOperationResult() { // from class: rex
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                BindUserInfoActivity.this.a(str, (CloudCommonReponse) obj, str2, z);
            }
        });
    }

    public /* synthetic */ void a(String str, CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
        if (z) {
            j(str);
            LogUtil.a("BindUserInfoActivity", "sendExitAuthorization success ");
        } else if (cloudCommonReponse != null) {
            int intValue = cloudCommonReponse.getResultCode().intValue();
            LogUtil.h("BindUserInfoActivity", "errorCode = ", Integer.valueOf(intValue), " | errorDes = ", cloudCommonReponse.getResultDesc());
            if (intValue == 112000030) {
                au();
            } else {
                j(str);
            }
        }
    }

    private void au() {
        this.f.put("uniqueId", this.bb);
        this.f.put("productId", this.av);
        this.t.JX_(this.f, this.bp, this.aw);
        csb.a().e(this.av);
        finish();
    }

    private void j(String str) {
        LogUtil.a("BindUserInfoActivity", "subUserUnBind in");
        this.bu.setDevId(str);
        jbs.a(cpp.a()).e(this.bu, new ICloudOperationResult() { // from class: rfi
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                BindUserInfoActivity.this.b((CloudCommonReponse) obj, str2, z);
            }
        });
    }

    public /* synthetic */ void b(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        LogUtil.a("BindUserInfoActivity", "subUserUnBind :", Boolean.valueOf(z));
        if (z) {
            au();
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        if (i == 112000030 || i == 112000000) {
            au();
        }
        LogUtil.h("BindUserInfoActivity", " subUserUnBind error:", Integer.valueOf(i), ",resultDesc:", str2);
    }

    private void dNi_(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("BindUserInfoActivity", "processRestoreFactory illegal argments, eventType ", str);
            return;
        }
        str.hashCode();
        if (str.equals("send_reset_cmd")) {
            LogUtil.a("BindUserInfoActivity", "RESTORE_FACTORY_EVENT_SEND_RESET_COMMAND");
            EventBus.d(new EventBus.b("device_reset"));
        } else {
            LogUtil.h("BindUserInfoActivity", "processRestoreFactory unknow event_type");
        }
    }

    private void ab() {
        if (!ctv.d(BaseApplication.getContext())) {
            nrh.b(this, R$string.IDS_device_wifi_not_network);
            return;
        }
        MeasurableDevice d = ceo.d().d(this.bb, false);
        if (d instanceof ctk) {
            ctk ctkVar = (ctk) d;
            if (!TextUtils.isEmpty(ctkVar.d())) {
                LogUtil.a("BindUserInfoActivity", "getDeviceId :", ctkVar.d());
                this.bp = ctkVar;
            }
        }
        ctk ctkVar2 = this.bp;
        if (ctkVar2 != null) {
            if (ctkVar2.b().k() == 2) {
                c(this, this.bp.d());
                return;
            } else {
                a(this.bp.d());
                return;
            }
        }
        cpw.a(false, "BindUserInfoActivity", " onClickUnBind mWFDevice is null");
    }

    private void c(Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "BindUserInfoActivity", "sendExitAuthorizationWifi deviceid is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        HashMap hashMap = new HashMap(16);
        hashMap.put("dltId", LoginInit.getInstance(context).getAccountInfo(1011));
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        arrayList.add(deviceServiceInfo);
        this.bw.setDeviceServiceInfo(arrayList);
        this.bw.setDevId(str);
        jbs.a(context).d(this.bw, new ICloudOperationResult() { // from class: rfj
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                BindUserInfoActivity.this.c(str, (CloudCommonReponse) obj, str2, z);
            }
        });
    }

    public /* synthetic */ void c(String str, CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
        if (z) {
            h(str);
            cpw.a(false, "BindUserInfoActivity", "sendExitAuthorizationWifi success ");
            return;
        }
        if (cloudCommonReponse != null) {
            int intValue = cloudCommonReponse.getResultCode().intValue();
            cpw.a(false, "BindUserInfoActivity", "errorCode = " + intValue + " | errorDes = " + cloudCommonReponse.getResultDesc());
            if (intValue == 112000030) {
                av();
            } else {
                h(str);
            }
        }
    }

    private void h(String str) {
        WifiDeviceExitAuthorizeSubUserReq wifiDeviceExitAuthorizeSubUserReq = new WifiDeviceExitAuthorizeSubUserReq();
        wifiDeviceExitAuthorizeSubUserReq.setDevId(str);
        jbs.a(cpp.a()).e(wifiDeviceExitAuthorizeSubUserReq, new ICloudOperationResult() { // from class: rfc
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                BindUserInfoActivity.this.e((CloudCommonReponse) obj, str2, z);
            }
        });
    }

    public /* synthetic */ void e(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        cpw.a(false, "BindUserInfoActivity", "subUserUnBind :" + z);
        if (z) {
            av();
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        if (i == 112000030 || i == 112000000) {
            av();
        } else {
            runOnUiThread(new Runnable() { // from class: rez
                @Override // java.lang.Runnable
                public final void run() {
                    BindUserInfoActivity.this.a();
                }
            });
        }
        cpw.a(false, "BindUserInfoActivity", " subUserUnBind error:" + i + ",resultDesc:" + str2);
    }

    private void a(String str) {
        if (str == null) {
            cpw.a(false, "BindUserInfoActivity", " proccessUnbind deviceId is null");
            return;
        }
        WifiDeviceUnbindReq wifiDeviceUnbindReq = new WifiDeviceUnbindReq();
        wifiDeviceUnbindReq.setDevId(str);
        cpw.a(false, "BindUserInfoActivity", "onClickUnBind :" + cpw.a(str));
        jbs.a(cpp.a()).c(wifiDeviceUnbindReq, new ICloudOperationResult() { // from class: rfl
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str2, boolean z) {
                BindUserInfoActivity.this.a((CloudCommonReponse) obj, str2, z);
            }
        });
    }

    public /* synthetic */ void a(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        int i;
        String str2;
        cpw.a(false, "BindUserInfoActivity", "onClickUnBind :" + z);
        if (z) {
            av();
            return;
        }
        if (cloudCommonReponse != null) {
            i = cloudCommonReponse.getResultCode().intValue();
            str2 = cloudCommonReponse.getResultDesc();
        } else {
            i = Constants.CODE_UNKNOWN_ERROR;
            str2 = "unknown error";
        }
        if (i == 112000000) {
            av();
        } else {
            runOnUiThread(new Runnable() { // from class: reo
                @Override // java.lang.Runnable
                public final void run() {
                    BindUserInfoActivity.this.d();
                }
            });
        }
        cpw.a(false, "BindUserInfoActivity", " onClickUnBind error:" + i + ",resultDesc:" + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ax, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void d() {
        nrh.b(this, R$string.IDS_update_server_bussy);
    }

    private void av() {
        cpa.c(this.bb);
        cpw.a(false, "BindUserInfoActivity", " unBindLocalDevice currentDevice mBindProductId::" + this.av);
        if (this.bp != null) {
            SharedPreferenceManager.e(this, "wifi_weight_device", "support_multi_account_" + this.bp.d(), "", (StorageParams) null);
            SharedPreferenceManager.e(this, "wifi_weight_device", "health_is_wifi_add_member_first_" + this.bp.d(), "false", (StorageParams) null);
        }
        cjx.e().o(this.bb);
        ClaimWeightDataManager.INSTANCE.unRegisterCallBack(getClass().getSimpleName());
        SharedPreferenceManager.e(this, String.valueOf(10000), "pressure_calibrate_hrv_userinfo_" + this.bb, "", (StorageParams) null);
        SharedPreferenceManager.e(this, String.valueOf(10000), "wifi_scale_unit_change", "", (StorageParams) null);
        if (this.aw != null) {
            SharedPreferenceManager.e(this, String.valueOf(10000), "wife_device_send_event_to_kaka_" + this.aw.g(), "", (StorageParams) null);
        } else {
            LogUtil.h("BindUserInfoActivity", "unBindLocalDevice mProductInfo is null");
        }
        finish();
    }

    private void aw() {
        this.aw = ResourceManager.e().d(this.av);
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, this.aw.n().b());
        if (cjx.e().h(this.av)) {
            LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==isDeviceKitUniversal");
            if (cjx.e().h(this.av, this.bb)) {
                LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==unbindDeviceUniversalByUniqueId");
                cjx.e().e(this.av, this.bb);
                cjx.e().b(this.av, this.bb);
                if (cpa.ab(this.av)) {
                    LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==isHonourWeightDevice");
                    cpa.j(this.bb, "");
                    kxp.c().c(this.av, this.bb);
                }
                kxp.c().c(this.av, this.bb);
                ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap, 0);
                finish();
                return;
            }
            return;
        }
        LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==else");
        String str = this.av;
        if (str != null && cpa.ab(str)) {
            LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==prepare");
            z();
        }
        if (ceo.d().n(this.bb)) {
            LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==unbindDeviceByUniqueId");
            cjx.e().e(this.av, this.bb, -5);
            if (cpa.ab(this.av)) {
                LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==isHonourWeightDevice");
                cpa.j(this.bb, "");
                kxp.c().c(this.av, this.bb);
            }
            ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_UNBIND_SUCCEED_2060014.value(), hashMap, 0);
            setResult(102);
            finish();
        }
    }

    private void z() {
        dcz d = ResourceManager.e().d(this.av);
        if (d != null) {
            LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==prepare=productInfo");
            MeasureKit g = cjx.e().g(d.s());
            if (g == null) {
                LogUtil.h("BindUserInfoActivity", "kit is null");
                return;
            }
            MeasureController measureController = g.getMeasureController();
            Bundle bundle = new Bundle();
            bundle.putInt("type", -5);
            bundle.putString("productId", this.av);
            if (measureController != null) {
                LogUtil.a("BindUserInfoActivity", "switchDeviceUnbind==prepare=mControl");
                measureController.prepare(cjx.e().a(this.av), null, bundle);
                return;
            }
            return;
        }
        LogUtil.h("BindUserInfoActivity", "switchDeviceUnbind productInfo is null");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
