package defpackage;

import android.text.TextUtils;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class txj {
    public byte[] b;
    public String c;
    public a d;
    public b e;

    public static class a {
        public int c;
        public String d;
    }

    public static class b {
        public String[] d;
        public String e;
    }

    public void b(String[] strArr) throws twc {
        try {
            JSONObject jSONObject = new JSONObject(new String(twe.c(strArr[0], 8), StandardCharsets.UTF_8));
            JSONArray optJSONArray = jSONObject.optJSONArray("x5c");
            String[] strArr2 = {optJSONArray.optString(0), optJSONArray.optString(1)};
            b bVar = new b();
            this.e = bVar;
            bVar.e = jSONObject.getString("alg");
            this.e.d = strArr2;
        } catch (RuntimeException | JSONException e) {
            StringBuilder e2 = twf.e("Fail to convert jws string to header, ");
            e2.append(e.getMessage());
            throw new twc(1012L, e2.toString());
        }
    }

    public void c(String[] strArr) throws twc {
        try {
            JSONObject jSONObject = new JSONObject(new String(twe.c(strArr[1], 8), StandardCharsets.UTF_8));
            a aVar = new a();
            this.d = aVar;
            aVar.d = jSONObject.getString("component");
            this.d.c = jSONObject.getInt("version");
        } catch (RuntimeException | JSONException e) {
            StringBuilder e2 = twf.e("Fail to convert jws string to payload, ");
            e2.append(e.getMessage());
            throw new twc(1012L, e2.toString());
        }
    }

    public void b(String str, String[] strArr) throws twc {
        try {
            this.b = twe.c(strArr[2], 8);
            this.c = str.substring(0, str.lastIndexOf("."));
        } catch (twc e) {
            StringBuilder e2 = twf.e("Fail to convert jws string to Content, ");
            e2.append(e.getMessage());
            throw new twc(1012L, e2.toString());
        }
    }

    public final void c(String str) throws twc {
        if (TextUtils.isEmpty(str)) {
            throw new twc(1012L, "ComponentJws is empty.");
        }
    }

    public txj(String str) throws twc {
        c(str);
        String[] split = str.split("\\.");
        b(split);
        c(split);
        b(str, split);
    }
}
