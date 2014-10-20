public class Node implements Comparable<Node>{
	
	private String name;
	private String version;
	private boolean isTeacher;
	private boolean isInfected;
	private int nodeNumber;
	
	private int grade;
	
	public Node(String name, boolean isTeacher, int nodeNumber){
		this.name = name;
		this.isTeacher = isTeacher;
		this.isInfected = false;
		this.nodeNumber = nodeNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	@Override
	public int compareTo(Node o) {
		return this.getGrade() - o.getGrade();

	}
	
	public int getGrade() {
		return grade;
	}
	
	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public boolean isTeacher() {
		return isTeacher;
	}

	public void setTeacher(boolean isTeacher) {
		this.isTeacher = isTeacher;
	}

}
