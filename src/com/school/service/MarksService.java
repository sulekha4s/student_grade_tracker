package com.school.service;

import com.school.model.Marks;
import com.school.model.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MarksService {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private final Map<Integer, Student> studentMap = new HashMap<>();

    public void addStudent() throws IOException {
        boolean isDone = false;
        System.out.println("\nProvide details for the new student.");
        while(!isDone) {
            System.out.print("Roll number: ");
            int rollNumber = Integer.parseInt(br.readLine());

            if (rollNumber <= 0) {
                System.out.println("Student roll number must be a positive integer number.\n");
                continue;
            }

            if (studentMap.containsKey(rollNumber)) {
                System.out.println("Student with roll number " + rollNumber + " already exists.");
                return;
            }
            System.out.print("Student name: ");
            String name = br.readLine();
            studentMap.put(rollNumber, new Student(rollNumber, name));
            System.out.println("\nStudent added successfully.");
            isDone = true;
        }
    }

    public void addStudentMarks() throws IOException {
        if (studentMap.isEmpty()){
            System.out.println("No student record found.");
            return;
        }

        System.out.println("\nProvide student detail to add marks.");
        boolean isDone = false;
        while (!isDone) {
            System.out.print("Roll number:");
            int rollNumber = Integer.parseInt(br.readLine());

            if (rollNumber <= 0) {
                System.out.println("Student roll number must be a positive integer number.\n");
                continue;
            }

            if (!studentMap.containsKey(rollNumber)) {
                System.out.println("Student with roll number " + rollNumber + " doesn't exists.");
                return;
            }

            Student student = studentMap.get(rollNumber);
            System.out.println("Enter marks for " + student.getName());

            System.out.print("Maths:");
            int maths = Integer.parseInt(br.readLine());

            System.out.print("Physics:");
            int physics = Integer.parseInt(br.readLine());

            System.out.print("Chemistry:");
            int chemistry = Integer.parseInt(br.readLine());

            Marks marks = new Marks(physics, chemistry, maths);

            student.setMarks(marks);
            studentMap.put(rollNumber, student);
            System.out.println("\nMarks added successfully for roll number " + rollNumber);
            isDone = true;
        }
    }

    public void updateStudent() throws IOException {
        if (studentMap.isEmpty()){
            System.out.println("No student record found.");
            return;
        }
        System.out.println("\nProvide student detail to update marks.");
        boolean updated = false;
        while (!updated) {
            System.out.print("Roll number:");
            int rollNumber = Integer.parseInt(br.readLine());

            if (rollNumber <= 0) {
                System.out.println("Student roll number must be a positive integer number.\n");
                continue;
            }

            Student student  = studentMap.get(rollNumber);
            if (student == null) {
                System.out.println("Student with roll number " + rollNumber + " not found. Enter a valid roll number.");
            } else {
                System.out.println("Enter new marks for " + student.getName());

                System.out.println("Maths:");
                int newMath = Integer.parseInt(br.readLine());

                System.out.println("Physics:");
                int newPhysics = Integer.parseInt(br.readLine());

                System.out.println("Chemistry:");
                int newChemistry = Integer.parseInt(br.readLine());

                System.out.println("\nReview the new marks entered:\n");
                System.out.println("Old Math Marks: " + student.getMarks().getMaths() + " New Maths Marks: " + newMath);
                System.out.println("Old Physics Marks: " + student.getMarks().getPhysics() + " New Maths Marks: " + newPhysics);
                System.out.println("Old Chemistry Marks: " + student.getMarks().getChemistry() + " New Maths Marks: " + newChemistry);

                System.out.println("Enter Y/N to confirm the new marks:");
                String confirm = br.readLine();

                Student updatedStudent;
                if (confirm.equalsIgnoreCase("y")) {
                    Marks updatedMarks = new Marks(newPhysics, newChemistry, newMath);
                    updatedStudent = new Student(rollNumber, student.getName(), updatedMarks);
                    studentMap.put(rollNumber, updatedStudent);
                    updated = true;
                    System.out.println("Marks added successfully for roll number " + student.getRollNumber());
                }
            }
        }
    }

    public void viewStudents(){
        if (studentMap.isEmpty()){
            System.out.println("No student record found.");
            return;
        }
        System.out.println("\nStudent details:");
        System.out.println("--------------");

        System.out.println("Roll No.\tName\tPhysics\tChemistry\tMaths");
        for (Map.Entry<Integer, Student> entry: studentMap.entrySet()){
            Student student = entry.getValue();
            System.out.println(student.getRollNumber()+ "\t\t"+student.getName()+"\t\t"+student.getMarks().getPhysics()+"\t\t"+student.getMarks().getChemistry()+"\t\t"+student.getMarks().getMaths());
        }

        System.out.println("\nNOTE: Subject with -1 marks indicates that marks are not added yet for the student.");
    }

    public void viewStudent() throws IOException {
        if (studentMap.isEmpty()){
            System.out.println("No student record found.");
            return;
        }
        System.out.println("\nProvide student detail to view marks.");
        boolean isDone = false;
        while (!isDone) {
            System.out.print("Roll number:");
            int rollNumber = Integer.parseInt(br.readLine());

            if (rollNumber <= 0) {
                System.out.println("Student roll number must be a positive integer number.\n");
                continue;
            }

            if (!studentMap.containsKey(rollNumber)) {
                System.out.println("Student with roll number " + rollNumber + " doesn't exists.");
                return;
            }

            Student student = studentMap.get(rollNumber);
            System.out.println("Roll Number\tName\tPhysics\tChemistry\tMaths");
            System.out.println(student.getRollNumber() + "\t" + student.getName() + "\t" + student.getMarks().getPhysics() + "\t" + student.getMarks().getChemistry() + "\t" + student.getMarks().getMaths());
            System.out.println("\nNOTE: Subject with -1 marks indicates that marks are not added yet for the student.");
            isDone = true;
        }
    }

    public void showMetrics(){
        if (studentMap.isEmpty()){
            System.out.println("No student record found.");
            return;
        }
        List<Student> studentWithMarks = studentMap.values().stream()
                // filter students whose marks are not added yet
                .filter(student -> student.getMarks().getMaths() != -1).collect(Collectors.toList());

        List<Integer> mathMarks=  studentWithMarks.stream()
                .map(student -> student.getMarks().getMaths()).collect(Collectors.toList());

        List<Integer> physicsMarks=  studentWithMarks.stream()
                .map(student -> student.getMarks().getPhysics()).collect(Collectors.toList());

        List<Integer> chemistryMarks=  studentWithMarks.stream()
                .map(student -> student.getMarks().getChemistry()).collect(Collectors.toList());

        IntSummaryStatistics mathSummary = mathMarks.stream().mapToInt(Integer::intValue).summaryStatistics();
        IntSummaryStatistics physicsSummary = physicsMarks.stream().mapToInt(Integer::intValue).summaryStatistics();
        IntSummaryStatistics chemistrySummary= chemistryMarks.stream().mapToInt(Integer::intValue).summaryStatistics();

        System.out.println("\nMetrics for each subjects:\n");

        System.out.println("Physics:");
        System.out.println("--------");
        System.out.println("Average: " + physicsSummary.getAverage());
        System.out.println("Highest: " + physicsSummary.getMax());
        System.out.println("Lowest: " + physicsSummary.getMin());

        System.out.println("\nChemistry:");
        System.out.println("--------");
        System.out.println("Average: " + chemistrySummary.getAverage());
        System.out.println("Highest: " + chemistrySummary.getMax());
        System.out.println("Lowest: " + chemistrySummary.getMin());

        System.out.println("\nMaths:");
        System.out.println("--------");
        System.out.println("Average: " + mathSummary.getAverage());
        System.out.println("Highest: " + mathSummary.getMax());
        System.out.println("Lowest: " + mathSummary.getMin());
    }

}
