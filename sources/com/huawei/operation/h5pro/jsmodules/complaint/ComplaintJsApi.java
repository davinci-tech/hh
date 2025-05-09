package com.huawei.operation.h5pro.jsmodules.complaint;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.huawei.health.h5pro.core.H5ProBundle;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.ixx;
import defpackage.nrv;
import health.compact.a.SharedPreferenceManager;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ComplaintJsApi extends JsBaseModule {
    private ComplaintInfo mComplaintInfoStorage;

    @JavascriptInterface
    public String complainAddInfo() {
        LogUtil.i(this.TAG, "complainAddInfo: enter, ");
        JSONObject jSONObject = new JSONObject();
        if (this.mComplaintInfoStorage == null) {
            LogUtil.e(this.TAG, "complainAddInfo: mComplaintInfoStorage is null");
            return "";
        }
        try {
            jSONObject.put("appId", ComplaintConstants.COMPLAINT_APP_ID);
            jSONObject.put(ComplaintConstants.SCENE_ID_PARAM_KEY, this.mComplaintInfoStorage.getSceneId());
            jSONObject.put(ComplaintConstants.SUB_SCENE_ID_PARAM_KEY, this.mComplaintInfoStorage.getSubSceneId());
            jSONObject.put("disableUserUpload", false);
            jSONObject.put("deviceId", LoginInit.getInstance(this.mContext).getDeviceId());
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(this.mComplaintInfoStorage.getContentId())) {
                jSONObject2.put("contentId", this.mComplaintInfoStorage.getContentId());
                jSONObject2.put(ComplaintConstants.CONTENT_TITLE_PARAM_KEY, this.mComplaintInfoStorage.getContentTitle());
            }
            if (!TextUtils.isEmpty(this.mComplaintInfoStorage.getGroupId())) {
                jSONObject2.put("groupId", this.mComplaintInfoStorage.getGroupId());
                jSONObject2.put(ComplaintConstants.GROUP_NAME_PARAM_KEY, this.mComplaintInfoStorage.getGroupName());
                jSONObject2.put(ComplaintConstants.MEMBER_ID_PARAM_KEY, this.mComplaintInfoStorage.getMemberId());
                jSONObject2.put(ComplaintConstants.MEMBER_NAME_PARAM_KEY, this.mComplaintInfoStorage.getMemberName());
                jSONObject2.put(ComplaintConstants.CHAT_EVIDENCE_PARAM_KEY, this.mComplaintInfoStorage.getChatEvidence());
            }
            jSONObject.put(ComplaintConstants.ADDITIONAL_CONTEXT_PARAM_KEY, jSONObject2);
        } catch (JSONException e) {
            LogUtil.e(this.TAG, "complainAddInfo: JSONException, " + e.getMessage());
        }
        LogUtil.i(this.TAG, "complainAddInfo: res, " + jSONObject.toString());
        return jSONObject.toString();
    }

    @JavascriptInterface
    public void afterSubmit(String str) {
        LogUtil.i(this.TAG, "afterSubmit: " + str);
        SharedPreferenceManager.c("privacy_center", "user_complaint", String.valueOf(System.currentTimeMillis()));
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("event", 1);
        LogUtil.d(this.TAG, "putBiEvent key:20401105 content:" + nrv.a(hashMap));
        ixx.d().d(this.mContext, ComplaintConstants.BI_KEY_COMPLAINT_SUBMIT, hashMap, 0);
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule
    public void onCreate(H5ProBundle h5ProBundle) {
        super.onCreate(h5ProBundle);
        if (this.mComplaintInfoStorage == null) {
            this.mComplaintInfoStorage = new ComplaintInfo();
        }
        if (h5ProBundle == null) {
            LogUtil.w(this.TAG, "onCreate: h5ProBundle is null");
            return;
        }
        this.mComplaintInfoStorage.setSceneId(h5ProBundle.getString(ComplaintConstants.SCENE_ID_PARAM_KEY));
        this.mComplaintInfoStorage.setSubSceneId(h5ProBundle.getString(ComplaintConstants.SUB_SCENE_ID_PARAM_KEY));
        this.mComplaintInfoStorage.setContentId(h5ProBundle.getString("contentId"));
        this.mComplaintInfoStorage.setContentTitle(h5ProBundle.getString(ComplaintConstants.CONTENT_TITLE_PARAM_KEY));
        this.mComplaintInfoStorage.setGroupId(h5ProBundle.getString("groupId"));
        this.mComplaintInfoStorage.setGroupName(h5ProBundle.getString(ComplaintConstants.GROUP_NAME_PARAM_KEY));
        this.mComplaintInfoStorage.setMemberId(h5ProBundle.getString(ComplaintConstants.MEMBER_ID_PARAM_KEY));
        this.mComplaintInfoStorage.setMemberName(h5ProBundle.getString(ComplaintConstants.MEMBER_NAME_PARAM_KEY));
        this.mComplaintInfoStorage.setChatEvidence(h5ProBundle.getString(ComplaintConstants.CHAT_EVIDENCE_PARAM_KEY));
    }

    @Override // com.huawei.health.h5pro.jsbridge.base.JsBaseModule, com.huawei.health.h5pro.jsbridge.base.JsModuleBase
    public void onDestroy() {
        super.onDestroy();
        this.mComplaintInfoStorage = null;
    }
}
