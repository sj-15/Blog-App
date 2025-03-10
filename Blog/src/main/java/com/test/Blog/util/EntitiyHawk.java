package com.test.Blog.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EntitiyHawk {
    public ResponseEntity genericSuccess() {
        Map map = new HashMap();
        map.put("status", true);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity genericSuccess(Object data) {
        Map map = new HashMap();
        map.put("status", true);
        map.put("data", data);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity genericError() {
        Map map = new HashMap();
        map.put("status", false);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity genericError(Object data) {
        Map map = new HashMap();
        map.put("status", false);
        map.put("data", data);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity genericResponse(Object data) {
        Map map = new HashMap();
        map.put("data", data);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity genericResponse(boolean status) {
        Map map = new HashMap();
        map.put("status", status);
        return ResponseEntity.ok(map);
    }

    public ResponseEntity genericResponse(boolean status, Object data) {
        Map map = new HashMap();
        map.put("status", status);
        map.put("data", data);
        return ResponseEntity.ok(map);
    }
}
