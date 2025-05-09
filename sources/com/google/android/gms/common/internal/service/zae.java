package com.google.android.gms.common.internal.service;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;

/* loaded from: classes7.dex */
final class zae extends zah {
    zae(zad zadVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zai zaiVar) throws RemoteException {
        ((zal) zaiVar.getService()).zaa(new zaf(this));
    }
}
