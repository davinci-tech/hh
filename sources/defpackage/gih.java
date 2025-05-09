package defpackage;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import android.view.TextureView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.suggestion.videoplayer.IngotsMediaInterface;
import com.huawei.health.suggestion.videoplayer.ResizeTextureView;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Map;

/* loaded from: classes8.dex */
public class gih implements TextureView.SurfaceTextureListener {

    /* renamed from: a, reason: collision with root package name */
    private static gih f12815a;
    private Handler b;
    private IngotsMediaInterface c;
    private HandlerThread g;
    private SurfaceTexture h;
    private Surface i;
    private d j;
    private ResizeTextureView l;
    private int f = -1;
    private int e = 0;
    private int d = 0;

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    private gih() {
        HandlerThread handlerThread = new HandlerThread("Ingots_IngotsMediaManager");
        this.g = handlerThread;
        handlerThread.start();
        this.j = new d(this.g.getLooper(), this);
        this.b = new Handler();
        if (this.c == null) {
            this.c = new gif();
        }
    }

    public static gih e() {
        if (f12815a == null) {
            f12815a = new gih();
        }
        return f12815a;
    }

    public static void c(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("Ingots_IngotsMediaManager", "setDataSource() dataSource is null");
        } else {
            e().c.setDataSource(map);
        }
    }

    public static void e(boolean z) {
        e().c.setLoop(z);
    }

    public static String c() {
        return e().c.getCurrentDataSource();
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Ingots_IngotsMediaManager", "setCurrentDataSource() currentDataSource is null");
        } else {
            e().c.setCurrentDataSource(str);
        }
    }

    public static long a() {
        return e().c.getCurrentPosition();
    }

    public static long d() {
        return e().c.getDuration();
    }

    public static void a(long j) {
        e().c.seekTo(j);
    }

    public static void b() {
        e().c.pause();
    }

    public static void i() {
        e().c.start();
    }

    public void n() {
        this.j.removeCallbacksAndMessages(null);
        Message obtain = Message.obtain();
        obtain.what = 2;
        this.j.sendMessage(obtain);
    }

    public void m() {
        n();
        Message obtain = Message.obtain();
        obtain.what = 0;
        this.j.sendMessage(obtain);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (gii.b().a() == null) {
            LogUtil.h("Ingots_IngotsMediaManager", "onSurfaceTextureAvailable() IngotsVideoPlayerManager.getInstance().getCurrentVideoPlayer() is null");
            return;
        }
        SurfaceTexture surfaceTexture2 = this.h;
        if (surfaceTexture2 == null) {
            this.h = surfaceTexture;
            m();
        } else {
            this.l.setSurfaceTexture(surfaceTexture2);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return this.h == null;
    }

    public int j() {
        return this.f;
    }

    public void e(int i) {
        this.f = i;
    }

    public ResizeTextureView l() {
        return this.l;
    }

    public void c(ResizeTextureView resizeTextureView) {
        this.l = resizeTextureView;
    }

    public SurfaceTexture aMX_() {
        return this.h;
    }

    public void aMZ_(SurfaceTexture surfaceTexture) {
        this.h = surfaceTexture;
    }

    public Surface aMY_() {
        return this.i;
    }

    public void aNa_(Surface surface) {
        this.i = surface;
    }

    public int g() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int f() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(IngotsMediaInterface ingotsMediaInterface) {
        if (ingotsMediaInterface == null) {
            LogUtil.h("Ingots_IngotsMediaManager", "setMediaInterface() ingotsMediaInterface is null");
        } else {
            this.c = ingotsMediaInterface;
        }
    }

    public Handler aMW_() {
        return this.b;
    }

    static class d extends BaseHandler<gih> {
        d(Looper looper, gih gihVar) {
            super(looper, gihVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aNb_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(gih gihVar, Message message) {
            int i = message.what;
            if (i != 0) {
                if (i == 2) {
                    gihVar.c.release();
                    return;
                } else {
                    LogUtil.h("Ingots_IngotsMediaManager", "handleMessage() is no use");
                    return;
                }
            }
            gihVar.e = 0;
            gihVar.d = 0;
            gihVar.c.prepare();
            c(gihVar);
        }

        private void c(gih gihVar) {
            if (gihVar.aMX_() != null) {
                if (gihVar.aMY_() != null) {
                    gihVar.aMY_().release();
                }
                Surface surface = new Surface(gihVar.aMX_());
                gihVar.aNa_(surface);
                gihVar.c.setSurface(surface);
            }
        }
    }
}
