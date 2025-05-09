package com.huawei.health.suggestion.ui.viewholder;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.suggestion.ui.fitness.module.FitnessTopicDeleteModel;
import com.huawei.health.suggestion.ui.viewholder.CourseItemHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.arx;
import defpackage.bzs;
import defpackage.ffv;
import defpackage.ffy;
import defpackage.fot;
import defpackage.ggm;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.moe;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class CourseItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3428a;
    private HealthDivider b;
    private ClickItemCount c;
    private HealthTextView d;
    private HealthTextView e;
    private HealthTextView f;
    private HealthTextView g;
    private FitnessTopicDeleteModel h;
    private HealthCheckBox i;
    private final HealthTextView j;
    private int k;
    private RelativeLayout l;
    private LinearLayout m;
    private RelativeLayout n;
    private final ImageView o;
    private View r;

    public interface ClickItemCount {
        void onClickCheckBoxItem();
    }

    private int b(int i) {
        if (i == 0) {
            return 1;
        }
        if (i != 1) {
            return i != 2 ? 0 : 2;
        }
        return 3;
    }

    public CourseItemHolder(View view) {
        super(view);
        this.k = 0;
        this.n = (RelativeLayout) view.findViewById(R.id.recycle_item);
        this.g = (HealthTextView) view.findViewById(R.id.course_title);
        this.m = (LinearLayout) view.findViewById(R.id.layout_trained_difficulty_duration);
        this.f = (HealthTextView) view.findViewById(R.id.course_trained);
        this.d = (HealthTextView) view.findViewById(R.id.course_difficulty);
        this.e = (HealthTextView) view.findViewById(R.id.course_duration);
        this.f3428a = (ImageView) view.findViewById(R.id.recycle_item_picture);
        this.b = (HealthDivider) view.findViewById(R.id.course_item_divider);
        this.l = (RelativeLayout) view.findViewById(R.id.sug_course_checkable);
        this.i = (HealthCheckBox) view.findViewById(R.id.sug_course_checkbox);
        this.r = view.findViewById(R.id.sug_course_view_space);
        this.j = (HealthTextView) view.findViewById(R.id.course_tag);
        this.o = (ImageView) view.findViewById(R.id.series_course_shadow);
    }

    public void a(int i) {
        this.b.setVisibility(i);
    }

    public void e(int i) {
        View view = this.r;
        if (view == null) {
            return;
        }
        view.setVisibility(i);
    }

    public void e(FitnessTopicDeleteModel fitnessTopicDeleteModel, String str, boolean z, Object obj) {
        if (!(obj instanceof Workout)) {
            LogUtil.h("SportCourseItemViewHolder", "setDataAndRefresh workout == null");
            return;
        }
        final Workout workout = (Workout) obj;
        if (z && fitnessTopicDeleteModel != null) {
            this.h = fitnessTopicDeleteModel;
            a();
        }
        e(workout);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.viewholder.CourseItemHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.a(500)) {
                    if (CourseItemHolder.this.h != null && CourseItemHolder.this.h.issDeleteMode()) {
                        CourseItemHolder.this.i.performClick();
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    FitWorkout a2 = mod.a(workout);
                    WorkoutPackageInfo e = mod.e(workout);
                    if (a2 != null) {
                        CourseItemHolder.this.b(a2);
                    } else if (e != null) {
                        int enableNewUrl = e.getEnableNewUrl();
                        LogUtil.a("SportCourseItemViewHolder", "workoutPackageInfo isEnableNewUrl ", Integer.valueOf(enableNewUrl));
                        if (enableNewUrl != 1) {
                            CourseItemHolder.this.d(e.getWorkoutPackageId());
                        } else {
                            JumpUtil.e(BaseApplication.getContext(), e.getWorkoutPackageId(), 1);
                        }
                    } else {
                        LogUtil.h("SportCourseItemViewHolder", "setDataAndRefresh workout is null");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("SportCourseItemViewHolder", "itemView click too fast.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void e(FitnessTopicDeleteModel fitnessTopicDeleteModel, Object obj) {
        if (!(obj instanceof SleepAudioSeries)) {
            LogUtil.h("SportCourseItemViewHolder", "setSleepCourse audio is null");
            return;
        }
        final SleepAudioSeries sleepAudioSeries = (SleepAudioSeries) obj;
        if (fitnessTopicDeleteModel != null) {
            this.h = fitnessTopicDeleteModel;
            a();
        }
        b(sleepAudioSeries);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.viewholder.CourseItemHolder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.a(500)) {
                    if (CourseItemHolder.this.h != null && CourseItemHolder.this.h.issDeleteMode()) {
                        CourseItemHolder.this.i.performClick();
                        ViewClickInstrumentation.clickOnView(view);
                        return;
                    }
                    int enableNewUrl = sleepAudioSeries.getEnableNewUrl();
                    LogUtil.a("SportCourseItemViewHolder", "itemView isEnableNewUrl ", Integer.valueOf(enableNewUrl));
                    if (enableNewUrl != 1) {
                        CourseItemHolder.this.c(sleepAudioSeries);
                    } else {
                        JumpUtil.e(BaseApplication.getContext(), Integer.toString(sleepAudioSeries.getId()), 2);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.h("SportCourseItemViewHolder", "itemView click too fast.");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(SleepAudioSeries sleepAudioSeries) {
        Intent intent = new Intent();
        intent.setClassName(this.itemView.getContext(), "com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseListActivity");
        intent.addFlags(268435456);
        intent.putExtra("id", Integer.toString(sleepAudioSeries.getId()));
        intent.putExtra("from", b(this.k));
        this.itemView.getContext().startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitWorkout fitWorkout) {
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.s(fitWorkout.acquireId());
        mmpVar.d(d());
        if (fitWorkout.getCourseDefineType() == 1) {
            mmpVar.g(1);
        }
        mod.b(this.itemView.getContext(), fitWorkout, mmpVar);
    }

    private String d() {
        int i = this.k;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "4" : "3" : "1" : "2";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).addCustomizeJsModule("tradeApi", bzs.e().getCommonJsModule("tradeApi")).addCustomizeJsModule("mediaCenter", bzs.e().getCommonJsModule("mediaCenter")).enableOnPauseCallback().setImmerse().showStatusBar().setStatusBarTextBlack(true).addCustomizeArg("workoutPackageId", str).setForceDarkMode(0).build();
        SeriesCourseH5Repository.registerService();
        H5ProClient.startH5MiniProgram(BaseApplication.getContext(), "com.huawei.health.h5.course", build);
    }

    private void e(Workout workout) {
        if (workout == null) {
            LogUtil.h("SportCourseItemViewHolder", "showCourseWorkout workout is null");
            return;
        }
        FitWorkout a2 = mod.a(workout);
        if (a2 != null) {
            c(a2);
        } else {
            b(mod.e(workout));
        }
    }

    private void b() {
        this.f.setMaxWidth(Integer.MAX_VALUE);
        this.d.setMaxWidth(Integer.MAX_VALUE);
        this.e.setMaxWidth(Integer.MAX_VALUE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = 0;
        this.e.setLayoutParams(layoutParams);
        this.d.setLayoutParams(layoutParams);
        this.d.setGravity(GravityCompat.START);
        this.m.setOrientation(1);
    }

    private void b(WorkoutPackageInfo workoutPackageInfo) {
        if (workoutPackageInfo == null) {
            LogUtil.h("SportCourseItemViewHolder", "setWorkoutPackageInfo workout is null");
            return;
        }
        this.g.setText(workoutPackageInfo.getWorkoutPackageName());
        this.f.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903319_res_0x7f030117, workoutPackageInfo.getWorkoutFinishCount().intValue(), workoutPackageInfo.getWorkoutFinishCount(), workoutPackageInfo.getWorkoutTotalCount()));
        this.d.setText("");
        Integer commodityFlag = workoutPackageInfo.getCommodityFlag();
        Long expireTime = workoutPackageInfo.getExpireTime();
        String squarePicture = workoutPackageInfo.getSquarePicture();
        if (TextUtils.isEmpty(squarePicture)) {
            squarePicture = workoutPackageInfo.getTopicPreviewPicUrl();
        }
        nrf.cIS_(this.f3428a, squarePicture, nrf.e, 0, R.drawable._2131431605_res_0x7f0b10b5);
        a(true, commodityFlag.intValue());
        if (commodityFlag.intValue() != 1 || expireTime == null) {
            this.e.setText("");
            LogUtil.h("SportCourseItemViewHolder", "setWorkoutPackageInfo expireTime is null or commodityFlag is no paid");
        } else {
            this.e.setText(e(expireTime.longValue()));
        }
    }

    private void b(SleepAudioSeries sleepAudioSeries) {
        if (sleepAudioSeries == null) {
            LogUtil.h("SportCourseItemViewHolder", "setHeartideStressInfo workout is null");
            return;
        }
        nrf.cIS_(this.f3428a, sleepAudioSeries.getSmallImage(), nrf.e, 0, R.drawable._2131431605_res_0x7f0b10b5);
        b(true, sleepAudioSeries.getIsVip());
        this.g.setText(sleepAudioSeries.getName());
        e(Integer.toString(sleepAudioSeries.getId()));
        this.d.setText("");
        long expireTime = sleepAudioSeries.getExpireTime();
        int i = this.k;
        if (i == 0 || i == 1) {
            this.e.setText("");
            LogUtil.a("SportCourseItemViewHolder", "mFragmentType= ", Integer.valueOf(this.k));
        } else {
            this.e.setText(e(expireTime));
        }
    }

    private String e(long j) {
        String str;
        if (j > 0) {
            str = BaseApplication.getContext().getResources().getString(R.string._2130845343_res_0x7f021e9f, UnitUtil.a("yyyy/M/d", j));
        } else {
            str = "";
        }
        if (j == -1) {
            str = BaseApplication.getContext().getResources().getString(R.string._2130845342_res_0x7f021e9e);
        } else if (j == -2) {
            str = BaseApplication.getContext().getResources().getString(R.string._2130845341_res_0x7f021e9d);
        } else {
            LogUtil.h("SportCourseItemViewHolder", "setWorkoutPackageInfo expireTime is invalidation");
        }
        LogUtil.a("SportCourseItemViewHolder", "workout Info expireTime= ", Long.valueOf(j));
        return str;
    }

    private void e(final String str) {
        final CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("SportCourseItemViewHolder", "getSleepRelaxCourse : courseApi is null.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gfl
                @Override // java.lang.Runnable
                public final void run() {
                    CourseItemHolder.this.d(courseApi, str);
                }
            });
        }
    }

    public /* synthetic */ void d(CourseApi courseApi, String str) {
        courseApi.queryAudiosPackageBySeriesId(str, new UiCallback<List<SleepAudioPackage>>() { // from class: com.huawei.health.suggestion.ui.viewholder.CourseItemHolder.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.h("SportCourseItemViewHolder", "errcode = ", Integer.valueOf(i), ", errorInfo = ", str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<SleepAudioPackage> list) {
                if (!koq.b(list)) {
                    CourseItemHolder.this.a(list);
                } else {
                    LogUtil.h("SportCourseItemViewHolder", "onSuccess data is null");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<SleepAudioPackage> list) {
        for (SleepAudioPackage sleepAudioPackage : list) {
            if (sleepAudioPackage.getSleepAudioSeries() == null) {
                LogUtil.h("SportCourseItemViewHolder", "sleepAudioSeries is null");
            } else {
                int displayStyle = sleepAudioPackage.getSleepAudioSeries().getDisplayStyle();
                if (displayStyle == 1) {
                    if (koq.b(sleepAudioPackage.getSleepAudioInfoList())) {
                        LogUtil.h("SportCourseItemViewHolder", "sleepAudioInfoList is null");
                    } else {
                        d(sleepAudioPackage, ffv.c(sleepAudioPackage.getSleepAudioInfoList()));
                    }
                } else if (displayStyle == 2) {
                    if (koq.b(sleepAudioPackage.getSleepAudioGroupList())) {
                        LogUtil.h("SportCourseItemViewHolder", "sleepAudioGroupList is null");
                    } else {
                        d(sleepAudioPackage, ffv.a(sleepAudioPackage.getSleepAudioGroupList()));
                    }
                } else {
                    LogUtil.h("SportCourseItemViewHolder", "wrong style:", Integer.valueOf(displayStyle));
                }
            }
        }
    }

    private void d(SleepAudioPackage sleepAudioPackage, String str) {
        if (str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_not_played))) {
            this.f.setText(sleepAudioPackage.getSleepAudioSeries().getAudioDescription());
        } else {
            this.f.setText(str);
        }
    }

    private void g() {
        this.n.setMinimumHeight(BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363131_res_0x7f0a053b));
        this.o.setVisibility(0);
    }

    private void i() {
        this.j.setVisibility(0);
        this.j.setText(arx.b().getResources().getString(R.string._2130847510_res_0x7f022716));
        this.j.setTextColor(arx.b().getColor(R.color._2131296866_res_0x7f090262));
        this.j.setBackground(ContextCompat.getDrawable(BaseApplication.getContext(), R.drawable._2131431679_res_0x7f0b10ff));
    }

    private void c() {
        this.j.setVisibility(0);
        this.j.setText(arx.b().getResources().getString(R.string._2130845344_res_0x7f021ea0));
        this.j.setTextColor(arx.b().getColor(R.color._2131297564_res_0x7f09051c));
        this.j.setBackground(ContextCompat.getDrawable(BaseApplication.getContext(), R.drawable._2131431682_res_0x7f0b1102));
    }

    private void b(boolean z, int i) {
        if (z) {
            g();
        }
        if (i == 1) {
            i();
        } else if (i == 2) {
            c();
        } else {
            this.j.setVisibility(8);
        }
    }

    private void a(boolean z, int i) {
        if (z) {
            g();
        }
        if (i == 2) {
            i();
        } else if (i == 1) {
            c();
        } else {
            this.j.setVisibility(8);
        }
    }

    private void c(FitWorkout fitWorkout) {
        if (nsn.p() || nsn.a(3.4f) || e()) {
            b();
        }
        this.g.setText(fitWorkout.acquireName());
        this.e.setText(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
        this.d.setText(ggm.d(fitWorkout.acquireDifficulty()));
        if (fitWorkout.getIntervals() == -4 && !fitWorkout.isLongExplainVideoCourse()) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
            this.f.setText(e(fitWorkout));
        }
        if (fitWorkout.getCourseDefineType() == 1) {
            a(fitWorkout);
        } else if (!TextUtils.isEmpty(fitWorkout.getTopicPreviewPicUrl())) {
            nrf.cIS_(this.f3428a, fitWorkout.getTopicPreviewPicUrl(), nrf.e, 0, R.drawable._2131431605_res_0x7f0b10b5);
        } else {
            nrf.cIM_(R.drawable._2131431605_res_0x7f0b10b5, this.f3428a, nrf.e);
        }
        a(false, fitWorkout.acquireCommodityFlag());
    }

    private boolean e() {
        return !LanguageUtil.al(this.itemView.getContext());
    }

    private String e(FitWorkout fitWorkout) {
        if (fitWorkout.isLongExplainVideoCourse()) {
            return arx.b().getString(R.string._2130846129_res_0x7f0221b1);
        }
        if (fitWorkout.getIntervals() == -2) {
            return arx.b().getString(R.string._2130846128_res_0x7f0221b0);
        }
        if (fitWorkout.getIntervals() == -3) {
            return arx.b().getString(R.string._2130846127_res_0x7f0221af);
        }
        if (fitWorkout.getIntervals() == 0) {
            return arx.b().getString(R.string._2130846126_res_0x7f0221ae);
        }
        int intervals = fitWorkout.getIntervals();
        return arx.b().getResources().getQuantityString(R.plurals._2130903356_res_0x7f03013c, intervals, Integer.valueOf(intervals));
    }

    private void a(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            LogUtil.b("SportCourseItemViewHolder", "setCustomCourseWorkout fitWorkout is null.");
            return;
        }
        this.e.setVisibility(8);
        this.d.setMaxWidth(Integer.MAX_VALUE);
        this.d.setText(fot.b(fitWorkout.getPublishDate()));
        long g = CommonUtils.g(fitWorkout.acquireId()) % 3;
        nrf.cIP_(this.f3428a, g == 0 ? R.drawable.pic_custom_yellow_small : g == 1 ? R.drawable.pic_custom_red_small : R.drawable.pic_custom_blue_small, nrf.e, 0, R.drawable._2131431605_res_0x7f0b10b5);
    }

    private void a() {
        FitnessTopicDeleteModel fitnessTopicDeleteModel = this.h;
        if (fitnessTopicDeleteModel == null) {
            return;
        }
        if (fitnessTopicDeleteModel.issDeleteMode()) {
            this.l.setVisibility(0);
            if (this.h.acquireSelects().contains(Integer.valueOf(this.h.acquirePosition()))) {
                this.i.setChecked(true);
            } else {
                this.i.setChecked(false);
            }
        } else {
            this.l.setVisibility(8);
        }
        this.i.setTag(Integer.valueOf(this.h.acquirePosition()));
        this.i.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getTag() instanceof Integer) {
            Integer num = (Integer) view.getTag();
            if (this.i.isChecked()) {
                this.h.acquireSelects().add(num);
            } else if (this.h.acquireSelects().contains(num)) {
                this.h.acquireSelects().remove(num);
            } else {
                LogUtil.c("SportCourseItemViewHolder", "else isChecked");
            }
        }
        LogUtil.a("SportCourseItemViewHolder", "after mDeleteModel.acquireSelects():", this.h.acquireSelects());
        ClickItemCount clickItemCount = this.c;
        if (clickItemCount != null) {
            clickItemCount.onClickCheckBoxItem();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c(int i) {
        this.k = i;
    }

    public void e(ClickItemCount clickItemCount) {
        this.c = clickItemCount;
    }
}
