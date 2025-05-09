package defpackage;

import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes3.dex */
public class csa {
    private ArrayList<b> c;

    public ArrayList<b> e() {
        return this.c;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private Map<String, e> f11422a;
        private String d;

        public void a(String str) {
            this.d = str;
        }

        public Map<String, e> c() {
            return this.f11422a;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("UserInfoData{dateTime ='");
            stringBuffer.append(this.d).append("'userdata ='");
            Map<String, e> map = this.f11422a;
            stringBuffer.append(map == null ? Constants.NULL : map.toString()).append("'}");
            return stringBuffer.toString();
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    if (jSONObject.has("dateTime")) {
                        a(jSONObject.getString("dateTime"));
                    } else {
                        cpw.a(false, "DeviceServiceUser", "UserData toObject not has dateTime");
                    }
                    this.f11422a = new HashMap(16);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (next.contains("id_")) {
                            e eVar = new e();
                            eVar.c(jSONObject.getJSONObject(next));
                            this.f11422a.put(next, eVar);
                        }
                    }
                    if (this.f11422a.size() == 0) {
                        e eVar2 = new e();
                        eVar2.c(jSONObject);
                        this.f11422a.put("id_1", eVar2);
                    }
                } catch (JSONException e) {
                    cpw.e(false, "DeviceServiceUser", "UserInfoData toObject JSONException ", e.getMessage());
                }
            }
        }

        public e c(String str) {
            if (TextUtils.isEmpty(str)) {
                cpw.a(false, "DeviceServiceUser", "getUserData data accountId is null ");
                return null;
            }
            Map<String, e> c = c();
            if (c != null) {
                for (e eVar : c.values()) {
                    if (eVar != null && str.equals(eVar.d())) {
                        return eVar;
                    }
                }
            } else {
                cpw.a(false, "DeviceServiceUser", "UserInfoData getUserData userDataMap is null");
            }
            cpw.a(false, "DeviceServiceUser", "UserInfoData getUserData not foung UserData id", str);
            return null;
        }

        public void c(Map<String, String> map) {
            if (map != null) {
                if (map.containsKey("dateTime")) {
                    a(map.get("dateTime"));
                } else {
                    cpw.d(false, "DeviceServiceUser", "UserInfoData mapToObject map has not dateTime");
                }
                this.f11422a = new HashMap(16);
                e eVar = new e();
                eVar.e(map);
                this.f11422a.put("id_1", eVar);
                return;
            }
            cpw.d(false, "DeviceServiceUser", "UserInfoData mapToObject map is null ");
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private Map<String, String> f11423a;
        private String d;

        public String d() {
            return this.d;
        }

        public void c(String str) {
            this.d = str;
        }

        public Map<String, String> c() {
            return this.f11423a;
        }

        public void c(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    if (jSONObject.has("id")) {
                        c(jSONObject.getString("id"));
                    } else {
                        cpw.a(false, "DeviceServiceUser", "UserData toObject not has id");
                    }
                    this.f11423a = new HashMap(16);
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (next.contains("userInfo")) {
                            String string = jSONObject.getString(next);
                            String[] split = string.split("\\|");
                            cpw.c(false, "DeviceServiceUser", "UserData mapToObject userInfo ", string);
                            b(string, split);
                        } else {
                            cpw.d(false, "DeviceServiceUser", "UserData toObject keyStr is error ", next);
                        }
                    }
                } catch (JSONException e) {
                    cpw.e(false, "DeviceServiceUser", "UserData toObject JSONException ", e.getMessage());
                }
            }
        }

        private void b(String str, String[] strArr) {
            if (strArr != null && strArr.length >= 4) {
                if (String.valueOf(0).equals(strArr[1])) {
                    strArr[1] = d();
                    StringBuffer stringBuffer = new StringBuffer(16);
                    stringBuffer.append("1|");
                    stringBuffer.append(strArr[1]).append("|");
                    stringBuffer.append(strArr[2]).append("|");
                    stringBuffer.append(strArr[3]);
                    this.f11423a.put(strArr[1], stringBuffer.toString());
                    return;
                }
                this.f11423a.put(strArr[1], str);
                return;
            }
            cpw.d(false, "DeviceServiceUser", "UserData toObject userInfo size is error ", str);
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("UserData{id ='");
            stringBuffer.append(this.d).append("'user ='");
            Map<String, String> map = this.f11423a;
            stringBuffer.append(map == null ? Constants.NULL : map.toString()).append("'}");
            return stringBuffer.toString();
        }

        public void e(Map<String, String> map) {
            if (map != null) {
                this.f11423a = new HashMap(16);
                if (map.containsKey("id")) {
                    c(map.get("id"));
                } else {
                    cpw.d(false, "DeviceServiceUser", "UserData mapToObject Not carrying id");
                }
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    b(it.next());
                }
                return;
            }
            cpw.d(false, "DeviceServiceUser", "UserData mapToObject map is null ");
        }

        private void b(Map.Entry<String, String> entry) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value) || !key.contains("userInfo")) {
                return;
            }
            String[] split = value.split("\\|");
            cpw.c(false, "DeviceServiceUser", "UserData mapToObject userInfo ", value);
            if (split != null && split.length >= 4) {
                b(value, split);
            } else {
                cpw.d(false, "DeviceServiceUser", "UserData mapToObject userInfo size is error ", value);
            }
        }
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.e(false, "DeviceServiceUser", "toObject json is null ");
            return;
        }
        try {
            Object nextValue = new JSONTokener(str).nextValue();
            if (nextValue instanceof JSONObject) {
                d((JSONObject) nextValue);
            } else if (nextValue instanceof JSONArray) {
                d((JSONArray) nextValue);
            } else {
                cpw.d(false, "DeviceServiceUser", "toObject json type is error ", nextValue.getClass().getSimpleName());
            }
        } catch (JSONException e2) {
            cpw.e(false, "DeviceServiceUser", "toObject JSONException ", e2.getMessage());
        }
    }

    private void d(JSONObject jSONObject) {
        if (jSONObject != null) {
            if (this.c == null) {
                this.c = new ArrayList<>(16);
            }
            b bVar = new b();
            bVar.a(jSONObject);
            this.c.add(bVar);
            return;
        }
        cpw.a(false, "DeviceServiceUser", "parserJsonObject jsonObject is null ");
    }

    private void d(JSONArray jSONArray) {
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    d(jSONArray.getJSONObject(i));
                } catch (JSONException e2) {
                    cpw.e(false, "DeviceServiceUser", "parserJsonArray JSONException ", e2.getMessage());
                }
            }
            return;
        }
        cpw.a(false, "DeviceServiceUser", "parserJsonArray jsonArray is null ");
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeviceServiceUser{data ='");
        ArrayList<b> arrayList = this.c;
        stringBuffer.append(arrayList == null ? Constants.NULL : arrayList.toString()).append("'}");
        return stringBuffer.toString();
    }

    public e c(String str) {
        if (TextUtils.isEmpty(str)) {
            cpw.a(false, "DeviceServiceUser", "getUserData accountId is null ");
            return null;
        }
        ArrayList<b> e2 = e();
        if (e2 != null && e2.size() > 0) {
            cpw.a(false, "DeviceServiceUser", "getUserData datas ", e2.toString());
            Iterator<b> it = e2.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if (next != null) {
                    return next.c(str);
                }
            }
        } else {
            cpw.a(false, "DeviceServiceUser", "getUserData datas is null");
        }
        cpw.a(false, "DeviceServiceUser", "getUserData not found userdata");
        return null;
    }

    public void d(Map<String, String> map) {
        if (map != null) {
            if (this.c == null) {
                this.c = new ArrayList<>(16);
            }
            b bVar = new b();
            bVar.c(map);
            this.c.add(bVar);
            return;
        }
        cpw.d(false, "DeviceServiceUser", " mapToObject map is null ");
    }
}
