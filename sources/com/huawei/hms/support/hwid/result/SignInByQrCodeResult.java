package com.huawei.hms.support.hwid.result;

import android.text.TextUtils;
import com.huawei.hms.support.hwid.common.IntraAuthResult;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class SignInByQrCodeResult extends IntraAuthResult {
    public SignInByQrCodeResult fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        jsonToSuper(new JSONObject(str));
        return this;
    }
}
