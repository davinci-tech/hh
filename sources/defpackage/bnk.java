package defpackage;

import com.huawei.dynamicanimation.util.Pound;

/* loaded from: classes8.dex */
public class bnk<T> implements Pound<T> {
    private int c;
    private final Object[] e;

    public bnk(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The max pound capacity must > 0");
        }
        this.e = new Object[i];
    }

    @Override // com.huawei.dynamicanimation.util.Pound
    public T getInstance() {
        int i = this.c;
        if (i > 0) {
            int i2 = i - 1;
            Object[] objArr = this.e;
            if (objArr != null && i2 < objArr.length) {
                T t = (T) objArr[i2];
                objArr[i2] = null;
                this.c = i2;
                return t;
            }
        }
        return null;
    }

    @Override // com.huawei.dynamicanimation.util.Pound
    public boolean restoreInstance(T t) {
        if (d(t)) {
            throw new IllegalStateException("The instance is already in the pounds!");
        }
        int i = this.c;
        Object[] objArr = this.e;
        if (i >= objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.c = i + 1;
        return true;
    }

    private boolean d(T t) {
        for (int i = 0; i < this.c; i++) {
            if (this.e[i] == t) {
                return true;
            }
        }
        return false;
    }
}
