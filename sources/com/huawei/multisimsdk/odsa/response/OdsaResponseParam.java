package com.huawei.multisimsdk.odsa.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.operation.utils.Constants;
import defpackage.lpr;

/* loaded from: classes5.dex */
public class OdsaResponseParam implements Parcelable {
    public static final Parcelable.Creator<OdsaResponseParam> CREATOR = new Parcelable.Creator<OdsaResponseParam>() { // from class: com.huawei.multisimsdk.odsa.response.OdsaResponseParam.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bYY_, reason: merged with bridge method [inline-methods] */
        public OdsaResponseParam createFromParcel(Parcel parcel) {
            return new OdsaResponseParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public OdsaResponseParam[] newArray(int i) {
            return new OdsaResponseParam[i];
        }
    };
    private String appId;
    private String companionAppEligibility;
    private String companionDeviceServices;
    private String iccid;
    private int manageSubscriptionType;
    private String notEnabledContentsType;
    private String notEnabledUrl;
    private String notEnabledUserData;
    private int operationResult;
    private String operationType;
    private int pollingInterval;
    private String profileActivationCode;
    private String profileIccid;
    private String profileSmdpAddress;
    private String serviceStatus;
    private int subscriptionResult;
    private String subscriptionServiceContentsType;
    private String subscriptionServiceUrl;
    private String subscriptionServiceUserData;
    private String token;
    private String validity;
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public OdsaResponseParam() {
        this.pollingInterval = 20000;
        this.operationType = lpr.b[2];
    }

    protected OdsaResponseParam(Parcel parcel) {
        this.pollingInterval = 20000;
        this.operationType = parcel.readString();
        this.operationResult = parcel.readInt();
        this.version = parcel.readString();
        this.validity = parcel.readString();
        this.token = parcel.readString();
        this.appId = parcel.readString();
        this.companionAppEligibility = parcel.readString();
        this.companionDeviceServices = parcel.readString();
        this.notEnabledUrl = parcel.readString();
        this.notEnabledUserData = parcel.readString();
        this.notEnabledContentsType = parcel.readString();
        this.iccid = parcel.readString();
        this.profileIccid = parcel.readString();
        this.serviceStatus = parcel.readString();
        this.subscriptionServiceUrl = parcel.readString();
        this.subscriptionServiceUserData = parcel.readString();
        this.subscriptionServiceContentsType = parcel.readString();
        this.subscriptionResult = parcel.readInt();
        this.profileSmdpAddress = parcel.readString();
        this.profileActivationCode = parcel.readString();
        this.manageSubscriptionType = parcel.readInt();
        this.pollingInterval = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.operationType);
        parcel.writeInt(this.operationResult);
        parcel.writeString(this.version);
        parcel.writeString(this.validity);
        parcel.writeString(this.token);
        parcel.writeString(this.appId);
        parcel.writeString(this.companionAppEligibility);
        parcel.writeString(this.companionDeviceServices);
        parcel.writeString(this.notEnabledUrl);
        parcel.writeString(this.notEnabledUserData);
        parcel.writeString(this.notEnabledContentsType);
        parcel.writeString(this.iccid);
        parcel.writeString(this.profileIccid);
        parcel.writeString(this.serviceStatus);
        parcel.writeString(this.subscriptionServiceUrl);
        parcel.writeString(this.subscriptionServiceUserData);
        parcel.writeString(this.subscriptionServiceContentsType);
        parcel.writeInt(this.subscriptionResult);
        parcel.writeString(this.profileSmdpAddress);
        parcel.writeString(this.profileActivationCode);
        parcel.writeInt(this.manageSubscriptionType);
        parcel.writeInt(this.pollingInterval);
    }

    public String getOperationType() {
        return this.operationType;
    }

    public void setOperationType(String str) {
        this.operationType = str;
    }

    public int getOperationResult() {
        return this.operationResult;
    }

