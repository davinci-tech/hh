package defpackage;

import android.text.TextUtils;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.common.util.CollectionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes6.dex */
class ojz {

    /* renamed from: a, reason: collision with root package name */
    private static volatile ojz f15747a;
    private int b;
    private int d;
    private String e;

    ojz() {
    }

    public static ojz e() {
        if (f15747a == null) {
            synchronized (ojz.class) {
                if (f15747a == null) {
                    f15747a = new ojz();
                }
            }
        }
        return f15747a;
    }

    public void a() {
        final int i = this.d + 1;
        LogUtil.a("AudioRequestHelper", "updateWorkoutsByTopicId. sTopicId = ", this.e, "; updatePageNum = ", Integer.valueOf(i), "; mTotalSize = ", Integer.valueOf(this.b));
        if (i * 20 >= this.b) {
            LogUtil.a("AudioRequestHelper", "finish update this topic playlist. no need to update.");
        } else {
            ojw.c(this.e, new BiConsumer<List<omb>, Integer>() { // from class: ojz.3
                @Override // com.google.android.gms.common.util.BiConsumer
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void accept(List<omb> list, Integer num) {
                    if (CollectionUtils.isEmpty(list)) {
                        LogUtil.h("AudioRequestHelper", "audioWorkoutByTopicList is empty, no need to update");
                        return;
                    }
                    List<oly> d = ojw.d(list);
                    LogUtil.a("AudioRequestHelper", "requestWorkoutsByTopicId result updatePageNum = ", Integer.valueOf(i), ", result size = ", Integer.valueOf(d.size()));
                    List<enq> c = ojw.c(d);
                    if (!CollectionUtils.isEmpty(c) && !CollectionUtils.isEmpty(d)) {
                        ojz.this.d = i;
                        ojz.this.b = num.intValue();
                    }
                    oli.a().a(c, d);
                }
            }, i);
        }
    }

    public void a(final String str, final BiConsumer<List<enq>, List<oly>> biConsumer, final int i) {
        ojw.c(str, new BiConsumer<List<omb>, Integer>() { // from class: ojz.2
            @Override // com.google.android.gms.common.util.BiConsumer
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void accept(List<omb> list, Integer num) {
                if (CollectionUtils.isEmpty(list)) {
                    biConsumer.accept(null, null);
                    return;
                }
                List<oly> d = ojw.d(list);
                LogUtil.a("AudioRequestHelper", "requestWorkoutsByTopicId result pageNum = ", Integer.valueOf(i), ", result size = ", Integer.valueOf(d.size()), "; totalSize = ", num);
                List<enq> c = ojw.c(d);
                if (!CollectionUtils.isEmpty(c) && !CollectionUtils.isEmpty(d)) {
                    ojz.this.d = i;
                    ojz.this.e = str;
                    ojz.this.b = num.intValue();
                }
                biConsumer.accept(c, d);
            }
        }, i);
    }

    public boolean c(int i, String str) {
        if (!TextUtils.equals(str, this.e)) {
            LogUtil.a("AudioRequestHelper", "topicId is not same. input topicId = ", str, "; sTopicId = ", this.e);
            return true;
        }
        int size = oli.a().t().size();
        LogUtil.a("AudioRequestHelper", "pageNum = ", Integer.valueOf(i), "; sTopicPageNum = ", Integer.valueOf(this.d), "; topicId = ", str, "; sTopicId = ", this.e, "; PlayList.size() = ", Integer.valueOf(size));
        return this.d <= 0 || this.b > size;
    }
}
