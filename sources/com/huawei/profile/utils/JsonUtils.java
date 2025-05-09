package com.huawei.profile.utils;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.profile.utils.logger.DsLog;
import java.text.Normalizer;

/* loaded from: classes6.dex */
public final class JsonUtils {
    private static final String LEFT_PARENTHESIS = "{";
    private static final String TAG = "JsonUtils";

    private JsonUtils() {
    }

    public static <T> T parse(String str, Class<T> cls) {
        if (TextUtils.isEmpty(str) || cls == null) {
            DsLog.et(TAG, "Failed to parse json file, error: invalid parameter.", new Object[0]);
            return null;
        }
        try {
            return (T) new Gson().fromJson(sanitize(str), (Class) cls);
        } catch (JsonSyntaxException e) {
            DsLog.et(TAG, "Failed to parse json file, error: %s.", e.getMessage());
            return null;
        }
    }

    public static JsonElement parse(String str) {
        if (TextUtils.isEmpty(str)) {
            DsLog.et(TAG, "Failed to parse json string, error: Json is empty.", new Object[0]);
            return null;
        }
        try {
            return JsonParser.parseString(sanitize(str));
        } catch (JsonSyntaxException unused) {
            return null;
        }
    }

    public static boolean isValidJson(String str) {
        if (TextUtils.isEmpty(str)) {
            DsLog.et(TAG, "Json is empty!", new Object[0]);
            return false;
        }
        try {
            JsonParser.parseString(sanitize(str));
            return true;
        } catch (JsonSyntaxException | IllegalStateException unused) {
            return false;
        }
    }

    public static boolean isJsonObject(String str) {
        if (TextUtils.isEmpty(str)) {
            DsLog.et(TAG, "Json is empty!", new Object[0]);
            return false;
        }
        try {
            return JsonParser.parseString(sanitize(str)).isJsonObject();
        } catch (JsonSyntaxException | IllegalStateException unused) {
            return false;
        }
    }

    public static boolean isValidJsonArray(String str) {
        if (TextUtils.isEmpty(str)) {
            DsLog.et(TAG, "Failed to parse json string, error: JsonArray is empty.", new Object[0]);
            return false;
        }
        try {
            return new JsonParser().parse(sanitize(str)).getAsJsonArray().size() >= 0;
        } catch (JsonSyntaxException | IllegalStateException unused) {
            return false;
        }
    }

    public static boolean isJsonFormat(String str) {
        if (TextUtils.isEmpty(str)) {
            DsLog.et(TAG, "Json is empty! ", new Object[0]);
            return false;
        }
        String normalize = Normalizer.normalize(str, Normalizer.Form.NFKC);
        return parse(sanitize(normalize)) != null && normalize.startsWith(LEFT_PARENTHESIS);
    }

    public static String sanitize(String str) {
        return JsonSanitizer.sanitize(str);
    }
}
