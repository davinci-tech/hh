package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.json.JsonSanitizer;
import com.huawei.exercise.modle.IExerciseAdviceCallback;
import com.huawei.framework.servicemgr.Consumer;
import com.huawei.health.algorithm.api.BreathTrainApi;
import com.huawei.health.algorithm.api.BreatheWearAppInterface;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwstressmgr.interfaces.StressWearAppInterface;
import com.huawei.linkage.sportlinkage.DataListener;
import com.huawei.linkage.sportlinkage.LinkagePlatformApi;
import com.huawei.pluginsport.helper.DeviceInterface;
import defpackage.dul;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dul {

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f11847a;
    private IBaseResponseCallback b;
    private List<IBaseResponseCallback> c;
    private IBaseResponseCallback d;
    private BreatheWearAppInterface e;
    private String f;
    private IExerciseAdviceCallback g;
    private DeviceInfo h;
    private IBaseResponseCallback i;
    private IBaseResponseCallback j;
    private IBaseResponseCallback k;
    private int l;
    private boolean m;
    private LinkagePlatformApi n;
    private IBaseResponseCallback o;
    private IBaseResponseCallback p;
    private IBaseResponseCallback q;
    private IBaseResponseCallback r;
    private IBaseResponseCallback s;
    private IBaseResponseCallback t;
    private StressWearAppInterface u;
    private DeviceInterface v;
    private IBaseResponseCallback x;

    public int c(int i) {
        if (i == 35) {
            return 5;
        }
        if (i == 36) {
            return 3;
        }
        if (i == 39) {
            return 7;
        }
        if (i == 74) {
            return 16;
        }
        switch (i) {
            case 21:
                return 0;
            case 22:
                return 1;
            case 23:
                return -2;
            default:
                switch (i) {
                    case 41:
                        return 8;
                    case 42:
                        return 13;
                    case 43:
                        return 11;
                    case 44:
                        return 12;
                    case 45:
                        return 14;
                    case 46:
                        return 10;
                    case 47:
                        return 15;
                    default:
                        return -1;
                }
        }
    }

    /* synthetic */ dul(AnonymousClass2 anonymousClass2) {
        this();
    }

    static class e {

        /* renamed from: a, reason: collision with root package name */
        private static final dul f11855a = new dul(null);
    }

    /* renamed from: dul$2, reason: invalid class name */
    class AnonymousClass2 implements IBaseResponseCallback {
        AnonymousClass2() {
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("HWhealthLinkage_", "registerRealTimeGuidanceCallback err_code is ", Integer.valueOf(i));
            if (obj == null) {
                LogUtil.b("HWhealthLinkage_", "registerRealTimeGuidanceCallback objData is null");
                return;
            }
            LogUtil.c("HWhealthLinkage_", "registerRealTimeGuidanceCallback objData is ", obj.toString());
            try {
                if (obj instanceof String) {
                    JSONObject jSONObject = new JSONObject((String) obj);
                    final int i2 = jSONObject.getInt("run_phrase_number");
                    JSONArray jSONArray = jSONObject.getJSONArray("run_phrase_variable");
                    final ArrayList arrayList = new ArrayList(10);
                    for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                        arrayList.add((Integer) jSONArray.get(i3));
                    }
                    LogUtil.c("HWhealthLinkage_", "mRealTimeGuidanceCallback number=", Integer.valueOf(i2), ", param=", arrayList);
                    Services.b("PluginFitnessAdvice", PluginSuggestion.class, BaseApplication.getContext(), new Consumer() { // from class: duo
                        @Override // com.huawei.framework.servicemgr.Consumer
                        public final void accept(Object obj2) {
                            ((PluginSuggestion) obj2).realTimeGuidance(i2, arrayList);
                        }
                    });
                }
            } catch (JSONException e) {
                LogUtil.h("HWhealthLinkage_", e.getMessage());
            }
        }
    }

    /* renamed from: dul$19, reason: invalid class name */
    class AnonymousClass19 implements DeviceInterface {
        AnonymousClass19() {
        }

        @Override // com.huawei.pluginsport.helper.DeviceInterface
        public void getOperator(final IBaseResponseCallback iBaseResponseCallback) {
            ReleaseLogUtil.e("HWhealthLinkage_", "DeviceInterface:isSporting");
            dwo.d().c(new IBaseResponseCallback() { // from class: dul.19.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "DeviceInterface:onResponse");
                    IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                    if (iBaseResponseCallback2 != null) {
                        iBaseResponseCallback2.d(i, obj);
                    }
                }
            });
        }

        @Override // com.huawei.pluginsport.helper.DeviceInterface
        public void getDeviceStepRateAlgorithmEnterprise(final IBaseResponseCallback iBaseResponseCallback) {
            dwo.d().a(new IBaseResponseCallback() { // from class: dur
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    dul.AnonymousClass19.b(IBaseResponseCallback.this, i, obj);
                }
            });
        }

        static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
            if (iBaseResponseCallback == null) {
                ReleaseLogUtil.e("HWhealthLinkage_", "getDeviceStepRateAlgorithmEnterprise callback is null");
            } else {
                iBaseResponseCallback.d(i, obj);
            }
        }

        @Override // com.huawei.pluginsport.helper.DeviceInterface
        public void setBoltWearStatusListener(String str, IBaseResponseCallback iBaseResponseCallback) {
            duk.a().d(str, iBaseResponseCallback);
        }

        @Override // com.huawei.pluginsport.helper.DeviceInterface
        public void unregBoltWearStatusListener(String str) {
            duk.a().c(str);
        }
    }

    private dul() {
        this.f = "";
        this.l = -2;
        this.t = new AnonymousClass2();
        this.r = new IBaseResponseCallback() { // from class: dul.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "notifyDeviceTrackStatusSwitchResult err_code ", Integer.valueOf(i));
                LogUtil.c("HWhealthLinkage_", "notifyDeviceTrackStatusSwitchResult objData ", obj);
            }
        };
        this.j = new IBaseResponseCallback() { // from class: dul.12
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "notifyDeviceStatusChanged err_code is ", Integer.valueOf(i));
                if (obj == null) {
                    LogUtil.b("HWhealthLinkage_", "notifyDeviceStatusChanged objData is null");
                    return;
                }
                ReleaseLogUtil.e("HWhealthLinkage_", "notifyDeviceStatusChanged objData is ", obj, Boolean.valueOf(dum.d().j()), Boolean.valueOf(dum.d().h()));
                if (i == 100000 && dum.d().j() && dum.d().h()) {
                    dul.this.b(obj);
                }
            }
        };
        this.k = new IBaseResponseCallback() { // from class: dul.11
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100000 && (obj instanceof JSONObject)) {
                    dug.c().d((JSONObject) obj, 50001);
                }
            }
        };
        this.o = new IBaseResponseCallback() { // from class: dul.13
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 100000) {
                    LogUtil.a("HWhealthLinkage_", "HealthTrackInteractor succeed to close heartrate");
                }
            }
        };
        this.p = new IBaseResponseCallback() { // from class: dul.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "mNotifyVoicePlayCallback err_code is ", Integer.valueOf(i));
                if (obj != null) {
                    LogUtil.c("HWhealthLinkage_", "mNotifyVoicePlayCallback objData is ", obj.toString());
                }
                if (i == 100000) {
                    int s = gso.e().s();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("result", s);
                    } catch (JSONException e2) {
                        LogUtil.h("HWhealthLinkage_", e2.getMessage());
                    }
                    dwo.d().e(jSONObject, (IBaseResponseCallback) null);
                }
            }
        };
        this.d = new IBaseResponseCallback() { // from class: dul.15
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "registerConnectionStatusChangeNotification err_code is ", Integer.valueOf(i));
                if (i != 100000 || obj == null) {
                    ReleaseLogUtil.d("HWhealthLinkage_", "registerConnectionStatusChangeNotification response error or objData is null");
                    return;
                }
                LogUtil.c("HWhealthLinkage_", "registerConnectionStatusChangeNotification objData is ", obj.toString());
                try {
                    if (obj instanceof String) {
                        JSONObject jSONObject = new JSONObject((String) obj);
                        if (cvt.c(jSONObject.getInt("product_type"))) {
                            return;
                        }
                        dul.this.d(jSONObject);
                    }
                } catch (JSONException e2) {
                    LogUtil.h("HWhealthLinkage_", e2.getMessage());
                }
            }
        };
        this.v = new AnonymousClass19();
        this.e = new BreatheWearAppInterface() { // from class: dul.17
            @Override // com.huawei.health.algorithm.api.BreatheWearAppInterface
            public void setHeartRateReportStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().b(jSONObject, iBaseResponseCallback);
            }

            @Override // com.huawei.health.algorithm.api.BreatheWearAppInterface
            public boolean getDeviceSupportBreatheReport() {
                DeviceInfo a2 = jpt.a("HWhealthLinkage_");
                DeviceCapability d = cvs.d();
                if (a2 == null || d == null) {
                    return false;
                }
                boolean isSupportPressAutoMonitor = d.isSupportPressAutoMonitor();
                ReleaseLogUtil.e("HWhealthLinkage_", "Adjust isSupportPressAutoMonitor() = ", Boolean.valueOf(isSupportPressAutoMonitor));
                return a2.getDeviceConnectState() == 2 && isSupportPressAutoMonitor;
            }

            @Override // com.huawei.health.algorithm.api.BreatheWearAppInterface
            public void registerNotificationPress(IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().g(iBaseResponseCallback);
            }

            @Override // com.huawei.health.algorithm.api.BreatheWearAppInterface
            public void unRegisterNotificationPress(IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().s(iBaseResponseCallback);
            }

            @Override // com.huawei.health.algorithm.api.BreatheWearAppInterface
            public String getDeviceUuid() {
                DeviceInfo a2 = jpt.a("HWhealthLinkage_");
                if (a2 == null) {
                    return "";
                }
                jpp.i(a2);
                return jpp.d();
            }
        };
        this.i = new IBaseResponseCallback() { // from class: dul.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj == null) {
                    if (i != 100000) {
                        dum.d().c(false);
                        ReleaseLogUtil.d("HWhealthLinkage_", "failed to open or close heartrate");
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.e("HWhealthLinkage_", "setHeartRateReportStatus err_code is ", Integer.valueOf(i));
                LogUtil.a("HWhealthLinkage_", "setHeartRateReportStatus heart rate is not null");
                if (i != 100000) {
                    dum.d().c(false);
                    LogUtil.h("HWhealthLinkage_", "failed to open or close hearrate");
                } else {
                    c(obj);
                }
            }

            private void c(Object obj) {
                if (obj instanceof String) {
                    try {
                        JSONArray jSONArray = new JSONArray((String) obj);
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            if (due.a().c() == 1 || duj.b().e() == 1 || CoachController.d().a() == 1) {
                                dul.this.f11847a.d(100000, jSONObject);
                            } else {
                                dul.this.b();
                            }
                        }
                    } catch (JSONException e2) {
                        LogUtil.b("HWhealthLinkage_", LogAnonymous.b((Throwable) e2));
                    }
                }
            }
        };
        this.u = new StressWearAppInterface() { // from class: dul.5
            @Override // com.huawei.hwstressmgr.interfaces.StressWearAppInterface
            public void setStressMeasureStatus(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().o(jSONObject, iBaseResponseCallback);
            }

            @Override // com.huawei.hwstressmgr.interfaces.StressWearAppInterface
            public boolean getDeviceSupportStressReport() {
                return dum.d().a();
            }
        };
        this.s = new IBaseResponseCallback() { // from class: dul.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "report RunPlan ETE result err_code=", Integer.valueOf(i));
                bnl.a().e(obj);
            }
        };
        this.x = new IBaseResponseCallback() { // from class: dul.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "report Workout result err_code=", Integer.valueOf(i));
                bnl.a().a(obj);
            }
        };
        this.g = new IExerciseAdviceCallback() { // from class: dul.9
            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void registerServiceConnectedListener(IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().m(iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void getDeviceETEState(IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().c(iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void registerConnectionStatusChangeNotification(IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().h(iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void getDeviceFitnessPlanParamter(IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().i(iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void pushFitnessPlan(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().j(jSONObject, iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public JSONObject getDeviceCapability() {
                return dwo.d().a();
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void setRunPlanReminderSwitch(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().f(jSONObject, iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public void setMetricUnit(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
                dwo.d().g(jSONObject, iBaseResponseCallback);
            }

            @Override // com.huawei.exercise.modle.IExerciseAdviceCallback
            public DeviceInfo getCurrentDeviceInfo() {
                return dwo.d().e();
            }
        };
        this.q = new IBaseResponseCallback() { // from class: dul.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HWhealthLinkage_", "mOpenAllSportDataCallback errorCode = ", Integer.valueOf(i));
                if (100000 != i) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "mOpenAllSportDataCallback failed to OPEN report");
                    return;
                }
                LogUtil.a("HWhealthLinkage_", "mOpenAllSportDataCallback succeed to OPEN report");
                dug.c().e(obj);
                dug.c().d(obj);
            }
        };
        this.c = new ArrayList(10);
        this.b = new IBaseResponseCallback() { // from class: dul.7
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                ReleaseLogUtil.e("HWhealthLinkage_", "mCloseAllSportDataCallback errorCode = ", Integer.valueOf(i));
            }
        };
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback != null) {
            this.c.add(iBaseResponseCallback);
        }
    }

    public static final dul c() {
        return e.f11855a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject) throws JSONException {
        if ("deviceDisconnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle deviceDisconnected");
            if (r() || "health_deviceInstantDisconnected".equals(this.f)) {
                return;
            }
            dum.d().c(false);
            q();
            return;
        }
        if ("deviceConnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle deviceConnected");
            s();
            return;
        }
        if ("deviceInstantDisconnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle deviceInstantDisconnected");
            if (r()) {
                return;
            }
            q();
            return;
        }
        if ("deviceInstantConnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle deviceInstantConnected");
            Iterator<IBaseResponseCallback> it = this.c.iterator();
            while (it.hasNext()) {
                it.next().d(4, new Object());
            }
            s();
            return;
        }
        if ("health_deviceDisconnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle health_deviceDisconnected");
            this.f = "health_deviceDisconnected";
            if (t() || r()) {
                return;
            }
            dum.d().c(false);
            q();
            return;
        }
        if ("health_deviceConnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle deviceConnected");
            this.f = "health_deviceConnected";
            s();
            return;
        }
        if ("health_deviceInstantDisconnected".equals(jSONObject.get("state"))) {
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle health_deviceInstantDisconnected");
            this.f = "health_deviceInstantDisconnected";
            if (t()) {
                return;
            }
            q();
            return;
        }
        if ("health_deviceInstantConnected".equals(jSONObject.get("state"))) {
            this.f = "health_deviceInstantConnected";
            ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle health_deviceInstantConnected");
            Iterator<IBaseResponseCallback> it2 = this.c.iterator();
            while (it2.hasNext()) {
                it2.next().d(4, new Object());
            }
            s();
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "WearAppInteractor handle other bt status");
    }

    public boolean k() {
        LogUtil.a("HWhealthLinkage_", "registerRealTimeGuidanceCallback");
        dwo.d().l(this.t);
        return false;
    }

    public boolean m() {
        LogUtil.a("HWhealthLinkage_", "registerVoicePlayCallback");
        dwo.d().n(this.p);
        return false;
    }

    public boolean h() {
        dwo.d().f(this.j);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Object obj) {
        try {
            if (obj instanceof String) {
                JSONObject jSONObject = new JSONObject((String) obj);
                int i = jSONObject.getInt("operator_type");
                int i2 = jSONObject.getInt(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE);
                ReleaseLogUtil.e("HWhealthLinkage_", "operator type:", Integer.valueOf(i), " workout type:", Integer.valueOf(i2));
                if (i2 != 279 && i2 != 137) {
                    b(i);
                }
                a(i);
            }
        } catch (JSONException e2) {
            LogUtil.h("HWhealthLinkage_", e2.getMessage());
        }
    }

    private void b(int i) {
        gso.e().c(1);
        if (i == 2) {
            w();
        } else if (i == 3) {
            y();
        } else {
            if (i != 4) {
                return;
            }
            v();
        }
    }

    private void a(int i) {
        if (i == 2) {
            p();
        } else if (i == 3) {
            u();
        } else {
            if (i != 4) {
                return;
            }
            x();
        }
    }

    private void v() {
        gso.e().b(1);
        kzc.n().v();
        if (gso.e().d((ISportDataCallback) null) == 0) {
            due.a().d(true);
            d(100000);
        } else {
            d(123002);
            dum.d().c(false);
        }
    }

    private void w() {
        if (gso.e().v() == 0) {
            due.a().d(true);
            d(100000);
        } else {
            d(123002);
            dum.d().c(false);
        }
    }

    private void y() {
        if (gso.e().y() == 0) {
            due.a().d(true);
            d(100000);
        } else {
            d(123002);
            dum.d().c(false);
        }
    }

    private void x() {
        CoachController.d().c(true);
        if (CoachController.d().c(CoachController.StatusSource.NEW_LINK_WEAR)) {
            d(100000);
            b();
            if (dum.d().f()) {
                a();
                return;
            }
            return;
        }
        d(123002);
        dum.d().c(false);
    }

    private void p() {
        if (CoachController.d().a(CoachController.StatusSource.NEW_LINK_WEAR)) {
            d(100000);
            b();
            if (dum.d().f()) {
                a();
                return;
            }
            return;
        }
        d(123002);
        dum.d().c(false);
    }

    private void u() {
        if (CoachController.d().d(CoachController.StatusSource.NEW_LINK_WEAR)) {
            d(100000);
            j();
            if (dum.d().f()) {
                e();
                return;
            }
            return;
        }
        d(123002);
        dum.d().c(false);
    }

    private void d(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("notification_status_response", i);
        } catch (JSONException e2) {
            LogUtil.h("HWhealthLinkage_", e2.getMessage());
        }
        dwo.d().h(jSONObject, this.r);
    }

    private boolean t() {
        List<DeviceInfo> j = dwo.d().j();
        boolean z = false;
        if (j != null && j.size() > 0) {
            for (DeviceInfo deviceInfo : j) {
                if (deviceInfo != null && 1 == deviceInfo.getDeviceActiveState() && deviceInfo.getDeviceConnectState() == 2) {
                    z = true;
                }
            }
        }
        LogUtil.a("HWhealthLinkage_", "isHuaweiWearDeviceConnected res:", Boolean.valueOf(z));
        return z;
    }

    private boolean r() {
        List<DeviceInfo> g = dwo.d().g();
        boolean z = false;
        if (g != null && g.size() > 0) {
            for (DeviceInfo deviceInfo : g) {
                if (deviceInfo != null && 1 == deviceInfo.getDeviceActiveState() && deviceInfo.getDeviceConnectState() == 2) {
                    z = true;
                }
            }
        }
        LogUtil.a("HWhealthLinkage_", "isHuaweiHealthDeviceConnected res:", Boolean.valueOf(z));
        return z;
    }

    public boolean f() {
        LogUtil.a("HWhealthLinkage_", "registerBluetoothStateListener enter");
        dwo.d().h(this.d);
        return false;
    }

    public void a(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        dwo.d().i(jSONObject, iBaseResponseCallback);
    }

    public void e(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (this.m) {
            this.n.sendDataToDevice(jSONObject, this.h);
        } else {
            dwo.d().a(jSONObject, iBaseResponseCallback);
        }
    }

    private void s() {
        dwm.b(true);
        dum.d().e(3);
        d(true);
        ReleaseLogUtil.e("HWhealthLinkage_", "--------------before add----------------", dum.d().b());
        dum.d().c();
        ReleaseLogUtil.e("HWhealthLinkage_", "--------------after add----------------", dum.d().b());
    }

    private void q() {
        dwm.b(false);
        dum.d().e(0);
        d(false);
        ReleaseLogUtil.e("HWhealthLinkage_", "--------------before delete----------------", dum.d().b());
        dub.a().c();
        ReleaseLogUtil.e("HWhealthLinkage_", "--------------after delete----------------", dum.d().b());
    }

    private void d(boolean z) {
        if (CoachController.d().i()) {
            CoachController.d().d(z);
        }
    }

    public void j() {
        int d = d(1, this.k);
        this.l = d;
        ReleaseLogUtil.e("HWhealthLinkage_", "mHeartRateSwitchStatus", Integer.valueOf(d));
    }

    public void i() {
        int b = b(1, this.k);
        this.l = b;
        ReleaseLogUtil.e("HWhealthLinkage_", "openSilentHeartRateReport mHeartRateSwitchStatus", Integer.valueOf(b));
    }

    public void b() {
        this.l = -2;
        d(2, this.o);
    }

    public int d() {
        return this.l;
    }

    public int d(int i, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_", "switchHeartRateReport, 1 means open while 2 means close, hearRateSwitch = ", Integer.valueOf(i));
        boolean h = dum.d().h();
        boolean j = dum.d().j();
        if (!dum.d().g()) {
            ReleaseLogUtil.d("HWhealthLinkage_", "current device not support heart rate report");
            return 0;
        }
        if (j && !h && i == 1) {
            ReleaseLogUtil.d("HWhealthLinkage_", "current status is not linking");
            sqo.w("current status is not linking");
            return 1;
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "switchHeartRateReport is linking");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_", LogAnonymous.b((Throwable) e2));
            sqo.w("hearRateSwitch is exception" + e2.getMessage());
        }
        this.f11847a = iBaseResponseCallback;
        dwo.d().b(jSONObject, this.i);
        return 2;
    }

    public int b(int i, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_", "switchHeartRateReport, 1 means open while 2 means close, hearRateSwitch = ", Integer.valueOf(i));
        if (!dum.d().g()) {
            ReleaseLogUtil.d("HWhealthLinkage_", "current device not support heart rate report");
            return 0;
        }
        ReleaseLogUtil.e("HWhealthLinkage_", "switchHeartRateReport is linking");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
        } catch (JSONException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_", e2.getMessage());
        }
        this.f11847a = iBaseResponseCallback;
        dwo.d().b(jSONObject, this.i);
        return 2;
    }

    public void e() {
        ReleaseLogUtil.e("HWhealthLinkage_", "openAllDataReport enter");
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HWhealthLinkage_");
        this.h = deviceInfo;
        boolean c = cwi.c(deviceInfo, FitnessSleepType.HW_FITNESS_WAKE);
        this.m = c;
        ReleaseLogUtil.e("HWhealthLinkage_", "isSupportReal is ", Boolean.valueOf(c));
        if (this.m) {
            e(1);
            b(this.h);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("operator_type", 1);
            dwo.d().c(jSONObject, this.q);
        } catch (JSONException e2) {
            ReleaseLogUtil.d("HWhealthLinkage_", "openAllDataReport JSONException = ", e2.getMessage());
        }
    }

    private void e(int i) {
        dwo.d().d(i, new IBaseResponseCallback() { // from class: dun
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                dul.c(i2, obj);
            }
        });
    }

    static /* synthetic */ void c(int i, Object obj) {
        LogUtil.a("HWhealthLinkage_", "mOpenAllSportDataCallback errorCode = ", Integer.valueOf(i));
        if (100000 != i) {
            ReleaseLogUtil.e("HWhealthLinkage_", "mOpenAllSportDataCallback failed to OPEN report");
        }
    }

    private void b(DeviceInfo deviceInfo) {
        LinkagePlatformApi c = lds.c();
        this.n = c;
        if (c == null) {
            ReleaseLogUtil.c("HWhealthLinkage_", "openOrCloseReport error linkagePlatformApi null");
        } else {
            final String securityUuid = deviceInfo.getSecurityUuid();
            this.n.registerRealDataListener(new DataListener() { // from class: duq
                @Override // com.huawei.linkage.sportlinkage.DataListener
                public final void onResponse(ldo ldoVar) {
                    dul.e(securityUuid, ldoVar);
                }
            });
        }
    }

    static /* synthetic */ void e(String str, ldo ldoVar) {
        kon e2 = dwk.e(str, ldoVar);
        dug.c().a(e2);
        dug.c().b(e2);
    }

    private void z() {
        LinkagePlatformApi c = lds.c();
        this.n = c;
        if (c == null) {
            ReleaseLogUtil.c("HWhealthLinkage_", "openOrCloseReport error linkagePlatformApi null");
        } else {
            c.unRegisterRealDataListener(new DataListener() { // from class: dup
                @Override // com.huawei.linkage.sportlinkage.DataListener
                public final void onResponse(ldo ldoVar) {
                    ReleaseLogUtil.e("HWhealthLinkage_", "unRegisterRealDataListener");
                }
            });
        }
    }

    public void a() {
        ReleaseLogUtil.e("HWhealthLinkage_", "closeAllDataReport enter");
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HWhealthLinkage_");
        this.h = deviceInfo;
        boolean c = cwi.c(deviceInfo, FitnessSleepType.HW_FITNESS_WAKE);
        this.m = c;
        ReleaseLogUtil.e("HWhealthLinkage_", "closeAllDataReport isSupportReal is ", Boolean.valueOf(c));
        if (this.m) {
            e(0);
            z();
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("operator_type", 0);
            dwo.d().c(jSONObject, this.b);
        } catch (JSONException e2) {
            ReleaseLogUtil.d("HWhealthLinkage_", "openAllDataReport JSONException = ", e2.getMessage());
            sqo.w("closeAllDataReport e" + e2.getMessage());
        }
    }

    public void l() {
        bnl.a().a(this.g);
    }

    public void n() {
        dwo.d().k(this.s);
        dwo.d().o(this.x);
    }

    public void o() {
        kxc.e().c(this.u);
        BreathTrainApi breathTrainApi = (BreathTrainApi) Services.a("BreathTrainService", BreathTrainApi.class);
        if (breathTrainApi == null) {
            LogUtil.h("WearAppInteractor", "brath train api is null");
        } else {
            breathTrainApi.registerBreatheCallback(this.e);
        }
    }

    public void g() {
        LogUtil.a("HWhealthLinkage_", "registerDeviceSportStateCallback");
        mwu.d().e(this.v);
    }

    public void e(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HWhealthLinkage_", "getHuaweiWearDeviceListForV2 enter, types:", str);
        List<Integer> b = b(str);
        ArrayList arrayList = new ArrayList(10);
        if (!CommonUtil.al(BaseApplication.getContext())) {
            List<DeviceInfo> h = dwo.d().h();
            if (h == null) {
                if (iBaseResponseCallback != null) {
                    iBaseResponseCallback.d(100001, null);
                    return;
                }
                return;
            }
            for (DeviceInfo deviceInfo : h) {
                if (deviceInfo != null) {
                    deviceInfo.setProductType(c(deviceInfo.getProductType()));
                    LogUtil.a("HWhealthLinkage_", "getHuaweiWearDeviceListForV2 dev type is:", Integer.valueOf(deviceInfo.getProductType()));
                    if (b.contains(Integer.valueOf(deviceInfo.getProductType()))) {
                        deviceInfo.setDeviceConnectState(3);
                        deviceInfo.setDeviceActiveState(0);
                        arrayList.add(deviceInfo);
                    }
                }
            }
            LogUtil.a("HWhealthLinkage_", "getHuaweiWearDeviceListForV2 dev size is:", Integer.valueOf(arrayList.size()));
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, arrayList);
                return;
            }
            return;
        }
        e(iBaseResponseCallback, b, arrayList);
    }

    private void e(final IBaseResponseCallback iBaseResponseCallback, final List<Integer> list, final List<DeviceInfo> list2) {
        dwo.d().b(new IBaseResponseCallback() { // from class: dul.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("HWhealthLinkage_", "getHuaweiWearDeviceListForV2 getDeviceListFromWear onResponse err_code:", Integer.valueOf(i));
                if (i == 0 && obj != null) {
                    for (com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo deviceInfo : (List) new Gson().fromJson(JsonSanitizer.sanitize((String) obj), new TypeToken<List<com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo>>() { // from class: dul.6.2
                    }.getType())) {
                        if (deviceInfo != null) {
                            deviceInfo.setProductType(dul.this.c(deviceInfo.getProductType()));
                            LogUtil.a("HWhealthLinkage_", "getHuaweiWearDeviceListForV2 dev type is:", Integer.valueOf(deviceInfo.getProductType()));
                            if (list.contains(Integer.valueOf(deviceInfo.getProductType()))) {
                                DeviceInfo convertToCommonDevice = deviceInfo.convertToCommonDevice();
                                convertToCommonDevice.setDeviceConnectState(3);
                                convertToCommonDevice.setDeviceActiveState(0);
                                list2.add(convertToCommonDevice);
                            }
                        }
                    }
                }
                LogUtil.a("HWhealthLinkage_", "getHuaweiWearDeviceListForV2 new version, devList size is:", Integer.valueOf(list2.size()));
                iBaseResponseCallback.d(0, list2);
            }
        });
    }

    public List<Integer> b(String str) {
        ArrayList arrayList = new ArrayList(10);
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            if (split.length == 0) {
                return arrayList;
            }
            try {
                for (String str2 : split) {
                    int parseInt = Integer.parseInt(str2);
                    arrayList.add(Integer.valueOf(parseInt));
                    LogUtil.a("HWhealthLinkage_", "unbind devicetype:", Integer.valueOf(parseInt));
                }
            } catch (NumberFormatException e2) {
                LogUtil.b("HWhealthLinkage_", "number exception,", e2.getMessage());
            }
        }
        return arrayList;
    }
}
