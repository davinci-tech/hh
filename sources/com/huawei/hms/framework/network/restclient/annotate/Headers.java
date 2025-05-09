package com.huawei.hms.framework.network.restclient.annotate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Deprecated
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Headers {
    String[] value();
}
