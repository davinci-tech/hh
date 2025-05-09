package com.huawei.watchface;

import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.api.HwWatchFaceBtManager;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.datatype.WatchFaceSignBean;
import com.huawei.watchface.mvp.model.helper.systemparam.SystemParamInfo;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class bp extends BaseHttpRequest<SystemParamInfo> {

    /* renamed from: a, reason: collision with root package name */
    private String f10931a = null;
    private int b = 0;

    public void b(String str) {
        f(str);
    }

    private void f(String str) {
        this.f10931a = str;
    }

    public void a(int i) {
        this.b = i;
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i("GetSystemParam", "getResponse url");
        return a(WatchFaceHttpUtil.g() + d(), str, c());
    }

    private LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, HwWatchFaceBtManager.getInstance(Environment.getApplicationContext()).getWatchFaceMaxVersonBySurport());
        return linkedHashMap;
    }

    private String d() {
        WatchFaceSignBean c;
        StringBuffer stringBuffer = new StringBuffer("sign=");
        HwLog.i("GetSystemParam", "getRequestParams deviceType:" + this.b);
        if (this.b == 0) {
            c = WatchFaceHttpUtil.b();
        } else {
            c = WatchFaceHttpUtil.c();
        }
        if (c != null) {
            stringBuffer.append(c.getSign());
        } else {
            HwLog.e("GetSystemParam", "getRequestParams, sign is null!");
        }
        stringBuffer.append("&themeVersion=10.0");
        if (this.b == 0) {
            stringBuffer.append("&buildNumber=").append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        } else {
            stringBuffer.append("&buildNumber=").append(MobileInfoHelper.getDeviceName());
        }
        stringBuffer.append("&versionCode=" + CommonUtils.getVersionCode());
        stringBuffer.append("&paramCode=").append(this.f10931a);
        return stringBuffer.toString();
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public SystemParamInfo c(String str) {
        if (str == null) {
            HwLog.i("GetSystemParam", "handleJsonData json is null");
            return null;
        }
        try {
            if (new JSONObject(str).getInt("resultcode") != 0) {
                HwLog.i("GetSystemParam", "handleJsonData response unknown error");
                return null;
            }
            return (SystemParamInfo) dx.a().a(str, SystemParamInfo.class);
        } catch (Exception e) {
            HwLog.e("GetSystemParam", "SystemParam hitop exception " + HwLog.printException(e));
            return null;
        }
    }
}