    public void setOperationResult(int i) {
        this.operationResult = i;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getValidity() {
        return this.validity;
    }

    public void setValidity(String str) {
        this.validity = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getCompanionAppEligibility() {
        return this.companionAppEligibility;
    }

    public void setCompanionAppEligibility(String str) {
        this.companionAppEligibility = str;
    }

    public String getCompanionDeviceServices() {
        return this.companionDeviceServices;
    }

    public void setCompanionDeviceServices(String str) {
        this.companionDeviceServices = str;
    }

    public String getNotEnabledUrl() {
        return this.notEnabledUrl;
    }

    public void setNotEnabledUrl(String str) {
        this.notEnabledUrl = str;
    }

    public String getNotEnabledUserData() {
        return this.notEnabledUserData;
    }

    public void setNotEnabledUserData(String str) {
        this.notEnabledUserData = str;
    }

    public String getNotEnabledContentsType() {
        return this.notEnabledContentsType;
    }

    public void setNotEnabledContentsType(String str) {
        this.notEnabledContentsType = str;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public String getProfileIccid() {
        return this.profileIccid;
    }

    public void setProfileIccid(String str) {
        this.profileIccid = str;
    }

    public String getServiceStatus() {
        return this.serviceStatus;
    }

    public void setServiceStatus(String str) {
        this.serviceStatus = str;
    }

    public String getSubscriptionServiceUrl() {
        return this.subscriptionServiceUrl;
    }

    public void setSubscriptionServiceUrl(String str) {
        this.subscriptionServiceUrl = str;
    }

    public String getSubscriptionServiceUserData() {
        return this.subscriptionServiceUserData;
    }

    public void setSubscriptionServiceUserData(String str) {
        this.subscriptionServiceUserData = str;
    }

    public String getsubscriptionServiceContentsType() {
        return this.subscriptionServiceContentsType;
    }

    public void setsubscriptionServiceContentsType(String str) {
        this.subscriptionServiceContentsType = str;
    }

    public int getSubscriptionResult() {
        return this.subscriptionResult;
    }

    public void setSubscriptionResult(int i) {
        this.subscriptionResult = i;
    }

    public String getProfileSmdpAddress() {
        return this.profileSmdpAddress;
    }

    public void setProfileSmdpAddress(String str) {
        this.profileSmdpAddress = str;
    }

    public String getProfileActivationCode() {
        return this.profileActivationCode;
    }

    public void setProfileActivationCode(String str) {
        this.profileActivationCode = str;
    }

    public void setPollingInterval(int i) {
        this.pollingInterval = i * 60000;
    }

    public int getPollingInterval() {
        return this.pollingInterval;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("OdsaResponseParam{operationType='");
        sb.append(this.operationType);
        sb.append("', operationResult=");
        sb.append(this.operationResult);
        sb.append(", version=");
        sb.append(this.version);
        sb.append(", validity='");
        sb.append(this.validity);
        sb.append("', token='");
        String str = this.token;
        Object obj = Constants.NULL;
        sb.append(str != null ? Integer.valueOf(str.length()) : Constants.NULL);
        sb.append("', appId='");
        sb.append(this.appId);
        sb.append("', companionAppEligibility='");
        sb.append(this.companionAppEligibility);
        sb.append("', companionDeviceServices='");
        sb.append(this.companionDeviceServices);
        sb.append("', notEnabledUrl='");
        sb.append(this.notEnabledUrl);
        sb.append("', notEnabledUserData='");
        String str2 = this.notEnabledUserData;
        sb.append(str2 != null ? Integer.valueOf(str2.length()) : Constants.NULL);
        sb.append("', notEnabledContentsType='");
        sb.append(this.notEnabledContentsType);
        sb.append("', iccid='");
        String str3 = this.iccid;
        sb.append(str3 != null ? Integer.valueOf(str3.length()) : Constants.NULL);
        sb.append("', profileIccid='");
        String str4 = this.profileIccid;
        sb.append(str4 != null ? Integer.valueOf(str4.length()) : Constants.NULL);
        sb.append("', serviceStatus='");
        sb.append(this.serviceStatus);
        sb.append("', subscriptionServiceUrl='");
        sb.append(this.subscriptionServiceUrl);
        sb.append("', subscriptionServiceUserData='");
        String str5 = this.subscriptionServiceUserData;
        sb.append(str5 != null ? Integer.valueOf(str5.length()) : Constants.NULL);
        sb.append("', subscriptionResult=");
        sb.append(this.subscriptionResult);
        sb.append(", profileSmdpAddress='");
        sb.append(this.profileSmdpAddress);
        sb.append("', subscriptionServiceContentsType='");
        sb.append(this.subscriptionServiceContentsType);
        sb.append("', profileActivationCode='");
        String str6 = this.profileActivationCode;
        if (str6 != null) {
            obj = Integer.valueOf(str6.length());
        }
        sb.append(obj);
        sb.append("', manageSubscriptionType=");
        sb.append(this.manageSubscriptionType);
        sb.append("', pollingInterval=");
        sb.append(this.pollingInterval);
        sb.append('}');
        return sb.toString();
    }
}
