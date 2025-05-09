package com.huawei.health.ecologydevice.open.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* loaded from: classes3.dex */
public class RopeBestRecord {
    private BestRecordData bestReports;
    private int resultCode;
    private String resultDesc;
    private UserCycleReportData userCycleReport;

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String str) {
        this.resultDesc = str;
    }

    public BestRecordData getBestReports() {
        return this.bestReports;
    }

    public UserCycleReportData getUserCycleReport() {
        return this.userCycleReport;
    }

    public static class BestRecordData implements Parcelable {
        public static final Parcelable.Creator<BestRecordData> CREATOR = new Parcelable.Creator<BestRecordData>() { // from class: com.huawei.health.ecologydevice.open.data.model.RopeBestRecord.BestRecordData.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BestRecordData createFromParcel(Parcel parcel) {
                return new BestRecordData(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BestRecordData[] newArray(int i) {
                return new BestRecordData[i];
            }
        };
        private List<RopeBestRecordBean> bestRopeSkippingContinuousCount;
        private List<RopeBestRecordBean> bestRopeSkippingEnduranceAbility;
        private List<RopeBestRecordBean> bestRopeSkippingEnduranceTimeAbility;
        private List<RopeBestRecordBean> bestRopeSkippingMaxSpeed1MIN;
        private List<RopeBestRecordBean> bestRopeSkippingSingleCount;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected BestRecordData(Parcel parcel) {
            if (parcel != null) {
                this.bestRopeSkippingEnduranceTimeAbility = parcel.createTypedArrayList(RopeBestRecordBean.CREATOR);
                this.bestRopeSkippingMaxSpeed1MIN = parcel.createTypedArrayList(RopeBestRecordBean.CREATOR);
                this.bestRopeSkippingEnduranceAbility = parcel.createTypedArrayList(RopeBestRecordBean.CREATOR);
                this.bestRopeSkippingSingleCount = parcel.createTypedArrayList(RopeBestRecordBean.CREATOR);
                this.bestRopeSkippingContinuousCount = parcel.createTypedArrayList(RopeBestRecordBean.CREATOR);
            }
        }

        public List<RopeBestRecordBean> getBestRopeSkippingEnduranceTimeAbility() {
            return this.bestRopeSkippingEnduranceTimeAbility;
        }

        public void setBestRopeSkippingEnduranceTimeAbility(List<RopeBestRecordBean> list) {
            this.bestRopeSkippingEnduranceTimeAbility = list;
        }

        public List<RopeBestRecordBean> getBestRopeSkippingMaxSpeed1MIN() {
            return this.bestRopeSkippingMaxSpeed1MIN;
        }

        public void setBestRopeSkippingMaxSpeed1MIN(List<RopeBestRecordBean> list) {
            this.bestRopeSkippingMaxSpeed1MIN = list;
        }

        public List<RopeBestRecordBean> getBestRopeSkippingEnduranceAbility() {
            return this.bestRopeSkippingEnduranceAbility;
        }

        public void setBestRopeSkippingEnduranceAbility(List<RopeBestRecordBean> list) {
            this.bestRopeSkippingEnduranceAbility = list;
        }

        public List<RopeBestRecordBean> getBestRopeSkippingSingleCount() {
            return this.bestRopeSkippingSingleCount;
        }

        public void setBestRopeSkippingSingleCount(List<RopeBestRecordBean> list) {
            this.bestRopeSkippingSingleCount = list;
        }

        public List<RopeBestRecordBean> getBestRopeSkippingContinuousCount() {
            return this.bestRopeSkippingContinuousCount;
        }

        public void setBestRopeSkippingContinuousCount(List<RopeBestRecordBean> list) {
            this.bestRopeSkippingContinuousCount = list;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            if (parcel != null) {
                parcel.writeTypedList(this.bestRopeSkippingEnduranceTimeAbility);
                parcel.writeTypedList(this.bestRopeSkippingMaxSpeed1MIN);
                parcel.writeTypedList(this.bestRopeSkippingEnduranceAbility);
                parcel.writeTypedList(this.bestRopeSkippingSingleCount);
                parcel.writeTypedList(this.bestRopeSkippingContinuousCount);
            }
        }

        public String toString() {
            return "BestRecordData{bestRopeSkippingEnduranceTimeAbility=" + this.bestRopeSkippingEnduranceTimeAbility + ", bestRopeSkippingMaxSpeed1MIN=" + this.bestRopeSkippingMaxSpeed1MIN + ", bestRopeSkippingEnduranceAbility=" + this.bestRopeSkippingEnduranceAbility + ", bestRopeSkippingSingleCount=" + this.bestRopeSkippingSingleCount + ", bestRopeSkippingContinuousCount=" + this.bestRopeSkippingContinuousCount + '}';
        }
    }

    public static class UserCycleReportData {
        private BestRecordData bestReports;

        public BestRecordData getBestReports() {
            return this.bestReports;
        }
    }
}
