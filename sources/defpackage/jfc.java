package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.StorageParams;
import health.compact.a.StorageResult;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class jfc {
    public static void d(Context context, HashMap<Long, Long> hashMap, String str, StorageDataCallback storageDataCallback) {
        HashMap hashMap2 = new HashMap();
        if (context == null) {
            LogUtil.h("NotifyTrackUtils", "getNotifyTrackMap context == null");
            storageDataCallback.onProcessed(new StorageResult());
            return;
        }
        String d = knl.d("notifyTrackUtils" + str);
        KeyValDbManager b = KeyValDbManager.b(context);
        String d2 = b.d(d, new StorageParams(1));
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(d2)) {
            try {
                hashMap2 = (HashMap) gson.fromJson(CommonUtil.z(d2), new TypeToken<HashMap<Long, Long>>() { // from class: jfc.4
                }.getType());
            } catch (JsonSyntaxException unused) {
                hashMap2 = new HashMap();
                LogUtil.b("NotifyTrackUtils", "getNotifyTrackMap json exception");
            }
        }
        if (hashMap2 == null) {
            LogUtil.b("NotifyTrackUtils", "map is null");
            hashMap2 = new HashMap();
        }
        LogUtil.a("NotifyTrackUtils", "getTrackList", Integer.valueOf(hashMap2.size()), hashMap2);
        if (hashMap != null) {
            hashMap.putAll(hashMap2);
        }
        if (hashMap2.size() != 0) {
            b.e(d, gson.toJson(new HashMap()), new StorageParams(1), storageDataCallback);
        } else {
            storageDataCallback.onProcessed(new StorageResult());
        }
    }

    public static void c(Context context, HashMap<Long, Long> hashMap, String str, StorageDataCallback storageDataCallback) {
        HashMap hashMap2;
        if (context == null || hashMap == null || hashMap.size() == 0) {
            LogUtil.h("NotifyTrackUtils", "saveTrackList context == null || map == null || map.size() == 0");
            return;
        }
        KeyValDbManager b = KeyValDbManager.b(context);
        String d = knl.d("notifyTrackUtils" + str);
        String d2 = b.d(d, new StorageParams(1));
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(d2)) {
            try {
                hashMap2 = (HashMap) gson.fromJson(CommonUtil.z(d2), new TypeToken<HashMap<Long, Long>>() { // from class: jfc.5
                }.getType());
            } catch (JsonSyntaxException unused) {
                LogUtil.b("NotifyTrackUtils", "saveTrackList json exception");
                hashMap2 = null;
            }
            if (hashMap2 != null) {
                hashMap.putAll(hashMap2);
            } else {
                LogUtil.h("NotifyTrackUtils", "lastNotifyMap == null");
            }
        }
        LogUtil.a("NotifyTrackUtils", "saveTrackList", Integer.valueOf(hashMap.size()), hashMap);
        b.e(d, gson.toJson(hashMap), new StorageParams(1), storageDataCallback);
    }
}
