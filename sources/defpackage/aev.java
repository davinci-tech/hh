package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class aev implements c<IBinder> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, gN_(parcel, i));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(Parcel parcel, Field field, int i, IBinder iBinder, int i2, boolean z) {
        if (iBinder == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeStrongBinder(iBinder);
            afo.hB_(parcel, hD_);
        }
    }

    public static IBinder gN_(Parcel parcel, int i) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        IBinder iBinder = null;
        if (hu_ == 0) {
            return null;
        }
        try {
            iBinder = parcel.readStrongBinder();
        } catch (Exception e) {
            afp.d.c("BinderTypeProcess", "error readBinder:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return iBinder;
    }
}
