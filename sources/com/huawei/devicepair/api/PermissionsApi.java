package com.huawei.devicepair.api;

import android.content.Context;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface PermissionsApi {
    void permissionGrantRegisterContactObserver(String str);

    Map<String, Boolean> requestPermissionsForPair(String str);

    void requestPermissionsGrantStatus(List<String> list, String str, Context context, IBaseResponseCallback iBaseResponseCallback);
}
