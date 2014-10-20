import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Graph {

	private Map<Node, List<Node>> adjList;
	private Map<String, Node> teacherNodes;
	private Map<String, Node> studentNodes;
	private int nodeCount;

	public Graph(Map<Node, List<Node>> adjList, Map<String, Node> teacherNodes,
			Map<String, Node> studentNodes, int nodeCount) {
		this.adjList = adjList;
		this.teacherNodes = teacherNodes;
		this.studentNodes = studentNodes;
		this.nodeCount = nodeCount;
	}

	public boolean hasTeacher(String name) {
		return teacherNodes.containsKey(name);
	}

	public boolean hasStudent(String name) {
		return studentNodes.containsKey(name);
	}

	public Node getTeacherNode(String name) {
		return teacherNodes.get(name);
	}

	public Map<String, Node> getTeacherNodes() {
		return teacherNodes;
	}

	public Node getStudentNode(String name) {
		return studentNodes.get(name);
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public Map<Node, List<Node>> getAdjList() {
		return adjList;
	}

	public void revert() {
	}

	@SuppressWarnings("unchecked")
	public void print() {
		for (@SuppressWarnings("rawtypes")
		Entry classroom : adjList.entrySet()) {
			if (teacherNodes.containsKey(((Node) classroom.getKey()).getName())) {

				System.out.print(((Node) classroom.getKey()).getName());

				if (((Node) classroom.getKey()).isInfected()) {
					System.out.print(" ("
							+ ((Node) classroom.getKey()).getVersion() + ")");
				}
				System.out.println();
				System.out.println("----------");
				for (Node students : (List<Node>) classroom.getValue()) {
					System.out.print(students.getName());
					if (students.isInfected()) {
						System.out.print(" (" + students.getVersion() + ")");
					}
					System.out.println();
				}
				System.out.println();
			}

		}
	}

	public Node findBestStudent(Node teacher) {
		List<Node> students = adjList.get(teacher);
		int highestGrade = 0;
		Node bestStudent = null;

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getGrade() > highestGrade) {
				highestGrade = students.get(i).getGrade();
				bestStudent = students.get(i);
			}
		}
		return bestStudent;
	}

	public void createNewClassroom(Node teachingAssistant, Node oldTeacher,
			int infectCount) {

		studentToTeacher(teachingAssistant, oldTeacher);

		List<Node> newerStudents = new ArrayList<Node>();

		for (int i = 0; i < infectCount - 1; i++) {
			newerStudents.add(adjList.get(oldTeacher).remove(i));
		}
		adjList.put(teachingAssistant, newerStudents);

	}

	private void studentToTeacher(Node teachingAssistant, Node oldTeacher) {
		teachingAssistant.setTeacher(true);
		studentNodes.remove(teachingAssistant.getName());
		teacherNodes.put(teachingAssistant.getName(), teachingAssistant);

		adjList.get(oldTeacher).remove(teachingAssistant);

	}

}
