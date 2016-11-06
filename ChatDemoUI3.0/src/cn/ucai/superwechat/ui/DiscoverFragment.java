package cn.ucai.superwechat.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ucai.superwechat.R;

/**
 * Created by clawpo on 2016/9/22.
 */
public class DiscoverFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dicover, container, false);
    }

//    //朋友圈点击事件
//    @OnClick(R.id.txt_pengyouquan)
//    public void txt_pengyouquan_Click() {
//        MFGT.gotoFeedActivity(getActivity());
//    }
//
//    //扫一扫点击事件
//    @OnClick(R.id.txt_saoyisao)
//    public void txt_saoyisao_Click() {
//        MFGT.gotoZXCode(getActivity());
//    }
//
//    //摇一摇点击事件
//    @OnClick(R.id.txt_yaoyiyao)
//    public void txt_yaoyiyao_Click() {
//        MFGT.gotoCommon(getActivity(), getString(R.string.discover_txt_yaoyiyao));
//    }
//
//    //附近的人点击事件
//    @OnClick(R.id.txt_nearby)
//    public void txt_nearby_Click() {
//        MFGT.startActivity(getActivity(), NearByActivity.class);
//    }
//
//    //漂流瓶点击事件
//    @OnClick(R.id.txt_piaoliuping)
//    public void txt_piaoliuping_Click() {
//        MFGT.gotoCommon(getActivity(), getString(R.string.discover_txt_piaoliuping));
//    }
//
//    //购物点击事件
//    @OnClick(R.id.txt_shop)
//    public void txt_shop_Click() {
//        MFGT.gotoWebView(getActivity(), getString(R.string.discover_txt_shop), Constants.SHOP_URL);
//    }
//
//    //游戏点击事件
//    @OnClick(R.id.txt_game)
//    public void txt_game_Click() {
//        MFGT.gotoWebView(getActivity(), getString(R.string.discover_txt_game), Constants.GAME_URL);
//    }
}
