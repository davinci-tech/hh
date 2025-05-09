package kotlinx.coroutines.flow;

import com.huawei.hiai.awareness.client.AwarenessRequest;
import defpackage.uny;
import defpackage.uod;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/flow/SharingStarted;", "", AwarenessRequest.Field.COMMAND, "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "Companion", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public interface SharingStarted {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.f14494a;

    Flow<SharingCommand> command(StateFlow<Integer> subscriptionCount);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/SharingStarted$Companion;", "", "()V", "Eagerly", "Lkotlinx/coroutines/flow/SharingStarted;", "getEagerly", "()Lkotlinx/coroutines/flow/SharingStarted;", "Lazily", "getLazily", "WhileSubscribed", "stopTimeoutMillis", "", "replayExpirationMillis", "kotlinx-coroutines-core"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.flow.SharingStarted$d, reason: from kotlin metadata */
    public static final class Companion {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ Companion f14494a = new Companion();
        private static final SharingStarted c = new uny();
        private static final SharingStarted e = new uod();

        private Companion() {
        }
    }
}
