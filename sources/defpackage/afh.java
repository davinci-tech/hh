package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afh implements c<int[]> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, gY_(parcel, i));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gZ_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, int[] iArr, int i2, boolean z) {
        if (iArr == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeIntArray(iArr);
            afo.hB_(parcel, hD_);
        }
    }

    private int[] gY_(Parcel parcel, int i) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        int[] iArr = new int[0];
        if (hu_ == 0) {
            return iArr;
        }
        try {
            iArr = parcel.createIntArray();
        } catch (Exception e) {
            afp.d.c("IntegerArrayTypeProcess", "error readIntArray:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return iArr;
    }
}
