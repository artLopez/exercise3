package edu.csumb.app;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.esotericsoftware.yamlbeans.*;

public class Main {
    public static void main( String[] args ) throws IOException {
        Integer i = Integer.valueOf(args[0]);
        String o = args[1];
        Double v = Double.valueOf(args[2]);
        String g = Main.class.getClassLoader().getResource("features.yml").getFile();
        YamlReader r = new YamlReader(new FileReader(g));
        Map<String, Map<String, String>> fs = r.read(Map.class);
        Map<String, Boolean> s = new HashMap<>();
        for (String n : fs.keySet()) {
            Map<String, String> f = fs.get(n);
            s.put(n, (!f.containsKey("os") || f.get("os").equals(o))
                    && (!f.containsKey("percentage") || i % 100 < Integer.valueOf(f.get("percentage"))));
        }
        for (Map.Entry<String, Boolean> f : s.entrySet()) {
            System.out.println(f.getKey() + ":" + f.getValue());
        }
    }
}
