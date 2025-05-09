package com.huawei.hms.support.picker.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.common.CommonPickerConstant;
import com.huawei.hms.support.feature.request.AbstractAuthParams;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class AccountPickerParams extends AbstractAuthParams implements Cloneable {
    private String mDeviceId;
    private String mDeviceInfo;
    private String mDeviceType;
    private String mGrsAppName;
    private String mHostAccessToken;
    private int mIdTokenSignAlg;
    private boolean mIsGuideLogin;
    private String mLoginUrl;
    private boolean mOobeFlag;
    private String mQRPromptFirstLevelType;
    private String mQRPromptSecondLevel;
    private String mRedirectUrl;
    private String signInParams;
    public static final AccountPickerParams DEFAULT_AUTH_REQUEST_PARAM = new AccountPickerParamsHelper().setId().setProfile().createParams();
    public static final AccountPickerParams DEFAULT_AUTH_REQUEST_PARAM_GAME = new AccountPickerParamsHelper().setScope(SCOPE_GAMES).createParams();
    public static final Parcelable.Creator<AccountPickerParams> CREATOR = new Parcelable.Creator<AccountPickerParams>() { // from class: com.huawei.hms.support.picker.request.AccountPickerParams.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountPickerParams createFromParcel(Parcel parcel) {
            return new AccountPickerParams(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccountPickerParams[] newArray(int i) {
            return new AccountPickerParams[i];
        }
    };

    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    private void setRedirectUrl(String str) {
        this.mRedirectUrl = str;
    }

    public String getLoginUrl() {
        return this.mLoginUrl;
    }

    private void setLoginUrl(String str) {
        this.mLoginUrl = str;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getDeviceInfo() {
        return this.mDeviceInfo;
    }

    private void setDeviceInfo(String str) {
        this.mDeviceInfo = str;
    }

    public boolean isGuideLogin() {
        return this.mIsGuideLogin;
    }

    private void setGuideLogin(boolean z) {
        this.mIsGuideLogin = z;
    }

    public String getQRPromptFirstLevelType() {
        return this.mQRPromptFirstLevelType;
    }

    private void setQRPromptFirstLevelType(String str) {
        this.mQRPromptFirstLevelType = str;
    }

    public String getQRPromptSecondLevel() {
        return this.mQRPromptSecondLevel;
    }

    private void setQRPromptSecondLevel(String str) {
        this.mQRPromptSecondLevel = str;
    }

    public boolean getOobeFlag() {
        return this.mOobeFlag;
    }

    private void setOobeFlag(boolean z) {
        this.mOobeFlag = z;
    }

    public String getHostAccessToken() {
        return this.mHostAccessToken;
    }

    public void setHostAccessToken(String str) {
        this.mHostAccessToken = str;
    }

    public void setIdTokenSignAlg(int i) {
        this.mIdTokenSignAlg = i;
    }

    public int getIdTokenSignAlg() {
        return this.mIdTokenSignAlg;
    }

    protected AccountPickerParams(Set<Scope> set, Set<PermissionInfo> set2) {
        this((ArrayList<Scope>) new ArrayList(set), (ArrayList<PermissionInfo>) new ArrayList(set2));
    }

    AccountPickerParams(Set<Scope> set, Set<PermissionInfo> set2, String str) {
        this((ArrayList<Scope>) new ArrayList(set), (ArrayList<PermissionInfo>) new ArrayList(set2));
        this.signInParams = str;
    }

    protected AccountPickerParams(ArrayList<Scope> arrayList, ArrayList<PermissionInfo> arrayList2) {
        super(arrayList, arrayList2);
        this.signInParams = "";
        this.mDeviceType = "";
        this.mDeviceId = "";
        this.mRedirectUrl = "";
        this.mDeviceInfo = "";
        this.mQRPromptFirstLevelType = "";
        this.mQRPromptSecondLevel = "";
        this.mLoginUrl = "";
    }

    protected AccountPickerParams(ArrayList<Scope> arrayList, ArrayList<PermissionInfo> arrayList2, String str) {
        super(arrayList, arrayList2);
        this.mDeviceType = "";
        this.mDeviceId = "";
        this.mRedirectUrl = "";
        this.mDeviceInfo = "";
        this.mQRPromptFirstLevelType = "";
        this.mQRPromptSecondLevel = "";
        this.mLoginUrl = "";
        this.signInParams = str;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AccountPickerParams m643clone() throws CloneNotSupportedException {
        return (AccountPickerParams) super.clone();
    }

    private AccountPickerParams(Parcel parcel) {
        super((ArrayList<Scope>) parcel.createTypedArrayList(Scope.CREATOR), (ArrayList<PermissionInfo>) parcel.createTypedArrayList(PermissionInfo.CREATOR));
        this.signInParams = "";
        this.mDeviceType = "";
        this.mDeviceId = "";
        this.mRedirectUrl = "";
        this.mDeviceInfo = "";
        this.mQRPromptFirstLevelType = "";
        this.mQRPromptSecondLevel = "";
        this.mLoginUrl = "";
        this.signInParams = parcel.readString();
    }

    @Override // com.huawei.hms.support.feature.request.AbstractAuthParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountPickerParams)) {
            return false;
        }
        AccountPickerParams accountPickerParams = (AccountPickerParams) obj;
        return isListEquals(this.scopeArrayList, accountPickerParams.scopeArrayList) && isListEquals(this.permissionArrayList, accountPickerParams.permissionArrayList);
    }

    public static AccountPickerParams fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return fromJsonObject(new JSONObject(str));
    }

    public static AccountPickerParams fromJsonObject(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray(AuthInternalPickerConstant.SignInReqKey.SCOP_ARRAYLIST);
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jsonToScope(jSONArray.getJSONObject(i)));
            }
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray(AuthInternalPickerConstant.SignInReqKey.PERMISSION_ARRAYLIST);
        ArrayList arrayList2 = new ArrayList();
        if (jSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                arrayList2.add(jsonToPermission(jSONArray2.getJSONObject(i2)));
            }
        }
        String optString = jSONObject.optString("signInParams");
        JSONObject jSONObject2 = new JSONObject(optString);
        String optString2 = jSONObject2.optString(CommonPickerConstant.RequestParams.KEY_REDIRECT_URL);
        String optString3 = jSONObject2.optString("deviceInfo");
        boolean optBoolean = jSONObject2.optBoolean(CommonPickerConstant.RequestParams.KEY_GUIDE_LOGIN);
        String optString4 = jSONObject2.optString(CommonPickerConstant.RequestParams.KEY_LOGNIN_URL);
        String optString5 = jSONObject2.optString(CommonPickerConstant.RequestParams.KEY_QR_PROMPT_FIRST_LEVEL_TYPE);
        String optString6 = jSONObject2.optString(CommonPickerConstant.RequestParams.KEY_QR_PROMPT_SECOND_LEVEL);
        boolean optBoolean2 = jSONObject2.optBoolean(CommonPickerConstant.RequestParams.KEY_OOBE_FLAG);
        int optInt = jSONObject2.optInt("idTokenSignAlg");
        String optString7 = jSONObject2.optString(CommonPickerConstant.RequestParams.KEY_HOST_ACCESS_TOKEN);
        String optString8 = jSONObject2.optString(CommonPickerConstant.RequestParams.KEY_GRS_APP_NAME);
        AccountPickerParams accountPickerParams = new AccountPickerParams((ArrayList<Scope>) arrayList, (ArrayList<PermissionInfo>) arrayList2);
        accountPickerParams.setSignInParams(optString);
        accountPickerParams.setRedirectUrl(optString2);
        accountPickerParams.setDeviceInfo(optString3);
        accountPickerParams.setGuideLogin(optBoolean);
        accountPickerParams.setQRPromptFirstLevelType(optString5);
        accountPickerParams.setQRPromptSecondLevel(optString6);
        accountPickerParams.setOobeFlag(optBoolean2);
        accountPickerParams.setLoginUrl(optString4);
        accountPickerParams.setIdTokenSignAlg(optInt);
        accountPickerParams.setHostAccessToken(optString7);
        accountPickerParams.setGrsAppName(optString8);
        return accountPickerParams;
    }

    @Override // com.huawei.hms.support.feature.request.AbstractAuthParams
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("signInParams", this.signInParams);
        return jsonObject;
    }

    public void setSignInParams(String str) {
        this.signInParams = str;
    }

    public String getSignInParams() {
        return this.signInParams;
    }

    @Override // com.huawei.hms.support.feature.request.AbstractAuthParams
    public int hashCode() {
        return (((this.scopeArrayList == null ? 0 : this.scopeArrayList.hashCode()) + 31) * 31) + (this.permissionArrayList != null ? this.permissionArrayList.hashCode() : 0);
    }

    public String[] getStringArray() {
        int size = this.scopeArrayList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = this.scopeArrayList.get(i).getScopeUri();
        }
        return strArr;
    }

    public ArrayList<PermissionInfo> getPermissionArray() {
        return this.permissionArrayList;
    }

    public String getGrsAppName() {
        return this.mGrsAppName;
    }

    public void setGrsAppName(String str) {
        this.mGrsAppName = str;
    }
}
