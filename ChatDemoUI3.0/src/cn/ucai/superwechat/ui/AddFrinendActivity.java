package cn.ucai.superwechat.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/8.
 */
public class AddFrinendActivity extends BaseActivity {
    private final String TAG=AddFrinendActivity.class.getSimpleName();
    @InjectView(R.id.iv_back)
    ImageView mivBack;
    @InjectView(R.id.tv_title)
    TextView mtvTitle;
    @InjectView(R.id.edit_addfriend)
    EditText meditAddfriend;
    @InjectView(R.id.btn_rig)
    Button mbtnRig;
    String userName;
    String msg;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_addfriend);
        ButterKnife.inject(this);
        userName = getIntent().getStringExtra("UserName");
        if (userName==null){
            finish();
        }
        initView();
    }

    private void initView() {
        mbtnRig.setVisibility(View.VISIBLE);
        mtvTitle.setVisibility(View.VISIBLE);
        mivBack.setVisibility(View.VISIBLE);
        mtvTitle.setText(getString(R.string.add_friend));
        msg=getString(R.string.addcontact_send_msg_prefix)
                + EaseUserUtils.getCurrentAppUserInfo().getMUserNick();
        meditAddfriend.setText(msg);
    }

    @OnClick({R.id.iv_back,R.id.btn_rig})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_rig:
                sendaddfriend();
                break;
        }
    }

    private void sendaddfriend() {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.addcontact_adding));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String string = meditAddfriend.getText().toString().trim();
                    EMClient.getInstance().contactManager().addContact(userName , string);
                    L.e(TAG,"add Username"+userName);
                    L.e(TAG,"add MSG"+msg);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                            MFGT.finish(AddFrinendActivity.this);
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                            MFGT.finish(AddFrinendActivity.this);
                        }
                    });
                }
            }
        }).start();
    }

}
