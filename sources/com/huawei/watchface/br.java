package com.huawei.watchface;

import com.google.gson.JsonSyntaxException;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.api.HwWatchFaceManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.ScreenInfo;
import com.huawei.watchface.mvp.model.datatype.WatchFaceListBean;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.ArrayUtils;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.IntegerUtils;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class br extends BaseHttpRequest<WatchFaceListBean> {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10933a = "br";
    private boolean b;

    public br(boolean z) {
        this.b = z;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i(f10933a, "getResponse version: " + str + "-isBihHotTest:" + this.b);
        return a(WatchFaceHttpUtil.e(), e(str), c());
    }

    private String e(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        WatchFaceSignBean b = WatchFaceHttpUtil.b();
        if (b != null) {
            linkedHashMap.put(HwPayConstant.KEY_SIGN, b.getSign());
        } else {
            HwLog.e(f10933a, "getWatchFaceParams sign is null!");
            linkedHashMap.put(HwPayConstant.KEY_SIGN, "");
        }
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, str);
        linkedHashMap.put("filetype", "hwt");
        linkedHashMap.put("type", "1");
        linkedHashMap.put("versionCode", a());
        linkedHashMap.put("begin", "1");
        if (CommonUtils.d()) {
            linkedHashMap.put("length", "24");
        } else {
            linkedHashMap.put("length", "12");
        }
        if (this.b) {
            linkedHashMap.put("listCode", "bihottest");
            linkedHashMap.put("sourceFlag", "1");
        } else {
            linkedHashMap.put("listCode", "hottest");
            linkedHashMap.put("sourceFlag", "0");
        }
        linkedHashMap.put("resourceType", 1);
        linkedHashMap.put("isVipVersion", Integer.valueOf(CommonUtils.A()));
        linkedHashMap.put("contentPrivType", "1,2,3,4");
        linkedHashMap.put("subType", "0");
        if (HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea()) {
            if (!CommonUtils.i()) {
                linkedHashMap.put("chargeFlag", 0);
            }
        } else {
            linkedHashMap.put("chargeFlag", -1);
        }
        if (n.a(Environment.getApplicationContext()).a() && !ab.a().f()) {
            linkedHashMap.put("chargeFlag", 0);
            linkedHashMap.put("listCode", "hottest");
            linkedHashMap.put("sourceFlag", "0");
        }
        linkedHashMap.put("phoneId", HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceIdAsTheme(true));
        return WatchFaceHttpUtil.a((LinkedHashMap<String, ?>) linkedHashMap);
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a() {
        return CommonUtils.getVersionCode() + "";
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public WatchFaceListBean c(String str) {
        return f(str);
    }

    private WatchFaceListBean f(String str) {
        try {
            WatchFaceListBean watchFaceListBean = (WatchFaceListBean) dx.a().a(str, WatchFaceListBean.class);
            if (watchFaceListBean == null) {
                HwLog.e(f10933a, "isDealRightReceive watch face unknown error!");
                return null;
            }
            int a2 = IntegerUtils.a(watchFaceListBean.getResultCode(), 10);
            String str2 = f10933a;
            HwLog.i(str2, "isDealRightReceive errCode : " + a2);
            if (!a(watchFaceListBean)) {
                HwLog.w(str2, "isDealRightReceive response not suitable");
                return null;
            }
            if (watchFaceListBean.getWatchFaceBeanList() == null) {
                HwLog.w(str2, "isDealRightReceive failed.");
                return null;
            }
            HwLog.i(str2, "isDealRightReceive after deal list size:" + watchFaceListBean.getWatchFaceBeanList().size());
            return watchFaceListBean;
        } catch (JsonSyntaxException e) {
            HwLog.e(f10933a, "isDealRightReceive JsonSyntaxException: " + HwLog.printException((Exception) e));
            return null;
        }
    }

    private boolean a(WatchFaceListBean watchFaceListBean) {
        List<WatchFaceListBean.WatchFaceBean> watchFaceBeanList = watchFaceListBean.getWatchFaceBeanList();
        ArrayList arrayList = new ArrayList(3);
        int i = CommonUtils.d() ? 6 : 3;
        if (watchFaceBeanList != null) {
            HwLog.i(f10933a, "isSuitableWatchFaceList before deal list size:" + watchFaceBeanList.size());
            Iterator<WatchFaceListBean.WatchFaceBean> it = watchFaceBeanList.iterator();
            while (it.hasNext() && arrayList.size() < i) {
                WatchFaceListBean.WatchFaceBean next = it.next();
                String version = next.getVersion();
                if (version != null) {
                    String[] split = version.split("\\.");
                    if (split.length >= 3 && IntegerUtils.a(split[2], 10) < 9000) {
                        arrayList.add(next);
                    }
                }
            }
            watchFaceListBean.setWatchFaceBeanList(arrayList);
        }
        return arrayList.size() > 0;
    }

    private LinkedHashMap<String, String> c() {
        ScreenInfo screenInfo;
        String str = f10933a;
        HwLog.i(str, "generateCommonHeaders() enter");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        List<ScreenInfo> compatibleInfo = HwWatchFaceManager.getInstance(Environment.getApplicationContext()).getCompatibleInfo();
        if (!ArrayUtils.isEmpty(compatibleInfo) && (screenInfo = (ScreenInfo) ArrayUtils.a(compatibleInfo, 0)) != null) {
            try {
                String jSONObject = new JSONObject().put(screenInfo.getWidth() + "*" + screenInfo.getHeight(), screenInfo.getSupportVersion()).toString();
                linkedHashMap.put("x-adaptScreenInfo", jSONObject);
                StringBuilder sb = new StringBuilder("generateCommonHeaders：");
                sb.append(jSONObject);
                HwLog.i(str, sb.toString());
            } catch (JSONException e) {
                HwLog.e(f10933a, "generateCommonHeaders error " + HwLog.printException((Exception) e));
            }
        }
        linkedHashMap.put("x-screen", HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceScreen(false, HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceConnectState() == 0));
        linkedHashMap.put("x-brandChannel", String.valueOf(cv.e()));
        linkedHashMap.put("x-accountBrand", String.valueOf(cv.c()));
        linkedHashMap.put("x-appBrand", String.valueOf(cv.c()));
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, MobileInfoHelper.generateUUID());
        n.a(Environment.getApplicationContext()).a(linkedHashMap, b());
        if (this.b) {
            linkedHashMap.put("personalSwitch", "1");
        } else {
            linkedHashMap.put("personalSwitch", "0");
        }
        return linkedHashMap;
    }
}
