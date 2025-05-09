package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class jbp {
    public static jca e(String str) {
        jca jcaVar = new jca();
        jcaVar.d(-1.0d);
        jcaVar.b(0);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AtmosphereInfoParseUtils", "weather info is empty");
            return jcaVar;
        }
        LogUtil.a("AtmosphereInfoParseUtils", "response:", str);
        try {
            JsonObject a2 = jbu.a(str);
            if (a2 != null) {
                jca e = e(jcaVar, a2);
                LogUtil.a("AtmosphereInfoParseUtils", "getDataWeather() :", e.toString());
                return e;
            }
        } catch (JsonParseException unused) {
            LogUtil.b("AtmosphereInfoParseUtils", "getDataWeather() JsonParseException json parse error");
        }
        return jcaVar;
    }

    private static jca e(jca jcaVar, JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("items");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("itemsRootJson is null", new Object[0]);
            return jcaVar;
        }
        return d(jcaVar, jsonElement);
    }

    private static jca d(jca jcaVar, JsonElement jsonElement) {
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        if (asJsonArray == null || asJsonArray.isJsonNull() || !asJsonArray.isJsonArray() || asJsonArray.size() < 1) {
            LogUtil.h("itemsRoots is null", new Object[0]);
            return jcaVar;
        }
        JsonElement jsonElement2 = asJsonArray.get(0);
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("itemsRoot is null", new Object[0]);
            return jcaVar;
        }
        JsonObject asJsonObject = jsonElement2.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("items is null", new Object[0]);
            return jcaVar;
        }
        return b(jcaVar, asJsonObject);
    }

    private static jca b(jca jcaVar, JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("condition");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("conditionRoot is null", new Object[0]);
            return jcaVar;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("condition is null", new Object[0]);
            return jcaVar;
        }
        JsonElement jsonElement2 = asJsonObject.get("pressure");
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("pressureIdRoot is null", new Object[0]);
            return jcaVar;
        }
        jcaVar.d(jsonElement2.getAsFloat());
        jcaVar.b(1);
        return jcaVar;
    }
}
