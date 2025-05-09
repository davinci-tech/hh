package com.huawei.hianalytics;

import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.transport.TransportHandlerFactory;
import com.huawei.hianalytics.core.transport.net.Response;
import com.huawei.hianalytics.framework.config.CipherType;
import com.huawei.hianalytics.framework.config.IMandatoryParameters;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d implements IMandatoryParameters {
    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public String getAppVer() {
        return i.a().m550a().h;
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public byte[] getLoadWorkKey() {
        p0 a2 = p0.a();
        if (a2.f62a == null) {
            a2.f62a = HexUtil.hexStr2ByteArray(a2.m576a());
        }
        return a2.f62a;
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public Pair<String, String> getRsaPublicKeyFromNetWork(String str, String str2) {
        String str3;
        String str4;
        String str5;
        String e = j.e();
        String str6 = "";
        if (TextUtils.isEmpty(e)) {
            e = j.a("Privacy_MY", "public_key_version", "");
            i.a().m550a().t = e;
        }
        if ("maint".equals(str2)) {
            str3 = i.a().m550a().q;
            if (TextUtils.isEmpty(str3)) {
                str3 = AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Public_Sp_Key", j.a("Privacy_MY", "public_key_maint", ""));
                i.a().m550a().q = str3;
            }
        } else {
            str3 = i.a().m550a().p;
            if (TextUtils.isEmpty(str3)) {
                str3 = AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Public_Sp_Key", j.a("Privacy_MY", "public_key_oper", ""));
                i.a().m550a().p = str3;
            }
        }
        if (TextUtils.isEmpty(str3) || !"2.0".equals(e) || j.m560a()) {
            HashMap hashMap = new HashMap();
            e1 a2 = i.a().a(str);
            String str7 = a2 != null ? a2.f32a : "";
            if (TextUtils.isEmpty(str7)) {
                l m550a = i.a().m550a();
                str7 = TextUtils.isEmpty(m550a.g) ? m550a.f : m550a.g;
            }
            hashMap.put("App-Id", str7);
            a1 a3 = j.a(str, str2);
            Map<String, String> map = a3 != null ? a3.f8a : null;
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    if ("x-hasdk-pkg".equals(entry.getKey()) || "x-hasdk-token".equals(entry.getKey()) || "x-hasdk-clientid".equals(entry.getKey())) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            if (j.m561a(EnvUtils.getAppContext())) {
                str4 = j.a();
            } else {
                a1 a4 = j.a(str, str2);
                str4 = a4 != null ? a4.f14c : "";
            }
            if (TextUtils.isEmpty(str4)) {
                HiLog.w("GetPublicKey", "collectUrl is null, tag: " + str + ", type: " + str2);
            } else {
                String replace = "{url}/getPublicKey?keytype=4".replace("{url}", str4);
                byte[] bArr = new byte[0];
                a1 a5 = j.a(str, str2);
                Response execute = TransportHandlerFactory.create(replace, hashMap, bArr, a5 == null ? 1 : a5.c).execute();
                if (execute == null) {
                    str5 = "get pubKey response is null";
                } else if (execute.getHttpCode() != 200) {
                    str5 = "get pubKey fail HttpCode: " + execute.getHttpCode();
                } else {
                    String content = execute.getContent();
                    if (!TextUtils.isEmpty(content)) {
                        try {
                            JSONObject jSONObject = new JSONObject(content);
                            String optString = jSONObject.optString("publicKey");
                            String optString2 = jSONObject.optString("publicKeyOM");
                            String optString3 = jSONObject.optString("pubkey_version");
                            String str8 = System.currentTimeMillis() + "";
                            String optString4 = jSONObject.optString(HwExerciseConstants.JSON_NAME_TIME_INTERVAL);
                            String optString5 = jSONObject.optString("serverTime");
                            j.m559a("Privacy_MY", "public_key_oper", AesGcmKsWrapper.encrypt("HiAnalytics_Sdk_Public_Sp_Key", optString));
                            j.m559a("Privacy_MY", "public_key_maint", AesGcmKsWrapper.encrypt("HiAnalytics_Sdk_Public_Sp_Key", optString2));
                            j.m559a("Privacy_MY", "public_key_time_interval", optString4);
                            j.m559a("Privacy_MY", "public_key_time_last", str8);
                            j.m559a("Privacy_MY", "public_key_version", optString3);
                            if (!TextUtils.isEmpty(optString5)) {
                                long parseLong = Long.parseLong(optString5) - System.currentTimeMillis();
                                j.m558a("global_v2", "public_key_abs_time", parseLong);
                                i.a().m550a().c = parseLong;
                            }
                            i.a().m550a().p = optString;
                            i.a().m550a().q = optString2;
                            i.a().m550a().t = optString3;
                            i.a().m550a().s = str8;
                            i.a().m550a().r = optString4;
                            if ("maint".equals(str2)) {
                                j.f3877a = optString2;
                            } else {
                                j.f3877a = optString;
                            }
                            HiLog.si("GetPublicKey", "get pubKey success");
                        } catch (JSONException unused) {
                            HiLog.sw("GetPublicKey", "get pubKey parse json failed");
                        }
                    }
                    str6 = j.f3877a;
                }
                HiLog.sw("GetPublicKey", str5);
                str6 = j.f3877a;
            }
        } else {
            str6 = str3;
        }
        return Pair.create(str6, j.e());
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public boolean isFlashKey() {
        long a2 = j.a("analytics_key", "flashKeyTime", -1L);
        if (a2 == -1) {
            a2 = j.a("Privacy_MY", "flashKeyTime", -1L);
        } else {
            j.b(EnvUtils.getAppContext(), "analytics_key");
        }
        return System.currentTimeMillis() - a2 > 1296000000;
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public void refreshKey(String str, int i) {
        p0 a2 = p0.a();
        a2.getClass();
        if (TextUtils.isEmpty(str) || i != 1) {
            return;
        }
        HiLog.si("RootKeyManager", "generate a new local working key");
        a2.f63b = str;
        a2.m578a(a2.a(str));
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public String rsaEncrypt(String str, String str2, String str3) {
        return h.a(str, str2, str3);
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public String getProcessName() {
        return j.b(EnvUtils.getAppContext());
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public String getModel() {
        return Build.MODEL;
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public String getDebugModeUrl() {
        return j.a();
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public String getCipherType() {
        return CipherType.AES_GCM;
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public List<String> getAllTags() {
        return HiAnalyticsManager.getAllTags();
    }

    @Override // com.huawei.hianalytics.framework.config.IMandatoryParameters
    public boolean checkDebugModeEnabled() {
        return j.m561a(EnvUtils.getAppContext());
    }
}
