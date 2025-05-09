package com.huawei.hiai.awareness.service;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

/* loaded from: classes4.dex */
public class RequestResult implements Parcelable {
    public static final Parcelable.Creator<RequestResult> CREATOR = new Parcelable.Creator<RequestResult>() { // from class: com.huawei.hiai.awareness.service.RequestResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestResult createFromParcel(Parcel parcel) {
            return new RequestResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestResult[] newArray(int i) {
            return new RequestResult[i];
        }
    };
    public static final int DEFAULT_CONFIDENCE_VALUE = 100;
    private static final int ERROR_CODE_INVALID_VALUE = -1;
    private static final int FENCE_TYPE_INVALID_VALUE = 0;
    private static final int INVALID_VALUE = -1;
    private static final long TIME_LONG_INVALID_VALUE = 0;
    private static final int TOP_KEY_ARRAY_PACKAGE_NAME_INDEX = 0;
    private int action;
    private int confidence;
    private String content;
    private int errorCode;
    private String errorResult;
    private String registerTopKey;
    private int resultType;
    private String secondAction;
    private long sensorTime;
    private int status;
    private long time;
    private int triggerStatus;
    private int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RequestResult() {
        this.type = 0;
        this.secondAction = null;
        this.action = -1;
        this.time = 0L;
        this.sensorTime = 0L;
        this.confidence = 100;
        this.status = -1;
        this.registerTopKey = null;
        this.resultType = -1;
        this.content = null;
        this.triggerStatus = -1;
        this.errorCode = -1;
        this.errorResult = null;
    }

    public RequestResult(int i, int i2, int i3, String str) {
        this.time = 0L;
        this.sensorTime = 0L;
        this.confidence = 100;
        this.registerTopKey = null;
        this.resultType = -1;
        this.content = null;
        this.triggerStatus = -1;
        this.errorCode = -1;
        this.errorResult = null;
        this.type = i;
        this.status = i2;
        this.action = i3;
        this.secondAction = str;
    }

    public RequestResult(int i, String str) {
        this.type = 0;
        this.secondAction = null;
        this.action = -1;
        this.time = 0L;
        this.sensorTime = 0L;
        this.confidence = 100;
        this.status = -1;
        this.registerTopKey = null;
        this.resultType = -1;
        this.content = null;
        this.triggerStatus = -1;
        this.errorCode = i;
        this.errorResult = str;
    }

    public RequestResult(int i, String str, int i2) {
        this(i, str);
        this.resultType = i2;
    }

    public RequestResult(Parcel parcel) {
        this.type = 0;
        this.secondAction = null;
        this.action = -1;
        this.time = 0L;
        this.sensorTime = 0L;
        this.confidence = 100;
        this.status = -1;
        this.registerTopKey = null;
        this.resultType = -1;
        this.content = null;
        this.triggerStatus = -1;
        this.errorCode = -1;
        this.errorResult = null;
        readFromParcelInner(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeInt(this.status);
        parcel.writeInt(this.action);
        parcel.writeString(this.secondAction);
        parcel.writeLong(this.time);
        parcel.writeLong(this.sensorTime);
        parcel.writeInt(this.confidence);
        parcel.writeString(this.registerTopKey);
        parcel.writeInt(this.resultType);
        parcel.writeString(this.content);
        parcel.writeInt(this.triggerStatus);
        parcel.writeInt(this.errorCode);
        parcel.writeString(this.errorResult);
    }

    public void readFromParcel(Parcel parcel) {
        readFromParcelInner(parcel);
    }

    private void readFromParcelInner(Parcel parcel) {
        this.type = parcel.readInt();
        this.status = parcel.readInt();
        this.action = parcel.readInt();
        this.secondAction = parcel.readString();
        this.time = parcel.readLong();
        this.sensorTime = parcel.readLong();
        this.confidence = parcel.readInt();
        this.registerTopKey = parcel.readString();
        this.resultType = parcel.readInt();
        this.content = parcel.readString();
        this.triggerStatus = parcel.readInt();
        this.errorCode = parcel.readInt();
        this.errorResult = parcel.readString();
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getSecondAction() {
        return this.secondAction;
    }

    public void setSecondAction(String str) {
        this.secondAction = str;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int i) {
        this.action = i;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public long getSensorTime() {
        return this.sensorTime;
    }

    public void setSensorTime(long j) {
        this.sensorTime = j;
    }

    public int getConfidence() {
        return this.confidence;
    }

    public void setConfidence(int i) {
        this.confidence = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getRegisterTopKey() {
        return this.registerTopKey;
    }

    public void setRegisterTopKey(String str) {
        this.registerTopKey = str;
    }

    public int getResultType() {
        return this.resultType;
    }

    public void setResultType(int i) {
        this.resultType = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public int getTriggerStatus() {
        return this.triggerStatus;
    }

    public void setTriggerStatus(int i) {
        this.triggerStatus = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getErrorResult() {
        return this.errorResult;
    }

    public void setErrorResult(String str) {
        this.errorResult = str;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "RequestResult{%d, %s}", Integer.valueOf(this.errorCode), this.errorResult);
    }
}
