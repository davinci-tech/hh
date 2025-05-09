package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.Node;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public final class zzah extends AbstractSafeParcelable implements CapabilityInfo {
    public static final Parcelable.Creator<zzah> CREATOR = new zzai();
    private final String name;
    private final List<zzfo> zzca;
    private final Object lock = new Object();
    private Set<Node> zzbt = null;

    public zzah(String str, List<zzfo> list) {
        this.name = str;
        this.zzca = list;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(list);
    }

    @Override // com.google.android.gms.wearable.CapabilityInfo
    public final String getName() {
        return this.name;
    }

    @Override // com.google.android.gms.wearable.CapabilityInfo
    public final Set<Node> getNodes() {
        Set<Node> set;
        synchronized (this.lock) {
            if (this.zzbt == null) {
                this.zzbt = new HashSet(this.zzca);
            }
            set = this.zzbt;
        }
        return set;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzca, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        String str = this.name;
        String valueOf = String.valueOf(this.zzca);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 18 + String.valueOf(valueOf).length());
        sb.append("CapabilityInfo{");
        sb.append(str);
        sb.append(", ");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzah zzahVar = (zzah) obj;
        String str = this.name;
        if (str == null ? zzahVar.name != null : !str.equals(zzahVar.name)) {
            return false;
        }
        List<zzfo> list = this.zzca;
        List<zzfo> list2 = zzahVar.zzca;
        return list == null ? list2 == null : list.equals(list2);
    }

    public final int hashCode() {
        String str = this.name;
        int hashCode = str != null ? str.hashCode() : 0;
        List<zzfo> list = this.zzca;
        return ((hashCode + 31) * 31) + (list != null ? list.hashCode() : 0);
    }
}
