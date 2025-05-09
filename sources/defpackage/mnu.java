package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class mnu implements Serializable, Cloneable {
    private static final long serialVersionUID = 5886445939023550795L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("dayNumber")
    private int f15071a;

    @SerializedName("clockFlag")
    private int b;

    @SerializedName("courseList")
    private List<CourseDataBean> c;

    @SerializedName("recordList")
    private List<mob> d;

    public void d(List<CourseDataBean> list) {
        this.c = list;
    }

    public List<CourseDataBean> a() {
        return this.c;
    }

    public void b(int i) {
        this.f15071a = i;
    }

    public int c() {
        return this.f15071a;
    }

    public boolean e() {
        return this.b == 1;
    }

    private void a(List<mob> list) {
        this.d = list;
    }

    public List<mob> b() {
        return this.d;
    }

    public void b(boolean z) {
        if (z) {
            this.b = 1;
        } else {
            this.b = 0;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        try {
            if (!(super.clone() instanceof mnu)) {
                return new mnu();
            }
            mnu mnuVar = (mnu) super.clone();
            if (this.c != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<CourseDataBean> it = this.c.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
                mnuVar.d(arrayList);
            }
            if (this.d != null) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<mob> it2 = this.d.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(it2.next());
                }
                mnuVar.a(arrayList2);
            }
            return mnuVar;
        } catch (CloneNotSupportedException e) {
            LogUtil.e("PlanDayDataBean clone failed", LogAnonymous.b((Throwable) e));
            throw new AssertionError();
        }
    }
}
