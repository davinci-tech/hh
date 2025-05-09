package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Subscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class ListSubscriptionsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<ListSubscriptionsResult> CREATOR = new zzg();
    private final Status zzir;
    private final List<Subscription> zziv;

    public ListSubscriptionsResult(List<Subscription> list, Status status) {
        this.zziv = list;
        this.zzir = status;
    }

    public static ListSubscriptionsResult zzd(Status status) {
        return new ListSubscriptionsResult(Collections.emptyList(), status);
    }

    public List<Subscription> getSubscriptions() {
        return this.zziv;
    }

    public List<Subscription> getSubscriptions(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : this.zziv) {
            if (dataType.equals(subscription.zzq())) {
                arrayList.add(subscription);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ListSubscriptionsResult)) {
            return false;
        }
        ListSubscriptionsResult listSubscriptionsResult = (ListSubscriptionsResult) obj;
        return this.zzir.equals(listSubscriptionsResult.zzir) && Objects.equal(this.zziv, listSubscriptionsResult.zziv);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zziv);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("subscriptions", this.zziv).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSubscriptions(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
