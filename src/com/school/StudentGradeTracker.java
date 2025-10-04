package com.school;


import com.school.service.MarksService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentGradeTracker {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final MarksService service = new MarksService();

        System.out.println("\n~~~~~~~~Welcome to Student Grade Tracker~~~~~~~~");
        int choice;
        do {
            System.out.println("\nChoose the option given below:\n");
            System.out.println("1. Add Student");
            System.out.println("2. Add Marks to Student");
            System.out.println("3. View All Students");
            System.out.println("4. Search Student by Roll No");
            System.out.println("5. Update Student Marks");
            System.out.println("6. Show Average, Highest and Lowest Marks");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice from above options: ");
            choice = Integer.parseInt(br.readLine());
            switch (choice) {
                case 1:
                    service.addStudent();
                    break;
                case 2:
                    service.addStudentMarks();
                    break;
                case 3:
                    service.viewStudents();
                    break;
                case 4:
                    service.viewStudent();
                    break;
                case 5:
                    service.updateStudent();
                    break;
                case 6:
                    service.showMetrics();
                    break;
                case 7:
                    System.out.println("Exiting .......");
                    break;
                default:
                    System.out.println("Invalid choice...");
                    break;
            }
        } while(choice != 7);
    }
}