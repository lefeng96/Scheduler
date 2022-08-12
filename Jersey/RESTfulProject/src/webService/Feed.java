package webService;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.ClassManager;
import model.CorequisiteManager;
import model.LoginManager;
import model.PathManager;
import model.PrerequisiteManager;
import model.StudentManager;
import objectLists.ClassList;
import objectLists.CorequisiteList;
import objectLists.LoginList;
import objectLists.PathList;
import objectLists.PrerequisiteList;
import objectLists.StudentList;
import objectLists.Roadmap;
import algorithm.Constraint;
import algorithm.DisplayClass;
import algorithm.FileInput;
import algorithm.SemesterCourses;

@Path("/WebService")
public class Feed {
	
	/*Use Endpoint.html to give a brief overview of the endpoints that are used and what they do.
	 *This has the same information as the html but this shows the code, obviously.*/
	

	/*------------------------------Beginning of Classes CRUD-------------------------------*/
	//Get all class Information from the database
	//Will not retrieve pre and co requisites or when they are offered since
	//prepared statement does not retrive that info from the other tables
	@GET
	@Path("/Classes")
	@Produces(MediaType.APPLICATION_JSON)
	public String feed() {
		String feeds = null;//Placeholder
		try {

			ArrayList<ClassList> feedData = null;
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.GetFeeds();
			
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
			
			//To test the return output
			//System.out.println(gson.toJson(feedData));
			
			feeds = gson.toJson(feedData);
		} catch (Exception e) {
			System.out.println("Error in getting classes."); // Console
		}
		return feeds;
	}

