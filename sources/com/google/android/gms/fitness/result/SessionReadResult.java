package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class SessionReadResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzh();
    private final List<Session> zzgn;
    private final Status zzir;
    private final List<zzae> zziw;

    public SessionReadResult(List<Session> list, List<zzae> list2, Status status) {
        this.zzgn = list;
        this.zziw = Collections.unmodifiableList(list2);
        this.zzir = status;
    }

    public static SessionReadResult zze(Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    public List<DataSet> getDataSet(Session session, DataType dataType) {
        Preconditions.checkArgument(this.zzgn.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzaeVar : this.zziw) {
            if (Objects.equal(session, zzaeVar.getSession()) && dataType.equals(zzaeVar.getDataSet().getDataType())) {
                arrayList.add(zzaeVar.getDataSet());
            }
        }
        return arrayList;
    }

    public List<DataSet> getDataSet(Session session) {
        Preconditions.checkArgument(this.zzgn.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzaeVar : this.zziw) {
            if (Objects.equal(session, zzaeVar.getSession())) {
                arrayList.add(zzaeVar.getDataSet());
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionReadResult)) {
            return false;
        }
        SessionReadResult sessionReadResult = (SessionReadResult) obj;
        return this.zzir.equals(sessionReadResult.zzir) && Objects.equal(this.zzgn, sessionReadResult.zzgn) && Objects.equal(this.zziw, sessionReadResult.zziw);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzgn, this.zziw);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("sessions", this.zzgn).add("sessionDataSets", this.zziw).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSessions(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zziw, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
