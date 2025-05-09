package defpackage;

import com.huawei.operation.ble.BleConstants;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BY\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012:\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0011H\u0096\u0002RB\u0010\b\u001a6\u0012\u0004\u0012\u00020\u0004\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\r0\t¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", "input", "", "startIndex", "", BleConstants.LIMIT, "getNextMatch", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "currentIndex", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes7.dex */
final class ujs implements Sequence<uiv> {

    /* renamed from: a, reason: collision with root package name */
    private final CharSequence f17444a;
    private final Function2<CharSequence, Integer, ueo<Integer, Integer>> b;
    private final int d;
    private final int e;

    /* JADX WARN: Multi-variable type inference failed */
    public ujs(CharSequence charSequence, int i, int i2, Function2<? super CharSequence, ? super Integer, ueo<Integer, Integer>> function2) {
        uhy.e((Object) charSequence, "");
        uhy.e((Object) function2, "");
        this.f17444a = charSequence;
        this.d = i;
        this.e = i2;
        this.b = function2;
    }

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\t\u0010\u0019\u001a\u00020\u001aH\u0096\u0002J\t\u0010\u001b\u001a\u00020\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0006\"\u0004\b\u0013\u0010\bR\u001a\u0010\u0014\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006\u001c"}, d2 = {"kotlin/text/DelimitedRangesSequence$iterator$1", "", "Lkotlin/ranges/IntRange;", "counter", "", "getCounter", "()I", "setCounter", "(I)V", "currentStartIndex", "getCurrentStartIndex", "setCurrentStartIndex", "nextItem", "getNextItem", "()Lkotlin/ranges/IntRange;", "setNextItem", "(Lkotlin/ranges/IntRange;)V", "nextSearchIndex", "getNextSearchIndex", "setNextSearchIndex", "nextState", "getNextState", "setNextState", "calcNext", "", "hasNext", "", "next", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class a implements Iterator<uiv>, KMappedMarker {

        /* renamed from: a, reason: collision with root package name */
        private int f17445a;
        private uiv c;
        private int d;
        private int e;
        private int h = -1;

        a() {
            int d = uja.d(ujs.this.d, 0, ujs.this.f17444a.length());
            this.f17445a = d;
            this.d = d;
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
        
            if (r0 < r6.b.e) goto L9;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final void a() {
            /*
                r6 = this;
                int r0 = r6.d
                r1 = 0
                if (r0 >= 0) goto Lc
                r6.h = r1
                r0 = 0
                r6.c = r0
                goto L9e
            Lc:
                ujs r0 = defpackage.ujs.this
                int r0 = defpackage.ujs.d(r0)
                r2 = -1
                r3 = 1
                if (r0 <= 0) goto L23
                int r0 = r6.e
                int r0 = r0 + r3
                r6.e = r0
                ujs r4 = defpackage.ujs.this
                int r4 = defpackage.ujs.d(r4)
                if (r0 >= r4) goto L31
            L23:
                int r0 = r6.d
                ujs r4 = defpackage.ujs.this
                java.lang.CharSequence r4 = defpackage.ujs.e(r4)
                int r4 = r4.length()
                if (r0 <= r4) goto L47
            L31:
                uiv r0 = new uiv
                int r1 = r6.f17445a
                ujs r4 = defpackage.ujs.this
                java.lang.CharSequence r4 = defpackage.ujs.e(r4)
                int r4 = defpackage.ujy.e(r4)
                r0.<init>(r1, r4)
                r6.c = r0
                r6.d = r2
                goto L9c
            L47:
                ujs r0 = defpackage.ujs.this
                kotlin.jvm.functions.Function2 r0 = defpackage.ujs.b(r0)
                ujs r4 = defpackage.ujs.this
                java.lang.CharSequence r4 = defpackage.ujs.e(r4)
                int r5 = r6.d
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                java.lang.Object r0 = r0.invoke(r4, r5)
                ueo r0 = (defpackage.ueo) r0
                if (r0 != 0) goto L77
                uiv r0 = new uiv
                int r1 = r6.f17445a
                ujs r4 = defpackage.ujs.this
                java.lang.CharSequence r4 = defpackage.ujs.e(r4)
                int r4 = defpackage.ujy.e(r4)
                r0.<init>(r1, r4)
                r6.c = r0
                r6.d = r2
                goto L9c
            L77:
                java.lang.Object r2 = r0.b()
                java.lang.Number r2 = (java.lang.Number) r2
                int r2 = r2.intValue()
                java.lang.Object r0 = r0.d()
                java.lang.Number r0 = (java.lang.Number) r0
                int r0 = r0.intValue()
                int r4 = r6.f17445a
                uiv r4 = defpackage.uja.c(r4, r2)
                r6.c = r4
                int r2 = r2 + r0
                r6.f17445a = r2
                if (r0 != 0) goto L99
                r1 = r3
            L99:
                int r2 = r2 + r1
                r6.d = r2
            L9c:
                r6.h = r3
            L9e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: ujs.a.a():void");
        }

        @Override // java.util.Iterator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public uiv next() {
            if (this.h == -1) {
                a();
            }
            if (this.h == 0) {
                throw new NoSuchElementException();
            }
            uiv uivVar = this.c;
            uhy.b(uivVar, "");
            this.c = null;
            this.h = -1;
            return uivVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.h == -1) {
                a();
            }
            return this.h == 1;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<uiv> iterator() {
        return new a();
    }
}
