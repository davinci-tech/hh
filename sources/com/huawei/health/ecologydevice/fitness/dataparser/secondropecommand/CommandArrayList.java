package com.huawei.health.ecologydevice.fitness.dataparser.secondropecommand;

import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cyf;
import defpackage.cyh;
import defpackage.cyw;
import defpackage.dis;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class CommandArrayList {

    /* renamed from: a, reason: collision with root package name */
    private byte f2288a;
    private boolean b;
    private cyf c;
    private byte d;
    private ReceiveCallback e;
    private int g;
    private int h;
    private int i;
    private int k;
    private byte l;
    private byte[] m;
    private List<CourseModel.Course> j = new ArrayList();
    private AbstractCommand[] f = new AbstractCommand[20];

    public interface ReceiveCallback {
        void onReceiveSuccess(byte b, CommandArrayList commandArrayList);
    }

    public void b(int i, AbstractCommand abstractCommand) {
        this.f[i] = abstractCommand;
        int i2 = this.g + 1;
        this.g = i2;
        LogUtil.a("PDROPE_CommandArrayList", "mFrameCount = ", Integer.valueOf(i2), ", mFrameSum = ", Integer.valueOf(this.i));
        if (this.g == this.i) {
            this.b = true;
            this.e.onReceiveSuccess(this.l, this);
        }
    }

    public void g() {
        int e = cyw.e(this.f[0].getParameterLength(), 18, 0);
        LogUtil.a("PDROPE_CommandArrayList", "processPara, length = ", Integer.valueOf(e));
        this.m = new byte[e];
        int i = 0;
        for (int i2 = 0; i2 < this.i; i2++) {
            cyh.d(this.m, i, this.f[i2].getPara(), 0, this.f[i2].getPara().length);
            i += this.f[i2].getPara().length;
        }
        byte[] bArr = this.m;
        this.k = bArr.length;
        LogUtil.a("PDROPE_CommandArrayList", "processPara, mPara = ", dis.d(bArr, ""));
        h();
    }

    public boolean b() {
        if (cyw.e(this.f[0].getParameterLength(), 18, 0) != this.k) {
            LogUtil.h("PDROPE_CommandArrayList", "The length of parameter content is not equal with value of parameter length.");
            return false;
        }
        byte check = this.f[this.i - 1].getCheck();
        LogUtil.a("PDROPE_CommandArrayList", "appCheck = ", Byte.valueOf(this.c.getCheck()), ", deviceCheck = ", Byte.valueOf(check));
        if (this.c.getCheck() == check) {
            return true;
        }
        LogUtil.h("PDROPE_CommandArrayList", "Data verify failed!");
        return false;
    }

    private void h() {
        cyf.c cVar = new cyf.c();
        cVar.d(this.f[0].getHead());
        cVar.e(this.f[0].getOrder());
        cVar.c(this.f[0].getCommand());
        cVar.b(this.f[0].getCode());
        cVar.a(this.h);
        cVar.e(this.m);
        this.c = cVar.d();
    }

    public void b(int i) {
        this.i = i;
    }

    public void d(ReceiveCallback receiveCallback) {
        this.e = receiveCallback;
    }

    public void d(byte b) {
        this.l = b;
    }

    public byte c() {
        return this.d;
    }

    public void b(byte b) {
        this.d = b;
    }

    public byte d() {
        return this.f2288a;
    }

    public byte[] f() {
        return this.m;
    }

    public void c(byte b) {
        this.f2288a = b;
    }

    public int e() {
        return this.h;
    }

    public void b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            this.h = 0;
        } else {
            this.h = cyw.e(bArr, 18, 0);
        }
    }

    public int a() {
        return this.k;
    }
}
