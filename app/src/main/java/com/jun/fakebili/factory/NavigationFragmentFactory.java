package com.jun.fakebili.factory;

import com.jun.fakebili.R;
import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.fragment.NavFreeFowFragment;
import com.jun.fakebili.fragment.NavHistoryFragment;
import com.jun.fakebili.fragment.NavHomeFragment;
import com.jun.fakebili.fragment.NavLiveCenterFragment;
import com.jun.fakebili.fragment.NavLookLaterFragment;
import com.jun.fakebili.fragment.NavMyCollectFragment;
import com.jun.fakebili.fragment.NavMyFollowFragment;
import com.jun.fakebili.fragment.NavMyVipFragment;
import com.jun.fakebili.fragment.NavOfflineCacheFragment;
import com.jun.fakebili.fragment.NavVipOrderFragment;

import java.util.HashMap;
import java.util.Map;

public class NavigationFragmentFactory {

    public static Map<Integer,BaseFragment> navFragmentsMap = new HashMap<>();

    public static BaseFragment getFragmentById(int id){
        BaseFragment fragment = navFragmentsMap.get(id);
        if(fragment == null){
            switch (id){
                case R.id.nav_home:
                    fragment = new NavHomeFragment();
                    break;
                case R.id.nav_history:
                    fragment = new NavHistoryFragment();
                    break;
                case R.id.nav_offline_cache:
                    fragment = new NavOfflineCacheFragment();
                    break;
                case R.id.nav_my_collect:
                    fragment = new NavMyCollectFragment();
                    break;
                case R.id.nav_my_follow:
                    fragment = new NavMyFollowFragment();
                    break;
                case R.id.nav_look_later:
                    fragment = new NavLookLaterFragment();
                    break;
                case R.id.nav_live_center:
                    fragment = new NavLiveCenterFragment();
                    break;
                case R.id.nav_my_vip:
                    fragment = new NavMyVipFragment();
                    break;
                case R.id.nav_free_fow:
                    fragment = new NavFreeFowFragment();
                    break;

                case R.id.nav_vip_order:
                    fragment = new NavVipOrderFragment();
                    break;

            }

            navFragmentsMap.put(id,fragment);
        }

        return fragment;
    }

}
