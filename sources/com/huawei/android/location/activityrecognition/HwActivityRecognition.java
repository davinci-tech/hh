package com.huawei.android.location.activityrecognition;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareService;
import com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareSink;
import com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService;
import com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink;
import defpackage.abh;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class HwActivityRecognition {

    /* renamed from: a, reason: collision with root package name */
    private d f1844a;
    private Context f;
    private com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink k;
    private IActivityRecognitionHardwareSink l;
    private String o;
    private static final int c = Build.VERSION.SDK_INT;
    private static int b = -1;
    private int g = 0;
    private HwActivityRecognitionServiceConnection m = null;
    private IActivityRecognitionHardwareService i = null;
    private com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService n = null;
    private String[] d = {"android.activity_recognition.in_vehicle", "android.activity_recognition.on_bicycle", "android.activity_recognition.walking", "android.activity_recognition.running", "android.activity_recognition.still", "android.activity_recognition.tilting", "android.activity_recognition.fast_walking", "android.activity_recognition.high_speed_rail", "android.activity_recognition.unknown", "android.activity_recognition.on_foot", "android.activity_recognition.outdoor", "android.activity_recognition.elevator", "android.activity_recognition.relative_still", "android.activity_recognition.walking_handhold", "android.activity_recognition.lying_posture"};
    private String[] e = {"android.activity_recognition.env_home", "android.activity_recognition.env_office", "android.activity_recognition.env_way_home", "android.activity_recognition.env_way_office"};
    private Handler j = new Handler() { // from class: com.huawei.android.location.activityrecognition.HwActivityRecognition.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                HwActivityRecognition.this.o();
            } else {
                if (i != 1) {
                    return;
                }
                HwActivityRecognition.this.g();
            }
        }
    };
    private ServiceConnection h = new ServiceConnection() { // from class: com.huawei.android.location.activityrecognition.HwActivityRecognition.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            abh.c("ARMoudle.HwActivityRecognition", "Connection service ok");
            HwActivityRecognition.this.j.removeMessages(1);
            if (HwActivityRecognition.c >= 25) {
                HwActivityRecognition.this.n = IActivityRecognitionHardwareService.Stub.asInterface(iBinder);
                HwActivityRecognition.this.k();
            } else {
                HwActivityRecognition.this.i = IActivityRecognitionHardwareService.Stub.asInterface(iBinder);
            }
            HwActivityRecognition.this.l();
            HwActivityRecognition.this.m();
            if (HwActivityRecognition.c >= 25) {
                HwActivityRecognition.this.m.onServiceConnected();
            } else {
                HwActivityRecognition.this.j.sendEmptyMessage(0);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (HwActivityRecognition.c < 25) {
                HwActivityRecognition.this.i = null;
            } else {
                HwActivityRecognition.this.n = null;
            }
            HwActivityRecognition.this.m.onServiceDisconnected();
        }
    };

    public HwActivityRecognition(Context context) {
        d dVar = null;
        this.f = null;
        abh.c("ARMoudle.HwActivityRecognition", "AR sdk version:12");
        abh.c("ARMoudle.HwActivityRecognition", "HwActivityRecognition, android version:" + c);
        abh.c("ARMoudle.HwActivityRecognition", "AR sdk modify version:1.3.1");
        if (context != null) {
            this.f = context;
            this.o = context.getPackageName();
            this.f1844a = new d(this, dVar);
        }
    }

    public static int d() {
        return b;
    }

    public static void c(int i) {
        b = i;
    }

    private boolean n() {
        try {
            int intValue = ((Integer) Class.forName("android.os.UserHandle").getMethod("myUserId", new Class[0]).invoke(null, new Object[0])).intValue();
            abh.c("ARMoudle.HwActivityRecognition", "user id:" + intValue);
            return intValue == 0;
        } catch (ClassNotFoundException unused) {
            abh.e("ARMoudle.HwActivityRecognition", "ClassNotFoundException");
            return false;
        } catch (IllegalAccessException unused2) {
            abh.e("ARMoudle.HwActivityRecognition", "IllegalAccessException");
            return false;
        } catch (IllegalArgumentException unused3) {
            abh.e("ARMoudle.HwActivityRecognition", "IllegalArgumentException");
            return false;
        } catch (NoSuchMethodException unused4) {
            abh.e("ARMoudle.HwActivityRecognition", "NoSuchMethodException");
            return false;
        } catch (InvocationTargetException unused5) {
            abh.e("ARMoudle.HwActivityRecognition", "InvocationTargetException");
            return false;
        }
    }

    public boolean b(HwActivityRecognitionHardwareSink hwActivityRecognitionHardwareSink, HwActivityRecognitionServiceConnection hwActivityRecognitionServiceConnection) {
        abh.c("ARMoudle.HwActivityRecognition", "connectService");
        if (!n()) {
            abh.e("ARMoudle.HwActivityRecognition", "not system user.");
            return false;
        }
        if (hwActivityRecognitionServiceConnection == null || hwActivityRecognitionHardwareSink == null) {
            abh.e("ARMoudle.HwActivityRecognition", "connection or sink is null.");
            return false;
        }
        this.m = hwActivityRecognitionServiceConnection;
        if (c >= 25) {
            if (this.n != null) {
                return true;
            }
            this.k = b(hwActivityRecognitionHardwareSink);
            g();
            return true;
        }
        if (this.i != null) {
            return true;
        }
        this.l = a(hwActivityRecognitionHardwareSink);
        g();
        return true;
    }

    public boolean c() {
        abh.c("ARMoudle.HwActivityRecognition", "disconnectService");
        if (c >= 25) {
            com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
            if (iActivityRecognitionHardwareService == null) {
                abh.e("ARMoudle.HwActivityRecognition", "mService_O is null.");
                return false;
            }
            try {
                iActivityRecognitionHardwareService.asBinder().unlinkToDeath(this.f1844a, 0);
            } catch (Exception unused) {
                abh.e("ARMoudle.HwActivityRecognition", "mService unlink fail.");
            }
        } else {
            IActivityRecognitionHardwareService iActivityRecognitionHardwareService2 = this.i;
            if (iActivityRecognitionHardwareService2 == null) {
                abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
                return false;
            }
            iActivityRecognitionHardwareService2.asBinder().unlinkToDeath(this.f1844a, 0);
        }
        s();
        this.f.unbindService(this.h);
        this.m.onServiceDisconnected();
        if (c >= 25) {
            this.n = null;
        } else {
            this.i = null;
        }
        this.g = 0;
        this.j.removeMessages(1);
        this.j.removeMessages(0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean l() {
        if (c >= 25) {
            return r();
        }
        return t();
    }

    private boolean t() {
        IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        abh.c("ARMoudle.HwActivityRecognition", "registerSink_N");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.l) == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService or mSink is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.registerSink(iActivityRecognitionHardwareSink);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "registerSink error:" + e.getMessage());
            return false;
        }
    }

    private boolean r() {
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        abh.c("ARMoudle.HwActivityRecognition", "registerSink_O");
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.k) == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService_O or mSink_O is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.registerSink(this.o, iActivityRecognitionHardwareSink);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "registerSink error:" + e.getMessage());
            return false;
        }
    }

    private boolean s() {
        if (c >= 25) {
            return p();
        }
        return q();
    }

    private boolean q() {
        IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        abh.c("ARMoudle.HwActivityRecognition", "unregisterSink_N");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.l) == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService or mSink is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.unregisterSink(iActivityRecognitionHardwareSink);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "unregisterSink error:" + e.getMessage());
            return false;
        }
    }

    private boolean p() {
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        abh.c("ARMoudle.HwActivityRecognition", "unregisterSink_O");
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.k) == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService_O or mService_O is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.unregisterSink(this.o, iActivityRecognitionHardwareSink);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "unregisterSink error:" + e.getMessage());
            return false;
        }
    }

    public int i() {
        abh.c("ARMoudle.HwActivityRecognition", "getSupportedModule");
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService_O is null.");
            return 0;
        }
        try {
            return iActivityRecognitionHardwareService.getSupportedModule();
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "getSupportedModule error:" + e.getMessage());
            return 0;
        }
    }

    public boolean d(String str, int i, long j) {
        if (c >= 25) {
            return e(str, i, j);
        }
        return b(str, i, j);
    }

    private boolean b(String str, int i, long j) {
        abh.c("ARMoudle.HwActivityRecognition", "enableActivityEvent");
        if (TextUtils.isEmpty(str) || j < 0) {
            abh.e("ARMoudle.HwActivityRecognition", "activity is null or reportLatencyNs < 0");
            return false;
        }
        abh.c("ARMoudle.HwActivityRecognition", String.valueOf(b(str)) + "," + i + "," + j);
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.enableActivityEvent(str, i, j);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "enableActivityEvent error:" + e.getMessage());
            return false;
        }
    }

    private boolean e(String str, int i, long j) {
        abh.c("ARMoudle.HwActivityRecognition", "enableActivityEvent");
        if (TextUtils.isEmpty(str) || j < 0) {
            abh.e("ARMoudle.HwActivityRecognition", "activity is null or reportLatencyNs < 0");
            return false;
        }
        abh.c("ARMoudle.HwActivityRecognition", String.valueOf(b(str)) + "," + i + "," + j);
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.enableActivityEvent(this.o, str, i, j);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "enableActivityEvent error:" + e.getMessage());
            return false;
        }
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.d;
            if (i >= strArr.length) {
                i = -1;
                break;
            }
            if (str.equals(strArr[i])) {
                break;
            }
            i++;
        }
        if (i == -1) {
            abh.e("ARMoudle.HwActivityRecognition", "activity type invalid!");
        }
        return i;
    }

    private int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.e;
            if (i >= strArr.length) {
                i = -1;
                break;
            }
            if (str.equals(strArr[i])) {
                break;
            }
            i++;
        }
        if (i == -1) {
            abh.e("ARMoudle.HwActivityRecognition", "environment type invalid!");
        }
        return i;
    }

    public boolean b(String str, int i) {
        if (c >= 25) {
            return e(str, i);
        }
        return d(str, i);
    }

    private boolean d(String str, int i) {
        abh.c("ARMoudle.HwActivityRecognition", "disableActivityEvent");
        if (TextUtils.isEmpty(str)) {
            abh.e("ARMoudle.HwActivityRecognition", "activity is null.");
            return false;
        }
        abh.c("ARMoudle.HwActivityRecognition", String.valueOf(b(str)) + "," + i);
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.disableActivityEvent(str, i);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "disableActivityEvent error:" + e.getMessage());
            return false;
        }
    }

    private boolean e(String str, int i) {
        abh.c("ARMoudle.HwActivityRecognition", "disableActivityEvent");
        if (TextUtils.isEmpty(str)) {
            abh.e("ARMoudle.HwActivityRecognition", "activity is null.");
            return false;
        }
        abh.c("ARMoudle.HwActivityRecognition", String.valueOf(b(str)) + "," + i);
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService_O is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.disableActivityEvent(this.o, str, i);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "disableActivityEvent error:" + e.getMessage());
            return false;
        }
    }

    public String e() {
        abh.c("ARMoudle.HwActivityRecognition", "getCurrentActivity");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return "unknown";
        }
        try {
            return iActivityRecognitionHardwareService.getCurrentActivity();
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "getCurrentActivity error:" + e.getMessage());
            return "unknown";
        }
    }

    public HwActivityChangedExtendEvent f() {
        abh.c("ARMoudle.HwActivityRecognition", "getCurrentActivityExtend");
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        com.huawei.systemserver.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent = null;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return null;
        }
        try {
            if (b == 1) {
                hwActivityChangedExtendEvent = iActivityRecognitionHardwareService.getCurrentActivityV1_1();
            } else {
                hwActivityChangedExtendEvent = iActivityRecognitionHardwareService.getCurrentActivity();
            }
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "getCurrentActivity error:" + e.getMessage());
        }
        return c(hwActivityChangedExtendEvent);
    }

    public boolean b() {
        if (c >= 25) {
            return j();
        }
        return h();
    }

    private boolean h() {
        abh.c("ARMoudle.HwActivityRecognition", "flush");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.flush();
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "flush error:" + e.getMessage());
            return false;
        }
    }

    private boolean j() {
        abh.c("ARMoudle.HwActivityRecognition", "flush");
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.flush();
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "flush error:" + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        abh.c("ARMoudle.HwActivityRecognition", "getARVersion");
        com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
        if (iActivityRecognitionHardwareService == null) {
            abh.e("ARMoudle.HwActivityRecognition", "mService is null.");
            return;
        }
        int i = -1;
        try {
            i = iActivityRecognitionHardwareService.getARVersion(this.o, 12);
            abh.c("ARMoudle.HwActivityRecognition", "version:" + i);
        } catch (RemoteException e) {
            abh.e("ARMoudle.HwActivityRecognition", "getARVersion error:" + e.getMessage());
        }
        c(i);
    }

    private IActivityRecognitionHardwareSink a(final HwActivityRecognitionHardwareSink hwActivityRecognitionHardwareSink) {
        if (hwActivityRecognitionHardwareSink == null) {
            return null;
        }
        return new IActivityRecognitionHardwareSink.Stub() { // from class: com.huawei.android.location.activityrecognition.HwActivityRecognition.5
            @Override // com.huawei.android.location.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException {
                abh.c("ARMoudle.HwActivityRecognition", "onActivityChanged_N...");
                hwActivityRecognitionHardwareSink.onActivityChanged(hwActivityChangedEvent);
            }
        };
    }

    /* loaded from: classes2.dex */
    static class b {

        /* renamed from: a, reason: collision with root package name */
        private long f1846a;
        private String b;
        private int c;
        private int d;
        private OtherParameters e;

        public int b() {
            return this.d;
        }

        public String d() {
            return this.b;
        }

        public int e() {
            return this.c;
        }

        public long c() {
            return this.f1846a;
        }

        public OtherParameters a() {
            return this.e;
        }

        public b(String str, int i, long j, OtherParameters otherParameters, int i2) {
            this.b = str;
            this.c = i;
            this.f1846a = j;
            this.e = otherParameters;
            this.d = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwActivityChangedEvent b(com.huawei.systemserver.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) {
        if (hwActivityChangedEvent == null) {
            abh.e("ARMoudle.HwActivityRecognition", "tranferToHwActivityChangedEvent event is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.huawei.systemserver.activityrecognition.HwActivityRecognitionEvent hwActivityRecognitionEvent : hwActivityChangedEvent.getActivityRecognitionEvents()) {
            arrayList.add(new b(hwActivityRecognitionEvent.getActivity(), hwActivityRecognitionEvent.getEventType(), hwActivityRecognitionEvent.getTimestampNs(), null, hwActivityRecognitionEvent.getConfidence()));
            abh.c("ARMoudle.HwActivityRecognition", "onActivityChanged_O: " + b(hwActivityRecognitionEvent.getActivity()) + "  , " + hwActivityRecognitionEvent.getEventType() + "  , " + hwActivityRecognitionEvent.getTimestampNs() + "  ," + hwActivityRecognitionEvent.getConfidence());
        }
        HwActivityRecognitionEvent[] hwActivityRecognitionEventArr = new HwActivityRecognitionEvent[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            hwActivityRecognitionEventArr[i] = new HwActivityRecognitionEvent(bVar.d(), bVar.e(), bVar.c(), bVar.b());
        }
        return new HwActivityChangedEvent(hwActivityRecognitionEventArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwActivityChangedExtendEvent c(com.huawei.systemserver.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        if (hwActivityChangedExtendEvent == null) {
            abh.e("ARMoudle.HwActivityRecognition", "hwActivityEvent is null.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.huawei.systemserver.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            arrayList.add(new b(hwActivityRecognitionExtendEvent.getActivity(), hwActivityRecognitionExtendEvent.getEventType(), hwActivityRecognitionExtendEvent.getTimestampNs(), c(hwActivityRecognitionExtendEvent.getOtherParams()), hwActivityRecognitionExtendEvent.getConfidence()));
            abh.c("ARMoudle.HwActivityRecognition", "hwActivityEvent: " + b(hwActivityRecognitionExtendEvent.getActivity()) + "," + hwActivityRecognitionExtendEvent.getEventType() + "," + hwActivityRecognitionExtendEvent.getTimestampNs() + "," + hwActivityRecognitionExtendEvent.getConfidence());
        }
        HwActivityRecognitionExtendEvent[] hwActivityRecognitionExtendEventArr = new HwActivityRecognitionExtendEvent[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            hwActivityRecognitionExtendEventArr[i] = new HwActivityRecognitionExtendEvent(bVar.d(), bVar.e(), bVar.c(), bVar.a(), bVar.b());
        }
        return new HwActivityChangedExtendEvent(hwActivityRecognitionExtendEventArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwEnvironmentChangedEvent b(com.huawei.systemserver.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
        if (hwEnvironmentChangedEvent == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.huawei.systemserver.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwEnvironmentChangedEvent.getEnvironmentRecognitionEvents()) {
            arrayList.add(new b(hwActivityRecognitionExtendEvent.getActivity(), hwActivityRecognitionExtendEvent.getEventType(), hwActivityRecognitionExtendEvent.getTimestampNs(), c(hwActivityRecognitionExtendEvent.getOtherParams()), hwActivityRecognitionExtendEvent.getConfidence()));
            abh.c("ARMoudle.HwActivityRecognition", "hwEnvironmentEvent: " + a(hwActivityRecognitionExtendEvent.getActivity()) + "," + hwActivityRecognitionExtendEvent.getEventType() + "," + hwActivityRecognitionExtendEvent.getTimestampNs() + "," + hwActivityRecognitionExtendEvent.getConfidence());
        }
        HwActivityRecognitionExtendEvent[] hwActivityRecognitionExtendEventArr = new HwActivityRecognitionExtendEvent[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            hwActivityRecognitionExtendEventArr[i] = new HwActivityRecognitionExtendEvent(bVar.d(), bVar.e(), bVar.c(), bVar.a(), bVar.b());
        }
        return new HwEnvironmentChangedEvent(hwActivityRecognitionExtendEventArr);
    }

    private OtherParameters c(com.huawei.systemserver.activityrecognition.OtherParameters otherParameters) {
        if (otherParameters == null) {
            return null;
        }
        return new OtherParameters(otherParameters.getmParam1(), otherParameters.getmParam2(), otherParameters.getmParam3(), otherParameters.getmParam4(), otherParameters.getmParam5());
    }

    private com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink b(final HwActivityRecognitionHardwareSink hwActivityRecognitionHardwareSink) {
        if (hwActivityRecognitionHardwareSink == null) {
            abh.e("ARMoudle.HwActivityRecognition", "createActivityRecognitionHardwareSink_O... sink is null");
            return null;
        }
        return new IActivityRecognitionHardwareSink.Stub() { // from class: com.huawei.android.location.activityrecognition.HwActivityRecognition.2
            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityChanged(com.huawei.systemserver.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException {
                abh.c("ARMoudle.HwActivityRecognition", "onActivityChanged_O...");
                abh.c("ARMoudle.HwActivityRecognition", "package name = " + HwActivityRecognition.this.f.getPackageName());
                hwActivityRecognitionHardwareSink.onActivityChanged(HwActivityRecognition.this.b(hwActivityChangedEvent));
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityExtendChanged(com.huawei.systemserver.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) throws RemoteException {
                abh.e("ARMoudle.HwActivityRecognition", "onActivityExtendChanged...");
                hwActivityRecognitionHardwareSink.onActivityExtendChanged(HwActivityRecognition.this.c(hwActivityChangedExtendEvent));
            }

            @Override // com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onEnvironmentChanged(com.huawei.systemserver.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) throws RemoteException {
                abh.c("ARMoudle.HwActivityRecognition", "onEnvironmentChanged...");
                hwActivityRecognitionHardwareSink.onEnvironmentChanged(HwActivityRecognition.this.b(hwEnvironmentChangedEvent));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        try {
            IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
            if (iActivityRecognitionHardwareService != null) {
                if (iActivityRecognitionHardwareService.providerLoadOk()) {
                    this.j.removeMessages(0);
                    this.m.onServiceConnected();
                } else {
                    this.j.sendEmptyMessageDelayed(0, 500L);
                }
            }
        } catch (RemoteException unused) {
            abh.e("ARMoudle.HwActivityRecognition", "providerLoadOk fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.g > 10) {
            abh.c("ARMoudle.HwActivityRecognition", "try connect 10 times, connection fail");
            return;
        }
        if (c >= 25) {
            if (this.n == null) {
                abh.c("ARMoudle.HwActivityRecognition", String.valueOf(this.f.getPackageName()) + " bind ar service.");
                Intent intent = new Intent();
                intent.setClassName("com.huawei.systemserver", "com.huawei.systemserver.activityrecognition.ActivityRecognitionService");
                abh.c("ARMoudle.HwActivityRecognition", "bindIntent = " + intent);
                this.f.bindService(intent, this.h, 1);
                this.g = this.g + 1;
                this.j.sendEmptyMessageDelayed(1, 2000L);
                return;
            }
            return;
        }
        if (this.i == null) {
            abh.c("ARMoudle.HwActivityRecognition", String.valueOf(this.f.getPackageName()) + " bind ar service.");
            Intent intent2 = new Intent();
            intent2.setClassName("com.huawei.android.location.activityrecognition", "com.huawei.android.location.activityrecognition.ActivityRecognitionService");
            this.f.bindService(intent2, this.h, 1);
            this.g++;
            this.j.sendEmptyMessageDelayed(1, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            if (c >= 25) {
                com.huawei.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.n;
                if (iActivityRecognitionHardwareService != null) {
                    iActivityRecognitionHardwareService.asBinder().linkToDeath(this.f1844a, 0);
                }
            } else {
                IActivityRecognitionHardwareService iActivityRecognitionHardwareService2 = this.i;
                if (iActivityRecognitionHardwareService2 != null) {
                    iActivityRecognitionHardwareService2.asBinder().linkToDeath(this.f1844a, 0);
                }
            }
        } catch (RemoteException unused) {
            abh.e("ARMoudle.HwActivityRecognition", "IBinder register linkToDeath function fail.");
        }
    }

    /* loaded from: classes2.dex */
    class d implements IBinder.DeathRecipient {
        private d() {
        }

        /* synthetic */ d(HwActivityRecognition hwActivityRecognition, d dVar) {
            this();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            abh.c("ARMoudle.HwActivityRecognition", "Ar service has died!");
            if (HwActivityRecognition.this.m != null) {
                HwActivityRecognition.this.m.onServiceDisconnected();
            }
            if (HwActivityRecognition.c < 25) {
                if (HwActivityRecognition.this.i != null) {
                    HwActivityRecognition.this.i.asBinder().unlinkToDeath(HwActivityRecognition.this.f1844a, 0);
                    HwActivityRecognition.this.i = null;
                    return;
                }
                return;
            }
            if (HwActivityRecognition.this.n != null) {
                HwActivityRecognition.this.n.asBinder().unlinkToDeath(HwActivityRecognition.this.f1844a, 0);
                HwActivityRecognition.this.n = null;
            }
        }
    }
}
