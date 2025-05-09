package com.huawei.watchface;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.CouponsByproductBean;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public class bn extends BaseHttpRequest<CouponsByproductBean> {

    /* renamed from: a, reason: collision with root package name */
    private String f10929a;

    public bn(String str) {
        this.f10929a = str;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i("GetVipProductInfoThread", "getResponse url");
        return a(WatchFaceHttpUtil.C(), d(), c());
    }

    private LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> reqHeaders = super.getReqHeaders();
        reqHeaders.put("x-brandChannel", String.valueOf(cv.e()));
        reqHeaders.put("x-accountBrand", String.valueOf(cv.c()));
        reqHeaders.put("x-appBrand", String.valueOf(cv.c()));
        reqHeaders.remove(WatchFaceConstant.X_UID);
        reqHeaders.remove("usertoken");
        reqHeaders.remove("authtype");
        return reqHeaders;
    }

    private String d() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        if (!TextUtils.isEmpty(this.f10929a)) {
            linkedHashMap.put("hitopId", this.f10929a);
        }
        if (WatchFaceHttpUtil.a() != null) {
            linkedHashMap.put(HwPayConstant.KEY_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        linkedHashMap.put("resourceType", "6");
        linkedHashMap.put("userToken", am.a(n.a(Environment.getApplicationContext()).d()));
        linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType());
        linkedHashMap.put("deviceId", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceId());
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put("isVipVersion", Integer.valueOf(CommonUtils.A()));
        String a2 = a(linkedHashMap);
        HwLog.i("GetVipProductInfoThread", "getRequestParams -- mProductId:" + this.f10929a);
        return a2;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public CouponsByproductBean c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("GetVipProductInfoThread", "dealReceive json is empty");
            return null;
        }
        try {
            return (CouponsByproductBean) dx.a().a(str, CouponsByproductBean.class);
        } catch (JsonSyntaxException e) {
            HwLog.e("GetVipProductInfoThread", "dealReceive JsonSyntaxException:" + HwLog.printException((Exception) e));
            return null;
        } catch (Exception e2) {
            HwLog.e("GetVipProductInfoThread", "dealReceive Exception:" + HwLog.printException(e2));
            return null;
        }
    }
}
