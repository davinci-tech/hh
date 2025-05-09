package com.huawei.hms.support.hwid.result;

import android.accounts.Account;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.hwid.ap;
import com.huawei.hms.hwid.as;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.feature.result.AbstractAuthAccount;
import com.huawei.hms.utils.HMSPackageManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AuthHuaweiId extends AbstractAuthAccount {
    public static final Parcelable.Creator<AuthHuaweiId> CREATOR = new Parcelable.Creator<AuthHuaweiId>() { // from class: com.huawei.hms.support.hwid.result.AuthHuaweiId.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthHuaweiId createFromParcel(Parcel parcel) {
            return new AuthHuaweiId(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public AuthHuaweiId[] newArray(int i) {
            return new AuthHuaweiId[i];
        }
    };

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public /* bridge */ /* synthetic */ AbstractAuthAccount requestExtraScopes(List list) {
        return requestExtraScopes((List<Scope>) list);
    }

    AuthHuaweiId(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9) {
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
    }

    protected AuthHuaweiId(Parcel parcel) {
        readFromParcel(parcel);
    }

    public AuthHuaweiId() {
        this.gender = -1;
        this.status = 0;
        this.grantedScopes = new HashSet();
    }

    public static AuthHuaweiId createDefault() {
        return build(null, null, null, null, null, null, 0, -1, new HashSet(), null, null, null);
    }

    public static AuthHuaweiId build(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9) {
        return new AuthHuaweiId(str, str2, str3, str4, str5, str6, i, i2, set, str7, str8, str9);
    }

    public static AuthHuaweiId fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            throw new JSONException("invalid json format");
        }
        return fromJson(new JSONObject(str));
    }

    public static AuthHuaweiId fromJson(JSONObject jSONObject) throws JSONException {
        AuthHuaweiId authHuaweiId = new AuthHuaweiId();
        authHuaweiId.parserJson(jSONObject);
        return authHuaweiId;
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AuthHuaweiId) {
            return getAuthorizedScopes().equals(((AuthHuaweiId) obj).getAuthorizedScopes());
        }
        return false;
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public AuthHuaweiId requestExtraScopes(List<Scope> list) {
        if (ap.b(list).booleanValue()) {
            this.extensionScopes.addAll(list);
        }
        return this;
    }

    public Account getHuaweiAccount(Context context) {
        String hMSPackageName = HMSPackageManager.getInstance(context).getHMSPackageName();
        if (TextUtils.isEmpty(this.email) || TextUtils.isEmpty(hMSPackageName)) {
            return null;
        }
        return new Account(this.email, hMSPackageName);
    }

    public int getAgeRangeFlag() {
        try {
            return Integer.parseInt(this.ageRange);
        } catch (NumberFormatException unused) {
            as.b("AuthHuaweiId", "Invalid ageRange NumberFormatException", true);
            return -1;
        }
    }

    @Override // com.huawei.hms.support.feature.result.AbstractAuthAccount
    public int hashCode() {
        return getRequestedScopes().hashCode();
    }
}
