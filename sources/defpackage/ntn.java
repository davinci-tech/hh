package defpackage;

import android.content.Context;
import android.graphics.Color;
import com.github.mikephil.charting.utils.Utils;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import health.compact.a.UnitUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class ntn {
    public static final int d = Color.argb(255, OldToNewMotionPath.SPORT_TYPE_AEROBICS, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY, 191);
    public static final int c = Color.argb(255, 251, 101, 34);
    public static final int i = Color.argb(255, 179, 124, 111);
    public static final int f = Color.argb(255, 127, 224, 227);
    public static final int b = Color.argb(255, 31, 113, 255);
    public static final int h = Color.argb(255, OldToNewMotionPath.SPORT_TYPE_PILATES, 187, 255);

    /* renamed from: a, reason: collision with root package name */
    public static final int f15485a = Color.argb(255, 180, 204, 61);
    public static final int e = Color.argb(255, 251, 101, 34);
    public static final int j = Color.argb(51, 0, 0, 0);

    static /* synthetic */ boolean a(int i2, int i3, int i4) {
        return false;
    }

    public static HwHealthLineDataSet i(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_bolt_vertical_amplitude), context.getResources().getString(R$string.IDS_bolt_vertical_amplitude), context.getResources().getString(UnitUtil.h() ? R$string.IDS_ins : R$string.IDS_cm));
        c(npcVar);
        npcVar.setColor(f);
        return npcVar;
    }

    public static void b(HwHealthLineDataSet hwHealthLineDataSet) {
        hwHealthLineDataSet.d(new HwHealthLineDataSet.NodeDrawStyle() { // from class: ntn.5
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float initNodeStyle(int i2) {
                return 0.0f;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public boolean needDrawNodeFill(boolean z) {
                return false;
            }

            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.NodeDrawStyle
            public float calcuNodeWidthPixel(boolean z) {
                return Utils.convertDpToPixel(4.0f);
            }
        });
    }

    public static HwHealthLineDataSet e(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_bolt_balance_left_right_touches), context.getResources().getString(R$string.IDS_bolt_balance_left_right_touches), "");
        c(npcVar);
        npcVar.setColor(d);
        return npcVar;
    }

    public static HwHealthLineDataSet b(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_running_posture_ground_contact_time), context.getResources().getString(R$string.IDS_running_posture_ground_contact_time), context.getResources().getString(R$string.IDS_msec_unit));
        c(npcVar);
        npcVar.setColor(b);
        return npcVar;
    }

    public static HwHealthLineDataSet j(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_bolt_vertical_stride_rate), context.getResources().getString(R$string.IDS_bolt_vertical_stride_rate), "%");
        c(npcVar);
        npcVar.setColor(i);
        return npcVar;
    }

    public static HwHealthLineDataSet d(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_aw_version2_duration_of_passage), context.getResources().getString(R$string.IDS_aw_version2_duration_of_passage), context.getResources().getString(R$string.IDS_msec_unit));
        c(npcVar);
        npcVar.setColor(h);
        return npcVar;
    }

    private static void c(npc npcVar) {
        npcVar.setCircleRadius(5.0f);
        npcVar.setNodeCircleBackground(false);
        npcVar.a(new HwHealthLineDataSet.LineLinkerFilter() { // from class: ntm
            @Override // com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet.LineLinkerFilter
            public final boolean drawLine(int i2, int i3, int i4) {
                return ntn.a(i2, i3, i4);
            }
        });
    }

    public static HwHealthLineDataSet c(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_bolt_shock_peak), context.getResources().getString(R$string.IDS_bolt_shock_peak), context.getResources().getString(R$string.IDS_bolt_shock_peak_unit));
        c(npcVar);
        npcVar.setColor(c);
        return npcVar;
    }

    public static HwHealthLineDataSet a(ArrayList<HwHealthBaseEntry> arrayList, Context context, TrackLineChartHolder.b bVar) {
        npc npcVar = new npc(arrayList, context.getResources().getString(R$string.IDS_motiontrack_ground_to_air_ratio), context.getResources().getString(R$string.IDS_motiontrack_ground_to_air_ratio), "");
        c(npcVar);
        npcVar.setColor(f15485a);
        return npcVar;
    }

    public static int b(int i2) {
        int i3 = j;
        if (i2 == 7) {
            i3 = b;
        }
        if (i2 == 8) {
            i3 = e;
        }
        if (i2 == 16) {
            i3 = h;
        }
        if (i2 == 17) {
            i3 = f15485a;
        }
        if (i2 == 19) {
            i3 = d;
        }
        if (i2 == 18) {
            i3 = f;
        }
        if (i2 == 20) {
            i3 = i;
        }
        return i2 == 21 ? c : i3;
    }
}
