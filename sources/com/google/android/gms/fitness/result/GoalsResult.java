package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Goal;
import java.util.List;

/* loaded from: classes8.dex */
public class GoalsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<GoalsResult> CREATOR = new zzf();
    private final Status zzir;
    private final List<Goal> zziu;

    public GoalsResult(Status status, List<Goal> list) {
        this.zzir = status;
        this.zziu = list;
    }

    public List<Goal> getGoals() {
        return this.zziu;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getGoals(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
