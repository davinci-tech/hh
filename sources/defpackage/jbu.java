package defpackage;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.tencent.open.SocialConstants;
import health.compact.a.GRSManager;
import health.compact.a.HEXUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes5.dex */
public class jbu {
    public static int d(float f) {
        return (int) (f >= 0.0f ? f + 0.5f : f - 0.5f);
    }

    public static JsonObject a(String str) {
        try {
            JsonElement parse = new JsonParser().parse(str);
            if (parse == null || parse.isJsonNull()) {
                return null;
            }
            JsonObject asJsonObject = parse.getAsJsonObject();
            if (asJsonObject != null && !asJsonObject.isJsonNull()) {
                String asString = asJsonObject.get("code").getAsString();
                String asString2 = asJsonObject.get(SocialConstants.PARAM_APP_DESC).getAsString();
                if (!TextUtils.isEmpty(asString) && !TextUtils.isEmpty(asString2)) {
                    if ("0".equals(asString) && "Success".equals(asString2)) {
                        JsonArray a2 = a(asJsonObject);
                        if (a2 == null) {
                            LogUtil.h("HagWeatherParseUtils", "commandsRoots is null");
                            return null;
                        }
                        return b(a2);
                    }
                    LogUtil.h("HagWeatherParseUtils", "weather info error:", asString2);
                    return null;
                }
                LogUtil.h("HagWeatherParseUtils", "code is null or description is null");
                return null;
            }
            LogUtil.h("HagWeatherParseUtils", "code is null or description is null");
            return null;
        } catch (JsonParseException unused) {
            LogUtil.b("HagWeatherParseUtils", "initJsonParser() JsonParseException");
            return null;
        } catch (IllegalStateException unused2) {
            LogUtil.b("HagWeatherParseUtils", "initJsonParser() IllegalStateException");
            return null;
        }
    }

    public static int c(JsonElement jsonElement) {
        int d = (jsonElement == null || jsonElement.isJsonNull()) ? -99 : d(jsonElement.getAsFloat());
        LogUtil.a("HagWeatherParseUtils", "parseTemperature() tempValue:", Integer.valueOf(d));
        return d;
    }

