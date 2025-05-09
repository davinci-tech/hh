package com.huawei.hmf.orb.tbis.type;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.huawei.hmf.orb.Releasable;
import com.huawei.hmf.orb.tbis.TBNativeType;
import com.huawei.hmf.services.ui.ref.Allocator;
import com.huawei.hmf.services.ui.ref.ReferenceType;

/* loaded from: classes9.dex */
public class ObjectRef implements TBNativeType.Unboxable<Object>, Releasable {
    private static final long UNIQUE_ID = 1597138300712L;
    private long mReferenceId;
    private String mReferenceTag;
    private transient ReferenceType mValue;
    public static final TBNativeType.Factory UnBoxedFactory = TBNativeType.newUnBoxedFactory(ObjectRef.class);
    public static final TBNativeType.Factory BoxedFactory = new TBNativeType.Factory<ObjectRef, Context>() { // from class: com.huawei.hmf.orb.tbis.type.ObjectRef.1
        @Override // com.huawei.hmf.orb.tbis.TBNativeType.Factory
        public ObjectRef create(Context context) {
            return new ObjectRef(context);
        }
    };

    public ObjectRef() {
    }

    public ObjectRef(Object obj) {
        this.mValue = Allocator.DEFAULT.alloc(obj);
        Parcel obtain = Parcel.obtain();
        obtain.writeParcelable((Parcelable) this.mValue, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        this.mReferenceTag = Base64.encodeToString(marshall, 2);
    }

    @Override // com.huawei.hmf.orb.tbis.TBNativeType.Unboxable
    public Object unboxing() {
        if (this.mValue == null && this.mReferenceTag != null && this.mReferenceId == UNIQUE_ID) {
            Parcel obtain = Parcel.obtain();
            try {
                try {
                    byte[] decode = Base64.decode(this.mReferenceTag, 2);
                    obtain.unmarshall(decode, 0, decode.length);
                    obtain.setDataPosition(0);
                    this.mValue = (ReferenceType) obtain.readParcelable(ReferenceType.class.getClassLoader());
                } catch (Exception unused) {
                    throw new IllegalArgumentException("Invalid reference object");
                }
            } finally {
                obtain.recycle();
            }
        }
        ReferenceType referenceType = this.mValue;
        if (referenceType == null) {
            return null;
        }
        return referenceType.get();
    }

    public String boxed() {
        return String.format("{\"referenceId\":%s,\"referenceTag\":'%s'}", Long.valueOf(UNIQUE_ID), this.mReferenceTag);
    }

    public void setReferenceTag(String str) {
        this.mReferenceTag = str;
    }

    public void setReferenceId(long j) {
        this.mReferenceId = j;
    }

    @Override // com.huawei.hmf.orb.Releasable
    public void release() {
        this.mValue.free();
    }
}
