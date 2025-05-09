package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ref {
    public static List<reh> c(String str, List<String> list) {
        LogUtil.a("UIDV_LightCloudParse", "parseResult");
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("UIDV_LightCloudParse", "result is null");
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            LogUtil.a("UIDV_LightCloudParse", "jsonArray.length = ", Integer.valueOf(jSONArray.length()));
            return c(jSONArray, list);
        } catch (JSONException e) {
            LogUtil.a("UIDV_LightCloudParse", "JSONException : " + e.getMessage());
            return arrayList;
        }
    }

    private static List<reh> c(JSONArray jSONArray, List<String> list) {
        LogUtil.a("UIDV_LightCloudParse", "parseJsonArray");
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() != 0) {
            LogUtil.a("UIDV_LightCloudParse", "jsonArray.length() = ", Integer.valueOf(jSONArray.length()));
            for (String str : list) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (!jSONObject.isNull(RecommendConstants.FILE_ID) && str.equals(jSONObject.getString(RecommendConstants.FILE_ID))) {
                            reh rehVar = new reh();
                            rehVar.d(jSONObject.getString(RecommendConstants.FILE_ID));
                            if (!jSONObject.isNull(RecommendConstants.VER)) {
                                rehVar.e(jSONObject.getString(RecommendConstants.VER));
                            }
                            if (!jSONObject.isNull(RecommendConstants.DOWNLOAD_URL)) {
                                rehVar.a(jSONObject.getString(RecommendConstants.DOWNLOAD_URL));
                            }
                            arrayList.add(rehVar);
                        }
                    } catch (JSONException e) {
                        LogUtil.b("UIDV_LightCloudParse", "JSONException : ", e.getMessage());
                    }
                }
            }
            return arrayList;
        }
        LogUtil.a("UIDV_LightCloudParse", "jsonArray is null or no data");
        return arrayList;
    }
}
