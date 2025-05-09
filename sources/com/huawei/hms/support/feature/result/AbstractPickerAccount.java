package com.huawei.hms.support.feature.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.support.api.entity.auth.Scope;
import defpackage.ksy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class AbstractPickerAccount extends AbstractAuthAccount implements Parcelable {
    private static final String TAG = "AbstractPickerAccount";
    protected int accountAttr;
    protected String code;
    protected String scope;

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    AbstractPickerAccount(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9, int i3, int i4) {
        this.openId = str;
        this.uid = str2;
        this.displayName = str3;
        this.photoUriString = str4;
        this.accessToken = str5;
        this.serviceCountryCode = str6;
        this.status = i;
        this.gender = i2;
        this.grantedScopes = set;
        this.serverAuthCode = str7;
        this.unionId = str8;
        this.countryCode = str9;
        this.carrierId = i3;
        this.accountAttr = i4;
    }

    protected AbstractPickerAccount(Parcel parcel) {
        readFromParcel(parcel);
    }

    public AbstractPickerAccount() {
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uid);
        parcel.writeString(this.openId);
        parcel.writeString(this.displayName);
        parcel.writeString(this.photoUriString);
        parcel.writeString(this.accessToken);
        parcel.writeInt(this.status);
        parcel.writeInt(this.gender);
        parcel.writeString(this.serverAuthCode);
        parcel.writeString(this.serviceCountryCode);
        parcel.writeString(this.countryCode);
        parcel.writeList(new ArrayList(this.grantedScopes));
        parcel.writeString(this.unionId);
        parcel.writeString(this.email);
        parcel.writeString(this.idToken);
        parcel.writeLong(this.expirationTimeSecs);
        parcel.writeString(this.givenName);
        parcel.writeString(this.familyName);
        parcel.writeString(this.ageRange);
        parcel.writeInt(this.homeZone);
        parcel.writeInt(this.carrierId);
        parcel.writeInt(this.accountAttr);
        parcel.writeString(this.scope);
        parcel.writeString(this.code);
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public void readFromParcel(Parcel parcel) {
        this.uid = parcel.readString();
        this.openId = parcel.readString();
        this.displayName = parcel.readString();
        this.photoUriString = parcel.readString();
        this.accessToken = parcel.readString();
        this.status = parcel.readInt();
        this.gender = parcel.readInt();
        this.serverAuthCode = parcel.readString();
        this.serviceCountryCode = parcel.readString();
        this.countryCode = parcel.readString();
        this.grantedScopes = new HashSet(parcel.readArrayList(Scope.class.getClassLoader()));
        this.unionId = parcel.readString();
        this.email = parcel.readString();
        this.idToken = parcel.readString();
        this.expirationTimeSecs = parcel.readLong();
        this.givenName = parcel.readString();
        this.familyName = parcel.readString();
        this.ageRange = parcel.readString();
        this.homeZone = parcel.readInt();
        this.carrierId = parcel.readInt();
        this.accountAttr = parcel.readInt();
        this.scope = parcel.readString();
        this.code = parcel.readString();
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public void parserJson(JSONObject jSONObject) throws JSONException {
        this.uid = jSONObject.optString("uid", null);
        this.openId = jSONObject.optString("openId", null);
        this.displayName = jSONObject.optString(CommonConstant.KEY_DISPLAY_NAME, null);
        this.photoUriString = jSONObject.optString(CommonConstant.KEY_PHOTO_URI, null);
        this.accessToken = jSONObject.optString("accessToken", null);
        this.status = jSONObject.optInt("status", -1);
        this.gender = jSONObject.optInt(CommonConstant.KEY_GENDER, -1);
        this.serverAuthCode = jSONObject.optString("serverAuthCode", null);
        this.serviceCountryCode = jSONObject.optString(CommonConstant.KEY_SERVICE_COUNTRY_CODE, null);
        this.countryCode = jSONObject.optString("countryCode", null);
        JSONArray jSONArray = jSONObject.toString().contains(CommonConstant.KEY_GRANTED_SCOPES) ? jSONObject.getJSONArray(CommonConstant.KEY_GRANTED_SCOPES) : null;
        if (jSONArray != null) {
            HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                String optString = jSONArray.getJSONObject(i).optString("mScopeUri", null);
                if (optString != null) {
                    hashSet.add(new Scope(optString));
                }
            }
            this.grantedScopes = hashSet;
        }
        this.unionId = jSONObject.optString(CommonConstant.KEY_UNION_ID, null);
        this.email = jSONObject.optString("email", null);
        this.idToken = jSONObject.optString(CommonConstant.KEY_ID_TOKEN, null);
        this.expirationTimeSecs = 0L;
        if (jSONObject.toString().contains(CommonConstant.KEY_EXPIRATION_TIME_SECS)) {
            try {
                this.expirationTimeSecs = Long.parseLong(jSONObject.getString(CommonConstant.KEY_EXPIRATION_TIME_SECS));
            } catch (NumberFormatException unused) {
                ksy.c(TAG, "NumberFormatException", true);
            }
        }
        this.givenName = jSONObject.optString(CommonConstant.KEY_GIVEN_NAME, null);
        this.familyName = jSONObject.optString(CommonConstant.KEY_FAMILY_NAME, null);
        this.ageRange = jSONObject.optString(CommonConstant.KEY_AGE_RANGE, null);
        this.homeZone = jSONObject.optInt(CommonConstant.KEY_HOME_ZONE, 0);
        this.carrierId = jSONObject.optInt(CommonConstant.KEY_CARRIER_ID, 0);
        this.accountAttr = jSONObject.optInt("accountAttr", -1);
        this.scope = jSONObject.optString("scope", null);
        this.code = jSONObject.optString("code", null);
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public String toJson() throws JSONException {
        return toJsonObject().toString();
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        putJsonObject(jSONObject, "uid", getUid(), true);
        putJsonObject(jSONObject, "openId", getOpenId(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_PHOTO_URI, getAvatarUriString(), true);
        putJsonObject(jSONObject, "accessToken", getAccessToken(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_DISPLAY_NAME, getDisplayName(), true);
        putJsonObject(jSONObject, "status", Integer.valueOf(getStatus()), false);
        putJsonObject(jSONObject, CommonConstant.KEY_GENDER, Integer.valueOf(getGender()), false);
        putJsonObject(jSONObject, "countryCode", getCountryCode(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_UNION_ID, getUnionId(), true);
        putJsonObject(jSONObject, "email", getEmail(), true);
        putJsonObject(jSONObject, "serverAuthCode", getAuthorizationCode(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_SERVICE_COUNTRY_CODE, getServiceCountryCode(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_ID_TOKEN, getIdToken(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_EXPIRATION_TIME_SECS, Long.valueOf(getExpirationTimeSecs()), false);
        putJsonObject(jSONObject, CommonConstant.KEY_GIVEN_NAME, getGivenName(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_FAMILY_NAME, getFamilyName(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_AGE_RANGE, getAgeRange(), true);
        putJsonObject(jSONObject, CommonConstant.KEY_HOME_ZONE, Integer.valueOf(getHomeZone()), false);
        putJsonObject(jSONObject, CommonConstant.KEY_CARRIER_ID, Integer.valueOf(getCarrierId()), false);
        putJsonObject(jSONObject, "accountAttr", Integer.valueOf(getAccountAttr()), false);
        putJsonObject(jSONObject, "scope", getScope(), true);
        putJsonObject(jSONObject, "code", getCode(), true);
        return jSONObject;
    }

    private void putJsonObject(JSONObject jSONObject, String str, Object obj, boolean z) throws JSONException {
        if (!z) {
            jSONObject.put(str, obj);
        } else if (obj != null) {
            jSONObject.put(str, obj);
        }
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public String toString() {
        return "{uid: " + this.uid + ",displayName: " + this.displayName + ",photoUriString: " + this.photoUriString + ",status: " + this.status + ",gender: " + this.gender + ",serviceCountryCode: " + this.serviceCountryCode + ",countryCode: " + this.countryCode + "}serverAuthCode: " + this.serverAuthCode + ",accountAttr: " + this.accountAttr + '}';
    }

    public int getAccountAttr() {
        return this.accountAttr;
    }

    public void setAccountAttr(int i) {
        this.accountAttr = i;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public String getScope() {
        return this.scope;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setServerAuthCode(String str) {
        this.serverAuthCode = str;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }
}
