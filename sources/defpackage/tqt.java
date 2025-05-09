package defpackage;

import com.huawei.wearengine.sensor.AsyncReadCallback;
import com.huawei.wearengine.sensor.DataResult;
import com.huawei.wearengine.sensor.Sensor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class tqt {

    /* renamed from: a, reason: collision with root package name */
    private int f17352a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private int f;
    private int g;
    private Sensor h;
    private int i;
    private AsyncReadCallback j;
    private List<Float> k = new ArrayList(10);
    private String l;
    private long m;
    private long n;
    private tqq o;
    private int r;

    private int e(int i) {
        if (i == 2 || i == 3) {
            return 3;
        }
        return i != 4 ? 0 : 4;
    }

    public tqt(tqq tqqVar, tqq tqqVar2, AsyncReadCallback asyncReadCallback, String str) {
        this.j = asyncReadCallback;
        this.l = str;
        this.o = tqqVar2;
        a(tqqVar);
    }

    public void c(tqq tqqVar) {
        tos.a("AsyncReadListener", "enter updateDeviceReportFrequency");
        a(tqqVar);
    }

    private void a(tqq tqqVar) {
        if (this.o == null || tqqVar == null) {
            tos.e("AsyncReadListener", "initData mSubscribeFqu or deviceReportFqu is null");
            return;
        }
        this.d = tqqVar.d() == this.o.d();
        this.f17352a = this.o.a();
        this.g = tqqVar.c();
        int a2 = tqqVar.a();
        this.e = a2;
        if (a2 != 0) {
            this.b = this.g / a2;
        }
        if (tqqVar.c() != 0) {
            this.f = this.o.c() / tqqVar.c();
        }
        this.c = 0;
    }

    public void a() {
        tos.a("AsyncReadListener", "enter flush");
        if (this.k.isEmpty()) {
            return;
        }
        c(this.i);
    }

    public void c(int i, DataResult dataResult) {
        tos.a("AsyncReadListener", "enter handlerData");
        if (dataResult == null) {
            tos.e("AsyncReadListener", "handleData dataResult is null");
            return;
        }
        if (this.d) {
            tos.a("AsyncReadListener", "handlerData onReadResult " + dataResult.getSensor().getType());
            try {
                this.j.onReadResult(i, dataResult);
                return;
            } catch (Exception unused) {
                tos.e("AsyncReadListener", "handleData onReadResult Exception");
                return;
            }
        }
        int i2 = this.r + 1;
        this.r = i2;
        if ((i2 * this.g) % this.f17352a == 0) {
            float[] asFloats = dataResult.asFloats();
            if (dataResult.getSensor() == null) {
                tos.e("AsyncReadListener", "dataResult sensor is null");
                d();
                return;
            }
            this.c = e(dataResult.getSensor().getType());
            a(asFloats);
            tos.b("AsyncReadListener", "handlerData pick data size：" + this.k.size() + " mWaitedTime=" + this.r + " getRepPeriod=" + this.o.c() + " highFreq=" + this.g + " floats.length=" + asFloats.length + " packLength=" + this.c);
        }
        this.h = dataResult.getSensor();
        this.m = dataResult.getUtc();
        this.n = dataResult.getTimestamp();
        this.i = i;
        if (this.r == this.f) {
            c(i);
        }
    }

    private void d() {
        this.r = 0;
        this.k.clear();
    }

    private void a(float[] fArr) {
        if (this.c == fArr.length) {
            for (int i = 0; i < this.c; i++) {
                if (i >= fArr.length) {
                    tos.e("AsyncReadListener", "pickData one pack wrong data");
                    d();
                    return;
                }
                this.k.add(Float.valueOf(fArr[i]));
            }
            return;
        }
        for (int i2 = 1; i2 <= this.b; i2++) {
            if ((this.e * i2) % this.f17352a == 0) {
                int i3 = 0;
                while (true) {
                    int i4 = this.c;
                    if (i3 >= i4) {
                        break;
                    }
                    int i5 = ((i2 - 1) * i4) + i3;
                    if (i5 >= fArr.length) {
                        tos.e("AsyncReadListener", "pickData wrong data");
                        d();
                        break;
                    } else {
                        this.k.add(Float.valueOf(fArr[i5]));
                        i3++;
                    }
                }
            }
        }
    }

    private void c(int i) {
        if (this.h == null) {
            tos.e("AsyncReadListener", "sendData mSensor");
            return;
        }
        DataResult dataResult = new DataResult();
        float[] fArr = new float[this.k.size()];
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            fArr[i2] = this.k.get(i2).floatValue();
        }
        dataResult.setValues(fArr);
        dataResult.setSensor(this.h);
        dataResult.setUtc(this.m);
        dataResult.setTimestamp(this.n);
        tos.a("AsyncReadListener", "sendData => type: " + this.h.getType());
        try {
            this.j.onReadResult(i, dataResult);
        } catch (Exception unused) {
            tos.e("AsyncReadListener", "sendData onReadResult Exception");
        }
        d();
    }

    public tqq e() {
        return this.o;
    }
}
