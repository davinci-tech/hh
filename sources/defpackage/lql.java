package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import com.google.json.JsonSanitizer;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes5.dex */
public class lql {

    /* renamed from: a, reason: collision with root package name */
    private static GsonBuilder f14838a = new GsonBuilder().disableHtmlEscaping().setLongSerializationPolicy(LongSerializationPolicy.STRING);
    private static int b = 1;
    private static GsonBuilder d;

    public static Gson b() {
        return f14838a.create();
    }

    private static Gson a() {
        GsonBuilder gsonBuilder = d;
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder().disableHtmlEscaping().setLongSerializationPolicy(LongSerializationPolicy.STRING).setObjectToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER);
            d = gsonBuilder;
        }
        return gsonBuilder.create();
    }

    public static Map<String, Object> d(Object obj) {
        return a(b(), obj);
    }

    public static Map<String, Object> c(Object obj) {
        return a(a(), obj);
    }

    private static Map<String, Object> a(Gson gson, Object obj) {
        String json = gson.toJson(obj);
        try {
            return (Map) gson.fromJson(TextUtils.isEmpty(json) ? "" : JsonSanitizer.sanitize(json), new TypeToken<HashMap<String, Object>>() { // from class: lql.4
            }.getType());
        } catch (Exception e) {
            ReleaseLogUtil.b("RequestHelper", "Exception is ", ExceptionUtils.d(e));
            return new HashMap();
        }
    }

    public static String b(Object obj) {
        try {
            return b().toJson(obj);
        } catch (Exception e) {
            ReleaseLogUtil.b("RequestHelper", "Exception is ", ExceptionUtils.d(e));
            return "";
        }
    }

    public static String e(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            z = true;
        }
        return sb.toString();
    }

    public static byte[] e(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        if (bArr == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((int) (bArr.length * 0.8d));
        GZIPOutputStream gZIPOutputStream2 = null;
        GZIPOutputStream gZIPOutputStream3 = null;
        try {
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream, bArr.length);
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = gZIPOutputStream2;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            int length = bArr.length;
            gZIPOutputStream.write(bArr, 0, length);
            gZIPOutputStream.flush();
            FileUtils.d(gZIPOutputStream);
            gZIPOutputStream2 = length;
        } catch (IOException e2) {
            e = e2;
            gZIPOutputStream3 = gZIPOutputStream;
            LogUtil.e("RequestHelper", "gzip error!", e.getMessage());
            FileUtils.d(gZIPOutputStream3);
            gZIPOutputStream2 = gZIPOutputStream3;
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            FileUtils.d(byteArrayOutputStream);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            FileUtils.d(gZIPOutputStream);
            byteArrayOutputStream.toByteArray();
            FileUtils.d(byteArrayOutputStream);
            throw th;
        }
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        FileUtils.d(byteArrayOutputStream);
        return byteArray2;
    }

    public static int c() {
        return b;
    }
}
