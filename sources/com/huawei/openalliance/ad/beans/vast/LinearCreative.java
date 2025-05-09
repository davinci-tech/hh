package com.huawei.openalliance.ad.beans.vast;

import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class LinearCreative {
    private int duration;
    private List<VastIcon> icons;
    private List<VastMediaFile> mediaFiles;
    private Map<String, List<Tracking>> trackingEvents;
    private VideoClicks videoClicks;

    public VideoClicks e() {
        return this.videoClicks;
    }

    public List<VastIcon> d() {
        return this.icons;
    }

    public Map<String, List<Tracking>> c() {
        return this.trackingEvents;
    }

    public void b(List<VastIcon> list) {
        this.icons = list;
    }

    public List<VastMediaFile> b() {
        return this.mediaFiles;
    }

    public void a(Map<String, List<Tracking>> map) {
        this.trackingEvents = map;
    }

    public void a(List<VastMediaFile> list) {
        this.mediaFiles = list;
    }

    public void a(VideoClicks videoClicks) {
        this.videoClicks = videoClicks;
    }

    public void a(int i) {
        this.duration = i;
    }

    public int a() {
        return this.duration;
    }
}
