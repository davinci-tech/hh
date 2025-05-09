package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import com.huawei.pluginachievement.manager.model.MedalConstants;

/* loaded from: classes3.dex */
public class esk implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("timestamp")
    private Long f12224a;

    @SerializedName(MedalConstants.EVENT_KEY)
    private String b;

    @SerializedName(MedalConstants.EVENT_KEYTYPE)
    private String d;

    @SerializedName("value")
    private e e;

    private esk(c cVar) {
        this.b = cVar.d;
        this.d = cVar.e;
        this.f12224a = cVar.c;
        this.e = cVar.b;
    }

    public static class e {

        @SerializedName("duration")
        private Integer b;

        @SerializedName("calorie")
        private Float c;

        private e(b bVar) {
            this.c = bVar.d;
            this.b = bVar.b;
        }

        public static final class b {
            private Integer b;
            private Float d;

            public b e(Float f) {
                this.d = f;
                return this;
            }

            public b e(Integer num) {
                this.b = num;
                return this;
            }

            public e c() {
                return new e(this);
            }
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bs();
    }

    public static final class c {
        private e b;
        private Long c;
        private String d;
        private String e;

        public c a(String str) {
            this.d = str;
            return this;
        }

        public c c(String str) {
            this.e = str;
            return this;
        }

        public c e(Long l) {
            this.c = l;
            return this;
        }

        public c e(e eVar) {
            this.b = eVar;
            return this;
        }

        public esk d() {
            return new esk(this);
        }
    }
}
