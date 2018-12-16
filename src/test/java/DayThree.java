import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DayThree {

    private static final Pattern PATTERN = Pattern.compile("^#(\\d*) @ (\\d*),(\\d*): (\\d*)x(\\d*)$");
    private int[][] fabric;

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
        List<Claim> claims = new ArrayList<>();
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
}
