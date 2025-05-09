package com.huawei.appgallery.agd.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import com.huawei.appgallery.coreservice.api.IConnectionResult;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ConnectionResult implements IConnectionResult {
    private IConnectionResult d;

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        this.d.startResolutionForResult(activity, i);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.d.getStatusCode()), this.d.getResolution(), this.d.getErrorMessage()});
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public boolean hasResolution() {
        return this.d.hasResolution();
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public int getStatusCode() {
        return this.d.getStatusCode();
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public PendingIntent getResolution() {
        return this.d.getResolution();
    }

    @Override // com.huawei.appgallery.coreservice.api.IConnectionResult
    public String getErrorMessage() {
        return this.d.getErrorMessage();
    }

    public boolean equals(Object obj) {
        return this.d.equals(obj);
    }

    public ConnectionResult(IConnectionResult iConnectionResult) {
        this.d = iConnectionResult;
    }
}
