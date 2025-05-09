package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.VipPackageBean;
import com.huawei.watchface.mvp.model.info.VipPayInfoCoupons;
import com.huawei.watchface.mvp.model.webview.JsInteractAddition;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.analytice.data.BaseEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class ej extends BaseEvent<ej> {
    private static Map<String, ej> h = new ConcurrentHashMap();
    private String i;
    private String k;
    private StringBuilder j = new StringBuilder();

    /* renamed from: a, reason: collision with root package name */
    public LinkedHashMap<String, String> f11009a = new LinkedHashMap<>();

    public static ej a(String str, VipPayInfoCoupons vipPayInfoCoupons) {
        ej a2 = a(str, (VipPackageBean) vipPayInfoCoupons);
        if (a2 != null && vipPayInfoCoupons != null) {
            a2.a("couponId", vipPayInfoCoupons.getCouponId());
        }
        return a2;
    }

    public static ej a(String str, VipPackageBean vipPackageBean) {
        if (str == null) {
            return new ej();
        }
        ej a2 = a(str);
        if (a2 == null || vipPackageBean == null) {
            return new ej();
        }
        a2.b("1").a("productCode", vipPackageBean.getProductCode()).a(HwPayConstant.KEY_PRODUCTNAME, vipPackageBean.getProductName()).a(ParsedFieldTag.PRICE, vipPackageBean.getPrice()).a("discountPrice", vipPackageBean.getDiscountPrice());
        return a2;
    }

    public static ej a(String str) {
        ej ejVar;
        HwLog.i("AnalyticsPayEvent", "getPayEvents hitopid " + str);
        if (h == null) {
            h = new ConcurrentHashMap();
        }
        try {
            ejVar = h.get(str);
        } catch (ClassCastException | NullPointerException e) {
            HwLog.i("AnalyticsPayEvent", "getPayEvents error " + HwLog.printException((Exception) e));
            ejVar = null;
        }
        if (ejVar == null) {
            ejVar = new ej().a(System.currentTimeMillis()).l(str);
            if (str != null) {
                h.put(str, ejVar);
            }
        }
        return ejVar;
    }

    private static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            h.remove(str);
        } catch (Exception e) {
            HwLog.e("AnalyticsPayEvent", "removePayEvent error:" + HwLog.printException(e));
        }
    }

    public static Map<String, ej> d() {
        if (h == null) {
            h = new ConcurrentHashMap();
        }
        return h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.k;
    }

    public ej b(String str) {
        this.k = str;
        return this;
    }

    public ej c(String str) {
        this.i = str;
        return this;
    }

    public String g() {
        if (this.j == null) {
            this.j = new StringBuilder();
        }
        return this.j.toString();
    }

    public ej a(String str, String str2) {
        if (this.j == null) {
            this.j = new StringBuilder();
        }
        if (!TextUtils.isEmpty(this.j)) {
            this.j.append('|');
        }
        StringBuilder sb = this.j;
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        return this;
    }

    @Override // com.huawei.watchface.em
    public LinkedHashMap<String, String> b() {
        return this.f11009a;
    }

    public static void a(String str, int i, String str2) {
        ej a2 = a(str);
        a2.b(System.currentTimeMillis()).k(String.valueOf(i));
        if (a2.f11009a == null) {
            a2.f11009a = new LinkedHashMap<>();
        }
        a2.f11009a.put("startts", String.valueOf(a2.m()));
        a2.f11009a.put("endts", String.valueOf(a2.n()));
        a2.f11009a.put("totalTime", String.valueOf(a2.n() - a2.m()));
        HwWatchFaceApi hwWatchFaceApi = HwWatchFaceApi.getInstance(Environment.getApplicationContext());
        if (!hwWatchFaceApi.isOversea() && TextUtils.equals(hwWatchFaceApi.getCommonCountryCode(), "CN")) {
            a2.f11009a.put("orderId", a2.e());
        }
        a2.f11009a.put(JsInteractAddition.HI_TOP_ID, a2.q());
        a2.f11009a.put(BaseEvent.KEY_CONTENTINFO, a2.g());
        a2.f11009a.put("errorCode", String.valueOf(i));
        LinkedHashMap<String, String> linkedHashMap = a2.f11009a;
        if (TextUtils.isEmpty(str2)) {
            str2 = bd.f10920a.get(i);
        }
        linkedHashMap.put(BaseEvent.KEY_DESCINFO, str2);
        a2.f11009a.put("sourceType", a2.f());
        a2.a_();
        d(str);
    }

    @Override // com.huawei.watchface.em
    public String a() {
        return "WATCHFACE_BUY";
    }
}
