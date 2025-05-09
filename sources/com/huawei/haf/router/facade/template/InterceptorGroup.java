package com.huawei.haf.router.facade.template;

import java.util.Map;

/* loaded from: classes.dex */
public interface InterceptorGroup {
    void loadInto(Map<Integer, Class<? extends InterceptorHandler>> map);
}
