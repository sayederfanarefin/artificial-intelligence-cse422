
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cse422lab1 {

    public static ArrayList[] matrix;
    public static String[] city_names;
    public static String[] broken_routes;
    public static int city_counter = 0;
    public static int startCity = 0, endCity = 0;
    public static ArrayList<Integer> paths = new ArrayList<>();
    public static int path_cntr;
    public static ArrayList<String> tracker = new ArrayList<>();

    public static void main(String[] args) {

        try {
            Scanner nemesis = new Scanner(new File("input.txt"));
            int verti = nemesis.nextInt();
            int edgs = nemesis.nextInt();
            String start_node = nemesis.next();
            String end_node = nemesis.next();

            String br = nemesis.next();
            int number_of_broken_routes = Integer.parseInt(br);

            br = nemesis.nextLine();

            broken_routes = new String[number_of_broken_routes];

            for (int i = 0; i < broken_routes.length; i++) {
                broken_routes[i] = nemesis.nextLine();

            }

            matrix = new ArrayList[verti];
            city_names = new String[verti];

            for (int i = 0; i < verti; i++) {
                matrix[i] = new ArrayList();
            }

            String city_1;
            String city_2;
            int city_1_idx = 0;
            int city_2_idx = 0;

            for (int ii = 0; ii < edgs; ii++) {

                city_1 = nemesis.next();
                city_2 = nemesis.next();

                int co = 0;

                for (int i = 0; i < city_names.length; i++) {
                    if (city_1.equals(city_names[i])) {
                        city_1_idx = i;
                        co++;
                    } else if (city_2.equals(city_names[i])) {
                        city_2_idx = i;
                        co = co + 2;
                    }
                }
                if (co == 1) {
                    city_names[city_counter] = city_2;
                    city_2_idx = city_counter;
                    city_counter++;

                } else if (co == 2) {
                    city_names[city_counter] = city_1;
                    city_1_idx = city_counter;
                    city_counter++;
                } else if (co == 0) {
                    city_names[city_counter] = city_1;
                    city_1_idx = city_counter;
                    city_counter++;
                    city_names[city_counter] = city_2;
                    city_2_idx = city_counter;
                    city_counter++;
                }
                matrix[city_1_idx].add(city_2_idx);
                matrix[city_2_idx].add(city_1_idx);
            }

            for (int i = 0; i < matrix.length; i++) {
                if (city_names[i].equals(start_node)) {
                    startCity = i;
                } else if (city_names[i].equals(end_node)) {
                    endCity = i;
                }
            }

            DFS(matrix);

            for (int i = 0; i < tracker.size(); i++) {
                if ((tracker.get(i)).contains("Safe paths:")) {
                    System.out.println(tracker.get(i));
                }
            }
            for (int i = 0; i < tracker.size(); i++) {
                if ((tracker.get(i)).contains("Broken paths:")) {
                    System.out.println(tracker.get(i));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("file not found bro!");
        }
    }

    public static void DFS(Object[] G) {

        paths.add(startCity);
        path_cntr = paths.size() - 1;
        mark_visit(startCity);
    }

    public static void mark_visit(int node) {
        int v;
        for (int s = 0; s < ((ArrayList<Integer>) matrix[node]).size(); s++) {
            v = ((ArrayList<Integer>) matrix[node]).get(s);
            if (v != endCity) {
                if (!paths.contains(v)) {
                    paths.add(v);
                    path_cntr = paths.size() - 1;
                    mark_visit(v);
                }
            } else if (v == endCity) {
                if (!paths.contains(v)) {
                    paths.add(v);
                    path_cntr = paths.size() - 1;

                    String routes = "";
                    for (int i = 0; i < paths.size(); i++) {
                        for (int j = 0; j < city_names.length; j++) {
                            if (paths.get(i) == j) {
                                routes += city_names[j] + " ";
                            }
                        }
                    }
                    if (routes.contains(broken_routes[0]) || routes.contains(broken_routes[1])) {
                        routes = "Broken paths: " + routes;
                    } else {
                        routes = "Safe paths: " + routes;
                    }
                    tracker.add(routes);
                    paths.remove(path_cntr);
                    path_cntr = paths.size() - 1;
                    break;
                }
            }
        }
        paths.remove(path_cntr);
        path_cntr--;
    }

}
