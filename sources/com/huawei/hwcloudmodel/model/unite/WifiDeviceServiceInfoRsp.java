package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class WifiDeviceServiceInfoRsp extends CloudCommonReponse {
    private static final int CODE_UNKOWN_ERROR = 1999;
    private static final String TAG = "WifiDeviceServiceInfoRsp";
    private List<DeviceServiceInfo> deviceServiceInfoList;

    public void setDeviceServiceInfo(List<DeviceServiceInfo> list) {
        this.deviceServiceInfoList = list;
    }

    public List<DeviceServiceInfo> getDeviceServiceInfo() {
        return this.deviceServiceInfoList;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceServiceInfoRsp{deviceServiceInfoList=" + this.deviceServiceInfoList + '}';
    }

    public void toObject(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("resultCode")) {
                setResultCode(Integer.valueOf(jSONObject.getInt("resultCode")));
            }
            if (jSONObject.has("deviceServiceInfoList")) {
                JSONArray jSONArray = jSONObject.getJSONArray("deviceServiceInfoList");
                this.deviceServiceInfoList = new ArrayList();
                if (jSONArray != null) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
                        deviceServiceInfo.toObject(jSONArray.getJSONObject(i));
                        this.deviceServiceInfoList.add(deviceServiceInfo);
                    }
                    return;
                }
                return;
            }
            if (jSONObject.has("resultDesc")) {
                setResultDesc(jSONObject.getString("resultDesc"));
            }
        } catch (JSONException e) {
            setResultCode(1999);
            setResultDesc("unknown error");
            LogUtil.c(TAG, " toObject(),JSONException= ", e.getMessage());
        }
    }
}
