package com.huawei.hms.support.feature.request;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class AbstractAuthParams implements Parcelable, Api.ApiOptions.HasOptions {
    protected ArrayList<PermissionInfo> permissionArrayList;
    protected final ArrayList<Scope> scopeArrayList;
    public static final PermissionInfo UID_DYNAMIC_PERMISSION = new PermissionInfo().setPermissionUri("com.huawei.android.hms.account.getUID");
    public static final Scope PROFILE = new Scope("profile");
    public static final Scope EMAIL = new Scope("email");
    public static final Scope OPENID = new Scope("openid");
    public static final Scope SCOPE_GAMES = new Scope("https://www.huawei.com/auth/games");

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected AbstractAuthParams(Set<Scope> set, Set<PermissionInfo> set2) {
        this((ArrayList<Scope>) new ArrayList(set), (ArrayList<PermissionInfo>) new ArrayList(set2));
    }

    public AbstractAuthParams(ArrayList<Scope> arrayList, ArrayList<PermissionInfo> arrayList2) {
        this.scopeArrayList = arrayList;
        this.permissionArrayList = arrayList2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.scopeArrayList);
        parcel.writeList(this.permissionArrayList);
    }

    public List<Scope> getRequestScopeList() {
        return this.scopeArrayList;
    }

    public List<PermissionInfo> getPermissionInfos() {
        return this.permissionArrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractAuthParams)) {
            return false;
        }
        AbstractAuthParams abstractAuthParams = (AbstractAuthParams) obj;
        return isListEquals(this.scopeArrayList, abstractAuthParams.scopeArrayList) && isListEquals(this.permissionArrayList, abstractAuthParams.permissionArrayList);
    }

    protected <T> boolean isListEquals(ArrayList<T> arrayList, ArrayList<T> arrayList2) {
        if (arrayList == arrayList2) {
            return true;
        }
        if (arrayList.size() != arrayList2.size()) {
            return false;
        }
        return arrayList.containsAll(arrayList2);
    }

    public String toJson() throws JSONException {
        return toJsonObject().toString();
    }

    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.scopeArrayList != null) {
            JSONArray jSONArray = new JSONArray();
            Iterator<Scope> it = this.scopeArrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(scopeToJson(it.next()));
            }
            jSONObject.put(AuthInternalPickerConstant.SignInReqKey.SCOP_ARRAYLIST, jSONArray);
        }
        if (this.permissionArrayList != null) {
            JSONArray jSONArray2 = new JSONArray();
            Iterator<PermissionInfo> it2 = this.permissionArrayList.iterator();
            while (it2.hasNext()) {
                jSONArray2.put(permissionToJson(it2.next()));
            }
            jSONObject.put(AuthInternalPickerConstant.SignInReqKey.PERMISSION_ARRAYLIST, jSONArray2);
        }
        return jSONObject;
    }

    protected JSONObject scopeToJson(Scope scope) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (scope.getScopeUri() != null) {
            jSONObject.put("mScopeUri", scope.getScopeUri());
        }
        return jSONObject;
    }

    public static Scope jsonToScope(JSONObject jSONObject) {
        return new Scope(jSONObject.optString("mScopeUri", null));
    }

    protected JSONObject permissionToJson(PermissionInfo permissionInfo) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (permissionInfo.getPermission() != null) {
            jSONObject.put("permission", permissionInfo.getPermission());
        }
        return jSONObject;
    }

    public static PermissionInfo jsonToPermission(JSONObject jSONObject) {
        return new PermissionInfo().setPermissionUri(jSONObject.optString("permission", null));
    }

    public int hashCode() {
        ArrayList<Scope> arrayList = this.scopeArrayList;
        int hashCode = arrayList == null ? 0 : arrayList.hashCode();
        ArrayList<PermissionInfo> arrayList2 = this.permissionArrayList;
        return ((hashCode + 31) * 31) + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.scopeArrayList;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }
}
