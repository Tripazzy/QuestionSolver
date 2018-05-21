package salsaboy.solver;

import java.util.HashMap;
import java.util.Map;

public class Interpretation {
    private String response = "...";
    private Map<String, Integer> integers = new HashMap<>();
    private Map<String, String> strings = new HashMap<>();
    
    public boolean isValid = false;
    public Interpretation(String commands) {
        this(commands.split("\n"));
    }
    private String constructArray(String[] array) {
        String complete = "";
        for (int i = 0; i < array.length; i++) {
            complete = complete + array[i];
            if (i == array.length - 1) {
                complete = complete + " ";
            }
        }
        return complete;
    }
    public Interpretation(String[] commands) {
        try {
            for (int i = 0; i < commands.length; i++) {
                commands[i] = commands[i].replace("calculate", "what is");
                commands[i] = commands[i].replace("?", "");
                commands[i] = commands[i].toLowerCase();
                String[] split = commands[i].split(" ");
                
                if (constructArray(commands).equals("what is the mass of the sun")) {
                    response = "Haha. Very funny.";
                    return;
                }
        
                isValid = true;
                if (split[1].equals("is")) {
                    if (split[0].equals("what")) {
                        if (split.length == 3) {
                            if (integers.containsKey(split[2])) {
                                response = integers.get(split[2]).toString();
                            } else if (strings.containsKey(split[2])) {
                                response = strings.get(split[2]).toString();
                            }
                        } else if (split.length == 5) {
                            if (split[3].equals("plus")) {
                                if (isNumber(split[2])) {
                                    if (isNumber(split[4])) {
                                        response = Integer.toString(
                                            Integer.parseInt(split[2]) + Integer.parseInt(split[4])
                                        );
                                    } else {
                                        response = Integer.toString(
                                            Integer.parseInt(split[2]) + integers.get(split[4])
                                        );
                                    }
                                } else {
                                    if (isNumber(split[4])) {
                                        response = Integer.toString(
                                            integers.get(split[2]) + Integer.parseInt(split[4])
                                        );
                                    } else {
                                        response = Integer.toString(
                                            integers.get(split[2]) + integers.get(split[4])
                                        );
                                    }
                                }
                            }
                        }
                    } else {
                        int value = Integer.parseInt(split[2]);
                        String varName = split[0];
    
                        integers.put(varName, value);
                    }
                } else if (split[0].equals("is")) {
                    if (split[2].equals("equal")) {
                        if (integers.containsKey(split[1])) {
                            if (integers.containsKey(split[4])) {
                                if (integers.get(split[1]).equals(integers.get(split[4]))) {
                                    response = "Yes";
                                } else {
                                    response = "No";
                                }
                            } else {
                                if (integers.get(split[1]).equals(Integer.parseInt(split[4]))) {
                                    response = "Yes";
                                } else {
                                    response = "No";
                                }
                            }
                        } else {
                            if (integers.containsKey(split[4])) {
                                if (integers.get(split[4]).equals(Integer.parseInt(split[1]))) {
                                    response = "Yes";
                                } else {
                                    response = "No";
                                }
                            } else {
                                if (Integer.parseInt(split[4]) == Integer.parseInt(split[1])) {
                                    response = "Yes";
                                } else {
                                    response = "No";
                                }
                            }
                        }
                    }
                } else {
                    isValid = false;
                }
            }
        } catch (Exception e) {
            response = "...";
        }
    }
    
    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return response;
    }
}
