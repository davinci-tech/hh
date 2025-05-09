package com.huawei.appmarket.framework.coreservice;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data.BaseIPCResponse;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;

/* loaded from: classes3.dex */
public final class Status<C extends BaseIPCResponse> extends AutoParcelable {
    public static final Parcelable.Creator<Status> CREATOR = new AutoParcelable.AutoCreator(Status.class);

    @EnableAutoParcel(2)
    private PendingIntent mResolution;

    @EnableAutoParcel(3)
    private C mResponse;

    @EnableAutoParcel(1)
    private int mStatusCode;

    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mResolution.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public void setStatusCode(int i) {
        this.mStatusCode = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setResponse(BaseIPCResponse baseIPCResponse) {
        this.mResponse = baseIPCResponse;
    }

    public boolean hasResolution() {
        return this.mResolution != null;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public C getResponse() {
        return this.mResponse;
    }

    public PendingIntent getResolution() {
        return this.mResolution;
    }

    public Status(int i) {
        this.mStatusCode = i;
    }

    public Status() {
    }
}
