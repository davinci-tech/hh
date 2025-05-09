package com.huawei.health.suggestion.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.socialshare.api.SocialShareCenterApi;
import com.huawei.health.suggestion.ui.MyPlanReportActivity;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.up.model.UserInfomation;
import defpackage.asc;
import defpackage.ash;
import defpackage.fdu;
import defpackage.ffy;
import defpackage.ixx;
import defpackage.jdv;
import defpackage.mny;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/* loaded from: classes4.dex */
public class MyPlanReportActivity extends BaseStateActivity {

    /* renamed from: a, reason: collision with root package name */
    private String f3055a;
    private Context d;
    private MyPlanReportActivity e;
    private ImageView f;
    private int g;
    private RelativeLayout h;
    private ImageView i;
    private RunPlanReportDisplayFragment j;
    private RelativeLayout k;
    private RelativeLayout m;
    private HealthTextView n;
    private HealthTextView o;
    private mny p;
    private CustomTitleBar q;
    private long r;
    private LinearLayout t;
    private Handler b = new c(this);
    private Bitmap c = null;
    private Bitmap l = null;

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
    }

    class c extends BaseHandler<MyPlanReportActivity> {
        c(MyPlanReportActivity myPlanReportActivity) {
            super(myPlanReportActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ayC_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(MyPlanReportActivity myPlanReportActivity, Message message) {
            if (message == null) {
                LogUtil.b("Track_MyPlanReportActivity", "MyPlanReportHandler message null");
                return;
            }
            int i = message.what;
            if (i == 0) {
                MyPlanReportActivity.this.k();
            } else {
                if (i != 1) {
                    return;
                }
                MyPlanReportActivity.this.j.b(MyPlanReportActivity.this.p);
                MyPlanReportActivity.this.findViewById(R.id.sug_run_report_title_bar).setVisibility(0);
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Track_MyPlanReportActivity", "onCreate");
        clearBackgroundDrawable();
        this.r = System.currentTimeMillis();
        ash.a("showReportTime", String.valueOf(System.currentTimeMillis()));
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("Track_MyPlanReportActivity", "onResume");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.n.setText(this.p.g());
        this.o.setText(ffy.d(this.d, R.string._2130842647_res_0x7f021417, c(this.p.n(), this.p.o()), c(this.p.b(), this.p.a())));
        c(this.p.d(), this.p.e());
        this.m.setVisibility(8);
        this.j.b(this.p);
    }

    private String c(String str, long j) {
        if (j > 0) {
            return UnitUtil.a("yyyy/MM/dd", j * 1000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        LogUtil.a("Track_MyPlanReportActivity", "timeFormat timeString before:", str);
        try {
            long time = ((Date) Objects.requireNonNull(simpleDateFormat.parse(str))).getTime();
            LogUtil.a("Track_MyPlanReportActivity", "timeFormat time:", Long.valueOf(time));
            if (time > 0) {
                str = UnitUtil.a("yyyy/MM/dd", time);
            }
        } catch (ParseException e) {
            LogUtil.b("Track_MyPlanReportActivity", LogAnonymous.b((Throwable) e));
        }
        LogUtil.a("Track_MyPlanReportActivity", "timeString after:", str);
        return str;
    }

    private void c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("Track_MyPlanReportActivity", "setRunReportBackground url null");
        } else if (nsn.ag(this)) {
            nrf.cJh_(str, this.i, 0);
        } else {
            nrf.cJh_(str2, this.i, 0);
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        this.e = this;
        this.d = this;
        if (!CommonUtil.aa(this)) {
            nsn.a(this.d, MyPlanReportActivity.class.getName(), a(), false);
            finish();
            return;
        }
        setContentView(R.layout.sug_run_report_activity);
        c();
        cancelAdaptRingRegion();
        i();
        e();
    }

    private void e() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.d);
        builder.e(R.string._2130848690_res_0x7f022bb2);
        builder.czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_MyPlanReportActivity", "run plan language tips");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        boolean z = LanguageUtil.j(this.d) || LanguageUtil.p(this.d);
        if (e.isShowing() || z) {
            return;
        }
        e.show();
    }

    private String a() {
        return this.d.getResources().getString(R.string._2130844817_res_0x7f021c91);
    }

    private void i() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        if (supportFragmentManager.findFragmentById(R.id.frag_sug_report_list) == null) {
            RunPlanReportDisplayFragment runPlanReportDisplayFragment = new RunPlanReportDisplayFragment();
            this.j = runPlanReportDisplayFragment;
            beginTransaction.add(R.id.frag_sug_report_list, runPlanReportDisplayFragment);
            beginTransaction.commit();
        } else if (supportFragmentManager.findFragmentById(R.id.frag_sug_report_list) instanceof RunPlanReportDisplayFragment) {
            this.j = (RunPlanReportDisplayFragment) supportFragmentManager.findFragmentById(R.id.frag_sug_report_list);
        }
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.sug_run_report_title_bar);
        this.q = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        if (LanguageUtil.bc(this)) {
            this.q.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430036_res_0x7f0b0a94), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.q.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.q.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MyPlanReportActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.q.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: fjp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyPlanReportActivity.this.ayB_(view);
            }
        });
        this.n = (HealthTextView) findViewById(R.id.report_name);
        this.o = (HealthTextView) findViewById(R.id.report_time);
        this.i = (ImageView) findViewById(R.id.report_background);
        this.m = (RelativeLayout) findViewById(R.id.sug_report_loading_layout);
        this.f = (ImageView) findViewById(R.id.report_bg);
        this.k = (RelativeLayout) findViewById(R.id.share_report_layout);
        this.t = (LinearLayout) findViewById(R.id.report_user_layout);
        h();
        g();
        f();
    }

    public /* synthetic */ void ayB_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        if (this.g != IntPlan.PlanType.AI_RUN_PLAN.getType()) {
            return;
        }
        int i = Utils.o() ? R.drawable._2131431177_res_0x7f0b0f09 : R.drawable._2131431176_res_0x7f0b0f08;
        if (nsn.ag(this)) {
            i = R.drawable._2131431178_res_0x7f0b0f0a;
        }
        if (LanguageUtil.bc(this)) {
            this.i.setBackground(nrz.cKn_(this, i));
        } else {
            this.i.setBackgroundResource(i);
        }
    }

    private void g() {
        ((HealthScrollView) findViewById(R.id.scroll_view)).d(new HealthScrollView.ScrollChangeToBoundaryListener() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.1
            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrollStateChange(int i) {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrolledToBottom() {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrolledTop() {
            }

            @Override // com.huawei.ui.commonui.scrollview.HealthScrollView.ScrollChangeToBoundaryListener
            public void onScrollToChangeAlpha(float f) {
                if (MyPlanReportActivity.this.d == null || MyPlanReportActivity.this.q == null) {
                    return;
                }
                if (f > 0.1d) {
                    MyPlanReportActivity.this.q.setBackgroundColor(MyPlanReportActivity.this.d.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
                } else {
                    MyPlanReportActivity.this.q.setBackgroundColor(MyPlanReportActivity.this.d.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Bitmap bitmap;
        if (this.c != null && (bitmap = this.l) != null) {
            ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(ayA_(bitmap.copy(Bitmap.Config.ARGB_8888, true)), this.d);
            return;
        }
        this.h = (RelativeLayout) findViewById(R.id.root_view);
        findViewById(R.id.sug_run_report_title_bar).setVisibility(8);
        this.j.e(new UiCallback() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Track_MyPlanReportActivity", "notifyShareDataChange ", Integer.valueOf(i), "and", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onSuccess(Object obj) {
                LogUtil.a("Track_MyPlanReportActivity", "screenCut success");
                MyPlanReportActivity.this.q.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.3.2
                    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                    public void onGlobalLayout() {
                        MyPlanReportActivity.this.q.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        LogUtil.a("Track_MyPlanReportActivity", "screenCut onGlobalLayout");
                        if (MyPlanReportActivity.this.q.getVisibility() == 8) {
                            MyPlanReportActivity.this.j();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        l();
        this.c = jdv.bGg_(this.h);
        this.f.setBackground(new BitmapDrawable(getResources(), this.c));
        this.f.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (MyPlanReportActivity.this.f.getHeight() == 0 || MyPlanReportActivity.this.f.getWidth() == 0) {
                    return;
                }
                MyPlanReportActivity.this.f.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (MyPlanReportActivity.this.l != null) {
                    return;
                }
                MyPlanReportActivity myPlanReportActivity = MyPlanReportActivity.this;
                myPlanReportActivity.l = jdv.bGg_(myPlanReportActivity.k);
                MyPlanReportActivity.this.ayz_(MyPlanReportActivity.this.l != null ? MyPlanReportActivity.this.l.copy(Bitmap.Config.ARGB_8888, true) : null);
            }
        });
    }

    private void l() {
        ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).updateShareUserView("Track_MyPlanReportActivity", this.t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ayz_(final Bitmap bitmap) {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.MyPlanReportActivity.8
            @Override // java.lang.Runnable
            public void run() {
                if (bitmap != null) {
                    ((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).exeShare(MyPlanReportActivity.ayA_(bitmap), MyPlanReportActivity.this.d);
                    MyPlanReportActivity.this.b.sendEmptyMessageDelayed(1, 500L);
                } else {
                    LogUtil.h("Track_MyPlanReportActivity", "screenCut is null");
                    MyPlanReportActivity.this.findViewById(R.id.sug_run_report_title_bar).setVisibility(0);
                    nrh.e(MyPlanReportActivity.this.d, R.string._2130842310_res_0x7f0212c6);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static fdu ayA_(Bitmap bitmap) {
        fdu fduVar = new fdu(1);
        fduVar.awp_(bitmap);
        fduVar.c((String) null);
        fduVar.a((String) null);
        fduVar.f(null);
        fduVar.b(AnalyticsValue.HEALTH_SHARE_FITNESS_REPORT_SHARE_2100009.value());
        fduVar.i(false);
        fduVar.b(2);
        return fduVar;
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        d();
    }

    private void d() {
        if (this.f3055a == null) {
            LogUtil.h("Track_MyPlanReportActivity", "planId is null.");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Track_MyPlanReportActivity", "getMyPlanInfo : planApi is null.");
        } else {
            planApi.getTrainingReport(this.f3055a, this.g, new d(this));
        }
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.b("Track_MyPlanReportActivity", "initReportData intent == null");
            return;
        }
        this.g = intent.getIntExtra("plantype", IntPlan.PlanType.AI_RUN_PLAN.getType());
        this.f3055a = intent.getStringExtra("planId");
        LogUtil.a("Track_MyPlanReportActivity", "getIntentData planType: ", Integer.valueOf(this.g), "mPlanId:", this.f3055a);
    }

    private void f() {
        Bitmap cIe_;
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.report_share_detail_title_usrname);
        ImageView imageView = (ImageView) findViewById(R.id.img_report_share_user_img);
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.b("Track_MyPlanReportActivity", "getUserInfo : userProfileMgrApi is null.");
            return;
        }
        healthTextView.setText(((SocialShareCenterApi) Services.c("PluginSocialShare", SocialShareCenterApi.class)).getShareNickName());
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        String picPath = userInfo != null ? userInfo.getPicPath() : null;
        if (TextUtils.isEmpty(picPath) || (cIe_ = nrf.cIe_(this.d, picPath)) == null) {
            return;
        }
        imageView.setImageBitmap(cIe_);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Track_MyPlanReportActivity", "onDestroy");
        this.c = null;
        this.l = null;
        asc.e().b(new b((System.currentTimeMillis() - this.r) / 1000, this.p));
    }

    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        mny f3057a;
        long c;

        b(long j, mny mnyVar) {
            this.c = j;
            this.f3057a = mnyVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", 1);
            mny mnyVar = this.f3057a;
            if (mnyVar == null) {
                LogUtil.h("Track_MyPlanReportActivity", "BiRunnable run mTrainReportBean is null.");
                return;
            }
            hashMap.put("type", Integer.valueOf((mnyVar.j() ? IntPlan.PlanType.AI_RUN_PLAN : IntPlan.PlanType.FIT_PLAN).getType()));
            String g = this.f3057a.g();
            if (!TextUtils.isEmpty(g)) {
                hashMap.put("plan_name", g);
            }
            hashMap.put("duration", Long.valueOf(this.c));
            ixx.d().d(BaseApplication.getContext(), "1120023", hashMap, 0);
        }
    }

    class d extends UiCallback<mny> {
        WeakReference<MyPlanReportActivity> e;

        d(MyPlanReportActivity myPlanReportActivity) {
            this.e = new WeakReference<>(myPlanReportActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Track_MyPlanReportActivity", "CurrentPlanUiCallback errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
            MyPlanReportActivity myPlanReportActivity = this.e.get();
            if (myPlanReportActivity == null) {
                LogUtil.h("Track_MyPlanReportActivity", "CurrentPlanRecordUiCallback onFailure activity is null.");
            } else {
                myPlanReportActivity.showErrorLayout();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(mny mnyVar) {
            MyPlanReportActivity myPlanReportActivity = this.e.get();
            if (myPlanReportActivity == null) {
                LogUtil.h("Track_MyPlanReportActivity", "CurrentPlanRecordUiCallback onSuccess activity is null.");
                return;
            }
            if (mnyVar != null) {
                mnyVar.b(MyPlanReportActivity.this.g == IntPlan.PlanType.AI_RUN_PLAN.getType());
                LogUtil.a("Track_MyPlanReportActivity", "CurrentPlanUiCallback onSuccess: ");
                myPlanReportActivity.p = mnyVar;
                myPlanReportActivity.b.sendEmptyMessage(0);
                return;
            }
            LogUtil.b("Track_MyPlanReportActivity", "data is null");
            myPlanReportActivity.showErrorLayout();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
