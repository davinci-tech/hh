package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.efb;
import defpackage.fis;
import defpackage.gdu;
import defpackage.gge;
import defpackage.ggm;
import defpackage.gic;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mnf;
import defpackage.mod;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class YogaRecommendCourseProvider extends FitnessRecommendCourseProvider<List<FitWorkout>> {
    protected void d() {
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 23;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 137;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<FitWorkout> list) {
        c(context, hashMap, list);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessRecommendCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        if (!efb.h()) {
            super.loadData(context, sectionBean);
            return;
        }
        LogUtil.b(getLogTag(), "loadData RecommendCourse");
        if (super.isNeedUpdate()) {
            JSONObject i = sectionBean.i();
            if (i == null) {
                LogUtil.a(getLogTag(), "data is null");
                super.loadData(context, sectionBean);
                return;
            }
            LogUtil.a(getLogTag(), i.toString());
            List<FitWorkout> b = mnf.b(i);
            if (koq.b(b)) {
                LogUtil.a(getLogTag(), "parse recommend data is null");
                return;
            }
            gdu.c(b, getCourseCategory());
            List<Workout> j = mod.j(b);
            if (koq.b(j)) {
                LogUtil.b(getLogTag(), "getRecWorkoutByCourseType onSuccess with null");
            } else {
                sectionBean.e(mod.a(j));
                this.mIsLoad = true;
            }
        }
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<FitWorkout> list) {
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string._2130848517_res_0x7f022b05));
        hashMap.put("SUBTITLE", getSubViewTitle());
        e(list, hashMap);
        b(context, list, hashMap);
    }

    private void b(final Context context, final List<FitWorkout> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("ALL_CLICK_EVENT".equals(str)) {
                    YogaRecommendCourseProvider.this.d();
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Track_Provider_Fitness_YogaRecommendCourseProvider", "position is out of bounds");
                    return;
                }
                FitWorkout fitWorkout = (FitWorkout) list.get(i);
                if (fitWorkout != null) {
                    YogaRecommendCourseProvider.this.b(fitWorkout, context);
                } else {
                    LogUtil.b("Track_Provider_Fitness_YogaRecommendCourseProvider", "fitworkout is null");
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitWorkout fitWorkout, Context context) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("entrance", "5");
        hashMap.put("position", 0);
        hashMap.put("type", Integer.valueOf(getCourseCategory()));
        hashMap.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, TextUtils.isEmpty(fitWorkout.acquireId()) ? "" : fitWorkout.acquireId());
        gge.e("1130015", hashMap);
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d(fitWorkout.acquireName());
        mod.b(context, fitWorkout, mmpVar);
    }

    private void e(List<FitWorkout> list, Map<String, Object> map) {
        float b = fis.d().b();
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
                arrayList2.add(mod.b(fitWorkout.acquirePriceTagBeanList()));
                arrayList3.add(fitWorkout.acquireName());
                arrayList4.add(gic.d(BaseApplication.getContext(), R.string._2130837534_res_0x7f02001e, gic.e(fitWorkout.acquireDuration())));
                arrayList5.add(fitWorkout.isLongExplainVideoCourse() ? "" : gic.d(BaseApplication.getContext(), R.string._2130837535_res_0x7f02001f, gic.a(gic.d(fitWorkout.acquireCalorie() * b))));
                arrayList6.add(fitWorkout.isLongExplainVideoCourse() ? "" : ggm.d(fitWorkout.acquireDifficulty()));
                arrayList7.add(gic.b(R.plurals._2130903040_res_0x7f030000, fitWorkout.acquireUsers(), UnitUtil.e(fitWorkout.acquireUsers(), 1, 0)));
            }
        }
        map.put("BACKGROUND_IMAGE", arrayList);
        map.put("CORNER_VIEW", arrayList2);
        map.put("NAME", arrayList3);
        map.put(PlayInfo.KEY_DURATION, arrayList4);
        map.put("CALORIES", arrayList5);
        map.put("DIFFICULTY", arrayList6);
        map.put("TRAIN_NUMBER", arrayList7);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Fitness_YogaRecommendCourseProvider";
    }
}
