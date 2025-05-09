package defpackage;

import android.text.TextUtils;
import com.huawei.achievement.MedalInfoAble;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public class uu implements MedalInfoAble {
    private static volatile uu c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, mfo> f17558a;

    private uu() {
    }

    public static uu d() {
        if (c == null) {
            synchronized (d) {
                if (c == null) {
                    c = new uu();
                }
            }
        }
        return c;
    }

    @Override // com.huawei.achievement.MedalInfoAble
    public Task<mfo> obtainMedalInfo(final String str) {
        return Tasks.callInBackground(new Callable<mfo>() { // from class: uu.2
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public mfo call() {
                if (uu.this.f17558a == null || uu.this.f17558a.size() == 0) {
                    LogUtil.h("AchieveMedalPort", "obtainMedalInfo get new medalConfigMap.");
                    uu.this.f17558a = mcv.d(BaseApplication.getContext()).f();
                }
                uu uuVar = uu.this;
                return uuVar.a(uuVar.f17558a, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public mfo a(Map<String, mfo> map, String str) {
        if (map == null || map.size() == 0) {
            LogUtil.h("AchieveMedalPort", "getMedalConfig medalConfigMap is empty.");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Map.Entry<String, mfo> entry : map.entrySet()) {
            if (str.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override // com.huawei.achievement.MedalInfoAble
    public Task<ArrayList<mfs>> obtainMedalGainStatus(List<Integer> list) {
        LogUtil.a("AchieveMedalPort", "LightTask obtainMedalGainStatus startTask");
        return mcv.d(BaseApplication.getContext()).g();
    }

    @Override // com.huawei.achievement.MedalInfoAble
    public Task<ArrayList<mfs>> obtainCurrentMedalGainStatus(List<Integer> list) {
        return Tasks.callInBackground(new Callable<ArrayList<mfs>>() { // from class: uu.5
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public ArrayList<mfs> call() {
                ArrayList<mfs> k = meh.c(BaseApplication.getContext()).k();
                if (koq.b(k)) {
                    return k;
                }
                String b = mct.b(BaseApplication.getContext(), "_achieve_gain_id_medal");
                if (TextUtils.isEmpty(b)) {
                    return k;
                }
                ArrayList<mfs> arrayList = new ArrayList<>(10);
                Iterator<mfs> it = k.iterator();
                while (it.hasNext()) {
                    mfs next = it.next();
                    if (b.equals(next.a())) {
                        arrayList.add(next);
                    }
                }
                return arrayList;
            }
        });
    }
}
