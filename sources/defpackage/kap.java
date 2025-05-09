package defpackage;

import com.huawei.hwdevice.phoneprocess.mgr.hwcontactsyncmgr.contacts.sync.utils.Bean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kap {
    public static JSONArray e() {
        List<String> c = jzn.c(jzn.d(true, 0L), true);
        if (c.isEmpty()) {
            LogUtil.h("ProfileContactUtils", "queryProfile: raw contact id list is empty");
            return new JSONArray();
        }
        return d(c);
    }

    private static JSONArray d(List<String> list) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        JSONArray jSONArray = new JSONArray();
        if (list == null || list.size() == 0) {
            return jSONArray;
        }
        String[] strArr = (String[]) list.toArray(new String[0]);
        jzn.b(strArr, concurrentHashMap, true);
        List<Bean> a2 = jzn.a(concurrentHashMap, strArr);
        try {
            JSONArray jSONArray2 = new JSONArray();
            try {
                for (Bean bean : a2) {
                    if (bean instanceof kaa) {
                        JSONObject jSONObject = new JSONObject();
                        String d = kab.d((kaa) bean);
                        if (kac.b().equals(((kaa) bean).c())) {
                            jSONObject.put("hicall", d);
                        } else {
                            jSONObject.put("local", d);
                        }
                        jSONArray2.put(jSONObject);
                    }
                }
                return jSONArray2;
            } catch (JSONException unused) {
                jSONArray = jSONArray2;
                LogUtil.b("ProfileContactUtils", "queryAndUpdateProfile: occurred JSONException.");
                return jSONArray;
            }
        } catch (JSONException unused2) {
        }
    }
}
