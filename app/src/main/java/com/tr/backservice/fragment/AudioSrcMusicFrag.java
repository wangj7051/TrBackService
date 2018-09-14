package com.tr.backservice.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tr.backservice.R;
import com.tr.backservice.activity.SwitchAudioSrcActivity;

public class AudioSrcMusicFrag extends BaseAudioSrcFragment {

    private View contentV;
    private SwitchAudioSrcActivity mAttachedActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAttachedActivity = (SwitchAudioSrcActivity) getActivity();
    }

    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentV = inflater.inflate(R.layout.frag_audiosrc_music, null);
        return contentV;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
    }
}
