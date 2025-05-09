package com.huawei.hms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.Preconditions;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import java.util.Locale;

/* loaded from: classes4.dex */
public class Cap implements Parcelable {
    public static final Parcelable.Creator<Cap> CREATOR = new Parcelable.Creator<Cap>() { // from class: com.huawei.hms.maps.model.Cap.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Cap[] newArray(int i) {
            return new Cap[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Cap createFromParcel(Parcel parcel) {
            return Cap.a(parcel);
        }
    };
    public static final int TYPE_BUTT_CAP = 0;
    public static final int TYPE_CUSTOM_CAP = 3;
    public static final int TYPE_ROUND_CAP = 2;
    public static final int TYPE_SQUARE_CAP = 1;

    /* renamed from: a, reason: collision with root package name */
    private int f4989a;
    private BitmapDescriptor b;
    private float c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeInt(2, this.f4989a);
        BitmapDescriptor bitmapDescriptor = this.b;
        parcelWrite.writeIBinder(3, bitmapDescriptor != null ? bitmapDescriptor.getObject().asBinder() : null, true);
        parcelWrite.writeFloat(4, this.c);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public String toString() {
        return "Cap: type=" + this.f4989a;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f4989a), this.b, Float.valueOf(this.c));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return super.equals(obj);
        }
        if (!(obj instanceof Cap)) {
            return false;
        }
        Cap cap = (Cap) obj;
        return this.f4989a == cap.f4989a && Objects.equal(this.b, cap.b) && Objects.equal(Float.valueOf(this.c), Float.valueOf(cap.c));
    }

    protected static Cap a(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        int readInt = parcelReader.readInt(2, 3);
        if (readInt == 0) {
            return new ButtCap();
        }
        if (readInt == 1) {
            return new SquareCap();
        }
        if (readInt == 2) {
            return new RoundCap();
        }
        if (readInt == 3) {
            IBinder readIBinder = parcelReader.readIBinder(3, null);
            float readFloat = parcelReader.readFloat(4, 0.0f);
            if (readIBinder != null) {
                BitmapDescriptor bitmapDescriptor = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(readIBinder));
                return readFloat != 0.0f ? new CustomCap(bitmapDescriptor, readFloat) : new CustomCap(bitmapDescriptor);
            }
        }
        return null;
    }

    protected Cap(BitmapDescriptor bitmapDescriptor, float f) {
        this(3, bitmapDescriptor, Float.valueOf(f));
    }

    private Cap(int i, BitmapDescriptor bitmapDescriptor, Float f) {
        boolean z = true;
        boolean z2 = f != null && f.floatValue() > 0.0f;
        if (i == 3 && (bitmapDescriptor == null || !z2)) {
            z = false;
        }
        Preconditions.checkArgument(z, String.format(Locale.ENGLISH, "Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", Integer.valueOf(i), bitmapDescriptor, f));
        this.f4989a = i;
        this.b = bitmapDescriptor;
        if (f != null) {
            this.c = f.floatValue();
        }
    }

    Cap(int i, IBinder iBinder, Float f) {
        this(i, iBinder == null ? null : new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder)), f);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    protected Cap(int i) {
        this(i, (IBinder) null, Float.valueOf(0.0f));
    }
}
