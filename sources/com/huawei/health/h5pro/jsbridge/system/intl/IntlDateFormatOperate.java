package com.huawei.health.h5pro.jsbridge.system.intl;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.ArrayMap;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class IntlDateFormatOperate {
    public static final ArrayMap<String, Integer> b = new ArrayMap<>();

    public JSONObject formatDateTime(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            long optLong = jSONObject2.optLong("millis");
            String upperCase = jSONObject2.optString(ParamConstants.Param.FLAGS).toUpperCase(Locale.ENGLISH);
            b();
            int i = 0;
            for (Map.Entry<String, Integer> entry : b.entrySet()) {
                if (upperCase.contains(entry.getKey())) {
                    i |= entry.getValue().intValue();
                }
            }
            jSONObject.put("dateTimeStr", DateUtils.formatDateTime(context, optLong, i));
        } catch (JSONException e) {
            LogUtil.e("H5PRO_DateFormatUtils", "formatDateTime: " + e.getMessage());
        }
        return jSONObject;
    }

    public JSONObject formatDateRange(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            long optLong = jSONObject2.optLong("startMillis");
            long optLong2 = jSONObject2.optLong("endMillis");
            String upperCase = jSONObject2.optString(ParamConstants.Param.FLAGS).toUpperCase(Locale.ENGLISH);
            b();
            int i = 0;
            for (Map.Entry<String, Integer> entry : b.entrySet()) {
                if (upperCase.contains(entry.getKey())) {
                    i |= entry.getValue().intValue();
                }
            }
            jSONObject.put("dateTimeStr", DateUtils.formatDateRange(context, optLong, optLong2, i));
        } catch (JSONException e) {
            LogUtil.e("H5PRO_DateFormatUtils", "formatDateRange: " + e.getMessage());
        }
        return jSONObject;
    }

    private void b() {
        ArrayMap<String, Integer> arrayMap = b;
        if (arrayMap.isEmpty()) {
            arrayMap.put("FORMAT_12HOUR", 64);
            arrayMap.put("FORMAT_24HOUR", 128);
            arrayMap.put("FORMAT_ABBREV_ALL", 524288);
            arrayMap.put("FORMAT_ABBREV_MONTH", 65536);
            arrayMap.put("FORMAT_ABBREV_RELATIVE", 262144);
            arrayMap.put("FORMAT_ABBREV_TIME", 16384);
            arrayMap.put("FORMAT_ABBREV_WEEKDAY", 32768);
            arrayMap.put("FORMAT_CAP_AMPM", 256);
            arrayMap.put("FORMAT_CAP_MIDNIGHT", 4096);
            arrayMap.put("FORMAT_CAP_NOON", 1024);
            arrayMap.put("FORMAT_CAP_NOON_MIDNIGHT", 5120);
            arrayMap.put("FORMAT_NO_MIDNIGHT", 2048);
            arrayMap.put("FORMAT_NO_MONTH_DAY", 32);
            arrayMap.put("FORMAT_NO_NOON", 512);
            arrayMap.put("FORMAT_NO_NOON_MIDNIGHT", Integer.valueOf(HealthData.ALTITUDE));
            arrayMap.put("FORMAT_NO_YEAR", 8);
            arrayMap.put("FORMAT_NUMERIC_DATE", 131072);
            arrayMap.put("FORMAT_SHOW_DATE", 16);
            arrayMap.put("FORMAT_SHOW_TIME", 1);
            arrayMap.put("FORMAT_SHOW_WEEKDAY", 2);
            arrayMap.put("FORMAT_SHOW_YEAR", 4);
            arrayMap.put("FORMAT_UTC", 8192);
        }
    }
}
