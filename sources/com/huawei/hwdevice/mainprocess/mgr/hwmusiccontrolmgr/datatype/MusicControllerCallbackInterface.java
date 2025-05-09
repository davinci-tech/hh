package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import java.util.List;

/* loaded from: classes5.dex */
public interface MusicControllerCallbackInterface {
    void onGetFolders(List<MusicFolderStruct> list, int i);

    void onGetMusicFileInfo(int i, String str, int i2);

    void onGetNegotiationData(NegotiationData negotiationData, int i);
}
