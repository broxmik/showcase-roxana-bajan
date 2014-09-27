package ro.brox.coding;

import java.util.*;

/**
 * Created by Roxana on 27/09/2014.
 */
public class VoxCodei {

    private static final String WAIT = "WAIT";

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // width of the firewall grid
        int height = in.nextInt(); // height of the firewall grid
        in.nextLine();
        Map<Integer, ArrayList<Integer>> sNodes = new HashMap<Integer, ArrayList<Integer>>();
        Map<Integer, ArrayList<Integer>> nNodes = new HashMap<Integer, ArrayList<Integer>>();
        Map<Integer, ArrayList<Integer>> freeNodes = new HashMap<Integer, ArrayList<Integer>>();
        int sNodesNo = 0;
        for (int i = 0; i < height; i++) {
            sNodes.put(i, new ArrayList<Integer>());
            nNodes.put(i, new ArrayList<Integer>());
            freeNodes.put(i, new ArrayList<Integer>());
            String mapRow = in.next(); // one line of the firewall grid
            for(int j=0; j < mapRow.length(); j++){
                char node = mapRow.charAt(j);
                if(node == '@'){
                    sNodes.get(i).add(j);
                    sNodesNo++;
                } else if(node == '#'){
                    nNodes.get(i).add(j);
                } else {
                    freeNodes.get(i).add(j);
                }
            }
            in.nextLine();
        }

        int rounds = in.nextInt(); // number of rounds left before the end of the game
        int bombs = in.nextInt(); // number of bombs left
        in.nextLine();

        Queue<String> instr = new ArrayDeque<String>();
        if(bombs == sNodesNo) {
            for (Map.Entry<Integer, ArrayList<Integer>> sNode : sNodes.entrySet()) {
                int line = sNode.getKey();
                getInstructionsForLine(nNodes, freeNodes, instr, sNode, line, height);
            }
        } else {
            Map<String, Integer> wheights = new HashMap<String, Integer>();
            for(int line = 0; line<height; line++){
                for (Integer freePosition : freeNodes.get(line)) {
                    int count = 0;
                    for (Map.Entry<Integer, ArrayList<Integer>> entry : sNodes.entrySet()) {
                        int sLine = entry.getKey();
                        for (Integer sPosition : entry.getValue()) {
                            if(checksPosition(line, freePosition, sLine, sPosition, nNodes)){
                                count++;
                            }
                        }
                    }
                    wheights.put(freePosition + " " + line, count);
                }
            }
            for(int b=0; b<bombs; b++){
                for (Map.Entry<String, Integer> entry : wheights.entrySet()) {
                    if(entry.getValue()>3){
                        instr.add(entry.getKey());
                    }
                }
            }
        }

        // game loop
        while (true) {
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            if(rounds == 0){
                break;
            }

            if(bombs == 0 || instr.size()==0){
                System.out.println(WAIT);
            } else {
                System.out.println(instr.poll());
            }

            rounds = in.nextInt(); // number of rounds left before the end of the game
            bombs = in.nextInt(); // number of bombs left
            in.nextLine();
        }
    }

    private static boolean checksPosition(int line, Integer freePosition, int sLine, Integer sPosition, Map<Integer, ArrayList<Integer>> nNodes) {
        if (freePosition >= sPosition - 3 && freePosition <= sPosition + 3 && line == sLine) {
            return true;
        }
        if(freePosition.intValue() == sPosition.intValue() ){
            boolean ok = true;
            if(line < sLine){
                for (int k=line+1; k<sLine; k++){
                    if(nNodes.get(k).contains(sPosition)){
                        ok = false;
                    }
                }
            }else {
                for (int k=line-1; k>sLine; k--){
                    if(nNodes.get(k).contains(sPosition)){
                        ok = false;
                    }
                }
            }
            return ok;
        }
        return false;
    }

    private static void getInstructionsForLine(Map<Integer, ArrayList<Integer>> nNodes, Map<Integer, ArrayList<Integer>> freeNodes, Queue<String> instr, Map.Entry<Integer, ArrayList<Integer>> sNode, int currentLine, int height) {
        ArrayList<Integer> nPositions = nNodes.get(currentLine);
        ArrayList<Integer> freePositions = freeNodes.get(currentLine);
        ArrayList<Integer> sNodeList = sNode.getValue();
        if(sNodeList.size()>0){
            //find a position for the bomb
            //right, left
            for (Integer sPosition : sNodeList) {
                // find right or left
                boolean found = findRightOrLeft(instr, currentLine, nPositions, freePositions, sPosition, false);

                if(!found){
                    // find up or down
                    findUpOrDown(nNodes, freeNodes, instr, currentLine, height, sPosition);
                }
            }
        }
    }

    private static void findUpOrDown(Map<Integer, ArrayList<Integer>> nNodes, Map<Integer, ArrayList<Integer>> freeNodes, Queue<String> instr, int currentLine, int height, Integer sPosition) {
        for(int line=Math.max(currentLine-3, 0); line<Math.min(currentLine + 3,height); line++){
            if(line == currentLine || instr.contains(sPosition + " " + line)){
                continue;
            }
            ArrayList<Integer> lineFreePositions = freeNodes.get(line);
            if(lineFreePositions.contains(sPosition)){
                boolean ok = true;
                if(line < currentLine){
                    for (int k=line+1; k<currentLine; k++){
                        if(nNodes.get(k).contains(sPosition)){
                            ok = false;
                        }
                    }
                }else {
                    for (int k=line-1; k>currentLine; k--){
                        if(nNodes.get(k).contains(sPosition)){
                            ok = false;
                        }
                    }
                }
                if(ok){
                    instr.add(sPosition + " " + line);
                    break;
                }
            }
        }
    }

    private static boolean findRightOrLeft(Queue<String> instr, int currentLine, ArrayList<Integer> nPositions, ArrayList<Integer> freePositions, Integer sPosition, boolean found) {
        if(nPositions.size()>0){
            if(freePositions.size()>0) {
                freeSearch:for (Integer freePosition : freePositions) {
                    if(instr.contains(freePosition + " " + currentLine)){
                        continue;
                    }
                    for (Integer nPosition : nPositions) {
                        if((freePosition<nPosition && sPosition>nPosition)
                                || (freePosition>nPosition && sPosition<nPosition)){
                            continue freeSearch;
                        }
                    }
                    if (freePosition >= sPosition - 3 && freePosition <= sPosition + 3) {
                        instr.add(freePosition + " " + currentLine);
                        found = true;
                        break;
                    }
                }
            }
        } else if(freePositions.size()>0){
            for (Integer freePosition : freePositions) {
                if(instr.contains(freePosition + " " + currentLine)){
                    continue;
                }
                if(freePosition >= sPosition - 3 && freePosition <= sPosition + 3){
                    instr.add(freePosition + " " + currentLine);
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
