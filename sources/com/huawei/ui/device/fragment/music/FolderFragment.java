package com.huawei.ui.device.fragment.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.device.activity.music.LocalMusicResourceActivity;

/* loaded from: classes6.dex */
public class FolderFragment extends BaseLocalMusicFragment {
    @Override // com.huawei.ui.device.fragment.music.BaseLocalMusicFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        super.c(MusicSong.SORT_TYPE_FOLDER);
        return onCreateView;
    }

    @Override // com.huawei.ui.device.fragment.music.BaseLocalMusicFragment
    public boolean c() {
        HealthToolBar c;
        if (this.b == null) {
            return false;
        }
        if (this.b.a() == 0) {
            if (getActivity() != null && (getActivity() instanceof LocalMusicResourceActivity)) {
                LocalMusicResourceActivity localMusicResourceActivity = getActivity() instanceof LocalMusicResourceActivity ? (LocalMusicResourceActivity) getActivity() : null;
                if (localMusicResourceActivity != null && (c = localMusicResourceActivity.c()) != null) {
                    c.setVisibility(8);
                }
            }
            this.b.e(3);
            this.b.a(this.j);
            this.b.notifyDataSetChanged();
            return false;
        }
        return super.c();
    }
}
