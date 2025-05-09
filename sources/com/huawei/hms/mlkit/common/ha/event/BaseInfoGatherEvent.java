package com.huawei.hms.mlkit.common.ha.event;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.huawei.hms.mlkit.common.ha.HianalyticsLogProvider;
import com.huawei.hms.mlkit.common.ha.a;
import com.huawei.hms.mlkit.common.ha.d;
import com.huawei.hms.network.embedded.r3;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public abstract class BaseInfoGatherEvent {
    public static final int TYPE_MAINT = 2;
    public static final int TYPE_OPERATE = 1;
    public static final int TYPE_PRELOAD = 4;

    private LinkedHashMap<String, String> getCommonData(Context context) {
        a a2 = d.a(context, getAppInfo());
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("package", a2.f5045a);
        linkedHashMap.put("appid", a2.b);
        linkedHashMap.put("appName", a2.d);
        linkedHashMap.put("version", a2.c);
        linkedHashMap.put("service", "MLKit");
        linkedHashMap.put("transId", HianalyticsLogProvider.getInstance().getTransId());
        linkedHashMap.put("operator", d.b(context.getApplicationContext()));
        linkedHashMap.put(r3.y, d.a(context.getApplicationContext()));
        linkedHashMap.put("apkVersion", getModelApkVersion());
        linkedHashMap.put("apiName", getApiName());
        linkedHashMap.put("countryCode", a2.f);
        linkedHashMap.put("deviceType", Build.MODEL);
        linkedHashMap.put(FaqConstants.FAQ_EMUIVERSION, d.a());
        linkedHashMap.put("moduleName", getModuleName());
        linkedHashMap.put("moduleVersion", getModuleVersion());
        linkedHashMap.put("deviceCategory", "");
        return linkedHashMap;
    }

    public abstract String getApiName();

    public abstract Bundle getAppInfo();

    public abstract LinkedHashMap<String, String> getCustomizedData();

    public final LinkedHashMap<String, String> getEventData(Context context) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.putAll(getCommonData(context));
        linkedHashMap.putAll(getCustomizedData());
        return linkedHashMap;
    }

    public abstract String getEventId();

    public abstract String getExtension(String str);

    public abstract String getModelApkVersion();

    public abstract String getModuleName();

    public abstract String getModuleVersion();
}
