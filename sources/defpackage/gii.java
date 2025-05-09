package defpackage;

import com.huawei.health.suggestion.videoplayer.IngotsVideoPlayer;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class gii {
    private IngotsVideoPlayer b;
    private IngotsVideoPlayer e;

    static class a {
        private static final gii d = new gii();
    }

    public static gii b() {
        return a.d;
    }

    public IngotsVideoPlayer c() {
        return this.b;
    }

    public void e(IngotsVideoPlayer ingotsVideoPlayer) {
        if (ingotsVideoPlayer == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerManager", "setFirstFloor() ingotsVideoPlayer is null");
        }
        this.b = ingotsVideoPlayer;
    }

    public IngotsVideoPlayer e() {
        return this.e;
    }

    public void c(IngotsVideoPlayer ingotsVideoPlayer) {
        if (ingotsVideoPlayer == null) {
            LogUtil.h("Ingots_IngotsVideoPlayerManager", "setSecondFloor() ingotsVideoPlayer is null");
        }
        this.e = ingotsVideoPlayer;
    }

    public IngotsVideoPlayer a() {
        if (e() != null) {
            return e();
        }
        return c();
    }

    public void d() {
        IngotsVideoPlayer ingotsVideoPlayer = this.e;
        if (ingotsVideoPlayer != null) {
            ingotsVideoPlayer.onCompletion();
            this.e = null;
        }
        IngotsVideoPlayer ingotsVideoPlayer2 = this.b;
        if (ingotsVideoPlayer2 != null) {
            ingotsVideoPlayer2.onCompletion();
            this.b = null;
        }
    }
}
