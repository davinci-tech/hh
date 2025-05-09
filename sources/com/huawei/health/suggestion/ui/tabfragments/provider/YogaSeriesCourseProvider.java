package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.health.R;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessSeriesCourseDetailsActivity;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.arx;
import defpackage.efb;
import defpackage.ffy;
import defpackage.gge;
import defpackage.ggm;
import defpackage.koq;
import defpackage.mmp;
import defpackage.mnf;
import defpackage.mod;
import defpackage.moe;
import defpackage.nsj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class YogaSeriesCourseProvider extends FitnessSeriesCourseProvider<List<Topic>> {
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

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        a(context, (HashMap<String, Object>) hashMap, (List<Topic>) obj);
    }

    public void a(Context context, HashMap<String, Object> hashMap, List<Topic> list) {
        c(context, hashMap, list);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessSeriesCourseProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        if (!efb.h()) {
            super.loadData(context, sectionBean);
            return;
        }
        LogUtil.a(getLogTag(), "loadData SeriesCourse");
        if (!super.isNeedUpdate()) {
            LogUtil.a(getLogTag(), "loadData no need update.");
            return;
        }
        JSONObject i = sectionBean.i();
        if (i == null) {
            LogUtil.a(getLogTag(), "data is null");
            super.loadData(context, sectionBean);
            return;
        }
        LogUtil.a(getLogTag(), i.toString());
        List<Topic> c = mnf.c(i);
        if (koq.b(c)) {
            LogUtil.a(getLogTag(), "topicList is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Topic topic : c) {
            if (topic != null && topic.getCourseCategory() == getCourseCategory()) {
                arrayList.add(topic);
            }
        }
        if (koq.b(arrayList)) {
            LogUtil.a(getLogTag(), "targetList is null");
            return;
        }
        LogUtil.a(getLogTag(), "getCourseTopicList List<Topic>", Integer.valueOf(arrayList.size()));
        sectionBean.e(arrayList);
        this.mIsLoad = true;
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<Topic> list) {
        if (koq.b(list)) {
            return;
        }
        hashMap.put("PAGE_TYPE", Integer.valueOf(getPageType()));
        hashMap.put("TITLE", context.getResources().getString(R.string._2130848533_res_0x7f022b15));
        int min = Math.min(list.size(), 6);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < min; i++) {
            arrayList.add(list.get(i).getTopicBackImgUrl());
        }
        hashMap.put("BACKGROUND", arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < min; i2++) {
            arrayList2.add(list.get(i2).acquireName());
        }
        hashMap.put("SERIES_COURSE_TITLE", arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < min; i3++) {
            arrayList3.add(list.get(i3).getSubtitle());
        }
        hashMap.put("SERIES_COURSE_SUBTITLE", arrayList3);
        ArrayList arrayList4 = new ArrayList();
        for (int i4 = 0; i4 < min; i4++) {
            d(list.get(i4), arrayList4);
        }
        hashMap.put("RECYCLERVIEW", arrayList4);
        ArrayList arrayList5 = new ArrayList();
        for (int i5 = 0; i5 < min; i5++) {
            arrayList5.add(context.getResources().getString(R$string.IDS_hw_common_ui_xlistview_footer_hint_normal));
        }
        hashMap.put("SHOWMORE", arrayList5);
        b(context, list, hashMap);
        LogUtil.a("Fitness_YogaSeriesCourseProvider", "viewMap", hashMap);
    }

    private void b(final Context context, final List<Topic> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider.2
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("SUBTITILE_CLICK_EVENT".equals(str)) {
                    MarketingBiUtils.e(YogaSeriesCourseProvider.this.getPageType(), YogaSeriesCourseProvider.this.getSubViewTitle(), 7);
                    try {
                        context.startActivity(new Intent(context, (Class<?>) FitnessSeriesCourseActivity.class));
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("Fitness_YogaSeriesCourseProvider", "setClickListener ActivityNotFoundException.");
                    }
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
                if (koq.b(list, i)) {
                    return;
                }
                Topic topic = (Topic) list.get(i);
                if (koq.b(topic.acquireWorkoutList(), i2)) {
                    return;
                }
                YogaSeriesCourseProvider.this.a(topic.acquireWorkoutList().get(i2), context, topic.acquireName());
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                if (koq.b(list, i)) {
                    return;
                }
                Topic topic = (Topic) list.get(i);
                if ("SHOW_MORE_CLICK_EVENT".equals(str)) {
                    YogaSeriesCourseProvider.this.a(topic, context);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Topic topic, Context context) {
        if (topic == null) {
            LogUtil.h("Fitness_YogaSeriesCourseProvider", "jumpFitnessSerieseView: topic is null");
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("date", nsj.d());
        hashMap.put("topicId", Integer.valueOf(topic.acquireId()));
        gge.e("1130032", hashMap);
        Intent intent = new Intent(context, (Class<?>) FitnessSeriesCourseDetailsActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("intent_key_topicid", topic.acquireId());
        intent.putExtra("intent_key_topicname", topic.acquireName());
        intent.putExtra("intent_key_description", topic.getDescription());
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Fitness_YogaSeriesCourseProvider", "jumpFitnessSerieseView ActivityNotFoundException.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(FitWorkout fitWorkout, Context context, String str) {
        if (fitWorkout == null) {
            return;
        }
        MarketingBiUtils.e(getPageType(), getSubViewTitle(), 8);
        mmp mmpVar = new mmp(fitWorkout.acquireId());
        mmpVar.d("8");
        mmpVar.f(str);
        mod.b(context, fitWorkout, mmpVar);
    }

    private void d(Topic topic, List<Map<String, Object>> list) {
        Map<String, Object> hashMap = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        int i = 0;
        for (FitWorkout fitWorkout : topic.acquireWorkoutList()) {
            if (i >= 2) {
                break;
            }
            arrayList.add(fitWorkout.getTopicPreviewPicUrl());
            arrayList2.add(fitWorkout.acquireName());
            arrayList3.add(ggm.d(fitWorkout.acquireDifficulty()));
            arrayList4.add(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(fitWorkout.acquireDuration())));
            i++;
        }
        hashMap.put("IMAGE", arrayList);
        hashMap.put("NAME", arrayList2);
        hashMap.put("DIFFICULTY", arrayList3);
        hashMap.put("TIME", arrayList4);
        list.add(hashMap);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getString(R.string._2130848533_res_0x7f022b15);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Fitness_YogaSeriesCourseProvider";
    }
}
