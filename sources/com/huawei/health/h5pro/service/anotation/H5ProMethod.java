package com.huawei.health.h5pro.service.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface H5ProMethod {
    String name() default "";

    String scope() default "";
}
