package com.huawei.hms.support.account.result;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.hwid.ap;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.feature.result.AbstractAuthAccount;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hms.utils.HMSPackageManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AuthAccount extends AbstractAuthAccount {
    public static final Parcelable.Creator<AuthAccount> CREATOR = new Parcelable.Creator<AuthAccount>() { // from class: com.huawei.hms.support.account.result.AuthAccount.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthAccount createFromParcel(Parcel parcel) {
            return new AuthAccount(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthAccount[] newArray(int i) {
            return new AuthAccount[i];
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private int f5947a;

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public /* bridge */ /* synthetic */ AbstractAuthAccount requestExtraScopes(List list) {
        return requestExtraScopes((List<Scope>) list);
    }

    AuthAccount(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9, int i3) {
        this.openId = str;
        this.uid = str2;
        this.photoUriString = str4;
        this.displayName = str3;
        this.accessToken = str5;
        this.serviceCountryCode = str6;
        this.gender = i2;
        this.status = i;
        this.serverAuthCode = str7;
        this.grantedScopes = set;
        this.unionId = str8;
        this.countryCode = str9;
        this.carrierId = i3;
    }

    protected AuthAccount(Parcel parcel) {
        readFromParcel(parcel);
    }

    public AuthAccount() {
        this.gender = -1;
        this.status = 0;
        this.grantedScopes = new HashSet();
        this.f5947a = 0;
    }

    public static AuthAccount createDefault() {
        return build(null, null, null, null, null, null, 0, -1, new HashSet(), null, null, null, 0);
    }

    public static AuthAccount build(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9, int i3) {
        return new AuthAccount(str, str2, str3, str4, str5, str6, i, i2, set, str7, str8, str9, i3);
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f5947a);
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.f5947a = parcel.readInt();
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public AuthAccount requestExtraScopes(List<Scope> list) {
        if (ap.b(list).booleanValue()) {
            this.extensionScopes.addAll(list);
        }
        return this;
    }

    public static AuthAccount fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            throw new JSONException("invalid json format");
        }
        return fromJson(new JSONObject(str));
    }

    public static AuthAccount fromJson(JSONObject jSONObject) throws JSONException {
        AuthAccount authAccount = new AuthAccount();
        authAccount.parserJson(jSONObject);
        return authAccount;
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public void parserJson(JSONObject jSONObject) throws JSONException {
        super.parserJson(jSONObject);
        this.f5947a = jSONObject.optInt(CommonConstant.KEY_ACCOUNT_FLAG, 0);
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (getUid() != null) {
            jSONObject.put("uid", getUid());
        }
        if (getOpenId() != null) {
            jSONObject.put("openId", getOpenId());
        }
        if (getDisplayName() != null) {
            jSONObject.put(CommonConstant.KEY_DISPLAY_NAME, getDisplayName());
        }
        if (getAvatarUriString() != null) {
            jSONObject.put(CommonConstant.KEY_PHOTO_URI, getAvatarUriString());
        }
        if (getAccessToken() != null) {
            jSONObject.put("accessToken", getAccessToken());
        }
        jSONObject.put("status", getStatus());
        jSONObject.put(CommonConstant.KEY_GENDER, getGender());
        if (getAuthorizationCode() != null) {
            jSONObject.put("serverAuthCode", getAuthorizationCode());
        }
        if (getServiceCountryCode() != null) {
            jSONObject.put(CommonConstant.KEY_SERVICE_COUNTRY_CODE, getServiceCountryCode());
        }
        if (getCountryCode() != null) {
            jSONObject.put("countryCode", getCountryCode());
        }
        if (getUnionId() != null) {
            jSONObject.put(CommonConstant.KEY_UNION_ID, getUnionId());
        }
        if (getEmail() != null) {
            jSONObject.put("email", getEmail());
        }
        if (getIdToken() != null) {
            jSONObject.put(CommonConstant.KEY_ID_TOKEN, getIdToken());
        }
        jSONObject.put(CommonConstant.KEY_EXPIRATION_TIME_SECS, getExpirationTimeSecs());
        if (getGivenName() != null) {
            jSONObject.put(CommonConstant.KEY_GIVEN_NAME, getGivenName());
        }
        if (getFamilyName() != null) {
            jSONObject.put(CommonConstant.KEY_FAMILY_NAME, getFamilyName());
        }
        if (getAgeRange() != null) {
            jSONObject.put(CommonConstant.KEY_AGE_RANGE, getAgeRange());
        }
        jSONObject.put(CommonConstant.KEY_HOME_ZONE, getHomeZone());
        jSONObject.put(CommonConstant.KEY_ACCOUNT_FLAG, getAccountFlag());
        jSONObject.put(CommonConstant.KEY_CARRIER_ID, getCarrierId());
        return jsonPutOpt(jsonPutScopes(jSONObject));
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AuthAccount) {
            return getAuthorizedScopes().equals(((AuthAccount) obj).getAuthorizedScopes());
        }
        return false;
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public String toString() {
        return "{uid: " + this.uid + ",displayName: " + this.displayName + ",photoUriString: " + this.photoUriString + ",status: " + this.status + ",gender: " + this.gender + ",serviceCountryCode: " + this.serviceCountryCode + ",countryCode: " + this.countryCode + CommonConstant.KEY_ACCOUNT_FLAG + this.f5947a + '}';
    }

    public int getAccountFlag() {
        return this.f5947a;
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public int hashCode() {
        return getRequestedScopes().hashCode();
    }

    public Account getAccount(Context context) {
        String hMSPackageName = HMSPackageManager.getInstance(context).getHMSPackageName();
        if (TextUtils.isEmpty(this.email) || TextUtils.isEmpty(hMSPackageName)) {
            return null;
        }
        return new Account(this.email, hMSPackageName);
    }
}
