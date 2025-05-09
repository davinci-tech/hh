package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public final class zzas {
    private final zzbj<zzao> zzcb;
    private final Context zzcu;
    private ContentProviderClient zzcv = null;
    private boolean zzcw = false;
    private final Map<ListenerHolder.ListenerKey<LocationListener>, zzax> zzcx = new HashMap();
    private final Map<ListenerHolder.ListenerKey<Object>, zzaw> zzcy = new HashMap();
    private final Map<ListenerHolder.ListenerKey<LocationCallback>, zzat> zzcz = new HashMap();

    public final void zzb(ListenerHolder.ListenerKey<LocationCallback> listenerKey, zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzcz) {
            zzat remove = this.zzcz.remove(listenerKey);
            if (remove != null) {
                remove.release();
                this.zzcb.getService().zza(zzbf.zza(remove, zzajVar));
            }
        }
    }

    public final void zzb() throws RemoteException {
        if (this.zzcw) {
            zza(false);
        }
    }

    public final void zza(boolean z) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(z);
        this.zzcw = z;
    }

    public final void zza(LocationRequest locationRequest, ListenerHolder<LocationListener> listenerHolder, zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(1, zzbd.zza(locationRequest), zza(listenerHolder).asBinder(), null, null, zzajVar != null ? zzajVar.asBinder() : null));
    }

    public final void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(1, zzbd.zza(locationRequest), null, pendingIntent, null, zzajVar != null ? zzajVar.asBinder() : null));
    }

    public final void zza(zzbd zzbdVar, ListenerHolder<LocationCallback> listenerHolder, zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(1, zzbdVar, null, null, zzb(listenerHolder).asBinder(), zzajVar != null ? zzajVar.asBinder() : null));
    }

    public final void zza(zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(zzajVar);
    }

    public final void zza(ListenerHolder.ListenerKey<LocationListener> listenerKey, zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzcx) {
            zzax remove = this.zzcx.remove(listenerKey);
            if (remove != null) {
                remove.release();
                this.zzcb.getService().zza(zzbf.zza(remove, zzajVar));
            }
        }
    }

    public final void zza(Location location) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(location);
    }

    public final void zza(PendingIntent pendingIntent, zzaj zzajVar) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(new zzbf(2, null, null, pendingIntent, null, zzajVar != null ? zzajVar.asBinder() : null));
    }

    public final LocationAvailability zza() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zzb(this.zzcu.getPackageName());
    }

    public final void removeAllListeners() throws RemoteException {
        synchronized (this.zzcx) {
            for (zzax zzaxVar : this.zzcx.values()) {
                if (zzaxVar != null) {
                    this.zzcb.getService().zza(zzbf.zza(zzaxVar, (zzaj) null));
                }
            }
            this.zzcx.clear();
        }
        synchronized (this.zzcz) {
            for (zzat zzatVar : this.zzcz.values()) {
                if (zzatVar != null) {
                    this.zzcb.getService().zza(zzbf.zza(zzatVar, (zzaj) null));
                }
            }
            this.zzcz.clear();
        }
        synchronized (this.zzcy) {
            for (zzaw zzawVar : this.zzcy.values()) {
                if (zzawVar != null) {
                    this.zzcb.getService().zza(new zzo(2, null, zzawVar.asBinder(), null));
                }
            }
            this.zzcy.clear();
        }
    }

    public final Location getLastLocation() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zza(this.zzcu.getPackageName());
    }

    private final zzat zzb(ListenerHolder<LocationCallback> listenerHolder) {
        zzat zzatVar;
        synchronized (this.zzcz) {
            zzatVar = this.zzcz.get(listenerHolder.getListenerKey());
            if (zzatVar == null) {
                zzatVar = new zzat(listenerHolder);
            }
            this.zzcz.put(listenerHolder.getListenerKey(), zzatVar);
        }
        return zzatVar;
    }

    private final zzax zza(ListenerHolder<LocationListener> listenerHolder) {
        zzax zzaxVar;
        synchronized (this.zzcx) {
            zzaxVar = this.zzcx.get(listenerHolder.getListenerKey());
            if (zzaxVar == null) {
                zzaxVar = new zzax(listenerHolder);
            }
            this.zzcx.put(listenerHolder.getListenerKey(), zzaxVar);
        }
        return zzaxVar;
    }

    public zzas(Context context, zzbj<zzao> zzbjVar) {
        this.zzcu = context;
        this.zzcb = zzbjVar;
    }
}
