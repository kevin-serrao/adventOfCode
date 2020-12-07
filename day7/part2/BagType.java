package day7.part2;

import java.util.*;

public class BagType {
    String name;
    List<InnerBagInfo> contents = new ArrayList<InnerBagInfo>();

    public BagType(String bagTypeString) {
        name = getNameFromBagTypeString(bagTypeString);
        getContentsFromBagTypeString(bagTypeString);
    }
    private String getNameFromBagTypeString(String bagTypeString) {
        return bagTypeString.split("bags contain")[0].trim();
    }

    private void getContentsFromBagTypeString(String bagTypeString) {
        String contentsString = bagTypeString.split("bags contain")[1].trim();
        String[] specificContentStrings = contentsString.split("bag");
        for (int i = 0; i < specificContentStrings.length - 1; i++) {
            String contentString = cleanContentString(specificContentStrings[i]);
            int spaceIndex = contentString.indexOf(" ");
            String quantityString = contentString.substring(0, spaceIndex);
            int quantity;
            if (quantityString.equals("no")) {
                continue;
            } else {
                quantity = Integer.parseInt(quantityString);
            }
            String nameString = contentString.substring(spaceIndex).trim();
            InnerBagInfo innerBagInfo = new InnerBagInfo(nameString, quantity);
            contents.add(innerBagInfo);
        }   
    }

    private String cleanContentString(String contentString) {
        for (int i = 0; i < contentString.length(); i++) {
            String str = contentString.substring(i, i + 1);
            if (str.matches("\\d+")) {
                return contentString.substring(i);
            }
        }
        return contentString;
    } 

    public boolean canContain(String bagTypeName) {
        // System.out.println(name + ": ");
        for (InnerBagInfo innerBagInfo: contents) {
            // System.out.println("       " + innerBagInfo.name);
            if (innerBagInfo.name.equals(bagTypeName)) {
                return true;
            }
        }
        return false;
    }

    public int getMinimumNumberOfBagsInside(Hashtable<String, BagType> bagTypeRegistry) {
        if (contents.size() == 0) {
            return 0;
        }
        int total = 0;
        for (InnerBagInfo bagInfo: contents) {
            total += bagInfo.quantity;
            System.out.println(bagInfo.name);
            System.out.println(bagInfo.quantity);
            BagType bt = bagTypeRegistry.get(bagInfo.name);
            total += bagInfo.quantity * bt.getMinimumNumberOfBagsInside(bagTypeRegistry);
        }
        return total;
    }
}


class InnerBagInfo {
    String name;
    int quantity;
    public InnerBagInfo(String inputName, int inputQuantity) {
        name = inputName;
        quantity = inputQuantity;
    }
}
