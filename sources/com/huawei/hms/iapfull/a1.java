package com.huawei.hms.iapfull;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.PermissionChecker;
import com.huawei.health.R;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;
import com.huawei.secure.android.common.util.SafeBase64;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a1 {
    public static String a(Context context, String str, String str2, String str3) {
        String str4 = (TextUtils.isEmpty(str3) || "CNY".equals(str3)) ? "¥" : "";
        if (context == null) {
            y0.a("PayPresenter", "context is null");
            return str;
        }
        String string = context.getString(R.string._2130851274_res_0x7f0235ca, str4, str);
        if ("string_cny_normal".equals(str2)) {
            return string;
        }
        if (!"string_cny_discount".equals(str2)) {
            return "string_cny_confirm".equals(str2) ? context.getString(R.string._2130851272_res_0x7f0235c8, string) : str4;
        }
        return "-\u200e" + string;
    }

    public static boolean b(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo = (context == null || PermissionChecker.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") != 0 || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) ? null : connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x005c, code lost:
    
        if (r8 != null) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(java.lang.String r7, java.lang.Object r8, org.json.JSONObject r9) throws org.json.JSONException, java.lang.IllegalAccessException {
        /*
            Method dump skipped, instructions count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.iapfull.a1.a(java.lang.String, java.lang.Object, org.json.JSONObject):boolean");
    }

    public static String a() {
        String str;
        try {
            str = System.currentTimeMillis() + "_" + String.format(Locale.ROOT, "%06d", Integer.valueOf(SecureRandom.getInstanceStrong().nextInt(1000000)));
        } catch (NoSuchAlgorithmException unused) {
            y0.a("getStrongNoiseTamp", "getStrongNoiseTamp NoSuchAlgorithmException.");
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return System.currentTimeMillis() + "_" + String.format(Locale.ROOT, "%06d", Integer.valueOf(new SecureRandom().nextInt(1000000)));
    }

    public static String a(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                if (!HwPayConstant.KEY_SIGN.equals(next)) {
                    Object obj = jSONObject.get(next);
                    if (obj instanceof Integer) {
                        if (((Integer) obj).intValue() >= 0) {
                            obj = ((Integer) obj) + "";
                        }
                    }
                    if (obj instanceof ArrayList) {
                        StringBuilder sb = new StringBuilder();
                        for (Map map : (List) obj) {
                            if (sb.length() > 0) {
                                sb.append("#");
                            }
                            sb.append(a((Map<String, String>) map));
                        }
                        obj = sb.toString();
                    }
                    if (obj instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) obj;
                        StringBuilder sb2 = new StringBuilder();
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            if (sb2.length() > 0) {
                                sb2.append("#");
                            }
                            sb2.append(a(jSONObject2));
                        }
                        obj = sb2.toString();
                    }
                    if (obj != null) {
                        hashMap.put(next, obj.toString());
                    }
                }
            } catch (JSONException unused) {
                y0.a("SignUtils", "json exception");
            }
        }
        return a(hashMap);
    }

    public static String a(String str, String str2) {
        String str3;
        PrivateKey b = y0.b(str);
        if (b != null) {
            try {
            } catch (Exception unused) {
                y0.a("SignUtil", "getPrivateKeyLength, Exception");
            }
            if (((RSAPrivateKey) b).getModulus().bitLength() > 512) {
                if (HwPayConstant.SIGNTYPE_RSA256PSS.equalsIgnoreCase(str2)) {
                    y0.b("SignUtils", "getSignTypByBuildVersion.");
                    return HwPayConstant.SIGNTYPE_RSA256PSS;
                }
                str3 = "getSignTypByBuildVersion. Using Default Configuration RSA256";
                y0.b("SignUtils", str3);
                return "RSA256";
            }
        }
        str3 = "getSignTypByBuildVersion. If the key length is less than 512 bytes, use RSA256.";
        y0.b("SignUtils", str3);
        return "RSA256";
    }

    public static String a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
                PrivateKey b = y0.b(str);
                if (TextUtils.isEmpty(str2) || b == null) {
                    y0.a("RSASign", "content or key is null");
                } else {
                    try {
                        Signature signature = Signature.getInstance(HwPayConstant.SIGNTYPE_RSA256PSS.equals(str3) ? "SHA256WithRSA/PSS" : "SHA256WithRSA");
                        signature.initSign(b);
                        signature.update(str2.getBytes("UTF-8"));
                        return SafeBase64.encodeToString(signature.sign(), 0);
                    } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | SignatureException unused) {
                    }
                }
            } else {
                y0.a("RSASign", "sign content or key is null");
            }
        }
        return "";
    }

    private static boolean b(String str) {
        return (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str)) ? false : true;
    }

    public static String b(b1 b1Var) {
        StringBuilder sb;
        String message;
        if (b1Var == null) {
            y0.a("JsonUtil", "createJsonString error, the input IMessageEntity is null");
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (Class<?> cls = b1Var.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    String name = field.getName();
                    Object obj = field.get(b1Var);
                    field.setAccessible(isAccessible);
                    a(name, obj, jSONObject);
                }
            } catch (IllegalAccessException e) {
                sb = new StringBuilder("catch IllegalAccessException ");
                message = e.getMessage();
                sb.append(message);
                y0.a("JsonUtil", sb.toString());
                return jSONObject.toString();
            } catch (JSONException e2) {
                sb = new StringBuilder("catch JSONException ");
                message = e2.getMessage();
                sb.append(message);
                y0.a("JsonUtil", sb.toString());
                return jSONObject.toString();
            }
        }
        return jSONObject.toString();
    }

    public static String b() {
        String str = Build.MODEL;
        if (!b(str)) {
            str = Build.DEVICE;
        }
        if (!b(str)) {
            str = Build.PRODUCT;
        }
        return !b(str) ? "0" : str;
    }

    public static boolean a(Intent intent) {
        boolean z;
        if (intent != null) {
            try {
                intent.getStringExtra("ANYTHING");
                z = false;
            } catch (Exception unused) {
            }
            y0.b("UIUtil", "checkIntent4DDOS: " + z);
            return z;
        }
        z = true;
        y0.b("UIUtil", "checkIntent4DDOS: " + z);
        return z;
    }

    public static boolean a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }

    public static void a(Activity activity, int i, int i2) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new f1(activity, i, i2));
    }

    public static Map<String, String> a(String str, String str2, boolean z) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        for (String str3 : str.split(str2)) {
            String[] split = str3.split("=");
            if (split.length > 1) {
                String str4 = split[0];
                String substring = str3.substring(str4.length() + 1);
                if (z) {
                    substring = a(substring);
                }
                hashMap.put(str4, substring);
            }
        }
        return hashMap;
    }

    public static String a(Map<String, String> map) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str2 = (String) arrayList.get(i);
            if (!HwPayConstant.KEY_SIGN.equals(str2) && (str = map.get(str2)) != null && !str.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "" : "&");
                sb.append(str2);
                sb.append("=");
                sb.append(str);
                stringBuffer.append(sb.toString());
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static String a(String str) {
        String str2;
        if (!TextUtils.isEmpty(str)) {
            try {
                return URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException unused) {
                str2 = "WebFormerUtil decodeUrl UnsupportedEncodingException.";
                y0.a("decodeUrl", str2);
                return str;
            } catch (IllegalArgumentException unused2) {
                str2 = "WebFormerUtil decodeUrl IllegalArgumentException.";
                y0.a("decodeUrl", str2);
                return str;
            }
        }
        return str;
    }

    private static String a(b1 b1Var) throws IllegalAccessException, JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Class<?> cls = b1Var.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                String name = field.getName();
                Object obj = field.get(b1Var);
                field.setAccessible(isAccessible);
                a(name, obj, jSONObject);
            }
        }
        return jSONObject.toString();
    }
}
