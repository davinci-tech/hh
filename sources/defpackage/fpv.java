package defpackage;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy;
import com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public final class fpv extends BaseActionDetailPlayerStrategy implements LongMediaHelper.OnPreparedListener {

    /* renamed from: a, reason: collision with root package name */
    private int f12600a;
    private Motion b;
    private int c;
    private final String e;

    public fpv(HeaderViewInterface headerViewInterface, Motion motion, String str) {
        super(headerViewInterface);
        this.b = motion;
        this.e = str;
        initMediaPlayer();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void startPlayer() {
        Motion motion = this.b;
        if (motion == null) {
            return;
        }
        if (koq.d(motion.getVideoSegments(), 0)) {
            if (this.b.getVideoSegments().get(0) == null) {
                return;
            }
            this.f12600a = (int) this.b.getVideoSegments().get(0).getStartTime();
            this.c = (int) this.b.getVideoSegments().get(0).getEndTime();
        }
        if (this.mMediaHelper instanceof LongMediaHelper) {
            LongMediaHelper longMediaHelper = (LongMediaHelper) this.mMediaHelper;
            longMediaHelper.start();
            longMediaHelper.e(this.f12600a);
            b(longMediaHelper, this.f12600a, this.c);
            setIsMediaPlayerStarted(true);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void pauseVideo() {
        super.pauseVideo();
        if (this.mMediaHelper.aCq_() != null && this.mMediaHelper.aCq_().isPlaying() && isMediaPlayerStarted()) {
            this.mMediaHelper.pause();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void onResume() {
        super.onResume();
        if (this.mMediaHelper == null) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "onResume mMediaHelper is null");
            return;
        }
        if (!getIsShowMediaPlayer()) {
            startPlayer();
        } else if (this.mMediaHelper.aCq_() == null) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "onResume mMediaHelper.getPlayer is null");
        } else {
            if (this.mMediaHelper.aCq_().isPlaying()) {
                return;
            }
            this.mMediaHelper.videoContinue();
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void initMediaPlayer() {
        if (this.mMediaHelper == null) {
            this.mMediaHelper = new LongMediaHelper(BaseApplication.getContext());
            setSurfaceTextureListener(this);
        }
        if (!isNetWorkConnected()) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "initializationMediaPlayer no network connected");
            return;
        }
        if (!isDisplayedNoWifi() && !wifiOrNot()) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "initializationMediaPlayer no wifi connected");
            setIsDisplayedNoWifi(true);
            return;
        }
        refreshHeaderView(5);
        if (TextUtils.isEmpty(this.e)) {
            return;
        }
        this.mMediaHelper.setMediaSources(Uri.parse(this.e));
        e();
    }

    private void e() {
        if (!(this.mMediaHelper instanceof LongMediaHelper)) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "setMediaLongVideoListener mMediaHelper is null");
        } else {
            ((LongMediaHelper) this.mMediaHelper).a(this);
        }
    }

    private void b(final LongMediaHelper longMediaHelper, final int i, final int i2) {
        if (getHandler() == null) {
            return;
        }
        getHandler().post(new Runnable() { // from class: fpv.3
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i2;
                if (i3 <= 0) {
                    return;
                }
                if (fpv.this.a(longMediaHelper, i3)) {
                    longMediaHelper.e(i);
                }
                fpv.this.getHandler().postDelayed(this, 1000L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(LongMediaHelper longMediaHelper, int i) {
        return longMediaHelper != null && longMediaHelper.aCq_() != null && longMediaHelper.aCq_().getCurrentPosition() >= i && longMediaHelper.o();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy, android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        LogUtil.a("Suggestion_ActionDetailPlayerLongVideoStrategy", "onSurfaceTextureAvailable start");
        if (this.mMediaHelper == null) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "onSurfaceTextureAvailable mMediaHelper is null");
        } else {
            this.mMediaHelper.aCu_(new Surface(surfaceTexture));
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy, android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.mMediaHelper == null) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "onSurfaceTextureDestroyed mMediaHelper is null");
            return false;
        }
        setIsMediaPlayerStarted(false);
        this.mMediaHelper.release();
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.BaseActionDetailPlayerStrategy
    public void releasePlayer() {
        super.releasePlayer();
        setIsMediaPlayerStarted(false);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onPrepared() {
        LogUtil.a("Suggestion_ActionDetailPlayerLongVideoStrategy", "setMediaLongVideoListener onPrepared");
        if (!getIsForeGround()) {
            LogUtil.h("Suggestion_ActionDetailPlayerLongVideoStrategy", "setMediaLongVideoListener--isActivityForGround:", false);
        } else {
            startPlayer();
            LogUtil.a("Suggestion_ActionDetailPlayerLongVideoStrategy", "setMediaLongVideoListener--isActivityForGround:", true);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onBufferingStart() {
        LogUtil.a("Suggestion_ActionDetailPlayerLongVideoStrategy", "long Video buffering start");
        refreshHeaderView(5);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onBufferingEnd() {
        LogUtil.a("Suggestion_ActionDetailPlayerLongVideoStrategy", "long video buffering end");
        refreshHeaderView(6);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        LogUtil.a("Suggestion_ActionDetailPlayerLongVideoStrategy", "long video seek complete");
        if (this.f12600a == 0) {
            refreshHeaderView(6);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.LongMediaHelper.OnPreparedListener
    public void onVideoError() {
        LogUtil.b("Suggestion_ActionDetailPlayerLongVideoStrategy", "onVideoError");
    }
}
