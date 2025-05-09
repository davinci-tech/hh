package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.healthcloud.plugintrack.golf.Utils;
import com.huawei.healthcloud.plugintrack.golf.cloud.CloudManager;
import com.huawei.healthcloud.plugintrack.golf.cloud.requests.GolfCourseBriefInfosReq;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.CourseBriefInfo;
import com.huawei.healthcloud.plugintrack.golf.cloud.response.GolfCourseBrieInfosRsp;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.cau;
import defpackage.ixx;
import defpackage.koq;
import defpackage.moj;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class GolfMyTrainingCourseProvider extends BaseKnitDataProvider<List<HiHealthData>> {
    private SectionBean b;
    private List<HiHealthData> d = new ArrayList();
    private boolean c = false;
    private b e = new b(this);

    /* renamed from: a, reason: collision with root package name */
    private d f9544a = new d(this);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.c;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        List<HiHealthData> b2 = moj.b(cau.a("Track_Provider_Track_GolfMyTrainingProvider"), HiHealthData[].class);
        this.d = b2;
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "loadDefaultData() cachedGolfTrainingSize ", Integer.valueOf(b2.size()));
        if (koq.c(this.d)) {
            this.c = true;
        }
        sectionBean.e(this.d);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        if (!CommonUtil.aa(context)) {
            this.c = false;
            ReleaseLogUtil.c("Track_Provider_Track_GolfMyTrainingProvider", "Network is not connected");
            return;
        }
        this.b = sectionBean;
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "requestTrackMyTraining Record");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{286});
        HiHealthManager.d(context).fetchSequenceDataBySportType(hiDataReadOption, this.e);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, List<HiHealthData> list) {
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "parseParams");
        a(hashMap, list);
    }

    private void a(HashMap<String, Object> hashMap, List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "dataList is empty");
            this.c = false;
            return;
        }
        List b2 = moj.b(cau.a("Track_Provider_Track_GolfMyTrainingProvidername"), String[].class);
        if (koq.b(b2)) {
            LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "courseNameList is empty");
            this.c = false;
            return;
        }
        hashMap.put("NO_MORE_DATA", true);
        hashMap.put("IS_LOAD_DEFAULT_VIEW", false);
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R.string._2130845479_res_0x7f021f27));
        hashMap.put("NAME", b2);
        hashMap.put("INTERVALS", moj.b(cau.a("Track_Provider_Track_GolfMyTrainingProvidertime"), String[].class));
        ArrayList arrayList = new ArrayList();
        arrayList.add(56);
        arrayList.add(100);
        hashMap.put("IMAGE_SIZE", arrayList);
        e(hashMap, moj.b(cau.a("Track_Provider_Track_GolfMyTrainingProvider"), HiHealthData[].class));
    }

    private void c(List<HiHealthData> list, List<String> list2, List<String> list3, List<HiHealthData> list4) {
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "setCourseData");
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "hiHealthData is null");
            } else {
                try {
                    HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                    LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", ": trackMetaData=" + hiTrackMetaData);
                    if (hiTrackMetaData == null) {
                        LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", ": trackMetaData is null");
                        return;
                    }
                    String str = hiTrackMetaData.getExtendTrackMap().get("courseId");
                    if (arrayList.contains(str)) {
                        continue;
                    } else {
                        String c = c(str);
                        if (TextUtils.isEmpty(c)) {
                            continue;
                        } else {
                            arrayList.add(str);
                            list2.add(c);
                            c(hiHealthData, list3);
                            list4.add(hiHealthData);
                            if (arrayList.size() == 2) {
                                LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "trainedCourse size=" + arrayList.size());
                                return;
                            }
                        }
                    }
                } catch (JsonSyntaxException unused) {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "trackMetaData is jsonSyntaxException");
                }
            }
        }
    }

    private void e(Map<String, Object> map, final List<HiHealthData> list) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.GolfMyTrainingCourseProvider.2
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
                HiTrackMetaData hiTrackMetaData;
                if (koq.b(list)) {
                    LogUtil.b("Track_Provider_Track_GolfMyTrainingProvider", "list is empty");
                    return;
                }
                if (koq.b(list, i)) {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "position isOutOfBounds", Integer.valueOf(i));
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) list.get(i);
                if (hiHealthData == null) {
                    LogUtil.b("Track_Provider_Track_GolfMyTrainingProvider", "hiHealthData is null");
                    return;
                }
                try {
                    hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                } catch (JsonSyntaxException unused) {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "trackMetaData is jsonSyntaxException");
                    hiTrackMetaData = null;
                }
                if (hiTrackMetaData == null) {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "trackMetaData is null");
                    return;
                }
                GolfMyTrainingCourseProvider.this.b(BaseApplication.getContext(), hiTrackMetaData.getExtendTrackMap().get("courseId"));
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.GOLF_TRAINED_COURSE_CLICK_EVENT.value(), hashMap, 0);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str) {
        if (context == null) {
            LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "jumpToGolfTrainingCourse(): context is null");
            return;
        }
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "jumpToGolfTrainingCourse(): courseId is ", str);
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeJsModule(RemoteMessageConst.NOTIFICATION, bzs.e().getCommonJsModule(RemoteMessageConst.NOTIFICATION));
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
        builder.addPath("#/details?courseId=" + str + "&downLoad=2");
        builder.setImmerse();
        builder.showStatusBar();
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.golf", builder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            this.d = new ArrayList();
        } else if (list != null) {
            this.d = list;
        }
        SectionBean sectionBean = this.b;
        if (sectionBean != null) {
            sectionBean.e(this.d);
        }
    }

    static class d implements IBaseResponseCallback {
        WeakReference<GolfMyTrainingCourseProvider> d;

        d(GolfMyTrainingCourseProvider golfMyTrainingCourseProvider) {
            this.d = new WeakReference<>(golfMyTrainingCourseProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            GolfMyTrainingCourseProvider golfMyTrainingCourseProvider = this.d.get();
            if (golfMyTrainingCourseProvider == null) {
                LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "provider is null, onResponse is ", obj);
                return;
            }
            List arrayList = new ArrayList();
            if (koq.e(obj, HiHealthData.class)) {
                List list = (List) obj;
                if (list.size() > 0) {
                    LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "data size is ", Integer.valueOf(list.size()));
                    golfMyTrainingCourseProvider.c = true;
                    arrayList = list;
                } else {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "data size =", Integer.valueOf(list.size()));
                }
            } else {
                LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "class type is wrong");
            }
            if (golfMyTrainingCourseProvider.a((List<HiHealthData>) arrayList)) {
                golfMyTrainingCourseProvider.e(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        c(list, arrayList, arrayList2, arrayList3);
        if (c("Track_Provider_Track_GolfMyTrainingProvidername", moj.e(arrayList)) && c("Track_Provider_Track_GolfMyTrainingProvidertime", moj.e(arrayList2)) && c("Track_Provider_Track_GolfMyTrainingProvider", moj.e(arrayList3))) {
            LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "same data");
            return false;
        }
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "refresh cache and ui");
        cau.d("Track_Provider_Track_GolfMyTrainingProvider", moj.e(arrayList3));
        cau.d("Track_Provider_Track_GolfMyTrainingProvidertime", moj.e(arrayList));
        cau.d("Track_Provider_Track_GolfMyTrainingProvidername", moj.e(arrayList2));
        return true;
    }

    private boolean c(String str, String str2) {
        return cau.a(str).equals(str2);
    }

    private String c(String str) {
        GolfCourseBriefInfosReq.Builder builder = new GolfCourseBriefInfosReq.Builder();
        builder.addCourseId(Long.valueOf(str)).language(Utils.getLanguage());
        GolfCourseBrieInfosRsp golfCourseBrieInfos = CloudManager.getInstance().getGolfCourseBrieInfos(builder.build());
        if (golfCourseBrieInfos == null) {
            LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "setCourseName response == null");
            return null;
        }
        List<CourseBriefInfo> courseBriefInfo = golfCourseBrieInfos.getCourseBriefInfo();
        if (courseBriefInfo == null) {
            LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "setCourseName infos == null");
            return null;
        }
        LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "setCourseImageAndName: courseId=", str, "golfCourseDetail response=", courseBriefInfo);
        return !courseBriefInfo.isEmpty() ? courseBriefInfo.get(0).getName() : "";
    }

    private static void c(HiHealthData hiHealthData, List<String> list) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hiHealthData.getStartTime());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());
        calendar2.set(11, 0);
        calendar2.set(12, 0);
        calendar2.set(13, 0);
        calendar2.set(14, 0);
        long timeInMillis = (calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 86400000;
        Resources resources = BaseApplication.getContext().getResources();
        if (timeInMillis == 0) {
            list.add(resources.getString(R.string._2130845483_res_0x7f021f2b));
        } else {
            list.add(resources.getQuantityString(R.plurals._2130903329_res_0x7f030121, (int) timeInMillis, Long.valueOf(timeInMillis)));
        }
    }

    static class b implements HiDataReadResultListener {
        WeakReference<GolfMyTrainingCourseProvider> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        b(GolfMyTrainingCourseProvider golfMyTrainingCourseProvider) {
            this.d = new WeakReference<>(golfMyTrainingCourseProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            GolfMyTrainingCourseProvider golfMyTrainingCourseProvider = this.d.get();
            if (golfMyTrainingCourseProvider != null) {
                d dVar = golfMyTrainingCourseProvider.f9544a;
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "fetchSequenceDataBySportType return data is error.");
                    dVar.d(i, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "fetchSequenceDataBySportType return map is empty.");
                    dVar.d(i, null);
                    return;
                } else {
                    Object obj2 = sparseArray.get(i);
                    LogUtil.a("Track_Provider_Track_GolfMyTrainingProvider", "obj size is ", obj2.toString());
                    dVar.d(0, obj2);
                    return;
                }
            }
            LogUtil.h("Track_Provider_Track_GolfMyTrainingProvider", "onResult: provider is null");
        }
    }
}
