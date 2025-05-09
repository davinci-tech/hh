package com.huawei.watchface;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.watchface.api.HwWatchFaceApi;
import com.huawei.watchface.environment.Environment;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.utils.LanguageUtils;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.WatchFaceHttpUtil;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class bm extends BaseHttpRequest<at> {
    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public String a(String str) {
        HwLog.i("GetOnlineState", "getResponse url");
        return a(WatchFaceHttpUtil.h() + c(), str, getReqHeaders());
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    public LinkedHashMap<String, String> getReqHeaders() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(ProfileRequestConstants.X_APPID_KEY, "10003");
        return linkedHashMap;
    }

    private static String c() {
        StringBuilder sb = new StringBuilder("firmware=");
        sb.append(Build.VERSION.RELEASE);
        sb.append(Constants.VERSION);
        sb.append(CommonUtils.l());
        sb.append("&buildNumber=");
        sb.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getDeviceModel());
        sb.append("&versionCode=80003&locale=");
        sb.append(LanguageUtils.a(false));
        sb.append("&phoneType=");
        sb.append(MobileInfoHelper.getDeviceName());
        sb.append("&isoCode=");
        sb.append(HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode());
        sb.append("&ver=1.6");
        HwLog.i("GetOnlineState", "getRequestParams: " + sb.toString());
        return sb.toString();
    }

    @Override // com.huawei.watchface.mvp.model.thread.BaseHttpRequest
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public at c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        at f = at.f(str);
        String commonCountryCode = HwWatchFaceApi.getInstance(Environment.getApplicationContext()).getCommonCountryCode();
        if (!TextUtils.isEmpty(commonCountryCode)) {
            if (!HwWatchFaceApi.getInstance(Environment.getApplicationContext()).isOversea() && commonCountryCode.equals("CN")) {
                as.a(Environment.getApplicationContext(), "SupportOnlineInfo", str);
            } else {
                as.a(Environment.getApplicationContext(), "overseasSupportOnlineInfo", str);
            }
        }
        return f;
    }
}
