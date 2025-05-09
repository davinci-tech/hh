package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.hihealth.data.DeviceInfo;

/* loaded from: classes8.dex */
public final class SyncType implements Parcelable {
    public static final int AUTO_SYNC_CONTACT = 96;
    public static final int AUTO_SYNC_CONTACT_NETCON = 112;
    public static final int AUTO_SYNC_WLAN = 128;
    private static final int CONTACT_CHANGE = 16;
    public static final int GROUP_CHANGE = 9;
    public static final int I_CONTACT_GROUP_ALL = 8;
    public static final int WLAN_CHANGE = 129;
    private int biReportType;
    private int syncType;
    private final int type;
    public static final SyncType CONTACT = new SyncType(16);
    public static final SyncType MAMUNAL_INTELLIGENCE_SYNC = new SyncType(16, 4, 1);
    public static final SyncType MAMUNAL_LOCALTOCLOUD_SYNC = new SyncType(16, 1, 2);
    public static final SyncType MAMUNAL_CLOUDTOLOCAL_SYNC = new SyncType(16, 2, 3);
    public static final SyncType MAMUNAL_OPENAUTO_SYNC = new SyncType(16, 3, 4);
    public static final SyncType PUSH_SYNC = new SyncType(16, 3, 5);
    public static final SyncType RESTORE_SYNC = new SyncType(16, 3, 14);
    public static final SyncType LOCALCONTACT_CHANGE_AUTOSYNC = new SyncType(16, 3, 6);
    public static final SyncType THIRD_LOGIN_SYNC_SLOW = new SyncType(16, 3, 11);
    public static final SyncType THIRD_LOGIN_SYNC_CLOUDTOLOCAL = new SyncType(16, 2, 7);
    public static final SyncType NETWORK_CHANGE_SYNC = new SyncType(16, 3, 9);
    public static final SyncType DELETE_CONTINUESYNC_SYNC = new SyncType(16, 5, 13);
    public static final Parcelable.Creator<SyncType> CREATOR = new d();

    static final class d implements Parcelable.Creator<SyncType> {
        d() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SyncType[] newArray(int i) {
            return new SyncType[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fh_, reason: merged with bridge method [inline-methods] */
        public SyncType createFromParcel(Parcel parcel) {
            return new SyncType(parcel);
        }
    }

    public SyncType(Parcel parcel) {
        this.syncType = 3;
        this.biReportType = 12;
        this.type = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getBiReportType() {
        return this.biReportType;
    }

    public int getSyncType() {
        return this.syncType;
    }

    public int getType() {
        return this.type;
    }

    public void setBiReportType(int i) {
        this.biReportType = i;
    }

    public String toString() {
        return this.type == 16 ? "Contact" : DeviceInfo.STR_TYPE_UNKNOWN;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
    }

    SyncType(int i, int i2, int i3) {
        this.type = i;
        this.syncType = i2;
        this.biReportType = i3;
    }

    SyncType(int i) {
        this.syncType = 3;
        this.biReportType = 12;
        this.type = i;
    }

    SyncType(int i, int i2) {
        this.biReportType = 12;
        this.type = i;
        this.syncType = i2;
    }
}
