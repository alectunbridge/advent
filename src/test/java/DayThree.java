import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DayThree {

    private static final Pattern PATTERN = Pattern.compile("^#(\\d*) @ (\\d*),(\\d*): (\\d*)x(\\d*)$");
    private int[][] fabric;
    private List<Claim> claims;

    Claim parseClaim(String input) {
        Claim claim = new Claim();
        Matcher matcher = PATTERN.matcher(input);
        if(matcher.matches()) {
            claim.setId(Integer.parseInt(matcher.group(1)));
            claim.setDistanceFromLeft(Integer.parseInt(matcher.group(2)));
            claim.setDistanceFromTop(Integer.parseInt(matcher.group(3)));
            claim.setWidth(Integer.parseInt(matcher.group(4)));
            claim.setHeight(Integer.parseInt(matcher.group(5)));
        }
        return claim;
    }


    int[][] parseClaims(String[] input) {
        int maxWidth = 0;
        int maxHeight = 0;
        claims = new ArrayList<>();
        for (String line : input) {
            Claim claim = parseClaim(line);
            claims.add(claim);
            int width = claim.getDistanceFromLeft() + claim.getWidth();
            int height = claim.getDistanceFromTop() + claim.getHeight();
            if(width>maxWidth){
                maxWidth = width;
            }
            if(height>maxHeight){
                maxHeight = height;
            }
        }
        fabric = new int[maxWidth][maxHeight];

        for (Claim claim : claims) {
            drawClaim(claim);
        }

        return fabric;
    }

    private int[][] drawClaim(Claim claim) {
        for (int col = claim.getDistanceFromLeft(); col < claim.getDistanceFromLeft()+claim.getWidth(); col++) {
            for (int row = claim.getDistanceFromTop(); row < claim.getDistanceFromTop()+claim.getHeight() ; row++) {
                fabric[col][row]++;
            }
        }
        return fabric;
    }

    int calculateOverlap() {
        int overlappingCells = 0;
        for (int[] cols : fabric) {
            overlappingCells += Arrays.stream(cols).filter(i->i>1).count();
        }
        return overlappingCells;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int[] column : fabric) {
            for (int distanceFromTop = 0; distanceFromTop < fabric[0].length; distanceFromTop++) {
                result.append(column[distanceFromTop]);
            }
            result.append("\n");
        }
        return result.toString();
    }


    boolean checkClaimForNoOverlap(Claim claim) {
        for(int x = 0; x < claim.getWidth(); x++){
            for (int y = 0; y < claim.getHeight(); y++) {
                if(fabric[x+claim.getDistanceFromLeft()][y+claim.getDistanceFromTop()] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    Claim findClaimWithNoOverLap() {
        for (Claim claim : claims) {
            if(checkClaimForNoOverlap(claim)){
                return claim;
            }
        }

        return null;
    }
}
