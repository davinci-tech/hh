package com.huawei.health.adapterhealthmgr.api;

import android.content.Context;
import com.huawei.pluginbase.PluginBaseAdapter;
import java.util.Map;

/* loaded from: classes3.dex */
public interface AdapterHealthMgrApi {
    PluginBaseAdapter getAchieveAdapterImpl();

    Map<String, String> getAppData(String[] strArr);

    PluginBaseAdapter getOperationAdapterImpl(Context context);
}
