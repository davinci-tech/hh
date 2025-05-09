package defpackage;

import com.huawei.android.location.activityrecognition.HwActivityChangedEvent;
import com.huawei.android.location.activityrecognition.HwActivityChangedExtendEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognition;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionExtendEvent;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink;
import com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection;
import com.huawei.android.location.activityrecognition.HwEnvironmentChangedEvent;
import com.huawei.healthcloud.plugintrack.manager.inteface.IActivityRecognitionStateListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gtd;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Iterator;
import okhttp3.internal.connection.RealConnection;

/* loaded from: classes4.dex */
public class gtd {
    private boolean b;
    private IActivityRecognitionStateListener e;
    private HwActivityRecognition d = null;
    private com.hihonor.android.location.activityrecognition.HwActivityRecognition c = null;

    private boolean n() {
        return false;
    }

    public gtd(IActivityRecognitionStateListener iActivityRecognitionStateListener) {
        this.e = iActivityRecognitionStateListener;
    }

    public void d() {
        this.b = false;
        if (CommonUtil.bj()) {
            this.c = new com.hihonor.android.location.activityrecognition.HwActivityRecognition(BaseApplication.getContext());
            jdx.b(new Runnable() { // from class: gth
                @Override // java.lang.Runnable
                public final void run() {
                    gtd.this.g();
                }
            });
        } else {
            this.d = new HwActivityRecognition(BaseApplication.getContext());
            jdx.b(new Runnable() { // from class: gtg
                @Override // java.lang.Runnable
                public final void run() {
                    gtd.this.e();
                }
            });
        }
        ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "ActivityRecognitionMotionDetector is start");
    }

    public void c() {
        this.b = true;
        if (CommonUtil.bj()) {
            f();
        } else {
            i();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            this.d.b(new HwActivityRecognitionHardwareSink() { // from class: gtd.5
                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityChanged(HwActivityChangedEvent hwActivityChangedEvent) {
                    gtd.this.d(hwActivityChangedEvent);
                }

                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityExtendChanged(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
                    gtd.this.b(hwActivityChangedExtendEvent);
                }

                @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onEnvironmentChanged(HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
                    gtd.this.e(hwEnvironmentChangedEvent);
                }
            }, new AnonymousClass1());
        } catch (SecurityException e) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "connectActivityRecognitionModule ", LogAnonymous.b((Throwable) e));
        } catch (Exception e2) {
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "connectActivityRecognitionModule exception ", LogAnonymous.b((Throwable) e2));
        }
    }

    /* renamed from: gtd$1, reason: invalid class name */
    class AnonymousClass1 implements HwActivityRecognitionServiceConnection {
        AnonymousClass1() {
        }

        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection
        public void onServiceConnected() {
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "HwActivityRecognitionServiceConnection onServiceConnected()");
            jdx.b(new Runnable() { // from class: gtf
                @Override // java.lang.Runnable
                public final void run() {
                    gtd.AnonymousClass1.this.c();
                }
            });
        }

        /* synthetic */ void c() {
            gtd.this.k();
        }

        @Override // com.huawei.android.location.activityrecognition.HwActivityRecognitionServiceConnection
        public void onServiceDisconnected() {
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "HwActivityRecognitionServiceConnection onServiceDisconnected()");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            this.c.a(new com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink() { // from class: gtd.2
                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityChanged(com.hihonor.android.location.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) {
                    gtd.this.c(hwActivityChangedEvent);
                }

                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onActivityExtendChanged(com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
                    gtd.this.d(hwActivityChangedExtendEvent);
                }

                @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionHardwareSink
                public void onEnvironmentChanged(com.hihonor.android.location.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
                    gtd.this.a(hwEnvironmentChangedEvent);
                }
            }, new AnonymousClass3());
        } catch (SecurityException e) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "connectHnActivityRecognitionModule ", LogAnonymous.b((Throwable) e));
        } catch (Exception unused) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "connectHnActivityRecognitionModule exception");
        }
    }

    /* renamed from: gtd$3, reason: invalid class name */
    class AnonymousClass3 implements com.hihonor.android.location.activityrecognition.HwActivityRecognitionServiceConnection {
        AnonymousClass3() {
        }

        @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionServiceConnection
        public void onServiceConnected() {
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "connectHnActivityRecognitionModule onServiceConnected()");
            jdx.b(new Runnable() { // from class: gtm
                @Override // java.lang.Runnable
                public final void run() {
                    gtd.AnonymousClass3.this.b();
                }
            });
        }

        /* synthetic */ void b() {
            gtd.this.o();
        }

        @Override // com.hihonor.android.location.activityrecognition.HwActivityRecognitionServiceConnection
        public void onServiceDisconnected() {
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "connectHnActivityRecognitionModule onServiceDisconnected()");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HwActivityChangedEvent hwActivityChangedEvent) {
        if (hwActivityChangedEvent == null) {
            return;
        }
        Iterator<HwActivityRecognitionEvent> it = hwActivityChangedEvent.getActivityRecognitionEvents().iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(com.hihonor.android.location.activityrecognition.HwActivityChangedEvent hwActivityChangedEvent) {
        if (hwActivityChangedEvent == null) {
            return;
        }
        Iterator<com.hihonor.android.location.activityrecognition.HwActivityRecognitionEvent> it = hwActivityChangedEvent.getActivityRecognitionEvents().iterator();
        while (it.hasNext()) {
            a(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        if (hwActivityChangedExtendEvent == null) {
            return;
        }
        for (HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            if (hwActivityRecognitionExtendEvent != null) {
                LogUtil.a("Track_ActivityRecognitionMotionDetector", hwActivityRecognitionExtendEvent.getActivity(), " ", Integer.valueOf(hwActivityRecognitionExtendEvent.getEventType()));
            }
            LogUtil.a("Track_ActivityRecognitionMotionDetector", "onActivityExtendChanged ", this.d.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        if (hwActivityChangedExtendEvent == null) {
            return;
        }
        for (com.hihonor.android.location.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            if (hwActivityRecognitionExtendEvent != null) {
                LogUtil.a("Track_ActivityRecognitionMotionDetector", hwActivityRecognitionExtendEvent.getActivity(), " ", Integer.valueOf(hwActivityRecognitionExtendEvent.getEventType()));
            }
            LogUtil.a("Track_ActivityRecognitionMotionDetector", "handleHnActivityExtendChange ", this.c.e());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
        if (hwEnvironmentChangedEvent == null) {
            return;
        }
        for (HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwEnvironmentChangedEvent.getEnvironmentRecognitionEvents()) {
            if (hwActivityRecognitionExtendEvent != null) {
                LogUtil.a("Track_ActivityRecognitionMotionDetector", hwActivityRecognitionExtendEvent.getActivity(), " ", Integer.valueOf(hwActivityRecognitionExtendEvent.getEventType()));
            }
        }
        LogUtil.a("Track_ActivityRecognitionMotionDetector", "onEnvironmentChanged ", this.d.e());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.hihonor.android.location.activityrecognition.HwEnvironmentChangedEvent hwEnvironmentChangedEvent) {
        if (hwEnvironmentChangedEvent == null) {
            return;
        }
        for (com.hihonor.android.location.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwEnvironmentChangedEvent.getEnvironmentRecognitionEvents()) {
            if (hwActivityRecognitionExtendEvent != null) {
                LogUtil.a("Track_ActivityRecognitionMotionDetector", hwActivityRecognitionExtendEvent.getActivity(), " ", Integer.valueOf(hwActivityRecognitionExtendEvent.getEventType()));
            }
        }
        LogUtil.a("Track_ActivityRecognitionMotionDetector", "handleHnEnvironmentChange ", this.c.e());
    }

    private void d(HwActivityRecognitionEvent hwActivityRecognitionEvent) {
        if (hwActivityRecognitionEvent == null || hwActivityRecognitionEvent.getActivity() == null) {
            LogUtil.b("Track_ActivityRecognitionMotionDetector", "event or event getActivity is null, system error ");
            return;
        }
        if (this.e == null) {
            LogUtil.b("Track_ActivityRecognitionMotionDetector", "mActivityRecognitionListener is null, system error ");
            return;
        }
        LogUtil.a("Track_ActivityRecognitionMotionDetector", hwActivityRecognitionEvent.getActivity(), " ", Integer.valueOf(hwActivityRecognitionEvent.getEventType()));
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.still") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(0);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.still") && hwActivityRecognitionEvent.getEventType() == 2) {
            this.e.onStateChange(1);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.walking") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(2);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.running") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(3);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.on_bicycle") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(4);
        } else if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.in_vehicle") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(5);
        }
    }

    private void a(com.hihonor.android.location.activityrecognition.HwActivityRecognitionEvent hwActivityRecognitionEvent) {
        if (hwActivityRecognitionEvent == null || hwActivityRecognitionEvent.getActivity() == null) {
            LogUtil.b("Track_ActivityRecognitionMotionDetector", "activityHnChanged event or event getActivity is null, system error ");
            return;
        }
        if (this.e == null) {
            LogUtil.b("Track_ActivityRecognitionMotionDetector", "activityHnChanged mActivityRecognitionListener is null, system error ");
            return;
        }
        LogUtil.a("Track_ActivityRecognitionMotionDetector", "activityHnChanged ", hwActivityRecognitionEvent.getActivity(), " ", Integer.valueOf(hwActivityRecognitionEvent.getEventType()));
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.still") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(0);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.still") && hwActivityRecognitionEvent.getEventType() == 2) {
            this.e.onStateChange(1);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.walking") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(2);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.running") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(3);
            return;
        }
        if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.on_bicycle") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(4);
        } else if (hwActivityRecognitionEvent.getActivity().equals("android.activity_recognition.in_vehicle") && hwActivityRecognitionEvent.getEventType() == 1) {
            this.e.onStateChange(5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        if (this.d == null) {
            ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "mStillActivityRecognition is null");
            return;
        }
        if (this.b) {
            ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "sport is stop, not enableActivityEvent");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.d.d("android.activity_recognition.still", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.d.d("android.activity_recognition.still", 2, 2000000000L);
        this.d.d("android.activity_recognition.walking", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.d.d("android.activity_recognition.walking", 2, 2000000000L);
        this.d.d("android.activity_recognition.running", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.d.d("android.activity_recognition.running", 2, 2000000000L);
        this.d.d("android.activity_recognition.on_bicycle", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.d.d("android.activity_recognition.on_bicycle", 2, 2000000000L);
        this.d.d("android.activity_recognition.in_vehicle", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.d.d("android.activity_recognition.in_vehicle", 2, 2000000000L);
        ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "serviceConnectedEnable cost:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.c == null) {
            ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "mHnStillActivityRecognition is null");
            return;
        }
        if (this.b) {
            ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "sport is stop, not enableActivityEvent");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.c.b("android.activity_recognition.still", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.c.b("android.activity_recognition.still", 2, 2000000000L);
        this.c.b("android.activity_recognition.walking", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.c.b("android.activity_recognition.walking", 2, 2000000000L);
        this.c.b("android.activity_recognition.running", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.c.b("android.activity_recognition.running", 2, 2000000000L);
        this.c.b("android.activity_recognition.on_bicycle", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.c.b("android.activity_recognition.on_bicycle", 2, 2000000000L);
        this.c.b("android.activity_recognition.in_vehicle", 1, RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        this.c.b("android.activity_recognition.in_vehicle", 2, 2000000000L);
        ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "serviceHnConnectedEnable cost:", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    private void i() {
        ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "disconnectWithActivityRecognitionModule ");
        try {
            HwActivityRecognition hwActivityRecognition = this.d;
            if (hwActivityRecognition == null) {
                ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "mStillActivityRecognition is null");
                return;
            }
            hwActivityRecognition.b("android.activity_recognition.still", 1);
            this.d.b("android.activity_recognition.still", 2);
            this.d.b("android.activity_recognition.walking", 1);
            this.d.b("android.activity_recognition.running", 1);
            this.d.b("android.activity_recognition.on_bicycle", 1);
            this.d.b("android.activity_recognition.in_vehicle", 1);
            this.d.b("android.activity_recognition.walking", 2);
            this.d.b("android.activity_recognition.running", 2);
            this.d.b("android.activity_recognition.on_bicycle", 2);
            this.d.b("android.activity_recognition.in_vehicle", 2);
            this.d.c();
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "disconnectWithActivityRecognitionModule finish");
        } catch (SecurityException e) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "disconnectWithActivityRecognitionModule ", LogAnonymous.b((Throwable) e));
        } catch (Exception unused) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "disconnectWithActivityRecognitionModule exception");
        }
    }

    private void f() {
        ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "disconnectWithHnActivityRecognitionModule ");
        try {
            com.hihonor.android.location.activityrecognition.HwActivityRecognition hwActivityRecognition = this.c;
            if (hwActivityRecognition == null) {
                ReleaseLogUtil.d("Track_ActivityRecognitionMotionDetector", "mHnStillActivityRecognition is null");
                return;
            }
            hwActivityRecognition.c("android.activity_recognition.still", 1);
            this.c.c("android.activity_recognition.still", 2);
            this.c.c("android.activity_recognition.walking", 1);
            this.c.c("android.activity_recognition.running", 1);
            this.c.c("android.activity_recognition.on_bicycle", 1);
            this.c.c("android.activity_recognition.in_vehicle", 1);
            this.c.c("android.activity_recognition.walking", 2);
            this.c.c("android.activity_recognition.running", 2);
            this.c.c("android.activity_recognition.on_bicycle", 2);
            this.c.c("android.activity_recognition.in_vehicle", 2);
            this.c.a();
            ReleaseLogUtil.e("Track_ActivityRecognitionMotionDetector", "disconnectWithHnActivityRecognitionModule finish");
        } catch (SecurityException e) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "disconnectWithHnActivityRecognitionModule ", LogAnonymous.b((Throwable) e));
        } catch (Exception unused) {
            ReleaseLogUtil.c("Track_ActivityRecognitionMotionDetector", "disconnectWithHnActivityRecognitionModule exception");
        }
    }

    public String a() {
        if (CommonUtil.bj()) {
            return a("unkwon");
        }
        if (this.d == null) {
            return "unkwon";
        }
        if (n()) {
            return this.d.e();
        }
        HwActivityChangedExtendEvent f = this.d.f();
        return f == null ? "unkwon" : e(f);
    }

    private String e(HwActivityChangedExtendEvent hwActivityChangedExtendEvent) {
        String str = "";
        long j = 0;
        for (HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : hwActivityChangedExtendEvent.getActivityRecognitionExtendEvents()) {
            if (hwActivityRecognitionExtendEvent != null) {
                if (j == 0) {
                    j = hwActivityRecognitionExtendEvent.getTimestampNs();
                    str = hwActivityRecognitionExtendEvent.getActivity();
                } else if (j > hwActivityRecognitionExtendEvent.getTimestampNs()) {
                    j = hwActivityRecognitionExtendEvent.getTimestampNs();
                    str = hwActivityRecognitionExtendEvent.getActivity();
                }
            }
        }
        return str;
    }

    private String a(String str) {
        long timestampNs;
        String activity;
        if (this.c == null) {
            return str;
        }
        if (n()) {
            return this.c.e();
        }
        com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent d = this.c.d();
        if (d == null) {
            return str;
        }
        String str2 = "";
        long j = 0;
        for (com.hihonor.android.location.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : d.getActivityRecognitionExtendEvents()) {
            if (hwActivityRecognitionExtendEvent != null) {
                if (j == 0) {
                    timestampNs = hwActivityRecognitionExtendEvent.getTimestampNs();
                    activity = hwActivityRecognitionExtendEvent.getActivity();
                } else if (j > hwActivityRecognitionExtendEvent.getTimestampNs()) {
                    timestampNs = hwActivityRecognitionExtendEvent.getTimestampNs();
                    activity = hwActivityRecognitionExtendEvent.getActivity();
                }
                long j2 = timestampNs;
                str2 = activity;
                j = j2;
            }
        }
        return str2;
    }

    public boolean b() {
        if (CommonUtil.bj()) {
            if (this.c == null) {
                return false;
            }
            if (n()) {
                if ("android.activity_recognition.still".equals(this.c.e())) {
                    LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_MAGIC_SYSTEM curState is ", "android.activity_recognition.still");
                    return true;
                }
                LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_MAGIC_SYSTEM curState is ", this.c.e());
            } else {
                return j().booleanValue();
            }
        } else {
            if (this.d == null) {
                return false;
            }
            if (n()) {
                if ("android.activity_recognition.still".equals(this.d.e())) {
                    LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_EMUI_SYSTEM curState is ", "android.activity_recognition.still");
                    return true;
                }
                LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_EMUI_SYSTEM curState is ", this.d.e());
            } else {
                return h().booleanValue();
            }
        }
        return false;
    }

    private Boolean h() {
        HwActivityChangedExtendEvent f = this.d.f();
        if (f == null) {
            return false;
        }
        for (HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : f.getActivityRecognitionExtendEvents()) {
            if ("android.activity_recognition.still".equals(hwActivityRecognitionExtendEvent.getActivity())) {
                LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_EMUI_SYSTEM curState is ", "android.activity_recognition.still");
                return true;
            }
            LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_EMUI_SYSTEM curState is ", hwActivityRecognitionExtendEvent.getActivity());
        }
        return false;
    }

    private Boolean j() {
        com.hihonor.android.location.activityrecognition.HwActivityChangedExtendEvent d = this.c.d();
        if (d == null) {
            return false;
        }
        for (com.hihonor.android.location.activityrecognition.HwActivityRecognitionExtendEvent hwActivityRecognitionExtendEvent : d.getActivityRecognitionExtendEvents()) {
            if ("android.activity_recognition.still".equals(hwActivityRecognitionExtendEvent.getActivity())) {
                LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_MAGIC_SYSTEM curState is ", "android.activity_recognition.still");
                return true;
            }
            LogUtil.a("Track_ActivityRecognitionMotionDetector", "IS_MAGIC_SYSTEM curState is ", hwActivityRecognitionExtendEvent.getActivity());
        }
        return false;
    }
}
