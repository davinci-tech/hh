package com.huawei.health.h5pro.service.anotation;

import com.huawei.health.h5pro.service.H5ProServiceLiveTerm;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface H5ProService {
    H5ProServiceLiveTerm liveTerm() default H5ProServiceLiveTerm.WHOLE;

    String name() default "";

    String[] users() default {};
}
