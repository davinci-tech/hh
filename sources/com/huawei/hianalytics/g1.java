package com.huawei.hianalytics;

import android.text.TextUtils;
import com.huawei.hianalytics.core.log.HiLog;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class g1 {
    public static void a(File file, String str) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception unused) {
            }
        } catch (Throwable th) {
            th = th;
            fileOutputStream = fileOutputStream2;
        }
        try {
            fileOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
        } catch (Exception unused2) {
            fileOutputStream2 = fileOutputStream;
            HiLog.e("HASU", "save file error");
            fileOutputStream = fileOutputStream2;
            a(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            a(fileOutputStream);
            throw th;
        }
        a(fileOutputStream);
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                HiLog.w("HASU", "close error");
            }
        }
    }

    public static JSONObject a(LinkedHashMap<String, String> linkedHashMap) {
        JSONObject jSONObject;
        synchronized (g1.class) {
            jSONObject = new JSONObject();
            try {
                for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
                    if (!TextUtils.isEmpty(entry.getKey())) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception unused) {
                HiLog.e("HASU", "map to json error");
            }
        }
        return jSONObject;
    }
}
