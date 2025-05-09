package com.huawei.pluginachievement.ui;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MedalInfo;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.ui.AchieveMedalDetailActivity;
import com.huawei.pluginachievement.ui.views.SameTypeMedalView;
import com.huawei.ucd.helper.gles.Obj3DBufferLoadAider;
import com.huawei.ucd.medal.MedalBackType;
import com.huawei.ucd.medal.MedalView;
import com.huawei.ucd.medal.MedalViewCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import com.huawei.up.api.UpApi;
import com.huawei.up.model.UserInfomation;
import defpackage.ixx;
import defpackage.mct;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mdz;
import defpackage.meb;
import defpackage.meh;
import defpackage.mer;
import defpackage.met;
import defpackage.mfg;
import defpackage.mfl;
import defpackage.mfp;
import defpackage.mih;
import defpackage.mkw;
import defpackage.mkx;
import defpackage.mla;
import defpackage.mlb;
import defpackage.mld;
import defpackage.mlg;
import defpackage.njw;
import defpackage.nqf;
import defpackage.nrf;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes9.dex */
public class AchieveMedalDetailActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private float f8394a;
    private RelativeLayout aa;
    private SameTypeMedalView ab;
    private RelativeLayout ad;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ah;
    private ImageView ai;
    private int aj;
    private UserProfileMgrApi ak;
    private HealthTextView an;
    private int ao;
    private HealthTextView aq;
    private HealthButton at;
    private int av;
    private ImageView b;
    private LinearLayout c;
    private LinearLayout d;
    private float e;
    private Context f;
    private HealthTextView g;
    private ImageView k;
    private int l;
    private Bitmap m;
    private HiUserInfo n;
    private CustomTitleBar o;
    private FrameLayout p;
    private String q;
    private HealthTextView r;
    private LinearLayout t;
    private MedalView x;
    private String z;
    private float as = 60.0f;
    private int h = 250;
    private int ag = 100;
    private String i = "";
    private String am = "";
    private String ap = "";
    private String s = "";
    private String y = "";
    private String ar = "";
    private String w = "";
    private String u = "";
    private String al = "";
    private String v = "";
    private String ac = "";
    private int au = 0;
    private Handler j = new e(this);

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void loadApplicationTheme() {
    }

    static class e extends BaseHandler<AchieveMedalDetailActivity> {
        e(AchieveMedalDetailActivity achieveMedalDetailActivity) {
            super(achieveMedalDetailActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: chw_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveMedalDetailActivity achieveMedalDetailActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                if (achieveMedalDetailActivity == null || achieveMedalDetailActivity.isFinishing()) {
                    return;
                }
                achieveMedalDetailActivity.q();
                achieveMedalDetailActivity.a();
                return;
            }
            if (i == 1) {
                LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", "Obj3DBufferLoadAider.OnLoadListener load failed");
                return;
            }
            if (i == 2) {
                if (message.obj instanceof UserInfomation) {
                    achieveMedalDetailActivity.c((UserInfomation) message.obj);
                }
            } else if (i == 3) {
                LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "MSG_GET_USER_INFO_FAIL");
            } else {
                if (i != 4) {
                    return;
                }
                achieveMedalDetailActivity.s();
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isLargerThanEmui910(CommonUtil.r()) || CommonUtil.az() || SystemInfo.h()) {
            setStatusBarColor();
        }
        getWindow().setNavigationBarColor(getResources().getColor(R.color._2131298069_res_0x7f090715));
        setContentView(R.layout.achieve_medal_single_detail);
        this.ak = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);
        cancelAdaptRingRegion();
        this.f = this;
        m();
        nqf.d(this.f);
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
    }

    private int c(String str, boolean z) {
        MedalInfo medalInfo = mla.e().c(true).get(str);
        if (medalInfo == null) {
            return 0;
        }
        if (z) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "medalInfo getEnableResId is ", Integer.valueOf(medalInfo.getEnableResId()));
            return medalInfo.getEnableResId();
        }
        return medalInfo.getDisableResId();
    }

    private void g() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.detail_view);
        this.ad = relativeLayout;
        setViewSafeRegion(false, relativeLayout);
        this.aq = (HealthTextView) findViewById(R.id.medal_get_time);
        this.av = getWindowManager().getDefaultDisplay().getWidth();
        k();
    }

    private void k() {
        Object systemService = this.f.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "WindowManager is null!");
            return;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        this.ao = displayMetrics.widthPixels;
        this.aj = displayMetrics.heightPixels;
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "width=", Integer.valueOf(this.av), "mWindowWidth=", Integer.valueOf(this.ao), "mWindowHeight=", Integer.valueOf(this.aj));
    }

    private void o() {
        final meh c2 = meh.c(BaseApplication.getContext());
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveMedalDetailActivity.2
            @Override // java.lang.Runnable
            public void run() {
                MedalInfo medalInfo;
                HashMap hashMap = new HashMap(2);
                hashMap.put("medalID", AchieveMedalDetailActivity.this.ar);
                mcz d = c2.d(9, hashMap);
                mcz d2 = c2.d(8, hashMap);
                if ((d instanceof MedalConfigInfo) && (d2 instanceof MedalLocation)) {
                    MedalConfigInfo medalConfigInfo = (MedalConfigInfo) d;
                    MedalLocation medalLocation = (MedalLocation) d2;
                    AchieveMedalDetailActivity.this.ap = medalConfigInfo.acquireMedalName();
                    AchieveMedalDetailActivity.this.w = medalLocation.acquireGainedCount() >= 1 ? "true" : "false";
                    AchieveMedalDetailActivity.this.u = medalLocation.acquireMedalGainedTime();
                    AchieveMedalDetailActivity.this.l = medalLocation.acquireGainedCount();
                    AchieveMedalDetailActivity.this.al = medalConfigInfo.acquireMedalType() + "_" + medalConfigInfo.acquireMedalLevel();
                    AchieveMedalDetailActivity.this.y = medalConfigInfo.acquireMedalType();
                    if ("false".equals(AchieveMedalDetailActivity.this.w)) {
                        AchieveMedalDetailActivity.this.v = medalConfigInfo.acquireGrayPromotionName();
                        AchieveMedalDetailActivity.this.ac = medalConfigInfo.acquireGrayPromotionUrl();
                        AchieveMedalDetailActivity.this.s = medalConfigInfo.acquireGrayDescription();
                    } else {
                        AchieveMedalDetailActivity.this.v = medalConfigInfo.acquireLightPromotionName();
                        AchieveMedalDetailActivity.this.ac = medalConfigInfo.acquireLightPromotionUrl();
                        AchieveMedalDetailActivity.this.s = medalConfigInfo.acquireLightDescription();
                    }
                    if ("true".equals(AchieveMedalDetailActivity.this.w) && mlb.a().contains(AchieveMedalDetailActivity.this.ar) && (medalInfo = mla.e().c(true).get(AchieveMedalDetailActivity.this.al)) != null) {
                        AchieveMedalDetailActivity.this.ap = medalInfo.getText();
                        AchieveMedalDetailActivity.this.s = medalInfo.getContent();
                    }
                    if (!TextUtils.isEmpty(AchieveMedalDetailActivity.this.z)) {
                        AchieveMedalDetailActivity.this.d(medalConfigInfo.acquireMedalLabel(), medalLocation.acquireSecondTabDesc(), AchieveMedalDetailActivity.this.z);
                    }
                    AchieveMedalDetailActivity.this.j.sendEmptyMessage(0);
                }
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str, String str2) {
        HashMap hashMap = new HashMap(8);
        hashMap.put("click", 1);
        hashMap.put("name", this.ap);
        hashMap.put("className", str);
        hashMap.put("type", this.y);
        hashMap.put("label", Integer.valueOf(i));
        hashMap.put("from", str2);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_REPORT_1100010.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (!TextUtils.isEmpty(this.s) && !TextUtils.isEmpty(this.ap)) {
            String replace = this.s.replace("/n", System.lineSeparator());
            this.s = replace;
            this.af.setText(replace);
            this.g.setText(this.ap);
        }
        String e2 = e(this.s, this.y, this.l, this.u);
        this.q = e2;
        if (!TextUtils.isEmpty(e2)) {
            this.aq.setText(this.q);
        }
        b(this.v, this.ac);
        a(this.w, this.al);
        l();
        c();
    }

    private void m() {
        g();
        Intent intent = getIntent();
        if (intent != null) {
            chp_(intent);
            UserInfomation userInfo = this.ak.getUserInfo();
            if (userInfo != null) {
                this.am = mlb.a(userInfo.getName());
            }
            this.as = mld.d(this.f, this.as);
            this.h = mld.d(this.f, this.h);
            this.ag = mld.d(this.f, this.ag);
            LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "positionY=", Float.valueOf(this.as));
            this.aa = (RelativeLayout) findViewById(R.id.detail_layout);
            this.at = (HealthButton) findViewById(R.id.promotion_button);
            this.af = (HealthTextView) findViewById(R.id.medal_desc);
            this.g = (HealthTextView) findViewById(R.id.medal_content);
            this.q = e(this.s, this.y, this.l, this.u);
            CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.medal_titlebar);
            this.o = customTitleBar;
            customTitleBar.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
            this.o.setRightButtonOnClickListener(new View.OnClickListener() { // from class: mik
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AchieveMedalDetailActivity.this.cht_(view);
                }
            });
            this.o.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: mij
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AchieveMedalDetailActivity.this.chu_(view);
                }
            });
            this.o.setLeftButtonVisibility(0);
            this.o.setRightButtonVisibility(0);
            if (LanguageUtil.bc(this.f)) {
                this.o.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
                this.o.setRightButtonDrawable(nrz.cKn_(this.f, R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
            } else {
                this.o.setLeftButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
                this.o.setRightButtonDrawable(BaseApplication.getContext().getResources().getDrawable(R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
            }
            this.o.setTitleTextColor(getResources().getColor(R.color._2131297371_res_0x7f09045b));
            this.o.setTitleText(BaseApplication.getContext().getResources().getString(R.string._2130840757_res_0x7f020cb5));
            b(this.v, this.ac);
            a(this.w, this.al);
            d();
            return;
        }
        finish();
    }

    public /* synthetic */ void cht_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else if (PermissionUtil.c()) {
            p();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.f, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.f) { // from class: com.huawei.pluginachievement.ui.AchieveMedalDetailActivity.1
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    AchieveMedalDetailActivity.this.p();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void chu_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "onNewIntent");
        if (TextUtils.isEmpty(this.ar) || mer.e(1000)) {
            return;
        }
        int intExtra = intent.getIntExtra("from", 0);
        String stringExtra = intent.getStringExtra("real_from");
        if (intExtra == 0 || !"4".equals(stringExtra)) {
            return;
        }
        String stringExtra2 = intent.getStringExtra("medal_res_id");
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "onNewIntent medalId:", this.ar, " newMedalId:", stringExtra2);
        if (this.ar.equals(stringExtra2)) {
            return;
        }
        finish();
        mcv.d(BaseApplication.getContext()).c(BaseApplication.getContext(), stringExtra2, stringExtra);
    }

    private void chp_(Intent intent) {
        String stringExtra = intent.getStringExtra("medal_res_id");
        this.ar = stringExtra;
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        met.c(this.ar);
        this.s = intent.getStringExtra("medal_des_id");
        this.ap = intent.getStringExtra("medal_content_id");
        this.w = intent.getStringExtra("medal_obtain_id");
        this.u = intent.getStringExtra("medal_gain_time");
        this.l = intent.getIntExtra("medal_gain_count", 0);
        this.al = intent.getStringExtra("medal_type_level");
        this.y = intent.getStringExtra("medal_type");
        this.v = intent.getStringExtra("promotion_name");
        this.ac = intent.getStringExtra("promotion_url");
        this.z = intent.getStringExtra("real_from");
        if (intent.getIntExtra("from", 0) != 0) {
            o();
        } else if (!intent.getBooleanExtra("KEY_IS_SAME_TYPE_MEDAL_VIEW", false)) {
            a();
        }
        this.f8394a = intent.getFloatExtra("click_x", (this.av / 2.0f) - 150.0f) + mld.d(this.f, 16.0f);
        this.e = intent.getFloatExtra("click_y", 700.0f) + mld.d(this.f, 65.0f);
    }

    private void b(String str, final String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            this.at.setVisibility(8);
            return;
        }
        if (!str2.contains("pullfrom=medal") && str2.contains("?")) {
            str2 = str2 + "&pullfrom=medal";
        }
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "initPromotionButton url:", str2);
        this.at.setVisibility(0);
        this.at.setText(str);
        this.at.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalDetailActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (str2.startsWith("huaweihealth") || str2.startsWith("huaweischeme") || str2.startsWith("geo:") || str2.startsWith("mailto:") || str2.startsWith(KakaConstants.SCHEME_TEL)) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                        intent.setFlags(268435456);
                        intent.setData(Uri.parse(str2));
                        AchieveMedalDetailActivity.this.chn_(intent);
                        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "jump to scheme view");
                    } catch (ActivityNotFoundException e2) {
                        LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", e2.getMessage());
                    }
                } else {
                    Intent intent2 = new Intent();
                    intent2.setClassName(BaseApplication.getAppPackage(), "com.huawei.operation.activity.WebViewActivity");
                    intent2.putExtra("url", str2);
                    intent2.putExtra("EXTRA_BI_ID", "");
                    intent2.putExtra("EXTRA_BI_NAME", "");
                    intent2.putExtra("EXTRA_BI_SOURCE", "Medal_Promotion");
                    AchieveMedalDetailActivity.this.startActivity(intent2);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chn_(Intent intent) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        intent.setComponent(new ComponentName(resolveActivity.activityInfo.packageName, resolveActivity.activityInfo.name));
        startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "onConfigurationChanged()");
        k();
        a(this.w, this.al);
    }

    private void h() {
        RelativeLayout relativeLayout;
        MedalView medalView;
        if (!n() || (relativeLayout = this.aa) == null || (medalView = this.x) == null) {
            return;
        }
        relativeLayout.removeView(medalView);
        this.x = null;
    }

    private void f() {
        ImageView imageView = this.k;
        if (imageView != null) {
            imageView.setImageResource(R.color._2131296971_res_0x7f0902cb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "showWithAnimatorFail!");
        i();
        r();
    }

    private void i() {
        MedalView medalView;
        RelativeLayout relativeLayout = this.aa;
        if (relativeLayout == null || (medalView = this.x) == null) {
            return;
        }
        relativeLayout.removeView(medalView);
        this.x = null;
    }

    private void r() {
        ImageView imageView = this.k;
        if (imageView == null) {
            return;
        }
        Bitmap bitmap = this.m;
        if (bitmap == null) {
            imageView.setImageResource(this.au);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    private void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.p = (FrameLayout) findViewById(R.id.medal_framelayout);
        this.k = (ImageView) findViewById(R.id.medal_pic_imageview);
        Bitmap cko_ = mlb.cko_(this.ar, false, false);
        this.m = mlb.cko_(this.ar, true, false);
        if (TextUtils.equals(str, "false")) {
            chs_(cko_, str2);
            return;
        }
        this.au = c(str2, true);
        String a2 = meb.e(mct.b(this.f, "_medalTextureDownload"), this.ar, this.y) ? meb.a(this.ar) : "";
        if (n() || "".equals(a2) || !mfl.e(this.f, this.ar)) {
            LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "STATIC IMG");
            h();
            if (!meb.d(this.ar)) {
                meh.c(BaseApplication.getContext()).d(this.ar);
            }
            Bitmap bitmap = this.m;
            if (bitmap == null) {
                this.k.setImageResource(this.au);
                return;
            } else {
                this.k.setImageBitmap(bitmap);
                return;
            }
        }
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "3D medal");
        f();
        if (!a(a2)) {
            Bitmap bitmap2 = this.m;
            if (bitmap2 == null) {
                this.k.setImageResource(this.au);
                return;
            } else {
                this.k.setImageBitmap(bitmap2);
                return;
            }
        }
        this.ad.setAlpha(0.0f);
        this.ad.setVisibility(0);
        new Handler().postDelayed(new Runnable() { // from class: mie
            @Override // java.lang.Runnable
            public final void run() {
                AchieveMedalDetailActivity.this.e();
            }
        }, 800L);
        t();
    }

    public /* synthetic */ void e() {
        this.ad.animate().alpha(1.0f).setDuration(500L).setListener(null);
    }

    private void t() {
        int c2;
        HealthTextView healthTextView = this.g;
        if (healthTextView == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = healthTextView.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            if (nsn.ae(this.f) && nsn.h() == 1) {
                c2 = nsn.c(this.f, 280.0f);
            } else {
                c2 = nsn.c(this.f, 130.0f);
            }
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "setMedalContentForPad: topMargin -> " + c2);
            layoutParams2.topMargin = Math.max(c2, 50);
            this.g.setLayoutParams(layoutParams2);
        }
    }

    private boolean n() {
        boolean isInMultiWindowMode = isInMultiWindowMode();
        j();
        b();
        return isInMultiWindowMode;
    }

    private void j() {
        if (isInMultiWindowMode()) {
            d(getResources().getDimensionPixelSize(R.dimen._2131363832_res_0x7f0a07f8), getResources().getDimensionPixelSize(R.dimen._2131363832_res_0x7f0a07f8));
        }
    }

    private void b() {
        if (EnvironmentInfo.k()) {
            d(getResources().getDimensionPixelSize(R.dimen._2131363832_res_0x7f0a07f8), getResources().getDimensionPixelSize(R.dimen._2131363832_res_0x7f0a07f8));
        }
    }

    private void d(int i, int i2) {
        if (this.k != null) {
            this.k.setLayoutParams(new FrameLayout.LayoutParams(i, i2));
        }
    }

    private void chs_(Bitmap bitmap, String str) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "obtain status is false");
        if (bitmap == null) {
            this.k.setImageResource(c(str, false));
        } else {
            this.k.setImageBitmap(bitmap);
        }
        this.o.setRightButtonVisibility(4);
        this.aq.setVisibility(4);
        b();
    }

    private String e(String str, String str2, int i, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return "";
        }
        try {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "setGainTime medal_time = ", str3);
            String a2 = UnitUtil.a(new Date(Long.parseLong(str3)), 20);
            this.i = a2;
            return d(str, str2, i, a2);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", "setGainTime NumberFormatException");
            return "";
        }
    }

    private String d(String str, String str2, int i, String str3) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "setGainTime name=", str, " type=", str2, " gainCount=", Integer.valueOf(i), " timeStr=", str3);
        if (i > 1 && mlb.n(str2)) {
            return getResources().getQuantityString(R.plurals._2130903194_res_0x7f03009a, i, str3, Integer.valueOf(i));
        }
        return String.format(getResources().getString(R.string._2130840762_res_0x7f020cba), str3);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " enter onDestroy");
        MedalView medalView = this.x;
        if (medalView != null) {
            medalView.c();
        }
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " enter onResume");
        super.onResume();
        MedalView medalView = this.x;
        if (medalView != null) {
            medalView.onResume();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " enter onPause");
        super.onPause();
        MedalView medalView = this.x;
        if (medalView != null) {
            medalView.onPause();
        }
    }

    private Bitmap cho_(String str) {
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "getBitmapFromPath: untrusted -> " + str);
            return null;
        }
        String c2 = CommonUtil.c(str);
        if (TextUtils.isEmpty(c2)) {
            return null;
        }
        if (!new File(c2).exists()) {
            LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "getBitmapFromPath: file not exists");
            return null;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(c2);
        if (decodeFile == null) {
            LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " bitmap is null");
        }
        return decodeFile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v18, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r10v31 */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v33 */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.huawei.pluginachievement.ui.AchieveMedalDetailActivity] */
    private boolean a(String str) {
        Throwable th;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3;
        FileInputStream fileInputStream4;
        FileInputStream fileInputStream5;
        FileInputStream fileInputStream6;
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "initMedal: untrusted -> " + str);
            return false;
        }
        String str2 = CommonUtil.c(str) + File.separator;
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "initMedal: path is empty");
            return false;
        }
        StringBuilder sb = new StringBuilder(str2);
        StringBuilder sb2 = new StringBuilder(str2);
        StringBuilder sb3 = new StringBuilder(str2);
        StringBuilder sb4 = new StringBuilder(str2);
        sb.append("medal.tST");
        FileInputStream sb5 = sb.toString();
        sb2.append("medal.nXYZ");
        String sb6 = sb2.toString();
        sb3.append("medal.vXYZ");
        String sb7 = sb3.toString();
        sb4.append("texture.jpg");
        Bitmap cho_ = cho_(sb4.toString());
        FileInputStream fileInputStream7 = null;
        try {
            try {
                fileInputStream4 = new FileInputStream((String) sb5);
                try {
                    fileInputStream3 = new FileInputStream(sb6);
                    try {
                        FileInputStream fileInputStream8 = new FileInputStream(sb7);
                        try {
                            chr_(fileInputStream4, fileInputStream3, fileInputStream8, cho_);
                            e(fileInputStream4, fileInputStream3, fileInputStream8);
                            return true;
                        } catch (FileNotFoundException unused) {
                            fileInputStream7 = fileInputStream8;
                            LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " close InputStream is exception");
                            sb5 = fileInputStream3;
                            e(fileInputStream4, sb5, fileInputStream7);
                            return false;
                        } catch (Exception unused2) {
                            fileInputStream7 = fileInputStream8;
                            LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", "3D view load exception!");
                            sb5 = fileInputStream3;
                            e(fileInputStream4, sb5, fileInputStream7);
                            return false;
                        } catch (OutOfMemoryError unused3) {
                            fileInputStream7 = fileInputStream8;
                            LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", "Obj3DBufferLoadAider.loadFromInputStream OutOfMemoryError");
                            sb5 = fileInputStream3;
                            e(fileInputStream4, sb5, fileInputStream7);
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            fileInputStream7 = fileInputStream8;
                            fileInputStream6 = fileInputStream3;
                            fileInputStream2 = fileInputStream7;
                            fileInputStream5 = fileInputStream6;
                            fileInputStream7 = fileInputStream4;
                            fileInputStream = fileInputStream5;
                            e(fileInputStream7, fileInputStream, fileInputStream2);
                            throw th;
                        }
                    } catch (FileNotFoundException unused4) {
                    } catch (Exception unused5) {
                    } catch (OutOfMemoryError unused6) {
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream6 = fileInputStream3;
                    }
                } catch (FileNotFoundException unused7) {
                    fileInputStream3 = null;
                } catch (Exception unused8) {
                    fileInputStream3 = null;
                } catch (OutOfMemoryError unused9) {
                    fileInputStream3 = null;
                } catch (Throwable th4) {
                    th = th4;
                    fileInputStream6 = null;
                }
            } catch (Throwable th5) {
                th = th5;
                fileInputStream2 = fileInputStream7;
                fileInputStream5 = sb5;
            }
        } catch (FileNotFoundException unused10) {
            fileInputStream3 = null;
            fileInputStream4 = null;
        } catch (Exception unused11) {
            fileInputStream3 = null;
            fileInputStream4 = null;
        } catch (OutOfMemoryError unused12) {
            fileInputStream3 = null;
            fileInputStream4 = null;
        } catch (Throwable th6) {
            th = th6;
            fileInputStream = null;
            fileInputStream2 = null;
            e(fileInputStream7, fileInputStream, fileInputStream2);
            throw th;
        }
    }

    private void chr_(final InputStream inputStream, final InputStream inputStream2, final InputStream inputStream3, final Bitmap bitmap) {
        njw.c(new WeakReference(new mdz()));
        new Obj3DBufferLoadAider().c(BaseApplication.getContext(), inputStream, inputStream2, inputStream3, new Obj3DBufferLoadAider.OnLoadListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalDetailActivity.5
            @Override // com.huawei.ucd.helper.gles.Obj3DBufferLoadAider.OnLoadListener
            public void onLoadOK(Obj3DBufferLoadAider.d dVar) {
                AchieveMedalDetailActivity.this.chq_(bitmap, dVar);
                AchieveMedalDetailActivity.this.e(inputStream, inputStream2, inputStream3);
            }

            @Override // com.huawei.ucd.helper.gles.Obj3DBufferLoadAider.OnLoadListener
            public void onLoadFailed(String str) {
                AchieveMedalDetailActivity.this.j.sendEmptyMessage(1);
                AchieveMedalDetailActivity.this.e(inputStream, inputStream2, inputStream3);
                LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " onLoadFailed msg=", str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void chq_(final Bitmap bitmap, final Obj3DBufferLoadAider.d dVar) {
        if (dVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", " onLoadOK result is null");
            runOnUiThread(new mih(this));
        } else {
            LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", " onLoadOK result=", dVar.toString());
            runOnUiThread(new Runnable() { // from class: mid
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveMedalDetailActivity.this.chv_(bitmap, dVar);
                }
            });
        }
    }

    public /* synthetic */ void chv_(Bitmap bitmap, Obj3DBufferLoadAider.d dVar) {
        MedalView medalView = this.x;
        if (medalView != null) {
            this.aa.removeView(medalView);
        }
        MedalView medalView2 = new MedalView(BaseApplication.getContext());
        this.x = medalView2;
        medalView2.setLayerType(2, null);
        this.x.setBackContent(new String[]{StringUtils.c((Object) this.am), this.i}, MedalBackType.ModelType.SOLID_CIRCLE, MedalBackType.ColorType.GOLD);
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "loadFromInputStream setTexture.");
        this.x.setTexture(bitmap, new MedalViewCallback() { // from class: mif
            @Override // com.huawei.ucd.medal.MedalViewCallback
            public final void onResponse(int i, String str) {
                AchieveMedalDetailActivity.this.b(i, str);
            }
        });
        if (nsn.ac(this.f)) {
            int[] iArr = new int[2];
            this.p.getLocationOnScreen(iArr);
            MedalView medalView3 = this.x;
            int i = iArr[0];
            medalView3.setTouchRect(new Rect(i, this.ag, this.ao + i, this.aj - this.h));
        } else {
            this.x.setTouchRect(new Rect(0, this.ag, this.ao, this.aj - this.h));
        }
        this.x.setObjData(dVar);
        this.x.setAnimatorScaleX(0.02f, 0.08f);
        this.x.setAnimatorScaleY(0.02f, 0.08f);
        this.x.setAnimatorRotationY(0.0f, 360.0f);
        this.x.setAnimatorPositionX(this.f8394a, this.ao / 2.0f);
        this.x.setAnimatorPositionY(this.e, (this.aj / 3.0f) + this.as);
        this.x.setAnimatorDuration(1000L);
        this.aa.addView(this.x);
        this.x.e();
    }

    public /* synthetic */ void b(int i, String str) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "loadFromInputStream Id=", this.ar, " code=", Integer.valueOf(i), " res=", str);
        if (i == -1) {
            runOnUiThread(new mih(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(InputStream inputStream, InputStream inputStream2, InputStream inputStream3) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
                LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", " InpuStream tst close exception!");
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException unused2) {
                LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", " InputStream nxy close exception!");
            }
        }
        if (inputStream3 != null) {
            try {
                inputStream3.close();
            } catch (IOException unused3) {
                LogUtil.b("PLGACHIEVE_AchieveMedalDetailActivity", " InputStream xyz close exception!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (!mcx.d(this.f)) {
            mlg.e(this.f);
            return;
        }
        if (mfg.c(1) && mcx.e()) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(4);
        }
        if (mcv.d(BaseApplication.getContext()).getAdapter() != null) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("name", this.ap);
            hashMap.put("className", this.y);
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "shareMedal: shotScreen");
            Bitmap cgJ_ = mfp.cgJ_(this.d);
            if (cgJ_ != null) {
                mcx.cfN_(this.f, cgJ_, AnalyticsValue.SUCCESSES_SHARE_1100011.value(), hashMap);
            } else {
                LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "screenCut is null");
            }
            if (mcv.e()) {
                ixx d = ixx.d();
                hashMap.put("type", 12);
                d.d(this.f, AnalyticsValue.SUCCESSES_SHARE_1100011.value(), hashMap, 0);
                return;
            }
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "adapter is null");
    }

    public void d() {
        this.t = (LinearLayout) findViewById(R.id.medal_theme_layout);
        this.d = (LinearLayout) findViewById(R.id.before_layout);
        this.b = (ImageView) findViewById(R.id.head_imageview);
        this.ah = (HealthTextView) findViewById(R.id.medal_share_desc);
        this.r = (HealthTextView) findViewById(R.id.medal_get_date);
        this.ae = (HealthTextView) findViewById(R.id.medal_share_content);
        this.an = (HealthTextView) findViewById(R.id.name_textview);
        this.ai = (ImageView) findViewById(R.id.medal_imageview);
        ImageView imageView = (ImageView) findViewById(R.id.vip_image);
        this.c = (LinearLayout) findViewById(R.id.account_layout);
        if (mkw.e()) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        String str = this.s;
        if (str != null && this.ap != null) {
            String replace = str.replace("/n", System.lineSeparator());
            this.s = replace;
            this.af.setText(replace);
            this.g.setText(this.ap);
            String str2 = this.q;
            if (str2 != null) {
                this.aq.setText(str2);
            }
        }
        l();
        c();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void l() {
        /*
            r4 = this;
            java.lang.String r0 = r4.ar
            android.graphics.Bitmap r0 = defpackage.mlb.ckp_(r0)
            r1 = 0
            java.lang.String r2 = r4.ar     // Catch: java.lang.NumberFormatException -> L16
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.NumberFormatException -> L16
            if (r2 != 0) goto L21
            java.lang.String r2 = r4.ar     // Catch: java.lang.NumberFormatException -> L16
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L16
            goto L22
        L16:
            java.lang.String r2 = "NumberFormatException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r3 = "PLGACHIEVE_AchieveMedalDetailActivity"
            com.huawei.hwlogsmodel.LogUtil.b(r3, r2)
        L21:
            r2 = r1
        L22:
            if (r0 == 0) goto L35
            r3 = 19
            if (r2 > r3) goto L29
            goto L35
        L29:
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            r3 = 0
            r2.<init>(r3, r0)
            android.widget.LinearLayout r0 = r4.t
            r0.setBackground(r2)
            goto L3d
        L35:
            android.widget.LinearLayout r0 = r4.t
            r2 = 2131431454(0x7f0b101e, float:1.8484638E38)
            r0.setBackgroundResource(r2)
        L3d:
            java.lang.String r0 = r4.s
            if (r0 == 0) goto L61
            java.lang.String r2 = r4.ap
            if (r2 == 0) goto L61
            com.huawei.ui.commonui.healthtextview.HealthTextView r2 = r4.ah
            r2.setText(r0)
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r4.ae
            java.lang.String r2 = r4.ap
            r0.setText(r2)
            java.lang.String r0 = r4.q
            if (r0 == 0) goto L5b
            com.huawei.ui.commonui.healthtextview.HealthTextView r2 = r4.r
            r2.setText(r0)
            goto L61
        L5b:
            com.huawei.ui.commonui.healthtextview.HealthTextView r0 = r4.r
            r2 = 4
            r0.setVisibility(r2)
        L61:
            java.lang.String r0 = r4.ar
            r2 = 1
            android.graphics.Bitmap r0 = defpackage.mlb.cko_(r0, r2, r1)
            if (r0 == 0) goto L76
            android.widget.ImageView r0 = r4.ai
            java.lang.String r3 = r4.ar
            android.graphics.Bitmap r1 = defpackage.mlb.cko_(r3, r2, r1)
            r0.setImageBitmap(r1)
            goto L7d
        L76:
            android.widget.ImageView r0 = r4.ai
            int r1 = r4.au
            r0.setImageResource(r1)
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginachievement.ui.AchieveMedalDetailActivity.l():void");
    }

    public void c() {
        w();
    }

    private void w() {
        if ("1".equals(SharedPreferenceManager.b(this.f, Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN))) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "accountmigrate: isthirdlogin == 1 and return!");
            v();
        } else {
            this.ak.getUserInfo(new c(this));
        }
    }

    private void v() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.pluginachievement.ui.AchieveMedalDetailActivity.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                AchieveMedalDetailActivity achieveMedalDetailActivity = AchieveMedalDetailActivity.this;
                achieveMedalDetailActivity.n = mkx.ckg_(obj, achieveMedalDetailActivity.n, AchieveMedalDetailActivity.this.j, 4);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "fetchUserData onFailure");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(UserInfomation userInfomation) {
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "handleWhenGetUserInfoSuccess");
        if (userInfomation != null) {
            String name = userInfomation.getName();
            if (TextUtils.isEmpty(name)) {
                LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "handleWhenGetUserInfoSuccess: name is empty");
                this.an.setText(mcx.b());
                String accountName = new UpApi(this.f).getAccountName();
                if (accountName != null) {
                    e(accountName);
                }
            } else {
                this.an.setText(name);
                e(name);
            }
            String picPath = userInfomation.getPicPath();
            if (!TextUtils.isEmpty(picPath)) {
                Bitmap cIe_ = nrf.cIe_(this.f, picPath);
                if (cIe_ != null) {
                    this.b.setImageBitmap(cIe_);
                    return;
                } else {
                    d(userInfomation);
                    LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "handleWhenGetUserInfoSuccess() btimap is null");
                    return;
                }
            }
            LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "handle", "WhenGetUserInfoSuccess! headImgPath is null! ");
            this.b.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "handleWhenGetUserInfoSuccess! userinfo is null! ");
    }

    private void e(String str) {
        if (!TextUtils.isEmpty(this.am)) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "userName not empty ");
        } else {
            this.am = mlb.a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "setUserNameFromLocal");
        HiUserInfo hiUserInfo = this.n;
        if (hiUserInfo == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "accountmigrate: setUserNameFromLocal mHiUserInfo == null");
            return;
        }
        this.an.setText(TextUtils.isEmpty(hiUserInfo.getName()) ? mcx.b() : this.n.getName());
        String headImgUrl = this.n.getHeadImgUrl();
        LogUtil.c("PLGACHIEVE_AchieveMedalDetailActivity", "accountfilepath = ", BaseApplication.getContext().getFilesDir(), "/photos/headimage");
        String c2 = c(headImgUrl);
        if (!TextUtils.isEmpty(c2)) {
            Bitmap cIe_ = nrf.cIe_(BaseApplication.getContext(), c2);
            if (cIe_ != null) {
                this.b.setImageBitmap(cIe_);
                return;
            } else {
                LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "setUserNameFromLocal: bmp is null");
                return;
            }
        }
        this.b.setImageResource(R.mipmap._2131821050_res_0x7f1101fa);
        LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "accountmigrate: setUserNameFromLocal() headImgPath is null! ");
    }

    private void d(UserInfomation userInfomation) {
        if (userInfomation.getPortraitUrl() == null) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "it's so terrible, as missing the headImage url, we can do nothing!");
        }
    }

    private String c(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(File.separator);
        if (split.length > 0) {
            str2 = this.f.getFilesDir() + "/photos/headimage/" + split[split.length - 1];
        } else {
            str2 = this.f.getFilesDir() + "/photos/headimage/" + str;
        }
        if (new File(str2).exists()) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "accountmigrate: getHeadImageFromLocal file.exists() yes");
        } else {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "accountmigrate: getHeadImageFromLocal file.exists() no");
            File[] listFiles = new File(this.f.getFilesDir() + "/photos/headimage").listFiles();
            if (listFiles != null && listFiles.length > 0) {
                try {
                    return listFiles[listFiles.length - 1].getCanonicalPath();
                } catch (IOException unused) {
                    return "";
                }
            }
        }
        return str2;
    }

    static class c implements BaseResponseCallback<UserInfomation> {
        private final WeakReference<AchieveMedalDetailActivity> c;

        c(AchieveMedalDetailActivity achieveMedalDetailActivity) {
            this.c = new WeakReference<>(achieveMedalDetailActivity);
        }

        @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, UserInfomation userInfomation) {
            AchieveMedalDetailActivity achieveMedalDetailActivity = this.c.get();
            if (achieveMedalDetailActivity == null || achieveMedalDetailActivity.isFinishing()) {
                return;
            }
            if (i != 0) {
                achieveMedalDetailActivity.j.sendEmptyMessage(3);
                return;
            }
            if (userInfomation == null) {
                LogUtil.h("PLGACHIEVE_AchieveMedalDetailActivity", "get userinfo success but obtain null objData");
                achieveMedalDetailActivity.j.sendEmptyMessage(3);
            } else {
                Message obtain = Message.obtain();
                obtain.obj = userInfomation;
                obtain.what = 2;
                achieveMedalDetailActivity.j.sendMessage(obtain);
            }
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    public void a() {
        if (!mlb.j(this.y)) {
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "showSameTypeMedalView: Not the Perfect Moon Medal! medalType -> " + this.y);
        } else {
            if (this.ab == null) {
                this.ab = (SameTypeMedalView) findViewById(R.id.view_sameTypeMedalView);
            }
            LogUtil.a("PLGACHIEVE_AchieveMedalDetailActivity", "showSameTypeMedalView: medalId -> " + this.ar);
            this.ab.b(this.ar, this.y, false);
        }
    }
}
