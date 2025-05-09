package defpackage;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lkotlin/ranges/LongProgressionIterator;", "Lkotlin/collections/LongIterator;", "first", "", "last", "step", "(JJJ)V", "finalElement", "hasNext", "", "next", "getStep", "()J", "nextLong", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class uis extends LongIterator {

    /* renamed from: a, reason: collision with root package name */
    private final long f17434a;
    private boolean b;
    private final long d;
    private long e;

    public uis(long j, long j2, long j3) {
        this.f17434a = j3;
        this.d = j2;
        boolean z = j3 <= 0 ? j >= j2 : j <= j2;
        this.b = z;
        this.e = z ? j : j2;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.b;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        long j = this.e;
        if (j == this.d) {
            if (!this.b) {
                throw new NoSuchElementException();
            }
            this.b = false;
        } else {
            this.e = this.f17434a + j;
        }
        return j;
    }
}
