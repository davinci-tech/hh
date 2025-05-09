package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzbq;
import com.google.android.gms.internal.fitness.zzbr;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class GoalsReadRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoalsReadRequest> CREATOR = new zzad();
    private final List<DataType> zzah;
    private final List<Integer> zzdl;
    private final zzbq zzhj;
    private final List<Integer> zzhk;

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<Integer> getObjectiveTypes() {
        if (this.zzhk.isEmpty()) {
            return null;
        }
        return this.zzhk;
    }

    public List<String> getActivityNames() {
        if (this.zzdl.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = this.zzdl.iterator();
        while (it.hasNext()) {
            arrayList.add(zzfa.getName(it.next().intValue()));
        }
        return arrayList;
    }

    public static class Builder {
        private final List<DataType> zzah = new ArrayList();
        private final List<Integer> zzhk = new ArrayList();
        private final List<Integer> zzdl = new ArrayList();

        public Builder addDataType(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder addActivity(String str) {
            int zzl = zzfa.zzl(str);
            Preconditions.checkState(zzl != 4, "Attempting to add an unknown activity");
            com.google.android.gms.internal.fitness.zzf.zza(Integer.valueOf(zzl), this.zzdl);
            return this;
        }

        public Builder addObjectiveType(int i) {
            boolean z = true;
            if (i != 1 && i != 2 && i != 3) {
                z = false;
            }
            Preconditions.checkState(z, "Attempting to add an invalid objective type");
            if (!this.zzhk.contains(Integer.valueOf(i))) {
                this.zzhk.add(Integer.valueOf(i));
            }
            return this;
        }

        public GoalsReadRequest build() {
            Preconditions.checkState(!this.zzah.isEmpty(), "At least one data type should be specified.");
            return new GoalsReadRequest(this);
        }
    }

    GoalsReadRequest(IBinder iBinder, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this.zzhj = iBinder == null ? null : zzbr.zzf(iBinder);
        this.zzah = list;
        this.zzhk = list2;
        this.zzdl = list3;
    }

    private GoalsReadRequest(Builder builder) {
        this((zzbq) null, (List<DataType>) builder.zzah, (List<Integer>) builder.zzhk, (List<Integer>) builder.zzdl);
    }

    public GoalsReadRequest(GoalsReadRequest goalsReadRequest, zzbq zzbqVar) {
        this(zzbqVar, goalsReadRequest.getDataTypes(), goalsReadRequest.zzhk, goalsReadRequest.zzdl);
    }

    private GoalsReadRequest(zzbq zzbqVar, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this(zzbqVar == null ? null : zzbqVar.asBinder(), list, list2, list3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GoalsReadRequest)) {
            return false;
        }
        GoalsReadRequest goalsReadRequest = (GoalsReadRequest) obj;
        return Objects.equal(this.zzah, goalsReadRequest.zzah) && Objects.equal(this.zzhk, goalsReadRequest.zzhk) && Objects.equal(this.zzdl, goalsReadRequest.zzdl);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzah, this.zzhk, getActivityNames());
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).add("objectiveTypes", this.zzhk).add("activities", getActivityNames()).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 1, this.zzhj.asBinder(), false);
        SafeParcelWriter.writeList(parcel, 2, getDataTypes(), false);
        SafeParcelWriter.writeList(parcel, 3, this.zzhk, false);
        SafeParcelWriter.writeList(parcel, 4, this.zzdl, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
