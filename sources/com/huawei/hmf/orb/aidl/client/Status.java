package com.huawei.hmf.orb.aidl.client;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import java.util.Arrays;

/* loaded from: classes9.dex */
public final class Status {
    private PendingIntent mPendingIntent;
    private int statusCode;
    private String statusMessage;
    public static final Status SUCCESS = new Status(0);
    public static final Status MessageNotFound = new Status(404);
    public static final Status CoreException = new Status(500);

    public Status(int i) {
        this(i, null);
    }

    public Status(int i, String str) {
        this(i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this.statusCode = i;
        this.statusMessage = str;
        this.mPendingIntent = pendingIntent;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public void startResolutionForResult(Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public boolean isSuccess() {
        return this.statusCode <= 0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.statusCode), this.statusMessage, this.mPendingIntent});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.statusCode == status.statusCode && equal(this.statusMessage, status.statusMessage) && equal(this.mPendingIntent, status.mPendingIntent);
    }

    public String toString() {
        return getClass().getName() + " {\n\tstatusCode: " + this.statusCode + "\n\tstatusMessage: " + this.statusMessage + "\n\tmPendingIntent: " + this.mPendingIntent + "\n}";
    }

    public static boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }
}
