// IBookManager.aidl
package com.lang.binderdemo.model;

// Declare any non-default types here with import statements
import com.lang.binderdemo.model.GoodBean;
interface IGoodManager {
    GoodBean getGoodBean();
    void setGoodBean(in GoodBean goodBean);
}
