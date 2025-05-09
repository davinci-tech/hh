package defpackage;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartResponseWrapper;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartHttpCallback;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.SmartObserver;
import com.huawei.hwsmartinteractmgr.smarthttpmodel.smarthttpparser.SmartHttpBaseParser;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.connectivity.config.AUserProfile;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kwb {
    private static final Object c = new Object();
    private static final kwb d = new kwb();
    private AUserProfile j;
    private Map<Integer, List<SmartObserver>> b = new HashMap(5);
    private Map<Integer, SmartHttpBaseParser> e = new HashMap(5);

    /* renamed from: a, reason: collision with root package name */
    private Context f14646a = BaseApplication.getContext();

    private kwb() {
    }

    public static kwb d() {
        return d;
    }

    public void c(int i, Map<String, String> map, SmartHttpCallback smartHttpCallback) {
        AUserProfile t = meh.c(this.f14646a).t();
        this.j = t;
        b(i, t, map, smartHttpCallback);
    }

    public <R> void b(final int i, final AUserProfile aUserProfile, final Map<String, String> map, final SmartHttpCallback<R> smartHttpCallback) {
        LogUtil.a("SMART_SmartHttpUtil", "enter pullData():contentType =  ", Integer.valueOf(i));
        if (aUserProfile == null) {
            return;
        }
        if (Utils.o()) {
            LogUtil.a("SMART_SmartHttpUtil", "request, isNoCloudVersion, return");
            if (smartHttpCallback != null) {
                smartHttpCallback.onResponse(-1, null);
                return;
            }
            return;
        }
        if (i == 1) {
            LogUtil.a("SMART_SmartHttpUtil", "user label contentType = ", Integer.valueOf(i));
            SmartResponseWrapper smartResponseWrapper = new SmartResponseWrapper(0, "", i);
            smartResponseWrapper.setResponse(e());
            b(i, smartResponseWrapper);
            return;
        }
        GRSManager.a(this.f14646a).e(e(i), new GrsQueryCallback() { // from class: kwb.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.a("SMART_SmartHttpUtil", "GET KEY SUCCESS");
                String str2 = str + kwb.this.b(i);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                kwd.d(str2, kwb.this.c(aUserProfile, (Map<String, String>) map), kwb.this.d(i), new IBaseResponseCallback() { // from class: kwb.3.3
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i2, Object obj) {
                        LogUtil.a("SMART_SmartHttpUtil", "onFinished:resCode=", Integer.valueOf(i2), " contentType=", Integer.valueOf(i));
                        if (obj instanceof String) {
                            kwb.this.a(i2, (String) obj, i, smartHttpCallback);
                        } else {
                            smartHttpCallback.onResponse(-1, null);
                        }
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.a("SMART_SmartHttpUtil", "onCallBackFail errorCode = ", Integer.valueOf(i2));
            }
        });
    }

    private List<String> e() {
        LogUtil.a("SMART_SmartHttpUtil", "getAllLabels()");
        ArrayList arrayList = new ArrayList(5);
        List<String> allLabels = ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getAllLabels(LoginInit.getInstance(this.f14646a).getAccountInfo(1011));
        return koq.e((Object) allLabels, String.class) ? allLabels : arrayList;
    }

    private String e(int i) {
        if (i == 0) {
            return "activityUrl";
        }
        if (i == 1 || i == 2 || i == 3) {
            return "healthRecommendUrl";
        }
        LogUtil.a("SMART_SmartHttpUtil", "getUrl invalid contentType");
        return "";
    }

    public String b(int i) {
        if (i == 0) {
            return "/activity/getActivities";
        }
        if (i == 1) {
            return "/dataRecommend/label/getUserLabel";
        }
        if (i == 2) {
            return "/dataRecommend/goal/achieveGoal";
        }
        if (i == 3) {
            return "/dataRecommend/goal/getCompletionNum";
        }
        LogUtil.a("SMART_SmartHttpUtil", "getUrl invalid contentType");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <V> void a(int i, String str, int i2, SmartHttpCallback<V> smartHttpCallback) {
        if (i == 200) {
            SmartHttpBaseParser smartHttpBaseParser = this.e.get(Integer.valueOf(i2));
            if (smartHttpBaseParser == null) {
                LogUtil.b("SMART_SmartHttpUtil", "parser in null");
                return;
            }
            SmartResponseWrapper parse = smartHttpBaseParser.parse(str);
            LogUtil.c("SMART_SmartHttpUtil", "processData wrapper = ", parse.getResponse());
            if (smartHttpCallback != 0) {
                smartHttpCallback.onResponse(0, parse.getResponse());
            }
            b(i2, parse);
            return;
        }
        LogUtil.a("SMART_SmartHttpUtil", "smartCenter connection failed, errCode = ", Integer.valueOf(i));
        if (smartHttpCallback != 0) {
            smartHttpCallback.onResponse(-1, null);
        }
    }

    private void b(int i, final SmartResponseWrapper smartResponseWrapper) {
        synchronized (c) {
            List<SmartObserver> list = this.b.get(Integer.valueOf(i));
            if (list != null && !list.isEmpty()) {
                LogUtil.a("SMART_SmartHttpUtil", "notifyCorrespondSubscriber size is ", Integer.valueOf(list.size()));
                for (final SmartObserver smartObserver : list) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: kwb.2
                        @Override // java.lang.Runnable
                        public void run() {
                            smartObserver.onDataChanged(smartResponseWrapper);
                        }
                    });
                }
                return;
            }
            LogUtil.a("SMART_SmartHttpUtil", "subscriber ", Integer.valueOf(i), " is already unregitered or never register!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> d(int i) {
        HashMap<String, String> hashMap = new HashMap<>(5);
        hashMap.put("x-version", CommonUtil.c(this.f14646a));
        if (i != 0 || !TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008))) {
            hashMap.put("x-huid", KeyValDbManager.b(this.f14646a).e("user_id"));
        } else {
            LogUtil.h("SMART_SmartHttpUtil", "getHeaders mapHeaders isActivitiesInvalid");
        }
        LogUtil.c("SMART_SmartHttpUtil", "getHeaders mapHeaders:", hashMap.toString());
        return hashMap;
    }

    private HashMap<String, String> c(AUserProfile aUserProfile) {
        HashMap<String, String> hashMap = new HashMap<>(5);
        String str = "com.huawei.bone";
        if ("com.huawei.bone".equals(BaseApplication.getAppPackage())) {
            hashMap.put("appType", String.valueOf(2));
        } else {
            hashMap.put("appType", String.valueOf(1));
        }
        hashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        hashMap.put("tokenType", String.valueOf(ThirdLoginDataStorageUtil.getTokenTypeValue()));
        hashMap.put("token", b());
        if (LoginInit.getInstance(this.f14646a).isLoginedByWear()) {
            LogUtil.a("SMART_SmartHttpUtil", "callService appid wear logined");
        } else {
            LogUtil.a("SMART_SmartHttpUtil", "callService appid health logined");
            str = BaseApplication.getAppPackage();
        }
        hashMap.put("appId", str);
        hashMap.put("deviceType", String.valueOf(CommonUtil.h(this.f14646a)));
        String deviceId = LoginInit.getInstance(BaseApplication.getContext()).getDeviceId();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "clientnull";
        }
        hashMap.put("deviceId", deviceId);
        hashMap.put("sysVersion", Build.VERSION.RELEASE);
        hashMap.put("bindDeviceType", aUserProfile != null ? aUserProfile.getBindDeviceType() : "");
        hashMap.put("iVersion", String.valueOf(Utils.b()));
        hashMap.put("language", BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage());
        hashMap.put("environment", String.valueOf(CommonUtil.l(this.f14646a)));
        hashMap.put("upDeviceType", LoginInit.getInstance(BaseApplication.getContext()).getDeviceType());
        LogUtil.c("SMART_SmartHttpUtil", "getPublicParams->mapParams:", hashMap.toString());
        return hashMap;
    }

    private void d(HashMap<String, String> hashMap, Map<String, String> map) {
        if (hashMap == null || map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!TextUtils.isEmpty(key)) {
                hashMap.put(key, String.valueOf(value));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap<String, String> c(AUserProfile aUserProfile, Map<String, String> map) {
        HashMap<String, String> c2 = c(aUserProfile);
        d(c2, map);
        return c2;
    }

    public void a(int i, SmartObserver smartObserver) {
        synchronized (c) {
            List<SmartObserver> list = this.b.get(Integer.valueOf(i));
            if (list == null) {
                list = new ArrayList<>(5);
                this.b.put(Integer.valueOf(i), list);
            }
            if (smartObserver != null) {
                list.add(smartObserver);
            }
        }
    }

    public void d(int i, SmartObserver smartObserver) {
        synchronized (c) {
            List<SmartObserver> list = this.b.get(Integer.valueOf(i));
            if (list != null && smartObserver != null) {
                list.remove(smartObserver);
            }
        }
    }

    public <I> void d(int i, SmartHttpBaseParser<I> smartHttpBaseParser) {
        if (smartHttpBaseParser == null || this.e.containsKey(Integer.valueOf(i))) {
            return;
        }
        this.e.put(Integer.valueOf(i), smartHttpBaseParser);
    }

    public void c() {
        LogUtil.a("SMART_SmartHttpUtil", "release smartInteract resources");
        a();
        h();
    }

    private void h() {
        this.e.clear();
    }

    private void a() {
        synchronized (c) {
            Iterator<Map.Entry<Integer, List<SmartObserver>>> it = this.b.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().clear();
            }
            this.b.clear();
        }
    }

    private String b() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1008);
        if (TextUtils.isEmpty(accountInfo)) {
            return accountInfo;
        }
        try {
            return URLEncoder.encode(accountInfo, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("SMART_SmartHttpUtil", "token encode Exception ", e.toString());
            return accountInfo;
        }
    }
}
