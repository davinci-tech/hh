package com.huawei.datatype;

import com.huawei.hihealth.data.model.HiStressMetaData;
import defpackage.jdy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PeriodRriEntity {
    private static final int DEFAULT_RRI_SQI_LIST_SIZE = 14;
    private int bitmap;
    private long failTime = -1;
    private long fileSize;
    private String headerReserved;
    private List<HiStressMetaData> pressDataList;
    private List<RriDataEntity> rriAndSqiList;
    private int size;
    private int version;

    public static class RriDataEntity {
        private int intensity;
        private String reserved;
        private List<Integer> rriList;
        private List<Integer> sqiList;
        private long startTime;

        public void addRri(int i) {
            if (this.rriList == null) {
                this.rriList = new ArrayList(14);
            }
            this.rriList.add(Integer.valueOf(i));
        }

        public void addSqi(int i) {
            if (this.rriList == null) {
                this.sqiList = new ArrayList(14);
            }
            this.sqiList.add(Integer.valueOf(i));
        }

        public List<Integer> fetchRriList() {
            return (List) jdy.d(this.rriList);
        }

        public void configRriList(List<Integer> list) {
            this.rriList = (List) jdy.d(list);
        }

        public List<Integer> fetchSqiList() {
            return (List) jdy.d(this.sqiList);
        }

        public void configSqiList(List<Integer> list) {
            this.sqiList = (List) jdy.d(list);
        }

        public long fetchStartTime() {
            return ((Long) jdy.d(Long.valueOf(this.startTime))).longValue();
        }

        public void configStartTime(long j) {
            this.startTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
        }

        public int fetchIntensity() {
            return ((Integer) jdy.d(Integer.valueOf(this.intensity))).intValue();
        }

        public void configIntensity(int i) {
            this.intensity = i;
        }

        public String fetchReserved() {
            return (String) jdy.d(this.reserved);
        }

        public void configReserved(String str) {
            this.reserved = (String) jdy.d(str);
        }

        public String toString() {
            return "RriDataEntity{rriList=" + this.rriList + ", sqiList=" + this.sqiList + ", startTime=" + this.startTime + ", intensity=" + this.intensity + ", reserved='" + this.reserved + "'}";
        }
    }

    public long fetchFileSize() {
        return ((Long) jdy.d(Long.valueOf(this.fileSize))).longValue();
    }

    public void configFileSize(long j) {
        this.fileSize = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int fetchVersion() {
        return ((Integer) jdy.d(Integer.valueOf(this.version))).intValue();
    }

    public void configVersion(int i) {
        this.version = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int fetchSize() {
        return ((Integer) jdy.d(Integer.valueOf(this.size))).intValue();
    }

    public void configSize(int i) {
        this.size = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String fetchHeaderReserved() {
        return (String) jdy.d(this.headerReserved);
    }

    public void configHeaderReserved(String str) {
        this.headerReserved = (String) jdy.d(str);
    }

    public List<RriDataEntity> fetchRriDataList() {
        return (List) jdy.d(this.rriAndSqiList);
    }

    public void configRriDataList(List<RriDataEntity> list) {
        this.rriAndSqiList = (List) jdy.d(list);
    }

    public void configFailTime(long j) {
        this.failTime = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public long fetchFailTime() {
        return ((Long) jdy.d(Long.valueOf(this.failTime))).longValue();
    }

    public void configPressDataList(List<HiStressMetaData> list) {
        this.pressDataList = (List) jdy.d(list);
    }

    public List<HiStressMetaData> fetchPressDataList() {
        return (List) jdy.d(this.pressDataList);
    }

    public void configBitmap(int i) {
        this.bitmap = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int fetchBitmap() {
        return ((Integer) jdy.d(Integer.valueOf(this.bitmap))).intValue();
    }

    public String toString() {
        return "PeriodRriEntity{fileSize=" + this.fileSize + ", version=" + this.version + ", size=" + this.size + ", headerReserved='" + this.headerReserved + "', rriAndSqiList=" + this.rriAndSqiList + ", pressDataList=" + this.pressDataList + ", bitmap=" + this.bitmap + ", failTime=" + this.failTime + '}';
    }
}
