package com.huawei.wearengine.utils;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.wearengine.device.Device;
import defpackage.toe;
import defpackage.toh;
import defpackage.tos;
import defpackage.tot;
import defpackage.tow;
import defpackage.trt;
import defpackage.tsc;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class DeviceProcessor {
    private static final String DEVICE_PROCESSOR_FILE = "WearEngine_DeviceProcessor";
    private static final String DEVICE_PROCESSOR_THREAD_NAME = "WearEngine_DeviceProcessor_Thread";
    private static final Object MAP_LOCK = new Object();
    private static final int MAX_DEVICE_NUM = 24;
    private static final int MAX_WEAR_ENGINE_DEVICE_ID_NUM = 10;
    private static final int PACKAGE_NAME_KEEP_LENGTH = 30;
    private static final String RESERVEDNESS_FIELD = "mReservedness";
    private static final Set<String> SUPER_CALLER_SET;
    private static final String TAG = "DeviceProcessor";
    private static final int UUID_ARRAY_LEN = 2;
    private static final String UUID_DELIMITER = "##";
    private static final String WEAR_ENGINE_SERVICE_ID_MAP = "WearEngineDeviceIdMap";
    private static volatile boolean isInit;
    private static Handler mSaveDataHandler;
    private static SharedPreferences mSharedPreferences;
    private static Map<String, List<String>> sUUIDWearEngineDeviceIdListMap;
    private static Map<String, String> sWearEngineDeviceIdMap;

    static {
        HashSet hashSet = new HashSet(toe.c());
        SUPER_CALLER_SET = hashSet;
        sWearEngineDeviceIdMap = new ConcurrentHashMap();
        sUUIDWearEngineDeviceIdListMap = new LinkedHashMap();
        isInit = false;
        hashSet.addAll(toh.c());
    }

    private DeviceProcessor() {
    }

    public static Set<String> getSuperCallerSet() {
        return new HashSet(SUPER_CALLER_SET);
    }

    public static List<Device> processOutputDevice(String str, List<Device> list) {
        tos.a(TAG, "processOutputDevice callingPkgName: " + str);
        if (TextUtils.isEmpty(str)) {
            tos.e(TAG, "processOutputDevice callingPkgName is empty");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (list == null) {
            tos.e(TAG, "processOutputDevice devices is null");
            throw new IllegalStateException(String.valueOf(12));
        }
        handleDeviceSnAndUdidInfo(str, list);
        if ("com.huawei.deveco.assistant".equals(str)) {
            return processOutputDeviceForAssistant(list);
        }
        if (SUPER_CALLER_SET.contains(str)) {
            return list;
        }
        checkLocalCacheInit();
        HashMap hashMap = new HashMap(16);
        for (Device device : list) {
            if (device.getProductType() == 0) {
                saveDeviceIdUuIdMapAndSetDeviceUuid(str, device, hashMap);
            }
        }
        updateWearEngineIdDeviceInfo(hashMap);
        dumpDeviceInfo(list);
        return list;
    }

    private static void handleDeviceSnAndUdidInfo(String str, List<Device> list) {
        boolean d = toh.d(str);
        tos.a(TAG, "handleDeviceSnAndUdidInfo isCanGetDeviceSnCaller: " + d);
        if (d) {
            return;
        }
        for (Device device : list) {
            if (device.getProductType() == 0) {
                deleteDeviceSnAndUdidInfo(device);
            }
        }
    }

    private static void deleteDeviceSnAndUdidInfo(Device device) {
        String b = tow.b(device);
        if (TextUtils.isEmpty(b)) {
            return;
        }
        try {
            tow towVar = new tow(b);
            if (towVar.has("device_extra")) {
                String a2 = towVar.a();
                if (TextUtils.isEmpty(a2)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(a2);
                jSONObject.remove(HealthEngineRequestManager.PARAMS_DEVICE_SN);
                jSONObject.remove("deviceUdid");
                towVar.a(jSONObject.length() == 0 ? "" : jSONObject.toString());
                device.setReservedness(towVar.toString());
            }
        } catch (JSONException unused) {
            tos.e(TAG, "deleteDeviceSnAndUdidInfo has an JSONException");
        }
    }

    private static void saveDeviceIdUuIdMapAndSetDeviceUuid(String str, Device device, Map<String, String> map) {
        String o = tsc.o(getJsonFromDevice(device));
        if (TextUtils.isEmpty(o)) {
            tos.e(TAG, "processOutputDevice wearEngineDeviceId is empty");
            throw new IllegalStateException(String.valueOf(12));
        }
        String uuid = device.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            tos.e(TAG, "processOutputDevice uuId is empty");
            throw new IllegalStateException(String.valueOf(12));
        }
        map.put(o, uuid);
        String d = trt.d(str, "SHA-256");
        if (TextUtils.isEmpty(d)) {
            tos.e(TAG, "processOutputDevice pkgNameSha256 is empty");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (d.length() < 30) {
            tos.e(TAG, "processOutputDevice pkgNameSha256 length is invalid");
            throw new IllegalStateException(String.valueOf(12));
        }
        device.setUuid(o + UUID_DELIMITER + d.substring(0, 30));
    }

    public static Device processInputDevice(String str, Device device) {
        tos.a(TAG, "processInputDevice callingPkgName: " + str);
        if (device == null) {
            tos.e(TAG, "processInputDevice inputDevice is null");
            throw new IllegalStateException(String.valueOf(5));
        }
        if (TextUtils.isEmpty(str)) {
            tos.e(TAG, "processInputDevice callingPkgName is empty");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (device.getProductType() != 0) {
            return device;
        }
        if ("com.huawei.deveco.assistant".equals(str)) {
            return processInputDeviceForAssistant(device);
        }
        if (SUPER_CALLER_SET.contains(str)) {
            return device;
        }
        checkLocalCacheInit();
        device.setUuid(getInputDeviceRealUuId(device));
        return device;
    }

    private static String getInputDeviceRealUuId(Device device) {
        String uuid = device.getUuid();
        tos.a(TAG, "processInputDevice input uuid: " + uuid);
        if (TextUtils.isEmpty(uuid) || !uuid.contains(UUID_DELIMITER)) {
            tos.e(TAG, "processInputDevice input uuid is invalid");
            throw new IllegalStateException(String.valueOf(17));
        }
        String wearEngineDeviceIdFromInputUuid = getWearEngineDeviceIdFromInputUuid(uuid);
        if (TextUtils.isEmpty(wearEngineDeviceIdFromInputUuid)) {
            tos.e(TAG, "processInputDevice wearEngineDeviceId from inputDeviceId is empty");
            throw new IllegalStateException(String.valueOf(17));
        }
        String realUuIdFromCache = getRealUuIdFromCache(wearEngineDeviceIdFromInputUuid);
        if (!TextUtils.isEmpty(realUuIdFromCache)) {
            return realUuIdFromCache;
        }
        tos.e(TAG, "processInputDevice uuId from cache is empty");
        throw new IllegalStateException(String.valueOf(17));
    }

    private static void dumpDeviceInfo(List<Device> list) {
        StringBuilder sb = new StringBuilder();
        for (Device device : list) {
            sb.append("device name: ");
            sb.append(device.getName());
            sb.append(", wearEngineDeviceId: ");
            sb.append(device.getUuid());
            sb.append(" ");
        }
        tos.a(TAG, sb.toString());
    }

    private static String getRealUuIdFromCache(String str) {
        String str2;
        tos.a(TAG, "getRealUuIdFromCache:" + str);
        synchronized (MAP_LOCK) {
            str2 = sWearEngineDeviceIdMap.get(str);
        }
        return str2;
    }

    private static String getWearEngineDeviceIdFromInputUuid(String str) {
        String[] split = str.split(UUID_DELIMITER);
        if (split.length < 2) {
            tos.e(TAG, "getWearEngineDeviceIdFromInputUuid strings length invalid");
            return "";
        }
        return split[0];
    }

    private static String getJsonFromDevice(Object obj) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(RESERVEDNESS_FIELD);
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            if (!(obj2 instanceof String)) {
                return "";
            }
            return (String) obj2;
        } catch (IllegalAccessException unused) {
            tos.e(TAG, "getJsonFromDevice catch IllegalAccessException");
            return "";
        } catch (NoSuchFieldException unused2) {
            tos.e(TAG, "getJsonFromDevice catch NoSuchFieldException");
            return "";
        }
    }

    private static void checkLocalCacheInit() {
        synchronized (MAP_LOCK) {
            if (!isInit) {
                initLocalDeviceWearEngineInfo();
            }
        }
    }

    private static void initLocalDeviceWearEngineInfo() {
        synchronized (MAP_LOCK) {
            if (isInit) {
                tos.d(TAG, "initLocalDeviceWearEngineInfo already init return");
                return;
            }
            HandlerThread handlerThread = new HandlerThread(DEVICE_PROCESSOR_THREAD_NAME);
            handlerThread.start();
            if (handlerThread.getLooper() == null) {
                tos.e(TAG, "initLocalDeviceWearEngineInfo looper is null return");
                return;
            }
            mSaveDataHandler = new Handler(handlerThread.getLooper());
            mSharedPreferences = tot.a().getSharedPreferences(DEVICE_PROCESSOR_FILE, 0);
            sUUIDWearEngineDeviceIdListMap.clear();
            sWearEngineDeviceIdMap.clear();
            String string = mSharedPreferences.getString(WEAR_ENGINE_SERVICE_ID_MAP, "");
            if (TextUtils.isEmpty(string)) {
                isInit = true;
                tos.d(TAG, "initLocalDeviceWearEngineInfo local cache is null");
                return;
            }
            try {
                Map<? extends String, ? extends List<String>> map = (Map) new Gson().fromJson(string, new TypeToken<Map<String, List<String>>>() { // from class: com.huawei.wearengine.utils.DeviceProcessor.5
                }.getType());
                if (map != null) {
                    sUUIDWearEngineDeviceIdListMap.putAll(map);
                }
                tos.a(TAG, "local save size:" + sUUIDWearEngineDeviceIdListMap.size());
            } catch (JsonSyntaxException unused) {
                tos.e(TAG, "parse local device id info exception");
            }
            for (Map.Entry<String, List<String>> entry : sUUIDWearEngineDeviceIdListMap.entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    sWearEngineDeviceIdMap.put(it.next(), entry.getKey());
                }
            }
            isInit = true;
        }
    }

    private static void updateLocalCache(final Map<String, List<String>> map) {
        mSaveDataHandler.post(new Runnable() { // from class: com.huawei.wearengine.utils.DeviceProcessor.1
            @Override // java.lang.Runnable
            public void run() {
                DeviceProcessor.updateLocalCacheLock(map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004c A[Catch: all -> 0x0056, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x000b, B:9:0x0012, B:10:0x0046, B:12:0x004c, B:13:0x0054, B:17:0x0039), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void updateLocalCacheLock(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r4) {
        /*
            java.lang.Object r0 = com.huawei.wearengine.utils.DeviceProcessor.MAP_LOCK
            monitor-enter(r0)
            android.content.SharedPreferences r1 = com.huawei.wearengine.utils.DeviceProcessor.mSharedPreferences     // Catch: java.lang.Throwable -> L56
            android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch: java.lang.Throwable -> L56
            if (r4 == 0) goto L39
            int r2 = r4.size()     // Catch: java.lang.Throwable -> L56
            if (r2 != 0) goto L12
            goto L39
        L12:
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch: java.lang.Throwable -> L56
            r2.<init>()     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "WearEngineDeviceIdMap"
            java.lang.String r2 = r2.toJson(r4)     // Catch: java.lang.Throwable -> L56
            r1.putString(r3, r2)     // Catch: java.lang.Throwable -> L56
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "updateLocalCache size:"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L56
            java.lang.String r3 = "DeviceProcessor"
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L56
            r2.append(r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L56
            defpackage.tos.a(r3, r4)     // Catch: java.lang.Throwable -> L56
            goto L46
        L39:
            java.lang.String r4 = "WearEngineDeviceIdMap"
            r1.remove(r4)     // Catch: java.lang.Throwable -> L56
            java.lang.String r4 = "DeviceProcessor"
            java.lang.String r2 = "updateLocalCache map is null or size 0"
            defpackage.tos.a(r4, r2)     // Catch: java.lang.Throwable -> L56
        L46:
            boolean r4 = r1.commit()     // Catch: java.lang.Throwable -> L56
            if (r4 != 0) goto L54
            java.lang.String r4 = "DeviceProcessor"
            java.lang.String r1 = "updateLocalCache failed"
            defpackage.tos.d(r4, r1)     // Catch: java.lang.Throwable -> L56
        L54:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
            return
        L56:
            r4 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.wearengine.utils.DeviceProcessor.updateLocalCacheLock(java.util.Map):void");
    }

    private static void removeMemoryCache(Map<String, List<String>> map) {
        int size = sUUIDWearEngineDeviceIdListMap.size() - 24;
        tos.a(TAG, "removeMemoryCache need remove size:" + size);
        if (size <= 0) {
            tos.d(TAG, "removeMemoryCache needRemoveNum less than 0");
            return;
        }
        Iterator<Map.Entry<String, List<String>>> it = sUUIDWearEngineDeviceIdListMap.entrySet().iterator();
        while (it.hasNext() && size > 0) {
            Map.Entry<String, List<String>> next = it.next();
            if (!map.containsKey(next.getKey())) {
                it.remove();
                size--;
                List<String> value = next.getValue();
                if (value != null && value.size() != 0) {
                    Iterator<String> it2 = value.iterator();
                    while (it2.hasNext()) {
                        sWearEngineDeviceIdMap.remove(it2.next());
                    }
                }
            }
        }
    }

    private static void updateWearEngineIdDeviceInfo(Map<String, String> map) {
        synchronized (MAP_LOCK) {
            updateWearEngineIdDeviceInfoLocked(map);
        }
    }

    private static void updateWearEngineIdDeviceInfoLocked(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean z = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!sWearEngineDeviceIdMap.containsKey(key) || !value.equals(sWearEngineDeviceIdMap.get(key))) {
                List<String> list = sUUIDWearEngineDeviceIdListMap.get(value);
                if (list == null) {
                    tos.a(TAG, "updateWearEngineIdDeviceInfo WearEngineDeviceId list is null");
                    list = new ArrayList<>();
                    sUUIDWearEngineDeviceIdListMap.put(value, list);
                }
                if (list.size() >= 10) {
                    tos.a(TAG, "updateWearEngineIdDeviceInfo remove oldest WearEngineDeviceId");
                    sWearEngineDeviceIdMap.remove(list.remove(0));
                }
                list.add(key);
                sWearEngineDeviceIdMap.put(key, value);
                z = true;
            }
            linkedHashMap.put(value, sUUIDWearEngineDeviceIdListMap.get(value));
        }
        if (sUUIDWearEngineDeviceIdListMap.size() > 24) {
            removeMemoryCache(linkedHashMap);
        }
        if (z) {
            updateLocalCache(linkedHashMap);
        }
    }

    private static List<Device> processOutputDeviceForAssistant(List<Device> list) {
        checkLocalCacheInit();
        HashMap hashMap = new HashMap(16);
        for (Device device : list) {
            if (device.getProductType() == 0) {
                String o = tsc.o(getJsonFromDevice(device));
                if (TextUtils.isEmpty(o)) {
                    tos.e(TAG, "processOutputDeviceForAssistant fullUdId is empty");
                    throw new IllegalStateException(String.valueOf(12));
                }
                String uuid = device.getUuid();
                if (TextUtils.isEmpty(uuid)) {
                    tos.e(TAG, "processOutputDeviceForAssistant uuId is empty");
                    throw new IllegalStateException(String.valueOf(12));
                }
                hashMap.put(o, uuid);
                device.setUuid(o);
            }
        }
        updateWearEngineIdDeviceInfo(hashMap);
        return list;
    }

    private static Device processInputDeviceForAssistant(Device device) {
        checkLocalCacheInit();
        String uuid = device.getUuid();
        if (TextUtils.isEmpty(uuid)) {
            tos.e(TAG, "processInputDeviceForAssistant input uuid is invalid");
            throw new IllegalStateException(String.valueOf(17));
        }
        String realUuIdFromCache = getRealUuIdFromCache(uuid);
        if (TextUtils.isEmpty(realUuIdFromCache)) {
            tos.e(TAG, "processInputDevice uuId from cache is empty");
            throw new IllegalStateException(String.valueOf(17));
        }
        device.setUuid(realUuIdFromCache);
        return device;
    }
}
