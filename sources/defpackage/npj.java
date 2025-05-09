package defpackage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IHwHealthLineDatasContainer;
import com.huawei.ui.commonui.linechart.view.datarender.HwHealthRenderMode;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class npj {
    protected HwHealthRenderMode e;
    protected c d = new c();
    protected Transformer b = null;

    public npj(HwHealthRenderMode hwHealthRenderMode) {
        this.e = hwHealthRenderMode;
    }

    private int[] a(List<npf> list, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        float lowestVisibleX = hwHealthBaseBarLineChart.getLowestVisibleX();
        float highestVisibleX = hwHealthBaseBarLineChart.getHighestVisibleX();
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < list.size(); i3++) {
            npf npfVar = list.get(i3);
            if (npfVar.b() >= lowestVisibleX && npfVar.b() <= highestVisibleX) {
                if (i == -1) {
                    i = i3;
                }
                i2 = i3 + 1;
            }
        }
        if (i == -1 || i2 == -1) {
            for (int i4 = 0; i4 < list.size() && list.get(i4).b() <= lowestVisibleX; i4++) {
                i = i4;
            }
            i2 = i + 1;
        }
        if (i == -1) {
            return null;
        }
        int i5 = i - 1;
        if (i5 >= 0) {
            i = i5;
        }
        int i6 = i2 + 1;
        if (i6 <= list.size()) {
            i2 = i6;
        }
        return new int[]{i, i2};
    }

    protected List<npf> e(IHwHealthLineDatasContainer iHwHealthLineDatasContainer) {
        return iHwHealthLineDatasContainer.queryNodes();
    }

    public void cDs_(Canvas canvas, IHwHealthLineDatasContainer iHwHealthLineDatasContainer, Transformer transformer) {
        int[] a2;
        npf npfVar;
        this.b = transformer;
        List<npf> e = e(iHwHealthLineDatasContainer);
        if (koq.b(e) || (a2 = a(e, iHwHealthLineDatasContainer.acquireChart())) == null) {
            return;
        }
        int i = a2[0];
        int i2 = a2[1];
        Path path = new Path();
        int i3 = i;
        int i4 = 0;
        npf npfVar2 = null;
        while (i3 < i2) {
            npf npfVar3 = e.get(i3);
            if (npfVar3.f()) {
                this.d.cDC_(canvas, npfVar3, this.e);
            } else {
                if (path.isEmpty() && !npfVar3.i()) {
                    path.moveTo(npfVar3.b(), npfVar3.d());
                    npfVar = npfVar3;
                } else {
                    if (path.isEmpty() && npfVar3.i()) {
                        npfVar2 = e.get(i3 - 1);
                        path.moveTo(npfVar2.b(), npfVar2.d());
                        cDt_(path, npfVar2, npfVar3);
                    } else {
                        cDt_(path, e.get(i3 - 1), npfVar3);
                    }
                    npfVar = npfVar2;
                }
                int i5 = i4 + 1;
                if (!path.isEmpty()) {
                    if (e(npfVar, e, i3, i5, i3 == i2 + (-1))) {
                        this.d.cDB_(canvas, npfVar, path, npfVar3, this.e);
                        path.reset();
                        i4 = 0;
                        npfVar2 = null;
                    }
                }
                npfVar2 = npfVar;
                i4 = i5;
            }
            i3++;
        }
        cDr_(canvas, e);
    }

    private boolean e(npf npfVar, List<npf> list, int i, int i2, boolean z) {
        if (npfVar == null) {
            return false;
        }
        if (z) {
            return true;
        }
        int i3 = i + 1;
        return !(koq.b(list, i3) || list.get(i3).i()) || i2 >= 128;
    }

    private void cDr_(Canvas canvas, List<npf> list) {
        for (npf npfVar : list) {
            if (npfVar.j() || npfVar.e()) {
                this.d.cDC_(canvas, npfVar, this.e);
            }
        }
        HwHealthRenderMode.NodeStyle nodeStyle = this.e.a().getNodeStyle();
        if (nodeStyle instanceof npl) {
            Iterator<npf> it = list.iterator();
            while (it.hasNext()) {
                this.d.cDC_(canvas, it.next(), this.e);
            }
            return;
        }
        if (nodeStyle instanceof npk) {
            for (int i = 0; i < list.size() - 1; i++) {
                this.d.cDC_(canvas, list.get(i), this.e);
            }
            this.e.a().setNodeStyle(new npm(nsn.c(BaseApplication.getContext(), 6.0f)));
            this.d.cDC_(canvas, list.get(list.size() - 1), this.e);
        }
    }

    boolean c(HwHealthRenderMode.NodeStyle nodeStyle) {
        return nodeStyle instanceof npl;
    }

    boolean a(HwHealthRenderMode.NodeStyle nodeStyle) {
        return nodeStyle instanceof npk;
    }

    boolean e(HwHealthRenderMode.NodeStyle nodeStyle) {
        return nodeStyle instanceof npm;
    }

    protected void cDt_(Path path, npf npfVar, npf npfVar2) {
        if (this.e.a().isLadderMode()) {
            path.lineTo(npfVar2.b(), npfVar.d());
            path.lineTo(npfVar2.b(), npfVar2.d());
        } else {
            path.lineTo(npfVar2.b(), npfVar2.d());
        }
    }

    protected class c {
        private a b;

        protected c() {
            this.b = npj.this.new a();
        }

        public void cDC_(Canvas canvas, npf npfVar, HwHealthRenderMode hwHealthRenderMode) {
            hwHealthRenderMode.a().draw(canvas, c(npfVar), this.b);
        }

        public void cDB_(Canvas canvas, npf npfVar, Path path, npf npfVar2, HwHealthRenderMode hwHealthRenderMode) {
            npf c = c(npfVar);
            cDA_(path);
            hwHealthRenderMode.a().draw(canvas, c, path, c(npfVar2), this.b);
        }

        private void cDA_(Path path) {
            npj.this.b.pathValueToPixel(path);
        }

        private npf c(npf npfVar) {
            return npfVar.c(npj.this.b);
        }
    }

    public class a {
        protected Paint d = new Paint(1);

        public a() {
        }

        public void cDw_(Canvas canvas, npf npfVar, Path path, npf npfVar2, int i) {
            this.d.setStyle(Paint.Style.STROKE);
            this.d.setStrokeCap(Paint.Cap.SQUARE);
            this.d.setStrokeJoin(Paint.Join.ROUND);
            this.d.setColor(i);
            this.d.setStrokeWidth(npj.this.e.a().getLineWidth());
            HwHealthBaseBarLineChart c = npj.this.e.c();
            int contentBottom = (int) (npj.this.e.b().contentBottom() + c.getXAxis().getYOffset());
            int save = canvas.save();
            canvas.clipRect(npfVar.b(), (int) r1.contentTop(), npfVar2.b(), contentBottom);
            if (npj.this.e.a().getNodeStyleConst() == 1) {
                this.d.setShadowLayer(Utils.convertDpToPixel(5.0f), 0.0f, 6.0f, Color.argb(38, Color.red(i), Color.green(i), Color.blue(i)));
                canvas.drawPath(path, this.d);
                this.d.clearShadowLayer();
            } else {
                canvas.drawPath(path, this.d);
            }
            canvas.restoreToCount(save);
        }

        public void cDx_(Canvas canvas, npf npfVar, Path path, npf npfVar2, Drawable... drawableArr) {
            cDv_(canvas, npfVar, path, npfVar2, drawableArr);
        }

        public void cDy_(Canvas canvas, npf npfVar, int i) {
            HwHealthRenderMode.IRenderMode a2 = npj.this.e.a();
            HwHealthRenderMode.NodeStyle nodeStyle = a2.getNodeStyle();
            if (npj.this.c(nodeStyle)) {
                cDu_(canvas, npfVar, i, nrn.d(R$color.colorBackground), nodeStyle);
                return;
            }
            if (npj.this.a(nodeStyle) || npj.this.e(nodeStyle)) {
                cDu_(canvas, npfVar, nrn.d(R$color.emui_card_bg), i, nodeStyle);
                return;
            }
            if (a2.isLadderMode()) {
                Path path = new Path();
                path.moveTo(npfVar.b(), npfVar.d());
                path.lineTo(npfVar.b() + a2.getDisplayIntervalPx(npj.this.b), npfVar.d());
                cDw_(canvas, npfVar, path, new npf(npfVar.b() + a2.getDisplayIntervalPx(npj.this.b), npfVar.d(), null), i);
                return;
            }
            if (npfVar.j() || npfVar.e()) {
                npo npoVar = new npo();
                npj.this.e.a().setNodeStyle(npoVar);
                cDu_(canvas, npfVar, nrn.d(R$color.chart_background_color), i, npoVar);
            } else {
                this.d.setStrokeCap(Paint.Cap.ROUND);
                this.d.setColor(i);
                this.d.setStrokeWidth(nodeStyle.getInnerRadius());
                canvas.drawLine(npfVar.b(), npfVar.d(), npfVar.b(), npfVar.d(), this.d);
            }
        }

        private void cDu_(Canvas canvas, npf npfVar, int i, int i2, HwHealthRenderMode.NodeStyle nodeStyle) {
            this.d.setAntiAlias(true);
            this.d.setStrokeWidth(1.0f);
            this.d.setStyle(Paint.Style.FILL);
            this.d.setColor(i);
            canvas.drawCircle(npfVar.b(), npfVar.d(), nodeStyle.getOuterRadius(), this.d);
            this.d.setColor(i2);
            canvas.drawCircle(npfVar.b(), npfVar.d(), nodeStyle.getInnerRadius(), this.d);
        }

        public void cDz_(Canvas canvas, npf npfVar, Drawable... drawableArr) {
            if (npj.this.e.a() instanceof HwHealthRenderMode.RenderModeCommon) {
                Path path = new Path();
                HwHealthRenderMode.RenderModeCommon renderModeCommon = (HwHealthRenderMode.RenderModeCommon) npj.this.e.a();
                HwHealthRenderMode.NodeStyle nodeStyle = renderModeCommon.getNodeStyle();
                if (renderModeCommon.isLadderMode()) {
                    path.moveTo(npfVar.b(), npfVar.d());
                    path.lineTo(npfVar.b() + renderModeCommon.getDisplayIntervalPx(npj.this.b), npfVar.d());
                    cDx_(canvas, new npf(npfVar.b(), npfVar.d(), null), path, new npf(npfVar.b() + renderModeCommon.getDisplayIntervalPx(npj.this.b), npfVar.d(), null), drawableArr);
                } else if (nodeStyle instanceof npn) {
                    float innerRadius = nodeStyle.getInnerRadius();
                    Path path2 = new Path();
                    float f = innerRadius / 2.0f;
                    float b = npfVar.b() - f;
                    float b2 = npfVar.b() + f;
                    path2.moveTo(b, npfVar.d());
                    path2.lineTo(b2, npfVar.d());
                    cDx_(canvas, new npf(b, npfVar.d(), null), path2, new npf(b2, npfVar.d(), null), drawableArr);
                }
            }
        }

        protected void cDv_(Canvas canvas, npf npfVar, Path path, npf npfVar2, Drawable... drawableArr) {
            Path path2;
            boolean z;
            HwHealthBaseBarLineChart c = npj.this.e.c();
            Path path3 = new Path();
            path3.addPath(path);
            path3.lineTo(npfVar2.b(), c.getContentRect().bottom + c.getXAxis().getYOffset());
            path3.lineTo(npfVar.b(), c.getContentRect().bottom + c.getXAxis().getYOffset());
            path3.close();
            HwHealthRenderMode.IRenderMode a2 = npj.this.e.a();
            if (a2 instanceof HwHealthRenderMode.c) {
                path2 = ((HwHealthRenderMode.c) a2).cDE_();
                z = true;
            } else {
                path2 = null;
                z = false;
            }
            int save = canvas.save();
            canvas.clipPath(path3);
            if (z) {
                canvas.clipPath(path2);
            }
            int length = drawableArr.length;
            Drawable[] drawableArr2 = new Drawable[length];
            System.arraycopy(drawableArr, 0, drawableArr2, 0, length);
            npj.this.cDq_(canvas, drawableArr2, c);
            canvas.restoreToCount(save);
        }
    }

    private float a(HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler) {
        if (hwHealthBaseBarLineChart.getAxisFirstParty().mEntries != null && hwHealthBaseBarLineChart.getAxisFirstParty().mEntries.length > 2) {
            float[] fArr = (float[]) hwHealthBaseBarLineChart.getAxisFirstParty().mEntries.clone();
            int length = fArr.length - 2;
            if (b(this.b)) {
                length = 1;
            }
            float[] fArr2 = {0.0f, fArr[length]};
            this.b.pointValuesToPixel(fArr2);
            return fArr2[1];
        }
        return viewPortHandler.contentTop() + ((viewPortHandler.contentBottom() - viewPortHandler.contentTop()) / 5.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cDq_(Canvas canvas, Drawable[] drawableArr, HwHealthBaseBarLineChart hwHealthBaseBarLineChart) {
        Drawable drawable;
        Drawable drawable2;
        float f;
        Drawable drawable3 = null;
        if (drawableArr.length == 1) {
            drawable2 = drawableArr[0];
            drawable = null;
        } else if (drawableArr.length == 2) {
            Drawable drawable4 = drawableArr[0];
            drawable = drawableArr[1];
            drawable3 = drawable4;
            drawable2 = null;
        } else {
            drawable = null;
            drawable2 = null;
        }
        ViewPortHandler b = this.e.b();
        float a2 = a(hwHealthBaseBarLineChart, b);
        boolean z = this.e.c;
        if (z) {
            float contentBottom = (b.contentBottom() - b.contentTop()) / 4.0f;
            f = contentBottom;
            a2 = b.contentTop() + contentBottom;
        } else {
            f = 0.0f;
        }
        if (drawable3 != null) {
            drawable3.setBounds((int) b.contentLeft(), (int) b.contentTop(), (int) b.contentRight(), (int) a2);
            drawable3.draw(canvas);
        }
        int contentBottom2 = (int) (b.contentBottom() + hwHealthBaseBarLineChart.getXAxis().getYOffset());
        if (z) {
            contentBottom2 = (int) (b.contentTop() + (f * 2.0f));
        }
        if (drawable != null) {
            drawable.setBounds((int) b.contentLeft(), (int) a2, (int) b.contentRight(), contentBottom2);
            drawable.draw(canvas);
        }
        if (drawable2 != null) {
            drawable2.setBounds((int) b.contentLeft(), (int) b.contentTop(), (int) b.contentRight(), contentBottom2);
            drawable2.draw(canvas);
        }
    }

    private boolean b(Transformer transformer) {
        ViewPortHandler b = this.e.b();
        float[] fArr = {0.0f, b.contentTop()};
        float[] fArr2 = {0.0f, b.contentBottom()};
        transformer.pixelsToValue(fArr);
        transformer.pixelsToValue(fArr2);
        return fArr2[1] > fArr[1];
    }
}
