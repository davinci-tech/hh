package com.huawei.openalliance.ad.beans.inner;

import com.huawei.openalliance.ad.utils.ao;
import java.util.List;

/* loaded from: classes5.dex */
public class AdEventReport {
    private String activityName;
    private int adType;
    private String appPkgName;
    private String appSdkVersion;
    private String contentId;
    private String customData;
    private Float density;
    private String destination;
    private Integer endProgress;
    private Long endTime;
    private String eventType;
    private Integer intentDest;
    private Integer intentFailReason;
    private String isAdContainerSizeMatched;
    private List<String> keyWords;
    private boolean mute;
    private int opTimes;
    private boolean phyShow;
    private String requestId;
    private Long showDuration;
    private String showId = String.valueOf(ao.c());
    private Integer showRatio;
    private Integer sld;
    private Integer source;
    private Integer startProgress;
    private Long startTime;
    private Integer upX;
    private Integer upY;
    private String userId;
    private Long videoTime;
    private int x;
    private int y;

    public Long o() {
        return this.videoTime;
    }

    public String n() {
        return this.activityName;
    }

    public String m() {
        return this.customData;
    }

    public List<String> l() {
        return this.keyWords;
    }

    public String k() {
        return this.destination;
    }

    public int j() {
        return this.y;
    }

    public int i() {
        return this.x;
    }

    public Integer h() {
        return this.endProgress;
    }

    public Integer g() {
        return this.startProgress;
    }

    public Long f() {
        return this.endTime;
    }

    public Long e() {
        return this.startTime;
    }

    public void d(String str) {
        this.isAdContainerSizeMatched = str;
    }

    public Integer d() {
        return this.source;
    }

    public void c(String str) {
        this.requestId = str;
    }

    public void c(int i) {
        this.y = i;
    }

    public Integer c() {
        return this.showRatio;
    }

    public void b(String str) {
        this.showId = str;
    }

    public void b(int i) {
        this.x = i;
    }

    public Long b() {
        return this.showDuration;
    }

    public boolean a() {
        return this.phyShow;
    }

    public void a(List<String> list) {
        this.keyWords = list;
    }

    public void a(String str) {
        this.contentId = str;
    }

    public void a(int i) {
        this.adType = i;
    }
}
