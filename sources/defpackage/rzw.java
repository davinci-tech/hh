package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MedalConfigInfo;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.ui.main.R$string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class rzw implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private boolean f16980a;
    private final List<String> c;
    private final IBaseResponseCallback d;

    public rzw(List<String> list, IBaseResponseCallback iBaseResponseCallback) {
        this.c = list;
        this.d = iBaseResponseCallback;
    }

    public void d(boolean z) {
        this.f16980a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        List<String> b = b(this.c);
        if (koq.b(b)) {
            LogUtil.h("GenerateMedalMessageRunnable", "updateMessageCenter isShownAllMedals, return!");
            this.d.d(0, true);
            return;
        }
        LogUtil.a("GenerateMedalMessageRunnable", "updateMessageCenter unShownMedalIds: ", b.toString());
        List<mcz> b2 = meh.c(BaseApplication.getContext()).b(9, new HashMap(0));
        if (b2 == null) {
            this.d.d(0, true);
            return;
        }
        ArrayList<String> l = meh.c(BaseApplication.getContext()).l();
        for (mcz mczVar : b2) {
            Iterator<String> it = b.iterator();
            while (it.hasNext()) {
                e(it.next(), mczVar, l);
            }
        }
        this.d.d(0, true);
    }

    private List<String> b(List<String> list) {
        String b = mct.b(BaseApplication.getContext(), "medal_release_message_shown_list");
        if (TextUtils.isEmpty(b.trim())) {
            LogUtil.h("GenerateMedalMessageRunnable", "updateMessageCenter medalSp is isEmpty!");
            return list;
        }
        LogUtil.h("GenerateMedalMessageRunnable", "updateMessageCenter medalSp ", b);
        List asList = Arrays.asList(b.split(","));
        if (koq.b(asList)) {
            LogUtil.h("GenerateMedalMessageRunnable", "updateMessageCenter medalSpList isEmpty!");
            return list;
        }
        ArrayList arrayList = new ArrayList(16);
        for (String str : list) {
            if (!asList.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void e(String str, mcz mczVar, ArrayList<String> arrayList) {
        if (this.f16980a) {
            LogUtil.h("GenerateMedalMessageRunnable", "checkNewMedal fragment is destroyed, need stop ");
            return;
        }
        if (mczVar instanceof MedalConfigInfo) {
            MedalConfigInfo medalConfigInfo = (MedalConfigInfo) mczVar;
            if (arrayList.contains(medalConfigInfo.acquireMedalID())) {
                LogUtil.h("GenerateMedalMessageRunnable", "checkNewMedal medalConfigInfos is lighted!");
                return;
            }
            if (!mlb.b(str, medalConfigInfo.getClientTypes(), medalConfigInfo.getPhoneTypes(), 0)) {
                LogUtil.h("GenerateMedalMessageRunnable", "checkNewMedal PhoneType is not match! medalId ", str, " ClientTypes ", medalConfigInfo.getClientTypes(), " PhoneTypes ", medalConfigInfo.getPhoneTypes());
                return;
            }
            if (str.equals(medalConfigInfo.acquireMedalID())) {
                String acquireEndTime = medalConfigInfo.acquireEndTime();
                long currentTimeMillis = System.currentTimeMillis();
                if (TextUtils.isEmpty(acquireEndTime)) {
                    LogUtil.a("GenerateMedalMessageRunnable", "medal endTime is null.");
                    return;
                }
                try {
                    currentTimeMillis = Long.parseLong(acquireEndTime);
                } catch (NumberFormatException unused) {
                    LogUtil.b("GenerateMedalMessageRunnable", "NumberFormatException");
                }
                if (currentTimeMillis > System.currentTimeMillis()) {
                    a(medalConfigInfo);
                }
            }
        }
    }

    private void a(MedalConfigInfo medalConfigInfo) {
        if (this.f16980a) {
            LogUtil.h("GenerateMedalMessageRunnable", "saveMedalMessage fragment is destroyed, need stop ");
            return;
        }
        String b = mct.b(BaseApplication.getContext(), "activity_medal_message_shown_list");
        String acquireMedalName = medalConfigInfo.acquireMedalName();
        int acquireActivityId = medalConfigInfo.acquireActivityId();
        if (acquireActivityId != 0 && b.contains(String.valueOf(acquireActivityId))) {
            LogUtil.h("GenerateMedalMessageRunnable", "saveMedalMessage ", Integer.valueOf(acquireActivityId), acquireMedalName, " has shown!", b);
            return;
        }
        String d = d(acquireActivityId, acquireMedalName);
        Context context = BaseApplication.getContext();
        MessageObject e = meh.c(context).e(medalConfigInfo);
        e.setType(MessageConstant.NEW_MEDAL_TYPE);
        e.setMsgTitle(context.getString(R$string.IDS_hw_messagecenter_medal_release_notification));
        LogUtil.a("GenerateMedalMessageRunnable", "saveMedalMessage is showing msgName =", d, " medalId = ", medalConfigInfo.acquireMedalID(), " activityId = ", Integer.valueOf(acquireActivityId));
        e.setMsgContent(nsf.b(R$string.IDS_hw_messagecenter_medal_release_notification_content, d));
        e.setMetaData(context.getString(R$string.IDS_hw_messagecenter_medal_release_notification));
        e.setReadFlag(0);
        if (mcv.d(context).getAdapter() != null) {
            LogUtil.a("GenerateMedalMessageRunnable", "generate Medal Message");
            mcv.d(context).getAdapter().generateMessage(e);
            b(medalConfigInfo.acquireMedalID(), acquireActivityId, b);
        }
    }

    private String d(int i, String str) {
        if (i == 0) {
            return str;
        }
        for (String str2 : mct.b(BaseApplication.getContext(), "activity_medal_id_shown_name").split(",")) {
            if (str2.contains(String.valueOf(i))) {
                String[] split = str2.split("_");
                if (split.length > 1) {
                    return split[1];
                }
            }
        }
        return str;
    }

    private void b(String str, int i, String str2) {
        mct.b(BaseApplication.getContext(), "medal_release_message_shown_list", mct.b(BaseApplication.getContext(), "medal_release_message_shown_list") + "," + str);
        if (i != 0) {
            mct.b(BaseApplication.getContext(), "activity_medal_message_shown_list", str2 + "," + i);
        }
    }
}
