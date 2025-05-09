package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class RespQueryAudiosPackageBySeriesId extends BaseResponse implements Parcelable {
    public static final Parcelable.Creator<RespQueryAudiosPackageBySeriesId> CREATOR = new Parcelable.Creator<RespQueryAudiosPackageBySeriesId>() { // from class: com.huawei.health.marketing.request.RespQueryAudiosPackageBySeriesId.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RespQueryAudiosPackageBySeriesId createFromParcel(Parcel parcel) {
            return new RespQueryAudiosPackageBySeriesId(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RespQueryAudiosPackageBySeriesId[] newArray(int i) {
            return new RespQueryAudiosPackageBySeriesId[i];
        }
    };
    private List<SleepAudioSeriesInfo> packageList;
    private int totalCount;

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RespQueryAudiosPackageBySeriesId(Parcel parcel) {
        super(parcel);
        this.totalCount = parcel.readInt();
        if (this.packageList == null) {
            this.packageList = new ArrayList();
        }
        parcel.readTypedList(this.packageList, SleepAudioSeriesInfo.CREATOR);
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public List<SleepAudioSeriesInfo> getPackageList() {
        return this.packageList;
    }

    public void setPackageList(List<SleepAudioSeriesInfo> list) {
        this.packageList = list;
    }

    @Override // com.huawei.health.marketing.request.BaseResponse, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.totalCount);
        parcel.writeTypedList(this.packageList);
    }
}
