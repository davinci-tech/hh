package com.huawei.wearengine.scope;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.device.model.RecordAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class ScopeInfo {

    @SerializedName("permissions")
    private List<String> mPermissions = new ArrayList();

    @SerializedName("name")
    private String mName = null;

    @SerializedName("id")
    private int mId = 0;

    @SerializedName("uri")
    private String mUri = null;

    public List<String> getPermissions() {
        return this.mPermissions;
    }

    public void setPermissions(List<String> list) {
        this.mPermissions = list;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public String getUri() {
        return this.mUri;
    }

    public void setUri(String str) {
        this.mUri = str;
    }

    public String toString() {
        return RecordAction.ACT_ID_TAG + this.mId + ",name=" + this.mName + ",uri=" + this.mUri + ",permissions=" + this.mPermissions.toString();
    }

    public int hashCode() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mId);
        stringBuffer.append("_");
        stringBuffer.append(this.mName);
        return stringBuffer.toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ScopeInfo)) {
            return false;
        }
        ScopeInfo scopeInfo = (ScopeInfo) obj;
        return scopeInfo.getId() == this.mId && scopeInfo.getName().equals(this.mName) && scopeInfo.getUri().equals(this.mUri) && scopeInfo.getPermissions().containsAll(this.mPermissions);
    }

    public String getPermissionsString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.mPermissions.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(';');
        }
        return sb.toString();
    }

    public List<String> getPermissionList(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(";")) {
            arrayList.add(str2);
        }
        return arrayList;
    }
}
