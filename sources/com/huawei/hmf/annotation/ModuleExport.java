package com.huawei.hmf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes2.dex */
public @interface ModuleExport {
    Type[] value();

    /* loaded from: classes8.dex */
    public enum Type {
        LOCAL(1),
        REMOTE(2),
        TBIS(4),
        DEX(8);

        private final int value;

        Type(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public boolean in(Type[] typeArr) {
            for (Type type : typeArr) {
                if (type == this) {
                    return true;
                }
            }
            return false;
        }
    }
}
