package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes8.dex */
public class bds {

    /* renamed from: a, reason: collision with root package name */
    public static final Map<String, String> f338a = new ConcurrentHashMap(0);
    static final Timer c = new Timer("VoipResendHandle");
    public final Map<String, String> d = new HashMap();
    public final Map<String, String> e = new HashMap();
    private TimerTask b = null;

    public void a() {
        if (this.b != null) {
            LogUtil.a("VoipResendHandle", "onNotificationRemoved cleaResendLongVibrateCache timerTask cancel");
            this.b.cancel();
        } else {
            LogUtil.a("VoipResendHandle", "onNotificationRemoved clearResendLongVibrateCache timerTask is null");
        }
    }

    public void b() {
        this.d.put("com.whatsapp", "reject_call");
        this.d.put("jp.naver.line.android", "com.linecorp.intent.action.voip.default");
        this.d.put("com.instagram.android", "DECLINE");
        this.d.put("org.telegram.messenger", "org.telegram.messenger.DECLINE_CALL");
        this.d.put("com.facebook.orca", "com.facebook.intent.action.prod.RTC_DECLINE_CALL_ACTION");
        this.e.put("com.whatsapp", "hangup_call");
        this.e.put("jp.naver.line.android", "com.linecorp.intent.action.voip.default");
        this.e.put("com.instagram.android", "LEAVE");
        this.e.put("org.telegram.messenger", "org.telegram.messenger.END_CALL");
        this.e.put("com.facebook.orca", "com.facebook.rtc.fbwebrtc.intent.action.HOSTED_FRAGMENT_SHOW_UI");
    }

    public void b(String str) {
        LogUtil.b("VoipResendHandle", "createResendLongVibrateCache put  app=", str);
        Map<String, String> map = f338a;
        map.put(str, str);
        TimerTask timerTask = this.b;
        if (timerTask != null) {
            timerTask.cancel();
        }
        try {
            TimerTask a2 = a(map, str);
            this.b = a2;
            c.schedule(a2, 60000L);
        } catch (IllegalArgumentException | IllegalStateException e) {
            ReleaseLogUtil.c("VoipResendHandle", "activeClearMapTimerTaskAfterGap exception: ", ExceptionUtils.d(e));
        }
    }

    private TimerTask a(final Map map, final String str) {
        return new TimerTask() { // from class: bds.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                map.clear();
                ReleaseLogUtil.e("Notify_VoipResendHandle", str, " map been cleared");
            }
        };
    }
}
