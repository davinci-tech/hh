package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr;

import android.text.TextUtils;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.api.CustomConfigWatchFaceApi;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.commonservice.WatchFaceWearService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.CustomConfigWatchFaceService;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.jpt;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class CustomConfigWatchFaceManager implements CustomConfigWatchFaceApi {
    private static final String REGISTER_NAME_PREFIX = "CustomConfigWatchFaceManager#";
    private static final String TAG = "CustomConfigWatchFaceManager";
    private final Map<String, Map<String, CustomConfig>> customConfigCache;
    private final List<String> registerNameList;

    private CustomConfigWatchFaceManager() {
        this.registerNameList = new ArrayList();
        this.customConfigCache = new ConcurrentHashMap();
    }

    public void setDeviceWatchFaceConfig(String str, String str2, CustomConfig customConfig) {
        if (str == null || str2 == null) {
            return;
        }
        if (!this.customConfigCache.containsKey(str)) {
            this.customConfigCache.put(str, new ConcurrentHashMap());
        }
        Map<String, CustomConfig> map = this.customConfigCache.get(str);
        if (map == null) {
            return;
        }
        map.put(str2, customConfig);
    }

    public CustomConfig getDeviceWatchFaceConfig(String str, String str2) {
        Map<String, CustomConfig> map;
        if (str == null || str2 == null || !this.customConfigCache.containsKey(str) || (map = this.customConfigCache.get(str)) == null) {
            return null;
        }
        return map.get(str2);
    }

    public static CustomConfigWatchFaceManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override // com.huawei.hwdevice.api.CustomConfigWatchFaceApi
    public void registerCustomConfigListener(String str, Consumer<CustomConfig> consumer) {
        String str2 = REGISTER_NAME_PREFIX + str;
        CustomConfigWatchFaceService.getInstance().registerCustomConfigListener(str2, consumer);
        this.registerNameList.add(str2);
    }

    @Override // com.huawei.hwdevice.api.CustomConfigWatchFaceApi
    public void applyCustomConfig(String str, CustomConfig customConfig, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "applyCustomConfig enter");
        DeviceInfo curValidDeviceInfo = getCurValidDeviceInfo(str);
        if (curValidDeviceInfo == null) {
            LogUtil.h(TAG, "applyCustomConfig deviceInfo is null");
        } else {
            WatchFaceWearService.getInstance().refreshMyWatchFaceApplyTime();
            CustomConfigWatchFaceService.getInstance().setCustomConfig(curValidDeviceInfo, customConfig, iBaseResponseCallback);
        }
    }

    @Override // com.huawei.hwdevice.api.CustomConfigWatchFaceApi
    public void queryCustomConfig(String str, String str2) {
        LogUtil.a(TAG, "queryCustomConfig enter");
        DeviceInfo curValidDeviceInfo = getCurValidDeviceInfo(str);
        if (curValidDeviceInfo == null) {
            return;
        }
        CustomConfigWatchFaceService.getInstance().queryCustomConfig(curValidDeviceInfo, str2);
    }

    @Override // com.huawei.hwdevice.api.CustomConfigWatchFaceApi
    public void destroyWatchFace() {
        LogUtil.a(TAG, "destroyWatchFace: clear count ", Integer.valueOf(this.registerNameList.size()));
        Iterator<String> it = this.registerNameList.iterator();
        while (it.hasNext()) {
            CustomConfigWatchFaceService.getInstance().unregisterCustomConfigListener(it.next());
        }
        this.registerNameList.clear();
    }

    /* loaded from: classes5.dex */
    static class SingletonHolder {
        static final CustomConfigWatchFaceManager INSTANCE = new CustomConfigWatchFaceManager();

        private SingletonHolder() {
        }
    }

    private DeviceInfo getCurValidDeviceInfo(String str) {
        LogUtil.a(TAG, "getCurValidDeviceInfo: ", CommonUtil.l(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "getCurValidDeviceInfo: deviceId is empty");
            return null;
        }
        DeviceInfo d = jpt.d(TAG);
        if (d == null) {
            LogUtil.h(TAG, "getCurValidDeviceInfo: deviceInfo is null");
            return null;
        }
        if (Objects.equals(d.getDeviceIdentify(), str)) {
            return d;
        }
        LogUtil.h(TAG, "getCurValidDeviceInfo: device has changed");
        return null;
    }
}
