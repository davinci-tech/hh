package com.huawei.hms.network.embedded;

import javax.annotation.Nullable;

/* loaded from: classes9.dex */
public final class l9 extends w7 {

    @Nullable
    public final String b;
    public final long c;
    public final db d;

    @Override // com.huawei.hms.network.embedded.w7
    public db x() {
        return this.d;
    }

    @Override // com.huawei.hms.network.embedded.w7
    public o7 w() {
        String str = this.b;
        if (str != null) {
            return o7.b(str);
        }
        return null;
    }

    @Override // com.huawei.hms.network.embedded.w7
    public long v() {
        return this.c;
    }

    public l9(@Nullable String str, long j, db dbVar) {
        this.b = str;
        this.c = j;
        this.d = dbVar;
    }
}
