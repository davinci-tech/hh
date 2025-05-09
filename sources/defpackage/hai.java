package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.huawei.healthcloud.plugintrack.sportmusic.SportMusicController;
import com.huawei.healthcloud.plugintrack.trackanimation.bgm.choosedownload.MusicPrePlayService;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.util.LogUtil;

/* loaded from: classes4.dex */
public class hai {
    private static volatile hai e;

    /* renamed from: a, reason: collision with root package name */
    private boolean f13045a;
    private ServiceConnection b = new ServiceConnection() { // from class: hai.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    private hai() {
    }

    public static hai a() {
        if (e == null) {
            synchronized (SportMusicController.class) {
                if (e == null) {
                    e = new hai();
                }
            }
        }
        return e;
    }

    public void d(String str) {
        LogUtil.d("Track_MusicPreController", "bindService enter");
        Intent intent = new Intent();
        intent.setClass(BaseApplication.getContext(), MusicPrePlayService.class);
        intent.putExtra("dynamic_music_path", str);
        this.f13045a = BaseApplication.getContext().bindService(intent, this.b, 1);
    }

    public void e() {
        if (this.b == null || !this.f13045a) {
            return;
        }
        BaseApplication.getContext().unbindService(this.b);
        this.f13045a = false;
    }
}
