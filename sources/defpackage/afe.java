package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import defpackage.afi;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class afe implements c<List> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        Class subClass = ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).subClass();
        field.set(autoParcelable, (subClass == null || !Parcelable.class.isAssignableFrom(subClass) || ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).useClassLoader()) ? a(parcel, i, afq.c(subClass)) : hd_(parcel, i, subClass));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: hg_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, List list, int i2, boolean z) {
        Class subClass = ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).subClass();
        if (subClass == null || !Parcelable.class.isAssignableFrom(subClass) || ((EnableAutoParcel) field.getAnnotation(EnableAutoParcel.class)).useClassLoader()) {
            hf_(parcel, i, list, z);
        } else {
            he_(parcel, i, list, i2, z);
        }
    }

    private void hf_(Parcel parcel, int i, List list, boolean z) {
        if (list == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeList(list);
            afo.hB_(parcel, hD_);
        }
    }

    private <T extends Parcelable> void he_(Parcel parcel, int i, List<T> list, int i2, boolean z) {
        if (list == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
                return;
            }
            return;
        }
        int hD_ = afo.hD_(parcel, i);
        parcel.writeInt(list.size());
        for (T t : list) {
            if (t == null) {
                parcel.writeInt(0);
            } else {
                afi.hl_(parcel, t, i2);
            }
        }
        afo.hB_(parcel, hD_);
    }

    private ArrayList a(Parcel parcel, int i, ClassLoader classLoader) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        ArrayList arrayList = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            arrayList = parcel.readArrayList(classLoader);
        } catch (Exception e) {
            afp.d.c("ListTypeProcess", "error readList:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return arrayList;
    }

    private <T extends Parcelable> ArrayList<T> hd_(Parcel parcel, int i, Class cls) {
        afp afpVar;
        StringBuilder sb;
        String message;
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        ArrayList<T> arrayList = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            Parcelable.Creator hj_ = afi.hj_(cls);
            if (hj_ != null) {
                arrayList = parcel.createTypedArrayList(hj_);
            }
        } catch (afi.e e) {
            afpVar = afp.d;
            sb = new StringBuilder("error getCreator:");
            message = e.getMessage();
            sb.append(message);
            afpVar.c("ListTypeProcess", sb.toString());
            parcel.setDataPosition(dataPosition + hu_);
            return arrayList;
        } catch (Exception e2) {
            afpVar = afp.d;
            sb = new StringBuilder("error readParcelableList:");
            message = e2.getMessage();
            sb.append(message);
            afpVar.c("ListTypeProcess", sb.toString());
            parcel.setDataPosition(dataPosition + hu_);
            return arrayList;
        }
        parcel.setDataPosition(dataPosition + hu_);
        return arrayList;
    }
}
