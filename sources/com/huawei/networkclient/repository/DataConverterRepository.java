package com.huawei.networkclient.repository;

import com.huawei.hms.framework.network.restclient.hwhttp.ResponseBody;
import defpackage.lrd;
import health.compact.a.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class DataConverterRepository {
    public static final lrd b = new lrd();
    private Map<Class, DataConverter> d = new HashMap();

    public interface DataConverter<Output> {
        Output convert(ResponseBody responseBody, Class<Output> cls);

        Output convert(String str, Class<Output> cls);

        String revert(Output output);
    }

    public DataConverter a(Class<?> cls) {
        if (this.d.containsKey(cls)) {
            return this.d.get(cls);
        }
        return b;
    }

    public boolean e(Class<?> cls, DataConverter dataConverter) {
        if (this.d.containsKey(cls)) {
            LogUtil.a("DataConverterRepository", "converterFactory already defined in repository:", cls);
            return false;
        }
        this.d.put(cls, dataConverter);
        return true;
    }
}
