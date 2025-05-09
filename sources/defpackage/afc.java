package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afc implements c<Float> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, Float.valueOf(gV_(parcel, i)));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gW_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, Float f, int i2, boolean z) {
        if (f == null) {
            return;
        }
        afo.hC_(parcel, i, 4);
        parcel.writeFloat(f.floatValue());
    }

    private float gV_(Parcel parcel, int i) {
        afn.a(parcel, i, 4);
        return parcel.readFloat();
    }
}
