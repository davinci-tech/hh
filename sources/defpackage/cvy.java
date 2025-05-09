package defpackage;

import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo;
import health.compact.a.IoUtils;
import health.compact.a.UnitUtil;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class cvy {
    private static String[] d = {"sr-Latn", "jv-Latn"};
    private static HashMap<String, String> e = new HashMap() { // from class: cvy.3
        private static final long serialVersionUID = 1212859686823339267L;

        {
            put("sr-Latn", "b+sr+Latn");
            put("jv-Latn", "b+jv+Latn");
        }
    };

    public static List<String> c(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        Locale locale = BaseApplication.getContext().getResources().getConfiguration().locale;
        ArrayList arrayList = new ArrayList(16);
        List<String> e2 = e(str, jSONObject, jSONObject2);
        if (e2.size() == 0) {
            return arrayList;
        }
        List<String> e3 = e(str + "_fromat", jSONObject, jSONObject2);
        int i = 0;
        String str2 = e2.get(0);
        if (e3.size() == 0) {
            arrayList.add(str2);
        } else {
            List<Object> b = b(e3);
            while (i < b.size()) {
                i++;
                String str3 = "%" + i + "$s";
                str2 = str2.replace("%" + i + "$f", str3).replace("%d", str3).replace("%s", str3).replace("%f", str3).replace("%" + i + "$d", str3);
            }
            arrayList.add(String.format(Locale.ROOT, str2, b.toArray()));
        }
        return arrayList;
    }

    private static List<Object> b(List<String> list) {
        ArrayList arrayList = new ArrayList(16);
        for (String str : list) {
            if (h(str)) {
                String e2 = UnitUtil.e(Long.parseLong(str), 1, 0);
                LogUtil.b("DeviceDataDealUtils", "isNumber formatValue:", e2);
                arrayList.add(e2);
            } else {
                try {
                    String[] split = str.split("\\.");
                    if (split.length > 1) {
                        str = UnitUtil.e(Double.valueOf(str).doubleValue(), 1, split[1].length());
                    }
                } catch (NumberFormatException unused) {
                }
                LogUtil.b("DeviceDataDealUtils", "string formatValue:", str);
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static boolean h(String str) {
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    private static List<String> e(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject d2;
        ArrayList arrayList = new ArrayList(10);
        if (jSONObject == null) {
            LogUtil.c("DeviceDataDealUtils", "loadStringForWear jsonFile == null.");
            return arrayList;
        }
        try {
            d2 = d(str, jSONObject, jSONObject2);
        } catch (JSONException unused) {
            LogUtil.e("DeviceDataDealUtils", "loadStringForWear JSONException");
        }
        if (d2 == null) {
            return arrayList;
        }
        Object obj = d2.get(str);
        if (obj instanceof String) {
            arrayList.add((String) obj);
            return arrayList;
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        }
        return arrayList;
    }

    private static JSONObject d(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject.has(str)) {
            return jSONObject;
        }
        if (jSONObject2 == null) {
            return null;
        }
        if (jSONObject2.has(str)) {
            return jSONObject2;
        }
        LogUtil.c("DeviceDataDealUtils", "loadStringForWear jsonObject not has key: ", str);
        return null;
    }

    public static File c(String str) {
        File[] listFiles;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
            return d("en.json", listFiles);
        }
        return null;
    }

    private static File d(String str, File[] fileArr) {
        File file = null;
        for (File file2 : fileArr) {
            if (str.equals(file2.getName())) {
                file = file2;
            }
        }
        return file;
    }

    public static File e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Locale locale = Resources.getSystem().getConfiguration().locale;
        return d(locale.getLanguage(), locale.getCountry(), locale.toLanguageTag(), str);
    }

    private static File d(String str, String str2, String str3, String str4) {
        String str5;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DeviceDataDealUtils", " getTargetJsonFile TextUtils.isEmpty(language).");
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            str5 = str + ".json";
        } else {
            str5 = str + "-r" + str2 + ".json";
        }
        File file = new File(str4);
        if (file.exists() && file.isDirectory()) {
            return a(file.listFiles(), str3, str5, str);
        }
        LogUtil.d("DeviceDataDealUtils", "getFormatString4,jsonDir is not exit");
        return null;
    }

    private static File a(File[] fileArr, String str, String str2, String str3) {
        String j;
        if (fileArr == null || fileArr.length == 0) {
            LogUtil.c("DeviceDataDealUtils", "getFileByName wrong dir.no file. can not find target file, return null.");
            return null;
        }
        if (str != null && (j = j(str)) != null) {
            File c = c(fileArr, e.get(j) + ".json");
            if (c != null && c.length() > 0) {
                LogUtil.d("DeviceDataDealUtils", "getFileByName file is ok");
                return c;
            }
            File c2 = c(fileArr, "en.json");
            if (c2 != null && c2.length() > 0) {
                LogUtil.d("DeviceDataDealUtils", "getFileByName en file is ok");
                return c2;
            }
        }
        for (File file : fileArr) {
            if (str2.equals(file.getName())) {
                return file;
            }
        }
        if (str2.contains("-r")) {
            String str4 = d(str3) + ".json";
            for (File file2 : fileArr) {
                if (str4.equals(file2.getName())) {
                    return file2;
                }
            }
        }
        for (File file3 : fileArr) {
            if ("en.json".equals(file3.getName())) {
                return file3;
            }
        }
        return null;
    }

    public static String d(String str) {
        char c;
        Locale locale = Resources.getSystem().getConfiguration().locale;
        String script = locale.getScript();
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 3149) {
            if (str.equals("bo")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 3241) {
            if (str.equals("en")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 3500) {
            if (hashCode == 3886 && str.equals(MLAsrConstants.LAN_ZH)) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("my")) {
                c = 2;
            }
            c = 65535;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? str : locale.toString().endsWith("#Hans") ? "zh-rCN" : "zh-rTW" : "Qaag".equals(script) ? "my-rZG" : "my" : "Qaag".equals(script) ? "en-rGB" : str : "bo-rCN";
    }

    private static String j(String str) {
        String str2;
        String[] strArr = d;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str2 = null;
                break;
            }
            str2 = strArr[i];
            if (str.startsWith(str2)) {
                break;
            }
            i++;
        }
        LogUtil.b("DeviceDataDealUtils", "changeTag is ", str, " after changeTag is ", str2);
        return str2;
    }

    private static File c(File[] fileArr, String str) {
        for (File file : fileArr) {
            if (str.equals(file.getName())) {
                return file;
            }
        }
        return null;
    }

    public static void a(File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            LogUtil.c("DeviceDataDealUtils", "deleteDirWithFile deleteFile is not exist");
            return;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            LogUtil.c("DeviceDataDealUtils", "deleteDirWithFile files is null");
            return;
        }
        for (File file2 : listFiles) {
            c(file2);
        }
        if (file.delete()) {
            LogUtil.d("DeviceDataDealUtils", "delete file success");
        }
    }

    private static void c(File file) {
        if (file != null) {
            if (file.isFile()) {
                LogUtil.b("DeviceDataDealUtils", "deleteDirWithFile result=", Boolean.valueOf(file.delete()));
            } else if (file.isDirectory()) {
                a(file);
            } else {
                LogUtil.b("DeviceDataDealUtils", "file type error");
            }
        }
    }

    public static String a() {
        return cuv.f11488a + "index_all" + File.separator + "done";
    }

    public static String c() {
        return cuv.f11488a + "index_all" + File.separator + "index_all.json";
    }

    public static String d() {
        return cuv.f11488a + "img_index_all" + File.separator;
    }

    public static String b() {
        return cuv.f11488a + "lang_index_all" + File.separator;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return cuv.f11488a + str + File.separator;
    }

    public static void a(String str) {
        File file;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("DeviceDataDealUtils", "createDoneMarkFile, path is empty");
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                file = new File(str);
            } catch (IOException unused) {
                LogUtil.e("DeviceDataDealUtils", "createDoneMarkFile createDoneFile IOException");
            }
            if (file.getParentFile() == null) {
                return;
            }
            if (!file.getParentFile().exists()) {
                LogUtil.d("DeviceDataDealUtils", "createDoneMarkFile isDirMade is ", Boolean.valueOf(file.mkdir()));
            }
            if (!file.exists()) {
                LogUtil.d("DeviceDataDealUtils", "createDoneMarkFile isNewFileCreated is ", Boolean.valueOf(file.createNewFile()));
            }
            String valueOf = String.valueOf(new Date().getTime() / 1000);
            LogUtil.d("DeviceDataDealUtils", "createDoneMarkFile timestamp is =", valueOf);
            fileOutputStream = FileUtils.openOutputStream(file);
            byte[] bytes = valueOf.getBytes("UTF-8");
            fileOutputStream.write(bytes, 0, bytes.length);
            fileOutputStream.flush();
        } finally {
            IoUtils.e(fileOutputStream);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.json.JSONObject b(java.io.File r9) {
        /*
            java.lang.String r0 = "DeviceDataDealUtils"
            r1 = 0
            java.lang.String r2 = r9.getCanonicalPath()     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            java.lang.String r2 = health.compact.a.CommonUtil.c(r2)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            r4 = 0
            if (r3 == 0) goto L2d
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            java.lang.String r2 = "getJsonObjectFromFile safePath is empty"
            r9[r4] = r2     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            health.compact.a.util.LogUtil.c(r0, r9)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            boolean r9 = android.text.TextUtils.isEmpty(r1)
            if (r9 != 0) goto L29
            java.lang.Object[] r9 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r9)
        L29:
            health.compact.a.IoUtils.e(r1)
            return r1
        L2d:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L85 org.json.JSONException -> L88 java.io.IOException -> L8a java.io.UnsupportedEncodingException -> Lc5 java.io.FileNotFoundException -> Ld6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r5 = 16
            r2.<init>(r5)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            long r5 = r9.length()     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L72
            int r9 = (int) r5     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            byte[] r9 = new byte[r9]     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
        L46:
            int r5 = r3.read(r9)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r6 = -1
            if (r5 == r6) goto L58
            java.lang.String r6 = new java.lang.String     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            java.nio.charset.Charset r7 = java.nio.charset.StandardCharsets.UTF_8     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r6.<init>(r9, r4, r5, r7)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r2.append(r6)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            goto L46
        L58:
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            java.lang.String r2 = r2.toString()     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            r9.<init>(r2)     // Catch: org.json.JSONException -> L81 java.io.IOException -> L83 java.lang.Throwable -> Lb3 java.io.UnsupportedEncodingException -> Lc6 java.io.FileNotFoundException -> Ld7
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L6e
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r1)
        L6e:
            health.compact.a.IoUtils.e(r3)
            return r9
        L72:
            boolean r9 = android.text.TextUtils.isEmpty(r1)
            if (r9 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r9)
            goto Le6
        L81:
            r9 = move-exception
            goto L8c
        L83:
            r9 = move-exception
            goto L8c
        L85:
            r9 = move-exception
            r3 = r1
            goto Lb4
        L88:
            r9 = move-exception
            goto L8b
        L8a:
            r9 = move-exception
        L8b:
            r3 = r1
        L8c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb3
            r2.<init>()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r4 = "getJsonObjectFromFile "
            r2.append(r4)     // Catch: java.lang.Throwable -> Lb3
            java.lang.Class r9 = r9.getClass()     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r9 = r9.getSimpleName()     // Catch: java.lang.Throwable -> Lb3
            r2.append(r9)     // Catch: java.lang.Throwable -> Lb3
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Throwable -> Lb3
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.util.LogUtil.e(r0, r9)
            goto Le6
        Lb3:
            r9 = move-exception
        Lb4:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto Lc1
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            health.compact.a.util.LogUtil.e(r0, r1)
        Lc1:
            health.compact.a.IoUtils.e(r3)
            throw r9
        Lc5:
            r3 = r1
        Lc6:
            java.lang.String r9 = "getJsonObjectFromFile UnsupportedEncodingException"
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.util.LogUtil.e(r0, r9)
            goto Le6
        Ld6:
            r3 = r1
        Ld7:
            java.lang.String r9 = "getJsonObjectFromFile FileNotFoundException"
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            if (r2 != 0) goto Le6
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            health.compact.a.util.LogUtil.e(r0, r9)
        Le6:
            health.compact.a.IoUtils.e(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.cvy.b(java.io.File):org.json.JSONObject");
    }

    public static DeviceDownloadSourceInfo c(com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        if (deviceDownloadSourceInfo == null) {
            return null;
        }
        DeviceDownloadSourceInfo deviceDownloadSourceInfo2 = new DeviceDownloadSourceInfo(null);
        deviceDownloadSourceInfo2.setConfigurationPoint(deviceDownloadSourceInfo.getConfigurationPoint());
        deviceDownloadSourceInfo2.setIndexName(deviceDownloadSourceInfo.getIndexName());
        deviceDownloadSourceInfo2.setSite(deviceDownloadSourceInfo.getSite());
        return deviceDownloadSourceInfo2;
    }
}
