package com.huawei.hmf.orb.tbis.result;

import com.huawei.hmf.orb.tbis.TBNativeType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class TBResultParser {
    private static Map<Type, TBNativeType.Factory> typeAdapterMap = new HashMap();

    public static void registry(Type type, TBNativeType.Factory factory) {
        typeAdapterMap.put(type, factory);
    }

    public static TBResult parser(Class<?> cls, Object obj) {
        TBNativeType.Factory factory = typeAdapterMap.get(cls);
        if (factory == null) {
            if (!cls.isInterface()) {
                return new DefaultResult(obj);
            }
            return SimpleResult.valueOf(obj);
        }
        return SimpleResult.valueOf(obj == null ? null : factory.create(obj));
    }

    static class SimpleResult extends TBResult {
        private final Object object;

        /* JADX INFO: Access modifiers changed from: private */
        public static TBResult valueOf(Object obj) {
            return new SimpleResult(obj);
        }

        private SimpleResult(Object obj) {
            this.object = obj;
        }

        @Override // com.huawei.hmf.orb.tbis.result.TBResult
        public Object getValue() {
            return this.object;
        }
    }
}
