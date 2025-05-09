package com.huawei.operation.beans;

import com.huawei.hms.support.feature.result.CommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class OauthInfoRsp {
    private String ageGroupFlag;
    private String birthDate;
    private String displayName;
    private String email;
    private String headPictureURL;
    private String mobileNumber;
    private String nationalCode;
    private String openID;
    private String srvNationalCode;

    public String getOpenID() {
        return this.openID;
    }

    public String toString() {
        return "OauthInfoRsp{openID='" + this.openID + "', displayName='" + this.displayName + "', headPictureURL='" + this.headPictureURL + "', mobileNumber='" + this.mobileNumber + "', srvNationalCode='" + this.srvNationalCode + "', nationalCode='" + this.nationalCode + "', birthDate='" + this.birthDate + "', ageGroupFlag='" + this.ageGroupFlag + "', email='" + this.email + "'}";
    }

    public JSONObject getJsonResult(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("accessToken", str);
            jSONObject.put("openID", this.openID);
            jSONObject.put(CommonConstant.KEY_DISPLAY_NAME, this.displayName);
            jSONObject.put("headPictureURL", this.headPictureURL);
            jSONObject.put("mobileNumber", this.mobileNumber);
            jSONObject.put("srvNationalCode", this.srvNationalCode);
            jSONObject.put("nationalCode", this.nationalCode);
            jSONObject.put("birthDate", this.birthDate);
            jSONObject.put("ageGroupFlag", this.ageGroupFlag);
            jSONObject.put("email", this.email);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
