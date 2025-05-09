package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class SessionStopResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzi();
    private final List<Session> zzgn;
    private final Status zzir;

    public SessionStopResult(Status status, List<Session> list) {
        this.zzir = status;
        this.zzgn = Collections.unmodifiableList(list);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionStopResult)) {
            return false;
        }
        SessionStopResult sessionStopResult = (SessionStopResult) obj;
        return this.zzir.equals(sessionStopResult.zzir) && Objects.equal(this.zzgn, sessionStopResult.zzgn);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzgn);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("sessions", this.zzgn).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getSessions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
