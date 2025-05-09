package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.BeaconStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class d implements Parcelable, BeaconStatus {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: com.huawei.hms.kit.awareness.b.d.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return new d[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return new d(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private List<b> f4834a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f4834a);
    }

    @Override // com.huawei.hms.kit.awareness.status.BeaconStatus
    public List<BeaconStatus.BeaconData> getBeaconData() {
        return Collections.unmodifiableList(this.f4834a);
    }

    public d(List<b> list) {
        new ArrayList();
        this.f4834a = list;
    }

    private d(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.f4834a = arrayList;
        parcel.readList(arrayList, b.class.getClassLoader());
    }

    public d() {
        this.f4834a = new ArrayList();
    }
}
