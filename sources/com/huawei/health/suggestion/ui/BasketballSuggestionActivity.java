package com.huawei.health.suggestion.ui;

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
import com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.health.suggestion.ui.fragment.AverageJumpHeightFragment;
import com.huawei.health.suggestion.ui.fragment.AverageJumpTimeFragment;
import com.huawei.health.suggestion.ui.fragment.BasketballSuggestionBaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.BasketballAdvice;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.watchface.mvp.model.webview.JsNetwork;
import defpackage.ffy;
import defpackage.koq;
import defpackage.nqx;
import defpackage.nrh;
import defpackage.smy;
import defpackage.squ;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class BasketballSuggestionActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private View f3046a;
    private TranslateAnimation b;
    private HealthViewPager c;
    private List<BasketballAdvice> d;
    private TranslateAnimation e;
    private BasketballSuggestionBaseFragment[] f;
    private nqx h;
    private LinearLayout j;
    private IntroPagerAdapter m;
    private c n;
    private HealthSubTabWidget p;
    private TrainActionIntro q;
    private b s;
    private String[] t;
    private int i = 0;
    private int g = 0;
    private List<Motion> l = new ArrayList(10);
    private List k = new ArrayList(10);
    private boolean o = false;

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
        LogUtil.a("Suggestion_BasketballSuggestionActivity", "initViewController()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            this.g = intent.getIntExtra("basketballFlag", 0);
            try {
                this.d = intent.getParcelableArrayListExtra("basketballAdviceList");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("Suggestion_BasketballSuggestionActivity", LogAnonymous.b((Throwable) e));
            }
        }
        this.t = new String[]{getResources().getString(R$string.IDS_aw_version2_average_jump_height_full), getResources().getString(R$string.IDS_aw_version2_average_jump_time_full)};
        List<BasketballAdvice> list = this.d;
        if (list == null || list.size() != this.t.length) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "basketball advice length error");
            finish();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        LogUtil.a("Suggestion_BasketballSuggestionActivity", "initViewController()");
        if (isFinishing()) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "Activity is finishing");
            return;
        }
        setContentView(R.layout.sug_basketball_suggest);
        getWindow().setFlags(16777216, 16777216);
        this.c = (HealthViewPager) findViewById(R.id.sug_basketball_suggest_view_pager);
        this.f = new BasketballSuggestionBaseFragment[]{new AverageJumpHeightFragment(), new AverageJumpTimeFragment()};
        this.p = (HealthSubTabWidget) findViewById(R.id.sug_basketball_tab);
        this.c.setOffscreenPageLimit(this.t.length);
        this.h = new nqx(this, this.c, this.p);
        int i = 0;
        while (true) {
            String[] strArr = this.t;
            if (i < strArr.length) {
                smy c2 = this.p.c(strArr[i]);
                Bundle bundle = new Bundle();
                bundle.putParcelable("basketballAdvice", this.d.get(i));
                this.f[i].setArguments(bundle);
                this.h.c(c2, this.f[i], this.g == i);
                i++;
            } else {
                a();
                runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BasketballSuggestionActivity.this.q.setAdapter(BasketballSuggestionActivity.this.m);
                    }
                });
                this.s = new b();
                IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
                intentFilter.setPriority(Integer.MAX_VALUE);
                registerReceiver(this.s, intentFilter);
                return;
            }
        }
    }

    private void a() {
        this.q = (TrainActionIntro) findViewById(R.id.basketball_train_action_intro);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_fitness_getdata_error);
        this.j = linearLayout;
        linearLayout.setOnClickListener(this);
        this.f3046a = findViewById(R.id.sug_basketball_suggest_content);
        IntroPagerAdapter introPagerAdapter = new IntroPagerAdapter(this.k, R.layout.sug_traindetail_vp_intro);
        this.m = introPagerAdapter;
        introPagerAdapter.c(false);
        this.q.getPreAction().setOnClickListener(this);
        this.q.getNextAction().setOnClickListener(this);
        this.q.getTitleBar().setVisibility(0);
        this.q.getTitleBar().setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BasketballSuggestionActivity basketballSuggestionActivity = BasketballSuggestionActivity.this;
                basketballSuggestionActivity.downDismiss(basketballSuggestionActivity.q);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.q.setOnSlidingListener(new OnSlidingListener() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.5
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSliding(float f) {
                BasketballSuggestionActivity.this.f3046a.setVisibility(0);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSlidingFinish(boolean z) {
                if (z) {
                    BasketballSuggestionActivity.this.q.setVisibility(4);
                    BasketballSuggestionActivity.this.f3046a.setVisibility(0);
                } else {
                    BasketballSuggestionActivity.this.q.setVisibility(0);
                    BasketballSuggestionActivity.this.f3046a.setVisibility(8);
                }
            }
        });
    }

    public void e() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (BasketballSuggestionActivity.this.j != null) {
                    if (BasketballSuggestionActivity.this.o == (!CommonUtil.aa(BaseApplication.getContext())) && BasketballSuggestionActivity.this.j.getVisibility() == 0) {
                        return;
                    }
                    HealthTextView healthTextView = (HealthTextView) BasketballSuggestionActivity.this.j.findViewById(R.id.nfc_tip_title_text);
                    BasketballSuggestionActivity.this.o = !CommonUtil.aa(BaseApplication.getContext());
                    if (BasketballSuggestionActivity.this.o) {
                        BasketballSuggestionActivity.this.j.findViewById(R.id.ll_setting).setVisibility(0);
                        healthTextView.setText(R$string.IDS_hwh_home_group_network_disconnection);
                    } else {
                        BasketballSuggestionActivity.this.j.findViewById(R.id.ll_setting).setVisibility(8);
                        healthTextView.setText(R$string.IDS_heart_rate_measuring_status_data_fail);
                    }
                    if (BasketballSuggestionActivity.this.j.getVisibility() != 0) {
                        BasketballSuggestionActivity.this.j.setVisibility(0);
                    }
                }
            }
        });
    }

    public void d() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (BasketballSuggestionActivity.this.j == null || BasketballSuggestionActivity.this.j.getVisibility() == 8) {
                    return;
                }
                BasketballSuggestionActivity.this.j.setVisibility(8);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        List<Motion> list;
        super.onResume();
        TrainActionIntro trainActionIntro = this.q;
        if (trainActionIntro == null || trainActionIntro.getVisibility() != 0 || (list = this.l) == null || list.size() <= this.i) {
            return;
        }
        h();
        if (f()) {
            return;
        }
        k();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        TrainActionIntro trainActionIntro = this.q;
        if (trainActionIntro != null && trainActionIntro.getVisibility() == 0) {
            downDismiss(this.q);
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        b bVar = this.s;
        if (bVar != null) {
            unregisterReceiver(bVar);
        }
    }

    public void d(int i, List<Motion> list) {
        this.q.b(i + 1, list.size());
        this.l = list;
        this.i = i;
        upShow(this.q);
        h();
        d(this.i);
        e(i);
    }

    public void upShow(final View view) {
        if (view == null) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "upShow, view is null");
            return;
        }
        if (this.e == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            this.e = translateAnimation;
            translateAnimation.setDuration(500L);
            this.e.setInterpolator(new DecelerateInterpolator());
            this.e.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.9
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LogUtil.a("Suggestion_BasketballSuggestionActivity", "onAnimationEnd");
                    BasketballSuggestionActivity.this.f3046a.setVisibility(8);
                }
            });
        }
        view.startAnimation(this.e);
    }

    public void downDismiss(final View view) {
        if (view == null) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "downDismiss, view is null");
            return;
        }
        TranslateAnimation translateAnimation = this.b;
        if (translateAnimation == null) {
            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            this.b = translateAnimation2;
            translateAnimation2.setDuration(500L);
            this.b.setInterpolator(new DecelerateInterpolator());
            this.b.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.10
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    BasketballSuggestionActivity.this.f3046a.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(4);
                }
            });
        } else if (!translateAnimation.hasEnded()) {
            return;
        }
        view.startAnimation(this.b);
    }

    private void d(int i) {
        if (koq.b(this.l, i)) {
            return;
        }
        Motion motion = (Motion) ayr_(this.l.get(i));
        this.k.clear();
        motion.saveMotionPath(squ.n(motion.acquireMotionPath()));
        this.k.add(motion);
        if (motion.acquireCovers() != null) {
            this.k.add(motion.acquireCovers());
        }
        if (f()) {
            return;
        }
        k();
    }

    private boolean f() {
        boolean i = i();
        LogUtil.a("Suggestion_BasketballSuggestionActivity", "isDownload:", Boolean.valueOf(i));
        if (i) {
            this.m.c(true);
            this.m.notifyDataSetChanged();
            this.m.c(3);
        }
        return i;
    }

    private boolean i() {
        if (koq.b(this.l, this.i)) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "isDownload, mCurrentActionPosition out of bounds");
            return false;
        }
        return squ.s(this.l.get(this.i).acquireMotionPath());
    }

    private void k() {
        this.m.c(false);
        this.m.notifyDataSetChanged();
        if (koq.b(this.l, this.i)) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "showMask, mCurrentActionPosition out of bounds");
            return;
        }
        if (CommonUtil.ah(BaseApplication.getContext())) {
            LogUtil.a("Suggestion_BasketballSuggestionActivity", JsNetwork.NetworkType.MOBILE);
            this.m.c(1);
            this.m.d(ffy.d(BaseApplication.getContext(), R$string.IDS_device_upgrade_file_size_mb, UnitUtil.e((this.l.get(this.i).acquireLength() * 1.0f) / 1048576.0f, 1, 1)));
        } else {
            LogUtil.a("Suggestion_BasketballSuggestionActivity", "wifi");
            this.m.c(0);
            c(this.l.get(this.i).acquireMotionPath());
        }
        this.m.c(new IntroPagerAdapter.IvDownLoadClick() { // from class: com.huawei.health.suggestion.ui.BasketballSuggestionActivity.8
            @Override // com.huawei.health.suggestion.ui.fitness.helper.IntroPagerAdapter.IvDownLoadClick
            public void onIvDownLoadClick() {
                if (koq.b(BasketballSuggestionActivity.this.l, BasketballSuggestionActivity.this.i)) {
                    return;
                }
                BasketballSuggestionActivity basketballSuggestionActivity = BasketballSuggestionActivity.this;
                basketballSuggestionActivity.c(((Motion) basketballSuggestionActivity.l.get(BasketballSuggestionActivity.this.i)).acquireMotionPath());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogUtil.a("Suggestion_BasketballSuggestionActivity", str);
        int size = this.l.size();
        int i = this.i;
        if (size <= i) {
            LogUtil.b("Suggestion_BasketballSuggestionActivity", "the download position is wrong");
            return;
        }
        Motion motion = this.l.get(i);
        if (this.n != null) {
            if (motion.acquireMotionPath().equals(this.n.e) && !this.n.d) {
                LogUtil.h("Suggestion_BasketballSuggestionActivity", "the video is on downloading");
                return;
            } else if (!motion.acquireMotionPath().equals(this.n.e)) {
                LogUtil.h("Suggestion_BasketballSuggestionActivity", "canceled the video download task");
                this.n.d = true;
            }
        }
        c cVar = new c();
        this.n = cVar;
        cVar.e = motion.acquireMotionPath();
        Media media = new Media();
        media.setUrl(motion.acquireMotionPath());
        media.setPath(squ.n(media.getUrl()));
        media.setLength(motion.acquireLength());
        media.setType(1);
        this.m.c(0);
        this.m.c(UnitUtil.e(0.0d, 2, 0));
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(media);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "toDownloadWorkoutMediaFile : courseApi is null.");
            return;
        }
        try {
            courseApi.downloadCourseMediaFileList(arrayList, media.getLength(), this.n);
        } catch (IllegalArgumentException e) {
            LogUtil.b("Suggestion_BasketballSuggestionActivity", "download mUrl is invalid:", LogAnonymous.b((Throwable) e));
        }
    }

    private void e(int i) {
        if (i == 0) {
            this.q.getPreAction().setVisibility(4);
            if (i == this.l.size() - 1) {
                this.q.getNextAction().setVisibility(4);
                return;
            } else {
                this.q.getNextAction().setVisibility(0);
                return;
            }
        }
        if (i == this.l.size() - 1) {
            this.q.getPreAction().setVisibility(0);
            this.q.getNextAction().setVisibility(4);
        } else {
            this.q.getPreAction().setVisibility(0);
            this.q.getNextAction().setVisibility(0);
        }
    }

    private void g() {
        int i = this.i + 1;
        this.i = i;
        LogUtil.a("Suggestion_BasketballSuggestionActivity", "show next action ", Integer.valueOf(i));
        if (this.i < this.l.size()) {
            h();
            this.q.setCurrentIndex(this.i + 1);
            d(this.i);
            e(this.i);
            return;
        }
        this.i = this.l.size() - 1;
    }

    private void j() {
        int i = this.i - 1;
        this.i = i;
        LogUtil.a("Suggestion_BasketballSuggestionActivity", "show pre action", Integer.valueOf(i));
        if (this.i >= 0) {
            h();
            this.q.setCurrentIndex(this.i + 1);
            d(this.i);
            e(this.i);
            return;
        }
        this.i = 0;
    }

    private void h() {
        this.m.c(4);
    }

    class c extends UiCallback<String> {
        private boolean d = false;
        private String e;

        c() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Suggestion_BasketballSuggestionActivity", "errorCode:", Integer.valueOf(i), "errorInfo:", str);
            if (CommonUtil.ah(BaseApplication.getContext())) {
                LogUtil.h("Suggestion_BasketballSuggestionActivity", "onFailure1");
                BasketballSuggestionActivity.this.m.c(1);
            } else {
                LogUtil.h("Suggestion_BasketballSuggestionActivity", "onFailure2");
                BasketballSuggestionActivity.this.m.c(2);
            }
            BasketballSuggestionActivity.this.n.d = true;
            nrh.b(BasketballSuggestionActivity.this, R.string._2130848735_res_0x7f022bdf);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            BasketballSuggestionActivity.this.m.c(true);
            BasketballSuggestionActivity.this.m.notifyDataSetChanged();
            BasketballSuggestionActivity.this.m.c(3);
            LogUtil.a("Suggestion_BasketballSuggestionActivity", "MediaFileItemDownLoadCallBack_success");
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public boolean isCanceled() {
            return this.d;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onProgress(long j, long j2) {
            super.onProgress(j, j2);
            LogUtil.a("Suggestion_BasketballSuggestionActivity", "handleBytes:", Long.valueOf(j), "-contentLength:", Long.valueOf(j2));
            if (j <= j2) {
                BasketballSuggestionActivity.this.m.c(UnitUtil.e(((j * 1.0f) / j2) * 100.0f, 2, 0));
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.sug_coach_iv_action_pre) {
            j();
        } else if (view.getId() == R.id.sug_coach_iv_action_nex) {
            g();
        } else if (view.getId() == R.id.ll_fitness_getdata_error) {
            b();
        } else {
            LogUtil.a("Suggestion_BasketballSuggestionActivity", "onClick()");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        d();
        if (this.o) {
            CommonUtil.q(this);
        } else {
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        BasketballSuggestionBaseFragment[] basketballSuggestionBaseFragmentArr = this.f;
        if (basketballSuggestionBaseFragmentArr != null) {
            for (BasketballSuggestionBaseFragment basketballSuggestionBaseFragment : basketballSuggestionBaseFragmentArr) {
                if (basketballSuggestionBaseFragment != null) {
                    basketballSuggestionBaseFragment.e();
                }
            }
        }
    }

    public static <T> T ayr_(Parcelable parcelable) {
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

    class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("Suggestion_BasketballSuggestionActivity", "onReceive");
            if (intent == null || intent.getAction() == null || !"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || !CommonUtil.aa(BaseApplication.getContext())) {
                return;
            }
            BasketballSuggestionActivity.this.d();
            BasketballSuggestionActivity.this.c();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        this.q.c();
        IntroPagerAdapter introPagerAdapter = this.m;
        if (introPagerAdapter != null) {
            introPagerAdapter.c();
        }
    }
}
