package com.lu.manager_android;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainFragment extends Fragment {
    private Activity activity;
    private TextView tvUser,tvData,tvService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUser = view.findViewById(R.id.tvUser);
        tvData = view.findViewById(R.id.tvData);
        tvService = view.findViewById(R.id.tvService);
//        SpannableString content = new SpannableString("使用者資料管理");
//        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
//        tvUser.setText(content);
//        SpannableString content1 = new SpannableString("統計數據");
//        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
//        tvData.setText(content1);
//        SpannableString content2 = new SpannableString("客服管理");
//        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
//        tvService.setText(content2);
    }
}
