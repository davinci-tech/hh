package com.huawei.wisesecurity.kfs.validation.constrains.validator;

import defpackage.ttr;
import java.lang.annotation.Annotation;

/* loaded from: classes7.dex */
public interface KfsConstraintValidator<A extends Annotation, T> {
    String getMessage();

    void initialize(String str, A a2) throws ttr;

    boolean isValid(T t);
}
