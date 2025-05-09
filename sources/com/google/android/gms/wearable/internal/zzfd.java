package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wearable.MessageClient;

/* loaded from: classes8.dex */
final class zzfd extends UnregisterListenerMethod<zzhg, MessageClient.OnMessageReceivedListener> {
    private final MessageClient.OnMessageReceivedListener zzej;

    private zzfd(MessageClient.OnMessageReceivedListener onMessageReceivedListener, ListenerHolder.ListenerKey<MessageClient.OnMessageReceivedListener> listenerKey) {
        super(listenerKey);
        this.zzej = onMessageReceivedListener;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final /* synthetic */ void unregisterListener(zzhg zzhgVar, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzhgVar.zza(new zzgg(taskCompletionSource), this.zzej);
    }
}
