package com.huawei.ui.homehealth.functionsetcardmanagement;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.homehealth.homeinterface.OnStartDragListener;
import defpackage.koq;
import defpackage.nsn;
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
public class FunctionSetCardManagementViewChineseAdapter extends RecyclerView.Adapter<FunctionSetCardManagementShowChineseViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private int f9450a;
    private FunctionSetCardManagementActivity b;
    private Context c;
    private List<ojs> d;
    private c e;
    private LayoutInflater g;
    private OnStartDragListener k;
    private NoTitleCustomAlertDialog n;
    private int q;
    private List<ojs> m = new ArrayList(9);
    private List<ojs> o = new ArrayList(9);
    private boolean i = true;
    private boolean j = true;
    private boolean h = false;
    private boolean f = false;
    private List<ojs> l = new ArrayList();

    public void b(boolean z) {
        this.i = z;
        notifyDataSetChanged();
    }

    static class c extends BaseHandler<FunctionSetCardManagementViewChineseAdapter> {
        public c(FunctionSetCardManagementViewChineseAdapter functionSetCardManagementViewChineseAdapter) {
            super(functionSetCardManagementViewChineseAdapter);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dcb_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(FunctionSetCardManagementViewChineseAdapter functionSetCardManagementViewChineseAdapter, Message message) {
            LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "handleMessageWhenReferenceNotNull()");
            if (functionSetCardManagementViewChineseAdapter == null || message == null) {
                LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "handleMessageWhenReferenceNotNull obj or msg == null !");
                return;
            }
            int i = message.what;
            if (i == 0) {
                Bundle data = message.getData();
                if (data != null) {
                    functionSetCardManagementViewChineseAdapter.g(data.getInt("position"));
                    return;
                } else {
                    ReleaseLogUtil.d("FunctionSetCardManagementViewChineseAdapter", "handleMessageWhenReferenceNotNull SHOW_MENSTRUAL_DIALOG bundle is null");
                    return;
                }
            }
            if (i == 1) {
                Bundle data2 = message.getData();
                if (data2 != null) {
                    functionSetCardManagementViewChineseAdapter.e(data2.getInt("position"));
                    return;
                } else {
                    ReleaseLogUtil.d("FunctionSetCardManagementViewChineseAdapter", "handleMessageWhenReferenceNotNull GET_MENSTRUAL_SWITCH_STATUS bundle is null");
                    return;
                }
            }
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "unknown msg");
        }
    }

    public FunctionSetCardManagementViewChineseAdapter(List<ojs> list, Context context, OnStartDragListener onStartDragListener) {
        ArrayList arrayList = new ArrayList(list);
        this.d = arrayList;
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "mDataList: ", arrayList);
        this.c = context;
        if (onStartDragListener instanceof FunctionSetCardManagementActivity) {
            this.b = (FunctionSetCardManagementActivity) onStartDragListener;
        }
        this.k = onStartDragListener;
        this.g = LayoutInflater.from(context);
        this.e = new c(this);
        j();
    }

    public void d(final ArrayList<ojs> arrayList) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    FunctionSetCardManagementViewChineseAdapter.this.d(arrayList);
                }
            });
            return;
        }
        this.d.clear();
        this.d.addAll(arrayList);
        j();
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dca_, reason: merged with bridge method [inline-methods] */
    public FunctionSetCardManagementShowChineseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = this.g;
        if (layoutInflater == null) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "mLayoutInflater is null");
            return null;
        }
        return new FunctionSetCardManagementShowChineseViewHolder(layoutInflater.inflate(R.layout.function_set_management_view_card_delete_chinese, viewGroup, false), this.c, false, this.k);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.m.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(FunctionSetCardManagementShowChineseViewHolder functionSetCardManagementShowChineseViewHolder, final int i) {
        List<ojs> list;
        if (functionSetCardManagementShowChineseViewHolder == null || (list = this.m) == null) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "holder or mShowedDataList is null");
            return;
        }
        if (i < 0 || i >= list.size()) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "mShowedDataList position is wrong :", Integer.valueOf(i));
            return;
        }
        dbZ_(functionSetCardManagementShowChineseViewHolder.itemView, i);
        functionSetCardManagementShowChineseViewHolder.setIsRecyclable(false);
        LogUtil.c("FunctionSetCardManagementViewChineseAdapter", "ViewHolder cardName:", this.m.get(i).e(), "position", Integer.valueOf(i));
        functionSetCardManagementShowChineseViewHolder.c(this.m.get(i), this.i);
        dbY_(functionSetCardManagementShowChineseViewHolder.dbU_(), i);
        functionSetCardManagementShowChineseViewHolder.dbT_().setOnLongClickListener(new View.OnLongClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.5
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "shown card is long clicked, position: ", Integer.valueOf(i));
                if (FunctionSetCardManagementViewChineseAdapter.this.i) {
                    return false;
                }
                FunctionSetCardManagementViewChineseAdapter.this.b.d(true);
                return false;
            }
        });
    }

    private void dbZ_(View view, int i) {
        if (view.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            int dimensionPixelSize = this.c.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
            int dimensionPixelSize2 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize3 = this.c.getResources().getDimensionPixelSize(R.dimen._2131362860_res_0x7f0a042c);
            int dimensionPixelSize4 = this.c.getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516);
            if (nsn.ae(this.c) || nsn.ag(this.c)) {
                int i2 = i % 4;
                if (i2 == 0) {
                    layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4);
                } else if (i2 == 3) {
                    layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize4);
                } else {
                    layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4);
                }
            } else if (i % 2 == 0) {
                layoutParams.setMargins(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize3, dimensionPixelSize4);
            } else {
                layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize4);
            }
            view.setLayoutParams(layoutParams);
        }
    }

    public void c(int i) {
        j();
        notifyDataSetChanged();
    }

    public void e(int i, int i2) {
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "fromPosition =", Integer.valueOf(i));
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "toPosition =", Integer.valueOf(i2));
        List<ojs> list = this.m;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "mShowedDataList is null");
            return;
        }
        if (i < 0 || i >= list.size() || i2 < 0 || i2 >= this.m.size() || this.m.get(i).c() != 1 || this.m.get(i2).c() != 1 || i == i2) {
            return;
        }
        this.h = true;
        if (this.j) {
            this.l.clear();
            this.l.addAll(this.d);
            this.f9450a = i;
            this.j = false;
        }
        this.q = i2;
        Collections.swap(this.m, i, i2);
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", this.d);
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", this.m);
        notifyItemMoved(i, i2);
    }

    public void c() {
        int i;
        int i2;
        if (!this.h) {
            LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "only long click, not move");
            return;
        }
        int i3 = 0;
        this.h = false;
        this.d.clear();
        int i4 = this.q;
        int i5 = this.f9450a;
        if (i4 == i5) {
            this.d.addAll(this.l);
        } else if (i5 > i4) {
            while (i3 < this.q) {
                this.d.add(this.l.get(i3));
                i3++;
            }
            this.d.add(this.l.get(this.f9450a));
            int i6 = this.q;
            while (true) {
                i2 = this.f9450a;
                if (i6 >= i2) {
                    break;
                }
                this.d.add(this.l.get(i6));
                i6++;
            }
            for (int i7 = i2 + 1; i7 < this.l.size(); i7++) {
                this.d.add(this.l.get(i7));
            }
        } else {
            while (true) {
                i = this.f9450a;
                if (i3 >= i) {
                    break;
                }
                this.d.add(this.l.get(i3));
                i3++;
            }
            for (int i8 = i + 1; i8 < this.q + 1; i8++) {
                this.d.add(this.l.get(i8));
            }
            this.d.add(this.l.get(this.f9450a));
            for (int i9 = this.q + 1; i9 < this.l.size(); i9++) {
                this.d.add(this.l.get(i9));
            }
        }
        j();
        notifyDataSetChanged();
        this.b.a(this.d);
        this.j = true;
        this.b.a(this.f9450a, this.q);
    }

    public void b(int i) {
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "onItemSwiped position = ", Integer.valueOf(i));
        this.m.remove(i);
        this.d.remove(i);
        notifyDataSetChanged();
    }

    private void dbY_(View view, final int i) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                int i2 = i;
                if (i2 >= 0 && i2 < FunctionSetCardManagementViewChineseAdapter.this.m.size()) {
                    FunctionSetCardManagementViewChineseAdapter.this.j(i);
                    ViewClickInstrumentation.clickOnView(view2);
                } else {
                    LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "setDeleteOnClick : position is wrong :", Integer.valueOf(i));
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        if (i < 0 || i >= this.m.size()) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "setItemDeleteStatus : position is wrong :", Integer.valueOf(i));
            return;
        }
        if (this.c.getResources().getString(R.string.IDS_physiological_cycle).equals(this.m.get(i).e())) {
            d(i);
            return;
        }
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "setItemDeleteStatus position = ", Integer.valueOf(i));
        this.m.get(i).d(2);
        j();
        notifyDataSetChanged();
        this.b.b(i, this.m.size() - 1);
    }

    private void d(final int i) {
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "processPhysiologicalCycleCardDelete");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                String g = owp.g(FunctionSetCardManagementViewChineseAdapter.this.c);
                if (g == null) {
                    LogUtil.b("FunctionSetCardManagementViewChineseAdapter", "getMenstrualSwitch is null.");
                    FunctionSetCardManagementViewChineseAdapter.this.a(i);
                } else {
                    if ("".equals(g)) {
                        FunctionSetCardManagementViewChineseAdapter.this.i(i);
                        return;
                    }
                    try {
                        if (new JSONObject(g).getInt("masterSwitch") == 1 && FunctionSetCardManagementViewChineseAdapter.this.e != null) {
                            FunctionSetCardManagementViewChineseAdapter.this.i(i);
                            return;
                        }
                    } catch (JSONException unused) {
                        LogUtil.b("FunctionSetCardManagementViewChineseAdapter", "setPhysicalCycleSwitch JSONException.");
                    }
                    FunctionSetCardManagementViewChineseAdapter.this.a(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i) {
        Message obtainMessage = this.e.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        obtainMessage.what = 0;
        obtainMessage.setData(bundle);
        this.e.sendMessage(obtainMessage);
    }

    private void c(final String str) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ArrayList arrayList = new ArrayList(5);
                    arrayList.add("masterSwitch");
                    arrayList.add("menstrualStartSwitch");
                    arrayList.add("menstrualEndSwitch");
                    arrayList.add("easyPregnancyStartSwitch");
                    arrayList.add("easyPregnancyEndSwitch");
                    JSONObject jSONObject = new JSONObject(str);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        jSONObject.put((String) it.next(), 0);
                    }
                    HiUserPreference userPreference = HiHealthManager.d(FunctionSetCardManagementViewChineseAdapter.this.c).getUserPreference("com.huawei.health.mc");
                    if (userPreference == null) {
                        ReleaseLogUtil.d("FunctionSetCardManagementViewChineseAdapter", "setPhysicalCycleSwitch userPreference is null");
                        return;
                    }
                    userPreference.setValue(jSONObject.toString());
                    userPreference.setSyncStatus(0);
                    HiHealthManager.d(FunctionSetCardManagementViewChineseAdapter.this.c).setUserPreference(userPreference);
                } catch (JSONException unused) {
                    LogUtil.b("FunctionSetCardManagementViewChineseAdapter", "setPhysicalCycleSwitch JSONException.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final int i) {
        if (this.n == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.c);
            builder.e(this.c.getResources().getString(R.string._2130838114_res_0x7f020262)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.functionsetcardmanagement.FunctionSetCardManagementViewChineseAdapter.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    FunctionSetCardManagementViewChineseAdapter.this.f = true;
                    FunctionSetCardManagementViewChineseAdapter.this.e(i);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.n = builder.e();
        }
        if (this.n.isShowing()) {
            return;
        }
        this.n.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        c cVar = this.e;
        if (cVar == null) {
            ReleaseLogUtil.d("FunctionSetCardManagementViewChineseAdapter", "sendMessageRefreshCards mHandler is null");
            return;
        }
        Message obtainMessage = cVar.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        obtainMessage.what = 1;
        obtainMessage.setData(bundle);
        this.e.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        ojs ojsVar;
        if (koq.d(this.m, i) && (ojsVar = this.m.get(i)) != null) {
            ojsVar.d(2);
        }
        j();
        notifyDataSetChanged();
        this.b.b(i, this.m.size() - 1);
    }

    private void g() {
        MenstrualSwitchStatus menstrualSwitchStatus = new MenstrualSwitchStatus();
        menstrualSwitchStatus.setMasterSwitch(0);
        menstrualSwitchStatus.setMenstruationStartRemindSwitch(0);
        menstrualSwitchStatus.setMenstruationEndRemindSwitch(0);
        menstrualSwitchStatus.setEasyPregnancyStartSwitch(0);
        menstrualSwitchStatus.setEasyPregnancyEndSwitch(0);
        omz.a().b(menstrualSwitchStatus);
    }

    private void j() {
        b();
        e();
        for (int i = 0; i < this.m.size(); i++) {
            this.m.get(i).b(i);
        }
        for (int i2 = 0; i2 < this.o.size(); i2++) {
            this.o.get(i2).b(i2 + 1000);
        }
        Collections.sort(this.d);
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "mDataList: ", this.d);
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "mShowedDataList: ", this.m);
        LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "mNotShowedDataList: ", this.o);
    }

    private void b() {
        this.m.clear();
        List<ojs> list = this.d;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "getShowedDataList : mDataList is null");
            return;
        }
        for (ojs ojsVar : list) {
            if (ojsVar.c() == 1) {
                this.m.add(ojsVar);
            }
        }
    }

    public void a() {
        if (this.f) {
            String g = owp.g(this.c);
            try {
                if (new JSONObject(g).getInt("masterSwitch") == 1) {
                    c(g);
                    g();
                    this.f = false;
                }
            } catch (JSONException unused) {
                LogUtil.a("FunctionSetCardManagementViewChineseAdapter", "parse fail");
            }
        }
    }

    private void e() {
        this.o.clear();
        List<ojs> list = this.d;
        if (list == null) {
            LogUtil.h("FunctionSetCardManagementViewChineseAdapter", "getNotShowedDataList : mDataList is null");
            return;
        }
        for (ojs ojsVar : list) {
            if (ojsVar.c() != 1) {
                this.o.add(ojsVar);
            }
        }
    }

    public List<ojs> d() {
        return this.d;
    }
}
