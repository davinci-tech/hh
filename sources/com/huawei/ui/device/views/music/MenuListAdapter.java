package com.huawei.ui.device.views.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class MenuListAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private ViewHolder f9319a;
    private OnItemClickListener b;
    private LayoutInflater c;
    private HashMap<String, ImageView> d = new HashMap<>(16);
    private List<MusicMenu> e;
    private View f;

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public MenuListAdapter(Context context, List<MusicMenu> list) {
        this.e = new ArrayList(16);
        this.c = LayoutInflater.from(context);
        this.e = list;
    }

    public List<MusicMenu> c() {
        return this.e;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<MusicMenu> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cVx_, reason: merged with bridge method [inline-methods] */
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.f = this.c.inflate(R.layout.menu_item_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(this.f);
        this.f9319a = viewHolder;
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        List<MusicMenu> list = this.e;
        if (list == null || list.isEmpty() || i < 0 || i >= this.e.size()) {
            return;
        }
        viewHolder.b(this.e.get(i), i);
    }

    public void a(int i) {
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (i2 == i) {
                this.d.get("" + i2).setImageResource(R.drawable._2131427695_res_0x7f0b016f);
            } else {
                this.d.get("" + i2).setImageResource(R.drawable._2131427694_res_0x7f0b016e);
            }
        }
    }

    public void c(OnItemClickListener onItemClickListener) {
        this.b = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f9320a;
        private View b;
        private ImageView d;

        public ViewHolder(View view) {
            super(view);
            this.f9320a = (HealthTextView) MenuListAdapter.this.f.findViewById(R.id.item_menu_name);
            this.b = MenuListAdapter.this.f.findViewById(R.id.item_menu_line);
            this.d = (ImageView) MenuListAdapter.this.f.findViewById(R.id.radio_picture);
        }

        public void b(MusicMenu musicMenu, final int i) {
            if (musicMenu == null) {
                return;
            }
            this.f9320a.setText(musicMenu.getMenuName());
            MenuListAdapter.this.d.put("" + i, this.d);
            if (i == MenuListAdapter.this.e.size() - 1) {
                this.b.setVisibility(8);
            } else {
                this.b.setVisibility(0);
            }
            MenuListAdapter.this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.views.music.MenuListAdapter.ViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MenuListAdapter.this.b != null) {
                        MenuListAdapter.this.b.onItemClick(MenuListAdapter.this.f, i);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }
}
