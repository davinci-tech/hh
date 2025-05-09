package com.huawei.health.h5pro.core;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class H5ProCommand implements Parcelable {
    public static final int CMD_LOAD = 2;
    public static final Parcelable.Creator<H5ProCommand> CREATOR = new Parcelable.Creator<H5ProCommand>() { // from class: com.huawei.health.h5pro.core.H5ProCommand.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProCommand[] newArray(int i) {
            return new H5ProCommand[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public H5ProCommand createFromParcel(Parcel parcel) {
            H5ProCommand h5ProCommand = new H5ProCommand();
            h5ProCommand.command = parcel.readInt();
            h5ProCommand.packageName = parcel.readString();
            h5ProCommand.url = parcel.readString();
            h5ProCommand.launchOption = (H5ProLaunchOption) parcel.readParcelable(AnonymousClass1.class.getClassLoader());
            h5ProCommand.isEmbedded = parcel.readByte() != 0;
            return h5ProCommand;
        }
    };
    public int command;
    public boolean isEmbedded;
    public H5ProLaunchOption launchOption;
    public String packageName;
    public String url;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.command);
        parcel.writeString(this.packageName);
        parcel.writeString(this.url);
        parcel.writeParcelable(this.launchOption, i);
        parcel.writeByte(this.isEmbedded ? (byte) 1 : (byte) 0);
    }

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        public String f2368a;
        public String c;
        public H5ProLaunchOption e;
        public int d = 0;
        public boolean b = false;

        public Builder setupLoad() {
            this.d = 2;
            return this;
        }

        public Builder setUrl(String str) {
            this.f2368a = str;
            return this;
        }

        public Builder setPackageName(String str) {
            this.c = str;
            return this;
        }

        public Builder setLaunchOption(H5ProLaunchOption h5ProLaunchOption) {
            this.e = h5ProLaunchOption;
            return this;
        }

        public Builder setEmbedded() {
            this.b = true;
            return this;
        }

        public boolean isEmbedded() {
            return this.b;
        }

        public String getUrl() {
            return this.f2368a;
        }

        public String getPackageName() {
            return this.c;
        }

        public H5ProLaunchOption getLaunchOption() {
            return this.e;
        }

        public int getCommand() {
            return this.d;
        }

        public H5ProCommand build() {
            return new H5ProCommand(this);
        }
    }

    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    public String getUrl() {
        return this.url;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public H5ProLaunchOption getLaunchOption() {
        return this.launchOption;
    }

    public int getCommand() {
        return this.command;
    }

    public H5ProCommand(Builder builder) {
        this.command = builder.getCommand();
        this.packageName = builder.getPackageName();
        this.url = builder.getUrl();
        this.launchOption = builder.getLaunchOption();
        this.isEmbedded = builder.b;
    }

    public H5ProCommand() {
    }
}
