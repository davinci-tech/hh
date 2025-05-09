package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.request.GlobalSearchContent;
import com.huawei.health.marketing.request.GlobalSearchResult;
import com.huawei.health.marketing.request.HighLight;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.search.GlobalSearchActivity;
import com.huawei.ui.homehealth.search.SearchResultFragment;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public class otb {
    public static void a(Context context, final String str, Map<String, Object> map, final int i) {
        if (str == null) {
            return;
        }
        map.put(str, new OnClickSectionListener() { // from class: otb.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2, int i3) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i2, String str2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str2) {
                SearchResultFragment d;
                if (!str.equals(str2) || (d = SearchResultFragment.d()) == null) {
                    return;
                }
                d.c(i);
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static void c(Context context, SectionBean sectionBean, int i) {
        LogUtil.c("SearchProviderUtil", "loadOverallData pageType: ", Integer.valueOf(i));
        SearchResultFragment d = SearchResultFragment.d();
        if (d == null) {
            sectionBean.e((Object) null);
            return;
        }
        List<GlobalSearchContent> a2 = d.a(i == 300 ? 206 : i);
        if (koq.b(a2)) {
            sectionBean.e((Object) null);
            return;
        }
        if (i == 206) {
            a2 = c(a2, true);
        }
        if (i == 300) {
            a2 = c(a2, false);
        }
        if (koq.b(a2)) {
            sectionBean.e((Object) null);
        } else {
            sectionBean.e(a2);
        }
    }

    public static List<GlobalSearchContent> e(int i) {
        List<GlobalSearchContent> a2;
        ArrayList arrayList = new ArrayList();
        SearchResultFragment d = SearchResultFragment.d();
        if (d == null || (a2 = d.a(i)) == null) {
            return arrayList;
        }
        arrayList.addAll(a2);
        return arrayList;
    }

    public static List<GlobalSearchContent> d() {
        ArrayList arrayList = new ArrayList();
        SearchResultFragment d = SearchResultFragment.d();
        return d == null ? arrayList : d.a(301);
    }

    public static boolean a(BaseKnitDataProvider baseKnitDataProvider) {
        Bundle extra = baseKnitDataProvider.getExtra();
        if (extra == null) {
            return false;
        }
        return extra.getBoolean("IS_OVERALL", false);
    }

    public static List<Map<String, Object>> b(int i, int i2, String str, List<String> list, List<String> list2) {
        if (str == null || koq.b(list) || koq.b(list2) || list2.size() != list.size()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i3 = 0; i3 < list2.size(); i3++) {
            arrayList.add(a(i, i2, str, list.get(i3), list2.get(i3)));
        }
        return arrayList;
    }

    public static Map<String, Object> a(int i, int i2, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put(ParamConstants.CallbackMethod.ON_SHOW, "1");
        hashMap.put("word", str);
        hashMap.put("category", Integer.valueOf(i));
        hashMap.put("page", Integer.valueOf(i2));
        hashMap.put("id", str2);
        hashMap.put("name", str3);
        return hashMap;
    }

    public static List<GlobalSearchContent> c(List<GlobalSearchContent> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (GlobalSearchContent globalSearchContent : list) {
            if (globalSearchContent != null && ((z && globalSearchContent.getType() != 4) || (!z && globalSearchContent.getType() == 4))) {
                arrayList.add(globalSearchContent);
            }
        }
        return arrayList;
    }

    public static void a(List<GlobalSearchContent> list, int i, boolean z, Context context, boolean z2) {
        MarketRouterApi marketRouterApi;
        if (koq.b(list, i)) {
            LogUtil.e("SearchProviderUtil", "click out out bounds");
            return;
        }
        GlobalSearchContent globalSearchContent = list.get(i);
        if (globalSearchContent == null) {
            LogUtil.e("SearchProviderUtil", "click content is null");
            return;
        }
        if (z) {
            String productDetailUrl = globalSearchContent.getProductDetailUrl();
            LogUtil.c("SearchProviderUtil", "detail url: ", productDetailUrl);
            MarketRouterApi marketRouterApi2 = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
            if (marketRouterApi2 != null) {
                marketRouterApi2.router(d(productDetailUrl));
                return;
            }
            return;
        }
        String deepLink = globalSearchContent.getDeepLink();
        LogUtil.c("SearchProviderUtil", "information deeplink: ", deepLink);
        if (TextUtils.isEmpty(deepLink) || (marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class)) == null) {
            return;
        }
        fbh.b(context, GlobalSearchActivity.d(), 202, globalSearchContent.getId(), globalSearchContent.getTitle(), z2);
        marketRouterApi.router(d(deepLink));
    }

    public static void d(SectionBean sectionBean, Map<Integer, ResourceResultInfo> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.e("SearchProviderUtil", "marketingResponse is invalid");
            sectionBean.e((Object) null);
        }
    }

    public static void c(SectionBean sectionBean, Map<Integer, ResourceResultInfo> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.e("SearchProviderUtil", "filterResultInfoMap is invalid");
            sectionBean.e((Object) null);
        }
    }

    public static String d(GlobalSearchContent globalSearchContent, int i) {
        if (globalSearchContent == null) {
            return "";
        }
        if (i != 201) {
            if (i == 202) {
                return globalSearchContent.getTitle();
            }
            if (i != 300) {
                switch (i) {
                    case 204:
                        return globalSearchContent.getActivityName();
                    case 205:
                    case 206:
                        break;
                    default:
                        switch (i) {
                            case a.z /* 208 */:
                            case a.A /* 209 */:
                            case a.C /* 210 */:
                                break;
                            default:
                                return "";
                        }
                }
            }
            return globalSearchContent.getName();
        }
        return globalSearchContent.getLessonName();
    }

    public static void b(List<GlobalSearchResult> list, SectionBean sectionBean, Context context, String str, int i) {
        for (GlobalSearchResult globalSearchResult : list) {
            if (globalSearchResult != null && str.equals(globalSearchResult.getCategoryId()) && !koq.b(globalSearchResult.getContent())) {
                sectionBean.e(globalSearchResult.getContent());
                return;
            }
        }
        LogUtil.c("SearchProviderUtil", "no results found, show products in the overall result");
        c(context, sectionBean, i);
    }

    public static String c(GlobalSearchContent globalSearchContent, boolean z) {
        if (globalSearchContent == null || globalSearchContent.getHeatCount() <= 0) {
            LogUtil.a("SearchProviderUtil", "getJoinNumberString content.getJoinNumber()= 0");
            return "";
        }
        if (globalSearchContent.getHeatCount() < PreConnectManager.CONNECT_INTERNAL) {
            StringBuilder sb = new StringBuilder();
            sb.append(ffy.b(z ? R.plurals._2130903123_res_0x7f030053 : R.plurals._2130903128_res_0x7f030058, (int) globalSearchContent.getHeatCount(), Long.valueOf(globalSearchContent.getHeatCount())));
            sb.append("  ");
            return sb.toString();
        }
        String e = UnitUtil.e(globalSearchContent.getHeatCount() / 10000.0f, 1, globalSearchContent.getHeatCount() == PreConnectManager.CONNECT_INTERNAL ? 0 : 2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(ffy.b(z ? R.plurals._2130903127_res_0x7f030057 : R.plurals._2130903420_res_0x7f03017c, (int) (globalSearchContent.getHeatCount() / 1000), e));
        sb2.append("  ");
        return sb2.toString();
    }

    public static String d(GlobalSearchContent globalSearchContent, String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str2 : globalSearchContent.getLabels()) {
            if (dpf.c(str2, str) && i < 3) {
                sb.append(str2);
                sb.append("  ");
                i++;
            }
        }
        return sb.toString();
    }

    public static void e(GlobalSearchContent globalSearchContent, List<String> list, List<String> list2, HashSet<String> hashSet) {
        String description = globalSearchContent.getDescription();
        String b = b(hashSet);
        boolean c = dpf.c(description, b);
        list.add(koq.c(globalSearchContent.getLabels()) && dpf.c(globalSearchContent.getLabels().toString(), b) ? d(globalSearchContent, b) : "");
        if (description.length() > 15 && !dpf.c(description.substring(0, 15), b)) {
            description = GlobalSearchActivity.d() + "#" + description;
        }
        list2.add(c ? description : "");
    }

    public static void b(GlobalSearchContent globalSearchContent, HashSet<String> hashSet) {
        if (koq.b(globalSearchContent.getHighLights())) {
            LogUtil.a("SearchProviderUtil", "getHighlightText content.getHighLights() is null");
            return;
        }
        Pattern compile = Pattern.compile("<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*?>");
        Iterator<HighLight> it = globalSearchContent.getHighLights().iterator();
        while (it.hasNext()) {
            String highlightStr = it.next().getHighlightStr();
            if (!TextUtils.isEmpty(highlightStr)) {
                Matcher matcher = compile.matcher(highlightStr);
                while (matcher.find()) {
                    if (!TextUtils.isEmpty(matcher.group(1))) {
                        hashSet.add(matcher.group(1));
                    }
                }
            }
        }
    }

    public static String b(HashSet<String> hashSet) {
        if (koq.b(hashSet)) {
            hashSet.add(GlobalSearchActivity.d());
            LogUtil.a("SearchProviderUtil", "content highlightText is null ");
        }
        LogUtil.c("SearchProviderUtil", "getHighlightText= ", hashSet.toString());
        return hashSet.toString();
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&" : "?");
        sb.append("pullfrom=globalsearch");
        return sb.toString();
    }
}
