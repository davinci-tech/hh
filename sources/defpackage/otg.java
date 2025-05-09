package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.data.msgcontent.NotificationMsgContent;
import com.huawei.ui.homehealth.smartcard.SmartCardViewHolder;
import com.huawei.ui.main.stories.smartcenter.activity.SmartMsgSkipActivity;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class otg extends AbstractBaseCardData implements View.OnClickListener {
    private static int e;
    private ExecutorService b;
    private Context c;
    private Handler h;
    private MessageCenterApi i;
    private nqc l;
    private SmartCardViewHolder n;
    private int k = 0;
    private int g = 0;
    private int o = 0;
    private String j = "";
    private String f = "";

    /* renamed from: a, reason: collision with root package name */
    private String f15944a = "";
    private long d = 0;

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        refreshCardData();
        ReleaseLogUtil.e("SmartCardData", "main card onResume finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.a("SmartCardData", "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.hw_health_smart_card_more && SystemClock.elapsedRealtime() - this.d > 1000) {
            this.d = SystemClock.elapsedRealtime();
            try {
                nqc nqcVar = new nqc(this.c, this.n.e);
                this.l = nqcVar;
                nqcVar.cEh_(view, 0);
            } catch (IllegalStateException e2) {
                LogUtil.b("SmartCardData", "popView exception", e2.getMessage());
            }
        } else if (view.getId() == R.id.hw_health_smart_card_pop_text1) {
            HashMap hashMap = new HashMap();
            hashMap.put("click", "1");
            hashMap.put("type", Integer.valueOf(this.k));
            hashMap.put("title", this.f);
            ixx.d().d(this.c, AnalyticsValue.HEALTH_HOME_SMART_CARD_MORE_IGNORE_CLICK_2010055.value(), hashMap, 0);
            this.l.b();
            ContentValues contentValues = new ContentValues();
            contentValues.put("status", (Integer) 2);
            kvs.b(this.c).bSl_(this.o, contentValues);
            SmartCardViewHolder smartCardViewHolder = this.n;
            if (smartCardViewHolder != null) {
                smartCardViewHolder.c.setVisibility(8);
                SharedPreferenceManager.e(this.c, Integer.toString(10000), "health_show_smartcard", "false", new StorageParams());
            }
            if (this.k == 100000) {
                Context context = this.c;
                String num = Integer.toString(20009);
                long currentTimeMillis = System.currentTimeMillis();
                SharedPreferenceManager.e(context, num, "last_partiactivity_disappear_time", String.valueOf(currentTimeMillis), new StorageParams());
            }
            this.i = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            b(this.j, this.g);
        } else if (view.getId() == R.id.hw_health_smart_card_layout && SystemClock.elapsedRealtime() - this.d > 1000) {
            LogUtil.c("SmartCardData", "SmartCard_mSmartMsgKeyId1", Integer.valueOf(this.o));
            b();
        } else {
            LogUtil.h("SmartCardData", "onClick else");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b() {
        this.d = SystemClock.elapsedRealtime();
        c();
        Intent intent = new Intent();
        intent.putExtra("id", this.o);
        intent.putExtra("msgType", this.k);
        intent.putExtra("msgContent", this.j);
        intent.putExtra(CommonUtil.MSG_TITLE, this.f);
        intent.putExtra("from", 0);
        intent.setClass(this.c, SmartMsgSkipActivity.class);
        this.c.startActivity(intent);
        if (this.k == 100000) {
            Context context = this.c;
            String num = Integer.toString(20009);
            long currentTimeMillis = System.currentTimeMillis();
            SharedPreferenceManager.e(context, num, "last_partiactivity_disappear_time", String.valueOf(currentTimeMillis), new StorageParams());
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            LogUtil.h("SmartCardData", "getCardViewHolder layoutInflater is null");
            return this.n;
        }
        SmartCardViewHolder smartCardViewHolder = new SmartCardViewHolder(layoutInflater.inflate(R.layout.home_item_layout_smart, viewGroup, false), this.c, false);
        this.n = smartCardViewHolder;
        smartCardViewHolder.d.setOnClickListener(this);
        this.n.h.setOnClickListener(this);
        this.n.c.setOnClickListener(this);
        refreshCardData();
        return this.n;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
        if (Utils.o()) {
            this.h.sendEmptyMessage(1);
            return;
        }
        String b = SharedPreferenceManager.b(this.c, Integer.toString(10000), "health_show_smartcard");
        LogUtil.a("SmartCardData", "showSmartCard = ", b);
        if ("false".equals(b)) {
            return;
        }
        String b2 = SharedPreferenceManager.b(this.c, Integer.toString(10000), "health_msg_switch_smartcard");
        LogUtil.a("SmartCardData", "querySmartMsg smartcardRecommend = ", b2);
        if ("0".equals(b2)) {
            this.h.sendEmptyMessage(1);
        } else {
            kvt.e(this.c).a(0, this.o, new IBaseResponseCallback() { // from class: otg.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("SmartCardData", "querySmartMsg onResponse");
                    if (obj instanceof List) {
                        otg.this.e((List) obj);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<SmartMsgDbObject> list) {
        if (list != null && list.size() > 0) {
            Message obtainMessage = this.h.obtainMessage(0);
            obtainMessage.obj = list.get(0);
            if (obtainMessage.obj != null) {
                boolean sendMessageAtFrontOfQueue = this.h.sendMessageAtFrontOfQueue(obtainMessage);
                LogUtil.a("SmartCardData", "querySmartMsgData result1 = ", Boolean.valueOf(sendMessageAtFrontOfQueue));
                if (!sendMessageAtFrontOfQueue) {
                    this.h.sendMessage(obtainMessage);
                }
                LogUtil.c("SmartCardData", "MSG_SHOW_SMART_CARD_getSmartMsgData", Integer.valueOf(list.get(0).getMsgType()));
                return;
            }
            return;
        }
        this.h.sendEmptyMessage(1);
    }

    public static void d(int i) {
        e = i;
    }

    private void b(final String str, int i) {
        if (i != 5) {
            return;
        }
        LogUtil.a("SmartCardData", "MsgCenter set expired");
        if (this.b == null) {
            this.b = Executors.newSingleThreadExecutor();
        }
        ExecutorService executorService = this.b;
        if (executorService != null) {
            executorService.execute(new Runnable() { // from class: otg.5
                @Override // java.lang.Runnable
                public void run() {
                    NotificationMsgContent notificationMsgContent = (NotificationMsgContent) HiJsonUtil.e(str, NotificationMsgContent.class);
                    if (notificationMsgContent == null) {
                        return;
                    }
                    otg.this.i.setMessageRead(notificationMsgContent.getNotificationId());
                }
            });
        }
    }

    private void c() {
        int i;
        if (this.g == 4) {
            try {
                i = Integer.parseInt(SharedPreferenceManager.b(this.c, Integer.toString(10000), "health_information_click_count"));
            } catch (NumberFormatException e2) {
                LogUtil.b("SmartCardData", "parseInt Exception", e2.getMessage());
                i = 0;
            }
            LogUtil.a("SmartCardData", "StepsCardData_refreshCardData = ", Integer.valueOf(i));
            SharedPreferenceManager.e(this.c, Integer.toString(10000), "health_information_click_count", Integer.toString(i + 1), new StorageParams());
            Context context = this.c;
            String num = Integer.toString(10000);
            long currentTimeMillis = System.currentTimeMillis();
            SharedPreferenceManager.e(context, num, "health_information_click_time", String.valueOf(currentTimeMillis), new StorageParams());
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "SmartCardData";
    }
}
