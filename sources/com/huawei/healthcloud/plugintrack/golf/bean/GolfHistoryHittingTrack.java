package com.huawei.healthcloud.plugintrack.golf.bean;

import java.util.List;

/* loaded from: classes8.dex */
public class GolfHistoryHittingTrack {
    private List<GolfTrackLatLng> tracks;
    private int validTracks;

    public int getValidTracks() {
        return this.validTracks;
    }

    public void setValidTracks(int i) {
        this.validTracks = i;
    }

    public List<GolfTrackLatLng> getTracks() {
        return this.tracks;
    }

    public void setTracks(List<GolfTrackLatLng> list) {
        this.tracks = list;
    }
}
