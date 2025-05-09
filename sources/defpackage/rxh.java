package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.inter.data.INativeAd;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.CommonUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class rxh {
    private static int d = 41;

    public static int b(int i) {
        switch (i) {
            case 20002:
            case 20003:
            case 20005:
            case 20006:
                return 20002;
            case 20004:
            default:
                return i;
        }
    }

    public static void e(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(context.getApplicationContext(), str, hashMap, 0);
    }

    public static void c(List<MessageObject> list, List<MessageObject> list2) {
        if (list == null) {
            return;
        }
        if ((list.size() > 0 ? list.get(0) : null) instanceof pgq) {
            d(list, list2);
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < list.size(); i++) {
            MessageObject messageObject = list.get(i);
            if (messageObject != null && messageObject.getImgUri() != null && !messageObject.getImgUri().trim().isEmpty() && messageObject.getDetailUri() != null && !messageObject.getDetailUri().trim().isEmpty()) {
                arrayList.add(messageObject);
            }
        }
        Collections.sort(arrayList, new c());
        Collections.sort(arrayList, new e());
        list2.clear();
        if (arrayList.size() > 3) {
            list2.add((MessageObject) arrayList.get(0));
            list2.add((MessageObject) arrayList.get(1));
            list2.add((MessageObject) arrayList.get(2));
            return;
        }
        list2.addAll(arrayList);
    }

    private static void d(List<MessageObject> list, List<MessageObject> list2) {
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        for (int i = 0; i < list.size(); i++) {
            MessageObject messageObject = list.get(i);
            if (messageObject != null && !sdl.d(messageObject.getImgUri())) {
                pgq pgqVar = (pgq) messageObject;
                if (!pgqVar.b()) {
                    if (!sdl.d(messageObject.getDetailUri())) {
                        arrayList.add(messageObject);
                    }
                } else {
                    String p = pgqVar.d().p();
                    if (TextUtils.equals(p, String.valueOf(0)) || TextUtils.equals(p, String.valueOf(1))) {
                        arrayList2.add(messageObject);
                    }
                }
            }
        }
        c cVar = new c();
        Collections.sort(arrayList, cVar);
        e eVar = new e();
        Collections.sort(arrayList, eVar);
        list2.clear();
        if (arrayList.size() > 3) {
            for (int i2 = 0; i2 < 3; i2++) {
                list2.add((MessageObject) arrayList.get(i2));
            }
        } else {
            list2.addAll(arrayList);
        }
        d(arrayList2, list2, cVar, eVar);
    }

    private static void d(List<MessageObject> list, List<MessageObject> list2, Comparator<MessageObject> comparator, Comparator<MessageObject> comparator2) {
        int i;
        if (koq.b(list)) {
            return;
        }
        Collections.sort(list, new b());
        Collections.sort(list, new a());
        if (koq.c(list)) {
            MessageObject messageObject = list.get(0);
            list2.add(messageObject);
            Collections.sort(list2, comparator);
            Collections.sort(list2, comparator2);
            i = list2.indexOf(messageObject);
            list2.remove(messageObject);
        } else {
            i = -1;
        }
        if (i <= -1) {
            i = list2.size();
        }
        if (list.size() <= 5) {
            list2.addAll(i, list);
            return;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            list2.add(i + i2, list.get(i2));
        }
    }

    public static int[] e(List<MessageObject> list) {
        if (koq.b(list)) {
            LogUtil.h("UIDV_SocialInteractor", "getImageScaleSize() messageList is empty.");
            return new int[0];
        }
        String str = "";
        for (MessageObject messageObject : list) {
            if (messageObject == null) {
                LogUtil.h("UIDV_SocialInteractor", "getImageScaleSize() messageObject is null.");
            } else if (TextUtils.isEmpty(messageObject.getHarmonyImgUrl())) {
                LogUtil.h("UIDV_SocialInteractor", "getImageScaleSize() harmonyImgUrl is empty.");
            } else {
                String harmonyImageSize = messageObject.getHarmonyImageSize();
                if (TextUtils.isEmpty(harmonyImageSize)) {
                    LogUtil.h("UIDV_SocialInteractor", "getImageScaleSize() harmonyImageSize is empty.");
                } else {
                    str = harmonyImageSize.trim();
                    if ("4:5".equals(str)) {
                        break;
                    }
                }
            }
        }
        return c(str);
    }

    public static boolean e(int[] iArr) {
        return iArr != null && iArr.length >= 2 && iArr[0] > 0;
    }

    private static int[] c(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(":")) {
            LogUtil.h("UIDV_SocialInteractor", "getImageScaleSize() imageSizeString is empty or not contain .");
            return new int[0];
        }
        LogUtil.a("UIDV_SocialInteractor", "getImageScaleSize imageSizeString = ", str);
        String[] split = str.split(":");
        if (split.length < 2) {
            return new int[0];
        }
        int[] iArr = {CommonUtil.b(BaseApplication.getContext(), split[0], 21), CommonUtil.b(BaseApplication.getContext(), split[1], 9)};
        LogUtil.a("UIDV_SocialInteractor", "getImageScaleSize widthScaleSize = ", Integer.valueOf(iArr[0]), ", heightScaleSize = ", Integer.valueOf(iArr[1]));
        return iArr;
    }

    public static int e(Context context) {
        int h = (int) ((nsn.h(context) - BaseApplication.getContext().getResources().getDimension(R.dimen._2131362366_res_0x7f0a023e)) - BaseApplication.getContext().getResources().getDimension(R.dimen._2131362365_res_0x7f0a023d));
        if (nsn.ag(context) && !nsn.aa(context)) {
            int b2 = (h - nrr.b(BaseApplication.getContext())) / 2;
            LogUtil.h("UIDV_SocialInteractor", "getBannerImageWidth() width = ", Integer.valueOf(b2));
            return b2;
        }
        int i = d;
        boolean z = i == 37 || i == 38;
        Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
        if (z) {
            return (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131365210_res_0x7f0a0d5a);
        }
        return (h - ((Integer) safeRegionWidth.first).intValue()) - ((Integer) safeRegionWidth.second).intValue();
    }

    public static class e implements Comparator<MessageObject>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObject messageObject, MessageObject messageObject2) {
            return messageObject2.getWeight() - messageObject.getWeight();
        }
    }

    public static class c implements Comparator<MessageObject>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObject messageObject, MessageObject messageObject2) {
            return Long.compare(messageObject2.getCreateTime(), messageObject.getCreateTime());
        }
    }

    public static class d implements Comparator<INativeAd>, Serializable {
        private static final long serialVersionUID = -6580311201672862038L;

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(INativeAd iNativeAd, INativeAd iNativeAd2) {
            if (iNativeAd == null || iNativeAd2 == null) {
                return 1;
            }
            return Long.compare(iNativeAd2.getStartTime(), iNativeAd.getStartTime());
        }
    }

    public static class a implements Comparator<MessageObject>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObject messageObject, MessageObject messageObject2) {
            boolean z = messageObject2 instanceof pgq;
            if (!(messageObject instanceof pgq) || !z) {
                return 0;
            }
            pgq pgqVar = (pgq) messageObject;
            pgq pgqVar2 = (pgq) messageObject2;
            if (!pgqVar.b() || !pgqVar2.b()) {
                return 0;
            }
            return pgqVar2.d().w() - pgqVar.d().w();
        }
    }

    public static class b implements Comparator<MessageObject>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public int compare(MessageObject messageObject, MessageObject messageObject2) {
            boolean z = messageObject2 instanceof pgq;
            if (!(messageObject instanceof pgq) || !z) {
                return 0;
            }
            pgq pgqVar = (pgq) messageObject;
            pgq pgqVar2 = (pgq) messageObject2;
            if (!pgqVar.b() || !pgqVar2.b()) {
                return 0;
            }
            return pgqVar2.d().m() - pgqVar.d().m();
        }
    }

    public static List<MessageObject> d(List<MessageObject> list) {
        if (koq.b(list)) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<MessageObject> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        return arrayList;
    }

    public static MessageObject a(MessageObject messageObject) {
        if (messageObject == null) {
            return messageObject;
        }
        pgq pgqVar = new pgq();
        pgqVar.setCreateTime(messageObject.getCreateTime());
        pgqVar.setDetailUri(messageObject.getDetailUri());
        pgqVar.setDetailUriExt(messageObject.getDetailUriExt());
        pgqVar.setExpireTime(messageObject.getExpireTime());
        pgqVar.setFlag(messageObject.getFlag());
        pgqVar.setHarmonyImageSize(messageObject.getHarmonyImageSize());
        pgqVar.setHarmonyImgUrl(messageObject.getHarmonyImgUrl());
        pgqVar.setHeatMapCity(messageObject.getHeatMapCity());
        pgqVar.setHuid(messageObject.getHuid());
        pgqVar.setImageTextSeparateSwitch(messageObject.getImageTextSeparateSwitch());
        pgqVar.setImei(messageObject.getImei());
        pgqVar.setImgBigUri(messageObject.getImgBigUri());
        pgqVar.setImgUri(messageObject.getImgUri());
        pgqVar.setInfoClassify(messageObject.getInfoClassify());
        pgqVar.setInfoRecommend(messageObject.getInfoRecommend());
        pgqVar.setLatestGetMsgTimestamp(messageObject.getLatestGetMsgTimestamp());
        pgqVar.setLayout(messageObject.getLayout());
        pgqVar.setMessageExtList(messageObject.getMessageExtList());
        pgqVar.setMetadata(messageObject.getMetadata());
        pgqVar.setModule(messageObject.getModule());
        pgqVar.setMsgContent(messageObject.getMsgContent());
        pgqVar.setMsgFrom(messageObject.getMsgFrom());
        pgqVar.setMsgId(messageObject.getMsgId());
        pgqVar.setMsgPosition(messageObject.getMsgPosition());
        pgqVar.setMsgTitle(messageObject.getMsgTitle());
        pgqVar.setMsgType(messageObject.getMsgType());
        pgqVar.setMsgUserLabel(messageObject.getMsgUserLabel());
        pgqVar.setNativeAd(messageObject.getNativeAd());
        pgqVar.setNotified(messageObject.getNotified());
        pgqVar.setPageType(messageObject.getPageType());
        pgqVar.setPosition(messageObject.getPosition());
        pgqVar.setReadFlag(messageObject.getReadFlag());
        pgqVar.setReceiveTime(messageObject.getReceiveTime());
        pgqVar.setType(messageObject.getType());
        pgqVar.setWeight(messageObject.getWeight());
        return pgqVar;
    }

    public static void e(int i) {
        d = i;
    }

    public static int[] c(List<MessageObject> list) {
        if (koq.b(list)) {
            return new int[0];
        }
        int[] iArr = new int[list.size()];
        int i = 0;
        for (MessageObject messageObject : list) {
            if (messageObject instanceof pgq) {
                pgq pgqVar = (pgq) messageObject;
                if (pgqVar.b()) {
                    int b2 = b(pgqVar.d().ag());
                    if (sdl.e(iArr, b2) <= -1) {
                        iArr[i] = b2;
                        i++;
                    }
                }
            }
        }
        if (i <= 0) {
            return new int[0];
        }
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i);
        return iArr2;
    }
}
