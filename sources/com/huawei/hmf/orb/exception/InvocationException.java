package com.huawei.hmf.orb.exception;

/* loaded from: classes9.dex */
public class InvocationException extends RuntimeException {
    public InvocationException(String str) {
        super(str);
    }

    public InvocationException(Exception exc) {
        super("by " + exc.toString());
    }
}
