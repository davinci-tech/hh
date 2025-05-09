package com.huawei.hms.support.hwid.bean;

import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class GetRealNameInfoReq {
    private String requestChannelExtraInfo;
    private String requestChannelInfo;
    private String transId;

    public GetRealNameInfoReq() {
        this.requestChannelInfo = "";
        this.requestChannelExtraInfo = "";
        this.transId = "";
    }

    public GetRealNameInfoReq(String str) {
        this.requestChannelInfo = "";
        this.requestChannelExtraInfo = "";
        this.transId = "";
        if (str != null) {
            this.requestChannelInfo = str;
        }
    }

    public GetRealNameInfoReq(String str, String str2) {
        this.requestChannelInfo = "";
        this.requestChannelExtraInfo = "";
        this.transId = "";
        if (str != null) {
            this.requestChannelInfo = str;
        }
        if (str2 != null) {
            this.requestChannelExtraInfo = str2;
        }
    }

    public void setTransId(String str) {
        if (str != null) {
            this.transId = str;
        }
    }

    public String getTransId() {
        return this.transId;
    }

    public String getRequestChannelInfo() {
        return this.requestChannelInfo;
    }

    public void setRequestChannelInfo(String str) {
        if (str != null) {
            this.requestChannelInfo = str;
        }
    }

    public String getRequestChannelExtraInfo() {
        return this.requestChannelExtraInfo;
    }

    public void setRequestChannelExtraInfo(String str) {
        if (str != null) {
            this.requestChannelExtraInfo = str;
        }
    }

    public String getGetRealNameInfoReqJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CommonConstant.REALNAMEREQUEST.REQUEST_TRANS_ID, this.transId);
        jSONObject.put(CommonConstant.REALNAMEREQUEST.REQUEST_CHANNEL_INFO, this.requestChannelInfo);
        jSONObject.put(CommonConstant.REALNAMEREQUEST.REQUEST_CHANNEL_EXTRA_INFO, this.requestChannelExtraInfo);
        return jSONObject.toString();
    }
}
