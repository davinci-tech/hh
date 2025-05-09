package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* loaded from: classes8.dex */
final class zzhj implements Runnable {
    private final /* synthetic */ Uri zzco;
    private final /* synthetic */ long zzcq;
    private final /* synthetic */ long zzcr;
    private final /* synthetic */ String zzcs;
    private final /* synthetic */ BaseImplementation.ResultHolder zzfh;
    private final /* synthetic */ zzhg zzfi;

    zzhj(zzhg zzhgVar, Uri uri, BaseImplementation.ResultHolder resultHolder, String str, long j, long j2) {
        this.zzfi = zzhgVar;
        this.zzco = uri;
        this.zzfh = resultHolder;
        this.zzcs = str;
        this.zzcq = j;
        this.zzcr = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.v("WearableClient", "Executing sendFileToChannelTask");
        }
        if (!"file".equals(this.zzco.getScheme())) {
            Log.w("WearableClient", "Channel.sendFile used with non-file URI");
            this.zzfh.setFailedResult(new Status(10, "Channel.sendFile used with non-file URI"));
            return;
        }
        File file = new File(this.zzco.getPath());
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, 268435456);
            try {
                try {
                    ((zzep) this.zzfi.getService()).zza(new zzhc(this.zzfh), this.zzcs, open, this.zzcq, this.zzcr);
                } finally {
                    try {
                        open.close();
                    } catch (IOException e) {
                        Log.w("WearableClient", "Failed to close sourceFd", e);
                    }
                }
            } catch (RemoteException e2) {
                Log.w("WearableClient", "Channel.sendFile failed.", e2);
                this.zzfh.setFailedResult(new Status(8));
                try {
                    open.close();
                } catch (IOException e3) {
                    Log.w("WearableClient", "Failed to close sourceFd", e3);
                }
            }
        } catch (FileNotFoundException unused) {
            String valueOf = String.valueOf(file);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
            sb.append("File couldn't be opened for Channel.sendFile: ");
            sb.append(valueOf);
            Log.w("WearableClient", sb.toString());
            this.zzfh.setFailedResult(new Status(13));
        }
    }
}
