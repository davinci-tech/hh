package com.huawei.featureuserprofile.me;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.caloriecalculate.ActivityCaloriesCalculateApi;
import com.huawei.featureuserprofile.me.MyTargetActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.UserInformationCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.model.UserInfomation;
import com.tencent.open.apireq.BaseResp;
import defpackage.bdw;
import defpackage.bzw;
import defpackage.cei;
import defpackage.doj;
import defpackage.dss;
import defpackage.glz;
import defpackage.ixx;
import defpackage.kot;
import defpackage.nip;
import defpackage.nro;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Marker;

/* loaded from: classes.dex */
public class MyTargetActivity extends BaseActivity implements View.OnClickListener {
    private static int[] e = {R.style.setting_goal_sport_target_text, R.style.settarget_unit_text_style, R.style.setting_goal_weight_target_text, R.style.setting_goal_weight_target_text};

    /* renamed from: a, reason: collision with root package name */
    private boolean f1988a;
    private HealthTextView ab;
    private ImageView ac;
    private HealthTextView ad;
    private HealthTextView ae;
    private ImageView af;
    private ImageView ah;
    private HealthButton ai;
    private RelativeLayout aj;
    private ImageView ak;
    private HealthTextView al;
    private HealthTextView am;
    private ImageView an;
    private HealthTextView ao;
    private double ap;
    private CustomTitleBar ar;
    private View d;
    private Context f;
    private LinearLayout g;
    private HealthTextView h;
    private View i;
    private HealthTextView j;
    private int k;
    private int l;
    private HealthTextView o;
    private HealthTextView q;
    private UserInfomation r;
    private int s;
    private int t;
    private LinearLayout u;
    private HealthTextView v;
    private HealthTextView w;
    private LinearLayout y;
    private HealthTextView z;
    private HealthOpenSDK m = null;
    private boolean c = false;
    private HealthSeekBar aa = null;
    private MotionGoal n = new MotionGoal();
    private HiUserInfo p = new HiUserInfo();
    private Handler ag = new c(this);
    private Runnable x = new Runnable() { // from class: com.huawei.featureuserprofile.me.MyTargetActivity.1
        @Override // java.lang.Runnable
        public void run() {
            MyTargetActivity.this.e();
        }
    };
    private boolean b = false;
    private HealthSeekBar.OnSeekBarChangeListener as = new HealthSeekBar.OnSeekBarChangeListener() { // from class: com.huawei.featureuserprofile.me.MyTargetActivity.5
        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
        }

        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
        }

        @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
        public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
            LogUtil.a("Main_MyTargetActivity", "onProgressChanged() value:", Integer.valueOf(i));
            int i2 = ((i / 1000) * 1000) + 2000;
            MyTargetActivity.this.e(i2);
            MyTargetActivity.this.n.setGoalType(1);
            MyTargetActivity.this.n.setStepGoal(i2);
        }
    };

    private int c(int i, int i2) {
        return i > i2 ? -1 : 1;
    }

    private void h() {
        this.m = dss.c(this).d();
    }

    private float d(int i) {
        if (i <= 0) {
            LogUtil.h("Main_MyTargetActivity", "steps is less or equal than 0");
            return 0.0f;
        }
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        if (activityCaloriesCalculateApi == null) {
            return 0.0f;
        }
        activityCaloriesCalculateApi.initUserInfo(a());
        return activityCaloriesCalculateApi.calculateCaloriesOnlyStep(i);
    }

    private int c(float f, int i, int i2) {
        long j;
        ActivityCaloriesCalculateApi activityCaloriesCalculateApi = (ActivityCaloriesCalculateApi) Services.c("BaseSportModel", ActivityCaloriesCalculateApi.class);
        if (activityCaloriesCalculateApi != null) {
            activityCaloriesCalculateApi.initUserInfo(a());
            j = activityCaloriesCalculateApi.calculateDurationByCalories(f, i, i2);
        } else {
            j = 0;
        }
        return (int) j;
    }

    private bdw a() {
        bdw.e eVar = new bdw.e();
        HiUserInfo hiUserInfo = this.p;
        if (hiUserInfo != null) {
            eVar.d(hiUserInfo.getAge()).c(this.p.getHeight() / 100.0f).b(this.p.getWeight()).c(this.p.getGender());
        }
        return eVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        LogUtil.a("Main_MyTargetActivity", "refreshStepGoalView() value:", Integer.valueOf(i));
        if (i <= 7000) {
            l();
        } else if (i <= 13000) {
            k();
        } else {
            o();
        }
        float abs = Math.abs(d(i));
        LogUtil.a("Main_MyTargetActivity", "calorieValue=", Float.valueOf(abs));
        this.j.setText(getResources().getString(R.string._2130839545_res_0x7f0207f9, NumberFormat.getInstance().format(abs)));
        c(this.ab, R.plurals._2130903205_res_0x7f0300a5, i, 1, e);
        int c2 = c(abs, 257, 1);
        String quantityString = getResources().getQuantityString(R.plurals._2130903494_res_0x7f0301c6, c2, Integer.valueOf(c2));
        int c3 = c(abs, 258, 1);
        String quantityString2 = getResources().getQuantityString(R.plurals._2130903494_res_0x7f0301c6, c3, Integer.valueOf(c3));
        int c4 = c(abs, 259, 1);
        String quantityString3 = getResources().getQuantityString(R.plurals._2130903494_res_0x7f0301c6, c4, Integer.valueOf(c4));
        LogUtil.a("Main_MyTargetActivity", "footSportTime ", Integer.valueOf(c2), " runSportTime ", Integer.valueOf(c3), " bikeSportTime ", Integer.valueOf(c4));
        this.o.setText(quantityString);
        this.q.setText(quantityString2);
        this.h.setText(quantityString3);
        this.ab.setVisibility(0);
        this.j.setVisibility(0);
        this.o.setVisibility(0);
        this.q.setVisibility(0);
        this.h.setVisibility(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Main_MyTargetActivity", "onCreate");
        super.onCreate(bundle);
        if (!LoginInit.getInstance(this).getIsLogined()) {
            finish();
            return;
        }
        this.f = this;
        setContentView(R.layout.hw_show_set_goal_activity);
        i();
        h();
        this.n.setStepGoal(10000);
        this.s = getIntent().getIntExtra("from", 6);
    }

    private void i() {
        LogUtil.a("Main_MyTargetActivity", "initView 222");
        if (LanguageUtil.bc(this.f)) {
            e = new int[]{R.style.setting_goal_sport_target_text, R.style.settarget_unit_text_rt_style, R.style.setting_goal_weight_target_text, R.style.setting_goal_weight_target_text};
        }
        d();
        c();
        m();
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        this.r = userInfo;
        if (userInfo == null) {
            this.r = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        e(this.r);
        this.aa.setTouchable(true);
        this.aa.setMax(18000);
        b(this.n.getStepGoal());
        if (UnitUtil.h()) {
            d(0.0d);
        } else {
            c(0.0d);
        }
        this.aa.setOnSeekBarChangeListener(this.as);
        j();
        Drawable drawable = getResources().getDrawable(R.drawable._2131429999_res_0x7f0b0a6f);
        drawable.setBounds(0, 0, drawable.getMinimumHeight(), drawable.getMinimumHeight());
    }

    private void d() {
        this.ar = (CustomTitleBar) findViewById(R.id.me_mytarget_titlebar);
        this.ao = (HealthTextView) findViewById(R.id.hw_show_set_target_weight_unit);
        this.aa = (HealthSeekBar) findViewById(R.id.set_step_goal_seekbar);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.hw_show_set_target_steps);
        this.ab = healthTextView;
        healthTextView.setVisibility(0);
        this.j = (HealthTextView) findViewById(R.id.hw_show_set_target_calorie);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.hw_show_set_target_weight);
        this.am = healthTextView2;
        healthTextView2.setVisibility(0);
        this.o = (HealthTextView) findViewById(R.id.hw_show_set_target_foot_min);
        this.q = (HealthTextView) findViewById(R.id.hw_show_set_target_run_min);
        this.al = (HealthTextView) findViewById(R.id.target_ignore_text);
        this.ae = (HealthTextView) findViewById(R.id.target_complete_text);
        this.aj = (RelativeLayout) findViewById(R.id.hw_set_target_tips);
        this.h = (HealthTextView) findViewById(R.id.hw_show_set_target_bike_min);
        this.w = (HealthTextView) findViewById(R.id.hw_show_set_target_sport_little);
        this.z = (HealthTextView) findViewById(R.id.hw_show_set_target_sport_match);
        this.ad = (HealthTextView) findViewById(R.id.hw_show_set_target_sport_server);
        this.ai = (HealthButton) findViewById(R.id.hw_target_save_goa);
        this.g = (LinearLayout) findViewById(R.id.health_data_edit_weight_goal);
        this.v = (HealthTextView) findViewById(R.id.set_goal_activity_target_about_as);
        this.ac = (ImageView) findViewById(R.id.sport_target_icon);
        this.af = (ImageView) findViewById(R.id.target_foot_min_icon);
        this.an = (ImageView) findViewById(R.id.target_run_min_icon);
        this.ah = (ImageView) findViewById(R.id.target_bike_min_icon);
        this.ak = (ImageView) findViewById(R.id.health_set_target_weight_icon);
        this.u = (LinearLayout) findViewById(R.id.set_goal_activity_layout2);
        this.y = (LinearLayout) findViewById(R.id.set_goal_activity_layout10);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        f();
    }

    private void f() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new UserInformationCallback<MyTargetActivity>(this) { // from class: com.huawei.featureuserprofile.me.MyTargetActivity.4
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, final UserInfomation userInfomation) {
                final MyTargetActivity myTargetActivity;
                if (userInfomation == null) {
                    LogUtil.h("Main_MyTargetActivity", "UserInfoCallback getUserInfo failed");
                } else {
                    if (this.mReference == null || (myTargetActivity = (MyTargetActivity) this.mReference.get()) == null) {
                        return;
                    }
                    myTargetActivity.runOnUiThread(new Runnable() { // from class: com.huawei.featureuserprofile.me.MyTargetActivity.4.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (myTargetActivity.isFinishing()) {
                                return;
                            }
                            myTargetActivity.e(userInfomation);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(UserInfomation userInfomation) {
        if (!userInfomation.isHeightValid() || !userInfomation.isWeightValid()) {
            this.aj.setVisibility(0);
            this.ae.setText(this.f.getResources().getString(R.string._2130844051_res_0x7f021993).toUpperCase());
            this.al.setText(this.f.getResources().getString(R.string._2130841424_res_0x7f020f50).toUpperCase());
            return;
        }
        this.aj.setVisibility(8);
    }

    private void m() {
        this.ai.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.ad.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.al.setOnClickListener(this);
        this.ae.setOnClickListener(this);
        this.ar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.featureuserprofile.me.MyTargetActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyTargetActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void c() {
        this.d = findViewById(R.id.mBigCD3);
        this.i = findViewById(R.id.mBigCD4);
        this.d.setVisibility(0);
        this.i.setVisibility(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        if (nsn.ag(this.f)) {
            this.d.setVisibility(8);
            this.i.setVisibility(8);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 1;
            this.u.setLayoutParams(layoutParams);
            this.y.setLayoutParams(layoutParams);
            this.w.setTextSize(1, 13.0f);
            this.z.setTextSize(1, 13.0f);
            this.ad.setTextSize(1, 13.0f);
            this.j.setTextSize(1, 14.0f);
            this.v.setTextSize(1, 13.0f);
            this.o.setTextSize(1, 13.0f);
            this.q.setTextSize(1, 13.0f);
            this.h.setTextSize(1, 13.0f);
        }
    }

    /* loaded from: classes7.dex */
    static class d implements ResponseCallback<Float> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<MyTargetActivity> f1990a;

        d(MyTargetActivity myTargetActivity) {
            this.f1990a = new WeakReference<>(myTargetActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, Float f) {
            MyTargetActivity myTargetActivity = this.f1990a.get();
            if (myTargetActivity == null || myTargetActivity.isFinishing() || myTargetActivity.isDestroyed()) {
                ReleaseLogUtil.d("Main_MyTargetActivity", "WeakGoalInfoHiCommonListener activity ", myTargetActivity);
                return;
            }
            if (f == null) {
                myTargetActivity.ap = 0.0d;
                myTargetActivity.ag.sendEmptyMessage(106);
            } else {
                myTargetActivity.ag.sendMessage(myTargetActivity.ag.obtainMessage(102, 0, 0, Double.valueOf(Double.parseDouble(String.valueOf(f)))));
            }
        }
    }

    /* loaded from: classes7.dex */
    static class b implements HiCommonListener {
        private WeakReference<MyTargetActivity> d;

        b(MyTargetActivity myTargetActivity) {
            this.d = new WeakReference<>(myTargetActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            MyTargetActivity myTargetActivity = this.d.get();
            if (myTargetActivity == null) {
                return;
            }
            LogUtil.a("Main_MyTargetActivity", "fetchUserData onSuccess,data = ", obj);
            if (obj != null) {
                List list = (List) obj;
                if (list.size() > 0) {
                    LogUtil.a("Main_MyTargetActivity", "fetchUserData onSuccess");
                    myTargetActivity.p = (HiUserInfo) list.get(0);
                    myTargetActivity.l = myTargetActivity.p.getGender();
                    myTargetActivity.k = myTargetActivity.p.getHeight();
                    myTargetActivity.ag.sendMessage(myTargetActivity.ag.obtainMessage(105, 0, 0, obj));
                    LogUtil.c("Main_MyTargetActivity", "birthday==", Integer.valueOf(myTargetActivity.p.getBirthday()), "gender==", Integer.valueOf(myTargetActivity.p.getGender()));
                }
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("Main_MyTargetActivity", "fetchUserData onFailure");
        }
    }

    public void e() {
        kot.a().c(new d(this));
        HiHealthManager.d(this.f).fetchUserData(new b(this));
        nip.d("900200006", new e());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        try {
            super.onResume();
            ThreadPoolManager.d().d("getHeightIsNotSet", new Runnable() { // from class: com.huawei.featureuserprofile.me.MyTargetActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    MyTargetActivity.this.f1988a = cei.b().getHeightIsNotSet();
                    MyTargetActivity.this.ag.post(MyTargetActivity.this.x);
                }
            });
        } catch (BadParcelableException e2) {
            LogUtil.a("Main_MyTargetActivity", "BadParcelableException e =", e2.getMessage());
        } catch (Exception unused) {
            LogUtil.a("Main_MyTargetActivity", "onResume Exception e");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.w) {
            b(5000);
        } else if (view == this.z) {
            b(10000);
        } else if (view == this.ad) {
            b(17000);
        } else if (view == this.ai) {
            HashMap hashMap = new HashMap();
            hashMap.put("click", 1);
            ixx.d().d(this.f, AnalyticsValue.HEALTH_MINE_SETTINGS_MY_TARGET_SAVE_2040033.value(), hashMap, 0);
            bzw.e().setEvent(this.f, String.valueOf(1200), new HashMap());
            b();
        } else if (view == this.g) {
            if (glz.d()) {
                LogUtil.a("Main_MyTargetActivity", "onClick isFastClickCard");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            this.c = true;
            if (Utils.o()) {
                AppRouter.b("/Main/WeightGoalActivity").c(this.f);
            } else if (cei.b().getLastestWeight() > 0.0f) {
                AppRouter.b("/Main/WeightGoalActivity").c(this.f);
            } else {
                r();
            }
        } else if (view == this.al) {
            this.aj.setVisibility(8);
        } else if (view == this.ae) {
            AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(this.f);
        } else {
            LogUtil.a("Main_MyTargetActivity", "onClick is null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void r() {
        new NoTitleCustomAlertDialog.Builder(this).e(R.string._2130839552_res_0x7f020800).czC_(R.string._2130841425_res_0x7f020f51, new View.OnClickListener() { // from class: bsc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyTargetActivity.this.tT_(view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: bsb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
    }

    public /* synthetic */ void tT_(View view) {
        glz.b(this, 65.0d, 20.0d);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void b() {
        LogUtil.a("Main_MyTargetActivity", "enter saveData");
        this.b = true;
        int stepGoal = this.n.getStepGoal();
        if (stepGoal == this.t) {
            ReleaseLogUtil.e("Main_MyTargetActivity", "saveData failed, no need to save with not changed,", Integer.valueOf(stepGoal));
            finish();
        } else {
            nip.e("900200006", stepGoal);
            a(c(this.t, stepGoal), stepGoal);
            LogUtil.a("Main_MyTargetActivity", "saveData step is ", Integer.valueOf(stepGoal));
            this.ag.sendEmptyMessage(101);
        }
    }

    private void a(int i, int i2) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        hashMap.put("from", Integer.valueOf(this.s));
        hashMap.put("adjustType", Integer.valueOf(i));
        hashMap.put("value", Integer.valueOf(i2));
        ixx.d().d(BaseApplication.e(), AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040111.value(), hashMap, 0);
    }

    private void a(double d2) {
        LogUtil.a("Main_MyTargetActivity", "updateWeightTargetInfor() enter");
        c(true);
        this.ao.setVisibility(8);
        if (UnitUtil.h()) {
            c(this.am, R.plurals._2130903216_res_0x7f0300b0, d2, 2, e);
        } else {
            c(this.am, R.plurals._2130903215_res_0x7f0300af, d2, 0, e);
        }
    }

    private void c(HealthTextView healthTextView, int i, double d2, int i2, int... iArr) {
        int i3;
        int i4;
        LogUtil.a("Main_MyTargetActivity", "setDifferentTextSize() ");
        double h = i2 == 2 ? UnitUtil.h(d2) : d2;
        String e2 = UnitUtil.e(h, 1, i2 == 1 ? 0 : e(h));
        String quantityString = getResources().getQuantityString(i, (int) h, e2);
        int length = e2.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(quantityString);
        LogUtil.c("Main_MyTargetActivity", "setDifferentText= ", quantityString.substring(0, length), Marker.ANY_NON_NULL_MARKER, String.valueOf(h), Marker.ANY_NON_NULL_MARKER, quantityString);
        if (quantityString.substring(0, length).equals(e2)) {
            i3 = spannableStringBuilder.length();
            i4 = length;
        } else {
            int length2 = spannableStringBuilder.length();
            int length3 = spannableStringBuilder.length();
            int length4 = spannableStringBuilder.length() - length;
            int i5 = length2 - length;
            i3 = length3;
            i4 = i5;
            length = length4;
        }
        spannableStringBuilder.setSpan(new TextAppearanceSpan(getApplicationContext(), i2 == 1 ? iArr[0] : iArr[2]), 0, length, 17);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(getApplicationContext(), iArr[1]), i4, i3, 17);
        healthTextView.setText(spannableStringBuilder);
    }

    private int e(double d2) {
        if (d2 == Double.valueOf(d2).intValue()) {
            return 0;
        }
        return Math.round(Math.pow(10.0d, 2.0d) * d2) % 10 == 0 ? 1 : 2;
    }

    private void l() {
        this.w.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.z.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.ad.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
    }

    private void o() {
        this.w.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.z.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.ad.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
    }

    private void k() {
        this.w.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        this.z.setTextColor(getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        this.ad.setTextColor(getResources().getColor(R.color._2131299241_res_0x7f090ba9));
    }

    private void b(int i) {
        int i2 = i + BaseResp.CODE_ERROR_PARAMS;
        LogUtil.a("Main_MyTargetActivity", "mStepGoalSeekbar.setProgress: ", Integer.valueOf(i2));
        this.aa.setProgress(i2);
        e(i);
    }

    private void d(double d2) {
        LogUtil.a("Main_MyTargetActivity", "(kgToLb)  mWeightGoalSeekbar.setProgress: ", Integer.valueOf(glz.a(((float) UnitUtil.h(d2)) - 22.0f)));
        a(d2);
    }

    private void c(double d2) {
        LogUtil.a("Main_MyTargetActivity", "mWeightGoalSeekbar.setProgress: ", Double.valueOf(d2 - 10.0d));
        a(d2);
    }

    /* loaded from: classes7.dex */
    class c extends BaseHandler<MyTargetActivity> {
        c(MyTargetActivity myTargetActivity) {
            super(myTargetActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: tU_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MyTargetActivity myTargetActivity, Message message) {
            switch (message.what) {
                case 101:
                    nro.e(myTargetActivity.getApplicationContext(), 6);
                    if (myTargetActivity.b) {
                        Intent intent = MyTargetActivity.this.getIntent();
                        if (intent != null) {
                            intent.putExtra("StepGoal", MyTargetActivity.this.n.getStepGoal());
                            MyTargetActivity.this.setResult(-1, intent);
                        }
                        myTargetActivity.finish();
                        LogUtil.a("Main_MyTargetActivity", "savesuccess");
                        break;
                    }
                    break;
                case 102:
                    myTargetActivity.d(message.obj);
                    break;
                case 104:
                    myTargetActivity.c(MyTargetActivity.this.n.getStepGoal());
                    break;
                case 105:
                case 106:
                    myTargetActivity.n();
                    break;
                case 107:
                    int i = message.arg1;
                    LogUtil.a("Main_MyTargetActivity", "initData stepGoalValue is ", Integer.valueOf(i));
                    MyTargetActivity.this.c(i);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        boolean z;
        LogUtil.a("Main_MyTargetActivity", "showTarget data ", obj);
        if (obj instanceof Double) {
            this.ap = ((Double) obj).doubleValue();
            z = true;
        } else {
            this.ap = 0.0d;
            z = false;
        }
        cei.b().setUserGoalWeight((float) this.ap);
        if (z) {
            if (UnitUtil.h()) {
                d(this.ap);
                return;
            } else {
                c(this.ap);
                return;
            }
        }
        n();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (i > 0) {
            b(i);
        } else {
            b(10000);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        setIntent(null);
        super.finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.ag;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.ao.setVisibility(8);
        double d2 = this.ap;
        if (d2 == 0.0d) {
            if (g()) {
                this.am.setText("--");
                this.am.setTextSize(1, 35.0f);
                this.ao.setVisibility(0);
                if (UnitUtil.h()) {
                    this.ao.setText(getResources().getString(R.string._2130843856_res_0x7f0218d0));
                    return;
                } else {
                    this.ao.setText(getResources().getString(R.string._2130839548_res_0x7f0207fc));
                    return;
                }
            }
            c(true);
            int intValue = new BigDecimal(doj.a((byte) this.l, this.k, this.p.getAge())).setScale(0, 4).intValue();
            if (UnitUtil.h()) {
                d(intValue);
                return;
            } else {
                c(intValue);
                return;
            }
        }
        a(d2);
    }

    private boolean g() {
        int i = this.k;
        return (i == 170 && this.f1988a) || i == 0;
    }

    private void c(boolean z) {
        if (z) {
            this.ai.setEnabled(true);
            this.ai.setBackgroundResource(R.drawable.button_background_emphasize);
        } else {
            this.ai.setEnabled(false);
            this.ai.setBackgroundResource(R.drawable.button_background_emphasize_disable);
        }
    }

    private void j() {
        tS_(this.ac, R.drawable._2131429999_res_0x7f0b0a6f);
        tS_(this.af, R.drawable._2131428730_res_0x7f0b057a);
        tS_(this.an, R.drawable._2131428729_res_0x7f0b0579);
        tS_(this.ah, R.drawable._2131428728_res_0x7f0b0578);
        tS_(this.ak, R.drawable._2131427842_res_0x7f0b0202);
    }

    private void tS_(ImageView imageView, int i) {
        if (LanguageUtil.bc(this.f)) {
            BitmapDrawable cKn_ = nrz.cKn_(this.f, i);
            if (cKn_ != null) {
                imageView.setImageDrawable(cKn_);
                return;
            }
            return;
        }
        imageView.setImageResource(i);
    }

    /* loaded from: classes7.dex */
    static class e implements IBaseResponseCallback {
        private WeakReference<MyTargetActivity> d;

        private e(MyTargetActivity myTargetActivity) {
            this.d = new WeakReference<>(myTargetActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            int stepGoal;
            Object[] objArr = new Object[4];
            objArr[0] = "onResponse: errorCode = ";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = " objData = ";
            objArr[3] = obj == null ? null : obj.toString();
            LogUtil.a("Main_MyTargetActivity", objArr);
            MyTargetActivity myTargetActivity = this.d.get();
            if (myTargetActivity == null) {
                LogUtil.h("Main_MyTargetActivity", "onResponse: provider is null");
                return;
            }
            if (!(obj instanceof Integer) || myTargetActivity.c) {
                stepGoal = myTargetActivity.n.getStepGoal();
                LogUtil.c("Main_MyTargetActivity", "effectiveGoal: ", Integer.valueOf(stepGoal));
            } else {
                stepGoal = ((Integer) obj).intValue();
                myTargetActivity.t = stepGoal;
                LogUtil.c("Main_MyTargetActivity", "effectiveGoal: ", Integer.valueOf(stepGoal), "mInitGoal: ", Integer.valueOf(myTargetActivity.t));
            }
            Message obtain = Message.obtain();
            obtain.what = 107;
            obtain.arg1 = stepGoal;
            myTargetActivity.ag.sendMessage(obtain);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
