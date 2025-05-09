package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.healthcloud.plugintrack.ui.runningpostureadvicemgr.RunningPostureAdviceBase;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineDataSet;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.icommon.IChartStorageHelper;
import com.huawei.ui.commonui.linechart.view.HwHealthLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.IHwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode;
import com.huawei.ui.main.stories.health.fragment.rqpackage.RqLineStateChartStorageHelper;
import com.huawei.up.model.UserInfomation;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class qjd extends HwHealthLineScrollChartHolder {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16440a;
    private Context b;
    private HwHealthLineChart c;
    private boolean d;
    private boolean e;
    private IHealthMultiColorMode f;
    private IHealthMultiColorMode g;
    private IHealthMultiColorMode h;
    private int i;
    private IChartStorageHelper j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private int q;
    private IHealthMultiColorMode r;
    private float s;

    static /* synthetic */ boolean a(int i, int i2, int i3) {
        return true;
    }

    static /* synthetic */ boolean b(int i, int i2, int i3) {
        return true;
    }

    static /* synthetic */ boolean c(int i, int i2, int i3) {
        return true;
    }

    public qjd(Context context, int i, int i2) {
        super(context);
        this.s = 91.0f;
        this.f = new IHealthMultiColorMode() { // from class: qjd.5
            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public int[] getDataColor() {
                return new int[]{-16733458, -11748673, RunningPostureAdviceBase.COLOR_NORMAL, -15078, RunningPostureAdviceBase.COLOR_SUBOPTIMAL, -35584, RunningPostureAdviceBase.COLOR_POORER};
            }

            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public float[] getThreshold() {
                return new float[]{25.0f, 30.0f, 40.0f, 50.0f, 60.0f, 65.0f};
            }
        };
        this.g = new IHealthMultiColorMode() { // from class: qjd.2
            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public int[] getDataColor() {
                return new int[]{-16733458, -11748673, RunningPostureAdviceBase.COLOR_NORMAL, -15078, RunningPostureAdviceBase.COLOR_SUBOPTIMAL, -35584, RunningPostureAdviceBase.COLOR_POORER};
            }

            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public float[] getThreshold() {
                return new float[]{22.0f, 27.0f, 36.0f, 45.0f, 53.0f, 58.0f};
            }
        };
        this.h = new IHealthMultiColorMode() { // from class: qjd.4
            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public int[] getDataColor() {
                return new int[]{RunningPostureAdviceBase.COLOR_POORER, -35584, -15078, RunningPostureAdviceBase.COLOR_NORMAL, -16733458};
            }

            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public float[] getThreshold() {
                return new float[]{-16.0f, -6.0f, 0.5f, 3.0f};
            }
        };
        this.r = new IHealthMultiColorMode() { // from class: qjd.1
            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public int[] getDataColor() {
                return new int[]{RunningPostureAdviceBase.COLOR_POORER, -35584, RunningPostureAdviceBase.COLOR_SUBOPTIMAL, -15078, RunningPostureAdviceBase.COLOR_NORMAL, -11748673, -16733458};
            }

            @Override // com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode
            public float[] getThreshold() {
                Integer[] d = qjd.this.d();
                return new float[]{d[1].intValue(), d[2].intValue(), d[3].intValue(), d[4].intValue(), d[5].intValue(), d[6].intValue()};
            }
        };
        this.b = context;
        this.q = i;
        this.i = i2;
        if (i == 0) {
            this.j = new qjo();
        } else if (i == 1) {
            this.j = new RqLineStateChartStorageHelper(i2);
        } else {
            this.j = new qjt();
        }
    }

    public boolean b() {
        return this.f16440a;
    }

    public void a(boolean z) {
        this.f16440a = z;
    }

    public boolean e() {
        return this.e;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.d;
    }

    public void b(boolean z) {
        this.d = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Integer[] d() {
        UserInfomation userInfo = ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo();
        if (userInfo == null) {
            userInfo = new UserInfomation(UnitUtil.h() ? 1 : 0);
        }
        return qrv.a(userInfo.getGender(), userInfo.getAge());
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder
    public IChartStorageHelper acquireStorageHelper() {
        return this.j;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMax(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        HwHealthLineChart hwHealthLineChart;
        float b;
        if (Math.abs(f) == Float.MAX_VALUE || Math.abs(f2) == Float.MAX_VALUE || "VO2MAX_TABLE".equals(hwHealthBaseBarLineDataSet.getLabel()) || (hwHealthLineChart = this.c) == null) {
            return 100.0f;
        }
        List<T> dataSets = ((now) hwHealthLineChart.getData()).getDataSets();
        boolean z = dataSets.size() > 1;
        Iterator it = dataSets.iterator();
        while (true) {
            if (it.hasNext()) {
                if ("STATE_TABLE".equals(((IHwHealthLineDataSet) it.next()).getLabel())) {
                    b = d(f, f2);
                    break;
                }
            } else {
                b = b(f, f2);
                break;
            }
        }
        return z ? b(hwHealthBaseBarLineDataSet, b) : b;
    }

    private float b(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f) {
        String label = hwHealthBaseBarLineDataSet.getLabel();
        if (label.equals("STATE_TABLE")) {
            this.o = f;
        } else if (label.equals("FITNESS_TABLE")) {
            this.l = f;
        } else {
            this.k = f;
        }
        return Math.max(this.k, Math.max(this.o, this.l));
    }

    private float b(float f, float f2) {
        float f3 = (f + f2) / 2.0f;
        float max = (float) Math.max(f3 + (Math.min(f - f3, f3 - f2) * 2.0f * 0.65f), f + (Math.pow(Math.max(f3, 5.0f), 0.5d) * 0.5d));
        LogUtil.a("RqChartLineViewHolder", "computeDynamicBorderMax: ", "runningMaxValue: ", Float.valueOf(max));
        return max;
    }

    private float d(float f, float f2) {
        float max;
        float abs = Math.abs(Math.max(f, 0.0f));
        float abs2 = Math.abs(Math.min(f2, 0.0f));
        if (abs > abs2 * 2.5f) {
            max = Math.max(6.0f, abs * 1.24f);
        } else if (abs2 > 2.5f * abs) {
            max = Math.max(6.0f, abs2 * 1.24f) / 3.0f;
        } else {
            max = Math.max(4.0f, Math.max(abs, abs2) * 1.1f);
        }
        LogUtil.a("RqChartLineViewHolder", "computeDynamicBorderMax: ", "stateMaxValue: ", Float.valueOf(max));
        return max;
    }

    @Override // com.huawei.ui.commonui.linechart.scrolladapter.DynamicBorderSupportable
    public float computeDynamicBorderMin(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f, float f2) {
        HwHealthLineChart hwHealthLineChart;
        float c;
        if (Math.abs(f) == Float.MAX_VALUE || Math.abs(f2) == Float.MAX_VALUE || "VO2MAX_TABLE".equals(hwHealthBaseBarLineDataSet.getLabel()) || (hwHealthLineChart = this.c) == null) {
            return 0.0f;
        }
        List<T> dataSets = ((now) hwHealthLineChart.getData()).getDataSets();
        boolean z = dataSets.size() > 1;
        Iterator it = dataSets.iterator();
        while (true) {
            if (it.hasNext()) {
                if ("STATE_TABLE".equals(((IHwHealthLineDataSet) it.next()).getLabel())) {
                    c = a(f, f2);
                    break;
                }
            } else {
                c = c(f, f2);
                this.s = 91.0f;
                break;
            }
        }
        return z ? a(hwHealthBaseBarLineDataSet, c) : c;
    }

    private float a(HwHealthBaseBarLineDataSet hwHealthBaseBarLineDataSet, float f) {
        String label = hwHealthBaseBarLineDataSet.getLabel();
        if (label.equals("STATE_TABLE")) {
            this.s = f;
        } else if (label.equals("FITNESS_TABLE")) {
            this.m = f;
        } else {
            this.n = f;
        }
        return Math.min(this.n, Math.min(this.s, this.m));
    }

    private float c(float f, float f2) {
        double min;
        double min2 = Math.min(f2 - ((Math.min(f - r0, r0 - f2) * 2.0f) * 0.05d), ((f + f2) / 2.0f) * 0.8d);
        if (this.q == 0) {
            min = Math.max(min2, 0.0d);
        } else {
            min = Math.min(min2, 0.0d);
        }
        float f3 = (float) min;
        LogUtil.a("RqChartLineViewHolder", "computeDynamicBorderMin: ", "runningMinValue: ", Float.valueOf(f3));
        return f3;
    }

    private float a(float f, float f2) {
        float f3;
        float abs = Math.abs(Math.max(f, 0.0f));
        float abs2 = Math.abs(Math.min(f2, 0.0f));
        if (abs > abs2 * 2.5f) {
            f3 = (-Math.max(6.0f, abs * 1.24f)) / 3.0f;
        } else if (abs2 > 2.5f * abs) {
            f3 = (-(Math.max(6.0f, abs2 * 1.24f) / 3.0f)) * 3.0f;
        } else {
            f3 = -Math.max(4.0f, Math.max(abs, abs2) * 1.1f);
        }
        LogUtil.a("RqChartLineViewHolder", "computeDynamicBorderMin: ", "stateMinValue: ", Float.valueOf(f3));
        return f3;
    }

    public String e(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : ruf.c(hwHealthBaseEntry.getY());
    }

    protected String a() {
        return b() ? "STATE_TABLE" : e() ? "FITNESS_TABLE" : "FATIGUE_TABLE";
    }

    @Override // com.huawei.ui.commonui.linechart.HwHealthLineScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthScrollChartHolder, com.huawei.ui.commonui.linechart.HwHealthChartHolder
    public HwHealthLineDataSet onCreateDataSet(HwHealthLineChart hwHealthLineChart, DataInfos dataInfos, HwHealthChartHolder.b bVar) {
        this.c = hwHealthLineChart;
        ArrayList arrayList = new ArrayList();
        if (b() || e() || c()) {
            HwHealthLineDataSet hwHealthLineDataSet = new HwHealthLineDataSet(this.b, arrayList, getChartBrief(dataInfos), a(), getChartUnit(dataInfos));
            hwHealthLineDataSet.d(Color.argb(229, 178, 178, 178));
            hwHealthLineDataSet.setLabelCount(5, true);
            hwHealthLineDataSet.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: qje
                @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
                public final boolean drawLine(int i, int i2, int i3) {
                    return qjd.a(i, i2, i3);
                }
            });
            hwHealthLineDataSet.setLineWidth(1.5f);
            hwHealthLineDataSet.setShowMaxMinValue(false);
            if (b()) {
                hwHealthLineDataSet.setColor(nrn.d(this.b, R.color._2131298953_res_0x7f090a89));
            } else if (e()) {
                hwHealthLineDataSet.setColor(nrn.d(this.b, R.color._2131298952_res_0x7f090a88));
            } else {
                hwHealthLineDataSet.setColor(nrn.d(this.b, R.color._2131298958_res_0x7f090a8e));
            }
            return hwHealthLineDataSet;
        }
        npc npcVar = new npc(arrayList, getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos));
        int i = this.q;
        if (i == 0) {
            npcVar.e(ruf.a() == 0 ? this.f : this.g);
            npcVar.setLabel("RUNNING_TABLE");
        } else {
            if (i == 1) {
                HwHealthLineDataSet hwHealthLineDataSet2 = new HwHealthLineDataSet(this.b, arrayList, getChartBrief(dataInfos), getChartLabel(dataInfos), getChartUnit(dataInfos));
                e(hwHealthLineDataSet2);
                hwHealthLineDataSet2.d(Color.argb(229, 178, 178, 178));
                hwHealthLineDataSet2.setLabelCount(5, true);
                hwHealthLineDataSet2.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: qjc
                    @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
                    public final boolean drawLine(int i2, int i3, int i4) {
                        return qjd.b(i2, i3, i4);
                    }
                });
                hwHealthLineDataSet2.setLineWidth(1.5f);
                hwHealthLineDataSet2.setLabel("STATE_TABLE");
                hwHealthLineDataSet2.setShowMaxMinValue(false);
                return hwHealthLineDataSet2;
            }
            npcVar.e(this.r);
            npcVar.setLabel("VO2MAX_TABLE");
        }
        npcVar.d(Color.argb(229, 178, 178, 178));
        npcVar.setLabelCount(5, true);
        npcVar.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: qjg
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public final boolean drawLine(int i2, int i3, int i4) {
                return qjd.c(i2, i3, i4);
            }
        });
        npcVar.setLineWidth(1.5f);
        npcVar.setShowMaxMinValue(false);
        return npcVar;
    }

    private void e(HwHealthLineDataSet hwHealthLineDataSet) {
        if (this.i == 0) {
            hwHealthLineDataSet.setColor(nrn.d(this.b, R.color._2131298953_res_0x7f090a89));
        }
        if (this.i == 1) {
            hwHealthLineDataSet.setColor(nrn.d(this.b, R.color._2131298952_res_0x7f090a88));
        }
        if (this.i == 2) {
            hwHealthLineDataSet.setColor(nrn.d(this.b, R.color._2131298958_res_0x7f090a8e));
        }
    }
}
