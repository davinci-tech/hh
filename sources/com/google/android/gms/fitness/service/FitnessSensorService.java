package com.google.android.gms.fitness.service;

import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.fitness.zzbk;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzeu;
import com.google.android.gms.internal.fitness.zzew;
import com.google.android.gms.internal.fitness.zzez;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class FitnessSensorService extends Service {
    public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
    private zza zzix;

    public abstract List<DataSource> onFindDataSources(List<DataType> list);

    public abstract boolean onRegister(FitnessSensorServiceRequest fitnessSensorServiceRequest);

    public abstract boolean onUnregister(DataSource dataSource);

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.zzix = new zza();
    }

    static final class zza extends zzez {
        private final FitnessSensorService zziy;

        private zza(FitnessSensorService fitnessSensorService) {
            this.zziy = fitnessSensorService;
        }

        @Override // com.google.android.gms.internal.fitness.zzey
        public final void zza(zzeu zzeuVar, zzbk zzbkVar) throws RemoteException {
            this.zziy.zzab();
            zzbkVar.zza(new DataSourcesResult(this.zziy.onFindDataSources(zzeuVar.getDataTypes()), Status.RESULT_SUCCESS));
        }

        @Override // com.google.android.gms.internal.fitness.zzey
        public final void zza(FitnessSensorServiceRequest fitnessSensorServiceRequest, zzcq zzcqVar) throws RemoteException {
            this.zziy.zzab();
            if (this.zziy.onRegister(fitnessSensorServiceRequest)) {
                zzcqVar.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcqVar.onResult(new Status(13));
            }
        }

        @Override // com.google.android.gms.internal.fitness.zzey
        public final void zza(zzew zzewVar, zzcq zzcqVar) throws RemoteException {
            this.zziy.zzab();
            if (this.zziy.onUnregister(zzewVar.getDataSource())) {
                zzcqVar.onResult(Status.RESULT_SUCCESS);
            } else {
                zzcqVar.onResult(new Status(13));
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (!SERVICE_INTERFACE.equals(intent.getAction())) {
            return null;
        }
        if (Log.isLoggable("FitnessSensorService", 3)) {
            String valueOf = String.valueOf(intent);
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20 + String.valueOf(name).length());
            sb.append("Intent ");
            sb.append(valueOf);
            sb.append(" received by ");
            sb.append(name);
            Log.d("FitnessSensorService", sb.toString());
        }
        return this.zzix.asBinder();
    }

    protected final void zzab() throws SecurityException {
        int callingUid = Binder.getCallingUid();
        if (PlatformVersion.isAtLeastKitKat()) {
            ((AppOpsManager) getSystemService("appops")).checkPackage(callingUid, "com.google.android.gms");
            return;
        }
        String[] packagesForUid = getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            for (String str : packagesForUid) {
                if (str.equals("com.google.android.gms")) {
                    return;
                }
            }
        }
        throw new SecurityException("Unauthorized caller");
    }
}
