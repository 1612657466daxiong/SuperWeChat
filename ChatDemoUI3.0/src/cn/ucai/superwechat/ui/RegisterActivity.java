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
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.net.NetDao;
import cn.ucai.superwechat.utils.I;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;
import cn.ucai.superwechat.utils.OkHttpUtils;

/**
 * register screen
 *
 */
public class RegisterActivity extends BaseActivity {
    @InjectView(R.id.register_username)
    EditText userNameEditText;
    @InjectView(R.id.usernick)
    EditText usernickEditText;
    @InjectView(R.id.password)
    EditText passwordEditText;
    @InjectView(R.id.confirm_password)
    EditText confirmPwdEditText;
    @InjectView(R.id.btn_gister)
    Button btnGister;
    String username;
    String pwd;
    String confirm_pwd ;
    String nick;
    Context contex;
   ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_register);
        ButterKnife.inject(this);
        contex=this;
    }
    @OnClick(R.id.btn_gister)
    public void register(View view) {
        username = userNameEditText.getText().toString().trim();
        pwd = passwordEditText.getText().toString().trim();
        confirm_pwd = confirmPwdEditText.getText().toString().trim();
        nick = usernickEditText.getText().toString().trim();
        if (userNameEditText.getText().toString().trim()==null) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            userNameEditText.requestFocus();
            return;
        }else if(usernickEditText.getText().toString().trim()==null){
            Toast.makeText(this, "User nick cannot be empty!", Toast.LENGTH_SHORT).show();
        }
        else if (passwordEditText.getText().toString().trim()==null) {
            Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return;
        } else if (confirmPwdEditText.getText().toString().trim()==null) {
            Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            confirmPwdEditText.requestFocus();
            return;
        } else if (!pwd.equals(confirm_pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Two_input_password), Toast.LENGTH_SHORT).show();
            return;
        }
        pd = new ProgressDialog(this);
        pd.setMessage(getResources().getString(R.string.Is_the_registered));
        pd.show();
        rigisterDao();


    }

    private void rigisterDao() {
        NetDao.register(contex, username, nick, pwd, new OkHttpUtils.OnCompleteListener<Result>() {
            @Override
            public void onSuccess(Result result) {
                if (result!=null){
                    if (result.isRetMsg()){
                        Log.i("main",result.toString());
                        Log.i("main",username);
                        registerEM(username,pwd);
                    }else if(result.getRetCode()== I.MSG_REGISTER_FAIL){
                        Toast.makeText(RegisterActivity.this, R.string.MSG_102, Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    public void registerEM(final String username, final String pwd){
        new Thread(new Runnable() {
            public void run() {
                try {
                    // call method in SDK
                    EMClient.getInstance().createAccount(username, pwd);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (!RegisterActivity.this.isFinishing())
                                pd.dismiss();
                            // save current user
                            SuperWeChatHelper.getInstance().setCurrentUserName(username);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                            MFGT.gotoLoginActivity(RegisterActivity.this);
                            finish();
                        }
                    });
                } catch (final HyphenateException e) {

                    runOnUiThread(new Runnable() {
                        public void run() {
                             if (!RegisterActivity.this.isFinishing())
                            pd.dismiss();
                            int errorCode = e.getErrorCode();
                            if (errorCode == EMError.NETWORK_ERROR) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    unregister();
                }

            }
        }).start();
    }

    private void unregister() {
        NetDao.unregister(contex, username, new OkHttpUtils.OnCompleteListener<Result>(){
            @Override
            public void onSuccess(Result result) {
                if (result!=null){
                    if (result.isRetMsg()){
                        L.e("取消注册成功");
                    }else {
                        L.e("取消注册失败");
                    }
                }
            }

            @Override
            public void onError(String error) {
                L.e("取消注册异常"+error);
            }
        });
    }

    public void back(View view) {
        finish();
    }

}
