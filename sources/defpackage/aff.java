package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class aff implements c<Long> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, Long.valueOf(hh_(parcel, i)));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: hi_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, Long l, int i2, boolean z) {
        if (l == null) {
            return;
        }
        afo.hC_(parcel, i, 8);
        parcel.writeLong(l.longValue());
    }

    private long hh_(Parcel parcel, int i) {
        afn.a(parcel, i, 8);
        return parcel.readLong();
    }
}
