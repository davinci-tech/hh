package com.huawei.android.emcom;

import android.emcom.IHandoffSdkCallback;
import android.emcom.IOneHopAppCallback;
import android.emcom.IOneHopAuthReqCallback;
import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EmcomManagerEx {
    private static final String TAG = "EmcomManagerExSDK";
    private static EmcomManagerEx sEmcomManagerEx;

    private EmcomManagerEx() {
    }

    public static EmcomManagerEx getInstance() {
        EmcomManagerEx emcomManagerEx;
        synchronized (EmcomManagerEx.class) {
            if (sEmcomManagerEx == null) {
                sEmcomManagerEx = new EmcomManagerEx();
            }
            emcomManagerEx = sEmcomManagerEx;
        }
        return emcomManagerEx;
    }

    public int registerHandoff(String str, int i, IHandoffSdkCallback iHandoffSdkCallback) {
        Log.d(TAG, "registerHandoff packageName: " + str + "DataType: " + i);
        return 0;
    }

    public int syncHandoffData(String str, JSONObject jSONObject) {
        Log.d(TAG, "syncHandoffData packageName: " + str);
        return 0;
    }

    public boolean isHandoffServiceSupported(String str, int i) {
        Log.d(TAG, "isHandoffServiceSupported");
        return true;
    }

    public Map<String, List<String>> getHandoffBindRelationMap(String str, int i) {
        HashMap hashMap = new HashMap();
        Log.d(TAG, "getHandoffBindRelationMap packageName: " + str);
        return hashMap;
    }

    public int unbindHandoffRelation(String str, int i, String str2) {
        Log.d(TAG, "unbindHandoffRelation packageName: " + str);
        return 0;
    }

    public boolean isEnableHandoff() {
        Log.d(TAG, "isEnableHandoff");
        return true;
    }

    public String onehopGetVersion() {
        Log.d(TAG, "onehopGetVersion");
        return "";
    }

    public int registerOneHop(String str, int i, IOneHopAppCallback iOneHopAppCallback) {
        Log.d(TAG, "registerOneHop packageName: " + str + "DataType: " + i);
        return 0;
    }

    public int unregisterOneHop(String str, int i) {
        Log.d(TAG, "unregisterOneHop packageName: " + str + "DataType: " + i);
        return 0;
    }

    public int oneHopSend(String str, JSONObject jSONObject) {
        Log.d(TAG, "syncHandoffData packageName: " + str);
        return 0;
    }

    public int onehopAuthReq(String str, IOneHopAuthReqCallback iOneHopAuthReqCallback) {
        Log.d(TAG, "onehopAuthReq, packageName: " + str);
        return 0;
    }
}
