package defpackage;

import android.content.Context;
import androidx.core.view.PointerIconCompat;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.socialshare.api.WatermarkViewBase;
import com.huawei.health.socialshare.model.EditShareCommonView;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.view.sharewatermark.EmotionalHealthWatermark;
import com.huawei.pluginsocialshare.view.sharewatermark.FitnessShareWatermarkOne;
import com.huawei.pluginsocialshare.view.sharewatermark.FitnessShareWatermarkOneVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.FitnessShareWatermarkThree;
import com.huawei.pluginsocialshare.view.sharewatermark.FitnessShareWatermarkThreeVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.FitnessShareWatermarkTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.FitnessShareWatermarkTwoVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.PlanWaterMarkVersionOne;
import com.huawei.pluginsocialshare.view.sharewatermark.PlanWaterMarkVersionThree;
import com.huawei.pluginsocialshare.view.sharewatermark.PlanWaterMarkVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.SportBottomMainWatermark;
import com.huawei.pluginsocialshare.view.sharewatermark.SportBottomMainWatermarkVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.SportCommonWatermark;
import com.huawei.pluginsocialshare.view.sharewatermark.SportCommonWatermarkVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.SportShareWatermarkOutline;
import com.huawei.pluginsocialshare.view.sharewatermark.SportTitleWatermark;
import com.huawei.pluginsocialshare.view.sharewatermark.SportTitleWatermarkVersionTwo;
import com.huawei.pluginsocialshare.view.sharewatermark.ThreeCircleShareWatermark;
import com.huawei.pluginsocialshare.view.sharewatermark.TrackShareWatermark;
import com.huawei.pluginsocialshare.view.sharewatermark.TrackSimpleViewVersionTwo;
import health.compact.a.LogAnonymous;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mwe implements WatermarkViewBase {
    private String aa;
    private feh ab;
    private List<mvr> ac;
    private String ad;
    private int af;
    private Map<String, String> ai;
    private Context v;
    private List<hjd> w;
    private int y;
    private int z;

    /* renamed from: a, reason: collision with root package name */
    private static final List<Integer> f15215a = new ArrayList(Arrays.asList(1021));
    private static final List<Integer> h = new ArrayList(Arrays.asList(1022));
    private static final List<Integer> e = new ArrayList(Arrays.asList(Integer.valueOf(PointerIconCompat.TYPE_GRAB)));
    private static final List<Integer> d = new ArrayList(Arrays.asList(1121));
    private static final List<Integer> j = new ArrayList(Arrays.asList(1122));
    private static final List<Integer> c = new ArrayList(Arrays.asList(1120));
    private static final List<Integer> o = new ArrayList(Arrays.asList(1001, 1005, 1015, 1019, 1023, 1024, 1026, 1027));
    private static final List<Integer> n = new ArrayList(Arrays.asList(1101, Integer.valueOf(ExceptionCode.CHECK_FILE_SIZE_FAILED), 1115, 1119, 1123, 1124, 1126, 1127, 1102, 1106, 1116, 1134, 1135, 1136, 1137, 1142, 1143, 1144, 1145, 1146, 1148, 1151));
    private static final List<Integer> k = new ArrayList(Arrays.asList(1002, 1003, 1004, 1006, 1010, 1011, 1016, 1017, 1018));
    private static final List<Integer> l = new ArrayList(Arrays.asList(Integer.valueOf(ExceptionCode.CHECK_FILE_HASH_FAILED), 1111, 1118, 1131, 1132, 1133, 1140, 1141, 1166, 1167));
    private static final List<Integer> m = new ArrayList(Arrays.asList(1008, 1009, 1014));
    private static final List<Integer> r = new ArrayList(Arrays.asList(1012, 1013, 1025, 1028, 1029, Integer.valueOf(GLMapStaticValue.MAP_PARAMETERNAME_SCENIC), 1031, 1032));
    private static final List<Integer> s = new ArrayList(Arrays.asList(999, Integer.valueOf(ExceptionCode.DOWNLOAD_RENAME_ERROR), Integer.valueOf(ExceptionCode.FILE_NO_EXIST), 1125, 1128, 1129, 1130, 1103, Integer.valueOf(ExceptionCode.SERVER_EXCEPTION), 1117, 1156, 1158, 1138, 1139, 1147, 1150));
    private static final List<Integer> t = new ArrayList(Arrays.asList(1007));
    private static final List<Integer> q = new ArrayList(Arrays.asList(Integer.valueOf(ExceptionCode.CREATE_DOWNLOAD_FILE_FAILED), 1168));
    private static final List<Integer> p = new ArrayList(Arrays.asList(1165));
    private static final List<Integer> b = new ArrayList(Arrays.asList(1169, 1170, 1171, 1172));
    private static final List<Integer> g = new ArrayList(Arrays.asList(1153, 1157, 1163));
    private static final List<Integer> i = new ArrayList(Arrays.asList(1154, 1160, 1161));
    private static final List<Integer> f = new ArrayList(Arrays.asList(1149, 1152, 1155, 1159, 1162));
    private ArrayList<EditShareCommonView> u = new ArrayList<>();
    private boolean x = true;

    public mwe(feh fehVar, Context context) {
        this.ab = fehVar;
        this.v = context;
        b();
    }

    private void b() {
        e();
        this.ai = this.ab.b();
        this.w = this.ab.a();
        this.ad = this.ab.c();
    }

    private void e() {
        Context context = this.v;
        if (context == null) {
            return;
        }
        try {
            this.ac = (List) ixu.e(context.getAssets().open("shareWatermark/watermarkConfig.json"), new TypeToken<ArrayList<mvr>>() { // from class: mwe.4
            });
        } catch (IOException e2) {
            LogUtil.b("Share_WatermarkViewUtils", "initShareWatermarkConfig", LogAnonymous.b((Throwable) e2));
        }
    }

    public void c(String str) {
        LogUtil.a("Share_WatermarkViewUtils", "setRequestShareModule requestShareModule:" + str);
        this.aa = str;
    }

    @Override // com.huawei.health.socialshare.api.WatermarkViewBase
    public void constructDownloadWatermarkViewList(List<ShareDataInfo> list) {
        this.x = false;
        for (ShareDataInfo shareDataInfo : list) {
            if (shareDataInfo instanceof fef) {
                fef fefVar = (fef) shareDataInfo;
                int id = fefVar.getId();
                if (o.contains(Integer.valueOf(id))) {
                    e(id, fefVar, new SportBottomMainWatermark(this.v));
                } else if (k.contains(Integer.valueOf(id))) {
                    e(id, fefVar, new SportCommonWatermark(this.v));
                } else if (m.contains(Integer.valueOf(id))) {
                    e(id, fefVar, new SportShareWatermarkOutline(this.v));
                } else {
                    b(id, fefVar);
                }
            }
        }
    }

    private void b(int i2, fef fefVar) {
        if (r.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new SportTitleWatermark(this.v));
            return;
        }
        if (t.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new TrackShareWatermark(this.v));
            return;
        }
        if (f15215a.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new FitnessShareWatermarkOne(this.v));
            return;
        }
        if (h.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new FitnessShareWatermarkTwo(this.v));
            return;
        }
        if (e.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new FitnessShareWatermarkThree(this.v));
            return;
        }
        if (n.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new SportBottomMainWatermarkVersionTwo(this.v));
            return;
        }
        if (l.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new SportCommonWatermarkVersionTwo(this.v));
            return;
        }
        if (s.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new SportTitleWatermarkVersionTwo(this.v));
            return;
        }
        if (q.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new TrackSimpleViewVersionTwo(this.v));
            return;
        }
        if (d.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new FitnessShareWatermarkOneVersionTwo(this.v));
            return;
        }
        if (j.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new FitnessShareWatermarkTwoVersionTwo(this.v));
            return;
        }
        if (c.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new FitnessShareWatermarkThreeVersionTwo(this.v));
            return;
        }
        if (g.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new PlanWaterMarkVersionOne(this.v));
            return;
        }
        if (i.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new PlanWaterMarkVersionTwo(this.v));
            return;
        }
        if (f.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new PlanWaterMarkVersionThree(this.v));
            return;
        }
        if (p.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new ThreeCircleShareWatermark(this.v));
        } else if (b.contains(Integer.valueOf(i2))) {
            e(i2, fefVar, new EmotionalHealthWatermark(this.v));
        } else {
            LogUtil.h("Share_WatermarkViewUtils", "constructDownloadWatermarkViewList is other id");
        }
    }

    @Override // com.huawei.health.socialshare.api.WatermarkViewBase
    public void constructLocalDefaultWatermarkViewList(List<feb> list) {
        this.x = true;
        for (feb febVar : list) {
            int a2 = febVar.a();
            int d2 = febVar.d();
            if (o.contains(Integer.valueOf(a2))) {
                a(a2, d2, new SportBottomMainWatermark(this.v));
            } else if (k.contains(Integer.valueOf(a2))) {
                a(a2, d2, new SportCommonWatermark(this.v));
            } else if (m.contains(Integer.valueOf(a2))) {
                a(a2, d2, new SportShareWatermarkOutline(this.v));
            } else {
                b(a2, d2);
            }
        }
    }

    private void b(int i2, int i3) {
        if (r.contains(Integer.valueOf(i2))) {
            a(i2, i3, new SportTitleWatermark(this.v));
            return;
        }
        if (t.contains(Integer.valueOf(i2))) {
            a(i2, i3, new TrackShareWatermark(this.v));
            return;
        }
        if (f15215a.contains(Integer.valueOf(i2))) {
            a(i2, i3, new FitnessShareWatermarkOne(this.v));
            return;
        }
        if (h.contains(Integer.valueOf(i2))) {
            a(i2, i3, new FitnessShareWatermarkTwo(this.v));
            return;
        }
        if (e.contains(Integer.valueOf(i2))) {
            a(i2, i3, new FitnessShareWatermarkThree(this.v));
            return;
        }
        if (n.contains(Integer.valueOf(i2))) {
            a(i2, i3, new SportBottomMainWatermarkVersionTwo(this.v));
            return;
        }
        if (l.contains(Integer.valueOf(i2))) {
            a(i2, i3, new SportCommonWatermarkVersionTwo(this.v));
            return;
        }
        if (s.contains(Integer.valueOf(i2))) {
            a(i2, i3, new SportTitleWatermarkVersionTwo(this.v));
            return;
        }
        if (q.contains(Integer.valueOf(i2))) {
            a(i2, i3, new TrackSimpleViewVersionTwo(this.v));
            return;
        }
        if (d.contains(Integer.valueOf(i2))) {
            a(i2, i3, new FitnessShareWatermarkOneVersionTwo(this.v));
            return;
        }
        if (j.contains(Integer.valueOf(i2))) {
            a(i2, i3, new FitnessShareWatermarkTwoVersionTwo(this.v));
            return;
        }
        if (c.contains(Integer.valueOf(i2))) {
            a(i2, i3, new FitnessShareWatermarkThreeVersionTwo(this.v));
            return;
        }
        if (g.contains(Integer.valueOf(i2))) {
            a(i2, i3, new PlanWaterMarkVersionOne(this.v));
            return;
        }
        if (i.contains(Integer.valueOf(i2))) {
            a(i2, i3, new PlanWaterMarkVersionTwo(this.v));
            return;
        }
        if (f.contains(Integer.valueOf(i2))) {
            a(i2, i3, new PlanWaterMarkVersionThree(this.v));
            return;
        }
        if (p.contains(Integer.valueOf(i2))) {
            a(i2, i3, new ThreeCircleShareWatermark(this.v));
        } else if (b.contains(Integer.valueOf(i2))) {
            a(i2, i3, new EmotionalHealthWatermark(this.v));
        } else {
            LogUtil.h("Share_WatermarkViewUtils", "constructLocalDefaultWatermarkViewList is other watermarkId");
        }
    }

    private void e(int i2, fef fefVar, EditShareCommonView editShareCommonView) {
        String path = fefVar.getPath();
        String b2 = fefVar.b();
        editShareCommonView.setWatermarkId(i2);
        editShareCommonView.refreshData(b(i2, b2));
        editShareCommonView.setSourcePath(path);
        editShareCommonView.setIsDefaultSource(this.x);
        editShareCommonView.refreshUi(this.af, this.y);
        editShareCommonView.refreshTopUi(this.z);
        b(editShareCommonView);
        this.u.add(editShareCommonView);
    }

    private void b(EditShareCommonView editShareCommonView) {
        LogUtil.a("Share_WatermarkViewUtils", "setUserInfo mRequestShareModule: ", this.aa);
        if (!mwd.i()) {
            editShareCommonView.setUserInfo(null, null);
        } else {
            editShareCommonView.setUserInfo(mwd.cqw_(this.v), mwd.b());
        }
    }

    private void a(int i2, int i3, EditShareCommonView editShareCommonView) {
        editShareCommonView.refreshData(b(i2, (String) null));
        editShareCommonView.setWatermarkId(i2);
        editShareCommonView.setBitmap(i3);
        editShareCommonView.setIsDefaultSource(this.x);
        editShareCommonView.refreshUi(this.af, this.y);
        editShareCommonView.refreshTopUi(this.z);
        b(editShareCommonView);
        this.u.add(editShareCommonView);
    }

    private fed b(int i2, String str) {
        fed fedVar = new fed();
        mvr c2 = c(i2);
        fedVar.b(c2.n());
        fedVar.s(e(c2.l()));
        fedVar.t(e(c2.q()));
        fedVar.q(e(c2.t()));
        fedVar.p(e(c2.s()));
        fedVar.r(e(c2.p()));
        LogUtil.a("Share_WatermarkViewUtils", "location= ", e(c2.p()), " id: ", Integer.valueOf(i2));
        fedVar.x(e(c2.r()));
        fedVar.w(e(c2.w()));
        fedVar.d(e(c2.e()));
        fedVar.c(e(c2.a()));
        fedVar.a(e(c2.d()));
        fedVar.j(e(c2.g()));
        fedVar.f(e(c2.h()));
        fedVar.g(e(c2.f()));
        fedVar.i(e(c2.i()));
        fedVar.l(e(c2.m()));
        fedVar.n(e(c2.k()));
        fedVar.e(e(c2.b()));
        fedVar.b(e(c2.c()));
        fedVar.h(e(c2.j()));
        fedVar.o(str);
        fedVar.c(this.w);
        fedVar.k(this.ad);
        fedVar.m(e(c2.o()));
        fedVar.c(c2.v());
        return fedVar;
    }

    private String e(String str) {
        return this.ai.containsKey(str) ? this.ai.get(str) : "";
    }

    private mvr c(int i2) {
        List<mvr> list = this.ac;
        if (list != null) {
            for (mvr mvrVar : list) {
                if (mvrVar.n() == i2) {
                    return mvrVar;
                }
            }
        }
        return new mvr();
    }

    @Override // com.huawei.health.socialshare.api.WatermarkViewBase
    public void setWidgetColor(int i2) {
        this.af = i2;
    }

    @Override // com.huawei.health.socialshare.api.WatermarkViewBase
    public void setDoMainColor(int i2) {
        this.y = i2;
    }

    @Override // com.huawei.health.socialshare.api.WatermarkViewBase
    public void setTopWidgetColor(int i2) {
        this.z = i2;
    }

    @Override // com.huawei.health.socialshare.api.WatermarkViewBase
    public List<EditShareCommonView> getEditShareCommonViewList() {
        return this.u;
    }
}
