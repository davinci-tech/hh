package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr;

import android.app.Notification;
import android.os.Binder;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.hwdevice.mainprocess.service.SyncMusicService;
import java.util.List;

/* loaded from: classes5.dex */
public class SyncMusicBinder extends Binder {

    /* renamed from: a, reason: collision with root package name */
    private SyncMusicStatusListener f6275a;
    private SyncMusicService c;

    public interface SyncMusicStatusListener {
        void onBlueToothDisconnect(List<MusicSong> list, List<MusicSong> list2);

        void onGetSyncMusicNameAndTotalProgress(String str, String str2);

        void onGetSyncMusicProgress(int i);

        void onGetSyncTaskRunningStatus(boolean z, int i);

        void onSyncAllMusicsFinished(List<MusicSong> list, List<MusicSong> list2);

        void onSyncOneMusicFinished(MusicSong musicSong);
    }

    public SyncMusicBinder(SyncMusicService syncMusicService) {
        this.c = syncMusicService;
    }

    public void setSyncMusicListener(SyncMusicStatusListener syncMusicStatusListener) {
        this.f6275a = syncMusicStatusListener;
    }

    public SyncMusicService getService() {
        return this.c;
    }

    public void notifyUiIsSyncMusicTaskRunning() {
        SyncMusicService syncMusicService;
        SyncMusicStatusListener syncMusicStatusListener = this.f6275a;
        if (syncMusicStatusListener == null || (syncMusicService = this.c) == null || syncMusicService.e() == null) {
            return;
        }
        syncMusicStatusListener.onGetSyncTaskRunningStatus(this.c.i(), this.c.e().getMusicAppType());
    }

    public void notifyUiCurrentMusicNameAndTotalProgress() {
        SyncMusicService syncMusicService;
        SyncMusicStatusListener syncMusicStatusListener = this.f6275a;
        if (syncMusicStatusListener == null || (syncMusicService = this.c) == null) {
            return;
        }
        syncMusicStatusListener.onGetSyncMusicNameAndTotalProgress(syncMusicService.b(), this.c.c());
    }

    public void notifyUiCurrentMusicProgress() {
        SyncMusicService syncMusicService;
        SyncMusicStatusListener syncMusicStatusListener = this.f6275a;
        if (syncMusicStatusListener == null || (syncMusicService = this.c) == null) {
            return;
        }
        syncMusicStatusListener.onGetSyncMusicProgress(syncMusicService.a());
    }

    public void notifyUiCurrentMusicFinish() {
        SyncMusicService syncMusicService;
        SyncMusicStatusListener syncMusicStatusListener = this.f6275a;
        if (syncMusicStatusListener == null || (syncMusicService = this.c) == null) {
            return;
        }
        syncMusicStatusListener.onSyncOneMusicFinished(syncMusicService.e());
    }

    public void notifyUiAllMusicsFinish() {
        SyncMusicService syncMusicService;
        SyncMusicStatusListener syncMusicStatusListener = this.f6275a;
        if (syncMusicStatusListener == null || (syncMusicService = this.c) == null) {
            return;
        }
        syncMusicStatusListener.onSyncAllMusicsFinished(syncMusicService.g(), this.c.h());
    }

    public void notifyServiceCancel() {
        this.c.d();
    }

    public void notifyUiBlueToothDisconnect() {
        SyncMusicService syncMusicService;
        SyncMusicStatusListener syncMusicStatusListener = this.f6275a;
        if (syncMusicStatusListener == null || (syncMusicService = this.c) == null) {
            return;
        }
        syncMusicStatusListener.onBlueToothDisconnect(syncMusicService.g(), this.c.h());
    }

    public void startForeground(int i, Notification notification) {
        if (PermissionUtil.c()) {
            this.c.startForeground(i, notification, 16);
        } else {
            this.c.startForeground(i, notification);
        }
    }

    public void stopForeground() {
        this.c.stopForeground(true);
    }
}
