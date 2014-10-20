import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Infection {

	private String version;
	private Graph classrooms;
	private Map<Node, Integer> studentsPerTeacher;
	private int compare;

	public Infection(String version, Graph classrooms) {
		this.version = version;
		this.classrooms = classrooms;
		studentsPerTeacher = new HashMap<Node, Integer>();
	}

	public void totalInfection(String name) {
		if (classrooms.hasTeacher(name)) {
			dfsWrapper(name, true);
		} else if (classrooms.hasStudent(name)) {
			dfsWrapper(name, false);
		}

	}

	public void limitedInfection(int count) {
		updateNumberOfStudents();
		compare = -1;
		String teacher = findClassroom(count);
		if (teacher == null)
			System.out.println("Limited Infection Error.");
		if (compare == 0)
			totalInfection(teacher);
		else if (compare == 1) {
			Node teachingAssistant = classrooms.findBestStudent(classrooms
					.getTeacherNode(teacher));
			classrooms.createNewClassroom(teachingAssistant,
					classrooms.getTeacherNode(teacher), count);
			infectClassroom(teachingAssistant);
		}
		else{
			System.out.println("Limited infection for " + String.valueOf(count) + " people not possible.");
		}

	}

	private void updateNumberOfStudents() {
		for(Node teacherNode : classrooms.getTeacherNodes().values()){
			int classSize = classrooms.getAdjList().get(teacherNode).size();
			studentsPerTeacher.put(teacherNode, classSize);
		}
	}

	private String findClassroom(int count) {
		String teacher = null;
		for (@SuppressWarnings("rawtypes")
		Entry e : studentsPerTeacher.entrySet()) {
			if ((int) e.getValue() == (count - 1)) {
				compare = 0;
				return teacher = (String) ((Node) e.getKey()).getName();
			} else if ((int) e.getValue() > (count - 1)) {
				teacher = (String) ((Node) e.getKey()).getName();
				compare = 1;
			}
		}
		return teacher;
	}

	private void dfsWrapper(String name, boolean isTeacher) {
		boolean[] isMarkedArray = new boolean[classrooms.getNodeCount()];
		Arrays.fill(isMarkedArray, Boolean.FALSE);

		Node person;
		if (isTeacher)
			person = classrooms.getTeacherNode(name);
		else
			person = classrooms.getStudentNode(name);

		dfs(person, isMarkedArray);
	}

	private void dfs(Node person, boolean[] isMarkedArray) {
		isMarkedArray[person.getNodeNumber()] = true;
		infect(person);
		for (Node node : classrooms.getAdjList().get(person)) {
			if (!isMarkedArray[node.getNodeNumber()]) {
				dfs(node, isMarkedArray);
			}
		}
	}

	private void infect(Node person) {
		person.setInfected(true);
		person.setVersion(version);
	}

	private void infectClassroom(Node teacher) {
		infect(teacher);
		for (Node student : classrooms.getAdjList().get(teacher)) {
			infect(student);
		}
	}

}
