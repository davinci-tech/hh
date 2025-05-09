package defpackage;

import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.util.Consumer;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.bff.impl.IResponseParseCallback;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.views.ResourceBriefInfoOwnable;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ead {
    public static void a(JSONObject jSONObject, List<Integer> list, boolean z, BasePageResTrigger basePageResTrigger, final Consumer<List<SectionBean>> consumer, final List<View> list2) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        e(jSONObject, list, z, basePageResTrigger.getPageType(), basePageResTrigger.getResPosId(), new IResponseParseCallback() { // from class: ead.5
            @Override // com.huawei.health.knit.bff.impl.IResponseParseCallback
            public void onCompleted(List<SectionBean> list3) {
                ead.b(list3, list2);
                consumer.accept(list3);
                LogUtil.a("ResParseUtil", "bff parse time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(List<SectionBean> list, List<View> list2) {
        if (list2 == null || koq.b(list)) {
            return;
        }
        list2.clear();
        for (SectionBean sectionBean : list) {
            if (sectionBean != null && sectionBean.agN_() != null) {
                list2.add(sectionBean.agN_());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static List<SectionBean> a(List<ResourceBriefInfo> list, List<View> list2, boolean z, int i, int i2) {
        View view;
        LogUtil.a("ResParseUtil", "parseMarketingData");
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < list.size(); i3++) {
            ResourceBriefInfo resourceBriefInfo = list.get(i3);
            if (resourceBriefInfo != null) {
                if (!koq.b(list2)) {
                    for (KeyEvent.Callback callback : list2) {
                        if (callback instanceof ResourceBriefInfoOwnable) {
                            ResourceBriefInfoOwnable resourceBriefInfoOwnable = (ResourceBriefInfoOwnable) callback;
                            if (resourceBriefInfoOwnable.isOwnedByBriefInfo(resourceBriefInfo)) {
                                LogUtil.a("ResParseUtil", "isOwnedByBriefInfo");
                                view = (View) resourceBriefInfoOwnable;
                                break;
                            }
                        }
                    }
                }
                view = null;
                arrayList.add(new SectionBean(resourceBriefInfo, view, i, i2, z));
            }
        }
        return arrayList;
    }

    private static void e(JSONObject jSONObject, List<Integer> list, boolean z, int i, int i2, final IResponseParseCallback iResponseParseCallback) {
        String str;
        final ArrayList arrayList = new ArrayList();
        String str2 = "ResParseUtil";
        if (jSONObject == null) {
            LogUtil.a("ResParseUtil", "response is null");
            iResponseParseCallback.onCompleted(arrayList);
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject == null) {
            LogUtil.a("ResParseUtil", "data is null");
            iResponseParseCallback.onCompleted(arrayList);
            return;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("sections");
        if (optJSONArray != null && optJSONArray.length() != 0) {
            int length = optJSONArray.length();
            int i3 = 9999;
            int i4 = 0;
            while (i4 < length) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i4);
                if (optJSONObject2 == null) {
                    LogUtil.a(str2, "item is null");
                } else {
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("data");
                    if (optJSONObject3 == null) {
                        LogUtil.a(str2, "sectionData is null");
                    } else {
                        JSONObject optJSONObject4 = optJSONObject3.optJSONObject("allData");
                        if (optJSONObject4 == null) {
                            LogUtil.a(str2, "allData is null: i = ", Integer.valueOf(i4));
                        } else {
                            int optInt = optJSONObject2.optInt("templateType");
                            str = str2;
                            arrayList.add(new SectionBean(optInt, i3, i, eag.e(i, optInt), i2, optJSONObject4));
                            i3--;
                            i4++;
                            str2 = str;
                        }
                    }
                }
                str = str2;
                i4++;
                str2 = str;
            }
        }
        d(optJSONObject.optJSONObject("configurationData"), list, z, i, i2, new IResponseParseCallback() { // from class: ead.4
            @Override // com.huawei.health.knit.bff.impl.IResponseParseCallback
            public void onCompleted(List<SectionBean> list2) {
                if (!koq.b(list2)) {
                    arrayList.addAll(ead.c(list2, arrayList));
                } else {
                    LogUtil.a("ResParseUtil", "marketingSectionBeans is empty");
                }
                LogUtil.a("ResParseUtil", "SectionBean size: ", Integer.valueOf(arrayList.size()));
                iResponseParseCallback.onCompleted(arrayList);
            }
        });
    }

    private static void d(JSONObject jSONObject, final List<Integer> list, final boolean z, final int i, final int i2, final IResponseParseCallback iResponseParseCallback) {
        LogUtil.a("ResParseUtil", "getMarketingSectionBeanList");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            iResponseParseCallback.onCompleted(null);
            return;
        }
        Map<Integer, ResourceResultInfo> parseMarketingResource = marketingApi.parseMarketingResource(jSONObject, list);
        if (parseMarketingResource == null || parseMarketingResource.isEmpty()) {
            LogUtil.b("ResParseUtil", "sectionMap is empty");
            iResponseParseCallback.onCompleted(null);
            return;
        }
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(parseMarketingResource);
        if (filterMarketingRules == null) {
            LogUtil.b("ResParseUtil", "no section map for " + list, ", after ruling");
            iResponseParseCallback.onCompleted(null);
            return;
        }
        final HashMap hashMap = new HashMap(filterMarketingRules);
        HandlerExecutor.e(new Runnable() { // from class: ead.2
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                List<View> marketingViewList = MarketingApi.this.getMarketingViewList(BaseApplication.getContext(), hashMap);
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder("views: ");
                sb.append(marketingViewList != null ? marketingViewList.size() : -1);
                objArr[0] = sb.toString();
                LogUtil.a("ResParseUtil", objArr);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    int intValue = ((Integer) it.next()).intValue();
                    ResourceResultInfo resourceResultInfo = (ResourceResultInfo) hashMap.get(Integer.valueOf(intValue));
                    if (resourceResultInfo == null) {
                        LogUtil.b("ResParseUtil", "no resourceResultInfo for " + intValue);
                    } else {
                        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
                        if (koq.b(resources)) {
                            LogUtil.b("ResParseUtil", "no briefInfoList for " + intValue);
                        } else {
                            LogUtil.a("ResParseUtil", "resPosId: ", Integer.valueOf(intValue));
                            Iterator<ResourceBriefInfo> it2 = resources.iterator();
                            while (it2.hasNext()) {
                                LogUtil.a("ResParseUtil", "resourceBriefInfo: ", it2.next().toString());
                            }
                            List<SectionBean> a2 = ead.a(resources, marketingViewList, z, i, i2);
                            LogUtil.a("ResParseUtil", "request successfully sectionBeans size = ", Integer.valueOf(a2.size()));
                            arrayList.addAll(a2);
                        }
                    }
                }
                iResponseParseCallback.onCompleted(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<SectionBean> c(List<SectionBean> list, List<SectionBean> list2) {
        ArrayList arrayList = new ArrayList(list.size());
        for (SectionBean sectionBean : list) {
            Iterator<SectionBean> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    SectionBean next = it.next();
                    if (sectionBean.o() == next.o() && sectionBean.p()) {
                        next.e(sectionBean.p());
                        break;
                    }
                } else {
                    arrayList.add(sectionBean);
                    break;
                }
            }
        }
        return arrayList;
    }
}
