package com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface RequireVersion {
    int value();
}
