package defpackage;

import com.huawei.watchface.utils.constants.WatchFaceConstant;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\n\u0010\u000b\u001a\u0004\u0018\u00010\u0000H\u0016J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0005H\u0016R\u0010\u0010\b\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/JobCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lkotlinx/coroutines/CopyableThrowable;", "message", "", "cause", "", "job", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Ljava/lang/Throwable;Lkotlinx/coroutines/Job;)V", "createCopy", "equals", "", "other", "", "fillInStackTrace", WatchFaceConstant.HASHCODE, "", "toString", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class umg extends CancellationException implements CopyableThrowable<umg> {
    public final Job c;

    public umg(String str, Throwable th, Job job) {
        super(str);
        this.c = job;
        if (th != null) {
            initCause(th);
        }
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        if (ASSERTIONS_ENABLED.d()) {
            return super.fillInStackTrace();
        }
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public umg createCopy() {
        if (!ASSERTIONS_ENABLED.d()) {
            return null;
        }
        String message = getMessage();
        uhy.d((Object) message);
        return new umg(message, this, this.c);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.c;
    }

    public boolean equals(Object other) {
        if (other != this) {
            if (other instanceof umg) {
                umg umgVar = (umg) other;
                if (!uhy.e((Object) umgVar.getMessage(), (Object) getMessage()) || !uhy.e(umgVar.c, this.c) || !uhy.e(umgVar.getCause(), getCause())) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String message = getMessage();
        uhy.d((Object) message);
        int hashCode = message.hashCode();
        int hashCode2 = this.c.hashCode();
        Throwable cause = getCause();
        return (((hashCode * 31) + hashCode2) * 31) + (cause == null ? 0 : cause.hashCode());
    }
}
