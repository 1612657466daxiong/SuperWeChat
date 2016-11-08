package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/8.
 */
public class FriendProfileActivity extends BaseActivity {
    @InjectView(R.id.iv_back)
    ImageView mivBack;
    @InjectView(R.id.tv_title)
    TextView mtvTitle;
    @InjectView(R.id.iv_friend_avatar)
    ImageView mivFriendAvatar;
    @InjectView(R.id.tv_friend_nick)
    TextView mtvFriendNick;
    @InjectView(R.id.tv_friend_name)
    TextView mvFriendName;
    User user;
    @InjectView(R.id.bt_friend_addcontact)
    Button mbtFriendAddcontact;
    @InjectView(R.id.bt_friend_sendmessage)
    Button mbtFriendSendmessage;
    @InjectView(R.id.bt_friend_shiping)
    Button mbtFriendShiping;


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_friend_pro);
        ButterKnife.inject(this);
        user = (User) getIntent().getSerializableExtra("User");
        if (user == null) {
            finish();
        }
        initView();
    }

    private void setUserInfo() {
        EaseUserUtils.setCurrentAppUserAvatar(this, user.getMUserName(), mivFriendAvatar);
        EaseUserUtils.setCurrentAppUserNick(user.getMUserNick(), mtvFriendNick);
        EaseUserUtils.setAppUserNameWithNum(user.getMUserName(), mvFriendName);
    }

    private void initView() {
        mivBack.setVisibility(View.VISIBLE);
        mtvTitle.setVisibility(View.VISIBLE);
        mtvTitle.setText(getString(R.string.userinfo_txt_profile));
        setUserInfo();
        isFriend();
    }


    public void isFriend() {
        if (SuperWeChatHelper.getInstance().getappContactList().containsKey(user.getMUserName())) {
            mbtFriendSendmessage.setVisibility(View.VISIBLE);
            mbtFriendShiping.setVisibility(View.VISIBLE);
        } else {
            mbtFriendAddcontact.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.iv_back, R.id.bt_friend_addcontact, R.id.bt_friend_sendmessage, R.id.bt_friend_shiping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.bt_friend_addcontact:
                MFGT.gotoaddfriendActivity(this,user.getMUserName());
                break;
            case R.id.bt_friend_sendmessage:
                break;
            case R.id.bt_friend_shiping:
                break;
        }
    }
}
