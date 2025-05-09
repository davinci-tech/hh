package defpackage;

import androidx.exifinterface.media.ExifInterface;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.uel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.CancellableFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\b\u0012\u0004\u0012\u0002H\u00010\u0006:\u0001hB\u001d\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020,2\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020,H\u0002J\u001f\u00103\u001a\u0002042\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u000006H\u0096@ø\u0001\u0000¢\u0006\u0002\u00107J\u0010\u00108\u001a\u00020,2\u0006\u00109\u001a\u00020\u0012H\u0002J\b\u0010:\u001a\u00020\u0003H\u0014J\u001d\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e2\u0006\u0010<\u001a\u00020\bH\u0014¢\u0006\u0002\u0010=J\b\u0010>\u001a\u00020,H\u0002J\u0019\u0010?\u001a\u00020,2\u0006\u0010@\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u0019\u0010B\u001a\u00020,2\u0006\u0010@\u001a\u00028\u0000H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010AJ\u0012\u0010C\u001a\u00020,2\b\u0010D\u001a\u0004\u0018\u00010\u000fH\u0002J1\u0010E\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010F0\u000e2\u0014\u0010G\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010F0\u000eH\u0002¢\u0006\u0002\u0010HJ&\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000J2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010N\u001a\u0004\u0018\u00010\u000f2\u0006\u0010O\u001a\u00020\u0012H\u0002J7\u0010P\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0010\u0010Q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e2\u0006\u0010R\u001a\u00020\b2\u0006\u0010S\u001a\u00020\bH\u0002¢\u0006\u0002\u0010TJ\b\u0010U\u001a\u00020,H\u0016J\u0015\u0010V\u001a\u00020W2\u0006\u0010@\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010XJ\u0015\u0010Y\u001a\u00020W2\u0006\u0010@\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010XJ\u0015\u0010Z\u001a\u00020W2\u0006\u0010@\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010XJ\u0010\u0010[\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u0003H\u0002J\u0012\u0010\\\u001a\u0004\u0018\u00010\u000f2\u0006\u0010-\u001a\u00020\u0003H\u0002J(\u0010]\u001a\u00020,2\u0006\u0010^\u001a\u00020\u00122\u0006\u0010_\u001a\u00020\u00122\u0006\u0010`\u001a\u00020\u00122\u0006\u0010a\u001a\u00020\u0012H\u0002J%\u0010b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010F0\u000e2\u0006\u0010c\u001a\u00020\u0012H\u0000¢\u0006\u0004\bd\u0010eJ\r\u0010f\u001a\u00020\u0012H\u0000¢\u0006\u0002\bgR\u001a\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u001a\u0010\u0018\u001a\u00028\u00008DX\u0084\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0014R\u000e\u0010 \u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\"8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006i"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl;", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/SharedFlowSlot;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "replay", "", "bufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(IILkotlinx/coroutines/channels/BufferOverflow;)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferEndIndex", "", "getBufferEndIndex", "()J", "bufferSize", "head", "getHead", "lastReplayedLocked", "getLastReplayedLocked$annotations", "()V", "getLastReplayedLocked", "()Ljava/lang/Object;", "minCollectorIndex", "queueEndIndex", "getQueueEndIndex", "queueSize", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "replayIndex", "replaySize", "getReplaySize", "()I", "totalSize", "getTotalSize", "awaitValue", "", "slot", "(Lkotlinx/coroutines/flow/SharedFlowSlot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelEmitter", "emitter", "Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "cleanupTailLocked", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "correctCollectorIndexesOnDropOldest", "newHead", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/SharedFlowSlot;", "dropOldestLocked", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitSuspend", "enqueueLocked", "item", "findSlotsToResumeLocked", "Lkotlin/coroutines/Continuation;", "resumesIn", "([Lkotlin/coroutines/Continuation;)[Lkotlin/coroutines/Continuation;", "fuse", "Lkotlinx/coroutines/flow/Flow;", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "capacity", "getPeekedValueLockedAt", "index", "growBuffer", "curBuffer", "curSize", "newSize", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "resetReplayCache", "tryEmit", "", "(Ljava/lang/Object;)Z", "tryEmitLocked", "tryEmitNoCollectorsLocked", "tryPeekLocked", "tryTakeValue", "updateBufferLocked", "newReplayIndex", "newMinCollectorIndex", "newBufferEndIndex", "newQueueEndIndex", "updateCollectorIndexLocked", "oldIndex", "updateCollectorIndexLocked$kotlinx_coroutines_core", "(J)[Lkotlin/coroutines/Continuation;", "updateNewCollectorIndexLocked", "updateNewCollectorIndexLocked$kotlinx_coroutines_core", "Emitter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public class unz<T> extends AbstractSharedFlow<uoa> implements MutableSharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {

    /* renamed from: a, reason: collision with root package name */
    private final int f17478a;
    private long b;
    private int c;
    private Object[] d;
    private final BufferOverflow e;
    private final int f;
    private int g;
    private long h;

    public unz(int i, int i2, BufferOverflow bufferOverflow) {
        this.f = i;
        this.f17478a = i2;
        this.e = bufferOverflow;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long j() {
        return Math.min(this.b, this.h);
    }

    private final int i() {
        return (int) ((j() + this.c) - this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int g() {
        return this.c + this.g;
    }

    private final long h() {
        return j() + this.c;
    }

    private final long f() {
        return j() + this.c + this.g;
    }

    protected final T e() {
        Object c2;
        Object[] objArr = this.d;
        uhy.d(objArr);
        c2 = NO_VALUE.c(objArr, (this.h + i()) - 1);
        return (T) c2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object a(defpackage.unz r8, kotlinx.coroutines.flow.FlowCollector r9, kotlin.coroutines.Continuation r10) {
        /*
            Method dump skipped, instructions count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.unz.a(unz, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(T value) {
        int i;
        boolean z;
        Continuation<ueu>[] continuationArr = EMPTY_RESUMES.e;
        synchronized (this) {
            i = 0;
            if (a((unz<T>) value)) {
                continuationArr = d(continuationArr);
                z = true;
            } else {
                z = false;
            }
        }
        int length = continuationArr.length;
        while (i < length) {
            Continuation<ueu> continuation = continuationArr[i];
            i++;
            if (continuation != null) {
                uel.b bVar = uel.d;
                continuation.resumeWith(uel.b(ueu.d));
            }
        }
        return z;
    }

    static /* synthetic */ Object a(unz unzVar, Object obj, Continuation continuation) {
        Object e;
        return (!unzVar.tryEmit(obj) && (e = unzVar.e(obj, continuation)) == ugw.a()) ? e : ueu.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean a(T t) {
        if (getNCollectors() == 0) {
            return b((unz<T>) t);
        }
        if (this.c >= this.f17478a && this.b <= this.h) {
            int i = c.c[this.e.ordinal()];
            if (i == 1) {
                return false;
            }
            if (i == 2) {
                return true;
            }
        }
        d(t);
        int i2 = this.c + 1;
        this.c = i2;
        if (i2 > this.f17478a) {
            d();
        }
        if (i() > this.f) {
            b(this.h + 1, this.b, h(), f());
        }
        return true;
    }

    private final boolean b(T t) {
        if (ASSERTIONS_ENABLED.a() && getNCollectors() != 0) {
            throw new AssertionError();
        }
        if (this.f == 0) {
            return true;
        }
        d(t);
        int i = this.c + 1;
        this.c = i;
        if (i > this.f) {
            d();
        }
        this.b = j() + this.c;
        return true;
    }

    private final void d() {
        Object[] objArr = this.d;
        uhy.d(objArr);
        NO_VALUE.c(objArr, j(), null);
        this.c--;
        long j = j() + 1;
        if (this.h < j) {
            this.h = j;
        }
        if (this.b < j) {
            a(j);
        }
        if (ASSERTIONS_ENABLED.a() && j() != j) {
            throw new AssertionError();
        }
    }

    private final void a(long j) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        unz<T> unzVar = this;
        if (((AbstractSharedFlow) unzVar).nCollectors != 0 && (abstractSharedFlowSlotArr = ((AbstractSharedFlow) unzVar).slots) != null) {
            int length = abstractSharedFlowSlotArr.length;
            int i = 0;
            while (i < length) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i];
                i++;
                if (abstractSharedFlowSlot != null) {
                    uoa uoaVar = (uoa) abstractSharedFlowSlot;
                    if (uoaVar.f17481a >= 0 && uoaVar.f17481a < j) {
                        uoaVar.f17481a = j;
                    }
                }
            }
        }
        this.b = j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d(Object obj) {
        int g = g();
        Object[] objArr = this.d;
        if (objArr == null) {
            objArr = d(null, 0, 2);
        } else if (g >= objArr.length) {
            objArr = d(objArr, g, objArr.length * 2);
        }
        NO_VALUE.c(objArr, j() + g, obj);
    }

    private final Object[] d(Object[] objArr, int i, int i2) {
        Object c2;
        if (i2 <= 0) {
            throw new IllegalStateException("Buffer size overflow".toString());
        }
        Object[] objArr2 = new Object[i2];
        this.d = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long j = j();
        for (int i3 = 0; i3 < i; i3++) {
            long j2 = i3 + j;
            c2 = NO_VALUE.c(objArr, j2);
            NO_VALUE.c(objArr2, j2, c2);
        }
        return objArr2;
    }

    public final long a() {
        long j = this.h;
        if (j < this.b) {
            this.b = j;
        }
        return j;
    }

    public final Continuation<ueu>[] b(long j) {
        int i;
        Object c2;
        Object c3;
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        if (ASSERTIONS_ENABLED.a() && j < this.b) {
            throw new AssertionError();
        }
        if (j > this.b) {
            return EMPTY_RESUMES.e;
        }
        long j2 = j();
        long j3 = this.c + j2;
        long j4 = 1;
        if (this.f17478a == 0 && this.g > 0) {
            j3++;
        }
        unz<T> unzVar = this;
        if (((AbstractSharedFlow) unzVar).nCollectors != 0 && (abstractSharedFlowSlotArr = ((AbstractSharedFlow) unzVar).slots) != null) {
            int length = abstractSharedFlowSlotArr.length;
            int i2 = 0;
            while (i2 < length) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i2];
                i2++;
                if (abstractSharedFlowSlot != null) {
                    uoa uoaVar = (uoa) abstractSharedFlowSlot;
                    if (uoaVar.f17481a >= 0 && uoaVar.f17481a < j3) {
                        j3 = uoaVar.f17481a;
                    }
                }
            }
        }
        if (ASSERTIONS_ENABLED.a() && j3 < this.b) {
            throw new AssertionError();
        }
        if (j3 <= this.b) {
            return EMPTY_RESUMES.e;
        }
        long h = h();
        if (getNCollectors() > 0) {
            i = Math.min(this.g, this.f17478a - ((int) (h - j3)));
        } else {
            i = this.g;
        }
        Continuation<ueu>[] continuationArr = EMPTY_RESUMES.e;
        long j5 = this.g + h;
        if (i > 0) {
            continuationArr = new Continuation[i];
            Object[] objArr = this.d;
            uhy.d(objArr);
            long j6 = h;
            int i3 = 0;
            while (true) {
                if (h >= j5) {
                    h = j6;
                    break;
                }
                long j7 = h + j4;
                c3 = NO_VALUE.c(objArr, h);
                if (c3 != NO_VALUE.b) {
                    if (c3 != null) {
                        d dVar = (d) c3;
                        int i4 = i3 + 1;
                        continuationArr[i3] = dVar.d;
                        NO_VALUE.c(objArr, h, NO_VALUE.b);
                        long j8 = j6;
                        NO_VALUE.c(objArr, j8, dVar.e);
                        long j9 = j8 + 1;
                        if (i4 >= i) {
                            h = j9;
                            break;
                        }
                        i3 = i4;
                        j6 = j9;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    }
                }
                h = j7;
                j4 = 1;
            }
        }
        int i5 = (int) (h - j2);
        long j10 = getNCollectors() == 0 ? h : j3;
        long max = Math.max(this.h, h - Math.min(this.f, i5));
        if (this.f17478a == 0 && max < j5) {
            Object[] objArr2 = this.d;
            uhy.d(objArr2);
            c2 = NO_VALUE.c(objArr2, max);
            if (uhy.e(c2, NO_VALUE.b)) {
                h++;
                max++;
            }
        }
        b(max, j10, h, j5);
        b();
        return (continuationArr.length == 0) ^ true ? d(continuationArr) : continuationArr;
    }

    private final void b(long j, long j2, long j3, long j4) {
        long min = Math.min(j2, j);
        if (ASSERTIONS_ENABLED.a() && min < j()) {
            throw new AssertionError();
        }
        for (long j5 = j(); j5 < min; j5++) {
            Object[] objArr = this.d;
            uhy.d(objArr);
            NO_VALUE.c(objArr, j5, null);
        }
        this.h = j;
        this.b = j2;
        this.c = (int) (j3 - min);
        this.g = (int) (j4 - j3);
        if (ASSERTIONS_ENABLED.a() && this.c < 0) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && this.g < 0) {
            throw new AssertionError();
        }
        if (ASSERTIONS_ENABLED.a() && this.h > j() + this.c) {
            throw new AssertionError();
        }
    }

    private final void b() {
        Object c2;
        if (this.f17478a != 0 || this.g > 1) {
            Object[] objArr = this.d;
            uhy.d(objArr);
            while (this.g > 0) {
                c2 = NO_VALUE.c(objArr, (j() + g()) - 1);
                if (c2 != NO_VALUE.b) {
                    return;
                }
                this.g--;
                NO_VALUE.c(objArr, j() + g(), null);
            }
        }
    }

    private final Object d(uoa uoaVar) {
        Object obj;
        Continuation<ueu>[] continuationArr = EMPTY_RESUMES.e;
        synchronized (this) {
            long a2 = a(uoaVar);
            if (a2 < 0) {
                obj = NO_VALUE.b;
            } else {
                long j = uoaVar.f17481a;
                Object c2 = c(a2);
                uoaVar.f17481a = a2 + 1;
                continuationArr = b(j);
                obj = c2;
            }
        }
        int length = continuationArr.length;
        int i = 0;
        while (i < length) {
            Continuation<ueu> continuation = continuationArr[i];
            i++;
            if (continuation != null) {
                uel.b bVar = uel.d;
                continuation.resumeWith(uel.b(ueu.d));
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long a(uoa uoaVar) {
        long j = uoaVar.f17481a;
        if (j < h()) {
            return j;
        }
        if (this.f17478a <= 0 && j <= j() && this.g != 0) {
            return j;
        }
        return -1L;
    }

    private final Object c(long j) {
        Object c2;
        Object[] objArr = this.d;
        uhy.d(objArr);
        c2 = NO_VALUE.c(objArr, j);
        return c2 instanceof d ? ((d) c2).e : c2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v9, types: [java.lang.Object, java.lang.Object[]] */
    public final Continuation<ueu>[] d(Continuation<ueu>[] continuationArr) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        uoa uoaVar;
        Continuation<? super ueu> continuation;
        int length = continuationArr.length;
        unz<T> unzVar = this;
        if (((AbstractSharedFlow) unzVar).nCollectors != 0 && (abstractSharedFlowSlotArr = ((AbstractSharedFlow) unzVar).slots) != null) {
            int length2 = abstractSharedFlowSlotArr.length;
            int i = 0;
            while (i < length2) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i];
                i++;
                if (abstractSharedFlowSlot != null && (continuation = (uoaVar = (uoa) abstractSharedFlowSlot).b) != null && a(uoaVar) >= 0) {
                    Continuation<ueu>[] continuationArr2 = continuationArr;
                    continuationArr = continuationArr;
                    if (length >= continuationArr2.length) {
                        ?? copyOf = Arrays.copyOf(continuationArr2, Math.max(2, continuationArr2.length * 2));
                        uhy.a((Object) copyOf, "");
                        continuationArr = copyOf;
                    }
                    continuationArr[length] = continuation;
                    uoaVar.b = null;
                    length++;
                }
            }
        }
        return continuationArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public uoa createSlot() {
        return new uoa();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public uoa[] createSlotArray(int i) {
        return new uoa[i];
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(CoroutineContext context, int capacity, BufferOverflow onBufferOverflow) {
        return NO_VALUE.a(this, context, capacity, onBufferOverflow);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B1\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\nH\u0016R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "Lkotlinx/coroutines/DisposableHandle;", "flow", "Lkotlinx/coroutines/flow/SharedFlowImpl;", "index", "", "value", "", "cont", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/SharedFlowImpl;JLjava/lang/Object;Lkotlin/coroutines/Continuation;)V", "dispose", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class d implements DisposableHandle {

        /* renamed from: a, reason: collision with root package name */
        public final unz<?> f17480a;
        public long c;
        public final Continuation<ueu> d;
        public final Object e;

        /* JADX WARN: Multi-variable type inference failed */
        public d(unz<?> unzVar, long j, Object obj, Continuation<? super ueu> continuation) {
            this.f17480a = unzVar;
            this.c = j;
            this.e = obj;
            this.d = continuation;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            this.f17480a.c(this);
        }
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        Object c2;
        synchronized (this) {
            int i = i();
            if (i == 0) {
                return ufe.b();
            }
            ArrayList arrayList = new ArrayList(i);
            Object[] objArr = this.d;
            uhy.d(objArr);
            for (int i2 = 0; i2 < i; i2++) {
                c2 = NO_VALUE.c(objArr, this.h + i2);
                arrayList.add(c2);
            }
            return arrayList;
        }
    }

    private final Object e(T t, Continuation<? super ueu> continuation) {
        Continuation<ueu>[] continuationArr;
        d dVar;
        ukr ukrVar = new ukr(ugw.a(continuation), 1);
        ukrVar.initCancellability();
        ukr ukrVar2 = ukrVar;
        Continuation<ueu>[] continuationArr2 = EMPTY_RESUMES.e;
        synchronized (this) {
            if (a((unz<T>) t)) {
                uel.b bVar = uel.d;
                ukrVar2.resumeWith(uel.b(ueu.d));
                continuationArr = d(continuationArr2);
                dVar = null;
            } else {
                d dVar2 = new d(this, g() + j(), t, ukrVar2);
                d(dVar2);
                this.g++;
                if (this.f17478a == 0) {
                    continuationArr2 = d(continuationArr2);
                }
                continuationArr = continuationArr2;
                dVar = dVar2;
            }
        }
        if (dVar != null) {
            getOrCreateCancellableContinuation.c(ukrVar2, dVar);
        }
        int length = continuationArr.length;
        int i = 0;
        while (i < length) {
            Continuation<ueu> continuation2 = continuationArr[i];
            i++;
            if (continuation2 != null) {
                uel.b bVar2 = uel.d;
                continuation2.resumeWith(uel.b(ueu.d));
            }
        }
        Object e = ukrVar.e();
        if (e == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e == ugw.a() ? e : ueu.d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c(d dVar) {
        Object c2;
        synchronized (this) {
            if (dVar.c < j()) {
                return;
            }
            Object[] objArr = this.d;
            uhy.d(objArr);
            c2 = NO_VALUE.c(objArr, dVar.c);
            if (c2 != dVar) {
                return;
            }
            NO_VALUE.c(objArr, dVar.c, NO_VALUE.b);
            b();
            ueu ueuVar = ueu.d;
        }
    }

    private final Object c(uoa uoaVar, Continuation<? super ueu> continuation) {
        ukr ukrVar = new ukr(ugw.a(continuation), 1);
        ukrVar.initCancellability();
        ukr ukrVar2 = ukrVar;
        synchronized (this) {
            if (a(uoaVar) < 0) {
                uoaVar.b = ukrVar2;
                uoaVar.b = ukrVar2;
            } else {
                uel.b bVar = uel.d;
                ukrVar2.resumeWith(uel.b(ueu.d));
            }
            ueu ueuVar = ueu.d;
        }
        Object e = ukrVar.e();
        if (e == ugw.a()) {
            probeCoroutineCreated.b(continuation);
        }
        return e == ugw.a() ? e : ueu.d;
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        synchronized (this) {
            b(h(), this.b, h(), f());
            ueu ueuVar = ueu.d;
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.flow.SharedFlowImpl", f = "SharedFlow.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {373, 380, 383}, m = "collect$suspendImpl", n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "this", "collector", "slot", "collectorJob"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    static final class a extends ContinuationImpl {

        /* renamed from: a, reason: collision with root package name */
        Object f17479a;
        Object b;
        Object c;
        Object d;
        int e;
        final /* synthetic */ unz<T> h;
        /* synthetic */ Object i;

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.i = obj;
            this.e |= Integer.MIN_VALUE;
            return unz.a((unz) this.h, (FlowCollector) null, (Continuation) this);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(unz<T> unzVar, Continuation<? super a> continuation) {
            super(continuation);
            this.h = unzVar;
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public final /* synthetic */ class c {
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            c = iArr;
        }
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow, kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t, Continuation<? super ueu> continuation) {
        return a(this, t, continuation);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, Continuation<?> continuation) {
        return a((unz) this, (FlowCollector) flowCollector, (Continuation) continuation);
    }
}
