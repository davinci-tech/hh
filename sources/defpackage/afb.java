package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afb implements c<Double> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, Double.valueOf(gT_(parcel, i)));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gU_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, Double d, int i2, boolean z) {
        if (d == null) {
            return;
        }
        afo.hC_(parcel, i, 8);
        parcel.writeDouble(d.doubleValue());
    }

    private double gT_(Parcel parcel, int i) {
        afn.a(parcel, i, 8);
        return parcel.readDouble();
    }
}
