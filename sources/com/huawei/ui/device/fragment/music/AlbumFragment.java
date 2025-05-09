package com.huawei.ui.device.fragment.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.activity.music.LocalMusicResourceActivity;

/* loaded from: classes6.dex */
public class AlbumFragment extends BaseLocalMusicFragment {
    @Override // com.huawei.ui.device.fragment.music.BaseLocalMusicFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        super.c(MusicSong.SORT_TYPE_ALBUM);
        return onCreateView;
    }

    @Override // com.huawei.ui.device.fragment.music.BaseLocalMusicFragment
    public boolean c() {
        HealthToolBar c;
        if (this.b == null) {
            return false;
        }
        if (this.b.a() == 0) {
            if (getActivity() != null && (getActivity() instanceof LocalMusicResourceActivity) && (c = ((LocalMusicResourceActivity) getActivity()).c()) != null) {
                c.setVisibility(8);
            }
            this.b.e(2);
            this.b.a(this.j);
            this.b.notifyDataSetChanged();
            return false;
        }
        return super.c();
    }
}
