package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dbt {
    public static String a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(RecommendConstants.FILE_ID, str);
            jSONObject.put(RecommendConstants.VER, "0");
        } catch (JSONException unused) {
            LogUtil.b("Plugin_DataManager", "requestSingleBuildParam JSONException");
        }
        return jSONObject.toString();
    }

    public static String c(ArrayList<String> arrayList) {
        if (arrayList == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(RecommendConstants.FILE_ID, next);
                jSONObject2.put(RecommendConstants.VER, "0");
                jSONArray.put(jSONObject2);
            } catch (JSONException unused) {
                LogUtil.b("Plugin_DataManager", "buildRequestParam JSONException fileId");
            }
        }
        try {
            jSONObject.put(RecommendConstants.FILE_ID, jSONArray);
            jSONObject.put(RecommendConstants.IS_BATCH, "1");
        } catch (JSONException unused2) {
            LogUtil.b("Plugin_DataManager", "buildRequestParam JSONException");
        }
        return jSONObject.toString();
    }

    public static ddo b(String str) {
        LogUtil.a("Plugin_DataManager", "Enter parseResult");
        ddo ddoVar = new ddo();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Plugin_DataManager", "parseResult param result is null or empty");
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull(RecommendConstants.FILE_ID)) {
                ddoVar.c(jSONObject.getString(RecommendConstants.FILE_ID));
            }
            if (!jSONObject.isNull(RecommendConstants.DOWNLOAD_URL)) {
                ddoVar.a(jSONObject.getString(RecommendConstants.DOWNLOAD_URL));
            }
        } catch (JSONException unused) {
            LogUtil.b("Plugin_DataManager", "parseResult JSONException");
        }
        return ddoVar;
    }

    public static List<ddo> c(String str) {
        LogUtil.a("Plugin_DataManager", "Enter parseResultList");
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Plugin_DataManager", "parseResultList param result is null or empty");
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = !jSONObject.isNull("resultCode") ? jSONObject.getInt("resultCode") : -1;
            LogUtil.a("Plugin_DataManager", "parseResultList resultCode = ", Integer.valueOf(i));
            if (i != 0 || jSONObject.isNull(RecommendConstants.FILE_INFOS)) {
                return arrayList;
            }
            JSONArray jSONArray = jSONObject.getJSONArray(RecommendConstants.FILE_INFOS);
            LogUtil.a("Plugin_DataManager", "parseResultList jsonArray length = ", Integer.valueOf(jSONArray.length()));
            return d(jSONArray);
        } catch (JSONException unused) {
            LogUtil.b("Plugin_DataManager", "parseResultList JSONException");
            return arrayList;
        }
    }

    private static List<ddo> d(JSONArray jSONArray) {
        JSONObject jSONObject;
        LogUtil.a("Plugin_DataManager", "Enter parseJsonArray");
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() != 0) {
            LogUtil.a("Plugin_DataManager", "parseJsonArray jsonArray length() = ", Integer.valueOf(jSONArray.length()));
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    jSONObject = jSONArray.getJSONObject(i);
                } catch (JSONException unused) {
                    LogUtil.b("Plugin_DataManager", "parseJsonArray JSONException");
                }
                if (jSONObject == null) {
                    LogUtil.h("Plugin_DataManager", "parseJsonArray jsonObject is null");
                    return arrayList;
                }
                ddo ddoVar = new ddo();
                if (!jSONObject.isNull(RecommendConstants.FILE_ID)) {
                    ddoVar.c(jSONObject.getString(RecommendConstants.FILE_ID));
                }
                if (!jSONObject.isNull(RecommendConstants.DOWNLOAD_URL)) {
                    ddoVar.a(jSONObject.getString(RecommendConstants.DOWNLOAD_URL));
                }
                arrayList.add(ddoVar);
            }
            return arrayList;
        }
        LogUtil.h("Plugin_DataManager", "parseJsonArray jsonArray is null or no data");
        return arrayList;
    }
}
