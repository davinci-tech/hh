package com.huawei.devicepair.api;

import com.huawei.hwbasemgr.IBaseResponseCallback;
import java.util.Map;

/* loaded from: classes3.dex */
public interface UpdateDeviceApi {
    Map<String, Boolean> getUpdatePageContent(String str);

    boolean isShowOtaPrivacy(String str);

    void setUpdateStatus(Map<String, Boolean> map, String str, IBaseResponseCallback iBaseResponseCallback);
}
