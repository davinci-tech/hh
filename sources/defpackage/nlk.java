package defpackage;

import android.content.Context;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nlk extends BaseDialog {
    private static final ArrayMap<String, a> d;

    /* renamed from: a, reason: collision with root package name */
    private Context f15372a;
    private View c;
    private ListView e;

    static {
        ArrayMap<String, a> arrayMap = new ArrayMap<>();
        d = arrayMap;
        a aVar = new a(R$string.IDS_permission_usage_contacts, R$string.IDS_permission_description_contacts);
        arrayMap.put("android.permission.READ_CONTACTS", aVar);
        arrayMap.put("android.permission.WRITE_CONTACTS", aVar);
        arrayMap.put("android.permission.GET_ACCOUNTS", aVar);
        a aVar2 = new a(R$string.IDS_permission_usage_calendar, R$string.IDS_permission_description_calendar);
        arrayMap.put("android.permission.READ_CALENDAR", aVar2);
        arrayMap.put("android.permission.WRITE_CALENDAR", aVar2);
        a aVar3 = new a(R$string.IDS_permission_usage_sms, R$string.IDS_permission_description_sms);
        arrayMap.put("android.permission.SEND_SMS", aVar3);
        arrayMap.put("android.permission.RECEIVE_SMS", aVar3);
        arrayMap.put("android.permission.READ_SMS", aVar3);
        arrayMap.put("android.permission.RECEIVE_MMS", aVar3);
        arrayMap.put("android.permission.RECEIVE_WAP_PUSH", aVar3);
        a aVar4 = new a(R$string.IDS_permission_usage_storage, R$string.IDS_permission_description_storage);
        arrayMap.put("android.permission.READ_EXTERNAL_STORAGE", aVar4);
        arrayMap.put("android.permission.WRITE_EXTERNAL_STORAGE", aVar4);
        arrayMap.put("android.permission.ACCESS_MEDIA_LOCATION", aVar4);
        a aVar5 = new a(R$string.IDS_permission_usage_location, R$string.IDS_permission_description_location);
        arrayMap.put("android.permission.ACCESS_FINE_LOCATION", aVar5);
        arrayMap.put("android.permission.ACCESS_COARSE_LOCATION", aVar5);
        arrayMap.put("android.permission.ACCESS_BACKGROUND_LOCATION", aVar5);
        a aVar6 = new a(R$string.IDS_permission_usage_call_log, R$string.IDS_permission_description_call_log);
        arrayMap.put("android.permission.READ_CALL_LOG", aVar6);
        arrayMap.put("android.permission.WRITE_CALL_LOG", aVar6);
        arrayMap.put("android.permission.PROCESS_OUTGOING_CALLS", aVar6);
        a aVar7 = new a(R$string.IDS_permission_usage_phone, R$string.IDS_permission_description_phone);
        arrayMap.put("android.permission.READ_PHONE_STATE", aVar7);
        arrayMap.put("android.permission.READ_PHONE_NUMBERS", aVar7);
        arrayMap.put("android.permission.CALL_PHONE", aVar7);
        arrayMap.put("com.android.voicemail.permission.ADD_VOICEMAIL", aVar7);
        arrayMap.put("android.permission.USE_SIP", aVar7);
        arrayMap.put("android.permission.ANSWER_PHONE_CALLS", aVar7);
        arrayMap.put("android.permission.ACCEPT_HANDOVER", aVar7);
        arrayMap.put("android.permission.RECORD_AUDIO", new a(R$string.IDS_permission_usage_microphone, R$string.IDS_permission_description_microphone));
        arrayMap.put("android.permission.ACTIVITY_RECOGNITION", new a(R$string.IDS_permission_usage_activity_recognition, R$string.IDS_permission_des_recognition));
        arrayMap.put("android.permission.CAMERA", new a(R$string.IDS_permission_usage_camera, R$string.IDS_permission_description_camera));
        arrayMap.put("android.permission.READ_PHONE_STATE", new a(R$string.IDS_permission_usage_read_phone_state, R$string.IDS_permission_description_phone_state));
        a aVar8 = new a(R$string.IDS_nearby_permission, R$string.IDS_location_permission_use);
        arrayMap.put("android.permission.BLUETOOTH_CONNECT", aVar8);
        arrayMap.put("android.permission.BLUETOOTH_SCAN", aVar8);
        arrayMap.put("android.permission.NEARBY_WIFI_DEVICES", aVar8);
        a aVar9 = new a(R$string.IDS_permission_usage_img_video, R$string.IDS_permission_description_storage);
        arrayMap.put("android.permission.READ_MEDIA_IMAGES", aVar9);
        arrayMap.put("android.permission.READ_MEDIA_VIDEO", aVar9);
    }

    public nlk(Context context, List<String> list) {
        super(context, R.style.CustomDialog);
        this.f15372a = context;
        c(list);
    }

    private void c(List<String> list) {
        LogUtil.a("PermissionDescDialog", "initView");
        View inflate = ((LayoutInflater) this.f15372a.getSystemService("layout_inflater")).inflate(R.layout.permission_desc_dialog, (ViewGroup) null);
        this.c = inflate;
        this.e = (ListView) inflate.findViewById(R.id.permission_desc_list);
        d(list);
        setContentView(this.c);
        setWindowGravityParams(48);
    }

    private void d(List<String> list) {
        a aVar;
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list.size());
        for (String str : list) {
            if (str != null && (aVar = d.get(str)) != null && !arrayList.contains(this.f15372a.getString(aVar.f15373a))) {
                arrayList.add(this.f15372a.getString(aVar.f15373a));
                arrayList2.add(this.f15372a.getString(aVar.c));
            }
        }
        this.e.setAdapter((ListAdapter) new b(arrayList, arrayList2));
    }

    static class b extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        private final List<String> f15374a;
        private final List<String> e;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        b(List<String> list, List<String> list2) {
            this.e = list;
            this.f15374a = list2;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.e.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (koq.d(this.e, i)) {
                return null;
            }
            return this.e.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            e eVar;
            LogUtil.a("PermissionDescDialog", "getView");
            if (view == null) {
                view = ((LayoutInflater) BaseApplication.e().getSystemService("layout_inflater")).inflate(R.layout.permission_description_item, (ViewGroup) null);
                eVar = new e();
                eVar.b = (HealthTextView) view.findViewById(R.id.permission_name);
                eVar.c = (HealthTextView) view.findViewById(R.id.permission_description);
                view.setTag(eVar);
            } else {
                eVar = (e) view.getTag();
            }
            if (koq.d(this.e, i)) {
                eVar.b.setText(this.e.get(i));
            }
            if (koq.d(this.f15374a, i)) {
                eVar.c.setText(this.f15374a.get(i));
            }
            return view;
        }
    }

    static class e {
        HealthTextView b;
        HealthTextView c;

        private e() {
        }
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        int f15373a;
        int c;

        public a(int i, int i2) {
            this.f15373a = i;
            this.c = i2;
        }
    }
}
