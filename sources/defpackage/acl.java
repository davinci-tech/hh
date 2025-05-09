package defpackage;

import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode;
import com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/* loaded from: classes8.dex */
public class acl implements ColorPipelineNode {
    private final Random b = new Random();

    @Override // com.huawei.animationkit.computationalwallpaper.coloranalysis.ColorPipelineNode
    public void process(ColorResult colorResult) {
        a(colorResult);
        b(colorResult);
        Log.i("ColorMatcher", "additional color: " + colorResult.getAdditionalColors());
        Log.i("ColorMatcher", "gradient: " + colorResult.getMainGradients() + "additional gradient: " + colorResult.getAdditionalGradients());
    }

    private void a(ColorResult colorResult) {
        if (colorResult.getMainColors().size() >= 3) {
            colorResult.setAdditionalColors(Collections.emptyList());
            return;
        }
        acf acfVar = colorResult.getMainColors().get(0);
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i <= 3 - colorResult.getMainColors().size(); i++) {
            if (acfVar.b() > 0.7f) {
                arrayList.add(Integer.valueOf(i + 6));
            } else if (acfVar.b() < 0.4d) {
                arrayList.add(Integer.valueOf(6 - i));
            } else {
                arrayList.add(Integer.valueOf(6 - i));
                arrayList.add(Integer.valueOf(i + 6));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < 3 - colorResult.getMainColors().size(); i2++) {
            arrayList2.add(aci.b(acfVar, ((Integer) arrayList.get(i2)).intValue()));
        }
        colorResult.setAdditionalColors(arrayList2);
    }

    private void b(ColorResult colorResult) {
        this.b.setSeed(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        arrayList.add(b(colorResult.getMainColors().get(0)));
        for (int i = 1; i < colorResult.getMainColors().size(); i++) {
            arrayList.add(e(colorResult.getMainColors().get(i)));
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<acf> it = colorResult.getAdditionalColors().iterator();
        while (it.hasNext()) {
            arrayList2.add(e(it.next()));
        }
        colorResult.setMainGradients(arrayList);
        colorResult.setAdditionalGradients(arrayList2);
    }

    private acf b(acf acfVar) {
        return new acf(Math.max(acfVar.e() - ((int) ((this.b.nextFloat() * 30.0f) + 30.0f)), 0.0f), acfVar.d(), acfVar.b(), acfVar.a());
    }

    private acf e(acf acfVar) {
        return aci.b(acfVar, 4);
    }
}
