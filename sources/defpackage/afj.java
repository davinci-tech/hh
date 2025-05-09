package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afj implements c<String> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, hs_(parcel, i));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: ht_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, String str, int i2, boolean z) {
        if (str == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeString(str);
            afo.hB_(parcel, hD_);
        }
    }

    private String hs_(Parcel parcel, int i) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        if (hu_ == 0) {
            return null;
        }
        String readString = parcel.readString();
        parcel.setDataPosition(dataPosition + hu_);
        return readString;
    }
}
