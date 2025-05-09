package defpackage;

import kotlin.Metadata;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt", "kotlinx/coroutines/channels/ChannelsKt__DeprecatedKt"}, k = 4, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class une {
    public static final void e(ReceiveChannel<?> receiveChannel, Throwable th) {
        DEFAULT_CLOSE_MESSAGE.d(receiveChannel, th);
    }
}
