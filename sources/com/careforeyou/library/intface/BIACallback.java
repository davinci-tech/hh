package com.careforeyou.library.intface;

import java.util.List;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface BIACallback {
    void onCanSyncUnit();

    void onResult(int i, JSONObject jSONObject);

    void onState(int i, String str);

    void onSyncAllUserInfo();

    void onUserMatchResult(JSONObject jSONObject, List<Integer> list);
}
