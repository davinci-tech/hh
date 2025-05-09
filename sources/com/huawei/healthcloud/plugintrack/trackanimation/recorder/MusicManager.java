package com.huawei.healthcloud.plugintrack.trackanimation.recorder;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.watchface.videoedit.presenter.PresenterUtils;
import defpackage.gwg;
import defpackage.gww;
import defpackage.hai;
import health.compact.a.StorageParams;
import java.io.File;

/* loaded from: classes4.dex */
public class MusicManager {
    private Context e;
    private AudioManager d = null;

    /* renamed from: a, reason: collision with root package name */
    private MediaPlayer f3586a = null;
    private gww b = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));

    public MusicManager(Context context) {
        this.e = context;
    }

    public void b() {
        Context context = this.e;
        if (context instanceof Activity) {
            ((Activity) context).setVolumeControlStream(3);
        }
        if (this.e.getSystemService(PresenterUtils.AUDIO) instanceof AudioManager) {
            this.d = (AudioManager) this.e.getSystemService(PresenterUtils.AUDIO);
        }
    }

    public void a() {
        MediaPlayer mediaPlayer = this.f3586a;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.f3586a.release();
            this.f3586a = null;
        }
        hai.a().e();
        gwg.aUN_(this.d);
    }

    public void e() {
        MediaPlayer mediaPlayer;
        String n = this.b.n();
        if (TextUtils.isEmpty(n)) {
            return;
        }
        this.f3586a = MediaPlayer.create(this.e, Uri.fromFile(new File(n)));
        if (gwg.aUT_(this.d) == 0 || (mediaPlayer = this.f3586a) == null) {
            return;
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.huawei.healthcloud.plugintrack.trackanimation.recorder.MusicManager.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer2) {
                mediaPlayer2.seekTo(0);
                mediaPlayer2.start();
            }
        });
        this.f3586a.start();
    }
}
