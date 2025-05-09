package defpackage;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class czd {

    @SerializedName("historyRecords")
    private boolean b;

    @SerializedName("heartRateControl")
    private boolean c;

    @SerializedName("lightEffectSetting")
    private boolean d;

    @SerializedName("marketing")
    private boolean e;

    @SerializedName("uuid")
    private String f;

    @SerializedName("personalPerformance")
    private d i;

    @SerializedName("menuItems")
    private b j = new b();

    @SerializedName("modes")
    private e h = new e();

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("enhanceMode")
    private a f11548a = new a();

    public boolean e() {
        return this.c;
    }

    public boolean h() {
        return this.b;
    }

    public boolean f() {
        return this.e;
    }

    public boolean i() {
        return this.d;
    }

    public b d() {
        return this.j;
    }

    public e a() {
        return this.h;
    }

    public a c() {
        return this.f11548a;
    }

    public d b() {
        return this.i;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("ota")
        private d f11550a;

        @SerializedName("deleteDevice")
        private boolean b;

        @SerializedName("deviceInfo")
        private boolean c;

        @SerializedName("authorizeHeightWeight")
        private boolean d;

        public boolean c() {
            return this.b;
        }

        public d d() {
            return this.f11550a;
        }

        public boolean e() {
            return this.c;
        }

        public boolean b() {
            return this.d;
        }

        public static class d {

            @SerializedName("otaRedPoint")
            private boolean d;

            public boolean d() {
                return this.d;
            }
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("countingMode")
        private boolean f11552a;

        @SerializedName("freeMode")
        private boolean b;

        @SerializedName("fancyMode")
        private boolean c;

        @SerializedName("caloriesMode")
        private boolean d;

        @SerializedName("distanceMode")
        private boolean e;

        @SerializedName("timingMode")
        private boolean h;

        public boolean c() {
            return this.b;
        }

        public boolean i() {
            return this.h;
        }

        public boolean a() {
            return this.e;
        }

        public boolean e() {
            return this.d;
        }

        public boolean b() {
            return this.f11552a;
        }

        public boolean d() {
            return this.c;
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("intervalTraining")
        private boolean f11549a;

        @SerializedName("courseJumpSetting")
        private boolean b;

        @SerializedName("music")
        private boolean c;

        @SerializedName("multiplayer")
        private boolean d;

        @SerializedName("musicJumpSetting")
        private boolean e;

        @SerializedName("ropeSetting")
        private boolean f;

        @SerializedName("warmUp")
        private boolean h;

        public boolean f() {
            return this.h;
        }

        public boolean c() {
            return this.c;
        }

        public boolean e() {
            return this.b;
        }

        public boolean a() {
            return this.e;
        }

        public boolean d() {
            return this.f11549a;
        }

        public boolean i() {
            return this.f;
        }

        public boolean b() {
            return this.d;
        }
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("ropePerformance ")
        private boolean f11551a;

        public boolean c() {
            return this.f11551a;
        }
    }
}
