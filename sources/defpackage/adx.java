package defpackage;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import com.huawei.animationkit.computationalwallpaper.pattern.arranger.Arranger;
import com.huawei.animationkit.computationalwallpaper.pattern.shape.AbstractShape;

/* loaded from: classes8.dex */
public class adx extends AbstractShape {
    private Arranger b;

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.shape.AbstractShape, com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape
    public void setLocation(Rect rect) {
        super.setLocation(rect);
        this.b.setBounds(rect);
    }

    @Override // com.huawei.animationkit.computationalwallpaper.pattern.shape.Shape
    public void draw(Canvas canvas) {
        this.b.arrange(canvas);
    }

    public void c(Arranger arranger) {
        this.b = arranger;
    }

    public void d(ColorResult colorResult) {
        this.b.setColor(colorResult);
    }

    public void d(String str) {
        this.b.setResource(str);
    }
}
