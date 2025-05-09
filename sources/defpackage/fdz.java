package defpackage;

import android.graphics.Bitmap;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class fdz {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mPresetBackgroundList")
    private List<Integer> f12465a;

    @SerializedName("mIsBackground")
    private boolean b;

    @SerializedName("mImagePath")
    private String c;

    @SerializedName("mTabTitle")
    private String f;

    @SerializedName("mShareType")
    private int g;

    @SerializedName("mWatermarkData")
    private feh i;

    @SerializedName("mRequestShareBiMap")
    private Map<String, Object> j;

    @SerializedName("watermarkInfos")
    private List<feb> k;

    @SerializedName("mSportType")
    private int h = 0;

    @SerializedName("mIsEditable")
    private boolean e = true;

    @SerializedName("mPreviewBitmap")
    private Bitmap d = null;

    public void a(String str) {
        this.f = str;
    }

    public String j() {
        return this.f;
    }

    public void b(List<feb> list) {
        this.k = list;
    }

    public List<feb> f() {
        return this.k;
    }

    public List<Integer> m() {
        return this.f12465a;
    }

    public void d(List<Integer> list) {
        this.f12465a = list;
    }

    public int d() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public boolean h() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public Bitmap aws_() {
        return this.d;
    }

    public void awt_(Bitmap bitmap) {
        this.d = bitmap;
    }

    public String e() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public Map<String, Object> b() {
        return this.j;
    }

    public void b(Map<String, Object> map) {
        this.j = map;
    }

    public int c() {
        return this.h;
    }

    public void b(int i) {
        this.h = i;
    }

    public void b(feh fehVar) {
        this.i = fehVar;
    }

    public feh g() {
        return this.i;
    }

    public boolean i() {
        return this.b;
    }

    public void n() {
        Bitmap aws_ = aws_();
        if (aws_ != null && !aws_.isRecycled()) {
            aws_.recycle();
        }
        awt_(null);
    }
}
