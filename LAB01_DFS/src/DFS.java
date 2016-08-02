
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DFS {

    public static ArrayList[] al;
    public static String [] city;
    public static String[] brR;
    public static int cno=0;
    public static int startCity=0,endCity=0;
    public static ArrayList<Integer> paths =new ArrayList<>();
    public static int pathc;
    public static ArrayList<String> sb= new ArrayList<>();
    
    public static void main(String[] args) throws FileNotFoundException {
      
        Scanner rex = new Scanner(new File("dfs.txt"));

        int ver = rex.nextInt();
        int edg = rex.nextInt();
        
        String start = rex.next();
        String end = rex.next();
        
        String br=rex.next();
        int brC = Integer.parseInt(br);
        br= rex.nextLine(); //for nextLine Skipping
        brR = new String[brC];
        for (int i = 0; i < brR.length; i++) {
            brR[i]=rex.nextLine();
        }

        al = new ArrayList[ver];
        city = new String[ver];
        for (int i = 0; i < ver; i++) {
            al[i] = new ArrayList();
        }
        String a, b;
        int w=0,x=0;
        for (int c = edg; c > 1; c--) {
            a = rex.next();
            b = rex.next();
            int co=0;
            for (int i = 0; i < city.length; i++) {
                if (a.equals(city[i])) {
                    w=i;
                    co+=1;
            }else if (b.equals(city[i])) {
                 x=i;
                 co+=2;
                }
            }
            if (co==1) {
                city[cno]=b;
                x=cno;
                cno++;
            }else if (co==2) {
                city[cno]=a;
                w=cno;
                cno++;
            }else if (co==0) {
                city[cno]=a;
                w=cno;
                cno++;
                city[cno]=b;
                x=cno;
                cno++;
            }
            (al[w]).add(x);
            (al[x]).add(w);
        }

        for (int i = 0; i < al.length; i++) {
            if (city[i].equals(start)) {
                startCity=i;
            }else if (city[i].equals(end)) {
                endCity=i;
            }
        }
      
        DFS(al);
        
        for (int i = 0; i < sb.size(); i++) {
            if ((sb.get(i)).contains("Safe paths:")) {
                System.out.println(sb.get(i));
            }
        }
        for (int i = 0; i < sb.size(); i++) {
            if ((sb.get(i)).contains("Broken paths:")) {
                System.out.println(sb.get(i));
            }
        }
    }
    
    public static void DFS(Object[] G) {

        paths.add(startCity);
        pathc=paths.size()-1;
        DFS_Visit(startCity);
    }

    public static void DFS_Visit(int u) {
        int v;
        for (int s = 0; s < ((ArrayList<Integer>) al[u]).size(); s++) {
            v = ((ArrayList<Integer>) al[u]).get(s);
                if (v!=endCity) {
                    if ( !paths.contains(v)) {
                        paths.add(v);
                        pathc=paths.size()-1;
                        DFS_Visit(v);
                    }
                }else {
                    if (v==endCity) {
                        if (!paths.contains(v) ) {
                            paths.add(v);
                            pathc=paths.size()-1;

                            String routes="";
                            for (int i = 0; i < paths.size(); i++) {
                                for (int j = 0; j < city.length; j++) {
                                    if (paths.get(i)==j) {
                                        routes+=city[j]+" ";
                                    }
                                }
                            }
                            if (routes.contains(brR[0]) || routes.contains(brR[1])) {
                                routes="Broken paths: "+routes;
                            }else{
                                routes="Safe paths: "+routes;
                            }
                            sb.add(routes);
                            paths.remove(pathc);
                            pathc=paths.size()-1;
                            break;
                        }
                    }
                }
        }
        paths.remove(pathc);
        pathc--;
    }
    
}
