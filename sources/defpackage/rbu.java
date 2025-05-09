package defpackage;

import android.os.Build;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class rbu {
    private static boolean e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Pattern.compile(str2).matcher(str).matches();
    }

    public static boolean a(String str) {
        if (d(str)) {
            return false;
        }
        return e(str, "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+");
    }

    private static boolean d(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String d() {
        String str = Build.MODEL;
        return !TextUtils.isEmpty(str) ? str : Build.DEVICE;
    }

    public static <T> List<T> d(JSONObject jSONObject, String str, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(new Gson().fromJson(jSONArray.get(i).toString(), (Class) cls));
            }
        } catch (JSONException unused) {
            LogUtil.b("StringUtil", "getListFromJson JSONException");
        }
        return arrayList;
    }

    public static <T> JSONArray e(List<T> list, Class<T> cls) {
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(new JSONObject(HiJsonUtil.d(it.next(), cls)));
            }
        } catch (JSONException unused) {
            LogUtil.b("StringUtil", "getListFromJson JSONException");
        }
        return jSONArray;
    }
}
