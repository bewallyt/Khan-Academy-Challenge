import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Command {

	private static final String[] graphCommands = {"revert", "print"};

	private static Scanner userInputScanner;
	private static boolean isEnd = false;

	public static String[] fetchCommand() {
		userInputScanner = new Scanner(System.in);
		String input = userInputScanner.nextLine();
		String[] command = input.split("\\s+");
		return command;
	}

	public static void runCommand(String[] command, Graph classrooms)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		if (command.length == 1) {
			checkEnd(command[0]);
			if (isEnd)
				return;
			runGraphCommands(command, classrooms);
		}

		else if (command.length == 3) {
			runInfectionCommands(command, classrooms);
		}

		else {
			System.out.println("Invalid Command");
		}

	}

	private static void runGraphCommands(String[] command, Graph classrooms)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String methodName = command[0].toLowerCase();
		if (Arrays.asList(graphCommands).contains(methodName)) {

			Method commandMethod = classrooms.getClass().getDeclaredMethod(
					methodName);

			commandMethod.invoke(classrooms);
		} else {
			System.out.println("Invalid Command");
		}

	}

	private static void runInfectionCommands(String[] command, Graph classrooms)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String methodName = command[0].toLowerCase();
		String version = command[1].toLowerCase();

		if (methodName.equals("limited") || methodName.equals("total")) {

			Infection newestInfection = new Infection(version, classrooms);

			if (methodName.equals("total")) {
				Method commandMethod = newestInfection.getClass()
						.getDeclaredMethod(methodName + "Infection",
								String.class);

				commandMethod.invoke(newestInfection, command[2]);
			} else {
				Method commandMethod = newestInfection.getClass()
						.getDeclaredMethod(methodName + "Infection",
								int.class);

				commandMethod.invoke(newestInfection, Integer.parseInt(command[2]));
			}

		}

		else {
			System.out.println("Invalid Command");
		}
	}

	private static void checkEnd(String end) {
		if (end.toLowerCase().equals("end")) {
			isEnd = true;
		}
	}

	public static boolean isEnd() {
		return isEnd;
	}
}
