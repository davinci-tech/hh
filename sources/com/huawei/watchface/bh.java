package com.huawei.watchface;

import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.secure.android.common.util.SafeString;
import com.huawei.watchface.mvp.model.thread.BaseHttpRequest;
import com.huawei.watchface.utils.MobileInfoHelper;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.LinkedHashMap;

/* loaded from: classes9.dex */
public abstract class bh<T> extends BaseHttpRequest<T> {
    public LinkedHashMap<String, String> c() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        String generateDeviceIDWithSeparator = MobileInfoHelper.generateDeviceIDWithSeparator();
        if (generateDeviceIDWithSeparator.contains(":") && generateDeviceIDWithSeparator.contains("=")) {
            linkedHashMap.put("x-deviceid", SafeString.substring(generateDeviceIDWithSeparator, 0, generateDeviceIDWithSeparator.indexOf(":")) + ";type=" + SafeString.substring(generateDeviceIDWithSeparator, generateDeviceIDWithSeparator.indexOf("=") + 1));
        }
        linkedHashMap.put(WatchFaceConstant.X_CLIENTTRACEID, fd.b().a());
        linkedHashMap.put(CloudParamKeys.X_CLIENT_VERSION, MobileInfoHelper.getVersion());
        return linkedHashMap;
    }
}
