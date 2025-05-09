package com.huawei.hms.hihealth.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacs;
import com.huawei.hms.hihealth.data.SamplePoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class SampleSet implements Parcelable {
    public static final Parcelable.Creator<SampleSet> CREATOR = new aab();
    private DataCollector dataCollector;
    private List<DataCollector> involvedDataCollectors;
    private List<SamplePoint> samplePointsList;

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkLegality(SamplePoint samplePoint, DataCollector dataCollector) throws IllegalArgumentException {
        if (dataCollector.getDataType().isFromSelfDefined()) {
            return;
        }
        if (aabz.aab(dataCollector.getDataType()).equals(dataCollector)) {
            Preconditions.checkState(dataCollector.getDataType().equals(samplePoint.getDataType()), "dataCollector DataType mismatch!");
        } else {
            Preconditions.checkArgument(samplePoint != null && samplePoint.getDataCollector().equals(dataCollector), "samplePoint is null or dataCollector mismatch!");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.dataCollector, i);
        parcel.writeTypedList(this.samplePointsList);
    }

    public String toString() {
        StringBuilder aab2 = com.huawei.hms.health.aab.aab("SampleSet{");
        StringBuilder aab3 = com.huawei.hms.health.aab.aab("dataCollector=");
        aab3.append(this.dataCollector);
        aab2.append(aab3.toString());
        StringBuilder aab4 = com.huawei.hms.health.aab.aab(", samplePointsList=");
        aab4.append(this.samplePointsList);
        aab2.append(aab4.toString());
        StringBuilder aab5 = com.huawei.hms.health.aab.aab(", involvedDataCollectors=");
        aab5.append(this.involvedDataCollectors);
        aab5.append('}');
        aab2.append(aab5.toString());
        return aab2.toString();
    }

    public boolean isEmpty() {
        return this.samplePointsList.isEmpty();
    }

    public final int hashCode() {
        return Objects.hashCode(this.dataCollector);
    }

    public List<SamplePoint> getSamplePoints() {
        return this.samplePointsList;
    }

    public DataType getDataType() {
        return this.dataCollector.getDataType();
    }

    public DataCollector getDataCollector() {
        return this.dataCollector;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SampleSet)) {
            return false;
        }
        SampleSet sampleSet = (SampleSet) obj;
        return this.dataCollector.equals(sampleSet.dataCollector) && Objects.equal(this.samplePointsList, sampleSet.samplePointsList);
    }

    public SamplePoint createSamplePoint() {
        return new SamplePoint.Builder(this.dataCollector).build();
    }

    public void addSampleNoCheckLegality(SamplePoint samplePoint) {
        this.samplePointsList.add(samplePoint);
    }

    public final void addSampleListNoCheckLegality(List<SamplePoint> list) {
        if (list == null) {
            return;
        }
        Iterator<SamplePoint> it = list.iterator();
        while (it.hasNext()) {
            addSampleNoCheckLegality(it.next());
        }
    }

    public final void addSampleList(Iterable<SamplePoint> iterable) {
        if (iterable == null) {
            return;
        }
        Iterator<SamplePoint> it = iterable.iterator();
        while (it.hasNext()) {
            addSample(it.next());
        }
    }

    public void addSample(SamplePoint samplePoint) {
        checkLegality(samplePoint, this.dataCollector);
        this.samplePointsList.add(samplePoint);
    }

    public static SampleSet create(DataCollector dataCollector) {
        Preconditions.checkNotNull(dataCollector, "DataCollector cannot be null");
        return new Builder(dataCollector).build();
    }

    public static Builder builder(DataCollector dataCollector) {
        Preconditions.checkNotNull(dataCollector, "DataCollector cannot be null");
        return new Builder(dataCollector);
    }

    public static class Builder {
        DataCollector dataCollector;
        List<SamplePoint> samplePointsList = new ArrayList();

        public SampleSet build() {
            SampleSet sampleSet = new SampleSet((aab) null);
            sampleSet.samplePointsList = this.samplePointsList;
            sampleSet.dataCollector = this.dataCollector;
            return sampleSet;
        }

        public Builder addSampleList(Iterable<SamplePoint> iterable) {
            Iterator<SamplePoint> it = iterable.iterator();
            while (it.hasNext()) {
                addSample(it.next());
            }
            return this;
        }

        public Builder addSample(SamplePoint samplePoint) {
            Preconditions.checkArgument(samplePoint.getDataCollector().getDataStreamId().equals(this.dataCollector.getDataStreamId()), "DataCollector mismatch");
            SampleSet.checkLegality(samplePoint, this.dataCollector);
            this.samplePointsList.add(samplePoint);
            return this;
        }

        public Builder(DataCollector dataCollector) {
            this.dataCollector = (DataCollector) Preconditions.checkNotNull(dataCollector, "dataCollector is not allow null!");
        }
    }

    /* synthetic */ SampleSet(aab aabVar) {
        this();
    }

    protected SampleSet(Parcel parcel) {
        this.dataCollector = (DataCollector) parcel.readParcelable(DataCollector.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        this.samplePointsList = arrayList;
        aacs.aab(parcel, arrayList, SamplePoint.CREATOR);
    }

    class aab implements Parcelable.Creator<SampleSet> {
        @Override // android.os.Parcelable.Creator
        public SampleSet createFromParcel(Parcel parcel) {
            return new SampleSet(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public SampleSet[] newArray(int i) {
            return new SampleSet[i];
        }

        aab() {
        }
    }

    private SampleSet() {
    }
}
