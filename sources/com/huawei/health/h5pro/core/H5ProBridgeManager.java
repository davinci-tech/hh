package com.huawei.health.h5pro.core;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.h5pro.ext.permission.PermissionResultHandler;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.load.H5ProCustomJsInterfaceCbk;
import com.huawei.health.h5pro.load.H5ProJsInterfacePreRequest;
import com.huawei.health.h5pro.load.JsInterfacePreRequestObj;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.utils.PermissionUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class H5ProBridgeManager {
    public static H5ProBridgeManager e;
    public HashMap<String, Class<? extends JsBaseModule>> b;

    /* renamed from: a, reason: collision with root package name */
    public Map<H5ProInstance, Map<String, JsModuleBase>> f2366a = new HashMap();
    public Map<H5ProInstance, Map<String, Class<? extends JsModuleBase>>> h = new HashMap();
    public Map<Integer, H5ProActivityResultCallback> c = new ConcurrentHashMap();
    public ConcurrentHashMap<String, Class<? extends JsModuleBase>> d = new ConcurrentHashMap<>();

    public static class CbkResult {

        @SerializedName("callback_id")
        public long b;

        @SerializedName("data")
        public Object c;

        @SerializedName("err_code")
        public int d;

        @SerializedName("isComplete")
        public boolean e;
    }

    public void unRegisterBridgeExtClass() {
        HashMap<String, Class<? extends JsBaseModule>> hashMap = this.b;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public void restoreBridgeClasses(String[] strArr) {
        if (strArr == null || strArr.length == 0 || !CommonUtil.isMapEmpty(this.d)) {
            return;
        }
        LogUtil.i("H5PRO_BridgeManager", "restoreBridgeClasses");
        for (int i = 0; i < strArr.length; i++) {
            if (!TextUtils.isEmpty(strArr[i])) {
                String[] split = strArr[i].split(",");
                if (split.length == 2) {
                    d(split[0], split[1]);
                }
            }
        }
    }

    public void registerBridgeExtClass(String str, Class<? extends JsBaseModule> cls) {
        if (TextUtils.isEmpty(str) || cls == null) {
            LogUtil.w("H5PRO_BridgeManager", "registerExtBridgeClass: moduleName or extBridgeClass is null");
            return;
        }
        HashMap<String, Class<? extends JsBaseModule>> hashMap = this.b;
        if (hashMap == null) {
            this.b = new HashMap<>();
        } else if (hashMap.containsKey(str)) {
            LogUtil.e("H5PRO_BridgeManager", "registerExtBridgeClass: one js module should only be register once");
            return;
        }
        this.b.put(str, cls);
    }

    public void registerBridgeClass(String str, Class<? extends JsModuleBase> cls) {
        if (this.d.containsKey(str)) {
            LogUtil.e("H5PRO_BridgeManager", "registerBridgeClass: one js module should only be register once");
        } else {
            this.d.put(str, cls);
        }
    }

    public void registerActivityResultCallback(int i, H5ProActivityResultCallback h5ProActivityResultCallback) {
        this.c.put(Integer.valueOf(i), h5ProActivityResultCallback);
    }

    public void preExecute(H5ProInstance h5ProInstance, long j, JsInterfacePreRequestObj jsInterfacePreRequestObj) {
        Map<H5ProInstance, Map<String, JsModuleBase>> map;
        if (jsInterfacePreRequestObj == null) {
            LogUtil.w("H5PRO_BridgeManager", "preRequestExecute: requestObj is null");
            return;
        }
        LogUtil.i("H5PRO_BridgeManager", "preRequestExecute: enter -> " + jsInterfacePreRequestObj.getRequestKey());
        if (!jsInterfacePreRequestObj.isAsync()) {
            LogUtil.w("H5PRO_BridgeManager", "preRequestExecute: Synchronization is not supported");
        } else if (h5ProInstance == null || (map = this.f2366a) == null) {
            LogUtil.w("H5PRO_BridgeManager", "preExecute, h5ProInstance or bridgeObjectMap is null");
        } else {
            H5ProJsInterfacePreRequest.preRequestExecute(j, jsInterfacePreRequestObj, h5ProInstance, map.get(h5ProInstance));
        }
    }

    public void onConfigurationChanged(H5ProInstance h5ProInstance) {
        LogUtil.i("H5PRO_BridgeManager", "onConfigurationChanged resources");
        Map<String, JsModuleBase> map = this.f2366a.get(h5ProInstance);
        if (map != null) {
            for (Object obj : map.values()) {
                if (obj != null && (obj instanceof WebViewConfigChangeListener)) {
                    ((WebViewConfigChangeListener) obj).onConfigurationChanged();
                }
            }
        }
    }

    public void notifyPermissionResult(final H5ProInstance h5ProInstance, int i, String[] strArr, int[] iArr) {
        PermissionUtil.getInstance().handlePermissionResult(i, strArr, iArr, new PermissionResultHandler() { // from class: com.huawei.health.h5pro.core.H5ProBridgeManager.1
            @Override // com.huawei.health.h5pro.ext.permission.PermissionResultHandler
            public void handle(int i2, String[] strArr2, int[] iArr2) {
                H5ProBridgeManager.this.d(i2, strArr2, iArr2, h5ProInstance);
            }
        });
    }

    public void notifyActivityResult(H5ProInstance h5ProInstance, int i, int i2, Intent intent) {
        H5ProActivityResultCallback h5ProActivityResultCallback = this.c.get(Integer.valueOf(i));
        if (h5ProActivityResultCallback != null) {
            h5ProActivityResultCallback.onResult(intent);
            this.c.remove(Integer.valueOf(i));
        }
        Map<String, JsModuleBase> map = this.f2366a.get(h5ProInstance);
        if (map != null) {
            for (JsModuleBase jsModuleBase : map.values()) {
                if (jsModuleBase != null) {
                    jsModuleBase.onActivityResult(i, i2, intent);
                }
            }
        }
        if (h5ProInstance != null) {
            h5ProInstance.onActivityResult(i, i2, intent);
        }
    }

    public static class CbkInvoker<T> implements H5ProJsCbkInvoker<T> {
        public H5ProCustomJsInterfaceCbk c;
        public H5ProBridgeCbkExecutor d;

        public void registerCustomCallback(H5ProCustomJsInterfaceCbk h5ProCustomJsInterfaceCbk) {
            this.c = h5ProCustomJsInterfaceCbk;
        }

        @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
        public void onSuccess(T t, long j) {
            b(j, 0, t, false);
        }

        @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
        public void onFailure(int i, String str, long j) {
            b(j, i, str, false);
        }

        @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
        public void onComplete(int i, String str, long j) {
            b(j, i, str, true);
        }

        @Override // com.huawei.health.h5pro.vengine.H5ProJsCbkInvoker
        public void invokeJsFunc(String str, Object... objArr) {
            this.d.execute(str + com.huawei.operation.utils.Constants.LEFT_BRACKET_ONLY + a(objArr) + com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY);
        }

        private void b(long j, int i, Object obj, boolean z) {
            H5ProCustomJsInterfaceCbk h5ProCustomJsInterfaceCbk = this.c;
            if (h5ProCustomJsInterfaceCbk == null || j >= 0) {
                invokeJsFunc("window.h5proRuntime.bridge.consumeCallback", d(j, i, obj, z));
            } else if (i == 0) {
                h5ProCustomJsInterfaceCbk.onCallback(j, obj);
            }
        }

        private String a(Object... objArr) {
            if (objArr == null || objArr.length == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < objArr.length; i++) {
                sb.append(GsonUtil.contentValueToJson(objArr[i]));
                if (i < objArr.length - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

        private CbkResult d(long j, int i, Object obj, boolean z) {
            LogUtil.d("H5PRO_BridgeManager", "triggerCallback enter, callback id:" + j + " errorCode:" + i + " isComplete" + z);
            CbkResult cbkResult = new CbkResult();
            cbkResult.b = j;
            cbkResult.d = i;
            if (obj instanceof JSONObject) {
                cbkResult.c = new Gson().fromJson(obj.toString(), (Class) JsonObject.class);
            } else {
                cbkResult.c = obj;
            }
            cbkResult.e = z;
            return cbkResult;
        }

        public CbkInvoker(H5ProBridgeCbkExecutor h5ProBridgeCbkExecutor) {
            this.d = h5ProBridgeCbkExecutor;
        }
    }

    public Map<String, Class<? extends JsModuleBase>> getCustomJsModule(H5ProInstance h5ProInstance) {
        Map<H5ProInstance, Map<String, Class<? extends JsModuleBase>>> map;
        return (h5ProInstance == null || (map = this.h) == null) ? new HashMap() : map.get(h5ProInstance);
    }

    public String[] getBridgeClassStrings() {
        if (CommonUtil.isMapEmpty(this.d)) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.d.keySet()) {
            Class<? extends JsModuleBase> cls = this.d.get(str);
            if (cls != null) {
                arrayList.add(str + "," + cls.getName());
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void destroy(H5ProInstance h5ProInstance) {
        LogUtil.i("H5PRO_BridgeManager", "destroy resources");
        Map<String, JsModuleBase> map = this.f2366a.get(h5ProInstance);
        if (map != null) {
            for (JsModuleBase jsModuleBase : map.values()) {
                if (jsModuleBase != null) {
                    jsModuleBase.onDestroy();
                }
            }
            this.f2366a.remove(h5ProInstance);
        }
        Map<H5ProInstance, Map<String, Class<? extends JsModuleBase>>> map2 = this.h;
        if (map2 != null) {
            map2.remove(h5ProInstance);
        }
        unRegisterBridgeExtClass();
    }

    public H5ProJsCbkInvoker<Object> createJsCbkInvoker(H5ProBridgeCbkExecutor h5ProBridgeCbkExecutor) {
        return new CbkInvoker(h5ProBridgeCbkExecutor);
    }

    public Map<String, JsModuleBase> createBridges(Context context, H5ProInstance h5ProInstance, Map<String, Class<? extends JsModuleBase>> map, Map<String, H5ProBundle> map2) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap(this.d);
        HashMap<String, Class<? extends JsBaseModule>> hashMap3 = this.b;
        if (hashMap3 != null && !hashMap3.isEmpty()) {
            hashMap2.putAll(this.b);
        }
        if (map != null) {
            hashMap2.putAll(map);
            if (this.h == null) {
                this.h = new HashMap();
            }
            this.h.put(h5ProInstance, map);
        }
        for (Map.Entry entry : hashMap2.entrySet()) {
            try {
                JsModuleBase jsModuleBase = (JsModuleBase) ((Class) entry.getValue()).getConstructor(new Class[0]).newInstance(new Object[0]);
                if (jsModuleBase instanceof JsBaseModule) {
                    ((JsBaseModule) jsModuleBase).onMount(context, h5ProInstance, map2 == null ? null : map2.get(entry.getKey()));
                } else {
                    jsModuleBase.onMount(context, h5ProInstance);
                }
                hashMap.put(entry.getKey(), jsModuleBase);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
                LogUtil.e("H5PRO_BridgeManager", "createBridges fail:" + e2.getClass().getName() + e2.getMessage());
            }
        }
        this.f2366a.put(h5ProInstance, hashMap);
        return hashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d(String str, String str2) {
        if (this.d.containsKey(str)) {
            LogUtil.e("H5PRO_BridgeManager", "registerBridgeClassByName: one js module should only be register once");
            return;
        }
        Class<?> cls = CommonUtil.getClass(str2);
        if (cls == null || !JsModuleBase.class.isAssignableFrom(cls)) {
            return;
        }
        this.d.put(str, cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String[] strArr, int[] iArr, H5ProInstance h5ProInstance) {
        Map<String, JsModuleBase> map = this.f2366a.get(h5ProInstance);
        if (map != null) {
            for (JsModuleBase jsModuleBase : map.values()) {
                if (jsModuleBase != null) {
                    jsModuleBase.onRequestPermissionsResult(i, strArr, iArr);
                }
            }
        }
        if (h5ProInstance != null) {
            h5ProInstance.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public static H5ProBridgeManager getInstance() {
        if (e == null) {
            synchronized (H5ProBridgeManager.class) {
                if (e == null) {
                    e = new H5ProBridgeManager();
                }
            }
        }
        return e;
    }
}
