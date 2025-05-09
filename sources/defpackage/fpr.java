package defpackage;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface;
import com.huawei.health.suggestion.ui.view.ActionDetailView;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.Video;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class fpr extends BaseActionDetailPlayerStrategy implements LongMediaHelper.OnPreparedListener {

    /* renamed from: a, reason: collision with root package name */
    private SurfaceTexture f12598a;
    private AtomicAction b;
    private String c;
    private LongMediaHelper d;
    private ActionDetailView e;

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onBufferingEnd() {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onBufferingStart() {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onVideoError() {
    }

    static class e implements Runnable {
        private WeakReference<fpr> d;

        e(fpr fprVar) {
            this.d = new WeakReference<>(fprVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            fpr fprVar = this.d.get();
            if (fprVar != null) {
                fprVar.b();
                LongMediaHelper longMediaHelper = fprVar.d;
                if (longMediaHelper == null) {
                    LogUtil.h("Suggestion_ActionDetailPlayerActionLibraryStrategy", "hideLoading mMediaHelper == null ");
                    return;
                }
                MediaPlayer aCp_ = longMediaHelper.aCp_();
                ActionDetailView actionDetailView = fprVar.e;
                if (aCp_ == null || actionDetailView == null) {
                    return;
                }
                int videoWidth = aCp_.getVideoWidth();
                int videoHeight = aCp_.getVideoHeight();
                ReleaseLogUtil.e("Suggestion_ActionDetailPlayerActionLibraryStrategy", "width: height:", Integer.valueOf(videoWidth), ": ", Integer.valueOf(videoHeight));
                actionDetailView.d(videoWidth, videoHeight);
            }
        }
    }

    public fpr(HeaderViewInterface headerViewInterface, AtomicAction atomicAction) {
        super(headerViewInterface);
        this.b = atomicAction;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void initMediaPlayer() {
        if (this.d == null) {
            this.d = new LongMediaHelper(arx.b());
            setSurfaceTextureListener(this);
        }
        if (isNetWorkConnected()) {
            if (!isDisplayedNoWifi() && !wifiOrNot()) {
                setIsDisplayedNoWifi(true);
            } else {
                refreshHeaderView(5);
                b(this.b.getExtendPropertyList("actionVideo", Video[].class));
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void startPlayer() {
        LongMediaHelper longMediaHelper = this.d;
        if (longMediaHelper == null) {
            return;
        }
        longMediaHelper.start();
        setIsDisplayedNoWifi(true);
        setIsMediaPlayerStarted(true);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void pauseVideo() {
        super.pauseVideo();
        if (this.d.aCq_() != null && this.d.aCq_().isPlaying() && isMediaPlayerStarted()) {
            this.d.pause();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void onResume() {
        if (this.d != null && !getIsShowMediaPlayer()) {
            startPlayer();
            return;
        }
        LongMediaHelper longMediaHelper = this.d;
        if (longMediaHelper == null || longMediaHelper.aCq_() == null || this.d.aCq_().isPlaying()) {
            return;
        }
        this.d.videoContinue();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void releasePlayer() {
        super.releasePlayer();
        this.f12598a = null;
        if (this.d != null) {
            setIsMediaPlayerStarted(false);
            this.d.release();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        ActionDetailView actionDetailView;
        SurfaceTexture surfaceTexture2 = this.f12598a;
        if (surfaceTexture2 == null || (actionDetailView = this.e) == null) {
            this.f12598a = surfaceTexture;
            this.d.aCu_(new Surface(this.f12598a));
        } else {
            actionDetailView.setVideoTextureView(surfaceTexture2);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy, android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        setIsMediaPlayerStarted(false);
        this.f12598a = surfaceTexture;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (getHeaderView() == null) {
            LogUtil.h("Suggestion_ActionDetailPlayerActionLibraryStrategy", " headerView is null");
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: fpr.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (fpr.this.getHeaderView() != null) {
                    fpr.this.getHeaderView().setVisibility(8);
                }
            }
        });
        alphaAnimation.setDuration(150L);
        getHeaderView().startAnimation(alphaAnimation);
    }

    private void b(List<Video> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_ActionDetailPlayerActionLibraryStrategy", "getVideo videos can not null");
            return;
        }
        int d = d();
        Iterator<Video> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Video next = it.next();
            if (next != null && next.getGender() == d) {
                this.c = next.getUrl();
                break;
            }
        }
        if (TextUtils.isEmpty(this.c)) {
            Video video = list.get(0);
            if (video == null) {
                LogUtil.h("Suggestion_ActionDetailPlayerActionLibraryStrategy", "getVideo video can not null");
                return;
            }
            this.c = video.getUrl();
        }
        this.d.setMediaSources(Uri.parse(this.c));
        this.d.e(true);
        this.d.a(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int d() {
        /*
            r4 = this;
            com.huawei.pluginfitnessadvice.AtomicAction r0 = r4.b
            r1 = 2
            if (r0 == 0) goto L21
            java.lang.String r2 = "courseAttr"
            java.lang.String r0 = r0.getExtendPropertyString(r2)
            java.lang.String r2 = "getVideo courseAttr"
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r0}
            java.lang.String r3 = "Suggestion_ActionDetailPlayerActionLibraryStrategy"
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r3, r2)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L21
            int r0 = health.compact.a.CommonUtil.e(r0, r1)
            goto L22
        L21:
            r0 = r1
        L22:
            int r2 = defpackage.ggg.a()
            if (r0 == r1) goto L29
            goto L2a
        L29:
            r0 = r2
        L2a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fpr.d():int");
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onPrepared() {
        startPlayer();
        getHandler().postDelayed(new e(this), 500L);
    }

    public void a(ActionDetailView actionDetailView) {
        if (actionDetailView == null) {
            LogUtil.h("Suggestion_ActionDetailPlayerActionLibraryStrategy", "setActionDetailView actionDetailView can not null");
        } else {
            this.e = actionDetailView;
        }
    }
}
