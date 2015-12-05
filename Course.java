
public class Course {
	
	private int _courseId;
	private String _courseName;
	private int _prereqquisite;
	
	Course(int courseId, String courseName, int prerequisite) {
		_courseId = courseId;
		_courseName = courseName;
		_prereqquisite = prerequisite;
	}
	
	int getCourseId() {
		return _courseId;
	}
	
	String getCourseName() {
		return _courseName;
	}
	
	int getPrerequisite() {
		return _prereqquisite;
	}

}
