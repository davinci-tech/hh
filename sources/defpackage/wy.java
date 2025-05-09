package defpackage;

import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class wy {
    static {
        wy.class.getClassLoader();
    }

    private wy() {
    }

    public static <T extends Parcelable> T ea_(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return creator.createFromParcel(parcel);
    }

    public static void ec_(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static void eb_(Parcel parcel, IInterface iInterface) {
        if (iInterface == null) {
            parcel.writeStrongBinder(null);
        } else {
            parcel.writeStrongBinder(iInterface.asBinder());
        }
    }
}
