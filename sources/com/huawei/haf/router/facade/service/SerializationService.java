package com.huawei.haf.router.facade.service;

import com.huawei.haf.router.facade.template.SingleServiceProvider;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public interface SerializationService extends SingleServiceProvider {
    String object2Json(Object obj);

    <T> T parseObject(String str, Type type);
}
