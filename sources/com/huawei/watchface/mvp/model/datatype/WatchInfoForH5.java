package com.huawei.watchface.mvp.model.datatype;

import com.huawei.nfc.PluginPayAdapter;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class WatchInfoForH5 {
    public static Map<String, String> createGalileoMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_MODEL, "Galileo-L10E");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, "HUAWEI WATCH3");
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_SOFT_VERSION, "11.0.10.016");
        hashMap.put("device_Identify", "00:00:00:00:00:00");
        hashMap.put("watch_screen", "466*466");
        hashMap.put("watch_version", "1.1");
        hashMap.put("watch_max_version", "1.1");
        return hashMap;
    }
}
