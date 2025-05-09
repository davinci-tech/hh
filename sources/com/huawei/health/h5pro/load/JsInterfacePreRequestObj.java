package com.huawei.health.h5pro.load;

import com.alipay.sdk.m.p.e;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.load.expression.ExpressionParser;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.operation.ble.BleConstants;
import java.util.Date;
import java.util.List;

/* loaded from: classes3.dex */
public class JsInterfacePreRequestObj {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("isAsync")
    public boolean f2446a;

    @SerializedName("moduleName")
    public String b;

    @SerializedName(e.n)
    public JsonElement c;

    @SerializedName(BleConstants.KEY_PATH)
    public String d;

    @SerializedName("methodName")
    public String e;

    @SerializedName("type")
    public int f;

    @SerializedName("requestKey")
    public String i;

    @SerializedName("platformList")
    public List<String> h = null;
    public long j = new Date().getTime();

    public void setType(int i) {
        this.f = i;
    }

    public void setRequestKey(String str) {
        this.i = str;
    }

    public void setPlatformList(List<String> list) {
        this.h = list;
    }

    public void setPath(String str) {
        this.d = str;
    }

    public void setParams(JsonElement jsonElement) {
        this.c = jsonElement;
    }

    public void setModuleName(String str) {
        this.b = str;
    }

    public void setMethodName(String str) {
        this.e = str;
    }

    public void setAsync(boolean z) {
        this.f2446a = z;
    }

    public boolean isAsync() {
        return this.f2446a;
    }

    public int getType() {
        return this.f;
    }

    public long getRequestTime() {
        return this.j;
    }

    public String getRequestKey() {
        return this.i;
    }

    public List<String> getPlatformList() {
        return this.h;
    }

    public String getPath() {
        return this.d;
    }

    public String getParamsStr(H5ProInstance h5ProInstance) {
        JsonElement jsonElement = this.c;
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return "";
        }
        if (this.c.isJsonObject()) {
            JsonObject deepCopy = this.c.getAsJsonObject().deepCopy();
            a(h5ProInstance, deepCopy);
            return deepCopy.toString();
        }
        if (!this.c.isJsonPrimitive()) {
            return "";
        }
        JsonPrimitive asJsonPrimitive = this.c.getAsJsonPrimitive();
        return asJsonPrimitive.isString() ? asJsonPrimitive.getAsString() : "";
    }

    public JsonElement getParams() {
        return this.c;
    }

    public String getModuleName() {
        return this.b;
    }

    public String getMethodName() {
        return this.e;
    }

    private void a(H5ProInstance h5ProInstance, JsonObject jsonObject) {
        for (String str : jsonObject.keySet()) {
            JsonElement jsonElement = jsonObject.get(str);
            if (jsonElement.isJsonObject()) {
                a(h5ProInstance, jsonElement.getAsJsonObject());
            } else if (jsonElement.isJsonArray()) {
                JsonArray asJsonArray = jsonElement.getAsJsonArray();
                for (int i = 0; i < asJsonArray.size(); i++) {
                    JsonElement jsonElement2 = asJsonArray.get(i);
                    if (jsonElement2.isJsonPrimitive()) {
                        b(h5ProInstance, asJsonArray, jsonElement2, i);
                    }
                }
            } else if (jsonElement.isJsonPrimitive()) {
                e(h5ProInstance, jsonObject, str, jsonElement);
            }
        }
    }

    private void e(H5ProInstance h5ProInstance, JsonObject jsonObject, String str, JsonElement jsonElement) {
        String asString = jsonElement.getAsString();
        if (ExpressionParser.isMatchExpression(asString)) {
            b(jsonObject, str, ExpressionParser.parse(h5ProInstance, asString));
        }
    }

    private void b(JsonObject jsonObject, String str, Object obj) {
        if (obj instanceof Number) {
            jsonObject.addProperty(str, (Number) obj);
        } else if (obj instanceof String) {
            jsonObject.addProperty(str, (String) obj);
        } else if (obj instanceof Boolean) {
            jsonObject.addProperty(str, (Boolean) obj);
        }
    }

    private void b(H5ProInstance h5ProInstance, JsonArray jsonArray, JsonElement jsonElement, int i) {
        String asString = jsonElement.getAsString();
        if (ExpressionParser.isMatchExpression(asString)) {
            Object parse = ExpressionParser.parse(h5ProInstance, asString);
            JsonPrimitive jsonPrimitive = parse instanceof Number ? new JsonPrimitive((Number) parse) : parse instanceof String ? new JsonPrimitive((String) parse) : parse instanceof Boolean ? new JsonPrimitive((Boolean) parse) : null;
            if (jsonPrimitive != null) {
                jsonArray.set(i, jsonPrimitive);
            }
        }
    }
}
