package com.huawei.pluginsport.helper;

import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.PhoneInfoUtils;
import defpackage.dql;
import defpackage.drd;
import defpackage.koq;
import defpackage.moj;
import defpackage.mwp;
import defpackage.mws;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public abstract class AbsAiModelConfigHelper {
    private static final String ALLOW_LIST_BRAND_MODE = "ALLOW_LIST";
    private static final String ALL_BRAND_MODE = "ALL";
    public static final int CPU_PLATFORM_UNSUPPORTED = -1;
    private static final String SHIELD_ALL_BRAND_SWITCH = "OPEN";
    public static final int SUPPORTED_MODEL_TYPE = 1;
    private static final String TAG = "AiModelConfigHelper";
    private static final int TIME_OUT_SECOND = 2;
    private static final int TWO_HUNDRED = 200;
    public static final int VALID_ARRAY_LENGTH = 2;
    private static Map<String, e> modelTypeMap = new HashMap();
    private final String mAiModelConfigName;

    protected int getKirModelType(int i) {
        return i >= 200 ? 1 : -1;
    }

    protected int getQuaModelType() {
        return 1;
    }

    protected abstract void initDefaultConfig();

    public AbsAiModelConfigHelper(String str) {
        this.mAiModelConfigName = str;
        initModelAndPlugin();
    }

    public static void preDownloadConfig() {
        if (!AuthorizationUtils.a(BaseApplication.getContext())) {
            LogUtil.a(TAG, "preDownloadConfig getAuthorizationStatus: false");
        } else {
            drd.e(new dql("com.huawei.health_common_config", new HashMap()), "SportField", 1, (DownloadCallback<File>) new DownloadCallback() { // from class: com.huawei.pluginsport.helper.AbsAiModelConfigHelper.3
                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onFinish(Object obj) {
                    LogUtil.a(AbsAiModelConfigHelper.TAG, "preDownloadConfig success, result: ", obj.toString());
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onProgress(long j, long j2, boolean z, String str) {
                    LogUtil.a(AbsAiModelConfigHelper.TAG, "preDownloadConfig onProgress, already: ", Long.valueOf(j), ", total: " + j2, ", fileId: ", str);
                }

                @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
                public void onFail(int i, Throwable th) {
                    LogUtil.b(AbsAiModelConfigHelper.TAG, "preDownloadConfig failed, e: ", th.getMessage());
                }
            });
        }
    }

    private void initModelAndPlugin() {
        initCloudConfigSetting();
        if (!isSupportMtkOrQua()) {
            String c = SystemInfo.c();
            ReleaseLogUtil.e(TAG, "hiaiversion = ", c);
            if (!TextUtils.isEmpty(c)) {
                String[] split = c.split("\\.");
                ReleaseLogUtil.e(TAG, "versionSplit = ", Arrays.toString(split));
                if (split.length >= 2) {
                    try {
                        int parseInt = Integer.parseInt(split[1]);
                        ReleaseLogUtil.e(TAG, "version = ", Integer.valueOf(parseInt));
                        getAiModel().e = getKirModelType(parseInt);
                        return;
                    } catch (NumberFormatException unused) {
                        ReleaseLogUtil.d(TAG, "NumberFormatException");
                        getAiModel().e = -1;
                        return;
                    }
                }
                getAiModel().e = -1;
                return;
            }
            getAiModel().e = -1;
            return;
        }
        getAiModel().e = getQuaModelType();
    }

    private boolean isSupportMtkOrQua() {
        e aiModel = getAiModel();
        if (aiModel == null) {
            ReleaseLogUtil.d(TAG, "isSupportMtkOrQua aiModel is null");
            return false;
        }
        String str = Build.BRAND;
        String a2 = CommonUtil.a();
        LogUtil.a(TAG, "isSupportMtkOrQua brand ", str, " cpuPlatform ", a2, " getDeviceModel ", PhoneInfoUtils.getDeviceModel());
        boolean z = aiModel.f8553a;
        boolean contains = aiModel.b.contains(str);
        boolean contains2 = aiModel.j.contains(a2);
        ReleaseLogUtil.e(TAG, "isSupportMtkOrQua isSupportAllBrand ", Boolean.valueOf(z), " isSupportMtkBrand ", Boolean.valueOf(contains), " isSupportMtkCpu ", Boolean.valueOf(contains2));
        if ((z || contains) && contains2) {
            return true;
        }
        boolean contains3 = aiModel.g.contains(str);
        boolean contains4 = aiModel.f.contains(a2);
        ReleaseLogUtil.e(TAG, "isSupportMtkOrQua isSupportQuaBrand ", Boolean.valueOf(contains3), " isSupportQuaCpu ", Boolean.valueOf(contains4));
        return (z || contains3) && contains4;
    }

    private void initCloudConfigSetting() {
        String str;
        String d = drd.d("com.huawei.health_common_config", "SportField", "json");
        LogUtil.c(TAG, "the sportCommon filePath is: ", d);
        try {
            str = new JSONObject(CommonUtil.t(d)).getString(this.mAiModelConfigName);
        } catch (JSONException e2) {
            LogUtil.b(TAG, "the exception msg is: ", e2.getMessage());
            str = null;
        }
        Iterator it = moj.b(str, mws[].class).iterator();
        while (true) {
            if (it.hasNext()) {
                mws mwsVar = (mws) it.next();
                Integer a2 = mwsVar.a();
                int c = mwsVar.c();
                if (a2 != null) {
                    if (c == null) {
                        c = Integer.MAX_VALUE;
                    }
                    Integer num = c;
                    int d2 = CommonUtil.d(BaseApplication.getContext());
                    LogUtil.a(TAG, "currentVer: ", Integer.valueOf(d2), " minVer: ", a2, " maxVer: ", num);
                    if (d2 >= a2.intValue() && d2 < num.intValue() && dealCondition(mwsVar.e())) {
                        break;
                    }
                }
            } else {
                initDefaultConfig();
                break;
            }
        }
        LogUtil.a(TAG, " the QUA_CPU: ", getAiModel().f, " isAllBrand: ", Boolean.valueOf(getAiModel().f8553a), " QUA_BRAND: ", getAiModel().g, " MTK_CPU: ", getAiModel().j, " MTK_BRAND: ", getAiModel().b, " isAllShield: ", Boolean.valueOf(getAiModel().d), "DeviceModel: ", getAiModel().c);
    }

    private boolean dealCondition(mwp mwpVar) {
        e eVar = new e();
        if (mwpVar == null || (koq.b(mwpVar.d()) && koq.b(mwpVar.b()))) {
            modelTypeMap.put(this.mAiModelConfigName, eVar);
            return false;
        }
        if (koq.c(mwpVar.b())) {
            eVar.j.addAll(mwpVar.b());
        }
        if (koq.c(mwpVar.d())) {
            eVar.f.addAll(mwpVar.d());
        }
        if ("ALL".equals(mwpVar.c())) {
            eVar.f8553a = true;
        } else {
            eVar.f8553a = false;
            if (koq.c(mwpVar.a())) {
                eVar.g.addAll(mwpVar.a());
            }
            if (koq.c(mwpVar.e())) {
                eVar.b.addAll(mwpVar.e());
            }
        }
        eVar.d = SHIELD_ALL_BRAND_SWITCH.equals(mwpVar.f());
        if (koq.c(mwpVar.g())) {
            eVar.c.addAll(mwpVar.g());
        }
        modelTypeMap.put(this.mAiModelConfigName, eVar);
        return true;
    }

    protected e getAiModel() {
        if (!modelTypeMap.containsKey(this.mAiModelConfigName)) {
            modelTypeMap.put(this.mAiModelConfigName, new e());
        }
        return modelTypeMap.get(this.mAiModelConfigName);
    }

    public int getModelType() {
        if (!modelTypeMap.containsKey(this.mAiModelConfigName)) {
            LogUtil.h(TAG, this.mAiModelConfigName, " not exist, return not support");
            return -1;
        }
        int i = getAiModel().e;
        LogUtil.a(TAG, "the sModelType: ", Integer.valueOf(i));
        return i;
    }

    protected void setQuaSupportBrand(Set<String> set) {
        if (set != null) {
            getAiModel().g.addAll(set);
        }
    }

    protected void setQuaSupportCpuPlatform(Set<String> set) {
        if (set != null) {
            getAiModel().f.addAll(set);
        }
    }

    protected void setDefaultDeviceModel(Set<String> set) {
        if (set != null) {
            getAiModel().c = set;
        }
    }

    protected Set<String> getDeviceModel() {
        return getAiModel().c;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private boolean f8553a;
        private Set<String> b;
        private Set<String> c;
        private boolean d;
        private int e;
        private Set<String> f;
        private Set<String> g;
        private Set<String> j;

        private e() {
            this.e = -1;
            this.f8553a = false;
            this.d = false;
            this.g = new HashSet();
            this.b = new HashSet();
            this.f = new HashSet();
            this.j = new HashSet();
            this.c = new HashSet();
        }

        public boolean a() {
            return this.d;
        }
    }
}
