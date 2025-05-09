package defpackage;

import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.amap.api.services.district.DistrictSearchQuery;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.huawei.hwcloudmodel.hwwear.hag.model.outdoor.FutureWeatherSunMoonData;
import com.huawei.hwcloudmodel.model.push.AlertWeather;
import com.huawei.hwcloudmodel.model.push.WeatherForecastDay;
import com.huawei.hwcloudmodel.model.push.WeatherForecastHour;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jbn {
    public static jbz c(String str) {
        LogUtil.a("DataWeatherParseUtils", "getDataWeather():", str);
        jbz jbzVar = new jbz();
        jbzVar.b(-99);
        jbzVar.c(-99);
        jbzVar.e(-99);
        if (TextUtils.isEmpty(str) || String.valueOf(601).equals(str)) {
            LogUtil.h("DataWeatherParseUtils", "weather info is empty or unknown error type");
            jbzVar.o(601);
            return jbzVar;
        }
        if (String.valueOf(503).equals(str)) {
            LogUtil.h("DataWeatherParseUtils", "weather info SERVICE_ERROR");
            jbzVar.o(503);
            return jbzVar;
        }
        if (String.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR).equals(str)) {
            LogUtil.h("DataWeatherParseUtils", "weather info RESPONSE_EMPTY_ERROR_TYPE");
            jbzVar.o(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR);
            return jbzVar;
        }
        if (String.valueOf(TypedValues.MotionType.TYPE_EASING).equals(str)) {
            LogUtil.h("DataWeatherParseUtils", "weather info NO_DATA_RETURNED_ERROR_TYPE");
            jbzVar.o(TypedValues.MotionType.TYPE_EASING);
            return jbzVar;
        }
        if (String.valueOf(600).equals(str)) {
            LogUtil.h("DataWeatherParseUtils", "weather info IO_ERROR_TYPE");
            jbzVar.o(600);
            return jbzVar;
        }
        try {
            JsonObject a2 = jbu.a(str);
            if (a2 == null) {
                LogUtil.h("DataWeatherParseUtils", "getDataWeather() commandsRoots is null");
                jbzVar.o(602);
                return jbzVar;
            }
            return e(jbzVar, a2);
        } catch (JsonParseException unused) {
            LogUtil.b("DataWeatherParseUtils", "getDataWeather() JsonParseException");
            jbzVar.o(602);
            return jbzVar;
        } catch (IllegalStateException unused2) {
            LogUtil.b("DataWeatherParseUtils", "getDataWeather() IllegalStateException");
            jbzVar.o(602);
            return jbzVar;
        }
    }

    private static jbz e(jbz jbzVar, JsonObject jsonObject) {
        jbzVar.e(s(jsonObject));
        LogUtil.a("DataWeatherParseUtils", "parseCommandsRootJson() source:", jbzVar.i());
        return a(jbzVar, jsonObject);
    }

    private static String s(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("dataSource");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "dataSourceRootJson is null");
            return "";
        }
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        if (asJsonArray == null || asJsonArray.isJsonNull() || !asJsonArray.isJsonArray() || asJsonArray.size() < 1) {
            LogUtil.h("DataWeatherParseUtils", "dataSourceRoots is null");
            return "";
        }
        JsonElement jsonElement2 = asJsonArray.get(0);
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "dataSourceRoot is null");
            return "";
        }
        JsonObject asJsonObject = jsonElement2.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("DataWeatherParseUtils", "dataSource is null");
            return "";
        }
        JsonElement jsonElement3 = asJsonObject.get("name");
        if (jsonElement3 == null || jsonElement3.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "dataSourceName is null");
            return "";
        }
        String asString = jsonElement3.getAsString();
        if (TextUtils.isEmpty(asString)) {
            LogUtil.h("DataWeatherParseUtils", "dataSourceName name is empty");
            return "";
        }
        LogUtil.a("DataWeatherParseUtils", "parseDataSource() dataSourceName:", asString);
        return asString;
    }

    private static jbz a(jbz jbzVar, JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("items");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "itemsRootJson is null");
            jbzVar.o(602);
            return jbzVar;
        }
        JsonArray asJsonArray = jsonElement.getAsJsonArray();
        if (asJsonArray == null || asJsonArray.isJsonNull() || !asJsonArray.isJsonArray()) {
            LogUtil.h("DataWeatherParseUtils", "itemsRoots is null");
            jbzVar.o(602);
            return jbzVar;
        }
        if (asJsonArray.size() < 1) {
            LogUtil.h("DataWeatherParseUtils", "itemsRoots is empty");
            jbzVar.o(TypedValues.MotionType.TYPE_EASING);
            return jbzVar;
        }
        JsonElement jsonElement2 = asJsonArray.get(0);
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "itemsRoot is null");
            jbzVar.o(602);
            return jbzVar;
        }
        JsonObject asJsonObject = jsonElement2.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("DataWeatherParseUtils", "items is null or is not object");
            jbzVar.o(602);
            return jbzVar;
        }
        return c(jbzVar, asJsonObject);
    }

    private static jbz c(jbz jbzVar, JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("condition");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "conditionRoot is null");
            jbzVar.o(602);
            return jbzVar;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
            LogUtil.h("DataWeatherParseUtils", "condition is null or is not object");
            jbzVar.o(602);
            return jbzVar;
        }
        int k = k(asJsonObject);
        int a2 = a(asJsonObject);
        int o = o(asJsonObject);
        int n = n(asJsonObject);
        int l = l(asJsonObject);
        String d = d(asJsonObject);
        int r = r(asJsonObject);
        int j = j(asJsonObject);
        jbzVar.i(k);
        jbzVar.j(a2);
        jbzVar.k(o);
        jbzVar.g(n);
        jbzVar.h(l);
        jbzVar.d(d);
        jbzVar.n(r);
        jbzVar.l(j);
        LogUtil.a("DataWeatherParseUtils", "parseWeatherIdJson() configWeather:", Integer.valueOf(jbzVar.v()), ",cnWeatherIcon:", Integer.valueOf(jbzVar.d()), ",uVIndex:", Integer.valueOf(jbzVar.f()), " windDirection:", Integer.valueOf(n), " windLevel:", Integer.valueOf(l));
        return b(jbzVar, jsonObject, asJsonObject);
    }

    private static jbz b(jbz jbzVar, JsonObject jsonObject, JsonObject jsonObject2) {
        JsonElement jsonElement = jsonObject2.get("updatetime");
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            jbzVar.c(jsonElement.getAsLong());
        }
        LogUtil.a("DataWeatherParseUtils", "parseTemperatureJson():", Long.valueOf(jbzVar.u()));
        JsonElement jsonElement2 = jsonObject2.get(HwExerciseConstants.JSON_NAME_WORKOUT_TEMPERATURE);
        if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
            jbzVar.b(jbu.d(jsonElement2.getAsFloat()));
        }
        LogUtil.a("DataWeatherParseUtils", "parseTemperatureJson() configCurrentTemperature:", Integer.valueOf(jbzVar.k()));
        JsonElement jsonElement3 = jsonObject.get("alert");
        if (jsonElement3 != null && !jsonElement3.isJsonNull()) {
            JsonArray asJsonArray = jsonElement3.getAsJsonArray();
            if (asJsonArray == null || asJsonArray.isJsonNull() || !asJsonArray.isJsonArray() || asJsonArray.size() < 1) {
                LogUtil.h("DataWeatherParseUtils", "parseAlertWeathers() parseAlertWeathers is null or is not array and or size less 1");
            } else {
                jbzVar.d(e(asJsonArray));
            }
        }
        JsonElement jsonElement4 = jsonObject.get(DistrictSearchQuery.KEYWORDS_CITY);
        if (jsonElement4 != null && !jsonElement4.isJsonNull()) {
            jbzVar.a(p(jsonElement4.getAsJsonObject()));
        }
        LogUtil.a("DataWeatherParseUtils", "parseTemperatureJson() configLocationName:", jbzVar.q());
        return d(jbzVar, jsonObject);
    }

    private static List<AlertWeather> e(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement == null || jsonElement.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseAlertWeathers() alertWeatherRoot is null");
                arrayList.clear();
                break;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject == null || asJsonObject.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseAlertWeathers() alertWeatherObject is null");
                arrayList.clear();
                return arrayList;
            }
            if (!asJsonObject.isJsonObject()) {
                LogUtil.h("DataWeatherParseUtils", "parseAlertWeathers() alertWeatherObject is null");
                arrayList.clear();
                return arrayList;
            }
            String b = b(asJsonObject);
            String c = c(asJsonObject);
            int i2 = i(asJsonObject);
            String g = g(asJsonObject);
            int h = h(asJsonObject);
            String m = m(asJsonObject);
            String e = e(asJsonObject);
            long f = f(asJsonObject);
            AlertWeather alertWeather = new AlertWeather();
            alertWeather.setAlertTitle(b);
            alertWeather.setAlertAreaName(c);
            alertWeather.setAlertLevel(i2);
            alertWeather.setAlertLevelName(g);
            alertWeather.setAlertType(h);
            alertWeather.setAlertTypeName(m);
            alertWeather.setAlertContent(e);
            alertWeather.setAlertPublicTime(f);
            arrayList.add(alertWeather);
        }
        return arrayList;
    }

    private static String p(JsonObject jsonObject) {
        if (jsonObject == null || jsonObject.isJsonNull() || !jsonObject.isJsonObject()) {
            LogUtil.h("DataWeatherParseUtils", "city is null");
            return "";
        }
        JsonElement jsonElement = jsonObject.get("name");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "cityNameRoot is null");
            return "";
        }
        String asString = jsonElement.getAsString();
        if (!TextUtils.isEmpty(asString)) {
            LogUtil.a("DataWeatherParseUtils", "parseCityName():", asString);
            return asString;
        }
        JsonElement jsonElement2 = jsonObject.get("englishCityName");
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "cityEnglishNameRoot is null");
            return "";
        }
        String asString2 = jsonElement2.getAsString();
        if (TextUtils.isEmpty(asString2)) {
            LogUtil.h("DataWeatherParseUtils", "cityEnglishNameRoot is null");
            return "";
        }
        LogUtil.a("DataWeatherParseUtils", "parseCityName():", asString2);
        return asString2;
    }

    private static jbz d(jbz jbzVar, JsonObject jsonObject) {
        jbzVar.a(t(jsonObject));
        int x = x(jsonObject);
        jbzVar.f(x);
        LogUtil.a("DataWeatherParseUtils", "parseAqiWeatherJson() configPm25:", Integer.valueOf(jbzVar.r()), "configAqi:", Integer.valueOf(jbzVar.m()));
        if (x > 200) {
            jbzVar.i(53);
            LogUtil.a("DataWeatherParseUtils", "parseAqiWeatherJson() configWeather:", Integer.valueOf(jbzVar.v()));
        }
        return b(jbzVar, jsonObject);
    }

    private static FutureWeatherSunMoonData w(JsonObject jsonObject) {
        FutureWeatherSunMoonData futureWeatherSunMoonData = new FutureWeatherSunMoonData();
        futureWeatherSunMoonData.setSunriseTime(d(jsonObject, "sunRise"));
        futureWeatherSunMoonData.setSunsetTime(d(jsonObject, "sunSet"));
        futureWeatherSunMoonData.setMoonRise(d(jsonObject, "moonRise"));
        futureWeatherSunMoonData.setMoonSet(d(jsonObject, "moonSet"));
        JsonElement jsonElement = jsonObject.get("moonphase");
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            futureWeatherSunMoonData.setMoonPhase(jbu.d(jsonElement.getAsString()));
        } else {
            futureWeatherSunMoonData.setMoonPhase(jbu.d(""));
        }
        LogUtil.a("DataWeatherParseUtils", "parseSunriseSunset() sunriseSunset:", futureWeatherSunMoonData.toString());
        return futureWeatherSunMoonData;
    }

    private static long d(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || jsonElement.isJsonNull()) {
            return 0L;
        }
        return jsonElement.getAsLong() / 1000;
    }

    private static int t(JsonObject jsonObject) {
        int i = -1;
        try {
            JsonElement jsonElement = jsonObject.get("aqi");
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                if (asJsonObject != null && !asJsonObject.isJsonNull() && asJsonObject.isJsonObject()) {
                    JsonElement jsonElement2 = asJsonObject.get("aqivalue");
                    if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                        int asInt = jsonElement2.getAsInt();
                        if (asInt >= 0 && asInt <= 500) {
                            return asInt;
                        }
                        try {
                            LogUtil.h("DataWeatherParseUtils", "parseAqi() aqi:", Integer.valueOf(asInt));
                            return -1;
                        } catch (JsonParseException unused) {
                            i = asInt;
                            LogUtil.b("DataWeatherParseUtils", "parseAqi() JsonParseException");
                            return i;
                        } catch (IllegalStateException unused2) {
                            i = asInt;
                            LogUtil.b("DataWeatherParseUtils", "parseAqi() IllegalStateException");
                            return i;
                        }
                    }
                    LogUtil.h("DataWeatherParseUtils", "parseAqi() aqiValueElement is null");
                    return -1;
                }
                LogUtil.h("DataWeatherParseUtils", "parseAqi() aqiElementBean is null or is not object");
                return -1;
            }
            LogUtil.h("DataWeatherParseUtils", "parseAqi() aqiElement is null");
            return -1;
        } catch (JsonParseException unused3) {
        } catch (IllegalStateException unused4) {
        }
    }

    private static int x(JsonObject jsonObject) {
        try {
            JsonElement jsonElement = jsonObject.get("aqi");
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                if (asJsonObject != null && !asJsonObject.isJsonNull() && asJsonObject.isJsonObject()) {
                    JsonElement jsonElement2 = asJsonObject.get("pm25");
                    if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                        return jsonElement2.getAsInt();
                    }
                    LogUtil.h("DataWeatherParseUtils", "parsePm() pm25Root is null");
                    return -1;
                }
                LogUtil.h("DataWeatherParseUtils", "parsePm() aqiElementBean is null or is not object");
                return -1;
            }
            LogUtil.h("DataWeatherParseUtils", "parsePm() aqiElement is null");
            return -1;
        } catch (JsonParseException unused) {
            LogUtil.b("DataWeatherParseUtils", "parsePm() JsonParseException");
            return -1;
        } catch (IllegalStateException unused2) {
            LogUtil.b("DataWeatherParseUtils", "parsePm() IllegalStateException");
            return -1;
        }
    }

    private static jbz b(jbz jbzVar, JsonObject jsonObject) {
        JsonArray c = jbu.c(jsonObject);
        if (c != null) {
            int i = 0;
            while (true) {
                if (i >= c.size()) {
                    break;
                }
                JsonElement jsonElement = c.get(i);
                if (jsonElement == null || jsonElement.isJsonNull()) {
                    LogUtil.h("DataWeatherParseUtils", "dailyWeathersItemRoot is null");
                } else {
                    JsonObject asJsonObject = jsonElement.getAsJsonObject();
                    if (asJsonObject == null || asJsonObject.isJsonNull() || !asJsonObject.isJsonObject()) {
                        LogUtil.h("DataWeatherParseUtils", "dailyWeathersItem is null or dailyWeathersItem is null");
                    } else {
                        JsonElement jsonElement2 = asJsonObject.get("publictime");
                        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
                            LogUtil.h("DataWeatherParseUtils", "publicTimeRoot is null");
                        } else {
                            long asLong = jsonElement2.getAsLong();
                            if (jbu.a(asLong)) {
                                jbzVar.d((int) (asLong / 1000));
                                JsonElement jsonElement3 = asJsonObject.get("maxtemp");
                                JsonElement jsonElement4 = asJsonObject.get("mintemp");
                                int c2 = jbu.c(jsonElement3);
                                int c3 = jbu.c(jsonElement4);
                                if (c3 > c2) {
                                    LogUtil.h("DataWeatherParseUtils", "maxConvertTempValue less than minConvertTempValue");
                                } else {
                                    int k = jbzVar.k();
                                    if (k > c2) {
                                        c2 = k;
                                    }
                                    if (k < c3) {
                                        c3 = k;
                                    }
                                    jbzVar.e(c2);
                                    jbzVar.c(c3);
                                }
                            }
                        }
                    }
                }
                i++;
            }
        }
        return e(jbzVar, jsonObject, c);
    }

    private static jbz e(jbz jbzVar, JsonObject jsonObject, JsonArray jsonArray) {
        if (jsonArray == null) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() dailyWeathersList is null");
            jbzVar.o(602);
            return jbzVar;
        }
        List<WeatherForecastDay> a2 = a(jsonArray);
        if (a2.isEmpty()) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() forecastDailies is empty");
            jbzVar.o(602);
            return jbzVar;
        }
        jbzVar.a(a2);
        LogUtil.a("DataWeatherParseUtils", "parseFutureWeatherJson() forecastDailies:", jbzVar.toString());
        JsonElement jsonElement = jsonObject.get("hourlys");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() hourlyRoot is null");
            jbzVar.o(602);
            return jbzVar;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() hourly is null");
            jbzVar.o(602);
            return jbzVar;
        }
        JsonElement jsonElement2 = asJsonObject.get("hourlyweathers");
        if (jsonElement2 == null || jsonElement2.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() hourlyWeathersRoot is null");
            jbzVar.o(602);
            return jbzVar;
        }
        JsonArray asJsonArray = jsonElement2.getAsJsonArray();
        if (asJsonArray == null || asJsonArray.isJsonNull() || !asJsonArray.isJsonArray() || asJsonArray.size() < 1) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() hourlyWeathersList is null or is not array and or size less 1");
            jbzVar.o(602);
            return jbzVar;
        }
        List<WeatherForecastHour> c = c(asJsonArray);
        if (c.isEmpty()) {
            LogUtil.h("DataWeatherParseUtils", "parseFutureWeatherJson() forecastHours is empty");
            jbzVar.o(602);
            return jbzVar;
        }
        jbzVar.b(c);
        LogUtil.a("DataWeatherParseUtils", "parseFutureWeatherJson() forecastHours:", jbzVar.toString());
        return jbzVar;
    }

    private static List<WeatherForecastDay> a(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement == null || jsonElement.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseDailiesWeathers() dailyWeathersRoot is null");
                arrayList.clear();
                return arrayList;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject == null || asJsonObject.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseDailiesWeathers() dailyWeathers is null");
                arrayList.clear();
                return arrayList;
            }
            if (!asJsonObject.isJsonObject()) {
                LogUtil.h("DataWeatherParseUtils", "parseDailiesWeathers() dailyWeathersElementListElementBean is null");
                arrayList.clear();
                return arrayList;
            }
            WeatherForecastDay q = q(asJsonObject);
            if (!q.isComplete()) {
                LogUtil.h("DataWeatherParseUtils", "parseDailiesWeathers() forecastDay is error");
                arrayList.clear();
                return arrayList;
            }
            FutureWeatherSunMoonData w = w(asJsonObject);
            if (w != null) {
                q.setFutureWeatherSunMoonData(w);
            }
            arrayList.add(q);
        }
        if (arrayList.size() < 5) {
            LogUtil.h("DataWeatherParseUtils", "parseDailiesWeathers() days is not array and or size less 5");
            arrayList.clear();
        }
        return arrayList;
    }

    private static List<WeatherForecastHour> c(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement == null || jsonElement.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseHourlyWeathers() hourlyWeatherRoot is null");
                arrayList.clear();
                break;
            }
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject == null || asJsonObject.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseHourlyWeathers() hourlyWeathers is null");
                arrayList.clear();
                return arrayList;
            }
            if (!asJsonObject.isJsonObject()) {
                LogUtil.h("DataWeatherParseUtils", "parseHourlyWeathers() hourlyWeathers is null");
                arrayList.clear();
                return arrayList;
            }
            int k = k(asJsonObject);
            int a2 = a(asJsonObject);
            if (k == -1 || a2 == -1) {
                LogUtil.h("DataWeatherParseUtils", "parseHourlyWeathers() hourlyDataRoot is -1");
                arrayList.clear();
                return arrayList;
            }
            JsonElement jsonElement2 = asJsonObject.get("date");
            JsonElement jsonElement3 = asJsonObject.get(BleConstants.TEMPERATURE);
            JsonElement jsonElement4 = asJsonObject.get("rainprobability");
            if (jsonElement2 == null || jsonElement3 == null || jsonElement2.isJsonNull() || jsonElement3.isJsonNull() || jsonElement4.isJsonNull() || jsonElement4.isJsonNull()) {
                LogUtil.h("DataWeatherParseUtils", "parseHourlyWeathers() hourlyDataRoot is null or hourlyTempRoot is null");
                arrayList.clear();
                return arrayList;
            }
            long asLong = jsonElement2.getAsLong();
            WeatherForecastHour weatherForecastHour = new WeatherForecastHour();
            weatherForecastHour.setTemperature(jbu.d(jsonElement3.getAsFloat()));
            weatherForecastHour.setTime(asLong / 1000);
            weatherForecastHour.setWeatherIcon(k);
            weatherForecastHour.setCnWeatherIcon(a2);
            weatherForecastHour.setPrecipitation(jsonElement4.getAsInt());
            LogUtil.a("DataWeatherParseUtils", "parseHourlyWeathers() forecastHour:", weatherForecastHour.toString());
            arrayList.add(weatherForecastHour);
        }
        return arrayList;
    }

    private static WeatherForecastDay q(JsonObject jsonObject) {
        WeatherForecastDay weatherForecastDay = new WeatherForecastDay();
        weatherForecastDay.setIsComplete(false);
        JsonElement jsonElement = jsonObject.get("publictime");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "parseDailyWeather() subPublicTimeRoot is null");
            return weatherForecastDay;
        }
        weatherForecastDay.setTime(jsonElement.getAsLong() / 1000);
        JsonElement jsonElement2 = jsonObject.get("maxtemp");
        JsonElement jsonElement3 = jsonObject.get("mintemp");
        JsonElement jsonElement4 = jsonObject.get("conditionDay");
        if (jsonElement2 == null || jsonElement3 == null || jsonElement4 == null) {
            LogUtil.h("DataWeatherParseUtils", "parseDailyWeather() maxTempRoot is null or minTempRoot is null or conditionDayRoot is null");
            return weatherForecastDay;
        }
        if (jsonElement2.isJsonNull() || jsonElement3.isJsonNull() || jsonElement4.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "parseDailyWeather() maxTempRoot is null or minTempRoot is null or conditionDayRoot is null");
            return weatherForecastDay;
        }
        int d = jbu.d(jsonElement2.getAsFloat());
        int d2 = jbu.d(jsonElement3.getAsFloat());
        if (d2 > d) {
            LogUtil.h("DataWeatherParseUtils", "parseDailyWeather() maxTempValue less than minTempElementValue");
            return weatherForecastDay;
        }
        weatherForecastDay.setHighestTemperature(d);
        weatherForecastDay.setLowestTemperature(d2);
        LogUtil.a("DataWeatherParseUtils", "parseDailyWeather() forecastDay:", weatherForecastDay.toString());
        return b(weatherForecastDay, jsonElement4);
    }

    private static WeatherForecastDay b(WeatherForecastDay weatherForecastDay, JsonElement jsonElement) {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (asJsonObject == null || asJsonObject.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "parseConditionDay() conditionDay is null");
            return weatherForecastDay;
        }
        int k = k(asJsonObject);
        if (k == -1) {
            LogUtil.h("DataWeatherParseUtils", "parseConditionDay() weatherIcon is -1");
            return weatherForecastDay;
        }
        int a2 = a(asJsonObject);
        if (a2 == -1) {
            LogUtil.h("DataWeatherParseUtils", "parseConditionDay() cnWeatherIcon is -1");
            return weatherForecastDay;
        }
        weatherForecastDay.setWeatherIcon(k);
        weatherForecastDay.setCnWeatherIcon(a2);
        weatherForecastDay.setIsComplete(true);
        LogUtil.a("DataWeatherParseUtils", "parseConditionDay() forecastDay:", weatherForecastDay.toString());
        return weatherForecastDay;
    }

    private static int k(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("weatherid");
        if (jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getWeatherIcon() weatherIdRoot is null");
            return -1;
        }
        Integer num = jby.d().get(String.valueOf(jsonElement.getAsInt()));
        if (num == null) {
            LogUtil.h("DataWeatherParseUtils", "getWeatherIcon() weatherId is null");
            return -1;
        }
        return num.intValue();
    }

    private static int a(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("cnweatherid");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getWeatherIcon() weatherIdRoot is null");
            return -1;
        }
        Integer num = jbt.b().get(String.valueOf(jsonElement.getAsInt()));
        if (num == null) {
            LogUtil.h("DataWeatherParseUtils", "getWeatherIcon() weatherId is null");
            return -1;
        }
        return num.intValue();
    }

    private static int o(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("uVIndex");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getUVIndex() uVIndexRoot is null");
            return -1;
        }
        int asInt = jsonElement.getAsInt();
        if (asInt >= 0 && asInt <= 15) {
            return asInt;
        }
        LogUtil.h("DataWeatherParseUtils", "getUVIndex() uVIndex is invalid value");
        return -1;
    }

    private static String d(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("humidity");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getHumidity() humidityRoot is null");
            return "";
        }
        return jsonElement.getAsString();
    }

    private static int r(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("windspeed");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getWindSpeed() windSpeedRoot is null");
            return Integer.MAX_VALUE;
        }
        return jsonElement.getAsInt();
    }

    private static int j(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("realfeel");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getRealFeel() realFeelRoot is null");
            return Integer.MAX_VALUE;
        }
        return jsonElement.getAsInt();
    }

    private static String b(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("title");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getAlertTitle() titleRoot is null");
            return "";
        }
        return jsonElement.getAsString();
    }

    private static String c(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("areaName");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getAreaName() areaNameRoot is null");
            return "";
        }
        return jsonElement.getAsString();
    }

    private static int i(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("level");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getLevel() levelRoot is null");
            return -1;
        }
        return jsonElement.getAsInt();
    }

    private static String g(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("levelName");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getLevelName() levelNameRoot is null");
            return "";
        }
        return jsonElement.getAsString();
    }

    private static int h(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("type");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getType() typeRoot is null");
            return -1;
        }
        return jsonElement.getAsInt();
    }

    private static String m(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("typeName");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getTypeName() typeNameRoot is null");
            return "";
        }
        return jsonElement.getAsString();
    }

    private static String e(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("content");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getContent() contentRoot is null");
            return "";
        }
        return jsonElement.getAsString();
    }

    private static long f(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("publictime");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getPublicTime() publicTimeRoot is null");
            return 0L;
        }
        return jsonElement.getAsLong();
    }

    private static int l(JsonObject jsonObject) {
        JsonElement jsonElement = jsonObject.get("windlevel");
        if (jsonElement == null || jsonElement.isJsonNull()) {
            LogUtil.h("DataWeatherParseUtils", "getWindLevel windLevelRoot is null");
            return -1;
        }
        int asInt = jsonElement.getAsInt();
        if (asInt >= 0 && asInt <= 17) {
            return asInt;
        }
        LogUtil.h("DataWeatherParseUtils", "getWindLevel windLevel is invalid value");
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ac, code lost:
    
        if (r11.equals(androidx.exifinterface.media.ExifInterface.LONGITUDE_EAST) == false) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int n(com.google.gson.JsonObject r11) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jbn.n(com.google.gson.JsonObject):int");
    }
}
