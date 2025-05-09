package defpackage;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.uikit.hwviewpager.widget.HwPagerAdapter;

/* loaded from: classes7.dex */
public class snh extends HwPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private boolean f17136a;
    private final HwPagerAdapter c;
    private boolean f;
    private int e = 4;
    private int b = 2;
    private SparseArray<b> d = new SparseArray<>();

    static class b {
        ViewGroup b;
        Object c;
        int d;

        b(ViewGroup viewGroup, int i, Object obj) {
            this.b = viewGroup;
            this.d = i;
            this.c = obj;
        }
    }

    public snh(HwPagerAdapter hwPagerAdapter, boolean z) {
        this.c = hwPagerAdapter;
        this.f17136a = z;
    }

    private int c() {
        return this.b;
    }

    private int e() {
        return (c() + d()) - 1;
    }

    public int a(int i) {
        int d = d();
        if (d == 0) {
            return 0;
        }
        int i2 = (i - this.b) % d;
        return i2 < 0 ? i2 + d : i2;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void a(DataSetObserver dataSetObserver) {
        this.c.a(dataSetObserver);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        int c = c();
        int e = e();
        int a2 = this.f17136a ? a(i) : i;
        if (this.f && (i == c || i == e)) {
            this.d.put(i, new b(viewGroup, a2, obj));
        } else {
            this.c.destroyItem(viewGroup, a2, obj);
        }
    }

    public int e(int i) {
        return i + this.b;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        this.c.finishUpdate(viewGroup);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        return this.f17136a ? this.c.getCount() + this.e : this.c.getCount();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return this.c.getItemPosition(obj);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public CharSequence getPageTitle(int i) {
        if (this.f17136a) {
            i = a(i);
        }
        return this.c.getPageTitle(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public float getPageWidth(int i) {
        if (this.f17136a) {
            i = a(i);
        }
        return this.c.getPageWidth(i);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        b bVar;
        int a2 = this.f17136a ? a(i) : i;
        if (!this.f || (bVar = this.d.get(i)) == null) {
            return this.c.instantiateItem(viewGroup, a2);
        }
        this.d.remove(i);
        return bVar.c;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return this.c.isViewFromObject(view, obj);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void notifyDataSetChanged() {
        this.d = new SparseArray<>();
        this.c.notifyDataSetChanged();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.c.registerDataSetObserver(dataSetObserver);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        this.c.restoreState(parcelable, classLoader);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Parcelable saveState() {
        return this.c.saveState();
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.f17136a) {
            i = a(i);
        }
        this.c.setPrimaryItem(viewGroup, i, obj);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        this.c.startUpdate(viewGroup);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.c.unregisterDataSetObserver(dataSetObserver);
    }

    public HwPagerAdapter b() {
        return this.c;
    }

    public void e(boolean z) {
        this.f = z;
    }

    public void d(int i) {
        int i2 = i - 1;
        this.b = i2;
        this.e = i + i2;
    }

    public int a() {
        return this.e;
    }

    public int d() {
        return this.c.getCount();
    }
}
