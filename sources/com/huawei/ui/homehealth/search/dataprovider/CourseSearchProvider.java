package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.dataprovider.CourseSearchProvider;
import defpackage.arx;
import defpackage.ary;
import defpackage.dod;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.ffy;
import defpackage.ggm;
import defpackage.ggs;
import defpackage.gic;
import defpackage.jdx;
import defpackage.koq;
import defpackage.mod;
import defpackage.moe;
import defpackage.otb;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class CourseSearchProvider extends BaseKnitDataProvider<List<GlobalSearchContent>> {
    public static /* synthetic */ void e(int i, Object obj) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        c(context, (HashMap<String, Object>) hashMap, (List<GlobalSearchContent>) obj);
    }

    public void c(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        d(context, hashMap, list);
    }

    private void d(Context context, HashMap<String, Object> hashMap, List<GlobalSearchContent> list) {
        boolean z;
        hashMap.clear();
        boolean a2 = otb.a(this);
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string._2130844108_res_0x7f0219cc));
        if (a2) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, 201);
            hashMap.put("ITEM_LIMIT", 3);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        HashSet hashSet = new HashSet();
        float b = ggs.b();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent == null) {
                z = a2;
            } else {
                z = a2;
                LogUtil.c("CourseSearchProvider", "content: ", HiJsonUtil.e(globalSearchContent));
                arrayList4.add(e(globalSearchContent, b));
                arrayList.add(globalSearchContent.getId());
                arrayList9.add(globalSearchContent.getPicUrl());
                arrayList3.add(globalSearchContent.getCommodityFlag() == 2 ? context.getString(R.string._2130847510_res_0x7f022716) : "");
                arrayList7.add(otb.c(globalSearchContent, true));
                c(globalSearchContent, arrayList2, arrayList5);
                otb.b(globalSearchContent, hashSet);
                otb.e(globalSearchContent, arrayList8, arrayList6, hashSet);
            }
            a2 = z;
        }
        boolean z2 = a2;
        hashMap.put("ITEM_BI_EVENT_MAP", otb.b(201, z2 ? 200 : 201, GlobalSearchActivity.d(), arrayList, arrayList2));
        hashMap.put("BI_OBSERVER_LABEL", fbh.d(z2 ? 200 : 201));
        hashMap.put("ITEM_IMAGE", arrayList9);
        hashMap.put("ITEM_TITLE", arrayList2);
        hashMap.put("ITEM_SUBTITLE", arrayList4);
        hashMap.put("ITEM_TAG", arrayList3);
        hashMap.put("HIGHLIGHTED_TEXT", otb.b(hashSet));
        hashMap.put("ITEM_PARENT_TITLE", arrayList5);
        hashMap.put("ITEM_DESCRIPTION", arrayList6);
        hashMap.put("ITEM_PAGE_ATTRIBUTE", arrayList8);
        hashMap.put("ITEM_JOIN_NUMBER", arrayList7);
        a(context, hashMap, list, z2);
    }

    private void c(GlobalSearchContent globalSearchContent, List<String> list, List<String> list2) {
        if (globalSearchContent.getInfoType() == 0) {
            list.add(globalSearchContent.getLessonName());
            list2.add("");
            return;
        }
        if (globalSearchContent.getInfoType() == 1 && globalSearchContent.isSubCourse()) {
            list.add(globalSearchContent.getLessonName());
            list2.add(globalSearchContent.getSeriesCourseName());
            return;
        }
        String seriesCourseName = globalSearchContent.getSeriesCourseName();
        if (globalSearchContent.getTotalChapters() > 0) {
            seriesCourseName = seriesCourseName + "·" + ffy.b(R.plurals._2130903129_res_0x7f030059, globalSearchContent.getTotalChapters(), Integer.valueOf(globalSearchContent.getTotalChapters()));
        }
        list.add(seriesCourseName);
        list2.add("");
    }

    private String e(GlobalSearchContent globalSearchContent, float f) {
        if (globalSearchContent.getLessonType() == 1) {
            return b(globalSearchContent, f);
        }
        return d(globalSearchContent);
    }

    private String d(GlobalSearchContent globalSearchContent) {
        LogUtil.c("CourseSearchProvider", "getHealthContentSubtitle ", globalSearchContent.getLabels(), " duration: ", Integer.valueOf(globalSearchContent.getDuration()));
        StringBuilder sb = new StringBuilder();
        if (globalSearchContent.getDuration() / 60 > 0) {
            sb.append(ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, (globalSearchContent.getDuration() / 60) + ""));
        }
        return sb.toString();
    }

    private String b(GlobalSearchContent globalSearchContent, float f) {
        String str;
        if (globalSearchContent.getDifficulty() == 0 && globalSearchContent.getDuration() == 0 && globalSearchContent.getCalorie() == 0) {
            LogUtil.a("CourseSearchProvider", "getCourseSubtitle all info is zero");
            return globalSearchContent.getDescription();
        }
        LogUtil.c("CourseSearchProvider", "getCourseSubtitle difficulty: ", Integer.valueOf(globalSearchContent.getDifficulty()), " duration: ", Integer.valueOf(globalSearchContent.getDuration()), " calorie: ", Integer.valueOf(globalSearchContent.getCalorie()));
        if (globalSearchContent.getInfoType() != 1 || globalSearchContent.isSubCourse()) {
            str = ggm.d(globalSearchContent.getDifficulty()) + "  ";
        } else {
            str = "";
        }
        String d = ffy.d(arx.b(), R.string._2130837534_res_0x7f02001e, moe.k(globalSearchContent.getDuration()));
        StringBuilder sb = new StringBuilder(str);
        if (((int) Math.round(globalSearchContent.getDuration() / 60.0d)) > 0) {
            sb.append(d);
        }
        if (((int) gic.d(globalSearchContent.getCalorie() * f)) > 0) {
            String d2 = gic.d(BaseApplication.getContext(), R.string._2130837535_res_0x7f02001f, gic.a(gic.d(globalSearchContent.getCalorie() * f)));
            sb.append("  ");
            sb.append(d2);
        }
        return sb.toString();
    }

    private void a(final Context context, Map<String, Object> map, final List<GlobalSearchContent> list, final boolean z) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.CourseSearchProvider.3
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                GlobalSearchContent globalSearchContent;
                if (koq.b(list, i) || (globalSearchContent = (GlobalSearchContent) list.get(i)) == null) {
                    return;
                }
                String deepLink = globalSearchContent.getDeepLink();
                if (!TextUtils.isEmpty(deepLink)) {
                    CourseSearchProvider.this.d(context, globalSearchContent, z, deepLink);
                } else {
                    CourseSearchProvider.this.e(context, globalSearchContent, z);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context, GlobalSearchContent globalSearchContent, boolean z) {
        if (TextUtils.isEmpty(globalSearchContent.getId())) {
            LogUtil.e("CourseSearchProvider", "jumpToDetail fail: course id is null");
            return;
        }
        if (LoginInit.getInstance(context).isBrowseMode()) {
            LogUtil.a("CourseSearchProvider", "jumpToDetail fail: isBrowseMode");
            LoginInit.getInstance(context).browsingToLogin(new IBaseResponseCallback() { // from class: oso
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    CourseSearchProvider.e(i, obj);
                }
            }, "");
            return;
        }
        int lessonType = globalSearchContent.getLessonType();
        if (lessonType == 1) {
            if (globalSearchContent.getInfoType() == 1) {
                if (globalSearchContent.isSubCourse()) {
                    b(globalSearchContent.getId(), context, globalSearchContent, z);
                    return;
                } else {
                    c(context, globalSearchContent, z);
                    return;
                }
            }
            b(globalSearchContent.getId(), context, globalSearchContent, z);
            return;
        }
        if (lessonType != 2) {
            if (lessonType == 3) {
                d(context, globalSearchContent, z, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.breath-training?h5pro=true&urlType=4&pName=com.huawei.health&statusBarTextBlack&isImmerse&path=trainPage&pullfrom=globalsearch&trainModeId=" + globalSearchContent.getId());
                return;
            }
            LogUtil.e("CourseSearchProvider", "jumpToDetail lessonType error");
            return;
        }
        if (globalSearchContent.getInfoType() == 0) {
            d(context, globalSearchContent, z, "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=audioDetail&type=sleepAudio&statusBarTextBlack&isImmerse&pullfrom=globalsearch&id=" + globalSearchContent.getId());
        } else if (globalSearchContent.getInfoType() == 1) {
            b(context, globalSearchContent, z);
        } else {
            LogUtil.e("CourseSearchProvider", "jumpToDetail gotoSleepCourseDetail error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context, GlobalSearchContent globalSearchContent, boolean z, String str) {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi == null) {
            LogUtil.e("CourseSearchProvider", "jumpToH5Detail marketRouterApi == null");
            return;
        }
        fbh.b(context, GlobalSearchActivity.d(), 201, globalSearchContent.getId(), globalSearchContent.getLessonName(), z);
        marketRouterApi.router(str);
        LogUtil.c("CourseSearchProvider", "jumpToH5Detail url: ", str);
        ary.a().e("COLLECTION_ADD");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.search.dataprovider.CourseSearchProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    CourseSearchProvider.this.e(context, sectionBean);
                }
            });
        } else if (otb.a(this)) {
            otb.c(context, sectionBean, 201);
        } else {
            eiw.c(GlobalSearchActivity.d(), "newlesson", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.CourseSearchProvider.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.c("CourseSearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    otb.c(context, sectionBean, 201);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    otb.b(list, sectionBean, context, "newlesson", 201);
                }
            });
        }
    }

    private void b(String str, final Context context, final GlobalSearchContent globalSearchContent, final boolean z) {
        ((CourseApi) Services.a("CoursePlanService", CourseApi.class)).getCourseById(str, null, null, new UiCallback<Workout>() { // from class: com.huawei.ui.homehealth.search.dataprovider.CourseSearchProvider.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.a("CourseSearchProvider", "getCourseById fail,", str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Workout workout) {
                FitWorkout a2 = mod.a(workout);
                if (a2 == null) {
                    LogUtil.a("CourseSearchProvider", "fitWorkout is null");
                } else {
                    fbh.b(context, GlobalSearchActivity.d(), 201, globalSearchContent.getId(), globalSearchContent.getLessonName(), z);
                    dod.c(context, a2, "9");
                }
            }
        });
    }

    private void c(Context context, GlobalSearchContent globalSearchContent, boolean z) {
        String seriesCourseId = globalSearchContent.isSubCourse() ? globalSearchContent.getSeriesCourseId() : globalSearchContent.getId();
        if (TextUtils.isEmpty(seriesCourseId)) {
            LogUtil.a("CourseSearchProvider", "jumpToSeriesCourseH5 id is null");
            return;
        }
        String str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.course?h5pro=true&urlType=4&pName=com.huawei.health&statusBarTextBlack&isImmerse&pullfrom=globalsearch&id=" + seriesCourseId;
        if (e(globalSearchContent)) {
            str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&statusBarTextBlack&showStatusBar&isImmerse&path=seriesCourse&pullfrom=globalsearch&id=" + seriesCourseId + "&type=1";
        }
        if (globalSearchContent.isSubCourse() && !TextUtils.isEmpty(globalSearchContent.getSeriesCourseId())) {
            str = str + "&workoutId=" + globalSearchContent.getId();
        }
        d(context, globalSearchContent, z, str);
    }

    private void b(Context context, GlobalSearchContent globalSearchContent, boolean z) {
        String seriesCourseId = globalSearchContent.isSubCourse() ? globalSearchContent.getSeriesCourseId() : globalSearchContent.getId();
        if (TextUtils.isEmpty(seriesCourseId)) {
            LogUtil.a("CourseSearchProvider", "gotoSleepCourseDetail id is null");
            return;
        }
        if (e(globalSearchContent)) {
            String str = "huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&statusBarTextBlack&showStatusBar&isImmerse&path=seriesCourse&pullfrom=globalsearch&id=" + seriesCourseId + "&type=2";
            if (globalSearchContent.isSubCourse() && !TextUtils.isEmpty(globalSearchContent.getSeriesCourseId())) {
                str = str + "&workoutId=" + globalSearchContent.getId();
            }
            d(context, globalSearchContent, z, str);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", seriesCourseId);
        AppRouter.b("/Main/SeriesCourseListActivity").zF_(bundle).c(context);
        fbh.b(context, GlobalSearchActivity.d(), 201, globalSearchContent.getId(), globalSearchContent.getLessonName(), z);
    }

    private boolean e(GlobalSearchContent globalSearchContent) {
        return globalSearchContent.getInfoType() == 1 && globalSearchContent.getEnableNewUrl() == 1;
    }
}
