package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class StartBleScanRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzbg();
    private final List<DataType> zzah;
    private final zzcq zzgj;
    private final zzae zzik;
    private final int zzil;
    private final BleScanCallback zzim;

    StartBleScanRequest(List<DataType> list, IBinder iBinder, int i, IBinder iBinder2) {
        zzae zzagVar;
        this.zzah = list;
        if (iBinder == null) {
            zzagVar = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            zzagVar = queryLocalInterface instanceof zzae ? (zzae) queryLocalInterface : new zzag(iBinder);
        }
        this.zzik = zzagVar;
        this.zzil = i;
        this.zzgj = zzcr.zzj(iBinder2);
        this.zzim = null;
    }

    public static class Builder {
        private DataType[] zzhf = new DataType[0];
        private int zzil = 10;
        private BleScanCallback zzin;

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhf = dataTypeArr;
            return this;
        }

        public Builder setBleScanCallback(BleScanCallback bleScanCallback) {
            this.zzin = bleScanCallback;
            return this;
        }

        public Builder setTimeoutSecs(int i) {
            Preconditions.checkArgument(i > 0, "Stop time must be greater than zero");
            Preconditions.checkArgument(i <= 60, "Stop time must be less than 1 minute");
            this.zzil = i;
            return this;
        }

        public StartBleScanRequest build() {
            Preconditions.checkState(this.zzin != null, "Must set BleScanCallback");
            return new StartBleScanRequest(ArrayUtils.toArrayList(this.zzhf), this.zzin, this.zzil);
        }
    }

    private StartBleScanRequest(List<DataType> list, BleScanCallback bleScanCallback, int i) {
        this.zzah = list;
        this.zzik = null;
        this.zzil = i;
        this.zzgj = null;
        this.zzim = bleScanCallback;
    }

    public StartBleScanRequest(List<DataType> list, zzae zzaeVar, int i, zzcq zzcqVar) {
        this.zzah = list;
        this.zzik = zzaeVar;
        this.zzil = i;
        this.zzgj = zzcqVar;
        this.zzim = null;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzah);
    }

    public int getTimeoutSecs() {
        return this.zzil;
    }

    public final BleScanCallback zzz() {
        return this.zzim;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.zzah).add("timeoutSecs", Integer.valueOf(this.zzil)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        zzae zzaeVar = this.zzik;
        SafeParcelWriter.writeIBinder(parcel, 2, zzaeVar == null ? null : zzaeVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 3, getTimeoutSecs());
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcqVar != null ? zzcqVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
