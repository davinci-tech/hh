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
final class zzhi implements Runnable {
    private final /* synthetic */ Uri zzco;
    private final /* synthetic */ boolean zzcp;
    private final /* synthetic */ String zzcs;
    private final /* synthetic */ BaseImplementation.ResultHolder zzfh;
    private final /* synthetic */ zzhg zzfi;

    zzhi(zzhg zzhgVar, Uri uri, BaseImplementation.ResultHolder resultHolder, boolean z, String str) {
        this.zzfi = zzhgVar;
        this.zzco = uri;
        this.zzfh = resultHolder;
        this.zzcp = z;
        this.zzcs = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (Log.isLoggable("WearableClient", 2)) {
            Log.v("WearableClient", "Executing receiveFileFromChannelTask");
        }
        if (!"file".equals(this.zzco.getScheme())) {
            Log.w("WearableClient", "Channel.receiveFile used with non-file URI");
            this.zzfh.setFailedResult(new Status(10, "Channel.receiveFile used with non-file URI"));
            return;
        }
        File file = new File(this.zzco.getPath());
        try {
            ParcelFileDescriptor open = ParcelFileDescriptor.open(file, (this.zzcp ? 33554432 : 0) | 671088640);
            try {
                try {
                    ((zzep) this.zzfi.getService()).zza(new zzhf(this.zzfh), this.zzcs, open);
                } catch (RemoteException e) {
                    Log.w("WearableClient", "Channel.receiveFile failed.", e);
                    this.zzfh.setFailedResult(new Status(8));
                    try {
                        open.close();
                    } catch (IOException e2) {
                        Log.w("WearableClient", "Failed to close targetFd", e2);
                    }
                }
            } finally {
                try {
                    open.close();
                } catch (IOException e3) {
                    Log.w("WearableClient", "Failed to close targetFd", e3);
                }
            }
        } catch (FileNotFoundException unused) {
            String valueOf = String.valueOf(file);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 49);
            sb.append("File couldn't be opened for Channel.receiveFile: ");
            sb.append(valueOf);
            Log.w("WearableClient", sb.toString());
            this.zzfh.setFailedResult(new Status(13));
        }
    }
}
