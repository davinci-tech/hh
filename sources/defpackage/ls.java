package defpackage;

import android.text.TextUtils;
import com.alipay.sdk.m.r.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ls {
    public String[] b;
    public a d;
    public String e;

    public static void a(ls lsVar) {
        String[] c = lsVar.c();
        if (c.length == 3 && TextUtils.equals("tid", c[0])) {
            lu b = lu.b(lw.c().d());
            if (TextUtils.isEmpty(c[1]) || TextUtils.isEmpty(c[2])) {
                return;
            }
            b.d(c[1], c[2]);
        }
    }

    public static String[] c(String str) {
        ArrayList arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        for (String str2 : str.substring(indexOf + 1, lastIndexOf).split("' *, *'", -1)) {
            arrayList.add(str2.trim().replaceAll("'", "").replaceAll("\"", ""));
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public String[] c() {
        return this.b;
    }

    public ls(String str, a aVar) {
        this.e = str;
        this.d = aVar;
    }

    public static List<ls> b(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        String[] a2 = a(jSONObject.optString("name", ""));
        for (int i = 0; i < a2.length; i++) {
            a a3 = a.a(a2[i]);
            if (a3 != a.None) {
                ls lsVar = new ls(a2[i], a3);
                lsVar.b = c(a2[i]);
                arrayList.add(lsVar);
            }
        }
        return arrayList;
    }

    public static String[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(";");
    }

    public a a() {
        return this.d;
    }
}
