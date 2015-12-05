/*
 * This program builds a degree based on course requirements
 *  and then walks through registering for classes for each term
 *  based on prerequisite courses that have already been taken. 
 */

public class Plan {

	public static void main(String[] args) {
		ComputerScience myDegree = new ComputerScience();
		int term = 1;
		while(myDegree.requiredCourses.size() > 0) {
			System.out.println("Term " + term + ":\n");
			Course nextCourse = myDegree.getNextCourse();
			term++;
		}
		System.out.println("graduated !");
	}

}
