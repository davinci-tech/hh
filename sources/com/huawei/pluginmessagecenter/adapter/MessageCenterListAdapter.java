package com.huawei.pluginmessagecenter.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import defpackage.mrq;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class MessageCenterListAdapter extends BaseAdapter {

    /* renamed from: a, reason: collision with root package name */
    private List<MessageObject> f8504a;
    private Context c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MessageCenterListAdapter(Context context, List<MessageObject> list) {
        this.f8504a = list;
        this.c = context;
    }

    public void d(List<MessageObject> list) {
        this.f8504a = list;
    }

    public void c() {
        if (this.f8504a == null) {
            return;
        }
        for (int i = 0; i < this.f8504a.size(); i++) {
            if ("kakaMessage".equals(this.f8504a.get(i).getMsgId())) {
                this.f8504a.remove(i);
            }
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f8504a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i >= this.f8504a.size() || i < 0) {
            LogUtil.a("MessageCenterListAdapter", "getItem = ", Integer.valueOf(i));
            return null;
        }
        return this.f8504a.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.c).inflate(R.layout.message_center_item, (ViewGroup) null);
            aVar = new a();
            aVar.h = (HealthTextView) view.findViewById(R.id.MessageCenter_titleleft_tv);
            aVar.b = (HealthTextView) view.findViewById(R.id.MessageCenter_content_tv);
            aVar.e = (HealthTextView) view.findViewById(R.id.tv_message_date);
            aVar.f8505a = (ImageView) view.findViewById(R.id.MessageCenter_head_40iv);
            aVar.d = (HealthDivider) view.findViewById(R.id.MessageCenter_content_underline);
            aVar.j = (HealthTextView) view.findViewById(R.id.message_red_num_dot);
            aVar.c = (ImageView) view.findViewById(R.id.iv_arrow);
            view.setTag(aVar);
            aVar.f = (ImageView) view.findViewById(R.id.MessageCenter_titleright_redpoint);
        } else {
            aVar = (a) view.getTag();
        }
        MessageObject messageObject = this.f8504a.get(i);
        aVar.h.setText(messageObject.getMsgTitle());
        String msgContent = messageObject.getMsgContent();
        aVar.e.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "M/d")).format(new Date(String.valueOf(90).equals(messageObject.getModule()) ? messageObject.getCreateTime() : messageObject.getReceiveTime())));
        aVar.d.setVisibility(i == this.f8504a.size() + (-1) ? 8 : 0);
        if (TextUtils.isEmpty(msgContent)) {
            aVar.b.setVisibility(8);
        } else {
            aVar.b.setVisibility(0);
            aVar.b.setText(msgContent);
        }
        if (messageObject.getImgUri() == null) {
            aVar.f8505a.setImageDrawable(this.c.getResources().getDrawable(R.drawable._2131429904_res_0x7f0b0a10));
            return view;
        }
        a(aVar, messageObject);
        return view;
    }

    private void a(a aVar, MessageObject messageObject) {
        LogUtil.a("MessageCenterListAdapter", "drawable id:" + mrq.c(messageObject.getModule()));
        int readFlag = messageObject.getReadFlag();
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        if (messageObject.getModule().equals(String.valueOf(17)) && achieveDataApi != null && achieveDataApi.getKakaTaskRedDot(BaseApplication.getContext())) {
            LogUtil.a("MessageCenterListAdapter", "refreshView, still has unclaimedKaka, unread +1");
            readFlag++;
        }
        String module = messageObject.getModule();
        aVar.f.setVisibility(8);
        if (String.valueOf(14).equals(module) && readFlag > 0) {
            aVar.f.setVisibility(0);
        } else {
            aVar.f.setVisibility(8);
        }
        aVar.f8505a.setImageDrawable(this.c.getResources().getDrawable(mrq.c(messageObject.getModule())));
        if (!String.valueOf(14).equals(module)) {
            c(readFlag, aVar.j);
        } else {
            aVar.j.setVisibility(8);
        }
        if (LanguageUtil.bc(this.c)) {
            aVar.c.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        if (String.valueOf(17).equals(messageObject.getModule())) {
            if (messageObject.getType().equals("kakaExpiration") || readFlag == 2) {
                HashMap hashMap = new HashMap(3);
                hashMap.put("click", 1);
                hashMap.put("event", 0);
                hashMap.put("from", 1);
                ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.SUCCESSES_KAKA_1100082.value(), hashMap, 0);
            }
        }
    }

    private void c(int i, HealthTextView healthTextView) {
        LogUtil.a("MessageCenterListAdapter", "Enter setUnreadMessageNum unreadMessageNum:", Integer.valueOf(i));
        if (i <= 0) {
            healthTextView.setVisibility(8);
        } else if (i < 10) {
            healthTextView.setVisibility(0);
            if (nsn.s()) {
                healthTextView.setTextSize(1, 16.0f);
                healthTextView.setBackgroundResource(R.drawable._2131430876_res_0x7f0b0ddc);
            } else {
                healthTextView.setBackgroundResource(R.drawable._2131430875_res_0x7f0b0ddb);
            }
        } else {
            healthTextView.setBackgroundResource(R.drawable._2131430877_res_0x7f0b0ddd);
            healthTextView.setVisibility(0);
        }
        healthTextView.setText(UnitUtil.e(i, 1, 0));
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        ImageView f8505a;
        HealthTextView b;
        ImageView c;
        HealthDivider d;
        HealthTextView e;
        ImageView f;
        HealthTextView h;
        HealthTextView j;

        private a() {
        }
    }
}
