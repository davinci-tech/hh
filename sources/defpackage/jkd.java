package defpackage;

import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicStruct;
import java.util.List;

/* loaded from: classes5.dex */
public class jkd {

    /* renamed from: a, reason: collision with root package name */
    private int f13905a;
    private int b;
    private List<MusicStruct> e;

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void b(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return this.f13905a;
    }

    public void d(int i) {
        this.f13905a = i;
    }

    public List<MusicStruct> d() {
        return (List) jdy.d(this.e);
    }

    public void c(List<MusicStruct> list) {
        this.e = (List) jdy.d(list);
    }
}
