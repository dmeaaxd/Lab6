package structure;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class Coordinates {
    @NonNull
    @Getter
    private Integer x;
    @NonNull
    @Getter

    private Double y;

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
