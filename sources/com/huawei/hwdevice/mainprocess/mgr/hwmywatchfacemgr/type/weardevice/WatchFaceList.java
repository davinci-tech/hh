package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import java.util.List;

/* loaded from: classes5.dex */
public class WatchFaceList {
    private boolean isWatchfaceFull = false;
    private int leftSpace = -1;
    private List<WatchFace> watchFaceList;

    public List<WatchFace> getWatchFaceList() {
        return this.watchFaceList;
    }

    public void setWatchFaceList(List<WatchFace> list) {
        this.watchFaceList = list;
    }

    public boolean getIsWatchfaceFull() {
        return this.isWatchfaceFull;
    }

    public void setIsWatchfaceFull(boolean z) {
        this.isWatchfaceFull = z;
    }

    public int getLeftSpace() {
        return this.leftSpace;
    }

    public void setLeftSpace(int i) {
        this.leftSpace = i;
    }

    public static class WatchFace {
        private int watchFaceExpandType;
        private List<Integer> watchFaceId;
        private int watchFaceType;
        private List<Integer> watchFaceVersion;

        public List<Integer> getWatchFaceId() {
            return this.watchFaceId;
        }

        public void setWatchFaceId(List<Integer> list) {
            this.watchFaceId = list;
        }

        public List<Integer> getWatchFaceVersion() {
            return this.watchFaceVersion;
        }

        public void setWatchFaceVersion(List<Integer> list) {
            this.watchFaceVersion = list;
        }

        public int getWatchFaceType() {
            return this.watchFaceType;
        }

        public void setWatchFaceType(int i) {
            this.watchFaceType = i;
        }

        public int getWatchFaceExpandType() {
            return this.watchFaceExpandType;
        }

        public void setWatchFaceExpandType(int i) {
            this.watchFaceExpandType = i;
        }
    }
}
