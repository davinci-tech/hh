package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.gic;
import defpackage.jdw;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mod;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class YogaBreathProvider extends BaseKnitDataProvider<List<FitWorkout>> {
    private Context d;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.b(getLogTag(), "loadData");
        this.d = context;
        ThreadPoolManager.d().execute(new e(this, sectionBean));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<FitWorkout> list) {
        a(list, hashMap);
        b(context, list, hashMap);
    }

    private void a(List<FitWorkout> list, Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                arrayList.add(fitWorkout.acquirePicture());
                arrayList2.add(mod.b(fitWorkout.acquirePriceTagBeanList()));
                arrayList3.add(fitWorkout.acquireName());
                arrayList4.add(gic.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)));
            }
        }
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("CORNER_VIEW", arrayList2);
        map.put("NAME", arrayList3);
        map.put("TRAIN_NUMBER", arrayList4);
    }

    private void b(final Context context, final List<FitWorkout> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.main.stories.health.pressure.provider.YogaBreathProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("ALL_CLICK_EVENT".equals(str)) {
                    YogaBreathProvider.this.b();
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("YogaBreathProvider", "position is out of bounds");
                    return;
                }
                FitWorkout fitWorkout = (FitWorkout) list.get(i);
                if (fitWorkout != null) {
                    YogaBreathProvider.this.d(context, fitWorkout);
                } else {
                    LogUtil.b("YogaBreathProvider", "fitworkout is null");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (nsn.o()) {
            LogUtil.h("YogaBreathProvider", "fast click");
            return;
        }
        LogUtil.a("YogaBreathProvider", "subtitle is clicked");
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweischeme://healthapp/fitnesspage?skip_type=course_details&id=" + Integer.toString(2104)));
        intent.setPackage(this.d.getPackageName());
        jdw.bGh_(intent, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            return;
        }
        if (nsn.o()) {
            LogUtil.h("YogaBreathProvider", "fast click");
            return;
        }
        LogUtil.a("YogaBreathProvider", "jump to course detail");
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d(fitWorkout.acquireName());
        mod.b(context, fitWorkout, mmpVar);
    }

    static class e implements Runnable {
        WeakReference<SectionBean> d;
        WeakReference<YogaBreathProvider> e;

        public e(YogaBreathProvider yogaBreathProvider, SectionBean sectionBean) {
            this.e = new WeakReference<>(yogaBreathProvider);
            this.d = new WeakReference<>(sectionBean);
        }

        @Override // java.lang.Runnable
        public void run() {
            final YogaBreathProvider yogaBreathProvider = this.e.get();
            final SectionBean sectionBean = this.d.get();
            if (yogaBreathProvider == null || sectionBean == null) {
                return;
            }
            LogUtil.a(yogaBreathProvider.getLogTag(), "GetRecWorkoutRunnable start");
            CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
            if (courseApi == null) {
                LogUtil.h(yogaBreathProvider.getLogTag(), "getCourseRecord : courseApi is null.");
            } else {
                courseApi.getCoursesByTopicId(0, 2104, new UiCallback<List<Workout>>() { // from class: com.huawei.ui.main.stories.health.pressure.provider.YogaBreathProvider.e.3
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.h(yogaBreathProvider.getLogTag(), "getRecWorkoutByCourseType onfalure errorCode:", Integer.valueOf(i), " errorInfo:", str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: e, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(List<Workout> list) {
                        if (koq.b(list)) {
                            LogUtil.b(yogaBreathProvider.getLogTag(), "getRecWorkoutByCourseType onSuccess with null");
                            return;
                        }
                        LogUtil.a(yogaBreathProvider.getLogTag(), "getRecommendCoursesByRules onSuccess List<FitWorkout> size:", Integer.valueOf(list.size()));
                        List<FitWorkout> a2 = mod.a(list);
                        LogUtil.a("YogaBreathProvider", "fitWorkouts size: ", Integer.valueOf(a2.size()));
                        sectionBean.e(a2);
                    }
                });
            }
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "YogaBreathProvider";
    }
}
