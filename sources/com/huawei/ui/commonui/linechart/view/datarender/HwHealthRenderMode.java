package com.huawei.ui.commonui.linechart.view.datarender;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.combinedchart.HwHealthCombinedChart;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.view.HwHealthLineDataSet;
import defpackage.nmz;
import defpackage.nnl;
import defpackage.npf;
import defpackage.npj;
import defpackage.npk;
import defpackage.npl;
import defpackage.npn;
import defpackage.nrn;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class HwHealthRenderMode {

    /* renamed from: a, reason: collision with root package name */
    protected ViewPortHandler f8904a;
    public DataInfos b;
    public boolean c;
    protected IRenderMode d = null;
    protected HwHealthBaseBarLineChart e;

    public interface IRenderMode {
        void clear();

        void draw(Canvas canvas, npf npfVar, Path path, npf npfVar2, npj.a aVar);

        void draw(Canvas canvas, npf npfVar, npj.a aVar);

        float getDisplayIntervalPx(Transformer transformer);

        float getLineWidth();

        NodeStyle getNodeStyle();

        int getNodeStyleConst();

        boolean isLadderMode();

        void prepareDraw();

        void setLadderMode(float f);

        void setLineWidth(float f);

        void setNodeStyle(NodeStyle nodeStyle);

        void setStandMode();
    }

    public interface NodeStyle {
        float getInnerRadius();

        float getOuterRadius();
    }

    public HwHealthRenderMode(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler) {
        this.e = hwHealthBaseBarLineChart;
        this.f8904a = viewPortHandler;
        c("render_foreground");
    }

    public HwHealthBaseBarLineChart c() {
        return this.e;
    }

    public ViewPortHandler b() {
        return this.f8904a;
    }

    public void b(String str) {
        c(str);
    }

    private void c(String str) {
        IRenderMode iRenderMode = this.d;
        if (iRenderMode != null) {
            iRenderMode.clear();
        }
        if ("render_foreground".equals(str)) {
            this.d = new e();
        } else if ("render_background".equals(str)) {
            this.d = new a();
        } else if ("render_focus_area".equals(str)) {
            this.d = new c(this.e, this.f8904a);
        }
    }

    public void d(HwHealthLineDataSet hwHealthLineDataSet) {
        this.d.setLineWidth(hwHealthLineDataSet.getLineWidth());
        c(hwHealthLineDataSet);
        IRenderMode iRenderMode = this.d;
        if (iRenderMode instanceof e) {
            e eVar = (e) iRenderMode;
            eVar.a(hwHealthLineDataSet.getColor());
            GradientDrawable cCP_ = hwHealthLineDataSet.cCP_();
            GradientDrawable cCO_ = hwHealthLineDataSet.cCO_();
            eVar.cDI_(cCP_);
            eVar.cDH_(cCO_);
            eVar.c(hwHealthLineDataSet.isDrawFilledEnabled());
            return;
        }
        if (iRenderMode instanceof a) {
            a aVar = (a) iRenderMode;
            aVar.b(nrn.d(R$color.health_bg_mode_line_color));
            aVar.cDD_(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{nrn.d(R$color.health_bg_mode_filleddrawable_start_color), nrn.d(R$color.health_bg_mode_filleddrawable_end_color)}));
            return;
        }
        if (iRenderMode instanceof c) {
            c cVar = (c) iRenderMode;
            cVar.e(nrn.d(R$color.health_bg_mode_line_color));
            cVar.b(hwHealthLineDataSet.getColor());
            GradientDrawable cCP_2 = hwHealthLineDataSet.cCP_();
            GradientDrawable cCO_2 = hwHealthLineDataSet.cCO_();
            cVar.cDG_(cCP_2);
            cVar.cDF_(cCO_2);
        }
    }

    private void c(HwHealthLineDataSet hwHealthLineDataSet) {
        IRenderMode iRenderMode = this.d;
        if (!(iRenderMode instanceof RenderModeCommon)) {
            LogUtil.b("HealthChart_HwHealthRenderMode", "mRenderMode type is error", iRenderMode);
            return;
        }
        iRenderMode.setNodeStyle(new npn(hwHealthLineDataSet.d(hwHealthLineDataSet.isDrawFilledEnabled())));
        RenderModeCommon renderModeCommon = (RenderModeCommon) this.d;
        if (renderModeCommon.getPointStyle() == 1) {
            HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.e;
            if (hwHealthBaseBarLineChart instanceof HwHealthCombinedChart) {
                List dataSets = hwHealthBaseBarLineChart.getData().getDataSets();
                if (dataSets.size() > 0 && (dataSets.get(0) instanceof HwHealthBarDataSet)) {
                    nmz barData = ((HwHealthCombinedChart) this.e).getBarData();
                    float[] fArr = {0.0f, 0.0f, barData != null ? barData.d() : 0.0f, 0.0f};
                    this.e.getTransformer(hwHealthLineDataSet.getAxisDependencyExt()).pointValuesToPixel(fArr);
                    this.d.setNodeStyle(new npl((fArr[2] - fArr[0]) / 2.0f));
                    return;
                }
            }
            this.d.setNodeStyle(new npl(nsn.c(BaseApplication.getContext(), 3.5f)));
            return;
        }
        if (renderModeCommon.getPointStyle() == 2) {
            this.d.setNodeStyle(new npk(nsn.c(BaseApplication.getContext(), 5.0f)));
        }
    }

    public IRenderMode a() {
        return this.d;
    }

    public void d() {
        this.d.prepareDraw();
    }

    public static abstract class RenderModeCommon implements IRenderMode {
        private float mDisplayInterval;
        private float mLineWidthPx;
        private NodeStyle mNodeStyle;
        private int mPointStyle = 0;
        private boolean mIsLadderLineMode = false;

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void setLadderMode(float f) {
            this.mIsLadderLineMode = true;
            this.mDisplayInterval = f;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public float getDisplayIntervalPx(Transformer transformer) {
            float[] fArr = {0.0f, 0.0f, this.mDisplayInterval, 0.0f};
            transformer.pointValuesToPixel(fArr);
            return Math.abs(fArr[2] - fArr[0]);
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void setStandMode() {
            this.mIsLadderLineMode = false;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public boolean isLadderMode() {
            return this.mIsLadderLineMode;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void setLineWidth(float f) {
            this.mLineWidthPx = f;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void setNodeStyle(NodeStyle nodeStyle) {
            this.mNodeStyle = nodeStyle;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public float getLineWidth() {
            return this.mLineWidthPx;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public NodeStyle getNodeStyle() {
            return this.mNodeStyle;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public int getNodeStyleConst() {
            return this.mPointStyle;
        }

        public int getPointStyle() {
            return this.mPointStyle;
        }

        public void setPointStyle(int i) {
            this.mPointStyle = i;
        }
    }

    public static class e extends RenderModeCommon {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f8907a;
        private boolean b = true;
        private int d;
        private Drawable e;

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void clear() {
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void prepareDraw() {
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void draw(Canvas canvas, npf npfVar, npj.a aVar) {
            aVar.cDy_(canvas, npfVar, this.d);
            if (this.b) {
                aVar.cDz_(canvas, npfVar, this.f8907a, this.e);
            }
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void draw(Canvas canvas, npf npfVar, Path path, npf npfVar2, npj.a aVar) {
            aVar.cDw_(canvas, npfVar, path, npfVar2, this.d);
            if (this.b) {
                aVar.cDx_(canvas, npfVar, path, npfVar2, this.f8907a, this.e);
            }
        }

        public void a(int i) {
            this.d = i;
        }

        public void cDI_(Drawable drawable) {
            this.f8907a = drawable;
        }

        public void cDH_(Drawable drawable) {
            this.e = drawable;
        }

        public void c(boolean z) {
            this.b = z;
        }
    }

    public static class a extends RenderModeCommon {

        /* renamed from: a, reason: collision with root package name */
        private int f8905a;
        private Drawable c;

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void clear() {
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void prepareDraw() {
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void draw(Canvas canvas, npf npfVar, npj.a aVar) {
            aVar.cDy_(canvas, npfVar, this.f8905a);
            aVar.cDz_(canvas, npfVar, this.c);
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void draw(Canvas canvas, npf npfVar, Path path, npf npfVar2, npj.a aVar) {
            aVar.cDw_(canvas, npfVar, path, npfVar2, this.f8905a);
            aVar.cDx_(canvas, npfVar, path, npfVar2, this.c);
        }

        public void b(int i) {
            this.f8905a = i;
        }

        public void cDD_(Drawable drawable) {
            this.c = drawable;
        }
    }

    public static class c extends RenderModeCommon {

        /* renamed from: a, reason: collision with root package name */
        private Drawable f8906a;
        private HwHealthBaseBarLineChart b;
        private Path c;
        private Drawable d;
        private List<nnl> e;
        private ViewPortHandler h;
        private int i;
        private int j;

        private c(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler) {
            this.e = new ArrayList();
            this.c = new Path();
            this.b = hwHealthBaseBarLineChart;
            this.h = viewPortHandler;
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void clear() {
            List<nnl> list = this.e;
            if (list != null) {
                list.clear();
            }
            Path path = this.c;
            if (path != null) {
                path.reset();
            }
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void prepareDraw() {
            d();
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void draw(Canvas canvas, npf npfVar, npj.a aVar) {
            int contentBottom = (int) (this.h.contentBottom() + this.b.getXAxis().getYOffset());
            int save = canvas.save();
            canvas.clipRect((int) this.h.contentLeft(), (int) this.h.contentTop(), (int) this.h.contentRight(), contentBottom);
            canvas.clipPath(this.c, Region.Op.DIFFERENCE);
            aVar.cDy_(canvas, npfVar, this.i);
            canvas.restoreToCount(save);
            int save2 = canvas.save();
            canvas.clipRect((int) this.h.contentLeft(), (int) this.h.contentTop(), (int) this.h.contentRight(), contentBottom);
            canvas.clipPath(this.c);
            aVar.cDy_(canvas, npfVar, this.j);
            aVar.cDz_(canvas, npfVar, this.d, this.f8906a);
            canvas.restoreToCount(save2);
        }

        @Override // com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode.IRenderMode
        public void draw(Canvas canvas, npf npfVar, Path path, npf npfVar2, npj.a aVar) {
            int contentBottom = (int) (this.h.contentBottom() + this.b.getXAxis().getYOffset());
            int save = canvas.save();
            float f = contentBottom;
            canvas.clipRect(npfVar.b(), (int) this.h.contentTop(), npfVar2.b(), f);
            canvas.clipPath(this.c, Region.Op.DIFFERENCE);
            aVar.cDw_(canvas, npfVar, path, npfVar2, this.i);
            canvas.restoreToCount(save);
            int save2 = canvas.save();
            canvas.clipRect(npfVar.b(), (int) this.h.contentTop(), npfVar2.b(), f);
            canvas.clipPath(this.c);
            aVar.cDw_(canvas, npfVar, path, npfVar2, this.j);
            aVar.cDx_(canvas, npfVar, path, npfVar2, this.d, this.f8906a);
            canvas.restoreToCount(save2);
        }

        public boolean b(List<nnl> list) {
            if (list == null || this.e == null || list.size() != this.e.size()) {
                return false;
            }
            for (int i = 0; i < this.e.size(); i++) {
                if (list.get(i).c() != this.e.get(i).c() || list.get(i).e() != this.e.get(i).e()) {
                    return false;
                }
            }
            return true;
        }

        public void d(List<nnl> list) {
            this.e.clear();
            this.e.addAll(list);
        }

        public void d() {
            this.c.reset();
            for (nnl nnlVar : this.e) {
                if (nnlVar != null) {
                    float[] fArr = {nnlVar.c(), 0.0f, nnlVar.e(), 0.0f};
                    HwHealthBaseBarLineChart hwHealthBaseBarLineChart = this.b;
                    hwHealthBaseBarLineChart.getTransformer(hwHealthBaseBarLineChart.getAxisFirstParty().e()).pointValuesToPixel(fArr);
                    this.c.addRect(fArr[0], this.h.contentTop(), fArr[2], (int) (this.h.contentBottom() + this.b.getXAxis().getYOffset()), Path.Direction.CW);
                }
            }
        }

        public void b(int i) {
            this.j = i;
        }

        public void cDG_(Drawable drawable) {
            this.d = drawable;
        }

        public void cDF_(Drawable drawable) {
            this.f8906a = drawable;
        }

        public Path cDE_() {
            return this.c;
        }

        public void e(int i) {
            this.i = i;
        }
    }
}
