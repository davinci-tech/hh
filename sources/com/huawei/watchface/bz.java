package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class bz extends BaseHttpRequest<Boolean> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10941a = "bz";
    private String b;
    private String c;

    public bz(String str, String str2) {
        this.c = str;
        this.b = str2;
    }

    public String c() {
        return a(WatchFaceHttpUtil.t(), d(), getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        return super.getReqHeaders();
    }

    private String d() {
        String str;
        try {
            LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
            WatchFaceSignBean b = WatchFaceHttpUtil.b();
            if (b != null) {
                str = b.getSign();
            } else {
                HwLog.e(f10941a, "getDesignerParameters() watchFaceSignBean is null.");
                str = null;
            }
            if (TextUtils.isEmpty(str)) {
                HwLog.e(f10941a, "getDesignerParameters() sign is null.");
                return "";
            }
            linkedHashMap.put(HwPayConstant.KEY_SIGN, str);
            linkedHashMap.put("userToken", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getToken());
            linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType());
            linkedHashMap.put("deviceId", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceId());
            linkedHashMap.put("couponId", this.b);
            linkedHashMap.put("orderId", this.c);
            return a(linkedHashMap);
        } catch (Exception e) {
            HwLog.i(f10941a, "Exception : " + HwLog.printException(e));
            return "";
        }
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Boolean c(String str) {
        if (str == null) {
            return false;
        }
        try {
            int optInt = new JSONObject(str).optInt("resultcode");
            HwLog.i(f10941a, "resultCode : " + optInt);
            if (optInt != 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            HwLog.e(f10941a, "HitopRequestCouponUnlock hitop exception " + HwLog.printException(e));
            return false;
        }
    }
}
