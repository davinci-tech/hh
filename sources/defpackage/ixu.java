package defpackage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class ixu {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0063 A[Catch: IOException -> 0x005f, TRY_LEAVE, TryCatch #7 {IOException -> 0x005f, blocks: (B:29:0x005b, B:23:0x0063), top: B:28:0x005b }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.gson.Gson] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v4, types: [T] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v19, types: [java.io.BufferedReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r7v22, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> T d(java.io.InputStream r7, java.lang.Class<T> r8) {
        /*
            java.lang.String r0 = "Foundation_JsonUtil"
            com.google.gson.GsonBuilder r1 = new com.google.gson.GsonBuilder
            r1.<init>()
            com.google.gson.Gson r1 = r1.create()
            r2 = 0
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L3e java.lang.NullPointerException -> L41 com.google.gson.JsonSyntaxException -> L43
            java.nio.charset.Charset r4 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.lang.Throwable -> L3e java.lang.NullPointerException -> L41 com.google.gson.JsonSyntaxException -> L43
            r3.<init>(r7, r4)     // Catch: java.lang.Throwable -> L3e java.lang.NullPointerException -> L41 com.google.gson.JsonSyntaxException -> L43
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L38 com.google.gson.JsonSyntaxException -> L3a
            r7.<init>(r3)     // Catch: java.lang.Throwable -> L34 java.lang.NullPointerException -> L38 com.google.gson.JsonSyntaxException -> L3a
            java.lang.Object r2 = r1.fromJson(r7, r8)     // Catch: java.lang.NullPointerException -> L30 com.google.gson.JsonSyntaxException -> L32 java.lang.Throwable -> L73
            r3.close()     // Catch: java.io.IOException -> L23
            r7.close()     // Catch: java.io.IOException -> L23
            goto L72
        L23:
            r7 = move-exception
            java.lang.String r7 = com.huawei.haf.common.exception.ExceptionUtils.d(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)
            goto L72
        L30:
            r8 = move-exception
            goto L47
        L32:
            r8 = move-exception
            goto L47
        L34:
            r7 = move-exception
            r8 = r2
            r2 = r3
            goto L78
        L38:
            r7 = move-exception
            goto L3b
        L3a:
            r7 = move-exception
        L3b:
            r8 = r7
            r7 = r2
            goto L47
        L3e:
            r7 = move-exception
            r8 = r2
            goto L78
        L41:
            r7 = move-exception
            goto L44
        L43:
            r7 = move-exception
        L44:
            r8 = r7
            r7 = r2
            r3 = r7
        L47:
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L73
            java.lang.String r4 = "readDataFromJsonFile exception :"
            r5 = 0
            r1[r5] = r4     // Catch: java.lang.Throwable -> L73
            java.lang.String r8 = com.huawei.haf.common.exception.ExceptionUtils.d(r8)     // Catch: java.lang.Throwable -> L73
            r4 = 1
            r1[r4] = r8     // Catch: java.lang.Throwable -> L73
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)     // Catch: java.lang.Throwable -> L73
            if (r3 == 0) goto L61
            r3.close()     // Catch: java.io.IOException -> L5f
            goto L61
        L5f:
            r7 = move-exception
            goto L67
        L61:
            if (r7 == 0) goto L72
            r7.close()     // Catch: java.io.IOException -> L5f
            goto L72
        L67:
            java.lang.String r7 = com.huawei.haf.common.exception.ExceptionUtils.d(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)
        L72:
            return r2
        L73:
            r8 = move-exception
            r2 = r3
            r6 = r8
            r8 = r7
            r7 = r6
        L78:
            if (r2 == 0) goto L80
            r2.close()     // Catch: java.io.IOException -> L7e
            goto L80
        L7e:
            r8 = move-exception
            goto L86
        L80:
            if (r8 == 0) goto L91
            r8.close()     // Catch: java.io.IOException -> L7e
            goto L91
        L86:
            java.lang.String r8 = com.huawei.haf.common.exception.ExceptionUtils.d(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r8)
        L91:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ixu.d(java.io.InputStream, java.lang.Class):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00a3 A[Catch: IOException -> 0x009f, TRY_LEAVE, TryCatch #7 {IOException -> 0x009f, blocks: (B:56:0x009b, B:47:0x00a3), top: B:55:0x009b }] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(java.io.InputStream r10) {
        /*
            java.lang.String r0 = "Foundation_JsonUtil"
            r1 = 1
            r2 = 0
            r3 = 2
            r4 = 0
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67
            java.lang.String r6 = "UTF-8"
            r5.<init>(r10, r6)     // Catch: java.lang.Throwable -> L64 java.io.IOException -> L67
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            r6 = 2048(0x800, float:2.87E-42)
            r10.<init>(r5, r6)     // Catch: java.lang.Throwable -> L5a java.io.IOException -> L5e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            r4.<init>()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
        L19:
            java.lang.String r6 = r10.readLine()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            if (r6 == 0) goto L3b
            int r7 = r4.length()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            r8 = 5242880(0x500000, float:7.34684E-39)
            if (r7 <= r8) goto L37
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            java.lang.String r7 = "json file len is beyond max limits:"
            r6[r2] = r7     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            r6[r1] = r7     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            com.huawei.hwlogsmodel.LogUtil.b(r0, r6)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            goto L3b
        L37:
            r4.append(r6)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            goto L19
        L3b:
            java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L56
            r5.close()     // Catch: java.io.IOException -> L46
            r10.close()     // Catch: java.io.IOException -> L46
            goto L94
        L46:
            r10 = move-exception
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)
            goto L94
        L53:
            r1 = move-exception
            r4 = r5
            goto L96
        L56:
            r4 = move-exception
            r9 = r5
            r5 = r4
            goto L62
        L5a:
            r10 = move-exception
            r1 = r4
            r4 = r5
            goto L99
        L5e:
            r10 = move-exception
            r9 = r5
            r5 = r10
            r10 = r4
        L62:
            r4 = r9
            goto L6a
        L64:
            r10 = move-exception
            r1 = r4
            goto L99
        L67:
            r10 = move-exception
            r5 = r10
            r10 = r4
        L6a:
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L95
            java.lang.String r6 = "readStringFromJsonFile"
            r3[r2] = r6     // Catch: java.lang.Throwable -> L95
            java.lang.String r2 = com.huawei.haf.common.exception.ExceptionUtils.d(r5)     // Catch: java.lang.Throwable -> L95
            r3[r1] = r2     // Catch: java.lang.Throwable -> L95
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> L95
            if (r4 == 0) goto L81
            r4.close()     // Catch: java.io.IOException -> L7f
            goto L81
        L7f:
            r10 = move-exception
            goto L87
        L81:
            if (r10 == 0) goto L92
            r10.close()     // Catch: java.io.IOException -> L7f
            goto L92
        L87:
            java.lang.String r10 = com.huawei.haf.common.exception.ExceptionUtils.d(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r10)
        L92:
            java.lang.String r1 = ""
        L94:
            return r1
        L95:
            r1 = move-exception
        L96:
            r9 = r1
            r1 = r10
            r10 = r9
        L99:
            if (r4 == 0) goto La1
            r4.close()     // Catch: java.io.IOException -> L9f
            goto La1
        L9f:
            r1 = move-exception
            goto La7
        La1:
            if (r1 == 0) goto Lb2
            r1.close()     // Catch: java.io.IOException -> L9f
            goto Lb2
        La7:
            java.lang.String r1 = com.huawei.haf.common.exception.ExceptionUtils.d(r1)
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r1)
        Lb2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ixu.b(java.io.InputStream):java.lang.String");
    }

    public static final <T> T e(InputStream inputStream, TypeToken<T> typeToken) {
        try {
            return (T) new Gson().fromJson(b(inputStream), typeToken.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b("Foundation_JsonUtil", "Gson to List error:", ExceptionUtils.d(e));
            return null;
        }
    }

    public static <T> T e(String str, TypeToken<T> typeToken) {
        try {
            return (T) new Gson().fromJson(str, typeToken.getType());
        } catch (JsonSyntaxException e) {
            LogUtil.b("Foundation_JsonUtil", "Gson to List error:", ExceptionUtils.d(e));
            return null;
        }
    }
}
