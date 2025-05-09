package defpackage;

import com.huawei.hwcloudmodel.hwwear.hag.model.tide.HagTideDataBean;
import com.huawei.hwcloudmodel.model.push.AlertWeather;
import com.huawei.hwcloudmodel.model.push.WeatherForecastDay;
import com.huawei.hwcloudmodel.model.push.WeatherForecastHour;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jbz {

    /* renamed from: a, reason: collision with root package name */
    private int f13731a;
    private List<AlertWeather> b;
    private int c;
    private int d;
    private int e;
    private int f;
    private String g;
    private Map<String, Object> h;
    private String i;
    private HagTideDataBean j;
    private String k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private List<WeatherForecastDay> s;
    private long t;
    private int u;
    private int w;
    private int x;
    private List<WeatherForecastHour> y;

    public int a() {
        return this.f13731a;
    }

    public void o(int i) {
        this.f13731a = i;
    }

    public HagTideDataBean b() {
        return this.j;
    }

    public void d(HagTideDataBean hagTideDataBean) {
        this.j = hagTideDataBean;
    }

    public Map<String, Object> c() {
        return this.h;
    }

    public void c(Map<String, Object> map) {
        this.h = map;
    }

    public int d() {
        return this.c;
    }

    public void j(int i) {
        this.c = i;
    }

    public int f() {
        return this.r;
    }

    public void k(int i) {
        this.r = i;
    }

    public String i() {
        return this.k;
    }

    public void e(String str) {
        this.k = str;
    }

    public List<WeatherForecastDay> j() {
        return this.s;
    }

    public void a(List<WeatherForecastDay> list) {
        this.s = list;
    }

    public List<WeatherForecastHour> l() {
        return this.y;
    }

    public void b(List<WeatherForecastHour> list) {
        this.y = list;
    }

    public int p() {
        return this.p;
    }

    public int m() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int t() {
        return this.n;
    }

    public void d(int i) {
        this.n = i;
    }

    public long u() {
        return this.t;
    }

    public void c(long j) {
        this.t = j;
    }

    public String q() {
        return this.g;
    }

    public int k() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public int r() {
        return this.o;
    }

    public void f(int i) {
        this.o = i;
    }

    public int s() {
        return this.m;
    }

    public void c(int i) {
        this.m = i;
    }

    public int n() {
        return this.f;
    }

    public void e(int i) {
        this.f = i;
    }

    public int v() {
        return this.q;
    }

    public void i(int i) {
        this.q = i;
    }

    public int x() {
        return this.x;
    }

    public void g(int i) {
        this.x = i;
    }

    public void h(int i) {
        this.w = i;
    }

    public int w() {
        return this.w;
    }

    public void a(String str) {
        this.g = str;
    }

    public String g() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public int o() {
        return this.u;
    }

    public void n(int i) {
        this.u = i;
    }

    public int h() {
        return this.l;
    }

    public void l(int i) {
        this.l = i;
    }

    public List<AlertWeather> e() {
        return this.b;
    }

    public void d(List<AlertWeather> list) {
        this.b = list;
    }

    public String toString() {
        return "DataWeather{weather=" + this.q + ", pm25=" + this.o + ", lowestTemperature=" + this.m + ", highestTemperature=" + this.f + ", currentTemperature=" + this.d + ", locationName='" + this.g + "', temperatureUnit=" + this.p + ", aqi=" + this.e + ", observationTime=" + this.n + ", updateTime=" + this.t + ", windDirection=" + this.x + ", windSpeed=" + this.w + ", source='" + this.k + "', weatherForecastDays=" + this.s + ", weatherForecastHours=" + this.y + ", cnWeatherId=" + this.c + ", uVIndex=" + this.r + ", hagTideDataBean=" + this.j + ", hagReportBiMap=" + this.h + ", errorType=" + this.f13731a + ", humidity='" + this.i + "', windSpeedValue=" + this.u + ", somatosensoryTemperature=" + this.l + ", alertWeathers=" + this.b + '}';
    }
}
