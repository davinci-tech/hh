package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.view.View;
import com.google.gson.reflect.TypeToken;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider;
import com.huawei.health.suggestion.util.ClickEventUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.arx;
import defpackage.ccq;
import defpackage.eed;
import defpackage.ffy;
import defpackage.fot;
import defpackage.gds;
import defpackage.gdz;
import defpackage.ggm;
import defpackage.ghd;
import defpackage.koq;
import defpackage.mod;
import defpackage.moe;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class RunMyCourseProvider extends FitnessEntranceProvider<List<gdz>> {
    private int e = 0;
    private boolean d = true;

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 3;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 4;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        d(context, (HashMap<String, Object>) hashMap, (List<gdz>) obj);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return this.d && Utils.j();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        boolean z = false;
        this.e = 0;
        super.loadDefaultData(sectionBean);
        if (this.mData != 0 && ((List) this.mData).size() != 0) {
            z = true;
        }
        this.d = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public List<gdz> getDefaultData() {
        List<gdz> cacheData = getCacheData(new TypeToken<List<gdz>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunMyCourseProvider.3
        }.getType());
        if (koq.b(cacheData)) {
            ReleaseLogUtil.a("Track_Provider_Track_RunMyCourseProvider", " getDefaultData is empty.");
        } else {
            ReleaseLogUtil.b("Track_Provider_Track_RunMyCourseProvider", " getDefaultData size is:", Integer.valueOf(cacheData.size()));
        }
        return cacheData;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        a(1);
    }

    public void d(Context context, HashMap<String, Object> hashMap, List<gdz> list) {
        a(hashMap, list);
    }

    private void a(HashMap<String, Object> hashMap, List<gdz> list) {
        hashMap.put("TITLE", getSubViewTitle());
        int min = Math.min(list.size(), 6);
        List<gdz> subList = list.subList(0, min);
        if (list.size() > 0) {
            d(subList, hashMap, min);
        }
        c(hashMap, subList);
    }

    private void c(Map<String, Object> map, final List<gdz> list) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunMyCourseProvider.1
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("MORE_CLICK_EVENT".equals(str)) {
                    if (nsn.o()) {
                        LogUtil.a("Track_Provider_Track_RunMyCourseProvider", "mChooseButton onClick, isFastClick");
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put("myCourseType", Integer.valueOf(RunMyCourseProvider.this.e));
                    ClickEventUtils.e(RunMyCourseProvider.this.d(), hashMap);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Track_Provider_Track_RunMyCourseProvider", "list is empty");
                    return;
                }
                gdz gdzVar = (gdz) list.get(i);
                if (gdzVar == null) {
                    LogUtil.b("Track_Provider_Track_RunMyCourseProvider", "fitWorkout is null");
                } else {
                    ClickEventUtils.e(ClickEventUtils.ClickEventType.RUN_COURSE, ccq.c(4, RunMyCourseProvider.this.c(), RunMyCourseProvider.this.getCourseCategory(), gdzVar.e()));
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d(List<gdz> list, Map<String, Object> map, int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        int i2 = 0;
        for (gdz gdzVar : list) {
            if (i2 >= i) {
                break;
            }
            if (gdzVar.b() == 1) {
                arrayList.add(Integer.valueOf(c(gdzVar)));
                arrayList2.add(gdzVar.h());
                arrayList3.add(fot.b(gdzVar.g()));
                arrayList4.add("");
                arrayList5.add(ghd.a(gdzVar.j()));
                arrayList6.add(new eed());
            } else {
                arrayList.add(gdzVar.i());
                arrayList2.add(gdzVar.h());
                arrayList3.add(ggm.d(gdzVar.c()));
                arrayList4.add(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(gdzVar.a())));
                arrayList5.add(ghd.a(gdzVar.j()));
                arrayList6.add(getTagData(gdzVar.d()));
            }
            i2++;
        }
        map.put("IMAGE", arrayList);
        map.put("NAME", arrayList2);
        map.put(PlayInfo.KEY_DURATION, arrayList4);
        map.put("DIFFICULTY", arrayList3);
        map.put("INTERVALS", arrayList5);
        map.put("ITEM_TAG", arrayList6);
    }

    private int c(gdz gdzVar) {
        long g = CommonUtils.g(gdzVar.f()) % 3;
        return g == 0 ? R.drawable.pic_custom_yellow_small : g == 1 ? R.drawable.pic_custom_red_small : R.drawable.pic_custom_blue_small;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130848513_res_0x7f022b01);
    }

    protected ClickEventUtils.ClickEventType d() {
        return ClickEventUtils.ClickEventType.MY_ALL_RUN_COURSE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final int i) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.b("Track_Provider_Track_RunMyCourseProvider", "getCourseData courseApi is null.");
            return;
        }
        if (i == 2) {
            this.e = 1;
        } else {
            this.e = 0;
        }
        String str = getCourseCategory() == 257 ? "WALKING_COURSE" : "RUNNING_COURSE";
        LogUtil.a("Track_Provider_Track_RunMyCourseProvider", "getCourseCategory:", Integer.valueOf(getCourseCategory()), " ", str);
        courseApi.getUserCourseList(0, 6, i, str, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunMyCourseProvider.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                LogUtil.h("Track_Provider_Track_RunMyCourseProvider", "opType = ", Integer.valueOf(i), ", errorCode = ", Integer.valueOf(i2), ", errorInfo = ", str2);
                if (i != 2) {
                    RunMyCourseProvider.this.a(2);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<Workout> list) {
                if (RunMyCourseProvider.this.b(i, list)) {
                    LogUtil.b("Track_Provider_Track_RunMyCourseProvider", Integer.valueOf(i), ", workouts is empty.");
                    return;
                }
                List<FitWorkout> a2 = mod.a(list);
                if (!RunMyCourseProvider.this.b(i, a2)) {
                    List d = RunMyCourseProvider.this.d(i, a2);
                    if (RunMyCourseProvider.this.b(i, d)) {
                        LogUtil.b("Track_Provider_Track_RunMyCourseProvider", Integer.valueOf(i), ", workoutList is empty.");
                        return;
                    }
                    ArrayList arrayList = new ArrayList(d.size());
                    Iterator it = d.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new gdz.a().b((FitWorkout) it.next()).d());
                    }
                    RunMyCourseProvider.this.d = arrayList.size() != 0;
                    RunMyCourseProvider.this.checkAndUpdateUi(arrayList);
                    return;
                }
                LogUtil.b("Track_Provider_Track_RunMyCourseProvider", Integer.valueOf(i), ", workouts is empty.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i, Collection<?> collection) {
        if (!koq.b(collection)) {
            return false;
        }
        if (i == 1) {
            a(2);
        } else {
            checkAndUpdateUi(null);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FitWorkout> d(int i, List<FitWorkout> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.b("Track_Provider_Track_RunMyCourseProvider", "dealWithWorkouts opType = ", Integer.valueOf(i), "workouts is null.");
            return arrayList;
        }
        return gds.b(new ArrayList(list));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c() {
        return this.e == 0 ? "2" : "1";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_RunMyCourseProvider";
    }
}