    public static JsonArray c(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("dailys");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "dailiesRoot is null");
            return null;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("HagWeatherParseUtils", "dailies is null or is not object");
            return null;
        }
        JsonElement jsonElement2 = asJsonObject.get("dailyweathers");
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "dailyWeathersRoot is null");
            return null;
        }
        JsonArray asJsonArray = jsonElement2.getAsJsonArray();
        if (asJsonArray.size() >= 1 && asJsonArray.isJsonArray() && !asJsonArray.isJsonNull()) {
            return asJsonArray;
        }
        LogUtil.h("HagWeatherParseUtils", "dailyWeathersList is less 1");
        return null;
    }

    private static JsonArray a(JsonObject jsonObject) {
        JsonArray d = d(jsonObject);
        if (d == null) {
            LogUtil.h("HagWeatherParseUtils", "abilityRoots is null");
            return null;
        }
        JsonElement jsonElement = d.get(0);
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "abilityJson is null");
            return null;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("HagWeatherParseUtils", "ability is null");
            return null;
        }
        JsonElement jsonElement2 = asJsonObject.get("commands");
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "commandRootJson is null");
            return null;
        }
        JsonArray asJsonArray = jsonElement2.getAsJsonArray();
        if (asJsonArray != null && !asJsonArray.isJsonNull() && asJsonArray.isJsonArray() && asJsonArray.size() >= 1) {
            return asJsonArray;
        }
        LogUtil.h("HagWeatherParseUtils", "commandRoots is null");
        return null;
    }

    private static JsonArray d(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("resultIntents");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "resultIntentRootJson is null");
            return null;
        }
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        if (asJsonArray == null || asJsonArray.isJsonNull() || !asJsonArray.isJsonArray() || asJsonArray.size() < 1) {
            LogUtil.h("HagWeatherParseUtils", "resultIntentRoots is null");
            return null;
        }
        JsonElement jsonElement2 = asJsonArray.get(0);
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "resultIntentJson is null");
            return null;
        }
        JsonObject asJsonObject = jsonElement2.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("HagWeatherParseUtils", "resultIntent is null");
            return null;
        }
        JsonElement jsonElement3 = asJsonObject.get("abilities");
        if (jsonElement3 == null || jsonElement3.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "abilityRootJson is null");
            return null;
        }
        JsonArray asJsonArray2 = jsonElement3.getAsJsonArray();
        if (asJsonArray2 != null && !asJsonArray2.isJsonNull() && asJsonArray2.isJsonArray() && asJsonArray2.size() >= 1) {
            return asJsonArray2;
        }
        LogUtil.h("HagWeatherParseUtils", "abilityRoots is null");
        return null;
    }

    private static JsonObject b(JsonArray jsonArray) {
        JsonElement jsonElement = jsonArray.get(0);
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "commandsRoot is null");
            return null;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("HagWeatherParseUtils", "commands is null or is not object");
            return null;
        }
        JsonElement jsonElement2 = asJsonObject.get("body");
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "bodyRoot is null");
            return null;
        }
        JsonObject asJsonObject2 = jsonElement2.getAsJsonObject();
        if (asJsonObject2 == null || asJsonObject2.isJsonNull() || !asJsonObject2.isJsonObject()) {
            LogUtil.h("HagWeatherParseUtils", "body is null or is not object");
            return null;
        }
        JsonElement jsonElement3 = asJsonObject2.get("templateContent");
        if (jsonElement3 == null || jsonElement3.isJsonNull()) {
            LogUtil.h("HagWeatherParseUtils", "templateContentRoot is null");
            return null;
        }
        JsonObject asJsonObject3 = jsonElement3.getAsJsonObject();
        if (asJsonObject3 != null && !asJsonObject3.isJsonNull() && asJsonObject3.isJsonObject()) {
            return asJsonObject3;
        }
        LogUtil.h("HagWeatherParseUtils", "templateContent is null or is not object");
        return null;
    }

    public static int d(String str) {
        if ("New".equalsIgnoreCase(str)) {
            return 1;
        }
        if ("WaxingCrescent".equalsIgnoreCase(str)) {
            return 2;
        }
        if ("First".equalsIgnoreCase(str)) {
            return 3;
        }
        if ("WaxingGibbous".equalsIgnoreCase(str)) {
            return 4;
        }
        if ("Full".equalsIgnoreCase(str)) {
            return 5;
        }
        if ("WaningGibbous".equalsIgnoreCase(str)) {
            return 6;
        }
        if ("Last".equalsIgnoreCase(str)) {
            return 7;
        }
        return "WaningCrescent".equalsIgnoreCase(str) ? 8 : 0;
    }

    public static boolean a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(new Date()).equals(simpleDateFormat.format(new Date(j)));
    }

    public static String e() {
        return GRSManager.a(BaseApplication.getContext()).getCommonCountryCode();
    }

    public static String d() {
        TimeZone timeZone = TimeZone.getDefault();
        return timeZone.getDisplayName(timeZone.useDaylightTime() && timeZone.inDaylightTime(new Date()), 0, Locale.ROOT);
    }

    public static String a() {
        Resources resources = BaseApplication.getContext().getResources();
        if (resources == null) {
            return e("en");
        }
        Configuration configuration = resources.getConfiguration();
        if (configuration == null) {
            return e("en");
        }
        Locale locale = configuration.locale;
        if (locale == null) {
            return e("en");
        }
        return e(locale.getLanguage());
    }

    public static String e(String str) {
        return b(str) + "_" + e();
    }

    private static String b(String str) {
        if ("iw".equalsIgnoreCase(str)) {
            return "he";
        }
        if ("in".equalsIgnoreCase(str)) {
            return "id";
        }
        LogUtil.a("HagWeatherParseUtils", "convertLanguage no conversion required");
        return str;
    }

    public static boolean c() {
        return "CN".equalsIgnoreCase(e());
    }

    public static String c(String str) {
        if (str != null && str.length() > 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
                return HEXUtils.a(messageDigest.digest());
            } catch (NoSuchAlgorithmException unused) {
                LogUtil.b("HagWeatherParseUtils", "sha512 NoSuchAlgorithmException");
            }
        }
        return "";
    }

    public static String b() {
        return "0";
    }
}
