package salsaboy.solver;

import java.util.HashMap;
import java.util.Map;

public class Interpretation {
    private String response = "...";
    private Map<String, Double> integers = new HashMap<>();
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
        
                isValid = true;
                if (split[1].equals("is")) {
                    if (split[0].equals("what")) {
                        if (split[3].equals("area")) {
                            if (integers.containsKey("height") && integers.containsKey("width")) {
                                response = Double.toString(integers.get("height") * integers.get("width"));
                            }
                        } else if (split[3].equals("volume")) {
                            if (integers.containsKey("height") && integers.containsKey("width") && integers.containsKey("depth")) {
                                response = Double.toString(integers.get("height") * integers.get("width") * integers.get("depth"));
                            }
                        } else {
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
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) + Double.parseDouble(split[4])
                                            );
                                        } else {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) + integers.get(split[4])
                                            );
                                        }
                                    } else {
                                        if (isNumber(split[4])) {
                                            response = Double.toString(
                                                integers.get(split[2]) + Double.parseDouble(split[4])
                                            );
                                        } else {
                                            response = Double.toString(
                                                integers.get(split[2]) + integers.get(split[4])
                                            );
                                        }
                                    }
                                } else if (split[3].equals("minus")) {
                                    if (isNumber(split[2])) {
                                        if (isNumber(split[4])) {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) - Double.parseDouble(split[4])
                                            );
                                        } else {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) - integers.get(split[4])
                                            );
                                        }
                                    } else {
                                        if (isNumber(split[4])) {
                                            response = Double.toString(
                                                integers.get(split[2]) - Double.parseDouble(split[4])
                                            );
                                        } else {
                                            response = Double.toString(
                                                integers.get(split[2]) - integers.get(split[4])
                                            );
                                        }
                                    }
                                } else if (split[3].equals("times")) {
                                    if (isNumber(split[2])) {
                                        if (isNumber(split[4])) {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) * Double.parseDouble(split[4])
                                            );
                                        } else {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) * integers.get(split[4])
                                            );
                                        }
                                    } else {
                                        if (isNumber(split[4])) {
                                            response = Double.toString(
                                                integers.get(split[2]) * Double.parseDouble(split[4])
                                            );
                                        } else {
                                            response = Double.toString(
                                                integers.get(split[2]) * integers.get(split[4])
                                            );
                                        }
                                    }
                                }
                            } else if (split.length == 6) {
                                if (split[3].equals("multiplied")) {
                                    if (isNumber(split[2])) {
                                        if (isNumber(split[5])) {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) * Double.parseDouble(split[5])
                                            );
                                        } else {
                                            response = Double.toString(
                                                Double.parseDouble(split[2]) * integers.get(split[5])
                                            );
                                        }
                                    } else {
                                        if (isNumber(split[5])) {
                                            response = Double.toString(
                                                integers.get(split[2]) * Double.parseDouble(split[5])
                                            );
                                        } else {
                                            response = Double.toString(
                                                integers.get(split[2]) * integers.get(split[5])
                                            );
                                        }
                                    }
                                } else if (split[3].equals("divided")) {
                                    if (isNumber(split[5])) {
                                        response = Double.toString(
                                            Double.parseDouble(split[2]) / Double.parseDouble(split[5])
                                        );
                                    } else {
                                        response = Double.toString(
                                            Double.parseDouble(split[2]) / integers.get(split[5])
                                        );
                                    }
                                } else {
                                    if (isNumber(split[5])) {
                                        response = Double.toString(
                                            integers.get(split[2]) / Double.parseDouble(split[5])
                                        );
                                    } else {
                                        response = Double.toString(
                                            integers.get(split[2]) / integers.get(split[5])
                                        );
                                    }
                                }
                            }
                        }
                    } else {
                        double value = Double.parseDouble(split[2]);
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
                                if (integers.get(split[1]).equals(Double.parseDouble(split[4]))) {
                                    response = "Yes";
                                } else {
                                    response = "No";
                                }
                            }
                        } else {
                            if (integers.containsKey(split[4])) {
                                if (integers.get(split[4]).equals(Double.parseDouble(split[1]))) {
                                    response = "Yes";
                                } else {
                                    response = "No";
                                }
                            } else {
                                if (Double.parseDouble(split[4]) == Double.parseDouble(split[1])) {
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
            Double.parseDouble(text);
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
