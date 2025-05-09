package com.huawei.ui.main.stories.nps.views;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class EcologyNpsAdapter extends BaseAdapter {
    private static final String TAG = "EcologyNpsAdapter";
    private Context mContext;
    private String[] mDataInfos;
    private Handler mHandler;
    private boolean mIsMultiple;
    private HashMap<String, Boolean> mStateMap;
    private OnCheckedChangeListener onCheckedChangeListener;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public EcologyNpsAdapter(Context context, Handler handler, NpsAdapterParams npsAdapterParams) {
        this.mStateMap = new HashMap<>(16);
        if (npsAdapterParams == null) {
            return;
        }
        if (npsAdapterParams.getDataInfos() != null) {
            this.mDataInfos = (String[]) Arrays.copyOf(npsAdapterParams.getDataInfos(), npsAdapterParams.getDataInfos().length);
        }
        this.mContext = context;
        this.mStateMap = npsAdapterParams.getStateMap();
        this.mHandler = handler;
        this.onCheckedChangeListener = npsAdapterParams.getOnCheckedChangeListener();
        this.mIsMultiple = npsAdapterParams.getIsMultiple();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        String[] strArr = this.mDataInfos;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        String[] strArr = this.mDataInfos;
        if (strArr == null || i < 0 || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LogUtil.a(TAG, "position:", Integer.valueOf(i));
        Context context = this.mContext;
        if (context == null) {
            return view;
        }
        LayoutInflater from = LayoutInflater.from(context);
        if (view == null) {
            view = from.inflate(R.layout.ecology_nps_single, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.textViewName = (HealthTextView) view.findViewById(R.id.tv_device_name);
            view.setTag(viewHolder);
        } else {
            if (view.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) view.getTag();
            }
            return view;
        }
        String[] strArr = this.mDataInfos;
        if (strArr != null && i >= 0 && i < strArr.length) {
            viewHolder.textViewName.setText(this.mDataInfos[i]);
        }
        if (this.mIsMultiple) {
            viewHolder.checkBoxState = (HealthCheckBox) view.findViewById(R.id.rb_light_multi);
        } else {
            viewHolder.checkBoxState = (HealthCheckBox) view.findViewById(R.id.rb_light);
        }
        boolean z = false;
        viewHolder.checkBoxState.setVisibility(0);
        viewHolder.checkBoxState.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.nps.views.EcologyNpsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                EcologyNpsAdapter.this.changeState(i);
                EcologyNpsAdapter.this.notifyDataSetChanged();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        if (this.mStateMap.get(String.valueOf(i)) == null || !this.mStateMap.get(String.valueOf(i)).booleanValue()) {
            this.mStateMap.put(String.valueOf(i), false);
        } else {
            z = true;
        }
        viewHolder.checkBoxState.setChecked(z);
        return view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeState(int i) {
        if (this.mIsMultiple) {
            this.mStateMap.put(String.valueOf(i), Boolean.valueOf(!this.mStateMap.get(String.valueOf(i)).booleanValue()));
        } else {
            Iterator<String> it = this.mStateMap.keySet().iterator();
            while (it.hasNext()) {
                this.mStateMap.put(it.next(), false);
            }
            this.mStateMap.put(String.valueOf(i), true);
        }
        this.onCheckedChangeListener.onClick(getCheckedItemCount() != 0);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendEmptyMessage(i);
        }
    }

    private int getCheckedItemCount() {
        Iterator<Boolean> it = this.mStateMap.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().booleanValue()) {
                i++;
            }
        }
        return i;
    }

    static class ViewHolder {
        HealthCheckBox checkBoxState;
        HealthTextView textViewName;

        ViewHolder() {
        }
    }
}
