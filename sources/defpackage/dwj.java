package defpackage;

import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dwj {
    private IBaseResponseCallback g;
    private boolean d = false;
    private IBaseResponseCallback j = new IBaseResponseCallback() { // from class: dwj.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Posture_BtProtocolChannel", "mOpenRunPostureCallback errorCode: ", Integer.valueOf(i), ", objData: ", obj.toString());
            if (i != 100000) {
                return;
            }
            DeviceInfo a2 = dwj.this.a();
            if (a2 == null) {
                LogUtil.h("Posture_BtProtocolChannel", "mOpenRunPostureCallback device is null.");
                return;
            }
            ffr ffrVar = (ffr) new Gson().fromJson(CommonUtil.z(obj.toString()), ffr.class);
            if (ffrVar != null) {
                dwj.this.e(ffrVar, a2);
            } else {
                LogUtil.h("Posture_BtProtocolChannel", "mOpenRunPostureCallback runPostureDataInfo null");
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f11865a = new IBaseResponseCallback() { // from class: dwj.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("HWhealthLinkage_Posture_BtProtocolChannel", "mCloseRunPostureCallback errorCode = ", Integer.valueOf(i));
        }
    };
    private IBaseResponseCallback e = new IBaseResponseCallback() { // from class: dwj.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Posture_BtProtocolChannel", "mOpenCadenceDataCallback errorCode: ", Integer.valueOf(i), ", objData: ", obj.toString());
            if (i != 100000) {
                return;
            }
            DeviceInfo a2 = dwj.this.a();
            if (a2 == null) {
                LogUtil.h("Posture_BtProtocolChannel", "mOpenCadenceDataCallback device is null.");
                return;
            }
            ffo ffoVar = (ffo) new Gson().fromJson(CommonUtil.z(obj.toString()), ffo.class);
            if (ffoVar != null) {
                dwj.this.e(ffoVar, a2);
            } else {
                LogUtil.h("Posture_BtProtocolChannel", "mOpenCadenceDataCallback runPostureDataInfo null");
            }
        }
    };
    private IBaseResponseCallback b = new IBaseResponseCallback() { // from class: dwj.1
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("HWhealthLinkage_Posture_BtProtocolChannel", "mCloseCadenceCallback errorCode = ", Integer.valueOf(i));
        }
    };
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: dwj.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Posture_BtProtocolChannel", "mAw70LinkReportStatusCallback errCode is ", Integer.valueOf(i));
            if (i != 100000) {
                return;
            }
            if (obj == null) {
                LogUtil.h("Posture_BtProtocolChannel", "mAw70LinkReportStatusCallback objData is null");
                return;
            }
            try {
                if (obj instanceof String) {
                    JSONArray jSONArray = new JSONArray((String) obj);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        dwj.this.g.d(100000, jSONArray.getJSONObject(i2));
                    }
                }
            } catch (JSONException e) {
                ReleaseLogUtil.c("HWhealthLinkage_Posture_BtProtocolChannel", "get mAw70LinkReportStatusCallback error: ", ExceptionUtils.d(e));
                sqo.w("mAw70LinkReportStatusCallback JSONException:" + e.getMessage());
            }
        }
    };

    public void e(int i, int i2) {
        b(i, i2, this.j);
    }

    public void c(int i, int i2) {
        b(i, i2, this.f11865a);
    }

    public void a(final int i, final int i2) {
        dwo.d().d(new IBaseResponseCallback() { // from class: dwj.9
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                LogUtil.a("Posture_BtProtocolChannel", "openRidePosture onResponse errCode:", Integer.valueOf(i3), "objData", obj);
                if (i3 == 100000 && "true".equals(obj)) {
                    dwj dwjVar = dwj.this;
                    dwjVar.b(i, i2, dwjVar.e);
                }
            }
        });
    }

    public void d(int i, int i2) {
        b(i, i2, this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceInfo a() {
        DeviceInfo d = dug.c().d();
        if (d != null && !this.d) {
            dug.c().c(Collections.singletonList(d));
            this.d = true;
        }
        return d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Posture_BtProtocolChannel", "changePostureReportStatus, 1 & 3 means open, 2 & 4 means close, sportStatus = ", Integer.valueOf(i));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i);
            jSONObject.put(BleConstants.SPORT_TYPE, i2);
        } catch (JSONException e) {
            ReleaseLogUtil.c("HWhealthLinkage_Posture_BtProtocolChannel", "changePostureReportStatus exception: ", ExceptionUtils.d(e));
            sqo.w("changePostureReportStatus exception:" + e.getMessage());
        }
        this.g = iBaseResponseCallback;
        dwo.d().d(jSONObject, this.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Object obj, DeviceInfo deviceInfo) {
        Map<Integer, Double> e = dwi.e(obj);
        if (e.isEmpty()) {
            ReleaseLogUtil.d("HWhealthLinkage_Posture_BtProtocolChannel", "parseData error, fieldsValue is empty.");
            return;
        }
        dug.c().e(deviceInfo, HiJsonUtil.e(e), dwi.e(e.keySet()));
    }
}
