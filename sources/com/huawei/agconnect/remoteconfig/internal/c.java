package com.huawei.agconnect.remoteconfig.internal;

import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.AgcCrypto;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import com.huawei.agconnect.remoteconfig.ConfigValues;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class c implements ConfigValues {

    /* renamed from: a, reason: collision with root package name */
    private ConfigContainer f1818a;
    private final String b;

    /* JADX WARN: Removed duplicated region for block: B:6:0x0023 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024 A[RETURN] */
    @Override // com.huawei.agconnect.remoteconfig.ConfigValues
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getValueAsString(java.lang.String r3) {
        /*
            r2 = this;
            com.huawei.agconnect.remoteconfig.internal.ConfigContainer r0 = r2.f1818a     // Catch: org.json.JSONException -> Ld
            if (r0 == 0) goto L20
            org.json.JSONObject r0 = r0.a()     // Catch: org.json.JSONException -> Ld
            java.lang.String r3 = r0.getString(r3)     // Catch: org.json.JSONException -> Ld
            goto L21
        Ld:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "not find in config values for key : "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "RemoteConfig"
            com.huawei.agconnect.common.api.Logger.d(r0, r3)
        L20:
            r3 = 0
        L21:
            if (r3 == 0) goto L24
            return r3
        L24:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.remoteconfig.internal.c.getValueAsString(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    @Override // com.huawei.agconnect.remoteconfig.ConfigValues
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Long getValueAsLong(java.lang.String r3) {
        /*
            r2 = this;
            com.huawei.agconnect.remoteconfig.internal.ConfigContainer r0 = r2.f1818a     // Catch: org.json.JSONException -> L11
            if (r0 == 0) goto L24
            org.json.JSONObject r0 = r0.a()     // Catch: org.json.JSONException -> L11
            long r0 = r0.getLong(r3)     // Catch: org.json.JSONException -> L11
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            goto L25
        L11:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "not find in config values for key : "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "RemoteConfig"
            com.huawei.agconnect.common.api.Logger.d(r0, r3)
        L24:
            r3 = 0
        L25:
            if (r3 == 0) goto L28
            return r3
        L28:
            r0 = 0
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.remoteconfig.internal.c.getValueAsLong(java.lang.String):java.lang.Long");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0028  */
    @Override // com.huawei.agconnect.remoteconfig.ConfigValues
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Double getValueAsDouble(java.lang.String r3) {
        /*
            r2 = this;
            com.huawei.agconnect.remoteconfig.internal.ConfigContainer r0 = r2.f1818a     // Catch: org.json.JSONException -> L11
            if (r0 == 0) goto L24
            org.json.JSONObject r0 = r0.a()     // Catch: org.json.JSONException -> L11
            double r0 = r0.getDouble(r3)     // Catch: org.json.JSONException -> L11
            java.lang.Double r3 = java.lang.Double.valueOf(r0)
            goto L25
        L11:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "not find in config values for key : "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "RemoteConfig"
            com.huawei.agconnect.common.api.Logger.d(r0, r3)
        L24:
            r3 = 0
        L25:
            if (r3 == 0) goto L28
            return r3
        L28:
            r0 = 0
            java.lang.Double r3 = java.lang.Double.valueOf(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.remoteconfig.internal.c.getValueAsDouble(java.lang.String):java.lang.Double");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    @Override // com.huawei.agconnect.remoteconfig.ConfigValues
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] getValueAsByteArray(java.lang.String r3) {
        /*
            r2 = this;
            com.huawei.agconnect.remoteconfig.internal.ConfigContainer r0 = r2.f1818a     // Catch: org.json.JSONException -> Ld
            if (r0 == 0) goto L20
            org.json.JSONObject r0 = r0.a()     // Catch: org.json.JSONException -> Ld
            java.lang.String r3 = r0.getString(r3)     // Catch: org.json.JSONException -> Ld
            goto L21
        Ld:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "not find in config values for key : "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "RemoteConfig"
            com.huawei.agconnect.common.api.Logger.d(r0, r3)
        L20:
            r3 = 0
        L21:
            if (r3 == 0) goto L2e
            java.lang.String r0 = "UTF-8"
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r0)
            byte[] r3 = r3.getBytes(r0)
            return r3
        L2e:
            byte[] r3 = com.huawei.agconnect.remoteconfig.AGConnectConfig.DEFAULT.BYTE_ARRAY_VALUE
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.remoteconfig.internal.c.getValueAsByteArray(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    @Override // com.huawei.agconnect.remoteconfig.ConfigValues
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Boolean getValueAsBoolean(java.lang.String r3) {
        /*
            r2 = this;
            com.huawei.agconnect.remoteconfig.internal.ConfigContainer r0 = r2.f1818a     // Catch: org.json.JSONException -> Ld
            if (r0 == 0) goto L20
            org.json.JSONObject r0 = r0.a()     // Catch: org.json.JSONException -> Ld
            java.lang.String r3 = r0.getString(r3)     // Catch: org.json.JSONException -> Ld
            goto L21
        Ld:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "not find in config values for key : "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            java.lang.String r0 = "RemoteConfig"
            com.huawei.agconnect.common.api.Logger.d(r0, r3)
        L20:
            r3 = 0
        L21:
            r0 = 0
            if (r3 == 0) goto L47
            java.util.regex.Pattern r1 = com.huawei.agconnect.remoteconfig.internal.c.a.InterfaceC0037a.f1819a
            java.util.regex.Matcher r1 = r1.matcher(r3)
            boolean r1 = r1.matches()
            if (r1 == 0) goto L36
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            return r3
        L36:
            java.util.regex.Pattern r1 = com.huawei.agconnect.remoteconfig.internal.c.a.InterfaceC0037a.b
            java.util.regex.Matcher r3 = r1.matcher(r3)
            boolean r3 = r3.matches()
            if (r3 == 0) goto L47
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            return r3
        L47:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.remoteconfig.internal.c.getValueAsBoolean(java.lang.String):java.lang.Boolean");
    }

    @Override // com.huawei.agconnect.remoteconfig.ConfigValues
    public boolean containKey(String str) {
        ConfigContainer configContainer = this.f1818a;
        return (configContainer == null || configContainer.a().opt(str) == null) ? false : true;
    }

    public Map<String, Object> c() {
        HashMap hashMap = new HashMap();
        ConfigContainer configContainer = this.f1818a;
        if (configContainer != null) {
            Iterator<String> keys = configContainer.a().keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object opt = this.f1818a.a().opt(next);
                if (opt != null) {
                    hashMap.put(next, opt);
                }
            }
        }
        return hashMap;
    }

    void b() {
        this.f1818a = new ConfigContainer((JSONObject) null);
        SharedPrefUtil.getInstance().remove("com.huawei.agconnect.config", this.b);
    }

    void a(ConfigContainer configContainer) {
        this.f1818a = configContainer;
        SharedPrefUtil.getInstance().put("com.huawei.agconnect.config", this.b, ConfigContainer.class, configContainer, AgcCrypto.class);
    }

    ConfigContainer a() {
        return this.f1818a;
    }

    c(String str, AGConnectInstance aGConnectInstance) {
        String str2 = str + "_" + aGConnectInstance.getIdentifier();
        this.b = str2;
        this.f1818a = (ConfigContainer) SharedPrefUtil.getInstance().get("com.huawei.agconnect.config", str2, ConfigContainer.class, null, AgcCrypto.class);
    }
}
