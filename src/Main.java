import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws IOException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		
		Graph classrooms = createGraph();

		while(!Command.isEnd()){
			Command.runCommand(Command.fetchCommand(), classrooms);
			if(Command.isReverted()){
				classrooms = createGraph();
				Command.setRevert();
			}
		}


	}
	
	public static Graph createGraph() throws IOException{
		Parser parser = new Parser();

		Map<Node, List<Node>> adjList = parser.parseInput("Classrooms.txt");
		Map<String, Node> teacherNodes = parser.getTeacherNodes();
		Map<String, Node> studentNodes = parser.getStudentNodes();
		int nodeCount = parser.getNodeCount();

		Graph classrooms = new Graph(adjList, teacherNodes, studentNodes,
				nodeCount);
		
		return classrooms;
	}

}
