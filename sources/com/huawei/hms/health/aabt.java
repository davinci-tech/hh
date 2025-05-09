package com.huawei.hms.health;

import java.lang.reflect.Field;
import java.security.PrivilegedAction;

/* loaded from: classes4.dex */
class aabt implements PrivilegedAction {
    final /* synthetic */ Field aab;

    @Override // java.security.PrivilegedAction
    public Object run() {
        this.aab.setAccessible(true);
        return null;
    }

    aabt(Field field) {
        this.aab = field;
    }
}
