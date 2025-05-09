package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afl implements c<String[]> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, ho_(parcel, i));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: hp_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, String[] strArr, int i2, boolean z) {
        if (strArr == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeStringArray(strArr);
            afo.hB_(parcel, hD_);
        }
    }

    public static String[] ho_(Parcel parcel, int i) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        String[] strArr = new String[0];
        if (hu_ == 0) {
            return strArr;
        }
        try {
            strArr = parcel.createStringArray();
        } catch (Exception e) {
            afp.d.c("StringArrayTypeProcess", "error readStringArray:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return strArr;
    }
}
