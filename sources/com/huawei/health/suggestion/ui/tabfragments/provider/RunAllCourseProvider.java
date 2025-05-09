package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.health.suggestion.model.RunRecommendWorkout;
import com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider;
import com.huawei.health.suggestion.util.ClickEventUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ccq;
import defpackage.fis;
import defpackage.ggm;
import defpackage.ggs;
import defpackage.gic;
import defpackage.koq;
import defpackage.mod;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class RunAllCourseProvider extends FitnessEntranceProvider<List<FitWorkout>> {
    private SectionBean j;
    private boolean c = true;
    private List<RunRecommendWorkout> d = new CopyOnWriteArrayList();
    private List<RunRecommendWorkout> e = new ArrayList();
    private List<FitWorkout> g = new ArrayList();
    private List<FitWorkout> f = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<FitWorkout> f3395a = new ArrayList();
    private final CountDownLatch b = new CountDownLatch(4);

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 258;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 4;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "refresh recommend data.");
        this.c = this.f3395a.size() != 0;
        this.j.e(this.f3395a);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return this.c && (Utils.j() || (Utils.o() && Utils.i()));
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.j = sectionBean;
        e();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<FitWorkout> list) {
        c(hashMap, list);
    }

    private void c(HashMap<String, Object> hashMap, List<FitWorkout> list) {
        if (ggs.e(BaseApplication.getContext())) {
            hashMap.put("TITLE", BaseApplication.getContext().getString(R.string._2130848517_res_0x7f022b05));
            hashMap.put("SUBTITLE", BaseApplication.getContext().getResources().getString(R.string._2130848518_res_0x7f022b06));
            hashMap.remove("RIGHT_ARRAY");
        } else {
            hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string._2130848518_res_0x7f022b06));
            hashMap.put("RIGHT_ARRAY", true);
            hashMap.remove("SUBTITLE");
        }
        b(list, hashMap);
        a(list, hashMap);
    }

    private void b(List<FitWorkout> list, Map<String, Object> map) {
        float b2 = fis.d().b();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null) {
                arrayList.add(fitWorkout.acquirePicture());
                arrayList2.add(fitWorkout.acquireName());
                arrayList3.add(gic.d(BaseApplication.getContext(), R.string._2130837534_res_0x7f02001e, gic.e(fitWorkout.acquireDuration())));
                arrayList4.add(gic.d(BaseApplication.getContext(), R.string._2130837535_res_0x7f02001f, gic.a(gic.d(fitWorkout.acquireCalorie() * b2))));
                arrayList5.add(ggm.d(fitWorkout.acquireDifficulty()));
                if (nsn.s()) {
                    arrayList6.add("");
                } else {
                    arrayList6.add(gic.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)));
                    arrayList7.add(c(fitWorkout));
                }
            }
        }
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put(PlayInfo.KEY_DURATION, arrayList3);
        map.put("CALORIES", arrayList4);
        map.put("DIFFICULTY", arrayList5);
        map.put("TRAIN_NUMBER", arrayList6);
        map.put("NEW_IMAGE", arrayList7);
    }

    private Integer c(FitWorkout fitWorkout) {
        if (fitWorkout.acquireStage() == 0 && fitWorkout.acquireIsSupportDevice() == 0) {
            return Integer.valueOf(R.drawable.pic_corner_new_watchwear);
        }
        if (fitWorkout.acquireStage() == 0) {
            return Integer.valueOf(R.drawable._2131430906_res_0x7f0b0dfa);
        }
        if (fitWorkout.acquireIsSupportDevice() == 0) {
            return Integer.valueOf(R.drawable.pic_corner_watchwear);
        }
        return null;
    }

    private void a(final List<FitWorkout> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.2
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("ALL_CLICK_EVENT".equals(str)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("courseCategory", Integer.valueOf(RunAllCourseProvider.this.getCourseCategory()));
                    ClickEventUtils.e(ClickEventUtils.ClickEventType.ALL_RUN_COURSE, hashMap);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Track_Provider_Track_RunAllCourseProvider", "position is out of bounds");
                    return;
                }
                FitWorkout fitWorkout = (FitWorkout) list.get(i);
                if (fitWorkout == null) {
                    LogUtil.b("Track_Provider_Track_RunAllCourseProvider", "fitworkout is null");
                } else {
                    ClickEventUtils.e(ClickEventUtils.ClickEventType.RUN_COURSE, ccq.c(4, "5", RunAllCourseProvider.this.getCourseCategory(), fitWorkout));
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public void e() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ges
            @Override // java.lang.Runnable
            public final void run() {
                RunAllCourseProvider.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        b();
        g();
        j();
        i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CountDownLatch countDownLatch = this.b;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "mCountDownLatch count = ", Long.valueOf(this.b.getCount()));
            if (this.b.getCount() <= 0) {
                d();
            }
        }
    }

    private void b() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_RunAllCourseProvider", "getCloudRecommendCourse : courseApi is null.");
        } else {
            courseApi.getRecommendCourseByCloud(new DataCallback() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.1
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Track_Provider_Track_RunAllCourseProvider", "getRecommedCourseByCloud error.", Integer.valueOf(i));
                    RunAllCourseProvider.this.a();
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "getRecommedCourseByCloud success.");
                    RunAllCourseProvider.this.a();
                    JSONArray optJSONArray = jSONObject.optJSONArray("runRecoWorkoutList");
                    if (optJSONArray != null) {
                        RunAllCourseProvider.this.e = (List) new Gson().fromJson(CommonUtil.z(optJSONArray.toString()), new TypeToken<List<RunRecommendWorkout>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.1.5
                        }.getType());
                    }
                }
            });
        }
    }

    private void j() {
        if (ggs.d() == 204) {
            d(204);
        } else {
            e(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_RunAllCourseProvider", "getTopicId : courseApi is null.", Integer.valueOf(i));
        } else {
            ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "getFitnessCourseTopicList()  pageNum:", Integer.valueOf(i));
            courseApi.getCourseTopicList(i, new b(this, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_RunAllCourseProvider", "getRunCourseByTopicId : courseApi is null.");
        } else {
            courseApi.getCoursesByTopicId(0, i, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.c("Track_Provider_Track_RunAllCourseProvider", str, "Failed, errorCode:", Integer.valueOf(i2));
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    if (koq.b(list)) {
                        ReleaseLogUtil.d("Track_Provider_Track_RunAllCourseProvider", "workouts is Empty");
                        return;
                    }
                    ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "workouts num:", Integer.valueOf(list.size()));
                    RunAllCourseProvider.this.g = mod.a(list);
                    RunAllCourseProvider.this.a();
                }
            });
        }
    }

    private void g() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_RunAllCourseProvider", "getCourseRecommedIndexList courseApi == null");
        } else {
            courseApi.getCourseRecommendIndexList(new RunCourseRecommendCallback() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.5
                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(String str) {
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(List<String> list) {
                    if (!koq.b(list)) {
                        RunAllCourseProvider.this.d.clear();
                        for (String str : list) {
                            RunRecommendWorkout runRecommendWorkout = new RunRecommendWorkout();
                            runRecommendWorkout.setWeight(1);
                            runRecommendWorkout.setWorkoutId(str);
                            if (!RunAllCourseProvider.this.d.contains(runRecommendWorkout)) {
                                RunAllCourseProvider.this.d.add(runRecommendWorkout);
                            }
                        }
                        ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "courseId size", Integer.valueOf(RunAllCourseProvider.this.d.size()));
                        RunAllCourseProvider.this.a();
                        return;
                    }
                    ReleaseLogUtil.d("Track_Provider_Track_RunAllCourseProvider", "CollectionUtils.isEmpty(courseId)");
                    RunAllCourseProvider.this.a();
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onFailure(int i, String str) {
                    RunAllCourseProvider.this.a();
                    ReleaseLogUtil.c("Track_Provider_Track_RunAllCourseProvider", "setRecommendData onFailure");
                }
            });
        }
    }

    private void i() {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_RunAllCourseProvider", "collect : courseApi is null.");
        } else {
            courseApi.getJoinedCourses(new WorkoutListBean(0, Integer.MAX_VALUE, (Integer[]) null, (Integer[]) null, (Integer[]) null, -1, (Integer[]) null, false), new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Track_Provider_Track_RunAllCourseProvider", "getJoinedWorkouts error.", Integer.valueOf(i));
                    RunAllCourseProvider.this.a();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "getJoinedWorkouts onSuccess");
                    if (koq.b(list)) {
                        ReleaseLogUtil.d("Track_Provider_Track_RunAllCourseProvider", "my running FitWorkout data == null");
                        RunAllCourseProvider.this.a();
                        return;
                    }
                    List<FitWorkout> a2 = mod.a(list);
                    ArrayList arrayList = new ArrayList();
                    for (FitWorkout fitWorkout : a2) {
                        if (fitWorkout != null && fitWorkout.getIntervals() != -2) {
                            arrayList.add(fitWorkout);
                        }
                    }
                    RunAllCourseProvider.this.f = ggs.d(arrayList, "RUNNING_COURSE");
                    ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "showTrainCourseData() mMyRunWorkouts.size = ", Integer.valueOf(RunAllCourseProvider.this.f.size()));
                    RunAllCourseProvider.this.a();
                }
            });
        }
    }

    private void e(List<String> list) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Track_Provider_Track_RunAllCourseProvider", "getCourseByIds : courseApi is null.");
        } else {
            courseApi.getCourseByIds(mod.d(list), false, new UiCallback<List<FitWorkout>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunAllCourseProvider.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Track_Provider_Track_RunAllCourseProvider", "getCourseByIds error.", Integer.valueOf(i));
                    RunAllCourseProvider.this.h();
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list2) {
                    ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "getCourseByIds onSuccess");
                    RunAllCourseProvider.this.f3395a = list2;
                    RunAllCourseProvider.this.h();
                }
            });
        }
    }

    private void d() {
        if (ggs.e(BaseApplication.getContext())) {
            a(b(this.e, this.d), this.f);
        } else {
            a(b(this.e, a(c(this.g))), this.f);
        }
    }

    private void a(List<RunRecommendWorkout> list, List<FitWorkout> list2) {
        if (koq.b(list)) {
            return;
        }
        c(list, list2);
        Collections.sort(list, new Comparator() { // from class: gfa
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return RunAllCourseProvider.c((RunRecommendWorkout) obj, (RunRecommendWorkout) obj2);
            }
        });
        ArrayList arrayList = new ArrayList(list.size());
        for (RunRecommendWorkout runRecommendWorkout : list) {
            if (runRecommendWorkout != null) {
                arrayList.add(runRecommendWorkout.getWorkoutId());
            }
        }
        e(arrayList);
    }

    public static /* synthetic */ int c(RunRecommendWorkout runRecommendWorkout, RunRecommendWorkout runRecommendWorkout2) {
        return runRecommendWorkout2.getWeight() - runRecommendWorkout.getWeight();
    }

    private void c(List<RunRecommendWorkout> list, List<FitWorkout> list2) {
        for (FitWorkout fitWorkout : list2) {
            if (fitWorkout != null) {
                for (RunRecommendWorkout runRecommendWorkout : list) {
                    if (runRecommendWorkout != null && runRecommendWorkout.getWorkoutId().equals(fitWorkout.acquireId())) {
                        runRecommendWorkout.setWeight(0);
                    }
                }
            }
        }
    }

    private List<RunRecommendWorkout> b(List<RunRecommendWorkout> list, List<RunRecommendWorkout> list2) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(new ArrayList(6));
        copyOnWriteArrayList.addAll(list);
        for (RunRecommendWorkout runRecommendWorkout : new ArrayList(list2)) {
            if (runRecommendWorkout != null) {
                Iterator it = copyOnWriteArrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        RunRecommendWorkout runRecommendWorkout2 = (RunRecommendWorkout) it.next();
                        if (runRecommendWorkout2 != null && runRecommendWorkout2.getWorkoutId().equals(runRecommendWorkout.getWorkoutId())) {
                            runRecommendWorkout2.setWeight(runRecommendWorkout2.getWeight() + 100);
                            break;
                        }
                    } else {
                        copyOnWriteArrayList.add(runRecommendWorkout);
                        break;
                    }
                }
            }
        }
        return new ArrayList(copyOnWriteArrayList);
    }

    private List<RunRecommendWorkout> a(Map<String, FitWorkout> map) {
        ArrayList arrayList = new ArrayList(3);
        for (FitWorkout fitWorkout : map.values()) {
            if (fitWorkout != null) {
                RunRecommendWorkout runRecommendWorkout = new RunRecommendWorkout();
                runRecommendWorkout.setWeight(1);
                runRecommendWorkout.setWorkoutId(fitWorkout.acquireId());
                arrayList.add(runRecommendWorkout);
                if (arrayList.size() == 3) {
                    break;
                }
            }
        }
        return arrayList;
    }

    private Map<String, FitWorkout> c(List<FitWorkout> list) {
        HashMap hashMap = new HashMap(list.size());
        for (FitWorkout fitWorkout : list) {
            if (fitWorkout != null && hashMap.get(fitWorkout.acquireId()) == null) {
                hashMap.put(fitWorkout.acquireId(), fitWorkout);
            }
        }
        return hashMap;
    }

    public static class b extends UiCallback<List<Topic>> {
        int b;
        WeakReference<RunAllCourseProvider> d;

        b(RunAllCourseProvider runAllCourseProvider, int i) {
            this.d = new WeakReference<>(runAllCourseProvider);
            this.b = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Track_Provider_Track_RunAllCourseProvider", "getFitnessCourseTopicList()  errorCode:", Integer.valueOf(i), " pageNum:", Integer.valueOf(this.b));
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final List<Topic> list) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gex
                @Override // java.lang.Runnable
                public final void run() {
                    RunAllCourseProvider.b.this.c(list);
                }
            });
        }

        public /* synthetic */ void c(List list) {
            RunAllCourseProvider runAllCourseProvider = this.d.get();
            if (runAllCourseProvider == null) {
                ReleaseLogUtil.d("Track_Provider_Track_RunAllCourseProvider", "requestRunCourseRecommend is null");
                return;
            }
            if (koq.b(list)) {
                ReleaseLogUtil.d("Track_Provider_Track_RunAllCourseProvider", "initRunCourseTopic data null");
                return;
            }
            ReleaseLogUtil.e("Track_Provider_Track_RunAllCourseProvider", "topic num:", Integer.valueOf(list.size()), " pageNum:", Integer.valueOf(this.b));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Topic topic = (Topic) it.next();
                if (topic != null && "SF006".equals(topic.acquireSerialNum())) {
                    runAllCourseProvider.d(topic.acquireId());
                    return;
                }
            }
            int i = this.b + 1;
            this.b = i;
            runAllCourseProvider.e(i);
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_RunAllCourseProvider";
    }
}
