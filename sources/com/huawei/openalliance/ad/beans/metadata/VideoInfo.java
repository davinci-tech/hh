package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.h;
import com.huawei.openalliance.ad.constant.NetworkTypeForControl;
import com.huawei.openalliance.ad.constant.ShowFlag;
import com.huawei.openalliance.ad.constant.VideoPlayFlag;
import com.huawei.openalliance.ad.constant.VideoShowMode;
import com.huawei.openalliance.ad.constant.VideoSoundFlag;

/* loaded from: classes5.dex */
public class VideoInfo {

    @h(e = {1, 100}, f = 100)
    private Integer autoPlayAreaRatio;

    @h(e = {0, 99}, f = 90)
    private Integer autoStopPlayAreaRatio;
    private int checkSha256Flag;
    private String sha256;
    private float splashSwitchTime;

    @a
    private String videoDownloadUrl;
    private int videoDuration;
    private int videoFileSize;
    private Float videoRatio;

    @h(b = VideoPlayFlag.class, d = "y")
    private String videoAutoPlayOnWifi = "y";

    @h(b = VideoSoundFlag.class, d = "n")
    private String videoAutoPlayWithSound = "n";
    private int timeBeforeVideoAutoPlay = 200;

    @h(b = VideoShowMode.class, c = 1)
    private int videoPlayMode = 1;

    @h(b = NetworkTypeForControl.class, c = 0)
    private int downloadNetwork = 0;

    @h(b = ShowFlag.class, d = "y")
    private String showSoundIcon = "y";

    public float o() {
        return this.splashSwitchTime;
    }

    public String n() {
        return this.showSoundIcon;
    }

    public Float m() {
        return this.videoRatio;
    }

    public int l() {
        return this.downloadNetwork;
    }

    public Integer k() {
        return this.autoStopPlayAreaRatio;
    }

    public Integer j() {
        return this.autoPlayAreaRatio;
    }

    public int i() {
        return this.checkSha256Flag;
    }

    public int h() {
        return this.videoPlayMode;
    }

    public String g() {
        return this.sha256;
    }

    public void f(int i) {
        this.downloadNetwork = i;
    }

    public int f() {
        return this.timeBeforeVideoAutoPlay;
    }

    public void e(String str) {
        this.showSoundIcon = str;
    }

    public void e(int i) {
        this.checkSha256Flag = i;
    }

    public String e() {
        return this.videoAutoPlayWithSound;
    }

    public void d(String str) {
        this.sha256 = str;
    }

    public void d(int i) {
        this.videoPlayMode = i;
    }

    public String d() {
        return this.videoAutoPlayOnWifi;
    }

    public void c(String str) {
        this.videoAutoPlayWithSound = str;
    }

    public void c(int i) {
        this.timeBeforeVideoAutoPlay = i;
    }

    public int c() {
        return this.videoFileSize;
    }

    public void b(String str) {
        this.videoAutoPlayOnWifi = str;
    }

    public void b(Integer num) {
        this.autoStopPlayAreaRatio = num;
    }

    public void b(int i) {
        this.videoFileSize = i;
    }

    public int b() {
        return this.videoDuration;
    }

    public void a(String str) {
        this.videoDownloadUrl = str;
    }

    public void a(Integer num) {
        this.autoPlayAreaRatio = num;
    }

    public void a(Float f) {
        this.videoRatio = f;
    }

    public void a(int i) {
        this.videoDuration = i;
    }

    public void a(float f) {
        this.splashSwitchTime = f;
    }

    public String a() {
        return this.videoDownloadUrl;
    }
}
