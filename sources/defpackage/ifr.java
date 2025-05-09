package defpackage;

import com.huawei.hihealthservice.HiDataOperation;

/* loaded from: classes7.dex */
public final class ifr {
    private final ifq c;
    private final ifq d;

    private ifr() {
        this.c = new ifq(null);
        this.d = new ifq("hihealth_sensitive.db");
    }

    public static ifr d() {
        return c.d;
    }

    static class c {
        private static final ifr d = new ifr();
    }

    public void d(HiDataOperation hiDataOperation) {
        int dataType = hiDataOperation.getDataType();
        if (dataType == 0) {
            hiDataOperation.execute(null);
        } else if (dataType == 2) {
            hiDataOperation.execute(this.d);
        } else {
            hiDataOperation.execute(this.c);
        }
    }
}
