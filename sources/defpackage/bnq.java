package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.SystemClock;
import com.huawei.appgallery.agd.api.AgdApiClient;
import com.huawei.appgallery.agd.api.ApiStatusCodes;
import com.huawei.appgallery.agd.api.ConnectionResult;
import com.huawei.appgallery.agd.api.OpenDetailResult;
import com.huawei.appgallery.agd.api.ResultCallback;
import com.huawei.appgallery.agd.api.ServiceInfo;
import com.huawei.appmarket.framework.coreservice.Status;
import com.huawei.appmarket.service.externalservice.distribution.opendetail.ModuleInfo;
import com.huawei.appmarket.service.externalservice.distribution.opendetail.OpenFADetailRequest;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.facardagds.FaCardAgdsApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import defpackage.bnq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiDefine(uri = FaCardAgdsApi.class)
@Singleton
/* loaded from: classes3.dex */
public class bnq implements FaCardAgdsApi {
    private static final Map<String, String> c = Collections.unmodifiableMap(new HashMap<String, String>() { // from class: bnq.4
        private static final long serialVersionUID = -6952984575158174724L;

        {
            put("1*2", "1x2");
            put("2*2", "2x2");
            put("2*4", "2x4");
            put("4*4", "4x4");
        }
    });
    private static final Map<String, Integer> e = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: bnq.1
        private static final long serialVersionUID = 2665717746699023356L;

