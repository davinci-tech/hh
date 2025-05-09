package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afm implements c<Parcelable> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, hm_(parcel, i, field, map));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: hn_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, Parcelable parcelable, int i2, boolean z) {
        if (parcelable == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcelable.writeToParcel(parcel, i2);
            afo.hB_(parcel, hD_);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v6, types: [android.os.Parcelable] */
    private <T extends Parcelable> T hm_(Parcel parcel, int i, Field field, Map<String, String> map) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        T t = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            Parcelable.Creator hk_ = afi.hk_(field, map);
            if (hk_ != null) {
                t = (Parcelable) hk_.createFromParcel(parcel);
            }
        } catch (Exception e) {
            afp.d.e("ParcelableTypeProcess", "readParcelable failed: " + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return t;
    }
}
