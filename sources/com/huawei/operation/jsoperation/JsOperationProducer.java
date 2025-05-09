package com.huawei.operation.jsoperation;

import com.huawei.operation.jsoperation.JsUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class JsOperationProducer {
    private static final Map<String, JsCommand> REQUEST;

    static {
        HashMap hashMap = new HashMap(2);
        REQUEST = hashMap;
        hashMap.put(JsUtil.ServiceType.STRESS, new StressService());
        hashMap.put(JsUtil.ServiceType.DATA, new DataService());
    }

    private JsOperationProducer() {
    }

    public static JsCommand produceRequest(String str) {
        return REQUEST.get(str);
    }
}
