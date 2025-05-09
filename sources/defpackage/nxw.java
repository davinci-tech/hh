package defpackage;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.datatype.Contact;
import com.huawei.datatype.PhoneNumber;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter;
import com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityView;
import health.compact.a.IoUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class nxw extends pjp<ContactSelectActivityView> implements ContactSelectActivityPresenter {
    private boolean b;
    private boolean c;
    private List<nxv> d;
    private int e;
    private ArrayList<String> f;
    private List<nxv> h;
    private File i;
    private List<Contact> j = new ArrayList(10);

    /* renamed from: a, reason: collision with root package name */
    private Handler f15549a = new Handler() { // from class: nxw.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 200 || nxw.this.a() == null) {
                return;
            }
            nxw.this.a().setAdapter(nxw.this.d);
        }
    };

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public List<nxv> getContacts() {
        LogUtil.d("ContactSelectActivityPresenterImpl", "getContacts enter");
        ArrayList arrayList = new ArrayList(10);
        if (a() == null) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "getContacts getView is null");
            return arrayList;
        }
        HashMap hashMap = new HashMap(10);
        HashMap hashMap2 = new HashMap(10);
        setContactData(hashMap, hashMap2);
        arrayList.addAll(hashMap.values());
        try {
            contactsSort(arrayList);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("ContactSelectActivityPresenterImpl", "getContacts IllegalArgumentException");
        }
        arrayList.addAll(hashMap2.values());
        a(arrayList);
        return arrayList;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void contactsSort(List<nxv> list) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "contactsSort enter");
        if (list == null || list.isEmpty()) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "contactsSort contactDataList is null or empty");
        } else {
            Collections.sort(list, new Comparator<nxv>() { // from class: nxw.3
                @Override // java.util.Comparator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public int compare(nxv nxvVar, nxv nxvVar2) {
                    if (nxvVar == null && nxvVar2 == null) {
                        return 0;
                    }
                    if (nxvVar == null) {
                        return 1;
                    }
                    if (nxvVar2 == null) {
                        return -1;
                    }
                    if (nxvVar.f().contains("#")) {
                        return 1;
                    }
                    if (nxvVar2.f().contains("#")) {
                        return -1;
                    }
                    return nxvVar.f().compareTo(nxvVar2.f());
                }
            });
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void setContactSortData(nxv nxvVar) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "setContactSortData enter");
        if (nxvVar == null) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "setContactSortData contactData is null");
            return;
        }
        try {
            String upperCase = pf.d(nxvVar.d(), "").toUpperCase(Locale.ENGLISH);
            LogUtil.d("ContactSelectActivityPresenterImpl", "setContactSortData sortName is", bky.e(upperCase));
            if (TextUtils.isEmpty(upperCase)) {
                LogUtil.c("ContactSelectActivityPresenterImpl", "setContactSortData sortName is empty");
                nxvVar.j("#");
                nxvVar.g("#");
            } else {
                String substring = upperCase.substring(0, 1);
                if (substring.matches("[A-Z]")) {
                    nxvVar.j(substring);
                    nxvVar.g(upperCase);
                } else {
                    nxvVar.j("#");
                    nxvVar.g("#");
                }
            }
        } catch (Exception unused) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "exception Pinyin.toPinyin,", bky.e(nxvVar.d()));
            nxvVar.j("#");
            nxvVar.g("#");
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void initData() {
        LogUtil.d("ContactSelectActivityPresenterImpl", "initData enter");
        ThreadPoolManager.d().execute(new Runnable() { // from class: nxw.4
            @Override // java.lang.Runnable
            public void run() {
                nxw nxwVar = nxw.this;
                nxwVar.d = nxwVar.getContacts();
                nxw.this.f15549a.sendEmptyMessage(200);
            }
        });
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void itemSelected(int i) {
        if (this.c) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "itemSelected mIsSearch");
            itemSelectDataChange(i, this.h);
        } else {
            LogUtil.d("ContactSelectActivityPresenterImpl", "itemSelected not mIsSearch");
            itemSelectDataChange(i, this.d);
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void selectAll() {
        if (this.c) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "selectAll mIsSearch");
            selectAllDataChange(this.h);
        } else {
            LogUtil.d("ContactSelectActivityPresenterImpl", "selectAll not mIsSearch");
            selectAllDataChange(this.d);
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void selectAllDataChange(List<nxv> list) {
        if (list == null || list.isEmpty() || a() == null) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "selectAllDataChange dataList is empty");
            return;
        }
        int i = 0;
        if (this.b) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "selectAllDataChange delete all");
            for (nxv nxvVar : list) {
                if (nxvVar.h()) {
                    nxvVar.d(false);
                }
            }
            a().changeHealthToolBarState(false);
        } else {
            LogUtil.d("ContactSelectActivityPresenterImpl", "selectAllDataChange all");
            int optionalQuantity = optionalQuantity();
            for (nxv nxvVar2 : list) {
                if (!nxvVar2.h()) {
                    if (optionalQuantity == 0) {
                        break;
                    }
                    nxvVar2.d(true);
                    i++;
                    if (i >= optionalQuantity) {
                        break;
                    }
                }
            }
            a().changeHealthToolBarState(true);
        }
        a().setCustomTitleBar(getSelectedCount());
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public int optionalQuantity() {
        int selectedCount = this.e - getSelectedCount();
        LogUtil.d("ContactSelectActivityPresenterImpl", "optionalQuantity selectCount : ", Integer.valueOf(selectedCount), "optionalQuantity mMaxSelectCount : ", Integer.valueOf(this.e), " getSelectedCount : ", Integer.valueOf(getSelectedCount()));
        return selectedCount;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public int getSelectedCount() {
        int i = 0;
        if (koq.b(this.d)) {
            ReleaseLogUtil.d("DEVMGR_ContactSelectActivityPresenterImpl", "getSelectedCount mContactDataList is empty");
            return 0;
        }
        Iterator<nxv> it = this.d.iterator();
        while (it.hasNext()) {
            if (it.next().h()) {
                i++;
            }
        }
        return i;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public int getInputType(String str) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "getInputType enter");
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "getInputType intPutType is INPUT_TYPE_ERR");
            return -1;
        }
        LogUtil.d("ContactSelectActivityPresenterImpl", "getInputType inputString : ", str);
        if (StringUtils.a(str)) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "getInputType intPutType is INPUT_TYPE_NUMBER");
            return 0;
        }
        if (StringUtils.c(str)) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "getInputType intPutType is INPUT_TYPE_ENGLISH");
            return 1;
        }
        if (!StringUtils.e(str)) {
            return 3;
        }
        LogUtil.d("ContactSelectActivityPresenterImpl", "getInputType intPutType is INPUT_TYPE_CHINESE");
        return 2;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void onEditTextInputChange(String str) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "onEditTextInputChange input is ", str);
        if (a() == null) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "getView is null");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.d("ContactSelectActivityPresenterImpl", "onEditTextInputChange input is empty");
            a().setAdapter(this.d);
            this.c = false;
        } else {
            this.h = getContactsByInputType(getInputType(str), str);
            a().setAdapter(this.h);
            this.c = true;
        }
        if (optionalQuantity() < 1) {
            a().changeHealthToolBarState(true);
        } else {
            a().changeHealthToolBarState(false);
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public List<nxv> getContactsByInputType(int i, String str) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "getContactsByInputType inputString : ", str, " inputType : ", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(this.d)) {
            ReleaseLogUtil.d("DEVMGR_ContactSelectActivityPresenterImpl", "getContactsByInputType mContactDataList is empty");
            return arrayList;
        }
        for (nxv nxvVar : this.d) {
            if (nxvVar.a() != null) {
                if (i == 0) {
                    if (nxvVar.a().replaceAll(" ", "").trim().contains(str.trim()) || nxvVar.d().contains(str)) {
                        arrayList.add(nxvVar);
                    }
                } else if (i == 1) {
                    if (nxvVar.f().contains(str.trim().toUpperCase(Locale.ENGLISH))) {
                        arrayList.add(nxvVar);
                    }
                } else if (i == 2) {
                    if (nxvVar.d().toUpperCase(Locale.ENGLISH).contains(str.trim().toUpperCase(Locale.ENGLISH))) {
                        arrayList.add(nxvVar);
                    }
                } else if (i == 3) {
                    String upperCase = nxvVar.d().toUpperCase(Locale.ENGLISH);
                    String upperCase2 = str.trim().toUpperCase(Locale.ENGLISH);
                    if (upperCase.contains(upperCase2)) {
                        arrayList.add(nxvVar);
                    } else if (nxvVar.f().contains(upperCase2)) {
                        arrayList.add(nxvVar);
                    } else {
                        LogUtil.c("ContactSelectActivityPresenterImpl", "no contact to be found");
                    }
                } else {
                    LogUtil.c("ContactSelectActivityPresenterImpl", "other type");
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void setIsSelectAll(boolean z) {
        this.b = z;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void itemSelectDataChange(int i, List<nxv> list) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "itemSelectDataChange position : ", Integer.valueOf(i));
        if (list == null || list.isEmpty() || a() == null) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "itemSelectDataChange dataList is empty");
            return;
        }
        if (i >= list.size()) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "itemSelectDataChange position out of list size");
            return;
        }
        int selectedCount = getSelectedCount();
        if (list.get(i).h()) {
            list.get(i).d(false);
            selectedCount--;
        } else if (selectedCount < this.e) {
            list.get(i).d(true);
            selectedCount++;
        } else {
            a().showUpperLimitMessage(this.e);
        }
        a().setCustomTitleBar(selectedCount);
        if (optionalQuantity() < 1 || (this.d != null && getSelectedCount() == this.d.size())) {
            a().changeHealthToolBarState(true);
        } else {
            a().changeHealthToolBarState(false);
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void saveDataToContactList(String str, String str2, String str3, String str4) {
        com.huawei.hwlogsmodel.LogUtil.a("ContactSelectActivityPresenterImpl", "saveDataToContactList enter");
        PhoneNumber phoneNumber = new PhoneNumber(str4, str3);
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(phoneNumber);
        this.j.add(new Contact(str2, str, arrayList));
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void getResultList() {
        this.j = new ArrayList(10);
        if (!koq.b(this.d)) {
            for (nxv nxvVar : this.d) {
                if (nxvVar.h()) {
                    saveDataToContactList(nxvVar.d(), nxvVar.b(), nxvVar.a(), TypedValues.Custom.NAME);
                }
            }
        }
        if (a() != null) {
            a().finishSelect(this.j);
        }
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void setMaxSelectCount(int i) {
        this.e = i;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void setOldContactList(ArrayList<String> arrayList) {
        this.f = arrayList;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public void setContactData(Map<String, nxv> map, Map<String, nxv> map2) {
        Cursor cursor = null;
        try {
            try {
                cursor = a().getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        cSm_(map, map2, cursor);
                    } while (cursor.moveToNext());
                }
                if (cursor == null) {
                    return;
                }
            } catch (SQLException unused) {
                LogUtil.e("ContactSelectActivityPresenterImpl", "setContactData exception");
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void cSm_(Map<String, nxv> map, Map<String, nxv> map2, Cursor cursor) {
        String str;
        String str2;
        String string = cursor.getString(cursor.getColumnIndex("data1"));
        if (TextUtils.isEmpty(string)) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "setContactData phoneNumber is empty");
            return;
        }
        nxv nxvVar = new nxv();
        String formatNumbers = formatNumbers(string);
        nxvVar.c(formatNumbers);
        String string2 = cursor.getString(cursor.getColumnIndex("display_name"));
        if (string2 == null) {
            str = formatNumbers;
        } else {
            str = string2 + formatNumbers;
        }
        if (b(str) || map.containsKey(str)) {
            LogUtil.b("ContactSelectActivityPresenterImpl", "setContactData key:", str);
            return;
        }
        nxvVar.a(cursor.getString(cursor.getColumnIndex("contact_id")));
        int i = cursor.getInt(cursor.getColumnIndex("data2"));
        if (i == 0) {
            str2 = cursor.getString(cursor.getColumnIndex("data3"));
        } else {
            str2 = (a() == null || !(ContactsContract.CommonDataKinds.Phone.getTypeLabel(a().getContext().getResources(), i, "") instanceof String)) ? "" : (String) ContactsContract.CommonDataKinds.Phone.getTypeLabel(a().getContext().getResources(), i, "");
        }
        nxvVar.d(str2);
        if (string2 == null) {
            nxvVar.e(formatNumbers);
            nxvVar.j("#");
            nxvVar.g("#");
            map2.put(str, nxvVar);
            return;
        }
        nxvVar.e(string2);
        setContactSortData(nxvVar);
        if ("#".equals(nxvVar.f()) && "#".equals(nxvVar.g())) {
            map2.put(str, nxvVar);
        } else {
            map.put(str, nxvVar);
        }
    }

    private void a(List<nxv> list) {
        LogUtil.d("ContactSelectActivityPresenterImpl", "setContactPhoto enter");
        try {
            File file = new File(BaseApplication.e().getFilesDir().getCanonicalPath() + File.separator + "contactPhoto" + File.separator);
            this.i = file;
            if (!file.exists()) {
                LogUtil.d("ContactSelectActivityPresenterImpl", "setContactData isMkdirs：", Boolean.valueOf(this.i.mkdirs()));
            }
        } catch (IOException e) {
            ReleaseLogUtil.c("DEVMGR_ContactSelectActivityPresenterImpl", "setContactData IOException:", ExceptionUtils.d(e));
        }
        Cursor cursor = null;
        try {
            try {
                cursor = a().getContext().getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[]{"contact_id", "data15"}, "mimetype='vnd.android.cursor.item/photo'", new String[0], null);
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        cSn_(list, cursor);
                    } while (cursor.moveToNext());
                }
                if (cursor == null) {
                    return;
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } catch (SQLException unused) {
            LogUtil.e("ContactSelectActivityPresenterImpl", "setContactData exception");
            if (cursor == null) {
                return;
            }
        }
        cursor.close();
    }

    private void cSn_(List<nxv> list, Cursor cursor) {
        String str;
        FileOutputStream fileOutputStream;
        File e;
        String string = cursor.getString(cursor.getColumnIndex("contact_id"));
        for (nxv nxvVar : list) {
            String b = nxvVar.b();
            if (!TextUtils.isEmpty(b) && b.equals(string)) {
                byte[] blob = cursor.getBlob(cursor.getColumnIndex("data15"));
                if (blob != null) {
                    FileOutputStream fileOutputStream2 = null;
                    try {
                        try {
                            e = FileUtils.e(this.i, b + ".png");
                            fileOutputStream = new FileOutputStream(e);
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = null;
                        }
                    } catch (FileNotFoundException e2) {
                        e = e2;
                    } catch (IOException e3) {
                        e = e3;
                    }
                    try {
                        fileOutputStream.write(blob);
                        fileOutputStream.flush();
                        str = e.getCanonicalPath();
                        IoUtils.e(fileOutputStream);
                    } catch (FileNotFoundException e4) {
                        e = e4;
                        fileOutputStream2 = fileOutputStream;
                        ReleaseLogUtil.c("DEVMGR_ContactSelectActivityPresenterImpl", "setContactPhotoPath FileNotFoundException:", ExceptionUtils.d(e));
                        IoUtils.e(fileOutputStream2);
                        str = "";
                        nxvVar.b(str);
                        return;
                    } catch (IOException e5) {
                        e = e5;
                        fileOutputStream2 = fileOutputStream;
                        ReleaseLogUtil.c("DEVMGR_ContactSelectActivityPresenterImpl", "setContactPhotoPath IOException:", ExceptionUtils.d(e));
                        IoUtils.e(fileOutputStream2);
                        str = "";
                        nxvVar.b(str);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        IoUtils.e(fileOutputStream);
                        throw th;
                    }
                    nxvVar.b(str);
                    return;
                }
                str = "";
                nxvVar.b(str);
                return;
            }
        }
    }

    private boolean b(String str) {
        for (int i = 0; i < this.f.size(); i++) {
            if (this.f.get(i).contains(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.ui.device.activity.selectcontact.selectmvp.ContactSelectActivityPresenter
    public String formatNumbers(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("ContactSelectActivityPresenterImpl", "formatNumbers number is empty");
            return "";
        }
        String formatNumber = PhoneNumberUtils.formatNumber(str, Locale.getDefault().getCountry());
        return TextUtils.isEmpty(formatNumber) ? str : formatNumber;
    }
}
