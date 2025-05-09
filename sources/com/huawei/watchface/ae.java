package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.utils.HwLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ae {
    private static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_CODE_STRING = "0";
    private static final String TAG = "BaseResp";
    private af result;
    public String retCode = "";

    public void parseResult(String str) {
        af afVar = new af(-1);
        setResult(afVar);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("result");
            if (optJSONObject != null) {
                afVar.a(optJSONObject.optInt("resultCode", -1));
                afVar.a(optJSONObject.optString("resultInfo"));
            }
        } catch (JSONException unused) {
            HwLog.i(TAG, "parseResult error");
        }
    }

    public af getResult() {
        return this.result;
    }

    public void setResult(af afVar) {
        this.result = afVar;
    }

    public boolean isSuccess() {
        af afVar = this.result;
        return afVar != null && afVar.a() == 0;
    }
}
