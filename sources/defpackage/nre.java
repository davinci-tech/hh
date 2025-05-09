package defpackage;

import com.huawei.ui.commonui.tablewidget.model.ItemData;
import com.huawei.ui.commonui.tablewidget.model.TableDataSet;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes6.dex */
public class nre<T0 extends ItemData, T1 extends ItemData, T2 extends ItemData, T3 extends ItemData, T4 extends ItemData, T5 extends ItemData> implements TableDataSet<T0, T1, T2, T3, T4, T5> {
    private nra<T0> d = new nra<>();
    private nra<T1> f = new nra<>();

    /* renamed from: a, reason: collision with root package name */
    private nra<T2> f15454a = new nra<>();
    private nra<T3> c = new nra<>();
    private nra<T4> g = new nra<>();
    private nra<T5> i = new nra<>();
    private Set<Integer> b = new HashSet();
    private Set<Integer> j = new HashSet();
    private Set<Integer> n = new HashSet();
    private int h = 0;
    private int e = 0;

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public T0 getColumnHeaderData(int i, int i2) {
        return this.d.a(i, i2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void putColumnHeaderData(int i, int i2, T0 t0) {
        d(i, i2);
        this.b.add(Integer.valueOf(i));
        this.d.a(i, i2, t0);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public T1 getRowHeaderData(int i, int i2) {
        return this.f.a(i, i2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void putRowHeaderData(int i, int i2, T1 t1) {
        d(i, i2);
        this.j.add(Integer.valueOf(i2));
        this.f.a(i, i2, t1);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public T2 getRowColumnHeaderData(int i, int i2) {
        return this.f15454a.a(i, i2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void putRowColumnHeaderData(int i, int i2, T2 t2) {
        d(i, i2);
        this.f15454a.a(i, i2, t2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public T3 getContentData(int i, int i2) {
        return this.c.a(i, i2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void putContentData(int i, int i2, T3 t3) {
        d(i, i2);
        this.c.a(i, i2, t3);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public T4 getStatisticHeaderData(int i, int i2) {
        return this.g.a(i, i2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void putStatisticHeaderData(int i, int i2, T4 t4) {
        d(i, i2);
        this.n.add(Integer.valueOf(i));
        this.g.a(i, i2, t4);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public T5 getStatisticData(int i, int i2) {
        return this.i.a(i, i2);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void putStatisticData(int i, int i2, T5 t5) {
        d(i, i2);
        this.n.add(Integer.valueOf(i));
        this.i.a(i, i2, t5);
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public void clearAll() {
        this.f.e();
        this.d.e();
        this.f15454a.e();
        this.c.e();
        this.g.e();
        this.i.e();
        this.j.clear();
        this.b.clear();
        this.n.clear();
        this.h = 0;
        this.e = 0;
    }

    private void d(int i, int i2) {
        if (i > this.h - 1) {
            this.h = i + 1;
        }
        if (i2 > this.e - 1) {
            this.e = i2 + 1;
        }
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public int getRowsCount() {
        return this.h;
    }

    @Override // com.huawei.ui.commonui.tablewidget.model.TableDataSet
    public int getColumnsCount() {
        return this.e;
    }

    public int c() {
        return this.b.size();
    }

    public int d() {
        return this.j.size();
    }

    public int e() {
        return this.n.size();
    }
}
