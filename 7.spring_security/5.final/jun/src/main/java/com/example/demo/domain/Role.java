package com.example.demo.domain;
public enum Role {
    ROLE_USER, ROLE_ADMIN, ROLE_GUEST;

    public static boolean isValidRole(String role) {
        try {
            Role.valueOf(role); // 이 메서드가 예외 없이 실행되면 유효한 값
            return true;
        } catch (IllegalArgumentException e) {
            return false; // 예외 발생 시 유효하지 않은 값
        }
    }
}

