package com.huawei.openalliance.ad.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface h {
    boolean a() default true;

    Class b() default Void.class;

    int c() default 0;

    String d() default "";

    int[] e() default {0, 100};

    int f() default 0;
}
