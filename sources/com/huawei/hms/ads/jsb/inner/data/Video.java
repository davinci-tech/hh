package com.huawei.hms.ads.jsb.inner.data;

import com.huawei.openalliance.ad.inter.data.VideoInfo;

/* loaded from: classes4.dex */
public class Video {
    private String autoPlay;
    private Integer autoPlayAreaRatio;
    private String autoPlayWithSound;
    private Integer autoStopPlayAreaRatio;
    private int duration;
    private int fileSize;
    private float ratio;
    private String soundSwitch;
    private Integer timeBeforeAutoPlay;
    private String url;

    public Video(VideoInfo videoInfo) {
        this.url = videoInfo.g();
        this.autoStopPlayAreaRatio = Integer.valueOf(videoInfo.getAutoStopPlayAreaRatio());
        this.autoPlayAreaRatio = Integer.valueOf(videoInfo.getAutoPlayAreaRatio());
        this.timeBeforeAutoPlay = Integer.valueOf(videoInfo.getTimeBeforeVideoAutoPlay());
        this.autoPlay = videoInfo.getVideoAutoPlay();
        this.autoPlayWithSound = videoInfo.getVideoAutoPlayWithSound();
        this.duration = videoInfo.getVideoDuration();
        this.soundSwitch = videoInfo.getSoundSwitch();
        this.fileSize = videoInfo.getVideoFileSize();
        this.ratio = videoInfo.getVideoRatio() == null ? 1.7777778f : videoInfo.getVideoRatio().floatValue();
    }
}
