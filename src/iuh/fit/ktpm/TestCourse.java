/*
 * @ (#) TestCourse.java 		1.0 	Aug 21, 2024
 *
 * Copyright (c) 2024 IUH. All right reserved	
 */

package iuh.fit.ktpm;

import java.util.InputMismatchException;
/*
 * @description
 * @author: Pham Thanh Huy
 * @version: 1.0
 * @create:	Aug 21, 2024
 */
import java.util.Scanner;

/**
 * The TestCourse class provides a command-line interface for managing courses.
 * It allows users to add, display, delete, search, and sort courses.
 */
public class TestCourse {
    /**
     * The main method is the entry point of the application.
     * It presents a menu to the user and performs actions based on user input.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseList courseList = new CourseList(10); // Create a CourseList with a maximum of 10 courses
 
        while (true) {
            System.out.println("== Menu ==");
            System.out.println("1. Thêm khóa học");
            System.out.println("2. Hiển thị danh sách khóa học");
            System.out.println("3. Xóa khóa học");
            System.out.println("4. Tìm khóa học theo ID");
            System.out.println("5. Tìm khóa học theo tên (tương đối)");
            System.out.println("6. Tìm khóa học theo phòng ban");
            System.out.println("7. Sắp xếp khóa học theo tên");
            System.out.println("8. Tìm tín chỉ lớn nhất");
            System.out.println("9. Tìm phòng ban có số lượng khóa học nhiều nhất");
            System.out.println("0. Thoát");

            int option = -1;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Chọn một tùy chọn: ");
                try {
                    option = scanner.nextInt();
                    validInput = true; 
                } catch (InputMismatchException e) {
                    System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
                    scanner.next(); 
                }
            }

            scanner.nextLine();

            switch (option) {
                case 1: {
                    String id, title, department;
                    int credit = 0;
                    boolean validInputs = false;

                    System.out.print("Nhập ID khóa học: ");
                    id = scanner.nextLine();
                    System.out.print("Nhập tên khóa học: ");
                    title = scanner.nextLine();
                    System.out.print("Nhập phòng ban: ");
                    department = scanner.nextLine();

                    while (!validInputs) {
                        System.out.print("Nhập tín chỉ: ");
                        try {
                            credit = scanner.nextInt();
                            validInputs = true; 
                        } 
                        catch (InputMismatchException e) {
                            System.out.println("Credit must be greater than 0");
                            scanner.next(); 
                        }
                    }

                    scanner.nextLine(); 

                    try {
                        Course newCourse = new Course(id, title, credit, department);
                        courseList.addCourse(newCourse);
                        System.out.println("Khóa học đã được thêm.");
                    } 
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } 
                    catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }
                case 2: {
                    Course[] courses = courseList.getCourse();
                    if (courses != null) {
                        System.out.println("Danh sách khóa học: ");
                        System.out.println(String.format("%-10s %-25s %-6s %-20s", "ID", "TITLE", "CREDIT", "DEPARTMENT"));

                        for (Course course : courses) {
                            if (course != null) {
                                System.out.println(course);
                            }
                        }
                    }
                    else {
                        System.out.println("Danh sách khóa học trống.");
                    }

                    break; 
                }
                case 3:
                    System.out.print("Nhập ID khóa học cần xóa: ");
                    String deleteId = scanner.nextLine();
                    try {
                        courseList.deleteCourse(deleteId);
                        System.out.println("Khóa học đã được xóa.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Lỗi: Khóa học không tồn tại.");
                    }
                    break;

                case 4:
                    System.out.print("Nhập ID khóa học cần tìm: ");
                    String searchId = scanner.nextLine();
                    Course course = courseList.searchCourse(searchId);
                    if (course != null) {
                        System.out.println("Khóa học tìm thấy ");
                        System.out.println(String.format("%-10s %-25s %-6s %-20s", "ID", "TITLE", "CREDIT", "DEPARTMENT"));
                        System.out.println(course);
                    } else {
                        System.out.println("Không tìm thấy khóa học với ID đã cho.");
                    }
                    break;

                case 5:
                    System.out.print("Nhập tên khóa học (tương đối): ");
                    String searchTitle = scanner.nextLine();
                    Course[] coursesByTitle = courseList.searchCourseRelatively(searchTitle);
                    if (coursesByTitle != null && coursesByTitle.length > 0) {
                        System.out.println(String.format("%-10s %-25s %-6s %-20s", "ID", "TITLE", "CREDIT", "DEPARTMENT"));
                        for (Course c : coursesByTitle) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("Không tìm thấy khóa học với tiêu chí đã cho.");
                    }
                    break;

                case 6:
                    System.out.print("Nhập phòng ban cần tìm: ");
                    String searchDepartment = scanner.nextLine();
                    Course[] coursesByDepartment = courseList.searchDepartment(searchDepartment);
                    if (coursesByDepartment != null && coursesByDepartment.length > 0) {
                        System.out.println(String.format("%-10s %-25s %-6s %-20s", "ID", "TITLE", "CREDIT", "DEPARTMENT"));
                        for (Course c : coursesByDepartment) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("Không tìm thấy khóa học với phòng ban đã cho.");
                    }
                    break;

                case 7:
                    Course[] sortedCourses = courseList.sortName();
                    if (sortedCourses != null) {
                        System.out.println(String.format("%-10s %-25s %-6s %-20s", "ID", "TITLE", "CREDIT", "DEPARTMENT"));
                        for (Course c : sortedCourses) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("Danh sách khóa học trống.");
                    }
                    break;

                case 8:
                    Course[] coursesByCredit = courseList.maxCredit();
                    if (coursesByCredit != null && coursesByCredit.length > 0) {
                        System.out.println(String.format("%-10s %-25s %-6s %-20s", "ID", "TITLE", "CREDIT", "DEPARTMENT"));
                        for (Course c : coursesByCredit) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("Danh sách khóa học trống.");
                    }
                    break;

                case 9:
                    String departmentMax = courseList.countDepartment();
                    if (departmentMax != null && !departmentMax.isEmpty() && !departmentMax.equals("f")) {
                        System.out.println("Phòng ban có số lượng khóa học nhiều nhất là: " + departmentMax);
                    } 
                    else if (departmentMax == null) {
                        System.out.println("Danh sách khóa học trống.");
                    }
                    else {
                        System.out.println("Không có phòng ban nào nhiều nhất chỉ có bằng nhau");
                    }
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }
}

