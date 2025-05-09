package com.huawei.ui.device.activity.selectcontact.selectmvp;

import defpackage.nxv;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public interface ContactSelectActivityPresenter {
    void contactsSort(List<nxv> list);

    String formatNumbers(String str);

    List<nxv> getContacts();

    List<nxv> getContactsByInputType(int i, String str);

    int getInputType(String str);

    void getResultList();

    int getSelectedCount();

    void initData();

    void itemSelectDataChange(int i, List<nxv> list);

    void itemSelected(int i);

    void onEditTextInputChange(String str);

    int optionalQuantity();

    void saveDataToContactList(String str, String str2, String str3, String str4);

    void selectAll();

    void selectAllDataChange(List<nxv> list);

    void setContactData(Map<String, nxv> map, Map<String, nxv> map2);

    void setContactSortData(nxv nxvVar);

    void setIsSelectAll(boolean z);

    void setMaxSelectCount(int i);

    void setOldContactList(ArrayList<String> arrayList);
}
