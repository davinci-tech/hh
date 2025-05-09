package com.huawei.hwsportmodel;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.util.HiJsonUtil;
import health.compact.a.CommonUtil;
import java.util.Map;

/* loaded from: classes5.dex */
public class TrackFreeDivingSegment extends CommonSegment {
    private static final int FIELD_NUM = 4;
    private static final int SECTION_DIVING_BREAK_TIME_INDEX = 4;
    private static final int SECTION_DIVING_MAX_DEPTH_INDEX = 2;
    private static final int SECTION_DIVING_UNDER_WATER_INDEX = 3;
    private static final int SECTION_MAP_INDEX = 5;
    private static final int SECTION_NUM_INDEX = 1;
    private static final int STRING_BUILDER_SIZE = 30;
    private static final String TAG = "Track_FreeDivingSegment";
    private static final long serialVersionUID = 5363526654579232971L;

    @SerializedName("mMap")
    private Map mMap;

    @SerializedName("mSectionDivingBreakTime")
    private int mSectionDivingBreakTime;

    @SerializedName("mSectionDivingMaxDepth")
    private int mSectionDivingMaxDepth;

    @SerializedName("mSectionDivingUnderWaterTime")
    private int mSectionDivingUnderWaterTime;

    @SerializedName("mSectionNum")
    private int mSectionNum;

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getFieldNum() {
        return 4;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public int getSportSegmentMode() {
        return 0;
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public String toTrackString() {
        StringBuffer stringBuffer = new StringBuffer(30);
        toTrackString(stringBuffer);
        return stringBuffer.toString();
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void toTrackString(StringBuffer stringBuffer) {
        stringBuffer.append("tp=sec").append(";").append("sn=").append(getSectionNum()).append(";").append("sdmv=").append(getSectionDivingMaxDepth()).append(";").append("sdut=").append(getSectionDivingUnderWaterTime()).append(";").append("sdbt=").append(getSectionDivingBreakTime()).append(";").append("smap=").append(HiJsonUtil.e(this.mMap)).append(";").append(System.lineSeparator());
    }

    @Override // com.huawei.hwsportmodel.CommonSegment
    public void fromTrackString(String[] strArr) {
        if (strArr == null || strArr.length < getFieldNum() + 1) {
            return;
        }
        this.mSectionNum = CommonUtil.e(strArr[1].split("=")[1], -1);
        this.mSectionDivingMaxDepth = CommonUtil.e(strArr[2].split("=")[1], -1);
        this.mSectionDivingUnderWaterTime = CommonUtil.e(strArr[3].split("=")[1], -1);
        this.mSectionDivingBreakTime = CommonUtil.e(strArr[4].split("=")[1], -1);
        if (strArr.length < 6) {
            return;
        }
        this.mMap = (Map) HiJsonUtil.e(strArr[5].split("=")[1], Map.class);
    }

    public int getSectionNum() {
        return this.mSectionNum;
    }

    public void setSectionNum(int i) {
        this.mSectionNum = i;
    }

    public int getSectionDivingMaxDepth() {
        return this.mSectionDivingMaxDepth;
    }

    public void setSectionDivingMaxDepth(int i) {
        this.mSectionDivingMaxDepth = i;
    }

    public int getSectionDivingUnderWaterTime() {
        return this.mSectionDivingUnderWaterTime;
    }

    public void setSectionDivingUnderWaterTime(int i) {
        this.mSectionDivingUnderWaterTime = i;
    }

    public int getSectionDivingBreakTime() {
        return this.mSectionDivingBreakTime;
    }

    public void setSectionDivingBreakTime(int i) {
        this.mSectionDivingBreakTime = i;
    }

    public void setMap(Map map) {
        this.mMap = map;
    }
}
