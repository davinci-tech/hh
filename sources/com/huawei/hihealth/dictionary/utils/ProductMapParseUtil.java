package com.huawei.hihealth.dictionary.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.dictionary.constants.ProductMap;
import com.huawei.hihealth.dictionary.constants.ProductMapInfo;
import com.huawei.hihealth.util.ReleaseLogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.util.LogUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ProductMapParseUtil {
    private static final String c = File.separator + "productmap" + File.separator + "product_map.json";
    private static boolean b = false;

    private ProductMapParseUtil() {
    }

    public static boolean b(Context context) {
        if (b) {
            return true;
        }
        try {
            boolean a2 = a(context, context.getFilesDir().getCanonicalPath() + c);
            if (HiCommonUtil.b(context)) {
                b = a2;
            }
            LogUtil.d("HiH_ProductMapParseUtil", "loadProductMapConfig isParseSuccess = ", Boolean.valueOf(a2));
            return a2;
        } catch (IOException unused) {
            ReleaseLogUtil.c("HiH_ProductMapParseUtil", "loadProductMapConfig getCanonicalPath IOException");
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        JSONArray jSONArray;
        LogUtil.d("HiH_ProductMapParseUtil", "use HiLib parseJsonFile ProductMapParseUtil to parse filePath");
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("HiH_ProductMapParseUtil", "parseJsonFile filePath is empty");
            return false;
        }
        String d = d(context, new File(str));
        if (TextUtils.isEmpty(d)) {
            ReleaseLogUtil.d("HiH_ProductMapParseUtil", "parseJsonFile urlJsonString is empty");
            return false;
        }
        try {
            JSONArray jSONArray2 = new JSONArray(d);
            for (int i = 0; i < jSONArray2.length(); i++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i);
                if (jSONObject.has("productList") && (jSONArray = jSONObject.getJSONArray("productList")) != null && jSONArray.length() != 0) {
                    b(jSONArray);
                }
            }
            LogUtil.d("HiH_HiH_ProductMapParseUtil", "parse productMap jsonFile success");
            return true;
        } catch (JSONException unused) {
            ReleaseLogUtil.c("HiH_ProductMapParseUtil", "parseJsonFile JSONException");
            return false;
        }
    }

    private static void b(JSONArray jSONArray) {
        JSONObject jSONObject;
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if ((jSONArray.get(i) instanceof JSONObject) && (jSONObject = (JSONObject) jSONArray.get(i)) != null) {
                    String string = jSONObject.getString("deviceType");
                    String string2 = jSONObject.getString("smartProductId");
                    String string3 = jSONObject.getString("productId");
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        ProductMapInfo productMapInfo = new ProductMapInfo(string, string2, string3, jSONObject.getString("modelName"), jSONObject.getString("manufacturerId"), jSONObject.has("marketingName") ? jSONObject.getString("marketingName") : null, jSONObject.getInt("deviceId"));
                        if (jSONObject.has("optionalFileds")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("optionalFileds");
                            if (jSONObject2.has("deviceName")) {
                                String string4 = jSONObject2.getString("deviceName");
                                ProductMapInfo.OptionalFileds optionalFileds = new ProductMapInfo.OptionalFileds();
                                optionalFileds.b(string4);
                                productMapInfo.d(optionalFileds);
                            }
                        }
                        ProductMap.c(productMapInfo);
                    }
                }
            } catch (JSONException unused) {
                ReleaseLogUtil.c("HiH_ProductMapParseUtil", "handleProduct JSONException");
            }
        }
    }

    private static String d(Context context, File file) {
        if (file == null) {
            ReleaseLogUtil.d("HiH_ProductMapParseUtil", "readFileToData, file == null");
            return null;
        }
        LogUtil.d("HiH_ProductMapParseUtil", "enter readFileToData: fileName = ", file.getName());
        if (!file.exists() || e(context, file)) {
            ReleaseLogUtil.d("HiH_ProductMapParseUtil", "readFileToData, file not exist or presetVersionHigher");
            return a("product_map.json", context);
        }
        return d(file);
    }

    private static boolean e(Context context, File file) {
        String d = d(file);
        String a2 = a("product_map.json", context);
        if (TextUtils.isEmpty(d)) {
            LogUtil.d("HiH_ProductMapParseUtil", "presetVersionHigher, localProductMapData is empty");
            return true;
        }
        int a3 = a(d);
        int a4 = a(a2);
        ReleaseLogUtil.b("HiH_ProductMapParseUtil", "localVersion, ", Integer.valueOf(a3), ", presetVersion,", Integer.valueOf(a4));
        return a3 < a4;
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONArray(str).getJSONObject(0);
            if (!jSONObject.has("version")) {
                ReleaseLogUtil.b("HiH_ProductMapParseUtil", "getVersion, has not version");
                return 0;
            }
            return Integer.parseInt(jSONObject.getString("version"));
        } catch (NumberFormatException | JSONException unused) {
            ReleaseLogUtil.c("HiH_ProductMapParseUtil", "parseJsonFile JSONException or NumberFormatException");
            return 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String d(java.io.File r11) {
        /*
            long r0 = r11.length()
            r2 = 5242880(0x500000, double:2.590327E-317)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            java.lang.String r3 = ""
            java.lang.String r4 = "HiH_ProductMapParseUtil"
            if (r2 <= 0) goto L1e
            java.lang.String r11 = "readFileToData, file too length, length="
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r0}
            com.huawei.hihealth.util.ReleaseLogUtil.d(r4, r11)
            return r3
        L1e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 2048(0x800, float:2.87E-42)
            r0.<init>(r1)
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]
            r2 = 0
            r5 = r2
        L2b:
            r6 = 3
            r7 = 5242880(0x500000, float:7.34684E-39)
            if (r5 >= r6) goto L7c
            int r6 = r0.length()
            r0.delete(r2, r6)
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.io.IOException -> L61
            r6.<init>(r11)     // Catch: java.io.IOException -> L61
        L3c:
            int r8 = r6.read(r1)     // Catch: java.lang.Throwable -> L57
            r9 = -1
            if (r8 == r9) goto L53
            java.lang.String r9 = new java.lang.String     // Catch: java.lang.Throwable -> L57
            java.lang.String r10 = "UTF-8"
            r9.<init>(r1, r2, r8, r10)     // Catch: java.lang.Throwable -> L57
            r0.append(r9)     // Catch: java.lang.Throwable -> L57
            int r8 = r0.length()     // Catch: java.lang.Throwable -> L57
            if (r8 <= r7) goto L3c
        L53:
            r6.close()     // Catch: java.io.IOException -> L61
            goto L7c
        L57:
            r7 = move-exception
            r6.close()     // Catch: java.lang.Throwable -> L5c
            goto L60
        L5c:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch: java.io.IOException -> L61
        L60:
            throw r7     // Catch: java.io.IOException -> L61
        L61:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            int r7 = r0.length()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.String r8 = "readFileToData num="
            java.lang.String r9 = ", dataLen="
            java.lang.Object[] r6 = new java.lang.Object[]{r8, r6, r9, r7}
            com.huawei.hihealth.util.ReleaseLogUtil.c(r4, r6)
            int r5 = r5 + 1
            goto L2b
        L7c:
            int r11 = r0.length()
            if (r11 <= r7) goto L95
            int r11 = r0.length()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            java.lang.String r0 = "readFileToData, buffer too length, length="
            java.lang.Object[] r11 = new java.lang.Object[]{r0, r11}
            com.huawei.hihealth.util.ReleaseLogUtil.d(r4, r11)
            return r3
        L95:
            java.lang.String r11 = r0.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hihealth.dictionary.utils.ProductMapParseUtil.d(java.io.File):java.lang.String");
    }

    private static String a(String str, Context context) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            try {
                                break;
                            } catch (IOException unused) {
                                ReleaseLogUtil.c("HiH_ProductMapParseUtil", "bufferedReader IOException");
                            }
                        }
                    } catch (IOException unused2) {
                        bufferedReader2 = bufferedReader;
                        ReleaseLogUtil.c("HiH_ProductMapParseUtil", "handleProduct IOException");
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused3) {
                                ReleaseLogUtil.c("HiH_ProductMapParseUtil", "bufferedReader IOException");
                            }
                        }
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused4) {
                                ReleaseLogUtil.c("HiH_ProductMapParseUtil", "bufferedReader IOException");
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
            } catch (IOException unused5) {
            }
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = bufferedReader2;
        }
    }
}
