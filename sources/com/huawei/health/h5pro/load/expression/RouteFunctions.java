package com.huawei.health.h5pro.load.expression;

import android.text.TextUtils;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class RouteFunctions {
    @ExpressionFunction(alias = "getUrlByRoute")
    public String getUrlByRoute(H5ProInstance h5ProInstance, String str) {
        LogUtil.i("H5PRO_RouteFunctions", "getUrlByRoute: cloudPath -> " + str);
        Matcher matcher = Pattern.compile("\\{\\{(.*?)\\}\\}").matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            str = str.replace(group, d(h5ProInstance.getPath(), group.replace("{{", "").replace("}}", "")));
        }
        LogUtil.i("H5PRO_RouteFunctions", "getUrlByRoute: cloudPath(end) -> " + str);
        return str;
    }

    @ExpressionFunction(alias = "getKeyByRoute")
    public String getKeyByRoute(H5ProInstance h5ProInstance, String str) {
        LogUtil.i("H5PRO_RouteFunctions", "getKeyByRoute: paramName -> " + str);
        String d = h5ProInstance != null ? d(h5ProInstance.getPath(), str) : "";
        LogUtil.i("H5PRO_RouteFunctions", "getKeyByRoute: paramValue -> " + d);
        return d;
    }

    private String d(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || !str.contains("?")) {
            LogUtil.w("H5PRO_RouteFunctions", "getParamValue: return,empty");
            return "";
        }
        for (String str3 : str.substring(str.indexOf("?")).replace("?", "").split("&")) {
            String[] split = str3.split("=");
            if (split.length == 2 && split[0].equals(str2)) {
                return split[1];
            }
        }
        return "";
    }
}
