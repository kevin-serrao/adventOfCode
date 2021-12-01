package day7.part1;

import java.util.*;

public class BagTypeWithParents {
    String name;
    List<BagTypeWithParents> parents = new ArrayList<BagTypeWithParents>();

    public BagTypeWithParents(String inputName, Hashtable<String, BagType> bagTypeRegistry) {
        name = inputName;
        Enumeration<String> parentNames = bagTypeRegistry.keys();
        while (parentNames.hasMoreElements()) {
            String parentName = parentNames.nextElement();
            BagType parentBagType = bagTypeRegistry.get(parentName);
            if (parentBagType.canContain(name)) {
                BagTypeWithParents parentBagTypeWithParents = new BagTypeWithParents(parentName, bagTypeRegistry);
                parents.add(parentBagTypeWithParents);
                System.out.println(parentName + " is a parent of " + name);
            }
        }
    }
} 
