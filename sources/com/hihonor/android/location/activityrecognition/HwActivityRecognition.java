package com.hihonor.android.location.activityrecognition;

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
import com.hihonor.android.location.activityrecognition.IActivityRecognitionHardwareService;
import com.hihonor.android.location.activityrecognition.IActivityRecognitionHardwareSink;
import com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService;
import com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink;
import defpackage.ul;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class HwActivityRecognition {
    private e c;
    private Context h;
    private String m;
    private com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink n;
    private IActivityRecognitionHardwareSink o;
    private static final int d = Build.VERSION.SDK_INT;
    private static int b = -1;
    private int f = 0;
    private HwActivityRecognitionServiceConnection l = null;
    private IActivityRecognitionHardwareService i = null;
    private com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService k = null;
    private String[] e = {"android.activity_recognition.in_vehicle", "android.activity_recognition.on_bicycle", "android.activity_recognition.walking", "android.activity_recognition.running", "android.activity_recognition.still", "android.activity_recognition.tilting", "android.activity_recognition.fast_walking", "android.activity_recognition.high_speed_rail", "android.activity_recognition.unknown", "android.activity_recognition.on_foot", "android.activity_recognition.outdoor", "android.activity_recognition.elevator", "android.activity_recognition.relative_still", "android.activity_recognition.walking_handhold", "android.activity_recognition.lying_posture"};

    /* renamed from: a, reason: collision with root package name */
    private String[] f1664a = {"android.activity_recognition.env_home", "android.activity_recognition.env_office", "android.activity_recognition.env_way_home", "android.activity_recognition.env_way_office"};
    private Handler g = new Handler() { // from class: com.hihonor.android.location.activityrecognition.HwActivityRecognition.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                HwActivityRecognition.this.i();
            } else {
                if (i != 1) {
                    return;
                }
                HwActivityRecognition.this.j();
            }
        }
    };
    private ServiceConnection j = new ServiceConnection() { // from class: com.hihonor.android.location.activityrecognition.HwActivityRecognition.5
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ul.e("ARMoudle.HwActivityRecognition", "Connection service ok");
            HwActivityRecognition.this.g.removeMessages(1);
            if (HwActivityRecognition.d >= 25) {
                HwActivityRecognition.this.k = IActivityRecognitionHardwareService.Stub.asInterface(iBinder);
                HwActivityRecognition.this.g();
            } else {
                HwActivityRecognition.this.i = IActivityRecognitionHardwareService.Stub.asInterface(iBinder);
            }
            HwActivityRecognition.this.n();
            HwActivityRecognition.this.m();
            if (HwActivityRecognition.d >= 25) {
                HwActivityRecognition.this.l.onServiceConnected();
            } else {
                HwActivityRecognition.this.g.sendEmptyMessage(0);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (HwActivityRecognition.d < 25) {
                HwActivityRecognition.this.i = null;
            } else {
                HwActivityRecognition.this.k = null;
            }
            HwActivityRecognition.this.l.onServiceDisconnected();
        }
    };

    public HwActivityRecognition(Context context) {
        e eVar = null;
        this.h = null;
        ul.e("ARMoudle.HwActivityRecognition", "AR sdk version:12");
        ul.e("ARMoudle.HwActivityRecognition", "HwActivityRecognition, android version:" + d);
        ul.e("ARMoudle.HwActivityRecognition", "AR sdk modify version:1.3.1");
        if (context != null) {
            this.h = context;
            this.m = context.getPackageName();
            this.c = new e(this, eVar);
        }
    }

    public static int c() {
        return b;
    }

    public static void b(int i) {
        b = i;
    }

    private boolean h() {
        try {
            int intValue = ((Integer) Class.forName("android.os.UserHandle").getMethod("myUserId", new Class[0]).invoke(null, new Object[0])).intValue();
            ul.e("ARMoudle.HwActivityRecognition", "user id:" + intValue);
            return intValue == 0;
        } catch (ClassNotFoundException unused) {
            ul.c("ARMoudle.HwActivityRecognition", "ClassNotFoundException");
            return false;
        } catch (IllegalAccessException unused2) {
            ul.c("ARMoudle.HwActivityRecognition", "IllegalAccessException");
            return false;
        } catch (IllegalArgumentException unused3) {
            ul.c("ARMoudle.HwActivityRecognition", "IllegalArgumentException");
            return false;
        } catch (NoSuchMethodException unused4) {
            ul.c("ARMoudle.HwActivityRecognition", "NoSuchMethodException");
            return false;
        } catch (InvocationTargetException unused5) {
            ul.c("ARMoudle.HwActivityRecognition", "InvocationTargetException");
            return false;
        }
    }

    public boolean a(HwActivityRecognitionHardwareSink hwActivityRecognitionHardwareSink, HwActivityRecognitionServiceConnection hwActivityRecognitionServiceConnection) {
        ul.e("ARMoudle.HwActivityRecognition", "connectService");
        if (!h()) {
            ul.c("ARMoudle.HwActivityRecognition", "not system user.");
            return false;
        }
        if (hwActivityRecognitionServiceConnection == null || hwActivityRecognitionHardwareSink == null) {
            ul.c("ARMoudle.HwActivityRecognition", "connection or sink is null.");
            return false;
        }
        this.l = hwActivityRecognitionServiceConnection;
        if (d >= 25) {
            if (this.k != null) {
                return true;
            }
            this.n = b(hwActivityRecognitionHardwareSink);
            j();
            return true;
        }
        if (this.i != null) {
            return true;
        }
        this.o = c(hwActivityRecognitionHardwareSink);
        j();
        return true;
    }

    public boolean a() {
        ul.e("ARMoudle.HwActivityRecognition", "disconnectService");
        if (d >= 25) {
            com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
            if (iActivityRecognitionHardwareService == null) {
                ul.c("ARMoudle.HwActivityRecognition", "mService_O is null.");
                return false;
            }
            try {
                iActivityRecognitionHardwareService.asBinder().unlinkToDeath(this.c, 0);
            } catch (Exception unused) {
                ul.c("ARMoudle.HwActivityRecognition", "mService unlink fail.");
            }
        } else {
            IActivityRecognitionHardwareService iActivityRecognitionHardwareService2 = this.i;
            if (iActivityRecognitionHardwareService2 == null) {
                ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
                return false;
            }
            iActivityRecognitionHardwareService2.asBinder().unlinkToDeath(this.c, 0);
        }
        k();
        this.h.unbindService(this.j);
        this.l.onServiceDisconnected();
        if (d >= 25) {
            this.k = null;
        } else {
            this.i = null;
        }
        this.f = 0;
        this.g.removeMessages(1);
        this.g.removeMessages(0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        if (d >= 25) {
            return l();
        }
        return o();
    }

    private boolean o() {
        IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        ul.e("ARMoudle.HwActivityRecognition", "registerSink_N");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.o) == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService or mSink is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.registerSink(iActivityRecognitionHardwareSink);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "registerSink error:" + e2.getMessage());
            return false;
        }
    }

    private boolean l() {
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        ul.e("ARMoudle.HwActivityRecognition", "registerSink_O");
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.n) == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService_O or mSink_O is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.registerSink(this.m, iActivityRecognitionHardwareSink);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "registerSink error:" + e2.getMessage());
            return false;
        }
    }

    private boolean k() {
        if (d >= 25) {
            return s();
        }
        return q();
    }

    private boolean q() {
        IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        ul.e("ARMoudle.HwActivityRecognition", "unregisterSink_N");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.o) == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService or mSink is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.unregisterSink(iActivityRecognitionHardwareSink);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "unregisterSink error:" + e2.getMessage());
            return false;
        }
    }

    private boolean s() {
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink iActivityRecognitionHardwareSink;
        ul.e("ARMoudle.HwActivityRecognition", "unregisterSink_O");
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        if (iActivityRecognitionHardwareService == null || (iActivityRecognitionHardwareSink = this.n) == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService_O or mService_O is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.unregisterSink(this.m, iActivityRecognitionHardwareSink);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "unregisterSink error:" + e2.getMessage());
            return false;
        }
    }

    public int f() {
        ul.e("ARMoudle.HwActivityRecognition", "getSupportedModule");
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService_O is null.");
            return 0;
        }
        try {
            return iActivityRecognitionHardwareService.getSupportedModule();
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "getSupportedModule error:" + e2.getMessage());
            return 0;
        }
    }

    public boolean b(String str, int i, long j) {
        if (d >= 25) {
            return d(str, i, j);
        }
        return e(str, i, j);
    }

    private boolean e(String str, int i, long j) {
        ul.e("ARMoudle.HwActivityRecognition", "enableActivityEvent");
        if (TextUtils.isEmpty(str) || j < 0) {
            ul.c("ARMoudle.HwActivityRecognition", "activity is null or reportLatencyNs < 0");
            return false;
        }
        ul.e("ARMoudle.HwActivityRecognition", String.valueOf(d(str)) + "," + i + "," + j);
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.enableActivityEvent(str, i, j);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "enableActivityEvent error:" + e2.getMessage());
            return false;
        }
    }

    private boolean d(String str, int i, long j) {
        ul.e("ARMoudle.HwActivityRecognition", "enableActivityEvent");
        if (TextUtils.isEmpty(str) || j < 0) {
            ul.c("ARMoudle.HwActivityRecognition", "activity is null or reportLatencyNs < 0");
            return false;
        }
        ul.e("ARMoudle.HwActivityRecognition", String.valueOf(d(str)) + "," + i + "," + j);
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.enableActivityEvent(this.m, str, i, j);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "enableActivityEvent error:" + e2.getMessage());
            return false;
        }
    }

    private int d(String str) {
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
            ul.c("ARMoudle.HwActivityRecognition", "activity type invalid!");
        }
        return i;
    }

    private int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.f1664a;
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
            ul.c("ARMoudle.HwActivityRecognition", "environment type invalid!");
        }
        return i;
    }

    public boolean c(String str, int i) {
        if (d >= 25) {
            return b(str, i);
        }
        return a(str, i);
    }

    private boolean a(String str, int i) {
        ul.e("ARMoudle.HwActivityRecognition", "disableActivityEvent");
        if (TextUtils.isEmpty(str)) {
            ul.c("ARMoudle.HwActivityRecognition", "activity is null.");
            return false;
        }
        ul.e("ARMoudle.HwActivityRecognition", String.valueOf(d(str)) + "," + i);
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.disableActivityEvent(str, i);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "disableActivityEvent error:" + e2.getMessage());
            return false;
        }
    }

    private boolean b(String str, int i) {
        ul.e("ARMoudle.HwActivityRecognition", "disableActivityEvent");
        if (TextUtils.isEmpty(str)) {
            ul.c("ARMoudle.HwActivityRecognition", "activity is null.");
            return false;
        }
        ul.e("ARMoudle.HwActivityRecognition", String.valueOf(d(str)) + "," + i);
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService_O is null.");
            return false;
        }
        try {
            return iActivityRecognitionHardwareService.disableActivityEvent(this.m, str, i);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "disableActivityEvent error:" + e2.getMessage());
            return false;
        }
    }

    public String e() {
        ul.e("ARMoudle.HwActivityRecognition", "getCurrentActivity");
        IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
            return "unknown";
        }
        try {
            return iActivityRecognitionHardwareService.getCurrentActivity();
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "getCurrentActivity error:" + e2.getMessage());
            return "unknown";
        }
    }

    public HwActivityChangedExtendEvent d() {
        ul.e("ARMoudle.HwActivityRecognition", "getCurrentActivityExtend");
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        com.hihonor.systemserver.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent = null;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
            return null;
        }
        try {
            if (b == 1) {
                hwActivityChangedExtendEvent = iActivityRecognitionHardwareService.getCurrentActivityV1_1();
            } else {
                hwActivityChangedExtendEvent = iActivityRecognitionHardwareService.getCurrentActivity();
            }
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "getCurrentActivity error:" + e2.getMessage());
        }
        return a(hwActivityChangedExtendEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        ul.e("ARMoudle.HwActivityRecognition", "getARVersion");
        com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
        if (iActivityRecognitionHardwareService == null) {
            ul.c("ARMoudle.HwActivityRecognition", "mService is null.");
            return;
        }
        int i = -1;
        try {
            i = iActivityRecognitionHardwareService.getARVersion(this.m, 12);
            ul.e("ARMoudle.HwActivityRecognition", "version:" + i);
        } catch (RemoteException e2) {
            ul.c("ARMoudle.HwActivityRecognition", "getARVersion error:" + e2.getMessage());
        }
        b(i);
    }

    private IActivityRecognitionHardwareSink c(final HwActivityRecognitionHardwareSink hwActivityRecognitionHardwareSink) {
        if (hwActivityRecognitionHardwareSink == null) {
            return null;
        }
        return new IActivityRecognitionHardwareSink.Stub() { // from class: com.hihonor.android.location.activityrecognition.HwActivityRecognition.4
            @Override // com.hihonor.android.location.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException {
                ul.e("ARMoudle.HwActivityRecognition", "onActivityChanged_N...");
                hwActivityRecognitionHardwareSink.onActivityChanged(hwActivityChangedEvent);
            }
        };
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f1666a;
        private int b;
        private long c;
        private OtherParameters d;
        private int e;

        public int d() {
            return this.b;
        }

        public String e() {
            return this.f1666a;
        }

        public int b() {
            return this.e;
        }

        public long a() {
            return this.c;
        }

        public OtherParameters c() {
            return this.d;
        }

        public b(String str, int i, long j, OtherParameters otherParameters, int i2) {
            this.f1666a = str;
            this.e = i;
            this.c = j;
            this.d = otherParameters;
            this.b = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwActivityChangedEvent d(com.hihonor.systemserver.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) {
        if (hwActivityChangedEvent == null) {
            ul.c("ARMoudle.HwActivityRecognition", "tranferToHwActivityChangedEvent event is null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.hihonor.systemserver.activityrecognition.HwActivityRecognitionEvent hwActivityRecognitionEvent : hwActivityChangedEvent.getActivityRecognitionEvents()) {
            arrayList.add(new b(hwActivityRecognitionEvent.getActivity(), hwActivityRecognitionEvent.getEventType(), hwActivityRecognitionEvent.getTimestampNs(), null, hwActivityRecognitionEvent.getConfidence()));
            ul.e("ARMoudle.HwActivityRecognition", "onActivityChanged_O: " + d(hwActivityRecognitionEvent.getActivity()) + "  , " + hwActivityRecognitionEvent.getEventType() + "  , " + hwActivityRecognitionEvent.getTimestampNs() + "  ," + hwActivityRecognitionEvent.getConfidence());
        }
        HwActivityRecognitionEvent[] hwActivityRecognitionEventArr = new HwActivityRecognitionEvent[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            hwActivityRecognitionEventArr[i] = new HwActivityRecognitionEvent(bVar.e(), bVar.b(), bVar.a(), bVar.d());
        }
        return new HwActivityChangedEvent(hwActivityRecognitionEventArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwActivityChangedExtendEvent a(com.hihonor.systemserver.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        if (hwActivityChangedExtendEvent == null) {
            ul.c("ARMoudle.HwActivityRecognition", "hwActivityEvent is null.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.hihonor.systemserver.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            arrayList.add(new b(hwActivityRecognitionExtendEvent.getActivity(), hwActivityRecognitionExtendEvent.getEventType(), hwActivityRecognitionExtendEvent.getTimestampNs(), a(hwActivityRecognitionExtendEvent.getOtherParams()), hwActivityRecognitionExtendEvent.getConfidence()));
            ul.e("ARMoudle.HwActivityRecognition", "hwActivityEvent: " + d(hwActivityRecognitionExtendEvent.getActivity()) + "," + hwActivityRecognitionExtendEvent.getEventType() + "," + hwActivityRecognitionExtendEvent.getTimestampNs() + "," + hwActivityRecognitionExtendEvent.getConfidence());
        }
        HwActivityRecognitionExtendEvent[] hwActivityRecognitionExtendEventArr = new HwActivityRecognitionExtendEvent[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            hwActivityRecognitionExtendEventArr[i] = new HwActivityRecognitionExtendEvent(bVar.e(), bVar.b(), bVar.a(), bVar.c(), bVar.d());
        }
        return new HwActivityChangedExtendEvent(hwActivityRecognitionExtendEventArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HwEnvironmentChangedEvent c(com.hihonor.systemserver.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
        if (hwEnvironmentChangedEvent == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (com.hihonor.systemserver.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwEnvironmentChangedEvent.getEnvironmentRecognitionEvents()) {
            arrayList.add(new b(hwActivityRecognitionExtendEvent.getActivity(), hwActivityRecognitionExtendEvent.getEventType(), hwActivityRecognitionExtendEvent.getTimestampNs(), a(hwActivityRecognitionExtendEvent.getOtherParams()), hwActivityRecognitionExtendEvent.getConfidence()));
            ul.e("ARMoudle.HwActivityRecognition", "hwEnvironmentEvent: " + b(hwActivityRecognitionExtendEvent.getActivity()) + "," + hwActivityRecognitionExtendEvent.getEventType() + "," + hwActivityRecognitionExtendEvent.getTimestampNs() + "," + hwActivityRecognitionExtendEvent.getConfidence());
        }
        HwActivityRecognitionExtendEvent[] hwActivityRecognitionExtendEventArr = new HwActivityRecognitionExtendEvent[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            b bVar = (b) arrayList.get(i);
            hwActivityRecognitionExtendEventArr[i] = new HwActivityRecognitionExtendEvent(bVar.e(), bVar.b(), bVar.a(), bVar.c(), bVar.d());
        }
        return new HwEnvironmentChangedEvent(hwActivityRecognitionExtendEventArr);
    }

    private OtherParameters a(com.hihonor.systemserver.activityrecognition.OtherParameters otherParameters) {
        if (otherParameters == null) {
            return null;
        }
        return new OtherParameters(otherParameters.getmParam1(), otherParameters.getmParam2(), otherParameters.getmParam3(), otherParameters.getmParam4(), otherParameters.getmParam5());
    }

    private com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink b(final HwActivityRecognitionHardwareSink hwActivityRecognitionHardwareSink) {
        if (hwActivityRecognitionHardwareSink == null) {
            ul.c("ARMoudle.HwActivityRecognition", "createActivityRecognitionHardwareSink_O... sink is null");
            return null;
        }
        return new IActivityRecognitionHardwareSink.Stub() { // from class: com.hihonor.android.location.activityrecognition.HwActivityRecognition.2
            @Override // com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityChanged(com.hihonor.systemserver.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) throws RemoteException {
                ul.e("ARMoudle.HwActivityRecognition", "onActivityChanged_O...");
                ul.e("ARMoudle.HwActivityRecognition", "package name = " + HwActivityRecognition.this.h.getPackageName());
                hwActivityRecognitionHardwareSink.onActivityChanged(HwActivityRecognition.this.d(hwActivityChangedEvent));
            }

            @Override // com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onActivityExtendChanged(com.hihonor.systemserver.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) throws RemoteException {
                ul.c("ARMoudle.HwActivityRecognition", "onActivityExtendChanged...");
                hwActivityRecognitionHardwareSink.onActivityExtendChanged(HwActivityRecognition.this.a(hwActivityChangedExtendEvent));
            }

            @Override // com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareSink
            public void onEnvironmentChanged(com.hihonor.systemserver.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) throws RemoteException {
                ul.e("ARMoudle.HwActivityRecognition", "onEnvironmentChanged...");
                hwActivityRecognitionHardwareSink.onEnvironmentChanged(HwActivityRecognition.this.c(hwEnvironmentChangedEvent));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        try {
            IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.i;
            if (iActivityRecognitionHardwareService != null) {
                if (iActivityRecognitionHardwareService.providerLoadOk()) {
                    this.g.removeMessages(0);
                    this.l.onServiceConnected();
                } else {
                    this.g.sendEmptyMessageDelayed(0, 500L);
                }
            }
        } catch (RemoteException unused) {
            ul.c("ARMoudle.HwActivityRecognition", "providerLoadOk fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.f > 10) {
            ul.e("ARMoudle.HwActivityRecognition", "try connect 10 times, connection fail");
            return;
        }
        if (d >= 25) {
            if (this.k == null) {
                ul.e("ARMoudle.HwActivityRecognition", String.valueOf(this.h.getPackageName()) + " bind ar service.");
                Intent intent = new Intent();
                intent.setClassName("com.hihonor.systemserver", "com.hihonor.systemserver.activityrecognition.ActivityRecognitionService");
                ul.e("ARMoudle.HwActivityRecognition", "bindIntent = " + intent);
                this.h.bindService(intent, this.j, 1);
                this.f = this.f + 1;
                this.g.sendEmptyMessageDelayed(1, 2000L);
                return;
            }
            return;
        }
        if (this.i == null) {
            ul.e("ARMoudle.HwActivityRecognition", String.valueOf(this.h.getPackageName()) + " bind ar service.");
            Intent intent2 = new Intent();
            intent2.setClassName("com.hihonor.android.location.activityrecognition", "com.hihonor.android.location.activityrecognition.ActivityRecognitionService");
            this.h.bindService(intent2, this.j, 1);
            this.f++;
            this.g.sendEmptyMessageDelayed(1, 2000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        try {
            if (d >= 25) {
                com.hihonor.systemserver.activityrecognition.IActivityRecognitionHardwareService iActivityRecognitionHardwareService = this.k;
                if (iActivityRecognitionHardwareService != null) {
                    iActivityRecognitionHardwareService.asBinder().linkToDeath(this.c, 0);
                }
            } else {
                IActivityRecognitionHardwareService iActivityRecognitionHardwareService2 = this.i;
                if (iActivityRecognitionHardwareService2 != null) {
                    iActivityRecognitionHardwareService2.asBinder().linkToDeath(this.c, 0);
                }
            }
        } catch (RemoteException unused) {
            ul.c("ARMoudle.HwActivityRecognition", "IBinder register linkToDeath function fail.");
        }
    }

    class e implements IBinder.DeathRecipient {
        private e() {
        }

        /* synthetic */ e(HwActivityRecognition hwActivityRecognition, e eVar) {
            this();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ul.e("ARMoudle.HwActivityRecognition", "Ar service has died!");
            if (HwActivityRecognition.this.l != null) {
                HwActivityRecognition.this.l.onServiceDisconnected();
            }
            if (HwActivityRecognition.d < 25) {
                if (HwActivityRecognition.this.i != null) {
                    HwActivityRecognition.this.i.asBinder().unlinkToDeath(HwActivityRecognition.this.c, 0);
                    HwActivityRecognition.this.i = null;
                    return;
                }
                return;
            }
            if (HwActivityRecognition.this.k != null) {
                HwActivityRecognition.this.k.asBinder().unlinkToDeath(HwActivityRecognition.this.c, 0);
                HwActivityRecognition.this.k = null;
            }
        }
    }
}
