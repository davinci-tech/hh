package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jps {
    private static cvq d(String str) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.health.emotion");
        cvqVar.setWearPkgName("hw.watch.health.emotion");
        cvqVar.setCommandId(1);
        cvn cvnVar = new cvn();
        cvnVar.e(900500028L);
        cvnVar.c(cvx.a(cvx.e(1) + cvx.e(1) + cvx.e(Integer.parseInt(str))));
        cvnVar.d(1);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str) {
        DeviceInfo a2 = jpt.a("EmotionAutoMonitorUtils");
        if (a2 == null || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("DEVMGR_EmotionAutoMonitorUtils", "sendSettingDeviceCommand, deviceInfo is null or switchStatus is empty.");
        } else {
            if (a2.getDeviceConnectState() != 2) {
                ReleaseLogUtil.d("DEVMGR_EmotionAutoMonitorUtils", "device is not connected.");
                return;
            }
            cvq d = d(str);
            ReleaseLogUtil.e("DEVMGR_EmotionAutoMonitorUtils", "constructSampleConfig: ", d);
            cuk.b().sendSampleConfigCommand(a2, d, "EmotionAutoMonitorUtils");
        }
    }

    public static void b(final String str) {
        njj.a("9005", "900500028", dsl.e("emotionAutoMonitorSwitch", str), new HiDataOperateListener() { // from class: jps.5
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_EmotionAutoMonitorUtils", "setEmotionAutoMonitorSwitch setSampleConfig errorCode: ", Integer.valueOf(i));
                if (i == 0) {
                    jps.c(str);
                    jps.e();
                }
            }
        }, System.currentTimeMillis());
    }

    public static void a(String str) {
        njj.a("9003", "900300049", dsl.e("emotionRiskTipSwitch", str), new HiDataOperateListener() { // from class: jps.2
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                ReleaseLogUtil.e("DEVMGR_EmotionAutoMonitorUtils", "setEmotionRiskWarningSwitch setSampleConfig errorCode: ", Integer.valueOf(i));
                if (i == 0) {
                    jps.e();
                }
            }
        }, System.currentTimeMillis());
    }

    public static void e(final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900500028");
        njj.d("9005", arrayList, new HiDataReadResultListener() { // from class: jps.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x003f, code lost:
            
                if (android.text.TextUtils.isEmpty(r3) != false) goto L9;
             */
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResult(java.lang.Object r3, int r4, int r5) {
                /*
                    r2 = this;
                    java.lang.String r5 = "getEmotionAutoMonitorSwitch onResult errorCode: "
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
                    java.lang.Object[] r5 = new java.lang.Object[]{r5, r0}
                    java.lang.String r0 = "DEVMGR_EmotionAutoMonitorUtils"
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r5)
                    java.lang.Class<com.huawei.hihealth.HiSampleConfig> r5 = com.huawei.hihealth.HiSampleConfig.class
                    boolean r5 = defpackage.koq.e(r3, r5)
                    r1 = 0
                    if (r5 == 0) goto L41
                    if (r4 != 0) goto L41
                    java.util.List r3 = (java.util.List) r3
                    boolean r4 = defpackage.koq.b(r3)
                    if (r4 != 0) goto L41
                    java.lang.Object r3 = r3.get(r1)
                    com.huawei.hihealth.HiSampleConfig r3 = (com.huawei.hihealth.HiSampleConfig) r3
                    java.lang.String r3 = r3.getConfigData()
                    java.lang.String r4 = "emotionAutoMonitorSwitch"
                    java.lang.String r3 = defpackage.dsl.c(r3, r4)
                    java.lang.String r4 = "getEmotionAutoMonitorSwitch switchStatus :"
                    java.lang.Object[] r4 = new java.lang.Object[]{r4, r3}
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r4)
                    boolean r4 = android.text.TextUtils.isEmpty(r3)
                    if (r4 == 0) goto L43
                L41:
                    java.lang.String r3 = "1"
                L43:
                    com.huawei.hwbasemgr.IBaseResponseCallback r4 = com.huawei.hwbasemgr.IBaseResponseCallback.this
                    r4.d(r1, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.jps.AnonymousClass3.onResult(java.lang.Object, int, int):void");
            }
        });
    }

    public static void b(final IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("900300049");
        njj.d("9003", arrayList, new HiDataReadResultListener() { // from class: jps.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x003f, code lost:
            
                if (android.text.TextUtils.isEmpty(r3) != false) goto L9;
             */
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResult(java.lang.Object r3, int r4, int r5) {
                /*
                    r2 = this;
                    java.lang.String r5 = "getEmotionRiskWarningSwitch onResult errorCode: "
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
                    java.lang.Object[] r5 = new java.lang.Object[]{r5, r0}
                    java.lang.String r0 = "DEVMGR_EmotionAutoMonitorUtils"
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r5)
                    java.lang.Class<com.huawei.hihealth.HiSampleConfig> r5 = com.huawei.hihealth.HiSampleConfig.class
                    boolean r5 = defpackage.koq.e(r3, r5)
                    r1 = 0
                    if (r5 == 0) goto L41
                    if (r4 != 0) goto L41
                    java.util.List r3 = (java.util.List) r3
                    boolean r4 = defpackage.koq.b(r3)
                    if (r4 != 0) goto L41
                    java.lang.Object r3 = r3.get(r1)
                    com.huawei.hihealth.HiSampleConfig r3 = (com.huawei.hihealth.HiSampleConfig) r3
                    java.lang.String r3 = r3.getConfigData()
                    java.lang.String r4 = "emotionRiskTipSwitch"
                    java.lang.String r3 = defpackage.dsl.c(r3, r4)
                    java.lang.String r4 = "getEmotionRiskWarningSwitch switchStatus :"
                    java.lang.Object[] r4 = new java.lang.Object[]{r4, r3}
                    health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r0, r4)
                    boolean r4 = android.text.TextUtils.isEmpty(r3)
                    if (r4 == 0) goto L43
                L41:
                    java.lang.String r3 = "0"
                L43:
                    com.huawei.hwbasemgr.IBaseResponseCallback r4 = com.huawei.hwbasemgr.IBaseResponseCallback.this
                    r4.d(r1, r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: defpackage.jps.AnonymousClass1.onResult(java.lang.Object, int, int):void");
            }
        });
    }

    public static void c(final IBaseResponseCallback iBaseResponseCallback) {
        jqi.a().getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: jpq
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jps.c(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.d("EmotionAutoMonitorUtils", "getStressAutoMonitorSwitch: ", Integer.valueOf(i), obj);
        if (i == 0 && (obj instanceof String)) {
            if ("true".equals((String) obj)) {
                iBaseResponseCallback.d(i, "1");
                return;
            } else {
                iBaseResponseCallback.d(i, "0");
                return;
            }
        }
        iBaseResponseCallback.d(-1, "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }
}
