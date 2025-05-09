package defpackage;

import android.content.Intent;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.suggestion.CoachController;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class fnu {
    private static Map<String, Object> b(e eVar) {
        HashMap hashMap = new HashMap(7);
        if (eVar == null) {
            LogUtil.h("Suggestion_CoachBiHelper", "baseCoachBi coachBiKey == null");
            return hashMap;
        }
        hashMap.put("click", 1);
        hashMap.put("course_type", eVar.d());
        hashMap.put("body_position", eVar.e());
        hashMap.put("course_rate", eVar.c());
        hashMap.put("resourceType", Integer.valueOf(eVar.j()));
        hashMap.put("workout_name", eVar.i());
        hashMap.put("workout_id", eVar.a());
        hashMap.put("isAICourse", eVar.f());
        return hashMap;
    }

    public static void d(e eVar, CoachController.StatusSource statusSource) {
        Map<String, Object> b = b(eVar);
        if (eVar != null) {
            b.put("eventType", Integer.valueOf(eVar.b()));
        }
        if (statusSource.equals(CoachController.StatusSource.NEW_LINK_WEAR)) {
            b.put("ControlMode", PutDataRequest.WEAR_URI_SCHEME);
        } else {
            b.put("ControlMode", "app");
        }
        b("1130066", b);
    }

    public static void a(e eVar, int i) {
        b(eVar, i, CoachController.StatusSource.APP);
    }

    public static void b(e eVar, int i, CoachController.StatusSource statusSource) {
        Map<String, Object> b = b(eVar);
        if (eVar != null) {
            b.put("event", Integer.valueOf(i));
        }
        if (statusSource.equals(CoachController.StatusSource.NEW_LINK_WEAR)) {
            b.put("ControlMode", PutDataRequest.WEAR_URI_SCHEME);
        } else {
            b.put("ControlMode", "app");
        }
        b("1130067", b);
    }

    private static void b(final String str, final Map<String, Object> map) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fnv
            @Override // java.lang.Runnable
            public final void run() {
                ixx.d().d(BaseApplication.e(), str, map, 0);
            }
        });
    }

    public static e aBy_(Intent intent, String str, String str2, String str3) {
        e eVar = new e();
        eVar.a(str);
        eVar.e(str2);
        eVar.f(str3);
        if (intent == null) {
            LogUtil.h("Suggestion_CoachBiHelper", "getCoachBiValue intent == null");
            return eVar;
        }
        eVar.d(intent.getIntExtra("commodityFlag", 0));
        if (!(intent.getSerializableExtra("BI_INTENT_COURSE") instanceof Map)) {
            LogUtil.h("Suggestion_CoachBiHelper", "getCoachBiValue not instanceof Map");
            return eVar;
        }
        Map map = (Map) intent.getSerializableExtra("BI_INTENT_COURSE");
        if (map != null) {
            eVar.c(a((Map<String, Object>) map, "course_type"));
            eVar.b(a((Map<String, Object>) map, "body_position"));
            eVar.d(a((Map<String, Object>) map, "course_rate"));
        }
        return eVar;
    }

    public static String a(Map<String, Object> map, String str) {
        if (map == null || map.isEmpty() || !map.containsKey(str)) {
            return "";
        }
        if (map.get(str) instanceof String) {
            return (String) map.get(str);
        }
        if (map.get(str) instanceof Integer) {
            return (String) map.get(str);
        }
        return "";
    }

    public static class e {
        private String c;
        private int e;
        private String f;
        private int g;
        private String j;
        private String b = "";

        /* renamed from: a, reason: collision with root package name */
        private String f12576a = "";
        private String d = "";

        public void c(String str) {
            this.b = str;
        }

        public String d() {
            return this.b;
        }

        public void b(String str) {
            this.f12576a = str;
        }

        public String e() {
            return this.f12576a;
        }

        public void d(String str) {
            this.d = str;
        }

        public String c() {
            return this.d;
        }

        public void b(int i) {
            this.e = i;
        }

        public int b() {
            return this.e;
        }

        public void d(int i) {
            this.g = i;
        }

        public int j() {
            return this.g;
        }

        public void a(String str) {
            this.f = str;
        }

        public String i() {
            return this.f;
        }

        public void e(String str) {
            this.c = str;
        }

        public String a() {
            return this.c;
        }

        public String f() {
            return this.j;
        }

        public void f(String str) {
            this.j = str;
        }
    }
}
