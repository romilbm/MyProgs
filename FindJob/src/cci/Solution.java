package cci;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    private static class Class {
        int id;
        int capacity;
        int timePeriod;

        public Class(int id, int capacity, int timePeriod) {
            this.id = id;
            this.capacity = capacity;
            this.timePeriod = timePeriod;
        }
    }

    private static class Student {
        int id;
        int capacity;
        int startTime;
        int endTime;

        public Student(int id, int capacity, int startTime, int endTime) {
            this.id = id;
            this.capacity = capacity;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    private static Map<Integer, Class> classIdMap = new HashMap<>();
    private static Map<Integer, Student> studentIdMap = new HashMap<>();
    private static Map<Integer, List<Integer>> studentClassMap = new HashMap<>();
    private static Map<Integer, List<Integer>> enrollmentMap = new HashMap();

    public static String addClass(int id, int capacity, int time) {
        // If the class is added successfully,
        // return "Successfully added class ID".
        // Otherwise, return "Error adding class ID".

        if(classIdMap.containsKey(id)) {
            return "Error adding class " + id;
        }

        Class newClass = new Class(id, capacity, time);
        classIdMap.put(id, newClass);
        enrollmentMap.put(id, new ArrayList<Integer>());
        return "Successfully added class " + id;
    }

    public static String removeClass(int id) {
        //If the class is removed successfully,
        // return "Successfully removed class ID".
        // Otherwise, return "Error removing class ID".

        if(!classIdMap.containsKey(id)) {
            return "Error removing class " + id;
        }

        ArrayList<Integer> studentsEnrolledInClass = (ArrayList<Integer>) enrollmentMap.get(id);

        for(Integer studentId : studentsEnrolledInClass) {
            ArrayList<Integer> classesEnrolledByStudent = (ArrayList<Integer>) studentClassMap.get(studentId);
            classesEnrolledByStudent.remove(id);
            studentClassMap.put(studentId, classesEnrolledByStudent);
        }

        classIdMap.remove(id);
        return "Successfully removed class " + id;

    }

    public static String infoClass(int id) {
        // If the class does not exist,
        // return "Class ID does not exist".
        // If the class is empty,
        // return "Class ID is empty".
        // Otherwise, return the string
        // "Class ID has the following students: LIST"
        // where LIST is a sorted, comma-separated list
        // of student IDs corresponding to students currently
        // in the class.

        if(!classIdMap.containsKey(id)) {
            return "Class " + id + " does not exist";
        }

        ArrayList<Integer> studentsInClass = (ArrayList<Integer>) enrollmentMap.get(id);

        if(studentsInClass.isEmpty()) {
            return "Class " + id + " is empty";
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("Class " + id + " has the following students: ");

        Collections.sort(studentsInClass);

        for(int i = 0; i < studentsInClass.size(); i++) {
            buffer.append(studentsInClass.get(i));
            if(i != studentsInClass.size() - 1) {
                buffer.append(",");
            }
        }

        return buffer.toString();

    }

    public static String addStudent(int id, int capacity, int start, int end) {
        // If the student is added successfully,
        // return "Successfully added student ID".
        // Otherwise, return "Error adding student ID".

        if(studentIdMap.containsKey(id)) {
            return "Error adding student " + id;
        }

        Student newStudent = new Student(id, capacity, start, end);
        studentIdMap.put(id, newStudent);
        studentClassMap.put(id, new ArrayList<Integer>());

        return "Successfully added student " + id;
    }

    public static String removeStudent(int id) {
        // If the student is removed successfully,
        // return "Successfully removed student ID".
        // Otherwise, return "Error removing student ID".

        if(!studentIdMap.containsKey(id)) {
            return "Error removing student " + id;
        }

        ArrayList<Integer> classesEnrolledByStudent = (ArrayList<Integer>) studentClassMap.get(id);

        for(Integer classId : classesEnrolledByStudent) {
            ArrayList<Integer> studentsEnrolledInClass = (ArrayList<Integer>) enrollmentMap.get(classId);
            studentsEnrolledInClass.remove(id);
            enrollmentMap.put(classId, studentsEnrolledInClass);
        }

        studentIdMap.remove(id);
        return "Successfully removed student " + id;



    }

    public static String infoStudent(int id) {
        // If the student does not exist,
        // return "Student ID does not exist".
        // If the student is not taking any classes,
        // return "Student ID is not taking any classes".
        // Otherwise, return the string
        // "Student ID is taking the following classes: LIST"
        // where LIST is a sorted, comma-separated list of class IDs
        // corresponding to classes that the student is
        // currently taking.

        if(!studentIdMap.containsKey(id)) {
            return "Student " + id + " does not exist";
        }

        ArrayList<Integer> classesEnrolledByStudent = (ArrayList<Integer>) studentClassMap.get(id);

        if(classesEnrolledByStudent.isEmpty()) {
            return "Student " + id + " is not taking any classes";
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("Student " + id + " is taking the following classes: ");

        Collections.sort(classesEnrolledByStudent);

        for(int i = 0; i < classesEnrolledByStudent.size(); i++) {
            buffer.append(classesEnrolledByStudent.get(i));
            if(i != classesEnrolledByStudent.size() - 1) {
                buffer.append(",");
            }
        }

        return buffer.toString();
    }

    public static String enrollStudent(int studentId, int classId) {
        // If enrollment of the student in the class succeeded,
        // return "Number of free spots left in class CLASSID: FREESPOTS"
        // where FREESPOTS is the number of free spots left
        // in the class after the student enrolls.
        // Otherwise, return "Enrollment of student STUDENTID in class CLASSID failed".

        String failureMessage = "Enrollment of student " + studentId + " in class " + classId +" failed";

        if(!studentIdMap.containsKey(studentId)) {
            return failureMessage;
        }

        if(!classIdMap.containsKey(classId)) {
            return failureMessage;
        }

        ArrayList<Integer> classesEnrolledByStudent = (ArrayList<Integer>) studentClassMap.get(studentId);
        Student currentStudent = studentIdMap.get(studentId);

        Class currentClass = classIdMap.get(classId);
        ArrayList<Integer> studentsInClass = (ArrayList<Integer>) enrollmentMap.get(classId);

        if(classesEnrolledByStudent.contains(classId)
                || classesEnrolledByStudent.size() == currentStudent.capacity
                || studentsInClass.size() == currentClass.capacity
                || currentClass.timePeriod < currentStudent.startTime
                || currentClass.timePeriod > currentStudent.endTime) {
           return failureMessage;
        }

        for(Integer cid : classesEnrolledByStudent){
            Class c = classIdMap.get(cid);
            if(currentClass.timePeriod == c.timePeriod) {
                return failureMessage;
            }
        }

        classesEnrolledByStudent.add(classId);
        studentClassMap.put(studentId, classesEnrolledByStudent);

        studentsInClass.add(studentId);
        enrollmentMap.put(classId, studentsInClass);

        int remainingSpots = currentClass.capacity - studentsInClass.size();

        return "Number of free spots left in class " + classId + ": " + remainingSpots;

    }

    public static String unenrollStudent(int studentId, int classId) {
        // If unenrollment of the student in the class succeeded,
        // return "Number of free spots left in class CLASSID: FREESPOTS"
        // where FREESPOTS is the number of free spots left in the class
        // after the student unenrolls. Otherwise, return "Unenrollment
        // of student STUDENTID in class CLASSID failed".

        String failureMessage = "Unenrollment of student " + studentId + " in class " + classId +" " +
                "failed";

        if(!studentIdMap.containsKey(studentId)) {
            return failureMessage;
        }

        if(!classIdMap.containsKey(classId)) {
            return failureMessage;
        }

        ArrayList<Integer> classesEnrolledByStudent = (ArrayList<Integer>) studentClassMap.get(studentId);
        Student currentStudent = studentIdMap.get(studentId);

        Class currentClass = classIdMap.get(classId);
        ArrayList<Integer> studentsInClass = (ArrayList<Integer>) enrollmentMap.get(classId);

        if(!studentsInClass.contains(studentId)) {
            return failureMessage;
        }

        studentsInClass.remove(studentId);
        enrollmentMap.put(classId, studentsInClass);

        classesEnrolledByStudent.remove(classId);
        studentClassMap.put(studentId, classesEnrolledByStudent);

        int remainingSpots = currentClass.capacity - studentsInClass.size();

        return "Number of free spots left in class " + classId + ": " + remainingSpots;

    }

    public static String[] processCommands(String[] commands) {
        String[] ret = new String[commands.length];
        for(int i = 0; i < commands.length; i++) {
            StringTokenizer st = new StringTokenizer(commands[i]);
            String op = st.nextToken();
            if(op.equals("ADDCLASS")) {
                int id = Integer.parseInt(st.nextToken());
                int cap = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                ret[i] = addClass(id, cap, time);
            }
            else if(op.equals("REMOVECLASS")) {
                int id = Integer.parseInt(st.nextToken());
                ret[i] = removeClass(id);
            }
            else if(op.equals("INFOCLASS")) {
                int id = Integer.parseInt(st.nextToken());
                ret[i] = infoClass(id);
            }
            else if(op.equals("ADDSTUDENT")) {
                int id = Integer.parseInt(st.nextToken());
                int cap = Integer.parseInt(st.nextToken());
                int timeStart = Integer.parseInt(st.nextToken());
                int timeEnd = Integer.parseInt(st.nextToken());
                ret[i] = addStudent(id, cap, timeStart, timeEnd);
            }
            else if(op.equals("REMOVESTUDENT")) {
                int id = Integer.parseInt(st.nextToken());
                ret[i] = removeStudent(id);
            }
            else if(op.equals("INFOSTUDENT")) {
                int id = Integer.parseInt(st.nextToken());
                ret[i] = infoStudent(id);
            }
            else if(op.equals("ENROLLSTUDENT")) {
                int studentId = Integer.parseInt(st.nextToken());
                int classId = Integer.parseInt(st.nextToken());
                ret[i] = enrollStudent(studentId, classId);
            }
            else if(op.equals("UNENROLLSTUDENT")) {
                int studentId = Integer.parseInt(st.nextToken());
                int classId = Integer.parseInt(st.nextToken());
                ret[i] = unenrollStudent(studentId, classId);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;

        int _commands_size = Integer.parseInt(in.nextLine());
        String[] _commands = new String[_commands_size];
        String _commands_item;
        for(int _commands_i = 0; _commands_i < _commands_size; _commands_i++) {
            try {
                _commands_item = in.nextLine();
            } catch (Exception e) {
                _commands_item = null;
            }
            _commands[_commands_i] = _commands_item;
        }

        res = processCommands(_commands);
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }

}