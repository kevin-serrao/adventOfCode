package day21.part2;

import java.util.*;

public class AllergenDefinition {
    Hashtable<String, List<String>> candidateIngredientsByAllergen;
    Hashtable<String, String> confirmedIngredientByAllergen;
    Hashtable<String, Integer> numberOfUsesByUnconfirmedIngredient;

    public AllergenDefinition() {
        candidateIngredientsByAllergen = new Hashtable<String, List<String>>();
        confirmedIngredientByAllergen = new Hashtable<String, String>();
        numberOfUsesByUnconfirmedIngredient = new Hashtable<String, Integer>();
    }

    public void processLine(String line) {
        String[] ingredients = line.substring(0, line.indexOf(" (")).split(" ");
        String[] allergens = line.substring(line.indexOf("(") + 10, line.indexOf(")")).split(", ");
        addUsesOfIngredients(ingredients);
        for (int i = 0; i < allergens.length; i++) {
            updateCandidateIngredientsForAllergen(allergens[i], ingredients);
        }
    }

    private void addUsesOfIngredients(String[] ingredients) {
        for (int i =0; i < ingredients.length; i++) {
            String ingredient = ingredients[i];
            Collection<String> confirmedIngredients = confirmedIngredientByAllergen.values();
            if (confirmedIngredients.contains(ingredient)) {
                continue;
            }
            Integer uses = numberOfUsesByUnconfirmedIngredient.get(ingredient);
            if (uses != null) {
                numberOfUsesByUnconfirmedIngredient.put(ingredient, uses + 1);
            } else {
                numberOfUsesByUnconfirmedIngredient.put(ingredient, 1);
            }
        }
    }

    private void updateCandidateIngredientsForAllergen(String allergen, String[] ingredients) {
        List<String> candidateIngredientsList = candidateIngredientsByAllergen.get(allergen);
        if (candidateIngredientsList == null) {
            List<String> newCandidateIngredientsList =  new ArrayList<String>();
            for (int i = 0; i < ingredients.length; i++) {
                newCandidateIngredientsList.add(ingredients[i]);
            }
            candidateIngredientsByAllergen.put(allergen, newCandidateIngredientsList);
        } else {
            List<String> itemsToRemove = new ArrayList<String>();
            for (String candidateIngredient: candidateIngredientsList) {
                if (!Arrays.asList(ingredients).contains(candidateIngredient)) {
                    itemsToRemove.add(candidateIngredient);
                }
                Collection<String> confirmedIngredients = confirmedIngredientByAllergen.values();
                if (confirmedIngredients.contains(candidateIngredient)) {
                    itemsToRemove.add(candidateIngredient);
                }
            }
            candidateIngredientsList.removeAll(itemsToRemove);
            if (candidateIngredientsList.size() == 1) {
                System.out.println(allergen + " is definitely " + candidateIngredientsList.get(0));
                confirmedIngredientByAllergen.put(allergen, candidateIngredientsList.get(0));
                numberOfUsesByUnconfirmedIngredient.remove(candidateIngredientsList.get(0));
            }
        }
    }

    public boolean hasUnmappedAllergens() {
        Enumeration<String> keys = candidateIngredientsByAllergen.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (confirmedIngredientByAllergen.get(key) == null) {
                return true;
            }
        }
        return false;
    }

    public int sumUsesOfNonAllergenicIngredients() {
        int total = 0;
        Enumeration<String> keys = numberOfUsesByUnconfirmedIngredient.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            System.out.println(key + ": " + numberOfUsesByUnconfirmedIngredient.get(key));
            total += numberOfUsesByUnconfirmedIngredient.get(key);
        }
        return total;
    }


    public List<String> getCanonicalDangerousIngredientList() {
        List<String> allergens = new ArrayList<String>(confirmedIngredientByAllergen.keySet());
        java.util.Collections.sort(allergens);
        List<String> dangerousIngredients = new ArrayList<String>();
        for (String allergen: allergens) {
            dangerousIngredients.add(confirmedIngredientByAllergen.get(allergen));
        }
        return dangerousIngredients;
    }
}
