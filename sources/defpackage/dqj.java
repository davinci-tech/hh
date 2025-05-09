package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.trade.datatype.Product;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class dqj {

    /* renamed from: a, reason: collision with root package name */
    private static String f11782a = "";
    private static String b = "";
    private static int c = 8;
    private static boolean d = false;
    private static String e = "";
    private static String f = "";
    private static String g = "";
    private static String h = "";
    private static long i = 0;
    private static String j = "";
    private static String m = "";
    private static String o = "";

    public static void e(boolean z) {
        d = z;
    }

    public static void g(String str) {
        g = str;
    }

    public static void c(String str) {
        f11782a = str;
    }

    public static void o(String str) {
        j = str;
    }

    public static void q(String str) {
        o = str;
    }

    public static void l(String str) {
        m = str;
    }

    public static void b(String str) {
        b = str;
    }

    public static void k(String str) {
        f = str;
    }

    public static void e(int i2) {
        LogUtil.a("VipBiUtil", "from: ", Integer.valueOf(i2));
        c = i2;
    }

    public static void d(String str) {
        LogUtil.a("VipBiUtil", "province: ", str);
        h = str;
    }

    public static void e(String str) {
        LogUtil.a("VipBiUtil", "city: ", str);
        e = str;
    }

    public static String a() {
        return h;
    }

    public static String e() {
        return e;
    }

    public static void b(Context context, int i2) {
        Map<String, Object> u = u();
        u.put("event", Integer.valueOf(i2));
        c(context, AnalyticsValue.VIP_AGREEMENT_DIALOG_EVENT.value(), u);
    }

    public static void a(Context context, int i2, RecommendCardBean recommendCardBean) {
        if (recommendCardBean == null) {
            LogUtil.b("VipBiUtil", "setVipRecommendCardEvent recommendCardBean is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i2));
        hashMap.put("cardId", recommendCardBean.getCardId() != null ? recommendCardBean.getCardId() : "");
        hashMap.put("cardStatus", recommendCardBean.getCardStatus() != null ? recommendCardBean.getCardStatus() : "");
        hashMap.put("cardTopic", recommendCardBean.getTitle() != null ? recommendCardBean.getTitle() : "");
        hashMap.put("buttonTopic", recommendCardBean.getButtonText() != null ? recommendCardBean.getButtonText() : "");
        if (!TextUtils.isEmpty(recommendCardBean.getResourceId())) {
            hashMap.put("courseId", recommendCardBean.getResourceId());
        }
        c(context, recommendCardBean.getEventId(), hashMap);
    }

    public static boolean d(int i2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - i < i2) {
            return true;
        }
        i = elapsedRealtime;
        return false;
    }

    public static void c(Context context, int i2) {
        Map<String, Object> u = u();
        u.put("from", Integer.valueOf(c));
        u.put(CommonUtil.PAGE_TYPE, Integer.valueOf(i2));
        u.put("newEquity", Boolean.valueOf(d));
        u.put(WebViewHelp.BI_KEY_PULL_FROM, g);
        u.put("fromPageTitle", f11782a);
        c(context, AnalyticsValue.VIP_INTRO_PAGE_SHOW_EVENT.value(), u);
        c = 8;
    }

    public static void d(Context context, int i2) {
        Map<String, Object> u = u();
        u.put("from", Integer.valueOf(c));
        u.put(CommonUtil.PAGE_TYPE, Integer.valueOf(i2));
        u.put("newEquity", Boolean.valueOf(d));
        u.put(WebViewHelp.BI_KEY_PULL_FROM, g);
        u.put("fromPageTitle", f11782a);
        c(context, AnalyticsValue.VIP_CENTER_SHOW_EVENT.value(), u);
        c = 8;
    }

    public static void e(Context context, Product product) {
        Map<String, Object> u = u();
        u.put("isSale", Integer.valueOf((product.getPromotion() == null && product.getSubscriptionPromotion() == null) ? 0 : 1));
        u.put(HwPayConstant.KEY_PRODUCTNAME, product.getProductName());
        u.put("productId", product.getProductId());
        c(context, AnalyticsValue.VIP_INTRO_PRODUCT_SHOW_EVENT.value(), u);
    }

    public static void n(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_PRIVILEGE_OVERALL_SHOW_EVENT.value(), hashMap);
    }

    public static void d(String str, int i2, int i3, boolean z) {
        String value;
        Map<String, Object> u = u();
        u.put("type", str);
        u.put("name", b);
        u.put("payResourceType", f);
        f = "";
        u.put("from", Integer.valueOf(i2));
        u.put("ActivationMode", Integer.valueOf(i3));
        u.put(WebViewHelp.BI_KEY_PULL_FROM, g);
        g = "";
        u.put("fromPageTitle", f11782a);
        f11782a = "";
        u.put("resourceId", m);
        m = "";
        u.put("pullOrder", j);
        j = "";
        u.put("resourceName", o);
        o = "";
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            u.putAll(marketRouterApi.getLastMarketSource());
        }
        Context e2 = BaseApplication.e();
        if (z) {
            value = AnalyticsValue.VIP_RENEWAL_PAY_SUCCESS_EVENT.value();
        } else {
            value = AnalyticsValue.VIP_PAY_SUCCESS_EVENT.value();
        }
        c(e2, value, u);
        v();
    }

    private static void v() {
        String b2 = SharedPreferenceManager.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), Integer.toString(10000), "MAIN_VIP_KEY");
        LogUtil.a("VipBiUtil", "sendKakaTaskMessage vip status = :", b2);
        if ("1".equals(b2)) {
            bzw.e().finishKakaTask(BaseApplication.e(), 10005, null);
        }
    }

    public static void q() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_PRIVILEGE_MORE_CLICK_EVENT.value(), u());
    }

    public static void m(String str) {
        Map<String, Object> u = u();
        u.put("name", str);
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_PRIVILEGE_ITEM_CLICK_EVENT.value(), u);
        k();
        q();
    }

    public static void j(String str) {
        Uri parse = Uri.parse(nsa.g(str));
        HashMap hashMap = new HashMap();
        hashMap.put("operationmusic", "sleep");
        hashMap.put("breath-training", "sleep");
        hashMap.put("fitnesspage", "sport");
        hashMap.put("custom-recipe", "diet");
        hashMap.put("diet-diary", "diet");
        hashMap.put("MemberCoupons", "welfare");
        String queryParameter = parse.getQueryParameter("acquire_name");
        if (TextUtils.isEmpty(queryParameter)) {
            queryParameter = str;
        }
        String str2 = str;
        for (String str3 : hashMap.keySet()) {
            if (str.contains(str3)) {
                str2 = (String) hashMap.get(str3);
            }
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("name", queryParameter);
        hashMap2.put("benifitType", str2);
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_PRIVILEGE_CORE_SHOW_EVENT.value(), hashMap2);
    }

    public static void i(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("operationmusic", "sleep");
        hashMap.put("breath-training", "sleep");
        hashMap.put("fitnesspage", "sport");
        hashMap.put("custom-recipe", "diet");
        hashMap.put("diet-diary", "diet");
        hashMap.put("MemberCoupons", "welfare");
        String str2 = str;
        for (String str3 : hashMap.keySet()) {
            if (str.contains(str3)) {
                str2 = (String) hashMap.get(str3);
            }
        }
        Map<String, Object> u = u();
        u.put("name", str);
        u.put("benifitType", str2);
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_PRIVILEGE_CORE_ITEM_CLICK_EVENT.value(), u);
        k();
        q();
    }

    public static void s() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_HELP_SERVICE_EVENT.value(), u());
    }

    public static void t() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_MEMBER_HELP_CLICK_EVENT.value(), u());
        k();
    }

    public static void m() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_HELP_SHOW_EVENT.value(), u());
    }

    public static void l() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_HELP_CLICK_EVENT.value(), u());
        o();
    }

    public static void i() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_REDEEM_CODE_EVENT.value(), u());
    }

    public static void c() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_MEMBER_REDEEM_CODE_CLICK_EVENT.value(), u());
        k();
    }

    public static void j() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_RENEW_MANAGE_SHOW_EVENT.value(), u());
    }

    public static void g() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_AUTO_RENEW_CLICK_EVENT.value(), u());
        o();
    }

    public static void d() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_GIVEAWAY_CLICK_EVENT.value(), u());
        k();
    }

    public static void a(String str) {
        Map<String, Object> u = u();
        u.put("name", str);
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_GIVEAWAY_ITEM_CLICK_EVENT.value(), u);
        k();
    }

    public static void h() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_VIP_AGREEMENT_EVENT.value(), u());
        k();
    }

    public static void f() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_RENEW_AGREEMENT_EVENT.value(), u());
        k();
    }

    public static void d(long j2, long j3) {
        HashMap hashMap = new HashMap();
        hashMap.put("enterTime", Long.valueOf(j2));
        hashMap.put("leaveTime", Long.valueOf(j3));
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_STAY_EVENT.value(), hashMap);
    }

    public static void e(long j2, long j3) {
        HashMap hashMap = new HashMap();
        hashMap.put("enterTime", Long.valueOf(j2));
        hashMap.put("leaveTime", Long.valueOf(j3));
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_STAY_EVENT.value(), hashMap);
    }

    public static void k() {
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_CLICK_EVENT.value(), u());
    }

    public static void o() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_CLICK_EVENT.value(), u());
    }

    public static void c(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("entry", Integer.valueOf(i2));
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_MEMBER_CARD_CLICK_EVENT.value(), hashMap);
        k();
    }

    public static void a(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("entry", Integer.valueOf(i2));
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_MEMBER_CARD_CLICK_EVENT.value(), hashMap);
        o();
    }

    public static void a(Product product) {
        Map<String, Object> u = u();
        u.put("isSale", Integer.valueOf((product.getPromotion() == null && product.getSubscriptionPromotion() == null) ? 0 : 1));
        u.put(HwPayConstant.KEY_PRODUCTNAME, product.getProductName());
        u.put("productId", product.getProductId());
        u.put(ParsedFieldTag.PRICE, Long.valueOf(product.getMicroPrice() / 1000));
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_PRODUCT_CLICK_EVENT.value(), u);
        k();
    }

    public static void b(int i2, int i3) {
        Map<String, Object> u = u();
        u.put(CommonUtil.PAGE_TYPE, Integer.valueOf(i3));
        u.put("event", Integer.valueOf(i2));
        c(BaseApplication.e(), AnalyticsValue.VIP_EQUITY_CLICK_EVENT.value(), u);
        if (i3 == 1) {
            k();
        } else {
            o();
        }
    }

    public static void c(String str, String str2, int i2) {
        Map<String, Object> u = u();
        if (!TextUtils.isEmpty(str)) {
            u.put("name", str);
        } else {
            u.put("name", "vipTab");
        }
        u.put("from", str2);
        u.put("payResourceType", f);
        u.put("slidingScreens", Integer.valueOf(i2));
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_OPEN_BTN_CLICK_EVENT.value(), u);
        k();
    }

    public static void b(String str, String str2) {
        Map<String, Object> u = u();
        u.put("name", str);
        u.put("payResourceType", str2);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            u.putAll(marketRouterApi.getLastMarketSource());
        }
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_OPEN_BY_BUSINESS_BTN_CLICK_EVENT.value(), u);
    }

    public static void n() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_COUPON_SHOW_EVENT.value(), u());
    }

    public static void d(String str, String str2) {
        Map<String, Object> u = u();
        u.put("couponId", str);
        u.put("couponName", str2);
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_COUPON_CLICK_EVENT.value(), u);
        o();
    }

    public static void p() {
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_RENEW_BTN_SHOW_EVENT.value(), u());
    }

    public static void e(String str, String str2) {
        Map<String, Object> u = u();
        if (!TextUtils.isEmpty(str)) {
            u.put("name", str);
        } else {
            u.put("name", "vipTab");
        }
        u.put("from", str2);
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_RENEW_BTN_CLICK_EVENT.value(), u);
        o();
    }

    public static void f(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("name", str);
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_PRIVILEGE_SHOW_EVENT.value(), hashMap);
    }

    public static void h(String str) {
        Map<String, Object> u = u();
        u.put("name", str);
        c(BaseApplication.e(), AnalyticsValue.VIP_CENTER_PRIVILEGE_CLICK_EVENT.value(), u);
        o();
    }

    public static void e(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("type", str);
        hashMap.put("from", "6");
        hashMap.put("dataType", new String[]{"weight"});
        ixx.d().d(context, AnalyticsValue.HEALTH_HEALTH_WEIGHT_INPUT_2030017.value(), hashMap, 0);
    }

    private static Map<String, Object> u() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        return hashMap;
    }

    private static void c(final Context context, final String str, final Map<String, Object> map) {
        if (str == null || map == null) {
            return;
        }
        if (!TextUtils.isEmpty(h)) {
            map.put(DistrictSearchQuery.KEYWORDS_PROVINCE, h);
        }
        if (!TextUtils.isEmpty(e)) {
            map.put(DistrictSearchQuery.KEYWORDS_CITY, e);
        }
        LogUtil.a("VipBiUtil", "eventId: ", str, " eventMap: ", map);
        ThreadPoolManager.d().execute(new Runnable() { // from class: dqj.1
            @Override // java.lang.Runnable
            public void run() {
                ixx.d().d(context, str, map, 0);
            }
        });
    }

    public static Map<String, String> b() {
        HashMap hashMap = new HashMap(16);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, g);
        hashMap.put("resourceId", m);
        hashMap.put("resourceName", o);
        hashMap.put("pullOrder", j);
        return hashMap;
    }

    public static void r() {
        Map<String, Object> u = u();
        if (CollectionUtils.a(b) || CollectionUtils.a(f)) {
            return;
        }
        u.put("name", b);
        u.put("payResourceType", f);
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            u.putAll(marketRouterApi.getLastMarketSource());
        }
        c(BaseApplication.e(), AnalyticsValue.VIP_INTRO_OPEN_BY_BUSINESS_SHOW.value(), u);
    }
}