        {
            put(FaCardAgdsApi.SLEEP_FA_CARD, 2);
            put(FaCardAgdsApi.DIET_FA_CARD, 4);
            put(FaCardAgdsApi.SPORT_FA_CARD, 3);
        }
    });
    private AgdApiClient d;

    private int c(int i) {
        if (i == 1) {
            return 3;
        }
        if (i == 2 || i == 3) {
            return 4;
        }
        if (i != 4) {
            return i != 5 ? 0 : 2;
        }
        return 1;
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public int getResultcodeAgreeProtocol() {
        return 1001;
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public int getResultcodeNotAgreeProtocol() {
        return 1002;
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public void open(int i, String str, FaCardAgdsApi.OpenAgdsResultCallback openAgdsResultCallback) {
        ReleaseLogUtil.e("R_FaCardAgdsImpl", "open requestCode: ", Integer.valueOf(i), ", faCardModuleName: ", str);
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "open fail , activity is null");
            return;
        }
        AgdApiClient build = new AgdApiClient.Builder(activity).addConnectionCallbacks(new b(i, str, openAgdsResultCallback)).build();
        this.d = build;
        build.connect();
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public void close() {
        AgdApiClient agdApiClient = this.d;
        if (agdApiClient != null) {
            agdApiClient.disconnect();
        }
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public int getLoadResultCode(Intent intent) {
        OpenDetailResult from = OpenDetailResult.from(intent);
        if (from == null) {
            return -1;
        }
        int loadResult = from.getLoadResult();
        ReleaseLogUtil.e("R_FaCardAgdsImpl", "loadResult: ", Integer.valueOf(loadResult), "loadResultDesc:", from.getLoadResultDesc());
        return loadResult;
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public int faQuickServicesBiEvent(int i, int i2, int i3, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("click", 1);
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            hashMap.put("from", Integer.valueOf(i2));
        }
        if (i == 1 || i == 2 || i == 3) {
            hashMap.put("card_id", Integer.valueOf(i3));
        }
        if (i == 2 || i == 3) {
            hashMap.put("card_type", str);
        }
        return ixx.d().d(BaseApplication.getContext(), AnalyticsValue.FA_QUICK_SERVICES_EVENT_VALUE.value(), hashMap, 0);
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public int faQuickServicesBiEvent(int i, int i2) {
        return faQuickServicesBiEvent(i, i2, 0, "");
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public int faQuickServicesBiEvent(int i, int i2, int i3) {
        return faQuickServicesBiEvent(i, i2, i3, "");
    }

    @Override // com.huawei.health.facardagds.FaCardAgdsApi
    public void addToDeskTopBi(Intent intent, int i) {
        List<ModuleInfo> modules;
        OpenDetailResult from = OpenDetailResult.from(intent);
        if (from == null || (modules = from.getModules()) == null) {
            return;
        }
        for (ModuleInfo moduleInfo : modules) {
            String moduleName = moduleInfo.getModuleName();
            String formName = moduleInfo.getFormName();
            String dimension = moduleInfo.getDimension();
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "moduleName: ", moduleName, ", formName:", formName, ", dimension: ", dimension);
            String a2 = a(dimension);
            int b2 = b(moduleName, formName);
            if (moduleInfo.isAddedToDeskTop()) {
                ReleaseLogUtil.e("R_FaCardAgdsImpl", "addedToDeskTop isBiEvent20401301: ", Integer.valueOf(faQuickServicesBiEvent(3, i, b2, a2)), ", isBiEvent2040129: ", Integer.valueOf(c(b2, a2, c(i), 2)));
            }
            if (moduleInfo.isAddedToHiBoard()) {
                ReleaseLogUtil.e("R_FaCardAgdsImpl", "addedToHiBoard isBiEvent20401301: ", Integer.valueOf(faQuickServicesBiEvent(2, i, b2, a2)), ", isBiEvent2040129: ", Integer.valueOf(c(b2, a2, c(i), 1)));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static ServiceInfo b(String str) {
        char c2;
        List<ServiceInfo.FormInfo> arrayList = new ArrayList<>();
        List<String> arrayList2 = new ArrayList<>();
        str.hashCode();
        switch (str.hashCode()) {
            case -461441217:
                if (str.equals(FaCardAgdsApi.DIET_FA_CARD)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -88706369:
                if (str.equals(FaCardAgdsApi.SPORT_FA_CARD)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1155277794:
                if (str.equals(FaCardAgdsApi.SLEEP_FA_CARD)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1544803905:
                if (str.equals("default")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            arrayList = d();
            arrayList2 = Collections.singletonList(str);
        } else if (c2 == 1) {
            arrayList = c();
            arrayList2 = Collections.singletonList(str);
        } else if (c2 == 2) {
            arrayList = a();
            arrayList2 = Collections.singletonList(str);
        } else if (c2 == 3) {
            arrayList.addAll(a());
            arrayList.addAll(c());
            arrayList.addAll(d());
            arrayList2 = Arrays.asList(FaCardAgdsApi.SLEEP_FA_CARD, FaCardAgdsApi.SPORT_FA_CARD, FaCardAgdsApi.DIET_FA_CARD);
        } else {
            ReleaseLogUtil.d("R_FaCardAgdsImpl", "getServiceInfo constructed empty serviceInfo");
        }
        ServiceInfo serviceInfo = new ServiceInfo("com.huawei.ohos.healthservice");
        if (koq.c(arrayList)) {
            serviceInfo.setFormInfos(arrayList);
        }
        if (koq.c(arrayList2)) {
            serviceInfo.setModuleNames(arrayList2);
        }
        return serviceInfo;
    }

    private static List<ServiceInfo.FormInfo> a() {
        ServiceInfo.FormInfo formInfo = new ServiceInfo.FormInfo(FaCardAgdsApi.SLEEP_FA_CARD, "sleep_card_medium");
        ServiceInfo.FormInfo formInfo2 = new ServiceInfo.FormInfo(FaCardAgdsApi.SLEEP_FA_CARD, "sleep_data_mini");
        ServiceInfo.FormInfo formInfo3 = new ServiceInfo.FormInfo(FaCardAgdsApi.SLEEP_FA_CARD, "sleep_music_mini");
        ArrayList arrayList = new ArrayList();
        arrayList.add(formInfo);
        arrayList.add(formInfo2);
        arrayList.add(formInfo3);
        return arrayList;
    }

    private static List<ServiceInfo.FormInfo> d() {
        ServiceInfo.FormInfo formInfo = new ServiceInfo.FormInfo(FaCardAgdsApi.DIET_FA_CARD, "diet_diary_medium");
        ServiceInfo.FormInfo formInfo2 = new ServiceInfo.FormInfo(FaCardAgdsApi.DIET_FA_CARD, "diet_diary_mini");
        ArrayList arrayList = new ArrayList();
        arrayList.add(formInfo);
        arrayList.add(formInfo2);
        return arrayList;
    }

    private static List<ServiceInfo.FormInfo> c() {
        ServiceInfo.FormInfo formInfo = new ServiceInfo.FormInfo(FaCardAgdsApi.SPORT_FA_CARD, "fitness_courses_medium");
        ArrayList arrayList = new ArrayList();
        arrayList.add(formInfo);
        return arrayList;
    }

    private static String a(String str) {
        String str2 = c.get(str);
        return str2 == null ? "" : str2;
    }

    private int c(int i, String str, int i2, int i3) {
        HashMap hashMap = new HashMap();
        hashMap.put("card_id", Integer.valueOf(i));
        hashMap.put("card_type", str);
        hashMap.put("from", Integer.valueOf(i2));
        hashMap.put("click", 1);
        hashMap.put("setPage", Integer.valueOf(i3));
        return ixx.d().d(BaseApplication.getContext(), AnalyticsValue.GREATE_FA_CARD_TYPE_EVENT_VALUE.value(), hashMap, 0);
    }

    private int b(String str, String str2) {
        Map<String, Integer> map = e;
        if (!map.containsKey(str) || map.get(str) == null) {
            return 2;
        }
        if (str2.equals("sleep_data_mini")) {
            return 8;
        }
        return map.get(str).intValue();
    }

    class b implements AgdApiClient.ConnectionCallbacks {
        private final long b = SystemClock.elapsedRealtime();
        private final FaCardAgdsApi.OpenAgdsResultCallback c;
        private final int d;
        private final String e;

        b(int i, String str, FaCardAgdsApi.OpenAgdsResultCallback openAgdsResultCallback) {
            this.d = i;
            this.e = str;
            this.c = openAgdsResultCallback;
        }

        @Override // com.huawei.appgallery.agd.api.AgdApiClient.ConnectionCallbacks
        public void onConnected() {
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected Success, duration=", Long.valueOf(e()));
            c();
        }

        @Override // com.huawei.appgallery.agd.api.AgdApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnectionSuspended cause: ", Integer.valueOf(i), ", totalDuration=", Long.valueOf(e()));
            this.c.onResponse(1000);
        }

        @Override // com.huawei.appgallery.agd.api.AgdApiClient.ConnectionCallbacks
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Activity activity;
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnectionFailed ", connectionResult.getErrorMessage(), ", duration=", Long.valueOf(e()));
            this.c.onResponse(1001);
            try {
                activity = BaseApplication.getActivity();
            } catch (IntentSender.SendIntentException e) {
                ReleaseLogUtil.c("R_FaCardAgdsImpl", "onConnectionFailed error: ", ExceptionUtils.d(e));
            }
            if (activity == null) {
                ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnectionFailed startResolutionForResult fail 101, activity is null, totalDuration=", Long.valueOf(e()));
            } else {
                connectionResult.startResolutionForResult(activity, 101);
                ReleaseLogUtil.c("R_FaCardAgdsImpl", "onConnectionFailed end, totalDuration=", Long.valueOf(e()));
            }
        }

        private long e() {
            return SystemClock.elapsedRealtime() - this.b;
        }

        private void c() {
            new OpenFADetailRequest.Builder(bnq.this.d).serviceInfo(bnq.b(this.e)).callback(new ResultCallback() { // from class: bnt
                @Override // com.huawei.appgallery.agd.api.ResultCallback
                public final void onResult(Status status) {
                    bnq.b.this.b(status);
                }
            }).send();
        }

        /* synthetic */ void b(Status status) {
            Activity activity;
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected onStatus: ", ApiStatusCodes.getStatusCodeString(status.getStatusCode()), ", duration=", Long.valueOf(e()));
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected Code: ", Integer.valueOf(status.getStatusCode()));
            int statusCode = status.getStatusCode();
            if (statusCode == 6) {
                try {
                    this.c.onResponse(0);
                    activity = BaseApplication.getActivity();
                } catch (IntentSender.SendIntentException e) {
                    this.c.onResponse(1002);
                    ReleaseLogUtil.c("R_FaCardAgdsImpl", "onConnected startResolution error:", ExceptionUtils.d(e));
                }
                if (activity == null) {
                    ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected startResolutionForResult fail 6, activity is null, totalDuration=", Long.valueOf(e()));
                    return;
                } else {
                    status.startResolutionForResult(activity, this.d);
                    ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected startResolution end, totalDuration=", Long.valueOf(e()));
                    return;
                }
            }
            if (statusCode == 15 || statusCode == 2) {
                try {
                    Activity activity2 = BaseApplication.getActivity();
                    if (activity2 == null) {
                        ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected startResolutionForResult fail 100, activity is null, totalDuration=", Long.valueOf(e()));
                        return;
                    }
                    status.startResolutionForResult(activity2, 100);
                } catch (IntentSender.SendIntentException e2) {
                    ReleaseLogUtil.c("R_FaCardAgdsImpl", "onConnected startResolution error:", ExceptionUtils.d(e2));
                }
            }
            this.c.onResponse(1002);
            ReleaseLogUtil.e("R_FaCardAgdsImpl", "onConnected startResolution end, totalDuration=", Long.valueOf(e()));
        }
    }
}
