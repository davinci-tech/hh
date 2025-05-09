package com.huawei.ui.homehealth.search.dataprovider;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.request.ActivityIdInfo;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.health.marketing.request.RespActivityList;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.ActivityHtmlPathApi;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.SearchResultFragment;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import defpackage.ceb;
import defpackage.eiw;
import defpackage.fbh;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.otb;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class ActivitySearchProvider extends BaseKnitDataProvider<List<SingleGridContent>> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9592a;
    private String b = "";
    private Map<String, ceb> e = new HashMap();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        d(context, (HashMap<String, Object>) hashMap, (List<SingleGridContent>) obj);
    }

    public void d(Context context, HashMap<String, Object> hashMap, List<SingleGridContent> list) {
        c(context, hashMap, list);
    }

    private void c(Context context, HashMap<String, Object> hashMap, List<SingleGridContent> list) {
        this.f9592a = otb.a(this);
        hashMap.put("TITLE", BaseApplication.getContext().getResources().getString(R$string.IDS_take_part_in_activities));
        if (this.f9592a) {
            hashMap.put("SHOWMORE", context.getString(R.string.IDS_device_show_more_heartrate_device));
            otb.a(context, "SHOW_MORE_CLICK_EVENT", hashMap, 204);
            hashMap.put("ITEM_LIMIT", 3);
        }
        e(context, hashMap, list);
        hashMap.put("HIGHLIGHTED_TEXT", GlobalSearchActivity.d());
        c(context, (Map<String, Object>) hashMap, list);
    }

    private void e(Context context, HashMap<String, Object> hashMap, List<SingleGridContent> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        for (SingleGridContent singleGridContent : list) {
            if (singleGridContent != null) {
                arrayList.add(1008);
                arrayList2.add(singleGridContent.getItemId());
                arrayList3.add(singleGridContent.getTheme());
                a(singleGridContent, arrayList4);
                arrayList7.add(singleGridContent.getPicture());
                d(singleGridContent, arrayList5, arrayList8);
                e(singleGridContent, arrayList6);
                b(singleGridContent, arrayList9);
            }
        }
        hashMap.put("ITEM_VIEW_TYPE", arrayList);
        hashMap.put("ITEM_BI_EVENT_MAP", otb.b(204, this.f9592a ? 200 : 204, GlobalSearchActivity.d(), arrayList2, arrayList3));
        hashMap.put("BI_OBSERVER_LABEL", fbh.d(this.f9592a ? 200 : 204));
        hashMap.put("ITEM_TITLE", arrayList3);
        hashMap.put("ITEM_DESCRIPTION", arrayList4);
        hashMap.put("ITEM_STATUS", arrayList5);
        hashMap.put("ITEM_JOIN_NUMBER", arrayList6);
        hashMap.put("ITEM_IMAGE", arrayList7);
        hashMap.put("ITEM_STATUS_BACKGROUND", arrayList8);
        hashMap.put("ITEM_PAGE_ATTRIBUTE", arrayList9);
    }

    private void a(SingleGridContent singleGridContent, List<String> list) {
        if (TextUtils.isEmpty(singleGridContent.getBeginDate()) || TextUtils.isEmpty(singleGridContent.getEndDate())) {
            list.add("");
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date parse = simpleDateFormat.parse(singleGridContent.getBeginDate());
            Date parse2 = simpleDateFormat.parse(singleGridContent.getEndDate());
            list.add(DateUtils.formatDateTime(BaseApplication.getContext(), parse.getTime(), 24) + " - " + DateUtils.formatDateTime(BaseApplication.getContext(), parse2.getTime(), 24));
        } catch (ParseException unused) {
            list.add("");
        }
    }

    private void d(SingleGridContent singleGridContent, List<String> list, List<Drawable> list2) {
        String string;
        Drawable drawable;
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.h("ActivitySearchProvider", "operationInteractorsApi = null");
            return;
        }
        Context context = BaseApplication.getContext();
        LogUtil.a("ActivitySearchProvider", "setItemActivityStatus currentTime:", this.b, "--", singleGridContent.getBeginDate(), "--", singleGridContent.getEndDate(), "--", singleGridContent.getTheme(), "--id:", singleGridContent.getDynamicDataId());
        int activityStatus = operationInteractorsApi.getActivityStatus(this.b, singleGridContent.getBeginDate(), singleGridContent.getEndDate());
        if (activityStatus == -1) {
            string = context.getResources().getString(R$string.IDS_activity_social_is_over);
            drawable = context.getResources().getDrawable(R.drawable._2131430821_res_0x7f0b0da5);
        } else if (activityStatus == 0) {
            string = context.getResources().getString(R$string.IDS_activity_social_coming_soon);
            drawable = context.getResources().getDrawable(R.drawable._2131427521_res_0x7f0b00c1);
        } else if (activityStatus != 1) {
            string = "";
            drawable = null;
        } else {
            string = context.getResources().getString(R$string.IDS_hwh_home_group_underway);
            drawable = context.getResources().getDrawable(R.drawable._2131427521_res_0x7f0b00c1);
        }
        list.add(string);
        list2.add(drawable);
    }

    private void e(SingleGridContent singleGridContent, List<String> list) {
        String string;
        if (TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.ACTIVITY_TYPE) || TextUtils.equals(singleGridContent.getItemCategory(), SingleDailyMomentContent.ACTIVITY_TYPE)) {
            LogUtil.a("ActivitySearchProvider", "numOfPeople:", singleGridContent.getNumberOfPeople() + "--", singleGridContent.getTheme());
            int numberOfPeople = singleGridContent.getNumberOfPeople();
            if (!TextUtils.isEmpty(singleGridContent.getDynamicDataId()) && numberOfPeople >= 0) {
                string = BaseApplication.getContext().getResources().getString(R$string.IDS_activity_social_people_attended, UnitUtil.e(numberOfPeople, 1, 0));
                list.add(string);
            }
        }
        string = "";
        list.add(string);
    }

    private void b(SingleGridContent singleGridContent, List<String> list) {
        list.add(BaseApplication.getContext().getResources().getString(R$string.IDS_main_home_bottom_text_activity));
    }

    private void c(final Context context, Map<String, Object> map, final List<SingleGridContent> list) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.1
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
                SingleGridContent singleGridContent;
                if (nsn.o()) {
                    LogUtil.h("ActivitySearchProvider", "click too fast");
                    return;
                }
                if (koq.b(list, i) || (singleGridContent = (SingleGridContent) list.get(i)) == null) {
                    return;
                }
                String linkValue = singleGridContent.getLinkValue();
                if (TextUtils.isEmpty(linkValue)) {
                    ActivitySearchProvider.this.a(context, singleGridContent, true);
                    return;
                }
                LogUtil.a("ActivitySearchProvider", "activity deepLink: ", linkValue);
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    fbh.b(context, GlobalSearchActivity.d(), 204, singleGridContent.getItemId(), singleGridContent.getTheme(), ActivitySearchProvider.this.f9592a);
                    marketRouterApi.router(otb.d(linkValue));
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Context context, final SingleGridContent singleGridContent, boolean z) {
        String itemId = singleGridContent.getItemId();
        String theme = singleGridContent.getTheme();
        if (z && this.e.isEmpty()) {
            a(new UiCallback<Map<String, ceb>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("ActivitySearchProvider", "getActivities failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    nrh.b(BaseApplication.getContext(), R.string._2130850096_res_0x7f023130);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<String, ceb> map) {
                    if (map != null) {
                        ActivitySearchProvider.this.e = map;
                        ActivitySearchProvider.this.a(context, singleGridContent, false);
                    } else {
                        onFailure(1, "result data is null");
                    }
                }
            });
        } else {
            a(context, itemId, theme);
        }
    }

    private void a(final UiCallback<Map<String, ceb>> uiCallback) {
        if (uiCallback == null) {
            return;
        }
        final HashMap hashMap = new HashMap();
        final OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            uiCallback.onFailure(1, "operationInteractorsApi is null");
        } else {
            operationInteractorsApi.getOperationList(BaseApplication.getContext(), -1, null, new HttpResCallback() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.2
                @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
                public void onFinished(int i, String str) {
                    if (i != 200) {
                        uiCallback.onFailure(1, "response status code is not ok");
                        return;
                    }
                    try {
                        JSONArray jSONArray = new JSONObject(str).getJSONArray("activities");
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            ceb expoundOperationActivity = operationInteractorsApi.expoundOperationActivity(jSONArray.getJSONObject(i2));
                            if (expoundOperationActivity != null) {
                                hashMap.put(expoundOperationActivity.c(), expoundOperationActivity);
                            }
                        }
                        uiCallback.onSuccess(hashMap);
                    } catch (JSONException unused) {
                        uiCallback.onFailure(1, "json data error");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(final Context context, final SectionBean sectionBean) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.3
                @Override // java.lang.Runnable
                public void run() {
                    ActivitySearchProvider.this.e(context, sectionBean);
                }
            });
            return;
        }
        if (LoginInit.getInstance(context).isKidAccount()) {
            sectionBean.e((Object) null);
            return;
        }
        if (this.e.isEmpty()) {
            a(new UiCallback<Map<String, ceb>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("ActivitySearchProvider", "getActivities failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<String, ceb> map) {
                    if (map != null) {
                        ActivitySearchProvider.this.e = map;
                    } else {
                        onFailure(1, "result data is null");
                    }
                }
            });
        }
        if (otb.a(this)) {
            d(sectionBean);
        } else {
            eiw.c(GlobalSearchActivity.d(), "activity", new UiCallback<List<GlobalSearchResult>>() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.10
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("ActivitySearchProvider", "request failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    ActivitySearchProvider.this.d(sectionBean);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<GlobalSearchResult> list) {
                    for (GlobalSearchResult globalSearchResult : list) {
                        if (globalSearchResult != null && "activity".equals(globalSearchResult.getCategoryId()) && !koq.b(globalSearchResult.getContent())) {
                            List d = ActivitySearchProvider.this.d(globalSearchResult.getContent());
                            if (!koq.b(d)) {
                                sectionBean.e(d);
                                return;
                            }
                        }
                    }
                    LogUtil.a("ActivitySearchProvider", "no activity found, show activities in the overall result");
                    ActivitySearchProvider.this.d(sectionBean);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(SectionBean sectionBean) {
        LogUtil.a("ActivitySearchProvider", "loadActivityOverallData");
        SearchResultFragment d = SearchResultFragment.d();
        if (d == null) {
            sectionBean.e((Object) null);
            return;
        }
        List<GlobalSearchContent> a2 = d.a(204);
        if (koq.b(a2)) {
            sectionBean.e((Object) null);
            return;
        }
        List<SingleGridContent> d2 = d(a2);
        if (koq.b(d2)) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(d2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SingleGridContent> d(List<GlobalSearchContent> list) {
        int activityStatus;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap = new HashMap();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent != null && !TextUtils.isEmpty(globalSearchContent.getId())) {
                arrayList2.add(globalSearchContent.getId());
                hashMap.put(globalSearchContent.getId(), globalSearchContent);
            }
        }
        RespActivityList a2 = eiw.a(arrayList2);
        if (a2 == null) {
            return arrayList;
        }
        String currentTime = a2.getCurrentTime();
        List<ActivityIdInfo> pageActivityList = a2.getPageActivityList();
        if (TextUtils.isEmpty(currentTime) || koq.b(pageActivityList)) {
            LogUtil.a("ActivitySearchProvider", "current time or activity id info list is empty");
            return arrayList;
        }
        this.b = currentTime;
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.h("ActivitySearchProvider", "operationInteractorsApi = null");
            return arrayList;
        }
        HashMap hashMap2 = new HashMap();
        ArrayList<ActivityIdInfo> arrayList3 = new ArrayList();
        for (ActivityIdInfo activityIdInfo : pageActivityList) {
            if (activityIdInfo != null && !TextUtils.isEmpty(activityIdInfo.getActivityId()) && (activityStatus = operationInteractorsApi.getActivityStatus(currentTime, activityIdInfo.getBeginDate(), activityIdInfo.getEndDate())) != -1) {
                arrayList3.add(activityIdInfo);
                hashMap2.put(activityIdInfo.getActivityId(), Integer.valueOf(activityStatus));
            }
        }
        for (ActivityIdInfo activityIdInfo2 : arrayList3) {
            GlobalSearchContent globalSearchContent2 = (GlobalSearchContent) hashMap.get(activityIdInfo2.getActivityId());
            if (globalSearchContent2 != null) {
                arrayList.add(d(activityIdInfo2, globalSearchContent2));
            }
        }
        return arrayList;
    }

    private void a(final Context context, final String str, final String str2) {
        GRSManager.a(context).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: com.huawei.ui.homehealth.search.dataprovider.ActivitySearchProvider.9
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str3) {
                ceb cebVar = (ceb) ActivitySearchProvider.this.e.get(str);
                ActivityHtmlPathApi activityHtmlPathApi = (ActivityHtmlPathApi) Services.a("PluginOperation", ActivityHtmlPathApi.class);
                if (((OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class)) == null || activityHtmlPathApi == null) {
                    return;
                }
                StringBuilder sb = new StringBuilder(str3);
                sb.append(activityHtmlPathApi.getActivityHtmlPath());
                sb.append("activityShare");
                if (cebVar != null && cebVar.p() == 5) {
                    sb.append("New");
                }
                sb.append(".html?activityId=");
                sb.append(str);
                String sb2 = sb.toString();
                LogUtil.a("ActivitySearchProvider", "activityUrl: ", sb2);
                MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
                if (marketRouterApi != null) {
                    fbh.b(context, GlobalSearchActivity.d(), 204, str, str2, ActivitySearchProvider.this.f9592a);
                    marketRouterApi.router(otb.d(sb2));
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("ActivitySearchProvider", "GRSManager onCallBackFail ACTIVITY_KEY resultCode = " + i);
            }
        });
    }

    private SingleGridContent d(ActivityIdInfo activityIdInfo, GlobalSearchContent globalSearchContent) {
        SingleGridContent build = new SingleGridContent.Builder().setTheme(globalSearchContent.getActivityName()).setThemeVisibility(true).setDescription(globalSearchContent.getDescription()).setDescriptionVisibility(true).setItemId(globalSearchContent.getId()).setDynamicDataId(globalSearchContent.getId()).setPicture(globalSearchContent.getPicUrl()).setLinkValue(globalSearchContent.getDeepLink()).setItemCategory(SingleDailyMomentContent.ACTIVITY_TYPE).build();
        build.setBeginDate(activityIdInfo.getBeginDate());
        build.setEndDate(activityIdInfo.getEndDate());
        build.setNumberOfPeople(activityIdInfo.getNumberOfPeople());
        return build;
    }
}
