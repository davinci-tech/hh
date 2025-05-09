package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class MockView extends View {
    private int mDiagonalsColor;
    private boolean mDrawDiagonals;
    private boolean mDrawLabel;
    private int mMargin;
    private Paint mPaintDiagonals;
    private Paint mPaintText;
    private Paint mPaintTextBackground;
    protected String mText;
    private int mTextBackgroundColor;
    private Rect mTextBounds;
    private int mTextColor;

    public MockView(Context context) {
        super(context);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(255, 0, 0, 0);
        this.mTextColor = Color.argb(255, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(255, 50, 50, 50);
        this.mMargin = 4;
        init(context, null);
    }

    public MockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(255, 0, 0, 0);
        this.mTextColor = Color.argb(255, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(255, 50, 50, 50);
        this.mMargin = 4;
        init(context, attributeSet);
    }

    public MockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPaintDiagonals = new Paint();
        this.mPaintText = new Paint();
        this.mPaintTextBackground = new Paint();
        this.mDrawDiagonals = true;
        this.mDrawLabel = true;
        this.mText = null;
        this.mTextBounds = new Rect();
        this.mDiagonalsColor = Color.argb(255, 0, 0, 0);
        this.mTextColor = Color.argb(255, 200, 200, 200);
        this.mTextBackgroundColor = Color.argb(255, 50, 50, 50);
        this.mMargin = 4;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr._2131100857_res_0x7f0604b9, R.attr._2131100858_res_0x7f0604ba, R.attr._2131100859_res_0x7f0604bb, R.attr._2131100860_res_0x7f0604bc, R.attr._2131100861_res_0x7f0604bd, R.attr._2131100862_res_0x7f0604be});
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == 1) {
                    this.mText = obtainStyledAttributes.getString(index);
                } else if (index == 4) {
                    this.mDrawDiagonals = obtainStyledAttributes.getBoolean(index, this.mDrawDiagonals);
                } else if (index == 0) {
                    this.mDiagonalsColor = obtainStyledAttributes.getColor(index, this.mDiagonalsColor);
                } else if (index == 2) {
                    this.mTextBackgroundColor = obtainStyledAttributes.getColor(index, this.mTextBackgroundColor);
                } else if (index == 3) {
                    this.mTextColor = obtainStyledAttributes.getColor(index, this.mTextColor);
                } else if (index == 5) {
                    this.mDrawLabel = obtainStyledAttributes.getBoolean(index, this.mDrawLabel);
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (this.mText == null) {
            try {
                this.mText = context.getResources().getResourceEntryName(getId());
            } catch (Exception unused) {
            }
        }
        this.mPaintDiagonals.setColor(this.mDiagonalsColor);
        this.mPaintDiagonals.setAntiAlias(true);
        this.mPaintText.setColor(this.mTextColor);
        this.mPaintText.setAntiAlias(true);
        this.mPaintTextBackground.setColor(this.mTextBackgroundColor);
        this.mMargin = Math.round(this.mMargin * (getResources().getDisplayMetrics().xdpi / 160.0f));
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if (this.mDrawDiagonals) {
            width--;
            height--;
            float f = width;
            float f2 = height;
            canvas.drawLine(0.0f, 0.0f, f, f2, this.mPaintDiagonals);
            canvas.drawLine(0.0f, f2, f, 0.0f, this.mPaintDiagonals);
            canvas.drawLine(0.0f, 0.0f, f, 0.0f, this.mPaintDiagonals);
            canvas.drawLine(f, 0.0f, f, f2, this.mPaintDiagonals);
            canvas.drawLine(f, f2, 0.0f, f2, this.mPaintDiagonals);
            canvas.drawLine(0.0f, f2, 0.0f, 0.0f, this.mPaintDiagonals);
        }
        String str = this.mText;
        if (str == null || !this.mDrawLabel) {
            return;
        }
        this.mPaintText.getTextBounds(str, 0, str.length(), this.mTextBounds);
        float width2 = (width - this.mTextBounds.width()) / 2.0f;
        float height2 = ((height - this.mTextBounds.height()) / 2.0f) + this.mTextBounds.height();
        this.mTextBounds.offset((int) width2, (int) height2);
        Rect rect = this.mTextBounds;
        rect.set(rect.left - this.mMargin, this.mTextBounds.top - this.mMargin, this.mTextBounds.right + this.mMargin, this.mTextBounds.bottom + this.mMargin);
        canvas.drawRect(this.mTextBounds, this.mPaintTextBackground);
        canvas.drawText(this.mText, width2, height2, this.mPaintText);
    }
}
