package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryRequestModel;
import com.huawei.watchface.mvp.model.datatype.orderhistory.OrderHistoryResponse;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;
import java.util.List;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "body"), @ek(a = "recordCount")})
/* loaded from: classes7.dex */
public class cd extends BaseHttpRequest<OrderHistoryResponse> {
    public String a(List<OrderHistoryRequestModel> list) {
        return a(WatchFaceHttpUtil.v(), b(list), c());
    }

    private String b(List<OrderHistoryRequestModel> list) {
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("byidsReq", list);
            return GsonHelper.toJson(linkedHashMap);
        } catch (Exception e) {
            HwLog.e("OrderHistoryRequest", "Exception message: " + HwLog.printException(e));
            return "";
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public OrderHistoryResponse c(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            HwLog.i("OrderHistoryRequest", "dealReceive json is empty");
            return null;
        }
        try {
            return (OrderHistoryResponse) GsonHelper.fromJson(str, OrderHistoryResponse.class);
        } catch (Exception e) {
            HwLog.e("OrderHistoryRequest", "Exception message: " + HwLog.printException(e));
            return null;
        }
    }

    private LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        linkedHashMap.put("x-hc", "CN");
        linkedHashMap.put(ProfileRequestConstants.X_APPID_KEY, "10003");
        linkedHashMap.put("x-devicemodel", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        linkedHashMap.put("deviceid", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdAsTheme(true));
        linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType());
        linkedHashMap.put("x-brandChannel", String.valueOf(cv.e()));
        linkedHashMap.put("x-accountBrand", String.valueOf(cv.c()));
        linkedHashMap.put("x-appBrand", String.valueOf(cv.c()));
        if (WatchFaceHttpUtil.a() != null) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        linkedHashMap.put("terminalType", "1");
        n.a(Environment.getApplicationContext()).a(linkedHashMap, b());
        return linkedHashMap;
    }
}
