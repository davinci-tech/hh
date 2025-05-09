package com.huawei.hwdevice.phoneprocess.mgr.musiccontrol;

import com.huawei.hwcommonmodel.datatypes.MusicInfo;

/* loaded from: classes5.dex */
public interface ControlInterface {

    public interface MusicChangeCallback {
        void onMusicChanged();

        void onMusicDiedTimeOut();
    }

    void controlMusic(int i);

    void controlVolume(boolean z);

    MusicInfo getMusicInfo();

    int getPlayState();

    void registerMusicCallback(MusicChangeCallback musicChangeCallback);

    void removeMusicUpdate();

    void setVolume(int i);

    void unRegisterMusicCallback();
}
