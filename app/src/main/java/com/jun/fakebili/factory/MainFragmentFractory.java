package com.jun.fakebili.factory;

import com.jun.fakebili.base.BaseFragment;
import com.jun.fakebili.fragment.MainCategoryFragmen;
import com.jun.fakebili.fragment.MainCommunicateFragment;
import com.jun.fakebili.fragment.MainDynamicFragment;
import com.jun.fakebili.fragment.MainHomeFragment;

import java.util.HashMap;

public class MainFragmentFractory {

    public static HashMap<Integer, BaseFragment> mainFragmentsMap = new HashMap<>();

    public static BaseFragment getFragmentByPositoin(int pos){
        BaseFragment fragment = mainFragmentsMap.get(pos);
        if(fragment == null){
            switch (pos){
                case 0:
                    fragment = new MainHomeFragment();
                    break;
                case 1:
                    fragment = new MainCategoryFragmen();
                    break;
                case 2:
                    fragment = new MainDynamicFragment();
                    break;
                case 3:
                    fragment = new MainCommunicateFragment();
                    break;

            }

            mainFragmentsMap.put(pos,fragment);
        }

        return fragment;
    }
}
