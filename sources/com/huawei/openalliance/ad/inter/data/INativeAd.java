package com.huawei.openalliance.ad.inter.data;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.hms.ads.AdFeedbackListener;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.openalliance.ad.utils.cs;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public interface INativeAd extends IAd, IAdEx, Serializable {
    List<String> getAdCloseKeyWords();

    List<AdvertiserInfo> getAdvertiserInfo();

    AudioInfo getAudioInfo();

    String getDescription();

    Map<String, String> getExt();

    List<FeedbackInfo> getFeedbackInfoList();

    ImageInfo getIcon();

    List<ImageInfo> getImageInfos();

    String getIntentUri();

    int getInterActionType();

    List<String> getInvalidContentIds();

    String getJumpText(Context context);

    String getLandWebUrl();

    String getPrivacyLink();

    List<ImageInfo> getRawImageInfos();

    String getSlotId();

    List<String> getSubDescription();

    String getTitle();

    VideoConfiguration getVideoConfiguration();

    VideoInfo getVideoInfo();

    boolean hasAdvertiserInfo();

    boolean isAutoDownloadApp();

    boolean isClicked();

    boolean isUseGaussianBlur();

    boolean isValid(Context context);

    boolean isVideoAd();

    void onAdClose(Context context, List<String> list);

    boolean onFeedback(Context context, int i, List<FeedbackInfo> list);

    boolean recordClickEvent(Context context, Bundle bundle);

    boolean recordImpressionEvent(Context context, Bundle bundle);

    boolean recordShowStartEvent(Context context, Bundle bundle);

    void setAutoDownloadApp(boolean z);

    void setVideoConfiguration(VideoConfiguration videoConfiguration);

    boolean showFeedback(Context context, View view, AdFeedbackListener adFeedbackListener);

    boolean triggerClick(Context context, Bundle bundle);

    /* loaded from: classes9.dex */
    public static class Converter {
        public static String serialization(INativeAd iNativeAd) {
            return cs.a(iNativeAd);
        }

        public static INativeAd deserialization(String str) {
            Serializable b = cs.b(str);
            if (b instanceof e) {
                return a((e) b);
            }
            return null;
        }

        private static e a(e eVar) {
            VideoInfo videoInfo = eVar.getVideoInfo();
            if (videoInfo != null) {
                videoInfo.e(0);
                videoInfo.e(videoInfo.getVideoAutoPlayWithSound());
            }
            eVar.a(videoInfo);
            return eVar;
        }
    }
}
