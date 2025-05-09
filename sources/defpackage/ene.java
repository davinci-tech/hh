package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import health.compact.a.GRSManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ene implements IRequest {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName(JsbMapKeyNames.H5_LOC_LON)
    private double f12105a;

    @SerializedName("pageIndex")
    private int b;

    @SerializedName("cityId")
    private String c;

    @SerializedName(JsbMapKeyNames.H5_LOC_LAT)
    private double d;

    @SerializedName("distanceType")
    private int e;

    @SerializedName("pathTheme")
    private int f;

    @SerializedName(IAchieveDBMgr.PARAM_PAGE_SIZE)
    private int g;

    @SerializedName("pathFlag")
    private int h;

    @SerializedName("pathClass")
    private List<Integer> i;

    @SerializedName("pathLabel")
    private int j;

    @SerializedName("pathType")
    private int m;

    @SerializedName("sortType")
    private int n;

    public ene() {
    }

    private ene(a aVar) {
        this.f12105a = aVar.e;
        this.d = aVar.c;
        this.c = aVar.f12106a;
        this.i = aVar.h;
        this.f = aVar.f;
        this.h = aVar.i;
        this.m = aVar.o;
        this.j = aVar.g;
        this.n = aVar.k;
        this.e = aVar.d;
        this.b = aVar.b;
        this.g = aVar.j;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f12106a;
        private int b;
        private double c;
        private int d;
        private double e;
        private int f;
        private int g;
        private List<Integer> h;
        private int i;
        private int j;
        private int k;
        private int o;

        public a e(double d) {
            this.e = d;
            return this;
        }

        public a d(double d) {
            this.c = d;
            return this;
        }

        public a c(String str) {
            this.f12106a = str;
            return this;
        }

        public a d(List<Integer> list) {
            this.h = list;
            return this;
        }

        public a e(int i) {
            this.f = i;
            return this;
        }

        public a a(int i) {
            this.i = i;
            return this;
        }

        public a h(int i) {
            this.o = i;
            return this;
        }

        public a i(int i) {
            this.k = i;
            return this;
        }

        public a d(int i) {
            this.d = i;
            return this;
        }

        public a b(int i) {
            this.b = i;
            return this;
        }

        public a c(int i) {
            this.j = i;
            return this;
        }

        public ene a() {
            return new ene(this);
        }
    }

    public void b(double d) {
        this.f12105a = d;
    }

    public void c(double d) {
        this.d = d;
    }

    public String b() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public List<Integer> d() {
        return this.i;
    }

    public void e(List<Integer> list) {
        this.i = list;
    }

    public void g(int i) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i));
        this.i = arrayList;
    }

    public int h() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int e() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public int g() {
        return this.m;
    }

    public void f(int i) {
        this.m = i;
    }

    public int c() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public int i() {
        return this.n;
    }

    public void j(int i) {
        this.n = i;
    }

    public int a() {
        return this.b;
    }

    public void d(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.g = i;
    }

    public boolean f() {
        return this.f == 0 && this.m == 0 && this.e == 0 && this.b == 1;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/dataAnalyse/app/getHotPaths";
    }

    public String toString() {
        return "GetHotPathsReq{longitude=" + this.f12105a + ", latitude=" + this.d + ", cityId='" + this.c + "', pathClass=" + this.i + ", pathTheme=" + this.f + ", pathFlag=" + this.h + ", pathType=" + this.m + ", pathLabel=" + this.j + ", sortType=" + this.n + ", distanceType=" + this.e + ", pageIndex=" + this.b + ", pageSize=" + this.g + '}';
    }
}
