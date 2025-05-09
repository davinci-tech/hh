package com.huawei.pluginmessagecenter.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.listener.OnItemVisibleListener;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jec;
import defpackage.koq;
import defpackage.mrq;
import defpackage.nrf;
import defpackage.nrp;
import defpackage.nrr;
import defpackage.nsj;
import health.compact.a.LanguageUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class MessageDetailAdapter extends BaseAdapter {
    private Context b;
    private List<MessageObject> d;
    private OnItemVisibleListener<View, MessageObject> e;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MessageDetailAdapter(Context context, List<MessageObject> list) {
        this.d = list;
        this.b = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.d.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (i >= this.d.size() || i < 0) {
            LogUtil.a("MessageDetailAdapter", "getItem = ", Integer.valueOf(i));
            return null;
        }
        return this.d.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        d dVar;
        if (!koq.d(this.d, i)) {
            LogUtil.h("MessageDetailAdapter", "getView outOfBounds, position = ", Integer.valueOf(i));
            return null;
        }
        MessageObject messageObject = this.d.get(i);
        if (view == null) {
            LayoutInflater from = LayoutInflater.from(this.b);
            if (String.valueOf(14).equalsIgnoreCase(messageObject.getModule())) {
                view = from.inflate(R.layout.message_detail_layout, (ViewGroup) null);
                dVar = new d();
                dVar.h = (HealthTextView) view.findViewById(R.id.message_detail_title_tv);
                dVar.d = (HealthTextView) view.findViewById(R.id.message_detail_content_tv);
                dVar.e = (HealthTextView) view.findViewById(R.id.message_detail_date_tv);
                dVar.i = (ImageView) view.findViewById(R.id.iv_message_content);
                dVar.b = (ImageView) view.findViewById(R.id.iv_icon);
                dVar.c = (ImageView) view.findViewById(R.id.iv_arrow);
                view.setTag(dVar);
            } else {
                view = from.inflate(R.layout.message_detail_text_layout, (ViewGroup) null);
                dVar = new d();
                dVar.h = (HealthTextView) view.findViewById(R.id.message_detail_title_tv);
                dVar.g = (HealthTextView) view.findViewById(R.id.message_detail_text_subtitle_tv);
                dVar.d = (HealthTextView) view.findViewById(R.id.message_detail_text_content_tv);
                dVar.f8506a = (TextView) view.findViewById(R.id.message_detail_text_detail_tv);
                dVar.e = (HealthTextView) view.findViewById(R.id.message_detail_date_tv);
                dVar.b = (ImageView) view.findViewById(R.id.iv_icon);
                dVar.c = (ImageView) view.findViewById(R.id.iv_arrow);
                view.setTag(dVar);
            }
        } else {
            dVar = (d) view.getTag();
        }
        cnY_(dVar, messageObject, view, i);
        return view;
    }

    private void cnY_(d dVar, MessageObject messageObject, View view, int i) {
        OnItemVisibleListener<View, MessageObject> onItemVisibleListener;
        dVar.h.setText(messageObject.getInfoClassify());
        long createTime = String.valueOf(90).equals(messageObject.getModule()) ? messageObject.getCreateTime() : messageObject.getReceiveTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(createTime);
        dVar.e.setText(c(calendar.getTime()) + " " + nsj.c(BaseApplication.getContext(), createTime, 1));
        LogUtil.a("MessageDetailAdapter", "icon = ", Integer.valueOf(mrq.c(messageObject.getModule())));
        dVar.b.setImageDrawable(BaseApplication.getContext().getResources().getDrawable(mrq.c(messageObject.getModule())));
        if (String.valueOf(14).equalsIgnoreCase(messageObject.getModule())) {
            dVar.d.setText(messageObject.getMsgTitle());
            String imgUri = messageObject.getImgUri();
            LogUtil.a("MessageDetailAdapter", "imgUri = ", imgUri);
            if (!imgUri.equals((String) dVar.i.getTag(R.id.imageloader_uri))) {
                dVar.i.setImageResource(R.mipmap._2131821446_res_0x7f110386);
            }
            if (!TextUtils.isEmpty(imgUri)) {
                dVar.i.setTag(R.id.imageloader_uri, imgUri);
                cnX_(this.b, imgUri, dVar.i, 4);
            }
        } else {
            dVar.d.setText(messageObject.getMsgContent());
            cnZ_(dVar, null);
            dVar.g.setText(a(messageObject));
            boolean z = String.valueOf(90).equals(messageObject.getModule()) && TextUtils.isEmpty(messageObject.getDetailUri());
            if (String.valueOf(19).equals(messageObject.getModule()) && MessageConstant.VIEWING_OR_ENDED_LOCATION_MSG.equals(messageObject.getType())) {
                dVar.f8506a.setText(this.b.getResources().getString(R.string._2130845891_res_0x7f0220c3));
            }
            dVar.f8506a.setVisibility(z ? 4 : 0);
        }
        if (LanguageUtil.bc(this.b)) {
            dVar.c.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        }
        view.setTag(R.layout.message_detail_layout, Long.valueOf(System.currentTimeMillis()));
        if (messageObject.getPageType() != 43 || (onItemVisibleListener = this.e) == null) {
            return;
        }
        onItemVisibleListener.onItemVisible(view, i, messageObject);
    }

    private void cnZ_(d dVar, LinearLayout.LayoutParams layoutParams) {
        if (dVar.g.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            layoutParams = (LinearLayout.LayoutParams) dVar.g.getLayoutParams();
        }
        if (layoutParams == null) {
            LogUtil.b("MessageDetailAdapter", "layoutParams=null");
        } else if (LanguageUtil.m(this.b)) {
            layoutParams.setMarginStart(nrr.e(this.b, 8.0f));
            dVar.g.setLayoutParams(layoutParams);
        } else {
            layoutParams.setMarginStart(nrr.e(this.b, 16.0f));
            dVar.g.setLayoutParams(layoutParams);
        }
    }

    private String c(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd"));
        if (LanguageUtil.bn(com.huawei.haf.application.BaseApplication.e())) {
            return jec.g(simpleDateFormat.format(date));
        }
        return simpleDateFormat.format(date);
    }

    private void cnX_(Context context, String str, ImageView imageView, int i) {
        RequestOptions placeholder = new RequestOptions().skipMemoryCache(true).transform(new nrp(imageView.getContext().getApplicationContext(), nrr.e(context, i))).placeholder(R.drawable._2131427608_res_0x7f0b0118);
        nrf.e(placeholder, R.drawable._2131427608_res_0x7f0b0118);
        nrf.cIv_(str, placeholder, imageView);
    }

    private String a(MessageObject messageObject) {
        if (String.valueOf(30).equalsIgnoreCase(messageObject.getModule())) {
            if (String.valueOf(35).equals(messageObject.getDetailUriExt())) {
                return b(R.string._2130843739_res_0x7f02185b);
            }
            return b(R.string._2130843309_res_0x7f0216ad);
        }
        if (String.valueOf(40).equalsIgnoreCase(messageObject.getModule())) {
            return b(R.string._2130843310_res_0x7f0216ae);
        }
        if (String.valueOf(15).equalsIgnoreCase(messageObject.getModule())) {
            return b(R.string._2130843334_res_0x7f0216c6);
        }
        if (String.valueOf(17).equalsIgnoreCase(messageObject.getModule()) && messageObject.getMsgId().equals("kakaMessage")) {
            return b(R.string._2130847701_res_0x7f0227d5);
        }
        if (LanguageUtil.m(this.b)) {
            return "【" + messageObject.getMsgTitle() + "】";
        }
        return messageObject.getMsgTitle();
    }

    private String b(int i) {
        if (LanguageUtil.m(this.b)) {
            return "【" + BaseApplication.getContext().getString(i) + "】";
        }
        return BaseApplication.getContext().getString(i);
    }

    public void d(List<MessageObject> list) {
        this.d = list;
    }

    public void d(OnItemVisibleListener<View, MessageObject> onItemVisibleListener) {
        this.e = onItemVisibleListener;
    }

    static final class d {

        /* renamed from: a, reason: collision with root package name */
        TextView f8506a;
        ImageView b;
        ImageView c;
        HealthTextView d;
        HealthTextView e;
        HealthTextView g;
        HealthTextView h;
        ImageView i;

        private d() {
        }
    }
}
