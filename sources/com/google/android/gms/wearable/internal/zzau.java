package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;
import javax.annotation.Nullable;

/* loaded from: classes8.dex */
final class zzau extends UnregisterListenerMethod<zzhg, ChannelClient.ChannelCallback> {

    @Nullable
    private final String zzce;
    private final ChannelApi.ChannelListener zzcf;

    zzau(ChannelApi.ChannelListener channelListener, @Nullable String str, ListenerHolder.ListenerKey<ChannelClient.ChannelCallback> listenerKey) {
        super(listenerKey);
        this.zzcf = channelListener;
        this.zzce = str;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgg(taskCompletionSource), this.zzcf, this.zzce);
    }
}
