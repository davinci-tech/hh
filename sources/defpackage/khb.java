package defpackage;

import com.huawei.hwcommonmodel.datatypes.MusicInfo;
import com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface;

/* loaded from: classes5.dex */
public class khb implements ControlInterface {
    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void controlMusic(int i) {
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void controlVolume(boolean z) {
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public MusicInfo getMusicInfo() {
        return null;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public int getPlayState() {
        return 0;
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void registerMusicCallback(ControlInterface.MusicChangeCallback musicChangeCallback) {
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void removeMusicUpdate() {
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void setVolume(int i) {
    }

    @Override // com.huawei.hwdevice.phoneprocess.mgr.musiccontrol.ControlInterface
    public void unRegisterMusicCallback() {
    }
}
