package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
final class zzhh implements Callable<Boolean> {
    private final /* synthetic */ byte[] zzee;
    private final /* synthetic */ ParcelFileDescriptor zzfg;

    zzhh(zzhg zzhgVar, ParcelFileDescriptor parcelFileDescriptor, byte[] bArr) {
        this.zzfg = parcelFileDescriptor;
        this.zzee = bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.concurrent.Callable
    /* renamed from: zzd, reason: merged with bridge method [inline-methods] */
    public final Boolean call() {
        if (Log.isLoggable("WearableClient", 3)) {
            String valueOf = String.valueOf(this.zzfg);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
            sb.append("processAssets: writing data to FD : ");
            sb.append(valueOf);
            Log.d("WearableClient", sb.toString());
        }
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(this.zzfg);
        try {
            try {
                autoCloseOutputStream.write(this.zzee);
                autoCloseOutputStream.flush();
                if (Log.isLoggable("WearableClient", 3)) {
                    String valueOf2 = String.valueOf(this.zzfg);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 27);
                    sb2.append("processAssets: wrote data: ");
                    sb2.append(valueOf2);
                    Log.d("WearableClient", sb2.toString());
                }
                return true;
            } finally {
                try {
                    if (Log.isLoggable("WearableClient", 3)) {
                        String valueOf3 = String.valueOf(this.zzfg);
                        StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf3).length() + 24);
                        sb3.append("processAssets: closing: ");
                        sb3.append(valueOf3);
                        Log.d("WearableClient", sb3.toString());
                    }
                    autoCloseOutputStream.close();
                } catch (IOException unused) {
                }
            }
        } catch (IOException unused2) {
            String valueOf4 = String.valueOf(this.zzfg);
            StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf4).length() + 36);
            sb4.append("processAssets: writing data failed: ");
            sb4.append(valueOf4);
            Log.w("WearableClient", sb4.toString());
            try {
                if (Log.isLoggable("WearableClient", 3)) {
                    String valueOf5 = String.valueOf(this.zzfg);
                    StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf5).length() + 24);
                    sb5.append("processAssets: closing: ");
                    sb5.append(valueOf5);
                    Log.d("WearableClient", sb5.toString());
                }
                autoCloseOutputStream.close();
            } catch (IOException unused3) {
            }
            return false;
        }
    }
}
