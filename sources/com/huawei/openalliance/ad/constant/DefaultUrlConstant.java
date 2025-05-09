package com.huawei.openalliance.ad.constant;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.utils.cz;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DefaultUrlConstant {
    private static final Map<String, Integer> DEFAULT_URL_MAPS;

    public static String getUrl(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return "";
        }
        Map<String, Integer> map = DEFAULT_URL_MAPS;
        if (!map.containsKey(str) || !bz.a(context).d()) {
            return "";
        }
        if (map.containsKey(str + cz.a(context))) {
            str = str + cz.a(context);
        }
        return context.getString(map.get(str).intValue());
    }

    static {
        HashMap hashMap = new HashMap();
        DEFAULT_URL_MAPS = hashMap;
        hashMap.put("analyticsServer", Integer.valueOf(R.string._2130850993_res_0x7f0234b1));
        hashMap.put("eventServer", Integer.valueOf(R.string._2130851090_res_0x7f023512));
        hashMap.put("adxServer", Integer.valueOf(R.string._2130850989_res_0x7f0234ad));
        hashMap.put("adxServerFb", Integer.valueOf(R.string._2130850990_res_0x7f0234ae));
        hashMap.put("configServer", Integer.valueOf(R.string._2130851047_res_0x7f0234e7));
        hashMap.put("dnkeeperServer", Integer.valueOf(R.string._2130851074_res_0x7f023502));
        hashMap.put("amsServer", Integer.valueOf(R.string._2130851137_res_0x7f023541));
        hashMap.put("appDataServer", Integer.valueOf(R.string._2130850995_res_0x7f0234b3));
        hashMap.put("consentConfigServer", Integer.valueOf(R.string._2130851051_res_0x7f0234eb));
        hashMap.put("appInsListConfigServer", Integer.valueOf(R.string._2130850997_res_0x7f0234b5));
        hashMap.put("permissionServer", Integer.valueOf(R.string._2130851121_res_0x7f023531));
        hashMap.put("h5Server", Integer.valueOf(R.string._2130850970_res_0x7f02349a));
        hashMap.put(UrlConstant.BASE_COMPLAIN_H5_URL, Integer.valueOf(R.string._2130850969_res_0x7f023499));
        hashMap.put("analyticsServerTv", Integer.valueOf(R.string._2130850994_res_0x7f0234b2));
        hashMap.put("adxServerTv", Integer.valueOf(R.string._2130850992_res_0x7f0234b0));
        hashMap.put("adxServerFbTv", Integer.valueOf(R.string._2130850991_res_0x7f0234af));
        hashMap.put("eventServerTv", Integer.valueOf(R.string._2130851091_res_0x7f023513));
        hashMap.put("configServerTv", Integer.valueOf(R.string._2130851048_res_0x7f0234e8));
        hashMap.put("amsServerTv", Integer.valueOf(R.string._2130851138_res_0x7f023542));
        hashMap.put("h5ServerTv", Integer.valueOf(R.string._2130850971_res_0x7f02349b));
    }
}
