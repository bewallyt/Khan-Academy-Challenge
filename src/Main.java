import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Parser parser = new Parser();

		Map<Node, List<Node>> adjList = parser.parseInput("Classrooms.txt");
		Map<String, Node> teacherNodes = parser.getTeacherNodes();
		Map<String, Node> studentNodes = parser.getStudentNodes();
		int nodeCount = parser.getNodeCount();

		Graph classrooms = new Graph(adjList, teacherNodes, studentNodes,
				nodeCount);

		while(!Command.isEnd()){
			Command.runCommand(Command.fetchCommand(), classrooms);
		}


	}

}
