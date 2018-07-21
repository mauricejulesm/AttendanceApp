/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceapp;

import controllers.AttendanceController;

/**
 *
 * @author Jules Maurice Mulisa
 */
public class AttendanceApp {

    public static void run() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Attendance App\n" + "==============\n");

        AttendanceController attendanceController = new AttendanceController();
        attendanceController.run();

        System.out.println("Thank you for using Attendance App. Good bye.\n");

    }

}
