package com.hihonor.assistant.cardmgrsdk.model;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ClickPendingIntent implements Parcelable {
    public static final Parcelable.Creator<ClickPendingIntent> CREATOR = new Parcelable.Creator<ClickPendingIntent>() { // from class: com.hihonor.assistant.cardmgrsdk.model.ClickPendingIntent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClickPendingIntent[] newArray(int i) {
            return new ClickPendingIntent[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ClickPendingIntent createFromParcel(Parcel parcel) {
            return new ClickPendingIntent(parcel);
        }
    };
    public Intent intent;
    public int type;
    public int viewId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.viewId);
        parcel.writeInt(this.type);
        parcel.writeParcelable(this.intent, i);
    }

    public void setViewId(int i) {
        this.viewId = i;
    }

    /* loaded from: classes8.dex */
    public static class Builder {
        public ComponentName mComponentName;
        public final Bundle mExtras = new Bundle();
        public String mPackage;
        public String mPendingAction;
        public int mPendingIntentType;
        public int mResId;

        public Builder setPendingType(int i) {
            this.mPendingIntentType = i;
            return this;
        }

        public Builder setPendingAction(String str) {
            this.mPendingAction = str;
            return this;
        }

        public Builder setPackage(String str) {
            this.mPackage = str;
            return this;
        }

        public Builder setEventResId(int i) {
            this.mResId = i;
            return this;
        }

        public Builder setComponent(ComponentName componentName) {
            this.mComponentName = componentName;
            return this;
        }

        public Builder putParamString(String str, String str2) {
            this.mExtras.putString(str, str2);
            return this;
        }

        public Builder putParamInt(String str, int i) {
            this.mExtras.putInt(str, i);
            return this;
        }

        public Builder putParamBoolean(String str, boolean z) {
            this.mExtras.putBoolean(str, z);
            return this;
        }

        public ClickPendingIntent build() {
            Intent intent = new Intent(this.mPendingAction);
            intent.putExtras(this.mExtras);
            intent.setComponent(this.mComponentName);
            String str = this.mPackage;
            if (str != null) {
                intent.setPackage(str);
            }
            return new ClickPendingIntent(this.mResId, this.mPendingIntentType, intent);
        }
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getViewId() {
        return this.viewId;
    }

    public int getType() {
        return this.type;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public ClickPendingIntent(Parcel parcel) {
        this.viewId = parcel.readInt();
        this.type = parcel.readInt();
        this.intent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
    }

    public ClickPendingIntent(int i, int i2, Intent intent) {
        this.viewId = i;
        this.type = i2;
        this.intent = intent;
    }

    public ClickPendingIntent() {
    }
}
