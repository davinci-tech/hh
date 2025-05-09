package com.huawei.hms.support.hwid.request;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.feature.request.AbstractAuthParams;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HuaweiIdAuthParams extends AbstractAuthParams implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private String f6011a;
    public static final HuaweiIdAuthParams DEFAULT_AUTH_REQUEST_PARAM = new HuaweiIdAuthParamsHelper().setId().setProfile().createParams();
    public static final HuaweiIdAuthParams DEFAULT_AUTH_REQUEST_PARAM_GAME = new HuaweiIdAuthParamsHelper().setScope(SCOPE_GAMES).createParams();
    public static final Parcelable.Creator<HuaweiIdAuthParams> CREATOR = new Parcelable.Creator<HuaweiIdAuthParams>() { // from class: com.huawei.hms.support.hwid.request.HuaweiIdAuthParams.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HuaweiIdAuthParams createFromParcel(Parcel parcel) {
            return new HuaweiIdAuthParams(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public HuaweiIdAuthParams[] newArray(int i) {
            return new HuaweiIdAuthParams[i];
        }
    };

    protected HuaweiIdAuthParams(Set<Scope> set, Set<PermissionInfo> set2) {
        this((ArrayList<Scope>) new ArrayList(set), (ArrayList<PermissionInfo>) new ArrayList(set2));
    }

    protected HuaweiIdAuthParams(Set<Scope> set, Set<PermissionInfo> set2, String str) {
        this((ArrayList<Scope>) new ArrayList(set), (ArrayList<PermissionInfo>) new ArrayList(set2));
        this.f6011a = str;
    }

    protected HuaweiIdAuthParams(ArrayList<Scope> arrayList, ArrayList<PermissionInfo> arrayList2) {
        super(arrayList, arrayList2);
        this.f6011a = "";
    }

    protected HuaweiIdAuthParams(ArrayList<Scope> arrayList, ArrayList<PermissionInfo> arrayList2, String str) {
        super(arrayList, arrayList2);
        this.f6011a = str;
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public HuaweiIdAuthParams m642clone() throws CloneNotSupportedException {
        return (HuaweiIdAuthParams) super.clone();
    }

    private HuaweiIdAuthParams(Parcel parcel) {
        super((ArrayList<Scope>) parcel.createTypedArrayList(Scope.CREATOR), (ArrayList<PermissionInfo>) parcel.createTypedArrayList(PermissionInfo.CREATOR));
        this.f6011a = "";
        this.f6011a = parcel.readString();
    }

    @Override // com.huawei.hms.support.feature.request.AbstractAuthParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HuaweiIdAuthParams)) {
            return false;
        }
        HuaweiIdAuthParams huaweiIdAuthParams = (HuaweiIdAuthParams) obj;
        return isListEquals(this.scopeArrayList, huaweiIdAuthParams.scopeArrayList) && isListEquals(this.permissionArrayList, huaweiIdAuthParams.permissionArrayList);
    }

    public static HuaweiIdAuthParams fromJson(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return fromJsonObject(new JSONObject(str));
    }

    public static HuaweiIdAuthParams fromJsonObject(JSONObject jSONObject) throws JSONException {
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
        HuaweiIdAuthParams huaweiIdAuthParams = new HuaweiIdAuthParams((ArrayList<Scope>) arrayList, (ArrayList<PermissionInfo>) arrayList2);
        huaweiIdAuthParams.setSignInParams(optString);
        return huaweiIdAuthParams;
    }

    @Override // com.huawei.hms.support.feature.request.AbstractAuthParams
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jsonObject = super.toJsonObject();
        jsonObject.put("signInParams", this.f6011a);
        return jsonObject;
    }

    public void setSignInParams(String str) {
        this.f6011a = str;
    }

    @Override // com.huawei.hms.support.feature.request.AbstractAuthParams
    public int hashCode() {
        return (((this.scopeArrayList == null ? 0 : this.scopeArrayList.hashCode()) + 31) * 31) + (this.permissionArrayList != null ? this.permissionArrayList.hashCode() : 0);
    }
}
