package com.huawei.ui.homehealth.sportsrecordingcard;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class SportsRecordingBean implements Parcelable {
    public static final Parcelable.Creator<SportsRecordingBean> CREATOR = new Parcelable.Creator<SportsRecordingBean>() { // from class: com.huawei.ui.homehealth.sportsrecordingcard.SportsRecordingBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: dhd_, reason: merged with bridge method [inline-methods] */
        public SportsRecordingBean createFromParcel(Parcel parcel) {
            return new SportsRecordingBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SportsRecordingBean[] newArray(int i) {
            return new SportsRecordingBean[i];
        }
    };
    private static final int INIT_SIZE = 16;
    private static final String TAG = "SportsRecordingBean";
    private List<RelativeSportData> mChildSportItems;
    private int mShowType;
    private String mSportData;
    private long mSportEndTime;
    private String mSportKeepTime;
    private String mSportSpeed;
    private String mSportSpeedUnit;
    private long mSportStartTime;
    private String mSportTime;
    private String mSportTypeName;
    private String mSportUnit;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SportsRecordingBean() {
        this.mShowType = 0;
        this.mSportTime = "";
        this.mSportUnit = "";
        this.mSportTypeName = "";
        this.mSportSpeedUnit = "";
        this.mSportKeepTime = "";
        this.mSportData = "";
        this.mSportSpeed = "";
        this.mSportStartTime = 0L;
        this.mSportEndTime = 0L;
    }

    protected SportsRecordingBean(Parcel parcel) {
        this.mShowType = 0;
        this.mSportTime = "";
        this.mSportUnit = "";
        this.mSportTypeName = "";
        this.mSportSpeedUnit = "";
        this.mSportKeepTime = "";
        this.mSportData = "";
        this.mSportSpeed = "";
        this.mSportStartTime = 0L;
        this.mSportEndTime = 0L;
        this.mShowType = parcel.readInt();
        this.mSportTime = parcel.readString();
        this.mSportUnit = parcel.readString();
        this.mSportTypeName = parcel.readString();
        this.mSportSpeedUnit = parcel.readString();
        this.mSportKeepTime = parcel.readString();
        this.mSportData = parcel.readString();
        this.mSportSpeed = parcel.readString();
        this.mSportStartTime = parcel.readLong();
        this.mSportEndTime = parcel.readLong();
        ArrayList arrayList = new ArrayList(16);
        this.mChildSportItems = arrayList;
        parcel.readList(arrayList, RelativeSportData.class.getClassLoader());
    }

    public List<RelativeSportData> getChildSportItems() {
        return this.mChildSportItems;
    }

    public void setChildSportItems(List<RelativeSportData> list) {
        this.mChildSportItems = list;
    }

    public void setSportSpeedUnit(String str) {
        this.mSportSpeedUnit = str;
    }

    public void setSportSpeed(String str) {
        this.mSportSpeed = str;
    }

    public long getSportStartTime() {
        return this.mSportStartTime;
    }

    public void setSportStartTime(long j) {
        this.mSportStartTime = j;
    }

    public long getSportEndTime() {
        return this.mSportEndTime;
    }

    public void setSportEndTime(long j) {
        this.mSportEndTime = j;
    }

    public int getShowType() {
        return this.mShowType;
    }

    public void setShowType(int i) {
        this.mShowType = i;
    }

    public String getSportTime() {
        return this.mSportTime;
    }

    public void setSportTime(String str) {
        this.mSportTime = str;
    }

    public String getSportUnit() {
        return this.mSportUnit;
    }

    public void setSportUnit(String str) {
        this.mSportUnit = str;
    }

    public String getSportTypeName() {
        return this.mSportTypeName;
    }

    public void setSportTypeName(String str) {
        this.mSportTypeName = str;
    }

    public void setSportKeepTime(String str) {
        this.mSportKeepTime = str;
    }

    public String getSportKeepTime() {
        return this.mSportKeepTime;
    }

    public String getSportData() {
        return this.mSportData;
    }

    public void setSportData(String str) {
        this.mSportData = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.b(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeInt(this.mShowType);
        parcel.writeString(this.mSportTime);
        parcel.writeString(this.mSportUnit);
        parcel.writeString(this.mSportTypeName);
        parcel.writeString(this.mSportSpeedUnit);
        parcel.writeString(this.mSportKeepTime);
        parcel.writeString(this.mSportData);
        parcel.writeString(this.mSportSpeed);
        parcel.writeLong(this.mSportStartTime);
        parcel.writeLong(this.mSportEndTime);
        parcel.writeList(this.mChildSportItems);
    }
}
