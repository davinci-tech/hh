package com.huawei.wisesecurity.ucs.credential.entity;

import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringNotEmpty;
import defpackage.ttr;
import defpackage.tue;
import defpackage.twc;
import defpackage.twf;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ErrorBody {

    @KfsStringNotEmpty
    private String errorCode;

    @KfsStringNotEmpty
    private String errorMessage;

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public static ErrorBody fromString(String str) throws twc {
        try {
            ErrorBody errorBody = new ErrorBody();
            JSONObject jSONObject = new JSONObject(str);
            errorBody.errorCode = jSONObject.optString("errorCode");
            errorBody.errorMessage = jSONObject.optString("errorMessage");
            tue.d(errorBody);
            return errorBody;
        } catch (JSONException e) {
            StringBuilder e2 = twf.e("ErrorBody param is not a valid json string : ");
            e2.append(e.getMessage());
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, e2.toString());
        } catch (ttr e3) {
            StringBuilder e4 = twf.e("ErrorBody param invalid : ");
            e4.append(e3.getMessage());
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, e4.toString());
        }
    }
}
