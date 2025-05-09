package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class DataTypeCreateRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzr();
    private final String name;
    private final List<Field> zzbs;
    private final com.google.android.gms.internal.fitness.zzbn zzhh;

    DataTypeCreateRequest(String str, List<Field> list, IBinder iBinder) {
        this.name = str;
        this.zzbs = Collections.unmodifiableList(list);
        this.zzhh = com.google.android.gms.internal.fitness.zzbo.zze(iBinder);
    }

    public static class Builder {
        private String name;
        private List<Field> zzbs = new ArrayList();

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder addField(Field field) {
            if (!this.zzbs.contains(field)) {
                this.zzbs.add(field);
            }
            return this;
        }

        public Builder addField(String str, int i) {
            Preconditions.checkArgument((str == null || str.isEmpty()) ? false : true, "Invalid name specified");
            return addField(Field.zza(str, i));
        }

        public DataTypeCreateRequest build() {
            Preconditions.checkState(this.name != null, "Must set the name");
            Preconditions.checkState(!this.zzbs.isEmpty(), "Must specify the data fields");
            return new DataTypeCreateRequest(this);
        }
    }

    private DataTypeCreateRequest(Builder builder) {
        this(builder.name, (List<Field>) builder.zzbs, (com.google.android.gms.internal.fitness.zzbn) null);
    }

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, com.google.android.gms.internal.fitness.zzbn zzbnVar) {
        this(dataTypeCreateRequest.name, dataTypeCreateRequest.zzbs, zzbnVar);
    }

    private DataTypeCreateRequest(String str, List<Field> list, com.google.android.gms.internal.fitness.zzbn zzbnVar) {
        this.name = str;
        this.zzbs = Collections.unmodifiableList(list);
        this.zzhh = zzbnVar;
    }

    public String getName() {
        return this.name;
    }

    public List<Field> getFields() {
        return this.zzbs;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataTypeCreateRequest)) {
            return false;
        }
        DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
        return Objects.equal(this.name, dataTypeCreateRequest.name) && Objects.equal(this.zzbs, dataTypeCreateRequest.zzbs);
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzbs);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("fields", this.zzbs).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        com.google.android.gms.internal.fitness.zzbn zzbnVar = this.zzhh;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbnVar == null ? null : zzbnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
