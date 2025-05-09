package defpackage;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gsy;
import health.compact.a.HEXUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dwg implements DataReceiveCallback {
    private boolean d = false;
    private boolean b = false;

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        if (i != 100000) {
            ReleaseLogUtil.d("HWhealthLinkage_Posture_SamplePointChannel", "onDataReceived errCode is error");
            return;
        }
        if (deviceInfo == null || cvrVar == null) {
            ReleaseLogUtil.d("HWhealthLinkage_Posture_SamplePointChannel", "onDataReceived deviceInfo or sampleBase is null.");
            return;
        }
        String srcPkgName = cvrVar.getSrcPkgName();
        String wearPkgName = cvrVar.getWearPkgName();
        LogUtil.a("Posture_SamplePointChannel", "onDataReceived srcPkgNam: ", srcPkgName, ", wearPkgName:", wearPkgName);
        if ("hw.bolt.linkage".equals(srcPkgName) && "hw.sport.linkage".equals(wearPkgName)) {
            e(deviceInfo, cvrVar);
        } else if ("hw.linkage.watch.posture.device".equals(srcPkgName) && "hw.linkage.watch.posture.app".equals(wearPkgName)) {
            d(deviceInfo, cvrVar);
        }
    }

    public void aan_(Bundle bundle, DeviceInfo deviceInfo) {
        LogUtil.a("Posture_SamplePointChannel", "sendSamplePointToDevice");
        if (bundle == null) {
            return;
        }
        cvu cvuVar = new cvu();
        cvuVar.c(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA.value());
        cvuVar.d(new Date().getTime());
        cvuVar.b(new Date().getTime());
        cvuVar.setSrcPkgName("hw.linkage.watch.posture.app");
        cvuVar.setWearPkgName("hw.linkage.watch.posture.device");
        cvuVar.e(aam_(bundle));
        cuk.b().sendSamplePointCommand(deviceInfo, cvuVar, "Posture_SamplePointChannel");
    }

    private void e(DeviceInfo deviceInfo, cvr cvrVar) {
        if (cvrVar instanceof cvp) {
            c((cvp) cvrVar, deviceInfo);
            return;
        }
        if (cvrVar instanceof cvu) {
            cvu cvuVar = (cvu) cvrVar;
            LogUtil.a("Posture_SamplePointChannel", "mDataReceiveCallback bolt samplePoint = ", cvuVar);
            List<cvv> a2 = cvuVar.a();
            if (koq.b(a2)) {
                ReleaseLogUtil.d("HWhealthLinkage_Posture_SamplePointChannel", "mDataReceiveCallback bolt dataInfoList is empty");
            } else {
                d(deviceInfo, a2);
            }
        }
    }

    private void d(DeviceInfo deviceInfo, cvr cvrVar) {
        if (cvrVar instanceof cvu) {
            cvu cvuVar = (cvu) cvrVar;
            LogUtil.a("Posture_SamplePointChannel", "onDataReceived watch samplePoint = ", cvuVar);
            List<cvv> a2 = cvuVar.a();
            if (koq.b(a2)) {
                ReleaseLogUtil.d("HWhealthLinkage_Posture_SamplePointChannel", "onDataReceived watch dataInfoList is empty.");
                return;
            }
            Map<Integer, Double> c = dwi.c(a2);
            if (c.isEmpty()) {
                ReleaseLogUtil.d("HWhealthLinkage_Posture_SamplePointChannel", "onDataReceived watch fieldsValue is empty.");
                return;
            }
            if (!this.d) {
                jpp.i(deviceInfo);
                this.d = true;
            }
            dug.c().e(deviceInfo, HiJsonUtil.e(c), dwi.e(c.keySet()));
        }
    }

    private List<cvv> aam_(Bundle bundle) {
        HiHealthDictField d;
        ArrayList arrayList = new ArrayList(10);
        for (String str : dwi.c) {
            if (bundle.containsKey(str) && (d = HiHealthDictManager.d((Context) null).d(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA.value(), str)) != null) {
                int c = d.c();
                cvv cvvVar = new cvv();
                cvvVar.d(c);
                if (dwi.f11864a.contains(Integer.valueOf(c))) {
                    int intValue = ((Integer) bundle.get(str)).intValue();
                    if (c != DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_EVERSION.value() || intValue != -101) {
                        if (intValue != -1) {
                            cvvVar.b(HEXUtils.c(HEXUtils.e(intValue)));
                            arrayList.add(cvvVar);
                            LogUtil.a("Posture_SamplePointChannel", "samplePoint send to device key:", Long.valueOf(cvvVar.a()), ", value: ", HEXUtils.a(cvvVar.d()));
                        }
                    }
                } else if (dwi.b.contains(Integer.valueOf(c))) {
                    float floatValue = ((Float) bundle.get(str)).floatValue();
                    if (Float.compare(floatValue, -1.0f) != 0) {
                        if (c == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_VERTICAL_OSCILLATION.value()) {
                            cvvVar.b(HEXUtils.c(HEXUtils.e((int) (floatValue * 10.0f))));
                            arrayList.add(cvvVar);
                        } else if (c == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_GC_TIME_BALANCE.value()) {
                            cvvVar.b(HEXUtils.c(HEXUtils.e((int) (floatValue * 100.0f))));
                            arrayList.add(cvvVar);
                        } else if (c == DicDataTypeUtil.DataType.REALTIME_SPORT_VERTICAL_RATIO.value()) {
                            cvvVar.b(HEXUtils.c(HEXUtils.e((int) (floatValue * 10.0f))));
                            arrayList.add(cvvVar);
                        } else {
                            cvvVar.b(HEXUtils.c(HEXUtils.b(floatValue)));
                            arrayList.add(cvvVar);
                            LogUtil.a("Posture_SamplePointChannel", "samplePoint send to device key:", Long.valueOf(cvvVar.a()), ", value: ", HEXUtils.a(cvvVar.d()));
                        }
                    }
                } else {
                    if (dwi.e.contains(Integer.valueOf(c))) {
                        double doubleValue = ((Double) bundle.get(str)).doubleValue();
                        if (Double.compare(doubleValue, -1.0d) != 0) {
                            if (c == DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_FLIGHT_RATIO.value()) {
                                cvvVar.b(HEXUtils.c(HEXUtils.e((int) (doubleValue * 100.0d))));
                                arrayList.add(cvvVar);
                            } else {
                                cvvVar.b(HEXUtils.c(HEXUtils.a(doubleValue)));
                                arrayList.add(cvvVar);
                            }
                        }
                    } else {
                        LogUtil.a("Posture_SamplePointChannel", "sendData null");
                    }
                    LogUtil.a("Posture_SamplePointChannel", "samplePoint send to device key:", Long.valueOf(cvvVar.a()), ", value: ", HEXUtils.a(cvvVar.d()));
                }
            }
        }
        return arrayList;
    }

    private void c(cvp cvpVar, DeviceInfo deviceInfo) {
        if (cvpVar.e() == 800100001) {
            LogUtil.a("Posture_SamplePointChannel", "mDataReceiveCallback: sampleEvent = ", cvpVar);
            a();
            Iterator<IBaseResponseCallback> it = duk.a().e().values().iterator();
            while (it.hasNext()) {
                it.next().d(0, deviceInfo);
            }
        }
    }

    private void a() {
        String string;
        LogUtil.a("Posture_SamplePointChannel", "showBoltNotification...");
        Context context = BaseApplication.getContext();
        String e = cwa.e(554, context, context.getPackageName());
        if (LanguageUtil.m(context)) {
            string = context.getResources().getString(R.string._2130840099_res_0x7f020a23, e);
        } else {
            string = context.getResources().getString(R.string._2130840020_res_0x7f0209d4, e);
        }
        jdh.c().xh_(ggl.c(), new NotificationCompat.Builder(context, "channel_common_id").setContentText(string).setSmallIcon(R.drawable.healthlogo_ic_notification).setContentIntent(jdh.bFr_(context)).setStyle(new NotificationCompat.BigTextStyle().bigText(string)).setAutoCancel(true).build());
    }

    private void d(DeviceInfo deviceInfo, List<cvv> list) {
        Double d;
        Map<Integer, Double> c = dwi.c(list);
        if (c.isEmpty()) {
            LogUtil.h("Posture_SamplePointChannel", "onDataReceived: fieldsValue is empty.");
            return;
        }
        if (!this.b) {
            dug.c().c(duk.a().c());
            this.b = true;
        }
        String e = HiJsonUtil.e(c);
        String e2 = dwi.e(c.keySet());
        if (duk.a().b() == 259) {
            dug.c().e(deviceInfo, e, e2);
        } else if (!c.containsKey(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WALK_OR_RUN.value())) || ((d = c.get(Integer.valueOf(DicDataTypeUtil.DataType.REALTIME_SPORT_DATA_WALK_OR_RUN.value()))) != null && d.equals(Double.valueOf(1.0d)))) {
            dug.c().e(deviceInfo, e, e2);
        }
    }

    public void d(int i, int i2) {
        LogUtil.a("Posture_SamplePointChannel", "changeBoltPostureReportStatus, 1 & 3 means open, 2 & 4 means close, sportStatus = ", Integer.valueOf(i));
        cvq c = c(i2, i);
        Iterator<gsy.b> it = duk.a().d().iterator();
        while (it.hasNext()) {
            DeviceInfo e = it.next().e();
            LogUtil.a("Posture_SamplePointChannel", "sendSampleConfig result = " + cuk.b().sendSampleConfigCommand(e, c, "Posture_SamplePointChannel") + ", deviceName = " + e.getDeviceName());
        }
    }

    private cvq c(final int i, final int i2) {
        return new cvq() { // from class: dwg.4
            {
                setSrcPkgName("hw.sport.linkage");
                setWearPkgName("hw.bolt.linkage");
                setConfigInfoList(Collections.singletonList(new cvn() { // from class: dwg.4.3
                    {
                        e(900200001L);
                        d(i2);
                        c(HEXUtils.c(HEXUtils.c(SportSupportUtil.d(i))));
                    }
                }));
            }
        };
    }
}
