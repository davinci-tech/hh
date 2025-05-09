package defpackage;

import com.huawei.hms.network.embedded.g4;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.watchface.utils.constants.WatchFaceConstant;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ThreadContextElement;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0081\b\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\b\u0010\u0016\u001a\u00020\u0002H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/CoroutineId;", "Lkotlinx/coroutines/ThreadContextElement;", "", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", WatchFaceConstant.HASHCODE, "", "restoreThreadContext", "", ParamConstants.Param.CONTEXT, "Lkotlin/coroutines/CoroutineContext;", "oldState", "toString", "updateThreadContext", "Key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final /* data */ class ulf extends AbstractCoroutineContextElement implements ThreadContextElement<String> {

    /* renamed from: a, reason: collision with root package name */
    public static final c f17455a = new c(null);
    private final long e;

    /* renamed from: e, reason: from getter */
    public final long getE() {
        return this.e;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineId$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineId;", "()V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class c implements CoroutineContext.Key<ulf> {
        private c() {
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }
    }

    public ulf(long j) {
        super(f17455a);
        this.e = j;
    }

    public String toString() {
        return "CoroutineId(" + this.e + g4.l;
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public String updateThreadContext(CoroutineContext coroutineContext) {
        String str;
        uli uliVar = (uli) coroutineContext.get(uli.b);
        if (uliVar == null || (str = uliVar.getE()) == null) {
            str = "coroutine";
        }
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        int b = ujy.b(name, " @", 0, false, 6, null);
        if (b < 0) {
            b = name.length();
        }
        StringBuilder sb = new StringBuilder(str.length() + b + 10);
        String substring = name.substring(0, b);
        uhy.a(substring, "");
        sb.append(substring);
        sb.append(" @");
        sb.append(str);
        sb.append('#');
        sb.append(getE());
        String sb2 = sb.toString();
        uhy.a(sb2, "");
        currentThread.setName(sb2);
        return name;
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void restoreThreadContext(CoroutineContext coroutineContext, String str) {
        Thread.currentThread().setName(str);
    }

    public int hashCode() {
        return Long.hashCode(this.e);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ulf) && this.e == ((ulf) other).e;
    }
}
