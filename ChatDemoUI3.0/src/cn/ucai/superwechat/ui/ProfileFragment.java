package cn.ucai.superwechat.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.ucai.superwechat.Constant;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    @InjectView(R.id.ivAvatar)
    ImageView mivAvatar;
    @InjectView(R.id.tvNick)
    TextView mtvNick;
    @InjectView(R.id.tvUserName)
    TextView mtvUserName;
    @InjectView(R.id.layout_profile)
    RelativeLayout mlayoutProfile;
    @InjectView(R.id.tvMoney)
    TextView mtvMoney;
    @InjectView(R.id.tvSetting)
    TextView mtvSetting;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null&& savedInstanceState.getBoolean("isConflict",false))
            return;
        setUserInfo();
    }

    private void setUserInfo() {
        EaseUserUtils.setCurrentAppUserAvatar(getActivity(),mivAvatar);
        EaseUserUtils.setCurrentAppUserNick(mtvNick);
        EaseUserUtils.setCurrentAppUserNameWithNum(mtvUserName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.layout_profile, R.id.tvMoney, R.id.tvSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_profile:
                MFGT.gotoUserProfileActivity(getActivity());
                break;
            case R.id.tvMoney:
                //red packet code : 进入零钱页面
                RedPacketUtil.startChangeActivity(getActivity());
                break;
            case R.id.tvSetting:
                MFGT.gotoSettingsActivity(getActivity());
                break;
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(((MainActivity)getActivity()).isConflict){
            outState.putBoolean("isConflict", true);
        }else if(((MainActivity)getActivity()).getCurrentAccountRemoved()){
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserInfo();
    }
}
