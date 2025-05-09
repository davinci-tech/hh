package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class SampleSection extends aabq {
    public static final Parcelable.Creator<SampleSection> CREATOR = new aabq.aab(SampleSection.class);

    @aaby(id = 4)
    private long endTime;

    @aaby(id = 5)
    private List<SamplePoint> sectionDataList;

    @aaby(id = 1)
    private int sectionNum;

    @aaby(id = 2)
    private long sectionTime;

    @aaby(id = 3)
    private long startTime;

    public String toString() {
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("SampleSection{sectionNum = ");
        aab2.append(this.sectionNum);
        aab2.append(", sectionTime = ");
        aab2.append(this.sectionTime);
        aab2.append(", startTime = ");
        aab2.append(this.startTime);
        aab2.append(", endTime = ");
        aab2.append(this.endTime);
        aab2.append(", sectionDataList = ");
        aab2.append(this.sectionDataList);
        aab2.append("}");
        return aab2.toString();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.sectionNum), Long.valueOf(this.sectionTime), Long.valueOf(this.startTime), Long.valueOf(this.endTime), this.sectionDataList);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.startTime, TimeUnit.MILLISECONDS);
    }

    public static class Builder {
        private long endTime;
        private List<SamplePoint> sectionDataList = new ArrayList();
        private int sectionNum;
        private long sectionTime;
        private long startTime;

        public Builder setStartTime(long j, TimeUnit timeUnit) {
            this.startTime = TimeUnit.MILLISECONDS.convert(j, timeUnit);
            return this;
        }

        public Builder setSectionTime(long j, TimeUnit timeUnit) {
            this.sectionTime = TimeUnit.SECONDS.convert(j, timeUnit);
            return this;
        }

        public Builder setSectionNum(int i) {
            this.sectionNum = i;
            return this;
        }

        public Builder setSectionDataList(List<SamplePoint> list) {
            this.sectionDataList.addAll(list);
            return this;
        }

        public Builder setEndTime(long j, TimeUnit timeUnit) {
            this.endTime = TimeUnit.MILLISECONDS.convert(j, timeUnit);
            return this;
        }

        public SampleSection build() {
            int i = this.sectionNum;
            boolean z = false;
            Preconditions.checkArgument(i > 0 && i < 1000, "SampleSection:sectionNum must between 0 and 1000.");
            Preconditions.checkArgument(aacs.aaba(this.startTime), "SampleSection:startTime illegal.");
            if (aacs.aaba(this.endTime) && this.endTime >= this.startTime) {
                z = true;
            }
            Preconditions.checkArgument(z, "SampleSection:endtime should be later than start time.");
            Preconditions.checkArgument(aabz.aaba(this.sectionDataList).booleanValue(), "SampleSection:sectionDataList must not be empty.");
            return new SampleSection(this.sectionNum, this.sectionTime, this.startTime, this.endTime, this.sectionDataList);
        }

        public Builder addSectionData(SamplePoint samplePoint) {
            Preconditions.checkArgument(samplePoint != null, "sectionDataSamplePoint must not be null");
            this.sectionDataList.add(samplePoint);
            return this;
        }
    }

    public long getSectionTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.sectionTime, TimeUnit.SECONDS);
    }

    public int getSectionNum() {
        return this.sectionNum;
    }

    public List<SamplePoint> getSectionDataList() {
        return this.sectionDataList;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.endTime, TimeUnit.MILLISECONDS);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SampleSection)) {
            return false;
        }
        SampleSection sampleSection = (SampleSection) obj;
        return this.sectionNum == sampleSection.getSectionNum() && this.sectionTime == sampleSection.getSectionTime(TimeUnit.SECONDS) && this.startTime == sampleSection.getStartTime(TimeUnit.MILLISECONDS) && this.endTime == sampleSection.getEndTime(TimeUnit.MILLISECONDS) && java.util.Objects.equals(this.sectionDataList, sampleSection.getSectionDataList());
    }

    @aabw
    private SampleSection(@aabv(id = 1) int i, @aabv(id = 2) long j, @aabv(id = 3) long j2, @aabv(id = 4) long j3, @aabv(id = 5) List<SamplePoint> list) {
        this.sectionNum = i;
        this.sectionTime = j;
        this.startTime = j2;
        this.endTime = j3;
        this.sectionDataList = list;
    }

    public SampleSection() {
        this.sectionDataList = new ArrayList();
    }
}
