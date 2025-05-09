package com.huawei.hwsmartinteractmgr.smarter;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ContentMeasure;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import defpackage.kwn;
import health.compact.a.SharedPreferenceManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BaseSmarter {
    public Context e;

    public void d() {
    }

    public BaseSmarter(Context context) {
        this.e = context;
    }

    public boolean b(String str, int i, String str2) {
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10021), str);
        boolean c = kwn.c(i, str2);
        LogUtil.a("SMART_BaseSmarter", "userType = ", str, ", isNoLongerRecommend = ", b, ", isEnable = ", Boolean.valueOf(c));
        return !"1".equals(b) || c;
    }

    protected static void d(SmartMsgDbObject smartMsgDbObject, String str, int i, String str2) {
        if (smartMsgDbObject == null) {
            return;
        }
        smartMsgDbObject.setMsgContentType(2);
        smartMsgDbObject.setMsgContent(HiJsonUtil.d(new ContentMeasure(i), ContentMeasure.class));
        smartMsgDbObject.setShowMethod(str2);
        if (!TextUtils.isEmpty(str)) {
            smartMsgDbObject.setShowTime(str);
        } else {
            smartMsgDbObject.setShowTime(SmartMsgConstant.DEFAULT_SHOW_TIME);
        }
        smartMsgDbObject.setStatus(1);
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(10);
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("start_time");
                String string2 = jSONObject.getString("end_time");
                sb.append(string);
                sb.append(string2);
            }
        } catch (JSONException e) {
            LogUtil.b("SMART_BaseSmarter", "getShowTime JSONException = ", e.getMessage());
        }
        return sb.toString();
    }
}
