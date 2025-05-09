package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.linechart.view.commonlinechart.IHealthMultiColorMode;
import java.util.List;

/* loaded from: classes6.dex */
public class npc extends HwHealthLineDataSet {

    /* renamed from: a, reason: collision with root package name */
    private int[] f15419a;
    private boolean d;
    private float[] e;

    public npc(List<HwHealthBaseEntry> list, String str, String str2, String str3) {
        super(BaseApplication.getContext(), list, str, str2, str3);
        this.d = false;
    }

    public void e(IHealthMultiColorMode iHealthMultiColorMode) {
        if (iHealthMultiColorMode == null || iHealthMultiColorMode.getDataColor().length != iHealthMultiColorMode.getThreshold().length + 1) {
            this.d = false;
            LogUtil.c("HwHealthMultiColorDataSet", "muti color chart mode is invalid");
        } else {
            this.d = true;
            this.e = iHealthMultiColorMode.getThreshold();
            this.f15419a = iHealthMultiColorMode.getDataColor();
        }
    }

    public boolean n() {
        return this.d;
    }

    public float[] m() {
        return this.e;
    }

    public int[] e() {
        return this.f15419a;
    }
}
