package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiDataDeleteOption implements Parcelable {
    public static final Parcelable.Creator<HiDataDeleteOption> CREATOR = new Parcelable.Creator<HiDataDeleteOption>() { // from class: com.huawei.hihealth.HiDataDeleteOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bur_, reason: merged with bridge method [inline-methods] */
        public HiDataDeleteOption createFromParcel(Parcel parcel) {
            return new HiDataDeleteOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HiDataDeleteOption[] newArray(int i) {
            return new HiDataDeleteOption[i];
        }
    };
    private int ownerId;
    private List<HiTimeInterval> times;
    private int[] types;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiDataDeleteOption() {
        this.times = new ArrayList();
    }

    public HiDataDeleteOption(int[] iArr) {
        initData(iArr);
    }

    protected HiDataDeleteOption(Parcel parcel) {
        this.times = parcel.createTypedArrayList(HiTimeInterval.CREATOR);
        this.types = parcel.createIntArray();
        this.ownerId = parcel.readInt();
    }

    private void initData(int[] iArr) {
        this.times = new ArrayList();
        setTypes(iArr);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.times);
        parcel.writeIntArray(this.types);
        parcel.writeInt(this.ownerId);
    }

    public void setTimeInterval(long j, long j2) {
        addTimeInterval(new HiTimeInterval(j, j2));
    }

    public void addTimeInterval(HiTimeInterval hiTimeInterval) {
        this.times.add(hiTimeInterval);
    }

    public int[] getTypes() {
        int[] iArr = this.types;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    public void setTypes(int[] iArr) {
        if (iArr == null) {
            this.types = null;
        } else {
            this.types = (int[]) iArr.clone();
        }
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int i) {
        this.ownerId = i;
    }

    public List<HiTimeInterval> getTimes() {
        return this.times;
    }

    public void setTimes(List<HiTimeInterval> list) {
        this.times = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiDataDeleteOption{times=");
        stringBuffer.append(this.times);
        stringBuffer.append(", types=");
        if (this.types == null) {
            stringBuffer.append(Constants.NULL);
        } else {
            stringBuffer.append('[');
            int i = 0;
            while (i < this.types.length) {
                stringBuffer.append(i == 0 ? "" : ", ").append(this.types[i]);
                i++;
            }
            stringBuffer.append(']');
        }
        stringBuffer.append(", ownerId=").append(this.ownerId);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
