package defpackage;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.json.JsonSanitizer;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter;
import com.huawei.health.ecologydevice.ui.measure.view.SportIntroductionContentView;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import defpackage.dhf;
import defpackage.djo;
import defpackage.dlb;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/* loaded from: classes3.dex */
public class dhf implements SportIntroductionContentPresenter, MessageOrStateCallback {
    private IndoorEquipManagerApi b;
    private Handler c;
    private boolean e;
    private String f;
    private int g;
    private long h;
    private long i;
    private SportIntroductionContentView j;
    private ArrayList<Future<?>> d = new ArrayList<>(10);

    /* renamed from: a, reason: collision with root package name */
    private boolean f11658a = true;

    public dhf(int i, String str) {
        this.g = i;
        this.f = str;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void onCreate(SportIntroductionContentView sportIntroductionContentView) {
        this.j = sportIntroductionContentView;
        this.c = new Handler(Looper.getMainLooper());
        IndoorEquipManagerApi indoorEquipManagerApi = (IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class);
        this.b = indoorEquipManagerApi;
        indoorEquipManagerApi.init();
        this.b.setMessageOrStateCallback("SportIntroductionPresenter" + toString(), this, false);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void getLastSportRecord(final int i) {
        this.d.add(ThreadPoolManager.d().submit(new Runnable() { // from class: dhh
            @Override // java.lang.Runnable
            public final void run() {
                dhf.this.d(i);
            }
        }));
    }

    /* synthetic */ void d(int i) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{i});
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchSequenceDataBySportType(hiDataReadOption, new AnonymousClass4());
    }

