package com.huawei.health.suggestion.ui.fitness.helper.actionstrategy;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import com.huawei.health.suggestion.ui.fitness.helper.MediaHelper;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public abstract class BaseActionDetailPlayerStrategy implements HeaderViewInterface, TextureView.SurfaceTextureListener {
    private static final String TAG = "Suggestion_BaseActionDetailPlayerStrategy";
    private HeaderViewInterface mHeaderViewInterface;
    private boolean mIsMediaPlayerStarted;
    protected MediaHelper mMediaHelper;
    private boolean mIsDisplayedNoWifi = false;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public abstract void initMediaPlayer();

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public abstract void startPlayer();

    public BaseActionDetailPlayerStrategy(HeaderViewInterface headerViewInterface) {
        if (headerViewInterface == null) {
            LogUtil.h(TAG, "BaseActionDetailPlayerStrategy headerViewRefreshCallBack is null");
        } else {
            this.mHeaderViewInterface = (HeaderViewInterface) new WeakReference(headerViewInterface).get();
            this.mIsMediaPlayerStarted = false;
        }
    }

    public boolean isNetWorkConnected() {
        if (CommonUtil.aa(BaseApplication.getContext())) {
            return true;
        }
        refreshHeaderView(8);
        return false;
    }

    public boolean wifiOrNot() {
        if (!CommonUtil.ah(BaseApplication.getContext())) {
            return true;
        }
        refreshHeaderView(1);
        return false;
    }

    public void releasePlayer() {
        MediaHelper mediaHelper = this.mMediaHelper;
        if (mediaHelper != null) {
            mediaHelper.release();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public void pauseVideo() {
        LogUtil.a(TAG, "ActionDetailPlayerStrategy pauseVideo");
    }

    public void onResume() {
        LogUtil.a(TAG, "ActionDetailPlayerStrategy onResume");
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public void refreshHeaderView(int i) {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return;
        }
        headerViewInterface.refreshHeaderView(i);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public void setSurfaceTextureListener(TextureView.SurfaceTextureListener surfaceTextureListener) {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return;
        }
        headerViewInterface.setSurfaceTextureListener(surfaceTextureListener);
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public void showCoachImage() {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return;
        }
        headerViewInterface.showCoachImage();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public View getHeaderView() {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return null;
        }
        return headerViewInterface.getHeaderView();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public ImageView getCoachImageView() {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return null;
        }
        return headerViewInterface.getCoachImageView();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public boolean getIsShowMediaPlayer() {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return false;
        }
        return headerViewInterface.getIsShowMediaPlayer();
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.actionstrategy.HeaderViewInterface
    public boolean getIsForeGround() {
        HeaderViewInterface headerViewInterface = this.mHeaderViewInterface;
        if (headerViewInterface == null) {
            return false;
        }
        return headerViewInterface.getIsForeGround();
    }

    public boolean isMediaPlayerStarted() {
        return this.mIsMediaPlayerStarted;
    }

    public void setIsMediaPlayerStarted(boolean z) {
        this.mIsMediaPlayerStarted = z;
    }

    public boolean isDisplayedNoWifi() {
        return this.mIsDisplayedNoWifi;
    }

    public void setIsDisplayedNoWifi(boolean z) {
        this.mIsDisplayedNoWifi = z;
    }

    public Handler getHandler() {
        return this.mHandler;
    }
}
