package com.huawei.agconnect.remoteconfig.internal.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.huawei.agconnect.AGConnectInstance;
import com.huawei.agconnect.common.api.AGCInstanceID;
import com.huawei.agconnect.common.api.BackendService;
import com.huawei.agconnect.common.api.CPUModelUtil;
import com.huawei.agconnect.common.api.Logger;
import com.huawei.agconnect.common.api.RequestThrottle;
import com.huawei.agconnect.common.appinfo.SafeAppInfo;
import com.huawei.agconnect.exception.AGCServerException;
import com.huawei.agconnect.remoteconfig.AGCConfigException;
import com.huawei.agconnect.remoteconfig.AGConnectConfig;
import com.huawei.agconnect.remoteconfig.internal.ConfigContainer;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.TaskExecutors;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public final class c {
    private final BackendService.Options options;
    private final RequestThrottle.Throttle throttle;

    public Task<ConfigContainer> getConfigFromRemote(String str, Map<String, String> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        buildRequest(str, map).addOnSuccessListener(new OnSuccessListener<d>() { // from class: com.huawei.agconnect.remoteconfig.internal.a.c.1
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(d dVar) {
                BackendService.sendRequest(dVar, 1, e.class, c.this.options).addOnSuccessListener(TaskExecutors.immediate(), new OnSuccessListener<e>() { // from class: com.huawei.agconnect.remoteconfig.internal.a.c.1.2
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    public void onSuccess(e eVar) {
                        if (eVar.isSuccess()) {
                            taskCompletionSource.setResult(new ConfigContainer(eVar.getParameters(), eVar.getExperiments(), eVar.getTag()));
                        } else {
                            taskCompletionSource.setException(new AGCConfigException(eVar.getRet().getMsg(), eVar.getRet().getCode()));
                        }
                    }
                }).addOnFailureListener(TaskExecutors.immediate(), new OnFailureListener() { // from class: com.huawei.agconnect.remoteconfig.internal.a.c.1.1
                    @Override // com.huawei.hmf.tasks.OnFailureListener
                    public void onFailure(Exception exc) {
                        if (exc instanceof AGCServerException) {
                            AGCServerException aGCServerException = (AGCServerException) exc;
                            if (1 == aGCServerException.getCode()) {
                                taskCompletionSource.setException(new AGCConfigException(aGCServerException.getErrMsg(), 1, c.this.throttle.getEndTime()));
                                return;
                            }
                        }
                        taskCompletionSource.setException(exc);
                    }
                });
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Map<String, String>> getUserProperties(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            HashMap hashMap = new HashMap(10);
            hashMap.put(MedalConstants.EVENT_KEY, entry.getKey());
            hashMap.put("value", entry.getValue());
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private Task<d> buildRequest(final String str, final Map<String, String> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        com.huawei.agconnect.remoteconfig.internal.b.a().a(false).addOnSuccessListener(new OnSuccessListener<Map<String, String>>() { // from class: com.huawei.agconnect.remoteconfig.internal.a.c.2
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public void onSuccess(Map<String, String> map2) {
                String str2;
                Context context = AGConnectInstance.getInstance().getContext();
                d dVar = new d();
                dVar.setTag(str);
                dVar.setVersionName(c.this.appVersion(context));
                try {
                    str2 = ",HarmonyOS " + ((String) Class.forName("ohos.system.version.SystemVersion").getDeclaredMethod("getVersion", new Class[0]).invoke(null, new Object[0]));
                } catch (Throwable unused) {
                    Logger.w("RemoteConfig", "not find package ohos.system.version.SystemVersion");
                    str2 = "";
                }
                dVar.setPlatformVersion("Android " + Build.VERSION.RELEASE + str2);
                Locale locale = context.getResources().getConfiguration().getLocales().get(0);
                dVar.setLanguage(locale.getLanguage());
                dVar.setScript(locale.getScript());
                dVar.setCountry(locale.getCountry());
                dVar.setDateTime(System.currentTimeMillis());
                dVar.setAaId(AGCInstanceID.getInstance(context).getId());
                dVar.setCustomProperties(c.this.getUserProperties(map));
                dVar.setUserProperties(AGConnectConfig.getInstance().getEnableCollectUserPrivacy() ? c.this.getUserProperties(map2) : null);
                dVar.setDeviceChip(AGConnectConfig.getInstance().getEnableCollectUserPrivacy() ? CPUModelUtil.getCpuModel() : null);
                taskCompletionSource.setResult(dVar);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String appVersion(Context context) {
        PackageInfo safeGetPackageInfo = SafeAppInfo.safeGetPackageInfo(context.getPackageManager(), context.getPackageName(), 16384);
        if (safeGetPackageInfo != null) {
            return safeGetPackageInfo.versionName;
        }
        return null;
    }

    public c(AGConnectInstance aGConnectInstance) {
        RequestThrottle.Throttle throttle = RequestThrottle.getInstance().get("RemoteConfig-Fetch");
        this.throttle = throttle;
        this.options = new BackendService.Options.Builder().apiKey(true).throttle(throttle).app(aGConnectInstance).build();
    }
}
