package com.huawei.wear.oversea.router;

import android.content.Context;
import defpackage.sux;
import defpackage.suz;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public abstract class WalletProvider {
    private HashMap<String, WalletAction> actionMap = new HashMap<>();
    private String domain;
    private String schema;

    public abstract String getName();

    protected abstract void registerActions();

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public suz invokeAction(String str, Context context, Map<String, Object> map) {
        WalletAction findAction = findAction(str);
        if (findAction != null) {
            return findAction.invoke(context, new sux(str, map));
        }
        suz suzVar = new suz();
        suzVar.d("");
        suzVar.c("Action not found,domain: " + this.domain + " actionName: " + str);
        return suzVar;
    }

    protected void registerAction(String str, WalletAction walletAction) {
        this.actionMap.put(str, walletAction);
    }

    public WalletAction findAction(String str) {
        return this.actionMap.get(str);
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String str) {
        this.schema = str;
    }

    public String getAbsoluteName() {
        if (getSchema() != null) {
            return getSchema() + ":" + getDomain() + "_" + getName();
        }
        return getDomain() + "_" + getName();
    }
}
