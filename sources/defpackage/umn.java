package defpackage;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u000f\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\n\u0010\n\u001a\u0004\u0018\u00010\u0000H\u0016R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/coroutines/TimeoutCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lkotlinx/coroutines/CopyableThrowable;", "message", "", "(Ljava/lang/String;)V", "coroutine", "Lkotlinx/coroutines/Job;", "(Ljava/lang/String;Lkotlinx/coroutines/Job;)V", "createCopy", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class umn extends CancellationException implements CopyableThrowable<umn> {

    /* renamed from: a, reason: collision with root package name */
    public final Job f17467a;

    public umn(String str, Job job) {
        super(str);
        this.f17467a = job;
    }

    @Override // kotlinx.coroutines.CopyableThrowable
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public umn createCopy() {
        String message = getMessage();
        if (message == null) {
            message = "";
        }
        umn umnVar = new umn(message, this.f17467a);
        umnVar.initCause(this);
        return umnVar;
    }
}
