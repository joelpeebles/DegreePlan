import java.util.Vector;


public class ComputerScience implements Degree {
	
	Vector<Course> requiredCourses = null;
	Vector<Course> completedCourses = null;
	
	@Override
	public void degreeRequirements() {
		requiredCourses = new Vector<Course>();
		requiredCourses.addElement(new Course(4, "Distributed Systems", 3));
		requiredCourses.addElement(new Course(3, "Socket Implementation", 2));
		requiredCourses.addElement(new Course(0, "Logic", -1));
		requiredCourses.addElement(new Course(1, "Language Design", 0));
		requiredCourses.addElement(new Course(2, "Security", -1));
		requiredCourses.addElement(new Course(5, "GPU applications", 1));
	}

	public ComputerScience() {
		degreeRequirements();
	}

	@Override
	public Course getNextCourse() {
		
		Course nextCourse = null;
		boolean nextCourseFound = false;
		
		
		// For first course.  Need to find one with no prerequisites
		if(completedCourses == null) {
			for(int initialCourse = 0; initialCourse < requiredCourses.size(); initialCourse++) {
				if(requiredCourses.elementAt(initialCourse).getPrerequisite() < 0) {
					completedCourses = new Vector<Course>();
					completedCourses.addElement(requiredCourses.elementAt(initialCourse));
					System.out.println("Registered for course " + requiredCourses.elementAt(initialCourse).getCourseName() + "\n");
					requiredCourses.removeElementAt(initialCourse);
					return completedCourses.lastElement();
				}
				else {
					System.out.println("cannot take " + requiredCourses.elementAt(initialCourse).getCourseName() + " at this point\n");
				}
			}
		}
		else {
			// look through required courses for possible next course to register for
			int requiredCoursesElem = 0;
			boolean courseCompleted = false;

			requiredCourses:
			for(Course currentCourse : requiredCourses) {
				
				// look through completed courses to see if the current required course has been completed
				completedCourses:
				for(Course completedCourse : completedCourses) {
					if(completedCourse.getCourseId() == currentCourse.getCourseId()) {
						courseCompleted = true;
						requiredCoursesElem++;
						break requiredCourses;
					}
				}
				if(!courseCompleted) {
					boolean prereqSatisfied = false;
					if(currentCourse.getPrerequisite() < 0) {
						// no prerequisite.  
						prereqSatisfied = true;
					}
					else {
						
						// check that prerequisites are satisfied for this required course
						System.out.println("checking prerequisites for " + currentCourse.getCourseName());
						for(Course completed : completedCourses) {
							if(completed.getCourseId() == currentCourse.getPrerequisite()) {
								prereqSatisfied = true;
								break;
							}
						}
					}
					if(prereqSatisfied) {
						System.out.println("Registered for course " + currentCourse.getCourseName() + "\n");
						nextCourse = currentCourse;
						completedCourses.addElement(currentCourse);
						requiredCourses.remove(requiredCoursesElem);
						nextCourseFound = true;
						return nextCourse;
					}
					else {
						System.out.println("cannot take " + currentCourse.getCourseName() + " at this point\n");
						requiredCoursesElem++;
					}
				}
			}
			//if(!nextCourseFound) System.out.println(requiredCourses.size() + " more course to take");
			return nextCourse;
		}
		return nextCourse;
	}

}
