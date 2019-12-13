package controller.manager;

import java.util.Vector;

import model.Student;

public class StudentManager {
	private static Vector<Student> students = new Vector<>();
	
	public static int addStudent(Student student)
	{
		return students.add(student) ? 1:0;
	}
	
	public void viewStudent()
	{
		
	}
	
	public static int deleteStudent(String matricNo)
	{
		int index = -1;

		for (int i = 0; i < students.size(); i++){
			Student temp = students.get(i);

			if(temp != null && (temp.getMatricNo() == matricNo)){
				
				index = i;
				break;
			}
		}

		return students.remove(index) != null ? 1 : 0;
	}
	
	public int updateStudent(Student student)
	{
		int index = -1;

		for (int i = 0; i < students.size(); i++)
		{
			Student temp = students.get(i);

			if(temp != null && (temp.getMatricNo() == student.getMatricNo()))
			{
				students.set(index, student);
				index = i;

				break;
			}
		}

		return index;
	}
}
