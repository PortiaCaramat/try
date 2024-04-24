package prog2.finalgroup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.Scanner;

public class TesterMain {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Msi\\Downloads\\courses.txt"));
            String[] courseString = new String[10];
            String ln = "line";
            int i = 0;

            while ((ln = br.readLine()) != null) {
                courseString[i] = ln;
                i++;
            }
            br.close();

            Scanner scn = new Scanner(System.in);
            System.out.println("Menu \n[1] for Code Order \n[2] for Lexicographical Order");
            System.out.print("User choice: ");
            int choice = Integer.parseInt(scn.nextLine());
            switch (choice) {
                case 1 : ArrayList<Course> courses1 = new ArrayList<>();
                        for (String s : courseString) {
                            String[] arrOfStr = s.split("\t", 3);
                            String code = arrOfStr[0];
                            String title = arrOfStr[1];
                            int units = Integer.parseInt(arrOfStr[2]);
                            courses1.add(new Course(code, title, units));
                        }
                        for (Course sortedCourses : courses1) {
                            System.out.println(sortedCourses.toString());
                        }
                        break;
                case 2 : ArrayList<CourseNameSorter> courses2 = new ArrayList<>();
                            for (String s : courseString) {
                                String[] arrOfStr = s.split("\t", 3);
                                String code = arrOfStr[0];
                                String title = arrOfStr[1];
                                int units = Integer.parseInt(arrOfStr[2]);
                                courses2.add(new CourseNameSorter(code, title, units));
                            }
                        Collections.sort(courses2);
                        for (Course sortedCourses : courses2) {
                            System.out.println(sortedCourses.toString());
                        }
                    break;
            }
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("File not found.");
        } catch (IOException ioe) {
            System.out.println("The IO device has encountered an issue.");
        }
    }
}