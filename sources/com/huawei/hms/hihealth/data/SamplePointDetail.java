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
public class SamplePointDetail extends aabq {
    public static final Parcelable.Creator<SamplePointDetail> CREATOR = new aabq.aab(SamplePointDetail.class);
    private static final String TAG = "SamplePointDetail";

    @aaby(id = 3)
    private String mDataTypeName;

    @aaby(id = 2)
    private long mEndTime;

    @aaby(id = 4)
    private List<SamplePointValueDetail> mSamplePointsValueList;

    @aaby(id = 1)
    private long mStartTime;

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public void setSamplePointsValueList(List<SamplePointValueDetail> list) {
        this.mSamplePointsValueList = list;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public void setDataTypeName(String str) {
        this.mDataTypeName = str;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public List<SamplePointValueDetail> getSamplePointsValueList() {
        return this.mSamplePointsValueList;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public String getDataTypeName() {
        return this.mDataTypeName;
    }

    @aabw
    public SamplePointDetail(@aabv(id = 1) long j, @aabv(id = 2) long j2, @aabv(id = 3) String str, @aabv(id = 4) List<SamplePointValueDetail> list) {
        StringBuilder aab = com.huawei.hms.health.aab.aab("SamplePointDetail() SafeParm entered, point size");
        aab.append(list.size());
        aab.toString();
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDataTypeName = str;
        this.mSamplePointsValueList = list;
    }

    public SamplePointDetail() {
        this.mStartTime = 0L;
        this.mEndTime = 0L;
        this.mDataTypeName = null;
        this.mSamplePointsValueList = new ArrayList();
        aabz.aabb(TAG, "SamplePointDetail() ");
    }
}
