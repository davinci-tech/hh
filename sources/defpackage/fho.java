package defpackage;

import com.huawei.health.sportservice.utils.SmoothDataParser;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes8.dex */
public class fho implements SmoothDataParser {
    private Queue<Float> e = new LinkedList();

    /* renamed from: a, reason: collision with root package name */
    private float f12516a = 0.0f;

    @Override // com.huawei.health.sportservice.utils.SmoothDataParser
    public float getAverage(float f) {
        Float poll;
        this.e.offer(Float.valueOf(f));
        if (this.e.size() > 6 && (poll = this.e.poll()) != null) {
            this.f12516a -= poll.floatValue();
        }
        float f2 = this.f12516a + f;
        this.f12516a = f2;
        return f2 / this.e.size();
    }

    @Override // com.huawei.health.sportservice.utils.SmoothDataParser
    public void clear() {
        this.e.clear();
        this.f12516a = 0.0f;
    }
}
