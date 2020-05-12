package com.lu.manager_android;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;


public class LoginFragment extends Fragment {

    private Activity activity;
    private Button btLogin;
    private EditText etAccount,etPassword;
    private TextView tvAccountCheck,tvPasswordCheck;
    private CommonTask commonTask;
    private final static String TAG = "LoginFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etAccount = view.findViewById(R.id.etAccount);
        etPassword = view.findViewById(R.id.etPassword);
        tvAccountCheck = view.findViewById(R.id.tvAccountCheck);
        tvPasswordCheck = view.findViewById(R.id.tvPasswordCheck);
        btLogin = view.findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (Common.networkConnected(activity)) {
                    String url = Common.URL_SERVER + "ManagerServlet";//連伺服器
                    JsonObject jsonObject = new JsonObject();   //建一個物件
                    jsonObject.addProperty("action", "loginCheck");
                    jsonObject.addProperty("account", account);//幫key加屬性，送到伺服器
                    jsonObject.addProperty("password", password);
                    commonTask = new CommonTask(url, jsonObject.toString());//給伺服器位置，為傳送資料做準備
                    int loginResult = 0;
                    try {
                        String Login = commonTask.execute().get();//取得資料，啟動，呼叫execute，doinbackground會執行，呼叫get等開啟的新執行緒doinbackground，抓完資料回傳回來
//                        JsonObject jObject = new Gson().fromJson(Login, JsonObject.class);// Json 字串，直接轉成該類別的物件
                        loginResult = Integer.valueOf(Login);
                    } catch (Exception e) {
                        Log.e(TAG, "getString" + e.toString());
                    }

                    if (loginResult == 0 || account.isEmpty()) {
                        tvAccountCheck.setText("帳號輸入錯誤或帳號不存在");
                        tvPasswordCheck.setText("");
                    } else if (loginResult == 2 || password.isEmpty()) {
                        tvAccountCheck.setText("");
                        tvPasswordCheck.setText("密碼輸入錯誤");
                    } else if (loginResult == 1) {
                        Common.showToast(activity, "登入成功");
                        Navigation.findNavController(v)
                                .navigate(R.id.action_loginFragment_to_mainFragment);
                    } else {
                        Common.showToast(activity, "系統錯誤");
                    }
                }

            }
        });
    }
}
