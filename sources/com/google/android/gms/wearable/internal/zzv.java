package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;

/* loaded from: classes8.dex */
final class zzv implements CapabilityApi.CapabilityListener {
    private final String zzbc;
    private final CapabilityApi.CapabilityListener zzbs;

    zzv(CapabilityApi.CapabilityListener capabilityListener, String str) {
        this.zzbs = capabilityListener;
        this.zzbc = str;
    }

    @Override // com.google.android.gms.wearable.CapabilityApi.CapabilityListener
    public final void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        this.zzbs.onCapabilityChanged(capabilityInfo);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzv zzvVar = (zzv) obj;
        if (this.zzbs.equals(zzvVar.zzbs)) {
            return this.zzbc.equals(zzvVar.zzbc);
        }
        return false;
    }

    public final int hashCode() {
        return (this.zzbs.hashCode() * 31) + this.zzbc.hashCode();
    }
}
