package practice.leetcode;

import java.util.*;

public class CourseScheduleII {
    private class Node {
        int label;
        List<Node> prereqs;
        Node(int l) {
            label = l;
            prereqs = new ArrayList<>();
        }
    }

    HashSet<Integer> canTake = new LinkedHashSet<>();
    HashMap<Integer, Node> map = new HashMap<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) return new int[]{numCourses};

        for (int i=0; i<numCourses; i++) {
            map.put(i, new Node(i));
        }

        for (int i=0; i<prerequisites.length; i++) {
            Node courseNode = map.get(prerequisites[i][0]);
            courseNode.prereqs.add(map.get(prerequisites[i][1]));
        }

        for (int i=0; i<numCourses; i++) {
            if (canTake.contains(i)) continue;
            if (!canTakeCourse(i, new HashSet<>())) return new int[0];
            canTake.add(i);
        }

        int[] ret = new int[canTake.size()];
        int ctr = 0;
        for (int a: canTake) {
            ret[ctr] = a;
            ctr++;
        }

        return ret;
    }

    private boolean canTakeCourse(int course, HashSet<Integer> visited) {
        if (canTake.contains(course)) return true;
        if (visited.contains(course)) return false;

        Node courseNode = map.get(course);
        List<Node> prereqs = courseNode.prereqs;
        if (prereqs.size() == 0) {
            canTake.add(course);
            return true;
        }
        visited.add(course);
        for (Node prereq: prereqs) {
            if (!canTakeCourse(prereq.label, visited)) return false;
            canTake.add(prereq.label);
        }

        return true;
    }

    public static void main(String[] args) {
        CourseScheduleII cs2 = new CourseScheduleII();

        int[][] pr = new int[][] {{0,1}};

        for (int i: cs2.findOrder(2,pr)) {
            System.out.println(i + " ");
        }
    }

}
