package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice.WatchFaceIdReportInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.watchface.utils.ArrayUtils;
import defpackage.bkz;
import defpackage.bmk;
import defpackage.bms;
import defpackage.bmt;
import defpackage.cuk;
import defpackage.cvp;
import defpackage.cvr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class CustomConfigWatchFaceService implements DataReceiveCallback {
    private static final int COLOR_INDEX_0x6 = 6;
    private static final int CUSTOM_COLOR_VALUE_0x7 = 7;
    public static final String CUSTOM_CONFIG_APP_PACKAGE_NAME = "hw.app.watchface.custom";
    public static final String CUSTOM_CONFIG_DEVICE_PACKAGE_NAME = "hw.watch.watchface.custom";
    private static final int DIAL_EDIT_MODE_BACKGROUND_0x1 = 1;
    private static final int DIAL_EDIT_MODE_DUAL_0x2 = 2;
    private static final int DIAL_EDIT_MODE_HAND_0x7 = 7;
    private static final int DIAL_EDIT_MODE_SCALE_0x3 = 3;
    private static final int DIAL_EDIT_MODE_STYLE_0x0 = 0;
    private static final int DIAL_EDIT_MODE_SYMBOL_0x4 = 4;
    private static final int DIAL_EDIT_MODE_TIME_0x6 = 6;
    private static final int DIAL_EDIT_MODE_TYPEFACE_0x5 = 5;
    private static final int DIAL_NOT_FOUND = -1;
    private static final long EVENT_QUERY_CUSTOM_WATCHFACE = 800100008;
    private static final long EVENT_REPORT_CUSTOM_WATCHFACE = 800100009;
    private static final int MARKET_ID_0x1 = 1;
    private static final int STYLE_KEY_0x4 = 4;
    private static final int STYLE_LIST_0x2 = 2;
    private static final int STYLE_MODE_0x5 = 5;
    private static final int STYLE_STRUCT_0x3 = 3;
    private static final int SUCCEED_RESPONSE = 100000;
    private static final String TAG = "CustomConfigWatchFaceService";
    private static final int WIDGET_INDEX_0xA = 10;
    private static final int WIDGET_LIST_0x8 = 8;
    private static final int WIDGET_OPTION_INDEX_0xB = 11;
    private static final int WIDGET_STRUCT_0x9 = 9;
    private IBaseResponseCallback applyCustomConfigCallback;
    private final Map<String, Consumer<CustomConfig>> customConfigListenerMap;
    private String watchFaceToApply;

    public static CustomConfigWatchFaceService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CustomConfigWatchFaceService() {
        this.customConfigListenerMap = new ConcurrentHashMap();
        WatchFaceDeviceService.getInstance().registerIdReportCb(TAG, createWatchFaceReportCallback());
    }

    public void registerDeviceSampleListener() {
        cuk.b().registerDeviceSampleListener(CUSTOM_CONFIG_APP_PACKAGE_NAME, this);
    }

    public void queryCustomConfig(DeviceInfo deviceInfo, String str) {
        LogUtil.a(TAG, "queryCustomConfig enter");
        cvp buildSampleEvent = buildSampleEvent(EVENT_QUERY_CUSTOM_WATCHFACE, new bms().d(1, str).d());
        LogUtil.a(TAG, "queryCustomConfig sample event: ", buildSampleEvent);
        sendSampleEventToDevice(deviceInfo, buildSampleEvent);
    }

    public void setCustomConfig(DeviceInfo deviceInfo, CustomConfig customConfig, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(TAG, "setCustomConfig enter");
        cvp buildSampleEvent = buildSampleEvent(EVENT_REPORT_CUSTOM_WATCHFACE, buildCustomConfigEventDataBytes(customConfig));
        LogUtil.a(TAG, "setCustomConfig sample event: ", buildSampleEvent);
        this.watchFaceToApply = customConfig.getMarketId();
        this.applyCustomConfigCallback = iBaseResponseCallback;
        sendSampleEventToDevice(deviceInfo, buildSampleEvent);
    }

    public void notifyCustomConfigListener(CustomConfig customConfig) {
        LogUtil.a(TAG, "notifyCustomConfigListener enter...");
        if (customConfig == null) {
            LogUtil.h(TAG, "notifyCustomConfigListener customConfig is null");
            return;
        }
        Iterator<Map.Entry<String, Consumer<CustomConfig>>> it = this.customConfigListenerMap.entrySet().iterator();
        while (it.hasNext()) {
            try {
                it.next().getValue().accept(customConfig);
            } catch (Exception e) {
                LogUtil.b(TAG, "notifyCustomConfigListener error:", ExceptionUtils.d(e));
            }
        }
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (i != 100000) {
            LogUtil.b(TAG, "channel onDataReceived error: ", Integer.valueOf(i));
            return;
        }
        if (deviceInfo == null || cvrVar == null) {
            return;
        }
        String srcPkgName = cvrVar.getSrcPkgName();
        String wearPkgName = cvrVar.getWearPkgName();
        if (!isCheckPkgName(srcPkgName, wearPkgName)) {
            LogUtil.h(TAG, "channel onDataReceived pkg error srcPkgNam: ", srcPkgName, ", wearPkgName:", wearPkgName);
            return;
        }
        if (!(cvrVar instanceof cvp)) {
            LogUtil.h(TAG, "channel message not SampleEvent");
            return;
        }
        cvp cvpVar = (cvp) cvrVar;
        if (!Objects.equals(Long.valueOf(EVENT_REPORT_CUSTOM_WATCHFACE), Long.valueOf(cvpVar.e()))) {
            LogUtil.h(TAG, "channel message not report custom watchface");
            return;
        }
        LogUtil.a(TAG, "channel onDataReceived :", cvpVar);
        CustomConfig loadCustomConfigFromSampleEvent = loadCustomConfigFromSampleEvent(cvpVar);
        if (loadCustomConfigFromSampleEvent == null) {
            LogUtil.b(TAG, "channel load custom config fail");
        } else {
            LogUtil.a(TAG, "channel custom config:", loadCustomConfigFromSampleEvent);
            notifyCustomConfigListener(loadCustomConfigFromSampleEvent);
        }
    }

    public void registerCustomConfigListener(String str, Consumer<CustomConfig> consumer) {
        this.customConfigListenerMap.put(str, consumer);
    }

    public void unregisterCustomConfigListener(String str) {
        this.customConfigListenerMap.remove(str);
    }

    private cvp buildSampleEvent(long j, byte[] bArr) {
        cvp cvpVar = new cvp();
        cvpVar.a(j);
        cvpVar.b(0);
        cvpVar.setSrcPkgName(CUSTOM_CONFIG_APP_PACKAGE_NAME);
        cvpVar.setWearPkgName(CUSTOM_CONFIG_DEVICE_PACKAGE_NAME);
        cvpVar.e(bArr);
        return cvpVar;
    }

    private byte[] buildCustomConfigEventDataBytes(CustomConfig customConfig) {
        LogUtil.a(TAG, "buildCustomConfigEventDataBytes enter");
        bms bmsVar = new bms();
        bmsVar.d(1, customConfig.getMarketId());
        buildEventDataStyleList(customConfig.getCustomStyle(), bmsVar);
        if (customConfig.getColorSelectedIndex() != null) {
            bmsVar.a(6, customConfig.getColorSelectedIndex().intValue());
        }
        String customColorValue = customConfig.getCustomColorValue();
        if (!TextUtils.isEmpty(customColorValue)) {
            bmsVar.d(7, customColorValue);
        }
        buildEventDataWidgetList(customConfig, bmsVar);
        return bmsVar.d();
    }

    private void buildEventDataWidgetList(CustomConfig customConfig, bms bmsVar) {
        List<Integer> widgetSelectedOptions = customConfig.getWidgetSelectedOptions();
        if (ArrayUtils.isEmpty(widgetSelectedOptions)) {
            return;
        }
        bmsVar.b(8);
        for (int i = 0; i < widgetSelectedOptions.size(); i++) {
            bmsVar.c(9);
            bmsVar.d(10, i);
            bmsVar.d(11, widgetSelectedOptions.get(i).intValue());
            bmsVar.c(bmsVar.c());
        }
        bmsVar.b(bmsVar.b());
    }

    private void buildEventDataStyleList(CustomConfig.CustomStyle customStyle, bms bmsVar) {
        if (customStyle == null) {
            return;
        }
        ArrayList<int[]> arrayList = new ArrayList();
        if (customStyle.getStyleSelectedIndex() != null) {
            arrayList.add(new int[]{0, customStyle.getStyleSelectedIndex().intValue()});
        }
        if (customStyle.getBkgSelectedIndex() != null) {
            arrayList.add(new int[]{1, customStyle.getBkgSelectedIndex().intValue()});
        }
        if (customStyle.getScaleSelectedIndex() != null) {
            arrayList.add(new int[]{2, customStyle.getScaleSelectedIndex().intValue()});
        }
        if (customStyle.getScaleSelectedIndex() != null) {
            arrayList.add(new int[]{3, customStyle.getScaleSelectedIndex().intValue()});
        }
        if (customStyle.getSymbolSelectedIndex() != null) {
            arrayList.add(new int[]{4, customStyle.getSymbolSelectedIndex().intValue()});
        }
        if (customStyle.getTypefaceSelectedIndex() != null) {
            arrayList.add(new int[]{5, customStyle.getTypefaceSelectedIndex().intValue()});
        }
        if (customStyle.getTimeSelectedIndex() != null) {
            arrayList.add(new int[]{6, customStyle.getTimeSelectedIndex().intValue()});
        }
        if (customStyle.getHandSelectedIndex() != null) {
            arrayList.add(new int[]{7, customStyle.getHandSelectedIndex().intValue()});
        }
        if (bkz.e(arrayList)) {
            return;
        }
        bmsVar.b(2);
        for (int[] iArr : arrayList) {
            bmsVar.c(3);
            bmsVar.d(4, iArr[0]);
            bmsVar.d(5, iArr[1]);
            bmsVar.c(bmsVar.c());
        }
        bmsVar.b(bmsVar.b());
    }

    private void sendSampleEventToDevice(DeviceInfo deviceInfo, cvp cvpVar) {
        LogUtil.a(TAG, "sendSampleEventToDevice sampleEvent=", cvpVar);
        if (deviceInfo != null) {
            cuk.b().sendSampleEventCommand(deviceInfo, cvpVar, TAG);
        } else {
            LogUtil.h(TAG, "sendSampleEventToDevice deviceInfo null");
        }
    }

    private boolean isCheckPkgName(String str, String str2) {
        return CUSTOM_CONFIG_DEVICE_PACKAGE_NAME.equals(str) && CUSTOM_CONFIG_APP_PACKAGE_NAME.equals(str2);
    }

    private CustomConfig.CustomStyle loadCustomStyle(List<bmt.b> list) {
        CustomConfig.CustomStyle customStyle = new CustomConfig.CustomStyle();
        if (bkz.e(list)) {
            return null;
        }
        for (bmt.b bVar : list) {
            int a2 = bVar.a((byte) 4, -1);
            int a3 = bVar.a((byte) 5, -1);
            if (a2 == -1 || a3 == -1) {
                LogUtil.b(TAG, "style not found");
            } else {
                switch (a2) {
                    case 0:
                        customStyle.setStyleSelectedIndex(Integer.valueOf(a3));
                        break;
                    case 1:
                        customStyle.setBkgSelectedIndex(Integer.valueOf(a3));
                        break;
                    case 2:
                    case 3:
                        customStyle.setScaleSelectedIndex(Integer.valueOf(a3));
                        break;
                    case 4:
                        customStyle.setSymbolSelectedIndex(Integer.valueOf(a3));
                        break;
                    case 5:
                        customStyle.setTypefaceSelectedIndex(Integer.valueOf(a3));
                        break;
                    case 6:
                        customStyle.setTimeSelectedIndex(Integer.valueOf(a3));
                        break;
                    case 7:
                        customStyle.setHandSelectedIndex(Integer.valueOf(a3));
                        break;
                }
            }
        }
        return customStyle;
    }

    private List<Integer> loadWidgetList(List<bmt.b> list) {
        ArrayList arrayList = new ArrayList();
        if (bkz.e(list)) {
            return arrayList;
        }
        for (bmt.b bVar : list) {
            int a2 = bVar.a((byte) 10, -1);
            int a3 = bVar.a(BaseType.Float, -1);
            if (a2 < 0 || a2 >= list.size()) {
                LogUtil.b(TAG, "loadWidgetList err: widget len is ", Integer.valueOf(arrayList.size()), " but index: ", Integer.valueOf(a2));
            } else {
                arrayList.add(Integer.valueOf(a3));
            }
        }
        return arrayList;
    }

    private CustomConfig loadCustomConfigFromSampleEvent(cvp cvpVar) {
        CustomConfig customConfig = new CustomConfig();
        try {
            bmt.b b = new bmt().b(cvpVar.c(), 0);
            String b2 = b.b((byte) 1, (String) null);
            CustomConfig.CustomStyle loadCustomStyle = loadCustomStyle(b.a((byte) 2));
            int a2 = b.a((byte) 6, -1);
            String b3 = b.b((byte) 7, (String) null);
            List<Integer> loadWidgetList = loadWidgetList(b.a((byte) 8));
            customConfig.setMarketId(b2);
            customConfig.setCustomStyle(loadCustomStyle);
            if (a2 != -1) {
                customConfig.setColorSelectedIndex(Integer.valueOf(a2));
            }
            customConfig.setCustomColorValue(b3);
            if (!bkz.e(loadWidgetList)) {
                customConfig.setContainerCount(Integer.valueOf(loadWidgetList.size()));
                customConfig.setWidgetSelectedOptions(loadWidgetList);
            }
            return customConfig;
        } catch (bmk unused) {
            LogUtil.b(TAG, "channel load event device tlv error");
            return null;
        }
    }

    static class SingletonHolder {
        private static final CustomConfigWatchFaceService INSTANCE = new CustomConfigWatchFaceService();

        private SingletonHolder() {
        }
    }

    private ResponseCallback<WatchFaceIdReportInfo> createWatchFaceReportCallback() {
        return new ResponseCallback() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.deviceside.CustomConfigWatchFaceService$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                CustomConfigWatchFaceService.this.m662x528c0f1c(i, (WatchFaceIdReportInfo) obj);
            }
        };
    }

    /* renamed from: lambda$createWatchFaceReportCallback$0$com-huawei-hwdevice-mainprocess-mgr-hwmywatchfacemgr-deviceside-CustomConfigWatchFaceService, reason: not valid java name */
    /* synthetic */ void m662x528c0f1c(int i, WatchFaceIdReportInfo watchFaceIdReportInfo) {
        LogUtil.a(TAG, "createWatchFaceReportCallback onResponse status: ", Integer.valueOf(i), ", reportInfo.getReportStatus(): ", Integer.valueOf(watchFaceIdReportInfo.getReportStatus()), ", reportInfo.getWatchFaceId(): ", watchFaceIdReportInfo.getWatchFaceId());
        if (!Objects.equals(this.watchFaceToApply, watchFaceIdReportInfo.getWatchFaceId())) {
            LogUtil.a(TAG, "createWatchFaceReportCallback onResponse watchFaceToApply equals reportInfo.getWatchFaceId is false");
        } else {
            if (watchFaceIdReportInfo.getReportStatus() != 1) {
                return;
            }
            this.watchFaceToApply = null;
            this.applyCustomConfigCallback.d(0, watchFaceIdReportInfo.getWatchFaceId());
        }
    }
}
