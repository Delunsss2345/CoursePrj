/*
 * @ (#) Course.java 		1.0 	Aug 21, 2024
 *
 * Copyright (c) 2024 IUH. All right reserved	
 */

package iuh.fit.ktpm;

/*
 * @description
 * @author: Pham Thanh Huy
 * @version: 1.0
 * @create:	Aug 21, 2024
 */

/**
 * The Course class represents a course in a university.
 */
public class Course {
	 /* 
	  * The unique identifier of the course
	  * The name of the course
	  * The number of credits
	  * The department responsible for the course
	  * @param id the unique identifier of the course
	  * @param title the name of the course
	  * @param credit the number of credits
	  * @param department the department responsible for the course
	  */
		private String id;
	    private String title;
	    private int credit;
	    private String department;

	    /**
	     * Constructs a Course with the specified ID, title, credit, and department.
	     *
	     * @param id the unique identifier of the course (must be at least 3 characters long and contain only letters or digits)
	     * @param title the name of the course (must not be empty)
	     * @param credit the number of credits (must be greater than 0)
	     * @param department the department responsible for the course
	     * @throws IllegalArgumentException if id is null or does not meet the criteria, title is null or empty, or credit is less than or equal to 0
	     */
	    public Course(String id, String title, int credit, String department) {
	        setId(id);
	        setTitle(title);
	        setCredit(credit);
	        this.department = department;
	    }

	    /**
	     * Default constructor.
	     */
	    public Course() {
	        this("", "", 0, "");
	    }

	    /**
	     * Returns the ID of the course.
	     *
	     * @return the ID of the course
	     */
	    public String getId() {
	        return this.id;
	    }

	    /**
	     * Sets the ID of the course.
	     *
	     * @param id the new ID of the course
	     * @throws IllegalArgumentException if id is null, does not contain only letters or digits, or is less than 3 characters long
	     */
	    public void setId(String id) {
	        if (id == null || id.trim().length() < 3 || !id.matches("[a-zA-Z0-9]+")) {
	            throw new IllegalArgumentException("ID must have at least 3 characters and contain only letters or digits");
	        }
	        this.id = id;
	    }

	    /**
	     * Returns the title of the course.
	     *
	     * @return the title of the course
	     */
	    public String getTitle() {
	        return this.title;
	    }

	    /**
	     * Sets the title of the course.
	     *
	     * @param title the new title of the course
	     * @throws IllegalArgumentException if title is null or empty
	     */
	    public void setTitle(String title) {
	        if (title == null || title.trim().isEmpty()) {
	            throw new IllegalArgumentException("Title must not be empty");
	        }
	        this.title = title;
	    }

	    /**
	     * Returns the number of credits of the course.
	     *
	     * @return the number of credits
	     */
	    public int getCredit() {
	        return this.credit;
	    }

	    /**
	     * Sets the number of credits of the course.
	     *
	     * @param credit the new number of credits
	     * @throws IllegalArgumentException if credit is less than or equal to 0
	     */
	    public void setCredit(int credit) {
	        if (credit <= 0) {
	            throw new IllegalArgumentException("Credit must be greater than 0");
	        }
	        this.credit = credit;
	    }

	    /**
	     * Returns the department responsible for the course.
	     *
	     * @return the department responsible for the course
	     */
	    public String getDepartment() {
	        return this.department;
	    }

	    /**
	     * Sets the department responsible for the course.
	     *
	     * @param department the new department
	     */
	    public void setDepartment(String department) {
	        this.department = department;
	    }
	    
	    
	    /**
	     * Returns a string representation of the course.
	     *
	     * @return a string representation of the course
	     */
	    @Override
	    public String toString() {
	        return String.format("%-10s %-25s %-6d %-20s",
	                id.toUpperCase(), title, credit, department.toUpperCase());
	    }
	
	}

