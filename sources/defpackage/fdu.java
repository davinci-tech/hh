package defpackage;

import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.gson.annotations.SerializedName;
import com.huawei.health.socialshare.model.SaveBitampCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public class fdu implements Cloneable {

    @SerializedName(Constants.ACTIVITY_SHARE_TYPE)
    private int aa;

    @SerializedName("shareUrlContent")
    private String ab;

    @SerializedName("thumbImage")
    private Bitmap ac;

    @SerializedName("shareTextContent")
    private String ad;

    @SerializedName("imageNum")
    private int b;

    @SerializedName("imagePath")
    private String c;

    @SerializedName("editContents")
    private ArrayList<fdz> d;

    @SerializedName("imageUriList")
    private ArrayList<Uri> e;

    @SerializedName("isNeedPrint")
    private boolean f;

    @SerializedName("inSampleSize")
    private int g;

    @SerializedName("isNeedSavePdf")
    private boolean i;

    @SerializedName("isSharePicCompress")
    private boolean m;

    @SerializedName("mFilePath")
    private String n;

    @SerializedName("mFileUri")
    private Uri o;

    @SerializedName("requestShareBiMap")
    private Map<String, Object> p;

    @SerializedName("mVideoPath")
    private String q;

    @SerializedName("mVideoUri")
    private Uri r;

    @SerializedName("saveBitampCallback")
    private SaveBitampCallback u;

    @SerializedName("saveFileImages")
    private ArrayList<String> v;

    @SerializedName("sharePicUrl")
    private String w;

    @SerializedName("shareFrom")
    private int x;

    @SerializedName("sharePicContent")
    private Bitmap y;

    @SerializedName("shareTitleContent")
    private String z;

    @SerializedName("isShareFamily")
    private boolean j = true;

    @SerializedName("requestShareModule")
    private String s = AnalyticsValue.SHARE_1140001.value();

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("defaultPreviewIndex")
    private int f12463a = 0;

    @SerializedName("isShowTab")
    private boolean l = false;

    @SerializedName("mShareSportData")
    private HashMap<String, String> t = new HashMap<>();

    @SerializedName("isNeedWatermark")
    private boolean h = true;

    @SerializedName("isShowUserinfoButton")
    private boolean k = true;

    public fdu(int i) {
        this.aa = i;
    }

    public int aa() {
        return this.x;
    }

    public boolean h() {
        return this.h;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public boolean ad() {
        return this.i;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public boolean z() {
        return this.f;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public final void b(int i) {
        this.x = i;
    }

    public String i() {
        return this.c;
    }

    public final void d(String str) {
        this.c = str;
    }

    public ArrayList<String> n() {
        return this.v;
    }

    public void e(ArrayList<String> arrayList) {
        this.v = arrayList;
    }

    public int c() {
        return this.b;
    }

    public final void c(int i) {
        this.b = i;
    }

    public ArrayList<Uri> f() {
        ArrayList<Uri> arrayList = this.e;
        return arrayList == null ? new ArrayList<>(0) : arrayList;
    }

    public final void c(ArrayList<Uri> arrayList) {
        this.e = arrayList;
    }

    public int g() {
        return this.g;
    }

    public final void e(int i) {
        this.g = i;
    }

    public boolean ab() {
        return this.m;
    }

    public final void i(boolean z) {
        this.m = z;
    }

    public int u() {
        return this.aa;
    }

    public void a(int i) {
        this.aa = i;
    }

    public boolean j() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public String q() {
        return this.ad;
    }

    public final void a(String str) {
        this.ad = str;
    }

    public String t() {
        return this.z;
    }

    public final void c(String str) {
        this.z = str;
    }

    public String y() {
        return this.ab;
    }

    public final void f(String str) {
        this.ab = str;
    }

    public Bitmap awm_() {
        return this.y;
    }

    public final void awp_(Bitmap bitmap) {
        this.y = bitmap;
    }

    public String p() {
        return this.w;
    }

    public String m() {
        return this.s;
    }

    public final void b(String str) {
        this.s = str;
    }

    public SaveBitampCallback k() {
        return this.u;
    }

    public void c(SaveBitampCallback saveBitampCallback) {
        this.u = saveBitampCallback;
    }

    public Map<String, Object> l() {
        return this.p;
    }

    public final void b(Map<String, Object> map) {
        this.p = map;
    }

    public ArrayList<fdz> b() {
        ArrayList<fdz> arrayList = this.d;
        return arrayList == null ? new ArrayList<>(0) : arrayList;
    }

    public final void b(ArrayList<fdz> arrayList) {
        this.d = arrayList;
    }

    public void b(fdz fdzVar) {
        if (this.d == null) {
            this.d = new ArrayList<>(3);
        }
        this.d.add(fdzVar);
    }

    public int d() {
        return this.f12463a;
    }

    public final void d(int i) {
        this.f12463a = i;
    }

    public final void d(boolean z) {
        this.l = z;
    }

    public boolean o() {
        return this.l;
    }

    public final void j(String str) {
        this.q = str;
    }

    public String x() {
        return this.q;
    }

    public final void awr_(Uri uri) {
        this.r = uri;
    }

    public Uri awo_() {
        return this.r;
    }

    public String e() {
        return this.n;
    }

    public void e(String str) {
        this.n = str;
    }

    public Uri awl_() {
        return this.o;
    }

    public HashMap<String, String> s() {
        return this.t;
    }

    public final void b(HashMap<String, String> hashMap) {
        this.t = hashMap;
    }

    public Bitmap awn_() {
        return this.ac;
    }

    public void awq_(Bitmap bitmap) {
        this.ac = bitmap;
    }

    public void h(boolean z) {
        this.k = z;
    }

    public boolean ac() {
        return this.k;
    }

    public String toString() {
        return "shareType = " + this.aa;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            LogUtil.b("ShareContent", "clone CloneNotSupportedException");
            fdu fduVar = new fdu(this.aa);
            fduVar.d(this.f12463a);
            fduVar.awp_(this.y);
            fduVar.b(this.s);
            fduVar.b(this.d);
            fduVar.c(this.b);
            fduVar.d(this.c);
            fduVar.e(this.v);
            fduVar.c(this.e);
            fduVar.e(this.g);
            fduVar.d(this.l);
            fduVar.i(this.m);
            fduVar.a(this.ad);
            fduVar.b(this.t);
            fduVar.c(this.z);
            fduVar.f(this.ab);
            fduVar.j(this.q);
            fduVar.awr_(this.r);
            fduVar.b(this.x);
            fduVar.b(this.p);
            fduVar.h(this.k);
            return fduVar;
        }
    }

    public void ah() {
        Bitmap awm_ = awm_();
        if (awm_ != null && !awm_.isRecycled()) {
            awm_.recycle();
        }
        awp_(null);
        ArrayList<fdz> b = b();
        if (CollectionUtils.isEmpty(b)) {
            return;
        }
        Iterator<fdz> it = b.iterator();
        while (it.hasNext()) {
            fdz next = it.next();
            if (next != null) {
                next.n();
            }
        }
        b((ArrayList<fdz>) null);
    }
}
