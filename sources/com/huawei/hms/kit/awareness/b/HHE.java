package com.huawei.hms.kit.awareness.b;

import android.content.Context;
import android.os.Build;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;
import com.huawei.hms.kit.awareness.BarrierClient;
import com.huawei.hms.kit.awareness.CaptureClient;
import com.huawei.hms.kit.awareness.DonateClient;
import com.huawei.hms.kit.awareness.a.a.r;
import com.huawei.hms.kit.awareness.a.a.s;
import com.huawei.hms.kit.awareness.a.a.t;
import com.huawei.hms.kit.awareness.a.a.u;
import com.huawei.hms.kit.awareness.b.c;
import com.huawei.hms.kit.awareness.barrier.BarrierQueryRequest;
import com.huawei.hms.kit.awareness.barrier.BarrierQueryResponse;
import com.huawei.hms.kit.awareness.barrier.BarrierUpdateRequest;
import com.huawei.hms.kit.awareness.barrier.internal.f;
import com.huawei.hms.kit.awareness.capture.AmbientLightResponse;
import com.huawei.hms.kit.awareness.capture.ApplicationStatusResponse;
import com.huawei.hms.kit.awareness.capture.BeaconStatusResponse;
import com.huawei.hms.kit.awareness.capture.BehaviorResponse;
import com.huawei.hms.kit.awareness.capture.BluetoothStatusResponse;
import com.huawei.hms.kit.awareness.capture.CapabilityResponse;
import com.huawei.hms.kit.awareness.capture.DarkModeStatusResponse;
import com.huawei.hms.kit.awareness.capture.HeadsetStatusResponse;
import com.huawei.hms.kit.awareness.capture.LocationResponse;
import com.huawei.hms.kit.awareness.capture.ScreenStatusResponse;
import com.huawei.hms.kit.awareness.capture.TimeCategoriesResponse;
import com.huawei.hms.kit.awareness.capture.WeatherPosition;
import com.huawei.hms.kit.awareness.capture.WeatherStatusResponse;
import com.huawei.hms.kit.awareness.capture.WifiStatusResponse;
import com.huawei.hms.kit.awareness.capture.internal.SidInternalResult;
import com.huawei.hms.kit.awareness.donate.DonateRequest;
import com.huawei.hms.kit.awareness.donate.DonateResponse;
import com.huawei.hms.kit.awareness.donate.ServiceOpenIdResponse;
import com.huawei.hms.kit.awareness.donate.message.Content;
import com.huawei.hms.kit.awareness.donate.message.ContentData;
import com.huawei.hms.kit.awareness.donate.message.InsightIntent;
import com.huawei.hms.kit.awareness.donate.message.Message;
import com.huawei.hms.kit.awareness.donate.message.Session;
import com.huawei.hms.kit.awareness.internal.communication.Constants;
import com.huawei.hms.kit.awareness.quickapp.SubAppInfo;
import com.huawei.hms.kit.awareness.status.BeaconStatus;
import com.huawei.hms.kit.awareness.status.ServiceOpenIdStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class HHE implements BarrierClient, CaptureClient, DonateClient {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4813a = "AwarenessClientImpl";
    private static final String b = "AwarenessBinder is null, bind service failed.";
    private static final String c = "call %s method occurred remoteException.";
    private static final int d = 24;
    private static final int e = 100;
    private static final String f = "shareIntent";
    private static final String g = "deleteIntent";
    private static final String h = "deleteEntity";
    private static final String i = "getServiceOpenId";
    private static final String j = "3.1";
    private static final String k = "identifiers";
    private static final String l = "entityIds";
    private static final String m = "namespace";
    private static final String n = "name";
    private static final String o = "intent";
    private static final String p = "identifier";
    private static final String q = "intentVersion";
    private static final String r = "intentActionInfo";
    private static final String s = "intentEntityInfo";
    private static final String t = "intentServiceInfo";
    private static final String u = "renew";
    private static final String v = "reason loss";
    private static volatile HHE w;
    private final HHF x;
    private final Context y;
    private boolean z = false;

    @Override // com.huawei.hms.kit.awareness.BarrierClient
    public Task<Void> updateBarriers(final BarrierUpdateRequest barrierUpdateRequest, final boolean z) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda5
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void a2;
                a2 = HHE.this.a(barrierUpdateRequest, z);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.BarrierClient
    public Task<Void> updateBarriers(BarrierUpdateRequest barrierUpdateRequest) {
        return updateBarriers(barrierUpdateRequest, false);
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    public Task<DonateResponse> shareIntent(final List<InsightIntent> list) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda10
            @Override // java.util.concurrent.Callable
            public final Object call() {
                DonateResponse c2;
                c2 = HHE.this.c(list);
                return c2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.Client
    public void setSubAppInfo(SubAppInfo subAppInfo) {
        this.x.a(subAppInfo);
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    public Task<DonateResponse> sendMessage(final Message message) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda24
            @Override // java.util.concurrent.Callable
            public final Object call() {
                DonateResponse b2;
                b2 = HHE.this.b(message);
                return b2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<CapabilityResponse> querySupportingCapabilities() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda22
            @Override // java.util.concurrent.Callable
            public final Object call() {
                CapabilityResponse e2;
                e2 = HHE.this.e();
                return e2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.BarrierClient
    public Task<BarrierQueryResponse> queryBarriers(final BarrierQueryRequest barrierQueryRequest) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda16
            @Override // java.util.concurrent.Callable
            public final Object call() {
                BarrierQueryResponse a2;
                a2 = HHE.this.a(barrierQueryRequest);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<WifiStatusResponse> getWifiStatus() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda6
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WifiStatusResponse c2;
                c2 = HHE.this.c();
                return c2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<WeatherStatusResponse> getWeatherByPosition(final WeatherPosition weatherPosition) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WeatherStatusResponse a2;
                a2 = HHE.this.a(weatherPosition);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<WeatherStatusResponse> getWeatherByIP() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda7
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WeatherStatusResponse f2;
                f2 = HHE.this.f();
                return f2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<WeatherStatusResponse> getWeatherByDevice(final String str) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                WeatherStatusResponse c2;
                c2 = HHE.this.c(str);
                return c2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<WeatherStatusResponse> getWeatherByDevice() {
        return getWeatherByDevice(null);
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<TimeCategoriesResponse> getTimeCategoriesForFuture(final long j2) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda25
            @Override // java.util.concurrent.Callable
            public final Object call() {
                TimeCategoriesResponse a2;
                a2 = HHE.this.a(j2);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<TimeCategoriesResponse> getTimeCategoriesByUser(final double d2, final double d3) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                TimeCategoriesResponse a2;
                a2 = HHE.this.a(d2, d3);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<TimeCategoriesResponse> getTimeCategoriesByIP() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                TimeCategoriesResponse j2;
                j2 = HHE.this.j();
                return j2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<TimeCategoriesResponse> getTimeCategoriesByCountryCode(final String str) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda15
            @Override // java.util.concurrent.Callable
            public final Object call() {
                TimeCategoriesResponse d2;
                d2 = HHE.this.d(str);
                return d2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<TimeCategoriesResponse> getTimeCategories() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda23
            @Override // java.util.concurrent.Callable
            public final Object call() {
                TimeCategoriesResponse k2;
                k2 = HHE.this.k();
                return k2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    public Task<ServiceOpenIdResponse> getServiceOpenId(final boolean z) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda31
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ServiceOpenIdResponse c2;
                c2 = HHE.this.c(z);
                return c2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<ScreenStatusResponse> getScreenStatus() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda26
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ScreenStatusResponse d2;
                d2 = HHE.this.d();
                return d2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<LocationResponse> getLocation() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda14
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LocationResponse i2;
                i2 = HHE.this.i();
                return i2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<AmbientLightResponse> getLightIntensity() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda3
            @Override // java.util.concurrent.Callable
            public final Object call() {
                AmbientLightResponse g2;
                g2 = HHE.this.g();
                return g2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<HeadsetStatusResponse> getHeadsetStatus() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda27
            @Override // java.util.concurrent.Callable
            public final Object call() {
                HeadsetStatusResponse m2;
                m2 = HHE.this.m();
                return m2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<DarkModeStatusResponse> getDarkModeStatus() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                DarkModeStatusResponse b2;
                b2 = HHE.this.b();
                return b2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<LocationResponse> getCurrentLocation() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda11
            @Override // java.util.concurrent.Callable
            public final Object call() {
                LocationResponse h2;
                h2 = HHE.this.h();
                return h2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<BluetoothStatusResponse> getBluetoothStatus(final int i2) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda9
            @Override // java.util.concurrent.Callable
            public final Object call() {
                BluetoothStatusResponse a2;
                a2 = HHE.this.a(i2);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<BehaviorResponse> getBehavior() {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda30
            @Override // java.util.concurrent.Callable
            public final Object call() {
                BehaviorResponse l2;
                l2 = HHE.this.l();
                return l2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<BeaconStatusResponse> getBeaconStatus(BeaconStatus.Filter... filterArr) {
        return getBeaconStatus(Arrays.asList(filterArr));
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<BeaconStatusResponse> getBeaconStatus(final Collection<BeaconStatus.Filter> collection) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda21
            @Override // java.util.concurrent.Callable
            public final Object call() {
                BeaconStatusResponse a2;
                a2 = HHE.this.a(collection);
                return a2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.CaptureClient
    public Task<ApplicationStatusResponse> getApplicationStatus(final String str) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda13
            @Override // java.util.concurrent.Callable
            public final Object call() {
                ApplicationStatusResponse b2;
                b2 = HHE.this.b(str);
                return b2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.Client
    public void enableUpdateWindow(boolean z) {
        this.x.a(z);
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    public Task<DonateResponse> deleteIntent(final String str, final String[] strArr) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda12
            @Override // java.util.concurrent.Callable
            public final Object call() {
                DonateResponse d2;
                d2 = HHE.this.d(str, strArr);
                return d2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    public Task<DonateResponse> deleteIntent(String str) {
        return deleteIntent(str, new String[0]);
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    public Task<DonateResponse> deleteEntity(final String str, final String[] strArr) {
        return Tasks.callInBackground(new Callable() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda29
            @Override // java.util.concurrent.Callable
            public final Object call() {
                DonateResponse c2;
                c2 = HHE.this.c(str, strArr);
                return c2;
            }
        });
    }

    @Override // com.huawei.hms.kit.awareness.DonateClient
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HHE setAgreePrivacy(boolean z) {
        this.z = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ HeadsetStatusResponse m() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.e) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.w, "getHeadsetStatus", 24, com.huawei.hms.kit.awareness.a.a.e.class, 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BehaviorResponse l() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.d) a(202, "queryBehaviorResult", 28, com.huawei.hms.kit.awareness.a.a.d.class, 9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ TimeCategoriesResponse k() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.i) a(200, "getTimeCategories", 24, com.huawei.hms.kit.awareness.a.a.i.class, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ TimeCategoriesResponse j() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.i) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.z, "getTimeCategoriesByIP", 24, com.huawei.hms.kit.awareness.a.a.i.class, new com.huawei.hms.kit.awareness.a.a.m(2, (String) null), 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ LocationResponse i() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.g) a(201, "getLocation", 24, com.huawei.hms.kit.awareness.a.a.g.class, 8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ LocationResponse h() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.g) a(203, "getCurrentLocation", 24, com.huawei.hms.kit.awareness.a.a.g.class, 8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ AmbientLightResponse g() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.a) a(204, "getLightIntensity", 24, com.huawei.hms.kit.awareness.a.a.a.class, 5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WeatherStatusResponse f() {
        com.huawei.hms.kit.awareness.a.a.k kVar = new com.huawei.hms.kit.awareness.a.a.k(2);
        a(kVar);
        return HHL.a((com.huawei.hms.kit.awareness.a.a.j) a(205, Constants.API_GET_WEATHER_BY_IP, 24, com.huawei.hms.kit.awareness.a.a.j.class, kVar, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CapabilityResponse e() {
        return HHL.a((com.huawei.hms.kit.awareness.a.a.n) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.D, "querySupportingCapabilities", 24, com.huawei.hms.kit.awareness.a.a.n.class, 7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DonateResponse d(String str, String[] strArr) {
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, "deleteIntent start", new Object[0]);
        if (TextUtils.isEmpty(str)) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "intentName empty", new Object[0]);
            throw new HHG(AwarenessStatusCodes.AWARENESS_DONATE_V2_INVALID_PARAMS, "intentName empty");
        }
        u uVar = (u) a(107, g, 24, u.class, a(str, strArr), 12);
        com.huawei.hms.kit.awareness.b.a.c.c(f4813a, " deleteIntent result=" + uVar.a().getStatus(), new Object[0]);
        return HHL.a(uVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ TimeCategoriesResponse d(String str) {
        com.huawei.hms.kit.awareness.a.a.m mVar = new com.huawei.hms.kit.awareness.a.a.m(1, str);
        if (mVar.f()) {
            return HHL.a((com.huawei.hms.kit.awareness.a.a.i) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.z, "getTimeCategoriesInChina", 24, com.huawei.hms.kit.awareness.a.a.i.class, mVar, 10));
        }
        throw new HHG(10007, "time request countryCode is invalid");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ScreenStatusResponse d() {
        return HHL.a((s) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.K, "getScreenStatus", 24, s.class, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ServiceOpenIdResponse c(boolean z) {
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, "getServiceOpenId start", new Object[0]);
        SidInternalResult sidInternalResult = (SidInternalResult) a(107, i, 24, SidInternalResult.class, b(z), 13);
        com.huawei.hms.kit.awareness.b.a.c.c(f4813a, " getServiceOpenId result=" + sidInternalResult.getSidStatus().getResult() + " reason= " + sidInternalResult.getSidStatus().getReason(), new Object[0]);
        return HHL.a(sidInternalResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DonateResponse c(List list) {
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, "shareIntent start", new Object[0]);
        a((List<InsightIntent>) list);
        u uVar = (u) a(107, f, 24, u.class, b((List<InsightIntent>) list), 12);
        com.huawei.hms.kit.awareness.b.a.c.c(f4813a, " shareIntent result=" + uVar.a().getStatus(), new Object[0]);
        return HHL.a(uVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DonateResponse c(String str, String[] strArr) {
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, "deleteEntity start", new Object[0]);
        if (TextUtils.isEmpty(str)) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "entityName empty", new Object[0]);
            throw new HHG(AwarenessStatusCodes.AWARENESS_DONATE_V2_INVALID_PARAMS, "entityName empty");
        }
        u uVar = (u) a(107, h, 24, u.class, b(str, strArr), 12);
        com.huawei.hms.kit.awareness.b.a.c.c(f4813a, " deleteEntity result=" + uVar.a().getStatus(), new Object[0]);
        return HHL.a(uVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WifiStatusResponse c() {
        return HHL.a((t) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.L, "getWifiStatus", 24, t.class, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WeatherStatusResponse c(String str) {
        com.huawei.hms.kit.awareness.a.a.k kVar = new com.huawei.hms.kit.awareness.a.a.k(str, 0);
        a(kVar);
        return HHL.a((com.huawei.hms.kit.awareness.a.a.j) a(205, Constants.API_GET_WEATHER_BY_DEVICE, 24, com.huawei.hms.kit.awareness.a.a.j.class, kVar, 10));
    }

    private String b(com.huawei.hms.kit.awareness.a.a.f fVar) {
        final Class<SidInternalResult> cls = SidInternalResult.class;
        Optional filter = Optional.ofNullable(fVar).filter(new Predicate() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda17
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isInstance;
                isInstance = cls.isInstance((com.huawei.hms.kit.awareness.a.a.f) obj);
                return isInstance;
            }
        });
        final Class<SidInternalResult> cls2 = SidInternalResult.class;
        return (String) filter.map(new Function() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda18
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object cast;
                cast = cls2.cast((com.huawei.hms.kit.awareness.a.a.f) obj);
                return (SidInternalResult) cast;
            }
        }).map(new Function() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda19
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SidInternalResult) obj).getSidStatus();
            }
        }).map(new Function() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda20
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ServiceOpenIdStatus) obj).getReason();
            }
        }).orElse(v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContentData b(InsightIntent insightIntent) {
        ContentData header = new ContentData().setHeader("namespace", "intent");
        if (insightIntent != null) {
            header.setHeader("name", insightIntent.getIntentName()).setPayload(p, insightIntent.getIdentifier()).setPayload(q, insightIntent.getIntentVersion());
            for (Map.Entry<String, JSONObject> entry : insightIntent.getIntentInfo().entrySet()) {
                header.setPayload(entry.getKey(), entry.getValue());
            }
        }
        return header;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DonateResponse b(Message message) {
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, " sendMessage start", new Object[0]);
        a(message);
        u uVar = (u) a(107, "sendMessage", 24, u.class, new DonateRequest(message.getSession().toJsonString(), message.getContent().toJsonString()), 11);
        com.huawei.hms.kit.awareness.b.a.c.c(f4813a, " sendMessage result=" + uVar.a().getStatus(), new Object[0]);
        return HHL.a(uVar);
    }

    private DonateRequest b(boolean z) {
        Session a2 = a(i);
        Content content = new Content();
        content.setContentData(Collections.singletonList(a(i, Collections.singletonMap(u, Boolean.valueOf(z)))));
        return new DonateRequest(a2.toJsonString(), content.toJsonString());
    }

    private DonateRequest b(List<InsightIntent> list) {
        Session a2 = a(f);
        Content content = new Content();
        content.setContentData((List) list.stream().map(new Function() { // from class: com.huawei.hms.kit.awareness.b.HHE$$ExternalSyntheticLambda28
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ContentData b2;
                b2 = HHE.this.b((InsightIntent) obj);
                return b2;
            }
        }).collect(Collectors.toList()));
        return new DonateRequest(a2.toJsonString(), content.toJsonString());
    }

    private DonateRequest b(String str, String[] strArr) {
        Session a2 = a(h);
        Content content = new Content();
        content.setContentData(Collections.singletonList(a(str, Collections.singletonMap(l, strArr))));
        return new DonateRequest(a2.toJsonString(), content.toJsonString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DarkModeStatusResponse b() {
        return HHL.a((r) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.M, "getDarkModeStatus", 29, r.class, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ApplicationStatusResponse b(String str) {
        com.huawei.hms.kit.awareness.a.a.p pVar = new com.huawei.hms.kit.awareness.a.a.p(str);
        if (pVar.b()) {
            return HHL.a((com.huawei.hms.kit.awareness.a.a.q) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.N, "getApplicationStatus", 24, com.huawei.hms.kit.awareness.a.a.q.class, pVar, 10));
        }
        throw new HHG(10007, "package name is invalid");
    }

    private e b(Context context) {
        JSONObject b2 = HHA.a().b();
        if (com.huawei.hms.kit.awareness.barrier.internal.f.c.a(b2)) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "Configured json is null", new Object[0]);
            throw new HHG(10, "Configured json is null");
        }
        try {
            return new e(b2.getString("appId"), b2.getString(com.huawei.hms.kit.awareness.b.a.a.g), Process.myUid(), b2.getString("region"));
        } catch (JSONException e2) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "read agconnect-services.json failed: " + e2.getMessage(), new Object[0]);
            throw new HHG(10, "read agconnect-services.json failed.");
        }
    }

    private com.huawei.hms.kit.awareness.a.a.f b(int i2, Parcelable parcelable, HHK hhk) {
        if (!a(i2, parcelable)) {
            return null;
        }
        if (i2 == 101) {
            return a().a((BarrierUpdateRequest) parcelable, hhk);
        }
        if (i2 == 105) {
            return a().b((BarrierUpdateRequest) parcelable, hhk);
        }
        if (i2 == 107) {
            return a().a((DonateRequest) parcelable);
        }
        if (i2 == 215) {
            return a().a((com.huawei.hms.kit.awareness.a.a.p) parcelable, hhk);
        }
        if (i2 == 205) {
            return a().a((com.huawei.hms.kit.awareness.a.a.k) parcelable, hhk);
        }
        if (i2 == 206) {
            return a().a((c) parcelable, hhk);
        }
        if (i2 == 208) {
            return a().a((com.huawei.hms.kit.awareness.a.a.m) parcelable, hhk);
        }
        if (i2 != 209) {
            return null;
        }
        return a().a((i) parcelable, hhk);
    }

    private boolean a(Map<String, JSONObject> map) {
        return (map != null && map.containsKey(r) && (map.containsKey(s) || map.containsKey(t))) ? false : true;
    }

    private boolean a(InsightIntent insightIntent) {
        return insightIntent == null || insightIntent.getIdentifier() == null || insightIntent.getIntentName() == null || insightIntent.getIntentVersion() == null || a(insightIntent.getIntentInfo());
    }

    private boolean a(int i2, Parcelable parcelable) {
        if (i2 == 101 || i2 == 105) {
            return parcelable instanceof BarrierUpdateRequest;
        }
        if (i2 == 107) {
            return parcelable instanceof DonateRequest;
        }
        if (i2 == 215) {
            return parcelable instanceof com.huawei.hms.kit.awareness.a.a.p;
        }
        if (i2 == 205) {
            return parcelable instanceof com.huawei.hms.kit.awareness.a.a.k;
        }
        if (i2 == 206) {
            return parcelable instanceof c;
        }
        if (i2 == 208) {
            return parcelable instanceof com.huawei.hms.kit.awareness.a.a.m;
        }
        if (i2 != 209) {
            return false;
        }
        return parcelable instanceof i;
    }

    private void a(List<InsightIntent> list) {
        if (list == null || list.size() == 0) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "intents empty", new Object[0]);
            throw new HHG(AwarenessStatusCodes.AWARENESS_DONATE_V2_INVALID_PARAMS, "intents empty");
        }
        Iterator<InsightIntent> it = list.iterator();
        while (it.hasNext()) {
            if (a(it.next())) {
                com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "InsightIntent invalid", new Object[0]);
                throw new HHG(AwarenessStatusCodes.AWARENESS_DONATE_V2_INVALID_PARAMS, "InsightIntent invalid");
            }
        }
    }

    private void a(String str, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, "the current sdk-version is " + i3, new Object[0]);
        if (i3 >= i2) {
            return;
        }
        String format = String.format(Locale.ENGLISH, "%s failed, the minVersion is %d, but now is %d", str, Integer.valueOf(i2), Integer.valueOf(i3));
        com.huawei.hms.kit.awareness.b.a.c.d(f4813a, format, new Object[0]);
        throw new HHG(10100, format);
    }

    private void a(Message message) {
        if (message.getSession() == null || message.getContent() == null) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "invalid message", new Object[0]);
            throw new HHG(10007, "sendMessage message is invalid");
        }
        if (message.getContent().getContentKeySize() > 100) {
            com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "content is too big", new Object[0]);
            throw new HHG(10007, "sendMessage content is too big");
        }
        Session session = message.getSession();
        if (TextUtils.isEmpty(session.getPackageName())) {
            session.setPackageName(this.y.getPackageName());
        }
        session.setMessageVersion("1.1");
    }

    private void a(com.huawei.hms.kit.awareness.a.a.k kVar) {
        if (!kVar.e()) {
            throw new HHG(10007, "weather request is invalid");
        }
    }

    private void a(com.huawei.hms.kit.awareness.a.a.f fVar) {
        if (fVar == null) {
            throw new HHG(10009);
        }
        if (fVar.c() != 0) {
            throw new HHG(fVar.c());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void a(BarrierUpdateRequest barrierUpdateRequest, boolean z) {
        if (!(barrierUpdateRequest instanceof com.huawei.hms.kit.awareness.barrier.internal.f)) {
            throw new HHG(10007);
        }
        com.huawei.hms.kit.awareness.barrier.internal.f fVar = (com.huawei.hms.kit.awareness.barrier.internal.f) barrierUpdateRequest;
        if (!fVar.b()) {
            throw new HHG(10007);
        }
        int k2 = fVar.k();
        com.huawei.hms.kit.awareness.b.a.c.b(f4813a, "barrier miniApi level: " + k2, new Object[0]);
        f.a aVar = new f.a() { // from class: com.huawei.hms.kit.awareness.b.HHE.1
            @Override // com.huawei.hms.kit.awareness.barrier.internal.f.a
            public boolean a(com.huawei.hms.kit.awareness.barrier.internal.a aVar2) {
                int i2;
                if (aVar2.c() != 1 && aVar2.c() != 0 && aVar2.c() != 3) {
                    i2 = 10007;
                } else {
                    if (aVar2.c() != 1 || ((com.huawei.hms.kit.awareness.barrier.internal.a.c) aVar2.b()).c()) {
                        return false;
                    }
                    i2 = 10100;
                }
                a(i2);
                return true;
            }
        };
        aVar.a(0);
        if (!fVar.a(aVar) && aVar.a() != 0) {
            throw new HHG(aVar.a());
        }
        fVar.a(b(this.y));
        if (!z) {
            com.huawei.hms.kit.awareness.b.a.c.b(f4813a, "Update Barriers status code: " + a(101, "updateBarriers", 0, com.huawei.hms.kit.awareness.a.a.h.class, fVar, k2).c(), new Object[0]);
            return null;
        }
        if (k2 <= 8) {
            k2 = 8;
        }
        com.huawei.hms.kit.awareness.b.a.c.b(f4813a, "Update Barriers Auto Remove status code: " + a(105, "updateBarriersAllowRemove", 0, com.huawei.hms.kit.awareness.a.a.h.class, fVar, k2).c(), new Object[0]);
        return null;
    }

    private Session a(String str) {
        Session session = new Session();
        session.setMessageName(str);
        session.setMessageVersion(j);
        session.setPackageName(this.y.getPackageName());
        session.setAgreePrivacy(this.z);
        return session;
    }

    private ContentData a(String str, Map<String, Object> map) {
        ContentData contentData = new ContentData();
        contentData.setHeader("namespace", "intent");
        contentData.setHeader("name", str);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                contentData.setPayload(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Boolean) {
                contentData.setPayload(entry.getKey(), (Boolean) entry.getValue());
            } else if (entry.getValue() instanceof String[]) {
                contentData.setPayload(entry.getKey(), (String[]) entry.getValue());
            } else if (entry.getValue() instanceof JSONObject) {
                contentData.setPayload(entry.getKey(), (JSONObject) entry.getValue());
            } else {
                com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "unsupported payload value type", new Object[0]);
            }
        }
        return contentData;
    }

    private DonateRequest a(String str, String[] strArr) {
        Session a2 = a(g);
        Content content = new Content();
        content.setContentData(Collections.singletonList(a(str, Collections.singletonMap(k, strArr))));
        return new DonateRequest(a2.toJsonString(), content.toJsonString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WeatherStatusResponse a(WeatherPosition weatherPosition) {
        com.huawei.hms.kit.awareness.a.a.k kVar = new com.huawei.hms.kit.awareness.a.a.k(weatherPosition, 3);
        a(kVar);
        return HHL.a((com.huawei.hms.kit.awareness.a.a.j) a(205, Constants.API_GET_WEATHER_BY_POSITION, 24, com.huawei.hms.kit.awareness.a.a.j.class, kVar, 10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ TimeCategoriesResponse a(long j2) {
        com.huawei.hms.kit.awareness.a.a.m mVar = new com.huawei.hms.kit.awareness.a.a.m(3, j2);
        if (mVar.f()) {
            return HHL.a((com.huawei.hms.kit.awareness.a.a.i) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.z, "getTimeCategoriesForFuture", 24, com.huawei.hms.kit.awareness.a.a.i.class, mVar, 10));
        }
        throw new HHG(10007, "time request future timestamp is invalid");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ TimeCategoriesResponse a(double d2, double d3) {
        com.huawei.hms.kit.awareness.a.a.m mVar = new com.huawei.hms.kit.awareness.a.a.m(0, d2, d3);
        if (mVar.f()) {
            return HHL.a((com.huawei.hms.kit.awareness.a.a.i) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.z, "getTimeCategoriesByUser", 24, com.huawei.hms.kit.awareness.a.a.i.class, mVar, 10));
        }
        throw new HHG(10007, "time request latitude or longitude is invalid");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BluetoothStatusResponse a(int i2) {
        if (com.huawei.hms.kit.awareness.barrier.internal.d.h.b(i2)) {
            return HHL.a((com.huawei.hms.kit.awareness.a.a.l) a(com.huawei.hms.kit.awareness.barrier.internal.e.a.A, "getBluetoothStatus", 24, com.huawei.hms.kit.awareness.a.a.l.class, new i(i2), 6));
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BeaconStatusResponse a(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            BeaconStatus.Filter filter = (BeaconStatus.Filter) it.next();
            if (!(filter instanceof com.huawei.hms.kit.awareness.a.a.b)) {
                throw new HHG(10007, "typeFilters are invalid");
            }
            com.huawei.hms.kit.awareness.a.a.b bVar = (com.huawei.hms.kit.awareness.a.a.b) filter;
            if (!bVar.d()) {
                throw new HHG(10007, "typeFilters are invalid");
            }
            arrayList.add(bVar);
        }
        return HHL.a((com.huawei.hms.kit.awareness.a.a.c) a(206, "getBeaconStatus", 24, com.huawei.hms.kit.awareness.a.a.c.class, new c.a().a(arrayList).a(b(this.y)).a(), 9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ BarrierQueryResponse a(BarrierQueryRequest barrierQueryRequest) {
        if (!(barrierQueryRequest instanceof com.huawei.hms.kit.awareness.barrier.internal.c)) {
            throw new HHG(" request illegal parameter");
        }
        this.x.a(5);
        com.huawei.hms.kit.awareness.barrier.internal.b a2 = a().a(barrierQueryRequest);
        com.huawei.hms.kit.awareness.b.a.c.a(f4813a, " Query Barriers finish", new Object[0]);
        return HHL.a(a2);
    }

    public static HHE a(Context context) {
        if (w == null) {
            synchronized (HHE.class) {
                if (w == null) {
                    w = new HHE(context);
                    HHA.a().a(context);
                }
            }
        }
        return w;
    }

    private com.huawei.hms.kit.awareness.a a() {
        if (this.x.a() != null) {
            return this.x.a();
        }
        com.huawei.hms.kit.awareness.b.a.c.d(f4813a, b, new Object[0]);
        throw new HHG(10001, b);
    }

    private <T extends com.huawei.hms.kit.awareness.a.a.f> T a(int i2, String str, int i3, Class<T> cls, Parcelable parcelable, int i4) {
        a(str, i3);
        this.x.a(i4);
        HHK hhk = new HHK();
        try {
            com.huawei.hms.kit.awareness.a.a.f a2 = a(i2, parcelable, hhk);
            if (a2 == null) {
                throw new HHG(10009, str);
            }
            int c2 = a2.c();
            if (c2 != 0) {
                if (c2 != 10011) {
                    if (c2 != 10155) {
                        throw new HHG(a2.c(), str);
                    }
                    throw new HHG(a2.c(), str + " failed, reason: " + b(a2));
                }
                if (!hhk.b()) {
                    com.huawei.hms.kit.awareness.b.a.c.d(f4813a, "Waiting callback result timeout", new Object[0]);
                    throw new HHG(10003, str);
                }
                a2 = hhk.c();
                StringBuilder sb = new StringBuilder("Waiting callback result: ");
                sb.append(a2 != null);
                com.huawei.hms.kit.awareness.b.a.c.b(f4813a, sb.toString(), new Object[0]);
            }
            a(a2);
            T t2 = (T) com.huawei.hms.kit.awareness.a.a.f.a(a2, cls);
            if (t2 != null) {
                return t2;
            }
            throw new HHG(10009, str);
        } catch (RemoteException unused) {
            throw new HHG(10010, String.format(Locale.ROOT, c, str));
        }
    }

    private <T extends com.huawei.hms.kit.awareness.a.a.f> T a(int i2, String str, int i3, Class<T> cls, int i4) {
        return (T) a(i2, str, i3, cls, null, i4);
    }

    private com.huawei.hms.kit.awareness.a.a.f a(int i2, HHK hhk) {
        switch (i2) {
            case 200:
                return a().f(hhk);
            case 201:
                return a().c(hhk);
            case 202:
                return a().b(hhk);
            case 203:
                return a().d(hhk);
            case 204:
                return a().e(hhk);
            case 205:
            case 206:
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.z /* 208 */:
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.A /* 209 */:
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.C /* 210 */:
            default:
                return null;
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.w /* 207 */:
                return a().a(hhk);
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.D /* 211 */:
                return a().g(hhk);
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.K /* 212 */:
                return a().i(hhk);
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.L /* 213 */:
                return a().j(hhk);
            case com.huawei.hms.kit.awareness.barrier.internal.e.a.M /* 214 */:
                return a().k(hhk);
        }
    }

    private com.huawei.hms.kit.awareness.a.a.f a(int i2, Parcelable parcelable, HHK hhk) {
        com.huawei.hms.kit.awareness.a.a.f a2 = a(i2, hhk);
        return a2 != null ? a2 : b(i2, parcelable, hhk);
    }

    private HHE(Context context) {
        this.x = HHF.a(context);
        this.y = context;
    }
}
