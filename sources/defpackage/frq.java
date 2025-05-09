package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.fitness.helper.ActionDetailAdapter;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener;
import com.huawei.health.suggestion.ui.fitness.module.TrainActionIntro;
import com.huawei.health.suggestion.ui.fitness.mvp.CourseDetailProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class frq implements View.OnClickListener, ActionDetailAdapter.ActionDetailViewClickEvent {

    /* renamed from: a, reason: collision with root package name */
    private int f12625a;
    private Context b;
    private CourseDetailProvider c;
    private ActionDetailAdapter d;
    private View e;
    private TrainActionIntro g;
    private b i;
    private TranslateAnimation j;
    private TranslateAnimation l;
    private List f = new ArrayList();
    private List<Motion> h = new ArrayList();

    @Override // com.huawei.health.suggestion.ui.fitness.helper.ActionDetailAdapter.ActionDetailViewClickEvent
    public void onCloseImageClick() {
    }

    public frq(Context context, TrainActionIntro trainActionIntro, View view, CourseDetailProvider courseDetailProvider) {
        this.b = context;
        this.g = trainActionIntro;
        BaseActivity.setViewSafeRegion(false, trainActionIntro);
        this.e = view;
        this.c = courseDetailProvider;
        p();
        i();
    }

    public void a(int i, List<Motion> list) {
        this.f12625a = i;
        if (list != null) {
            this.h = list;
        }
        if (this.c.isLongVideoCourse()) {
            this.d.b(true);
            this.c.getLongVideo(i, new d(this, i));
        }
        d(i, null);
        a(true);
    }

    public void d() {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            actionDetailAdapter.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        int i2 = this.f12625a;
        if (i != i2) {
            ReleaseLogUtil.d("Suggestion_CourseActionIntroViewHolder", "refreshUi failed with error postion ", Integer.valueOf(i), " mCurrPosition ", Integer.valueOf(this.f12625a));
            return;
        }
        this.g.b(i2 + 1, this.h.size());
        if (!TextUtils.isEmpty(str)) {
            this.d.a(str);
        }
        aFf_(this.g);
        l();
        d(this.f12625a);
        a(this.f12625a);
    }

    private void p() {
        ReleaseLogUtil.e("Suggestion_CourseActionIntroViewHolder", "setTrainViewClick enter");
        if (this.g == null) {
            return;
        }
        m();
        this.g.getPreAction().setOnClickListener(this);
        this.g.getNextAction().setOnClickListener(this);
        this.g.setOnSlidingListener(new OnSlidingListener() { // from class: frq.4
            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSliding(float f) {
                frq.this.e.setVisibility(0);
            }

            @Override // com.huawei.health.suggestion.ui.fitness.module.OnSlidingListener
            public void onSlidingFinish(boolean z) {
                if (z) {
                    frq.this.g.setVisibility(4);
                    frq.this.e.setVisibility(0);
                    if (frq.this.d != null) {
                        frq.this.d.d();
                        return;
                    }
                    return;
                }
                frq.this.g.setVisibility(0);
                frq.this.e.setVisibility(8);
            }
        });
    }

    private void i() {
        ActionDetailAdapter actionDetailAdapter = new ActionDetailAdapter(this.f);
        this.d = actionDetailAdapter;
        actionDetailAdapter.a(this);
        this.d.c(false);
        this.g.setAdapter(this.d);
    }

    private void m() {
        if (this.g.getTitleBar() == null) {
            return;
        }
        this.g.getTitleBar().setVisibility(0);
        this.g.getTitleBar().setLeftButtonOnClickListener(new View.OnClickListener() { // from class: frq.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                frq frqVar = frq.this;
                frqVar.aFd_(frqVar.g);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(boolean z) {
        Context context = this.b;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.getWindow() == null) {
                LogUtil.h("Suggestion_CourseActionIntroViewHolder", "keepScreenOnControl() activity is isFinishing or getWindow() is null");
            } else if (z) {
                activity.getWindow().addFlags(128);
            } else {
                activity.getWindow().clearFlags(128);
            }
        }
    }

    public void aFd_(final View view) {
        a(false);
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            actionDetailAdapter.d();
        }
        TranslateAnimation translateAnimation = this.j;
        if (translateAnimation == null) {
            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            this.j = translateAnimation2;
            translateAnimation2.setDuration(400L);
            this.j.setInterpolator(new DecelerateInterpolator());
            this.j.setAnimationListener(new Animation.AnimationListener() { // from class: frq.5
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    frq.this.e.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(4);
                }
            });
        } else if (!translateAnimation.hasEnded()) {
            return;
        }
        view.startAnimation(this.j);
    }

    private int j() {
        return this.h.size();
    }

    public void aFf_(final View view) {
        if (this.l == null) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
            this.l = translateAnimation;
            translateAnimation.setDuration(400L);
            this.l.setInterpolator(new DecelerateInterpolator());
            this.l.setAnimationListener(new Animation.AnimationListener() { // from class: frq.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LogUtil.a("Suggestion_CourseActionIntroViewHolder", "onAnimationEnd");
                    frq.this.e.setVisibility(8);
                }
            });
        }
        view.startAnimation(this.l);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.sug_coach_iv_action_pre) {
            if (this.c.isLongVideoCourse()) {
                h();
            } else {
                n();
            }
        } else if (id == R.id.sug_coach_iv_action_nex) {
            if (this.c.isLongVideoCourse()) {
                k();
            } else {
                o();
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n() {
        this.f12625a--;
        b bVar = this.i;
        if (bVar != null) {
            bVar.e(true);
        }
        if (this.f12625a >= 0) {
            l();
            this.g.setCurrentIndex(this.f12625a + 1);
            d(this.f12625a);
            a(this.f12625a);
            return;
        }
        this.f12625a = 0;
    }

    private void o() {
        this.f12625a++;
        b bVar = this.i;
        if (bVar != null) {
            bVar.e(true);
        }
        if (this.f12625a < j()) {
            l();
            this.g.setCurrentIndex(this.f12625a + 1);
            d(this.f12625a);
            a(this.f12625a);
            return;
        }
        this.f12625a = j() - 1;
    }

    private void l() {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter == null || actionDetailAdapter.b() == null) {
            return;
        }
        this.d.b().refreshHeaderView(4);
    }

    private void d(int i) {
        List<Motion> list = this.h;
        if (list == null || i < 0 || i >= list.size()) {
            LogUtil.h("Suggestion_CourseActionIntroViewHolder", "refreshMotionIntro, position index out of bound");
            return;
        }
        Motion motion = this.h.get(i);
        this.f.clear();
        this.f.add(motion);
        if (motion.acquireCovers() != null) {
            this.f.add(motion.acquireCovers());
        }
        if (f()) {
            return;
        }
        b(i);
    }

    private void a(int i) {
        if (i == 0) {
            this.g.getPreAction().setVisibility(4);
            this.g.getNextAction().setVisibility(0);
        } else if (i == this.h.size() - 1) {
            this.g.getPreAction().setVisibility(0);
            this.g.getNextAction().setVisibility(4);
        } else {
            this.g.getPreAction().setVisibility(0);
            this.g.getNextAction().setVisibility(0);
        }
    }

    private boolean f() {
        ActionDetailAdapter actionDetailAdapter;
        boolean isCurActionResourceDownload = this.c.isCurActionResourceDownload(this.f12625a);
        LogUtil.a("Suggestion_CourseActionIntroViewHolder", "isHasDownLoaded:", Boolean.valueOf(isCurActionResourceDownload));
        if (isCurActionResourceDownload && (actionDetailAdapter = this.d) != null) {
            actionDetailAdapter.c(true);
            this.d.notifyDataSetChanged();
            this.d.b().refreshHeaderView(3);
        }
        return isCurActionResourceDownload;
    }

    private void b(int i) {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter == null) {
            return;
        }
        actionDetailAdapter.c(false);
        this.d.notifyDataSetChanged();
        if (CommonUtil.ah(BaseApplication.getContext())) {
            LogUtil.a("Suggestion_CourseActionIntroViewHolder", "NETWORK_TYPE_INVALID");
            if (this.d.b() != null) {
                this.d.b().refreshHeaderView(1);
            }
            this.d.d(ffy.d(BaseApplication.getContext(), R$string.IDS_device_upgrade_file_size_mb, UnitUtil.e((this.c.getCourseActionMediaSize(i) * 1.0f) / 1048576.0f, 1, 1)));
        } else {
            LogUtil.a("Suggestion_CourseActionIntroViewHolder", "wifi");
            if (this.d.b() != null) {
                this.d.b().refreshHeaderView(0);
            }
            c(i);
        }
        this.d.a(i);
    }

    private void c(int i) {
        LogUtil.a("Suggestion_CourseActionIntroViewHolder", "position:", Integer.valueOf(i));
        b bVar = this.i;
        if (bVar == null || i != bVar.b() || this.i.isCanceled()) {
            ActionDetailAdapter actionDetailAdapter = this.d;
            if (actionDetailAdapter != null && actionDetailAdapter.b() != null) {
                this.d.b().refreshHeaderView(0);
            }
            this.i = new b(this);
            ActionDetailAdapter actionDetailAdapter2 = this.d;
            if (actionDetailAdapter2 == null || this.c == null) {
                ReleaseLogUtil.d("Suggestion_CourseActionIntroViewHolder", "toDownLoadWorkoutMediaFile: mActionDetailAdapter mMediaFileUiCallBack or mCourseApi is null");
                return;
            }
            actionDetailAdapter2.b(UnitUtil.e(0.0d, 2, 0));
            this.i.b(i);
            this.c.downloadCurActionResource(i, this.i);
        }
    }

    public void aFe_(Configuration configuration) {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter == null || actionDetailAdapter.b() == null) {
            return;
        }
        this.d.b().b();
    }

    public void b() {
        TrainActionIntro trainActionIntro = this.g;
        if (trainActionIntro == null || trainActionIntro.getVisibility() != 0) {
            return;
        }
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            actionDetailAdapter.c();
        }
        l();
        if (f()) {
            return;
        }
        b(this.f12625a);
    }

    private void s() {
        this.d.notifyDataSetChanged();
        this.d.a(-1);
    }

    private void e(int i) {
        if (i < 0 || i >= j()) {
            LogUtil.h("Suggestion_CourseActionIntroViewHolder", "refreshMotionIntro, position index out of bound");
            return;
        }
        Motion motion = this.h.get(i);
        this.f.clear();
        this.f.add(motion);
        if (motion.acquireCovers() != null) {
            this.f.add(motion.acquireCovers());
        }
        s();
    }

    private void k() {
        int i = this.f12625a + 1;
        this.f12625a = i;
        if (i < this.h.size()) {
            this.g.setCurrentIndex(this.f12625a + 1);
            e(this.f12625a);
            a(this.f12625a);
            return;
        }
        this.f12625a = this.h.size() - 1;
    }

    private void h() {
        int i = this.f12625a;
        int i2 = i - 1;
        this.f12625a = i2;
        if (i2 >= 0) {
            this.g.setCurrentIndex(i);
            e(this.f12625a);
            a(this.f12625a);
            return;
        }
        this.f12625a = 0;
    }

    public boolean e() {
        TrainActionIntro trainActionIntro = this.g;
        if (trainActionIntro == null || trainActionIntro.getVisibility() != 0) {
            return false;
        }
        LogUtil.a("Suggestion_CourseActionIntroViewHolder", " onBackPressed success.");
        aFd_(this.g);
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.ActionDetailAdapter.ActionDetailViewClickEvent
    public void onDownLoadClick(int i) {
        if (this.c.isLongVideoCourse()) {
            this.d.b().getVideoPlayerStrategy().initMediaPlayer();
        } else {
            c(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            c(actionDetailAdapter);
        }
        this.i.e(true);
        nrh.b(BaseApplication.getContext(), R.string._2130848735_res_0x7f022bdf);
    }

    private void c(ActionDetailAdapter actionDetailAdapter) {
        if (actionDetailAdapter == null || actionDetailAdapter.b() == null) {
            LogUtil.h("Suggestion_CourseActionIntroViewHolder", "refreshNetWorkStatus, actionDetailAdapter or actionDetailAdapter.getActionDetailView is null");
        } else if (CommonUtil.ah(BaseApplication.getContext())) {
            actionDetailAdapter.b().refreshHeaderView(1);
        } else {
            actionDetailAdapter.b().refreshHeaderView(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            actionDetailAdapter.c(true);
            this.d.notifyDataSetChanged();
        }
        LogUtil.a("Suggestion_CourseActionIntroViewHolder", "MediaFileItemDownLoadCallBack_success");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, long j2) {
        if (j <= j2) {
            this.d.b(UnitUtil.e(((j * 1.0f) / j2) * 100.0f, 2, 0));
        }
    }

    public void a() {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            actionDetailAdapter.a();
        }
    }

    public void e(String str) {
        ActionDetailAdapter actionDetailAdapter = this.d;
        if (actionDetailAdapter != null) {
            actionDetailAdapter.e(FitWorkout.acquireComeFrom(str));
        }
    }

    static class b extends UiCallback<String> {
        private WeakReference<frq> e;
        private boolean c = false;
        private int b = 0;

        b(frq frqVar) {
            if (this.e == null) {
                this.e = new WeakReference<>(frqVar);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("Suggestion_CourseActionIntroViewHolder", "errorCode:", Integer.valueOf(i), "errorInfo:", str);
            frq frqVar = this.e.get();
            if (e(frqVar, "courseActionIntroViewHolder", "onFailure")) {
                frqVar.c();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            frq frqVar = this.e.get();
            if (e(frqVar, "courseActionIntroViewHolder", "onSuccess")) {
                frqVar.g();
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onProgress(long j, long j2) {
            super.onProgress(j, j2);
            ReleaseLogUtil.e("Suggestion_CourseActionIntroViewHolder", "handleBytes:", Long.valueOf(j), "-contentLength:", Long.valueOf(j2));
            frq frqVar = this.e.get();
            if (e(frqVar, "courseActionIntroViewHolder", "onProgress")) {
                frqVar.e(j, j2);
            }
        }

        private boolean e(Object obj, String str, String str2) {
            if (obj != null) {
                return true;
            }
            ReleaseLogUtil.d("Suggestion_CourseActionIntroViewHolder", "check ", str, " failed int method ", str2);
            return false;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public boolean isCanceled() {
            return this.c;
        }

        public void e(boolean z) {
            this.c = z;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }
    }

    static class d extends UiCallback<LongVideoInfo> {

        /* renamed from: a, reason: collision with root package name */
        private int f12627a;
        private WeakReference<frq> e;

        d(frq frqVar, int i) {
            this.e = new WeakReference<>(frqVar);
            this.f12627a = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.d("Suggestion_CourseActionIntroViewHolder", "getCourseLongVideoInfo:", Integer.valueOf(i), str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(LongVideoInfo longVideoInfo) {
            if (longVideoInfo == null) {
                ReleaseLogUtil.d("Suggestion_CourseActionIntroViewHolder", "getLongVideo startLongCoachActivity data.getUrl:url is null");
                return;
            }
            frq frqVar = this.e.get();
            if (frqVar != null) {
                frqVar.d(this.f12627a, longVideoInfo.getUrl());
            }
        }
    }
}
