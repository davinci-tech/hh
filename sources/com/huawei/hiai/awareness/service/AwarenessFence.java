package com.huawei.hiai.awareness.service;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hiai.awareness.AwarenessConstants;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class AwarenessFence implements Parcelable {
    private static final String COMMA = ",";
    public static final Parcelable.Creator<AwarenessFence> CREATOR = new Parcelable.Creator<AwarenessFence>() { // from class: com.huawei.hiai.awareness.service.AwarenessFence.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessFence createFromParcel(Parcel parcel) {
            return new AwarenessFence(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AwarenessFence[] newArray(int i) {
            return new AwarenessFence[i];
        }
    };
    private static final long FENCE_CONDITION_INVALID_VALUE = 0;
    public static final int FENCE_STATUS_ACTION_INVALID_VALUE = -1;
    public static final int FENCE_TYPE_INVALID_VALUE = 0;
    public static final int IS_RELATION = 1;
    private static final int MAX_APP_ARRAY_LEN = 1024;
    private static final String TAG = "AwarenessFence";
    private static final int TOP_KEY_ARRAY_PACKAGE_NAME_INDEX = 0;
    private int action;
    private long condition;
    private String endTime;
    private List<String> excludeAppList;
    private String excludeAppsStr;
    private String fenceKey;
    private String packageName;
    private String secondAction;
    private String startTime;
    private int status;
    private String topKey;
    private int type;

    private boolean checkAppArrayLen(int i) {
        return i > 0 && i <= 1024;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AwarenessFence(Parcel parcel) {
        this.type = 0;
        this.status = -1;
        this.action = -1;
        this.secondAction = null;
        this.startTime = null;
        this.endTime = null;
        this.condition = 0L;
        this.excludeAppList = null;
        this.packageName = null;
        this.fenceKey = null;
        this.topKey = null;
        this.excludeAppsStr = null;
        this.type = parcel.readInt();
        this.status = parcel.readInt();
        this.action = parcel.readInt();
        this.secondAction = parcel.readString();
        this.startTime = parcel.readString();
        this.endTime = parcel.readString();
        this.condition = parcel.readLong();
        this.excludeAppsStr = parcel.readString();
        this.packageName = parcel.readString();
        this.fenceKey = parcel.readString();
        this.topKey = parcel.readString();
        int readInt = parcel.readInt();
        if (checkAppArrayLen(readInt)) {
            String[] strArr = new String[readInt];
            parcel.readStringArray(strArr);
            this.excludeAppList = Arrays.asList(strArr);
        }
    }

    public AwarenessFence(int i, int i2, int i3, String str) {
        this.startTime = null;
        this.endTime = null;
        this.condition = 0L;
        this.excludeAppList = null;
        this.packageName = null;
        this.fenceKey = null;
        this.topKey = null;
        this.excludeAppsStr = null;
        this.type = i;
        this.status = i2;
        this.action = i3;
        this.secondAction = str;
        if (!TextUtils.isEmpty(str)) {
            this.fenceKey = i + "," + i2 + "," + i3 + AwarenessConstants.SECOND_ACTION_SPLITE_TAG + str;
            return;
        }
        this.fenceKey = i + "," + i2 + "," + i3;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.status);
        parcel.writeInt(this.action);
        parcel.writeString(this.secondAction);
        parcel.writeString(this.startTime);
        parcel.writeString(this.endTime);
        parcel.writeLong(this.condition);
        parcel.writeString(this.excludeAppsStr);
        parcel.writeString(this.packageName);
        parcel.writeString(this.fenceKey);
        parcel.writeString(this.topKey);
        List<String> list = this.excludeAppList;
        if (list != null && checkAppArrayLen(list.size())) {
            String[] strArr = (String[]) this.excludeAppList.toArray(new String[0]);
            parcel.writeInt(strArr.length);
            parcel.writeStringArray(strArr);
            return;
        }
        parcel.writeInt(0);
    }

    public int getType() {
        return this.type;
    }

    public int getStatus() {
        return this.status;
    }

    public int getAction() {
        return this.action;
    }

    public String getSecondAction() {
        return this.secondAction;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public List<String> getExcludeAppList() {
        return this.excludeAppList;
    }

    public long getCondition() {
        return this.condition;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getFenceKey() {
        return this.fenceKey;
    }

    public String getTopKey() {
        return this.topKey;
    }

    public String getExcludeAppsStr() {
        return this.excludeAppsStr;
    }

    public String getActionString() {
        if (!TextUtils.isEmpty(this.secondAction)) {
            return this.action + AwarenessConstants.SECOND_ACTION_SPLITE_TAG + this.secondAction;
        }
        return this.action + "";
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setAction(int i) {
        this.action = i;
    }

    public void setSecondAction(String str) {
        this.secondAction = str;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void setExcludeAppList(List<String> list) {
        this.excludeAppList = list;
    }

    public void setCondition(long j) {
        this.condition = j;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public void setTopKey(String str) {
        this.topKey = str;
    }

    public void setExcludeAppsStr(String str) {
        this.excludeAppsStr = str;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "AwarenessFence{%d, %d, %d}", Integer.valueOf(this.type), Integer.valueOf(this.status), Integer.valueOf(this.action));
    }
}
