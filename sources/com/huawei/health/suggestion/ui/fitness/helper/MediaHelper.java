package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.suggestion.h5pro.AudioConstant;
import com.huawei.health.suggestion.ui.fitness.helper.MediaHelper;
import com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import defpackage.squ;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class MediaHelper implements VideoInterface, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a, reason: collision with root package name */
    protected boolean f3166a;
    protected String b;
    protected int c;
    protected String d;
    protected MediaPlayer e;
    private Context f;
    private SurfaceView g;
    private List<String> h;
    private int i;
    private int j;
    private int k;
    private PathType l;
    private List<Integer> m;
    private List<Uri> n;
    private MediaPlayer.OnVideoSizeChangedListener o;
    private ViewGroup q;

    enum PathType {
        URI,
        ASSET,
        RAW,
        SD
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface stop() {
        return this;
    }

    public MediaHelper(Context context) {
        this.f3166a = true;
        this.d = AudioConstant.WOMAN;
        this.n = new ArrayList(10);
        this.h = new ArrayList(10);
        this.m = new ArrayList(10);
        this.f = context.getApplicationContext();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.e = mediaPlayer;
        mediaPlayer.setOnVideoSizeChangedListener(this);
        this.f3166a = true;
        this.e.setLooping(true);
    }

    public MediaHelper() {
        this.f3166a = true;
        this.d = AudioConstant.WOMAN;
        this.n = new ArrayList(10);
        this.h = new ArrayList(10);
        this.m = new ArrayList(10);
        this.f = BaseApplication.getContext();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.e = mediaPlayer;
        mediaPlayer.setOnVideoSizeChangedListener(this);
        this.f3166a = true;
        this.e.setLooping(true);
    }

    public void aCt_(MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.o = onVideoSizeChangedListener;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        SurfaceView surfaceView = this.g;
        if (surfaceView != null) {
            aCx_(surfaceView, i, i2);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        SurfaceView surfaceView;
        int width;
        if (this.q == null || (surfaceView = this.g) == null || this.e == null || (width = surfaceView.getWidth()) == this.q.getWidth() || width == (this.q.getHeight() * 16) / 9) {
            return;
        }
        LogUtil.a("Suggestion_mediaHelper", "onGlobalLayout, displayWidth =", Integer.valueOf(width), " mViewGroup.getWidth() =", Integer.valueOf(this.q.getWidth()), " mViewGroup.getHeight() =", Integer.valueOf(this.q.getHeight()));
        aCx_(this.g, this.e.getVideoWidth(), this.e.getVideoHeight());
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface start() {
        this.c = 0;
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setPlaybackParams(PlaybackParams playbackParams) {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.setPlaybackParams(playbackParams);
        }
        return this;
    }

    public void e(boolean z) {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(z);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setMediaSources(final Uri... uriArr) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fpc
            @Override // java.lang.Runnable
            public final void run() {
                MediaHelper.this.aCr_(uriArr);
            }
        });
        return this;
    }

    public /* synthetic */ void aCr_(Uri[] uriArr) {
        this.k = uriArr.length;
        this.c = 0;
        try {
            if (this.e != null) {
                this.l = PathType.URI;
                this.n.clear();
                this.n.addAll(Arrays.asList(uriArr));
                this.e.reset();
                this.e.setDataSource(this.f, uriArr[0]);
                f_();
            } else {
                LogUtil.b("Suggestion_mediaHelper", "setMediaSources mPlayer == null");
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            LogUtil.b("Suggestion_mediaHelper", LogAnonymous.b(e));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x004f, code lost:
    
        if (r2 != null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006c, code lost:
    
        if (r2 == null) goto L36;
     */
    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface setAssetSources(java.lang.String... r11) {
        /*
            r10 = this;
            java.lang.String r0 = "Suggestion_mediaHelper"
            r1 = 0
            r10.c = r1
            int r2 = r11.length     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            r10.k = r2     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            android.media.MediaPlayer r2 = r10.e     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            r3 = 1
            if (r2 == 0) goto L82
            com.huawei.health.suggestion.ui.fitness.helper.MediaHelper$PathType r2 = com.huawei.health.suggestion.ui.fitness.helper.MediaHelper.PathType.ASSET     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            r10.l = r2     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            java.util.List<java.lang.String> r2 = r10.h     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            r2.clear()     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            java.util.List<java.lang.String> r2 = r10.h     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            java.util.List r4 = java.util.Arrays.asList(r11)     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            r2.addAll(r4)     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            android.media.MediaPlayer r2 = r10.e     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            r2.reset()     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            android.content.Context r2 = r10.f     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            if (r2 == 0) goto L78
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            if (r2 == 0) goto L78
            r2 = 0
            android.content.Context r4 = r10.f     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            android.content.res.AssetManager r4 = r4.getAssets()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            r11 = r11[r1]     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            android.content.res.AssetFileDescriptor r2 = r4.openFd(r11)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            android.media.MediaPlayer r4 = r10.e     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            java.io.FileDescriptor r5 = r2.getFileDescriptor()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            long r6 = r2.getStartOffset()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            long r8 = r2.getLength()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            r4.setDataSource(r5, r6, r8)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            r10.f_()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L54 java.io.FileNotFoundException -> L63
            if (r2 == 0) goto L9e
            goto L6e
        L52:
            r11 = move-exception
            goto L72
        L54:
            r11 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L52
            java.lang.String r11 = health.compact.a.LogAnonymous.b(r11)     // Catch: java.lang.Throwable -> L52
            r3[r1] = r11     // Catch: java.lang.Throwable -> L52
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto L9e
            goto L6e
        L63:
            java.lang.Object[] r11 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L52
            java.lang.String r3 = "fileDescriptor, FileNotFoundException"
            r11[r1] = r3     // Catch: java.lang.Throwable -> L52
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto L9e
        L6e:
            r2.close()     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            goto L9e
        L72:
            if (r2 == 0) goto L77
            r2.close()     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
        L77:
            throw r11     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
        L78:
            java.lang.Object[] r11 = new java.lang.Object[r3]     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            java.lang.String r2 = "mContext == null || mContext.getAssets == null"
            r11[r1] = r2     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            goto L9e
        L82:
            java.lang.Object[] r11 = new java.lang.Object[r3]     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            java.lang.String r2 = "setAssetSources mPlayer == null"
            r11[r1] = r2     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)     // Catch: java.lang.IllegalStateException -> L8c java.lang.SecurityException -> L8e java.lang.IllegalArgumentException -> L90 java.io.IOException -> L92
            goto L9e
        L8c:
            r11 = move-exception
            goto L93
        L8e:
            r11 = move-exception
            goto L93
        L90:
            r11 = move-exception
            goto L93
        L92:
            r11 = move-exception
        L93:
            java.lang.String r11 = health.compact.a.LogAnonymous.b(r11)
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            com.huawei.hwlogsmodel.LogUtil.b(r0, r11)
        L9e:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.suggestion.ui.fitness.helper.MediaHelper.setAssetSources(java.lang.String[]):com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface");
    }

    protected void aCx_(View view, int i, int i2) {
        if (view == null) {
            LogUtil.h("Suggestion_mediaHelper", "parameter view is null");
            return;
        }
        if (!(view.getParent() instanceof ViewGroup)) {
            LogUtil.h("Suggestion_mediaHelper", "tryResetSurfaceSize !(view.getParent() instanceof ViewGroup)");
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int width = viewGroup.getWidth();
        int height = viewGroup.getHeight();
        LogUtil.a("Suggestion_mediaHelper", "tryResetSurfaceSize, width:", Integer.valueOf(width), " height:", Integer.valueOf(height), " videoWidth:", Integer.valueOf(i), " videoHeight:", Integer.valueOf(i2));
        if (width <= 0 || height <= 0) {
            return;
        }
        if (!(view.getLayoutParams() instanceof FrameLayout.LayoutParams)) {
            LogUtil.h("Suggestion_mediaHelper", "!(view.getLayoutParams() instanceof FrameLayout.LayoutParams)");
            return;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (i2 == 0) {
            LogUtil.h("Suggestion_mediaHelper", "tryResetSurfaceSize videoHeight == 0");
            return;
        }
        float f = i / i2;
        if (f == 0.0f) {
            LogUtil.h("Suggestion_mediaHelper", "tryResetSurfaceSize scaleVideo == 0");
            return;
        }
        if (f > width / height) {
            layoutParams.width = width;
        } else {
            layoutParams.width = (height * 16) / 9;
        }
        layoutParams.height = (int) (layoutParams.width / f);
        layoutParams.gravity = 17;
        LogUtil.a("Suggestion_mediaHelper", "params.width:", Integer.valueOf(layoutParams.width), " params.height:", Integer.valueOf(layoutParams.height));
        this.i = (width - layoutParams.width) / 2;
        this.j = (height - layoutParams.height) / 2;
        view.setLayoutParams(layoutParams);
        MediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = this.o;
        if (onVideoSizeChangedListener != null) {
            onVideoSizeChangedListener.onVideoSizeChanged(this.e, i, i2);
        }
    }

    public int h() {
        return this.i;
    }

    public int j() {
        return this.j;
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setAudioAssetSources(List<String> list) {
        this.k = list.size();
        this.c = 0;
        if (this.e != null) {
            try {
                this.l = PathType.ASSET;
                this.h.clear();
                this.h.addAll(list);
                this.e.reset();
                AssetFileDescriptor assetFileDescriptor = null;
                try {
                    assetFileDescriptor = this.f.getAssets().openFd(list.get(0));
                    this.e.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                    f_();
                    if (assetFileDescriptor != null) {
                        assetFileDescriptor.close();
                    }
                } catch (Throwable th) {
                    if (assetFileDescriptor != null) {
                        assetFileDescriptor.close();
                    }
                    throw th;
                }
            } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
                LogUtil.b("Suggestion_mediaHelper", "setAudioAssetSources", LogAnonymous.b(e));
            }
        } else {
            LogUtil.b("Suggestion_mediaHelper", "setAudioAssetSources mPlayer == null");
        }
        return this;
    }

    public VideoInterface e(String str) {
        return setSdSources(squ.a(str, this.d, this.b, AudioConstant.AUDIO));
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setSdSources(String... strArr) {
        if (CommonUtil.bu()) {
            return setAssetSources(strArr);
        }
        this.c = 0;
        try {
            this.k = strArr.length;
            if (this.e != null && this.h != null) {
                this.l = PathType.SD;
                this.h.clear();
                this.h.addAll(Arrays.asList(strArr));
                this.e.reset();
                if (this.h.size() > 0 && this.h.get(0) != null) {
                    this.e.setDataSource(this.h.get(0));
                    f_();
                }
            } else {
                LogUtil.b("Suggestion_mediaHelper", "setSdSources mPlayer == null or mAssetSources == null");
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            LogUtil.b("Suggestion_mediaHelper", "setDataSource fail -- ", LogAnonymous.b(e));
        }
        return this;
    }

    public VideoInterface a(List<String> list) {
        if (CommonUtil.bu()) {
            return setAudioAssetSources(list);
        }
        this.c = 0;
        try {
            this.k = list.size();
            if (this.e != null) {
                this.l = PathType.SD;
                this.h.clear();
                this.h.addAll(list);
                this.e.reset();
                if (this.h.get(0) != null) {
                    this.e.setDataSource(this.h.get(0));
                    f_();
                }
            } else {
                LogUtil.b("Suggestion_mediaHelper", "setSdSources mPlayer == null");
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            LogUtil.b("Suggestion_mediaHelper", "setSdSources(@NonNull List<String> fileName) ", LogAnonymous.b(e));
        }
        return this;
    }

    protected void f_() {
        this.e.setLooping(this.f3166a);
        k();
        try {
            this.e.prepare();
        } catch (Exception e) {
            LogUtil.b("Suggestion_mediaHelper", "later player prepare()error: ", LogAnonymous.b((Throwable) e));
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface setRawSources(Integer... numArr) {
        this.k = numArr.length;
        this.c = 0;
        if (this.e != null) {
            this.l = PathType.RAW;
            this.m.clear();
            this.m.addAll(Arrays.asList(numArr));
            this.e.reset();
            MediaPlayer create = MediaPlayer.create(this.f, numArr[0].intValue());
            this.e = create;
            create.setLooping(this.f3166a);
            k();
        } else {
            LogUtil.b("Suggestion_mediaHelper", "setRawSources mPlayer == null");
        }
        return this;
    }

    public void aCs_(final SurfaceHolder surfaceHolder) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.helper.MediaHelper.5
            @Override // java.lang.Runnable
            public void run() {
                if (MediaHelper.this.e != null && surfaceHolder.getSurface() != null && surfaceHolder.getSurface().isValid()) {
                    MediaHelper.this.e.setDisplay(surfaceHolder);
                } else {
                    LogUtil.b("Suggestion_mediaHelper", "setDisplay mPlayer == null");
                }
            }
        });
    }

    public VideoInterface aCv_(SurfaceView surfaceView) {
        if (surfaceView != null) {
            this.g = surfaceView;
        }
        return this;
    }

    public void aCw_(ViewGroup viewGroup) {
        this.q = viewGroup;
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public VideoInterface aCu_(Surface surface) {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.setSurface(surface);
        } else {
            LogUtil.b("Suggestion_mediaHelper", "setSurface mPlayer == null");
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface pause() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface repeat() {
        if (this.e != null) {
            this.c--;
            next();
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface pre() {
        try {
            if (this.e != null) {
                int i = this.c;
                int i2 = i - 1;
                this.c = i2;
                if (i2 < 0) {
                    this.c = i;
                    LogUtil.c("Suggestion_mediaHelper", "It's the first");
                    return this;
                }
                e();
            } else {
                LogUtil.b("Suggestion_mediaHelper", "Prepare mPlayer == null");
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            LogUtil.b("Suggestion_mediaHelper", LogAnonymous.b(e));
        }
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface next() {
        try {
            if (this.e != null) {
                int i = this.c;
                int i2 = i + 1;
                this.c = i2;
                if (i2 >= this.k) {
                    this.c = i;
                    LogUtil.c("Suggestion_mediaHelper", "It's the last");
                    return this;
                }
                e();
            } else {
                LogUtil.b("Suggestion_mediaHelper", "Next mPlayer == null");
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            LogUtil.b("Suggestion_mediaHelper", "next_error--", LogAnonymous.b(e));
        }
        return this;
    }

    private void e() throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            if (this.l == PathType.ASSET) {
                AssetFileDescriptor assetFileDescriptor = null;
                try {
                    if (koq.d(this.h, this.c)) {
                        assetFileDescriptor = this.f.getAssets().openFd(this.h.get(this.c));
                        this.e.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                        this.e.prepare();
                    }
                } finally {
                    if (assetFileDescriptor != null) {
                        try {
                            assetFileDescriptor.close();
                        } catch (IOException unused) {
                            LogUtil.b("Suggestion_mediaHelper", "IOException");
                        }
                    }
                }
            } else if (this.l == PathType.RAW) {
                if (koq.d(this.m, this.c)) {
                    this.e = MediaPlayer.create(this.f, this.m.get(this.c).intValue());
                }
            } else if (this.l == PathType.SD) {
                if (koq.d(this.h, this.c)) {
                    LogUtil.a("Suggestion_mediaHelper", "mCurrent video path is ", this.h.get(this.c));
                    this.e.setDataSource(this.h.get(this.c));
                    this.e.prepare();
                }
            } else if (koq.d(this.n, this.c)) {
                this.e.setDataSource(this.f, this.n.get(this.c));
                this.e.prepare();
            }
            this.e.setLooping(this.f3166a);
            this.e.start();
            k();
            return;
        }
        LogUtil.b("Suggestion_mediaHelper", "changeSource mPlayer == null");
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface videoContinue() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        return this;
    }

    public MediaHelper d(boolean z) {
        this.f3166a = z;
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public VideoInterface release() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            this.c = 0;
            this.m = null;
            this.h = null;
            this.n = null;
            mediaPlayer.release();
            this.e = null;
            this.g = null;
        }
        ViewGroup viewGroup = this.q;
        if (viewGroup != null) {
            viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        return this;
    }

    public String g() {
        if (koq.d(this.h, this.c)) {
            return this.h.get(this.c);
        }
        return null;
    }

    public int n() {
        return this.k;
    }

    public void e(float f) {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.inter.VideoInterface
    public void saveCurrent(int i) {
        this.c = i;
    }

    public void b(int i) {
        if (i == 1) {
            this.d = AudioConstant.WOMAN;
        } else {
            this.d = "M";
        }
    }

    public MediaPlayer aCq_() {
        return this.e;
    }

    public boolean o() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public int f() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public void m() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }

    public List<String> c(int i) {
        ArrayList arrayList = new ArrayList();
        if (i > 130) {
            b(arrayList, i);
        } else {
            StringBuilder sb = new StringBuilder("B");
            int length = String.valueOf(i).length();
            for (int i2 = 0; i2 < 3 - length; i2++) {
                sb.append("0");
            }
            sb.append(i);
            arrayList.add(squ.a(sb.toString(), this.d, this.b, AudioConstant.AUDIO));
        }
        return arrayList;
    }

    private void e(List<String> list, int i) {
        LogUtil.c("Suggestion_mediaHelper", "getNumWithinTen()---", Integer.valueOf(i));
        if (i < 10) {
            list.add(a(i));
        }
    }

    private String a(int i) {
        return squ.a("B00" + i, this.d, this.b, AudioConstant.AUDIO);
    }

    private void d(List<String> list, int i) {
        LogUtil.c("Suggestion_mediaHelper", "dealNumAboveTenWithinHundred", Integer.valueOf(i));
        if (i <= 9 || i >= 100) {
            return;
        }
        list.add(squ.a("B0" + ((i / 10) * 10), this.d, this.b, AudioConstant.AUDIO));
        int i2 = i % 10;
        if (i2 > 0) {
            e(list, i2);
        }
    }

    private void b(List<String> list, int i) {
        LogUtil.c("Suggestion_mediaHelper", "getNumAboveHundredWithinThousand", Integer.valueOf(i));
        list.add(squ.a("B" + ((i / 100) * 100), this.d, this.b, AudioConstant.AUDIO));
        if (i > 100) {
            if (i < 120) {
                e(list, 1);
                LogUtil.b("Suggestion_mediaHelper", "have no media");
            } else {
                d(list, i % 100);
            }
        }
    }

    public void b(String str) {
        this.b = str;
    }

    public void k() {
        MediaPlayer mediaPlayer = this.e;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVideoScalingMode(2);
            } catch (RuntimeException e) {
                LogUtil.b("Suggestion_mediaHelper", "setVideoScalingMode RuntimeError.", LogAnonymous.b((Throwable) e));
            }
        }
    }
}
