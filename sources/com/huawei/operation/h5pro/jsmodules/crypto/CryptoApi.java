package com.huawei.operation.h5pro.jsmodules.crypto;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.h5pro.jsmodules.interfaces.ServiceApiCallback;
import org.json.JSONException;
import org.json.JSONObject;

@H5ProService(name = "CryptoApi")
/* loaded from: classes.dex */
public class CryptoApi {
    private static final String TAG = "H5PRO_CryptoApi";
    private static volatile CryptoOperation sCryptoOperation;

    @H5ProMethod
    public static void generateKeyPair(String str, final ServiceApiCallback<JSONObject> serviceApiCallback) {
        LogUtil.a(TAG, "generateKeyPair");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "generateKeyPair: callback is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            serviceApiCallback.onFailure(-1, "generateKeyPair: Invalid parameter");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            final String optString = jSONObject.optString("algorithm", "");
            final int optInt = jSONObject.optInt("keySize");
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.crypto.CryptoApi$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CryptoApi.lambda$generateKeyPair$0(ServiceApiCallback.this, optString, optInt);
                }
            });
        } catch (JSONException e) {
            serviceApiCallback.onFailure(-1, e.getMessage());
        }
    }

    static /* synthetic */ void lambda$generateKeyPair$0(ServiceApiCallback serviceApiCallback, String str, int i) {
        initCryptoOperation();
        serviceApiCallback.onSuccess(sCryptoOperation.generateKeyPair(str, i));
    }

    @H5ProMethod
    public static void decryptByPrivateKey(String str, ServiceApiCallback<JSONObject> serviceApiCallback) {
        LogUtil.a(TAG, "decryptByPrivateKey");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "decryptByPrivateKey: callback is null");
            return;
        }
        CryptoObj cryptoObj = (CryptoObj) GsonUtil.parseJson(str, CryptoObj.class);
        if (cryptoObj == null) {
            serviceApiCallback.onFailure(-1, "decryptByPrivateKey: Invalid parameter");
        } else {
            initCryptoOperation();
            serviceApiCallback.onSuccess(sCryptoOperation.decryptByPrivateKey(cryptoObj.getTransform(), cryptoObj.getKey(), cryptoObj.getContent()));
        }
    }

    @H5ProMethod
    public static void aesDecrypt(String str, ServiceApiCallback<JSONObject> serviceApiCallback) {
        LogUtil.a(TAG, "aesDecrypt");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "aesDecrypt: callback is null");
            return;
        }
        CryptoObj cryptoObj = (CryptoObj) GsonUtil.parseJson(str, CryptoObj.class);
        if (cryptoObj == null) {
            serviceApiCallback.onFailure(-1, "aesDecrypt: Invalid parameter");
        } else {
            initCryptoOperation();
            serviceApiCallback.onSuccess(sCryptoOperation.aesDecrypt(cryptoObj.getTransform(), cryptoObj.getKey(), cryptoObj.getContent(), cryptoObj.getOpMode()));
        }
    }

    @H5ProMethod
    public static void decryptOnlineContent(String str, final ServiceApiCallback<JSONObject> serviceApiCallback) {
        LogUtil.a(TAG, "decryptOnlineContent");
        if (serviceApiCallback == null) {
            LogUtil.h(TAG, "aesDecrypt: callback is null");
            return;
        }
        final CryptoObj cryptoObj = (CryptoObj) GsonUtil.parseJson(str, CryptoObj.class);
        if (cryptoObj == null) {
            serviceApiCallback.onFailure(-1, "decryptOnlineContent: Invalid parameter");
        } else {
            initCryptoOperation();
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.crypto.CryptoApi$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CryptoApi.decryptOnlineContent(CryptoObj.this, (ServiceApiCallback<JSONObject>) serviceApiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void decryptOnlineContent(CryptoObj cryptoObj, ServiceApiCallback<JSONObject> serviceApiCallback) {
        serviceApiCallback.onSuccess(sCryptoOperation.decryptOnlineContent(cryptoObj.getTransform(), cryptoObj.getKey(), cryptoObj.getContent()));
    }

    private static void initCryptoOperation() {
        synchronized (CryptoApi.class) {
            if (sCryptoOperation == null) {
                sCryptoOperation = new CryptoOperation();
            }
        }
    }
}
