package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.up.utils.ErrorCode;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class jiq extends HwBaseManager implements BluetoothDataReceiveCallback {
    private static jiq d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f13877a;
    private Context b;
    private IBaseResponseCallback c;
    private jfq j;

    private jiq(Context context) {
        super(context);
        this.f13877a = new BroadcastReceiver() { // from class: jiq.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (context2 != null) {
                    LogUtil.a("HwFitnessPostureManager", "mConnectStateChangedReceiver() action is ", intent.getAction());
                    if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                        DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                        if (jiq.this.c == null) {
                            LogUtil.h("HwFitnessPostureManager", "mCallback is null");
                            return;
                        }
                        if (deviceInfo != null) {
                            LogUtil.a("HwFitnessPostureManager", "mConnectStateChangedReceiver() status is ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                            int deviceConnectState = deviceInfo.getDeviceConnectState();
                            if (deviceConnectState == 2) {
                                jiq.this.c.d(100000, 100001);
                            } else if (deviceConnectState == 3) {
                                jiq.this.c.d(100000, Integer.valueOf(ErrorCode.HWID_IS_STOPED));
                            } else {
                                LogUtil.h("HwFitnessPostureManager", "mConnectStateChangedReceiver() default");
                            }
                        }
                    }
                }
            }
        };
        this.b = context;
        jfq c = jfq.c();
        this.j = c;
        if (c != null) {
            c.e(36, this);
        } else {
            LogUtil.h("HwFitnessPostureManager", "HWDeviceFontManager() mHwDeviceConfigManager is null");
        }
        BroadcastManagerUtil.bFC_(this.b, this.f13877a, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void k(byte[] bArr) {
        byte b = bArr[1];
        if (b == 1) {
            this.c.d(1, Integer.valueOf(h(bArr)));
        }
        switch (b) {
            case 4:
                this.c.d(4, d(bArr));
                break;
            case 5:
                this.c.d(5, e(bArr));
                break;
            case 6:
                this.c.d(6, a(bArr));
                break;
            case 7:
                this.c.d(7, b(bArr));
                break;
            case 8:
                this.c.d(8, Integer.valueOf(f(bArr)));
                break;
            case 9:
                this.c.d(9, Integer.valueOf(i(bArr)));
                break;
            case 10:
                this.c.d(10, g(bArr));
                break;
            case 11:
                this.c.d(11, j(bArr));
                break;
            default:
                LogUtil.h("HwFitnessPostureManager", "responseCallbackCase default");
                break;
        }
    }

    public static jiq a(Context context) {
        jiq jiqVar;
        synchronized (e) {
            if (d == null) {
                LogUtil.h("HwFitnessPostureManager", "getInstance() context ", context);
                d = new jiq(BaseApplication.getContext());
            }
            jiqVar = d;
        }
        return jiqVar;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 36;
    }

    public void b(int i, int i2, int i3) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(36);
        deviceCommand.setCommandID(i);
        StringBuffer stringBuffer = new StringBuffer(16);
        if (i == 7) {
            stringBuffer.append(c(1, i2, 2));
            stringBuffer.append(c(2, i3, 2));
        } else if (i == 5) {
            stringBuffer.append(c(1, i2, 2));
            stringBuffer.append(c(2, i3, 2));
        } else {
            LogUtil.h("HwFitnessPostureManager", "sendRequireRecord() no match");
        }
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        LogUtil.a("HwFitnessPostureManager", "sendRequireRecord(): Command ", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
        jfq jfqVar = this.j;
        if (jfqVar != null) {
            jfqVar.b(deviceCommand);
        } else {
            LogUtil.h("HwFitnessPostureManager", "mHwDeviceConfigManager is null.");
        }
    }

    public void a(int i, int i2) {
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(36);
        deviceCommand.setCommandID(12);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(c(1, i, 2));
        stringBuffer.append(c(127, i2, 2));
        deviceCommand.setDataLen(stringBuffer.length() / 2);
        deviceCommand.setDataContent(cvx.a(stringBuffer.toString()));
        LogUtil.a("HwFitnessPostureManager", "sendReturnValue(): Command ", cvx.e(deviceCommand.getServiceID()), cvx.e(deviceCommand.getCommandID()), cvx.d(deviceCommand.getDataContent()));
        jfq jfqVar = this.j;
        if (jfqVar != null) {
            jfqVar.b(deviceCommand);
        } else {
            LogUtil.h("HwFitnessPostureManager", "mHwDeviceConfigManager is null.");
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("HwFitnessPostureManager", "registerDataCallback");
        this.c = iBaseResponseCallback;
    }

    public void a() {
        LogUtil.a("HwFitnessPostureManager", "unregisterDataCallback");
        this.c = null;
    }

    private List<ckf> b(byte[] bArr) {
        LogUtil.a("HwFitnessPostureManager", "Enter resolveCourseRecordList");
        ArrayList arrayList = new ArrayList(10);
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolveCourseRecordList dataContent is null");
            return new ArrayList(10);
        }
        String d2 = cvx.d(bArr);
        try {
            Iterator<cwe> it = new cwl().a(d2.substring(4, d2.length())).a().iterator();
            while (it.hasNext()) {
                for (cwe cweVar : it.next().a()) {
                    ckf ckfVar = new ckf();
                    List<cwd> e2 = cweVar.e();
                    List<cwe> a2 = cweVar.a();
                    a(e2, ckfVar);
                    ArrayList arrayList2 = new ArrayList(10);
                    Iterator<cwe> it2 = a2.iterator();
                    while (it2.hasNext()) {
                        c(it2.next().a(), arrayList2);
                    }
                    ckfVar.d(arrayList2);
                    LogUtil.c("HwFitnessPostureManager", "courseRecord:", ckfVar.toString());
                    arrayList.add(ckfVar);
                }
            }
            return arrayList;
        } catch (cwg e3) {
            LogUtil.b("HwFitnessPostureManager", "resolveCourseRecordList Exception : ", ExceptionUtils.d(e3));
            return new ArrayList(10);
        }
    }

    private void c(List<cwe> list, List<ckd> list2) {
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            List<cwd> e2 = it.next().e();
            ckd ckdVar = new ckd();
            for (cwd cwdVar : e2) {
                if (cwdVar != null) {
                    try {
                        int parseInt = Integer.parseInt(cwdVar.e(), 16);
                        LogUtil.c("HwFitnessPostureManager", "resolveCourseRecordList case2:", Integer.valueOf(parseInt), ",value2:", cwdVar.c());
                        switch (parseInt) {
                            case 26:
                                ckdVar.e(cvx.e(cwdVar.c()));
                                break;
                            case 27:
                                ckdVar.b(Integer.parseInt(cwdVar.c(), 16));
                                break;
                            case 28:
                                ckdVar.d(Integer.parseInt(cwdVar.c(), 16));
                                break;
                            case 29:
                                e(Integer.parseInt(cwdVar.c(), 16), ckdVar);
                                break;
                            default:
                                Object[] objArr = new Object[1];
                                objArr[0] = "resolveCourseRecordListTlvChildFatherForEach default";
                                LogUtil.h("HwFitnessPostureManager", objArr);
                                break;
                        }
                    } catch (NumberFormatException e3) {
                        LogUtil.b("HwFitnessPostureManager", "childFatherForEach Exception : ", ExceptionUtils.d(e3));
                    }
                }
            }
            list2.add(ckdVar);
        }
    }

    private void e(int i, ckd ckdVar) {
        if (i == 0) {
            ckdVar.d("beating");
        } else if (i == 1) {
            ckdVar.d("timer");
        } else {
            LogUtil.c("HwFitnessPostureManager", "resolveCourseRecordListTlvChildFatherForEach unhandled type:", Integer.valueOf(i));
        }
    }

    private void a(List<cwd> list, ckf ckfVar) {
        for (cwd cwdVar : list) {
            if (cwdVar != null) {
                try {
                    int parseInt = Integer.parseInt(cwdVar.e(), 16);
                    LogUtil.c("HwFitnessPostureManager", "resolveCourseRecordList case is ", Integer.valueOf(parseInt), ",tlv.getValue is ", cwdVar.c());
                    switch (parseInt) {
                        case 5:
                            ckfVar.a(cvx.e(cwdVar.c()));
                            continue;
                        case 6:
                            ckfVar.e(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 7:
                            ckfVar.c(cvx.e(cwdVar.c()));
                            continue;
                        case 8:
                            ckfVar.m(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 9:
                            ckfVar.i(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 10:
                            ckfVar.o(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 11:
                            ckfVar.l(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 12:
                            ckfVar.b(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 13:
                            ckfVar.d(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        case 14:
                            ckfVar.j(Integer.parseInt(cwdVar.c(), 16));
                            continue;
                        default:
                            d(parseInt, cwdVar, ckfVar);
                            continue;
                    }
                } catch (NumberFormatException e2) {
                    LogUtil.b("HwFitnessPostureManager", "resolveCourseRecordList Exception : ", ExceptionUtils.d(e2));
                }
                LogUtil.b("HwFitnessPostureManager", "resolveCourseRecordList Exception : ", ExceptionUtils.d(e2));
            }
        }
    }

    private void d(int i, cwd cwdVar, ckf ckfVar) {
        switch (i) {
            case 15:
                ckfVar.f(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 16:
                ckfVar.h(jds.c(cwdVar.c(), 16));
                break;
            case 17:
                ckfVar.c(jds.c(cwdVar.c(), 16));
                break;
            case 18:
                ckfVar.a(jds.c(cwdVar.c(), 16));
                break;
            case 19:
                ckfVar.g(jds.c(cwdVar.c(), 16));
                break;
            case 20:
                ckfVar.k(jds.c(cwdVar.c(), 16));
                break;
            case 21:
                ckfVar.d(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 22:
                ckfVar.n(jds.c(cwdVar.c(), 16));
                break;
            case 23:
                ckfVar.e(cvx.e(cwdVar.c()));
                break;
            default:
                LogUtil.h("HwFitnessPostureManager", "resolveCourseRecordListForEachCase default");
                break;
        }
    }

    private ckf a(byte[] bArr) {
        ckf ckfVar = new ckf();
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolveCourseRecord dataContent is null");
            return null;
        }
        String d2 = cvx.d(bArr);
        try {
            cwe a2 = new cwl().a(d2.substring(4, d2.length()));
            List<cwd> e2 = a2.e();
            List<cwe> a3 = a2.a();
            for (cwd cwdVar : e2) {
                if (cwdVar != null) {
                    try {
                        int parseInt = Integer.parseInt(cwdVar.e(), 16);
                        LogUtil.c("HwFitnessPostureManager", "resolveCourseRecord case is ", Integer.valueOf(parseInt), ", value is ", cwdVar.c());
                        if (parseInt == 1) {
                            ckfVar.a(cvx.e(cwdVar.c()));
                        } else if (parseInt == 2) {
                            ckfVar.e(Integer.parseInt(cwdVar.c(), 16));
                        } else {
                            a(parseInt, cwdVar, ckfVar);
                        }
                    } catch (NumberFormatException e3) {
                        LogUtil.b("HwFitnessPostureManager", "resolveCourseRecord number exception : ", ExceptionUtils.d(e3));
                    }
                }
            }
            ckfVar.d(a(a3));
            LogUtil.a("HwFitnessPostureManager", "courseRecord :", ckfVar.toString());
            return ckfVar;
        } catch (cwg e4) {
            LogUtil.b("HwFitnessPostureManager", "resolveCourseRecord Exception : ", ExceptionUtils.d(e4));
            return null;
        }
    }

    private void a(int i, cwd cwdVar, ckf ckfVar) {
        switch (i) {
            case 3:
                ckfVar.c(cvx.e(cwdVar.c()));
                break;
            case 4:
                ckfVar.m(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 5:
                ckfVar.i(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 6:
                ckfVar.o(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 7:
                ckfVar.l(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 8:
                ckfVar.b(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 9:
                ckfVar.d(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 10:
                ckfVar.j(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 11:
                ckfVar.f(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 12:
                ckfVar.h(jds.c(cwdVar.c(), 16));
                break;
            case 13:
                ckfVar.c(jds.c(cwdVar.c(), 16));
                break;
            case 14:
                ckfVar.a(jds.c(cwdVar.c(), 16));
                break;
            case 15:
                ckfVar.g(jds.c(cwdVar.c(), 16));
                break;
            case 16:
                ckfVar.k(jds.c(cwdVar.c(), 16));
                break;
            default:
                c(i, cwdVar, ckfVar);
                break;
        }
    }

    private void c(int i, cwd cwdVar, ckf ckfVar) {
        switch (i) {
            case 17:
                ckfVar.d(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 18:
                ckfVar.n(jds.c(cwdVar.c(), 16));
                break;
            case 19:
                ckfVar.e(cvx.e(cwdVar.c()));
                break;
            default:
                LogUtil.h("HwFitnessPostureManager", "resolveCourseRecord default");
                break;
        }
    }

    private List<ckd> a(List<cwe> list) {
        ArrayList arrayList = new ArrayList(10);
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            Iterator<cwe> it2 = it.next().a().iterator();
            while (it2.hasNext()) {
                List<cwd> e2 = it2.next().e();
                ckd ckdVar = new ckd();
                for (cwd cwdVar : e2) {
                    if (cwdVar != null) {
                        e(cwdVar, ckdVar);
                    }
                }
                arrayList.add(ckdVar);
            }
        }
        return arrayList;
    }

    private void e(cwd cwdVar, ckd ckdVar) {
        try {
            int parseInt = Integer.parseInt(cwdVar.e(), 16);
            LogUtil.c("HwFitnessPostureManager", "resolveCourseRecord deviceActionList case is ", Integer.valueOf(parseInt), ",value1 is ", cwdVar.c());
            switch (parseInt) {
                case 22:
                    ckdVar.e(cvx.e(cwdVar.c()));
                    break;
                case 23:
                    ckdVar.b(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 24:
                    ckdVar.d(Integer.parseInt(cwdVar.c(), 16));
                    break;
                case 25:
                    int parseInt2 = Integer.parseInt(cwdVar.c(), 16);
                    if (parseInt2 != 0) {
                        if (parseInt2 != 1) {
                            LogUtil.c("HwFitnessPostureManager", "resolveCourseRecord unhandled type:", Integer.valueOf(parseInt2));
                            break;
                        } else {
                            ckdVar.d("timer");
                            break;
                        }
                    } else {
                        ckdVar.d("beating");
                        break;
                    }
                default:
                    Object[] objArr = new Object[1];
                    objArr[0] = "deviceActionList default";
                    LogUtil.h("HwFitnessPostureManager", objArr);
                    break;
            }
        } catch (NumberFormatException e2) {
            LogUtil.b("HwFitnessPostureManager", "deviceActionListSwitch Exception : ", ExceptionUtils.d(e2));
        }
    }

    private List<ckl> e(byte[] bArr) {
        LogUtil.a("HwFitnessPostureManager", "Enter resolvePostureRecordList");
        ArrayList arrayList = new ArrayList(10);
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolvePostureRecordList dataContent is null");
            return arrayList;
        }
        String d2 = cvx.d(bArr);
        try {
            Iterator<cwe> it = new cwl().a(d2.substring(4, d2.length())).a().iterator();
            while (it.hasNext()) {
                for (cwe cweVar : it.next().a()) {
                    ckl cklVar = new ckl();
                    for (cwd cwdVar : cweVar.e()) {
                        if (cwdVar != null) {
                            try {
                                int parseInt = Integer.parseInt(cwdVar.e(), 16);
                                LogUtil.c("HwFitnessPostureManager", "resolvePostureRecordList case is ", Integer.valueOf(parseInt), ",value is ", cwdVar.c());
                                b(parseInt, cwdVar, cklVar);
                            } catch (NumberFormatException e2) {
                                LogUtil.b("HwFitnessPostureManager", "resolvePostureRecordList Exception : ", ExceptionUtils.d(e2));
                            }
                        }
                    }
                    LogUtil.c("HwFitnessPostureManager", "postureRecord is ", cklVar.toString());
                    arrayList.add(cklVar);
                }
            }
            return arrayList;
        } catch (cwg e3) {
            LogUtil.b("HwFitnessPostureManager", "resolvePostureRecordList Exception : ", ExceptionUtils.d(e3));
            return arrayList;
        }
    }

    private void b(int i, cwd cwdVar, ckl cklVar) {
        switch (i) {
            case 5:
                cklVar.c(cvx.e(cwdVar.c()));
                break;
            case 6:
                cklVar.b(cvx.e(cwdVar.c()));
                break;
            case 7:
                cklVar.m(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 8:
                cklVar.i(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 9:
                cklVar.o(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 10:
                cklVar.n(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 11:
                cklVar.f(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 12:
                cklVar.h(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 13:
                cklVar.d(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 14:
                cklVar.l(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 15:
                cklVar.k(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 16:
                cklVar.g(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 17:
                cklVar.e(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 18:
                cklVar.a(Integer.parseInt(cwdVar.c(), 16));
                break;
            default:
                e(i, cwdVar, cklVar);
                break;
        }
    }

    private void e(int i, cwd cwdVar, ckl cklVar) {
        switch (i) {
            case 19:
                cklVar.j(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 20:
                cklVar.p(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 21:
                cklVar.b(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 22:
                cklVar.c(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 23:
                cklVar.b(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 24:
                cklVar.e(cvx.e(cwdVar.c()));
                break;
            default:
                LogUtil.h("HwFitnessPostureManager", "resolvePostureRecordList default");
                break;
        }
    }

    private ckl d(byte[] bArr) {
        LogUtil.a("HwFitnessPostureManager", "Enter resolvePostureRecord");
        ckl cklVar = new ckl();
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolvePostureRecord dataContent is null");
            return null;
        }
        String d2 = cvx.d(bArr);
        try {
            for (cwd cwdVar : new cwl().a(d2.substring(4, d2.length())).e()) {
                if (cwdVar != null) {
                    try {
                        int parseInt = Integer.parseInt(cwdVar.e(), 16);
                        LogUtil.c("HwFitnessPostureManager", "resolvePostureRecord the case is ", Integer.valueOf(parseInt), ",value2 is ", cwdVar.c());
                        d(parseInt, cwdVar, cklVar);
                    } catch (NumberFormatException e2) {
                        LogUtil.b("HwFitnessPostureManager", "resolvePostureRecord Exception : ", ExceptionUtils.d(e2));
                    }
                }
            }
            LogUtil.c("HwFitnessPostureManager", "postureRecord :", cklVar.toString());
            return cklVar;
        } catch (cwg e3) {
            LogUtil.b("HwFitnessPostureManager", "resolvePostureRecord Exception : ", ExceptionUtils.d(e3));
            return null;
        }
    }

    private void d(int i, cwd cwdVar, ckl cklVar) {
        switch (i) {
            case 1:
                cklVar.c(cvx.e(cwdVar.c()));
                break;
            case 2:
                cklVar.b(cvx.e(cwdVar.c()));
                break;
            case 3:
                cklVar.m(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 4:
                cklVar.i(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 5:
                cklVar.o(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 6:
                cklVar.n(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 7:
                cklVar.f(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 8:
                cklVar.h(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 9:
                cklVar.d(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 10:
                cklVar.l(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 11:
                cklVar.k(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 12:
                cklVar.g(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 13:
                cklVar.e(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 14:
                cklVar.a(Integer.parseInt(cwdVar.c(), 16));
                break;
            default:
                c(i, cwdVar, cklVar);
                break;
        }
    }

    private void c(int i, cwd cwdVar, ckl cklVar) {
        switch (i) {
            case 15:
                cklVar.j(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 16:
                cklVar.p(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 17:
                cklVar.b(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 18:
                cklVar.c(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 19:
                cklVar.b(Integer.parseInt(cwdVar.c(), 16));
                break;
            case 20:
                cklVar.e(cvx.e(cwdVar.c()));
                break;
            default:
                LogUtil.h("HwFitnessPostureManager", "resolvePostureRecordCaseDefault default");
                break;
        }
    }

    private int i(byte[] bArr) {
        LogUtil.a("HwFitnessPostureManager", "Enter resolvePostureStatusValue");
        int i = 100014;
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolvePostureStatusValue dataContent is null");
            return 100014;
        }
        String d2 = cvx.d(bArr);
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4, d2.length())).e();
            if (e2 != null) {
                for (cwd cwdVar : e2) {
                    if (cwdVar != null) {
                        try {
                            i = Integer.parseInt(cwdVar.c(), 16);
                            break;
                        } catch (NumberFormatException e3) {
                            LogUtil.b("HwFitnessPostureManager", "resolvePostureStatusValue Exception : ", ExceptionUtils.d(e3));
                        }
                    }
                }
            }
            return i;
        } catch (cwg e4) {
            LogUtil.b("HwFitnessPostureManager", "resolveWatchStatus Exception : ", ExceptionUtils.d(e4));
            return 0;
        }
    }

    private int f(byte[] bArr) {
        LogUtil.a("HwFitnessPostureManager", "Enter resolveWatchStatus");
        int i = 0;
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolveWatchStatus dataContent is null");
            return 0;
        }
        String d2 = cvx.d(bArr);
        try {
            List<cwd> e2 = new cwl().a(d2.substring(4, d2.length())).e();
            if (e2 == null) {
                return 0;
            }
            for (cwd cwdVar : e2) {
                if (cwdVar != null) {
                    try {
                        if (Integer.parseInt(cwdVar.e(), 16) == 1) {
                            i = Integer.parseInt(cwdVar.c(), 16);
                        }
                    } catch (NumberFormatException e3) {
                        LogUtil.b("HwFitnessPostureManager", "resolveWatchStatus Exception : ", ExceptionUtils.d(e3));
                    }
                }
            }
            return i;
        } catch (cwg e4) {
            LogUtil.b("HwFitnessPostureManager", "resolveWatchStatus Exception : ", ExceptionUtils.d(e4));
            return 0;
        }
    }

    private int h(byte[] bArr) {
        LogUtil.a("HwFitnessPostureManager", "Enter resolvePostureVersionInfo");
        int i = -1;
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolvePostureVersionInfo dataContent is null");
            return -1;
        }
        String d2 = cvx.d(bArr);
        try {
            for (cwd cwdVar : new cwl().a(d2.substring(4, d2.length())).e()) {
                if (cwdVar != null) {
                    try {
                        int parseInt = Integer.parseInt(cwdVar.e(), 16);
                        if (parseInt == 1) {
                            LogUtil.c("HwFitnessPostureManager", "version value is ", cwdVar.c());
                            i = Integer.parseInt(cwdVar.c(), 16);
                        } else if (parseInt == 2) {
                            LogUtil.c("HwFitnessPostureManager", "mcu version value is ", cwdVar.c());
                        } else {
                            LogUtil.h("HwFitnessPostureManager", "resolvePostureVersionInfo default");
                        }
                    } catch (NumberFormatException e2) {
                        LogUtil.b("HwFitnessPostureManager", "resolvePostureVersionInfo Exception : ", ExceptionUtils.d(e2));
                    }
                }
            }
            return i;
        } catch (cwg e3) {
            LogUtil.b("HwFitnessPostureManager", "resolvePostureVersionInfo Exception : ", ExceptionUtils.d(e3));
            return -1;
        }
    }

    private cki g(byte[] bArr) {
        cki ckiVar = new cki();
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolveReminder dataContent is null");
            return null;
        }
        String d2 = cvx.d(bArr);
        try {
            for (cwd cwdVar : new cwl().a(d2.substring(4, d2.length())).e()) {
                if (cwdVar != null) {
                    try {
                        int parseInt = Integer.parseInt(cwdVar.e(), 16);
                        LogUtil.c("HwFitnessPostureManager", "resolveReminder case and value is ", Integer.valueOf(parseInt), ",", cwdVar.c());
                        if (parseInt == 1) {
                            ckiVar.d(cvx.e(cwdVar.c()));
                        } else if (parseInt == 2) {
                            ckiVar.b(Integer.parseInt(cwdVar.c(), 16));
                        } else if (parseInt == 3) {
                            ckiVar.e(Integer.parseInt(cwdVar.c(), 16));
                        } else if (parseInt == 4) {
                            ckiVar.a(Integer.parseInt(cwdVar.c(), 16));
                        } else {
                            LogUtil.h("HwFitnessPostureManager", "resolveReminder default");
                        }
                    } catch (NumberFormatException e2) {
                        LogUtil.b("HwFitnessPostureManager", "resolveReminder Exception : ", ExceptionUtils.d(e2));
                    }
                }
            }
            LogUtil.a("HwFitnessPostureManager", "postureReminder:", ckiVar.toString());
            return ckiVar;
        } catch (cwg e3) {
            LogUtil.b("HwFitnessPostureManager", "resolveReminder Exception : ", ExceptionUtils.d(e3));
            return null;
        }
    }

    private ckj j(byte[] bArr) {
        ckj ckjVar = new ckj();
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "resolveResult dataContent is null");
            return null;
        }
        String d2 = cvx.d(bArr);
        try {
            for (cwd cwdVar : new cwl().a(d2.substring(4, d2.length())).e()) {
                if (cwdVar != null) {
                    try {
                        int parseInt = Integer.parseInt(cwdVar.e(), 16);
                        LogUtil.c("HwFitnessPostureManager", "resolveResult case and value is ", Integer.valueOf(parseInt), ",", cwdVar.c());
                        if (parseInt == 1) {
                            ckjVar.a(cvx.e(cwdVar.c()));
                        } else if (parseInt == 2) {
                            ckjVar.e(Integer.parseInt(cwdVar.c(), 16));
                        } else if (parseInt == 3) {
                            ckjVar.d(Integer.parseInt(cwdVar.c(), 16));
                        } else if (parseInt == 4) {
                            ckjVar.a(Integer.parseInt(cwdVar.c(), 16));
                        } else {
                            d(parseInt, cwdVar, ckjVar);
                        }
                    } catch (NumberFormatException e2) {
                        LogUtil.b("HwFitnessPostureManager", "resolveResult Exception : ", ExceptionUtils.d(e2));
                    }
                }
            }
            LogUtil.a("HwFitnessPostureManager", "postureResult ", ckjVar.toString());
            return ckjVar;
        } catch (cwg e3) {
            LogUtil.b("HwFitnessPostureManager", "resolveResult Exception : ", ExceptionUtils.d(e3));
            return null;
        }
    }

    private void d(int i, cwd cwdVar, ckj ckjVar) {
        if (i == 5) {
            ckjVar.b(Integer.parseInt(cwdVar.c(), 16));
        } else if (i == 6) {
            ckjVar.c(Integer.parseInt(cwdVar.c(), 16));
        } else {
            LogUtil.h("HwFitnessPostureManager", "resolveResult default");
        }
    }

    private String c(int i, int i2, int i3) {
        String e2;
        int i4 = 1;
        if (i3 == 0) {
            e2 = cvx.e(i2);
        } else if (i3 == 1) {
            e2 = cvx.a(i2);
            i4 = 2;
        } else if (i3 == 2) {
            e2 = cvx.b(i2);
            i4 = 4;
        } else {
            LogUtil.h("HwFitnessPostureManager", "formTlvForIntType unknown valueType:", Integer.valueOf(i3));
            e2 = "";
            i4 = 0;
        }
        String str = cvx.e(i) + cvx.e(i4) + e2;
        LogUtil.a("HwFitnessPostureManager", "formTlvForIntType() result:", str);
        return str;
    }

    private int c(byte[] bArr) {
        if (bArr == null) {
            LogUtil.h("HwFitnessPostureManager", "getErrorCode dataContent is null");
            return 100001;
        }
        String d2 = cvx.d(bArr);
        try {
            int[] d3 = d(new cwl().a(d2.substring(4, d2.length())));
            LogUtil.a("HwFitnessPostureManager", "Error Code:", Integer.valueOf(d3[0]));
            int i = d3[0];
            if (i == 100000) {
                return 0;
            }
            return i;
        } catch (cwg e2) {
            LogUtil.b("HwFitnessPostureManager", "getErrorCode Exception : ", ExceptionUtils.d(e2));
            return 100001;
        }
    }

    private int[] d(cwe cweVar) {
        List<cwd> e2 = cweVar.e();
        if (e2 == null) {
            return new int[]{0};
        }
        int[] iArr = new int[e2.size()];
        for (cwd cwdVar : e2) {
            if (cwdVar != null) {
                try {
                    if (Integer.parseInt(cwdVar.e(), 16) == 127) {
                        iArr[0] = Integer.parseInt(cwdVar.c(), 16);
                    }
                } catch (NumberFormatException e3) {
                    LogUtil.b("HwFitnessPostureManager", "unTLVGetErrorCode Exception : ", ExceptionUtils.d(e3));
                }
            }
        }
        return iArr;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (i != 0 || bArr == null) {
            return;
        }
        if (bArr.length < 3) {
            LogUtil.h("HwFitnessPostureManager", "dataInfos length is less than 3.");
            return;
        }
        LogUtil.a("HwFitnessPostureManager", "onDataReceived data is ", cvx.d(bArr));
        IBaseResponseCallback iBaseResponseCallback = this.c;
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwFitnessPostureManager", "mCallback is null");
        } else if (bArr[2] == Byte.MAX_VALUE) {
            iBaseResponseCallback.d(127, Integer.valueOf(c(bArr)));
        } else {
            k(bArr);
        }
    }
}
