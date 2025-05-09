package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.uel;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\t\u0010\u0018\u001a\u00020\u0019H\u0096\u0002J\u000e\u0010\u001a\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u001d\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fH\u0016ø\u0001\u0000¢\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lkotlin/sequences/SequenceBuilderIterator;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlin/sequences/SequenceScope;", "", "Lkotlin/coroutines/Continuation;", "", "()V", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/Continuation;", "setNextStep", "(Lkotlin/coroutines/Continuation;)V", "nextValue", "Ljava/lang/Object;", "state", "", "Lkotlin/sequences/State;", "exceptionalState", "", "hasNext", "", "next", "()Ljava/lang/Object;", "nextNotReady", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "yield", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes10.dex */
final class ujb<T> extends SequenceScope<T> implements Iterator<T>, Continuation<ueu>, KMappedMarker {
    private T b;
    private int c;
    private Continuation<? super ueu> d;
    private Iterator<? extends T> e;

    public final void a(Continuation<? super ueu> continuation) {
        this.d = continuation;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            int i = this.c;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2 || i == 3) {
                        return true;
                    }
                    if (i == 4) {
                        return false;
                    }
                    throw b();
                }
                Iterator<? extends T> it = this.e;
                uhy.d(it);
                if (it.hasNext()) {
                    this.c = 2;
                    return true;
                }
                this.e = null;
            }
            this.c = 5;
            Continuation<? super ueu> continuation = this.d;
            uhy.d(continuation);
            this.d = null;
            uel.b bVar = uel.d;
            continuation.resumeWith(uel.b(ueu.d));
        }
    }

    @Override // java.util.Iterator
    public T next() {
        int i = this.c;
        if (i == 0 || i == 1) {
            return d();
        }
        if (i == 2) {
            this.c = 1;
            Iterator<? extends T> it = this.e;
            uhy.d(it);
            return it.next();
        }
        if (i == 3) {
            this.c = 0;
            T t = this.b;
            this.b = null;
            return t;
        }
        throw b();
    }

    private final T d() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    private final Throwable b() {
        int i = this.c;
        if (i == 4) {
            return new NoSuchElementException();
        }
        if (i == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.c);
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yield(T t, Continuation<? super ueu> continuation) {
        this.b = t;
        this.c = 3;
        this.d = continuation;
        Object a2 = ugw.a();
        if (a2 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return a2 == ugw.a() ? a2 : ueu.d;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yieldAll(Iterator<? extends T> it, Continuation<? super ueu> continuation) {
        if (!it.hasNext()) {
            return ueu.d;
        }
        this.e = it;
        this.c = 2;
        this.d = continuation;
        Object a2 = ugw.a();
        if (a2 == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return a2 == ugw.a() ? a2 : ueu.d;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object result) {
        createFailure.b(result);
        this.c = 4;
    }

    @Override // kotlin.coroutines.Continuation
    /* renamed from: getContext */
    public CoroutineContext getF17451a() {
        return ugq.f17420a;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
