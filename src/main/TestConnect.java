package main;

import db.MYSQLControl;

public class TestConnect {
    public static void main(String[] args){
        System.out.println(MYSQLControl.getConnection());

//
        System.out.println(MYSQLControl.insertProvince("北京"));
//        System.out.println(MYSQLControl.insertProvince("北京"));
//        System.out.println(MYSQLControl.insertProvince("天津"));

        System.out.println(MYSQLControl.selectProvince("北京"));

        System.out.println(MYSQLControl.close());
    }
}
