package com.cookandroid.swp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    //서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://hsclassroom.dothome.co.kr/Register.php";
    private  Map<String, String> map;

    public RegisterRequest(String u_seq, String u_pw, String u_name, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("u_seq",u_seq);
        map.put("u_name",u_name);
        map.put("u_pw",u_pw);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
