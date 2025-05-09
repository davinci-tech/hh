package defpackage;

import android.os.Parcel;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.b.c;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes2.dex */
public class aez implements c<byte[]> {
    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    public void a(AutoParcelable autoParcelable, Field field, Parcel parcel, int i, Map<String, String> map) {
        field.set(autoParcelable, gR_(parcel, i));
    }

    @Override // com.huawei.appgallery.coreservice.internal.support.parcelable.b.c
    /* renamed from: gS_, reason: merged with bridge method [inline-methods] */
    public void a(Parcel parcel, Field field, int i, byte[] bArr, int i2, boolean z) {
        if (bArr == null) {
            if (z) {
                afo.hC_(parcel, i, 0);
            }
        } else {
            int hD_ = afo.hD_(parcel, i);
            parcel.writeByteArray(bArr);
            afo.hB_(parcel, hD_);
        }
    }

    private byte[] gR_(Parcel parcel, int i) {
        int hu_ = afn.hu_(parcel, i);
        int dataPosition = parcel.dataPosition();
        byte[] bArr = new byte[0];
        if (hu_ == 0) {
            return bArr;
        }
        try {
            bArr = parcel.createByteArray();
        } catch (Exception e) {
            afp.d.c("ByteArrayTypeProcess", "error readByteArray:" + e.getMessage());
        }
        parcel.setDataPosition(dataPosition + hu_);
        return bArr;
    }
}
