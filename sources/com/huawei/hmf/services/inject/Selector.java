package com.huawei.hmf.services.inject;

/* loaded from: classes9.dex */
public abstract class Selector {
    abstract void add(Binder binder);

    public abstract Binder alias(String str);

    abstract boolean isEmpty();

    public class Binder {
        String mName;
        Class mService;

        Binder(String str) {
            this.mName = str;
        }

        public void bind(Class cls) {
            this.mService = cls;
            Selector.this.add(this);
        }
    }
}
