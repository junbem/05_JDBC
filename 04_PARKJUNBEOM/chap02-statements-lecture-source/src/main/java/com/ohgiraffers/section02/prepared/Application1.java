package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;    // PreparedStatement 객체의 타입은 PreparedStatement탑이면서 Statement타입이기도 하다.
        ResultSet rset = null;             // ==> 상속구조이기 때문이고, 그래서 다형성을 적용할 수 있다. (업캐스팅)

        try {
            pstmt = con.prepareStatement("SELECT MENU_NAME, MENU_PRICE FROM tbl_menu");
            rset = pstmt.executeQuery();

            while(rset.next()) {
                System.out.printf("%s %d\n", rset.getString("MENU_NAME"), rset.getInt("MENU_PRICE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}

/*
* JDBC에서 SELECT문을 실행할 때 사용하는 메소드
Statement객체일경우
executeQuery(쿼리문);


PreparedStatement객체일경우
executeQuery();
* */
