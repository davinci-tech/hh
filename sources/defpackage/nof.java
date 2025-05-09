package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.view.MotionEvent;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseBarLineChart;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.common.touch.HwHealthBarLineChartTouchListener;
import com.huawei.ui.commonui.linechart.common.touch.OperationSequence;

/* loaded from: classes6.dex */
public class nof implements OperationSequence {
    private HwHealthBaseBarLineChart b;
    private Matrix d;
    private HwHealthBarLineChartTouchListener e;

    /* renamed from: a, reason: collision with root package name */
    private boolean f15411a = false;
    private float g = 1.0f;
    private float j = 1.0f;
    private float h = 1.0f;
    private MPPointF i = MPPointF.getInstance(0.0f, 0.0f);
    private Matrix f = new Matrix();
    private float c = Utils.convertDpToPixel(3.5f);

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public void release() {
    }

    public nof(Context context, HwHealthBaseBarLineChart hwHealthBaseBarLineChart, ViewPortHandler viewPortHandler, HwHealthBarLineChartTouchListener hwHealthBarLineChartTouchListener) {
        this.b = null;
        this.e = null;
        this.d = null;
        this.b = hwHealthBaseBarLineChart;
        this.e = hwHealthBarLineChartTouchListener;
        this.d = viewPortHandler.getMatrixTouch();
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public boolean canSeqStart(MotionEvent motionEvent) {
        return motionEvent != null && (motionEvent.getAction() & 255) == 5;
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public void onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 2) {
            cBQ_(motionEvent);
        } else {
            if (action != 3) {
                if (action == 5) {
                    cBR_(motionEvent);
                } else if (action != 6) {
                    b();
                }
            }
            cBP_(motionEvent);
        }
        this.d = this.b.getViewPortHandler().refresh(this.d, this.b, true);
    }

    private void cBP_(MotionEvent motionEvent) {
        b();
    }

    private void cBR_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return;
        }
        if (motionEvent.getPointerCount() < 2) {
            b();
            return;
        }
        if (this.e.f8882a == HwHealthBaseScrollBarLineChart.ChartShowMode.SCROLL_MODE) {
            b();
            return;
        }
        cBU_(motionEvent);
        this.g = cBN_(motionEvent);
        this.j = cBO_(motionEvent);
        this.h = cBV_(motionEvent);
        if (!this.b.isScaleXEnabled()) {
            b();
            return;
        }
        if (this.h <= 10.0f) {
            b();
        } else if (this.g <= this.j) {
            b();
        } else {
            cBS_(this.i, motionEvent);
        }
    }

    private void cBU_(MotionEvent motionEvent) {
        this.f.set(this.d);
    }

    private static void cBS_(MPPointF mPPointF, MotionEvent motionEvent) {
        if (motionEvent == null) {
            return;
        }
        float x = motionEvent.getX(0);
        float x2 = motionEvent.getX(1);
        float y = motionEvent.getY(0);
        float y2 = motionEvent.getY(1);
        mPPointF.x = (x + x2) / 2.0f;
        mPPointF.y = (y + y2) / 2.0f;
    }

    private void cBQ_(MotionEvent motionEvent) {
        cBT_(motionEvent);
    }

    private void cBT_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return;
        }
        if (motionEvent.getPointerCount() < 2) {
            b();
            return;
        }
        if (cBV_(motionEvent) <= this.c) {
            b();
            return;
        }
        MPPointF d = d(this.i.x, this.i.y);
        ViewPortHandler viewPortHandler = this.b.getViewPortHandler();
        float cBN_ = cBN_(motionEvent) / this.g;
        if (cBN_ < 1.0f ? viewPortHandler.canZoomOutMoreX() : viewPortHandler.canZoomInMoreX()) {
            this.d.set(this.f);
            this.d.postScale(cBN_, 1.0f, d.x, d.y);
        }
        MPPointF.recycleInstance(d);
    }

    public MPPointF d(float f, float f2) {
        ViewPortHandler viewPortHandler = this.b.getViewPortHandler();
        return MPPointF.getInstance(f - viewPortHandler.offsetLeft(), -((this.b.getMeasuredHeight() - f2) - viewPortHandler.offsetBottom()));
    }

    private static float cBN_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return 0.0f;
        }
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    private static float cBO_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return 0.0f;
        }
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public boolean isSeqInterrupted() {
        return this.f15411a;
    }

    @Override // com.huawei.ui.commonui.linechart.common.touch.OperationSequence
    public void prepare() {
        this.f15411a = false;
    }

    private void b() {
        this.f15411a = true;
    }

    private static float cBV_(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return 0.0f;
        }
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((x * x) + (y * y));
    }
}
