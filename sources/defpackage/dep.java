package defpackage;

import android.text.TextUtils;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.profile.profile.DeviceProfile;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class dep {

    /* renamed from: a, reason: collision with root package name */
    private DeviceProfile f11627a;
    private Map<String, Object> b;
    private Map<String, Object> d;

    private dep() {
    }

    public DeviceProfile c() {
        return this.f11627a;
    }

    public Map<String, Object> b() {
        return this.b;
    }

    public Map<String, Object> a() {
        return this.d;
    }

    public static class e {
        DeviceProfile b = null;

        /* renamed from: a, reason: collision with root package name */
        private Map<String, Object> f11628a = null;
        private Map<String, Object> d = null;
        private Map<String, Object> c = null;

        public e b(String str) {
            if (this.b == null) {
                this.b = new DeviceProfile();
            }
            this.b.setId(str);
            return this;
        }

        public e m(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put(ProfileRequestConstants.SWV, str);
            return this;
        }

        public e e(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("model", str);
            return this;
        }

        public e f(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("devType", str);
            return this;
        }

        public e g(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("prodId", str);
            return this;
        }

        public e a(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put(ProfileRequestConstants.MANU, str);
            return this;
        }

        public e b(long j) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("timestamp", Long.valueOf(j));
            return this;
        }

        public e d(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put(ProfileRequestConstants.HIV, str);
            return this;
        }

        public e i(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("deviceName", str);
            return this;
        }

        public e h(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("udid", str);
            return this;
        }

        public e c(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put("mac", str);
            return this;
        }

        public e j(String str) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            if (!TextUtils.isEmpty(str)) {
                this.f11628a.put("sn", str);
            }
            return this;
        }

        public e d(int i) {
            if (this.f11628a == null) {
                this.f11628a = new HashMap(10);
            }
            this.f11628a.put(ProfileRequestConstants.PROT_TYPE, Integer.valueOf(i));
            return this;
        }

        public e a(int i) {
            if (this.d == null) {
                this.d = new HashMap(10);
            }
            this.d.put("characteristic.bt.connectionStatus", Integer.valueOf(i));
            return this;
        }

        public e l(String str) {
            if (this.c == null) {
                this.c = new HashMap(10);
            }
            this.c.put("type", str);
            return this;
        }

        public dep c() {
            if (this.b == null || this.f11628a == null || this.d == null || this.c == null) {
                return null;
            }
            dep depVar = new dep();
            depVar.f11627a = this.b;
            this.b.setIsNeedCloud(true);
            if (this.f11628a.get("devType") instanceof String) {
                this.b.setType((String) this.f11628a.get("devType"));
            }
            this.b.addEntities(this.f11628a);
            depVar.b = this.d;
            depVar.d = this.c;
            return depVar;
        }
    }
}
