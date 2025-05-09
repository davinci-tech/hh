package com.huawei.operation.h5pro.jsmodules.mediaCenter;

import android.media.MediaMetadata;
import android.media.session.PlaybackState;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.vengine.H5ProNativeView;
import com.huawei.health.h5pro.vengine.H5ProViewControls;
import com.huawei.health.health.player.MiniBottomPlayer;
import com.huawei.health.musicservice.api.IAudioPlayer;
import com.huawei.health.musicservice.mediacenter.MediaCenterCallback;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.dow;
import defpackage.enq;
import defpackage.eoi;
import defpackage.nkr;
import health.compact.a.LogUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class MediaCenterH5Api extends JsBaseModule {
    private static final String KEY_DATE = "date";
    private static final String KEY_INDEX = "index";
    private static final String KEY_IS_AUTO_STOP = "isAutoStop";
    private static final String KEY_MEDIA_ID = "mediaId";
    private static final String KEY_PLAY_STATUS = "playStatus";
    private static final String KEY_POSITION = "position";
    private static final String TAG = "MediaCenterH5Api";
    private H5ProViewControls mH5ProViewControls;
    private eoi mMediaCenter;
    private MiniBottomPlayer mMiniBottomPlayerForH5 = null;
    private String mMinibarTag;
    private H5PlayStatusSubscriber mPlayStatusSubscriber;

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        LogUtil.c(TAG, "onCreate");
        this.mH5ProViewControls = getH5ProViewControls();
        this.mMediaCenter = eoi.c();
        H5PlayStatusSubscriber h5PlayStatusSubscriber = new H5PlayStatusSubscriber();
        this.mPlayStatusSubscriber = h5PlayStatusSubscriber;
        this.mMediaCenter.d(h5PlayStatusSubscriber);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c(TAG, "onDestroy");
        removeMinibar();
        this.mMediaCenter.a(this.mPlayStatusSubscriber);
        this.mH5ProViewControls = null;
    }

    @JavascriptInterface
    public void init(final long j) {
        LogUtil.c(TAG, "init");
        this.mMediaCenter.b(new IBaseResponseCallback() { // from class: com.huawei.operation.h5pro.jsmodules.mediaCenter.MediaCenterH5Api$$ExternalSyntheticLambda0
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                MediaCenterH5Api.this.m751xb9247c79(j, i, obj);
            }
        });
    }

    /* renamed from: lambda$init$0$com-huawei-operation-h5pro-jsmodules-mediaCenter-MediaCenterH5Api, reason: not valid java name */
    /* synthetic */ void m751xb9247c79(long j, int i, Object obj) {
        LogUtil.c(TAG, "init result:", Integer.valueOf(i));
        if (i == 0) {
            onSuccessCallback(j);
        } else {
            onFailureCallback(j, (String) obj);
        }
    }

    @JavascriptInterface
    public void play(long j) {
        this.mMediaCenter.k();
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void pause(long j) {
        this.mMediaCenter.m();
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void stop(long j) {
        this.mMediaCenter.l();
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void seekToPosition(long j, long j2) {
        LogUtil.c(TAG, "seekToPosition position:", Long.valueOf(j2));
        this.mMediaCenter.a(j2);
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void setPlayList(long j, String str) {
        LogUtil.c(TAG, "setPlayList list:", str);
        List<enq> list = (List) new Gson().fromJson(str, new TypeToken<List<enq>>() { // from class: com.huawei.operation.h5pro.jsmodules.mediaCenter.MediaCenterH5Api.1
        }.getType());
        if (CollectionUtils.d(list)) {
            onFailureCallback(j, "playList is empty");
        } else {
            this.mMediaCenter.a(list);
            onSuccessCallback(j);
        }
    }

    @JavascriptInterface
    public void getPlayList(long j) {
        List<enq> j2 = this.mMediaCenter.j();
        onSuccessCallback(j, !CollectionUtils.d(j2) ? new Gson().toJson(j2) : "");
    }

    @JavascriptInterface
    public void getPlayStatus(long j) {
        LogUtil.c(TAG, "getPlayStatus");
        this.mPlayStatusSubscriber.setCallbackId(j);
        sendPlayStatusToH5(j);
    }

    @JavascriptInterface
    public void autoStop(long j, long j2) {
        LogUtil.c(TAG, "autoStop time:", Long.valueOf(j2));
        this.mMediaCenter.b(j2);
        onSuccessCallback(j, Long.valueOf(this.mMediaCenter.b()));
    }

    @JavascriptInterface
    public void setPlayMode(long j, String str) {
        LogUtil.c(TAG, "setPlayMode playMode:", str);
        if (TextUtils.isEmpty(str)) {
            onFailureCallback(j, "playMode is empty");
            return;
        }
        try {
            this.mMediaCenter.e(IAudioPlayer.PlayMode.valueOf(str));
            onSuccessCallback(j);
        } catch (IllegalArgumentException unused) {
            onFailureCallback(j, "playMode is invalid");
        }
    }

    @JavascriptInterface
    public void getPlayMode(long j) {
        onSuccessCallback(j, this.mMediaCenter.h().name());
    }

    @JavascriptInterface
    public void skipTo(long j, int i) {
        LogUtil.c(TAG, "skipTo index:", Integer.valueOf(i));
        this.mMediaCenter.b(i);
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void cancelAutoStop(long j) {
        this.mMediaCenter.d();
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void getAutoStopTime(long j) {
        onSuccessCallback(j, Long.valueOf(this.mMediaCenter.b()));
    }

    @JavascriptInterface
    public void initMiniPlayer(long j) {
        LogUtil.c(TAG, "initMiniPlayer");
        if (this.mMiniBottomPlayerForH5 == null) {
            this.mMiniBottomPlayerForH5 = new MiniBottomPlayer(BaseApplication.e());
        }
        if (!(nkr.d().cwW_() instanceof MiniBottomPlayer) && this.mMediaCenter.g() != 0) {
            nkr.d().cwX_(new MiniBottomPlayer(BaseApplication.e()));
            nkr.d().b(true);
        }
        onSuccessCallback(j);
    }

    @JavascriptInterface
    public void showMiniPlayer(long j, String str) {
        LogUtil.c(TAG, "showMiniPlayer parameter: ", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            boolean optBoolean = jSONObject.optBoolean("showMiniPlayer", true);
            int i = jSONObject.has("minibarBottomMargin") ? jSONObject.getInt("minibarBottomMargin") : 0;
            LogUtil.c(TAG, "showMiniPlayer : ", Boolean.valueOf(optBoolean));
            View cwW_ = nkr.d().cwW_();
            if (optBoolean) {
                if ((this.mMediaCenter.g() == 3 || this.mMediaCenter.g() == 6) && cwW_ == null) {
                    nkr.d().cwX_(new MiniBottomPlayer(BaseApplication.e()));
                    nkr.d().b(true);
                }
                if (cwW_ != null) {
                    if (this.mMiniBottomPlayerForH5 == null) {
                        this.mMiniBottomPlayerForH5 = new MiniBottomPlayer(BaseApplication.e());
                    }
                    addMinibarForH5(this.mMiniBottomPlayerForH5, i);
                    onSuccessCallback(j, Integer.valueOf(this.mContext.getResources().getDimensionPixelSize(R.dimen._2131363060_res_0x7f0a04f4)));
                    return;
                }
                onSuccessCallback(j);
                return;
            }
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.mediaCenter.MediaCenterH5Api.2
                @Override // java.lang.Runnable
                public void run() {
                    if (MediaCenterH5Api.this.mMiniBottomPlayerForH5 != null) {
                        MediaCenterH5Api.this.mMiniBottomPlayerForH5.setVisibility(8);
                    }
                }
            });
            onSuccessCallback(j);
        } catch (JSONException unused) {
            onFailureCallback(j, "loadH5ProApp fail:parameter invalid");
        }
    }

    @JavascriptInterface
    public void removeMinibarFromOldLayout(long j) {
        LogUtil.c(TAG, "removeMinibarFromOldLayout");
        removeMinibar();
        onSuccessCallback(j);
    }

    private void removeMinibar() {
        if (TextUtils.isEmpty(this.mMinibarTag) || this.mH5ProViewControls == null) {
            return;
        }
        LogUtil.c(TAG, "removeMinibar removeNativeView");
        this.mH5ProViewControls.removeNativeView(this.mMinibarTag);
        this.mMinibarTag = "";
    }

    private void addMinibarForH5(final MiniBottomPlayer miniBottomPlayer, int i) {
        final RelativeLayout.LayoutParams layoutParams;
        LogUtil.c(TAG, "addMinibarForH5");
        ViewGroup.LayoutParams layoutParams2 = miniBottomPlayer.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(layoutParams2);
        }
        layoutParams.addRule(12);
        layoutParams.addRule(14);
        layoutParams.bottomMargin = i;
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.operation.h5pro.jsmodules.mediaCenter.MediaCenterH5Api.3
            @Override // java.lang.Runnable
            public void run() {
                miniBottomPlayer.setLayoutParams(layoutParams);
                miniBottomPlayer.setVisibility(0);
                H5ProNativeView h5ProNativeView = new H5ProNativeView(miniBottomPlayer, null);
                if (MediaCenterH5Api.this.mH5ProViewControls != null) {
                    MediaCenterH5Api mediaCenterH5Api = MediaCenterH5Api.this;
                    mediaCenterH5Api.mMinibarTag = mediaCenterH5Api.mH5ProViewControls.showNativeView(h5ProNativeView);
                }
            }
        });
    }

    @JavascriptInterface
    public void setBusinessTag(long j, String str) {
        LogUtil.c(TAG, "setBusinessTag parameter : ", str);
        dow.c().e(str);
        onSuccessCallback(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPlayStatusToH5(long j) {
        int f = this.mMediaCenter.f();
        List<enq> j2 = this.mMediaCenter.j();
        if (CollectionUtils.c(j2, f)) {
            onFailureCallback(j, "get current audio fail");
            return;
        }
        enq enqVar = j2.get(f);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(KEY_MEDIA_ID, this.mMediaCenter.i());
        jsonObject.addProperty(KEY_PLAY_STATUS, Integer.valueOf(this.mMediaCenter.g()));
        jsonObject.addProperty("date", enqVar.d());
        jsonObject.addProperty("position", Long.valueOf(this.mMediaCenter.a()));
        jsonObject.addProperty(KEY_INDEX, Integer.valueOf(f));
        jsonObject.addProperty(KEY_IS_AUTO_STOP, Boolean.valueOf(this.mMediaCenter.o()));
        onSuccessCallback(j, new Gson().toJson((JsonElement) jsonObject));
    }

    public H5ProViewControls getH5ProViewControls() {
        if (this.mH5ProInstance == null) {
            LogUtil.e(TAG, "getH5ProViewControls failed, cause mH5ProInstance is null!");
            return null;
        }
        return this.mH5ProInstance.getViewControls();
    }

    @JavascriptInterface
    public boolean isNeedShowMobileDataDialog(String str) {
        LogUtil.c(TAG, "isNeedShowWifiDialog enter.");
        return this.mMediaCenter.e(str);
    }

    @JavascriptInterface
    public void checkMobileDataDialogBeforePlay(final long j, String str) {
        this.mMediaCenter.b(new MediaCenterCallback() { // from class: com.huawei.operation.h5pro.jsmodules.mediaCenter.MediaCenterH5Api$$ExternalSyntheticLambda1
            @Override // com.huawei.health.musicservice.mediacenter.MediaCenterCallback
            public final void onResponse(int i, String str2) {
                MediaCenterH5Api.this.m750xcdd9027f(j, i, str2);
            }
        }, str);
    }

    /* renamed from: lambda$checkMobileDataDialogBeforePlay$1$com-huawei-operation-h5pro-jsmodules-mediaCenter-MediaCenterH5Api, reason: not valid java name */
    /* synthetic */ void m750xcdd9027f(long j, int i, String str) {
        LogUtil.c(TAG, "checkMobileDataDialogBeforePlay when play = ", Integer.valueOf(i));
        if (i == 0) {
            onSuccessCallback(j, "success");
        } else {
            onSuccessCallback(j, ParamConstants.CallbackMethod.ON_FAIL);
        }
    }

    class H5PlayStatusSubscriber extends eoi.e {
        private long mCallbackId;

        private H5PlayStatusSubscriber() {
        }

        public void setCallbackId(long j) {
            this.mCallbackId = j;
        }

        @Override // eoi.e
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            MediaCenterH5Api.this.sendPlayStatusToH5(this.mCallbackId);
        }

        @Override // eoi.e
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            MediaCenterH5Api.this.sendPlayStatusToH5(this.mCallbackId);
        }
    }
}
