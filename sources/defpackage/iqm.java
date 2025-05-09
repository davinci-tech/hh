package defpackage;

import android.content.Context;
import android.os.Parcelable;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.IRealTimeDataCallback;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.MetaData;
import com.huawei.hihealth.model.SportStatusInfo;
import com.huawei.hihealth.model.Subscriber;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.openalliance.ad.db.bean.UserCloseRecord;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class iqm extends DataObservableNoCallback<Subscriber> {
    private static Context sContext;
    private static volatile iqm sInstance;
    private DeviceInfo mConnectDeviceInfo;
    private final DataReceiveCallback statusChangeReceiver = new DataReceiveCallback() { // from class: iqk
        @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
        public final void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
            iqm.this.m971x63492917(i, deviceInfo, cvrVar);
        }
    };
    private static final String SWITCH_OPEN = cvx.e(1);
    private static final String SWITCH_CLOSE = cvx.e(0);

    /* renamed from: lambda$new$0$com-huawei-hihealthservice-hihealthkit-model-StatusChangeObservable, reason: not valid java name */
    /* synthetic */ void m971x63492917(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        ReleaseLogUtil.b("StatusChangeObservable", "receive device event:", Integer.valueOf(i));
        if (!(cvrVar instanceof cvp)) {
            ReleaseLogUtil.a("StatusChangeObservable", "message not instanceof SampleEvent.");
        } else {
            notifyDataChanged(cvrVar);
        }
    }

    public static iqm getInstance(Context context) {
        iqm iqmVar;
        synchronized (iqm.class) {
            if (sInstance == null) {
                sInstance = new iqm();
                sContext = context;
            }
            iqmVar = sInstance;
        }
        return iqmVar;
    }

    public static boolean hasObservableInit() {
        return (sInstance == null || sInstance.eventInfos.isEmpty()) ? false : true;
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback
    public boolean registerObserver(Parcelable parcelable, Subscriber subscriber) {
        if (!(parcelable instanceof SportStatusInfo)) {
            return false;
        }
        boolean registerObserver = super.registerObserver(parcelable, (Parcelable) subscriber);
        if (registerObserver && this.eventInfos.get(parcelable) != null) {
            ReleaseLogUtil.b("HiH_StatusChangeObservable", "register sport status event to device!");
            ins.a(sContext).b(getRealTimeDataCallback((EventTypeInfo) parcelable));
        }
        return registerObserver;
    }

    @Override // com.huawei.hihealthservice.hihealthkit.model.DataObservableNoCallback
    public void notifyDataChanged(Object obj) {
        LogUtil.c("StatusChangeObservable", "enter notifyDataChanged");
        cvp cvpVar = (cvp) obj;
        if (isSampleEventValid(cvpVar)) {
            List<MetaData> parseSampleEventData = parseSampleEventData(cvpVar.c());
            String name = (cvpVar.e() == 800400002 ? SportStatusInfo.Type.SPORT_START : SportStatusInfo.Type.SPORT_END).getName();
            LogUtil.c("StatusChangeObservable", "notifyDataChanged subType:", name);
            for (EventTypeInfo eventTypeInfo : this.eventInfos.keySet()) {
                if (eventTypeInfo.getSubType().equals(name)) {
                    irf.b(sContext, eventTypeInfo, (Set<Subscriber>) this.eventInfos.get(eventTypeInfo), parseSampleEventData);
                }
            }
        }
    }

    public boolean unregisterObserver(EventTypeInfo eventTypeInfo, Subscriber subscriber) {
        if (!super.unregisterObserver((Parcelable) eventTypeInfo, (EventTypeInfo) subscriber)) {
            return false;
        }
        if (isEmpty()) {
            clearObserver();
            cuk.b().unRegisterDeviceSampleListener("hw.health.healthkit");
        }
        if (!koq.b((Collection) this.eventInfos.get(eventTypeInfo))) {
            return true;
        }
        unRegisterDeviceSampleListener(eventTypeInfo);
        return true;
    }

    public void notifyConnectionStateChange(DeviceInfo deviceInfo) {
        if (deviceInfo.getDeviceConnectState() == 2) {
            ReleaseLogUtil.b("HiH_StatusChangeObservable", "connected device has changed!");
            if (!isSupportSportStatus(deviceInfo)) {
                return;
            }
            this.mConnectDeviceInfo = deviceInfo;
            Iterator<EventTypeInfo> it = this.eventInfos.keySet().iterator();
            while (it.hasNext()) {
                registerDeviceSampleListener(deviceInfo, it.next());
            }
        }
        if (deviceInfo.getDeviceConnectState() == 3) {
            this.mConnectDeviceInfo = null;
            cuk.b().unRegisterDeviceSampleListener("hw.health.healthkit");
        }
    }

    private boolean isSampleEventValid(cvp cvpVar) {
        if (cvpVar.getCommandId() != 2) {
            ReleaseLogUtil.a("HiH_StatusChangeObservable", "onDataReceived, message commandId: ", Integer.valueOf(cvpVar.getCommandId()));
            return false;
        }
        if (cvpVar.e() != 800400002 && cvpVar.e() != 800400003) {
            ReleaseLogUtil.a("HiH_StatusChangeObservable", "sampleEvent EventId is:", Long.valueOf(cvpVar.e()));
            return false;
        }
        byte[] c = cvpVar.c();
        if (c != null && c.length != 0) {
            return true;
        }
        ReleaseLogUtil.a("HiH_StatusChangeObservable", "sampleEvent data is empty.");
        return false;
    }

    private cvn getSampleConfigData(int i, String str) {
        cvn cvnVar = new cvn();
        cvnVar.e(900400004L);
        cvnVar.d(1);
        cvnVar.c(buildSingleHexData(i, str));
        return cvnVar;
    }

    private cvq getSampleConfigHeader() {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.health.healthkit");
        cvqVar.setWearPkgName("in.huawei.fitness");
        cvqVar.setCommandId(2);
        return cvqVar;
    }

    private byte[] buildSingleHexData(int i, String str) {
        return cvx.a(cvx.e(i) + cvx.d(str.length() / 2) + str);
    }

    private IRealTimeDataCallback getRealTimeDataCallback(final EventTypeInfo eventTypeInfo) {
        return new IRealTimeDataCallback.Stub() { // from class: iqm.4
            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onResult(int i) {
                LogUtil.c("StatusChangeObservable", "getDeviceList result is: ", Integer.valueOf(i));
            }

            @Override // com.huawei.hihealth.IRealTimeDataCallback
            public void onChange(int i, String str) {
                LogUtil.c("StatusChangeObservable", "getDeviceList errCode:", Integer.valueOf(i));
                if (i == 0) {
                    DeviceInfo parseConnectedDeviceInfo = iqm.this.parseConnectedDeviceInfo(str);
                    if (parseConnectedDeviceInfo != null) {
                        if (iqm.this.isSupportSportStatus(parseConnectedDeviceInfo)) {
                            iqm.this.mConnectDeviceInfo = parseConnectedDeviceInfo;
                            iqm.this.registerDeviceSampleListener(parseConnectedDeviceInfo, eventTypeInfo);
                            return;
                        } else {
                            ReleaseLogUtil.a("HiH_StatusChangeObservable", "sport not supported.");
                            return;
                        }
                    }
                    ReleaseLogUtil.a("HiH_StatusChangeObservable", "There is no connected device.");
                    return;
                }
                LogUtil.e("HiH_StatusChangeObservable", "getDeviceList errCode is not success, code: ", Integer.valueOf(i));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInfo parseConnectedDeviceInfo(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getJSONObject(i).getString("deviceInfo");
                if (new JSONObject(string).getInt("mDeviceConnectState") == 2) {
                    return (DeviceInfo) HiJsonUtil.e(string, DeviceInfo.class);
                }
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("HiH_StatusChangeObservable", "parseConnectedDeviceInfo JSONException: ", e.getMessage());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerDeviceSampleListener(DeviceInfo deviceInfo, EventTypeInfo eventTypeInfo) {
        cvn sampleConfigData;
        cuk.b().registerDeviceSampleListener("hw.health.healthkit", this.statusChangeReceiver);
        cvq sampleConfigHeader = getSampleConfigHeader();
        if (Objects.equals(eventTypeInfo.getSubType(), SportStatusInfo.Type.SPORT_START.getName())) {
            sampleConfigData = getSampleConfigData(1, SWITCH_OPEN);
        } else {
            sampleConfigData = getSampleConfigData(2, SWITCH_OPEN);
        }
        sampleConfigHeader.getConfigInfoList().add(sampleConfigData);
        if (cuk.b().sendSampleConfigCommand(deviceInfo, sampleConfigHeader, "StatusChangeObservable")) {
            return;
        }
        ReleaseLogUtil.b("HiH_StatusChangeObservable", "sendSampleConfigCommand result is false.");
        cuk.b().unRegisterDeviceSampleListener("hw.health.healthkit");
    }

    private void unRegisterDeviceSampleListener(EventTypeInfo eventTypeInfo) {
        cvn sampleConfigData;
        if (this.mConnectDeviceInfo == null) {
            ReleaseLogUtil.b("HiH_StatusChangeObservable", "do unRegister but there is no connected device now.");
            return;
        }
        cvq sampleConfigHeader = getSampleConfigHeader();
        if (Objects.equals(eventTypeInfo.getSubType(), SportStatusInfo.Type.SPORT_START.getName())) {
            sampleConfigData = getSampleConfigData(1, SWITCH_CLOSE);
        } else {
            sampleConfigData = getSampleConfigData(2, SWITCH_CLOSE);
        }
        sampleConfigHeader.getConfigInfoList().add(sampleConfigData);
        cuk.b().sendSampleConfigCommand(this.mConnectDeviceInfo, sampleConfigHeader, "StatusChangeObservable");
    }

    private List<MetaData> parseSampleEventData(byte[] bArr) {
        List<cwd> e;
        String d = cvx.d(bArr);
        LogUtil.c("StatusChangeObservable", "eventData byte size:", Integer.valueOf(bArr.length), ",data:", d);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        long j = 0;
        try {
            e = new cwl().a(d).e();
        } catch (cwg e2) {
            ReleaseLogUtil.c("HiH_StatusChangeObservable", "parseEventData tlvException: ", ExceptionUtils.d(e2));
        } catch (NumberFormatException e3) {
            ReleaseLogUtil.c("HiH_StatusChangeObservable", "parseEventData numberFormatException: ", ExceptionUtils.d(e3));
        }
        if (e != null && e.size() != 0 && e.size() == 2) {
            j = bli.c(e.get(0).c());
            i = bli.e(e.get(1).c());
            LogUtil.c("HiH_StatusChangeObservable", "timeStamp:", Long.valueOf(j), " workoutType:", Integer.valueOf(i));
            arrayList.add(new MetaData(UserCloseRecord.TIME_STAMP, String.valueOf(j)));
            arrayList.add(new MetaData(HwExerciseConstants.METHOD_PARAM_WORKOUT_TYPE, String.valueOf(i)));
            return arrayList;
        }
        ReleaseLogUtil.a("HiH_StatusChangeObservable", "event data tlvList is wrong");
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSupportSportStatus(DeviceInfo deviceInfo) {
        boolean c = cwi.c(deviceInfo, MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY);
        ReleaseLogUtil.b("HiH_StatusChangeObservable", "device connect status:", Integer.valueOf(deviceInfo.getDeviceConnectState()), ",deviceName:", deviceInfo.getDeviceName(), ",isSupportSportStatus:", Boolean.valueOf(c));
        return c;
    }
}
