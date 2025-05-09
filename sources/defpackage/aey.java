package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class aey implements c<Bundle> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        Class subClass = ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).subClass();
        if (subClass == null || !Parcelable.class.isAssignableFrom(subClass) || ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).useClassLoader()) {
            subClass = field.getDeclaringClass();
        }
        field.set(autoParcelable, gP_(parcel, i, afq.c(subClass)));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gQ_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, Bundle bundle, int i2, boolean z) {
        if (bundle == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeBundle(bundle);
            afo.hB_(parcel, hD_);
        }
    }

    private Bundle gP_(Parcel parcel, int i, ClassLoader classLoader) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        Bundle bundle = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            bundle = parcel.readBundle(classLoader);
        } catch (Exception e) {
            afp.d.c("BundleTypeProcess", "error readBundle:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return bundle;
    }
}
