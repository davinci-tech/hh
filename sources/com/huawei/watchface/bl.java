package com.huawei.watchface;

import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.JsonSyntaxException;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.GetmarkeHitopIdBean;
import com.huawei.watchface.mvp.model.helper.GsonHelper;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public class bl extends BaseHttpRequest<GetmarkeHitopIdBean> {

    /* renamed from: a, reason: collision with root package name */
    private String f10928a;

    public bl(String str) {
        this.f10928a = str;
    }

    public String c() {
        return a(WatchFaceHttpUtil.z(), e(), d());
    }

    private LinkedHashMap<String, String> d() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(false, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
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
        linkedHashMap.put(WatchFaceConstant.X_UID, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getUserId());
        if (WatchFaceHttpUtil.a() != null) {
            linkedHashMap.put(WatchFaceConstant.X_SIGN, WatchFaceHttpUtil.a().getSign());
        }
        linkedHashMap.put("Content-Type", "application/json");
        linkedHashMap.put("terminalType", "1");
        linkedHashMap.put(WatchFaceConstant.CIPHER_VERSION, "2");
        linkedHashMap.put("childrenVisible", n.a(Environment.getApplicationContext()).a() ? "1" : "0");
        linkedHashMap.put(ProfileRequestConstants.X_LANGUAGE_KEY, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        n.a(Environment.getApplicationContext()).a(linkedHashMap, b());
        return linkedHashMap;
    }

    private String e() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("hitopid", this.f10928a);
        linkedHashMap.put("contentType", SmartMsgConstant.SHOW_METHOD_SMART_CARD);
        linkedHashMap.put("isFilter", "1");
        linkedHashMap.put(TypedValues.Custom.S_DIMENSION, "0");
        linkedHashMap.put("contentPrivType", "1");
        linkedHashMap.put("themeVersion", HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersion(true, false));
        return GsonHelper.toJson(linkedHashMap);
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public GetmarkeHitopIdBean c(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.i("GetMarketHitTopIdThread", "dealReceive json is empty");
            return null;
        }
        try {
            return (GetmarkeHitopIdBean) dx.a().a(str, GetmarkeHitopIdBean.class);
        } catch (JsonSyntaxException e) {
            HwLog.e("GetMarketHitTopIdThread", "dealReceive " + HwLog.printException((Exception) e));
            return null;
        }
    }

    public GetmarkeHitopIdBean.ListBean e(String str) {
        GetmarkeHitopIdBean.ListBean listBean;
        GetmarkeHitopIdBean c = c(str);
        if (c == null || (listBean = (GetmarkeHitopIdBean.ListBean) ArrayUtils.a(c.getList(), 0)) == null) {
            return null;
        }
        return listBean;
    }
}
