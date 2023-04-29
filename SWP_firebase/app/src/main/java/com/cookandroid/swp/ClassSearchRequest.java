package com.cookandroid.swp;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ClassSearchRequest extends StringRequest {
    //서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://hsclassroom.dothome.co.kr/Classroom.php";

    private Map<String, String> map;

    public ClassSearchRequest(String c_seq, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("c_seq",c_seq);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }


}


