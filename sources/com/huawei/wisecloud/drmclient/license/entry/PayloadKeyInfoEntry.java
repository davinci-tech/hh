package com.huawei.wisecloud.drmclient.license.entry;

import org.json.JSONObject;

/* loaded from: classes7.dex */
public class PayloadKeyInfoEntry {
    private int ckEncryptAlg;
    private JSONObject contentKey;
    private String keyId;
    private String secretInfo;
    private String sessionKey;
    private int skEncryptAlg;

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public int getSkEncryptAlg() {
        return this.skEncryptAlg;
    }

    public void setSkEncryptAlg(int i) {
        this.skEncryptAlg = i;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public void setSessionKey(String str) {
        this.sessionKey = str;
    }

    public int getCkEncryptAlg() {
        return this.ckEncryptAlg;
    }

    public void setCkEncryptAlg(int i) {
        this.ckEncryptAlg = i;
    }

    public JSONObject getContentKey() {
        return this.contentKey;
    }

    public void setContentKey(JSONObject jSONObject) {
        this.contentKey = jSONObject;
    }

    public String getSecretInfo() {
        return this.secretInfo;
    }

    public void setSecretInfo(String str) {
        this.secretInfo = str;
    }
}
