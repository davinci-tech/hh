package com.huawei.hihealth.device.indoor;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.device.open.data.MeasureRecord;
import com.huawei.hihealth.device.open.data.MeasureResult;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MeasureResultImpl implements MeasureResult, Parcelable {
    public static final Parcelable.Creator<MeasureResultImpl> CREATOR = new Parcelable.Creator<MeasureResultImpl>() { // from class: com.huawei.hihealth.device.indoor.MeasureResultImpl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MeasureResultImpl createFromParcel(Parcel parcel) {
            return new MeasureResultImpl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MeasureResultImpl[] newArray(int i) {
            return new MeasureResultImpl[0];
        }
    };
    private ArrayList<String> mDataTypes;
    private ArrayList<MeasureRecordImpl> mRecords;
    private ArrayList<String> mUnits;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public MeasureResultImpl(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList != null) {
            this.mDataTypes = new ArrayList<>(arrayList);
        }
        if (arrayList2 != null) {
            this.mUnits = new ArrayList<>(arrayList2);
        }
        this.mRecords = new ArrayList<>();
    }

    private MeasureResultImpl(Parcel parcel) {
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureResult
    public MeasureRecord createRecord() {
        return new MeasureRecordImpl(this);
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureResult
    public void joinRecord(MeasureRecord measureRecord) {
        if (measureRecord == null || !(measureRecord instanceof MeasureRecordImpl) || this.mRecords.contains(measureRecord)) {
            return;
        }
        this.mRecords.add((MeasureRecordImpl) measureRecord);
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureResult
    public MeasureRecord createAndJoinRecord() {
        MeasureRecordImpl measureRecordImpl = new MeasureRecordImpl(this);
        this.mRecords.add(measureRecordImpl);
        return measureRecordImpl;
    }

    @Override // com.huawei.hihealth.device.open.data.MeasureResult
    public ArrayList<MeasureRecord> getRecords() {
        ArrayList<MeasureRecord> arrayList = new ArrayList<>();
        Iterator<MeasureRecordImpl> it = this.mRecords.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public int getFieldNum() {
        ArrayList<String> arrayList = this.mDataTypes;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public ArrayList<String> getDataTypes() {
        return this.mDataTypes;
    }

    public int indexOfDataType(String str) {
        ArrayList<String> arrayList = this.mDataTypes;
        if (arrayList != null) {
            return arrayList.indexOf(str);
        }
        return -1;
    }

    public String getUnit(int i) {
        ArrayList<String> arrayList = this.mUnits;
        if (arrayList == null || i < 0 || i >= arrayList.size()) {
            return null;
        }
        return this.mUnits.get(i);
    }

    public String getUnit(String str) {
        ArrayList<String> arrayList = this.mDataTypes;
        if (arrayList == null) {
            return null;
        }
        return getUnit(arrayList.indexOf(str));
    }
}
