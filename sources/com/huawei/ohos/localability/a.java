package com.huawei.ohos.localability;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import defpackage.lsf;
import defpackage.lsi;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public interface a extends IInterface {

    /* renamed from: com.huawei.ohos.localability.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0170a extends Binder implements a {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Log.i("IFormSupplyClient.Stub", "onTransact code:" + i);
            int i3 = 0;
            if (i == 1) {
                parcel.enforceInterface("com.huawei.ohos.localability.IFormSupplyClient");
                int readInt = parcel.readInt();
                ArrayList arrayList = new ArrayList(readInt);
                while (i3 < readInt) {
                    arrayList.add(Long.valueOf(parcel.readLong()));
                    i3++;
                }
                Log.i("AbilityFormSupplyProxy", "onFormVisible called");
                WeakReference<lsi> weakReference = ((lsi.b) this).c;
                if (weakReference == null) {
                    Log.e("AbilityFormSupplyProxy", "weakProxy is null");
                } else {
                    lsi lsiVar = weakReference.get();
                    if (lsiVar != null) {
                        lsiVar.b(arrayList);
                    }
                }
                parcel2.writeNoException();
                return true;
            }
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.huawei.ohos.localability.IFormSupplyClient");
                return true;
            }
            parcel.enforceInterface("com.huawei.ohos.localability.IFormSupplyClient");
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            ArrayList arrayList2 = new ArrayList(readInt3);
            for (int i4 = 0; i4 < readInt3; i4++) {
                arrayList2.add(parcel.readString());
            }
            ListenerType intToEnum = ListenerType.intToEnum(readInt2);
            int readInt4 = parcel.readInt();
            ArrayList arrayList3 = new ArrayList(readInt3);
            while (i3 < readInt4) {
                arrayList3.add(new lsf(parcel.readLong(), parcel.readString(), FormState.intToEnum(parcel.readInt()), parcel.readInt()));
                i3++;
            }
            Log.i("AbilityFormSupplyProxy", "onVisibilityChange called");
            WeakReference<lsi> weakReference2 = ((lsi.b) this).c;
            if (weakReference2 == null) {
                Log.e("AbilityFormSupplyProxy", "weakProxy is null");
            } else {
                lsi lsiVar2 = weakReference2.get();
                if (lsiVar2 != null) {
                    lsiVar2.c(intToEnum, (List<String>) arrayList2, (List<lsf>) arrayList3);
                }
            }
            parcel2.writeNoException();
            return true;
        }

        public AbstractBinderC0170a() {
            attachInterface(this, "com.huawei.ohos.localability.IFormSupplyClient");
        }
    }
}
