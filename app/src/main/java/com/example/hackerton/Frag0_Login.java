package com.example.hackerton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Frag0_Login extends Fragment {
    private String fragmentTag = "Login";

    public void setFragmentTag(String tag) {
        this.fragmentTag = tag;
    }

    public String getFragmentTag() {
        return fragmentTag;
    }

    private View view;

    private FirebaseAuth mAuth; //파이어베이스 인증.
    private EditText mEtEmail, mEtPwd; // 로그인 입력필드

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag0_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        mEtEmail = view.findViewById(R.id.et_email);
        mEtPwd = view.findViewById(R.id.et_pwd);

        Button btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 요청
                String email = mEtEmail.getText().toString();
                String pwd = mEtPwd.getText().toString();

                mAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null && user.isEmailVerified()) {
                                // 이메일 인증 완료
                                Toast.makeText(getContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                // 청년정책 프래그먼트로 이동
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Fragment1 frag2YouthPolicy = new Fragment1();
                                transaction.replace(R.id.fragment_container, frag2YouthPolicy);
                                transaction.commit();
                            } else {
                                // 이메일 인증 미완료
                                Toast.makeText(getContext(), "Please verify your email address", Toast.LENGTH_SHORT).show();
                                mAuth.signOut();
                            }
                        } else {
                            // 로그인 실패
                            Toast.makeText(getContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Button btn_register = view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Frag0_Register frag1_register = new Frag0_Register();
                transaction.replace(R.id.fragment_container, frag1_register);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
