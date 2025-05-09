package defpackage;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.watchface.videoedit.gles.Constant;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import org.apache.commons.io.FileUtils;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0016\b\u0000\u0018\u0000 /*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001:\u0002/0B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0005¢\u0006\u0004\b\u0012\u0010\u0013J3\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000e2\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\t\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\u0013J-\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00010\u001b\"\u0004\b\u0001\u0010\u00182\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\"\u0010#J3\u0010&\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u000e2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0003H\u0002¢\u0006\u0004\b&\u0010'R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010(R\u0011\u0010)\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b)\u0010\u0013R\u0014\u0010*\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010(R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010+R\u0011\u0010.\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00061"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "", ExifInterface.LONGITUDE_EAST, "", "capacity", "", "singleConsumer", "<init>", "(IZ)V", FunctionSetBeanReader.BI_ELEMENT, "addLast", "(Ljava/lang/Object;)I", "", "state", "Lkotlinx/coroutines/internal/Core;", "allocateNextCopy", "(J)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "allocateOrGetNextCopy", "close", "()Z", "index", "fillPlaceholder", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "isClosed", "R", "Lkotlin/Function1;", "transform", "", "map", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "markFrozen", "()J", "next", "()Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "removeFirstOrNull", "()Ljava/lang/Object;", "oldHead", "newHead", "removeSlowPath", "(II)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "I", "isEmpty", Constant.MASK, "Z", "getSize", "()I", "size", "Companion", "Placeholder", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class upo<E> {
    private volatile /* synthetic */ Object _next = null;
    private volatile /* synthetic */ long _state = 0;
    private /* synthetic */ AtomicReferenceArray c;
    private final int g;
    private final int i;
    private final boolean j;
    public static final d b = new d(null);

    /* renamed from: a, reason: collision with root package name */
    public static final upu f17499a = new upu("REMOVE_FROZEN");
    private static final /* synthetic */ AtomicReferenceFieldUpdater d = AtomicReferenceFieldUpdater.newUpdater(upo.class, Object.class, "_next");
    private static final /* synthetic */ AtomicLongFieldUpdater e = AtomicLongFieldUpdater.newUpdater(upo.class, "_state");

    public upo(int i, boolean z) {
        this.g = i;
        this.j = z;
        int i2 = i - 1;
        this.i = i2;
        this.c = new AtomicReferenceArray(i);
        if (i2 > 1073741823) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if ((i & i2) != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
    }

    public final boolean d() {
        long j = this._state;
        return ((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30));
    }

    public final int e() {
        long j = this._state;
        return (((int) ((j & 1152921503533105152L) >> 30)) - ((int) (1073741823 & j))) & 1073741823;
    }

    private final upo<E> a(int i, E e2) {
        Object obj = this.c.get(this.i & i);
        if (!(obj instanceof e) || ((e) obj).c != i) {
            return null;
        }
        this.c.set(i & this.i, e2);
        return this;
    }

    public final upo<E> a() {
        return d(f());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final upo<E> b(long j) {
        upo<E> upoVar = new upo<>(this.g * 2, this.j);
        int i = (int) (1073741823 & j);
        int i2 = (int) ((1152921503533105152L & j) >> 30);
        while (true) {
            int i3 = this.i;
            int i4 = i & i3;
            if (i4 != (i3 & i2)) {
                Object obj = this.c.get(i4);
                if (obj == null) {
                    obj = new e(i);
                }
                upoVar.c.set(upoVar.i & i, obj);
                i++;
            } else {
                upoVar._state = b.b(j, FileUtils.ONE_EB);
                return upoVar;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", "", "index", "", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class e {
        public final int c;

        public e(int i) {
            this.c = i;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001a\u00020\u0004*\u00020\tJ\u0012\u0010\u0017\u001a\u00020\t*\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0004J\u0012\u0010\u0019\u001a\u00020\t*\u00020\t2\u0006\u0010\u001a\u001a\u00020\u0004JP\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0001\u0010\u001c*\u00020\t26\u0010\u001d\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0086\b¢\u0006\u0002\u0010#J\u0015\u0010$\u001a\u00020\t*\u00020\t2\u0006\u0010%\u001a\u00020\tH\u0086\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", "", "()V", "ADD_CLOSED", "", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "MIN_ADD_SPIN_CAPACITY", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "TAIL_MASK", "TAIL_SHIFT", "addFailReason", "updateHead", "newHead", "updateTail", "newTail", "withState", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "wo", "other", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class d {
        public final long b(long j, long j2) {
            return j & (~j2);
        }

        public final int e(long j) {
            return (j & 2305843009213693952L) != 0 ? 2 : 1;
        }

        private d() {
        }

        public final long e(long j, int i) {
            return b(j, 1073741823L) | i;
        }

        public final long a(long j, int i) {
            return b(j, 1152921503533105152L) | (i << 30);
        }

        public /* synthetic */ d(uib uibVar) {
            this();
        }
    }

    public final boolean b() {
        long j;
        do {
            j = this._state;
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((FileUtils.ONE_EB & j) != 0) {
                return false;
            }
        } while (!e.compareAndSet(this, j, j | 2305843009213693952L));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x004c, code lost:
    
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int c(E r13) {
        /*
            r12 = this;
        L0:
            long r2 = r12._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r6 = 0
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 == 0) goto L12
            upo$d r13 = defpackage.upo.b
            int r13 = r13.e(r2)
            return r13
        L12:
            upo$d r0 = defpackage.upo.b
            r4 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r4 = r4 & r2
            int r1 = (int) r4
            r4 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r4 = r4 & r2
            r8 = 30
            long r4 = r4 >> r8
            int r8 = (int) r4
            int r9 = r12.i
            int r4 = r8 + 2
            r4 = r4 & r9
            r5 = r1 & r9
            r10 = 1
            if (r4 != r5) goto L2e
            return r10
        L2e:
            boolean r4 = r12.j
            r5 = 1073741823(0x3fffffff, float:1.9999999)
            if (r4 != 0) goto L4d
            java.util.concurrent.atomic.AtomicReferenceArray r4 = r12.c
            r11 = r8 & r9
            java.lang.Object r4 = r4.get(r11)
            if (r4 == 0) goto L4d
            int r0 = r12.g
            r2 = 1024(0x400, float:1.435E-42)
            if (r0 < r2) goto L4c
            int r8 = r8 - r1
            r1 = r8 & r5
            int r0 = r0 >> 1
            if (r1 <= r0) goto L0
        L4c:
            return r10
        L4d:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = defpackage.upo.e
            int r4 = r8 + 1
            r4 = r4 & r5
            long r4 = r0.a(r2, r4)
            r0 = r1
            r1 = r12
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L0
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r12.c
            r1 = r8 & r9
            r0.set(r1, r13)
            r0 = r12
        L66:
            long r1 = r0._state
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            int r1 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r1 != 0) goto L70
            goto L7a
        L70:
            upo r0 = r0.a()
            upo r0 = r0.a(r8, r13)
            if (r0 != 0) goto L66
        L7a:
            r13 = 0
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.upo.c(java.lang.Object):int");
    }

    public final Object c() {
        while (true) {
            long j = this._state;
            if ((FileUtils.ONE_EB & j) != 0) {
                return f17499a;
            }
            d dVar = b;
            int i = (int) (1073741823 & j);
            int i2 = this.i;
            int i3 = ((int) ((1152921503533105152L & j) >> 30)) & i2;
            int i4 = i2 & i;
            if (i3 == i4) {
                return null;
            }
            Object obj = this.c.get(i4);
            if (obj == null) {
                if (this.j) {
                    return null;
                }
            } else {
                if (obj instanceof e) {
                    return null;
                }
                int i5 = (i + 1) & 1073741823;
                if (e.compareAndSet(this, j, dVar.e(j, i5))) {
                    this.c.set(this.i & i, null);
                    return obj;
                }
                if (this.j) {
                    upo<E> upoVar = this;
                    do {
                        upoVar = upoVar.e(i, i5);
                    } while (upoVar != null);
                    return obj;
                }
            }
        }
    }

    private final upo<E> e(int i, int i2) {
        long j;
        d dVar;
        int i3;
        do {
            j = this._state;
            dVar = b;
            i3 = (int) (1073741823 & j);
            if (ASSERTIONS_ENABLED.a() && i3 != i) {
                throw new AssertionError();
            }
            if ((FileUtils.ONE_EB & j) != 0) {
                return a();
            }
        } while (!e.compareAndSet(this, j, dVar.e(j, i2)));
        this.c.set(this.i & i3, null);
        return null;
    }

    private final long f() {
        long j;
        long j2;
        do {
            j = this._state;
            if ((j & FileUtils.ONE_EB) != 0) {
                return j;
            }
            j2 = j | FileUtils.ONE_EB;
        } while (!e.compareAndSet(this, j, j2));
        return j2;
    }

    private final upo<E> d(long j) {
        while (true) {
            upo<E> upoVar = (upo) this._next;
            if (upoVar != null) {
                return upoVar;
            }
            ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(d, this, (Object) null, b(j));
        }
    }
}
