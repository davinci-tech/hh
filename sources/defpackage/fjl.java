package defpackage;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fjl implements MediaPlayer.OnCompletionListener {
    private static volatile fjl b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private MediaPlayer f12544a;
    private Context d;
    private List<Integer> e;

    private fjl(Context context) {
        if (context == null) {
            this.d = BaseApplication.getContext();
        } else {
            this.d = context.getApplicationContext();
        }
        this.e = new ArrayList();
    }

    public static fjl e(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new fjl(context);
                }
            }
        }
        return b;
    }

    public void e(int i) {
        if (this.f12544a == null) {
            d();
        }
        if (this.f12544a.isPlaying()) {
            this.e.add(Integer.valueOf(i));
        } else {
            d(i);
        }
    }

    private void d(final int i) {
        if (Build.VERSION.SDK_INT < 31) {
            a(i, true);
        } else if (PermissionUtil.e(this.d, new String[]{"android.permission.READ_PHONE_STATE"})) {
            a(i, true);
        } else {
            PermissionUtil.bFX_(BaseApplication.getActivity(), new String[]{"android.permission.READ_PHONE_STATE"}, new PermissionsResultAction() { // from class: fjl.3
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    fjl.this.a(i, true);
                }

                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onDenied(String str) {
                    fjl.this.a(i, false);
                    LogUtil.a("Suggestion_SugVoice", "checkPermission onDenied");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(int i, boolean z) {
        if (z && CommonUtil.bw()) {
            onCompletion(null);
            return;
        }
        AssetFileDescriptor openRawResourceFd = this.d.getResources().openRawResourceFd(i);
        try {
            if (openRawResourceFd != null) {
                try {
                    this.f12544a.reset();
                    this.f12544a.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                    this.f12544a.prepare();
                    this.f12544a.start();
                } catch (IOException e) {
                    LogUtil.b("Suggestion_SugVoice", LogAnonymous.b((Throwable) e));
                    try {
                        openRawResourceFd.close();
                        openRawResourceFd = openRawResourceFd;
                    } catch (IOException e2) {
                        Object[] objArr = {LogAnonymous.b((Throwable) e2)};
                        LogUtil.b("Suggestion_SugVoice", objArr);
                        openRawResourceFd = objArr;
                    }
                }
            }
        } finally {
            try {
                openRawResourceFd.close();
            } catch (IOException e3) {
                LogUtil.b("Suggestion_SugVoice", LogAnonymous.b((Throwable) e3));
            }
        }
    }

    private void d() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.f12544a = mediaPlayer;
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.e.size() != 0 && mediaPlayer != null) {
            d(this.e.get(0).intValue());
            this.e.remove(0);
        } else {
            this.e.clear();
            this.f12544a.release();
            this.f12544a = null;
        }
    }
}