	//Get only Classes from that subject
	//Has the same problem as the previous one with the co/pre requisites and offered.
	@GET
	@Path("/Classes/{subject}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassesBySubject(@PathParam("subject") String subject) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<ClassList> feedData = null;
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.ClassSubject(subject);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Test
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("There was an error within the subclasses."); // Console
		}
		return feeds;
	}

	//Get a specific class from a database, returns on one class and all its information.
	//This one will return the co/pre requisites and when the class is offered. 
	@GET
	@Path("/Classes/classCode/{classCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassesByClassNumber(@PathParam("classCode") String classCode) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<ClassList> feedData = null;
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.ClassCode(classCode);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	//Get the information by the class name (ex. /Classes/className/BASIC Programming
	//Will not return co/pre requisites or offered - it was not implemented to this one.
	@GET
	@Path("/Classes/className/{className}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassesByClassName(@PathParam("className") String className) {
		String feeds = null;//Placeholder for output
		try {
			
			ArrayList<ClassList> feedData = null;
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.ClassName(className);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	//Get classes by the units of that course
	//Will return multiple courses
	//Will not return pre/co requisites or when they are offered.
	@GET
	@Path("/Classes/classUnit/{unit}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassesByClassUnit(@PathParam("unit") int unit) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<ClassList> feedData = null;
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.ClassUnit(unit);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	//Update an existing class in the database.
	//Did not implement adding of co/pre and offered fields.
	@PUT
	@Path("/Classes/{subject}/{classCode}/{className}/{unit}/{description}")
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateClasses(@PathParam("subject") String subject, @PathParam("classCode") String classCode,
			@PathParam("className") String className, @PathParam("unit") int unit,
			@PathParam("description") String description) {
		String feeds = null;//Placeholder
		Boolean feedData;//The return of the insertion from database.
		try {
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.UpdateClass(subject, classCode, className, unit, description);//returns as a boolean
			
			//Used to give feedback if successful or not to frontend
			if (feedData == true) {
				feeds = "Update successful";
			} else {
				feeds = "Update unsuccessful";
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	//Add a class into the database.
	//Did not implement adding of co/pre and offered fields.
	@POST
	@Path("/Classes/{subject}/{classCode}/{className}/{unit}/{description}")
	@Produces(MediaType.TEXT_PLAIN)
	public String AddClasses(@PathParam("subject") String subject, @PathParam("classCode") String classCode,
			@PathParam("className") String className, @PathParam("unit") int unit,
			@PathParam("description") String description) {
		
		String feeds = null;//Placeholder
		Boolean feedData;//Return value from the database insertion
		try {
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.AddClass(subject, classCode, className, unit, description);
			
			//Used to give feedback if successful or not to frontend
			if (feedData == true) {
				feeds = "Subject: "+subject + "\nClassCode: "+classCode+"\nClass Name: "+className+"\nUnits: "+ unit
						+"\nDescription: "+description;
			} else {
				feeds = "There was a problem inserting this class.";
			}
		}

		catch (Exception e) {
			System.out.println("There is an error in one of the files check the prepared statement for typos."); // Console
		}
		return feeds;
	}

	//Deletes the class from the database.
	@DELETE
	@Path("/Classes/{classCode}")
	@Produces(MediaType.TEXT_PLAIN)
	public String DeleteClasses(@PathParam("classCode") String classCode) {
		String feeds = null;//Place holder
		Boolean feedData;//Retrun value from database
		try {
			
			ClassManager projectManager = new ClassManager();
			feedData = projectManager.DeleteClass(classCode);
			
			//To give the frontend if the delete was successful or not.
			if (feedData == true) {
				feeds = "Delete successful-Class Code : " + classCode + " was deleted successfully";
			} else {
				feeds = "Delete unsuccessful from feeds.";
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error from one of the classes."); // Console
		}
		return feeds;
	}

	/*------------------------------End of Classes CRUD-------------------------------*/

	/*------------------------------Beginning of Students CRUD-------------------------------*/
	// Get all students
	@GET
	@Path("/Students")
	@Produces(MediaType.APPLICATION_JSON)
	public String feed1() {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<StudentList> feedData = null;
			StudentManager projectManager = new StudentManager();
			feedData = projectManager.GetFeeds();
			
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Get student by First name
	@GET
	@Path("/Students/fname/{fname}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudentByFname(@PathParam("fname") String fname) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<StudentList> feedData = null;
			StudentManager projectManager = new StudentManager();
			feedData = projectManager.fname(fname);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Get Student by last name
	@GET
	@Path("/Students/lname/{lname}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudentByLname(@PathParam("lname") String lname) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<StudentList> feedData = null;
			StudentManager projectManager = new StudentManager();
			feedData = projectManager.lname(lname);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Get Student by CIN
	@GET
	@Path("/Students/cin/{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStudentByCin(@PathParam("cin") int cin) {
		String feeds = null;//Placeholder
		
		try {
			
			ArrayList<StudentList> feedData = null;
			StudentManager projectManager = new StudentManager();
			feedData = projectManager.cin(cin);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Delete student or admin by CIN
	@DELETE
	@Path("/Students/delete/{cin}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteStudent(@PathParam("cin") int cin) {
		String feeds = null;//Placeholder
		boolean deleted;//Return type from the database in accessObjects class.
		
		try {
			StudentManager projectManager = new StudentManager();
			deleted = projectManager.deleteStudent(cin);
			
			//Return feedback is successful or not.
			if (deleted == true) {
				feeds = "Delete the student/admin with CIN of  " + cin;
			}
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Add a new Student
	@POST
	@Path("/Students/insert/{fname}/{lname}/{cin}")
	@Produces(MediaType.TEXT_PLAIN)
	public String newStudent(@PathParam("fname") String fname, @PathParam("lname") String lname,
			@PathParam("cin") int cin) {
		String feeds = null;//Placeholder
		boolean inserted;//Return type
		try {

			StudentManager projectManager = new StudentManager();
			inserted = projectManager.newStudent(fname, lname, cin);
			
			//Return Feedback
			if (inserted == true) {
				feeds = "Inserted student with first name of " + fname + ", last name of " + lname + ", cin of " + cin;
			}
		} catch (Exception e) {
			System.out.println("Exception Error from feeds"); // Console TEST
		}
		return feeds;
	}
	//Add a new Admin
		@POST
		@Path("/Admin/{cin}/{first}/{last}")
		@Produces(MediaType.TEXT_PLAIN)
		public String addAdmin(@PathParam("first") String first, @PathParam("last") String last,
				@PathParam("cin") int cin) {
			String feeds = null;//Placeholder
			boolean insertAdmin;//Return type from database
			try {
				
				StudentManager projectManager = new StudentManager();
				insertAdmin = projectManager.newAdmin(first, last, cin);
				
				//Return feedback if successful or not.
				if(insertAdmin == true) {
					feeds = "New Admin- Name: " +first +" "+ last + " with the cin of "+ cin;
				}
			}catch(Exception e) {
//				System.out.println("There was a problem, sorry."); // console TEST
				feeds = "There was an error in creating admin.";
			}
			return feeds;
		}

	// Edit a Student for only name either first or last.
	@PUT
	@Path("/Students/edit/{fname}/{lname}/{cin}")
	@Produces(MediaType.TEXT_PLAIN)
	public String editStudent(@PathParam("fname") String fname, @PathParam("lname") String lname,
			@PathParam("cin") int cin) {
		String feeds = null;//Placeholder
		boolean inserted;//Return type
		try {
			
			StudentManager projectManager = new StudentManager();
			inserted = projectManager.editStudent(fname, lname, cin);
			
			//Return feedback 
			if (inserted == true) {
				feeds = "Edited student info ";
			}
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}


	//Endpoints that need to be implemented.
	//Edit a student/admin password
	
	

	/*------------------------------End of Students CRUD-------------------------------*/

	/*------------------------------Beginning of Paths CRUD-------------------------------*/

	// Get all Paths
	@GET
	@Path("/Paths")
	@Produces(MediaType.APPLICATION_JSON)
	public String feed2() {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData = projectManager.GetPaths();
//			System.out.println(feedData);//TEST
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		System.out.println(feeds);
		return feeds;
	}

	// Get Path by Student Id/CIN
	@GET
	@Path("/Paths/{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPathsById(@PathParam("cin") int cin) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData = projectManager.StudentPath(cin);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Get Paths By Class Id/classCode 
	@GET
	@Path("/Paths/Students/{ClassCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPathsByClassId(@PathParam("ClassCode") String ClassCode) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData = projectManager.ClassPath(ClassCode);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}
		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Get Paths by Semester and the year.
	@GET
	@Path("/Paths/Semester/{semester}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPathsBySemester(@PathParam("semester") String semester, @PathParam("year") String year) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData = projectManager.SemesterPath(semester, year);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}
	//Returns a list of courses that the student has taken with grades.
	@GET
	@Path("/Paths/taken/{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String takenClasses(@PathParam("cin") String cin) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData= projectManager.TakenClasses(cin);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
			
		}catch(Exception e) {
			System.out.println("There is a problem somewhere in the subclasses.");
		}
		return feeds;
	}
	
	

	//Get the classes for a specific student with a specific semester and year.
	@GET
	@Path("/Paths/{cin}/{semester}/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCurrentStudentSemester(@PathParam("cin") String cin, @PathParam("semester") String semester,
			@PathParam("year") String year) {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData = projectManager.StudentSemesterPath(cin, semester, year);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Insert a new paths for student.
	@POST
	@Path("/Paths/{cin}/{classCode}/{semester}/{year}")
	@Produces(MediaType.TEXT_PLAIN)
	public String postPath(@PathParam("cin") String cin, @PathParam("classCode") String classCode,
			@PathParam("semester") String semester, @PathParam("year") String year) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass
		try {
			
			PathManager projectManager = new PathManager();
			inserted = projectManager.newPath(cin, classCode, semester, year);
			
			//Feedback that is returned to frontend.
			if (inserted == true) {
				feeds = "Insert paths for " + cin + " taking class code " + classCode + " in the semester of "
						+ semester + " " + year;
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Insert a new paths for student. Will be used for new students.
	@POST
	@Path("/Paths/{sid}/{classCode}/{semester}/{year}/{grade}")
	@Produces(MediaType.TEXT_PLAIN)
	public String postPathGrade(@PathParam("sid") String sid, @PathParam("classCode") String classCode,
			@PathParam("semester") String semester, @PathParam("year") String year, @PathParam("grade") String grade) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass
		try {
			
			PathManager projectManager = new PathManager();
			inserted = projectManager.newPathGrade(sid, classCode, semester, year, grade);
			
			//feedback that is returned to frontend.
			if (inserted == true) {
				feeds = "Insert paths for " + sid + " taking class code " + classCode + " in the semester of "
						+ semester + " " + year + " and got the grade of " + grade;
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Will edit those classes that need a grade after the semester is finished.
	@PUT
	@Path("/Paths/{sid}/{classCode}/{semester}/{year}/{grade}")
	@Produces(MediaType.TEXT_PLAIN)
	public String inputGrade(@PathParam("sid") String sid, @PathParam("classCode") String classCode,
			@PathParam("semester") String semester, @PathParam("year") String year, @PathParam("grade") String grade) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass.
		try {
			PathManager projectManager = new PathManager();
			inserted = projectManager.editGrade(sid, classCode, semester, year, grade);
			
			//feedback that is returned
			if (inserted == true) {
				feeds = "Edited path to insert a grade into " + classCode + " with a grade of " + grade;
			}
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// Delete a Path for the student
	@DELETE
	@Path("/Paths/{cin}/{classCode}/{semester}/{year}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePath(@PathParam("cin") String cin, @PathParam("classCode") String classCode,
			@PathParam("semester") String semester, @PathParam("year") String year) {
		String feeds = null;//Placeholder
		boolean deleted;//Return value from subclass
		try {
			PathManager projectManager = new PathManager();
			deleted = projectManager.deletePath(cin, classCode, semester, year);
//			System.out.println(classCode);//console TEST
			
			//Feedback that is returned
			if (deleted == true) {
				feeds = "Deleted from paths parameters " + cin + " taking class code " + classCode
						+ " in the semester of " + semester + " " + year;
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	//Returns a list of classes from the roadmap that the student is taking or needs to take.
	//These are courses that the student does not have a grade of completion.
	@GET
	@Path("/Paths/notTaken/{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String notFinished(@PathParam("cin") String cin) {
		String feeds = null;//Placeholder
		try {
			ArrayList<PathList> feedData = null;
			PathManager projectManager = new PathManager();
			feedData= projectManager.NotFinished(cin);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
			System.out.println(gson.toJson(feedData));
			feeds = gson.toJson(feedData);
			
		}catch(Exception e) {
			System.out.println("There is a problem in one of the subclasses.");
		}
		return feeds;
	}
	/*------------------------------End of Paths CRUD-------------------------------*/

	/*---------------------------Start of Corequisite CRUD--------------------------*/

	// Get all classes with corequisites
	@GET
	@Path("/Coreq")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoreq() {
		String feeds = null;//Placeholder
		try {
			ArrayList<CorequisiteList> feedData = null;
			CorequisiteManager projectManager = new CorequisiteManager();
			feedData = projectManager.getCoreq();

			// After retrieving we beautify the data to look nice in the JSON
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
		} catch (Exception e) {
			System.out.println("There was a problem retrieving the admin data.");
		}
		return feeds;
	}

	// Get all classes with corequisites
	@GET
	@Path("/Coreq/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoreqByCourse(@PathParam("course") String course) {
		String feeds = null;//Placeholder
		try {
			ArrayList<CorequisiteList> feedData = null;
			CorequisiteManager projectManager = new CorequisiteManager();
			feedData = projectManager.getCoreqByCourse(course);

			// After retrieving we beautify the data to look nice in the JSON
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
		} catch (Exception e) {
			System.out.println("There was a problem retrieving the admin data.");
		}
		return feeds;
	}

	// This is to post a new course with corequisites.
	@POST
	@Path("/Coreq/{course}/{corequisite}")
	@Produces(MediaType.TEXT_PLAIN)
	public String postCoreq(@PathParam("course") String course, @PathParam("corequisite") String corequisite) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass
		try {
			CorequisiteManager adminManager = new CorequisiteManager();
			inserted = adminManager.postCoreq(course, corequisite);
			
			//Return feedback from insertion
			if (inserted == true) {
				feeds = "Added the course :" + course + "with corequisite course-" + corequisite;
			} else {
				feeds = "There was a problem with adding the corequisite.";
			}
		} catch (Exception e) {
			System.out.println("Something bad happen.");
		}
		return feeds;
	}

	// Delete a Path for the student
	@DELETE
	@Path("/Coreq/{course}/{corequisite}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCoreq(@PathParam("course") String course, @PathParam("corequisite") String corequisite) {
		String feeds = null;//Placeholder
		boolean deleted;//Return value from subclass
		try {
			CorequisiteManager projectManager = new CorequisiteManager();
			deleted = projectManager.deleteCoreq(course, corequisite);
			
			//Feedback that is returned.
			if (deleted == true) {
				feeds = "Deleted from corequisites the course: " + course + " with the corequisite: " + corequisite;
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	/*-----------------------------End of Corequisite CRUD--------------------------*/

	/*---------------------------Start of Prerequisite CRUD-------------------------*/

	// Get all classes with prerequisites
	@GET
	//Did not work with prerequisite or prereq
	//Only worked with Pre
	@Path("/Pre")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPrereq() {
		String feeds = null;//Placeholder
		try {
			
			ArrayList<PrerequisiteList> feedData = null;
			PrerequisiteManager projectManager = new PrerequisiteManager();
			feedData = projectManager.getPrereq();

			// After retrieving we beautify the data to look nice in the JSON
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
		} catch (Exception e) {
			System.out.println("There was a problem retrieving the prereq data.");
		}
		return feeds;
	}

	// Get all classes with prerequisites
	@GET
	@Path("/Pre/{course}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPrereqByCourse(@PathParam("course") String course) {
		String feeds = null;//Placeholder
		try {
			ArrayList<PrerequisiteList> feedData = null;
			PrerequisiteManager projectManager = new PrerequisiteManager();
			feedData = projectManager.getPrereqByCourse(course);

			// After retrieving we beautify the data to look nice in the JSON
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
		} catch (Exception e) {
			System.out.println("There was a problem retrieving the admin data.");
		}
		return feeds;
	}

	// This is to post a new course with prerequisites.
	@POST
	@Path("/Pre/{course}/{prerequisite}")
	@Produces(MediaType.TEXT_PLAIN)
	public String postPrereq(@PathParam("course") String course, @PathParam("prerequisite") String prerequisite) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass
		try {
			PrerequisiteManager adminManager = new PrerequisiteManager();
			inserted = adminManager.postPrereq(course, prerequisite);
			
			//Return feedback
			if (inserted == true) {
				feeds = "Added the course :" + course + "with prerequisite course-" + prerequisite;
			} else {
				feeds = "There was a problem with adding the prerequisite.";
			}
		} catch (Exception e) {
			System.out.println("Something bad happen.");
		}

		return feeds;
	}

	// Delete a prerequisite class
	@DELETE
	@Path("/Prerequisite/{course}/{prerequisite}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePrereq(@PathParam("course") String course, @PathParam("prerequisite") String prerequisite) {
		String feeds = null;//Placeholder
		boolean deleted;//Return value from subclass
		try {
			PrerequisiteManager projectManager = new PrerequisiteManager();
			deleted = projectManager.deletePrereq(course, prerequisite);

			//Return feedback
			if (deleted == true) {
				feeds = "Deleted from prerequisites the course: " + course + " with the prerequisite: " + prerequisite;
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	/*------------------------------End of Prerequisite CRUD------------------------*/

	/*---------------------------Start of the Login CRUD----------------------------*/

	//Returns the login info of the student 
	//Use roles from the database to access this from the admin side. 
	//Used for testing only.
	@GET
	@Path("/Login/{cin}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("cin") int cin, @PathParam("password") String password) {
		String feeds = null;//Placeholder
		try {
			ArrayList<LoginList> feedData = null;
			LoginManager projectManager = new LoginManager();
//			System.out.println(cin + ": " + password);//Console TEST
			feedData = projectManager.GetFeeds(cin, password);
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
//			System.out.println(gson.toJson(feedData));//Console TEST
			feeds = gson.toJson(feedData);
		} catch (Exception e) {
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}

	// update password for the user
	// This will be used to update a password in the event that they forget or want
	// to change it.
	@PUT
	@Path("/Login/{cin}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(@PathParam("cin") String cin, @PathParam("password") String password) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass
		try {
			LoginManager projectManager = new LoginManager();
			inserted = projectManager.updateUser(cin, password);
			
			//Feedback that is returned
			if (inserted == true) {
				feeds = "We have updated admin information.";
			}
		} catch (Exception e) {
			System.out.println("There was a problem updating the admin information");
		}
		return feeds;
	}

	// Insert a new user
	@POST
	@Path("/Login/{cin}/{email}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String postLogin(@PathParam("cin") String cin, @PathParam("email") String email,
			@PathParam("password") String password) {
		String feeds = null;//Placeholder
		boolean inserted;//Return value from subclass
		try {
			LoginManager loginManager = new LoginManager();
			inserted = loginManager.newLogin(cin, email, password);
			
			//Feedback that is returned.
			if (inserted == true) {
				feeds = "The information for this student has been updated with a new password = " + password;
			}
		}

		catch (Exception e) {
			System.out.println("Exception Error for insert of new user"); // Console
		}
		return feeds;
	}

	/*------------------------------End of Login CRUD-------------------------------*/

	/*---------------------------------Algorithm get--------------------------------*/
	//Re-write to a GET and return a manually written JSON
	//Old one this is not used anymore since v.4.5
//	@GET
//	@Path("/Algorithm/{cin}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String insertFromDB(@PathParam("cin") String cin) {
//		String feeds = null;
//		boolean inserted;
//		try {
//			// PrerequisiteManager adminManager = new PrerequisiteManager();
//			Algorithm insertion = new Algorithm();
//			inserted = insertion.insertClasses(cin);
//			if (inserted == true) {
//				feeds = "Added the course :";
//			} else {
//				feeds = "There was a problem with adding the prerequisite.";
//			}
//		} catch (Exception e) {
//			System.out.println("Something bad happen.");
//		}
//		return feeds;
//	}
	
	//This will call the algorithm to get a roadmap that will show the classes needed to take to graduate.
	@GET
	@Path("/Algorithm/{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTestData(@PathParam("cin") String cin) {
		String feeds = null;//Placeholder
		int maxUnits = 17;//number of units to the roadmap
		
		try {
			//This is the path of the csv file that holds the classes info for the algorithm to use.
			//We did not implement the algorithm to utilize the database class info.
			//We have not tried storing in cs3- there might be a access issue since it is placed in root
			//or where the application of server is installed.
			String filePath = "/var/lib/tomcat7/Sample_Classes.csv";
			
			
			//get the classes that were taken
			List<String> ClassesTaken = new ArrayList<String>();
			PathManager projectManager = new PathManager();
			ClassesTaken= projectManager.taken(cin);
			
			//Algorithm classes are called here.
			FileInput f = new FileInput(filePath);
			DisplayClass DC = new DisplayClass(f.getListOfClassInfo(), ClassesTaken, maxUnits);
			List<SemesterCourses> sc = DC.Display(maxUnits);
			List<List<SemesterCourses>> listOfPaths = DC.getListOfPaths();
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
			feeds = gson.toJson(listOfPaths);
			
		} catch (Exception e) {
			//Print error stack trace since there are times that this does not work properly as intended.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			feeds = sStackTrace;
			System.out.println("Exception Error"); // Console
		}
		return feeds;
	}
	
	//This was going to be used to save the roadmap using a button or after a change/edit was made.
	//We had problems getting the consumes json even if syntax was correct here and in the frontend.
	@POST
	@Path("/Algorithm/{cin}/classinfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String saveRoadmap(@PathParam("cin") String cin, List<Roadmap> semesters) {
		String feeds = "success\n";
		
		
		/*The following block was used with hardcoded test values to see what it needed, will do, and what is the produced content.*/
		
		
/*		 Boolean checked = false;

		 //Insert test roadmaps into the list to read and check is its correctly
		// showned before posting.
		 List<Roadmap> roadmaps = new ArrayList<Roadmap>();
		
		
		 //This is what the Json will look like in object form
		 Roadmap road = new Roadmap();
		 road.setSemester("Fall");
		 String [] classes = {"CS 1010","CS 1012", "GE A"};
		 road.setClasses(classes);
		 road.setYear(2017);
		 roadmaps.add(road);
		
		 Roadmap r = new Roadmap();
		 r.setSemester("Spring");
		 String [] classe = {"CS 1013","MATH 1040", "GE B","GE C"};
		 r.setClasses(classe);
		 r.setYear(2018);
		
		 roadmaps.add(r);
		
		 //This is what I would be getting initially and I will store this in the
		 List<Roadmap> semesters commented out at the parameters
		 Gson gson = new
		 GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
		 System.out.println(gson.toJson(roadmaps));

		// Testing List for insertion into the database.
		// I will have this part become a try and catch and make a single database
		// connection by calling
		// PathsManager and then PathInfo
		// Will have a creating and closing database connection. This will be done in
		// PathInfo.java
*/
		//Once the Json was accepted it will read through it to produce prepared statements.
		//This was going to be level 2 Testing but we could not get to this since we didnt get level 1 working.
		
		//Inside the for loop was going to be the calling of needed methods from the classes that will insert the roadmap
		//into path in a single call instead of making one for each course. This would reduce connection time. 
		try {
			for (final Roadmap semester : semesters) {
				String[] c = semester.getClasses();
				// System.out.print("\n"+semester.getSemester() + " "+ semester.getYear());//console
				for (int i = 0; i < c.length; i++) {
					// Since we get the correct info here this will the place where I make the
					// prepared statement and insert the ? values appropriately
					feeds += "insert into paths(cin,classCode,semester,year) values(" + cin + "," + c[i] + ","
							+ semester.getSemester() + "," + semester.getYear() + ");\n";
					// System.out.print(c[i]+" ");//console

					// I will then close the connection and sent back the correct status code or
					// boolean number that the insertion was correctly done.
				}
			}

			// feeds= "there is an error.";
		} catch (Exception e) {
			//Print stack trace in case of error.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString();
			feeds = sStackTrace;
			System.out.println("Exception Error"); // Console
		}

		return feeds;
	}
	
	//This is the current working edit a class into the roadmap to get a new updated edited roadmap.
	//The modification that we wanted to implement is getting the entire current semester classes and previous classes from the semester
	//and only change the courses after that.
	
	//Currently if you use the edit any courses that are removed do not update. If you add a course to a semester that course
	//will show up where you put it but the courses you removed will also show up in the same place (if not affected by the course added)
	//We wanted to use consumes json but we could not get it to work. See line 1042
	@GET
	@Path("/Algorithm/{cin}/{semester}/{year}/{classCode}/")
	@Produces(MediaType.APPLICATION_JSON)
	public String doSomething4now(@PathParam("cin") String cin,@PathParam("semester") String semester,@PathParam("year") String year,
			@PathParam("classCode") String classCode) {
		String feeds = null;//Place holder
		int maxUnits = 15;
		try {
			
			//This is the path of the csv file that holds the classes info for the algorithm to use.
			//We did not implement the algorithm to utilize the database class info.
			//We have not tried storing in cs3- there might be a access issue since it is placed in root
			//or where the application of server is installed.
			String filePath = "/var/lib/tomcat7/Sample_Classes.csv";
			
			
			FileInput f = new FileInput(filePath);

			//A list of classes that are being edited
			List<String> constraintClasses = new ArrayList<String>();

//			//get the classes that were taken
			List<String> ClassesTaken = new ArrayList<String>();
			PathManager projectManager = new PathManager();
			ClassesTaken= projectManager.taken(cin);

			//parse the course 
			String classC = classCode.replaceAll("\\s", "-");
			String together =classC +" "+semester +" "+ year;
			
			//add it to the list
			constraintClasses.add(together);
			
			//Algorithm call starts from here
			Constraint c = new Constraint(f.getListOfClassInfo(), ClassesTaken, maxUnits, constraintClasses);
			List<List<SemesterCourses>> listOfPaths = c.getList();
			
			Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create();
			feeds = gson.toJson(listOfPaths);
			
		} catch (Exception e) {
			//Return the stack trace to frontend to see the problem.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); 
			feeds = sStackTrace;
		}
		return feeds;
	}
	
	//This will accept a JSON object to parse and send to the algorithm that will return a new
	//roadmap with the classes that were inserted as taking- leaving the previous semesters alone.
	//only modifying the current semester(that is being edited) and beyond.
	
	//This was used for testing purposes
	@GET
	@Path("/Algorithm/consume/{cin}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String Roadmap(@PathParam("cin")String cin, List<Roadmap> semesters) {
		//Get the JSON that is incoming from the front end HTTP call.
		String testing= "may work";
		
		//After consuming the json -print it.
		for (final Roadmap semester : semesters) {
	          System.out.println(semester);
	          //switch the data around.
	          //convert to a json and return.
		}
		//We wanted to return a Json because we wanted to implement modified edit.
		//Where certain classes can be locked (cannot be changed from there current place)
		//Since it will consume a json of the classes locked and produce a new roadmap with changes and the classes in the same spot.
		return testing;
	}
	
	/*--------------------------------End of Algorithm------------------------------*/

}