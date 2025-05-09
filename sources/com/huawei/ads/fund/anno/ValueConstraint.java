package com.huawei.ads.fund.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface ValueConstraint {
    Class constraintClass() default Void.class;

    int[] constraintRange() default {0, 100};

    int defaultIntValueForClass() default 0;

    String defaultStringValueForClass() default "";

    int defaultValueForRange() default 0;

    boolean value() default true;
}
