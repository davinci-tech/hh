package com.huawei.featureuserprofile.route.hwkmlmodel;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class GxTrack {
    private List<String> mGxCoordinates = new ArrayList();
    private List<Long> mWhenList = new ArrayList();
    private List<GxTrackPoint> mGxTrackPoints = new ArrayList();

    public static class GxTrackPoint {
        private double mEle = Double.MIN_VALUE;
        private double mLat;
        private double mLon;
        private long mTime;

        public double getLon() {
            return this.mLon;
        }

        public void setLon(double d) {
            this.mLon = d;
        }

        public double getLat() {
            return this.mLat;
        }

        public void setLat(double d) {
            this.mLat = d;
        }

        public double getEle() {
            return this.mEle;
        }

        public void setEle(double d) {
            this.mEle = d;
        }

        public long getTime() {
            return this.mTime;
        }

        public void setTime(long j) {
            this.mTime = j;
        }
    }

    public List<String> getGxCoordinates() {
        makeGxTrackPoint();
        return this.mGxCoordinates;
    }

    public void setGxCoordinates(List<String> list) {
        this.mGxCoordinates = list;
    }

    public List<Long> getWhen() {
        return this.mWhenList;
    }

    public void setWhen(List<Long> list) {
        this.mWhenList = list;
    }

    public void addGxCoordinates(String str) {
        this.mGxCoordinates.add(str);
    }

    public void addWhenList(long j) {
        this.mWhenList.add(Long.valueOf(j));
    }

    public List<GxTrackPoint> getGxTrackPoints() {
        return this.mGxTrackPoints;
    }

    public void setGxTrackPoints(List<GxTrackPoint> list) {
        this.mGxTrackPoints = list;
    }

    private void makeGxTrackPoint() {
        for (int i = 0; i < this.mGxCoordinates.size(); i++) {
            GxTrackPoint gxTrackPoint = new GxTrackPoint();
            String[] split = this.mGxCoordinates.get(i).split(" ");
            gxTrackPoint.mLon = Double.valueOf(split[0]).doubleValue();
            gxTrackPoint.mLat = Double.valueOf(split[1]).doubleValue();
            if (split.length > 2) {
                gxTrackPoint.mEle = Double.valueOf(split[2]).doubleValue();
            }
            gxTrackPoint.mTime = this.mWhenList.get(i).longValue();
            this.mGxTrackPoints.add(gxTrackPoint);
        }
    }
}
