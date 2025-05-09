package com.huawei.watchface;

import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.VipPackageBean;
import com.huawei.watchface.mvp.model.datatype.VipPackageResp;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

@el(a = {@ek(a = "resultinfo"), @ek(a = "resultcode"), @ek(a = "productInfoList")})
/* loaded from: classes9.dex */
public class bq extends BaseHttpRequest<VipPackageBean> {

    /* renamed from: a, reason: collision with root package name */
    private String f10932a;
    private String b;
    private String c;

    public bq(String str) {
        this.f10932a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.c = str;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i("GetVipProductInfoThread", "getResponse url");
        return a(WatchFaceHttpUtil.B() + d(), "", c());
    }

    private LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        linkedHashMap.put("x-hc", "CN");
        linkedHashMap.put(ProfileRequestConstants.X_APPID_KEY, "10003");
        linkedHashMap.put("x-devicemodel", !TextUtils.isEmpty(this.c) ? this.c : HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        linkedHashMap.put("deviceid", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdAsTheme(true));
        linkedHashMap.put("deviceType", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceType());
        linkedHashMap.put("x-brandChannel", String.valueOf(cv.e()));
        linkedHashMap.put("x-accountBrand", String.valueOf(cv.c()));
        linkedHashMap.put("x-appBrand", String.valueOf(cv.c()));
        linkedHashMap.put(WatchFaceConstant.X_UID, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getUserId());
        linkedHashMap.put("Content-Type", "application/json");
        if (!TextUtils.isEmpty(this.b)) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, this.b);
        } else if (WatchFaceHttpUtil.a() != null) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        linkedHashMap.put("terminalType", "1");
        linkedHashMap.put(WatchFaceConstant.CIPHER_VERSION, "2");
        n.a(Environment.getApplicationContext()).a(linkedHashMap, b());
        return linkedHashMap;
    }

    private String d() {
        LinkedHashMap<String, ?> linkedHashMap = new LinkedHashMap<>();
        if (!TextUtils.isEmpty(this.f10932a)) {
            linkedHashMap.put("productCode", this.f10932a);
        }
        linkedHashMap.put("productType", "2");
        linkedHashMap.put("versionCode", CommonUtils.getVersionCode() + "");
        String a2 = a(linkedHashMap);
        HwLog.i("GetVipProductInfoThread", "getRequestParams -- mProductId:" + this.f10932a);
        return a2;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public VipPackageBean c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("GetVipProductInfoThread", "dealReceive json is empty");
            return null;
        }
        try {
            VipPackageResp vipPackageResp = (VipPackageResp) dx.a().a(str, VipPackageResp.class);
            if (vipPackageResp == null) {
                return null;
            }
            HwLog.i("GetVipProductInfoThread", "receive");
            if (vipPackageResp.getProductInfoList() == null || vipPackageResp.getProductInfoList().size() <= 0) {
                return null;
            }
            return vipPackageResp.getProductInfoList().get(0);
        } catch (JsonSyntaxException e) {
            HwLog.e("GetVipProductInfoThread", "dealReceive " + HwLog.printException((Exception) e));
            return null;
        }
    }
}
