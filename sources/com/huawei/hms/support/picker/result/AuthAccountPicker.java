package com.huawei.hms.support.picker.result;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.feature.result.AbstractPickerAccount;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AuthAccountPicker extends AbstractPickerAccount {
    public static final Parcelable.Creator<AbstractPickerAccount> CREATOR = new Parcelable.Creator<AbstractPickerAccount>() { // from class: com.huawei.hms.support.picker.result.AuthAccountPicker.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel, reason: merged with bridge method [inline-methods] */
        public AbstractPickerAccount createFromParcel2(Parcel parcel) {
            return new AuthAccountPicker(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray, reason: merged with bridge method [inline-methods] */
        public AbstractPickerAccount[] newArray2(int i) {
            return new AuthAccountPicker[i];
        }
    };
    private static final String TAG = "AuthAccountPicker";

    AuthAccountPicker(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9, int i3, int i4) {
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
        this.accountAttr = i4;
    }

    protected AuthAccountPicker(Parcel parcel) {
        readFromParcel(parcel);
    }

    public AuthAccountPicker() {
    }

    public static AuthAccountPicker build(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, Set<Scope> set, String str7, String str8, String str9, int i3, int i4) {
        return new AuthAccountPicker(str, str2, str3, str4, str5, str6, i, i2, set, str7, str8, str9, i3, i4);
    }

    public static AuthAccountPicker fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            throw new JSONException("invalid json format");
        }
        return fromJson(new JSONObject(str));
    }

    public static AuthAccountPicker fromJson(JSONObject jSONObject) throws JSONException {
        AuthAccountPicker authAccountPicker = new AuthAccountPicker();
        authAccountPicker.parserJson(jSONObject);
        return authAccountPicker;
    }
}