    /* renamed from: dhf$4, reason: invalid class name */
    class AnonymousClass4 implements HiDataReadResultListener {
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass4() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, final int i, int i2) {
            LogUtil.a("SportIntroductionPresenter", "queryLastHistoryData onResult errorCode:", Integer.valueOf(i));
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("SportIntroductionPresenter", "error result data");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
                LogUtil.h("SportIntroductionPresenter", "requestTrackSimplifyData onResult obj not instanceof List");
                dhf.this.c.post(new Runnable() { // from class: dhl
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass4.this.a(i);
                    }
                });
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) ((List) sparseArray.get(i)).get(0);
            final HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(JsonSanitizer.sanitize(hiHealthData.getMetaData()), HiTrackMetaData.class);
            dhf.this.h = hiHealthData.getStartTime();
            dhf.this.i = hiHealthData.getEndTime();
            dhf.this.c.post(new Runnable() { // from class: dhm
                @Override // java.lang.Runnable
                public final void run() {
                    dhf.AnonymousClass4.this.d(hiTrackMetaData);
                }
            });
        }

        /* synthetic */ void a(int i) {
            dhf.this.j.onGetLastHistoryDataFail(i);
        }

        /* synthetic */ void d(HiTrackMetaData hiTrackMetaData) {
            dhf.this.j.onGetLastHistoryDataSuccess(hiTrackMetaData, dhf.this.h);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void getLastMonthRecord(final int i, final long j) {
        LogUtil.h("SportIntroductionPresenter", "requestTrackMonthData() sportType:", Integer.valueOf(i), " lastStartTime:", Long.valueOf(j));
        this.d.add(ThreadPoolManager.d().submit(new Runnable() { // from class: dhk
            @Override // java.lang.Runnable
            public final void run() {
                dhf.this.a(j, i);
            }
        }));
    }

    /* synthetic */ void a(long j, int i) {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(e(j, 0));
        hiSportStatDataAggregateOption.setEndTime(e(j, 1) - 1000);
        hiSportStatDataAggregateOption.setAggregateType(1);
        int[] iArr = new int[1];
        if (i == 264) {
            i = 258;
        }
        iArr[0] = i;
        hiSportStatDataAggregateOption.setHiHealthTypes(iArr);
        hiSportStatDataAggregateOption.setGroupUnitType(5);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Track_Walk_Distance_Sum", "Track_Walk_Calorie_Sum", "Track_Walk_Duration_Sum", "Track_Walk_Count_Sum"});
        hiSportStatDataAggregateOption.setType(new int[]{2, 3, 4, 5});
        hiSportStatDataAggregateOption.setReadType(0);
        HiHealthNativeApi.a(this.j.getContext()).aggregateSportStatData(hiSportStatDataAggregateOption, new AnonymousClass1(j));
    }

    /* renamed from: dhf$1, reason: invalid class name */
    class AnonymousClass1 implements HiAggregateListener {
        final /* synthetic */ long e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass1(long j) {
            this.e = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, final int i, int i2) {
            if (!koq.b(list)) {
                final String b = dhf.this.b(list.get(0));
                final String formatDateTime = DateUtils.formatDateTime(dhf.this.j.getContext(), this.e, 52);
                dhf.this.c.post(new Runnable() { // from class: dhn
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass1.this.a(b, formatDateTime);
                    }
                });
            } else {
                LogUtil.h("SportIntroductionPresenter", "requestTrackMonthData() all run data null or empty");
                dhf.this.c.post(new Runnable() { // from class: dho
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass1.this.b(i);
                    }
                });
            }
        }

        /* synthetic */ void b(int i) {
            dhf.this.j.onMonthDataFail(i);
        }

        /* synthetic */ void a(String str, String str2) {
            dhf.this.j.onMonthDataSuccess(str, str2);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void setSubPageBiEvent(final String str, final String str2, final String str3, final int i) {
        ThreadPoolManager.d().submit(new Runnable() { // from class: dhi
            @Override // java.lang.Runnable
            public final void run() {
                dhf.b(str, str2, str3, i);
            }
        });
    }

    static /* synthetic */ void b(String str, String str2, String str3, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("prodId", dij.e(str));
        hashMap.put("macAddress", dis.b(str2));
        hashMap.put("page_id", str3);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(i));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_SUB_PAGE_2170048.value(), hashMap, 0);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void setModeSelectBiEvent(final String str, final int i, final String str2, final int i2) {
        ThreadPoolManager.d().submit(new Runnable() { // from class: dhp
            @Override // java.lang.Runnable
            public final void run() {
                dhf.this.b(str, i, str2, i2);
            }
        });
    }

    /* synthetic */ void b(String str, int i, String str2, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("prodId", dij.e(str));
        hashMap.put("macAddress", dis.b(this.f));
        hashMap.put("current_sport_target_type", Integer.valueOf(i));
        hashMap.put("current_sport_target", str2);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(i2));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_SPORT_INTRODUCTION_PAGE_MODE_SELECT_2170047.value(), hashMap, 0);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void queryTrackDetailData() {
        this.d.add(ThreadPoolManager.d().submit(new Runnable() { // from class: dhg
            @Override // java.lang.Runnable
            public final void run() {
                dhf.this.b();
            }
        }));
    }

    /* synthetic */ void b() {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(this.h, this.i);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new AnonymousClass3());
    }

    /* renamed from: dhf$3, reason: invalid class name */
    class AnonymousClass3 implements HiDataReadResultListener {
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass3() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (!(obj instanceof SparseArray)) {
                LogUtil.b("SportIntroductionPresenter", "quaryTrackDetailData onResult data not instanceof SparseArray");
                dhf.this.c.post(new Runnable() { // from class: dhr
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass3.this.c();
                    }
                });
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                LogUtil.b("SportIntroductionPresenter", "quaryTrackDetailData onResult map size is null");
                dhf.this.c.post(new Runnable() { // from class: dhs
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass3.this.b();
                    }
                });
                return;
            }
            Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
            if (!koq.e(obj2, HiHealthData.class)) {
                LogUtil.b("SportIntroductionPresenter", "quaryTrackDetailData onResult obj not instanceof List");
                dhf.this.c.post(new Runnable() { // from class: dhq
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass3.this.d();
                    }
                });
                return;
            }
            List list = (List) obj2;
            if (koq.b(list)) {
                LogUtil.b("SportIntroductionPresenter", "quaryTrackDetailData onResult list is empty");
                dhf.this.c.post(new Runnable() { // from class: dht
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass3.this.e();
                    }
                });
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            if (hiHealthData == null) {
                LogUtil.b("SportIntroductionPresenter", "quaryTrackDetailData onResult hiHealthData is null");
                dhf.this.c.post(new Runnable() { // from class: dhu
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass3.this.a();
                    }
                });
            } else {
                final MotionPathSimplify e = kpt.e(hiHealthData);
                final String a2 = kpt.a(hiHealthData, e);
                dhf.this.c.post(new Runnable() { // from class: dhx
                    @Override // java.lang.Runnable
                    public final void run() {
                        dhf.AnonymousClass3.this.c(e, a2);
                    }
                });
            }
        }

        /* synthetic */ void c() {
            dhf.this.j.queryTrackDetailDataFail();
        }

        /* synthetic */ void b() {
            dhf.this.j.queryTrackDetailDataFail();
        }

        /* synthetic */ void d() {
            dhf.this.j.queryTrackDetailDataFail();
        }

        /* synthetic */ void e() {
            dhf.this.j.queryTrackDetailDataFail();
        }

        /* synthetic */ void a() {
            dhf.this.j.queryTrackDetailDataFail();
        }

        /* synthetic */ void c(MotionPathSimplify motionPathSimplify, String str) {
            dhf.this.j.queryTrackDetailDataSuccess(motionPathSimplify, str);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void saveTargetData(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put(b("target_cache_value"), str);
        a(str2, hashMap);
    }

    private void a(String str, Map<String, String> map) {
        SharedPreferenceManager.c("sport_introduction_content", e(str), lql.b(map));
    }

    private String c(Map<String, String> map, String str) {
        return (map == null || !map.containsKey(str)) ? "0" : map.get(str);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public BigDecimal getTargetCacheValue(String str) {
        String e = SharedPreferenceManager.e("sport_introduction_content", e(str), "");
        return new BigDecimal(!TextUtils.isEmpty(e) ? c((Map) gvv.a(e, new TypeToken<Map<String, String>>() { // from class: dhf.2
        }), b("target_cache_value")) : "0");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void removeNoShowDescriptionMac(String str) {
        LinkedList<String> a2 = a();
        if (koq.b(a2) || TextUtils.isEmpty(str) || !a2.contains(str)) {
            return;
        }
        a2.remove(str);
        SharedPreferenceManager.c("sport_introduction_content", e("rate_mode_description"), lql.b(a2));
    }

    private LinkedList<String> a() {
        String e = SharedPreferenceManager.e("sport_introduction_content", e("rate_mode_description"), "");
        if (TextUtils.isEmpty(e)) {
            return null;
        }
        return (LinkedList) gvv.a(e, new TypeToken<LinkedList<String>>() { // from class: dhf.5
        });
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void removeModeCache() {
        SharedPreferenceManager.d("sport_introduction_content", b("DeviceInformation"));
        c("sport_target_time");
        c("sport_target_distance");
        c("sport_target_cal");
    }

    private void c(String str) {
        Map<String, String> map;
        String e = SharedPreferenceManager.e("sport_introduction_content", e(str), "");
        if (TextUtils.isEmpty(e) || (map = (Map) gvv.a(e, new TypeToken<Map<String, String>>() { // from class: dhf.9
        })) == null) {
            return;
        }
        map.remove(b("target_cache_value"));
        map.remove(b("target_cache_select_index"));
        a(str, map);
    }

    private String e(String str) {
        return str + LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
    }

    private String b(String str) {
        return str + this.f;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public boolean isNeedShowDescription(String str) {
        if (a() == null || TextUtils.isEmpty(str)) {
            return true;
        }
        return !r0.contains(str);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void connectDevice(int i, dcz dczVar) {
        this.b.setDeviceType(i == 264 ? "31" : BleConstants.SPORT_TYPE_BIKE);
        if (!TextUtils.isEmpty(this.f)) {
            LogUtil.a("SportIntroductionPresenter", "connectDevice connectByMac");
            this.b.connectByMac(true, this.f);
        } else {
            String d = (dczVar == null || dczVar.aa() == null || !koq.d(dczVar.aa(), 0) || dczVar.aa().get(0) == null) ? "" : dczVar.aa().get(0).d();
            LogUtil.a("SportIntroductionPresenter", "connectDevice connectByName");
            this.b.connectByName(true, d);
        }
    }

    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    public void onNewMessage(int i, Bundle bundle) {
        LogUtil.a("SportIntroductionPresenter", "onNewMessage newCase is ", Integer.valueOf(i));
        if (this.b.isStartByDevice(bundle) && this.f11658a) {
            this.f11658a = false;
            LogUtil.a("SportIntroductionPresenter", "onNewMessage startSport");
            this.c.post(new Runnable() { // from class: dhj
                @Override // java.lang.Runnable
                public final void run() {
                    dhf.this.d();
                }
            });
        } else if (this.b.isStopByDevice(bundle)) {
            LogUtil.a("SportIntroductionPresenter", "onNewMessage stopSport");
            this.f11658a = true;
        }
    }

    /* synthetic */ void d() {
        this.j.startSport();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public DeviceInformation getDeviceInformation() {
        DeviceInformation deviceInformation = this.b.getDeviceInformation();
        if (TextUtils.isEmpty(deviceInformation.getManufacturerString()) || TextUtils.isEmpty(deviceInformation.getSerialNumber()) || TextUtils.isEmpty(deviceInformation.getSoftwareVersion()) || TextUtils.isEmpty(deviceInformation.getModelString())) {
            String e = SharedPreferenceManager.e("sport_introduction_content", b("DeviceInformation"), "");
            if (!TextUtils.isEmpty(e)) {
                deviceInformation = (DeviceInformation) gvv.a(e, new TypeToken<DeviceInformation>() { // from class: dhf.8
                });
            }
            return deviceInformation == null ? new DeviceInformation() : deviceInformation;
        }
        SharedPreferenceManager.c("sport_introduction_content", b("DeviceInformation"), lql.b(deviceInformation));
        return deviceInformation;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void disconnect(boolean z) {
        LogUtil.a("SportIntroductionPresenter", "disconnect: isBreakAsUnexpected->", Boolean.valueOf(z));
        this.b.disconnect(z);
        this.e = false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    public void onStateChange(String str) {
        char c;
        LogUtil.a("SportIntroductionPresenter", "onStateChange:", str);
        str.hashCode();
        final int i = 3;
        switch (str.hashCode()) {
            case -912943761:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -780096011:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -697798346:
                if (str.equals(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 91311644:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 325932403:
                if (str.equals(AbstractFitnessClient.ACTION_READ_SUCCESS)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 552708325:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1400671776:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1586831660:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTING)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1763518706:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 3:
            case 5:
            case '\b':
                i = 1;
                break;
            case 1:
            case 2:
            case 6:
                if (this.e) {
                    nrh.d(BaseApplication.getContext(), BaseApplication.getContext().getResources().getString(R.string._2130845060_res_0x7f021d84));
                }
                this.e = false;
                i = 0;
                break;
            case 4:
                this.b.setSendCalorieToDevice(dfa.c(this.j.getProductId()));
                this.e = true;
                if (this.g == 264) {
                    this.b.setUnlockCode(e());
                }
                c();
                if (dij.h(this.j.getProductId())) {
                    LogUtil.a("SportIntroductionPresenter", "registerDataClient");
                    djo djoVar = new djo();
                    djoVar.a(djoVar.e(new djo.c(this.j.getProductId(), this.f).d(getDeviceInformation())), null);
                }
                i = 2;
                break;
            case 7:
                break;
            default:
                return;
        }
        this.c.post(new Runnable() { // from class: dhb
            @Override // java.lang.Runnable
            public final void run() {
                dhf.this.a(i);
            }
        });
    }

    /* synthetic */ void a(int i) {
        this.j.onDeviceBleStateChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(HiHealthData hiHealthData) {
        String string;
        double d = hiHealthData.getDouble("Track_Walk_Duration_Sum") / 60000.0d;
        Resources resources = this.j.getContext().getResources();
        if (d > 60.0d) {
            string = resources.getQuantityString(R.plurals._2130903223_res_0x7f0300b7, (int) d, UnitUtil.e(d / 60.0d, 1, 1));
        } else {
            string = resources.getString(R.string._2130837641_res_0x7f020089, UnitUtil.e(d, 1, 2));
        }
        String b = hji.b(hiHealthData.getDouble("Track_Walk_Calorie_Sum") / 1000.0d);
        double d2 = hiHealthData.getDouble("Track_Walk_Distance_Sum");
        String quantityString = this.j.getContext().getResources().getQuantityString(R.plurals._2130903301_res_0x7f030105, ((int) d2) / 1000, UnitUtil.e(d2 / 1000.0d, 1, 2));
        double d3 = hiHealthData.getDouble("Track_Walk_Count_Sum");
        String quantityString2 = this.j.getContext().getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, (int) d3, UnitUtil.e(d3, 1, 0));
        if (d2 <= 0.0d) {
            return resources.getString(R.string._2130845546_res_0x7f021f6a, string, quantityString2, b);
        }
        return resources.getString(R.string._2130845547_res_0x7f021f6b, quantityString, string, quantityString2, b);
    }

    private long e(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.add(2, i);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.presenter.SportIntroductionContentPresenter
    public void onDetach() {
        if (!this.d.isEmpty()) {
            Iterator<Future<?>> it = this.d.iterator();
            while (it.hasNext()) {
                it.next().cancel(true);
            }
        }
        this.b.removeMessageOrStateCallback("SportIntroductionPresenter" + toString(), false);
    }

    private void c() {
        LogUtil.a("SportIntroductionPresenter", "uploadDataToHota");
        DeviceInformation deviceInformation = getDeviceInformation();
        if (deviceInformation == null) {
            LogUtil.h("SportIntroductionPresenter", "deviceInformation is null");
        } else {
            if (TextUtils.isEmpty(deviceInformation.getSerialNumber())) {
                return;
            }
            new dlb.a().c(deviceInformation.getModelString()).a(deviceInformation.getHardwareVersion()).b(deviceInformation.getManufacturerString()).f(deviceInformation.getSoftwareVersion()).d(deviceInformation.getSerialNumber()).e(this.j.getProductId()).c().a();
        }
    }

    private int[] e() {
        String serialNumber = getDeviceInformation().getSerialNumber();
        if (TextUtils.isEmpty(serialNumber) || serialNumber.length() < 6) {
            return null;
        }
        String substring = serialNumber.substring(serialNumber.length() - 6);
        LogUtil.a("SportIntroductionPresenter", "getUnlockCode unlockCode is ", substring);
        int[] iArr = new int[6];
        for (int i = 0; i < substring.length(); i++) {
            int a2 = a(substring.charAt(i));
            iArr[i] = a2;
            LogUtil.c("SportIntroductionPresenter", "getUnlockCode unlockCodeInt value ", Integer.valueOf(a2));
        }
        return iArr;
    }

    private int a(char c) {
        if (c == 'a' || c == 'A') {
            return 10;
        }
        if (c == 'b' || c == 'B') {
            return 11;
        }
        if (c == 'c' || c == 'C') {
            return 12;
        }
        if (c == 'd' || c == 'D') {
            return 13;
        }
        if (c == 'e' || c == 'E') {
            return 14;
        }
        if (c == 'f' || c == 'F') {
            return 15;
        }
        return Integer.parseInt(String.valueOf(c));
    }
}
