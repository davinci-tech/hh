package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class afa implements c<HashMap> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, a(parcel, i, autoParcelable.getClass().getClassLoader()));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gX_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, HashMap hashMap, int i2, boolean z) {
        if (hashMap == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeMap(hashMap);
            afo.hB_(parcel, hD_);
        }
    }

    private HashMap a(Parcel parcel, int i, ClassLoader classLoader) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        HashMap hashMap = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            hashMap = parcel.readHashMap(classLoader);
        } catch (Exception unused) {
            afp.d.c("HashMapTypeProcess", "can not read map");
        }
        parcel.setDataPosition(dataPosition + hu_);
        return hashMap;
    }
}
