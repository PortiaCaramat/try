package prog2.fingrp;
import java.io.*;
import java.nio.file.FileVisitResult;
import java.util.ArrayList;

public class DatReadWrite {

	/**
	 * <p>Used to read a .DAT file and returns a ArrayList of courses (semester). Because it returns an ArrayList, you can
	 * 	 use the methods provided by the ArrayList class to the {@code DatReadWrite.read()} method. For readability's sake,
	 * 	 it is sugggested to name a variable similar to the one shown below.
	 * </p>
	 *
	 * <br>
	 * 	{@code  ArrayList<Course> semester = DatReadWrite.read("FILE_PATH");}
	 * <br>
	 * <p> Example: we want to get the grades for CS 123. To access CS 123; first, you have to know which year and
	 * 	 which semester. Indicated by the class code, we know it is the third subject (3), in the second semester (2),
	 * 	 in the first year (1). Knowing the class code, we can locate the path to where CS 123 is at:
	 * 	 <pre>
	 * 	 	{@code src\prog2\fingrp\DATA\year1\read_only\sem2.dat}
	 * 	 </pre>
	 *
	 *
	 * 	 derive it to the method and we get:
	 * 	 <pre>
	 *{@code  ArrayList<Course> yr1sem2 = DatReadWrite.read("src\prog2\fingrp\DATA\year1\read_only\sem2.dat");}
	 *{@code  Course cs123 = yr1sem2.get(2); // 2 because it is located at index 2}
	 * 	 </pre>
	 *
	 * </p>
	 *
	 */
	public static ArrayList<Course> read(String fileIn) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileIn));

		ArrayList<Course> courses = (ArrayList<Course>) ois.readObject();
		ois.close();

		return courses;
	}

	/**
	 * <p>Used to read a .DAT file and returns a ArrayList of courses (semester). Because it returns an ArrayList, you can
	 * 	 use the methods provided by the ArrayList class to the {@code DatReadWrite.read()} method. For readability's sake,
	 * 	 it is sugggested to name a variable similar to the one shown below.
	 * </p>
	 *
	 * <br>
	 * 	{@code  ArrayList<Course> semester = DatReadWrite.read(FILE);}
	 * <br>
	 * <p> Example: we want to get the grades for CS 123. To access CS 123; first, you have to know which year and
	 * 	 which semester. Indicated by the class code, we know it is the third subject (3), in the second semester (2),
	 * 	 in the first year (1). Knowing the class code, we can locate the path to where CS 123 is at:
	 * 	 <pre>
	 * 	 	{@code src\prog2\fingrp\DATA\year1\read_only\sem2.dat}
	 * 	 </pre>
	 *
	 *
	 * 	 derive it to the method and we get:
	 * 	 <pre>
	 *{@code  ArrayList<Course> yr1sem2 = DatReadWrite.read(new File("src\prog2\fingrp\DATA\year1\read_only\sem2.dat"));}
	 *{@code  Course cs123 = yr1sem2.get(2); // 2 because it is located at index 2}
	 * 	 </pre>
	 *
	 * </p>
	 *
	 */
	public static ArrayList<Course> read(File fileIn) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileIn));

		ArrayList<Course> courses = (ArrayList<Course>) ois.readObject();
		ois.close();

		return courses;
	}

	/**
	 * <p>Use to read an entire directory of semesters (year)</p>
	 *
	 * Example:
	 * <pre>
	 *		DatReadWrite.readFolder(FILE)
	 * </pre>
	 * */
	static ArrayList<Course> readFolder(String stringFolder) throws IOException, ClassNotFoundException {
		File folder = new File(stringFolder);
		ArrayList<Course> allCourses = new ArrayList<>();

		int i = 1;
		for(File fileEntry: folder.listFiles()) {
			allCourses.addAll(read(fileEntry));
			i++;
		}

		return allCourses;
	}

	/**
	 * <p>Use to read an entire directory of semesters (year)</p>
	 *
	 * Example:
	 * <pre>
	 *		DatReadWrite.readFolder(FILE)
	 * </pre>
	 *
	 * */
	static ArrayList<Course> readFolder(File folder) throws IOException, ClassNotFoundException {
		ArrayList<Course> allCourses = new ArrayList<>();

		int i = 1;
		for(File fileEntry: folder.listFiles()) {
			allCourses.addAll(read(fileEntry));
			i++;
		}

		return allCourses;
	}

	/**
	 * <p>Used to write an ArrayList to a new .DAT file. </p>
	 *
	 * <p>Input the ArrayList of Courses (semester) and the destination on
	 * 	where the .dat file will go.</p>
	 *  <br>
	 * Example:
	 * <pre>
	 *     {@code DatReadWrite.write([ArrayList<Course> var, FILE_PATH]);}
	 * </pre>
	 * */
	public static void write(ArrayList<Course> input, String fileOut) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileOut));

		oos.writeObject(input);
		oos.close();
	}

	/**
	 * <p>Used to write an ArrayList to a new .DAT file. </p>
	 *
	 * <p>Input the ArrayList of Courses (semester) and the destination on
	 * 	where the .dat file will go.</p>
	 *  <br>
	 * Example:
	 * <pre>
	 *     {@code DatReadWrite.write([ArrayList<Course> var, FILE]);}
	 * </pre>
	 * */
	public static void write(ArrayList<Course> input, File fileOut) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileOut));

		oos.writeObject(input);
		oos.close();
	}

	/**
	 * Use to write multiple files to a directory
	 * */
	static void writeToFolder(String stringFolder) throws IOException {
		File folder = new File(stringFolder);

		int i = 1;
		for(final File fileEntry: folder.listFiles()) {
			write(toCourse(fileEntry), folder.getPath() + "\\sem" + i + ".dat");
			i++;
		}
	}

	/**
	 * Use to write multiple files to a directory
	 * */
	static void writeToFolder(File folder) throws IOException {
		int i = 1;
		for(final File fileEntry: folder.listFiles()) {
			write(toCourse(fileEntry), folder.getPath() + "\\sem" + i + ".dat");
			i++;
		}
	}

	private static ArrayList<Course> toCourse(File fileName) throws IOException{
		ArrayList<Course> courses = new ArrayList<>();

		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);

		try {
			String line;
			while ((line = br.readLine()) != null) {
				String[] courseString = line.split("\t");
				courses.add(extractToCourse(courseString));
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return courses;
	}

	private static Course extractToCourse(String[] data) {
		String code = data[0];
		String title = data[1];
		int units = Integer.parseInt(data[2]);
		return new Course(title, code, units);
	}


}
