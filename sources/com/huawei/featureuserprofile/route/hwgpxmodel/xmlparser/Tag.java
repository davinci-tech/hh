package com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface Tag {
    int order() default 0;

    String value() default "";
}
