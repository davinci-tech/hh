package com.huawei.hms.support.hwid.result;

import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.IntraAuthResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class AckLoginResult extends IntraAuthResult {
    public AckLoginResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        jsonToSuper(new JSONObject(str));
        return this;
    }
}
