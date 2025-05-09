package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageClient;

/* loaded from: classes8.dex */
final class zzfc extends RegisterListenerMethod<zzhg, MessageClient.OnMessageReceivedListener> {
    private final IntentFilter[] zzba;
    private final ListenerHolder<MessageApi.MessageListener> zzbz;
    private final MessageClient.OnMessageReceivedListener zzej;

    private zzfc(MessageClient.OnMessageReceivedListener onMessageReceivedListener, IntentFilter[] intentFilterArr, ListenerHolder<MessageClient.OnMessageReceivedListener> listenerHolder) {
        super(listenerHolder);
        this.zzej = onMessageReceivedListener;
        this.zzba = intentFilterArr;
        this.zzbz = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final /* synthetic */ void registerListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgh(taskCompletionSource), this.zzej, this.zzbz, this.zzba);
    }
}
