package defpackage;

import android.os.Parcel;
import androidx.core.internal.view.SupportMenu;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes2.dex */
public final class afn {
    static int e(int i) {
        return i & 65535;
    }

    static void hv_(Parcel parcel, int i) {
        int hu_ = hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        int i2 = dataPosition + hu_;
        if (i2 >= dataPosition && i2 <= parcel.dataSize()) {
            parcel.setDataPosition(i2);
            return;
        }
        throw new e("error length:" + dataPosition + Constants.LINK + hu_);
    }

    static void b(Parcel parcel, int i) {
        if ((i & SupportMenu.CATEGORY_MASK) == -65536) {
            parcel.setDataPosition(parcel.dataPosition() - 4);
        }
    }

    public static void a(Parcel parcel, int i, int i2) {
        int hu_ = hu_(parcel, i);
        if (hu_ == i2) {
            return;
        }
        throw new c("Expected size " + i2 + " got " + hu_ + " (0x" + Integer.toHexString(hu_) + com.huawei.operation.utils.Constants.RIGHT_BRACKET_ONLY, parcel);
    }

    public static int hu_(Parcel parcel, int i) {
        return (i & SupportMenu.CATEGORY_MASK) != -65536 ? (i >> 16) & 65535 : parcel.readInt();
    }

    static class c extends RuntimeException {
        c(String str, Parcel parcel) {
            super(str);
        }
    }

    static class e extends RuntimeException {
        e(String str) {
            super(str);
        }
    }

    static int a(Parcel parcel) {
        int readInt = parcel.readInt();
        int hu_ = hu_(parcel, readInt);
        int dataPosition = parcel.dataPosition();
        if (e(readInt) != 20293) {
            throw new c("Expected object header. Got 0x" + Integer.toHexString(readInt), parcel);
        }
        int i = hu_ + dataPosition;
        if (i >= dataPosition && i <= parcel.dataSize()) {
            return i;
        }
        throw new c("Size read is invalid start=" + dataPosition + " end=" + i, parcel);
    }
}
