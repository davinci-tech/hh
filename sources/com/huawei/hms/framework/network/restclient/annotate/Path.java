package com.huawei.hms.framework.network.restclient.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
/* loaded from: classes2.dex */
public @interface Path {
    String value();
}
