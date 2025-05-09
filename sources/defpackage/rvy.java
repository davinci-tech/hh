package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.CloudParamKeys;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class rvy {
    public static void d(Context context, int i, String str) {
        if (i == 0) {
            int d = d(context, str);
            LogUtil.a("UIDV_AiRuleParse", "AiRuleParse resCode = ", Integer.valueOf(d));
            if (d == 0) {
                LogUtil.a("UIDV_AiRuleParse", "AiRuleParse saveResult = ", Integer.valueOf(d));
                SharedPreferenceManager.e(context, String.valueOf(10000), "airule_v1", "1", new StorageParams());
                Intent intent = new Intent("com.huawei.bone.action.AIRULE_PARSE_SUCCESS");
                intent.setPackage(BaseApplication.getContext().getPackageName());
                context.sendBroadcast(intent, LocalBroadcast.c);
                return;
            }
            SharedPreferenceManager.e(context, String.valueOf(10000), "airule_v1", "0", new StorageParams());
            SharedPreferenceManager.e(context, String.valueOf(10000), "airule_ver", "0", new StorageParams());
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(10000), "airule_v1", "0", new StorageParams());
    }

    public static int d(Context context, String str) {
        LogUtil.a("UIDV_AiRuleParse", "parseResult");
        int i = -1;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("UIDV_AiRuleParse", "filePath is empty");
            return -1;
        }
        String d = d(str);
        if (!TextUtils.isEmpty(d)) {
            i = a(context, d);
        } else {
            LogUtil.a("UIDV_AiRuleParse", "resp is null ");
        }
        LogUtil.a("UIDV_AiRuleParse", "AIRuleParse finish ");
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [int] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.FileInputStream, java.io.InputStream] */
    private static String d(String str) {
        ?? r4;
        String str2 = "";
        String str3 = null;
        ?? r3 = 0;
        try {
            try {
                r4 = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                r4 = str3;
            }
        } catch (IOException unused) {
        }
        try {
            byte[] bArr = new byte[r4.available()];
            ?? read = r4.read(bArr);
            String str4 = read;
            if (read > 0) {
                String str5 = new String(bArr, "UTF-8");
                str2 = str5;
                str4 = str5;
            }
            try {
                r4.close();
                str3 = str4;
            } catch (IOException unused2) {
                LogUtil.b("UIDV_AiRuleParse", "inputStream.close IOException");
                str3 = str4;
            }
        } catch (IOException unused3) {
            r3 = r4;
            LogUtil.b("UIDV_AiRuleParse", "getStringFile IOException");
            str3 = r3;
            if (r3 != 0) {
                try {
                    r3.close();
                    str3 = r3;
                } catch (IOException unused4) {
                    LogUtil.b("UIDV_AiRuleParse", "inputStream.close IOException");
                    str3 = r3;
                }
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            if (r4 != null) {
                try {
                    r4.close();
                } catch (IOException unused5) {
                    LogUtil.b("UIDV_AiRuleParse", "inputStream.close IOException");
                }
            }
            throw th;
        }
        return str2;
    }

    private static int a(Context context, String str) {
        LogUtil.a("UIDV_AiRuleParse", "saveParse");
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("service");
            boolean z = jSONObject.getBoolean(k.g);
            LogUtil.a("UIDV_AiRuleParse", "service = ", string);
            LogUtil.a("UIDV_AiRuleParse", "enable = ", Boolean.valueOf(z));
            JSONArray jSONArray = new JSONArray(jSONObject.getString("modules"));
            int i = jSONArray.length() > 0 ? 0 : -1;
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String string2 = jSONObject2.getString("module");
                LogUtil.a("UIDV_AiRuleParse", "module = ", string2);
                if (!TextUtils.isEmpty(string2)) {
                    String valueOf = String.valueOf(a(string2));
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("airules");
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject3 = jSONArray2.getJSONObject(i3);
                        String string3 = jSONObject3.getString("airule");
                        LogUtil.c("UIDV_AiRuleParse", "airuleName = ", string3);
                        if (!TextUtils.isEmpty(string3) && !TextUtils.isEmpty(valueOf)) {
                            SharedPreferenceManager.e(context, valueOf, string3, jSONObject3.toString(), new StorageParams());
                        } else {
                            LogUtil.a("UIDV_AiRuleParse", "airuleName or moduleId is null");
                        }
                    }
                }
            }
            return i;
        } catch (JSONException e) {
            LogUtil.b("UIDV_AiRuleParse", "JSONException ", e.getMessage());
            return -1;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static int a(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case -1386153546:
                if (str.equals("bloodp")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1354814997:
                if (str.equals("common")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -791592328:
                if (str.equals("weight")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -21086770:
                if (str.equals("bloodsg")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3237038:
                if (str.equals(CloudParamKeys.INFO)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 3641801:
                if (str.equals("walk")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        int i = c != 0 ? c != 1 ? c != 2 ? c != 3 ? c != 4 ? c != 5 ? 30004 : 30000 : OnRegisterSkinAttrsListener.REGISTER_BY_SCAN : 30006 : 30001 : 30005 : 30002;
        LogUtil.a("UIDV_AiRuleParse", "moduleId = ", Integer.valueOf(i));
        return i;
    }
}
