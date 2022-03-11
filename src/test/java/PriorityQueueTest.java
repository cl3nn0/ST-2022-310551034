import java.util.PriorityQueue;
import java.util.stream.Stream;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PriorityQueueTest {
    public static Stream<Arguments> streamProvider() {
        /* correct */
        return Stream.of(
                Arguments.of(new int[] {1, 0, -1}, new int[] {-1, 0, 1}),
                Arguments.of(new int[] {3, 8, 7, 4}, new int[] {3, 4, 7, 8}),
                Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}),
                Arguments.of(new int[] {-1, -2, -3, -4, -5}, new int[] {-5, -4, -3, -2, -1}),
                Arguments.of(new int[] {0, 6, 9, -2}, new int[] {-2, 0, 6, 9})
        );
        /* fail */
        // return Stream.of(
        //         Arguments.of(new int[] {1, 0, -1}, new int[] {0, 1, 2}),
        //         Arguments.of(new int[] {3, 8, 7, 4}, new int[] {4, 5, 8, 9}),
        //         Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {2, 3, 4, 5, 6}),
        //         Arguments.of(new int[] {-1, -2, -3, -4, -5}, new int[] {-4, -3, -2, -1, 0}),
        //         Arguments.of(new int[] {0, 6, 9, -2}, new int[] {-1, 1, 7, 10})
        // );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        int[] result = new int[random_array.length];
        for (index = 0; index < random_array.length; index++) {
            test.add(random_array[index]);
        }
        for (index = 0; index < random_array.length; index++) {
            result[index] = test.poll();
        }
        assertArrayEquals(correct_array, result);
    }

    @Test
    public void whenExceptionThrown_IllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue<Integer>(-1, null);
        });
    }

    @Test
    public void whenExceptionThrown_NullPointerException_addNULL() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue<Integer>().add(null);
        });
    }

    @Test
    public void whenExceptionThrown_NoSuchElementException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            new PriorityQueue<Integer>().remove();
        });
    }
}