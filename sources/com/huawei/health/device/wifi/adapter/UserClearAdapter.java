package com.huawei.health.device.wifi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.device.ui.measure.fragment.WiFiBodyFatScaleDataManagerFragment;
import com.huawei.health.device.wifi.interfaces.SelectClearUserInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cpr;
import defpackage.cpw;
import defpackage.cpy;
import defpackage.crz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class UserClearAdapter extends RecyclerView.Adapter<MyViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private String f2271a;
    List<crz> b;
    Context c;
    private int d;
    private SelectClearUserInterface e;

    static /* synthetic */ int a(UserClearAdapter userClearAdapter) {
        int i = userClearAdapter.d;
        userClearAdapter.d = i + 1;
        return i;
    }

    static /* synthetic */ int b(UserClearAdapter userClearAdapter) {
        int i = userClearAdapter.d;
        userClearAdapter.d = i - 1;
        return i;
    }

    public UserClearAdapter(List<crz> list, Context context, SelectClearUserInterface selectClearUserInterface, String str) {
        this.b = list;
        this.c = context;
        this.e = selectClearUserInterface;
        this.f2271a = str;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Lz_, reason: merged with bridge method [inline-methods] */
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.c).inflate(R.layout.item_wifi_user_clear, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        List<crz> list;
        if (myViewHolder == null || (list = this.b) == null) {
            cpw.a(false, "PluginDevice_UserClearAdapter", "holder or mClearBeanList is null");
            return;
        }
        final crz crzVar = list.get(i);
        if (crzVar == null || crzVar.d() == null) {
            cpw.a(false, "PluginDevice_UserClearAdapter", "bean is null");
            return;
        }
        if (i == this.b.size() - 1) {
            myViewHolder.d.setVisibility(8);
        } else {
            myViewHolder.d.setVisibility(0);
        }
        WiFiBodyFatScaleDataManagerFragment.setUserNameAndPhoto(this.c, crzVar.d(), myViewHolder.e, myViewHolder.b);
        if (crzVar.b()) {
            myViewHolder.c.setChecked(true);
        } else {
            myViewHolder.c.setChecked(false);
        }
        myViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.wifi.adapter.UserClearAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (crzVar.b()) {
                    crzVar.d(false);
                    UserClearAdapter.b(UserClearAdapter.this);
                    UserClearAdapter.this.e.selectItem(UserClearAdapter.this.b.size(), UserClearAdapter.this.d);
                } else {
                    crzVar.d(true);
                    UserClearAdapter.a(UserClearAdapter.this);
                    UserClearAdapter.this.e.selectItem(UserClearAdapter.this.b.size(), UserClearAdapter.this.d);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        double a2 = crzVar.a();
        if (a2 <= 0.0d) {
            myViewHolder.f2273a.setText(this.c.getResources().getString(R.string.IDS_device_wifi_no_record));
        } else {
            myViewHolder.f2273a.setText(cpr.e(a2, cpy.c(a2, this.f2271a)));
        }
        this.e.selectItem(this.b.size(), this.d);
    }

    public void c() {
        List<crz> list = this.b;
        if (list == null) {
            cpw.a(false, "PluginDevice_UserClearAdapter", "setAllChooseItem mClearBeanList is null");
            return;
        }
        Iterator<crz> it = list.iterator();
        while (it.hasNext()) {
            it.next().d(true);
            this.d = this.b.size();
        }
        notifyDataSetChanged();
        this.e.selectItem(this.b.size(), this.d);
    }

    public void e() {
        List<crz> list = this.b;
        if (list == null) {
            cpw.a(false, "PluginDevice_UserClearAdapter", "unsetAllChooseItem mClearBeanList is null");
            return;
        }
        Iterator<crz> it = list.iterator();
        while (it.hasNext()) {
            it.next().d(false);
            this.d = 0;
        }
        notifyDataSetChanged();
        this.e.selectItem(this.b.size(), this.d);
    }

    public ArrayList<crz> d() {
        ArrayList<crz> arrayList = new ArrayList<>(16);
        List<crz> list = this.b;
        if (list == null) {
            cpw.a(false, "PluginDevice_UserClearAdapter", "getChooseData mList is null");
            return arrayList;
        }
        for (crz crzVar : list) {
            if (crzVar.b()) {
                arrayList.add(crzVar);
            }
        }
        cpw.a(false, "PluginDevice_UserClearAdapter", "getChooseData data size:", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<crz> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public ArrayList<crz> a() {
        ArrayList<crz> arrayList = new ArrayList<>(16);
        List<crz> list = this.b;
        if (list == null) {
            cpw.a(false, "PluginDevice_UserClearAdapter", "removeItem mList is null");
            return arrayList;
        }
        for (crz crzVar : list) {
            if (!crzVar.b()) {
                arrayList.add(crzVar);
            }
        }
        this.b = arrayList;
        this.d = 0;
        this.e.selectItem(arrayList.size(), this.d);
        return arrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        HealthTextView f2273a;
        ImageView b;
        HealthCheckBox c;
        HealthDivider d;
        HealthTextView e;

        public MyViewHolder(View view) {
            super(view);
            if (view == null) {
                cpw.a(false, "PluginDevice_UserClearAdapter", "MyViewHolder itemView is null");
                return;
            }
            this.e = (HealthTextView) view.findViewById(R.id.tv_user_clear_username);
            this.f2273a = (HealthTextView) view.findViewById(R.id.tv_user_clear_user_weight);
            this.c = (HealthCheckBox) view.findViewById(R.id.iv_user_clear_data_select_status);
            this.d = (HealthDivider) view.findViewById(R.id.view_clear_user_data_line);
            this.b = (ImageView) view.findViewById(R.id.item_user_clear_user_photo);
        }
    }
}
