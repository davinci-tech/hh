package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class afk implements c<List<String>> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, hq_(parcel, i));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: hr_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, List<String> list, int i2, boolean z) {
        if (list == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeStringList(list);
            afo.hB_(parcel, hD_);
        }
    }

    private ArrayList<String> hq_(Parcel parcel, int i) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        ArrayList<String> arrayList = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            arrayList = parcel.createStringArrayList();
        } catch (Exception e) {
            afp.d.c("StringListProcess", "error readStringList:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return arrayList;
    }
}
