package com.huawei.hms.network.file.core.util;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.embedded.x2;
import com.huawei.hms.network.file.api.GlobalRequestConfig;
import com.huawei.hms.network.file.api.Result;
import com.huawei.hms.network.file.core.Constants;
import com.huawei.hms.network.file.core.task.e;
import java.io.Closeable;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class Utils {
    public static final String REQUEST_TIME = "requestTime";
    public static final String RESPONSE_TIME = "responseTime";
    private static final String TAG = "Utils";
    public static final String TOTAL_TIME = "totalTime";
    private static final String WARN_MESSAGE = "com.huawei.android.hms:download and com.huawei.android.hms:upload will not receive new requirements in the future. The maintenance deadline is December 31, 2021. You are advised to switch services You are advised to switch services to com.huawei.hms:filemanager before this time point";
    private static final HashMap<String, String> METHOD_MAP = new HashMap<>();
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T cast(Object obj) {
        return obj;
    }

    public static boolean checkReadableStream(int i) {
        return i != 304;
    }

    public static void printDeprecatedMethodLog(String str) {
        HashMap<String, String> hashMap = METHOD_MAP;
        if (hashMap.containsKey(str)) {
            return;
        }
        hashMap.put(str, str);
        FLogger.w("Utils", "the method " + str + " is deprecated and invalid in this version", new Object[0]);
    }

    public static void printDeprecatedLogIfNeed() {
        FLogger.w("Utils", WARN_MESSAGE, new Object[0]);
    }

    public static String nullStrToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static boolean isUrlValid(List<String> list) {
        if (isEmpty(list)) {
            return true;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (!isUrlValid(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUrlValid(String str) {
        if (isBlank(str)) {
            return false;
        }
        return str.toLowerCase(Locale.ROOT).startsWith("http");
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isDeepEqual(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getValueFromOptions(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            try {
                return new JSONObject(str2).getString(str);
            } catch (JSONException unused) {
                FLogger.w("Utils", "get key From options fail, key is " + str, new Object[0]);
            }
        }
        return "";
    }

    public static String getUrlPath(String str) {
        String path = (isBlank(str) || !str.startsWith("http")) ? "" : Uri.parse(str).getPath();
        return (path == null || path.equals(str)) ? "" : path;
    }

    public static Result.STATUS getMapedStatus(e.a aVar) {
        int i = a.f5638a[aVar.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? Result.STATUS.INVALID : Result.STATUS.PAUSE : Result.STATUS.PROCESS : Result.STATUS.INIT;
    }

    public static long getLongId() {
        return e.b().a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
    
        if (r10 != null) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0075, code lost:
    
        if (r10 == null) goto L56;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFileHashData(java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "Close FileInputStream failed!"
            java.lang.String r1 = "Utils"
            r2 = 0
            java.security.MessageDigest r11 = java.security.MessageDigest.getInstance(r11)     // Catch: java.lang.Throwable -> L3f java.lang.IndexOutOfBoundsException -> L41 java.lang.IllegalArgumentException -> L4c java.io.IOException -> L57 java.io.FileNotFoundException -> L62 java.security.NoSuchAlgorithmException -> L6d
            java.io.FileInputStream r10 = com.huawei.hms.network.file.core.util.c.b(r10)     // Catch: java.lang.Throwable -> L3f java.lang.IndexOutOfBoundsException -> L41 java.lang.IllegalArgumentException -> L4c java.io.IOException -> L57 java.io.FileNotFoundException -> L62 java.security.NoSuchAlgorithmException -> L6d
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            r4 = 0
            r6 = r4
        L14:
            int r8 = r10.read(r3)     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            r9 = -1
            if (r8 == r9) goto L22
            r9 = 0
            r11.update(r3, r9, r8)     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            long r8 = (long) r8     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            long r6 = r6 + r8
            goto L14
        L22:
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 <= 0) goto L2f
            byte[] r11 = r11.digest()     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            java.lang.String r11 = byteArrayToHex(r11)     // Catch: java.lang.Throwable -> L32 java.lang.IndexOutOfBoundsException -> L35 java.lang.IllegalArgumentException -> L37 java.io.IOException -> L39 java.io.FileNotFoundException -> L3b java.security.NoSuchAlgorithmException -> L3d
            r2 = r11
        L2f:
            if (r10 == 0) goto L7e
            goto L77
        L32:
            r11 = move-exception
            r2 = r10
            goto L7f
        L35:
            r11 = move-exception
            goto L44
        L37:
            r11 = move-exception
            goto L4f
        L39:
            r11 = move-exception
            goto L5a
        L3b:
            r11 = move-exception
            goto L65
        L3d:
            r11 = move-exception
            goto L70
        L3f:
            r10 = move-exception
            goto L80
        L41:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L44:
            java.lang.String r3 = "getFileHashData IndexOutOfBoundsException"
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L4c:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L4f:
            java.lang.String r3 = "getFileHashData IllegalArgumentException"
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L57:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L5a:
            java.lang.String r3 = "getFileHashData IOException"
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L62:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L65:
            java.lang.String r3 = "getFileHashData FileNotFoundException"
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
            goto L77
        L6d:
            r10 = move-exception
            r11 = r10
            r10 = r2
        L70:
            java.lang.String r3 = "getFileHashData NoSuchAlgorithmException"
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r3, r11)     // Catch: java.lang.Throwable -> L32
            if (r10 == 0) goto L7e
        L77:
            r10.close()     // Catch: java.io.IOException -> L7b
            goto L7e
        L7b:
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r0)
        L7e:
            return r2
        L7f:
            r10 = r11
        L80:
            if (r2 == 0) goto L89
            r2.close()     // Catch: java.io.IOException -> L86
            goto L89
        L86:
            com.huawei.hms.network.file.core.util.FLogger.e(r1, r0)
        L89:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.network.file.core.util.Utils.getFileHashData(java.lang.String, java.lang.String):java.lang.String");
    }

    public static int getCheckRangeResult(String str, int i, boolean z, boolean z2) {
        boolean z3;
        StringBuilder sb;
        boolean z4;
        StringBuilder sb2;
        String str2;
        HashMap<Pair<Integer, Integer>, Integer> hashMap = Constants.a.i.get(str);
        if (hashMap == null || hashMap.isEmpty()) {
            return 0;
        }
        Iterator<Pair<Integer, Integer>> it = hashMap.keySet().iterator();
        Pair<Integer, Integer> pair = null;
        while (it.hasNext()) {
            pair = it.next();
        }
        Iterator<Integer> it2 = hashMap.values().iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            i2 = it2.next().intValue();
        }
        if (pair == null) {
            FLogger.e("Utils", "getCheckRangeResult null rangePair for:" + str);
            return i2;
        }
        if (z) {
            z3 = i >= ((Integer) pair.first).intValue();
            sb = new StringBuilder("[");
        } else {
            z3 = i > ((Integer) pair.first).intValue();
            sb = new StringBuilder(com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY);
        }
        sb.append(pair.first);
        sb.append(",");
        String sb3 = sb.toString();
        if (z2) {
            z4 = i <= ((Integer) pair.second).intValue();
            sb2 = new StringBuilder();
            sb2.append(pair.second);
            str2 = "]";
        } else {
            z4 = i < ((Integer) pair.second).intValue();
            sb2 = new StringBuilder();
            sb2.append(pair.second);
            str2 = com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY;
        }
        sb2.append(str2);
        String sb4 = sb2.toString();
        if (z3 && z4) {
            return i;
        }
        FLogger.w("Utils", str + " range is " + sb3 + sb4, new Object[0]);
        return i2;
    }

    public static boolean deleteFile(String str) {
        if (isBlank(str)) {
            return false;
        }
        File a2 = c.a(str);
        if (!a2.exists()) {
            return false;
        }
        if (a2.delete()) {
            FLogger.v("Utils", "deleteFile success filePath: " + str);
            return true;
        }
        FLogger.v("Utils", "deleteFile failed filePath: " + str);
        return false;
    }

    public static String convertToJsonString(GlobalRequestConfig globalRequestConfig) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (globalRequestConfig.getConnectTimeoutMillis() != -100) {
                jSONObject.put(x2.CONNECT_TIMEOUT, globalRequestConfig.getConnectTimeoutMillis());
            }
            if (globalRequestConfig.getReadTimeoutMillis() != -100) {
                jSONObject.put("read_timeout", globalRequestConfig.getReadTimeoutMillis());
            }
            if (globalRequestConfig.getWriteTimeoutMillis() != -100) {
                jSONObject.put(x2.WRITE_TIMEMEOUT, globalRequestConfig.getWriteTimeoutMillis());
            }
            if (globalRequestConfig.getPingIntervalMillis() != -100) {
                jSONObject.put("ping_interval", globalRequestConfig.getPingIntervalMillis());
            }
            if (globalRequestConfig.getCallTimeoutMillis() != -100) {
                jSONObject.put("call_timeout", globalRequestConfig.getCallTimeoutMillis());
            }
            jSONObject.put("retry_time", 0);
        } catch (JSONException e) {
            FLogger.w("Utils", "convertToJsonString exception", e);
        }
        return jSONObject.toString();
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (RuntimeException | Exception e) {
            FLogger.logException(e);
        }
    }

    public static void checkNpParamsWithException(String str, Object obj) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(str + " can not be null");
    }

    public static String byteArrayToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >>> 4) & 15];
            i += 2;
            cArr[i2] = cArr2[b & BaseType.Obj];
        }
        return new String(cArr);
    }

    public static String anonymizeMessage(String str) {
        return isBlank(str) ? "" : StringUtils.anonymizeMessage(str);
    }

    public static void addNonEmptyMapItem(Map<String, String> map, Map<String, String> map2) {
        if (map == null || map2 == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
    }

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5638a;

        static {
            int[] iArr = new int[e.a.values().length];
            f5638a = iArr;
            try {
                iArr[e.a.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5638a[e.a.PROCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5638a[e.a.PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
