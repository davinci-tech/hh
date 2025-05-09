package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ColumRecommendInfo;
import com.huawei.health.marketing.datatype.RcmItem;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleGridContent;
import com.huawei.health.marketing.datatype.templates.TopBannerTemplate;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.marketing.views.HealthFloatBar;
import com.huawei.health.marketing.views.SportBannerLayout;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.floatview.HealthFloatingView;
import com.huawei.ui.commonui.utils.ScrollUtil;
import com.huawei.ui.commonui.woundplastadview.WoundPlast;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class eil {
    public static boolean b(int i) {
        return i == 1 || i == 109 || i == 68 || i == 69 || i == 89 || i == 70 || i == 45 || i == 50 || i == 2 || i == 81 || i == 67;
    }

    public static String c(Context context) {
        String e = CommonUtil.e(context);
        return e.contains(Constants.LINK) ? e.substring(0, e.indexOf(Constants.LINK)) : e;
    }

    public static String e() {
        if (!EnvironmentInfo.k()) {
            return nsn.ae(BaseApplication.getContext()) ? "5" : SystemInfo.a() ? "1" : SystemInfo.d() ? SystemInfo.h() ? "2" : "1" : "3";
        }
        LogUtil.c("MarketingUtils", "getMarketClientType is mobile app engine");
        return "1";
    }

    public static void alQ_(Context context, LinearLayout linearLayout, int i) {
        if (context == null) {
            com.huawei.hwlogsmodel.LogUtil.b("MarketingUtils", "initMarketing failed cause context is null!");
            return;
        }
        if (linearLayout == null) {
            com.huawei.hwlogsmodel.LogUtil.b("MarketingUtils", "initMarketing failed cause marketingLayout is null!");
            return;
        }
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(i);
            com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "request positionId = ", Integer.valueOf(i));
            resourceResultInfo.addOnSuccessListener(new d(context, marketingApi, linearLayout));
        }
    }

    static class d implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {
        LinearLayout b;
        MarketingApi c;
        WeakReference<Context> d;

        d(Context context, MarketingApi marketingApi, LinearLayout linearLayout) {
            this.d = new WeakReference<>(context);
            this.c = marketingApi;
            this.b = linearLayout;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Map<Integer, ResourceResultInfo> map) {
            WeakReference<Context> weakReference = this.d;
            if (weakReference == null) {
                com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "ResourceSucceedListener mWeakReference is null.");
                return;
            }
            final Context context = weakReference.get();
            if (context == null) {
                com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "ResourceSucceedListener context is null.");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: eil.d.4
                    @Override // java.lang.Runnable
                    public void run() {
                        final Map<Integer, ResourceResultInfo> filterMarketingRules = d.this.c.filterMarketingRules(map);
                        HandlerExecutor.e(new Runnable() { // from class: eil.d.4.2
                            @Override // java.lang.Runnable
                            public void run() {
                                d.this.alX_(d.this.c.getMarketingViewList(context, filterMarketingRules), d.this.b);
                            }
                        });
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void alX_(List<View> list, LinearLayout linearLayout) {
            if (koq.b(list)) {
                com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "generationViews viewList is empty");
                return;
            }
            for (View view : list) {
                if (view instanceof SportBannerLayout) {
                    ((SportBannerLayout) view).setParentViewGroup(linearLayout);
                    com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "SportBanner parent width:", Integer.valueOf(linearLayout.getWidth()), " height:", Integer.valueOf(linearLayout.getHeight()));
                }
                linearLayout.addView(view);
            }
        }
    }

    public static void alR_(final int i, final LinearLayout linearLayout) {
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: eil.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "get woundPlast success. positionId: ", Integer.valueOf(i));
                    ResourceResultInfo resourceResultInfo = marketingApi.filterMarketingRules(map).get(Integer.valueOf(i));
                    if (resourceResultInfo == null || resourceResultInfo.getTotalNum() == 0) {
                        com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "wound plast resourceResultInfo is null. PositionId: ", Integer.valueOf(i));
                        return;
                    }
                    final ResourceBriefInfo resourceBriefInfo = resourceResultInfo.getResources().get(0);
                    String content = resourceBriefInfo.getContent().getContent();
                    Gson gson = new Gson();
                    final long currentTimeMillis = System.currentTimeMillis();
                    final TopBannerTemplate topBannerTemplate = (TopBannerTemplate) gson.fromJson(content, TopBannerTemplate.class);
                    WoundPlast woundPlast = new WoundPlast(com.huawei.haf.application.BaseApplication.e());
                    woundPlast.setAdImage(topBannerTemplate.getPicture());
                    woundPlast.setImageSizeMode(WoundPlast.ImageSizeMode.SMALL_IMAGE);
                    woundPlast.setAdClickListener(new View.OnClickListener() { // from class: eil.5.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            MarketingBiUtils.b(i, resourceBriefInfo, currentTimeMillis);
                            eil.a(topBannerTemplate.getLinkValue(), i, resourceBriefInfo);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    woundPlast.setCloseBtnClickListener(new View.OnClickListener() { // from class: eil.5.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            eil.a(i, 1, resourceBriefInfo);
                            linearLayout.setVisibility(8);
                            MarketingBiUtils.c(i, resourceBriefInfo, currentTimeMillis);
                            ViewClickInstrumentation.clickOnView(view);
                        }
                    });
                    linearLayout.addView(woundPlast);
                    MarketingBiUtils.d(i, resourceBriefInfo);
                }
            });
        }
    }

    private static void alS_(View view, String str, boolean z) {
        com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "setFloatBarVisible");
        if (view == null) {
            com.huawei.hwlogsmodel.LogUtil.c("MarketingUtils", "view is null.");
            return;
        }
        if (!z) {
            com.huawei.hwlogsmodel.LogUtil.c("MarketingUtils", "view is inVisible.");
            return;
        }
        View findViewWithTag = view.findViewWithTag(67);
        if (!(findViewWithTag instanceof HealthFloatBar)) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "floatBar is not HealthFloatBar. positionId = ", 8002, "contenttype is", 67);
            ObserverManagerUtil.c("HEALTH_FLOAT_BAR_STATE_CHANGE", 8);
        } else {
            ((HealthFloatBar) findViewWithTag).setPageVisibility(str);
            ntd.b().cMD_(findViewWithTag, true);
        }
    }

    private static void alU_(View view, String str, boolean z) {
        com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "setShapedFloatBoxVisible");
        if (view == null) {
            com.huawei.hwlogsmodel.LogUtil.c("MarketingUtils", "view is null.");
            return;
        }
        View findViewWithTag = view.findViewWithTag(81);
        ntd.b().cMD_(findViewWithTag, true);
        if (!z) {
            com.huawei.hwlogsmodel.LogUtil.c("MarketingUtils", "view is inVisible.");
        } else if (!(findViewWithTag instanceof HealthFloatBar)) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "floatBar is not HealthFloatBar. positionId = ", 8002, "contenttype is", 81);
            ObserverManagerUtil.c("HEALTH_FLOAT_BAR_STATE_CHANGE", 8);
        } else {
            ((HealthFloatBar) findViewWithTag).setPageVisibility(str);
        }
    }

    private static void alT_(View view, boolean z, int i) {
        com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "setFloatingViewVisible, positionId: ", Integer.valueOf(i));
        HealthFloatingView cKu_ = ScrollUtil.cKu_(view, i);
        if (cKu_ == null) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "floatview is null. positionId = ", Integer.valueOf(i));
            return;
        }
        if (z) {
            if (cKu_.i()) {
                cKu_.j();
                nmh.a(cKu_);
                return;
            }
            return;
        }
        if (cKu_.i()) {
            return;
        }
        cKu_.c();
    }

    public static void alV_(View view, boolean z, int i, String str) {
        com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "setViewVisible, isVisible: ", Boolean.valueOf(z), ", positionId: ", Integer.valueOf(i));
        if (z) {
            nmh.c(i);
        }
        if (view == null) {
            com.huawei.hwlogsmodel.LogUtil.c("MarketingUtils", "view is null.");
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            alS_(view, str, z);
            alU_(view, str, z);
        }
        alT_(view, z, i);
    }

    public static void a(int i, int i2, ResourceBriefInfo resourceBriefInfo) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.recordResourcePresent(i, i2, resourceBriefInfo);
        }
    }

    public static void c(String str, int i, ResourceBriefInfo resourceBriefInfo, int i2) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "linkUrl is null.");
            return;
        }
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        String c = c(str, resourceBriefInfo.getCategory());
        if (marketRouterApi != null) {
            marketRouterApi.router(c(c, resourceBriefInfo, i, i2));
        }
    }

    public static void a(String str, int i, ResourceBriefInfo resourceBriefInfo) {
        c(str, i, resourceBriefInfo, 1);
    }

    public static String c(String str, ResourceBriefInfo resourceBriefInfo, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        sb.append(i);
        String sb2 = sb.toString();
        if (resourceBriefInfo == null) {
            return sb2;
        }
        return sb2 + "&resourceName=" + resourceBriefInfo.getResourceName() + "&resourceId=" + resourceBriefInfo.getResourceId() + "&pullOrder=" + i2;
    }

    public static void c(ColumRecommendInfo columRecommendInfo, ResourceBriefInfo resourceBriefInfo) {
        if (columRecommendInfo == null || resourceBriefInfo == null) {
            return;
        }
        String d2 = d(resourceBriefInfo.getRecommendList(), columRecommendInfo.getItemId());
        if (!TextUtils.isEmpty(d2)) {
            columRecommendInfo.setAlgId(d2);
            columRecommendInfo.setSmartRecommend(true);
        }
        columRecommendInfo.setAbInfo(MarketingBiUtils.d(resourceBriefInfo));
        columRecommendInfo.setRecommendInfoGenerated(true);
    }

    public static String d(List<RcmItem> list, String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "getSingleGridAlgId itemId is null.");
            return "";
        }
        if (koq.b(list)) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "getSingleGridAlgId recommendList is null.");
            return "";
        }
        for (RcmItem rcmItem : list) {
            if (rcmItem != null && str.equals(rcmItem.getItemId())) {
                return rcmItem.getAlgId();
            }
        }
        return "";
    }

    public static String c(String str, int i, int i2, ColumRecommendInfo columRecommendInfo, ResourceBriefInfo resourceBriefInfo) {
        String d2;
        String d3;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (resourceBriefInfo == null) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "briefInfo is null.");
            return str;
        }
        if (columRecommendInfo == null) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "content is null.");
            return str;
        }
        String itemId = columRecommendInfo.getItemId();
        if (columRecommendInfo instanceof SingleGridContent) {
            SingleGridContent singleGridContent = (SingleGridContent) columRecommendInfo;
            itemId = resourceBriefInfo.getSmartRecommend() ? singleGridContent.getItemId() : singleGridContent.getDynamicDataId();
        }
        if (columRecommendInfo.isRecommendInfoGenerated()) {
            d2 = columRecommendInfo.getAbInfo();
            d3 = columRecommendInfo.getAlgId();
        } else {
            d2 = MarketingBiUtils.d(resourceBriefInfo);
            d3 = d(resourceBriefInfo.getRecommendList(), itemId);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        sb.append(i);
        sb.append("&resourceName=");
        sb.append(resourceBriefInfo.getResourceName());
        sb.append("&resourceId=");
        sb.append(resourceBriefInfo.getResourceId());
        sb.append("&pullOrder=");
        sb.append(i2);
        sb.append("&algId=");
        sb.append(d3);
        sb.append("&itemId=");
        sb.append(itemId);
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(d2)) {
            return sb2;
        }
        return sb2 + "&ab_info=" + d2;
    }

    public static String c(String str, String str2) {
        if (TextUtils.isEmpty(str) || !"Merchandise".equals(str2)) {
            return str;
        }
        if (str.contains("?")) {
            return str + "&pullCategory=" + str2;
        }
        return str + "?pullCategory=" + str2;
    }

    public static String a() {
        return nsn.ae(BaseApplication.getContext()) ? "5" : SystemInfo.a() ? "1" : SystemInfo.d() ? SystemInfo.h() ? "2" : "1" : "3";
    }

    public static boolean a(long j, long j2) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        return (j == 0 || j2 == 0) ? (j == 0 || j2 != 0) ? j != 0 || j2 == 0 || timeInMillis <= j2 : j <= timeInMillis : j <= timeInMillis && timeInMillis <= j2;
    }

    public static boolean e(Context context) {
        return "1".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "health_product_recommend"));
    }

    public static boolean b(Context context) {
        return "1".equals(SharedPreferenceManager.b(context, Integer.toString(10000), "personalized_recommend"));
    }

    public static boolean a(List<String> list) {
        boolean z;
        boolean z2;
        if (koq.b(list)) {
            com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "UserLabelKey is empty.");
            return true;
        }
        UserLabelServiceApi userLabelServiceApi = (UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class);
        if (userLabelServiceApi == null) {
            com.huawei.hwlogsmodel.LogUtil.h("MarketingUtils", "UserLabelServiceApi is null.");
            return true;
        }
        Context e = com.huawei.haf.application.BaseApplication.e();
        if (e(e) || !userLabelServiceApi.isContainsHealthLabel(list)) {
            z = true;
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "Contain health label. Health service switch is close.");
            z = false;
        }
        if (b(e) || !userLabelServiceApi.isContainsOtherLabel(list)) {
            z2 = true;
        } else {
            com.huawei.hwlogsmodel.LogUtil.a("MarketingUtils", "Contain other label. Personalized recommend switch is close.");
            z2 = false;
        }
        return z && z2;
    }

    public static int b(long j, long j2) {
        return Math.abs(jdl.e(j, j2)) - 1;
    }

    public static void c(int i, ResourceBriefInfo resourceBriefInfo, long j, String str, boolean z, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", Integer.valueOf(i));
        hashMap.put("resourceId", resourceBriefInfo.getResourceId());
        hashMap.put("resourceName", resourceBriefInfo.getResourceName());
        hashMap.put("event", 2);
        hashMap.put("itemCardName", str);
        hashMap.put("smartRecommend", Boolean.valueOf(z));
        hashMap.put("algId", "");
        hashMap.put("pullOrder", Integer.valueOf(i2 + 1));
        hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    public static boolean d(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        return (j == 0 || j2 == 0) ? (j == 0 || j2 != 0) ? j != 0 || j2 == 0 || currentTimeMillis <= j2 : j <= currentTimeMillis : j <= currentTimeMillis && currentTimeMillis <= j2;
    }
}
