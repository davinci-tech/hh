package defpackage;

import android.graphics.Rect;
import com.huawei.hwcommonmodel.application.BaseApplication;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jlz {
    public static final String e = BaseApplication.getContext().getFilesDir() + "/watchfacePhoto/background/";

    /* renamed from: a, reason: collision with root package name */
    private boolean f13951a;
    private int c;
    private int i;
    private List<String> j;
    private ArrayList<Rect> f = new ArrayList<>(16);
    private ArrayList<String> h = new ArrayList<>(16);
    private String d = e;
    private Rect b = new Rect();

    public List<String> h() {
        return this.j;
    }

    public void a(List<String> list) {
        this.j = list;
    }

    public ArrayList<String> c() {
        return this.h;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public Rect bIj_() {
        return this.b;
    }

    public void bIk_(Rect rect) {
        this.b = rect;
    }

    public int e() {
        return this.i;
    }

    public int a() {
        return this.c;
    }

    public boolean j() {
        return this.f13951a;
    }

    public void e(boolean z) {
        this.f13951a = z;
    }

    public void e(int i, int i2) {
        this.i = i;
        this.c = i2;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("clipRect: clipRect.left='").append(this.b.left);
        stringBuffer.append(", clipRect.top=").append(this.b.top);
        stringBuffer.append(", clipRect.right =").append(this.b.right);
        stringBuffer.append(", clipRect.bottom=").append(this.b.bottom);
        stringBuffer.append(", outputWidth=").append(this.i);
        stringBuffer.append(", outputHeight=").append(this.c);
        return stringBuffer.toString();
    }
}
