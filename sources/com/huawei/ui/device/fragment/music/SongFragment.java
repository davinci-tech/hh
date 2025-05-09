package com.huawei.ui.device.fragment.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;

/* loaded from: classes6.dex */
public class SongFragment extends BaseLocalMusicFragment {
    @Override // com.huawei.ui.device.fragment.music.BaseLocalMusicFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        super.c(MusicSong.SORT_TYPE_SONG);
        return onCreateView;
    }

    @Override // com.huawei.ui.device.fragment.music.BaseLocalMusicFragment
    public boolean c() {
        return super.c();
    }
}
