package defpackage;

import android.text.TextUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTide;
import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTideStationBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jbq {
    public static List<HagTideStationBean> a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HagTideParseUtils", "weather info is empty");
            return null;
        }
        try {
            JsonObject a2 = jbu.a(str);
            if (a2 == null) {
                LogUtil.h("HagTideParseUtils", "getTideInfo() templateContent is null");
                return null;
            }
            JsonElement jsonElement = a2.get("items");
            if (jsonElement != null && !jsonElement.isJsonNull()) {
                JsonArray asJsonArray = jsonElement.getAsJsonArray();
                if (asJsonArray != null && !asJsonArray.isJsonNull() && asJsonArray.isJsonArray() && asJsonArray.size() >= 1) {
                    JsonElement jsonElement2 = asJsonArray.get(0);
                    if (jsonElement2 != null && !jsonElement2.isJsonNull()) {
                        JsonObject asJsonObject = jsonElement2.getAsJsonObject();
                        if (asJsonObject != null && !asJsonObject.isJsonNull() && asJsonObject.isJsonObject()) {
                            return d(asJsonObject);
                        }
                        LogUtil.h("HagTideParseUtils", "items is null or is not object");
                        return null;
                    }
                    LogUtil.h("HagTideParseUtils", "itemsRoot is null");
                    return null;
                }
                LogUtil.h("HagTideParseUtils", "itemsRoots is null");
                return null;
            }
            LogUtil.h("HagTideParseUtils", "itemsRootJson is null");
            return null;
        } catch (JsonParseException unused) {
            LogUtil.b("HagTideParseUtils", "getTideInfo() JsonParseException");
            return null;
        } catch (IllegalStateException unused2) {
            LogUtil.b("HagTideParseUtils", "getTideInfo() IllegalStateException");
            return null;
        }
    }

    private static List<HagTideStationBean> d(JsonObject jsonObject) {
        JsonArray asJsonArray;
        ArrayList arrayList = new ArrayList(16);
        JsonArray asJsonArray2 = jsonObject.getAsJsonArray("forecasts");
        if (asJsonArray2 == null || asJsonArray2.isJsonNull() || asJsonArray2.size() <= 0) {
            LogUtil.h("HagTideParseUtils", "parseTideListJson() forecasts is null or is isJsonNull or size less 0");
            return arrayList;
        }
        int i = 0;
        while (true) {
            if (i >= asJsonArray2.size()) {
                break;
            }
            if (arrayList.size() >= 5) {
                LogUtil.h("HagTideParseUtils", "parseTideListJson() tideStationBeans size >= 5");
                break;
            }
            HagTideStationBean hagTideStationBean = new HagTideStationBean();
            JsonElement jsonElement = asJsonArray2.get(i);
            if (jsonElement == null || jsonElement.isJsonNull()) {
                LogUtil.h("HagTideParseUtils", "parseTideListJson() jsonElement is null or jsonElement value is null");
            } else {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                if (asJsonObject != null && !asJsonObject.isJsonNull() && asJsonObject.isJsonObject()) {
                    JsonElement jsonElement2 = asJsonObject.get("stationName");
                    if (jsonElement2 != null) {
                        hagTideStationBean.setName(jsonElement2.getAsString());
                    }
                    JsonElement jsonElement3 = asJsonObject.get("tides");
                    if (jsonElement3 != null && (asJsonArray = jsonElement3.getAsJsonArray()) != null && !asJsonArray.isJsonNull() && asJsonArray.size() > 0) {
                        hagTideStationBean.setHagTides(c(asJsonArray));
                    }
                    arrayList.add(hagTideStationBean);
                }
            }
            i++;
        }
        return arrayList;
    }

    private static List<HagTide> c(JsonArray jsonArray) {
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < jsonArray.size(); i++) {
            HagTide hagTide = new HagTide();
            JsonElement jsonElement = jsonArray.get(i);
            if (jsonElement == null) {
                LogUtil.h("HagTideParseUtils", "parseHagTide() tideRoots is null");
            } else {
                JsonObject asJsonObject = jsonElement.getAsJsonObject();
                if (asJsonObject == null || !asJsonObject.isJsonObject()) {
                    LogUtil.h("HagTideParseUtils", "parseHagTide() tideRoot is null or tideRoot is not json object");
                } else {
                    JsonElement jsonElement2 = asJsonObject.get("type");
                    if (jsonElement2 != null) {
                        String asString = jsonElement2.getAsString();
                        if ("Low".equalsIgnoreCase(asString)) {
                            hagTide.setTideType(1);
                        } else if ("High".equalsIgnoreCase(asString)) {
                            hagTide.setTideType(2);
                        } else {
                            hagTide.setTideType(0);
                        }
                    } else {
                        hagTide.setTideType(0);
                    }
                    JsonElement jsonElement3 = asJsonObject.get("epochTime");
                    if (jsonElement3 != null) {
                        hagTide.setTideTime(jsonElement3.getAsLong() / 1000);
                    }
                    JsonElement jsonElement4 = asJsonObject.get("height");
                    if (jsonElement4 != null) {
                        hagTide.setTideHeight(jsonElement4.getAsInt());
                    } else {
                        hagTide.setTideHeight(-32768);
                    }
                    arrayList.add(hagTide);
                }
            }
        }
        return arrayList;
    }
}
