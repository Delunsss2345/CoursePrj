/*
 * @ (#) CourseList.java 		1.0 	Aug 21, 2024
 *
 * Copyright (c) 2024 IUH. All right reserved	
 */

package iuh.fit.ktpm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/*
 * @description
 * @author: Pham Thanh Huy
 * @version: 1.0
 * @create:	Aug 21, 2024
 */

/* 
 * The CourseList class represents a list of courses in a university.
 * 
 * It allows users to add, delete, search, and sort courses.
 * @param courses the list of courses
 * @param count the number of courses
 * @param maxCourses the maximum number of courses
 * @param temp1 the first string
 * @param temp2 the second string
 * @param array an array containing the two strings converted to lowercase
 * 	
 */
public class CourseList {
	/*/ The maximum number of courses
	 * @param maxCourses the maximum number of courses
	 * @param courses the list of courses
	 */
	private Course[] courses;
	private int count = 0;
	
	/**
	 * Constructs a CourseList with the specified maximum number of courses.
	 *
	 * @param maxCourses the maximum number of courses
	 * @throws IllegalArgumentException if maxCourses is less than or equal to 0
	 */
	public CourseList(int maxCourses) {
		if (maxCourses <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0.");
        }
        courses = new Course[maxCourses];
    }

	/**
	 * Adds a course to the list.
	 *
	 * @param course the course to add
	 * @throws IllegalArgumentException if course is null
	 * @throws IllegalStateException    if the course list is full
	 */
	public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("The course cannot be null.");
        }
        if (count == courses.length) {
            throw new IllegalStateException("The course list is full.");
        }
        
		for (int i = 0; i < count; i++) {
			if (courses[i].getId().toLowerCase().equals(course.getId().toLowerCase())) {
				throw new IllegalArgumentException("The course ID is duplicated.");
			}
		}
        courses[count] = course;
        count++;
    }
	
	/** 
	 * Returns the courses in the list.
	 *
	 * @return the courses in the list
	 */
	public Course[] getCourse() {
		if (count == 0) {
			return null;
		}
		return courses;
	}
	
	/** 
	 * Converts two strings to lowercase.
	 *
	 * @param temp1 the first string
	 * @param temp2 the second string
	 * @return an array containing the two strings converted to lowercase
	 */
	public String[] lowerCaseList(String temp1 , String temp2) {
        String[] array = new String[2]; 
        array[0] = temp1.toLowerCase();
        array[1] = temp2.toLowerCase();
        return array; 
    }
	
	/** 
	 * Deletes a course from the list by its ID.
	 *
	 * @param id the ID of the course to delete
	 * @throws IllegalArgumentException if the course ID is null
	 * @throws IllegalArgumentException if the course ID is not found
	 */
	public void deleteCourse(String id) {
		int find = -1; 
		if (id == null) {
			throw new IllegalArgumentException("The course ID cannot be null.");
		}
		for (int i = 0; i < count; i++) {
			if (courses[i].getId().toLowerCase().equals(id.toLowerCase())) {
				find = 1;
				courses[i] = courses[count - 1];
				courses[count - 1] = null;
				count--;
				return;
			}
		}
		
		if (find == -1) {
			throw new IllegalArgumentException("The course ID is not found.");
		}
	}
	
	/** 
	 * Searches for a course by its ID.
	 *
	 * @param id the ID of the course to search for
	 * @return the course with the specified ID, or null if not found
	 * @throws IllegalArgumentException if the course ID is null
	 */
	public Course searchCourse(String id) {
		if (id == null) {
            throw new IllegalArgumentException("The course ID cannot be null.");
        }
        for (int i = 0; i < count; i++) {
        	
       
            if (courses[i].getId().toLowerCase().equals(id.toLowerCase())) {
                return courses[i];
            }
        }
        return null; 
	}
	
	/** 
	 * Searches for courses with titles containing a specified string.
	 *
	 * @param title the string to search for in the course titles
	 * @return an array of courses with titles containing the specified string, or null if none found
	 * @throws IllegalArgumentException if the title is null
	 */
	public Course[] searchCourseRelatively(String title) {
		if (title == null) {
			throw new IllegalArgumentException("The title cannot be null.");
		}
		
		if (this.count == 0) {
			return null;
		}
		
		int find = -1;
		ArrayList<Course> list = new ArrayList<>();
		for (int i = 0; i < this.count; i++) {
			String[] temp = lowerCaseList(courses[i].getTitle() , title);
			
			if (temp[0].contains(temp[1])) {
				find = 0;
				list.add(courses[i]); 
			}
		}
		
		if (find == -1) {
			return null;
		}
		
		Course[] array = new Course[list.size()];
		array = list.toArray(array);
		return array;
	}
	
	/** 
	 * Searches for courses in a specified department.
	 *
	 * @param department the department to search for
	 * @return an array of courses in the specified department, or null if none found
	 * @throws IllegalArgumentException if the department is null
	 */
	public Course[] searchDepartment(String department) {
		if (department == null) {
			throw new IllegalArgumentException("The department cannot be null.");
        }
		
		if (this.count == 0) {
			return null;
		}
		
		int find = -1;
		ArrayList<Course> list = new ArrayList<>();
		for (int i = 0; i < this.count; i++) {
			String[] temp = lowerCaseList(courses[i].getDepartment() , department);
			
			if (temp[0].equals(temp[1])) {
				find = 0;
				list.add(courses[i]); 
			}
		}
		
		if (find == -1) {
			return null;
		}
		
		Course[] array = new Course[list.size()];
		array = list.toArray(array);
		return array;
	}
	
	/** 
	 * Sorts the courses by name.
	 *
	 * @return an array of courses sorted by title, and then by ID if titles are the same
	 */
	public Course[] sortName() {
	    if (this.count == 0) {
	        return new Course[0]; 
	    }
	    
	    Course[] a = new Course[count];
	    int cnt = 0;
	    for (int i = 0; i < count; i++) {
	        if (courses[i] != null) {
	            a[cnt++] = courses[i];
	        }
	    }
	    
	    a = Arrays.copyOf(a, cnt);

	    Arrays.sort(a, new Comparator<Course>() {
	        @Override
	        public int compare(Course o1, Course o2) {
	        	if (!o1.getTitle().equals(o2.getTitle())) {
	        		return o1.getTitle().compareTo(o2.getTitle());
	        	} else {
					return o1.getId().compareTo(o2.getId());
				}
	        }
	    });
	    
	    return a;
	}
	
	/** 
	 * Finds courses with the maximum number of credits.
	 *
	 * @return an array of courses with the highest credit value
	 */
	public Course[] maxCredit() {
	    if (this.count == 0) {
	        return new Course[0]; 
	    }
	    int max = 0; 
	    ArrayList<Course> list = new ArrayList<>();
	    for (int i = 0; i < this.count; i++) {
			if (max < courses[i].getCredit() && courses[i] != null) {
				max = courses[i].getCredit();
			}
	    }
	    
	    for (int i = 0; i < this.count; i++) {
	    	if (max == courses[i].getCredit() && courses[i] != null) {
        		list.add(courses[i]); 
	    	}
	    }
	    
	    Course[] array = new Course[list.size()];
	    array = list.toArray(array);
	    return array;
	}

	/** 
	 * Counts the number of courses in each department and returns the department with the maximum number of courses.
	 *
	 * @return the department with the highest number of courses, or "f" if there are multiple departments with the highest number
	 */
	public String countDepartment() {
	    TreeMap<String, Integer> map = new TreeMap<>();
	    int maxCount = 0; 
	    String mostFrequentDepartment = "";
	    boolean isTie = false;
	    
	    if (this.count == 0) {
	        return null;
	    }
	    
	   
	    for (int i = 0; i < this.count; i++) {
	        String department = courses[i].getDepartment().toLowerCase();
	        map.put(department, map.getOrDefault(department, 0) + 1);
	    }
	    
	
	    for (Entry<String, Integer> entry : map.entrySet()) {
	        int count = entry.getValue();
	        if (count > maxCount) {
	            maxCount = count;
	            mostFrequentDepartment = entry.getKey();
	            isTie = false; 
	        } else if (count == maxCount) {
	            isTie = true;
	        }
	    }
	    
	    
	    if (isTie) {
	        return "f";
	    } else {
	        return mostFrequentDepartment;
	    }
	}

}

