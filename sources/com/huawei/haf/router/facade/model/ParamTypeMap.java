package com.huawei.haf.router.facade.model;

import java.util.HashMap;

/* loaded from: classes.dex */
public final class ParamTypeMap extends HashMap<String, Integer> {
    private static final long serialVersionUID = -237687171444477030L;

    public ParamTypeMap append(String str, int i) {
        put(str, Integer.valueOf(i));
        return this;
    }
}
