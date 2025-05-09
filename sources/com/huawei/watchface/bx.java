package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceOrderBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "tradeId"), @ek(a = "requstParams")})
/* loaded from: classes7.dex */
public class bx extends BaseHttpRequest<WatchFaceOrderBean> {

    /* renamed from: a, reason: collision with root package name */
    private String f10939a;
    private String b;
    private String c;
    private String d;
    private String e;

    public bx(String str, String str2, String str3, String str4, String str5) {
        this.f10939a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
    }

    public String c() {
        return a(WatchFaceHttpUtil.p(), e(), getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return this.e;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        return super.getReqHeaders();
    }

    public String d() {
        String str;
        try {
            str = getReqHeaders().get(WatchFaceConstant.X_CLIENTTRACEID);
        } catch (NullPointerException e) {
            HwLog.e("HitopReqOrderBatchCreateThread", "getTraceid error " + HwLog.printException((Exception) e));
            str = null;
        }
        return str == null ? "" : str;
    }

    private String e() {
        try {
            LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("isoCode", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
            linkedHashMap.put("productId", this.f10939a);
            linkedHashMap.put("versionCode", this.e);
            linkedHashMap.put("payAmount", this.b);
            linkedHashMap.put(JsbMapKeyNames.H5_USER_ID, WatchFaceHttpUtil.a().getUserId());
            linkedHashMap.put("isGift", false);
            linkedHashMap.put("isVipVersion", Integer.valueOf(CommonUtils.A()));
            if (!TextUtils.isEmpty(this.c)) {
                linkedHashMap.put("couponId", this.c);
            }
            if (!TextUtils.isEmpty(this.d)) {
                try {
                    linkedHashMap.put(HwPayConstant.KEY_RESERVEDINFOR, URLEncoder.encode(this.d, StandardCharsets.UTF_8.name()));
                } catch (UnsupportedEncodingException unused) {
                    HwLog.e("HitopReqOrderBatchCreateThread", "getWatchFacePayResultParams, UnsupportedEncodingException");
                }
            }
            return a(linkedHashMap);
        } catch (Exception e) {
            HwLog.i("HitopReqOrderBatchCreateThread", "Exception : " + HwLog.printException(e));
            return "";
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WatchFaceOrderBean c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("HitopReqOrderBatchCreateThread", "dealReceive json is empty");
            return new WatchFaceOrderBean();
        }
        try {
            WatchFaceOrderBean watchFaceOrderBean = (WatchFaceOrderBean) GsonHelper.fromJson(str, WatchFaceOrderBean.class);
            if ("0".equals(watchFaceOrderBean.getResultCode())) {
                watchFaceOrderBean.mCouponId = this.c;
                watchFaceOrderBean.mReservedInfor = this.d;
            }
            return watchFaceOrderBean;
        } catch (Exception e) {
            HwLog.i("HitopReqOrderBatchCreateThread", "handleJsonData exception: " + HwLog.printException(e));
            return new WatchFaceOrderBean();
        }
    }
}
