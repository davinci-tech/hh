package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class aet implements c<Boolean> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, Boolean.valueOf(a(parcel, i)));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gO_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, Boolean bool, int i2, boolean z) {
        if (bool == null) {
            return;
        }
        afo.hC_(parcel, i, 4);
        parcel.writeInt(bool.booleanValue() ? 1 : 0);
    }

    private boolean a(Parcel parcel, int i) {
        afn.a(parcel, i, 4);
        return parcel.readInt() != 0;
    }
}
