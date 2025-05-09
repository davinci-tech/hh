package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.networkclient.IRequest;

/* loaded from: classes3.dex */
public class erw implements IRequest {

    @SerializedName("userBestRecords")
    private d b;

    @SerializedName("bestRecords")
    private e c;

    @SerializedName("planId")
    private String e;

    private erw(c cVar) {
        this.e = cVar.b;
        this.c = cVar.d;
        this.b = cVar.f12203a;
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("7")
        private Integer f12205a;

        @SerializedName("3")
        private Integer b;

        @SerializedName("6")
        private Integer c;

        @SerializedName("4")
        private Integer d;

        @SerializedName("103")
        private Integer e;

        @SerializedName("8")
        private Integer f;

        @SerializedName("11")
        private Float g;

        @SerializedName("2")
        private Integer h;

        @SerializedName("5")
        private Integer i;

        @SerializedName("1")
        private Integer j;

        @SerializedName("10")
        private Integer l;

        @SerializedName(MessageConstant.CERTIFICATE)
        private String m;

        @SerializedName("9")
        private Float n;

        private e(c cVar) {
            this.e = cVar.b;
            this.j = cVar.g;
            this.h = cVar.h;
            this.b = cVar.e;
            this.d = cVar.f12206a;
            this.i = cVar.f;
            this.c = cVar.d;
            this.f12205a = cVar.c;
            this.f = cVar.i;
            this.n = cVar.m;
            this.l = cVar.o;
            this.m = cVar.l;
            this.g = cVar.j;
        }

        public static final class c {

            /* renamed from: a, reason: collision with root package name */
            private Integer f12206a;
            private Integer b;
            private Integer c;
            private Integer d;
            private Integer e;
            private Integer f;
            private Integer g;
            private Integer h;
            private Integer i;
            private Float j;
            private String l;
            private Float m;
            private Integer o;

            public c e(Integer num) {
                if (num.intValue() > 0) {
                    this.b = num;
                }
                return this;
            }

            public c j(Integer num) {
                if (num.intValue() > 0) {
                    this.g = num;
                }
                return this;
            }

            public c h(Integer num) {
                if (num.intValue() > 0) {
                    this.h = num;
                }
                return this;
            }

            public c b(Integer num) {
                if (num.intValue() > 0) {
                    this.e = num;
                }
                return this;
            }

            public c a(Integer num) {
                if (num.intValue() > 0) {
                    this.f12206a = num;
                }
                return this;
            }

            public c f(Integer num) {
                if (num.intValue() > 0) {
                    this.f = num;
                }
                return this;
            }

            public c d(Integer num) {
                if (num.intValue() > 0) {
                    this.d = num;
                }
                return this;
            }

            public c c(Integer num) {
                if (num.intValue() > 0) {
                    this.c = num;
                }
                return this;
            }

            public c i(Integer num) {
                if (num.intValue() > 0) {
                    this.i = num;
                }
                return this;
            }

            public c a(Float f) {
                if (f.floatValue() > 0.0f) {
                    this.m = f;
                }
                return this;
            }

            public c g(Integer num) {
                if (num.intValue() > 0) {
                    this.o = num;
                }
                return this;
            }

            public c c(String str) {
                if (!TextUtils.isEmpty(str)) {
                    this.l = str;
                }
                return this;
            }

            public c e(Float f) {
                if (f.floatValue() > 0.0f) {
                    this.j = f;
                }
                return this;
            }

            public e d() {
                return new e(this);
            }
        }
    }

    public static class d {

        @SerializedName("205")
        private Integer b;

        @SerializedName("207")
        private Integer c;

        @SerializedName("204")
        private Integer d;

        @SerializedName("206")
        private Integer e;

        private d(b bVar) {
            this.d = bVar.c;
            this.b = bVar.e;
            this.e = bVar.b;
            this.c = bVar.f12204a;
        }

        public static final class b {

            /* renamed from: a, reason: collision with root package name */
            private Integer f12204a;
            private Integer b;
            private Integer c;
            private Integer e;

            public b e(Integer num) {
                if (num.intValue() > 0) {
                    this.c = num;
                }
                return this;
            }

            public b b(Integer num) {
                if (num.intValue() > 0) {
                    this.e = num;
                }
                return this;
            }

            public b a(Integer num) {
                if (num.intValue() > 0) {
                    this.b = num;
                }
                return this;
            }

            public b d(Integer num) {
                if (num.intValue() > 0) {
                    this.f12204a = num;
                }
                return this;
            }

            public d d() {
                return new d(this);
            }
        }
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return eta.bb();
    }

    public static final class c {

        /* renamed from: a, reason: collision with root package name */
        private d f12203a;
        private String b;
        private e d;

        public c d(String str) {
            this.b = str;
            return this;
        }

        public c d(e eVar) {
            this.d = eVar;
            return this;
        }

        public c c(d dVar) {
            this.f12203a = dVar;
            return this;
        }

        public erw a() {
            return new erw(this);
        }
    }
}
