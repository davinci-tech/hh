package com.huawei.hms.kit.awareness.internal.hmscore;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class AwarenessOutBean {
    private final int code;
    private final Parcelable intent;

    public final PendingIntent getPendingIntent() {
        Parcelable parcelable = this.intent;
        if (parcelable instanceof PendingIntent) {
            return (PendingIntent) parcelable;
        }
        return null;
    }

    public Parcelable getParcelable() {
        return this.intent;
    }

    public final Intent getIntent() {
        Parcelable parcelable = this.intent;
        if (parcelable instanceof Intent) {
            return (Intent) parcelable;
        }
        return null;
    }

    public int getCode() {
        return this.code;
    }

    public AwarenessOutBean(int i, Parcelable parcelable) {
        this.code = i;
        this.intent = parcelable;
    }
}
