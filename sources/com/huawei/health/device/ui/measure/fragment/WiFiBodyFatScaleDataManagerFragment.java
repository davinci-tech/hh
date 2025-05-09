package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.kit.hwwsp.hagrid.bean.MiniUserDataInfo;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cfi;
import defpackage.cgt;
import defpackage.cgz;
import defpackage.cjx;
import defpackage.cld;
import defpackage.cpa;
import defpackage.cpp;
import defpackage.cpr;
import defpackage.cpy;
import defpackage.crz;
import defpackage.csf;
import defpackage.ctv;
import defpackage.nmn;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class WiFiBodyFatScaleDataManagerFragment extends BaseFragment implements View.OnClickListener {
    private static final int COLLECTION_DEFAULT_SIZE = 16;
    private static final String DEFAULT_IMAGE_PATH_IDENTIFIER = "default";
    private static final String DEVICE_MAIN_ID = "0";
    private static final int GET_USER_MAX_LEN = 31;
    private static final int MSG_INIT_ADAPTER = 1;
    private static final int MSG_ONRESUME_ADAPTER = 2;
    private static final String TAG = "WiFiBodyFatScaleDataManagerFragment";
    private static final float USERINFO_CLEAR_BUTTON = 0.3f;
    private static final int USER_TYPE = -1;
    private static final int USER_WEIGHT_DIVISOR_VALUE = 10;
    private static final double WEIGHT_VALUE_MAX = 250.0d;
    private static final double WEIGHT_VALUE_MIN = 0.0d;
    private ScaleManagerAdapter mAdapter;
    private HealthTextView mClearUserWeightDataTipMsg;
    private Context mContext;
    private HealthDevice mDevice;
    private String mDeviceId;
    private ContentValues mDeviceInfo;
    private boolean mIsHasDeviceData;
    private ListView mListView;
    private MyHandler mMyHandler;
    private String mProductId;
    private String mUniqueId;
    private MiniUserDataInfo mUserDataInfo;
    private ArrayList<MiniUserDataInfo> mUserDataInfoList;
    private HealthButton mUserInfoClearBtn;
    private List<crz> mUserList;
    private boolean mIsRefreshUserData = false;
    private EventBus.ICallback mCallback = new EventBus.ICallback() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiBodyFatScaleDataManagerFragment.1
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if (bVar != null && "device_user_success".equals(bVar.e())) {
                WiFiBodyFatScaleDataManagerFragment wiFiBodyFatScaleDataManagerFragment = WiFiBodyFatScaleDataManagerFragment.this;
                wiFiBodyFatScaleDataManagerFragment.mUserList = wiFiBodyFatScaleDataManagerFragment.getUserClearBean();
                WiFiBodyFatScaleDataManagerFragment.this.mMyHandler.sendEmptyMessage(1);
                return;
            }
            if (bVar != null && "get_user_data_result".equals(bVar.e())) {
                Bundle Kl_ = bVar.Kl_();
                if (Kl_ == null) {
                    LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "callback onEvent bundle is null");
                    return;
                }
                WiFiBodyFatScaleDataManagerFragment.this.mUserDataInfo = (MiniUserDataInfo) Kl_.getParcelable("userData");
                if (WiFiBodyFatScaleDataManagerFragment.this.mUserDataInfo != null && WiFiBodyFatScaleDataManagerFragment.this.mUserDataInfo.getIsOver() == 1) {
                    LogUtil.c(WiFiBodyFatScaleDataManagerFragment.TAG, "get userDataInfo from device by Ble, mUserDataInfo huid : ", WiFiBodyFatScaleDataManagerFragment.this.mUserDataInfo.getHuid(), "user weight : ", Double.valueOf(WiFiBodyFatScaleDataManagerFragment.this.mUserDataInfo.getWeight()));
                    EventBus.d(new EventBus.b("get_user_data_next"));
                }
                WiFiBodyFatScaleDataManagerFragment.this.mIsRefreshUserData = true;
                WiFiBodyFatScaleDataManagerFragment wiFiBodyFatScaleDataManagerFragment2 = WiFiBodyFatScaleDataManagerFragment.this;
                wiFiBodyFatScaleDataManagerFragment2.mUserList = wiFiBodyFatScaleDataManagerFragment2.getUserClearBean();
                WiFiBodyFatScaleDataManagerFragment.this.mMyHandler.sendEmptyMessage(1);
                return;
            }
            LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "callback onEvent error");
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate");
        this.mMyHandler = new MyHandler(this);
        this.mUserDataInfoList = new ArrayList<>(10);
        this.mContext = cpp.a();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mDeviceId = arguments.getString("deviceId");
            this.mProductId = arguments.getString("productId");
            ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mUniqueId = contentValues.getAsString("uniqueId");
                this.mProductId = this.mDeviceInfo.getAsString("productId");
            }
        }
        if (cpa.e(this.mProductId, this.mUniqueId)) {
            csf.b();
        } else if (cld.HJ_(this.mainActivity, this.mProductId, this.mUniqueId).b()) {
            sendGetUserDataDirectives();
        } else {
            LogUtil.h(TAG, "onCreate haiger device get user data fail");
        }
        EventBus.d(this.mCallback, 2, "device_user_success", "get_user_data_result");
    }

    private void sendGetUserDataDirectives() {
        byte[] bArr = new byte[31];
        byte[] d = new cgz().d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        System.arraycopy(d, 0, bArr, 0, d.length);
        Bundle bundle = new Bundle();
        bundle.putByteArray(JsbMapKeyNames.H5_USER_ID, bArr);
        bundle.putInt("userType", -15);
        bundle.putInt("type", -1);
        cgt.e().prepare(ceo.d().d(this.mUniqueId, false), null, bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.child = layoutInflater.inflate(R.layout.wifi_device_scale_manager_layout, viewGroup, false);
        initView();
        initData();
        if (onCreateView instanceof ViewGroup) {
            ((ViewGroup) onCreateView).addView(this.child);
        }
        return onCreateView;
    }

    private void initData() {
        this.mUserList = new ArrayList(16);
        HealthDevice e = cjx.e().e(this.mDeviceId);
        this.mDevice = e;
        if (e != null) {
            this.mClearUserWeightDataTipMsg.setVisibility(0);
        } else {
            this.mClearUserWeightDataTipMsg.setVisibility(8);
        }
        initWeightUser();
    }

    private void initWeightUser() {
        MultiUsersManager.INSTANCE.getOtherUserData(new IBaseResponseCallback() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiBodyFatScaleDataManagerFragment.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                WiFiBodyFatScaleDataManagerFragment wiFiBodyFatScaleDataManagerFragment = WiFiBodyFatScaleDataManagerFragment.this;
                wiFiBodyFatScaleDataManagerFragment.mUserList = wiFiBodyFatScaleDataManagerFragment.getUserClearBean();
                WiFiBodyFatScaleDataManagerFragment.this.mMyHandler.sendEmptyMessage(1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdapter() {
        ScaleManagerAdapter scaleManagerAdapter = new ScaleManagerAdapter(this.mUserList);
        this.mAdapter = scaleManagerAdapter;
        this.mListView.setAdapter((ListAdapter) scaleManagerAdapter);
        Object[] objArr = new Object[6];
        objArr[0] = "initAdapter mDevice is null :";
        objArr[1] = Boolean.valueOf(this.mDevice == null);
        objArr[2] = ",isNetworkConnected :";
        objArr[3] = Boolean.valueOf(CommonUtil.aa(this.mainActivity));
        objArr[4] = ",mIsHasDeviceData :";
        objArr[5] = Boolean.valueOf(this.mIsHasDeviceData);
        LogUtil.a(TAG, objArr);
        if ((this.mDevice != null && !CommonUtil.aa(this.mainActivity)) || !this.mIsHasDeviceData) {
            this.mUserInfoClearBtn.setEnabled(false);
            this.mUserInfoClearBtn.setAlpha(USERINFO_CLEAR_BUTTON);
        } else {
            this.mUserInfoClearBtn.setEnabled(true);
            this.mUserInfoClearBtn.setAlpha(1.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<crz> getUserClearBean() {
        ArrayList<crz> arrayList = new ArrayList<>(16);
        List<cfi> allUser = MultiUsersManager.INSTANCE.getAllUser();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        this.mIsHasDeviceData = false;
        for (cfi cfiVar : allUser) {
            crz crzVar = new crz();
            String i = cfiVar.i();
            LogUtil.c(TAG, "getUserClearBean, user:", cfiVar.toString(), "weight:", Float.valueOf(cfiVar.m()), "uuid:", i);
            if (!cpa.av(this.mProductId)) {
                crzVar = setUserDataFromBluetooth(cfiVar, i, accountInfo);
                arrayList.add(crzVar);
            } else {
                setUserData(accountInfo, cfiVar, crzVar, i);
                arrayList.add(crzVar);
            }
            LogUtil.c(TAG, "UserClearBean user:", crzVar.c(), "weight:", Double.valueOf(crzVar.a()));
        }
        return arrayList;
    }

    private crz setUserDataFromBluetooth(cfi cfiVar, String str, String str2) {
        crz crzVar = new crz();
        crzVar.e(cfiVar);
        if (this.mIsRefreshUserData) {
            setUserDataList(str2, crzVar, str);
        } else {
            crzVar.e(0.0d);
        }
        return crzVar;
    }

    private void setUserData(String str, cfi cfiVar, crz crzVar, String str2) {
        String e;
        if (ctv.c(str2, str)) {
            e = csf.e(this.mDeviceId, String.valueOf(0));
        } else {
            e = csf.e(this.mDeviceId, str2);
        }
        String j = csf.j(e);
        crzVar.e(cfiVar);
        if (!TextUtils.isEmpty(j) && !"0".equals(j)) {
            try {
                crzVar.e(Double.parseDouble(j) / (cpa.ae(this.mProductId) ? 100 : 10));
                this.mIsHasDeviceData = true;
                return;
            } catch (NumberFormatException e2) {
                LogUtil.b(TAG, "setUserWeight error:" + e2.getMessage());
                return;
            }
        }
        crzVar.e(0.0d);
        LogUtil.b(TAG, "userWeight is Empty or 0:");
    }

    private void setUserDataList(String str, crz crzVar, String str2) {
        String str3;
        if (this.mUserDataInfo == null) {
            LogUtil.h(TAG, "setUserDataList mUserDataInfo is null.");
            return;
        }
        if (this.mUserDataInfoList == null) {
            LogUtil.h(TAG, "setUserDataList mUserDataInfoList is null.");
            this.mUserDataInfoList = new ArrayList<>(10);
        }
        this.mUserDataInfoList.add(this.mUserDataInfo);
        for (int i = 0; i < this.mUserDataInfoList.size(); i++) {
            MiniUserDataInfo miniUserDataInfo = this.mUserDataInfoList.get(i);
            if (miniUserDataInfo == null) {
                LogUtil.h(TAG, "setUserDataList userDataInfo is null.");
            } else {
                String huid = miniUserDataInfo.getHuid();
                if (huid == null) {
                    LogUtil.h(TAG, "setUserDataList huid is null.");
                    return;
                }
                if ("0".equals(huid) || ctv.c(str2, str)) {
                    str3 = "";
                } else {
                    str3 = huid.substring(0, str2.length());
                    LogUtil.b(TAG, "getUserClearBean uid");
                }
                double weight = miniUserDataInfo.getWeight();
                if ((ctv.c(str2, str) && "0".equals(huid)) || str2.equals(str3)) {
                    if (weight > 0.0d) {
                        this.mIsHasDeviceData = true;
                        crzVar.e(weight);
                        return;
                    } else {
                        crzVar.e(0.0d);
                        return;
                    }
                }
                LogUtil.h(TAG, "setUserDataList User not found next data");
            }
        }
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_wifi_scale_manager));
        this.mListView = (ListView) nsy.cMd_(this.child, R.id.scale_manager_list_view);
        this.mUserInfoClearBtn = (HealthButton) nsy.cMd_(this.child, R.id.id_btn_clear_user_info);
        this.mClearUserWeightDataTipMsg = (HealthTextView) nsy.cMd_(this.child, R.id.tv_user_clear_must_online_tag);
        if (cpa.ah(this.mProductId)) {
            this.mClearUserWeightDataTipMsg.setVisibility(8);
        }
        this.mUserInfoClearBtn.setOnClickListener(this);
        this.mUserInfoClearBtn.setEnabled(false);
    }

    class ScaleManagerAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        private List<crz> mList = new ArrayList(16);

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        ScaleManagerAdapter(List<crz> list) {
            this.mInflater = LayoutInflater.from(WiFiBodyFatScaleDataManagerFragment.this.mContext);
            setData(list);
        }

        private void setData(List<crz> list) {
            this.mList.clear();
            this.mList.addAll(list);
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<crz> list = this.mList;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public crz getItem(int i) {
            if (i < 0) {
                LogUtil.h(WiFiBodyFatScaleDataManagerFragment.TAG, "position is out of bounds one");
            }
            List<crz> list = this.mList;
            if (list != null) {
                if (list.size() <= i) {
                    LogUtil.h(WiFiBodyFatScaleDataManagerFragment.TAG, "position is out of bounds two");
                } else {
                    return this.mList.get(i);
                }
            }
            return null;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.mInflater.inflate(R.layout.item_wifi_scales_manager, (ViewGroup) null);
                viewHolder.userPhoto = (ImageView) nsy.cMd_(view2, R.id.item_scale_manager_user_photo);
                viewHolder.userName = (HealthTextView) nsy.cMd_(view2, R.id.item_scale_manager_title_text);
                viewHolder.userWeight = (HealthTextView) nsy.cMd_(view2, R.id.hw_scale_manage_user_weight);
                viewHolder.line = (HealthDivider) nsy.cMd_(view2, R.id.user_manager_line);
                view2.setTag(viewHolder);
            } else if (view.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) view.getTag();
                view2 = view;
            } else {
                view2 = view;
                viewHolder = null;
            }
            if (viewHolder == null) {
                LogUtil.h(WiFiBodyFatScaleDataManagerFragment.TAG, "holder is null");
                return view;
            }
            List<crz> list = this.mList;
            if (list == null || list.size() != i + 1) {
                viewHolder.line.setVisibility(0);
            } else {
                viewHolder.line.setVisibility(4);
            }
            crz item = getItem(i);
            if (item == null) {
                LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "userBean is null");
                return null;
            }
            WiFiBodyFatScaleDataManagerFragment.setUserNameAndPhoto(WiFiBodyFatScaleDataManagerFragment.this.mContext, item.d(), viewHolder.userName, viewHolder.userPhoto);
            double a2 = item.a();
            int c = cpy.c(a2, WiFiBodyFatScaleDataManagerFragment.this.mProductId);
            LogUtil.c(WiFiBodyFatScaleDataManagerFragment.TAG, "userWeight=", Double.valueOf(a2), ", fractionDigit=", Integer.valueOf(c));
            if (a2 <= 0.0d || a2 > WiFiBodyFatScaleDataManagerFragment.WEIGHT_VALUE_MAX) {
                viewHolder.userWeight.setText(WiFiBodyFatScaleDataManagerFragment.this.mainActivity.getResources().getString(R.string.IDS_device_wifi_no_record));
            } else {
                viewHolder.userWeight.setText(cpr.e(a2, c));
            }
            return view2;
        }

        class ViewHolder {
            private HealthDivider line;
            private HealthTextView userName;
            private ImageView userPhoto;
            private HealthTextView userWeight;

            ViewHolder() {
            }
        }
    }

    static class MyHandler extends StaticHandler<WiFiBodyFatScaleDataManagerFragment> {
        MyHandler(WiFiBodyFatScaleDataManagerFragment wiFiBodyFatScaleDataManagerFragment) {
            super(wiFiBodyFatScaleDataManagerFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiBodyFatScaleDataManagerFragment wiFiBodyFatScaleDataManagerFragment, Message message) {
            if (wiFiBodyFatScaleDataManagerFragment != null) {
                if (wiFiBodyFatScaleDataManagerFragment.isDestroy()) {
                    LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "MyHandler activity is exist");
                    return;
                }
                if (!wiFiBodyFatScaleDataManagerFragment.isAdded()) {
                    LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "MyHandler mFragment is not add");
                    return;
                }
                if (message == null) {
                    LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "MyHandler msg is null");
                    return;
                }
                LogUtil.a(WiFiBodyFatScaleDataManagerFragment.TAG, "MyHandler what:", Integer.valueOf(message.what));
                if (message.what == 1) {
                    wiFiBodyFatScaleDataManagerFragment.initAdapter();
                    return;
                } else {
                    LogUtil.h(WiFiBodyFatScaleDataManagerFragment.TAG, "MyHandler what is other");
                    return;
                }
            }
            LogUtil.b(WiFiBodyFatScaleDataManagerFragment.TAG, "MyHandler object is null");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h(TAG, "onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view.getId() == R.id.id_btn_clear_user_info) {
                gotoClearUserFragment();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.a(this.mCallback, "device_user_success", "get_user_data_result");
        List<crz> list = this.mUserList;
        if (list != null) {
            list.clear();
            this.mUserList = null;
        }
        ArrayList<MiniUserDataInfo> arrayList = this.mUserDataInfoList;
        if (arrayList != null) {
            arrayList.clear();
            this.mUserDataInfoList = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.b(TAG, "DeviceMainActivity is null");
            return true;
        }
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        LogUtil.b(TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    public static void setUserNameAndPhoto(Context context, cfi cfiVar, HealthTextView healthTextView, ImageView imageView) {
        if (cfiVar == null) {
            LogUtil.b(TAG, "setUserNameAndPhoto user is null");
            return;
        }
        if (healthTextView == null || imageView == null) {
            LogUtil.b(TAG, "setUserNameAndPhoto userNameTv or userPhotoImg is null");
            return;
        }
        healthTextView.setText(cfiVar.h());
        imageView.setImageBitmap(null);
        imageView.setImageResource(0);
        Drawable cBh_ = nmn.cBh_(context.getResources(), new nmn.c(null, cfiVar.i(), true));
        if (cfiVar.n() == -1) {
            imageView.setImageDrawable(cBh_);
            return;
        }
        if (!TextUtils.isEmpty(cfiVar.e())) {
            setUserPhoto(cfiVar.e(), imageView, cBh_);
        } else if (cfiVar.Ex_() == null) {
            imageView.setImageDrawable(cBh_);
        } else {
            imageView.setImageBitmap(nrf.cHX_(cfiVar.Ex_()));
        }
    }

    private static void setUserPhoto(String str, ImageView imageView, Drawable drawable) {
        if (imageView == null || drawable == null) {
            LogUtil.b(TAG, "setUserPhoto: userPhotoIv or defaultUserPhoto is null");
            return;
        }
        if (!"default".equals(str)) {
            Bitmap cIe_ = nrf.cIe_(BaseApplication.getContext(), str);
            if (cIe_ != null) {
                imageView.setImageBitmap(cIe_);
                return;
            }
            return;
        }
        imageView.setImageDrawable(drawable);
    }

    private void gotoClearUserFragment() {
        Context context = BaseApplication.getContext();
        if (!ctv.d(context)) {
            nrh.b(context, R.string.IDS_device_wifi_not_network);
            return;
        }
        LogUtil.a(TAG, "gotoClearUserFragment");
        Bundle bundle = new Bundle();
        bundle.putString("deviceId", this.mDeviceId);
        bundle.putString("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        bundle.putParcelableArrayList("userData", this.mUserDataInfoList);
        WiFiUserClearFragment wiFiUserClearFragment = new WiFiUserClearFragment();
        wiFiUserClearFragment.setArguments(bundle);
        switchFragment(wiFiUserClearFragment);
    }
}
