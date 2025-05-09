package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes8.dex */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzaf();
    private final DataSet zzeb;
    private final Session zzz;

    public zzae(Session session, DataSet dataSet) {
        this.zzz = session;
        this.zzeb = dataSet;
    }

    public final Session getSession() {
        return this.zzz;
    }

    public final DataSet getDataSet() {
        return this.zzeb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzae)) {
            return false;
        }
        zzae zzaeVar = (zzae) obj;
        return Objects.equal(this.zzz, zzaeVar.zzz) && Objects.equal(this.zzeb, zzaeVar.zzeb);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzz, this.zzeb);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.zzz).add("dataSet", this.zzeb).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzz, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzeb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
