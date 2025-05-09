package com.huawei.profile.profile;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.huawei.profile.datamanager.AbstractDatabase;
import com.huawei.profile.datamanager.DatabaseFactory;
import com.huawei.profile.kv.DBEntity;
import com.huawei.profile.utils.AnonymousUtil;
import com.huawei.profile.utils.JsonUtils;
import com.huawei.profile.utils.logger.DsLog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ProfileBaseUtils {
    public static final String DEVICES_KEY = "devices";
    public static final String DEV_ID_AND_CLOUD_INDEX = "com.huawei.profile/localDevidIntoCloudDevid";
    private static final int END_NUM = 5;
    private static final int FRONT_NUM = 5;
    public static final String NEED_CLOUD_DEVICE = "needCloudDevice";
    public static final String OLD_NEED_CLOUD_DEVICE = "com.huawei.profile/needCloudDevice";
    public static final String RESEND_INDEX_KEY = "com.huawei.profile/reSendIndexKey";
    public static final String RESEND_PUT_DEVICE = "putDevice";
    public static final String RESEND_PUT_SERVICE = "putService";
    public static final String SERVICES_KEY = "services";
    private static final String TAG = "ProfileBaseUtils";
    private static final Object LOCAL_TO_CLOUD_LOCK = new Object();
    private static final Object NEED_CLOUD_DEV_LOCK = new Object();
    private static final Object CLOUD_DEV_LOCK = new Object();

    private ProfileBaseUtils() {
    }

    public static boolean isDeviceToBeUpload(Context context, String str) {
        String str2 = DatabaseFactory.generateDb(context).get("com.huawei.profile", "com.huawei.profile/reSendIndexKey");
        if (str2 == null || str2.isEmpty()) {
            DsLog.dt(TAG, "resend index is empty.", new Object[0]);
            return false;
        }
        JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(str2)).getAsJsonObject();
        if (asJsonObject == null) {
            DsLog.wt(TAG, "jsonObjectIndex is null.", new Object[0]);
            return false;
        }
        JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("devices");
        if (asJsonObject2 == null) {
            DsLog.dt(TAG, "jsonObjectDevice is null.", new Object[0]);
            return false;
        }
        if (asJsonObject2.get(str) == null) {
            DsLog.dt(TAG, "this device doesn't need to be upload.", new Object[0]);
            return false;
        }
        DsLog.dt(TAG, "resend device", new Object[0]);
        return true;
    }

    public static void clearCloudDeviceRecord(Context context, String str) {
        deleteNeedCloudDevice(context, str);
        deleteCloudDevId(context, str);
    }

    public static void clearCloudDeviceRecordSet(Context context, HashSet<String> hashSet) {
        if (context == null || hashSet == null || hashSet.isEmpty()) {
            DsLog.dt(TAG, "context or localDevIdSet is null", new Object[0]);
            return;
        }
        synchronized (CLOUD_DEV_LOCK) {
            deleteNeedCloudDevice(context, hashSet);
            deleteCloudDevIdSet(context, hashSet);
        }
    }

    private static void deleteNeedCloudDevice(Context context, HashSet<String> hashSet) {
        deleteNeedCloudDeviceSet(context, hashSet, "needCloudDevice");
        deleteNeedCloudDeviceSet(context, hashSet, OLD_NEED_CLOUD_DEVICE);
    }

    private static void deleteNeedCloudDeviceSet(Context context, HashSet<String> hashSet, String str) {
        JsonObject queryNeedCloudDeviceByKey = queryNeedCloudDeviceByKey(context, str);
        DsLog.it(TAG, "deleteNeedCloudDeviceSet before del needCloud", new Object[0]);
        if (queryNeedCloudDeviceByKey.entrySet().isEmpty()) {
            DsLog.dt(TAG, "empty json array, there's no record to delete", new Object[0]);
            return;
        }
        Iterator<String> it = hashSet.iterator();
        while (it.hasNext()) {
            queryNeedCloudDeviceByKey.remove(it.next());
        }
        DsLog.it(TAG, "deleteNeedCloudDeviceSet del needCloud", new Object[0]);
        new DBEntity(str, queryNeedCloudDeviceByKey.toString());
    }

    private static void deleteCloudDevIdSet(Context context, HashSet<String> hashSet) {
        JsonObject asJsonObject;
        String str = DatabaseFactory.generateDb(context).get("com.huawei.profile", "com.huawei.profile/localDevidIntoCloudDevid");
        if (TextUtils.isEmpty(str)) {
            DsLog.it(TAG, "localDeviceIdToCloudDeviceId is empty", new Object[0]);
            return;
        }
        try {
            asJsonObject = JsonParser.parseString(JsonUtils.sanitize(str)).getAsJsonObject();
        } catch (IllegalStateException unused) {
            DsLog.et(TAG, "dbValToJsonObject false", new Object[0]);
        }
        if (asJsonObject != null && !asJsonObject.entrySet().isEmpty()) {
            Iterator<String> it = hashSet.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (asJsonObject.has(next)) {
                    asJsonObject.remove(next);
                }
            }
            str = asJsonObject.toString();
            DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("com.huawei.profile/localDevidIntoCloudDevid", str));
        }
    }

    public static void deleteCloudDevId(Context context, String str) {
        synchronized (LOCAL_TO_CLOUD_LOCK) {
            String str2 = DatabaseFactory.generateDb(context).get("com.huawei.profile", "com.huawei.profile/localDevidIntoCloudDevid");
            if (str2 != null && !str2.isEmpty()) {
                JsonObject asJsonObject = JsonParser.parseString(JsonUtils.sanitize(str2)).getAsJsonObject();
                if (asJsonObject.has(str)) {
                    asJsonObject.remove(str);
                }
                DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("com.huawei.profile/localDevidIntoCloudDevid", asJsonObject.toString()));
                return;
            }
            DsLog.it(TAG, "deleteCloudDevId success, reason is dbVal is null", new Object[0]);
        }
    }

    public static void saveCloudDevId(Context context, String str, String str2) {
        JsonObject jsonObject;
        synchronized (LOCAL_TO_CLOUD_LOCK) {
            String str3 = DatabaseFactory.generateDb(context).get("com.huawei.profile", "com.huawei.profile/localDevidIntoCloudDevid");
            if (str3 != null && !str3.isEmpty()) {
                jsonObject = JsonParser.parseString(JsonUtils.sanitize(str3)).getAsJsonObject();
                jsonObject.addProperty(str, str2);
                DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("com.huawei.profile/localDevidIntoCloudDevid", jsonObject.toString()));
            }
            jsonObject = new JsonObject();
            jsonObject.addProperty(str, str2);
            DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("com.huawei.profile/localDevidIntoCloudDevid", jsonObject.toString()));
        }
    }

    public static void saveCloudDevIdList(Context context, List<String> list, List<String> list2) {
        if (context == null || list == null || list2 == null) {
            return;
        }
        synchronized (LOCAL_TO_CLOUD_LOCK) {
            DsLog.it(TAG, "start to save cloud device id list into database", new Object[0]);
            JsonObject dbValToJsonObject = dbValToJsonObject(DatabaseFactory.generateDb(context).get("com.huawei.profile", "com.huawei.profile/localDevidIntoCloudDevid"));
            for (int i = 0; i < list.size(); i++) {
                dbValToJsonObject.addProperty(list.get(i), list2.get(i));
            }
            DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("com.huawei.profile/localDevidIntoCloudDevid", dbValToJsonObject.toString()));
            DsLog.it(TAG, "save cloud device id list into database success", new Object[0]);
        }
    }

    private static JsonObject dbValToJsonObject(String str) {
        String sanitize = JsonUtils.sanitize(str);
        if (TextUtils.isEmpty(sanitize)) {
            return new JsonObject();
        }
        try {
            return JsonParser.parseString(sanitize).getAsJsonObject();
        } catch (IllegalStateException unused) {
            DsLog.et(TAG, "dbValToJsonObject false", new Object[0]);
            return new JsonObject();
        }
    }

    public static void deleteNeedCloudDevice(Context context, String str) {
        synchronized (NEED_CLOUD_DEV_LOCK) {
            deleteNeedCloudDevice(context, str, "needCloudDevice");
            deleteNeedCloudDevice(context, str, OLD_NEED_CLOUD_DEVICE);
        }
    }

    private static void deleteNeedCloudDevice(Context context, String str, String str2) {
        JsonObject queryNeedCloudDeviceByKey = queryNeedCloudDeviceByKey(context, str2);
        if (queryNeedCloudDeviceByKey.entrySet().isEmpty()) {
            DsLog.dt(TAG, "empty json array, there's no record to delete for " + str2, new Object[0]);
        } else {
            DsLog.dt(TAG, "to delete need cloud device is " + anonymousContent(str), new Object[0]);
            queryNeedCloudDeviceByKey.remove(str);
            DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity(str2, queryNeedCloudDeviceByKey.toString()));
        }
    }

    public static void deleteNeedCloudDevices(Context context, List<String> list) {
        synchronized (NEED_CLOUD_DEV_LOCK) {
            deleteNeedCloudDevices(context, list, "needCloudDevice");
            deleteNeedCloudDevices(context, list, OLD_NEED_CLOUD_DEVICE);
        }
    }

    private static void deleteNeedCloudDevices(Context context, List<String> list, String str) {
        if (list == null || list.isEmpty()) {
            DsLog.dt(TAG, "local device id list is empty, not need to delete", new Object[0]);
            return;
        }
        JsonObject queryNeedCloudDeviceByKey = queryNeedCloudDeviceByKey(context, str);
        if (queryNeedCloudDeviceByKey.entrySet().isEmpty()) {
            DsLog.dt(TAG, "empty json array, there's no record to delete for " + str, new Object[0]);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : list) {
            sb.append(anonymousContent(str2));
            sb.append(",");
            queryNeedCloudDeviceByKey.remove(str2);
        }
        DsLog.dt(TAG, "to delete need cloud devices are " + ((Object) sb), new Object[0]);
        DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity(str, queryNeedCloudDeviceByKey.toString()));
    }

    public static JsonObject queryNeedCloudDevice(Context context) {
        JsonObject queryNeedCloudDeviceByKey;
        synchronized (NEED_CLOUD_DEV_LOCK) {
            queryNeedCloudDeviceByKey = queryNeedCloudDeviceByKey(context, "needCloudDevice");
            for (Map.Entry<String, JsonElement> entry : queryNeedCloudDeviceByKey(context, OLD_NEED_CLOUD_DEVICE).entrySet()) {
                queryNeedCloudDeviceByKey.add(entry.getKey(), entry.getValue());
            }
        }
        return queryNeedCloudDeviceByKey;
    }

    public static void saveNeedCloudDevice(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            DsLog.et(TAG, " Failed to save device id that need cloud, local devId is empty", new Object[0]);
            return;
        }
        synchronized (NEED_CLOUD_DEV_LOCK) {
            JsonObject dbValToJsonObject = dbValToJsonObject(DatabaseFactory.generateDb(context).get("com.huawei.profile", "needCloudDevice"));
            dbValToJsonObject.add(str, new JsonObject());
            DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("needCloudDevice", dbValToJsonObject.toString()));
        }
    }

    public static void saveNeedCloudDevices(Context context, List<String> list) {
        if (list == null || list.size() < 1) {
            DsLog.et(TAG, " Failed to save device id list that need cloud local localDevIdList is empty", new Object[0]);
            return;
        }
        synchronized (NEED_CLOUD_DEV_LOCK) {
            JsonObject dbValToJsonObject = dbValToJsonObject(DatabaseFactory.generateDb(context).get("com.huawei.profile", "needCloudDevice"));
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                dbValToJsonObject.add(it.next(), new JsonObject());
            }
            DatabaseFactory.generateDb(context).put("com.huawei.profile", new DBEntity("needCloudDevice", dbValToJsonObject.toString()));
        }
    }

    private static JsonObject queryNeedCloudDeviceByKey(Context context, String str) {
        AbstractDatabase generateDb = DatabaseFactory.generateDb(context);
        String str2 = generateDb.get("com.huawei.profile", str);
        DsLog.dt(TAG, " " + str + ", needCloudDeviceStr = " + AnonymousUtil.anonymousJson(str2), new Object[0]);
        if (TextUtils.isEmpty(str2)) {
            return new JsonObject();
        }
        try {
            JsonObject asJsonObject = JsonParser.parseString(str2).getAsJsonObject();
            return asJsonObject != null ? asJsonObject : new JsonObject();
        } catch (JsonSyntaxException unused) {
            DsLog.et(TAG, "invalid json string", new Object[0]);
            return new JsonObject();
        } catch (IllegalStateException unused2) {
            DsLog.et(TAG, "invalid json string, rewrite", new Object[0]);
            generateDb.put("com.huawei.profile", new DBEntity(str, ""));
            return new JsonObject();
        }
    }

    private static JsonObject getAllResendIndexObject(Context context) {
        String str = DatabaseFactory.generateDb(context).get("com.huawei.profile", "com.huawei.profile/reSendIndexKey");
        if (str == null || str.isEmpty()) {
            DsLog.wt(TAG, "getAllResendIndex failed, reason is dbVal is invalid", new Object[0]);
            return new JsonObject();
        }
        return JsonParser.parseString(JsonUtils.sanitize(str)).getAsJsonObject();
    }

    private static Map<String, String> getResendIndexByKey(JsonObject jsonObject, String str) {
        JsonObject asJsonObject = jsonObject.getAsJsonObject(str);
        return asJsonObject != null ? (Map) new Gson().fromJson(JsonUtils.sanitize(asJsonObject.toString()), Map.class) : new HashMap();
    }

    public static boolean isResendIndexHasPutDevice(Context context, String str) {
        JsonObject allResendIndexObject = getAllResendIndexObject(context);
        if (allResendIndexObject == null) {
            DsLog.it(TAG, "resend index object is null.", new Object[0]);
            return false;
        }
        Map<String, String> resendIndexByKey = getResendIndexByKey(allResendIndexObject, "devices");
        if (resendIndexByKey == null || resendIndexByKey.isEmpty()) {
            DsLog.dt(TAG, "devInfoMap is empty, localDevId is not included.", new Object[0]);
            return false;
        }
        String str2 = resendIndexByKey.get(str);
        return str2 != null && str2.indexOf("putDevice") == 0;
    }

    public static boolean isResendIndexHasPutService(Context context, String str) {
        JsonObject allResendIndexObject = getAllResendIndexObject(context);
        if (allResendIndexObject == null) {
            DsLog.it(TAG, "resend index object is null.", new Object[0]);
            return false;
        }
        Map<String, String> resendIndexByKey = getResendIndexByKey(allResendIndexObject, "services");
        if (resendIndexByKey == null || resendIndexByKey.isEmpty()) {
            DsLog.dt(TAG, "serviceInfoMap is empty, localDevId is not included.", new Object[0]);
            return false;
        }
        String str2 = resendIndexByKey.get(str);
        return str2 != null && str2.indexOf("putService") == 0;
    }

    public static void notFoundDeviceWhenDelete(Context context, String str) {
        if (isResendIndexHasPutDevice(context, str)) {
            DsLog.dt(TAG, "There is a put device index, do nothing", new Object[0]);
        } else {
            deleteCloudDevId(context, str);
        }
    }

    public static String anonymousContent(String str) {
        int length;
        if (str == null || (length = str.length()) <= 10) {
            return str;
        }
        return str.substring(0, 5) + "*******" + str.substring(length - 5);
    }
}
