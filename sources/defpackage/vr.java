package defpackage;

import com.huawei.ads.adsrec.IUtilCallback;
import com.huawei.ads.adsrec.l;
import com.huawei.ads.adsrec.o0;
import com.huawei.ads.adsrec.rank.sortation.IModelScoreData;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes2.dex */
public class vr extends l {
    private vt h;
    IUtilCallback i;
    public static final String[] b = {Constants.RELATION_COL_A_KEY, "ad_score", "ad_intent_score", "ad_merge_score"};
    public static final String[] d = {"dsp_id"};
    public static final String[] c = {"interaction_type", "industry_id_1st", "media_type", "ad_type"};
    public static final String[] e = {"req_intent_id_1st", "day_intent_id_1st"};

    class b implements Comparator<vb> {
        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(vb vbVar, vb vbVar2) {
            return o0.a(vbVar, vbVar2, (vbVar.y().booleanValue() && vbVar2.y().booleanValue()) ? o0.a(vbVar2.p(), vbVar.p()) : o0.a(vbVar, vbVar2));
        }

        b() {
        }
    }

    @Override // com.huawei.ads.adsrec.h0
    public void a() {
        if (this.i == null || ListUtil.isEmpty(this.f1678a) || this.f1678a.size() == 1) {
            return;
        }
        IModelScoreData e2 = this.h.e();
        if (e2 != null) {
            for (vb vbVar : this.f1678a) {
                e2.setModelScore(vbVar);
                vbVar.f();
                vbVar.p();
            }
        } else {
            HiAdLog.w("ModelSortation", "modelScoreData is still null!");
        }
        Collections.sort(this.f1678a, new b());
        if (HiAdLog.isDebugEnable()) {
            for (vb vbVar2 : this.f1678a) {
                vbVar2.f();
                vbVar2.p();
            }
        }
    }

    public vr(List<vb> list, vt vtVar) {
        super(list);
        this.i = uw.d();
        this.h = vtVar;
    }
}
