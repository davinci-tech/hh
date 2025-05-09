package androidx.core.os;

import defpackage.ueu;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 7, 1}, xi = 176)
/* loaded from: classes8.dex */
public final class HandlerKt$postDelayed$runnable$1 implements Runnable {
    final /* synthetic */ Function0<ueu> $action;

    @Override // java.lang.Runnable
    public final void run() {
        this.$action.invoke();
    }

    public HandlerKt$postDelayed$runnable$1(Function0<ueu> function0) {
        this.$action = function0;
    }
}
