package com.huawei.ui.main.stories.recommendcloud.service;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import com.huawei.ui.main.stories.recommendcloud.data.RecommendCloudObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class RecommendCloudParse {
    private static final String TAG = "UIDV_RecommendCloudParse";

    private RecommendCloudParse() {
    }

    public static List<RecommendCloudObject> parseResult(String str) {
        JSONArray jSONArray;
        LogUtil.a(TAG, "parseResult");
        ArrayList arrayList = new ArrayList();
        if (str == null || "".equals(str)) {
            LogUtil.h(TAG, "result is null");
            return arrayList;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i = !jSONObject.isNull("resultCode") ? jSONObject.getInt("resultCode") : -1;
            LogUtil.a(TAG, "resultCode = ", Integer.valueOf(i));
            if (i != 0 || jSONObject.isNull(RecommendConstants.FILE_INFOS) || (jSONArray = jSONObject.getJSONArray(RecommendConstants.FILE_INFOS)) == null) {
                return arrayList;
            }
            LogUtil.a(TAG, "jsonArray.length = ", Integer.valueOf(jSONArray.length()));
            return parseJsonArray(jSONArray);
        } catch (JSONException e) {
            LogUtil.b(TAG, "parseResult JSONException : ", e.getMessage());
            return arrayList;
        }
    }

    private static List<RecommendCloudObject> parseJsonArray(JSONArray jSONArray) {
        LogUtil.a(TAG, "parseJsonArray");
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.h(TAG, "jsonArray is null or no data");
            return arrayList;
        }
        LogUtil.a(TAG, "jsonArray.length() = " + jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null && !jSONObject.isNull("resultCode")) {
                    int i2 = jSONObject.getInt("resultCode");
                    LogUtil.a(TAG, "fileInfos resultCode = ", Integer.valueOf(i2));
                    if (i2 == 0) {
                        RecommendCloudObject recommendCloudObject = new RecommendCloudObject();
                        if (!jSONObject.isNull(RecommendConstants.FILE_ID)) {
                            recommendCloudObject.setFileId(jSONObject.getString(RecommendConstants.FILE_ID));
                        }
                        if (!jSONObject.isNull(RecommendConstants.VER)) {
                            recommendCloudObject.setVer(jSONObject.getString(RecommendConstants.VER));
                        }
                        if (!jSONObject.isNull(RecommendConstants.DOWNLOAD_URL)) {
                            recommendCloudObject.setDownloadUrl(jSONObject.getString(RecommendConstants.DOWNLOAD_URL));
                        }
                        arrayList.add(recommendCloudObject);
                    }
                } else {
                    LogUtil.h(TAG, "jsonObject is null or no resultCode ");
                }
            } catch (JSONException e) {
                LogUtil.b(TAG, "parseJsonArray JSONException : ", e.getMessage());
            }
        }
        return arrayList;
    }
}
