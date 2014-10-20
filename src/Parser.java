import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Parser {

	private BufferedReader reader;

	private Map<String, Node> teacherNodes;
	private Map<String, Node> studentNodes;
	
	private int nodeCount;
	
	public Parser(){
		teacherNodes = new HashMap<String, Node>();
		studentNodes = new HashMap<String, Node>();
		nodeCount = 0;
	}

	public Map<Node, List<Node>> parseInput(String fileName) throws IOException {

		Map<Node, List<Node>> adjList = new HashMap<Node, List<Node>>();

		reader = new BufferedReader(new FileReader(fileName));
		String line = null;

		while ((line = reader.readLine()) != null) {

			String[] teacherAndStudent = line.split(":", -1);
			String[] studentStrings = teacherAndStudent[1].split("\\s");

			Node teacher = createTeacherNode(teacherAndStudent[0]);
			List<Node> students = createStudentNodes(studentStrings);

			updateAdjList(teacher, students, adjList);

		}
		return adjList;
	}

	private int generateGrade() {
		Random r = new Random();
		int low = 60;
		int high = 100;
		int random = r.nextInt(high - low) + low;
		return random;
	}

	private Node createTeacherNode(String teacherName) {
		Node teacher = new Node(teacherName, true, nodeCount);
		nodeCount++;
		teacherNodes.put(teacherName, teacher);
		return teacher;
	}

	private List<Node> createStudentNodes(String[] studentStrings) {

		List<Node> students = new ArrayList<Node>();

		for (int i = 0; i < studentStrings.length; i++) {
			Node student = new Node(studentStrings[i], false, nodeCount);
			student.setGrade(generateGrade());
			nodeCount++;
			students.add(student);
			studentNodes.put(studentStrings[i], student);
		}
		return students;
	}

	private Map<Node, List<Node>> updateAdjList(Node teacher,
			List<Node> students, Map<Node, List<Node>> adjList) {
		adjList.put(teacher, students);

		List<Node> teachers = new ArrayList<Node>();
		teachers.add(teacher);

		for (int i = 0; i < students.size(); i++) {
			adjList.put(students.get(i), teachers);
		}

		return adjList;
	}

	public Map<String, Node> getTeacherNodes() {
		return teacherNodes;
	}

	public Map<String, Node> getStudentNodes() {
		return studentNodes;
	}

	public int getNodeCount() {
		return nodeCount;
	}
}
