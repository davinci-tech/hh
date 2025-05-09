package com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import defpackage.gdu;
import defpackage.koq;
import defpackage.mod;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class FitnessRecommendCourseProvider<T> extends FitnessEntranceProvider<T> {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "loadData RecommendCourse");
        if (super.isNeedUpdate()) {
            ThreadPoolManager.d().execute(new c(this, sectionBean));
        }
    }

    protected void getLocalCourseData(SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "getLocalCourseData");
        List<FitWorkout> e = gdu.e(getCourseCategory());
        if (e == null || e.size() == 0) {
            LogUtil.a(getLogTag(), "local data is empty");
        } else {
            LogUtil.a(getLogTag(), "update ui from local data");
            sectionBean.e(e);
        }
    }

    public static class c implements Runnable {
        WeakReference<SectionBean> b;
        WeakReference<FitnessRecommendCourseProvider> e;

        public c(FitnessRecommendCourseProvider fitnessRecommendCourseProvider, SectionBean sectionBean) {
            this.e = new WeakReference<>(fitnessRecommendCourseProvider);
            this.b = new WeakReference<>(sectionBean);
        }

        @Override // java.lang.Runnable
        public void run() {
            final FitnessRecommendCourseProvider fitnessRecommendCourseProvider = this.e.get();
            final SectionBean sectionBean = this.b.get();
            if (fitnessRecommendCourseProvider == null || sectionBean == null) {
                return;
            }
            LogUtil.a(fitnessRecommendCourseProvider.getLogTag(), "GetRecWorkoutRunnable start");
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h(fitnessRecommendCourseProvider.getLogTag(), "getRecommendCourseRecord : courseApi is null.");
            } else {
                courseApi.getRecWorkoutByCourseType(fitnessRecommendCourseProvider.getCourseCategory(), new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider.c.4
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.h(fitnessRecommendCourseProvider.getLogTag(), "getRecWorkoutByCourseType onfalure errorCode:", Integer.valueOf(i), " errorInfo:", str);
                        fitnessRecommendCourseProvider.getLocalCourseData(sectionBean);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<Workout> list) {
                        if (koq.b(list)) {
                            LogUtil.b(fitnessRecommendCourseProvider.getLogTag(), "getRecWorkoutByCourseType onSuccess with null");
                            return;
                        }
                        LogUtil.a(fitnessRecommendCourseProvider.getLogTag(), "getRecommendCoursesByRules onSuccess List<FitWorkout> size:", Integer.valueOf(list.size()));
                        List<FitWorkout> a2 = mod.a(list);
                        sectionBean.e(a2);
                        gdu.c(a2, fitnessRecommendCourseProvider.getCourseCategory());
                        fitnessRecommendCourseProvider.mIsLoad = true;
                    }
                });
            }
        }
    }
}
