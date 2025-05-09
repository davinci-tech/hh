package com.huawei.android.hicloud.sync.service.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.aal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class SyncData implements Parcelable {
    public static final Parcelable.Creator<SyncData> CREATOR = new a();
    protected String data;
    protected List<UnstructData> deleteFileList;
    protected List<UnstructData> downFileList;
    protected String etag;
    protected String extensionData;
    protected String extraParam;
    protected List<UnstructData> fileList;
    protected String guid;
    private String hash;
    protected String luid;
    protected List<UnstructDataV107> newDeleteFileList;
    protected List<UnstructDataV107> newDownFileList;
    protected List<UnstructDataV107> newFileList;
    protected int recycleStatus;
    protected long recycleTime;
    private int status;
    protected String unstruct_uuid;
    private String uuid;
    protected int version;

    static final class a implements Parcelable.Creator<SyncData> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public SyncData[] newArray(int i) {
            return new SyncData[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ff_, reason: merged with bridge method [inline-methods] */
        public SyncData createFromParcel(Parcel parcel) {
            return new SyncData(parcel, null);
        }
    }

    /* synthetic */ SyncData(Parcel parcel, a aVar) {
        this(parcel);
    }

    public static Parcelable.Creator<SyncData> getCreator() {
        return CREATOR;
    }

    private void readFromParcel(Parcel parcel) {
        this.luid = parcel.readString();
        this.guid = parcel.readString();
        this.unstruct_uuid = parcel.readString();
        this.etag = parcel.readString();
        this.data = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.downFileList = arrayList;
        parcel.readTypedList(arrayList, UnstructData.CREATOR);
        ArrayList arrayList2 = new ArrayList();
        this.deleteFileList = arrayList2;
        parcel.readTypedList(arrayList2, UnstructData.CREATOR);
        ArrayList arrayList3 = new ArrayList();
        this.fileList = arrayList3;
        parcel.readTypedList(arrayList3, UnstructData.CREATOR);
        if (aal.a() >= 104) {
            this.recycleStatus = parcel.readInt();
            this.recycleTime = parcel.readLong();
        }
        if (aal.a() >= 107) {
            this.extensionData = parcel.readString();
            this.extraParam = parcel.readString();
            ArrayList arrayList4 = new ArrayList();
            this.newDownFileList = arrayList4;
            parcel.readTypedList(arrayList4, UnstructDataV107.CREATOR);
            ArrayList arrayList5 = new ArrayList();
            this.newDeleteFileList = arrayList5;
            parcel.readTypedList(arrayList5, UnstructDataV107.CREATOR);
            ArrayList arrayList6 = new ArrayList();
            this.newFileList = arrayList6;
            parcel.readTypedList(arrayList6, UnstructDataV107.CREATOR);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getData() {
        return this.data;
    }

    public List<UnstructData> getDeleteFileList() {
        return this.deleteFileList;
    }

    public List<UnstructData> getDownFileList() {
        return this.downFileList;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getExtensionData() {
        return this.extensionData;
    }

    public String getExtraParam() {
        return this.extraParam;
    }

    public List<UnstructData> getFileList() {
        return this.fileList;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getHash() {
        return this.hash;
    }

    public String getLuid() {
        return this.luid;
    }

    public List<UnstructDataV107> getNewDeleteFileList() {
        return this.newDeleteFileList;
    }

    public List<UnstructDataV107> getNewDownFileList() {
        return this.newDownFileList;
    }

    public List<UnstructDataV107> getNewFileList() {
        return this.newFileList;
    }

    public int getRecycleStatus() {
        return this.recycleStatus;
    }

    public long getRecycleTime() {
        return this.recycleTime;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUnstruct_uuid() {
        return this.unstruct_uuid;
    }

    public String getUuid() {
        return this.uuid;
    }

    public int getVersion() {
        return this.version;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setDeleteFileList(List<UnstructData> list) {
        this.deleteFileList = list;
    }

    public void setDownFileList(List<UnstructData> list) {
        this.downFileList = list;
    }

    public void setEtag(String str) {
        this.etag = str;
    }

    public void setExtensionData(String str) {
        this.extensionData = str;
    }

    public void setExtraParam(String str) {
        this.extraParam = str;
    }

    public void setFileList(List<UnstructData> list) {
        this.fileList = list;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setLuid(String str) {
        this.luid = str;
    }

    public void setNewDeleteFileList(List<UnstructDataV107> list) {
        this.newDeleteFileList = list;
    }

    public void setNewDownFileList(List<UnstructDataV107> list) {
        this.newDownFileList = list;
    }

    public void setNewFileList(List<UnstructDataV107> list) {
        this.newFileList = list;
    }

    public void setRecycleStatus(int i) {
        this.recycleStatus = i;
    }

    public void setRecycleTime(long j) {
        this.recycleTime = j;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setUnstruct_uuid(String str) {
        this.unstruct_uuid = str;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String toString() {
        return "[luid=" + this.luid + ", guid=" + this.guid + ", etag=" + this.etag + ", unstruct uuid=" + this.unstruct_uuid + ", recycleStatus=" + this.recycleStatus + ", extraParam=" + this.extraParam + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.luid);
        parcel.writeString(this.guid);
        parcel.writeString(this.unstruct_uuid);
        parcel.writeString(this.etag);
        parcel.writeString(this.data);
        parcel.writeTypedList(this.downFileList);
        parcel.writeTypedList(this.deleteFileList);
        parcel.writeTypedList(this.fileList);
    }

    private SyncData(Parcel parcel) {
        this.status = -1;
        this.uuid = null;
        this.hash = null;
        this.recycleStatus = 2;
        this.version = -1;
        readFromParcel(parcel);
    }

    public SyncData() {
        this.status = -1;
        this.uuid = null;
        this.hash = null;
        this.recycleStatus = 2;
        this.version = -1;
    }
}
