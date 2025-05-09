package com.huawei.wearkit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.List;

/* loaded from: classes8.dex */
public class HealthLogOption implements Parcelable {
    public static final Parcelable.Creator<HealthLogOption> CREATOR = new Parcelable.Creator<HealthLogOption>() { // from class: com.huawei.wearkit.model.HealthLogOption.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: ffu_, reason: merged with bridge method [inline-methods] */
        public HealthLogOption createFromParcel(Parcel parcel) {
            return new HealthLogOption(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HealthLogOption[] newArray(int i) {
            return new HealthLogOption[i];
        }
    };
    private static final String TAG = "HealthLogOption";
    private ActionType actionType;
    private List<String> fileNameList;
    private int uploadType;
    private String uuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            Log.w(TAG, "HealthLogOption writeToParcel dest == null.");
            return;
        }
        parcel.writeSerializable(this.actionType);
        parcel.writeString(this.uuid);
        parcel.writeInt(this.uploadType);
        parcel.writeStringList(this.fileNameList);
    }

    protected HealthLogOption(Parcel parcel) {
        this((ActionType) parcel.readSerializable(), parcel.readString(), parcel.readInt(), parcel.createStringArrayList());
    }

    public HealthLogOption(ActionType actionType, String str) {
        this(actionType, str, 0, null);
    }

    public HealthLogOption(ActionType actionType, String str, int i, List<String> list) {
        this.actionType = actionType;
        this.uuid = str;
        this.uploadType = i;
        this.fileNameList = list;
    }

    public HealthLogOption(ActionType actionType, List<String> list) {
        this(actionType, null, 0, list);
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public int getUploadType() {
        return this.uploadType;
    }

    public void setUploadType(int i) {
        this.uploadType = i;
    }

    public List<String> getFileNameList() {
        return this.fileNameList;
    }

    public void setFileNameList(List<String> list) {
        this.fileNameList = list;
    }

    public enum ActionType {
        CAPTURE_LOG(1),
        UPLOAD_LOG(2),
        REMOVE_LOG(3);

        private final int actionType;

        ActionType(int i) {
            this.actionType = i;
        }

        public int geActionType() {
            return this.actionType;
        }
    }
}
