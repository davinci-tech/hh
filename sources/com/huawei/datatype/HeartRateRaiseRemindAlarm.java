package com.huawei.datatype;

import com.huawei.hihealth.data.model.HiHeartRateData;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class HeartRateRaiseRemindAlarm {
    private static final int INIT_LIST_LENGTH = 10;
    private List<Entity> dataList;

    public List<Entity> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Entity> list) {
        this.dataList = list;
    }

    public static class Entity implements Comparable<Entity> {
        private long endTime;
        private int maxNumber;
        private int minNumber;
        private List<HiHeartRateData> rateDatas = new ArrayList(10);
        private long startTime;
        private int threshold;

        public String toString() {
            return "Entity{startTime=" + this.startTime + ", endTime=" + this.endTime + ", minNumber=" + this.minNumber + ", maxNumber=" + this.maxNumber + "}, threshold=" + this.threshold + '}';
        }

        public long getStartTime() {
            return this.startTime;
        }

        public void setStartTime(long j) {
            this.startTime = j;
        }

        public long getEndTime() {
            return this.endTime;
        }

        public void setEndTime(long j) {
            this.endTime = j;
        }

        public int getMinNumber() {
            return this.minNumber;
        }

        public void setMinNumber(int i) {
            this.minNumber = i;
        }

        public int getMaxNumber() {
            return this.maxNumber;
        }

        public void setMaxNumber(int i) {
            this.maxNumber = i;
        }

        public List<HiHeartRateData> getRateData() {
            return this.rateDatas;
        }

        public void setRateData(List<HiHeartRateData> list) {
            this.rateDatas = list;
        }

        public int getThreshold() {
            return this.threshold;
        }

        public void setThreshold(int i) {
            this.threshold = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(Entity entity) {
            return Long.compare(getStartTime(), entity.getStartTime());
        }

        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        public int hashCode() {
            return super.hashCode();
        }
    }

    public String toString() {
        return "HeartRateRaiseRemindAlarm{dataList=" + this.dataList + '}';
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
