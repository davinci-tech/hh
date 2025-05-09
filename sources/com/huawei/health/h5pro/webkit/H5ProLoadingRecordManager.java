package com.huawei.health.h5pro.webkit;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.LogUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class H5ProLoadingRecordManager {
    public static final H5ProLoadingRecordManager e = new H5ProLoadingRecordManager();
    public final Map<String, Long> c = Collections.synchronizedMap(new HashMap(1));

    /* renamed from: a, reason: collision with root package name */
    public final Map<String, Long> f2465a = Collections.synchronizedMap(new HashMap(1));

    public void removeLoadingRecord(String str) {
        synchronized (this) {
            if (!TextUtils.isEmpty(str)) {
                this.c.remove(str);
                this.f2465a.remove(str);
            }
        }
    }

    public void recordStartTime(String str) {
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.w("H5PRO_H5ProLoadRecordManager", "recordStartTime: packageName is empty");
            } else {
                if (!this.c.containsKey(str)) {
                    this.c.put(str, Long.valueOf(System.currentTimeMillis()));
                    return;
                }
                LogUtil.w("H5PRO_H5ProLoadRecordManager", "recordStartTime: containsKey -> " + str);
            }
        }
    }

    public void recordDownloadTime(String str, long j) {
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.w("H5PRO_H5ProLoadRecordManager", "recordDownloadTime: packageName is empty");
            } else if (this.c.containsKey(str)) {
                this.f2465a.put(str, Long.valueOf(j));
            } else {
                this.f2465a.remove(str);
                LogUtil.w("H5PRO_H5ProLoadRecordManager", "recordDownloadTime: loading start time not recorded");
            }
        }
    }

    public JSONObject getLoadingRecord(String str) {
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                LogUtil.w("H5PRO_H5ProLoadRecordManager", "getLoadingRecord: packageName is empty");
                return null;
            }
            Long l = this.c.get(str);
            if (l != null) {
                long j = 0;
                if (l.longValue() > 0) {
                    try {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("packageName", str);
                            jSONObject.put("totalTime", System.currentTimeMillis() - l.longValue());
                            Long l2 = this.f2465a.get(str);
                            jSONObject.put("hasDownload", (l2 == null || l2.longValue() <= 0) ? 1 : 0);
                            if (l2 != null && l2.longValue() > 0) {
                                j = l2.longValue();
                            }
                            jSONObject.put("downloadTime", j);
                            return jSONObject;
                        } catch (JSONException e2) {
                            LogUtil.w("H5PRO_H5ProLoadRecordManager", "getLoadingRecord: exception -> " + e2.getMessage());
                            return null;
                        }
                    } finally {
                        this.c.remove(str);
                        this.f2465a.remove(str);
                    }
                }
            }
            LogUtil.w("H5PRO_H5ProLoadRecordManager", "getLoadingRecord: loading start time not recorded");
            return null;
        }
    }
}
