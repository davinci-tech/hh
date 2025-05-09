package com.huawei.operation.br;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.br.BrConnectHelper;
import com.huawei.operation.h5pro.ble.bean.JsBrConnectBean;
import com.huawei.operation.h5pro.ble.bean.JsBrSendBean;
import com.huawei.operation.h5pro.ble.bean.JsDeviceIdBean;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BrOperatorCompactImpl implements BrConnectHelper.BtListener {
    private static final String KEY_CONNECTD_STATE = "connected";
    private static final String KEY_RECEIVED_DATA = "data";
    private static final String KEY_SEND_DATA = "sendData";
    private static final String KEY_SEND_FILE_DATA = "sendFileData";
    public static final String ON_BRRECEIVED_DATA = "onBrReceivedData";
    public static final String ON_BR_CONNECTION_STATE_CHANGE_CALLBACK_NAME = "onBrConnectionStateChange";
    public static final String ON_BR_SEND_MSG_CALLBACK_NAME = "onBrSendMsg";
    private static final String TAG = "PluginOperation_BrOperatorCompactImpl";
    private HashMap<String, String> mCallbackMap = null;
    private final H5ProJsCbkInvoker<Object> mJsInvoker;
    private final String mProductId;

    public BrOperatorCompactImpl(H5ProInstance h5ProInstance, String str) {
        LogUtil.a(TAG, "startBleState");
        this.mJsInvoker = h5ProInstance.getJsCbkInvoker();
        this.mProductId = str;
    }

    public void connect(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "connect function or data is null");
            callBackJsResult(this.mJsInvoker, str2, createParamError());
            return;
        }
        LogUtil.a(TAG, "connect ", str);
        try {
            JsBrConnectBean jsBrConnectBean = (JsBrConnectBean) new Gson().fromJson(str, JsBrConnectBean.class);
            String deviceId = jsBrConnectBean.getDeviceId();
            String brMac = jsBrConnectBean.getBrMac();
            String brUuid = jsBrConnectBean.getBrUuid();
            if (!TextUtils.isEmpty(this.mProductId) && this.mProductId.equals(deviceId)) {
                if (!TextUtils.isEmpty(brUuid) && !TextUtils.isEmpty(brMac)) {
                    onBrConnectionStateChange(str2, ON_BR_CONNECTION_STATE_CHANGE_CALLBACK_NAME);
                    BrConnectHelper.getInstance().connectDevice(brMac, brUuid);
                    return;
                }
                LogUtil.h(TAG, "uuid or mac is null");
                callBackJsResult(this.mJsInvoker, str2, createParamError());
                return;
            }
            LogUtil.h(TAG, "connect mProductId fail");
            callBackJsResult(this.mJsInvoker, str2, createParamError());
        } catch (JsonSyntaxException unused) {
            callBackJsResult(this.mJsInvoker, str2, createParamError());
            LogUtil.b(TAG, "connect parseData fail JsonSyntaxException");
        }
    }

    public String closeBrConnect(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "close mProductId fail");
            return String.valueOf(2);
        }
        LogUtil.a(TAG, "close");
        try {
            String deviceId = ((JsDeviceIdBean) new Gson().fromJson(str, JsDeviceIdBean.class)).getDeviceId();
            if (!TextUtils.isEmpty(this.mProductId) && this.mProductId.equals(deviceId)) {
                BrConnectHelper.getInstance().releaseResource();
                this.mCallbackMap.clear();
                this.mCallbackMap = null;
                return "0";
            }
            LogUtil.h(TAG, "close mProductId fail");
            return String.valueOf(2);
        } catch (JsonSyntaxException unused) {
            LogUtil.b(TAG, "close parseData fail JsonSyntaxException");
            return String.valueOf(2);
        }
    }

    public void releaseResource() {
        BrConnectHelper.getInstance().releaseResource();
    }

    public void onBrConnectionStateChange(String str, String str2) {
        setCallBackName(str, str2);
        BrConnectHelper.getInstance().onBtListener(this);
    }

    public void onBrReceivedData(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h(TAG, "onBrReceivedData function is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "onBrReceivedData mProductId fail");
            callBackJsResult(this.mJsInvoker, str2, createParamError());
            return;
        }
        try {
            String deviceId = ((JsDeviceIdBean) new Gson().fromJson(str, JsDeviceIdBean.class)).getDeviceId();
            if (!TextUtils.isEmpty(this.mProductId) && this.mProductId.equals(deviceId)) {
                setCallBackName(str2, str3);
                BrConnectHelper.getInstance().onBtListener(this);
                return;
            }
            LogUtil.h(TAG, "onBrReceivedData mProductId fail");
            callBackJsResult(this.mJsInvoker, str2, createParamError());
        } catch (JsonSyntaxException unused) {
            callBackJsResult(this.mJsInvoker, str2, createParamError());
            LogUtil.b(TAG, "onBrReceivedData parseData fail JsonSyntaxException");
        }
    }

    public void sendMsg(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h(TAG, "sendMsg function is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.h(TAG, "sendMsg mProductId fail");
            callBackJsResult(this.mJsInvoker, str2, createParamError());
            return;
        }
        LogUtil.a(TAG, "sendMsg");
        try {
            JsBrSendBean jsBrSendBean = (JsBrSendBean) new Gson().fromJson(str, JsBrSendBean.class);
            String deviceId = jsBrSendBean.getDeviceId();
            String data = jsBrSendBean.getData();
            if (!TextUtils.isEmpty(this.mProductId) && this.mProductId.equals(deviceId)) {
                setCallBackName(str2, ON_BR_SEND_MSG_CALLBACK_NAME);
                BrConnectHelper.getInstance().sendMsg(data);
                return;
            }
            LogUtil.h(TAG, "sendMsg mProductId fail");
            callBackJsResult(this.mJsInvoker, str2, createParamError());
        } catch (JsonSyntaxException unused) {
            callBackJsResult(this.mJsInvoker, str2, createParamError());
            LogUtil.b(TAG, "sendMsg parseData fail JsonSyntaxException");
        }
    }

    private void setCallBackName(String str, String str2) {
        LogUtil.a(TAG, "setCallBackName, function = ", str);
        if (this.mCallbackMap == null) {
            this.mCallbackMap = new HashMap<>(16);
        }
        if (!this.mCallbackMap.containsKey(str2) && !this.mCallbackMap.containsKey(str)) {
            this.mCallbackMap.put(str2, str);
        } else {
            LogUtil.h(TAG, "The callback method already exists.");
        }
    }

    private String createParamError() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", 2);
        } catch (JSONException unused) {
            LogUtil.b(TAG, "createParamError JSONException");
        }
        return jSONObject.toString();
    }

    private void callBackJsResult(H5ProJsCbkInvoker<Object> h5ProJsCbkInvoker, String str, String str2) {
        LogUtil.a(TAG, "callBackJsResult callbackFunc is ", str);
        if (TextUtils.isEmpty(str) || h5ProJsCbkInvoker == null) {
            LogUtil.h(TAG, "callbackFunc or mJsInvoker is null");
        } else {
            LogUtil.c(TAG, "callBackJsResult jsonResult: ", str2);
            h5ProJsCbkInvoker.invokeJsFunc(str, str2);
        }
    }

    @Override // com.huawei.operation.br.BrConnectHelper.BtListener
    public void socketNotify(int i, String str) {
        String str2;
        LogUtil.a(TAG, "state is ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        if (this.mCallbackMap == null) {
            LogUtil.h(TAG, "socketNotify mCallbackMap is null");
            return;
        }
        try {
            jSONObject.put("errCode", String.valueOf(0));
            if (i == 0 || i == 1) {
                String str3 = this.mCallbackMap.get(ON_BR_CONNECTION_STATE_CHANGE_CALLBACK_NAME);
                jSONObject.put("connected", i);
                str2 = str3;
            } else if (i == 2) {
                str2 = this.mCallbackMap.get(ON_BR_SEND_MSG_CALLBACK_NAME);
                jSONObject.put(KEY_SEND_DATA, str);
            } else if (i == 3) {
                jSONObject.put("errCode", BleConstants.ERRCODE_COMMON_ERR);
                str2 = this.mCallbackMap.get(ON_BR_SEND_MSG_CALLBACK_NAME);
                jSONObject.put(KEY_SEND_DATA, str);
            } else if (i == 5) {
                str2 = this.mCallbackMap.get(ON_BRRECEIVED_DATA);
                jSONObject.put("data", str);
            } else {
                if (i != 6) {
                    return;
                }
                LogUtil.c(TAG, "MSG_FILE_SENDED_PERCENT percent is ", str);
                str2 = this.mCallbackMap.get(ON_BR_SEND_MSG_CALLBACK_NAME);
                jSONObject.put(KEY_SEND_FILE_DATA, str);
            }
            callBackJsResult(this.mJsInvoker, str2, jSONObject.toString());
        } catch (JSONException e) {
            LogUtil.b(TAG, "callJsOnConnectionStateChange fail exception = ", e.getMessage());
        }
    }
}
