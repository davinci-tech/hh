package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.callback.FitnessCloudCallBack;
import com.huawei.datatype.FitnessUserInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hms.network.embedded.k;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.intelligent.TransferDeviceDataResponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.ActivityReminder;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.model.UserInfomation;
import defpackage.jhc;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class jgx {
    private Context c;
    private jhg d;
    private int b = 0;
    private UserProfileMgrApi e = (UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class);

    private void a() {
    }

    public jgx(Context context, jhg jhgVar) {
        this.c = context;
        this.d = jhgVar;
    }

    public void bHg_(Message message) {
        if (message == null) {
            LogUtil.h("HwFitnessManager", "handleMessage() message is null");
            return;
        }
        int i = message.what;
        if (i == 1) {
            LogUtil.a("HwFitnessManager", "handleMessage() FitnessCommandId.COMMAND_FITNESS_SET_MOTION_GOAL");
            bHe_(message);
            return;
        }
        if (i == 10001) {
            LogUtil.a("HwFitnessManager", "handleMessage() FitnessCommandId.GET_FITNESS_GET_ACTIVITY_REMINDER");
            bHd_(message);
            return;
        }
        if (i == 10010) {
            LogUtil.a("HwFitnessManager", "handleMessage() FitnessCommandId.INTELLIGENT_HOME_SLEEP");
            i();
            return;
        }
        if (i == 10011) {
            LogUtil.a("HwFitnessManager", "handleMessage() FitnessCommandId.INTELLIGENT_HOME_SLEEP_THREE");
            if (this.d.s != null) {
                this.d.s.removeMessages(10011);
            }
            this.d.g++;
            LogUtil.a("HwFitnessManager", "connectedThree :", Integer.valueOf(this.d.g));
            if (this.d.g == 3) {
                this.d.f13849a++;
                LogUtil.a("HwFitnessManager", "connected :", Integer.valueOf(this.d.f13849a));
                Message obtainMessage = this.d.s.obtainMessage();
                obtainMessage.what = 10010;
                this.d.s.sendMessageDelayed(obtainMessage, 600000L);
            }
            if (this.d.g < 3) {
                b();
                return;
            }
            return;
        }
        LogUtil.h("HwFitnessManager", "processReportData default");
    }

    private void e(final MotionGoal motionGoal) {
        LogUtil.a("FitnessManagerHelper", "handleGetUserInfo enter");
        this.e.getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: jgx.4
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                if (i == 0 && userInfomation != null) {
                    LogUtil.a("FitnessManagerHelper", "handleGetUserInfo getUserInfo onResponse userInfomation");
                    jho.d(1, userInfomation, motionGoal.getStepGoal());
                } else {
                    LogUtil.a("FitnessManagerHelper", "handleGetUserInfo userInfo is null");
                }
            }
        });
    }

    private void bHe_(Message message) {
        MotionGoal motionGoal = message.obj instanceof MotionGoal ? (MotionGoal) message.obj : null;
        if (motionGoal != null) {
            e(motionGoal);
        }
    }

    private void i() {
        if (this.d.s != null) {
            this.d.s.removeMessages(10010);
        }
        this.d.g = 0;
        if (this.d.f13849a < 3) {
            b();
        } else {
            this.d.f13849a = 0;
        }
    }

    private void bHd_(Message message) {
        JsonSyntaxException e;
        ActivityReminder activityReminder;
        if (jpt.d("FitnessManagerHelper") != null) {
            ActivityReminder activityReminder2 = new ActivityReminder();
            if (message.obj != null && (message.obj instanceof String)) {
                try {
                    String str = (String) message.obj;
                    if (str.contains(k.g)) {
                        LogUtil.a("FitnessManagerHelper", "processActivityRemind info contains enable");
                        str = str.replaceAll(k.g, "isEnable");
                    }
                    activityReminder = (ActivityReminder) new Gson().fromJson(str, ActivityReminder.class);
                } catch (JsonSyntaxException e2) {
                    e = e2;
                }
                try {
                    if (activityReminder.getEndTime() == 5120) {
                        LogUtil.a("FitnessManagerHelper", "processActivityRemind set new end time");
                        activityReminder.setEndTime(5376);
                    }
                    activityReminder2 = activityReminder;
                } catch (JsonSyntaxException e3) {
                    e = e3;
                    activityReminder2 = activityReminder;
                    LogUtil.b("FitnessManagerHelper", "processActivityRemind Exception ", ExceptionUtils.d(e));
                    LogUtil.a("03", 1, "FitnessManagerHelper", "processActivityRemind activityReminder = ", activityReminder2);
                    jqi.a().sendSetSwitchSettingCmd(jhn.a(activityReminder2), "", 7, 7);
                    f();
                }
            }
            LogUtil.a("03", 1, "FitnessManagerHelper", "processActivityRemind activityReminder = ", activityReminder2);
            jqi.a().sendSetSwitchSettingCmd(jhn.a(activityReminder2), "", 7, 7);
        } else {
            LogUtil.h("FitnessManagerHelper", "sendSetSwitchSettingCmd getConnectDeviceInfo is null");
        }
        f();
    }

    private void f() {
        jrq.b("FitnessManagerHelper", new jhc.d());
        if (e()) {
            b(6);
            this.b = 6;
        } else {
            b(1);
            this.b = 1;
        }
        m();
    }

    private void b() {
        DeviceInfo a2 = jpt.a("FitnessManagerHelper");
        if (a2 == null) {
            LogUtil.a("HwFitnessManager", "activeTransportDeviceData deviceInfo is null");
            return;
        }
        jqi a3 = jqi.a();
        if (a3 == null) {
            LogUtil.h("FitnessManagerHelper", "activeTransportDeviceData switchSettingManager is null");
        } else {
            a3.getSwitchSetting("intelligent_home_linkage", a2.getDeviceIdentify(), new IBaseResponseCallback() { // from class: jgx.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0 || obj == null) {
                        return;
                    }
                    LogUtil.a("HwFitnessManager", "activeTransportDeviceData getSwitchSetting success");
                    if (obj instanceof String) {
                        jgx.this.e((String) obj);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str) {
        if (str.contains("&&")) {
            String[] split = str.split("&&");
            LogUtil.a("HwFitnessManager", "INTELLIGENT_HOME_LINKAGE new split :", Integer.valueOf(split.length));
            if (split.length == 5) {
                String str2 = split[0];
                String str3 = split[1];
                String str4 = split[2];
                String str5 = split[3];
                String str6 = split[4];
                LogUtil.a("HwFitnessManager", "checkDeviceIdIsPermanent deviceId :", CommonUtil.l(str2), "expires :", str3, "productId :", str4, "enable :", str5, "isClick :", str6);
                if (Boolean.parseBoolean(str5) && Boolean.parseBoolean(str6)) {
                    c(str2);
                }
            }
        }
    }

    private void c(String str) {
        Message obtainMessage = this.d.s.obtainMessage();
        obtainMessage.what = 10011;
        this.d.s.sendMessageDelayed(obtainMessage, 60000L);
        jgy.e(this.c).c("sleep", "1", str, new FitnessCloudCallBack() { // from class: jgx.3
            @Override // com.huawei.callback.FitnessCloudCallBack
            public void onResponce(Object obj) {
                if (obj != null) {
                    if (!(obj instanceof TransferDeviceDataResponse)) {
                        LogUtil.h("HwFitnessManager", "transportDeviceData is success");
                        return;
                    }
                    if (((TransferDeviceDataResponse) obj).getResultCode().intValue() == 0) {
                        jgx.this.d.f13849a = 0;
                        jgx.this.d.g = 0;
                        jgx.this.d.s.removeMessages(10011);
                        LogUtil.a("HwFitnessManager", "transportDeviceData is success");
                        return;
                    }
                    LogUtil.h("HwFitnessManager", "transportDeviceData is fail");
                }
            }
        });
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090018.value(), new HashMap(16), 0);
    }

    public void b(int i, long j, DeviceInfo deviceInfo) {
        if (this.d.r != null) {
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = deviceInfo;
            this.d.r.sendMessageDelayed(obtain, j);
            return;
        }
        LogUtil.h("05", 1, "HwFitnessManager", "fitnessManagerSendMessageDelay mHwFitnessManagerHandler is null");
    }

    private void c(int i) {
        if (this.d.r != null) {
            if (this.d.r.hasMessages(i)) {
                this.d.r.removeMessages(i);
                return;
            }
            return;
        }
        LogUtil.h("05", 1, "HwFitnessManager", "fitnessManagerRemoveMessage mHwFitnessManagerHandler is null");
    }

    public void d(Object obj, DeviceInfo deviceInfo) {
        if (obj == null) {
            LogUtil.h("HwFitnessManager", "onResponse receive bluetooth data is null");
            return;
        }
        byte[] bArr = (byte[]) obj;
        if (bArr.length < 3) {
            LogUtil.h("FitnessManagerHelper", "dataInfos length is less than 3.");
            return;
        }
        LogUtil.a("HwFitnessManager", "onResponse receive bluetooth data", cvx.d(bArr));
        d(bArr, deviceInfo);
        l(bArr, deviceInfo);
        n(bArr, deviceInfo);
        c(bArr, deviceInfo);
    }

    private void d(byte[] bArr, DeviceInfo deviceInfo) {
        DeviceInfo d;
        byte b = bArr[1];
        switch (b) {
            case 1:
                c(bArr, 1);
                break;
            case 2:
                if (jhb.a() && (d = jpt.d("FitnessManagerHelper")) != null && jhb.d(d.getDeviceIdentify())) {
                    c(bArr);
                    break;
                } else {
                    c(bArr, 10002);
                    break;
                }
                break;
            case 3:
                jrb.b("FitnessManagerHelper", bArr[0], b);
                k(bArr, deviceInfo);
                break;
            case 4:
                j(bArr, deviceInfo);
                break;
            case 5:
                e(bArr, deviceInfo);
                break;
            case 6:
                h();
                break;
            case 7:
                c(bArr, 7);
                break;
            case 9:
                c(bArr, 10002);
                break;
            case 10:
                h(bArr, deviceInfo);
                break;
            case 11:
                g(bArr, deviceInfo);
                break;
        }
    }

    private void l(byte[] bArr, DeviceInfo deviceInfo) {
        switch (bArr[1]) {
            case 12:
                i(bArr, deviceInfo);
                break;
            case 13:
                f(bArr, deviceInfo);
                break;
            case 14:
                c(bArr, 14);
                break;
            case 16:
                c(bArr, 16);
                break;
            case 18:
                a();
                break;
            case 19:
                c(bArr, 19);
                break;
            case 21:
                a(bArr);
                break;
            case 22:
                d(bArr, 22);
                break;
            case 23:
                LogUtil.a("HwFitnessManager", "5.7.23 response : ", cvx.d(bArr));
                b(bArr, 23);
                break;
        }
    }

    private void n(byte[] bArr, DeviceInfo deviceInfo) {
        DeviceInfo d;
        switch (bArr[1]) {
            case 26:
                if (bArr.length > 3) {
                    byte b = bArr[2];
                    if (b != Byte.MAX_VALUE) {
                        if (b == 1) {
                            LogUtil.a("HwFitnessManager", "get command is 5.7.26");
                            jho.j(0, deviceInfo);
                            jho.g(0, deviceInfo);
                            break;
                        } else {
                            LogUtil.h("HwFitnessManager", "processOtherReplyData unknown");
                            break;
                        }
                    } else {
                        LogUtil.b("HwFitnessManager", "5.7.26 return errorCode");
                        break;
                    }
                }
                break;
            case 28:
                LogUtil.a("HwFitnessManager", "5.7.28 response : ", cvx.d(bArr));
                b(bArr, 28);
                break;
            case 29:
                LogUtil.a("HwFitnessManager", "5.7.29 response : ", cvx.d(bArr));
                b(bArr, 29);
                break;
            case 31:
                b(bArr, deviceInfo);
                break;
            case 32:
                a(bArr, deviceInfo);
                break;
            case 33:
                if (jhb.e() && (d = jpt.d("FitnessManagerHelper")) != null && jhb.d(d.getDeviceIdentify())) {
                    d(bArr);
                    break;
                } else {
                    c(bArr, 33);
                    break;
                }
        }
    }

    private void c(byte[] bArr, DeviceInfo deviceInfo) {
        byte b = bArr[1];
        if (b == 34) {
            LogUtil.a("HwFitnessManager", "5.7.34 response :", cvx.d(bArr));
            b(bArr, 34);
            return;
        }
        if (b == 41) {
            LogUtil.a("HwFitnessManager", "5.7.41 response :", cvx.d(bArr));
            return;
        }
        if (b == 36) {
            LogUtil.a("HwFitnessManager", "5.7.36 response :", cvx.d(bArr));
            b(bArr, 36);
        } else if (b == 37) {
            LogUtil.a("HwFitnessManager", "5.7.37 response :", cvx.d(bArr));
            b(bArr, 37);
        } else {
            LogUtil.h("HwFitnessManager", "processOtherReplyData default");
        }
    }

    private void c(byte[] bArr, int i) {
        LogUtil.a("05", 1, "HwFitnessManager", "processSetCmdResult Complete command :", Integer.valueOf(i));
        int i2 = 201000;
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                i2 = jru.e(bArr);
                LogUtil.b("HwFitnessManager", "processSetCmdResult return errorCode:", Integer.valueOf(i2));
            }
        } catch (cwg e) {
            LogUtil.b("FitnessManagerHelper", "processSetCmdResult Exception ", ExceptionUtils.d(e));
        }
        d(i, i2, null);
    }

    private void d(int i, int i2, Object obj) {
        LogUtil.a("05", 1, "HwFitnessManager", "processCallback callback command:", Integer.valueOf(i), "errorCode:", Integer.valueOf(i2));
        synchronized (jhg.b) {
            List<IBaseResponseCallback> list = this.d.e.get(Integer.valueOf(i));
            if (list != null) {
                int i3 = 0;
                while (true) {
                    if (list.size() <= 0) {
                        break;
                    }
                    IBaseResponseCallback iBaseResponseCallback = list.get(i3);
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(i2, obj);
                        list.remove(i3);
                        break;
                    } else {
                        list.remove(i3);
                        i3++;
                    }
                }
            }
        }
    }

    private void k(byte[] bArr, DeviceInfo deviceInfo) {
        int i;
        ReleaseLogUtil.e("Step_HwFitnessManager", "processGetTodayFitnessData Complete");
        c(4);
        int i2 = 201000;
        try {
            i = 0;
        } catch (cwg unused) {
            iyv.b(100007);
            ReleaseLogUtil.c("Step_HwFitnessManager", "processGetTodayFitnessData Exception.");
            this.d.c(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            i2 = jru.e(bArr);
            iyv.b(i2);
            ReleaseLogUtil.d("Step_HwFitnessManager", "processGetTodayFitnessData return errorCode:", Integer.valueOf(i2));
            this.d.c(i2, deviceInfo);
            i = i2;
            e(i, deviceInfo);
        }
        this.d.m.a(this.d, jhq.a(bArr), deviceInfo);
        e(i, deviceInfo);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void j(byte[] r7, com.huawei.health.devicemgr.business.entity.DeviceInfo r8) {
        /*
            r6 = this;
            r0 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.String r2 = "processGetRealTimeFrameCount"
            java.lang.String r3 = "HwFitnessManager"
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r3, r2}
            java.lang.String r2 = "05"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            r1 = 2
            r2 = 201000(0x31128, float:2.81661E-40)
            r4 = r7[r1]     // Catch: defpackage.cwg -> L4a
            r5 = 127(0x7f, float:1.78E-43)
            if (r4 != r5) goto L41
            int r7 = defpackage.jru.e(r7)     // Catch: defpackage.cwg -> L4a
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: defpackage.cwg -> L3e
            java.lang.String r2 = "procGetRealTimeFrameCount return errorCode:"
            r4 = 0
            r1[r4] = r2     // Catch: defpackage.cwg -> L3e
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)     // Catch: defpackage.cwg -> L3e
            r1[r0] = r2     // Catch: defpackage.cwg -> L3e
            com.huawei.hwlogsmodel.LogUtil.b(r3, r1)     // Catch: defpackage.cwg -> L3e
            jhg r0 = r6.d     // Catch: defpackage.cwg -> L3e
            r1 = 300007(0x493e7, float:4.204E-40)
            r0.d(r1, r8)     // Catch: defpackage.cwg -> L3e
            jhg r0 = r6.d     // Catch: defpackage.cwg -> L3e
            r0.c(r7, r8)     // Catch: defpackage.cwg -> L3e
            return
        L3e:
            r0 = move-exception
            r2 = r7
            goto L4c
        L41:
            jhg r0 = r6.d     // Catch: defpackage.cwg -> L4a
            jhz r7 = defpackage.jhq.g(r7)     // Catch: defpackage.cwg -> L4a
            r0.ad = r7     // Catch: defpackage.cwg -> L4a
            goto L61
        L4a:
            r7 = move-exception
            r0 = r7
        L4c:
            java.lang.String r7 = "processGetRealTimeFrameCount Exception "
            java.lang.String r0 = com.huawei.haf.common.exception.ExceptionUtils.d(r0)
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r0}
            java.lang.String r0 = "FitnessManagerHelper"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)
            jhg r7 = r6.d
            r0 = -1
            r7.c(r0, r8)
        L61:
            jhg r7 = r6.d
            jhz r7 = r7.ad
            if (r7 == 0) goto L78
            java.lang.String r7 = "processGetRealTimeFrameCount RealTimeFramePageList is not null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.a(r3, r7)
            jhg r7 = r6.d
            jhz r0 = r7.ad
            r7.b(r0, r8)
            goto L7d
        L78:
            jhg r7 = r6.d
            r7.b(r2, r8)
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jgx.j(byte[], com.huawei.health.devicemgr.business.entity.DeviceInfo):void");
    }

    private void e(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "processGetRealTimeCompressedData");
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int e = jru.e(bArr);
                LogUtil.b("HwFitnessManager", "processGetRealTimeCompressedData return errorCode:", Integer.valueOf(e));
                this.d.d(300007, deviceInfo);
                this.d.c(e, deviceInfo);
            } else {
                this.d.e(jhq.d(bArr));
            }
        } catch (cwg e2) {
            LogUtil.b("FitnessManagerHelper", "processGetRealTimeCompressedData Exception ", ExceptionUtils.d(e2));
            this.d.c(-1, deviceInfo);
        }
        this.d.t++;
        if (this.d.t < this.d.l) {
            jho.e(this.d.y.get(this.d.t).intValue(), deviceInfo);
        } else {
            this.d.b(0, deviceInfo);
        }
    }

    private void h() {
        LogUtil.a("05", 1, "HwFitnessManager", "processGetActivityReminder do not process");
    }

    private void h(byte[] bArr, DeviceInfo deviceInfo) {
        this.d.ac = 0;
        this.d.i = 0;
        LogUtil.a("05", 1, "HwFitnessManager", "processGetSamplePointFrameCount");
        try {
        } catch (bmk | cwg e) {
            LogUtil.b("FitnessManagerHelper", "processGetSamplePointFrameCount Exception ", ExceptionUtils.d(e));
            iyv.b(100007);
            this.d.c(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e2 = jru.e(bArr);
            LogUtil.b("HwFitnessManager", "processGetSamplePointFrameCount return errorCode:", Integer.valueOf(e2));
            iyv.b(e2);
            this.d.d(300007, deviceInfo);
            this.d.c(e2, deviceInfo);
            return;
        }
        this.d.ac = jhq.j(bArr);
        ReleaseLogUtil.e("Step_HwFitnessManager", "7-10 count : ", Integer.valueOf(this.d.ac));
        if (this.d.ac > 0) {
            LogUtil.a("05", 1, "HwFitnessManager", "processGetSamplePointFrameCount get sample frame index :", Integer.valueOf(this.d.i));
            jho.d(this.d.i, deviceInfo);
            this.d.i++;
            return;
        }
        e(deviceInfo);
    }

    private void g(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "processGetSamplePointFrame");
        try {
        } catch (bmk | cwg e) {
            LogUtil.b("FitnessManagerHelper", "processGetSamplePointFrame Exception ", ExceptionUtils.d(e));
            iyv.b(100007);
            this.d.c(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e2 = jru.e(bArr);
            iyv.b(e2);
            LogUtil.b("HwFitnessManager", "processGetSamplePointFrame return errorCode:", Integer.valueOf(e2));
            this.d.d(300007, deviceInfo);
            this.d.c(e2, deviceInfo);
            return;
        }
        this.d.ab.add(jhq.h(bArr));
        if (this.d.i < this.d.ac) {
            jho.d(this.d.i, deviceInfo);
            this.d.i++;
            return;
        }
        if (this.d.ag.b() == 2) {
            synchronized (jhg.c) {
                jim jimVar = this.d.ag;
                jhg jhgVar = this.d;
                jimVar.a(jhgVar, jhgVar.aa);
            }
        }
        e(deviceInfo);
    }

    private void i(byte[] bArr, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "processGetSamplePointFrameCount");
        this.d.af = 0;
        this.d.h = 0;
        try {
        } catch (bmk | cwg e) {
            LogUtil.b("FitnessManagerHelper", "processGetStatusFrameCount Exception ", ExceptionUtils.d(e));
            iyv.b(100007);
            this.d.c(-1, deviceInfo);
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e2 = jru.e(bArr);
            iyv.b(e2);
            LogUtil.b("HwFitnessManager", "processGetStatusFrameCount return errorCode:", Integer.valueOf(e2));
            this.d.d(300007, deviceInfo);
            this.d.c(e2, deviceInfo);
            return;
        }
        this.d.af = jhq.m(bArr);
        ReleaseLogUtil.e("DEVMGR_FitnessManagerHelper", "7-12 count : ", Integer.valueOf(this.d.af));
        if (this.d.af > 0) {
            jho.c(this.d.h, deviceInfo);
            this.d.h++;
            return;
        }
        this.d.b(0, deviceInfo);
    }

    private void f(byte[] bArr, DeviceInfo deviceInfo) {
        int i;
        LogUtil.a("05", 1, "HwFitnessManager", "processGetStatusFrame Complete");
        try {
            i = 0;
        } catch (bmk | cwg e) {
            LogUtil.b("FitnessManagerHelper", "processGetStatusFrame Exception ", ExceptionUtils.d(e));
            iyv.b(100007);
            this.d.c(-1, deviceInfo);
            i = 201000;
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            int e2 = jru.e(bArr);
            iyv.b(e2);
            LogUtil.b("HwFitnessManager", "processGetStatusFrame return errorCode:", Integer.valueOf(e2));
            this.d.d(300007, deviceInfo);
            this.d.c(e2, deviceInfo);
            return;
        }
        this.d.ai.add(jhq.i(bArr));
        if (this.d.h < this.d.af) {
            jho.c(this.d.h, deviceInfo);
            this.d.h++;
        } else {
            this.d.b(i, deviceInfo);
            if (this.d.ag.b() == 2) {
                this.d.ag.b(this.d, jhc.a(System.currentTimeMillis() / 1000));
            }
        }
    }

    private void b(byte[] bArr, DeviceInfo deviceInfo) {
        this.d.o = 0;
        this.d.j = 0;
        LogUtil.a("05", 1, "HwFitnessManager", "processGetDesFrameCount enter processGetDesFrameCount");
        try {
        } catch (cwg e) {
            LogUtil.b("FitnessManagerHelper", "processGetDesFrameCount Exception ", ExceptionUtils.d(e));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            LogUtil.b("HwFitnessManager", "processGetDesFrameCount return errorCode:", Integer.valueOf(jru.e(bArr)));
            this.d.a(300007, deviceInfo);
            return;
        }
        this.d.o = jhq.c(bArr);
        LogUtil.a("HwFitnessManager", "processGetDesFrameCount get sample frame count :", Integer.valueOf(this.d.o));
        if (this.d.o > 0) {
            LogUtil.a("05", 1, "HwFitnessManager", "processGetDesFrameCount get sample frame index :", Integer.valueOf(this.d.i), "desFrameCount Greater than zero :", Integer.valueOf(this.d.o));
            jho.b(this.d.j, deviceInfo);
            this.d.j++;
            return;
        }
        LogUtil.h("HwFitnessManager", "desFrameCount Less than zero :", Integer.valueOf(this.d.o));
        this.d.a(0, deviceInfo);
    }

    private void a(byte[] bArr, DeviceInfo deviceInfo) {
        try {
        } catch (cwg e) {
            LogUtil.b("FitnessManagerHelper", "processGetDesFrame Exception ", ExceptionUtils.d(e));
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            this.d.a(300007, deviceInfo);
            return;
        }
        this.d.k.add(jhq.e(bArr));
        if (this.d.j < this.d.o) {
            jho.b(this.d.j, deviceInfo);
            this.d.j++;
        } else {
            this.d.v = true;
            this.d.m.c(this.d.k, deviceInfo);
        }
    }

    private void a(byte[] bArr) {
        int i;
        FitnessUserInfo fitnessUserInfo;
        LogUtil.a("05", 1, "HwFitnessManager", "processGetUserInfoData Complete command:", 21);
        int i2 = 201000;
        try {
            i = 0;
        } catch (cwg e) {
            e = e;
            i = i2;
        }
        if (bArr[2] == Byte.MAX_VALUE) {
            i2 = jru.e(bArr);
            LogUtil.b("HwFitnessManager", "processSetCmdResult return errorCode:", Integer.valueOf(i2));
            i = i2;
            fitnessUserInfo = null;
            d(21, i, fitnessUserInfo);
        }
        try {
            fitnessUserInfo = jhq.n(bArr);
        } catch (cwg e2) {
            e = e2;
            LogUtil.b("FitnessManagerHelper", "processGetUserInfoData Exception ", ExceptionUtils.d(e));
            fitnessUserInfo = null;
            d(21, i, fitnessUserInfo);
        }
        d(21, i, fitnessUserInfo);
    }

    private void d(byte[] bArr, int i) {
        c(100);
        ReleaseLogUtil.e("DEVMGR_FitnessManagerHelper", "processSetCoreCmdResult Complete command :", Integer.valueOf(i));
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int e = jru.e(bArr);
                ReleaseLogUtil.e("DEVMGR_FitnessManagerHelper", "processSetCoreCmdResult return errorCode:", Integer.valueOf(e));
                if (e == 0) {
                    this.d.f = 1;
                    LogUtil.c("HwFitnessManager", "processSetCoreCmdResult button state:", Integer.valueOf(this.d.f));
                } else {
                    this.d.f = 0;
                    LogUtil.h("HwFitnessManager", "processSetCoreCmdResult button state:", Integer.valueOf(this.d.f));
                }
            }
        } catch (cwg e2) {
            LogUtil.b("FitnessManagerHelper", "processSetCoreCmdResult Exception ", ExceptionUtils.d(e2));
        }
    }

    private void b(byte[] bArr, int i) {
        ReleaseLogUtil.e("DEVMGR_FitnessManagerHelper", "printSwitchResult command :", Integer.valueOf(i));
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                ReleaseLogUtil.e("DEVMGR_FitnessManagerHelper", "printSwitchResult return errorCode:", Integer.valueOf(jru.e(bArr)));
            }
        } catch (cwg e) {
            ReleaseLogUtil.c("DEVMGR_FitnessManagerHelper", "printSwitchResult Exception ", ExceptionUtils.d(e));
        }
    }

    private boolean e() {
        int e = CommonUtil.e(new SimpleDateFormat("HH").format(new Date()), -1);
        return e >= 0 && e < 6;
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
        synchronized (jhg.c) {
            LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailDataRun enter thread isNeedSyncWork:", Boolean.valueOf(z));
            if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
                Boolean bool = false;
                if (this.d.w.containsKey(deviceInfo.getDeviceIdentify())) {
                    bool = this.d.w.get(deviceInfo.getDeviceIdentify());
                }
                if (bool != null && bool.booleanValue()) {
                    LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailData data syncing");
                    iBaseResponseCallback.d(300002, null);
                    this.d.c(10009, iBaseResponseCallback);
                    if (this.d.i()) {
                        jhc.a(this.c);
                    }
                    return;
                }
                this.d.w.put(deviceInfo.getDeviceIdentify(), true);
                this.d.ag.d(1);
                this.d.e(z, "syncFitnessDetailDataRun");
                b(0, 240000L, deviceInfo);
                this.d.c(10009, iBaseResponseCallback);
                this.d.e();
                jhg jhgVar = this.d;
                jhgVar.z = jhc.d(jhgVar);
                this.d.n = (int) (System.currentTimeMillis() / 1000);
                long a2 = jhc.a(this.d.n);
                if (this.d.ag.d(this.d) == -1) {
                    jim jimVar = this.d.ag;
                    jhg jhgVar2 = this.d;
                    jimVar.a(jhgVar2, jhc.a(jhgVar2.z));
                }
                if (jhc.a() != 3) {
                    a(deviceInfo);
                } else {
                    a(a2);
                }
                return;
            }
            LogUtil.h("05", 1, "HwFitnessManager", "syncFitnessDetailDataRun get device info error");
            iBaseResponseCallback.d(300004, null);
        }
    }

    private void a(DeviceInfo deviceInfo) {
        if (this.d.n - this.d.z > k.b.l || this.d.z == 0) {
            jhg jhgVar = this.d;
            jhgVar.z = jhc.a(jhgVar.n - k.b.l);
            jhc.e(this.d.z, this.d);
        } else if (this.d.z >= this.d.n && this.d.z - this.d.n <= 300) {
            LogUtil.a("05", 1, "HwFitnessManager", "processFitnessDetailSpecialDataFormat lastSync time is not correct.");
            jhg jhgVar2 = this.d;
            jhgVar2.z = jhgVar2.n - 61;
        } else if (this.d.z - this.d.n > 300) {
            LogUtil.a("05", 1, "HwFitnessManager", "processFitnessDetailSpecialDataFormat lastSync time is not correct and need write back.");
            jhg jhgVar3 = this.d;
            jhgVar3.z = jhgVar3.n - 61;
            jhc.e(this.d.z, this.d);
        } else {
            LogUtil.h("05", 1, "HwFitnessManager", "processFitnessDetailSpecialDataFormat unknown");
        }
        jho.b(this.d.z, this.d.n, deviceInfo);
    }

    private void a(long j) {
        if (this.d.z < this.d.n) {
            if (this.d.z < j) {
                this.d.z = j;
            }
        } else if (this.d.z >= this.d.n && this.d.z - this.d.n <= 300) {
            LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailData lastSync time is not correct.");
            jhg jhgVar = this.d;
            jhgVar.z = jhgVar.n - 61;
        } else if (this.d.z - this.d.n > 300) {
            LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailData lastSync time is not correct and need write back.");
            jhg jhgVar2 = this.d;
            jhgVar2.z = jhgVar2.n - 61;
            jhc.e(this.d.z, this.d);
        } else {
            LogUtil.h("05", 1, "HwFitnessManager", "processFitnessDetailOtherDataFormat unknown");
        }
        jho.a(this.d.z, this.d.n);
    }

    public void bHf_(Message message) {
        if (this.d == null) {
            return;
        }
        if (message == null) {
            LogUtil.h("FitnessManagerHelper", "processFitnessManagerHandler message is null");
            return;
        }
        DeviceInfo deviceInfo = message.obj instanceof DeviceInfo ? (DeviceInfo) message.obj : null;
        LogUtil.a("05", 1, "HwFitnessManager", "handleMessage message ", Integer.valueOf(message.what));
        a(message.what, deviceInfo);
    }

    private void a(int i, DeviceInfo deviceInfo) {
        if (i == 100) {
            this.d.f = 0;
            return;
        }
        if (i != 1003) {
            switch (i) {
                case 0:
                    LogUtil.b("HwFitnessManager", "sync detail timeout");
                    this.d.b(300001, deviceInfo);
                    this.d.c(0, deviceInfo);
                    break;
                case 1:
                    LogUtil.b("HwFitnessManager", "get frame count timeout");
                    break;
                case 2:
                    LogUtil.b("HwFitnessManager", "get frame timeout");
                    break;
                case 3:
                    LogUtil.a("HwFitnessManager", "Sync Complete message");
                    this.d.d(0, deviceInfo);
                    break;
                case 4:
                    LogUtil.a("HwFitnessManager", "Sync today timeout message");
                    e(300001, deviceInfo);
                    this.d.c(4, deviceInfo);
                    break;
                case 5:
                    LogUtil.a("HwFitnessManager", "Save fitness data timeout message");
                    this.d.d(300001, deviceInfo);
                    break;
                case 6:
                    this.d.h();
                    break;
                default:
                    LogUtil.h("HwFitnessManager", "unknown message type");
                    break;
            }
            return;
        }
        this.d.b(false, deviceInfo, (IBaseResponseCallback) null);
    }

    public void b(int i, DeviceInfo deviceInfo) {
        if (i == 1) {
            synchronized (jhg.c) {
                this.d.p = 1;
                this.d.ag.d(1);
                if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2) {
                    if (this.d.x) {
                        LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailData data syncing");
                        this.d.q.d(this.d.d);
                        return;
                    } else {
                        this.d.x = true;
                        b(0, 240000L, deviceInfo);
                        c();
                        o();
                        return;
                    }
                }
                LogUtil.h("FitnessManagerHelper", "syncIntensiveData deviceInfo is null or device disconnect");
                this.d.q.d(this.d.d);
                return;
            }
        }
        n();
    }

    private void o() {
        long a2 = jgz.a(this.d);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        long j = currentTimeMillis;
        long a3 = jhc.a(j);
        if (jgz.c(this.d) == -1) {
            jgz.e(this.d, jhc.a(a2));
        }
        if (a2 < j) {
            if (a2 < a3) {
                a2 = a3;
            }
        } else if (a2 >= j && a2 - j <= 300) {
            LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailData lastSync time is not correct.");
            a2 = currentTimeMillis - 61;
        } else if (a2 - j > 300) {
            LogUtil.a("05", 1, "HwFitnessManager", "syncFitnessDetailData lastSync time is not correct and need write back.");
            a2 = currentTimeMillis - 61;
            jgz.c(this.d, a2);
        } else {
            LogUtil.h("05", 1, "HwFitnessManager", "syncIntensiveOneDayOldSolutionData unknown");
        }
        LogUtil.a("05", 1, "HwFitnessManager", "mIntensiveStartTime2:", Long.valueOf(a2), ",mIntensiveEndTime", Integer.valueOf(currentTimeMillis));
        jho.b(a2, j, 0);
    }

    private void n() {
        synchronized (jhg.c) {
            this.d.p = 7;
            this.d.ag.d(2);
            long c = jgz.c(this.d);
            long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
            if (c > jhc.a(currentTimeMillis) - 14400) {
                LogUtil.a("HwFitnessManager", "status stage two already ok");
                this.d.x = false;
                return;
            }
            if (currentTimeMillis - c > k.b.l) {
                c = jhc.a(currentTimeMillis - k.b.l);
            }
            this.d.aa = currentTimeMillis;
            LogUtil.a("05", 1, "HwFitnessManager", "syncIntensiveData2 enter lastStatusTime:", Long.valueOf(c), "endTime", Long.valueOf(currentTimeMillis));
            jho.b(c, currentTimeMillis, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("FitnessManagerHelper", "handleSetUserInfo enter");
        this.e.getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: jgx.5
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                if (i == 0 && userInfomation != null) {
                    LogUtil.a("FitnessManagerHelper", "handleSetUserInfo getUserInfo onResponse userInfomation");
                    jhc.e(userInfomation);
                } else {
                    LogUtil.a("FitnessManagerHelper", "handleSetUserInfo userInfo is null");
                }
            }
        });
    }

    public void a(DeviceInfo deviceInfo, UserInfomation userInfomation) {
        boolean c = cwi.c(deviceInfo, 154);
        ReleaseLogUtil.e("Step_HwFitnessManager", "doHandleConnection isSupportCircleReminderSwitch :", Boolean.valueOf(c));
        if (!c) {
            if (b("custom.activity_reminder")) {
                LogUtil.a("FitnessManagerHelper", "connected device is family mode");
            } else {
                jqi.a().getSwitchSetting("custom.activity_reminder", new IBaseResponseCallback() { // from class: jgx.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        Message obtainMessage = jgx.this.d.s.obtainMessage();
                        obtainMessage.obj = obj;
                        obtainMessage.what = 10001;
                        jgx.this.d.s.sendMessage(obtainMessage);
                    }
                });
            }
        } else {
            f();
        }
        e(deviceInfo, userInfomation, new IBaseResponseCallback() { // from class: jgx.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i != 0) {
                    jgx.this.d();
                } else if (obj instanceof FitnessUserInfo) {
                    jgx.this.b((FitnessUserInfo) obj);
                }
            }
        });
        this.d.j();
    }

    private boolean b(String str) {
        DeviceInfo d = jpt.d("FitnessManagerHelper");
        if (d == null) {
            LogUtil.h("FitnessManagerHelper", "setLocaleStatus deviceInfo is null");
            return false;
        }
        if (!jhb.d(d.getDeviceIdentify())) {
            return false;
        }
        String b = SharedPreferenceManager.b(this.c, String.valueOf(10008), str);
        boolean parseBoolean = TextUtils.isEmpty(b) ? true : Boolean.parseBoolean(b);
        ActivityReminder activityReminder = new ActivityReminder();
        activityReminder.setEnabled(parseBoolean);
        String json = new Gson().toJson(activityReminder);
        Message obtainMessage = this.d.s.obtainMessage();
        obtainMessage.obj = json;
        obtainMessage.what = 10001;
        this.d.s.sendMessage(obtainMessage);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitnessUserInfo fitnessUserInfo) {
        long time = fitnessUserInfo.getTime();
        LogUtil.a("FitnessManagerHelper", "processGetUserInfoSuccess enter");
        final long j = time * 1000;
        this.e.getUserInfo(new BaseResponseCallback<UserInfomation>() { // from class: jgx.6
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onResponse(int i, UserInfomation userInfomation) {
                if (i == 0 && userInfomation != null) {
                    LogUtil.a("HwFitnessManager", "time :", Long.valueOf(userInfomation.getSetTime()), "watch time :", Long.valueOf(j));
                    if (userInfomation.getSetTime() > j) {
                        jhc.e(userInfomation);
                        return;
                    } else {
                        LogUtil.a("FitnessManagerHelper", "processGetUserInfoSuccess others");
                        return;
                    }
                }
                LogUtil.a("FitnessManagerHelper", "processGetUserInfoSuccess userInfo is null");
            }
        });
    }

    private void e(int i, DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("FitnessManagerHelper", "doSyncTodayComplete deviceInfo is null");
            return;
        }
        LogUtil.a("05", 1, "HwFitnessManager", "doSyncTodayComplete errorCode", Integer.valueOf(i));
        this.d.w.remove(deviceInfo.getDeviceIdentify());
        d(10008, i, null);
        j();
    }

    private void j() {
        LogUtil.a("05", 1, "HwFitnessManager", "sendTodaySyncSuccessBroadcast.");
        Intent intent = new Intent("com.huawei.bone.action.FITNESS_DATA_TODAY_SYNC");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        this.c.sendBroadcast(intent, LocalBroadcast.c);
    }

    public void e(DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManager", "syncStatusPoint current stage is ", Integer.valueOf(this.d.ag.b()));
        if (deviceInfo != null && this.d.ag.b() == 2) {
            b(deviceInfo);
            return;
        }
        long e = jgz.e(this.d);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        LogUtil.a("05", 1, "syncStatusPoint");
        long a2 = jhc.a(currentTimeMillis) - 14400;
        if (this.d.ag.e(this.d) == -1) {
            this.d.ag.b(this.d, jhc.a(e));
        }
        if (e < currentTimeMillis) {
            if (e < a2) {
                e = a2;
            }
        } else if (e >= currentTimeMillis && e - currentTimeMillis <= 300) {
            LogUtil.b("HwFitnessManager", "syncStatusPoint lastStatusTime is not correct.");
            e = currentTimeMillis - 61;
        } else if (e - currentTimeMillis > 300) {
            LogUtil.b("HwFitnessManager", "syncStatusPoint lastStatusTime is not correct and need write back.");
            e = currentTimeMillis - 61;
            jgz.d(this.d, e);
        } else {
            LogUtil.h("HwFitnessManager", "syncStatusPoint unknown");
        }
        jho.b(e, currentTimeMillis);
    }

    private void b(DeviceInfo deviceInfo) {
        this.d.ag.d(2);
        long e = this.d.ag.e(this.d);
        long currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (e > jhc.a(currentTimeMillis) - 14400) {
            LogUtil.a("HwFitnessManager", "status stage two already ok");
            this.d.w.remove(deviceInfo.getDeviceIdentify());
        } else {
            if (currentTimeMillis - e > k.b.l) {
                e = jhc.a(currentTimeMillis - k.b.l);
            }
            jho.b(e, currentTimeMillis);
        }
    }

    private void c() {
        this.d.k.clear();
    }

    public void e(DeviceInfo deviceInfo, UserInfomation userInfomation, IBaseResponseCallback iBaseResponseCallback) {
        if (deviceInfo == null) {
            LogUtil.h("FitnessManagerHelper", "getUserInfo deviceInfo is null.");
            return;
        }
        DeviceCapability e = cvs.e(deviceInfo.getDeviceIdentify());
        if (e == null) {
            LogUtil.h("05", 1, "HwFitnessManager", "getUserInfo deviceCapability is null");
            return;
        }
        if (e.isSupportGetUserInfo()) {
            LogUtil.h("05", 1, "HwFitnessManager", "getUserInfo support get user info");
            this.d.c(21, iBaseResponseCallback);
            jho.c(deviceInfo);
        } else {
            LogUtil.h("05", 1, "HwFitnessManager", "getUserInfo not support get user info");
            jhc.e(userInfomation);
        }
    }

    private void b(int i) {
        LogUtil.a("05", 1, "HwFitnessManager", "setDefaultDeviceReportThreshold.");
        DeviceCapability d = cvs.d();
        if (d == null) {
            LogUtil.h("HwFitnessManager", "setDefaultDeviceReportThreshold deviceCapability is null");
        } else if (!d.isSupportThreshold()) {
            LogUtil.h("HwFitnessManager", "setDefaultDeviceReportThreshold is not support");
        } else {
            jho.b(jid.e(i));
        }
    }

    private void m() {
        LogUtil.a("05", 1, "HwFitnessManager", "setDeviceReversDataSync enter");
        DeviceCapability d = cvs.d();
        if (d == null) {
            LogUtil.h("HwFitnessManager", "setDeviceReversDataSync deviceCapability is null");
        } else if (!d.isReserveSync()) {
            LogUtil.h("HwFitnessManager", "setDeviceReversDataSync is not support");
        } else {
            jho.e(jgz.b(this.d));
        }
    }

    private void c(byte[] bArr) {
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int e = jru.e(bArr);
                LogUtil.a("FitnessManagerHelper", "processStudentInfoFromDevice errorCode:", Integer.valueOf(e));
                if (e == 107003) {
                    LogUtil.a("FitnessManagerHelper", "processStudentInfoFromDevice no student info.");
                    jhb.a(new UserInfomation(0));
                    g();
                }
            }
            if (bArr[2] == 1) {
                jhb.a(jhq.b(bArr));
                g();
            }
        } catch (cwg e2) {
            g();
            LogUtil.b("FitnessManagerHelper", "processStudentInfoFromDevice Exception ", ExceptionUtils.d(e2));
        }
    }

    private void g() {
        DeviceInfo a2;
        if (jhb.e() && (a2 = jpt.a("FitnessManagerHelper")) != null && jhb.d(a2.getDeviceIdentify())) {
            jho.b(a2);
        }
    }

    private void d(byte[] bArr) {
        try {
            if (bArr[2] == Byte.MAX_VALUE) {
                int e = jru.e(bArr);
                LogUtil.a("FitnessManagerHelper", "processHeartDatasFromDevice errorCode:", Integer.valueOf(e));
                if (e == 107003) {
                    jhb.d();
                }
            } else {
                HeartRateThresholdConfig f = jhq.f(bArr);
                if (f == null) {
                    LogUtil.a("FitnessManagerHelper", "heartZoneConf is null");
                    jhb.d();
                } else {
                    jhb.c();
                    LogUtil.a("FitnessManagerHelper", "processHeartDatasFromDevice heartZoneConf:", f.toString());
                }
            }
        } catch (cwg unused) {
            LogUtil.b("FitnessManagerHelper", "processHeartDatasFromDevice Exception");
        }
    }
}
