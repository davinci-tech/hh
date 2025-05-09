package com.huawei.health.h5pro.load;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huawei.health.h5pro.core.H5ProBridgeManager;
import com.huawei.health.h5pro.jsbridge.system.prerequest.PreRequestEntry;
import com.huawei.health.h5pro.jsbridge.system.prerequest.PreResult;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class JsInterfacePreRequestTask implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public String f2447a;
    public WeakReference<H5ProInstance> c;
    public WeakReference<PreRequestEntry> d;
    public Context e;

    @Override // java.lang.Runnable
    public void run() {
        LogUtil.i(c(), "enter");
        if (this.e == null) {
            LogUtil.w("JsInterfacePreRequestTask", "appContext is null");
            return;
        }
        H5ProInstance h5ProInstance = this.c.get();
        if (h5ProInstance == null) {
            LogUtil.w(c(), "h5ProInstance is null");
        }
        final PreRequestEntry preRequestEntry = this.d.get();
        if (preRequestEntry == null) {
            LogUtil.w(c(), "launchOptionEntry is null");
        }
        final Map<Long, JsInterfacePreRequestObj> preRequestList = JsInterfacePreRequestUtils.getPreRequestList(this.e, this.f2447a, h5ProInstance.getPath(), h5ProInstance.readPreRequestsJson());
        if (CommonUtil.isMapEmpty(preRequestList)) {
            return;
        }
        final H5ProJsCbkInvoker<Object> jsCbkInvoker = h5ProInstance.getJsCbkInvoker();
        if (jsCbkInvoker instanceof H5ProBridgeManager.CbkInvoker) {
            ((H5ProBridgeManager.CbkInvoker) jsCbkInvoker).registerCustomCallback(new H5ProCustomJsInterfaceCbk() { // from class: com.huawei.health.h5pro.load.JsInterfacePreRequestTask.1
                @Override // com.huawei.health.h5pro.load.H5ProCustomJsInterfaceCbk
                public void onCallback(long j, Object obj) {
                    JsInterfacePreRequestObj jsInterfacePreRequestObj = (JsInterfacePreRequestObj) preRequestList.get(Long.valueOf(j));
                    if (preRequestEntry == null || jsInterfacePreRequestObj == null) {
                        return;
                    }
                    PreResult preResult = obj instanceof JSONObject ? new PreResult(jsInterfacePreRequestObj.getRequestTime(), new Gson().fromJson(obj.toString(), JsonObject.class)) : new PreResult(jsInterfacePreRequestObj.getRequestTime(), obj);
                    String requestKey = jsInterfacePreRequestObj.getRequestKey();
                    if (preRequestEntry.putPreRequestResult(requestKey, preResult)) {
                        jsCbkInvoker.invokeJsFunc("window.nativeEvent", requestKey, preResult.getData());
                    }
                }
            });
            for (Long l : preRequestList.keySet()) {
                H5ProBridgeManager.getInstance().preExecute(h5ProInstance, l.longValue(), preRequestList.get(l));
            }
        }
    }

    private String c() {
        return LogUtil.getTag(this.f2447a, "JsInterfacePreRequestTask");
    }

    public JsInterfacePreRequestTask(String str, Context context, H5ProInstance h5ProInstance, PreRequestEntry preRequestEntry) {
        this.f2447a = str;
        this.e = context != null ? context.getApplicationContext() : null;
        this.c = new WeakReference<>(h5ProInstance);
        this.d = new WeakReference<>(preRequestEntry);
    }
}
