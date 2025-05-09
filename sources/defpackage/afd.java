package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class afd implements com.huawei.appgallery.coreservice.internal.support.parcelable.b.c<IInterface> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        afp afpVar;
        StringBuilder sb;
        String message;
        for (Class<?> cls : field.getType().getDeclaredClasses()) {
            try {
                field.set(autoParcelable, cls.getDeclaredMethod("asInterface", IBinder.class).invoke(null, aev.gN_(parcel, i)));
                return;
            } catch (NoSuchMethodException e) {
                afpVar = afp.d;
                sb = new StringBuilder("can not set the interface");
                message = e.getMessage();
                sb.append(message);
                afpVar.c("InterfaceTypeProcess", sb.toString());
            } catch (Exception e2) {
                afpVar = afp.d;
                sb = new StringBuilder("can not set the interface");
                message = e2.getMessage();
                sb.append(message);
                afpVar.c("InterfaceTypeProcess", sb.toString());
            }
        }
        throw new c("Field has broken interface: " + field);
    }

    public static class c extends RuntimeException {
        public c(String str) {
            super(str);
        }
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: hc_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, IInterface iInterface, int i2, boolean z) {
        if (iInterface == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeStrongBinder(iInterface.asBinder());
            afo.hB_(parcel, hD_);
        }
    }
}
