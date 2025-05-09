package defpackage;

import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.operation.utils.Constants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Comparator;
import java.util.PriorityQueue;

/* loaded from: classes4.dex */
public class gjs {

    /* renamed from: a, reason: collision with root package name */
    private PriorityQueue<ThreeCircleRemindData> f12829a = new PriorityQueue<>(3, new Comparator() { // from class: gjv
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return gjs.b((ThreeCircleRemindData) obj, (ThreeCircleRemindData) obj2);
        }
    });

    static /* synthetic */ int b(ThreeCircleRemindData threeCircleRemindData, ThreeCircleRemindData threeCircleRemindData2) {
        return threeCircleRemindData.getPriority() - threeCircleRemindData2.getPriority();
    }

    public void d(ThreeCircleRemindData threeCircleRemindData) {
        synchronized (this) {
            ReleaseLogUtil.e("R_REMIND_DATA_QUEUE", "offer data:", threeCircleRemindData.getRemindType(), threeCircleRemindData.getSubRemindType(), threeCircleRemindData.getRemindText());
            this.f12829a.offer(threeCircleRemindData);
            while (this.f12829a.size() > 1) {
                ThreeCircleRemindData poll = this.f12829a.poll();
                if (poll != null) {
                    ReleaseLogUtil.e("R_REMIND_DATA_QUEUE", "poll data:", poll.getRemindType());
                }
            }
        }
    }

    public ThreeCircleRemindData e() {
        ThreeCircleRemindData peek;
        synchronized (this) {
            peek = this.f12829a.peek();
            Object[] objArr = new Object[2];
            objArr[0] = "peek:";
            objArr[1] = peek == null ? Constants.NULL : peek.getRemindType();
            ReleaseLogUtil.e("R_REMIND_DATA_QUEUE", objArr);
        }
        return peek;
    }

    public void d() {
        synchronized (this) {
            ReleaseLogUtil.e("R_REMIND_DATA_QUEUE", "postProcess clear");
            this.f12829a.clear();
        }
    }
}
