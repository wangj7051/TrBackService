package com.tr.backservice.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.tr.backservice.R;
import com.tr.backservice.adapter.VPFragStateAdapter;
import com.tr.backservice.fragment.AudioSrcBtMusicFrag;
import com.tr.backservice.fragment.AudioSrcMusicFrag;
import com.tr.backservice.fragment.AudioSrcRadioAmFrag;
import com.tr.backservice.fragment.AudioSrcRadioFmFrag;
import com.tr.backservice.fragment.BaseAudioSrcFragment;

import java.util.ArrayList;
import java.util.List;

public class SwitchAudioSrcActivity extends BaseFragActivity {
    //TAG
    private static final String TAG = "SwitchAudioSrcActivity";

    //---- Widgets in this Activity ----
    private ViewPager viewPager;

    //---- Variables in this Activity ----
    private List<BaseAudioSrcFragment> mListFrags;
    private VPFragStateAdapter mVpFragStateAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_audiosrc);
        init();
    }

    private void init() {
        // -- Widgets --
        viewPager = findViewById(R.id.viewpager);
        loadPages();
    }

    private void loadPages() {
        mListFrags = new ArrayList<>();
        mListFrags.add(new AudioSrcRadioFmFrag());
        mListFrags.add(new AudioSrcRadioAmFrag());
        mListFrags.add(new AudioSrcMusicFrag());
        mListFrags.add(new AudioSrcBtMusicFrag());

        mVpFragStateAdapter = new VPFragStateAdapter(getSupportFragmentManager());
        mVpFragStateAdapter.setListFrags(mListFrags);
        viewPager.setAdapter(mVpFragStateAdapter);
    }
}