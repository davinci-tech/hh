package defpackage;

import com.huawei.hwcommonmodel.datatypes.MsgImage;
import com.huawei.hwcommonmodel.datatypes.MsgText;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class jde {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13752a;
    private int b;
    private ArrayList<MsgImage> c;
    private ArrayList<MsgText> d;

    public jde(int i, ArrayList<MsgImage> arrayList, ArrayList<MsgText> arrayList2, boolean z) {
        this.b = i;
        this.c = arrayList;
        this.d = arrayList2;
        this.f13752a = z;
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void a(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public ArrayList<MsgText> d() {
        return (ArrayList) jdy.d(this.d);
    }

    public ArrayList<MsgImage> b() {
        return (ArrayList) jdy.d(this.c);
    }

    public void d(boolean z) {
        this.f13752a = ((Boolean) jdy.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean a() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.f13752a))).booleanValue();
    }
}
