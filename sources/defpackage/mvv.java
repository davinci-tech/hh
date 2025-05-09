package defpackage;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes6.dex */
public class mvv implements ShareDataHandlerInterface {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<Integer, fee> f15210a = new ConcurrentHashMap<>();

    mvv() {
        d();
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void updateData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fee)) {
            return;
        }
        if (mvqVar.e() == null) {
            mvqVar.a(new ArrayList());
        }
        if (!mvqVar.e().contains(Integer.valueOf(shareDataInfo.getId()))) {
            mvqVar.e().add(Integer.valueOf(shareDataInfo.getId()));
        }
        this.f15210a.put(Integer.valueOf(shareDataInfo.getId()), (fee) shareDataInfo);
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void deleteData(mvq mvqVar, ShareDataInfo shareDataInfo) {
        if (mvqVar == null || !(shareDataInfo instanceof fee)) {
            return;
        }
        mvqVar.e().remove(Integer.valueOf(shareDataInfo.getId()));
        if (StringUtils.g(shareDataInfo.getSportTypes())) {
            this.f15210a.remove(Integer.valueOf(shareDataInfo.getId()));
        } else {
            this.f15210a.put(Integer.valueOf(shareDataInfo.getId()), (fee) shareDataInfo);
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public ShareDataInfo getShareDataById(int i) {
        if (this.f15210a.isEmpty()) {
            LogUtil.b("StickerHandler", "sticker map do not load.");
            return null;
        }
        return this.f15210a.get(Integer.valueOf(i));
    }

    private void d() {
        if (this.f15210a.isEmpty()) {
            String c = mwa.c(mus.f);
            if (StringUtils.g(c)) {
                LogUtil.b("StickerHandler", "sticker config is empty.");
                this.f15210a = new ConcurrentHashMap<>();
            } else {
                this.f15210a = (ConcurrentHashMap) new GsonBuilder().create().fromJson(CommonUtil.z(c), new TypeToken<ConcurrentHashMap<Integer, fee>>() { // from class: mvv.4
                }.getType());
            }
        }
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public void writeJson() {
        ConcurrentHashMap<Integer, fee> concurrentHashMap = this.f15210a;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        LogUtil.a("StickerHandler", "write sticker config json result:", Boolean.valueOf(mwa.b(mus.f, new GsonBuilder().create().toJson(this.f15210a))));
    }

    @Override // com.huawei.pluginsocialshare.shareconfig.handler.ShareDataHandlerInterface
    public List<ShareDataInfo> getShareDataByIdList(List<Integer> list) {
        fee feeVar;
        if (CollectionUtils.d(list) || CollectionUtils.e(this.f15210a)) {
            LogUtil.h("StickerHandler", "getShareDataByIdList idList or mStickerInfoMap is empty.");
            return Collections.emptyList();
        }
        ArrayList<Integer> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList(list.size());
        for (Integer num : arrayList) {
            if (num != null && (feeVar = this.f15210a.get(num)) != null) {
                arrayList2.add(feeVar);
            }
        }
        return arrayList2;
    }
}
