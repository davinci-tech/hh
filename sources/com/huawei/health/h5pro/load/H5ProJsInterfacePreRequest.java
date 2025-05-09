package com.huawei.health.h5pro.load;

import android.text.TextUtils;
import com.huawei.health.h5pro.jsbridge.base.JsModuleBase;
import com.huawei.health.h5pro.jsbridge.system.servicebus.ServiceBusEntry;
import com.huawei.health.h5pro.utils.CommonUtil;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.health.h5pro.vengine.H5ProInstance;
import java.util.Map;

/* loaded from: classes3.dex */
public class H5ProJsInterfacePreRequest {
    public static void preRequestExecute(long j, JsInterfacePreRequestObj jsInterfacePreRequestObj, H5ProInstance h5ProInstance, Map<String, JsModuleBase> map) {
        if (jsInterfacePreRequestObj == null) {
            LogUtil.w("H5PRO_H5ProJsInterfacePreRequest", "preRequestExecute: requestObj is null");
            return;
        }
        if (CommonUtil.isMapEmpty(map)) {
            LogUtil.w("H5PRO_H5ProJsInterfacePreRequest", "preRequestExecute: allJsModule is empty");
        } else if (jsInterfacePreRequestObj.getType() == 1) {
            executeForServiceBus(j, jsInterfacePreRequestObj, h5ProInstance, map.get("servicebus"));
        } else {
            execute(j, jsInterfacePreRequestObj, h5ProInstance, map.get(jsInterfacePreRequestObj.getModuleName()));
        }
    }

    public static void executeForServiceBus(long j, JsInterfacePreRequestObj jsInterfacePreRequestObj, H5ProInstance h5ProInstance, JsModuleBase jsModuleBase) {
        if (!(jsModuleBase instanceof ServiceBusEntry)) {
            LogUtil.w("H5PRO_H5ProJsInterfacePreRequest", "execute: jsModule is not ServiceBusEntry");
            return;
        }
        try {
            ServiceBusEntry serviceBusEntry = (ServiceBusEntry) jsModuleBase;
            String paramsStr = jsInterfacePreRequestObj.getParamsStr(h5ProInstance);
            serviceBusEntry.asyncInvoke(j, jsInterfacePreRequestObj.getModuleName(), jsInterfacePreRequestObj.getMethodName(), TextUtils.isEmpty(paramsStr) ? new String[0] : new String[]{paramsStr});
        } catch (Exception e) {
            LogUtil.e("H5PRO_H5ProJsInterfacePreRequest", "executeForServiceBus: exception -> " + e.getMessage());
        }
    }

    public static void execute(long j, JsInterfacePreRequestObj jsInterfacePreRequestObj, H5ProInstance h5ProInstance, JsModuleBase jsModuleBase) {
        String moduleName = jsInterfacePreRequestObj.getModuleName();
        if (jsModuleBase == null) {
            LogUtil.w("H5PRO_H5ProJsInterfacePreRequest", "execute: jsModule is null -> " + moduleName);
            return;
        }
        try {
            String paramsStr = jsInterfacePreRequestObj.getParamsStr(h5ProInstance);
            if (TextUtils.isEmpty(paramsStr)) {
                jsModuleBase.getClass().getMethod(jsInterfacePreRequestObj.getMethodName(), Long.TYPE).invoke(jsModuleBase, Long.valueOf(j));
            } else {
                jsModuleBase.getClass().getMethod(jsInterfacePreRequestObj.getMethodName(), Long.TYPE, String.class).invoke(jsModuleBase, Long.valueOf(j), paramsStr);
            }
        } catch (Exception e) {
            LogUtil.e("H5PRO_H5ProJsInterfacePreRequest", "execute: exception -> " + e.getMessage());
        }
    }
}
