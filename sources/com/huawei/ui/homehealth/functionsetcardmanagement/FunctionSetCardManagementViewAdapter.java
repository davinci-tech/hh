package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.homehealth.homeinterface.OnStartDragListener;
import defpackage.koq;
import defpackage.ojs;
import defpackage.omz;
import defpackage.owp;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class FunctionSetCardManagementViewAdapter extends RecyclerView.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f9448a;
    private List<ojs> b;
    private NoTitleCustomAlertDialog c;
    private LayoutInflater e;
    private OnStartDragListener h;
    private List<ojs> i = new ArrayList(9);
    private List<ojs> g = new ArrayList(9);
    private b d = new b(this);

    static class b extends BaseHandler<FunctionSetCardManagementViewAdapter> {
        public b(FunctionSetCardManagementViewAdapter functionSetCardManagementViewAdapter) {
            super(functionSetCardManagementViewAdapter);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dbX_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(FunctionSetCardManagementViewAdapter functionSetCardManagementViewAdapter, Message message) {
            LogUtil.a("FunctionSetCardManagementViewAdapter", "handleMessageWhenReferenceNotNull()");
            if (functionSetCardManagementViewAdapter == null || message == null) {
                LogUtil.a("FunctionSetCardManagementViewAdapter", "handleMessageWhenReferenceNotNull obj or msg == null !");
                return;
            }
            int i = message.what;
            if (i == 0) {
                Bundle data = message.getData();
                if (data == null) {
                    ReleaseLogUtil.d("FunctionSetCardManagementViewAdapter", "handleMessageWhenReferenceNotNull SHOW_MENSTRUAL_DIALOG bundle is null");
                    return;
                } else {
                    functionSetCardManagementViewAdapter.a(data.getString("switch_string"), data.getInt("position"));
                    return;
                }
            }
            if (i == 1) {
                Bundle data2 = message.getData();
                if (data2 != null) {
                    functionSetCardManagementViewAdapter.a(data2.getInt("position"));
                    return;
                } else {
                    ReleaseLogUtil.d("FunctionSetCardManagementViewAdapter", "handleMessageWhenReferenceNotNull GET_MENSTRUAL_SWITCH_STATUS bundle is null");
                    return;
                }
            }
            LogUtil.h("FunctionSetCardManagementViewAdapter", "unknown msg");
        }
    }

    public FunctionSetCardManagementViewAdapter(List<ojs> list, Context context, OnStartDragListener onStartDragListener) {
        this.b = list;
        this.f9448a = context;
        this.h = onStartDragListener;
        this.e = LayoutInflater.from(context);
        c();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = this.e;
        if (layoutInflater == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "mLayoutInflater is null");
            return null;
        }
        if (i == 1) {
            return new FunctionSetCardManagementViewHolder(layoutInflater.inflate(R.layout.function_set_management_view_card, viewGroup, false), this.f9448a, false, this.h);
        }
        if (i == 0) {
            return new FunctionSetCardManagementDeleteViewHolder(layoutInflater.inflate(R.layout.function_set_management_view_card_delete, viewGroup, false), this.f9448a, false);
        }
        LogUtil.h("FunctionSetCardManagementViewAdapter", "wrong view type");
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        List<ojs> list;
        if (viewHolder == null || (list = this.b) == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "holder or mDataList is null");
            return;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "position is wrong :", Integer.valueOf(i));
            return;
        }
        viewHolder.setIsRecyclable(false);
        if (viewHolder instanceof FunctionSetCardManagementViewHolder) {
            LogUtil.c("FunctionSetCardManagementViewAdapter", "ViewHolder cardName:", this.b.get(i).e(), "position", Integer.valueOf(i));
            FunctionSetCardManagementViewHolder functionSetCardManagementViewHolder = (FunctionSetCardManagementViewHolder) viewHolder;
            functionSetCardManagementViewHolder.d(this.b.get(i));
            if (i != getItemCount() - 1 && this.i.size() - 1 == i) {
                ((HealthDivider) functionSetCardManagementViewHolder.itemView.findViewById(R.id.itemDivider)).setVisibility(8);
            }
            dbW_(functionSetCardManagementViewHolder.dcc_(), i);
        }
        if (viewHolder instanceof FunctionSetCardManagementDeleteViewHolder) {
            LogUtil.c("FunctionSetCardManagementViewAdapter", "DeleteViewHolder cardName:", this.b.get(i).e(), "position", Integer.valueOf(i));
            FunctionSetCardManagementDeleteViewHolder functionSetCardManagementDeleteViewHolder = (FunctionSetCardManagementDeleteViewHolder) viewHolder;
            if (this.i.size() == 0 || this.i.size() != i) {
                functionSetCardManagementDeleteViewHolder.b().setVisibility(8);
            }
            functionSetCardManagementDeleteViewHolder.c(this.b.get(i));
            dbV_(functionSetCardManagementDeleteViewHolder.dbP_(), i);
        }
    }

    public void b(int i, int i2) {
        LogUtil.a("FunctionSetCardManagementViewAdapter", "fromPosition =", Integer.valueOf(i));
        LogUtil.a("FunctionSetCardManagementViewAdapter", "toPosition =", Integer.valueOf(i2));
        List<ojs> list = this.b;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "mDataList is null");
            return;
        }
        if (i < 0 || i >= list.size() || i2 < 0 || i2 >= this.b.size() || this.b.get(i).c() != 1 || this.b.get(i2).c() != 1) {
            return;
        }
        int g = this.b.get(i).g();
        this.b.get(i).b(this.b.get(i2).g());
        this.b.get(i2).b(g);
        if (i2 < getItemCount()) {
            Collections.swap(this.b, i, i2);
            notifyItemMoved(i, i2);
        }
    }

    public void b(int i) {
        LogUtil.a("FunctionSetCardManagementViewAdapter", "onItemSwiped position = ", Integer.valueOf(i));
        this.b.remove(i);
        notifyDataSetChanged();
    }

    private void dbW_(View view, final int i) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int i2 = i;
                if (i2 >= 0 && i2 < FunctionSetCardManagementViewAdapter.this.b.size()) {
                    FunctionSetCardManagementViewAdapter.this.j(i);
                    ViewClickInstrumentation.clickOnView(view2);
                } else {
                    LogUtil.h("FunctionSetCardManagementViewAdapter", "setDeleteOnClick : position is wrong :", Integer.valueOf(i));
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        if (i < 0 || i >= this.b.size()) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "setItemDeleteStatus : position is wrong :", Integer.valueOf(i));
            return;
        }
        if (this.f9448a.getResources().getString(R.string.IDS_physiological_cycle).equals(this.b.get(i).e())) {
            d(i);
            return;
        }
        LogUtil.a("FunctionSetCardManagementViewAdapter", "setItemDeleteStatus position = ", Integer.valueOf(i));
        this.b.get(i).d(2);
        c();
        notifyDataSetChanged();
    }

    private void d(final int i) {
        LogUtil.a("FunctionSetCardManagementViewAdapter", "processPhysiologicalCycleCardDelete");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                String g = owp.g(FunctionSetCardManagementViewAdapter.this.f9448a);
                if (g == null) {
                    LogUtil.b("FunctionSetCardManagementViewAdapter", "getMenstrualSwitch is null.");
                    FunctionSetCardManagementViewAdapter.this.c(i);
                } else {
                    if ("".equals(g)) {
                        FunctionSetCardManagementViewAdapter.this.e(i, g);
                        return;
                    }
                    try {
                        if (new JSONObject(g).getInt("masterSwitch") == 1 && FunctionSetCardManagementViewAdapter.this.d != null) {
                            FunctionSetCardManagementViewAdapter.this.e(i, g);
                            return;
                        }
                    } catch (JSONException unused) {
                        LogUtil.b("FunctionSetCardManagementViewAdapter", "setPhysicalCycleSwitch JSONException.");
                    }
                    FunctionSetCardManagementViewAdapter.this.c(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        Message obtainMessage = this.d.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putString("switch_string", str);
        obtainMessage.what = 0;
        obtainMessage.setData(bundle);
        this.d.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewAdapter.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    ArrayList arrayList = new ArrayList(5);
                    arrayList.add("masterSwitch");
                    arrayList.add("menstrualStartSwitch");
                    arrayList.add("menstrualEndSwitch");
                    arrayList.add("easyPregnancyStartSwitch");
                    arrayList.add("easyPregnancyEndSwitch");
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        jSONObject.put((String) it.next(), 0);
                    }
                    HiUserPreference userPreference = HiHealthManager.d(FunctionSetCardManagementViewAdapter.this.f9448a).getUserPreference("com.huawei.health.mc");
                    if (userPreference == null) {
                        ReleaseLogUtil.d("FunctionSetCardManagementViewAdapter", "setPhysicalCycleSwitch userPreference is null");
                        return;
                    }
                    userPreference.setValue(jSONObject.toString());
                    userPreference.setSyncStatus(0);
                    HiHealthManager.d(FunctionSetCardManagementViewAdapter.this.f9448a).setUserPreference(userPreference);
                } catch (JSONException unused) {
                    LogUtil.b("FunctionSetCardManagementViewAdapter", "setPhysicalCycleSwitch JSONException.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final int i) {
        if (this.c == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f9448a);
            builder.e(this.f9448a.getResources().getString(R.string._2130838114_res_0x7f020262)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewAdapter.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewAdapter.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FunctionSetCardManagementViewAdapter.this.a(str);
                    FunctionSetCardManagementViewAdapter.this.e();
                    FunctionSetCardManagementViewAdapter.this.a(i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.c = builder.e();
        }
        if (this.c.isShowing()) {
            return;
        }
        this.c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        b bVar = this.d;
        if (bVar == null) {
            ReleaseLogUtil.d("FunctionSetCardManagementViewAdapter", "sendMessageRefreshCards mHandler is null");
            return;
        }
        Message obtainMessage = bVar.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        obtainMessage.what = 1;
        obtainMessage.setData(bundle);
        this.d.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        ojs ojsVar;
        if (koq.d(this.b, i) && (ojsVar = this.b.get(i)) != null) {
            ojsVar.d(2);
        }
        c();
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        MenstrualSwitchStatus menstrualSwitchStatus = new MenstrualSwitchStatus();
        menstrualSwitchStatus.setMasterSwitch(0);
        menstrualSwitchStatus.setMenstruationStartRemindSwitch(0);
        menstrualSwitchStatus.setMenstruationEndRemindSwitch(0);
        menstrualSwitchStatus.setEasyPregnancyStartSwitch(0);
        menstrualSwitchStatus.setEasyPregnancyEndSwitch(0);
        omz.a().b(menstrualSwitchStatus);
    }

    private void dbV_(View view, final int i) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewAdapter.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int i2 = i;
                if (i2 >= 0 && i2 < FunctionSetCardManagementViewAdapter.this.b.size()) {
                    FunctionSetCardManagementViewAdapter.this.e(i);
                    ViewClickInstrumentation.clickOnView(view2);
                } else {
                    LogUtil.h("FunctionSetCardManagementViewAdapter", "setAddOnClick : position is wrong :", Integer.valueOf(i));
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i < 0 || i >= this.b.size()) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "setItemAddStatus : position is wrong :", Integer.valueOf(i));
            return;
        }
        LogUtil.a("FunctionSetCardManagementViewAdapter", "setItemAddStatus position = ", Integer.valueOf(i));
        this.b.get(i).d(1);
        c();
        notifyDataSetChanged();
    }

    private void c() {
        b();
        a();
        for (int i = 0; i < this.i.size(); i++) {
            this.i.get(i).b(i);
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            this.g.get(i2).b(i2 + 1000);
        }
        Collections.sort(this.b);
    }

    private void b() {
        this.i.clear();
        List<ojs> list = this.b;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "getShowedDataList : mDataList is null");
            return;
        }
        for (ojs ojsVar : list) {
            if (ojsVar.c() == 1) {
                this.i.add(ojsVar);
            }
        }
    }

    private void a() {
        this.g.clear();
        List<ojs> list = this.b;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewAdapter", "getNotShowedDataList : mDataList is null");
            return;
        }
        for (ojs ojsVar : list) {
            if (ojsVar.c() != 1) {
                this.g.add(ojsVar);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        super.getItemViewType(i);
        if (i >= 0 && i < this.b.size()) {
            return this.b.get(i).c() == 1 ? 1 : 0;
        }
        LogUtil.h("FunctionSetCardManagementViewAdapter", "getItemViewType : position is wrong :", Integer.valueOf(i));
        return 1;
    }

    public List<ojs> d() {
        return this.b;
    }
}
