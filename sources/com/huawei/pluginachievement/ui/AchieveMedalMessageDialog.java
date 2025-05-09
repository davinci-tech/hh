package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.AchieveMessage;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.ui.AchieveMedalMessageDialog;
import com.huawei.ucd.helper.gles.Obj3DBufferLoadAider;
import com.huawei.ucd.medal.MedalBackType;
import com.huawei.ucd.medal.MedalView;
import com.huawei.ucd.medal.MedalViewCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.ixx;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mdn;
import defpackage.mdz;
import defpackage.meb;
import defpackage.meh;
import defpackage.mfg;
import defpackage.mfl;
import defpackage.mfp;
import defpackage.mkw;
import defpackage.mkx;
import defpackage.mla;
import defpackage.mlb;
import defpackage.mld;
import defpackage.mlg;
import defpackage.njw;
import defpackage.nqf;
import defpackage.nrf;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes9.dex */
public class AchieveMedalMessageDialog extends BaseActivity {
    private static ExecutorService d;

    /* renamed from: a, reason: collision with root package name */
    private Context f8397a;
    private HealthTextView aa;
    private HealthTextView ab;
    private ImageView ac;
    private LinearLayout ad;
    private HealthTextView ae;
    private HealthTextView af;
    private String aj;
    private UserProfileMgrApi am;
    private LinearLayout e;
    private HealthTextView f;
    private ImageView h;
    private HealthTextView i;
    private HiUserInfo j;
    private int l;
    private int m;
    private String n;
    private String o;
    private AchieveMessage r;
    private float s;
    private MedalView t;
    private float u;
    private FrameLayout w;
    private LinearLayout y;
    private ImageView z;
    private boolean c = false;
    private int k = 0;
    private Bitmap g = null;
    private String q = "";
    private String x = "";
    private String v = "";
    private String an = "";
    private String ah = "";
    private String ai = "";
    private String ag = "";
    private Handler b = new Handler(Looper.getMainLooper()) { // from class: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                return;
            }
            if (message.what == 1) {
                AchieveMedalMessageDialog.this.m();
            } else if (message.what == 2) {
                AchieveMedalMessageDialog.this.n();
            } else if (message.what == 5) {
                if (message.obj instanceof UserInfomation) {
                    AchieveMedalMessageDialog.this.d((UserInfomation) message.obj);
                } else {
                    LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "MSG_GET_USER_INFO_SUCCESS is not UserInfomation!");
                }
            } else if (message.what == 6) {
                LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "MSG_GET_USER_INFO_FAIL");
            } else if (message.what == 4) {
                AchieveMedalMessageDialog.this.l();
            } else if (message.what == 7) {
                AchieveMedalMessageDialog.this.h();
            } else {
                LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "wrong type message");
                return;
            }
            super.handleMessage(message);
        }
    };
    private Runnable p = new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog.5
        @Override // java.lang.Runnable
        public void run() {
            meh c = meh.c(BaseApplication.getContext());
            HashMap hashMap = new HashMap(2);
            hashMap.put("medalID", AchieveMedalMessageDialog.this.n);
            mcz d2 = c.d(9, hashMap);
            if (d2 == null) {
                LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "enter data is null");
                return;
            }
            if (d2 instanceof MedalConfigInfo) {
                MedalConfigInfo medalConfigInfo = (MedalConfigInfo) d2;
                AchieveMedalMessageDialog.this.aj = medalConfigInfo.acquireMessage();
                AchieveMedalMessageDialog.this.v = medalConfigInfo.acquireLightPromotionName();
                AchieveMedalMessageDialog.this.x = medalConfigInfo.acquireLightPromotionUrl();
                AchieveMedalMessageDialog.this.ai = medalConfigInfo.acquireMedalName();
                AchieveMedalMessageDialog.this.ag = medalConfigInfo.acquireLightDescription();
                AchieveMedalMessageDialog.this.m = medalConfigInfo.acquireMedalLabel();
                AchieveMedalMessageDialog.this.q = medalConfigInfo.acquireMedalType();
                int acquireMedalLevel = medalConfigInfo.acquireMedalLevel();
                AchieveMedalMessageDialog achieveMedalMessageDialog = AchieveMedalMessageDialog.this;
                achieveMedalMessageDialog.ah = mdn.d(achieveMedalMessageDialog.q, String.valueOf(acquireMedalLevel));
                LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "promotion_name: ", AchieveMedalMessageDialog.this.v, " promotion_url: ", AchieveMedalMessageDialog.this.x);
                LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "enter medalText = ", AchieveMedalMessageDialog.this.aj, " mToShareMedalName = ", AchieveMedalMessageDialog.this.ai, " mToShareMedalLightDesc = ", AchieveMedalMessageDialog.this.ag);
            }
            AchieveMedalMessageDialog.this.b.sendEmptyMessage(2);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        if (isLargerThanEmui910(CommonUtil.r()) || CommonUtil.az() || SystemInfo.h()) {
            setStatusBarColor();
        }
        setContentView(R.layout.achieve_medal_message_dialog);
        nqf.d(this.f8397a);
        cancelAdaptRingRegion();
        this.f8397a = this;
        d = Executors.newSingleThreadExecutor();
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        this.am = userProfileMgrApi;
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo != null) {
            this.an = mlb.a(userInfo.getName());
        }
        if (d()) {
            i();
            b();
            j();
            e();
            nqf.d(this.f8397a);
            overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
            return;
        }
        LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "initMedalGainTime fail, finish");
        finish();
    }

    private boolean d() {
        try {
            Intent intent = getIntent();
            if (intent == null) {
                return false;
            }
            Serializable serializableExtra = intent.getSerializableExtra("message");
            if (!(serializableExtra instanceof AchieveMessage)) {
                LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "mMessage invalid ");
                return false;
            }
            this.r = (AchieveMessage) serializableExtra;
            this.c = intent.getBooleanExtra("isMedalDialogInterceptorJump", false);
            this.o = this.r.getGainTime();
            this.q = this.r.acquireMedalType();
            this.l = this.r.acquireGainCount();
            if (TextUtils.isEmpty(this.o) || "0".equals(this.o)) {
                this.o = String.valueOf(System.currentTimeMillis());
            }
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "initMedalGainTime mMedalGainTime = ", this.o, " mMedal = ", this.r.acquireMedalId(), " count =", Integer.valueOf(this.l));
            if (mlb.n(this.q) || mlb.i(this.q)) {
                return true;
            }
            return !mlb.f(r1);
        } catch (ClassCastException e) {
            LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "AchieveMessageDialog e = ", e.getMessage());
            return false;
        }
    }

    private void i() {
        this.f = (HealthTextView) findViewById(R.id.medal_dialog_desc);
        this.h = (ImageView) findViewById(R.id.medal_dialog_pic_imageview);
        this.i = (HealthTextView) findViewById(R.id.medal_detail);
        setViewSafeRegion(false, (LinearLayout) findViewById(R.id.medal_message_dialog_title));
        this.i.setVisibility(4);
        ((HealthTextView) findViewById(R.id.medal_dialog_content)).setText(BaseApplication.getContext().getResources().getString(R.string._2130840764_res_0x7f020cbc));
        g();
        if (this.h == null || !EnvironmentInfo.k()) {
            return;
        }
        this.h.setLayoutParams(new FrameLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen._2131363832_res_0x7f0a07f8), getResources().getDimensionPixelSize(R.dimen._2131363832_res_0x7f0a07f8)));
    }

    private String e(String str, String str2, int i) {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "setGainTime name=", str, " type=", str2, " gainCount=", Integer.valueOf(i));
        if (i > 1 && mlb.n(str2) && !TextUtils.isEmpty(str)) {
            return getResources().getQuantityString(R.plurals._2130903193_res_0x7f030099, i, Integer.valueOf(i), str);
        }
        return BaseApplication.getContext().getResources().getString(R.string._2130840764_res_0x7f020cbc);
    }

    private void b() {
        MedalInfo medalInfo = mla.e().c(true).get(mdn.d(this.r.acquireMedalType(), String.valueOf(this.r.acquireMedalLevel())));
        if (medalInfo != null) {
            this.aj = medalInfo.getText();
            this.k = medalInfo.getEnableResId();
            this.ai = medalInfo.getText();
            this.ag = medalInfo.getContent();
        }
        this.s = ((getWindowManager().getDefaultDisplay().getWidth() / 2.0f) - 150.0f) + mld.d(this.f8397a, 16.0f);
        this.u = mld.d(this.f8397a, 50.0f) + 700.0f;
        String str = this.aj;
        if (str != null) {
            String replace = str.replace("/n", System.lineSeparator());
            this.aj = replace;
            this.f.setText(replace);
        } else {
            this.f.setText("");
        }
        String acquireMedalId = this.r.acquireMedalId();
        this.n = acquireMedalId;
        this.g = mlb.cko_(acquireMedalId, true, false);
        UserInfomation userInfo = this.am.getUserInfo();
        if (userInfo != null) {
            this.an = mlb.a(userInfo.getName());
        }
        if (this.g == null && medalInfo == null) {
            finish();
        } else {
            b(medalInfo);
        }
    }

    private void b(MedalInfo medalInfo) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("medalID", this.n);
        mcz d2 = meh.c(this.f8397a).d(9, hashMap);
        if (d2 instanceof MedalConfigInfo) {
            MedalConfigInfo medalConfigInfo = (MedalConfigInfo) d2;
            this.q = medalConfigInfo.acquireMedalType();
            if (!this.c) {
                MessageObject e = meh.c(this.f8397a).e(medalConfigInfo);
                if (mcv.d(this.f8397a).getAdapter() != null) {
                    LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "generate Medal Message");
                    e.setType(MessageConstant.ACQUIRE_MEDAL_TYPE);
                    mcv.d(this.f8397a).getAdapter().generateMessage(e);
                }
            } else {
                LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "initData: isMedalDialogInterceptorJump");
            }
        }
        if (medalInfo == null) {
            if (!d.isShutdown()) {
                LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "medalInfo is null");
                d.execute(this.p);
                d.shutdown();
            }
        } else {
            o();
        }
        String a2 = meb.a(this.n);
        if ("".equals(a2) || !mfl.e(this.f8397a, this.n)) {
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "STATIC IMG");
            d(medalInfo);
            return;
        }
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "3D medal");
        if (!a(a2)) {
            d(medalInfo);
        } else {
            k();
        }
    }

    private void e() {
        findViewById(R.id.medal_message_dialog_title_back_ImageView).setOnClickListener(new View.OnClickListener() { // from class: mir
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveMedalMessageDialog.this.chD_(view);
            }
        });
        ((HealthButton) findViewById(R.id.medal_dialog_share_button)).setOnClickListener(new View.OnClickListener() { // from class: mip
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveMedalMessageDialog.this.chE_(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                String replace = AchieveMedalMessageDialog.this.ag != null ? AchieveMedalMessageDialog.this.ag.replace("/n", System.lineSeparator()) : "";
                String str = AchieveMedalMessageDialog.this.ai != null ? AchieveMedalMessageDialog.this.ai : "";
                String str2 = AchieveMedalMessageDialog.this.o;
                intent.setClassName(AchieveMedalMessageDialog.this.f8397a, PersonalData.CLASS_NAME_PERSONAL_MEDAL_DETAIL);
                intent.putExtra("medal_res_id", AchieveMedalMessageDialog.this.n);
                intent.putExtra("medal_des_id", replace);
                intent.putExtra("medal_content_id", str);
                intent.putExtra("medal_type_level", AchieveMedalMessageDialog.this.ah);
                intent.putExtra("medal_gain_time", str2);
                intent.putExtra("medal_gain_count", AchieveMedalMessageDialog.this.l);
                intent.putExtra("medal_type", AchieveMedalMessageDialog.this.q);
                intent.putExtra("click_x", AchieveMedalMessageDialog.this.s);
                intent.putExtra("click_y", AchieveMedalMessageDialog.this.u);
                intent.putExtra("medal_obtain_id", "true");
                intent.putExtra("promotion_name", AchieveMedalMessageDialog.this.v);
                intent.putExtra("promotion_url", AchieveMedalMessageDialog.this.x);
                AchieveMedalMessageDialog.this.f8397a.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public /* synthetic */ void chD_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void chE_(View view) {
        if (PermissionUtil.c()) {
            s();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.f8397a, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.f8397a) { // from class: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog.2
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    AchieveMedalMessageDialog.this.s();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void g() {
        this.ad = (LinearLayout) findViewById(R.id.medal_theme_layout);
        this.y = (LinearLayout) findViewById(R.id.before_layout);
        this.z = (ImageView) findViewById(R.id.head_imageview);
        this.ae = (HealthTextView) findViewById(R.id.medal_desc);
        this.aa = (HealthTextView) findViewById(R.id.medal_get_date);
        this.ab = (HealthTextView) findViewById(R.id.medal_content);
        this.ac = (ImageView) findViewById(R.id.medal_pic_imageview);
        this.af = (HealthTextView) findViewById(R.id.name_textview);
        ImageView imageView = (ImageView) findViewById(R.id.vip_image);
        this.e = (LinearLayout) findViewById(R.id.account_layout);
        if (mkw.e()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        a();
        f();
    }

    private void f() {
        int i;
        AchieveMessage achieveMessage = this.r;
        if (achieveMessage == null) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "initShareBackgroundView mMessage is null!");
            return;
        }
        String acquireMedalId = achieveMessage.acquireMedalId();
        if (TextUtils.isEmpty(acquireMedalId)) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "initShareBackgroundView medalId isEmpty");
            return;
        }
        Bitmap ckp_ = mlb.ckp_(acquireMedalId);
        try {
            i = Integer.parseInt(acquireMedalId);
        } catch (NumberFormatException unused) {
            LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "initShareBackgroundView NumberFormatException");
            i = 0;
        }
        if (ckp_ == null || i <= 19) {
            this.ad.setBackgroundResource(R.drawable.share_background);
        } else {
            this.ad.setBackground(new BitmapDrawable((Resources) null, ckp_));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (!mcx.d(this.f8397a)) {
            mlg.e(this.f8397a);
            return;
        }
        Handler handler = this.b;
        if (handler != null) {
            handler.sendEmptyMessage(7);
        }
    }

    private void b(boolean z, final Map<String, Object> map) {
        if (z) {
            this.y.post(new Runnable() { // from class: mil
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveMedalMessageDialog.this.a(map);
                }
            });
            return;
        }
        Bitmap cgJ_ = mfp.cgJ_(this.y);
        if (cgJ_ != null) {
            mcx.cfN_(this.f8397a, cgJ_, AnalyticsValue.SUCCESSES_SHARE_1100011.value(), map);
        }
        if (mcv.e()) {
            ixx d2 = ixx.d();
            map.put("type", 12);
            d2.d(this.f8397a, AnalyticsValue.SUCCESSES_SHARE_1100011.value(), map, 0);
        }
    }

    public /* synthetic */ void a(Map map) {
        b(false, (Map<String, Object>) map);
    }

    public void a() {
        t();
    }

    private void t() {
        if ("1".equals(SharedPreferenceManager.b(this.f8397a, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "updateUserName: isThirdLogin = 1");
            q();
        } else {
            this.am.getUserInfo(new BaseResponseCallback() { // from class: mim
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    AchieveMedalMessageDialog.this.a(i, (UserInfomation) obj);
                }
            });
        }
    }

    public /* synthetic */ void a(int i, UserInfomation userInfomation) {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "updateUserName: errCode = ", Integer.valueOf(i));
        if (i != 0) {
            this.b.sendEmptyMessage(6);
            return;
        }
        if (userInfomation == null) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "get userinfo success but obtain null objData");
            return;
        }
        Message obtain = Message.obtain();
        obtain.obj = userInfomation;
        obtain.what = 5;
        this.b.sendMessage(obtain);
    }

    private void q() {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "updateUserNameFromLocal: fetchUserData");
        HiHealthManager.d(this.f8397a).fetchUserData(new HiCommonListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog.1
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "fetchUserData onSuccess");
                AchieveMedalMessageDialog achieveMedalMessageDialog = AchieveMedalMessageDialog.this;
                achieveMedalMessageDialog.j = mkx.ckg_(obj, achieveMedalMessageDialog.j, AchieveMedalMessageDialog.this.b, 4);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "fetchUserData onFailure");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "setUserNameFromLocal");
        HiUserInfo hiUserInfo = this.j;
        if (hiUserInfo == null) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "setUserNameFromLocal: mHiUserInfo is null");
            return;
        }
        this.af.setText(TextUtils.isEmpty(hiUserInfo.getName()) ? mcx.b() : this.j.getName());
        String d2 = d(this.j.getHeadImgUrl());
        if (!TextUtils.isEmpty(d2)) {
            Bitmap cIe_ = nrf.cIe_(this.f8397a, d2);
            if (cIe_ != null) {
                this.z.setImageBitmap(cIe_);
                return;
            } else {
                LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "setUserNameFromLocal: localHeadBitmap is null");
                return;
            }
        }
        this.z.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "setUserNameFromLocal: localHeadImgPath is empty");
    }

    private String d(String str) {
        String str2;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "getHeadImageFromLocal: untrusted -> " + str);
            return "";
        }
        String[] split = str.split("/");
        if (split.length > 0) {
            str2 = this.f8397a.getFilesDir() + "/photos/headimage/" + split[split.length - 1];
        } else {
            str2 = this.f8397a.getFilesDir() + "/photos/headimage/" + str;
        }
        if (new File(str2).exists()) {
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "accountmigrate: getHeadImageFromLocal file.exists() yes");
            return str2;
        }
        LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "accountmigrate: getHeadImageFromLocal file.exists() no");
        File[] listFiles = new File(this.f8397a.getFilesDir() + "/photos/headimage").listFiles();
        if (listFiles == null || listFiles.length <= 0) {
            return str2;
        }
        try {
            return listFiles[listFiles.length - 1].getCanonicalPath();
        } catch (IOException unused) {
            LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "getHeadImageFromLocal get local path fail: IOException");
            return str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(UserInfomation userInfomation) {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "handleWhenGetUserInfoSuccess");
        if (userInfomation == null) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "handleWhenGetUserInfoSuccess: userinfo is null");
            return;
        }
        String name = userInfomation.getName();
        if (TextUtils.isEmpty(name)) {
            this.af.setText(mcx.b());
            String accountName = new UpApi(this.f8397a).getAccountName();
            if (accountName != null) {
                c(accountName);
            }
        } else {
            this.af.setText(name);
            c(name);
        }
        String picPath = userInfomation.getPicPath();
        if (!TextUtils.isEmpty(picPath)) {
            Bitmap cIe_ = nrf.cIe_(this.f8397a, picPath);
            if (cIe_ != null) {
                this.z.setImageBitmap(cIe_);
                return;
            } else {
                LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "handleWhenGetUserInfoSuccess: headBitmap is null");
                return;
            }
        }
        this.z.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "handleWhenGetUserInfoSuccess: headImgPath is empty");
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(this.an)) {
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "userName not empty ");
        } else {
            this.an = mlb.a(str);
        }
    }

    private void j() {
        if (TextUtils.isEmpty(this.n)) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "initShareMedalView mMedalId isEmpty");
            return;
        }
        String c = c(this.aj, this.q, this.l, this.o);
        if (c == null) {
            c = "";
        }
        this.aa.setText(c);
        if (mlb.cko_(this.n, true, false) != null) {
            this.ac.setImageBitmap(mlb.cko_(this.n, true, false));
        } else {
            this.ac.setImageResource(this.k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        HealthTextView healthTextView;
        if (mfg.c(1) && mcx.e()) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(4);
        }
        String str = this.ag;
        String replace = str != null ? str.replace("/n", System.lineSeparator()) : "";
        String str2 = this.ai;
        String str3 = str2 != null ? str2 : "";
        if (TextUtils.isEmpty(replace)) {
            replace = e(str3, this.q, this.l);
        }
        if (TextUtils.isEmpty(str3) && (healthTextView = this.f) != null && healthTextView.getText() != null) {
            str3 = this.f.getText().toString();
        }
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "initShareMedalContent name ", replace, " content ", str3);
        this.ae.setText(replace);
        this.ab.setText(str3);
        if (mcv.d(this.f8397a).getAdapter() != null) {
            Map<String, Object> hashMap = new HashMap<>(10);
            hashMap.put("name", str3);
            hashMap.put("className", this.q);
            b(true, hashMap);
        }
    }

    private void d(MedalInfo medalInfo) {
        Bitmap bitmap = this.g;
        if (bitmap != null) {
            this.h.setImageBitmap(bitmap);
        } else if (medalInfo == null) {
            finish();
        } else {
            this.h.setImageResource(this.k);
        }
    }

    private void k() {
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.medal_dialog_view);
        relativeLayout.setAlpha(0.0f);
        relativeLayout.setVisibility(0);
        HandlerExecutor.d(new Runnable() { // from class: miq
            @Override // java.lang.Runnable
            public final void run() {
                relativeLayout.animate().alpha(1.0f).setDuration(500L).setListener(null);
            }
        }, 800L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Bitmap bitmap = this.g;
        if (bitmap == null) {
            this.h.setImageResource(this.k);
        } else {
            this.h.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        HealthTextView healthTextView;
        if (this.aj == null || this.f == null || (healthTextView = this.i) == null) {
            return;
        }
        healthTextView.setVisibility(0);
        String replace = this.aj.replace("/n", System.lineSeparator());
        this.aj = replace;
        this.f.setText(replace);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.medal_dialog_content);
        if (healthTextView2 != null) {
            healthTextView2.setText(e(this.ai, this.q, this.l));
        }
        o();
    }

    private void o() {
        HashMap hashMap = new HashMap(5);
        hashMap.put("id", this.n);
        hashMap.put("name", this.ai);
        hashMap.put("type", this.q);
        hashMap.put("label", Integer.valueOf(this.m));
        LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "setBiAnalytics id ", this.n, " mToShareMedalName ", this.ai, " mMedalType ", this.q, " mMedalLabel ", Integer.valueOf(this.m));
        String value = AnalyticsValue.MEDAL_MESSAGE_1100016.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "enter onDestroy");
        MedalView medalView = this.t;
        if (medalView != null) {
            medalView.c();
        }
        if (this.g != null) {
            this.g = null;
        }
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "enter onResume");
        super.onResume();
        MedalView medalView = this.t;
        if (medalView != null) {
            medalView.onResume();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "enter onPause");
        super.onPause();
        MedalView medalView = this.t;
        if (medalView != null) {
            medalView.onPause();
        }
    }

    public Bitmap chC_(String str) {
        String c = CommonUtil.c(str);
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "getBitmapFromPath: file not exists");
            return null;
        }
        if (!new File(c).exists()) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "getBitmapFromPath: file not exists");
            return null;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(c);
        if (decodeFile == null) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", " bitmap is null");
        }
        return decodeFile;
    }

    private String c(String str, String str2, int i, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return "";
        }
        try {
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "setGainTime medal_time = ", str3);
            return a(str, str2, i, UnitUtil.a(new Date(Long.parseLong(str3)), 20));
        } catch (NumberFormatException unused) {
            LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "setGainTime NumberFormatException");
            return "";
        }
    }

    private String a(String str, String str2, int i, String str3) {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "getMedalDate name=", str, " type=", str2, " gainCount=", Integer.valueOf(i), " timeStr=", str3);
        if (i > 1 && mlb.n(str2)) {
            return getResources().getQuantityString(R.plurals._2130903194_res_0x7f03009a, i, str3, Integer.valueOf(i));
        }
        return String.format(getResources().getString(R.string._2130840762_res_0x7f020cba), str3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [com.huawei.pluginachievement.ui.AchieveMedalMessageDialog] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    public boolean a(String str) {
        FileInputStream fileInputStream;
        InputStream inputStream;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        String str2 = CommonUtil.c(str) + File.separator;
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "initMedal: path is empty");
            return false;
        }
        String str3 = str2 + "texture.jpg";
        String str4 = str2 + "medal.tST";
        String str5 = str2 + "medal.nXYZ";
        String str6 = str2 + "medal.vXYZ";
        ?? r6 = " nxyzPath = ";
        ?? r7 = str5;
        LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "tstPath = ", str4, " nxyzPath = ", r7, " vxyzPath = ", str6, " picPath = ", str3);
        Bitmap chC_ = chC_(str3);
        if (chC_ == null) {
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "initMedal bitmap is null!");
            return false;
        }
        FileInputStream fileInputStream2 = null;
        try {
            try {
                r6 = new FileInputStream(str4);
                try {
                    FileInputStream fileInputStream3 = new FileInputStream(str5);
                    try {
                        r7 = new FileInputStream(str6);
                        try {
                            chA_(r6, fileInputStream3, r7, chC_);
                            b(r6, fileInputStream3, r7);
                            return true;
                        } catch (FileNotFoundException unused) {
                            fileInputStream2 = fileInputStream3;
                            obj5 = r6;
                            obj6 = r7;
                            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "close InputStream is exception");
                            r6 = obj5;
                            r7 = obj6;
                            b(r6, fileInputStream2, r7);
                            return false;
                        } catch (Exception unused2) {
                            fileInputStream2 = fileInputStream3;
                            obj3 = r6;
                            obj4 = r7;
                            LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "3D view load exception!");
                            r6 = obj3;
                            r7 = obj4;
                            b(r6, fileInputStream2, r7);
                            return false;
                        } catch (OutOfMemoryError unused3) {
                            fileInputStream2 = fileInputStream3;
                            obj = r6;
                            obj2 = r7;
                            LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "Obj3DBufferLoadAider.loadFromInputStream OutOfMemoryError");
                            r6 = obj;
                            r7 = obj2;
                            b(r6, fileInputStream2, r7);
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream2 = fileInputStream3;
                            fileInputStream = fileInputStream2;
                            fileInputStream2 = r6;
                            inputStream = r7;
                            b(fileInputStream2, fileInputStream, inputStream);
                            throw th;
                        }
                    } catch (FileNotFoundException unused4) {
                        r7 = 0;
                    } catch (Exception unused5) {
                        r7 = 0;
                    } catch (OutOfMemoryError unused6) {
                        r7 = 0;
                    } catch (Throwable th2) {
                        th = th2;
                        r7 = 0;
                    }
                } catch (FileNotFoundException unused7) {
                    obj6 = null;
                    obj5 = r6;
                } catch (Exception unused8) {
                    obj4 = null;
                    obj3 = r6;
                } catch (OutOfMemoryError unused9) {
                    obj2 = null;
                    obj = r6;
                } catch (Throwable th3) {
                    th = th3;
                    r7 = 0;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (FileNotFoundException unused10) {
            obj5 = null;
            obj6 = null;
        } catch (Exception unused11) {
            obj3 = null;
            obj4 = null;
        } catch (OutOfMemoryError unused12) {
            obj = null;
            obj2 = null;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            inputStream = null;
            b(fileInputStream2, fileInputStream, inputStream);
            throw th;
        }
    }

    private void chA_(InputStream inputStream, InputStream inputStream2, InputStream inputStream3, Bitmap bitmap) {
        njw.c(new WeakReference(new mdz()));
        new Obj3DBufferLoadAider().c(this.f8397a, inputStream, inputStream2, inputStream3, new AnonymousClass6(bitmap, inputStream, inputStream2, inputStream3));
    }

    /* renamed from: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog$6, reason: invalid class name */
    public class AnonymousClass6 implements Obj3DBufferLoadAider.OnLoadListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ InputStream f8398a;
        final /* synthetic */ InputStream b;
        final /* synthetic */ InputStream c;
        final /* synthetic */ Bitmap e;

        AnonymousClass6(Bitmap bitmap, InputStream inputStream, InputStream inputStream2, InputStream inputStream3) {
            this.e = bitmap;
            this.c = inputStream;
            this.f8398a = inputStream2;
            this.b = inputStream3;
        }

        @Override // com.huawei.ucd.helper.gles.Obj3DBufferLoadAider.OnLoadListener
        public void onLoadOK(Obj3DBufferLoadAider.d dVar) {
            if (dVar == null) {
                LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", " onLoadOK result is null");
                final AchieveMedalMessageDialog achieveMedalMessageDialog = AchieveMedalMessageDialog.this;
                achieveMedalMessageDialog.runOnUiThread(new Runnable() { // from class: min
                    @Override // java.lang.Runnable
                    public final void run() {
                        AchieveMedalMessageDialog.this.p();
                    }
                });
            } else {
                LogUtil.c("PluginAchievement_AchieveMedalMessageDialog", "onLoadOK result = ", dVar.toString());
                AchieveMedalMessageDialog.this.chB_(dVar, this.e);
                AchieveMedalMessageDialog.this.b(this.c, this.f8398a, this.b);
            }
        }

        @Override // com.huawei.ucd.helper.gles.Obj3DBufferLoadAider.OnLoadListener
        public void onLoadFailed(String str) {
            AchieveMedalMessageDialog.this.b.sendEmptyMessage(1);
            AchieveMedalMessageDialog.this.b(this.c, this.f8398a, this.b);
            LogUtil.h("PluginAchievement_AchieveMedalMessageDialog", "loadFromInputStream onLoadFailed msg = ", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chB_(final Obj3DBufferLoadAider.d dVar, final Bitmap bitmap) {
        runOnUiThread(new Runnable() { // from class: mii
            @Override // java.lang.Runnable
            public final void run() {
                AchieveMedalMessageDialog.this.chF_(bitmap, dVar);
            }
        });
    }

    public /* synthetic */ void chF_(Bitmap bitmap, Obj3DBufferLoadAider.d dVar) {
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.medal_message_dialog);
        this.w = frameLayout;
        if (frameLayout == null) {
            return;
        }
        MedalView medalView = this.t;
        if (medalView != null) {
            frameLayout.removeView(medalView);
        }
        MedalView medalView2 = new MedalView(this.f8397a);
        this.t = medalView2;
        medalView2.setLayerType(2, null);
        this.t.setBackContent(new String[]{StringUtils.c((Object) this.an), mlb.e(this.o)}, MedalBackType.ModelType.SOLID_CIRCLE, MedalBackType.ColorType.GOLD);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Object systemService = this.f8397a.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (systemService instanceof WindowManager) {
            ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        }
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "loadFromInputStream setTexture.");
        this.t.setTexture(bitmap, new AnonymousClass8());
        this.t.setTouchRect(new Rect(0, mld.d(this.f8397a, 100.0f), 1080, displayMetrics.heightPixels - mld.d(this.f8397a, 250.0f)));
        this.t.setObjData(dVar);
        this.t.setAnimatorScaleX(0.02f, 0.08f);
        this.t.setAnimatorScaleY(0.02f, 0.08f);
        this.t.setAnimatorRotationY(0.0f, 360.0f);
        this.t.setAnimatorPositionX(this.s, displayMetrics.widthPixels / 2.0f);
        this.t.setAnimatorPositionY(this.u, (displayMetrics.heightPixels / 3.0f) + mld.d(this.f8397a, 50.0f));
        this.t.setAnimatorDuration(1000L);
        this.w.addView(this.t);
        this.t.e();
    }

    /* renamed from: com.huawei.pluginachievement.ui.AchieveMedalMessageDialog$8, reason: invalid class name */
    public class AnonymousClass8 implements MedalViewCallback {
        AnonymousClass8() {
        }

        @Override // com.huawei.ucd.medal.MedalViewCallback
        public void onResponse(int i, String str) {
            LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "loadFromInputStream Id=", AchieveMedalMessageDialog.this.n, " code=", Integer.valueOf(i), " res=", str);
            if (i == -1) {
                final AchieveMedalMessageDialog achieveMedalMessageDialog = AchieveMedalMessageDialog.this;
                achieveMedalMessageDialog.runOnUiThread(new Runnable() { // from class: mio
                    @Override // java.lang.Runnable
                    public final void run() {
                        AchieveMedalMessageDialog.this.p();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        LogUtil.a("PluginAchievement_AchieveMedalMessageDialog", "showWithAnimatorFail!");
        c();
        r();
    }

    private void c() {
        MedalView medalView;
        FrameLayout frameLayout = this.w;
        if (frameLayout == null || (medalView = this.t) == null) {
            return;
        }
        frameLayout.removeView(medalView);
        this.t = null;
    }

    private void r() {
        ImageView imageView = this.h;
        if (imageView == null) {
            return;
        }
        Bitmap bitmap = this.g;
        if (bitmap == null) {
            imageView.setImageResource(this.k);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(InputStream inputStream, InputStream inputStream2, InputStream inputStream3) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "tst InputStream close exception!");
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException unused2) {
                LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "nxy InputStream close exception!");
            }
        }
        if (inputStream3 != null) {
            try {
                inputStream3.close();
            } catch (IOException unused3) {
                LogUtil.b("PluginAchievement_AchieveMedalMessageDialog", "xyz InputStream close exception!");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
