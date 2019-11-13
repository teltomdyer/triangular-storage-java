package triangular_storage;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TriangularStorageTest {

    private TriangularStorage triangularStorage = new TriangularStorage();

    private final Map<Integer, Long> EXPECTED_SEED_0_VALUES = new HashMap<>();
    {
        EXPECTED_SEED_0_VALUES.put(1, 1L);
        EXPECTED_SEED_0_VALUES.put(2, 3L);
        EXPECTED_SEED_0_VALUES.put(3, 6L);
        EXPECTED_SEED_0_VALUES.put(612, 187578L);
        EXPECTED_SEED_0_VALUES.put(11111, 61732716L);
        EXPECTED_SEED_0_VALUES.put(100000, 5000050000L);
    }

    @Test
    public void triangularValuesDefaultSeed() {
        var tailCall = triangularStorage.getNthTriangular(1);
        assert tailCall.execute().equals(EXPECTED_SEED_0_VALUES.get(1));

        tailCall = triangularStorage.getNthTriangular(2);
        assert tailCall.execute().equals(EXPECTED_SEED_0_VALUES.get(2));

        tailCall = triangularStorage.getNthTriangular(3);
        assert tailCall.execute().equals(EXPECTED_SEED_0_VALUES.get(3));

        tailCall = triangularStorage.getNthTriangular(612);
        assert tailCall.execute().equals(EXPECTED_SEED_0_VALUES.get(612));

        tailCall = triangularStorage.getNthTriangular(11111);
        assert tailCall.execute().equals(EXPECTED_SEED_0_VALUES.get(11111));

        tailCall = triangularStorage.getNthTriangular(100000);
        assert tailCall.execute().equals(EXPECTED_SEED_0_VALUES.get(100000));
    }

    @Test
    public void triangularValuesCustomSeeded() {
        var tailCall = triangularStorage.getNthTriangular(5, 1, 1);
        assert tailCall.execute().equals(11L);

        tailCall = triangularStorage.getNthTriangular(3, 3, 2);
        assert tailCall.execute().equals(8L);

        tailCall = triangularStorage.getNthTriangular(4, 6, 3);
        assert tailCall.execute().equals(18L);
    }

    @Test
    public void getIdAtXY() {
        assert triangularStorage.answer(2, 2).equals("5");
        assert triangularStorage.answer(2, 4).equals("12");
        assert triangularStorage.answer(4, 1).equals("10");
        assert triangularStorage.answer(4, 2).equals("14");
        assert triangularStorage.answer(612, 231).equals("354673");
        assert triangularStorage.answer(11111, 11111).equals("246886421");
        assert triangularStorage.answer(100000, 100000).equals("19999800001");
    }
}
