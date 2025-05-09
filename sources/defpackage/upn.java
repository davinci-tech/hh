package defpackage;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ-\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u0012\"\u0004\b\u0001\u0010\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000eR\u0011\u0010\u001b\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueue;", "", ExifInterface.LONGITUDE_EAST, "", "singleConsumer", "<init>", "(Z)V", FunctionSetBeanReader.BI_ELEMENT, "addLast", "(Ljava/lang/Object;)Z", "", "close", "()V", "isClosed", "()Z", "R", "Lkotlin/Function1;", "transform", "", "map", "(Lkotlin/jvm/functions/Function1;)Ljava/util/List;", "removeFirstOrNull", "()Ljava/lang/Object;", "isEmpty", "", "getSize", "()I", "size", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public class upn<E> {
    private static final /* synthetic */ AtomicReferenceFieldUpdater c = AtomicReferenceFieldUpdater.newUpdater(upn.class, Object.class, "_cur");
    private volatile /* synthetic */ Object _cur;

    public upn(boolean z) {
        this._cur = new upo(8, z);
    }

    public final int c() {
        return ((upo) this._cur).e();
    }

    public final void a() {
        while (true) {
            upo upoVar = (upo) this._cur;
            if (upoVar.b()) {
                return;
            } else {
                ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, upoVar, upoVar.a());
            }
        }
    }

    public final boolean c(E e) {
        while (true) {
            upo upoVar = (upo) this._cur;
            int c2 = upoVar.c(e);
            if (c2 == 0) {
                return true;
            }
            if (c2 == 1) {
                ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, upoVar, upoVar.a());
            } else if (c2 == 2) {
                return false;
            }
        }
    }

    public final E d() {
        while (true) {
            upo upoVar = (upo) this._cur;
            E e = (E) upoVar.c();
            if (e != upo.f17499a) {
                return e;
            }
            ProcessGlobalConfig$$ExternalSyntheticBackportWithForwarding0.m(c, this, upoVar, upoVar.a());
        }
    }
}
