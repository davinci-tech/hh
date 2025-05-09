package com.huawei.operation.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ixx;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class InformationBiOperate {
    private static final String BI_KEY_INFORMATION = "2020034";
    private static final String BI_PARAM_CLICK = "click";
    private static final String BI_PARAM_CURRENT_CATEGORY_ID = "currentCategoryId";
    private static final String BI_PARAM_CURRENT_CATEGORY_NAME = "currentCategoryName";
    private static final String BI_PARAM_INFORMATION_ITEM_ID = "informationItemId";
    private static final String BI_PARAM_LABEL = "label";
    private static final String BI_PARAM_PULLFROM = "pullfrom";
    private static final String BI_PARAM_TITLE = "title";
    private static final String BI_PARAM_URL = "url";
    private static final String TAG = "InformationBiOperate";
    private static final String TYPE_BROWSE_BI = "1";
    private final ArrayMap<String, String> mBiKey;

    public InformationBiOperate(Context context) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        this.mBiKey = arrayMap;
        if (context == null) {
            return;
        }
        arrayMap.put(context.getResources().getString(R.string._2130851628_res_0x7f02372c), BI_KEY_INFORMATION);
        arrayMap.put(context.getResources().getString(R.string._2130851629_res_0x7f02372d), BI_KEY_INFORMATION);
        arrayMap.put(context.getResources().getString(R.string._2130851631_res_0x7f02372f), BI_KEY_INFORMATION);
    }

    private String urlVerification(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : this.mBiKey.keySet()) {
                if (str.contains(str2)) {
                    return this.mBiKey.get(str2);
                }
            }
        }
        return "";
    }

    public void setBrowseBiAnalytics(Context context, String str, String str2) {
        synchronized (this) {
            String urlVerification = urlVerification(str);
            if (TextUtils.isEmpty(urlVerification)) {
                return;
            }
            LogUtil.a(TAG, "setBrowseBiAnalytics");
            LogUtil.a(TAG, "setBrowseBiAnalytics result: " + ixx.d().d(context, urlVerification, initBiParamsMap(str, str2), 0));
        }
    }

    private Map<String, Object> initBiParamsMap(String str, String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("type", "1");
        linkedHashMap.put("click", "1");
        linkedHashMap.put("title", str2);
        linkedHashMap.put(BI_PARAM_INFORMATION_ITEM_ID, Utils.parseUri(str, BI_PARAM_INFORMATION_ITEM_ID));
        linkedHashMap.put("label", Utils.parseUri(str, "label"));
        linkedHashMap.put(BI_PARAM_CURRENT_CATEGORY_ID, Utils.parseUri(str, BI_PARAM_CURRENT_CATEGORY_ID));
        linkedHashMap.put(BI_PARAM_CURRENT_CATEGORY_NAME, Utils.parseUri(str, BI_PARAM_CURRENT_CATEGORY_NAME));
        linkedHashMap.put("pullfrom", Utils.parseUri(str, "pullfrom"));
        linkedHashMap.put("url", str);
        return linkedHashMap;
    }
}
