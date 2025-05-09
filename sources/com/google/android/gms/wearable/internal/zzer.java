package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzer<T> {
    private final Map<T, zzhk<T>> zzeb = new HashMap();

    zzer() {
    }

    public final void zza(IBinder iBinder) {
        zzep zzeqVar;
        synchronized (this.zzeb) {
            if (iBinder == null) {
                zzeqVar = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableService");
                if (queryLocalInterface instanceof zzep) {
                    zzeqVar = (zzep) queryLocalInterface;
                } else {
                    zzeqVar = new zzeq(iBinder);
                }
            }
            zzgz zzgzVar = new zzgz();
            for (Map.Entry<T, zzhk<T>> entry : this.zzeb.entrySet()) {
                zzhk<T> value = entry.getValue();
                try {
                    zzeqVar.zza(zzgzVar, new zzd(value));
                    if (Log.isLoggable("WearableClient", 3)) {
                        String valueOf = String.valueOf(entry.getKey());
                        String valueOf2 = String.valueOf(value);
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27 + String.valueOf(valueOf2).length());
                        sb.append("onPostInitHandler: added: ");
                        sb.append(valueOf);
                        sb.append("/");
                        sb.append(valueOf2);
                        Log.d("WearableClient", sb.toString());
                    }
                } catch (RemoteException unused) {
                    String valueOf3 = String.valueOf(entry.getKey());
                    String valueOf4 = String.valueOf(value);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 32 + String.valueOf(valueOf4).length());
                    sb2.append("onPostInitHandler: Didn't add: ");
                    sb2.append(valueOf3);
                    sb2.append("/");
                    sb2.append(valueOf4);
                    Log.w("WearableClient", sb2.toString());
                }
            }
        }
    }

    public final void zza(zzhg zzhgVar, BaseImplementation.ResultHolder<Status> resultHolder, T t, zzhk<T> zzhkVar) throws RemoteException {
        synchronized (this.zzeb) {
            if (this.zzeb.get(t) != null) {
                if (Log.isLoggable("WearableClient", 2)) {
                    String valueOf = String.valueOf(t);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
                    sb.append("duplicate listener: ");
                    sb.append(valueOf);
                    Log.v("WearableClient", sb.toString());
                }
                resultHolder.setResult(new Status(4001));
                return;
            }
            if (Log.isLoggable("WearableClient", 2)) {
                String valueOf2 = String.valueOf(t);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 14);
                sb2.append("new listener: ");
                sb2.append(valueOf2);
                Log.v("WearableClient", sb2.toString());
            }
            this.zzeb.put(t, zzhkVar);
            try {
                ((zzep) zzhgVar.getService()).zza(new zzes(this.zzeb, t, resultHolder), new zzd(zzhkVar));
            } catch (RemoteException e) {
                if (Log.isLoggable("WearableClient", 3)) {
                    String valueOf3 = String.valueOf(t);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 39);
                    sb3.append("addListener failed, removing listener: ");
                    sb3.append(valueOf3);
                    Log.d("WearableClient", sb3.toString());
                }
                this.zzeb.remove(t);
                throw e;
            }
        }
    }

    public final void zza(zzhg zzhgVar, BaseImplementation.ResultHolder<Status> resultHolder, T t) throws RemoteException {
        synchronized (this.zzeb) {
            zzhk<T> remove = this.zzeb.remove(t);
            if (remove == null) {
                if (Log.isLoggable("WearableClient", 2)) {
                    String valueOf = String.valueOf(t);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
                    sb.append("remove Listener unknown: ");
                    sb.append(valueOf);
                    Log.v("WearableClient", sb.toString());
                }
                resultHolder.setResult(new Status(4002));
                return;
            }
            remove.clear();
            if (Log.isLoggable("WearableClient", 2)) {
                String valueOf2 = String.valueOf(t);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 24);
                sb2.append("service.removeListener: ");
                sb2.append(valueOf2);
                Log.v("WearableClient", sb2.toString());
            }
            ((zzep) zzhgVar.getService()).zza(new zzet(this.zzeb, t, resultHolder), new zzfw(remove));
        }
    }
}
