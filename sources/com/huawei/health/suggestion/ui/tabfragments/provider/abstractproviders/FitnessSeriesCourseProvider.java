package com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class FitnessSeriesCourseProvider<T> extends FitnessEntranceProvider<T> {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return !CommonUtil.bu();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a(getLogTag(), "loadData SeriesCourse");
        if (super.isNeedUpdate()) {
            ThreadPoolManager.d().execute(new b(this, sectionBean));
        }
    }

    public static class b implements Runnable {
        WeakReference<SectionBean> b;
        WeakReference<FitnessSeriesCourseProvider> c;

        public b(FitnessSeriesCourseProvider fitnessSeriesCourseProvider, SectionBean sectionBean) {
            this.c = new WeakReference<>(fitnessSeriesCourseProvider);
            this.b = new WeakReference<>(sectionBean);
        }

        @Override // java.lang.Runnable
        public void run() {
            final FitnessSeriesCourseProvider fitnessSeriesCourseProvider = this.c.get();
            final SectionBean sectionBean = this.b.get();
            if (fitnessSeriesCourseProvider == null || sectionBean == null) {
                return;
            }
            LogUtil.a(fitnessSeriesCourseProvider.getLogTag(), "GetCourseTopicsRunnable start");
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h(fitnessSeriesCourseProvider.getLogTag(), "getSeriesCourseRecord : courseApi is null.");
            } else {
                courseApi.getCourseTopicList(fitnessSeriesCourseProvider.getCourseCategory(), 0, new UiCallback<List<Topic>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider.b.2
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b(fitnessSeriesCourseProvider.getLogTag(), "failed to get series errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<Topic> list) {
                        if (koq.b(list)) {
                            LogUtil.h(fitnessSeriesCourseProvider.getLogTag(), "getCourseTopicList data is null");
                            return;
                        }
                        LogUtil.a(fitnessSeriesCourseProvider.getLogTag(), "getCourseTopicList List<Topic>", Integer.valueOf(list.size()));
                        sectionBean.e(list);
                        fitnessSeriesCourseProvider.mIsLoad = true;
                    }
                });
            }
        }
    }
}
