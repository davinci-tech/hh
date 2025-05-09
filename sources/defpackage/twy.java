package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class twy {
    public static Map<String, String> e(String str, String str2, String str3) {
        String sb;
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/json");
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("X-App-ID", str);
        }
        hashMap.put("X-Client-Version", "1.0.4.315");
        String str4 = Build.MODEL;
        hashMap.put("terminalType", str4);
        hashMap.put("X-Request-ID", str3);
        hashMap.put("X-Credential-Terminal", "aucs");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("terminalType", str4);
        hashMap2.put("appPkgName", str2);
        hashMap2.put("callTime", String.valueOf(System.currentTimeMillis()));
        if (hashMap2.size() == 0) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry entry : hashMap2.entrySet()) {
                sb2.append((String) entry.getKey());
                sb2.append("=");
                sb2.append((String) entry.getValue());
                sb2.append(",");
            }
            sb2.deleteCharAt(sb2.lastIndexOf(","));
            sb = sb2.toString();
        }
        hashMap.put("X-RequestContext", sb);
        return hashMap;
    }

    public static String b(String str) throws twc {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("request", str);
            return jSONObject.toString();
        } catch (JSONException e) {
            StringBuilder e2 = twf.e("getReqBody error : ");
            e2.append(e.getMessage());
            throw new twc(Const.RawDataType.HEALTH_SLEEP_RECORD, e2.toString());
        }
    }
}
