package com.huawei.health.suggestion.ui.runningposture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.health.suggestion.ui.fragment.PostureSuggestBaseFragment;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import defpackage.ffy;
import defpackage.gxq;
import defpackage.hjn;
import defpackage.koq;
import defpackage.nqx;
import defpackage.nrh;
import defpackage.smy;
import defpackage.squ;
import health.compact.a.CommonUtil;
import health.compact.a.ReflectionUtils;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class RuningPostureSuggestActivity extends BaseActivity implements View.OnClickListener {
    private nqx b;
    private PostureSuggestBaseFragment[] e;
    private LinearLayout f;
    private List<RunningPostureAdviceBase> g;
    private IntroPagerAdapter h;
    private c j;
    private HealthViewPager l;
    private View m;
    private HashMap<SportDetailChartDataType, List<gxq>> n;
    private HealthSubTabWidget o;
    private WifiReceiver p;
    private TranslateAnimation q;
    private String[] r;
    private TrainActionIntro s;
    private TranslateAnimation t;

    /* renamed from: a, reason: collision with root package name */
    protected int f3375a = 0;
    private int c = 0;
    private List<Motion> k = new ArrayList();
    private List d = new ArrayList();
    private boolean i = false;

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getIntExtra("runningPostureFlag", 0);
            try {
                this.g = intent.getParcelableArrayListExtra("runningPostureAdvices");
                Serializable serializableExtra = intent.getSerializableExtra("runningPostureJudgeList");
                if (serializableExtra instanceof HashMap) {
                    this.n = (HashMap) serializableExtra;
                }
            } catch (IndexOutOfBoundsException e) {
                LogUtil.h("Suggest_RuningPostureSuggestActivity", e.getMessage());
            }
        }
        List<RunningPostureAdviceBase> list = this.g;
        if (list != null) {
            this.r = new String[list.size()];
            for (int i = 0; i < this.g.size(); i++) {
                if (this.g.get(i) == null) {
                    LogUtil.h("Suggest_RuningPostureSuggestActivity", "posture advices mPostureAdvices.get(i) == null ", Integer.valueOf(i));
                    return;
                }
                this.r[i] = this.g.get(i).getText();
            }
        }
        List<RunningPostureAdviceBase> list2 = this.g;
        if (list2 == null || this.r == null || list2.size() != this.r.length) {
            LogUtil.h("Suggest_RuningPostureSuggestActivity", "posture advices length is error");
            finish();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        LogUtil.a("Suggest_RuningPostureSuggestActivity", "initViewController()");
        if (isFinishing()) {
            LogUtil.h("Suggest_RuningPostureSuggestActivity", "Activity is finishing");
            return;
        }
        setContentView(R.layout.sug_running_posture_suggest);
        getWindow().setFlags(16777216, 16777216);
        this.l = (HealthViewPager) findViewById(R.id.sug_running_posture_vp);
        this.e = new PostureSuggestBaseFragment[this.g.size()];
        for (int i = 0; i < this.g.size(); i++) {
            if (this.g.get(i) == null) {
                LogUtil.h("Suggest_RuningPostureSuggestActivity", "posture advices fragment mPostureAdvices.get(i) == null ", Integer.valueOf(i));
                return;
            }
            Object e = ReflectionUtils.e(this.g.get(i).getRunningPostureFragment());
            if (e == null) {
                LogUtil.h("Suggest_RuningPostureSuggestActivity", "posture advices fragment object == null ", this.g.get(i).getRunningPostureFragment());
            } else if (e instanceof PostureSuggestBaseFragment) {
                this.e[i] = (PostureSuggestBaseFragment) e;
            } else {
                LogUtil.h("Suggest_RuningPostureSuggestActivity", "posture advices not instanceof PostureSuggestBaseFragment error");
            }
        }
        this.o = (HealthSubTabWidget) findViewById(R.id.sug_running_posture_tab);
        this.l.setOffscreenPageLimit(this.r.length);
        this.b = new nqx(this, this.l, this.o);
        int i2 = 0;
        while (true) {
            String[] strArr = this.r;
            if (i2 < strArr.length) {
                smy c2 = this.o.c(strArr[i2]);
                Bundle bundle = new Bundle();
                bundle.putParcelable("runningPostureAdvice", this.g.get(i2));
                bundle.putSerializable("runningPostureJudgeList", this.n);
                PostureSuggestBaseFragment postureSuggestBaseFragment = this.e[i2];
                if (postureSuggestBaseFragment != null) {
                    postureSuggestBaseFragment.setArguments(bundle);
                    this.b.c(c2, this.e[i2], this.c == i2);
                }
                i2++;
            } else {
                c();
                runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.4
                    @Override // java.lang.Runnable
                    public void run() {
                        RuningPostureSuggestActivity.this.s.setAdapter(RuningPostureSuggestActivity.this.h);
                    }
                });
                this.p = new WifiReceiver();
                IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.setPriority(Integer.MAX_VALUE);
                registerReceiver(this.p, intentFilter);
                return;
            }
        }
    }

    private void c() {
        this.s = (TrainActionIntro) findViewById(R.id.runningpos_train_action_intro);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_fitness_getdata_error);
        this.f = linearLayout;
        linearLayout.setOnClickListener(this);
        this.m = findViewById(R.id.sug_running_posture_suggest_content);
        IntroPagerAdapter introPagerAdapter = new IntroPagerAdapter(this.d, R.layout.sug_traindetail_vp_intro);
        this.h = introPagerAdapter;
        introPagerAdapter.c(false);
        this.s.getPreAction().setOnClickListener(this);
        this.s.getNextAction().setOnClickListener(this);
        this.s.getTitleBar().setVisibility(0);
        this.s.getTitleBar().setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RuningPostureSuggestActivity runingPostureSuggestActivity = RuningPostureSuggestActivity.this;
                runingPostureSuggestActivity.downDismiss(runingPostureSuggestActivity.s);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.s.setOnSlidingListener(new OnSlidingListener() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.5
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSliding(float f) {
                RuningPostureSuggestActivity.this.m.setVisibility(0);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSlidingFinish(boolean z) {
                if (z) {
                    RuningPostureSuggestActivity.this.s.setVisibility(4);
                    RuningPostureSuggestActivity.this.m.setVisibility(0);
                } else {
                    RuningPostureSuggestActivity.this.s.setVisibility(0);
                    RuningPostureSuggestActivity.this.m.setVisibility(8);
                }
            }
        });
    }

    public void d() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (RuningPostureSuggestActivity.this.f != null) {
                    if (RuningPostureSuggestActivity.this.i == (!CommonUtil.aa(BaseApplication.getContext())) && RuningPostureSuggestActivity.this.f.getVisibility() == 0) {
                        return;
                    }
                    RuningPostureSuggestActivity.this.i = !CommonUtil.aa(BaseApplication.getContext());
                    HealthTextView healthTextView = (HealthTextView) RuningPostureSuggestActivity.this.f.findViewById(R.id.nfc_tip_title_text);
                    if (RuningPostureSuggestActivity.this.i) {
                        RuningPostureSuggestActivity.this.f.findViewById(R.id.ll_setting).setVisibility(0);
                        healthTextView.setText(R$string.IDS_hwh_home_group_network_disconnection);
                    } else {
                        RuningPostureSuggestActivity.this.f.findViewById(R.id.ll_setting).setVisibility(8);
                        healthTextView.setText(R$string.IDS_heart_rate_measuring_status_data_fail);
                    }
                    if (RuningPostureSuggestActivity.this.f.getVisibility() != 0) {
                        RuningPostureSuggestActivity.this.f.setVisibility(0);
                    }
                }
            }
        });
    }

    public void b() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (RuningPostureSuggestActivity.this.f == null || RuningPostureSuggestActivity.this.f.getVisibility() == 8) {
                    return;
                }
                RuningPostureSuggestActivity.this.f.setVisibility(8);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        List<Motion> list;
        super.onResume();
        TrainActionIntro trainActionIntro = this.s;
        if (trainActionIntro == null || trainActionIntro.getVisibility() != 0 || (list = this.k) == null || list.size() <= this.f3375a) {
            return;
        }
        h();
        if (j()) {
            return;
        }
        n();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        TrainActionIntro trainActionIntro = this.s;
        if (trainActionIntro != null && trainActionIntro.getVisibility() == 0) {
            downDismiss(this.s);
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        hjn.c().b();
        WifiReceiver wifiReceiver = this.p;
        if (wifiReceiver != null) {
            unregisterReceiver(wifiReceiver);
        }
    }

    public void upShow(final View view) {
        if (view == null) {
            LogUtil.h("Suggest_RuningPostureSuggestActivity", "upShow view == null");
            return;
        }
        if (this.t == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            this.t = translateAnimation;
            translateAnimation.setDuration(500L);
            this.t.setInterpolator(new DecelerateInterpolator());
            this.t.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.8
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LogUtil.a("Suggest_RuningPostureSuggestActivity", "onAnimationEnd");
                    RuningPostureSuggestActivity.this.m.setVisibility(8);
                }
            });
        }
        view.startAnimation(this.t);
    }

    public void downDismiss(final View view) {
        if (view == null) {
            LogUtil.h("Suggest_RuningPostureSuggestActivity", "downDismiss view is null");
            return;
        }
        TranslateAnimation translateAnimation = this.q;
        if (translateAnimation == null) {
            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            this.q = translateAnimation2;
            translateAnimation2.setDuration(500L);
            this.q.setInterpolator(new DecelerateInterpolator());
            this.q.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.6
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    RuningPostureSuggestActivity.this.m.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(4);
                }
            });
        } else if (!translateAnimation.hasEnded()) {
            return;
        }
        view.startAnimation(this.q);
    }

    private void a(int i) {
        Motion motion = (Motion) aLB_(this.k.get(i));
        this.d.clear();
        motion.saveMotionPath(squ.n(motion.acquireMotionPath()));
        this.d.add(motion);
        if (motion.acquireCovers() != null) {
            this.d.add(motion.acquireCovers());
        }
        if (j()) {
            return;
        }
        n();
    }

    private boolean j() {
        boolean e = e();
        LogUtil.a("Suggest_RuningPostureSuggestActivity", "isHasDownLoaded:", Boolean.valueOf(e));
        if (e) {
            this.h.c(true);
            this.h.notifyDataSetChanged();
            this.h.c(3);
        }
        return e;
    }

    private boolean e() {
        if (koq.b(this.k, this.f3375a)) {
            LogUtil.b("Suggest_RuningPostureSuggestActivity", "mShowMotions is out of bounds");
            return false;
        }
        return squ.s(this.k.get(this.f3375a).acquireMotionPath());
    }

    private void n() {
        if (koq.b(this.k, this.f3375a)) {
            return;
        }
        this.h.c(false);
        this.h.notifyDataSetChanged();
        if (koq.b(this.k, this.f3375a)) {
            LogUtil.b("Suggest_RuningPostureSuggestActivity", "mShowMotions is out of bounds");
            return;
        }
        if (CommonUtil.ah(BaseApplication.getContext())) {
            LogUtil.a("Suggest_RuningPostureSuggestActivity", JsNetwork.NetworkType.MOBILE);
            this.h.c(1);
            this.h.d(ffy.d(BaseApplication.getContext(), R$string.IDS_device_upgrade_file_size_mb, UnitUtil.e((this.k.get(this.f3375a).acquireLength() * 1.0f) / 1048576.0f, 1, 1)));
        } else {
            LogUtil.a("Suggest_RuningPostureSuggestActivity", "wifi");
            this.h.c(0);
            d(this.k.get(this.f3375a).acquireMotionPath());
        }
        this.h.c(new IntroPagerAdapter.IvDownLoadClick() { // from class: com.huawei.health.suggestion.ui.runningposture.RuningPostureSuggestActivity.10
            @Override // com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter.IvDownLoadClick
            public void onIvDownLoadClick() {
                RuningPostureSuggestActivity runingPostureSuggestActivity = RuningPostureSuggestActivity.this;
                runingPostureSuggestActivity.d(((Motion) runingPostureSuggestActivity.k.get(RuningPostureSuggestActivity.this.f3375a)).acquireMotionPath());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        LogUtil.a("Suggest_RuningPostureSuggestActivity", str);
        int size = this.k.size();
        int i = this.f3375a;
        if (size <= i) {
            LogUtil.b("Suggest_RuningPostureSuggestActivity", "the download position is wrong");
            return;
        }
        Motion motion = this.k.get(i);
        if (this.j != null) {
            if (motion.acquireMotionPath().equals(this.j.c) && !this.j.d) {
                LogUtil.h("Suggest_RuningPostureSuggestActivity", "the video is on downloading");
                return;
            } else if (!motion.acquireMotionPath().equals(this.j.c)) {
                LogUtil.h("Suggest_RuningPostureSuggestActivity", "canceled the video download task");
                this.j.d = true;
            }
        }
        c cVar = new c();
        this.j = cVar;
        cVar.c = motion.acquireMotionPath();
        Media media = new Media();
        media.setUrl(motion.acquireMotionPath());
        media.setPath(squ.n(media.getUrl()));
        media.setLength(motion.acquireLength());
        media.setType(1);
        this.h.c(0);
        this.h.c(UnitUtil.e(0.0d, 2, 0));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(media);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggest_RuningPostureSuggestActivity", "toDownloadWorkoutMediaFile : courseApi is null.");
            return;
        }
        try {
            courseApi.downloadCourseMediaFileList(arrayList, media.getLength(), this.j);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("Suggest_RuningPostureSuggestActivity", "download mUrl is invalid:", str);
        }
    }

    private void c(int i) {
        if (i == 0) {
            this.s.getPreAction().setVisibility(4);
            if (i == this.k.size() - 1) {
                this.s.getNextAction().setVisibility(4);
                return;
            } else {
                this.s.getNextAction().setVisibility(0);
                return;
            }
        }
        if (i == this.k.size() - 1) {
            this.s.getPreAction().setVisibility(0);
            this.s.getNextAction().setVisibility(4);
        } else {
            this.s.getPreAction().setVisibility(0);
            this.s.getNextAction().setVisibility(0);
        }
    }

    private void g() {
        int i = this.f3375a + 1;
        this.f3375a = i;
        LogUtil.a("Suggest_RuningPostureSuggestActivity", "show next action", Integer.valueOf(i));
        if (this.f3375a < this.k.size()) {
            h();
            this.s.setCurrentIndex(this.f3375a + 1);
            a(this.f3375a);
            c(this.f3375a);
            return;
        }
        this.f3375a = this.k.size() - 1;
    }

    private void i() {
        int i = this.f3375a - 1;
        this.f3375a = i;
        LogUtil.a("Suggest_RuningPostureSuggestActivity", "show pre action", Integer.valueOf(i));
        if (this.f3375a >= 0) {
            h();
            this.s.setCurrentIndex(this.f3375a + 1);
            a(this.f3375a);
            c(this.f3375a);
            return;
        }
        this.f3375a = 0;
    }

    private void h() {
        this.h.c(4);
    }

    class c extends UiCallback<String> {
        private String c;
        private boolean d = false;

        c() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggest_RuningPostureSuggestActivity", "errorCode:", Integer.valueOf(i), "errorInfo:", str);
            if (CommonUtil.ah(BaseApplication.getContext())) {
                LogUtil.b("Suggest_RuningPostureSuggestActivity", "onFailure1");
                RuningPostureSuggestActivity.this.h.c(1);
            } else {
                LogUtil.b("Suggest_RuningPostureSuggestActivity", "onFailure2");
                RuningPostureSuggestActivity.this.h.c(2);
            }
            RuningPostureSuggestActivity.this.j.d = true;
            nrh.b(RuningPostureSuggestActivity.this, R.string._2130848735_res_0x7f022bdf);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            RuningPostureSuggestActivity.this.h.c(true);
            RuningPostureSuggestActivity.this.h.notifyDataSetChanged();
            RuningPostureSuggestActivity.this.h.c(3);
            LogUtil.a("Suggest_RuningPostureSuggestActivity", "MediaFileItemDownLoadCallBack_success");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public boolean isCanceled() {
            return this.d;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onProgress(long j, long j2) {
            super.onProgress(j, j2);
            LogUtil.a("Suggest_RuningPostureSuggestActivity", "handleBytes:", Long.valueOf(j), "-contentLength:", Long.valueOf(j2));
            if (j <= j2) {
                RuningPostureSuggestActivity.this.h.c(UnitUtil.e(((j * 1.0f) / j2) * 100.0f, 2, 0));
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("Suggest_RuningPostureSuggestActivity", "onClick view = null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.sug_coach_iv_action_pre) {
            i();
        } else if (view.getId() == R.id.sug_coach_iv_action_nex) {
            g();
        } else if (view.getId() == R.id.ll_fitness_getdata_error) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        b();
        if (this.i) {
            CommonUtil.q(this);
        } else {
            f();
        }
    }

    private void f() {
        PostureSuggestBaseFragment[] postureSuggestBaseFragmentArr = this.e;
        if (postureSuggestBaseFragmentArr != null) {
            for (PostureSuggestBaseFragment postureSuggestBaseFragment : postureSuggestBaseFragmentArr) {
                if (postureSuggestBaseFragment == null) {
                    LogUtil.h("Suggest_RuningPostureSuggestActivity", "postureSuggestBaseFragment == null");
                } else {
                    postureSuggestBaseFragment.b();
                }
            }
            return;
        }
        LogUtil.h("Suggest_RuningPostureSuggestActivity", "setFragmentActionData fragments is null");
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
        LogUtil.a("Suggest_RuningPostureSuggestActivity", "initViewController()");
    }

    public static <T> T aLB_(Parcelable parcelable) {
        Parcel parcel;
        try {
            parcel = Parcel.obtain();
            try {
                parcel.writeParcelable(parcelable, 0);
                parcel.setDataPosition(0);
                T t = (T) parcel.readParcelable(parcelable.getClass().getClassLoader());
                if (parcel != null) {
                    parcel.recycle();
                }
                return t;
            } catch (Throwable th) {
                th = th;
                if (parcel != null) {
                    parcel.recycle();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            parcel = null;
        }
    }

    public class WifiReceiver extends BroadcastReceiver {
        public WifiReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Suggest_RuningPostureSuggestActivity", "onReceive");
            if (CommonUtil.aa(BaseApplication.getContext())) {
                RuningPostureSuggestActivity.this.b();
                RuningPostureSuggestActivity.this.o();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        PostureSuggestBaseFragment[] postureSuggestBaseFragmentArr = this.e;
        if (postureSuggestBaseFragmentArr != null) {
            for (PostureSuggestBaseFragment postureSuggestBaseFragment : postureSuggestBaseFragmentArr) {
                if (postureSuggestBaseFragment == null) {
                    LogUtil.h("Suggest_RuningPostureSuggestActivity", "postureSuggestBaseFragment == null");
                } else {
                    postureSuggestBaseFragment.b();
                }
            }
            return;
        }
        LogUtil.h("Suggest_RuningPostureSuggestActivity", "setFragmentActionDataFromWifi mFragments is null");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        this.s.c();
        this.h.c();
    }
}
