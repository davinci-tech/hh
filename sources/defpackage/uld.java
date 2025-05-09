package defpackage;

import com.huawei.hms.network.embedded.g4;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancelHandler;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001BZ\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012%\b\u0002\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0001\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0004HÆ\u0003J&\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003J`\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042%\b\u0002\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u001a\u0010\u001d\u001a\u00020\u000b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010\n\u001a\u00020\u0007J\t\u0010 \u001a\u00020!HÖ\u0001R\u0012\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\f\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R-\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lkotlinx/coroutines/CompletedContinuation;", "", "result", "cancelHandler", "Lkotlinx/coroutines/CancelHandler;", "onCancellation", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "idempotentResume", "cancelCause", "(Ljava/lang/Object;Lkotlinx/coroutines/CancelHandler;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelled", "", "getCancelled", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", WatchFaceConstant.HASHCODE, "", "invokeHandlers", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
final /* data */ class uld {

    /* renamed from: a, reason: collision with root package name */
    public final Object f17454a;
    public final Object b;
    public final Throwable c;
    public final CancelHandler d;
    public final Function1<Throwable, ueu> e;

    /* JADX WARN: Multi-variable type inference failed */
    public uld(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, ueu> function1, Object obj2, Throwable th) {
        this.b = obj;
        this.d = cancelHandler;
        this.e = function1;
        this.f17454a = obj2;
        this.c = th;
    }

    public /* synthetic */ uld(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, uib uibVar) {
        this(obj, (i & 2) != 0 ? null : cancelHandler, (i & 4) != 0 ? null : function1, (i & 8) != 0 ? null : obj2, (i & 16) != 0 ? null : th);
    }

    public final boolean d() {
        return this.c != null;
    }

    public final void e(ukr<?> ukrVar, Throwable th) {
        CancelHandler cancelHandler = this.d;
        if (cancelHandler != null) {
            ukrVar.c(cancelHandler, th);
        }
        Function1<Throwable, ueu> function1 = this.e;
        if (function1 == null) {
            return;
        }
        ukrVar.d(function1, th);
    }

    public String toString() {
        return "CompletedContinuation(result=" + this.b + ", cancelHandler=" + this.d + ", onCancellation=" + this.e + ", idempotentResume=" + this.f17454a + ", cancelCause=" + this.c + g4.l;
    }

    public int hashCode() {
        Object obj = this.b;
        int hashCode = obj == null ? 0 : obj.hashCode();
        CancelHandler cancelHandler = this.d;
        int hashCode2 = cancelHandler == null ? 0 : cancelHandler.hashCode();
        Function1<Throwable, ueu> function1 = this.e;
        int hashCode3 = function1 == null ? 0 : function1.hashCode();
        Object obj2 = this.f17454a;
        int hashCode4 = obj2 == null ? 0 : obj2.hashCode();
        Throwable th = this.c;
        return (((((((hashCode * 31) + hashCode2) * 31) + hashCode3) * 31) + hashCode4) * 31) + (th != null ? th.hashCode() : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof uld)) {
            return false;
        }
        uld uldVar = (uld) other;
        return uhy.e(this.b, uldVar.b) && uhy.e(this.d, uldVar.d) && uhy.e(this.e, uldVar.e) && uhy.e(this.f17454a, uldVar.f17454a) && uhy.e(this.c, uldVar.c);
    }

    public final uld c(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, ueu> function1, Object obj2, Throwable th) {
        return new uld(obj, cancelHandler, function1, obj2, th);
    }

    public static /* synthetic */ uld c(uld uldVar, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i, Object obj3) {
        if ((i & 1) != 0) {
            obj = uldVar.b;
        }
        if ((i & 2) != 0) {
            cancelHandler = uldVar.d;
        }
        CancelHandler cancelHandler2 = cancelHandler;
        if ((i & 4) != 0) {
            function1 = uldVar.e;
        }
        Function1 function12 = function1;
        if ((i & 8) != 0) {
            obj2 = uldVar.f17454a;
        }
        Object obj4 = obj2;
        if ((i & 16) != 0) {
            th = uldVar.c;
        }
        return uldVar.c(obj, cancelHandler2, function12, obj4, th);
    }
}
