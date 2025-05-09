package com.huawei.hms.network.base.common.trans;

import com.huawei.hms.framework.common.StringUtils;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;

/* loaded from: classes.dex */
public class CounterOutputStream extends OutputStream {

    /* renamed from: a, reason: collision with root package name */
    private final AtomicLong f5133a = new AtomicLong(0);

    @Override // java.io.OutputStream
    public void write(@Nonnull byte[] bArr, int i, int i2) {
        this.f5133a.addAndGet(i2);
    }

    @Override // java.io.OutputStream
    public void write(@Nonnull byte[] bArr) {
        this.f5133a.addAndGet(bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f5133a.addAndGet(StringUtils.getBytes(String.valueOf(i)).length);
    }

    public long getLenth() {
        return this.f5133a.get();
    }
}
