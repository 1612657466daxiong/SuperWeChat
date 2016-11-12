/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.superwechat.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.widget.EaseAlertDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.utils.CommonUtils;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.OkHttpUtils;


public class AddContactActivity extends BaseActivity {
    @InjectView(R.id.iv_back)
    ImageView mivBack;
    @InjectView(R.id.tv_title)
    TextView mtvTitle;
    @InjectView(R.id.et_username)
    EditText editText;
    @InjectView(R.id.btn_rig)
    Button mbtnRig;

    private String toAddUsername;
    private ProgressDialog progressDialog;
    private final String TAG = AddContactActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_add_contact);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        mivBack.setVisibility(View.VISIBLE);
        mtvTitle.setVisibility(View.VISIBLE);
        mtvTitle.setText(getString(R.string.menu_addfriend));
        mbtnRig.setVisibility(View.VISIBLE);
        mbtnRig.setText(getString(R.string.search));
    }


    public void searchContact() {
        final String name = editText.getText().toString();
        toAddUsername = name;
        if (TextUtils.isEmpty(name)) {
            new EaseAlertDialog(this, R.string.Please_enter_a_username).show();
            return;
        }

        progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.addcontact_search);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        searchAppUser();
    }

    private void searchAppUser() {
        NetDao.searchuser(this, toAddUsername, new OkHttpUtils.OnCompleteListener<Result>() {
            @Override
            public void onSuccess(Result result) {
                progressDialog.dismiss();
                if (result != null) {
                    if (result.isRetMsg()) {
                        Gson gson = new Gson();
                        User user = gson.fromJson(result.getRetData().toString(), User.class);
                        L.e(TAG,"user---"+user.getMUserName());
                        if (user != null) {
                            MFGT.gotofriendactivity(AddContactActivity.this, toAddUsername);
                        } else {
                            CommonUtils.showShortToast(R.string.search_failed);
                        }
                    } else {
                        CommonUtils.showShortToast(R.string.search_failed);
                    }
                } else {
                    CommonUtils.showShortToast(R.string.search_failed);
                }
            }

            @Override
            public void onError(String error) {
                L.e(TAG, "error=" + error);
                progressDialog.dismiss();
                CommonUtils.showShortToast(R.string.search_failed);
            }
        });
    }

    /**
     * add contact
     *
     * @param view
     */
    public void addContact(View view) {
        if (EMClient.getInstance().getCurrentUser().equals(editText.getText().toString())) {
            new EaseAlertDialog(this, R.string.not_add_myself).show();
            return;
        }

        if (SuperWeChatHelper.getInstance().getContactList().containsKey(editText.getText().toString())) {
            //let the user know the contact already in your contact list
            if (EMClient.getInstance().contactManager().getBlackListUsernames().contains(editText.getText().toString())) {
                new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
                return;
            }
            new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
            return;
        }


        new Thread(new Runnable() {
            public void run() {

                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String s = getResources().getString(R.string.Add_a_friend);
                    EMClient.getInstance().contactManager().addContact(toAddUsername, s);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }


    @OnClick({R.id.iv_back, R.id.btn_rig})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_rig:
                searchContact();
                break;
        }
    }


}
