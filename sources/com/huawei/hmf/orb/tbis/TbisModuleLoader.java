package com.huawei.hmf.orb.tbis;

import com.huawei.hmf.repository.ComponentRepository;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class TbisModuleLoader {
    private static final String PACKAGE = "com.huawei.hmf.md.tbis.";
    private static final String REGISTRY = "Registry";
    private static final Map<String, TBModuleService> moduleCache = new HashMap();

    public static TBModuleService load(String str) {
        synchronized (TbisModuleLoader.class) {
            Map<String, TBModuleService> map = moduleCache;
            TBModuleService tBModuleService = map.get(str);
            if (tBModuleService != null) {
                return tBModuleService;
            }
            if (ComponentRepository.getRepository().lookup(str) == null) {
                return null;
            }
            try {
                TBModuleService tBModuleService2 = new TBModuleService((TBModuleRegistry) Class.forName(PACKAGE + str + REGISTRY).newInstance());
                map.put(str, tBModuleService2);
                return tBModuleService2;
            } catch (Exception unused) {
                return null;
            }
        }
    }
}
