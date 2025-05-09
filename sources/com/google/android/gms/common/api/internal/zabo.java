package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
final class zabo implements Runnable {
    private final /* synthetic */ ConnectionResult zaiz;
    private final /* synthetic */ GoogleApiManager.zac zajg;

    zabo(GoogleApiManager.zac zacVar, ConnectionResult connectionResult) {
        this.zajg = zacVar;
        this.zaiz = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zai zaiVar;
        Api.Client client;
        zai zaiVar2;
        Api.Client client2;
        if (!this.zaiz.isSuccess()) {
            Map map = GoogleApiManager.this.zaii;
            zaiVar = this.zajg.zafq;
            ((GoogleApiManager.zaa) map.get(zaiVar)).onConnectionFailed(this.zaiz);
            return;
        }
        GoogleApiManager.zac.zaa(this.zajg, true);
        client = this.zajg.zaio;
        if (client.requiresSignIn()) {
            this.zajg.zabr();
            return;
        }
        try {
            client2 = this.zajg.zaio;
            client2.getRemoteService(null, Collections.emptySet());
        } catch (SecurityException e) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
            Map map2 = GoogleApiManager.this.zaii;
            zaiVar2 = this.zajg.zafq;
            ((GoogleApiManager.zaa) map2.get(zaiVar2)).onConnectionFailed(new ConnectionResult(10));
        }
    }
}
