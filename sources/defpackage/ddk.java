package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.ecologydevice.callback.RopeDeviceDataListener;
import com.huawei.health.ecologydevice.callback.RopeHistoryCallback;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.ecologydevice.fitness.datastruct.RopeModeSettingData;
import com.huawei.health.ecologydevice.fitness.datastruct.SettingStatusData;
import com.huawei.health.ecologydevice.fitness.datastruct.SwitchStatusData;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class ddk implements MessageOrStateCallback {

    /* renamed from: a, reason: collision with root package name */
    private int f11607a;
    private DeviceInformation b;
    private HiHealthData c;
    private int d;
    private String e;
    private final String f;
    private String g;
    private dcz h;
    private RopeDeviceDataListener i;
    private MotionPathSimplify j;
    private final Runnable k = new Runnable() { // from class: ddq
        @Override // java.lang.Runnable
        public final void run() {
            ddk.this.r();
        }
    };
    private String l;
    private HiHealthData m;
    private RopeModeSettingData n;
    private cym o;
    private String p;
    private SwitchStatusData r;
    private SettingStatusData t;

    public ddk(String str, String str2, RopeDeviceDataListener ropeDeviceDataListener) {
        this.i = ropeDeviceDataListener;
        this.g = str;
        this.p = str2;
        dcz d = ResourceManager.e().d(this.g);
        this.h = d;
        if (d != null) {
            LogUtil.c("PDROPE_DeviceDataManager", "Rope device product info:", d.g());
        }
        this.e = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getBondedDeviceAddress(this.g, this.p);
        this.f = "PDROPE_DeviceDataManager" + System.currentTimeMillis();
        if (did.c().d()) {
            return;
        }
        LogUtil.a("PDROPE_DeviceDataManager", "isRopeDeviceBtConnected = ", Boolean.valueOf(dds.c().f()));
        if (dds.c().f()) {
            Object[] objArr = new Object[2];
            objArr[0] = "isSameDevice = ";
            objArr[1] = Boolean.valueOf(!TextUtils.isEmpty(this.e) && this.e.equals(dds.c().d()));
            LogUtil.a("PDROPE_DeviceDataManager", objArr);
            if (!TextUtils.isEmpty(this.e) && this.e.equals(dds.c().d())) {
                t();
                this.f11607a = 2;
                return;
            } else {
                dds.c().h();
                this.f11607a = 0;
                return;
            }
        }
        dds.c().h();
        this.f11607a = 0;
    }

    public void c(String str, String str2) {
        this.g = str;
        this.p = str2;
        dcz d = ResourceManager.e().d(this.g);
        this.h = d;
        if (d != null) {
            LogUtil.c("PDROPE_DeviceDataManager", "Rope device product info:", d.g());
        }
        this.e = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getBondedDeviceAddress(this.g, this.p);
    }

    public dcz j() {
        return this.h;
    }

    public String f() {
        return this.h.n().b();
    }

    public int e() {
        return this.f11607a;
    }

    public int a() {
        return this.d;
    }

    public String n() {
        return this.l;
    }

    public HiHealthData c() {
        return this.c;
    }

    public MotionPathSimplify h() {
        return this.j;
    }

    public HiHealthData m() {
        return this.m;
    }

    public DeviceInformation d() {
        return this.b;
    }

    public RopeModeSettingData g() {
        return this.n;
    }

    public SettingStatusData k() {
        return this.t;
    }

    public SwitchStatusData o() {
        return this.r;
    }

    public void l() {
        LogUtil.a("PDROPE_DeviceDataManager", "queryLastHistoryData");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{283});
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: ddk.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a("PDROPE_DeviceDataManager", "queryLastHistoryData onResult errorCode:", Integer.valueOf(i));
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("PDROPE_DeviceDataManager", "error result data");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
                    LogUtil.h("PDROPE_DeviceDataManager", "requestTrackSimplifyData onResult obj not instanceof List");
                    ddk.this.c((List<HiHealthData>) null);
                } else {
                    List arrayList = new ArrayList();
                    if (sparseArray.get(i) instanceof List) {
                        arrayList = (List) sparseArray.get(i);
                    }
                    ddk.this.c((List<HiHealthData>) arrayList);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list) {
        if (!koq.b(list)) {
            this.c = list.get(0);
            RopeDeviceDataListener ropeDeviceDataListener = this.i;
            if (ropeDeviceDataListener != null) {
                ropeDeviceDataListener.onNewLastRope();
                return;
            }
            return;
        }
        RopeDeviceDataListener ropeDeviceDataListener2 = this.i;
        if (ropeDeviceDataListener2 != null) {
            ropeDeviceDataListener2.onSetDefaultValue();
        }
    }

    public void e(long j, long j2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: ddk.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!(obj instanceof SparseArray)) {
                    LogUtil.b("PDROPE_DeviceDataManager", "quaryTrackDetailData onResult data not instanceof SparseArray");
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.b("PDROPE_DeviceDataManager", "quaryTrackDetailData onResult map size is null");
                    return;
                }
                Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                if (!koq.e(obj2, HiHealthData.class)) {
                    LogUtil.b("PDROPE_DeviceDataManager", "quaryTrackDetailData onResult obj not instanceof List");
                    return;
                }
                List list = (List) obj2;
                if (koq.b(list)) {
                    LogUtil.b("PDROPE_DeviceDataManager", "quaryTrackDetailData onResult list is empty");
                    return;
                }
                HiHealthData hiHealthData = (HiHealthData) list.get(0);
                if (hiHealthData == null) {
                    LogUtil.b("PDROPE_DeviceDataManager", "quaryTrackDetailData onResult hiHealthData is null");
                    return;
                }
                ddk.this.l = hiHealthData.getSequenceFileUrl();
                LogUtil.a("PDROPE_DeviceDataManager", "mSequenceFileUrl is ", ddk.this.l);
                ddk ddkVar = ddk.this;
                ddkVar.j = ddkVar.b(hiHealthData);
                ddk.this.j.saveDeviceType(hiHealthData.getInt("trackdata_deviceType"));
                ddk.this.j.saveProductId(hiHealthData.getString("device_prodid"));
                if (ddk.this.i != null) {
                    ddk.this.i.onQuaryTrackDetailDataSuccess();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MotionPathSimplify b(HiHealthData hiHealthData) {
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) nrv.b(hiHealthData.getMetaData(), HiTrackMetaData.class);
        e(hiHealthData, motionPathSimplify, hiTrackMetaData);
        b(motionPathSimplify, hiTrackMetaData);
        return motionPathSimplify;
    }

    private void b(MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        motionPathSimplify.saveIsNewCoordinate(hiTrackMetaData.getIsNewCoordinate());
        motionPathSimplify.saveSwimSegments(hiTrackMetaData.getSwimSegments());
        motionPathSimplify.saveBritishSwimSegments(hiTrackMetaData.getBritishSwimSegments());
        motionPathSimplify.saveSwolfBase(hiTrackMetaData.getSwolfBase());
        motionPathSimplify.saveBritishSwolfBase(hiTrackMetaData.getBritishSwolfBase());
        motionPathSimplify.saveMaxAltitude(hiTrackMetaData.getMaxAlti());
        motionPathSimplify.saveMinAltitude(hiTrackMetaData.getMinAlti());
        motionPathSimplify.saveTotalDescent(hiTrackMetaData.getTotalDescent());
        motionPathSimplify.saveFreeMotion(hiTrackMetaData.getIsFreeMotion());
        motionPathSimplify.saveSportDataSource(hiTrackMetaData.getSportDataSource());
        motionPathSimplify.saveChiefSportDataType(hiTrackMetaData.getChiefSportDataType());
        motionPathSimplify.saveHasTrackPoint(hiTrackMetaData.getHasTrackPoint());
        motionPathSimplify.saveAbnormalTrack(hiTrackMetaData.getAbnormalTrack());
        motionPathSimplify.saveExtendDataMap(hiTrackMetaData.getExtendTrackMap());
        motionPathSimplify.saveDuplicated(hiTrackMetaData.getDuplicated());
        motionPathSimplify.saveHeartRateZoneType(hiTrackMetaData.getHeartrateZoneType());
        motionPathSimplify.saveRunCourseId(hiTrackMetaData.getRuncourseId());
        motionPathSimplify.saveChildSportItems(hiTrackMetaData.getChildSportItems());
        motionPathSimplify.saveFatherSportItem(hiTrackMetaData.getFatherSportItem());
        motionPathSimplify.saveMaxSpo2(hiTrackMetaData.getMaxSpo2());
        motionPathSimplify.saveMinSpo2(hiTrackMetaData.getMinSpo2());
    }

    private void e(HiHealthData hiHealthData, MotionPathSimplify motionPathSimplify, HiTrackMetaData hiTrackMetaData) {
        motionPathSimplify.saveTrackType(hiTrackMetaData.getTrackType());
        motionPathSimplify.saveTotalTime(hiTrackMetaData.getTotalTime());
        motionPathSimplify.saveTotalDistance(hiTrackMetaData.getTotalDistance());
        motionPathSimplify.saveAvgHeartRate(hiTrackMetaData.getAvgHeartRate());
        motionPathSimplify.saveAvgStepRate(hiTrackMetaData.getAvgStepRate());
        motionPathSimplify.saveTotalCalories(hiTrackMetaData.getTotalCalories());
        motionPathSimplify.saveSportId(hiTrackMetaData.getSportId());
        motionPathSimplify.saveSportType(hiTrackMetaData.getSportType());
        motionPathSimplify.saveTotalSteps(hiTrackMetaData.getTotalSteps());
        motionPathSimplify.saveBestStepRate(hiTrackMetaData.getBestStepRate());
        motionPathSimplify.saveMaxHeartRate(hiTrackMetaData.getMaxHeartRate());
        motionPathSimplify.saveEndTime(hiHealthData.getEndTime());
        motionPathSimplify.saveStartTime(hiHealthData.getStartTime());
        motionPathSimplify.saveSportData(hiTrackMetaData.getWearSportData());
        c(hiTrackMetaData, motionPathSimplify);
    }

    private void c(HiTrackMetaData hiTrackMetaData, MotionPathSimplify motionPathSimplify) {
        motionPathSimplify.saveAvgPace(hiTrackMetaData.getAvgPace());
        motionPathSimplify.saveBestPace(hiTrackMetaData.getBestPace());
        motionPathSimplify.savePaceMap(hiTrackMetaData.getPaceMap());
        motionPathSimplify.saveBritishPaceMap(hiTrackMetaData.getBritishPaceMap());
        motionPathSimplify.savePartTimeMap(hiTrackMetaData.getPartTimeMap());
        motionPathSimplify.saveBritishPartTimeMap(hiTrackMetaData.getBritishPartTimeMap());
        motionPathSimplify.saveCreepingWave(hiTrackMetaData.getCreepingWave());
    }

    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    public void onNewMessage(int i, Bundle bundle) {
        if (i == 901) {
            LogUtil.a("PDROPE_DeviceDataManager", "in onNewMessage, newCase is INIT_ALL_VARS_IN_SERVICE");
            return;
        }
        if (i != 902) {
            if (i == 1000 || i == 1001) {
                LogUtil.a("PDROPE_DeviceDataManager", "rope sport status change");
                RopeDeviceDataListener ropeDeviceDataListener = this.i;
                if (ropeDeviceDataListener != null) {
                    ropeDeviceDataListener.onRopeSportStatusChange(i);
                    return;
                }
                return;
            }
            LogUtil.a("PDROPE_DeviceDataManager", "in onNewMessage, newCase is ", Integer.valueOf(i));
            return;
        }
        if (bundle == null) {
            return;
        }
        LogUtil.a("PDROPE_DeviceDataManager", "in onNewMessage, newCase is DATA_AVAILABLE");
        int i2 = bundle.getInt("com.huawei.health.fitness.KEY_MESSAGE_FITNESS_DATA_TYPE");
        Serializable serializable = bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
        LogUtil.c("PDROPE_DeviceDataManager", "onNewMessage DATA_AVAILABLE:", serializable);
        if (serializable instanceof HashMap) {
            d(i2, (HashMap<Integer, Object>) serializable);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0069, code lost:
    
        if (r9 != 5) goto L44;
     */
    @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStateChange(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "onStateChange:"
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r9}
            java.lang.String r1 = "PDROPE_DeviceDataManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            r9.hashCode()
            int r0 = r9.hashCode()
            r2 = 5
            r3 = 4
            r4 = 0
            r5 = 3
            r6 = 2
            r7 = 1
            switch(r0) {
                case -780096011: goto L53;
                case 91311644: goto L48;
                case 325932403: goto L3d;
                case 552708325: goto L32;
                case 1586831660: goto L27;
                case 1763518706: goto L1c;
                default: goto L1b;
            }
        L1b:
            goto L5e
        L1c:
            java.lang.String r0 = "com.huawei.btsportdevice.ACTION_GATT_STATE_CONNECTING"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L25
            goto L5e
        L25:
            r9 = r2
            goto L5f
        L27:
            java.lang.String r0 = "com.huawei.btsportdevice.ACTION_GATT_STATE_DISCONNECTING"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L30
            goto L5e
        L30:
            r9 = r3
            goto L5f
        L32:
            java.lang.String r0 = "com.huawei.btsportdevice.ACTION_GATT_STATE_RECONNECTING"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L3b
            goto L5e
        L3b:
            r9 = r5
            goto L5f
        L3d:
            java.lang.String r0 = "com.huawei.btsportdevice.ACTION_READ_SUCCESS"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L46
            goto L5e
        L46:
            r9 = r6
            goto L5f
        L48:
            java.lang.String r0 = "com.huawei.btsportdevice.ACTION_SERVICE_DISCOVERIED"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L51
            goto L5e
        L51:
            r9 = r7
            goto L5f
        L53:
            java.lang.String r0 = "com.huawei.btsportdevice.ACTION_GATT_STATE_DISCONNECTED"
            boolean r9 = r9.equals(r0)
            if (r9 != 0) goto L5c
            goto L5e
        L5c:
            r9 = r4
            goto L5f
        L5e:
            r9 = -1
        L5f:
            if (r9 == 0) goto L97
            if (r9 == r7) goto L8d
            if (r9 == r6) goto L72
            if (r9 == r5) goto L6f
            if (r9 == r3) goto L6c
            if (r9 == r2) goto L6f
            goto L99
        L6c:
            r8.f11607a = r5
            goto L99
        L6f:
            r8.f11607a = r7
            goto L99
        L72:
            java.lang.String r9 = "ACTION_READ_SUCCESS"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r9)
            r8.f11607a = r6
            java.lang.String r9 = "DeviceMainActivity"
            java.lang.String r0 = defpackage.djx.d()
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L99
            r8.r()
            goto L99
        L8d:
            java.lang.String r9 = "ACTION_SERVICE_DISCOVERIED"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r9)
            goto L99
        L97:
            r8.f11607a = r4
        L99:
            com.huawei.health.ecologydevice.callback.RopeDeviceDataListener r9 = r8.i
            if (r9 == 0) goto La0
            r9.onNewDeviceState()
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ddk.onStateChange(java.lang.String):void");
    }

    private void d(int i, HashMap<Integer, Object> hashMap) {
        LogUtil.c("PDROPE_DeviceDataManager", "dataType", Integer.valueOf(i), " flushAndHandleData: rawData:", hashMap);
        if (hashMap == null) {
            return;
        }
        if (i == 20) {
            LogUtil.a("PDROPE_DeviceDataManager", "Get history data");
            a(hashMap);
            return;
        }
        if (i == 21) {
            LogUtil.a("PDROPE_DeviceDataManager", "Get battery status");
            this.d = a((ddk) hashMap.get(20017));
            RopeDeviceDataListener ropeDeviceDataListener = this.i;
            if (ropeDeviceDataListener != null) {
                ropeDeviceDataListener.onNewBatteryState();
                return;
            }
            return;
        }
        if (i == 23) {
            LogUtil.a("PDROPE_DeviceDataManager", "Get device info");
            this.b = e(hashMap);
            RopeDeviceDataListener ropeDeviceDataListener2 = this.i;
            if (ropeDeviceDataListener2 != null) {
                ropeDeviceDataListener2.onNewDeviceInfo();
                return;
            }
            return;
        }
        if (i == 24) {
            LogUtil.a("PDROPE_DeviceDataManager", "Get training status");
            j(hashMap);
        } else {
            if (i == 27) {
                LogUtil.a("PDROPE_DeviceDataManager", "Get rope mode setting");
                b(hashMap);
                RopeDeviceDataListener ropeDeviceDataListener3 = this.i;
                if (ropeDeviceDataListener3 != null) {
                    ropeDeviceDataListener3.onSetRopeMode();
                    return;
                }
                return;
            }
            e(i, hashMap);
        }
    }

    private void e(int i, HashMap<Integer, Object> hashMap) {
        if (i == 25) {
            LogUtil.a("PDROPE_DeviceDataManager", "Get rope config switch");
            d(hashMap);
            RopeDeviceDataListener ropeDeviceDataListener = this.i;
            if (ropeDeviceDataListener != null) {
                ropeDeviceDataListener.onRopeConfigSwitch();
                return;
            }
            return;
        }
        if (i == 26) {
            LogUtil.a("PDROPE_DeviceDataManager", "Get rope config setting");
            c(hashMap);
            RopeDeviceDataListener ropeDeviceDataListener2 = this.i;
            if (ropeDeviceDataListener2 != null) {
                ropeDeviceDataListener2.onRopeConfigSetting();
                return;
            }
            return;
        }
        LogUtil.a("PDROPE_DeviceDataManager", "Ignore message");
    }

    private void j(HashMap<Integer, Object> hashMap) {
        RopeDeviceDataListener ropeDeviceDataListener;
        Integer valueOf = Integer.valueOf(SmartMsgConstant.MSG_TYPE_SLEEP_USER);
        if (hashMap.get(valueOf) == null) {
            LogUtil.h("PDROPE_DeviceDataManager", "training status data error");
        } else {
            if (((Integer) hashMap.get(valueOf)).intValue() != 1 || this.n == null || System.currentTimeMillis() - this.n.getReceiveTimeMillis() > 1000 || (ropeDeviceDataListener = this.i) == null) {
                return;
            }
            ropeDeviceDataListener.onReverseControl();
        }
    }

    private void b(HashMap<Integer, Object> hashMap) {
        this.n = new RopeModeSettingData();
        if (hashMap.get(20008) != null) {
            this.n.setOpCode(((Integer) hashMap.get(20008)).intValue());
        }
        if (hashMap.get(40048) != null) {
            this.n.setReceiveTimeMillis(((Long) hashMap.get(40048)).longValue());
        }
        if (hashMap.get(40049) != null) {
            this.n.setConfigTimeJumpTime(((Integer) hashMap.get(40049)).intValue());
        }
        if (hashMap.get(40050) != null) {
            this.n.setConfigNumberJumpCount(((Integer) hashMap.get(40050)).intValue());
        }
        if (hashMap.get(40055) != null) {
            this.n.setConfigIntermittentJumpTime(((Integer) hashMap.get(40055)).intValue());
        }
        if (hashMap.get(40053) != null) {
            this.n.setConfigIntermittentJumpSingleCount(((Integer) hashMap.get(40053)).intValue());
        }
        if (hashMap.get(40056) != null) {
            this.n.setConfigIntermittentJumpResetTime(((Integer) hashMap.get(40056)).intValue());
        }
        if (hashMap.get(40054) != null) {
            this.n.setConfigIntermittentJumpCount(((Integer) hashMap.get(40054)).intValue());
        }
        if (hashMap.get(40060) != null) {
            this.n.setCourseId(((Integer) hashMap.get(40060)).intValue());
        }
        if (hashMap.get(40070) != null) {
            this.n.setMusicId(((Integer) hashMap.get(40070)).intValue());
        }
    }

    private void c(HashMap<Integer, Object> hashMap) {
        if (this.t == null) {
            this.t = new SettingStatusData();
        }
        if (hashMap.get(40042) != null) {
            this.t.setWeight(((Integer) hashMap.get(40042)).intValue());
        }
        if (hashMap.get(40043) != null) {
            this.t.setHeight(((Integer) hashMap.get(40043)).intValue());
        }
        if (hashMap.get(40044) != null) {
            this.t.setSex(((Integer) hashMap.get(40044)).intValue());
        }
        if (hashMap.get(40037) != null) {
            this.t.setSettingBestNumberSingleJumps(((Integer) hashMap.get(40037)).intValue());
        }
        if (hashMap.get(40038) != null) {
            this.t.setSettingBestNumberConsecutiveJumps(((Integer) hashMap.get(40038)).intValue());
        }
        if (hashMap.get(40039) != null) {
            this.t.setSettingBestSkippingDuration(((Integer) hashMap.get(40039)).intValue());
        }
        if (hashMap.get(40040) != null) {
            this.t.setSettingBestSkippingSpeed(((Integer) hashMap.get(40040)).intValue());
        }
    }

    private void d(HashMap<Integer, Object> hashMap) {
        if (this.r == null) {
            this.r = new SwitchStatusData();
        }
        Object obj = hashMap.get(40022);
        if (obj instanceof String) {
            this.r.setBrMacAddress((String) obj);
        }
        Object obj2 = hashMap.get(40023);
        if (obj2 instanceof Boolean) {
            this.r.setOpenBr(((Boolean) obj2).booleanValue());
        }
    }

    public void a(HashMap<Integer, Object> hashMap) {
        if (!(hashMap.get(1) instanceof cys)) {
            LogUtil.h("PDROPE_DeviceDataManager", "Get history data ERROR");
            return;
        }
        cys cysVar = (cys) hashMap.get(1);
        if (this.o == null) {
            this.o = new cym(this.p, new RopeHistoryCallback() { // from class: ddk.5
                @Override // com.huawei.health.ecologydevice.callback.RopeHistoryCallback
                public void mergeHistorySuccess(long j) {
                    ddk.this.b(j);
                }

                @Override // com.huawei.health.ecologydevice.callback.RopeHistoryCallback
                public void uploadHistorySuccess() {
                    LogUtil.c("PDROPE_DeviceDataManager", "uploadHistorySuccess");
                    ddk.this.l();
                }
            });
        }
        this.o.d(cysVar);
        Object obj = hashMap.get(0);
        if (!(obj instanceof Boolean) || ((Boolean) obj).booleanValue()) {
            return;
        }
        this.o.c();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> int a(T t) {
        if (t instanceof Integer) {
            int intValue = ((Integer) t).intValue();
            if (intValue > 100) {
                return 100;
            }
            if (intValue >= 0) {
                LogUtil.a("PDROPE_DeviceDataManager", "parseBatteryState else");
                return intValue;
            }
        }
        return 0;
    }

    private DeviceInformation e(HashMap<Integer, Object> hashMap) {
        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setDeviceType(((Integer) hashMap.get(20005)).intValue());
        deviceInformation.setManufacturerString(String.valueOf(hashMap.get(20006)));
        deviceInformation.setModelString(String.valueOf(hashMap.get(20007)));
        deviceInformation.setSerialNumber(String.valueOf(hashMap.get(20010)));
        deviceInformation.setHardwareVersion(String.valueOf(hashMap.get(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_WIFI))));
        deviceInformation.setSoftwareVersion(String.valueOf(hashMap.get(Integer.valueOf(CapabilityStatus.AWA_CAP_CODE_APPLICATION))));
        return deviceInformation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        if (this.h == null) {
            return;
        }
        HashMap hashMap = new HashMap(5);
        String t = this.h.t();
        String a2 = dks.a(t);
        hashMap.put("kind_name", this.h.l().name());
        hashMap.put("productId", t);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, a2);
        hashMap.put("data_type", 283);
        hashMap.put("duration_time", String.valueOf(j));
        hashMap.put("prodId", dij.e(t));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_ROPE_DEVICE_DATA_2170022.value(), hashMap, 0);
    }

    public void b() {
        if (TextUtils.isEmpty(this.e)) {
            LogUtil.h("PDROPE_DeviceDataManager", "connectToDevice mDeviceMacAddress isEmpty");
            return;
        }
        LogUtil.a("PDROPE_DeviceDataManager", "connectToDevice enter");
        dds.c().g();
        dds.c().b(this.g);
        t();
        dds.c().a(true, this.e);
        this.f11607a = 1;
        RopeDeviceDataListener ropeDeviceDataListener = this.i;
        if (ropeDeviceDataListener != null) {
            ropeDeviceDataListener.onNewDeviceState();
        }
    }

    private void t() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(21);
        arrayList.add(23);
        arrayList.add(20);
        arrayList.add(24);
        arrayList.add(27);
        arrayList.add(26);
        arrayList.add(25);
        dds.c().b(this.f, this, false, arrayList);
    }

    public void r() {
        dds.c().c(2, 0, null);
        LogUtil.c("PDROPE_DeviceDataManager", "CONTROL BATTERY_STATUS");
        dds.c().c(3, 7, new int[]{0});
        dds.c().d(6, 1, new int[0]);
    }

    public void q() {
        dds.c().e(this.f);
        ThreadPoolManager.d().execute(this.k);
        LogUtil.c("PDROPE_DeviceDataManager", "CONTROL QUERY DEVICE DATA");
    }

    public void p() {
        dds.c().d(1, 2, new int[0]);
        LogUtil.c("PDROPE_DeviceDataManager", "CONTROL OPEN BR DATA");
    }

    public void b(Context context, long j, long j2, int i) {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(j);
        hiSportStatDataAggregateOption.setEndTime(j2);
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{i});
        hiSportStatDataAggregateOption.setGroupUnitType(5);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Track_Walk_Calorie_Sum", "Track_Walk_Duration_Sum", "Track_Walk_Count_Sum", "Track_Walk_Step_Sum"});
        hiSportStatDataAggregateOption.setType(new int[]{3, 4, 5, 12});
        hiSportStatDataAggregateOption.setReadType(0);
        HiHealthNativeApi.a(context).aggregateSportStatData(hiSportStatDataAggregateOption, new HiAggregateListener() { // from class: ddk.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                if (koq.b(list)) {
                    LogUtil.h("PDROPE_DeviceDataManager", "requestTrackMonthData() all run data null or empty");
                    return;
                }
                ddk.this.m = list.get(0);
                ddk.this.i.onRopeMonthDataSuccess();
            }
        });
    }

    public void c(int i, int[] iArr) {
        dds.c().d(3, i, iArr);
    }

    public void i() {
        dds.c().d(6, 2, new int[0]);
    }

    public void s() {
        this.i = null;
        ThreadPoolManager.d().a(this.k);
        cym cymVar = this.o;
        if (cymVar != null) {
            cymVar.a((RopeHistoryCallback) null);
        }
        dds.c().c(this.f, false);
    }
}
