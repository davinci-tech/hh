package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;

/* loaded from: classes2.dex */
public final class zzas implements ChannelApi.ChannelListener {
    private final ChannelClient.ChannelCallback zzch;

    public zzas(ChannelClient.ChannelCallback channelCallback) {
        this.zzch = channelCallback;
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onChannelOpened(Channel channel) {
        zzay zza;
        ChannelClient.ChannelCallback channelCallback = this.zzch;
        zza = zzao.zza(channel);
        channelCallback.onChannelOpened(zza);
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onChannelClosed(Channel channel, int i, int i2) {
        zzay zza;
        ChannelClient.ChannelCallback channelCallback = this.zzch;
        zza = zzao.zza(channel);
        channelCallback.onChannelClosed(zza, i, i2);
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onInputClosed(Channel channel, int i, int i2) {
        zzay zza;
        ChannelClient.ChannelCallback channelCallback = this.zzch;
        zza = zzao.zza(channel);
        channelCallback.onInputClosed(zza, i, i2);
    }

    @Override // com.google.android.gms.wearable.ChannelApi.ChannelListener
    public final void onOutputClosed(Channel channel, int i, int i2) {
        zzay zza;
        ChannelClient.ChannelCallback channelCallback = this.zzch;
        zza = zzao.zza(channel);
        channelCallback.onOutputClosed(zza, i, i2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.zzch.equals(((zzas) obj).zzch);
    }

    public final int hashCode() {
        return this.zzch.hashCode();
    }
}
