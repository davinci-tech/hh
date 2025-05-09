package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.CommonUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nsz {
    public static void cLX_(String str, View view, int i) {
        try {
            if (CommonUtil.ag(BaseApplication.getContext())) {
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(str, "view=" + String.valueOf(view));
            } else {
                String str2 = "findId:" + i + " ,analyzedView viewTree:" + cLW_(view);
                LogUtil.a("ViewTreeUtils", str, str2);
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent(str, str2);
            }
        } catch (Throwable th) {
            LogUtil.a("ViewTreeUtils", str, th.getClass().getSimpleName());
        }
    }

    public static String cLW_(View view) {
        if (view == null) {
            return null;
        }
        return nrv.a(new e(cLZ_(view), cLY_(view)));
    }

    public static ArrayList<b> cLZ_(View view) {
        if (view == null) {
            return null;
        }
        ArrayList<b> arrayList = new ArrayList<>();
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                arrayList.add(new b(((View) parent).getId(), parent.getClass().getSimpleName()));
            }
        }
        return arrayList;
    }

    public static b cLY_(View view) {
        if (view == null) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            c cVar = new c(view.getId(), view.getClass().getSimpleName());
            if (viewGroup.getChildCount() > 0) {
                ArrayList arrayList = new ArrayList();
                cVar.e(arrayList);
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    arrayList.add(cLY_(viewGroup.getChildAt(i)));
                }
            }
            return cVar;
        }
        return new b(view.getId(), view.getClass().getSimpleName());
    }

    public static class e implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private List<b> f15476a;
        private b d;

        public e(List<b> list, b bVar) {
            this.f15476a = list;
            this.d = bVar;
        }
    }

    public static class b implements Serializable {
        private int d;
        private String e;

        public b(int i, String str) {
            this.d = i;
            this.e = str;
        }
    }

    public static class c extends b implements Serializable {
        private List<b> b;

        public c(int i, String str) {
            super(i, str);
        }

        public void e(List<b> list) {
            this.b = list;
        }
    }
}
