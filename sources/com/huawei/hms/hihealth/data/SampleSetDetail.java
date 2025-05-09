package com.huawei.hms.hihealth.data;

import android.os.Parcelable;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aabz;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class SampleSetDetail extends aabq {
    public static final Parcelable.Creator<SampleSetDetail> CREATOR = new aabq.aab(SampleSetDetail.class);
    private static final String TAG = "DetailsSampleSet";

    @aaby(id = 3)
    private String mDataCollectorId;

    @aaby(id = 2)
    private long mEndTime;

    @aaby(id = 4)
    private List<SamplePointDetail> mSamplePoints;

    @aaby(id = 1)
    private long mStartTime;

    public void setmStartTime(long j) {
        this.mStartTime = j;
    }

    public void setmEndTime(long j) {
        this.mEndTime = j;
    }

    public void setSamplePoints(List<SamplePointDetail> list) {
        this.mSamplePoints = list;
    }

    public void setDataCollectorId(String str) {
        this.mDataCollectorId = str;
    }

    public long getmStartTime() {
        return this.mStartTime;
    }

    public long getmEndTime() {
        return this.mEndTime;
    }

    public List<SamplePointDetail> getSamplePoints() {
        return this.mSamplePoints;
    }

    public String getDataCollectorId() {
        return this.mDataCollectorId;
    }

    @aabw
    public SampleSetDetail(@aabv(id = 1) long j, @aabv(id = 2) long j2, @aabv(id = 3) String str, @aabv(id = 4) List<SamplePointDetail> list) {
        StringBuilder aab = com.huawei.hms.health.aab.aab("SampleSetDetail() entered, samplePoints size ");
        aab.append(list.size());
        aabz.aabb(TAG, aab.toString());
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDataCollectorId = str;
        this.mSamplePoints = list;
    }

    public SampleSetDetail() {
        this.mStartTime = 0L;
        this.mEndTime = 0L;
        this.mDataCollectorId = null;
        this.mSamplePoints = new ArrayList();
        aabz.aabb(TAG, "DetailsSampleSet() ");
    }
}
