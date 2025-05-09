package com.huawei.android.airsharing.client;

import com.huawei.android.airsharing.api.PlayInfo;
import com.huawei.android.airsharing.api.TrackInfoSet;
import com.huawei.android.airsharing.client.IRemotePlayerListener;

/* loaded from: classes8.dex */
public class RemotePlayerListenerImpl extends IRemotePlayerListener.Stub {
    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onKeyRequest(String str, byte[] bArr) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onMediaItemChanged(PlayInfo playInfo) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onPlaySpeedChanged(float f) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onPlaySpeedListChanged(float[] fArr) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onPlayerError(int i, String str) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onPlayerStatusChanged(int i, boolean z) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onPositionChanged(int i, int i2, int i3) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onRepeatModeChanged(int i) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onTrackChanged(TrackInfoSet trackInfoSet) {
    }

    @Override // com.huawei.android.airsharing.client.IRemotePlayerListener
    public void onVolumeChanged(int i) {
    }
}
