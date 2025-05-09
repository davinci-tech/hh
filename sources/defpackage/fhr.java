package defpackage;

import com.huawei.health.sportservice.utils.SmoothDataParser;
import java.util.LinkedList;
import java.util.Queue;

/* loaded from: classes8.dex */
public class fhr implements SmoothDataParser {
    private Queue<Integer> b = new LinkedList();
    private int d = 0;

    @Override // com.huawei.health.sportservice.utils.SmoothDataParser
    public float getAverage(float f) {
        Integer poll;
        this.b.offer(Integer.valueOf((int) f));
        if (this.b.size() > 6 && (poll = this.b.poll()) != null) {
            this.d -= poll.intValue();
        }
        this.d = (int) (this.d + f);
        return Math.round(r3 / this.b.size());
    }

    @Override // com.huawei.health.sportservice.utils.SmoothDataParser
    public void clear() {
        this.b.clear();
        this.d = 0;
    }
}
