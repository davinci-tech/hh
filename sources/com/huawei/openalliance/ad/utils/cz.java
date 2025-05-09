package com.huawei.openalliance.ad.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.constant.Scheme;
import com.huawei.openalliance.ad.ho;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class cz {
    private static String r(String str) {
        StringBuilder sb;
        int i;
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf >= 0 && (i = lastIndexOf + 1) < str.length()) {
            str = str.substring(i);
        }
        int length = str.length();
        if (length > 3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str.substring(0, 3));
            sb = sb2;
        } else {
            if (length <= 1) {
                return Constants.CONFUSION_CHARS;
            }
            sb = new StringBuilder();
            sb.append(str.substring(0, length - 1));
        }
        sb.append(Constants.CONFUSION_CHARS);
        return sb.toString();
    }

    private static String q(String str) {
        StringBuilder sb = new StringBuilder();
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (scheme != null) {
            sb.append(scheme);
            sb.append("://");
        }
        String lastPathSegment = parse.getLastPathSegment();
        if (lastPathSegment == null) {
            lastPathSegment = URLUtil.isNetworkUrl(str) ? k(str) : parse.getHost();
        } else {
            sb.append("******/");
        }
        if (lastPathSegment != null) {
            int length = lastPathSegment.length();
            if (length > 3) {
                sb.append((CharSequence) lastPathSegment, 0, 3);
            } else if (length > 1) {
                sb.append((CharSequence) lastPathSegment, 0, length - 1);
            }
        }
        sb.append(Constants.CONFUSION_CHARS);
        return sb.toString();
    }

    public static boolean p(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^[0-9\\*\\+\\-\\.]*$", str) && str.length() < 100;
    }

    public static boolean o(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^[0-9]+,[0-9]+$", str) && str.length() < 100;
    }

    public static String n(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            if (str.length() > 100) {
                str = str.substring(0, 100);
            }
            return o.a(str.getBytes("UTF-8"));
        } catch (Throwable th) {
            ho.c("StringUtils", "formatInput Exception: %s", th.getClass().getSimpleName());
            return "";
        }
    }

    public static String m(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return o.a(str.getBytes("UTF-8"));
        } catch (Throwable th) {
            ho.c("StringUtils", "base64Encode Exception: %s", th.getClass().getSimpleName());
            return null;
        }
    }

    public static String l(String str) {
        return TextUtils.isEmpty(str) ? str : str.toUpperCase(Locale.ENGLISH);
    }

    public static String k(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (!URLUtil.isNetworkUrl(str)) {
                ho.c("StringUtils", "url don't starts with http or https");
                return null;
            }
            if (str.contains("{")) {
                str = str.replaceAll("\\{", "");
            }
            if (str.contains("}")) {
                str = str.replaceAll("\\}", "");
            }
            return new URI(str).getHost();
        } catch (URISyntaxException e) {
            ho.d("StringUtils", "getHostByURI error : " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static boolean j(String str) {
        return str != null && (str.startsWith(Scheme.HTTP.toString()) || str.startsWith(Scheme.HTTPS.toString()));
    }

    public static Long i(String str) {
        if (b(str)) {
            return null;
        }
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            ho.d("StringUtils", "toLong NumberFormatException:" + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Integer h(String str) {
        if (b(str)) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            ho.d("StringUtils", "toInteger NumberFormatException:" + e.getClass().getSimpleName());
            return null;
        }
    }

    public static boolean g(String str, String str2) {
        return TextUtils.isEmpty(f(str, str2));
    }

    public static String g(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        if (length <= 3) {
            if (length > 1) {
                str = str.substring(0, length - 1);
            }
            sb.append(str);
            sb.append(Constants.CONFUSION_CHARS);
        } else {
            int i = (length / 5) + 1;
            String substring = str.substring(0, Math.min(3, i));
            String substring2 = str.substring(length - Math.min(3, i));
            sb.append(substring);
            sb.append(Constants.CONFUSION_CHARS);
            sb.append(substring2);
        }
        return sb.toString();
    }

    public static String f(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        boolean isEmpty = TextUtils.isEmpty(str2);
        String trim = str.trim();
        return !isEmpty ? trim.replace(str2, "") : trim;
    }

    public static String f(String str) {
        return TextUtils.isEmpty(str) ? "" : str.contains("://") ? q(str) : r(str);
    }

    public static boolean e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return TextUtils.equals(str, str2);
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static boolean d(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str.startsWith(Scheme.HTTP.toString()) && !str.startsWith(Scheme.HTTPS.toString())) {
            return false;
        }
        String[] split = str2.split(",");
        String k = k(str);
        ho.b("StringUtils", "host:" + k);
        if (TextUtils.isEmpty(k)) {
            return false;
        }
        return Arrays.asList(split).contains(k);
    }

    public static String d(String str) {
        if (b(str)) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            ho.d("StringUtils", "unsupport encoding");
            return null;
        }
    }

    public static String c(String str, String str2) {
        return (TextUtils.isEmpty(str) || str2 == null || str.lastIndexOf(str2) != str.length() + (-1)) ? str : str.substring(0, str.length() - 1);
    }

    public static String c(String str) {
        String str2;
        if (b(str)) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str2 = "unsupport encoding";
            ho.d("StringUtils", str2);
            return null;
        } catch (Exception unused2) {
            str2 = "decode error";
            ho.d("StringUtils", str2);
            return null;
        }
    }

    private static Object c(Object obj) {
        if (obj instanceof Bundle) {
            return b((Bundle) obj);
        }
        try {
            return JSONObject.wrap(obj);
        } catch (Throwable th) {
            ho.c("StringUtils", "wrap Exception:" + th.getClass().getSimpleName());
            return JSONObject.NULL;
        }
    }

    public static Integer[] b(JSONArray jSONArray) {
        String[] a2 = a(jSONArray);
        if (bg.a(a2)) {
            return new Integer[0];
        }
        Integer[] numArr = new Integer[a2.length];
        for (int i = 0; i < a2.length; i++) {
            try {
                numArr[i] = Integer.valueOf(Integer.parseInt(a2[i]));
            } catch (Throwable unused) {
                numArr[i] = null;
                ho.c("StringUtils", "toIntegerArray exception.");
            }
        }
        return numArr;
    }

    public static boolean b(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static JSONObject b(Bundle bundle) {
        if (bundle == null) {
            return new JSONObject();
        }
        Set<String> keySet = bundle.keySet();
        JSONObject jSONObject = new JSONObject();
        for (String str : keySet) {
            try {
                jSONObject.put(str, c(bundle.get(str)));
            } catch (Throwable th) {
                ho.c("StringUtils", "converBundleToJson Exception:" + th.getClass().getSimpleName());
            }
        }
        return jSONObject;
    }

    public static List<Integer> b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        String[] split = str.split(str2);
        ArrayList arrayList = new ArrayList();
        for (String str3 : split) {
            try {
                arrayList.add(Integer.valueOf(Integer.parseInt(str3)));
            } catch (Throwable unused) {
                ho.c("StringUtils", "parse integer list error.");
            }
        }
        return arrayList;
    }

    public static String b(Object obj) {
        return obj == null ? com.huawei.operation.utils.Constants.NULL : "not null";
    }

    public static String[] a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new String[0];
        }
        int length = jSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = jSONArray.optString(i);
        }
        return strArr;
    }

    public static boolean a(Set<String> set, String str) {
        if (!bg.a(set) && !TextUtils.isEmpty(str)) {
            return set.contains(str);
        }
        ho.a("StringUtils", "ModelList or ModelName is empty");
        return true;
    }

    public static void a(StringBuilder sb, char c) {
        int lastIndexOf;
        if (sb == null || (lastIndexOf = sb.lastIndexOf(String.valueOf(c))) == -1 || lastIndexOf != sb.length() - 1) {
            return;
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    public static List<String> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (bg.a(list)) {
            return arrayList;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String c = c(it.next());
            if (c != null) {
                arrayList.add(c);
            }
        }
        return arrayList;
    }

    public static List<String> a(String str, String str2) {
        return TextUtils.isEmpty(str) ? Collections.emptyList() : new ArrayList(Arrays.asList(str.split(str2)));
    }

    public static String a(List<String> list, String str) {
        StringBuilder sb = new StringBuilder();
        if (list != null && !list.isEmpty()) {
            boolean z = true;
            for (String str2 : list) {
                if (!z) {
                    sb.append(str);
                }
                sb.append(str2);
                z = false;
            }
        }
        return sb.toString();
    }

    public static String a(Collection<String> collection, String str) {
        StringBuilder sb = new StringBuilder();
        if (collection != null && !collection.isEmpty()) {
            boolean z = true;
            for (String str2 : collection) {
                if (!z) {
                    sb.append(str);
                }
                sb.append(str2);
                z = false;
            }
        }
        return sb.toString();
    }

    public static String a(String str, Context context) {
        BufferedReader bufferedReader;
        InputStream open = context.getAssets().open(str);
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(open, "UTF-8"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine + "\n");
                    } catch (IOException e) {
                        e = e;
                        bufferedReader2 = bufferedReader;
                        ho.d("StringUtils", "getStringFromAsset, " + e.getClass().getSimpleName());
                        bufferedReader = bufferedReader2;
                        cy.a(bufferedReader);
                        cy.a((Closeable) open);
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader2 = bufferedReader;
                        cy.a(bufferedReader2);
                        cy.a((Closeable) open);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
            }
            cy.a(bufferedReader);
            cy.a((Closeable) open);
            return sb.toString();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public static String a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return b(bundle).toString();
    }

    public static String a(Context context, String str) {
        StringBuilder sb;
        if (!TextUtils.isEmpty(str) && context != null) {
            String str2 = str + a(context);
            Resources resources = context.getResources();
            try {
                return resources.getString(resources.getIdentifier(str2, "string", context.getPackageName()));
            } catch (RuntimeException e) {
                e = e;
                sb = new StringBuilder("getChinaString ");
                sb.append(e.getClass().getSimpleName());
                ho.d("StringUtils", sb.toString());
                return "";
            } catch (Exception e2) {
                e = e2;
                sb = new StringBuilder("getChinaString ");
                sb.append(e.getClass().getSimpleName());
                ho.d("StringUtils", sb.toString());
                return "";
            }
        }
        return "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(android.content.Context r3, int r4, java.lang.String r5, java.lang.Object... r6) {
        /*
            java.lang.String r0 = "getChinaString "
            android.content.res.Resources r1 = r3.getResources()
            com.huawei.openalliance.ad.co r2 = com.huawei.openalliance.ad.bz.a(r3)     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            boolean r2 = r2.a()     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            if (r2 == 0) goto L59
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            r2.<init>()     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            r2.append(r5)     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            java.lang.String r5 = "_zh"
            r2.append(r5)     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            java.lang.String r5 = r2.toString()     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            java.lang.String r2 = "string"
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            int r3 = r1.getIdentifier(r5, r2, r3)     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            if (r6 == 0) goto L33
            java.lang.String r3 = r1.getString(r3, r6)     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            goto L5a
        L33:
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Exception -> L38 java.lang.RuntimeException -> L3f
            goto L5a
        L38:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r0)
            goto L45
        L3f:
            r3 = move-exception
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r0)
        L45:
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            java.lang.String r5 = "StringUtils"
            com.huawei.openalliance.ad.ho.d(r5, r3)
        L59:
            r3 = 0
        L5a:
            if (r3 != 0) goto L6a
            if (r6 == 0) goto L66
            int r3 = r6.length
            if (r3 <= 0) goto L66
            java.lang.String r3 = r1.getString(r4, r6)
            goto L6a
        L66:
            java.lang.String r3 = r1.getString(r4)
        L6a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.utils.cz.a(android.content.Context, int, java.lang.String, java.lang.Object[]):java.lang.String");
    }

    public static String a(Context context) {
        return (context != null && w.a(context).b()) ? Constants.TV_SUFFIX : "";
    }

    public static Integer a(String str) {
        try {
            return Integer.valueOf((int) Double.parseDouble(str));
        } catch (Exception e) {
            ho.d("StringUtils", "parseStringToInt ex:" + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Float a(String str, Float f) {
        if (b(str)) {
            return f;
        }
        try {
            return Float.valueOf(str);
        } catch (NumberFormatException e) {
            ho.d("StringUtils", "toFloat NumberFormatException:" + e.getClass().getSimpleName());
            return f;
        }
    }

    public static long a(String str, long j) {
        try {
            return b(str) ? j : Long.parseLong(str);
        } catch (NumberFormatException e) {
            ho.c("StringUtils", "parseLongOrDefault exception: " + e.getClass().getSimpleName());
            return j;
        }
    }

    public static int a(String str, int i) {
        try {
            return b(str) ? i : Integer.parseInt(str);
        } catch (NumberFormatException e) {
            ho.c("StringUtils", "parseIntOrDefault exception: " + e.getClass().getSimpleName());
            return i;
        }
    }
}
