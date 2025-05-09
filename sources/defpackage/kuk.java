package defpackage;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.messagecenter.model.CommonUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class kuk {

    @SerializedName("dictTypes")
    private List<b> b;

    @SerializedName("version")
    private int c;

    @SerializedName("extraProcessTypes")
    private List<d> e;

    public List<b> c() {
        return this.b;
    }

    public List<d> e() {
        return this.e;
    }

    public class d {

        @SerializedName("processUrl")
        private String b;

        @SerializedName("typeId")
        private int e;

        public int b() {
            return this.e;
        }

        public String c() {
            return this.b;
        }

        public String toString() {
            return "ExtraProcessType{typeId=" + this.e + ", processUrl='" + this.b + "'}";
        }
    }

    public class b {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("name")
        private String f14603a;

        @SerializedName("syncType")
        private int c;

        @SerializedName("typeId")
        private int d;

        @SerializedName("fields")
        private List<d> e;

        public int c() {
            return this.c;
        }

        public int h() {
            return this.d;
        }

        public String b() {
            return this.f14603a;
        }

        public List<d> a() {
            return this.e;
        }

        public List<Integer> d() {
            HashSet hashSet = new HashSet();
            for (d dVar : a()) {
                if (dVar.a() != 0) {
                    hashSet.add(Integer.valueOf(dVar.a()));
                } else {
                    hashSet.add(Integer.valueOf(dVar.e()));
                }
            }
            return new ArrayList(hashSet);
        }

        public List<Integer> e() {
            HashSet hashSet = new HashSet();
            for (d dVar : a()) {
                if (!TextUtils.isEmpty(dVar.d())) {
                    Iterator<d.e> it = dVar.f().iterator();
                    while (it.hasNext()) {
                        hashSet.add(Integer.valueOf(it.next().a()));
                    }
                }
            }
            return new ArrayList(hashSet);
        }

        public int b(String str) {
            for (d dVar : a()) {
                if (!TextUtils.isEmpty(dVar.d()) && dVar.d().equals(str)) {
                    return TextUtils.isEmpty(dVar.c()) ? dVar.e() : dVar.a();
                }
            }
            return -1;
        }

        public class d {

            /* renamed from: a, reason: collision with root package name */
            @SerializedName("mappingName")
            String f14604a;

            @SerializedName("format")
            String b;

            @SerializedName("mappingId")
            int c;

            @SerializedName("isMetaData")
            boolean d;

            @SerializedName(CommonUtil.PARAM_HEALTH_TYPE)
            int e;

            @SerializedName("metaName")
            String f;

            @SerializedName("statMappings")
            List<e> g;

            @SerializedName("name")
            String i;

            public String b() {
                return this.f;
            }

            public boolean h() {
                return this.d;
            }

            public List<e> f() {
                return this.g;
            }

            public int a() {
                return this.c;
            }

            public String c() {
                return this.f14604a;
            }

            public int e() {
                return this.e;
            }

            public String d() {
                return this.i;
            }

            public String toString() {
                return "Field{healthType=" + this.e + ", name='" + this.i + "', format='" + this.b + "', isMetaData=" + this.d + ", mappingId=" + this.c + ", mappingName='" + this.f14604a + "', statMappings=" + this.g + ", metaName='" + this.f + "'}";
            }

            public class e {

                /* renamed from: a, reason: collision with root package name */
                @SerializedName("statPolicy")
                String f14605a;

                @SerializedName("statName")
                String c;

                @SerializedName("statType")
                int e;

                public String e() {
                    return this.f14605a;
                }

                public int a() {
                    return this.e;
                }

                public String c() {
                    return this.c;
                }

                public String toString() {
                    return "StatMapping{statType=" + this.e + ", statName='" + this.c + "', statPolicies='" + this.f14605a + "'}";
                }
            }
        }

        public String toString() {
            return "DictConfig{typeId=" + this.d + ", name='" + this.f14603a + "', fields=" + this.e + '}';
        }
    }

    public String toString() {
        return "DictConfig{mVersion=" + this.c + ", mExtraProcessTypes=" + this.e + ", mDictTypes=" + this.b + '}';
    }
}
