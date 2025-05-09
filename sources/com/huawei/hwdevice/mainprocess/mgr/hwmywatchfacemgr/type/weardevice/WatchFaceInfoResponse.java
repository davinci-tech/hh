package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.weardevice;

import java.util.List;

/* loaded from: classes5.dex */
public class WatchFaceInfoResponse {
    private List<WatchFaceInfoStruct> watchFaceList;

    public List<WatchFaceInfoStruct> getWatchFaceList() {
        return this.watchFaceList;
    }

    public void setWatchFaceList(List<WatchFaceInfoStruct> list) {
        this.watchFaceList = list;
    }

    public static class WatchFaceInfoStruct {
        private List<Integer> watchFaceBrief;
        private List<Integer> watchFaceId;
        private List<Integer> watchFaceName;
        private int watchFaceSize;

        public List<Integer> getWatchFaceId() {
            return this.watchFaceId;
        }

        public void setWatchFaceId(List<Integer> list) {
            this.watchFaceId = list;
        }

        public List<Integer> getWatchFaceName() {
            return this.watchFaceName;
        }

        public void setWatchFaceName(List<Integer> list) {
            this.watchFaceName = list;
        }

        public List<Integer> getWatchFaceBrief() {
            return this.watchFaceBrief;
        }

        public void setWatchFaceBrief(List<Integer> list) {
            this.watchFaceBrief = list;
        }

        public int getWatchFaceSize() {
            return this.watchFaceSize;
        }

        public void setWatchFaceSize(int i) {
            this.watchFaceSize = i;
        }
    }
}
