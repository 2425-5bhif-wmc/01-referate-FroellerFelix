package at.htlleonding.boundary;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.LinkedList;

@Path("/palindrome")
@Produces("application/json")
public class PalindromeResource {
    private final MeterRegistry registry;
    private final LinkedList<String> list = new LinkedList<>();

    public PalindromeResource(MeterRegistry registry) {
        this.registry = registry;
        registry.gaugeCollectionSize("palindrome.list.size", Tags.empty(), list);
    }

    @GET
    @Path("counter/check/{input}")
    public boolean checkPalindromeCounter(String input) {
        list.add(input);

        registry.counter("palindrome.counter").increment();
        boolean result = internalCheckPalindrome(input);
        return result;
    }

    @GET
    @Path("timer/check/{input}")
    public boolean checkPalindromeAndTimer(String input) {
        list.add(input);

        Timer.Sample sample = Timer.start(registry);
        boolean result = internalCheckPalindrome(input);
        sample.stop(registry.timer("palindrome.timer"));
        return result;
    }

    private boolean internalCheckPalindrome(String input) {
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @DELETE
    @Path("empty-list")
    public void emptyList() {
        list.clear();
    }
}
