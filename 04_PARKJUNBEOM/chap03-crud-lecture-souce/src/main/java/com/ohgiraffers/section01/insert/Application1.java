package com.ohgiraffers.section01.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;
/*
* MVC2
* 각 계층마다 해야할 역할과 책임
* view : 사용자가 보는 화면, 기능의 결과에 따라 보여질 페이지
* controller : 사용자가 전달한 값을 정제해야할 경우가 있으면 처리,
*               적절한 service 계층으로 전달
*               결과에 따라 페이지(view)로 이동
* service : 연결객체 생성, 비즈니스 로직 사용, 트랜잭션 처리
* repository : DB와의 연결하고 결과값을 리턴까지만
* */
public class Application1 {
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO tbl_menu(menu_name, menu_price, category_code, orderable_status)"
                + " VALUES (?, ?, ?, ?)";
        int result = 0; // insert, update, delete 시에는 수행된 행의 개수를 int로 반환

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "봉골레청국장");
            pstmt.setInt(2, 12000);
            pstmt.setInt(3, 4);
            pstmt.setString(4, "Y");

            result = pstmt.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }
    }
}
/* JDBC에서 SELECT문을 실행할 때 사용하는 메소드
* Statement객체일경우
* executeQuery(쿼리문);
* INSERT, UPDATE, DELETE실행할 때 사용하는 메소드
* executeUpdate();
*
* PreparedStatement객체일경우
* executeQuery();
* INSERT, UPDATE, DELETE실행할 때 사용하는 메소드
* executeUpdate()
* */
