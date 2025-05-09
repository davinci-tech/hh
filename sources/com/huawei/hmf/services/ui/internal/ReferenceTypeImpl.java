package com.huawei.hmf.services.ui.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hmf.services.ui.ref.ReferenceType;

/* loaded from: classes9.dex */
public class ReferenceTypeImpl<T> implements ReferenceType<T>, Parcelable {
    public static final Parcelable.Creator<ReferenceTypeImpl> CREATOR = new Parcelable.Creator<ReferenceTypeImpl>() { // from class: com.huawei.hmf.services.ui.internal.ReferenceTypeImpl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ReferenceTypeImpl createFromParcel(Parcel parcel) {
            return new ReferenceTypeImpl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ReferenceTypeImpl[] newArray(int i) {
            return new ReferenceTypeImpl[i];
        }
    };
    private Long id;
    private transient T value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ReferenceTypeImpl() {
    }

    protected ReferenceTypeImpl(Long l, T t) {
        this.id = l;
        this.value = t;
    }

    @Override // com.huawei.hmf.services.ui.ref.ReferenceType
    public T get() {
        if (this.value == null) {
            this.value = (T) ObjectPool.getInstance().get(this.id);
        }
        return this.value;
    }

    public static <T> ReferenceTypeImpl<T> create(T t) {
        return new ReferenceTypeImpl<>(ObjectPool.getInstance().add(t), t);
    }

    @Override // com.huawei.hmf.services.ui.ref.ReferenceType
    public void free() {
        ObjectPool.getInstance().remove(this.id);
    }

    protected ReferenceTypeImpl(Parcel parcel) {
        this.id = Long.valueOf(parcel.readLong());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id.longValue());
    }
}
