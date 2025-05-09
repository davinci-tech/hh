package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class SyncDataCompatible extends SyncData {
    public static final Parcelable.Creator<SyncDataCompatible> CREATOR = new c();

    static final class c implements Parcelable.Creator<SyncDataCompatible> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SyncDataCompatible[] newArray(int i) {
            return new SyncDataCompatible[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: fg_, reason: merged with bridge method [inline-methods] */
        public SyncDataCompatible createFromParcel(Parcel parcel) {
            return new SyncDataCompatible(parcel, null);
        }
    }

    /* synthetic */ SyncDataCompatible(Parcel parcel, c cVar) {
        this(parcel);
    }

    protected void readFromParcel(Parcel parcel) {
        this.version = parcel.readInt();
        this.luid = parcel.readString();
        this.guid = parcel.readString();
        this.unstruct_uuid = parcel.readString();
        this.etag = parcel.readString();
        this.data = parcel.readString();
        this.downFileList = new ArrayList();
        parcel.readTypedList(this.downFileList, UnstructData.CREATOR);
        this.deleteFileList = new ArrayList();
        parcel.readTypedList(this.deleteFileList, UnstructData.CREATOR);
        this.fileList = new ArrayList();
        parcel.readTypedList(this.fileList, UnstructData.CREATOR);
        this.recycleStatus = parcel.readInt();
        this.recycleTime = parcel.readLong();
        if (this.version >= 107) {
            this.extensionData = parcel.readString();
            this.extraParam = parcel.readString();
            this.newDownFileList = new ArrayList();
            parcel.readTypedList(this.newDownFileList, UnstructDataV107.CREATOR);
            this.newDeleteFileList = new ArrayList();
            parcel.readTypedList(this.newDeleteFileList, UnstructDataV107.CREATOR);
            this.newFileList = new ArrayList();
            parcel.readTypedList(this.newFileList, UnstructDataV107.CREATOR);
        }
    }

    @Override // com.huawei.android.hicloud.sync.service.aidl.SyncData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.version);
        parcel.writeString(this.luid);
        parcel.writeString(this.guid);
        parcel.writeString(this.unstruct_uuid);
        parcel.writeString(this.etag);
        parcel.writeString(this.data);
        parcel.writeTypedList(this.downFileList);
        parcel.writeTypedList(this.deleteFileList);
        parcel.writeTypedList(this.fileList);
        parcel.writeInt(this.recycleStatus);
        parcel.writeLong(this.recycleTime);
        if (this.version >= 107) {
            parcel.writeString(this.extensionData);
            parcel.writeString(this.extraParam);
            parcel.writeTypedList(this.newDownFileList);
            parcel.writeTypedList(this.newDeleteFileList);
            parcel.writeTypedList(this.newFileList);
        }
    }

    private SyncDataCompatible(Parcel parcel) {
        readFromParcel(parcel);
    }

    public SyncDataCompatible() {
    }

    public SyncDataCompatible(int i, String str, String str2, String str3, String str4, String str5, List<UnstructData> list, List<UnstructData> list2, List<UnstructData> list3, int i2, long j) {
        this.version = i;
        this.luid = str;
        this.guid = str2;
        this.unstruct_uuid = str3;
        this.etag = str4;
        this.data = str5;
        this.downFileList = list;
        this.deleteFileList = list2;
        this.fileList = list3;
        this.recycleStatus = i2;
        this.recycleTime = j;
    }

    public SyncDataCompatible(int i, String str, String str2, String str3, String str4, String str5, List<UnstructData> list, List<UnstructData> list2, List<UnstructData> list3, int i2, long j, String str6, String str7, List<UnstructDataV107> list4, List<UnstructDataV107> list5, List<UnstructDataV107> list6) {
        this.version = i;
        this.luid = str;
        this.guid = str2;
        this.unstruct_uuid = str3;
        this.etag = str4;
        this.data = str5;
        this.downFileList = list;
        this.deleteFileList = list2;
        this.fileList = list3;
        this.recycleStatus = i2;
        this.recycleTime = j;
        this.extensionData = str6;
        this.extraParam = str7;
        this.newDownFileList = list4;
        this.newDeleteFileList = list5;
        this.newFileList = list6;
    }
}
