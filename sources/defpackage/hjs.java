package defpackage;

import android.content.Context;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class hjs {

    /* renamed from: a, reason: collision with root package name */
    private HealthTableWidget f13190a;
    private SportDetailChartDataType b;
    private List<gxq> c;
    private Context e;
    private hgf g;
    private float[] i;
    private final nre<hjj, hjc, hjc, hjc, hjc, hjc> j = new nre<>();
    private float d = 1.0f;

    public hjs(Context context, SportDetailChartDataType sportDetailChartDataType, List<PostureJudgeBean> list, List<gxq> list2) {
        this.c = new ArrayList();
        this.b = sportDetailChartDataType;
        if (list != null && list.size() >= 1) {
            this.e = context;
            this.c = list2;
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "posturePostureJudgeBean.size() < 1";
            objArr[1] = Boolean.valueOf(list.size() < 1);
            LogUtil.b("Track_PostureTableViewHolder", objArr);
        }
    }

    public void a(float f) {
        this.d = f;
    }

    public void b(HealthTableWidget healthTableWidget, float[] fArr) {
        this.f13190a = healthTableWidget;
        if (fArr != null) {
            this.i = (float[]) fArr.clone();
        }
        a();
    }

    public void b(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        hjt.d(nreVar, this.b, this.e);
        for (int i = 0; i < 4; i++) {
            hjt.d(nreVar, i, this.c);
        }
    }

    private void a() {
        if (this.e == null) {
            return;
        }
        b(this.j);
        LogUtil.a("Track_PostureTableViewHolder", "setPaceTableAdapter");
        this.g = new hgf(this.e, this.j, false, this.d * (LanguageUtil.bi(this.e) ? 38.5f : 20.0f)) { // from class: hjs.4
            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnCount() {
                return 4;
            }

            @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidthValueType() {
                return 1;
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidth(int i) {
                if (hjs.this.i != null && i >= 0 && i < hjs.this.i.length) {
                    return nsn.c(hjs.this.e, hjs.this.i[i]);
                }
                return super.getColumnWidth(i);
            }

            @Override // defpackage.hgf
            public int a() {
                return hjs.this.e.getResources().getDimensionPixelSize(R.dimen._2131362564_res_0x7f0a0304);
            }
        };
        LogUtil.a("Track_PostureTableViewHolder", "setAdapter");
        int c = nsn.c(this.e, (this.c.size() * 25 * Math.round(this.d)) + 35);
        if (this.f13190a.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f13190a.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, c);
            layoutParams2.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
            this.f13190a.setLayoutParams(layoutParams2);
        }
        this.f13190a.setPostureAdapter(this.g, this.d);
        this.f13190a.setVisibility(0);
    }
}
