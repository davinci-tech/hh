package com.huawei.hwid.core.helper.handler;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class ErrorStatus implements Parcelable {
    public static final Parcelable.Creator<ErrorStatus> CREATOR = new Parcelable.Creator<ErrorStatus>() { // from class: com.huawei.hwid.core.helper.handler.ErrorStatus.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ErrorStatus createFromParcel(Parcel parcel) {
            ErrorStatus errorStatus = new ErrorStatus();
            errorStatus.mErrCode = parcel.readInt();
            errorStatus.mReason = parcel.readString();
            return errorStatus;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ErrorStatus[] newArray(int i) {
            return new ErrorStatus[i];
        }
    };
    public static final int ERRORSTATUS_NULL = 32;
    public static final int ERROR_NETWORK_UNAVIABLE = 2005;
    public static final int ERROR_NOT_USE_APK = 2033;
    public static final int ERROR_PARAM_INVALID = 2003;
    public static final int PERMISSION_NOT_ALLOW = 27;
    public static final int SIGN_IN_SDK_INNER_ERROR = 2015;
    public static final int SITE_DOMAIN_OR_PUBLICKEY_IS_NULL = 14;
    public static final int THIRD_ACCOUNT_NOT_ALLOW = 2026;
    private int mErrCode;
    private String mReason;
    private int mSubErrCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ErrorStatus() {
        this.mSubErrCode = -1;
    }

    public ErrorStatus(int i, String str) {
        this.mErrCode = i;
        this.mReason = str;
        this.mSubErrCode = i;
    }

    public ErrorStatus(Parcel parcel) {
        this.mSubErrCode = -1;
        this.mErrCode = parcel.readInt();
        this.mReason = parcel.readString();
        this.mSubErrCode = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mErrCode);
        parcel.writeString(this.mReason);
        parcel.writeInt(this.mSubErrCode);
    }

    public int getErrorCode() {
        return this.mErrCode;
    }

    public String getErrorReason() {
        return this.mReason;
    }

    public int getSubErrCode() {
        return this.mSubErrCode;
    }

    public void setSubErrCode(int i) {
        this.mSubErrCode = i;
    }

    public String toString() {
        return "[ErrorCode]:" + this.mErrCode;
    }
}
